package net.sf.seesea.provider.navigation.nmea.v2000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;
import net.sf.seesea.model.core.physx.Distance;
import net.sf.seesea.model.core.physx.DistanceType;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.LengthUnit;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.core.physx.RelativeSpeed;
import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.model.core.physx.Temperature;
import net.sf.seesea.model.core.physx.TemperatureUnit;
import net.sf.seesea.model.core.physx.Time;
import net.sf.seesea.model.core.weather.WeatherFactory;
import net.sf.seesea.model.core.weather.WindMeasurement;
import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.HeadingSensorReference;
import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.TemperatureSource;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.COGSOG;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.DistanceLog;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.EnvironmentalParameters1;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.EnvironmentalParameters2;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.GNSSPositionData;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.PositionRapid;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.SpeedWaterReferenced;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.TimeDate;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.VesselHeading;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.WaterDepth;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.WindData;

public class NMEA2000Reader {

	private final Set<Integer> pgnsToAnalyze;
	
	private long currentTimeMillis;

	public NMEA2000Reader(Set<Integer> pgnsToAnalyze) {
		this.pgnsToAnalyze = pgnsToAnalyze;
		currentTimeMillis = System.currentTimeMillis();
	}
	
	public NMEA2000Reader() {
		this.pgnsToAnalyze = Collections.emptySet();
	}
	
	public List<Measurement> extractMeasurementsFromNGT(int[] data) {
		long pgn = 0x40000 + data[0];
		return getMessages(pgn, 0, 0, 0, data, 1);
	}
	
	public List<Measurement> extractMeasurementsFromNMEA(int[] data) {
//		int priority = data[0];
		long pgn = (long) data[1] + 256L
				* ((long) data[2] + 256L * (long) data[3]);
//		int destination = data[4];
		int source = data[5];
//		int messageLength = data[10];
		return getMessages(pgn, source, 0, 0, data, 11);
	}
	
	private List<Measurement> getMessages(long pgn, int source, int destination, int priority, int[] data, int startIndex) {
		List<Measurement> results = new ArrayList<Measurement>(1);
		if(pgnsToAnalyze.isEmpty() || pgnsToAnalyze.contains((int)pgn)) {
			switch ((int) pgn) {
			case 60928:
//			System.out.println("ISO Address clain" + pgn);
				break;
			case 127250:
				// System.out.println("Vessel Heading" + pgn);
				VesselHeading vesselHeading = new VesselHeading(Arrays.copyOfRange(
						data, startIndex, startIndex + 8));
				Heading heading = PhysxFactory.eINSTANCE.createHeading();
				heading.setDegrees(vesselHeading.getHeadingSensorReading()
						.getValue() * 180 / Math.PI);
				if (vesselHeading.getHeadingSensorReference().equals(
						HeadingSensorReference.Magnetic)) {
					heading.setHeadingType(HeadingType.MAGNETIC);
				} else if (vesselHeading.getHeadingSensorReference().equals(
						HeadingSensorReference.True)) {
					heading.setHeadingType(HeadingType.TRUE);
				} else {
					heading.setHeadingType(HeadingType.UNKNOWN);
				}
				results.add(heading);
				break;
			case 127251:
				// rate of turn
				break;
			case 127258:
				// Magnetic Variation
				break;
			case 128259:
				// System.out.println("Speed, Water referenced " + pgn);
				SpeedWaterReferenced speedWaterReferenced = new SpeedWaterReferenced(
						Arrays.copyOfRange(data, startIndex, startIndex + 8));
				if (speedWaterReferenced.getSpeedWaterReferenced().isNaN()) {
					return results;
				}
				RelativeSpeed speed = PhysxFactory.eINSTANCE.createRelativeSpeed();
				Speed speedx = PhysxFactory.eINSTANCE.createSpeed();
				speedx.setSpeed(speedWaterReferenced.getSpeedWaterReferenced());
				speedx.setSpeedUnit(SpeedUnit.N);
				speed.setKey(SpeedType.SPEEDTHOUGHWATER);
				speed.setValue(speedx);
				speed.setValid(true);
				results.add(speed);
				break;
			case 128267:
				WaterDepth waterDepth = new WaterDepth(Arrays.copyOfRange(data, startIndex, startIndex + 8));
				if(!waterDepth.getWaterDepthAtTransducer().isNaN() && waterDepth.getWaterDepthAtTransducer() < 12000 && waterDepth.getWaterDepthAtTransducer() >= 0.0) {
					Depth depth = GeoFactory.eINSTANCE.createDepth();
					depth.setDepth(waterDepth.getWaterDepthAtTransducer());
					depth.setValid(true);
					depth.setMeasurementPosition(RelativeDepthMeasurementPosition.TRANSDUCER);
					results.add(depth);
				}
				break;
			case 128275:
				DistanceLog log = new DistanceLog(Arrays.copyOfRange(data, startIndex, startIndex + 14));
				if(log.getDistanceVoyage().isValid()) {
					double tripDistance = log.getDistanceVoyage().getValue();
					Distance tripDistanceObject = PhysxFactory.eINSTANCE
							.createDistance();
					tripDistanceObject.setValue(tripDistance);
					tripDistanceObject.setUnit(LengthUnit.METERS);
					tripDistanceObject.setDistanceType(DistanceType.TRIP);
				}
				if(log.getTotalDistance().isValid()) {
					double totalDistance = log.getTotalDistance().getValue();
					Distance totalDistanceObject = PhysxFactory.eINSTANCE
							.createDistance();
					totalDistanceObject.setValue(totalDistance);
					totalDistanceObject.setUnit(LengthUnit.METERS);
					totalDistanceObject.setDistanceType(DistanceType.TOTAL);
				}
				break;
			case 129025:
//			System.out.println("Position, Rapid Update " + pgn);
//			PositionRapid positionRapid = new PositionRapid(Arrays.copyOfRange(
//					data, startIndex, startIndex + 8));
//			MeasuredPosition3D measuredPositionRapid = GeoFactory.eINSTANCE
//					.createMeasuredPosition3D();
//			Latitude lat2 = GeoFactory.eINSTANCE.createLatitude();
//			lat2.setDecimalDegree(positionRapid.getLatitude().getValue());
//			measuredPositionRapid.setLatitude(lat2);
//			Longitude lon2 = GeoFactory.eINSTANCE.createLongitude();
//			lon2.setDecimalDegree(positionRapid.getLongitude().getValue());
//			measuredPositionRapid.setLongitude(lon2);
//			measuredPositionRapid.setSensorID(new Integer(source).toString());
//			measuredPositionRapid.setValid(true);
//			results.add(measuredPositionRapid);
				break;
			case 129026:
				// System.out.println("COG & SOG, Rapid Update " + pgn);
				COGSOG cogsog = new COGSOG(Arrays.copyOfRange(data, 11, 19));
				RelativeSpeed relSpeed = PhysxFactory.eINSTANCE
						.createRelativeSpeed();
				relSpeed.setKey(SpeedType.COG);
				Speed speed1 = PhysxFactory.eINSTANCE.createSpeed();
				speed1.setSpeed(cogsog.getSpeed().getValue());
				speed1.setSpeedUnit(SpeedUnit.N);
				relSpeed.setValue(speed1);
				Heading heading2 = PhysxFactory.eINSTANCE.createHeading();
				heading2.setDegrees(cogsog.getCourseOverGround().getValue() * 180
						/ Math.PI);
				heading2.setHeadingType(cogsog.getHeadingType());
				results.add(relSpeed);
				results.add(heading2);
				break;
			case 129029:
				// System.out.println("GNSS Position Data" + pgn);
				GNSSPositionData gnssPositionData = new GNSSPositionData(
						Arrays.copyOfRange(data, 11, data.length));
				if (gnssPositionData.getLatitude() != null && gnssPositionData.getLatitude().isValid() && gnssPositionData.getLongitude().isValid()) {
					MeasuredPosition3D measuredPosition3D = GeoFactory.eINSTANCE
							.createMeasuredPosition3D();
					measuredPosition3D.setPrecision(8); // as defined by standard - mustn't be the true value
					Latitude latitude = GeoFactory.eINSTANCE.createLatitude();
					latitude.setDecimalDegree(gnssPositionData.getLatitude()
							.getValue());
					measuredPosition3D.setLatitude(latitude);
					Longitude longitude = GeoFactory.eINSTANCE.createLongitude();
					longitude.setDecimalDegree(gnssPositionData.getLongitude()
							.getValue());
					measuredPosition3D.setLongitude(longitude);
					measuredPosition3D.setAltitude(gnssPositionData.getAltitude()
							.getValue());
					measuredPosition3D.setSensorID(new Integer(source).toString());
					measuredPosition3D.setTime(gnssPositionData.getTime());
					measuredPosition3D.setValid(true);
					Time time = PhysxFactory.eINSTANCE.createTime();
					time.setTime(gnssPositionData.getTime());
					time.setValid(true);
					results.add(measuredPosition3D);
					results.add(time);
//					if(!MethodGNSS.PRECISEGNSS.equals(gnssPositionData.getMethodGNSS())) {
//						System.out.println(latitude.getDecimalDegree() + ":" + longitude.getDecimalDegree() + ":" + gnssPositionData.getMethodGNSS());
//					}
//					if(GNSSIntegrity.UNSAFE
//							.equals(gnssPositionData.getQuality())) {
//						System.out.println(latitude.getDecimalDegree() + ":" + longitude.getDecimalDegree() + ":" + gnssPositionData.getQuality());
//					}
//					if(gnssPositionData.getSatelitesVisible() > 14) {
//						System.out.println(latitude.getDecimalDegree() + ":" + longitude.getDecimalDegree() + ":" + gnssPositionData.getSatelitesVisible());
//					}
				}
				break;
			case 129033:
				// System.out.println("Time and Date" + pgn);
				TimeDate timeDate = new TimeDate(Arrays.copyOfRange(data, 11, 19));
				Time time = PhysxFactory.eINSTANCE.createTime();
				time.setTime(timeDate.getDate());
				time.setSensorID(new Integer(source).toString());
				time.setValid(true);
				results.add(time);
				break;
			case 129539:
				// GNSS DOP
				break;
			case 129540:
//			// System.out.println("GNSS Sats in View " + pgn);
//			GNSSSatsInView satsInview = new GNSSSatsInView(Arrays.copyOfRange(
//					data, 11, data.length));
//			results.add(satsInview.getSatellitesVisible());
				break;
			case 130306:
				// System.out.println("Wind Data" + pgn);
				WindData windData = new WindData(Arrays.copyOfRange(data, 11, 19));
				WindMeasurement wind = WeatherFactory.eINSTANCE
						.createWindMeasurement();
				wind.setAngle(windData.getWindDirection().getValue() * 180
						/ Math.PI);
				wind.setSpeed(windData.getGenericSpeed().getValue());
				wind.setSpeedUnit(SpeedUnit.N);
				wind.setValid(true);
				results.add(wind);
				break;
			case 130310:
				// System.out.println("Environmental Parameters 1 " + pgn);
				EnvironmentalParameters1 parameters1 = new EnvironmentalParameters1(
						data);
				Temperature temperature = PhysxFactory.eINSTANCE
						.createTemperature();
				temperature.setUnit(TemperatureUnit.KELVIN);
				temperature.setValue(parameters1.getWaterTemperature().getValue());
				results.add(temperature);
				break;
			case 130311:
				EnvironmentalParameters2 parameters2 = new EnvironmentalParameters2(data);
				if(TemperatureSource.SeaTemperature.equals(parameters2.getTemperatureSource())) {
					Temperature wtemperature = PhysxFactory.eINSTANCE
							.createTemperature();
					wtemperature.setUnit(TemperatureUnit.KELVIN);
					wtemperature.setValue(parameters2.getTemperature().getValue());
					results.add(wtemperature);
				}
				
//			System.out.println("Environmental Parameters 2 " + pgn);
				break;
			default:
				// 130823:  Browser Control Status : 
				if (pgn > 120000) {
					System.out.println("NMEA PGN " + pgn);
				} else {
//				System.out.println("Engine PGN " + pgn);
				}
				break;
			}
		}
		if (pgn > 120000) {
			System.out.println("NMEA PGN " + pgn);
//		} else {
//		System.out.println("Engine PGN " + pgn);
		}
//		break;
//	}
		
		return results;
	}

}
