/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
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

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.ParallelPort;
import gnu.io.PortInUseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.sf.seesea.lib.IValidatingPage;
import net.sf.seesea.provider.navigation.nmea.ui.NMEAWizard;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.qbang.rxtx.ComPortLabelProvider;
import org.qbang.rxtx.RXTXActivator;

/**
 * 
 */
public class SelectComPortPage extends WizardPage implements IValidatingPage {

	private TableViewer tableViewer;
	private CommPortIdentifier commPortIdentifier;

	/**
	 * @param serialNMEAProvider 
	 * @param pageName
	 */
	public SelectComPortPage() {
		super("ComPortSetupPage", Messages.getString("SelectComPortPage.pageHead"), null); //$NON-NLS-1$ //$NON-NLS-2$
		setMessage(Messages.getString("SelectComPortPage.pageMessage")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		IDialogSettings settings = getWizard().getDialogSettings();
		   
		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new ComPortLabelProvider());
		List<CommPortIdentifier> ports = getPorts();
		tableViewer.setInput(ports);
		setControl(tableViewer.getControl());
		if(getPorts().isEmpty()) {
			setErrorMessage(Messages.getString("SelectComPortPage.noComPorts")); //$NON-NLS-1$
		} else {
			if(settings.get("lastComPort") != null) { //$NON-NLS-1$
				for (CommPortIdentifier comm : ports) {
					if(comm.getName().equals(settings.get("lastComPort"))) { //$NON-NLS-1$
						tableViewer.setSelection(new StructuredSelection(comm));
					}
				}
			} else {
				tableViewer.setSelection(new StructuredSelection(getPorts().get(0)));
			}
		}
		tableViewer.getControl().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				IStatus status = validatePage();
				if(!status.isOK()) {
					setErrorMessage(status.getMessage());
					return;
				}
				((NMEAWizard) getWizard()).getContainer().showPage(getNextPage());
			}
		});
		
	}
	
	private List<CommPortIdentifier> getPorts() {
		boolean manualPorts = RXTXActivator.getDefault().getPreferenceStore().getBoolean("manualPorts"); //$NON-NLS-1$
		List<CommPortIdentifier> cports = new ArrayList<CommPortIdentifier>();
		
		Enumeration<?> ports = CommPortIdentifier.getPortIdentifiers();
		while (ports.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier)ports.nextElement();
			if(!portId.isCurrentlyOwned()) {
				cports.add(portId);
			}
		}
		return cports;
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.provider.navigation.nmea.ui.IValidatingPage#getStatus()
	 */
	@Override
	public IStatus validatePage() {
//		if(getPorts().isEmpty()) {
//			setErrorMessage("No COM Ports available. Probably other software is using the COM port.");
//			return new Status(IStatus.ERROR, "org.qbang.rxtx", "No COM Ports available"); //$NON-NLS-1$
//		}
		IStructuredSelection structuredSelection = (IStructuredSelection) tableViewer.getSelection();
		if(!structuredSelection.isEmpty()) {
			CommPortIdentifier portIdentifier = (CommPortIdentifier) structuredSelection.getFirstElement();
			try {
				CommPort commPort = portIdentifier.open(getClass().getName(), 2000);
				if(commPort instanceof ParallelPort) {
					commPort.close();
					return new Status(IStatus.ERROR, "org.qbang.rxtx", Messages.getString("SelectComPortPage.noParallelPorts")); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					commPort.close();
					commPortIdentifier = portIdentifier;
					IDialogSettings settings = getWizard().getDialogSettings();
					settings.put("lastComPort", commPortIdentifier.getName()); //$NON-NLS-1$
					return new Status(IStatus.OK, "org.qbang.rxtx", Messages.getString("SelectComPortPage.sucessfulConnection")); //$NON-NLS-1$ //$NON-NLS-2$
				}
			} catch (PortInUseException e) {
				return new Status(IStatus.ERROR, "org.qbang.rxtx", Messages.getString("SelectComPortPage.failedOpen"), e); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return new Status(IStatus.ERROR, "org.qbang.rxtx", Messages.getString("SelectComPortPage.nothingSelected")); //$NON-NLS-1$ //$NON-NLS-2$;
	}

	@Override
	public void dispose() {
		tableViewer.getControl().dispose();
		super.dispose();
	}

	public CommPortIdentifier getCommPort() {
		return commPortIdentifier;
	}
	
	

}
