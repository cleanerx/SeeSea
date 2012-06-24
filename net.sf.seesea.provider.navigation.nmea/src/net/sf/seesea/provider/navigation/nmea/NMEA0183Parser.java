/**
 * 
 Copyright (c) 2010, Jens Kübler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL Jens Kübler BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.provider.navigation.nmea;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;
import net.sf.seesea.model.core.physx.HandOrientation;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.core.physx.RelativeWind;
import net.sf.seesea.model.core.physx.SatelliteInfo;
import net.sf.seesea.model.core.physx.SatellitesVisible;
import net.sf.seesea.model.core.physx.ShipMovementVector;
import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.model.core.physx.Temperature;
import net.sf.seesea.model.core.physx.TemperatureUnit;
import net.sf.seesea.model.core.physx.Time;
import net.sf.seesea.model.core.weather.Reference;
import net.sf.seesea.model.core.weather.WeatherFactory;
import net.sf.seesea.model.core.weather.WindMeasurement;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.services.navigation.listener.IRelativeWindSpeedListener;
import net.sf.seesea.services.navigation.listener.ISatelliteInfoListener;
import net.sf.seesea.services.navigation.listener.ISpeedVectorListener;
import net.sf.seesea.services.navigation.listener.ITimeListener;
import net.sf.seesea.services.navigation.listener.ITrackMadeGoodGroundSpeedListener;
import net.sf.seesea.services.navigation.listener.IWaterTemperatureListener;
import net.sf.seesea.services.navigation.provider.IPositionProvider;
import net.sf.seesea.services.navigation.provider.IShipMovementVectorProvider;
import net.sf.seesea.services.navigation.provider.IWaterTemperatureDataProvider;
import net.sf.seesea.services.navigation.provider.IWindDataProvider;

import org.apache.log4j.Logger;

public class NMEA0183Parser extends NMEAParser implements
		NMEAEventListener, IPositionProvider, IWaterTemperatureDataProvider,
		IWindDataProvider, IShipMovementVectorProvider {

	protected String PROVIDER_NAME = "NMEA"; //$NON-NLS-1$

	private static final String LEFT = "L"; //$NON-NLS-1$
	private static final String RIGHT = "R"; //$NON-NLS-1$

	private static final String WEST = "W"; //$NON-NLS-1$
	private static final String SOUTH = "S"; //$NON-NLS-1$

	private final Set<SatelliteInfo> satelliteDataCache;
	private int satelliteMessageCounter;

	private List<INMEAReader> _nmeaReaders = Collections
			.synchronizedList(new ArrayList<INMEAReader>(1));

	
	/**
	 * 
	 */
	public NMEA0183Parser() {
		satelliteDataCache = new HashSet<SatelliteInfo>();
		satelliteMessageCounter = 0;
	}
	
	public synchronized void bindReader(INMEAReader reader) {
		_nmeaReaders.add(reader);
		reader.addNMEAEventListener(this);
	}

	public synchronized void unbindReader(INMEAReader reader) {
		if(_nmeaReaders.size() == 1) {
			heartbeatThread.interrupt();
		}
		reader.removeNMEAEventListener(this);
		_nmeaReaders.remove(reader);
	}

	/**
	 * 
	 */
	@Override
	public void receiveNMEAEvent(NMEAEvent e) {
		// System.out.println(e.getNmeaMessageContent());
		try {
			if (removeValidChecksum(e.getNmeaMessageContent())) {
				String rawContent = e.getNmeaMessageContent().substring(1,
						e.getNmeaMessageContent().length() - 2);
				int checksumIndex = rawContent.lastIndexOf("*"); //$NON-NLS-1$
				rawContent = rawContent.substring(0, checksumIndex);
				
				List<String> nmeaListContent = new ArrayList<String>();
				int lastIndex = 0;
				for (int i = 0; i < rawContent.length(); i++) {
					Character c = rawContent.charAt(i);
					if (c.equals(',')) {
						nmeaListContent.add(rawContent.substring(lastIndex, i));
						lastIndex = i + 1;
					}
				}
				String[] nmeaContent = new String[nmeaListContent.size()];
				nmeaContent = nmeaListContent.toArray(nmeaContent);
				
				NMEASentence nmeaSentence = new NMEASentence();
				nmeaSentence.setTalkerId(nmeaContent[0].substring(0, 2));
				
				NMEA0183MessageTypes messageType = NMEA0183MessageTypes.valueOf(nmeaContent[0].substring(2));
				
				switch (messageType) {
				case MTW:
					MTW_WaterTemperature(nmeaContent);
					break;
				case MWV:
					MWV_WindMeasurement(nmeaContent);
					break;
				case VHW:
					VHW_ShipVector(nmeaContent);
					break;
				case GLL:
					GLL_Position(nmeaContent);
					break;
				case VTG:
					VTG_TrackMadeGood(nmeaContent);
					break;
				case VWR:
					VWR_RelativeWindSpeedAndAngle(nmeaContent);
					break;
				case GGA:
					GGA_Position(nmeaContent);
					break;
				case RMC:
					RMC_Position(nmeaContent);
					break;
				case GSV:
					GSV_SatelliteData(nmeaContent);
					break;
				case GSA:
					GSA_SatelliteData(nmeaContent);
					break;
				case DBK:
					DBK_DepthBelowKeel(nmeaContent);
					break;
				case DBS:
					DBS_DepthBelowSurface(nmeaContent);
					break;
				case DBT:
					DBT_DepthBelowTransducer(nmeaContent);
					break;
				case VLW:
					VLW_TotalTrip(nmeaContent);
					break;
				case HDM:
					HDM_HeadingCompass(nmeaContent);
					break;
				default:
					break;
				}
				
			}
			
		} catch (Throwable t) {
			Logger.getLogger(getClass()).error("Failed to process message " + e.getNmeaMessageContent(), t);
		}
	}

	private void HDM_HeadingCompass(String[] nmeaContent) {
		try {
			ShipMovementVector shipMovementVector = PhysxFactory.eINSTANCE
					.createShipMovementVector();
			if (!nmeaContent[1].isEmpty()) {
				shipMovementVector.getHeadings().put(HeadingType.MAGNETIC,
						Double.parseDouble(nmeaContent[1]));
			}

			synchronized (_speedVectorListeners) {
				heartbeat(ISpeedVectorListener.class);
				for (ISpeedVectorListener speedVectorListener : _speedVectorListeners) {
					speedVectorListener.notify(shipMovementVector);
				}
			}

		} catch (NumberFormatException e) {
			// nothing to do. fail silently
		}
	}

	private void VLW_TotalTrip(String[] nmeaContent) {
		try {
			// in nautical miles
			double totalDistance = Double.parseDouble(nmeaContent[1]);
			double tripDistance = Double.parseDouble(nmeaContent[3]);
		} catch (NumberFormatException e) {
			// nothing to do. fail silently
		}
	}

	private void GSA_SatelliteData(String[] nmeaContent) {

	}

	/**
	 * 
	 * @param nmeaContent
	 */
	private void GSV_SatelliteData(String[] nmeaContent) {
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
			heartbeat(ISatelliteInfoListener.class);
			for (ISatelliteInfoListener satelliteInfoListener : _satelliteInfoListeners) {
				SatellitesVisible satellitesVisible = PhysxFactory.eINSTANCE
						.createSatellitesVisible();
				satellitesVisible.getSatelliteInfo().addAll(satelliteDataCache);
				satelliteDataCache.clear();
				satelliteInfoListener.notify(satellitesVisible);
			}
		}

	}

	/**
	 * @param nmeaContent
	 */
	private void RMC_Position(String[] nmeaContent) {
		MeasuredPosition3D geoPosition = GeoFactory.eINSTANCE
				.createMeasuredPosition3D();
		setTime(nmeaContent, geoPosition, 1);
		Latitude latitude = parseLatitude(nmeaContent, 3);
		Longitude longitude = parseLongitude(nmeaContent, 5);
		geoPosition.setLatitude(latitude);
		geoPosition.setLongitude(longitude);
		
		Time time = PhysxFactory.eINSTANCE.createTime();
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(nmeaContent[1].substring(0, 2).trim()));
			calendar.set(Calendar.MINUTE, Integer.parseInt(nmeaContent[1].substring(2, 4).trim()));
			calendar.set(Calendar.SECOND, Integer.parseInt(nmeaContent[1].substring(4, 6).trim()));

			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(nmeaContent[9].substring(0, 2).trim()));
			calendar.set(Calendar.MONTH, Integer.parseInt(nmeaContent[9].substring(2, 4).trim()) - 1);
			calendar.set(Calendar.YEAR, Integer.parseInt(nmeaContent[9].substring(4, 6).trim()) + 2000);
			calendar.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
			time.setDate(calendar.getTime());
			time.setTimezone("UTC"); //$NON-NLS-1$
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

		ShipMovementVector movementVector = PhysxFactory.eINSTANCE
				.createShipMovementVector();
		try {
			Speed speed = PhysxFactory.eINSTANCE.createSpeed();
			speed.setSpeed(Double.parseDouble(nmeaContent[7]));
			movementVector.getSpeeds().put(SpeedType.COG, speed);
			speed.setSpeedUnit(SpeedUnit.N);
		} catch (NumberFormatException e) {
			// nothing to do. fail silently
		}
		try {
			movementVector.getHeadings().put(HeadingType.COG,
					Double.parseDouble(nmeaContent[8]));
		} catch (NumberFormatException e) {
			// nothing to do. fail silently
		}
		heartbeat(ISpeedVectorListener.class);
		for (ISpeedVectorListener speedVectorListener : _speedVectorListeners) {
			speedVectorListener.notify(movementVector);
		}

		heartbeat(IPositionListener.class);
		for (IPositionListener listener : _positionListeners) {
			listener.notify(geoPosition);
		}
		
		heartbeat(ITimeListener.class);
		for (ITimeListener listener : _timeListeners) {
			listener.notify(time);
		}

	}

	/**
	 * 
	 * @param nmeaContent
	 */
	private void GGA_Position(String[] nmeaContent) {
		MeasuredPosition3D geoPosition = GeoFactory.eINSTANCE
				.createMeasuredPosition3D();

		setTime(nmeaContent, geoPosition, 1);
		Latitude latitude = parseLatitude(nmeaContent, 2);
		Longitude longitude = parseLongitude(nmeaContent, 4);

		geoPosition.setLatitude(latitude);
		geoPosition.setLongitude(longitude);
		geoPosition.setAltitude(Double.parseDouble(nmeaContent[9]));

		synchronized (_positionListeners) {
			heartbeat(IPositionListener.class);
			for (IPositionListener listener : _positionListeners) {
				listener.notify(geoPosition);
			}
		}
	}

	private void setTime(String[] nmeaContent, MeasuredPosition3D geoPosition,
			int nmeaIndex) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss"); //$NON-NLS-1$
			geoPosition.setTime(simpleDateFormat.parse(nmeaContent[nmeaIndex]));
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

	private void VWR_RelativeWindSpeedAndAngle(String[] nmeaContent) {
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
		synchronized (_relativeWindSpeedListeners) {
			heartbeat(IRelativeWindSpeedListener.class);
			for (IRelativeWindSpeedListener listener : _relativeWindSpeedListeners) {
				listener.notify(relativeWind);
			}
		}
	}

	private void VTG_TrackMadeGood(String[] nmeaContent) {
		ShipMovementVector shipMovementVector = PhysxFactory.eINSTANCE
				.createShipMovementVector();
		if (nmeaContent[1].length() > 0) {
			shipMovementVector.getHeadings().put(HeadingType.TRUE,
					Double.parseDouble(nmeaContent[1]));
		}
		if (nmeaContent[3].length() > 0) {
			shipMovementVector.getHeadings().put(HeadingType.MAGNETIC,
					Double.parseDouble(nmeaContent[3]));
		}
		if (nmeaContent[5].length() > 0) {
			Speed speed = PhysxFactory.eINSTANCE.createSpeed();
			speed.setSpeed(Double.parseDouble(nmeaContent[5]));
			speed.setSpeedUnit(SpeedUnit.N);
			shipMovementVector.getSpeeds().put(SpeedType.COG, speed);
		}
		heartbeat(ITrackMadeGoodGroundSpeedListener.class);
		for (ITrackMadeGoodGroundSpeedListener listener : _trackMadeGoodListeners) {
			listener.notify(shipMovementVector);
		}
	}

	private void GLL_Position(String[] nmeaContent) {
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
		synchronized (_positionListeners) {
			heartbeat(IPositionListener.class);
			for (IPositionListener listener : _positionListeners) {
				listener.notify(geoPosition);
			}
		}
	}

	private void VHW_ShipVector(String[] nmeaContent) {
		for (ISpeedVectorListener listener : _speedVectorListeners) {
			ShipMovementVector shipMovementVector = PhysxFactory.eINSTANCE
					.createShipMovementVector();

			if (!nmeaContent[1].isEmpty()) {
				shipMovementVector.getHeadings().put(HeadingType.TRUE,
						Double.parseDouble(nmeaContent[1]));
			} 
			if (!nmeaContent[3].isEmpty()) {
				shipMovementVector.getHeadings().put(HeadingType.MAGNETIC,
						Double.parseDouble(nmeaContent[3]));
			}

			if (!nmeaContent[5].isEmpty()) {
				Speed speed = PhysxFactory.eINSTANCE.createSpeed();
				speed.setSpeed(Double.parseDouble(nmeaContent[5]));
				speed.setSpeedUnit(SpeedUnit.N);
				shipMovementVector.getSpeeds().put(SpeedType.SPEEDTHOUGHWATER, speed);
				synchronized (_speedVectorListeners) {
					heartbeat(ISpeedVectorListener.class);
					listener.notify(shipMovementVector);
				}
			} else if (!nmeaContent[7].isEmpty()) {
				Speed speed = PhysxFactory.eINSTANCE.createSpeed();
				speed.setSpeed(Double.parseDouble(nmeaContent[7]));
				speed.setSpeedUnit(SpeedUnit.K);
				shipMovementVector.getSpeeds().put(SpeedType.SPEEDTHOUGHWATER, speed);
				synchronized (_speedVectorListeners) {
					heartbeat(ISpeedVectorListener.class);
					listener.notify(shipMovementVector);
				}
			}
		}
	}

	private void MTW_WaterTemperature(String[] nmeaContent) {
		Temperature temperature = PhysxFactory.eINSTANCE.createTemperature();
		temperature.setValue(Double.parseDouble(nmeaContent[1]));
		if (nmeaContent[1].toUpperCase().equals('F')) {
			temperature.setUnit(TemperatureUnit.FAHRENHEIT);
		} else {
			temperature.setUnit(TemperatureUnit.CELSIUS);
		}
		for (IWaterTemperatureListener listener : _waterTemperatureListeners) {
			heartbeat(IWaterTemperatureListener.class);
			listener.notify(temperature);
		}
	}

	private void MWV_WindMeasurement(String[] nmeaContent) {
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
		notifyWind(windMeasurement);
	}

	private void DBK_DepthBelowKeel(String[] nmeaContent) {
		Depth depth = GeoFactory.eINSTANCE.createDepth();
		depth.setTime(Calendar.getInstance().getTime());
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.KEEL);
		setDepthFromContent(nmeaContent, depth);
		notifyDepthMeasurement(depth);
	}

	private void DBS_DepthBelowSurface(String[] nmeaContent) {
		Depth depth = GeoFactory.eINSTANCE.createDepth();
		depth.setTime(Calendar.getInstance().getTime());
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.SURFACE);
		setDepthFromContent(nmeaContent, depth);
		notifyDepthMeasurement(depth);
	}

	private void DBT_DepthBelowTransducer(String[] nmeaContent) {
		Depth depth = GeoFactory.eINSTANCE.createDepth();
		depth.setTime(Calendar.getInstance().getTime());
		depth.setMeasurementPosition(RelativeDepthMeasurementPosition.TRANSDUCER);
		setDepthFromContent(nmeaContent, depth);
		notifyDepthMeasurement(depth);
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
	}

	@Override
	public void disable() {
		heartbeatThread.interrupt();
	}

	@Override
	protected String getProviderName() {
		return "NMEA0183"; //$NON-NLS-1$
	}

}
