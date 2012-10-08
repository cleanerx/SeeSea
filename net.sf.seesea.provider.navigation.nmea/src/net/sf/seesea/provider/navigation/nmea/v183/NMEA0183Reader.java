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
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import net.sf.seesea.data.io.IDataReader;
import net.sf.seesea.model.core.geo.Depth;
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
	
	public NMEA0183Reader() {
		satelliteDataCache = new HashSet<SatelliteInfo>();
		satelliteMessageCounter = 0;
	}
	
	public NMEA0183Reader(InputStream inputStream) {
		this();
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	}
	
	@Override
	public Collection<Measurement> read() throws IOException {
		String line = null;
		while((line = bufferedReader.readLine()) != null) {
			List<Measurement> results = null;
			results = extractMeasurementsFromNMEA(line, new ArrayList<Measurement>(1));
			return results;
		}
		return null;
	}
	
	/**
	 * 
	 * @param line raw nmea 0183 sentence line 
	 * @param results the list of measurements
	 * @return the given result list with the newly added measurements
	 */
	public List<Measurement> extractMeasurementsFromNMEA(String line, List<Measurement> results) {
		return extractMeasurementsFromNMEA(line, results,EnumSet.<NMEA0183MessageTypes>allOf(NMEA0183MessageTypes.class));
	}

	/**
	 * 
	 * @param line
	 * @param results
	 * @param processMessageTypes process only these kind of messages to speed up parsing process
	 * @return
	 */
	public List<Measurement> extractMeasurementsFromNMEA(String line, List<Measurement> results, Set<NMEA0183MessageTypes> processMessageTypes) {
		String rawContent = removeValidChecksum(line);
		if(rawContent != null) {
			String[] nmeaContent = rawContent.split(","); //$NON-NLS-1$
			NMEA0183MessageTypes messageType = NMEA0183MessageTypes.valueOf(nmeaContent[0].substring(2));
			
			if(processMessageTypes.contains(messageType)) {
				switch (messageType) {
				case MTW:
					results.add(MTW_WaterTemperature(nmeaContent));
					break;
				case MWV:
					results.add(MWV_WindMeasurement(nmeaContent));
					break;
				case VHW:
					results.add(VHW_ShipVector(nmeaContent));
					break;
				case GLL:
					MeasuredPosition3D gll_Position = GLL_Position(nmeaContent);
					if(gll_Position != null) {
						results.add(gll_Position);
					}
					break;
				case VTG:
					results.add(VTG_TrackMadeGood(nmeaContent));
					break;
				case VWR:
					results.add(VWR_RelativeWindSpeedAndAngle(nmeaContent));
					break;
				case GGA:
					MeasuredPosition3D gga_Position = GGA_Position(nmeaContent);
					if(gga_Position != null) {
						results.add(gga_Position);
					}
					break;
				case RMC:
					CompositeMeasurement rmc_Position = RMC_Position(nmeaContent);
					results.add(rmc_Position);
					break;
				case GSV:
					SatellitesVisible gsv_SatelliteData = GSV_SatelliteData(nmeaContent);
					if(gsv_SatelliteData != null) {
						results.add(gsv_SatelliteData);
					}
					break;
				case GSA:
//					results.add(GSA_SatelliteData(nmeaContent));
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
				case VLW:
					results.addAll(VLW_TotalTrip(nmeaContent));
					break;
				case HDM:
					results.add(HDM_HeadingCompass(nmeaContent));
					break;
				default:
					break;
				}
			}
		}
		return results;
	}

	
	/**
	 * checks the checksum and additionally cuts off the checksum. This is not a sideffect free method
	 * 
	 * @param rawContent
	 * @return
	 */
	protected String removeValidChecksum(String nmeaMessageContent) {
		if(!nmeaMessageContent.startsWith("$")) { //$NON-NLS-1$
			return null;
		}
		try {
			String rawContent = nmeaMessageContent.substring(1,
					nmeaMessageContent.length());
			int checksumIndex = rawContent.lastIndexOf("*"); //$NON-NLS-1$
			if(checksumIndex != -1) {
				String checksumString = rawContent.substring(checksumIndex + 1, checksumIndex + 3);
				try {
					int checkSum = Integer.parseInt(checksumString, 16);
					
					rawContent = rawContent.substring(0, checksumIndex);
					char calculatedChecksum = 0;
					for (int i = 0; i < rawContent.length(); i++) {
						calculatedChecksum ^= rawContent.charAt(i);
					}
					if(((char)checkSum) == calculatedChecksum) {
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
			Heading heading = PhysxFactory.eINSTANCE
					.createHeading();
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
			Distance totalDistanceObject = PhysxFactory.eINSTANCE.createDistance();
			totalDistanceObject.setValue(totalDistance);
			totalDistanceObject.setUnit(LengthUnit.NAUTICAL_MILE);
			totalDistanceObject.setDistanceType(DistanceType.TOTAL);
			
			double tripDistance = Double.parseDouble(nmeaContent[3]);
			Distance tripDistanceObject = PhysxFactory.eINSTANCE.createDistance();
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
		return Collections.<Measurement>emptyList();
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
		GeoFactory geoFactory = GeoFactory.eINSTANCE;
		PhysxFactory physxFactory = PhysxFactory.eINSTANCE;
		CompositeMeasurement measurement = physxFactory.createCompositeMeasurement();
		
		MeasuredPosition3D geoPosition = geoFactory.createMeasuredPosition3D();
		RelativeSpeed relativeSpeed = physxFactory.createRelativeSpeed();
		Heading heading = physxFactory.createHeading();

		setTime(nmeaContent, geoPosition, 1);
		Latitude latitude = parseLatitude(nmeaContent, 3);
		Longitude longitude = parseLongitude(nmeaContent, 5);
		geoPosition.setLatitude(latitude);
		geoPosition.setLongitude(longitude);
		
		Time time = physxFactory.createTime();
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(nmeaContent[1].substring(0, 2).trim()));
			calendar.set(Calendar.MINUTE, Integer.parseInt(nmeaContent[1].substring(2, 4).trim()));
			calendar.set(Calendar.SECOND, Integer.parseInt(nmeaContent[1].substring(4, 6).trim()));
			
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(nmeaContent[9].substring(0, 2).trim()));
			calendar.set(Calendar.MONTH, Integer.parseInt(nmeaContent[9].substring(2, 4).trim()) - 1);
			calendar.set(Calendar.YEAR, Integer.parseInt(nmeaContent[9].substring(4, 6).trim()) + 2000);
			calendar.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
			time.setTime(calendar.getTime());
			time.setTimezone("UTC"); //$NON-NLS-1$
			heading.setTime(calendar.getTime());
			heading.setTimezone("UTC"); //$NON-NLS-1$
			relativeSpeed.setTime(calendar.getTime());
			relativeSpeed.setTimezone("UTC"); //$NON-NLS-1$
			geoPosition.setTime(calendar.getTime());
			geoPosition.setTimezone("UTC"); //$NON-NLS-1$
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
		measurement.getMeasurements().add(geoPosition);
		measurement.getMeasurements().add(time);

		setSensorID(nmeaContent[0], heading);
		setSensorID(nmeaContent[0], relativeSpeed);
		setSensorID(nmeaContent[0], geoPosition);
		setSensorID(nmeaContent[0], time);

		if("A".equals(nmeaContent[2].toUpperCase())) { //$NON-NLS-1$
			heading.setValid(true);
			relativeSpeed.setValid(true);
			geoPosition.setValid(true);
			time.setValid(true);
		} 
		return measurement;
	}

	/**
	 * 
	 * @param nmeaContent
	 * @return 
	 */
	private MeasuredPosition3D GGA_Position(String[] nmeaContent) {
		MeasuredPosition3D geoPosition = GeoFactory.eINSTANCE
				.createMeasuredPosition3D();
		
		setTime(nmeaContent, geoPosition, 1);
		Latitude latitude = parseLatitude(nmeaContent, 2);
		Longitude longitude = parseLongitude(nmeaContent, 4);
		
		geoPosition.setLatitude(latitude);
		geoPosition.setLongitude(longitude);
		geoPosition.setAltitude(Double.parseDouble(nmeaContent[9]));
		
		setSensorID(nmeaContent[0], geoPosition);
		try {
			int fixQuality = Integer.parseInt(nmeaContent[6]);
			if(fixQuality > 0) {
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
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss"); //$NON-NLS-1$
			geoPosition.setTime(simpleDateFormat.parse(nmeaContent[nmeaIndex]));
			geoPosition.setTimezone("UTC");
		} catch (ParseException e) {
			Logger.getRootLogger().error("Failed to parse", e); //$NON-NLS-1$
		}
	}

	private Longitude parseLongitude(String[] nmeaContent, int nmeaStartIndex) {
		String degree;
		String minute;
		double secondValue;
		Longitude longitude = GeoFactory.eINSTANCE.createLongitude();
		String[] positions = nmeaContent[nmeaStartIndex].split("\\."); //$NON-NLS-1$
		minute = positions[0].substring(positions[0].length() - 2, positions[0].length());
		degree = positions[0].substring(0, positions[0].length() - 2);
		longitude.setDegree(Integer.parseInt(degree.trim()));
		longitude.setMinute(Integer.parseInt(minute));
		secondValue = Double.parseDouble(positions[1]) * 6 / (Math.pow(10, positions[1].length() - 1));
//		secondValue = (Double.parseDouble(nmeaContent[nmeaStartIndex]
//				.substring(3)) - longitude.getMinute()) * 60;
		longitude.setSecond(secondValue);
		if (WEST.equals(nmeaContent[nmeaStartIndex + 1])) {
			longitude.setDegree(longitude.getDegree() * (-1));
		}
		return longitude;
	}

	private Latitude parseLatitude(String[] nmeaContent, int i) {
		Latitude latitude = GeoFactory.eINSTANCE.createLatitude();

		String[] positions = nmeaContent[i].split("\\."); //$NON-NLS-1$
		String minute = positions[0].substring(positions[0].length() - 2, positions[0].length());
		String degree = positions[0].substring(0, positions[0].length() - 2);

		latitude.setDegree(Integer.parseInt(degree.trim()));
		latitude.setMinute(Integer.parseInt(minute));
		double secondValue = Double.parseDouble(positions[1]) * 6 / (Math.pow(10, positions[1].length() - 1));
		latitude.setSecond(secondValue);
		if (SOUTH.equals(nmeaContent[i + 1])) {
			latitude.setDegree(latitude.getDegree() * (-1));
		}
		return latitude;
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
		CompositeMeasurement measurement = physxFactory.createCompositeMeasurement();

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

	private MeasuredPosition3D GLL_Position(String[] nmeaContent) {
		try {
			MeasuredPosition3D geoPosition = GeoFactory.eINSTANCE.createMeasuredPosition3D();
			Latitude latitude = GeoFactory.eINSTANCE.createLatitude();
			double lat = Double.parseDouble(nmeaContent[1]);
			latitude.setDegree(Integer.parseInt(nmeaContent[1].substring(0, 2)));
			latitude.setMinute(Integer.parseInt(nmeaContent[1].substring(2, 4)));
			latitude.setSecond((lat - ((int) lat)) * 60);
			geoPosition.setLatitude(latitude);
			if (nmeaContent[2].equals(SOUTH)) {
				latitude.setDegree(latitude.getDegree() * -1);
			}
			
			Longitude longitude = GeoFactory.eINSTANCE.createLongitude();
			double lon = Double.parseDouble(nmeaContent[3]);
			longitude.setDegree(Integer.parseInt(nmeaContent[3].substring(0, 3)));
			longitude.setMinute(Integer.parseInt(nmeaContent[3].substring(3, 5)));
			longitude.setSecond((lon - ((int) lon)) * 60);
			geoPosition.setLatitude(latitude);
			if (nmeaContent[4].equals(WEST)) {
				longitude.setDegree(longitude.getDegree() * -1);
			}
			geoPosition.setLongitude(longitude);
			setSensorID(nmeaContent[0], geoPosition);
			setTime(nmeaContent, geoPosition, 5);
			
			if("A".equals(nmeaContent[6])) { //$NON-NLS-1$
				geoPosition.setValid(true);
			} 
			return geoPosition;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private CompositeMeasurement VHW_ShipVector(String[] nmeaContent) {
			PhysxFactory physxFactory = PhysxFactory.eINSTANCE;
			CompositeMeasurement measurement = physxFactory.createCompositeMeasurement();
		
			if (!nmeaContent[1].isEmpty()) {
				Heading heading = physxFactory.createHeading();
				heading.setHeadingType(HeadingType.TRUE);
				heading.setDegrees(Double.parseDouble(nmeaContent[1]));
				measurement.getMeasurements().add(heading);
			} 
			if (!nmeaContent[3].isEmpty()) {
				Heading heading = physxFactory.createHeading();
				heading.setHeadingType(HeadingType.MAGNETIC);
				heading.setDegrees(Double.parseDouble(nmeaContent[3]));
				measurement.getMeasurements().add(heading);
			}

			if (!nmeaContent[5].isEmpty()) {
				Speed speed = PhysxFactory.eINSTANCE.createSpeed();
				speed.setSpeed(Double.parseDouble(nmeaContent[5]));
				speed.setSpeedUnit(SpeedUnit.N);
				RelativeSpeed relativeSpeed = physxFactory.createRelativeSpeed();
				relativeSpeed.setKey(SpeedType.SPEEDTHOUGHWATER);
				relativeSpeed.setValue(speed);
				setSensorID(nmeaContent[0], relativeSpeed);
				measurement.getMeasurements().add(relativeSpeed);
				return measurement;
			} else if (!nmeaContent[7].isEmpty()) {
				Speed speed = PhysxFactory.eINSTANCE.createSpeed();
				speed.setSpeed(Double.parseDouble(nmeaContent[7]));
				speed.setSpeedUnit(SpeedUnit.K);
				RelativeSpeed relativeSpeed = physxFactory.createRelativeSpeed();
				relativeSpeed.setKey(SpeedType.SPEEDTHOUGHWATER);
				relativeSpeed.setValue(speed);
				setSensorID(nmeaContent[0], relativeSpeed);
				measurement.getMeasurements().add(relativeSpeed);
				return measurement;
			}
			return null;
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
		depth.setTime(Calendar.getInstance().getTime());
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.KEEL);
		setDepthFromContent(nmeaContent, depth);
		return depth;
	}

	private Depth DBS_DepthBelowSurface(String[] nmeaContent) {
		Depth depth = GeoFactory.eINSTANCE.createDepth();
		depth.setTime(Calendar.getInstance().getTime());
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.SURFACE);
		setDepthFromContent(nmeaContent, depth);
		return depth;
	}

	private Depth DBT_DepthBelowTransducer(String[] nmeaContent) {
		Depth depth = GeoFactory.eINSTANCE.createDepth();
		depth.setTime(Calendar.getInstance().getTime());
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.TRANSDUCER);
		setDepthFromContent(nmeaContent, depth);
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
}
