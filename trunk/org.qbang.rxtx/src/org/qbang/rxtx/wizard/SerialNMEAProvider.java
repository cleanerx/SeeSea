/**
 * 
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
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
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package org.qbang.rxtx.wizard;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import net.sf.seesea.provider.navigation.nmea.NMEA0183Reader;
import net.sf.seesea.provider.navigation.nmea.ui.INMEAConnector;
import net.sf.seesea.services.navigation.provider.INMEAStreamProvider;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.qbang.rxtx.RXTXActivator;
import org.qbang.rxtx.SerialInputStreamProvider;

/**
 * 
 */
public class SerialNMEAProvider implements INMEAConnector {

	private final List<IWizardPage> wizardPages;

	private SerialPort commPort;

	private SerialInputStreamProvider serialInputStreamProvider;

	/**
	 * 
	 */
	public SerialNMEAProvider() {
		wizardPages = new ArrayList<IWizardPage>();
		wizardPages.add(new SelectComPortPage());
		wizardPages.add(new ComPortSetupPage());
	}
	
	/* (non-Javadoc)
	 * @see net.sf.seesea.provider.navigation.nmea.ui.IFixme#getStreamProviderName()
	 */
	@Override
	public String getStreamProviderName() {
		return Messages.getString("SerialNMEAProvider.providerName");  //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.provider.navigation.nmea.ui.IFixme#getStreamProviderIcon()
	 */
	@Override
	public ImageDescriptor getStreamProviderIcon() {
		URL url = RXTXActivator.getDefault().getBundle().getEntry("/res/icons/plug.png"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.provider.navigation.nmea.ui.INMEAConnector#getContributedPages()
	 */
	@Override
	public List<IWizardPage> getContributedPages() {
		return wizardPages;
	}
	
	/* (non-Javadoc)
	 * @see net.sf.seesea.provider.navigation.nmea.ui.INMEAConnector#performCancel()
	 */
	@Override
	public boolean performCancel() {
//		if(commPort != null) {
//			commPort.close();
//		}
		return true;
	}

	@Override
	public void addPageListeners(WizardDialog wizardDialog) {
		// nothing to do
	}

//	@Override
//	public void disconnect(String device) {
//		try {
//			serialInputStreamProvider.close();
//		} catch (IOException e) {
//			Logger.getLogger(getClass()).error("Failed to close NMEAStreamProvider", e); //$NON-NLS-1$
//		}
//	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.provider.navigation.nmea.ui.INMEAConnector#performFinish()
	 */
	@Override
	public boolean performFinish() {
		NMEA0183Reader reader = null;
		try {
			SelectComPortPage wizardPage = (SelectComPortPage) wizardPages.get(0);
			CommPortIdentifier commPortIdentifier = (CommPortIdentifier) wizardPage.getCommPort();
			commPort = (SerialPort) commPortIdentifier.open(getClass().getName(), 2000);
			
			ComPortSetupPage comPortSetupPage = (ComPortSetupPage) wizardPages.get(1);
			commPort.setSerialPortParams(comPortSetupPage.getBaudRate(), SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			commPort.enableReceiveTimeout(comPortSetupPage.getTimeout());
			
			serialInputStreamProvider = new SerialInputStreamProvider(commPort);
			reader = new NMEA0183Reader(serialInputStreamProvider);
			 FutureTask<Void> futureTask = new FutureTask<Void>(reader);
			 ExecutorService es = Executors.newSingleThreadExecutor ();
			 Future<Void> submit = (Future<Void>) es.submit (futureTask);
			return true;
		} catch (Exception e) {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e2) {
					Logger.getLogger(getClass()).error("Failed to close input stream", e2); //$NON-NLS-1$
				}
			}
			if(serialInputStreamProvider != null) {
				try {
					serialInputStreamProvider.close();
				} catch (IOException e2) {
					Logger.getLogger(getClass()).error("Failed to close input stream", e2); //$NON-NLS-1$
				}
			}
			MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.getString("SerialNMEAProvider.connectFailure"), Messages.getString("SerialNMEAProvider.message") + e.getLocalizedMessage()); //$NON-NLS-1$ //$NON-NLS-2$
			Logger.getLogger(getClass()).error("Failed to connect to device", e); //$NON-NLS-1$
			return true;
		}
	}

	
	

}