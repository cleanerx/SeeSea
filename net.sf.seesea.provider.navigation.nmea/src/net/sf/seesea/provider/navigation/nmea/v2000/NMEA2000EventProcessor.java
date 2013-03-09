package net.sf.seesea.provider.navigation.nmea.v2000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.CompositeMeasurement;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
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
import net.sf.seesea.provider.navigation.nmea.NMEAParser;
import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.HeadingSensorReference;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.COGSOG;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.EnvironmentalParameters1;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.GNSSPositionData;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.GNSSSatsInView;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.PositionRapid;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.SpeedWaterReferenced;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.TimeDate;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.VesselHeading;
import net.sf.seesea.provider.navigation.nmea.v2000.pgn.WindData;
import net.sf.seesea.services.navigation.provider.IPositionProvider;
import net.sf.seesea.services.navigation.provider.IShipMovementVectorProvider;
import net.sf.seesea.services.navigation.provider.IWaterTemperatureDataProvider;
import net.sf.seesea.services.navigation.provider.IWindDataProvider;

public class NMEA2000EventProcessor extends NMEAParser implements INMEA2000Listener,
IPositionProvider, IWaterTemperatureDataProvider, IWindDataProvider,
IShipMovementVectorProvider {

	private List<INMEA2000Reader> _nmeaReaders  = Collections.synchronizedList(new ArrayList<INMEA2000Reader>(1));

	@Override
	protected String getProviderName() {
		return "NMEA2000"; //$NON-NLS-1$
	}
	
	public NMEA2000EventProcessor() {
	}
	
	@Override
	public void nmeaEventReceived(int[] data) {
		int priority = data[0];
		long pgn = (long)data[1] + 256L * ((long) data[2] + 256L * (long) data[3]);
		int destination = data[4];
		int source = data[5];
		int messageLength = data[10];
		switch ((int)pgn) {
		case 60928:
			System.out.println("ISO Address clain" + pgn);
			break;
		case 127250:
			System.out.println("Vessel Heading" + pgn);
			VesselHeading vesselHeading = new VesselHeading(Arrays.copyOfRange(data, 11, 19));
			Heading heading = PhysxFactory.eINSTANCE.createHeading();
			heading.setDegrees(vesselHeading.getHeadingSensorReading().getValue() * 180 / Math.PI);
			if(vesselHeading.getHeadingSensorReference().equals(HeadingSensorReference.Magnetic)) {
				heading.setHeadingType(HeadingType.MAGNETIC);
			} else if(vesselHeading.getHeadingSensorReference().equals(HeadingSensorReference.True)) {
				heading.setHeadingType(HeadingType.TRUE);
			} else {
				heading.setHeadingType(HeadingType.UNKNOWN);
			}
			notifyListeners(heading);
			break;
		case 128259:
//			System.out.println("Speed, Water referenced " + pgn);
			SpeedWaterReferenced speedWaterReferenced = new SpeedWaterReferenced(Arrays.copyOfRange(data, 11, 19));
			if(speedWaterReferenced.getSpeedWaterReferenced().isNaN()) {
				return;
			} 
			RelativeSpeed speed = PhysxFactory.eINSTANCE.createRelativeSpeed();
			Speed speedx = PhysxFactory.eINSTANCE.createSpeed();
			speedx.setSpeed(speedWaterReferenced.getSpeedWaterReferenced());
			speedx.setSpeedUnit(SpeedUnit.N);	
			speed.setKey(SpeedType.SPEEDTHOUGHWATER);
			speed.setValue(speedx);
			speed.setValid(true);
			notifyListeners(speed);
			break;
		case 129025:
			System.out.println("Position, Rapid Update " + pgn);
			PositionRapid positionRapid = new PositionRapid(Arrays.copyOfRange(data, 11, 19));
			break;
		case 129026:
			System.out.println("COG & SOG, Rapid Update " + pgn);
			COGSOG cogsog = new COGSOG(Arrays.copyOfRange(data, 11, 19));
			RelativeSpeed relSpeed = PhysxFactory.eINSTANCE.createRelativeSpeed();
			relSpeed.setKey(SpeedType.COG);
			Speed speed1 = PhysxFactory.eINSTANCE.createSpeed();
			speed1.setSpeed(cogsog.getSpeed().getValue());
			speed1.setSpeedUnit(SpeedUnit.N);
			relSpeed.setValue(speed1);
			Heading heading2 = PhysxFactory.eINSTANCE.createHeading();
			heading2.setDegrees(cogsog.getCourseOverGround().getValue() * 180 / Math.PI);
			heading2.setHeadingType(cogsog.getHeadingType());
			CompositeMeasurement compositeMeasurement = PhysxFactory.eINSTANCE.createCompositeMeasurement();
			EList<Measurement> measurements = compositeMeasurement.getMeasurements();
			measurements.add(relSpeed);
			measurements.add(heading2);
			notifyListeners(compositeMeasurement);
			break;
		case 129029:
//			System.out.println("GNSS Position Data" + pgn);
			GNSSPositionData gnssPositionData = new GNSSPositionData(Arrays.copyOfRange(data, 11, data.length));
			if(gnssPositionData.getLatitude() != null) {
				MeasuredPosition3D measuredPosition3D = GeoFactory.eINSTANCE.createMeasuredPosition3D();
				Latitude latitude = GeoFactory.eINSTANCE.createLatitude();
				latitude.setDecimalDegree(gnssPositionData.getLatitude().getValue());
				measuredPosition3D.setLatitude(latitude);
				Longitude longitude = GeoFactory.eINSTANCE.createLongitude();
				longitude.setDecimalDegree(gnssPositionData.getLongitude().getValue());
				measuredPosition3D.setLongitude(longitude);
				measuredPosition3D.setAltitude(gnssPositionData.getAltitude().getValue());
				measuredPosition3D.setSensorID(new Integer(source).toString());
				measuredPosition3D.setValid(true);
				notifyListeners(measuredPosition3D);
				break;
			}
		case 129033:
			System.out.println("Time and Date" + pgn);
			TimeDate timeDate = new TimeDate(Arrays.copyOfRange(data, 11, 19));
			Time time = PhysxFactory.eINSTANCE.createTime();
			time.setTime(timeDate.getDate());
			time.setValid(true);
			notifyListeners(time);
			break;
		case 129540:
			System.out.println("GNSS Sats in View " + pgn);
			GNSSSatsInView satsInview = new GNSSSatsInView(Arrays.copyOfRange(data, 11, data.length));
			notifyListeners(satsInview.getSatellitesVisible());
			break;
		case 130306:
			System.out.println("Wind Data" + pgn);
			WindData windData = new WindData(Arrays.copyOfRange(data, 11, 19));
			WindMeasurement wind = WeatherFactory.eINSTANCE.createWindMeasurement();
			wind.setAngle(windData.getWindDirection().getValue() * 180 / Math.PI);
			wind.setSpeed(windData.getGenericSpeed().getValue());
			wind.setSpeedUnit(SpeedUnit.N);
			wind.setValid(true);
			notifyListeners(wind);
			break;
		case 130310:
			System.out.println("Environmental Parameters 1 " + pgn);
			EnvironmentalParameters1 parameters1 = new EnvironmentalParameters1(data);
			Temperature temperature = PhysxFactory.eINSTANCE.createTemperature();
			temperature.setUnit(TemperatureUnit.KELVIN);
			temperature.setValue(parameters1.getWaterTemperature().getValue());
			notifyListeners(temperature);
			break;
		case 130311:
			System.out.println("Environmental Parameters 2 " + pgn);
			break;
		default:
			if(pgn > 120000) { 
				System.out.println("NMEA PGN " + pgn);
			} else {
				System.out.println("Engine PGN " + pgn);
			}
			break;
		}
	}
	
	public synchronized void bindReader(INMEA2000Reader reader) {
		_nmeaReaders.add(reader);
		reader.addNMEA2000Listener(this);
	}

	public synchronized void unbindReader(INMEA2000Reader reader) {
		if (_nmeaReaders.size() == 1) {
			heartbeatThread.interrupt();
		}
		reader.removeNMEA2000Listener(this);
		_nmeaReaders.remove(reader);
	}
	
//	@Override
//	public void disable() {
//		heartbeatThread.interrupt();
//	}


}
