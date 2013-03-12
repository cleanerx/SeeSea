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
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.Callable;

import net.sf.seesea.services.navigation.provider.INMEAStreamProvider;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * A reader that processes nmea input streams
 */
public class ThreadedSerialInputReader implements Callable<Void>{

	private static final String PROVIDER_TYPE = "provider"; //$NON-NLS-1$
	
	private static final String PROVIDER_NAME = "providerName"; //$NON-NLS-1$

	private final INMEAStreamProvider streamProvider;
	private Thread processingThread;

	private ServiceTracker<IStreamProcessor, IStreamProcessor> streamTracker;

	private InputStream pushbackInputStream;

	/**
	 * @param in
	 * @param streamproviderName 
	 * @throws IOException 
	 */
	public ThreadedSerialInputReader(INMEAStreamProvider nmeaStreamProvider) throws IOException {
		pushbackInputStream = nmeaStreamProvider.getInputStream();
		streamProvider = nmeaStreamProvider;
		Hashtable<String, Object> properties = new Hashtable<String, Object>();
		properties.put("hardware", true); //$NON-NLS-1$
		properties.put(PROVIDER_NAME, nmeaStreamProvider.getName());
		properties.put(PROVIDER_TYPE, "sensor"); //$NON-NLS-1$
		if(NavigationServicesActivator.getDefault() != null) {
			BundleContext bundleContext = NavigationServicesActivator.getDefault().getBundle().getBundleContext();
//			serviceRegistration = bundleContext.registerService(INMEAReader.class.getName(), this, properties);
			streamTracker = new ServiceTracker<IStreamProcessor,IStreamProcessor>(bundleContext, IStreamProcessor.class, null);
			streamTracker.open();
		}
		
		Logger.getLogger(getClass()).info("Start reading from device"); //$NON-NLS-1$
	}
	

	

	public void close() throws IOException {
		if(streamTracker != null) {
			streamTracker.close();
		}
		if(processingThread != null) {
			processingThread.interrupt();
		}
		pushbackInputStream.close();
		try {
			streamProvider.close();
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("Failed to close input stream", e); //$NON-NLS-1$
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws NMEAProcessingException 
	 */
	public Void call() throws Exception {
		try {
			processingThread = Thread.currentThread();
			Object[] services = streamTracker.getServices();
			IStreamProcessor streamProcessor = null;
			int intialBytesToBeRead = 2048;
			int[] input = new int[intialBytesToBeRead];
			int x;
			int k = 0;
			Logger.getLogger(getClass()).info("Reading initial bytes for stream detection"); //$NON-NLS-1$
			while((x = pushbackInputStream.read()) != -1) {
				input[k] = x;
				k++;
				if(k >= intialBytesToBeRead) {
					break;
				}
			}
			if(services != null) {
				for (Object object : services) {
					IStreamProcessor currentStreamProcessor = (IStreamProcessor) object;
					if(currentStreamProcessor.isValidStreamProcessor(Arrays.copyOf(input,intialBytesToBeRead))) {
						streamProcessor = currentStreamProcessor;
						Logger.getLogger(getClass()).info("Detected provider: " + streamProcessor.getClass().getSimpleName()); //$NON-NLS-1$
						break;
					}
				}
				String streamProviderName = streamProvider.getName();
				if(streamProcessor == null) {
					Display.getDefault().asyncExec(new Runnable() {
						
						@Override
						public void run() {
							MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.getString("ThreadedSerialInputReader.noProvider"), Messages.getString("ThreadedSerialInputReader.invalidStream")); //$NON-NLS-1$ //$NON-NLS-2$
						}
					});
							
					try {
						close();
					} catch (IOException e2) {
						Logger.getLogger(getClass()).error("Failed to close reader", e2); //$NON-NLS-1$
					}
					return null;
				}
				try {
					int j = 0;
					boolean continueProcessing = true;
					for (int i = pushbackInputStream.read();  i != -1 ; i = pushbackInputStream.read()) {
						continueProcessing = streamProcessor.readByte(i, streamProviderName);
						if(!continueProcessing) {
							break;
						}
						if(j++ > 100) {
							j = 0;
							Thread.sleep(1);
						}
					}
					if(!continueProcessing) {
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.getString("ThreadedSerialInputReader.processingStopped"), Messages.getString("ThreadedSerialInputReader.stop1")); //$NON-NLS-1$ //$NON-NLS-2$
							}
						});
					} else {
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.getString("ThreadedSerialInputReader.processingStopped"), Messages.getString("ThreadedSerialInputReader.stop2")); //$NON-NLS-1$ //$NON-NLS-2$
							}
						});
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
			}
		} catch (SocketTimeoutException e) {
			Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.getString("ThreadedSerialInputReader.timeout"), Messages.getString("ThreadedSerialInputReader.nodata")); //$NON-NLS-1$ //$NON-NLS-2$
				}
			});
			Logger.getLogger(getClass()).error("Fail", e); //$NON-NLS-1$
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("Fail", e); //$NON-NLS-1$
		}
		return null;
	}
	
}
