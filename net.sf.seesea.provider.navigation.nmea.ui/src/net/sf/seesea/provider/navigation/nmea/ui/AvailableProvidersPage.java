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

import java.text.Collator;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.osgi.util.tracker.ServiceTracker;

/**
 * This page shows the available connection providers
 */
public class AvailableProvidersPage extends WizardPage {

	private final ServiceTracker providerServiceTracker;
	private TableViewer providersTableViewer;

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public AvailableProvidersPage() {
		super("Available Providers", Messages.getString("AvailableProvidersPage.availableProviders"), null); //$NON-NLS-1$ //$NON-NLS-2$
		setMessage(Messages.getString("AvailableProvidersPage.selectMessage")); //$NON-NLS-1$
		providerServiceTracker = new ServiceTracker(NMEAUIActivator.getDefault().getBundle().getBundleContext(), INMEAConnector.class.getName(), null);
		providerServiceTracker.open();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		providersTableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		providersTableViewer.setContentProvider(new ArrayContentProvider());
		providersTableViewer.setLabelProvider(new NMEAConnectorsLabelProvider());
		providersTableViewer.setSorter(new ViewerSorter(Collator.getInstance()));
		Object[] services = providerServiceTracker.getServices();
		providersTableViewer.setInput(services);
		setControl(providersTableViewer.getControl());
		
		providersTableViewer.addSelectionChangedListener(new ProviderChangedListener());
	}

	@Override
	public boolean isPageComplete() {
		return !providersTableViewer.getSelection().isEmpty();
	}

	/**
	 * 
	 */
	private final class ProviderChangedListener implements
			ISelectionChangedListener {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			if(event.getSelection().isEmpty()) {
				((NMEAWizard) getWizard()).setConnector(null);
			} else {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				((NMEAWizard) getWizard()).setConnector((INMEAConnector) selection.getFirstElement());
			}
			
		}
	}

}
