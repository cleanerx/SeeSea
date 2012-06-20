/**
 * 
Copyright (c) 2010-2012, Jens Kübler
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

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The generic NMEA connectors wizard. It shows available connector which are tracked as services 
 */
public class NMEAWizard extends Wizard {

	private INMEAConnector currentConnector  ;
	private final ServiceTracker availableConnectorsTracker;
	
	/**
	 * 
	 */
	public NMEAWizard() {
		setForcePreviousAndNextButtons(true);
		availableConnectorsTracker = new ServiceTracker(NMEAUIActivator.getDefault().getBundle().getBundleContext(), INMEAConnector.class.getName(), null);
		availableConnectorsTracker.open();
	}

	
	
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		for(Object o : availableConnectorsTracker.getServices()) {
			INMEAConnector connector = (INMEAConnector) o;
			connector.addPageListeners((WizardDialog) getContainer());
		}
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return currentConnector.performFinish();
	}

	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if(getStartingPage().equals(page)) {
			if(currentConnector == null) {
				return null;
			} else if(currentConnector.getContributedPages().isEmpty()) {
				return null;
			} else {
				IWizardPage next = currentConnector.getContributedPages().iterator().next();
				return next;
			}
		} else if(currentConnector.getContributedPages().indexOf(page) + 1 < currentConnector.getContributedPages().size()) {
			IWizardPage wizardPage = currentConnector.getContributedPages().get(currentConnector.getContributedPages().indexOf(page) + 1);
			return wizardPage;
		} else {
			return null;
		}
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		if(currentConnector == null || page.equals(getStartingPage())) {
			return null;
		} else if(currentConnector.getContributedPages().isEmpty()) {
			return null;
		} else if(page.equals(currentConnector.getContributedPages().get(0))) {
			return getPages()[0];
		} else {
			return currentConnector.getContributedPages().get(currentConnector.getContributedPages().indexOf(page) - 1);
		}
	}

	public INMEAConnector getConnector() {
		return currentConnector;
	}

	public void setConnector(INMEAConnector connector) {
		this.currentConnector = connector;
		getContainer().updateButtons();
	}

	@Override
	public boolean canFinish() {
		if(!getStartingPage().isPageComplete()) {
			return false;
		}
		if(currentConnector != null ) {
			for (IWizardPage wizardPage : currentConnector.getContributedPages()) {
	            if (!wizardPage.isPageComplete()) {
					return false;
				}
			}
		}
		return true;
	}

	public void addWizardPages(List<IWizardPage> pages) {
		for (IWizardPage iWizardPage : pages) {
			addPage(iWizardPage);
		}
	}

	@Override
	public boolean performCancel() {
		if(currentConnector != null) {
			return currentConnector.performCancel();
		}
		return true;
	}
	
	
	
}
