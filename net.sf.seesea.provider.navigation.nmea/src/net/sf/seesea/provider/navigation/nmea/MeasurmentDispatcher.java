/**
 * 
Copyright (c) 2010-2012, Jens K�bler
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
package net.sf.seesea.provider.navigation.nmea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Acceleration;
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
import net.sf.seesea.services.navigation.listener.IAccelerationListener;
import net.sf.seesea.services.navigation.listener.IDataListener;
import net.sf.seesea.services.navigation.listener.IDepthListener;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.services.navigation.listener.IRelativeWindSpeedListener;
import net.sf.seesea.services.navigation.listener.ISatelliteInfoListener;
import net.sf.seesea.services.navigation.listener.IHeadingListener;
import net.sf.seesea.services.navigation.listener.ISpeedListener;
import net.sf.seesea.services.navigation.listener.ITimeListener;
import net.sf.seesea.services.navigation.listener.ITotalLogListener;
import net.sf.seesea.services.navigation.listener.ITrackMadeGoodGroundSpeedListener;
import net.sf.seesea.services.navigation.listener.ITripLogListener;
import net.sf.seesea.services.navigation.listener.IWaterTemperatureListener;
import net.sf.seesea.services.navigation.listener.IWindListener;
import net.sf.seesea.services.navigation.provider.IPositionProvider;
import net.sf.seesea.services.navigation.provider.IShipMovementVectorProvider;
import net.sf.seesea.services.navigation.provider.IWaterTemperatureDataProvider;
import net.sf.seesea.services.navigation.provider.IWindDataProvider;

import org.apache.log4j.Logger;

public abstract class MeasurmentDispatcher implements  IPositionProvider, IWaterTemperatureDataProvider,
IWindDataProvider, IShipMovementVectorProvider {

	protected final List<IPositionListener> _positionListeners;
	protected final List<IWindListener> _windListeners;
	protected final List<IHeadingListener> _headingListeners;
	protected final List<ISpeedListener> _speedListeners;
	protected final List<IWaterTemperatureListener> _waterTemperatureListeners;
	protected final List<ITrackMadeGoodGroundSpeedListener> _trackMadeGoodListeners;
	protected final List<IRelativeWindSpeedListener> _relativeWindSpeedListeners;
	protected final List<ISatelliteInfoListener> _satelliteInfoListeners;
	protected final List<IDepthListener> _depthListeners;
	protected final List<ITimeListener> _timeListeners;
	protected final List<ITotalLogListener> _totalLogListeners; 
	protected final List<ITripLogListener> _tripLogListeners; 
	protected final Map<Class<?>, Long> sensorHeartbeats;
	protected final Thread heartbeatThread;
	protected final Object heartbeatSync;
	protected final List<IAccelerationListener> _accelerationListeners;

	public MeasurmentDispatcher() {
		super();
		_positionListeners = Collections
				.synchronizedList(new ArrayList<IPositionListener>(2));
		_windListeners = Collections
				.synchronizedList(new ArrayList<IWindListener>(1));
		_headingListeners = Collections
				.synchronizedList(new ArrayList<IHeadingListener>(1));
		_speedListeners = Collections
				.synchronizedList(new ArrayList<ISpeedListener>(1));
		_waterTemperatureListeners = Collections
				.synchronizedList(new ArrayList<IWaterTemperatureListener>(1));
		_trackMadeGoodListeners = Collections
				.synchronizedList(new ArrayList<ITrackMadeGoodGroundSpeedListener>(
						1));
		_relativeWindSpeedListeners = Collections
				.synchronizedList(new ArrayList<IRelativeWindSpeedListener>(1));
		_satelliteInfoListeners = Collections
				.synchronizedList(new ArrayList<ISatelliteInfoListener>(1));
		_depthListeners = Collections
				.synchronizedList(new ArrayList<IDepthListener>(1));
		_timeListeners = Collections
				.synchronizedList(new ArrayList<ITimeListener>(1));
		_totalLogListeners = Collections
				.synchronizedList(new ArrayList<ITotalLogListener>(1));
		_tripLogListeners = Collections
				.synchronizedList(new ArrayList<ITripLogListener>(1));
		_accelerationListeners = Collections
				.synchronizedList(new ArrayList<IAccelerationListener>(1));
		// activeReaders = new HashMap<INMEAStreamProvider, Thread>();
		sensorHeartbeats = new HashMap<Class<?>, Long>();
		heartbeatSync = new Object();
		heartbeatThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
				while (true) {
						synchronized (heartbeatSync) {
							for (Iterator<Class<?>> iterator = sensorHeartbeats
									.keySet().iterator(); iterator.hasNext();) {
								Class<?> dataListener = iterator.next();
								Long date = sensorHeartbeats.get(dataListener);
								long c = System.currentTimeMillis() - date;
								if (c > 2500) {
									notifyDataListeners(dataListener);
									iterator.remove();
								}
							}
						}
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					Logger.getRootLogger().info("Heartbeat thread sucessfully interrupted"); //$NON-NLS-1$
				}
			}

			private void notifyDataListeners(Class<?> dataListener) {
				if (dataListener.isAssignableFrom(IPositionListener.class)) {
					for (IPositionListener listener : _positionListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener.isAssignableFrom(IWindListener.class)) {
					for (IWindListener listener : _windListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener.isAssignableFrom(IDepthListener.class)) {
					for (IDepthListener listener : _depthListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener.isAssignableFrom(IAccelerationListener.class)) {
					for (IAccelerationListener listener : _accelerationListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener.isAssignableFrom(IHeadingListener.class)) {
					for (IHeadingListener listener : _headingListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener.isAssignableFrom(ISpeedListener.class)) {
					for (ISpeedListener listener : _speedListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener
						.isAssignableFrom(ITimeListener.class)) {
					for (ITimeListener listener : _timeListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener
						.isAssignableFrom(IWaterTemperatureListener.class)) {
					for (IWaterTemperatureListener listener : _waterTemperatureListeners) {
						listener.providerDisabled(getProviderName());
					}
//				} else if (dataListener
//						.isAssignableFrom(ITrackMadeGoodGroundSpeedListener.class)) {
//					for (ITrackMadeGoodGroundSpeedListener listener : _trackMadeGoodListeners) {
//						listener.providerDisabled(getProviderName());
//					}
				} else if (dataListener
						.isAssignableFrom(IRelativeWindSpeedListener.class)) {
					for (IRelativeWindSpeedListener listener : _relativeWindSpeedListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener.isAssignableFrom(ISatelliteInfoListener.class)) {
					for (ISatelliteInfoListener listener : _satelliteInfoListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener.isAssignableFrom(ITotalLogListener.class)) {
					for (ITotalLogListener listener : _totalLogListeners) {
						listener.providerDisabled(getProviderName());
					}
				} else if (dataListener.isAssignableFrom(ITripLogListener.class)) {
					for (ITripLogListener listener : _tripLogListeners) {
						listener.providerDisabled(getProviderName());
					}
				}
			}
		});
		heartbeatThread.setName("Heartbeat"); //$NON-NLS-1$
		heartbeatThread.start();
	}

	protected void heartbeat(Class<? extends IDataListener<?>> dataListener) {
			synchronized (heartbeatSync) {
				if (!sensorHeartbeats.containsKey(dataListener)) {
					if (dataListener.isAssignableFrom(IPositionListener.class)) {
						for (IPositionListener listener : _positionListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener.isAssignableFrom(IDepthListener.class)) {
						for (IDepthListener listener : _depthListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener.isAssignableFrom(IWindListener.class)) {
						for (IWindListener listener : _windListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener
							.isAssignableFrom(IHeadingListener.class)) {
						for (IHeadingListener listener : _headingListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener
							.isAssignableFrom(IAccelerationListener.class)) {
						for (IAccelerationListener listener : _accelerationListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener
							.isAssignableFrom(ISpeedListener.class)) {
						for (ISpeedListener listener : _speedListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener
							.isAssignableFrom(IWaterTemperatureListener.class)) {
						for (IWaterTemperatureListener listener : _waterTemperatureListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener
							.isAssignableFrom(ITimeListener.class)) {
						for (ITimeListener listener : _timeListeners) {
							listener.providerEnabled(getProviderName());
						}
//					} else if (dataListener
//							.isAssignableFrom(ITrackMadeGoodGroundSpeedListener.class)) {
//						for (ITrackMadeGoodGroundSpeedListener listener : _trackMadeGoodListeners) {
//							listener.providerEnabled(getProviderName());
//						}
					} else if (dataListener.isAssignableFrom(IRelativeWindSpeedListener.class)) {
						for (IRelativeWindSpeedListener listener : _relativeWindSpeedListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener.isAssignableFrom(ITotalLogListener.class)) {
						for (ITotalLogListener listener : _totalLogListeners) {
							listener.providerEnabled(getProviderName());
						}
					} else if (dataListener.isAssignableFrom(ITripLogListener.class)) {
						for (ITripLogListener listener : _tripLogListeners) {
							listener.providerEnabled(getProviderName());
						}
	//				} else if (dataListener
	//						.isAssignableFrom(IRelativeWindSpeedListener.class)) {
	//					for (ITrackMadeGoodGroundSpeedListener listener : _trackMadeGoodListeners) {
	//						listener.providerEnabled(PROVIDER_NAME);
	//					}
					} else if (dataListener.isAssignableFrom(ISatelliteInfoListener.class)) {
						for (ISatelliteInfoListener listener : _satelliteInfoListeners) {
							listener.providerEnabled(getProviderName());
						}
					}
				}
				sensorHeartbeats.put(dataListener, System.currentTimeMillis());
			}
	
		}


	@Override
	public synchronized void attachShipMovementListener(IHeadingListener listener) {
		_headingListeners.add(listener);
	}

	@Override
	public synchronized void detachShipMovementListener(IHeadingListener listener) {
		listener.providerDisabled(getProviderName());
		_headingListeners.remove(listener);
	}

	public synchronized void attachSpeedListener(ISpeedListener listener) {
		_speedListeners.add(listener);
	}

	public synchronized void detachSpeedListener(ISpeedListener listener) {
		listener.providerDisabled(getProviderName());
		_speedListeners.remove(listener);
	}

	@Override
	public synchronized void attachWindListener(IWindListener listener) {
		_windListeners.add(listener);
	}

	@Override
	public synchronized void detachWindListener(IWindListener listener) {
		listener.providerDisabled(getProviderName());
		_windListeners.remove(listener);
	}

	@Override
	public synchronized void attachWaterTemperatureListener(IWaterTemperatureListener listener) {
		_waterTemperatureListeners.add(listener);
	}

	@Override
	public synchronized void detachWaterTemperatureListener(IWaterTemperatureListener listener) {
		listener.providerDisabled(getProviderName());
		_waterTemperatureListeners.remove(listener);
	}

	public synchronized void attachPositionListener(IPositionListener positionListener) {
		_positionListeners.add(positionListener);
	}

	public synchronized void detachPositionListener(IPositionListener positionListener) {
		positionListener.providerDisabled(getProviderName());
		_positionListeners.remove(positionListener);
	}

	public synchronized void attachTrackMadeGoodGroundSpeedListener(
			ITrackMadeGoodGroundSpeedListener trackMadeGoodGroundSpeedListener) {
				_trackMadeGoodListeners.add(trackMadeGoodGroundSpeedListener);
			}

	public synchronized void detachTrackMadeGoodGroundSpeedListener(
			ITrackMadeGoodGroundSpeedListener trackMadeGoodGroundSpeedListener) {
//		trackMadeGoodGroundSpeedListener.providerDisabled(getProviderName());
				_trackMadeGoodListeners.remove(trackMadeGoodGroundSpeedListener);
			}

	public synchronized void attachRelativeWindSpeedListener(IRelativeWindSpeedListener relativeWindSpeedListener) {
		_relativeWindSpeedListeners.add(relativeWindSpeedListener);
	}

	public synchronized void detachRelativeWindSpeedListener(IRelativeWindSpeedListener relativeWindSpeedListener) {
		relativeWindSpeedListener.providerDisabled(getProviderName());
		_relativeWindSpeedListeners.remove(relativeWindSpeedListener);
	}

	public synchronized void attachSatelliteInfoListener(ISatelliteInfoListener satelliteInfoListener) {
		_satelliteInfoListeners.add(satelliteInfoListener);
	}

	public synchronized void detachSatelliteInfoListener(ISatelliteInfoListener satelliteInfoListener) {
		satelliteInfoListener.providerDisabled(getProviderName());
		_satelliteInfoListeners.remove(satelliteInfoListener);
	}

	public synchronized void attachDepthListener(IDepthListener depthListener) {
		_depthListeners.add(depthListener);
	}

	public synchronized void detachDepthListener(IDepthListener depthListener) {
		depthListener.providerDisabled(getProviderName());
		_depthListeners.remove(depthListener);
	}

	public synchronized void attachTimeListener(ITimeListener depthListener) {
		_timeListeners.add(depthListener);
	}

	public synchronized void detachTimeListener(ITimeListener depthListener) {
		depthListener.providerDisabled(getProviderName());
		_timeListeners.remove(depthListener);
	}

	public synchronized void attachLogListener(ITotalLogListener listener) {
		_totalLogListeners.add(listener);
	}

	public synchronized void detachLogListener(ITotalLogListener listener) {
		listener.providerDisabled(getProviderName());
		_totalLogListeners.remove(listener);
	}

	public synchronized void attachTripLogListener(ITripLogListener listener) {
		_tripLogListeners.add(listener);
	}

	public synchronized void detachTripLogListener(ITripLogListener listener) {
		listener.providerDisabled(getProviderName());
		_tripLogListeners.remove(listener);
	}

	public synchronized void attachAccelerationListener(IAccelerationListener listener) {
		_accelerationListeners.add(listener);
	}

	public synchronized void detachAccelerationListener(IAccelerationListener listener) {
		listener.providerDisabled(getProviderName());
		_accelerationListeners.remove(listener);
	}

	protected abstract String getProviderName();

	protected void notifyListeners(Measurement measurement) {
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
		} else if (measurement instanceof Acceleration) {
			synchronized (_accelerationListeners) {
				heartbeat(IAccelerationListener.class);
				for (IAccelerationListener speedVectorListener : _accelerationListeners) {
					speedVectorListener.notify(
							(Acceleration) measurement, null);
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

}