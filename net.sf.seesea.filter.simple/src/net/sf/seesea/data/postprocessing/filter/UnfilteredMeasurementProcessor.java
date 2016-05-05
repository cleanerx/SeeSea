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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;
import net.sf.seesea.data.io.WriterException;
import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.CompositeMeasurement;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.data.IBoatParameters;
import net.sf.seesea.track.api.exception.ProcessingException;
import net.sf.seesea.waterlevel.IWaterLevelCorrection;

public class UnfilteredMeasurementProcessor implements IMeasurmentProcessor {

	// output information
	private IWriterFactory writerFactory;
	
	private final Map<String, Object> outputOptions;

	private IDataWriter dataWriter = null;

	private MeasurmentWindow measurementWindow2;

	private long lastSourceTrackIdentifier;

	private final IWaterLevelCorrection tideProvider;

	private final IBoatParameters boatParameters;

	private double sensorOffsetToWaterline;

	private GeoBoundingBox boundingBox;

	public UnfilteredMeasurementProcessor(IWriterFactory writerFactory, Map<String, Object> outputOptions, IWaterLevelCorrection waterLevelCorrection, IBoatParameters boatParameters) {
		this.writerFactory = writerFactory;
		this.outputOptions = outputOptions;
		this.tideProvider = waterLevelCorrection;
		this.boatParameters = boatParameters;
	}


	@Override
	public void processMeasurements(List<Measurement> results,
			String messageType,long sourceTrackIdentifier, GeoBoundingBox boundingBox) throws ProcessingException {
		try {
			for (Measurement measurement : results) {
				if(measurement instanceof CompositeMeasurement) {
					CompositeMeasurement compositeMeasurement = (CompositeMeasurement) measurement;
					for (Measurement containedMeasurement : compositeMeasurement.getMeasurements()) {
						processSingleMeasurement(containedMeasurement, sourceTrackIdentifier, boundingBox);
					}
				} else {
					processSingleMeasurement(measurement, sourceTrackIdentifier, boundingBox);
				}
			}
		} catch (WriterException e) {
			throw new ProcessingException(e);
		}

	}
	
	protected void processSingleMeasurement(Measurement measurement, long sourceTrackIdentifier, GeoBoundingBox boundingBox) throws WriterException, ProcessingException {
		if(lastSourceTrackIdentifier != sourceTrackIdentifier) {
			sensorOffsetToWaterline = boatParameters.getSensorOffsetToWaterline(sourceTrackIdentifier, measurement.getSensorID());
			this.boundingBox = boundingBox;
		}
		this.lastSourceTrackIdentifier = sourceTrackIdentifier;
		if(measurement.isValid()) {
				if(measurement instanceof MeasuredPosition3D) {
					MeasuredPosition3D position3d = (MeasuredPosition3D) measurement;
					if(position3d.getLatitude().getDecimalDegree() >= -90.0 && position3d.getLatitude().getDecimalDegree() <= 90.0 && position3d.getLongitude().getDecimalDegree() >= -180 && position3d.getLongitude().getDecimalDegree() <= 180) {
						// if measurements are too far apart, create a new writer, an kalman smoother with an initial value
						// new time measurement available: is it after the current window, create a new one
						if(measurementWindow2 != null) {
							// update rates match -> process
							filterMeasurementWindow();
							measurementWindow2 = new MeasurmentWindow();
							
						} else if(measurementWindow2 == null) {
							finish();
							createNewDataWriter();
							// create a new window and add time measurment
							measurementWindow2 = new MeasurmentWindow();
						}
					} else {
						return; // discard invalid value
					}
				}
		}
		if(measurementWindow2 != null) {
			measurementWindow2.setMeasurement(measurement);
		}
	}

	@Override
	public void finish() throws ProcessingException {
		try {
			if(measurementWindow2 != null && measurementWindow2.getPositions().size() > 0) {
				filterMeasurementWindow();
			}
			internalFinishProcessing();
		} catch (WriterException e) {
			throw new ProcessingException(e);
		}

	}
	
	private void internalFinishProcessing() throws WriterException {
		
		if(dataWriter != null) {
			dataWriter.closeOutput();
		}
		dataWriter = null;
	}

	
	private void createNewDataWriter() throws WriterException {
		dataWriter = writerFactory.createWriter(outputOptions);
	}

	/**
	 * depending on the gathered data the filtering may be changed
	 * @throws IOException 
	 * @throws WriterException 
	 */
	private void filterMeasurementWindow() throws WriterException {
		if(measurementWindow2 != null) {
				if(dataWriter == null) {
					createNewDataWriter();
				}
			}
			MeasuredPosition3D lastPosition = (MeasuredPosition3D) measurementWindow2.getPositions().get(measurementWindow2.getPositions().size() - 1);
			
			Depth depth = null;
			if(!measurementWindow2.getDepths().isEmpty()) {
				List<Measurement> measurements = new ArrayList<Measurement>(2);
				measurements.add(lastPosition);
				depth = measurementWindow2.getDepths().get(measurementWindow2.getDepths().size() -1 );
//				System.out.println(lastPosition.getLatitude().getDecimalDegree() + ":" + lastPosition.getLongitude().getDecimalDegree() + ":" + depth.getDepth());
				if(tideProvider != null) {
					double tideHeight = tideProvider.getCorrection(lastPosition.getLatitude().getDecimalDegree(), lastPosition.getLongitude().getDecimalDegree(), lastPosition.getTime());
					if(!Double.isNaN(tideHeight)) {
						depth.setDepth(depth.getDepth() - tideHeight);
						depth.setDepth(depth.getDepth() - sensorOffsetToWaterline);
						measurements.add(depth);
						dataWriter.write(measurements, true, lastSourceTrackIdentifier);
					} else {
						Logger.getLogger(getClass()).info("No water level correction for:" + lastPosition.getLatitude().getDecimalDegree() + ":" + lastPosition.getLongitude().getDecimalDegree() + ":" + lastPosition.getTime());
					}
				} else {
					measurements.add(depth);
					dataWriter.write(measurements, true, lastSourceTrackIdentifier);
				}
//				System.out.println(latitude.getDecimalDegree() + ":" + longitude.getDecimalDegree() + ": " + depth);
			}
	}

}
