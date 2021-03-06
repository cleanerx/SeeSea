/**
 * 
Copyright (c) 2010-2013, Jens Kübler
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
package net.sf.seesea.gpsd;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import net.sf.seesea.lib.ui.DefaultFeedbackMessageConsumer;
import net.sf.seesea.provider.navigation.nmea.ui.INMEAConnector;
import net.sf.seesea.services.navigation.ThreadedSerialInputReader;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.ServiceRegistration;

public class GPSDConnector implements INMEAConnector {

	private List<IWizardPage> wizardPages;
	private ServiceRegistration<?> serviceRegistration;

	public GPSDConnector() {
		wizardPages = new ArrayList<IWizardPage>();
		wizardPages.add(new ConnectorStringPage());
	}
	
	@Override
	public String getStreamProviderName() {
		return "Linux GPSd"; //$NON-NLS-1$
	}

	@Override
	public ImageDescriptor getStreamProviderIcon() {
		URL url = GPSDActivator.getDefault().getBundle().getEntry("/res/Linux16.png"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}

	@Override
	public List<IWizardPage> getContributedPages() {
		return wizardPages;
	}

	@Override
	public boolean performCancel() {
		return true;
	}

	@Override
	public void addPageListeners(WizardDialog wizardDialog) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		
		ThreadedSerialInputReader reader = null;
		ConnectorStringPage selectHostPage = (ConnectorStringPage) wizardPages.get(0);
		String host = selectHostPage.getHost();
		int port = selectHostPage.getPort();
		int timeout = selectHostPage.getTimeout();
		GPSDInputStreamProvider inputStreamProvider = new GPSDInputStreamProvider(host, port, timeout);
		try {
			reader = new ThreadedSerialInputReader(inputStreamProvider, new DefaultFeedbackMessageConsumer());
			FutureTask<Void> futureTask = new FutureTask<Void>(reader);
			ExecutorService es = Executors.newSingleThreadExecutor ();
			es.submit (futureTask);
		} catch (Exception e) {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e2) {
					Logger.getLogger(getClass()).error("Failed to close input stream", e2); //$NON-NLS-1$
				}
			}
			if(inputStreamProvider != null) {
				try {
					inputStreamProvider.close();
				} catch (IOException e2) {
					Logger.getLogger(getClass()).error("Failed to close input stream", e2); //$NON-NLS-1$
				}
			}
			MessageDialog.openError(Display.getDefault().getActiveShell(), "", "" + e.getLocalizedMessage()); //$NON-NLS-1$ //$NON-NLS-2$
			Logger.getLogger(getClass()).error("Failed to connect to device", e); //$NON-NLS-1$
		}
		return true;
	}

}
