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
package net.sf.seesea.services.navigation;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Callable;

import net.sf.seesea.services.navigation.provider.INMEAStreamProvider;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

/**
 * A reader that processes nmea input streams
 */
public class ThreadedSerialInputReader extends InputStreamReader implements INMEAReader, Callable<Void>{

	private static final String PROVIDER_TYPE = "provider"; //$NON-NLS-1$
	
	private static final String PROVIDER_NAME = "providerName"; //$NON-NLS-1$
	
	private final List<RawDataEventListener> nmeaEventListeners;
	private List<RawDataEventListener> aisEventListeners;

	private final INMEAStreamProvider streamProvider;
	private ServiceRegistration<?> serviceRegistration;
	private Thread processingThread;


	/**
	 * @param in
	 * @param streamproviderName 
	 * @throws IOException 
	 */
	public ThreadedSerialInputReader(INMEAStreamProvider nmeaStreamProvider) throws IOException {
		super(nmeaStreamProvider.getInputStream());
		streamProvider = nmeaStreamProvider;
		nmeaEventListeners = new ArrayList<RawDataEventListener>(1);
		aisEventListeners = new ArrayList<RawDataEventListener>(1);
		Hashtable<String, Object> properties = new Hashtable<String, Object>();
		properties.put("hardware", true); //$NON-NLS-1$
		properties.put(PROVIDER_NAME, nmeaStreamProvider.getName());
		properties.put(PROVIDER_TYPE, "sensor");
		if(NavigationServicesActivator.getDefault() != null) {
			BundleContext bundleContext = NavigationServicesActivator.getDefault().getBundle().getBundleContext();
			serviceRegistration = bundleContext.registerService(INMEAReader.class.getName(), this, properties);
//			new ProcessorsTracker(bundleContext);
		}
		
		Logger.getLogger(getClass()).info("Start reading from device"); //$NON-NLS-1$
	}
	
	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void addNMEAEventListener(RawDataEventListener nmeaEventListener) {
		nmeaEventListeners.add(nmeaEventListener);
	}

	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void removeNMEAEventListener(RawDataEventListener nmeaEventListener) {
		nmeaEventListeners.remove(nmeaEventListener);
	}

	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void addAISEventListener(RawDataEventListener nmeaEventListener) {
		aisEventListeners.add(nmeaEventListener);
	}

	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void removeAISEventListener(RawDataEventListener nmeaEventListener) {
		aisEventListeners.remove(nmeaEventListener);
	}

	

	public void close() throws IOException {
		if(processingThread != null) {
			processingThread.interrupt();
		}
		try {
			streamProvider.close();
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("Failed to close input stream", e); //$NON-NLS-1$
		}
		if(serviceRegistration != null) {
			serviceRegistration.unregister();
			serviceRegistration = null;
		}
		super.close();
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws NMEAProcessingException 
	 */
	public Void call() throws Exception {
		processingThread = Thread.currentThread();
		try {
			StringBuffer stringBuffer = null;
			for (int i = read(); i != -1 ;) {
				switch (i) {
				case '\n':
					if(stringBuffer != null) {
						stringBuffer.append((char) i);
						String line = stringBuffer.toString();
						if(line.startsWith("!")) { //$NON-NLS-1$
							RawDataEvent nmeaEvent = new RawDataEvent(stringBuffer.toString(), streamProvider.getName());
							for(RawDataEventListener aisEventListener : aisEventListeners) {
								aisEventListener.receiveRawDataEvent(nmeaEvent);
							}
						} else if(line.startsWith("$")) { //$NON-NLS-1$
							RawDataEvent nmeaEvent = new RawDataEvent(stringBuffer.toString(), streamProvider.getName());
							for (RawDataEventListener nmeaEventListener : nmeaEventListeners) {
								nmeaEventListener.receiveRawDataEvent(nmeaEvent);
							}
						}
					}
					i = read();
					break;
				case '$':
					stringBuffer = new StringBuffer();
					stringBuffer.append((char) i);
					i = read();
					break;
				case '!':
					stringBuffer = new StringBuffer();
					stringBuffer.append((char) i);
					i = read();
					break;
				default :
					if(stringBuffer != null) {
						stringBuffer.append((char) i);
					}
					i = read();
					break;
				}
//				}
			}
		} catch (Exception e) {
			// exception during processing
			if(!processingThread.interrupted()) {
				Logger.getLogger(getClass()).error("Exception while reading input stream", e); //$NON-NLS-1$
				BundleContext bundleContext = NavigationServicesActivator.getDefault().getBundle().getBundleContext();
				Collection<ServiceReference<INMEAReaderFailureNotifier>> serviceReferences = bundleContext.getServiceReferences(INMEAReaderFailureNotifier.class, null);
				if(serviceReferences != null) {
					for (ServiceReference<INMEAReaderFailureNotifier> serviceReference : serviceReferences) {
						INMEAReaderFailureNotifier failureNotifier = bundleContext.getService(serviceReference);
						failureNotifier.notify(streamProvider, e);
						bundleContext.ungetService(serviceReference);
					}
				}
			}
			try {
				close();
			} catch (IOException e2) {
				Logger.getLogger(getClass()).error("Failed to close reader", e2); //$NON-NLS-1$
			}
		}
		return null;
	}
	
//	private class ProcessorsTracker extends ServiceTracker<RawDataEventListener,RawDataEventListener> {
//
//		private static final String NMEA = "nmea"; //$NON-NLS-1$
//		private static final String AIS = "ais"; //$NON-NLS-1$
//		private static final String TYPE = "type"; //$NON-NLS-1$
//
//		public ProcessorsTracker(BundleContext context) {
//			super(context, RawDataEventListener.class, null);
//		}
//		
//		@Override
//		public RawDataEventListener addingService(
//				ServiceReference<RawDataEventListener> reference) {
//			RawDataEventListener listener = super.addingService(reference);
//			if(reference.getProperty(TYPE).equals(AIS)) {
//				aisEventListeners.add(listener);
//			} else if(reference.getProperty(TYPE).equals(NMEA)) {
//				nmeaEventListeners.add(listener);
//			}
//			return listener; 
//		}
//		
//		@Override
//		public void removedService(
//				ServiceReference<RawDataEventListener> reference,
//				RawDataEventListener service) {
//			if(reference.getProperty(TYPE).equals(AIS)) {
//				aisEventListeners.add(service);
//			} else if(reference.getProperty(TYPE).equals(NMEA)) {
//				nmeaEventListeners.add(service);
//			}
//			super.removedService(reference, service);
//		}
//		
//	}


}
