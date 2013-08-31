package net.sf.seesea.provider.navigation.nmea.v183;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import net.sf.seesea.data.io.IDataReader;
import net.sf.seesea.model.core.geo.Coordinate;
import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GNSSMeasuredPosition;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;
import net.sf.seesea.model.core.physx.CompositeMeasurement;
import net.sf.seesea.model.core.physx.Distance;
import net.sf.seesea.model.core.physx.DistanceType;
import net.sf.seesea.model.core.physx.HandOrientation;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.LengthUnit;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.core.physx.RelativeSpeed;
import net.sf.seesea.model.core.physx.RelativeWind;
import net.sf.seesea.model.core.physx.SatelliteInfo;
import net.sf.seesea.model.core.physx.SatellitesVisible;
import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.model.core.physx.Temperature;
import net.sf.seesea.model.core.physx.TemperatureUnit;
import net.sf.seesea.model.core.physx.Time;
import net.sf.seesea.model.core.weather.Reference;
import net.sf.seesea.model.core.weather.WeatherFactory;
import net.sf.seesea.model.core.weather.WindMeasurement;
import net.sf.seesea.provider.navigation.nmea.NMEA0183MessageTypes;

import org.apache.log4j.Logger;

public class NMEA0183Reader implements IDataReader {

	private BufferedReader bufferedReader;

	private static final String LEFT = "L"; //$NON-NLS-1$
	private static final String RIGHT = "R"; //$NON-NLS-1$

	private static final String WEST = "W"; //$NON-NLS-1$
	private static final String SOUTH = "S"; //$NON-NLS-1$

	private final Set<SatelliteInfo> satelliteDataCache;
	private int satelliteMessageCounter;
	private Map<NMEA0183MessageTypes, Set<String>> processMessageTypes2SensorIds;

	private SimpleDateFormat simpleDateFormat;

	private boolean useLastDateFromAnotherSentenceGGA = false;
	
	private Date lastDate;

	private boolean processRMCWithMeasurements = true;

	public NMEA0183Reader(
			Map<NMEA0183MessageTypes, Set<String>> processMessageTypes2SensorIds, boolean useLastDateFromAnotherSentenceGGA) {
		satelliteDataCache = new HashSet<SatelliteInfo>();
		satelliteMessageCounter = 0;
		this.processMessageTypes2SensorIds = processMessageTypes2SensorIds;
		this.useLastDateFromAnotherSentenceGGA = useLastDateFromAnotherSentenceGGA;
		// we need the date for the tide calculations. the GGA sentence does not have the date so we stick to RMC
		if(useLastDateFromAnotherSentenceGGA && processMessageTypes2SensorIds.containsKey(NMEA0183MessageTypes.GGA) && !processMessageTypes2SensorIds.containsKey(NMEA0183MessageTypes.RMC)) {
			this.processMessageTypes2SensorIds.put(NMEA0183MessageTypes.RMC, processMessageTypes2SensorIds.get(NMEA0183MessageTypes.GGA));
			processRMCWithMeasurements = false;
		} else {
			processRMCWithMeasurements = true;
		}
		simpleDateFormat = new SimpleDateFormat("HHmmss"); //$NON-NLS-1$
	}

	public NMEA0183Reader() {
		satelliteDataCache = new HashSet<SatelliteInfo>();
		satelliteMessageCounter = 0;
		this.processMessageTypes2SensorIds = new HashMap<NMEA0183MessageTypes, Set<String>>();
		EnumSet<NMEA0183MessageTypes> allOf = EnumSet
				.<NMEA0183MessageTypes> allOf(NMEA0183MessageTypes.class);
		for (NMEA0183MessageTypes nmea0183MessageTypes : allOf) {
			processMessageTypes2SensorIds.put(nmea0183MessageTypes,
					Collections.<String> emptySet());
		}
		simpleDateFormat = new SimpleDateFormat("HHmmss"); //$NON-NLS-1$

	}

	public NMEA0183Reader(InputStream inputStream) {
		this();
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		simpleDateFormat = new SimpleDateFormat("HHmmss"); //$NON-NLS-1$
	}

	@Override
	public Collection<Measurement> read() throws IOException {
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			List<Measurement> results = null;
			results = extractMeasurementsFromNMEA(line,
					new ArrayList<Measurement>(1));
			return results;
		}
		return null;
	}

	/**
	 * 
	 * @param line
	 *            raw nmea 0183 sentence line
	 * @param results
	 *            the list of measurements
	 * @return the given result list with the newly added measurements
	 */
	public List<Measurement> extractMeasurementsFromNMEA(String line,
			List<Measurement> results) {
		String rawContent = removeValidChecksum(line);
		if (rawContent != null) {
			String[] nmeaContent = rawContent.split(","); //$NON-NLS-1$
			try {

				NMEA0183MessageTypes messageType = NMEA0183MessageTypes
						.valueOf(nmeaContent[0].substring(2));
				Measurement measurement = null;
				if (processMessageTypes2SensorIds.keySet()
						.contains(messageType)) {
					switch (messageType) {
					case MTW:
						measurement = MTW_WaterTemperature(nmeaContent);
						addMeasurmentIfUnfiltered(results,
								processMessageTypes2SensorIds, measurement,
								NMEA0183MessageTypes.MTW);
						break;
					case MWV:
						measurement = MWV_WindMeasurement(nmeaContent);
						addMeasurmentIfUnfiltered(results,
								processMessageTypes2SensorIds, measurement,
								NMEA0183MessageTypes.MWV);
						break;
					case VHW:
						measurement = VHW_ShipVector(nmeaContent);
						addMeasurmentIfUnfiltered(results,
								processMessageTypes2SensorIds, measurement,
								NMEA0183MessageTypes.VHW);
						break;
					case GLL:
						measurement = GLL_Position(nmeaContent);
						if (measurement != null) {
							addMeasurmentIfUnfiltered(results,
									processMessageTypes2SensorIds, measurement,
									NMEA0183MessageTypes.GLL);
						}
						break;
					case VTG:
						measurement = VTG_TrackMadeGood(nmeaContent);
						addMeasurmentIfUnfiltered(results,
								processMessageTypes2SensorIds, measurement,
								NMEA0183MessageTypes.VTG);
						break;
					case VWR:
						measurement = VWR_RelativeWindSpeedAndAngle(nmeaContent);
						addMeasurmentIfUnfiltered(results,
								processMessageTypes2SensorIds, measurement,
								NMEA0183MessageTypes.VWR);
						break;
					case GGA:
						measurement = GGA_Position(nmeaContent);
						if (measurement != null) {
							addMeasurmentIfUnfiltered(results,
									processMessageTypes2SensorIds, measurement,
									NMEA0183MessageTypes.GGA);
						}
						break;
					case RMC:
						measurement = RMC_Position(nmeaContent);
						if (measurement != null) {
							addMeasurmentIfUnfiltered(results,
									processMessageTypes2SensorIds, measurement,
									NMEA0183MessageTypes.RMC);
						}
						break;
					case GSV:
						measurement = GSV_SatelliteData(nmeaContent);
						if (measurement != null) {
							addMeasurmentIfUnfiltered(results,
									processMessageTypes2SensorIds, measurement,
									NMEA0183MessageTypes.GSV);
						}
						break;
					case GSA:
						// results.add(GSA_SatelliteData(nmeaContent));
						break;
					case DBK:
						results.add(DBK_DepthBelowKeel(nmeaContent));
						break;
					case DBS:
						results.add(DBS_DepthBelowSurface(nmeaContent));
						break;
					case DBT:
						results.add(DBT_DepthBelowTransducer(nmeaContent));
						break;
					case DPT:
						measurement = DPT_DepthOfWater(nmeaContent);
						if (measurement != null) {
							addMeasurmentIfUnfiltered(results,
									processMessageTypes2SensorIds, measurement,
									NMEA0183MessageTypes.DPT);
						}
						break;
					case VLW:
						results.addAll(VLW_TotalTrip(nmeaContent));
						break;
					case HDM:
						measurement = HDM_HeadingCompass(nmeaContent);
						addMeasurmentIfUnfiltered(results,
								processMessageTypes2SensorIds, measurement,
								NMEA0183MessageTypes.HDM);
						break;
					default:
						break;
					}
				}
			} catch (IllegalArgumentException e) {
				Logger.getLogger(getClass()).error(
						"Unknown message is being ignored " + e.getMessage());
			}
		}
		return results;
	}

	private void addMeasurmentIfUnfiltered(
			List<Measurement> results,
			Map<NMEA0183MessageTypes, Set<String>> processMessageTypes2SensorIds,
			Measurement measurement, NMEA0183MessageTypes filterType) {
		Set<String> sensorIds = processMessageTypes2SensorIds.get(filterType);
		if (sensorIds.isEmpty()
				|| sensorIds.contains(measurement.getSensorID())) {
			results.add(measurement);
		}
	}

	private Measurement DPT_DepthOfWater(String[] nmeaContent) {
		PhysxFactory physxFactory = PhysxFactory.eINSTANCE;
		CompositeMeasurement measurement = physxFactory
				.createCompositeMeasurement();
		List<Measurement> measurements = measurement.getMeasurements();
		if (nmeaContent.length > 1) {
			Depth depthBelowTransducer = null;
			if (!nmeaContent[1].isEmpty()) {
				depthBelowTransducer = GeoFactory.eINSTANCE.createDepth();
				setSensorID(nmeaContent[0], depthBelowTransducer);
				try {
					double depthInMeters = Double.parseDouble(nmeaContent[1]);
					depthBelowTransducer.setDepth(depthInMeters);
					depthBelowTransducer.setValid(true);
					depthBelowTransducer
							.setMeasurementPosition(RelativeDepthMeasurementPosition.TRANSDUCER);
					measurements.add(depthBelowTransducer);
				} catch (NumberFormatException e) {
					// fail silently
				}
			}
			if (nmeaContent.length > 2 && !nmeaContent[2].isEmpty() && depthBelowTransducer != null) {
				try {
					double offset = Double.parseDouble(nmeaContent[2]);
					if (offset != 0.0) {
						Depth depthBelowX = GeoFactory.eINSTANCE.createDepth();
						depthBelowX.setDepth(depthBelowTransducer.getDepth()
								+ offset);
						depthBelowX.setValid(true);
						if (offset < 0.0) {
							depthBelowX
									.setMeasurementPosition(RelativeDepthMeasurementPosition.KEEL);
						} else {
							depthBelowX
									.setMeasurementPosition(RelativeDepthMeasurementPosition.SURFACE);
						}
						setSensorID(nmeaContent[0], depthBelowX);
						measurements.add(depthBelowX);
					}
				} catch (NumberFormatException e) {
					// fail silently
				}
			}
		}
		return measurement;
	}

	/**
	 * checks the checksum and additionally cuts off the checksum. This is not a
	 * sideffect free method
	 * 
	 * @param rawContent
	 * @return
	 */
	protected String removeValidChecksum(String nmeaMessageContent) {
		if (!nmeaMessageContent.contains("$")) { //$NON-NLS-1$
			return null;
		}
		try {
			String rawContent = nmeaMessageContent.substring(nmeaMessageContent.indexOf("$") + 1,
					nmeaMessageContent.length());
			int checksumIndex = rawContent.lastIndexOf("*"); //$NON-NLS-1$
			if (checksumIndex != -1) {
				String checksumString = rawContent.substring(checksumIndex + 1,
						checksumIndex + 3);
				try {
					int checkSum = Integer.parseInt(checksumString, 16);

					rawContent = rawContent.substring(0, checksumIndex);
					char calculatedChecksum = 0;
					for (int i = 0; i < rawContent.length(); i++) {
						calculatedChecksum ^= rawContent.charAt(i);
					}
					if (((char) checkSum) == calculatedChecksum) {
						return rawContent;
					}
				} catch (NumberFormatException e) {
					return null;
				}
			}
			return null;
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}

	private Heading HDM_HeadingCompass(String[] nmeaContent) {
		try {
			Heading heading = PhysxFactory.eINSTANCE.createHeading();
			if (!nmeaContent[1].isEmpty()) {
				heading.setHeadingType(HeadingType.MAGNETIC);
				heading.setDegrees(Double.parseDouble(nmeaContent[1]));
			}
			return heading;

		} catch (NumberFormatException e) {
			// nothing to do. fail silently
		}
		return null;
	}

	private List<Measurement> VLW_TotalTrip(String[] nmeaContent) {
		try {
			// in nautical miles
			double totalDistance = Double.parseDouble(nmeaContent[1]);
			Distance totalDistanceObject = PhysxFactory.eINSTANCE
					.createDistance();
			totalDistanceObject.setValue(totalDistance);
			totalDistanceObject.setUnit(LengthUnit.NAUTICAL_MILE);
			totalDistanceObject.setDistanceType(DistanceType.TOTAL);

			double tripDistance = Double.parseDouble(nmeaContent[3]);
			Distance tripDistanceObject = PhysxFactory.eINSTANCE
					.createDistance();
			tripDistanceObject.setValue(tripDistance);
			tripDistanceObject.setUnit(LengthUnit.NAUTICAL_MILE);
			tripDistanceObject.setDistanceType(DistanceType.TRIP);

			List<Measurement> measurements = new ArrayList<Measurement>(3);
			measurements.add(totalDistanceObject);
			setSensorID(nmeaContent[0], totalDistanceObject);

			measurements.add(tripDistanceObject);
			setSensorID(nmeaContent[0], tripDistanceObject);

			return measurements;
		} catch (NumberFormatException e) {
			// nothing to do. fail silently
		}
		return Collections.<Measurement> emptyList();
	}

	private void GSA_SatelliteData(String[] nmeaContent) {

	}

	/**
	 * 
	 * @param nmeaContent
	 * @return
	 */
	private SatellitesVisible GSV_SatelliteData(String[] nmeaContent) {
		if (nmeaContent[2].equals("1")) { //$NON-NLS-1$
			satelliteDataCache.clear();
			satelliteMessageCounter = 0;
		}

		satelliteMessageCounter++;
		for (int i = 1; i < 5; i++) {
			int index2 = 4 * i;
			if (index2 < nmeaContent.length) {
				String id = nmeaContent[4 * i];
				if (id != null && id.length() > 0) {
					SatelliteInfo satelliteInfo = PhysxFactory.eINSTANCE
							.createSatelliteInfo();
					try {
						satelliteInfo.setId(Integer.parseInt(id));
					} catch (NumberFormatException e) {
						// ignore
					}
					try {
						int index = (4 * i) + 1;
						if (index < nmeaContent.length) {
							satelliteInfo.setElevation(Integer
									.parseInt(nmeaContent[(4 * i) + 1]));
						}
					} catch (NumberFormatException e) {
						// ignore
					}
					try {
						int index = (4 * i) + 2;
						if (index < nmeaContent.length) {
							satelliteInfo.setAzimuth(Integer
									.parseInt(nmeaContent[(4 * i) + 2]));
						}
					} catch (NumberFormatException e) {
						// ignore
					}
					try {
						int index = (4 * i) + 3;
						if (index < nmeaContent.length) {
							satelliteInfo.setSignalStrength(Integer
									.parseInt(nmeaContent[index]));
						}
					} catch (NumberFormatException e) {
						// ignore
					}
					satelliteDataCache.add(satelliteInfo);
				}

			}
		}

		int totalMessages = Integer.parseInt(nmeaContent[1]);
		if (totalMessages == satelliteMessageCounter) {
			SatellitesVisible satellitesVisible = PhysxFactory.eINSTANCE
					.createSatellitesVisible();
			satellitesVisible.getSatelliteInfo().addAll(satelliteDataCache);
			satelliteDataCache.clear();
			// send
			return satellitesVisible;
		} else {
			return null;
		}

	}

	/**
	 * @param nmeaContent
	 * @return
	 */
	private CompositeMeasurement RMC_Position(String[] nmeaContent) {
		if (nmeaContent.length < 9) {
			return null;
		}
		GeoFactory geoFactory = GeoFactory.eINSTANCE;
		PhysxFactory physxFactory = PhysxFactory.eINSTANCE;
		CompositeMeasurement measurement = physxFactory
				.createCompositeMeasurement();

		MeasuredPosition3D geoPosition = geoFactory.createMeasuredPosition3D();
		RelativeSpeed relativeSpeed = physxFactory.createRelativeSpeed();
		Heading heading = physxFactory.createHeading();

		try {
			PrecisionCoordinate precisionCoordinate = null;
			PrecisionCoordinate precisionCoordinate2 = null;
			if (!nmeaContent[3].isEmpty()) {
				precisionCoordinate = parseLatitude(nmeaContent, 3);
				Latitude latitude = (Latitude) precisionCoordinate.coordinate;
				geoPosition.setLatitude(latitude);
			}
			if (!nmeaContent[5].isEmpty()) {
				precisionCoordinate2 = parseLongitude(nmeaContent, 5);
				Longitude longitude = (Longitude) precisionCoordinate2.coordinate;
				geoPosition.setLongitude(longitude);
			}
			if (geoPosition.getLatitude() != null
					&& geoPosition.getLongitude() != null) {
				measurement.getMeasurements().add(geoPosition);
				geoPosition.setPrecision(Math.max(
						precisionCoordinate.precision,
						precisionCoordinate2.precision));
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.getLogger(NMEA0183Reader.class).error(
					"Failed to analyze positon RMC " + e.getMessage()); //$NON-NLS-1$
		} catch (StringIndexOutOfBoundsException e) {
			Logger.getLogger(NMEA0183Reader.class).error(
					"Failed to analyze positon RMC " + e.getMessage()); //$NON-NLS-1$
			return null;
		}

		Time time = physxFactory.createTime();
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.set(Calendar.HOUR_OF_DAY,
					Integer.parseInt(nmeaContent[1].substring(0, 2).trim()));
			calendar.set(Calendar.MINUTE,
					Integer.parseInt(nmeaContent[1].substring(2, 4).trim()));
			calendar.set(Calendar.SECOND,
					Integer.parseInt(nmeaContent[1].substring(4, 6).trim()));
			calendar.set(Calendar.MILLISECOND, 0);

			calendar.set(Calendar.DAY_OF_MONTH,
					Integer.parseInt(nmeaContent[9].substring(0, 2).trim()));
			calendar.set(Calendar.MONTH,
					Integer.parseInt(nmeaContent[9].substring(2, 4).trim()) - 1);
			calendar.set(
					Calendar.YEAR,
					Integer.parseInt(nmeaContent[9].substring(4, 6).trim()) + 2000);
			calendar.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
			time.setTime(calendar.getTime());
			time.setTimezone("UTC"); //$NON-NLS-1$
			heading.setTime(calendar.getTime());
			heading.setTimezone("UTC"); //$NON-NLS-1$
			relativeSpeed.setTime(calendar.getTime());
			relativeSpeed.setTimezone("UTC"); //$NON-NLS-1$
			geoPosition.setTime(calendar.getTime());
			geoPosition.setTimezone("UTC"); //$NON-NLS-1$
			if(useLastDateFromAnotherSentenceGGA) {
				lastDate = geoPosition.getTime();
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

		try {
			Speed speed = physxFactory.createSpeed();
			speed.setSpeed(Double.parseDouble(nmeaContent[7]));
			speed.setSpeedUnit(SpeedUnit.N);
			relativeSpeed.setKey(SpeedType.COG);
			relativeSpeed.setValue(speed);
			measurement.getMeasurements().add(relativeSpeed);
		} catch (NumberFormatException e) {
			// nothing to do. fail silently
		}
		try {
			heading.setHeadingType(HeadingType.COG);
			heading.setDegrees(Double.parseDouble(nmeaContent[8]));
			measurement.getMeasurements().add(heading);
		} catch (NumberFormatException e) {
			// nothing to do. fail silently
		}
		
		// abort processing if only the time was of interest for other sentences
		if(!processRMCWithMeasurements) {
			return null;
		}

		measurement.getMeasurements().add(time);

		setSensorID(nmeaContent[0], heading);
		setSensorID(nmeaContent[0], relativeSpeed);
		setSensorID(nmeaContent[0], geoPosition);
		setSensorID(nmeaContent[0], time);
		setSensorID(nmeaContent[0], measurement);

		if ("A".equals(nmeaContent[2].toUpperCase())) { //$NON-NLS-1$
			heading.setValid(true);
			relativeSpeed.setValid(true);
			geoPosition.setValid(true);
			time.setValid(true);
			measurement.setValid(true);
		}
		return measurement;
	}

	/**
	 * 
	 * @param nmeaContent
	 * @return
	 */
	private GNSSMeasuredPosition GGA_Position(String[] nmeaContent) {
		GNSSMeasuredPosition geoPosition = GeoFactory.eINSTANCE
				.createGNSSMeasuredPosition();
		try {
			setTime(nmeaContent, geoPosition, 1);
			if(useLastDateFromAnotherSentenceGGA) {
				Date time = geoPosition.getTime();
				if(lastDate != null && time != null) {
					Calendar lastTimeCalendar = Calendar.getInstance();
					lastTimeCalendar.setTime(lastDate);
					Calendar measurmentTimeCalendar = Calendar.getInstance();
					measurmentTimeCalendar.setTime(time);
					measurmentTimeCalendar.set(Calendar.YEAR, lastTimeCalendar.get(lastTimeCalendar.YEAR));
					measurmentTimeCalendar.set(Calendar.DAY_OF_YEAR, lastTimeCalendar.get(lastTimeCalendar.DAY_OF_YEAR));
					geoPosition.setTime(measurmentTimeCalendar.getTime());
				}
			}
			PrecisionCoordinate precisionCoordinate = parseLatitude(
					nmeaContent, 2);
			if (precisionCoordinate != null) {
				Latitude latitude = (Latitude) precisionCoordinate.coordinate;
				geoPosition.setLatitude(latitude);
			}
			PrecisionCoordinate precisionCoordinate2 = parseLongitude(
					nmeaContent, 4);
			if (precisionCoordinate2 != null) {
				Longitude longitude = (Longitude) precisionCoordinate2.coordinate;
				geoPosition.setLongitude(longitude);
			}
			if (precisionCoordinate != null && precisionCoordinate2 != null) {
				geoPosition.setPrecision(Math.max(
						precisionCoordinate.precision,
						precisionCoordinate2.precision));
			} else {
				return null;
			}
//			System.out.println(geoPosition.getLatitude());

			if(nmeaContent.length > 9) {
				try {
					geoPosition.setAltitude(Double.parseDouble(nmeaContent[9]));
				} catch (NumberFormatException e) {
					// nothing to do
				}
			}
			if(nmeaContent.length > 8) {
			try {
				geoPosition.setHdop(Double.parseDouble(nmeaContent[8]));
			} catch (NumberFormatException e) {
				// nothing to do
			}
			}
		} catch (StringIndexOutOfBoundsException e) {
			Logger.getLogger(NMEA0183Reader.class).error(
					"Failed to analyze positon" + e.getMessage()); //$NON-NLS-1$
			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.getLogger(NMEA0183Reader.class).error(
					"Failed to analyze positon" + e.getMessage()); //$NON-NLS-1$
			return null;
		}

		setSensorID(nmeaContent[0], geoPosition);
		try {
			int fixQuality = Integer.parseInt(nmeaContent[6]);
			if (fixQuality > 0) {
				geoPosition.setValid(true);
			}
			return geoPosition;
		} catch (NumberFormatException e) {
			geoPosition.setValid(false);
		}
		return null;
	}

	private void setTime(String[] nmeaContent, MeasuredPosition3D geoPosition,
			int nmeaIndex) {
		try {
			if (!nmeaContent[nmeaIndex].isEmpty()) {
				geoPosition.setTime(simpleDateFormat
						.parse(nmeaContent[nmeaIndex]));
				geoPosition.setTimezone("UTC"); //$NON-NLS-1$
				// System.out.println(geoPosition.getTime().getTime());
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// no time supplied is valid
		} catch (ParseException e) {
			Logger.getRootLogger().error("Failed to parse", e); //$NON-NLS-1$
		}
	}

	private PrecisionCoordinate parseLongitude(String[] nmeaContent, int i) {
		try {
			if (nmeaContent[i].isEmpty()) {
				return null;
			}
			int indexOfDot = nmeaContent[i].indexOf('.');
			Longitude longitude = GeoFactory.eINSTANCE.createLongitude();
			if (indexOfDot == -1) {
				longitude.setDecimalDegree(Integer.parseInt(nmeaContent[i]
						.trim()));
				return new PrecisionCoordinate(longitude, 0);
			} else {
				String minute = nmeaContent[i].substring(indexOfDot - 2,
						nmeaContent[i].length());
				String degree = nmeaContent[i].substring(0, indexOfDot - 2);
				int deg = Integer.parseInt(degree.trim());
				longitude.setDecimalDegree(deg + Double.parseDouble(minute)
						/ 60.0);
				if (WEST.equals(nmeaContent[i + 1])) {
					longitude.setDecimalDegree(longitude.getDecimalDegree()
							* (-1));
				}
				return new PrecisionCoordinate(longitude,
						nmeaContent[i].length() - indexOfDot - 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	private PrecisionCoordinate parseLatitude(String[] nmeaContent, int i) {
		try {

			if (nmeaContent[i].isEmpty() || i + 1 >= nmeaContent.length) {
				return null;
			}
			int indexOfDot = nmeaContent[i].indexOf('.');
			Latitude latitude = GeoFactory.eINSTANCE.createLatitude();
			if (indexOfDot == -1) {
				latitude.setDecimalDegree(Integer.parseInt(nmeaContent[i]
						.trim()));
				return new PrecisionCoordinate(latitude, 0);
			} else {
				String minute = nmeaContent[i].substring(indexOfDot - 2,
						nmeaContent[i].length());
				String degree = nmeaContent[i].substring(0, indexOfDot - 2);

				int deg = Integer.parseInt(degree.trim());
				latitude.setDecimalDegree(deg + Double.parseDouble(minute)
						/ 60.0);
				if (SOUTH.equals(nmeaContent[i + 1])) {
					latitude.setDecimalDegree(latitude.getDecimalDegree()
							* (-1));
				}
				return new PrecisionCoordinate(latitude,
						nmeaContent[i].length() - indexOfDot - 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	private RelativeWind VWR_RelativeWindSpeedAndAngle(String[] nmeaContent) {
		RelativeWind relativeWind = PhysxFactory.eINSTANCE.createRelativeWind();
		relativeWind.setDegrees(Double.parseDouble(nmeaContent[1]));
		if (LEFT.equals(nmeaContent[2].toUpperCase())) {
			relativeWind.setBowOrientation(HandOrientation.LEFT);
		} else if (RIGHT.equals(nmeaContent[2].toUpperCase())) {
			relativeWind.setBowOrientation(HandOrientation.RIGHT);
		} else {
			relativeWind.setBowOrientation(HandOrientation.UNKNOWN);
		}
		relativeWind.setSpeed(Double.parseDouble(nmeaContent[3]));
		relativeWind.setSpeedUnit(SpeedUnit.N);
		setSensorID(nmeaContent[0], relativeWind);
		return relativeWind;
	}

	private CompositeMeasurement VTG_TrackMadeGood(String[] nmeaContent) {
		PhysxFactory physxFactory = PhysxFactory.eINSTANCE;
		CompositeMeasurement measurement = physxFactory
				.createCompositeMeasurement();

		if (nmeaContent[1].length() > 0) {
			Heading heading = physxFactory.createHeading();
			heading.setHeadingType(HeadingType.TRUE);
			heading.setDegrees(Double.parseDouble(nmeaContent[1]));
			measurement.getMeasurements().add(heading);
			setSensorID(nmeaContent[0], heading);
		}
		if (nmeaContent[3].length() > 0) {
			Heading heading = physxFactory.createHeading();
			heading.setHeadingType(HeadingType.MAGNETIC);
			heading.setDegrees(Double.parseDouble(nmeaContent[3]));
			measurement.getMeasurements().add(heading);
			setSensorID(nmeaContent[0], heading);
		}
		if (nmeaContent[5].length() > 0) {
			Speed speed = PhysxFactory.eINSTANCE.createSpeed();
			speed.setSpeed(Double.parseDouble(nmeaContent[5]));
			speed.setSpeedUnit(SpeedUnit.N);

			RelativeSpeed relativeSpeed = physxFactory.createRelativeSpeed();
			relativeSpeed.setKey(SpeedType.COG);
			relativeSpeed.setValue(speed);
			setSensorID(nmeaContent[0], relativeSpeed);
			measurement.getMeasurements().add(relativeSpeed);
		}
		setSensorID(nmeaContent[0], measurement);
		return measurement;
	}

	private Measurement GLL_Position(String[] nmeaContent) {
		try {
			GeoFactory geoFactory = GeoFactory.eINSTANCE;
			PhysxFactory physxFactory = PhysxFactory.eINSTANCE;
			CompositeMeasurement measurement = physxFactory
					.createCompositeMeasurement();

			MeasuredPosition3D geoPosition = geoFactory
					.createMeasuredPosition3D();

			PrecisionCoordinate precisionCoordinate = parseLatitude(
					nmeaContent, 1);
			if (precisionCoordinate != null) {
				Latitude latitude = (Latitude) precisionCoordinate.coordinate;
				geoPosition.setLatitude(latitude);
			}
			PrecisionCoordinate precisionCoordinate2 = parseLongitude(
					nmeaContent, 3);
			if (precisionCoordinate2 != null) {
				Longitude longitude = (Longitude) precisionCoordinate2.coordinate;
				geoPosition.setLongitude(longitude);
			}
			if (precisionCoordinate != null && precisionCoordinate2 != null) {
				geoPosition.setPrecision(Math.max(
						precisionCoordinate.precision,
						precisionCoordinate2.precision));
			} else {
				return null;
			}

			setSensorID(nmeaContent[0], geoPosition);
			measurement.getMeasurements().add(geoPosition);

			if (nmeaContent.length > 5 && !nmeaContent[5].isEmpty()) {
				if(useLastDateFromAnotherSentenceGGA) {
					Date time = geoPosition.getTime();
					if(lastDate != null && time != null) {
						Calendar lastTimeCalendar = Calendar.getInstance();
						lastTimeCalendar.setTime(lastDate);
						lastTimeCalendar.set(Calendar.HOUR_OF_DAY, Integer
								.parseInt(nmeaContent[5].substring(0, 2).trim()));
						lastTimeCalendar.set(Calendar.MINUTE, Integer
								.parseInt(nmeaContent[5].substring(2, 4).trim()));
						lastTimeCalendar.set(Calendar.SECOND, Integer
								.parseInt(nmeaContent[5].substring(4, 6).trim()));
						lastTimeCalendar.set(Calendar.MILLISECOND, 0);
						lastTimeCalendar.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
						lastTimeCalendar.set(Calendar.YEAR, lastTimeCalendar.get(lastTimeCalendar.YEAR));
						lastTimeCalendar.set(Calendar.DAY_OF_YEAR, lastTimeCalendar.get(lastTimeCalendar.DAY_OF_YEAR));
						geoPosition.setTime(lastTimeCalendar.getTime());
					}
				}

//				measurement.getMeasurements().add(time);
			}
			setSensorID(nmeaContent[0], measurement);

			if (nmeaContent.length > 5) {
				if ("A".equals(nmeaContent[6])) { //$NON-NLS-1$
					geoPosition.setValid(true);
				}
			} else {
				geoPosition.setValid(true);
			}

			return measurement;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private CompositeMeasurement VHW_ShipVector(String[] nmeaContent) {
		PhysxFactory physxFactory = PhysxFactory.eINSTANCE;
		CompositeMeasurement measurement = physxFactory
				.createCompositeMeasurement();
		List<Measurement> measurements = measurement.getMeasurements();

		if (!nmeaContent[1].isEmpty()) {
			Heading heading = physxFactory.createHeading();
			heading.setHeadingType(HeadingType.TRUE);
			heading.setDegrees(Double.parseDouble(nmeaContent[1]));
			measurements.add(heading);
			setSensorID(nmeaContent[0], heading);
		}
		if (!nmeaContent[3].isEmpty()) {
			Heading heading = physxFactory.createHeading();
			heading.setHeadingType(HeadingType.MAGNETIC);
			heading.setDegrees(Double.parseDouble(nmeaContent[3]));
			measurements.add(heading);
			setSensorID(nmeaContent[0], heading);
		}
		setSensorID(nmeaContent[0], measurement);

		if (nmeaContent.length > 5) {
			if (!nmeaContent[5].isEmpty()) {
				Speed speed = PhysxFactory.eINSTANCE.createSpeed();
				speed.setSpeed(Double.parseDouble(nmeaContent[5]));
				speed.setSpeedUnit(SpeedUnit.N);
				RelativeSpeed relativeSpeed = physxFactory
						.createRelativeSpeed();
				relativeSpeed.setKey(SpeedType.SPEEDTHOUGHWATER);
				relativeSpeed.setValue(speed);
				setSensorID(nmeaContent[0], relativeSpeed);
				measurements.add(relativeSpeed);
				return measurement;
			} else if (!nmeaContent[7].isEmpty()) {
				Speed speed = PhysxFactory.eINSTANCE.createSpeed();
				speed.setSpeed(Double.parseDouble(nmeaContent[7]));
				speed.setSpeedUnit(SpeedUnit.K);
				RelativeSpeed relativeSpeed = physxFactory
						.createRelativeSpeed();
				relativeSpeed.setKey(SpeedType.SPEEDTHOUGHWATER);
				relativeSpeed.setValue(speed);
				setSensorID(nmeaContent[0], relativeSpeed);
				measurements.add(relativeSpeed);
				return measurement;
			}
		}
		return measurement;
	}

	private Temperature MTW_WaterTemperature(String[] nmeaContent) {
		Temperature temperature = PhysxFactory.eINSTANCE.createTemperature();
		temperature.setValue(Double.parseDouble(nmeaContent[1]));
		if (nmeaContent[1].toUpperCase().equals('F')) {
			temperature.setUnit(TemperatureUnit.FAHRENHEIT);
		} else {
			temperature.setUnit(TemperatureUnit.CELSIUS);
		}
		setSensorID(nmeaContent[0], temperature);
		return temperature;
	}

	private void setSensorID(String string, Measurement measurement) {
		measurement.setSensorID(string.substring(0, 2));
	}

	private WindMeasurement MWV_WindMeasurement(String[] nmeaContent) {
		WindMeasurement windMeasurement = WeatherFactory.eINSTANCE
				.createWindMeasurement();
		windMeasurement.setAngle(Double.parseDouble(nmeaContent[1]));
		if (RIGHT.equals(nmeaContent[2].toUpperCase())) {
			windMeasurement.setReference(Reference.RELATIVE);
		} else if ("T".equals(nmeaContent[2].toUpperCase())) { //$NON-NLS-1$
			windMeasurement.setReference(Reference.ABSOLUTE);
		} else {
			windMeasurement.setReference(Reference.UNKNOWN);
		}
		windMeasurement.setSpeed(Double.parseDouble(nmeaContent[3]));
		if ("K".equals(nmeaContent[4].toUpperCase())) { //$NON-NLS-1$
			windMeasurement.setSpeedUnit(SpeedUnit.K);
		} else if ("M".equals(nmeaContent[4].toUpperCase())) { //$NON-NLS-1$
			windMeasurement.setSpeedUnit(SpeedUnit.M);
		} else if ("N".equals(nmeaContent[4].toUpperCase())) { //$NON-NLS-1$
			windMeasurement.setSpeedUnit(SpeedUnit.N);
		} else {
			windMeasurement.setSpeedUnit(SpeedUnit.UNKNOWN);
		}
		setSensorID(nmeaContent[0], windMeasurement);
		return windMeasurement;
	}

	private Depth DBK_DepthBelowKeel(String[] nmeaContent) {
		Depth depth = GeoFactory.eINSTANCE.createDepth();
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.KEEL);
		setDepthFromContent(nmeaContent, depth);
		depth.setValid(true);
		return depth;
	}

	private Depth DBS_DepthBelowSurface(String[] nmeaContent) {
		Depth depth = GeoFactory.eINSTANCE.createDepth();
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.SURFACE);
		setDepthFromContent(nmeaContent, depth);
		depth.setValid(true);
		return depth;
	}

	private Depth DBT_DepthBelowTransducer(String[] nmeaContent) {
		Depth depth = GeoFactory.eINSTANCE.createDepth();
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.TRANSDUCER);
		setDepthFromContent(nmeaContent, depth);
		depth.setValid(true);
		return depth;
	}

	/**
	 * helper method for the three depths values
	 * 
	 * @param nmeaContent
	 * @param depth
	 */
	private void setDepthFromContent(String[] nmeaContent, Depth depth) {
		if (!nmeaContent[3].isEmpty()) {
			double depthInMeters = Double.parseDouble(nmeaContent[3]);
			depth.setDepth(depthInMeters);
		} else if (!nmeaContent[1].isEmpty()) {
			double depthInMeters = Double.parseDouble(nmeaContent[1]) * 0.3048;
			depth.setDepth(depthInMeters);
		} else if (!nmeaContent[5].isEmpty()) {
			double depthInMeters = Double.parseDouble(nmeaContent[1]) * 1.8288;
			depth.setDepth(depthInMeters);
		}
		setSensorID(nmeaContent[0], depth);
	}

	private class PrecisionCoordinate {

		public PrecisionCoordinate(Coordinate coordinate, int precision) {
			super();
			this.coordinate = coordinate;
			this.precision = precision;
		}

		Coordinate coordinate;

		int precision;
	}
}

