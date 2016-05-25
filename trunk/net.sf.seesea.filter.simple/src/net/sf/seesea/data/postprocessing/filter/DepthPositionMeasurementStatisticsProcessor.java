/**
Copyright (c) 2013-2015, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package net.sf.seesea.data.postprocessing.filter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.sf.seesea.data.postprocessing.process.IStatisticsPreprocessor;
import net.sf.seesea.data.postprocessing.process.StatisticsException;
import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GNSSMeasuredPosition;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.CompositeMeasurement;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.track.api.ITrackFileProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.SensorDescription;
import net.sf.seesea.track.api.data.SensorDescriptionUpdateRate;
import net.sf.seesea.track.api.exception.ProcessingException;

/**
 *  
 * @param <T>
 */
public class DepthPositionMeasurementStatisticsProcessor<T extends Measurement> implements IStatisticsPreprocessor {

	private Map<Class<T>, Map<String, Map<String, Map<Long, Integer>>>> sensorRateDistribution;

	private Map<Class<?>, Map<String, Map<String, Long>>> lastMeasurement;

	private Map<Class<?>, Map<String, Map<String, Long>>> lastMeasurementCount;

	private Map<SensorDescription<MeasuredPosition3D>, Map<Boolean, Integer>> hdopAvailability;

	private Map<SensorDescription<MeasuredPosition3D>, Integer> positionPrecisionDigits;

	private long lastMeasurmentTime;

	private final ITrackFileProcessor trackFileProcessor;

	public DepthPositionMeasurementStatisticsProcessor(ITrackFileProcessor trackFileProcessor) {
		this.trackFileProcessor = trackFileProcessor;
		trackFileProcessor.setMeasurementProcessor(this);
		lastMeasurmentTime = 0;
		sensorRateDistribution = new HashMap<Class<T>, Map<String, Map<String, Map<Long, Integer>>>>();
		lastMeasurement = new HashMap<Class<?>, Map<String, Map<String, Long>>>();
		lastMeasurementCount = new HashMap<Class<?>, Map<String, Map<String, Long>>>();
		hdopAvailability = new HashMap<SensorDescription<MeasuredPosition3D>, Map<Boolean, Integer>>();
		positionPrecisionDigits = new HashMap<SensorDescription<MeasuredPosition3D>, Integer>();
	}

	/**
	 * 
	 * @param results
	 * @param messageType
	 */
	public void processMeasurements(List<Measurement> results,
			String messageType, long sourceTrackIdentifier,
			GeoBoundingBox boundingBox, ITrackFile trackfile) {
		// update rate for depth, position, velocity
		for (Measurement measurement : results) {
			if (measurement instanceof CompositeMeasurement) {
				processMeasurements(
						((CompositeMeasurement) measurement).getMeasurements(),
						messageType, sourceTrackIdentifier, boundingBox, null);
			} else if (measurement instanceof MeasuredPosition3D
					&& (measurement.getTime() != null || !trackFileProcessor.hasTimedMeasurments()) && measurement.isValid()) {
				MeasuredPosition3D measuredPosition3D = (MeasuredPosition3D) measurement;
				Map<String, Map<String, Map<Long, Integer>>> sensorId2Distribution = getSensorId2MessageType((Class<T>) MeasuredPosition3D.class);
				Map<String, Map<Long, Integer>> messageType2Distrubution = getMessageType2Distribution(
						measurement.getSensorID(), sensorId2Distribution);
				Map<String, Map<String, Long>> sensorID2MessageType = getLastMeasurement4Sensor((Class<T>) MeasuredPosition3D.class);
				Map<String, Long> sensorId2Time = getMessageType2Time(
						measurement, sensorID2MessageType);
				Map<Long, Integer> updateDistribution = getDistribution(
						messageType, messageType2Distrubution);
				Long lastMeasurment = getLastMeasurment4Sensor(messageType,
						sensorId2Time);

				long measurmentTime = measurement.getTime().getTime();
				long timeDifference = measurmentTime - lastMeasurment;
				Integer count = updateDistribution.get(timeDifference);
				if (count == null) {
					count = 0;
				}
				updateDistribution.put(timeDifference, count + 1);
				sensorId2Time.put(messageType, measurmentTime);

				// check hdop availablity in sentence
				SensorDescription<MeasuredPosition3D> sensorDescription = new SensorDescription<MeasuredPosition3D>(
						MeasuredPosition3D.class, measurement.getSensorID(),
						messageType);
				Map<Boolean, Integer> hdopDistribution = hdopAvailability
						.get(sensorDescription);
				if (hdopDistribution == null) {
					hdopDistribution = new HashMap<Boolean, Integer>();
					hdopAvailability.put(sensorDescription, hdopDistribution);
				}
				if (measurement instanceof GNSSMeasuredPosition
						&& ((GNSSMeasuredPosition) measurement).getHdop() > 0.0) {
					Integer countHdop = hdopDistribution.get(true);
					if (countHdop == null) {
						countHdop = 0;
					}
					hdopDistribution.put(true, countHdop + 1);
				} else {
					Integer countHdop = hdopDistribution.get(false);
					if (countHdop == null) {
						countHdop = 0;
					}
					hdopDistribution.put(true, countHdop + 1);
				}

				// set measurement distribution
				Integer precision = positionPrecisionDigits
						.get(sensorDescription);
				if (precision == null) {
					positionPrecisionDigits.put(sensorDescription,
							measuredPosition3D.getPrecision());
				} else {
					positionPrecisionDigits.put(
							sensorDescription,
							Math.max(precision,
									measuredPosition3D.getPrecision()));
				}

				lastMeasurmentTime = measurmentTime;
			} else if ((measurement instanceof Depth
					|| measurement instanceof Heading || measurement instanceof Speed)
					&& measurement.isValid()) {
				// distribution
				Map<String, Map<String, Map<Long, Integer>>> sensorId2MessageType = getSensorId2MessageType((Class<T>) measurement
						.getClass());
				Map<String, Map<Long, Integer>> messageType2Distrubution = getMessageType2Distribution(
						measurement.getSensorID(), sensorId2MessageType);
				Map<Long, Integer> updateDistribution = getDistribution(
						messageType, messageType2Distrubution);

				// message time
				Map<String, Map<String, Long>> sensorID2MessageType = getLastMeasurement4Sensor((Class<T>) measurement
						.getClass());
				Map<String, Long> sensorId2Time = getMessageType2Time(
						measurement, sensorID2MessageType);

				// message count
				Map<String, Map<String, Long>> sensorID2MessageTypeCount = getSensorID2Count(measurement
						.getClass());
				Map<String, Long> messageType2Count = getMessageType2Time(
						measurement, sensorID2MessageTypeCount);

				Long lastMeasurment = getLastMeasurment4Sensor(messageType,
						sensorId2Time);

				long measurmentTime;
				// no time coded for measurement, attach to other measurements
				// that have time
				if (measurement.getTime() == null) {
					measurmentTime = lastMeasurmentTime;
				} else {
					measurmentTime = measurement.getTime().getTime();
				}
				long timeDifference = measurmentTime - lastMeasurment;
				if (timeDifference == 0) {
					// raise count
					// Map<String, Map<String, Long>> sensorId2MessageType =
					// getSensorID2Count();
					Long measurementCountInSameTimeframe = getLastMeasurment4Sensor(
							messageType, messageType2Count);
					messageType2Count.put(messageType,
							measurementCountInSameTimeframe + 1);
				} else {
					// reset count
					// Map<String, Map<String, Long>> sensorId2MessageType =
					// getSensorID2Count();
					Long measurementCountInSameTimeframe = getLastMeasurment4Sensor(
							messageType, messageType2Count);
					measurementCountInSameTimeframe++;
					timeDifference = (int) (timeDifference / measurementCountInSameTimeframe);
					Integer count = updateDistribution.get(timeDifference);
					if (count == null) {
						count = 0;
					}
					updateDistribution.put(timeDifference, count + 1);
					messageType2Count.remove(messageType);
				}
				sensorId2Time.put(messageType, measurmentTime);
				lastMeasurmentTime = measurmentTime;

			}
		}

	}

	private Map<String, Long> getMessageType2Time(Measurement measurement,
			Map<String, Map<String, Long>> sensorID2MessageType) {
		Map<String, Long> sensorId2Time = sensorID2MessageType.get(measurement
				.getSensorID());
		if (sensorId2Time == null) {
			sensorId2Time = new HashMap<String, Long>();
			sensorID2MessageType.put(measurement.getSensorID(), sensorId2Time);
		}
		return sensorId2Time;
	}

	private Map<String, Map<Long, Integer>> getMessageType2Distribution(
			String messageType,
			Map<String, Map<String, Map<Long, Integer>>> sensorId2Distribution) {
		Map<String, Map<Long, Integer>> messageType2Distrubution = sensorId2Distribution
				.get(messageType);
		if (messageType2Distrubution == null) {
			messageType2Distrubution = new HashMap<String, Map<Long, Integer>>();
			sensorId2Distribution.put(messageType, messageType2Distrubution);
		}
		return messageType2Distrubution;
	}

	private Long getLastMeasurment4Sensor(String messageType,
			Map<String, Long> sensorId2Time) {
		Long lastMeasurment = sensorId2Time.get(messageType);
		if (lastMeasurment == null) {
			lastMeasurment = 0L;
		}
		return lastMeasurment;
	}

	private <T extends Measurement> Map<String, Map<String, Long>> getSensorID2Count(
			Class<T> clazz) {
		Map<String, Map<String, Long>> sensorId2Message = lastMeasurementCount
				.get(clazz);
		if (sensorId2Message == null) {
			sensorId2Message = new HashMap<String, Map<String, Long>>();
			lastMeasurementCount.put(clazz, sensorId2Message);
		}
		return sensorId2Message;
	}

	private Map<Long, Integer> getDistribution(String messageType,
			Map<String, Map<Long, Integer>> sensorId2Distribution) {
		Map<Long, Integer> updateDistribution = sensorId2Distribution
				.get(messageType);
		if (updateDistribution == null) {
			updateDistribution = new TreeMap<Long, Integer>();
			sensorId2Distribution.put(messageType, updateDistribution);
		}
		return updateDistribution;
	}

	private Map<String, Map<String, Long>> getLastMeasurement4Sensor(
			Class<T> clazz) {
		Map<String, Map<String, Long>> sensorId2Time = lastMeasurement
				.get(clazz);
		if (sensorId2Time == null) {
			sensorId2Time = new HashMap<String, Map<String, Long>>();
			lastMeasurement.put(clazz, sensorId2Time);
		}
		return sensorId2Time;
	}

	private Map<String, Map<String, Map<Long, Integer>>> getSensorId2MessageType(
			Class<T> clazz) {
		Map<String, Map<String, Map<Long, Integer>>> sensorId2Distribution = sensorRateDistribution
				.get(clazz);
		if (sensorId2Distribution == null) {
			sensorId2Distribution = new HashMap<String, Map<String, Map<Long, Integer>>>();
			sensorRateDistribution.put(clazz, sensorId2Distribution);
		}
		return sensorId2Distribution;
	}

	protected <T extends Measurement> long getUpdateRate(Class<T> class1,
			String sensorID, String messageType) {
		int maxCount = Integer.MIN_VALUE;
		long updateRate = 0;

		Map<String, Map<String, Map<Long, Integer>>> sensorId2Distribution = sensorRateDistribution
				.get(class1);
		if (sensorId2Distribution == null) {
			return -1;
		}
		Map<String, Map<Long, Integer>> sensorId2MessageType = sensorId2Distribution
				.get(sensorID);
		if (sensorId2MessageType == null) {
			return -1;
		}

		Map<Long, Integer> distribution = sensorId2MessageType.get(messageType);
		if (distribution == null) {
			return -1;
		}

		for (Entry<Long, Integer> entry : distribution.entrySet()) {
			// do not pick time invariant values as they do not yield an update
			// rate
			if (entry.getValue() > maxCount && entry.getKey() != 0) {
				maxCount = entry.getValue();
				updateRate = entry.getKey();
			}
		}
		return updateRate;
	}

	/**
	 * Pick sensor with highest rate and most measurements
	 */
	public SensorDescriptionUpdateRate<MeasuredPosition3D> getBestPositionSensor() {
		long bestMeasurment = 0;
		SensorDescriptionUpdateRate<MeasuredPosition3D> description = null;
		for (Entry<Class<T>, Map<String, Map<String, Map<Long, Integer>>>> sensor1 : sensorRateDistribution
				.entrySet()) {
			if (MeasuredPosition3D.class.isAssignableFrom(sensor1.getKey())) {
				for (Entry<String, Map<String, Map<Long, Integer>>> sensor : sensor1
						.getValue().entrySet()) {
					for (Entry<String, Map<Long, Integer>> sensorIds2Distribution : sensor
							.getValue().entrySet()) {
						long updateRate = getUpdateRate(sensor1.getKey(),
								sensor.getKey(),
								sensorIds2Distribution.getKey());
						Integer measurementCount = sensorIds2Distribution
								.getValue().get(updateRate);
						// sanity check - need update rates less then 60 seconds
						if(updateRate < 90000) {
							if (updateRate * measurementCount > bestMeasurment) {
								bestMeasurment = updateRate * measurementCount;
								SensorDescription<MeasuredPosition3D> sensorDescription = new SensorDescription<MeasuredPosition3D>(
										MeasuredPosition3D.class, sensor.getKey(),
										sensorIds2Distribution.getKey());
								description = new SensorDescriptionUpdateRate<MeasuredPosition3D>(
										MeasuredPosition3D.class,
										sensor.getKey(),
										sensorIds2Distribution.getKey(),
										updateRate,
										getDigitPrecision4PositionSensor(sensorDescription));
							}
						}
					}

				}
			}
		}
		return description;

	}

	/**
	 * Pick sensor with highest rate and most measurements
	 */
	public SensorDescriptionUpdateRate<Depth> getBestDepthSensor() {
		long bestMeasurment = 0;
		SensorDescriptionUpdateRate<Depth> description = null;

		for (Entry<Class<T>, Map<String, Map<String, Map<Long, Integer>>>> sensor1 : sensorRateDistribution
				.entrySet()) {
			if (Depth.class.isAssignableFrom(sensor1.getKey())) {
				for (Entry<String, Map<String, Map<Long, Integer>>> sensor : sensor1
						.getValue().entrySet()) {
					for (Entry<String, Map<Long, Integer>> sensorIds2Distribution : sensor
							.getValue().entrySet()) {
						long updateRate = getUpdateRate(sensor1.getKey(),
								sensor.getKey(),
								sensorIds2Distribution.getKey());
						Integer measurementCount = sensorIds2Distribution
								.getValue().get(updateRate);
						if (updateRate * measurementCount > bestMeasurment) {
							bestMeasurment = updateRate * measurementCount;
							description = new SensorDescriptionUpdateRate<Depth>(
									Depth.class, sensor.getKey(),
									sensorIds2Distribution.getKey(),
									updateRate, 0);
						}
					}

				}
			}
		}
		return description;
	}

	public int getDigitPrecision4PositionSensor(
			SensorDescription<MeasuredPosition3D> sensorDescription) {
		Integer integer = positionPrecisionDigits.get(sensorDescription);
		return integer;
	}

	public Set<SensorDescriptionUpdateRate<Measurement>> getBestSensors()
			throws StatisticsException {
		// processFiles(orderedFiles);

		Set<SensorDescriptionUpdateRate<Measurement>> descriptions = new HashSet<SensorDescriptionUpdateRate<Measurement>>();
		SensorDescriptionUpdateRate bestDepthSensor = getBestDepthSensor();
		if(bestDepthSensor != null) {
			descriptions.add(bestDepthSensor);
		}
		SensorDescriptionUpdateRate bestPositionSensor = getBestPositionSensor();
		if(bestPositionSensor != null) {
			descriptions.add(bestPositionSensor);
		}
		return descriptions;
	}

	@Override
	public void processFiles(ITrackFile trackFile) throws StatisticsException {
		try {
			trackFileProcessor.processFile(trackFile);
		} catch (IOException | ProcessingException e) {
			throw new StatisticsException(e);
		}
	}

	@Override
	public void finish() throws ProcessingException {
		// TODO Auto-generated method stub
		
	}

}
