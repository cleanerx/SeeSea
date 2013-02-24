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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.CompositeMeasurement;
import net.sf.seesea.model.core.physx.Distance;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.RelativeSpeed;
import net.sf.seesea.model.core.physx.RelativeWind;
import net.sf.seesea.model.core.physx.SatellitesVisible;
import net.sf.seesea.model.core.physx.Temperature;
import net.sf.seesea.model.core.physx.Time;
import net.sf.seesea.model.core.weather.WindMeasurement;
import net.sf.seesea.provider.navigation.nmea.v183.NMEA0183Reader;
import net.sf.seesea.services.navigation.INMEAReader;
import net.sf.seesea.services.navigation.RawDataEventListener;
import net.sf.seesea.services.navigation.RawDataEvent;
import net.sf.seesea.services.navigation.listener.IDepthListener;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.services.navigation.listener.IRelativeWindSpeedListener;
import net.sf.seesea.services.navigation.listener.ISatelliteInfoListener;
import net.sf.seesea.services.navigation.listener.IHeadingListener;
import net.sf.seesea.services.navigation.listener.ISpeedListener;
import net.sf.seesea.services.navigation.listener.ITimeListener;
import net.sf.seesea.services.navigation.listener.ITotalLogListener;
import net.sf.seesea.services.navigation.listener.ITripLogListener;
import net.sf.seesea.services.navigation.listener.IWaterTemperatureListener;
import net.sf.seesea.services.navigation.listener.IWindListener;
import net.sf.seesea.services.navigation.provider.IPositionProvider;
import net.sf.seesea.services.navigation.provider.IShipMovementVectorProvider;
import net.sf.seesea.services.navigation.provider.IWaterTemperatureDataProvider;
import net.sf.seesea.services.navigation.provider.IWindDataProvider;

public class NMEA0183EventProcessor extends NMEAParser implements RawDataEventListener,
		IPositionProvider, IWaterTemperatureDataProvider, IWindDataProvider,
		IShipMovementVectorProvider {

	private List<INMEAReader> _nmeaReaders = Collections
			.synchronizedList(new ArrayList<INMEAReader>(1));

	private NMEA0183Reader nmea0183Reader;

	/**
	 * 
	 */
	public NMEA0183EventProcessor() {
		nmea0183Reader = new NMEA0183Reader();
	}

	public synchronized void bindReader(INMEAReader reader) {
		_nmeaReaders.add(reader);
		reader.addNMEAEventListener(this);
	}

	public synchronized void unbindReader(INMEAReader reader) {
		if (_nmeaReaders.size() == 1) {
			heartbeatThread.interrupt();
		}
		reader.removeNMEAEventListener(this);
		_nmeaReaders.remove(reader);
	}

	/**
	 * 
	 */
	@Override
	public void receiveRawDataEvent(RawDataEvent e) {
		List<Measurement> measurements = nmea0183Reader
				.extractMeasurementsFromNMEA(e.getNmeaMessageContent(),
						new ArrayList<Measurement>(1));
		for (Measurement measurement : measurements) {
			notifyListeners(measurement);
		}
	}

	private void notifyListeners(Measurement measurement) {
		if(measurement instanceof CompositeMeasurement) {
			for (Measurement childMeasurements : ((CompositeMeasurement) measurement).getMeasurements()) {
				notifyListeners(childMeasurements);
			}
		} else if (measurement instanceof Temperature) {
			synchronized (_waterTemperatureListeners) {
				for (IWaterTemperatureListener listener : _waterTemperatureListeners) {
					heartbeat(IWaterTemperatureListener.class);
					listener.notify((Temperature) measurement, null);
				}
			}
		} else if (measurement instanceof Heading) {
			synchronized (_headingListeners) {
				heartbeat(IHeadingListener.class);
				for (IHeadingListener speedVectorListener : _headingListeners) {
					speedVectorListener.notify(
							(Heading) measurement, null);
				}
			}
		} else if (measurement instanceof RelativeSpeed) {
			synchronized (_speedListeners) {
				heartbeat(ISpeedListener.class);
				for (ISpeedListener speedListener : _speedListeners) {
					speedListener.notify(
							(RelativeSpeed) measurement, null);
				}
			}
		} else if (measurement instanceof Distance) {
			Distance distance = (Distance) measurement;
			switch (distance.getDistanceType()) {
			case TOTAL:
				synchronized (_totalLogListeners) {
					heartbeat(ITotalLogListener.class);
					for (ITotalLogListener totalLogListener : _totalLogListeners) {
						totalLogListener.notify(distance, null);
					}
				}
				break;
			case TRIP:
				synchronized (_tripLogListeners) {
					heartbeat(ITripLogListener.class);
					for (ITripLogListener tripLogListener : _tripLogListeners) {
						tripLogListener.notify(distance, null);
					}
				}
			default:
				break;
			}
		} else if (measurement instanceof MeasuredPosition3D) {
			synchronized (_positionListeners) {
				heartbeat(IPositionListener.class);
				for (IPositionListener listener : _positionListeners) {
					listener.notify((MeasuredPosition3D) measurement, null);
				}
			}
		} else if (measurement instanceof Time) {
			heartbeat(ITimeListener.class);
			for (ITimeListener listener : _timeListeners) {
				listener.notify((Time) measurement, null);
			}
		} else if (measurement instanceof SatellitesVisible) {
			synchronized (_satelliteInfoListeners) {
				heartbeat(ISatelliteInfoListener.class);
				for (ISatelliteInfoListener satelliteInfoListener : _satelliteInfoListeners) {
					satelliteInfoListener.notify(
							(SatellitesVisible) measurement, null);
				}
			}
		} else if (measurement instanceof RelativeWind) {
			synchronized (_relativeWindSpeedListeners) {
				heartbeat(IRelativeWindSpeedListener.class);
				for (IRelativeWindSpeedListener listener : _relativeWindSpeedListeners) {
					listener.notify((RelativeWind) measurement, null);
				}
			}
		} else if (measurement instanceof WindMeasurement) {
			synchronized (_windListeners) {
				for (IWindListener listener : _windListeners) {
					heartbeat(IWindListener.class);
					listener.notify((WindMeasurement) measurement, null);
				}
			}
		} else if (measurement instanceof Depth) {
			synchronized (_depthListeners) {
				for (IDepthListener listener : _depthListeners) {
					heartbeat(IDepthListener.class);
					listener.notify((Depth) measurement, null);
				}
			}
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
