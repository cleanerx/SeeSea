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
package net.sf.seesea.provider.navigation.nmea.ui;

import net.sf.seesea.lib.ValidatingWizardDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * 
 */
public class NMEAConnectWizardHandler extends AbstractHandler {
	
	private final ServiceTracker nmeaConnectorTracker;

	/**
	 * 
	 */
	public NMEAConnectWizardHandler() {
		nmeaConnectorTracker = new ServiceTracker(NMEAUIActivator.getDefault().getBundle().getBundleContext(), INMEAConnector.class.getName(), null);
		nmeaConnectorTracker.open();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		NMEAWizard nmeaWizard = new NMEAWizard();
		nmeaWizard.setWindowTitle(Messages.getString("NMEAWizardHandler.connect")); //$NON-NLS-1$
		nmeaWizard.addPage(new AvailableProvidersPage());
		for(Object object : nmeaConnectorTracker.getServices()) {
			if(object instanceof INMEAConnector) {
				INMEAConnector connector = (INMEAConnector) object;
				nmeaWizard.addWizardPages(connector.getContributedPages());
			}
		}
		ValidatingWizardDialog wizardDialog = new ValidatingWizardDialog(HandlerUtil.getActiveShell(event), nmeaWizard);
		if(wizardDialog.open() == Window.OK) {
			return Window.OK;
		} else {
			return Window.CANCEL;
		}
		
	}

}
