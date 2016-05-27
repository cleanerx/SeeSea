/**
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

package net.sf.seesea.gps.bluetooth.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

import net.sf.seesea.gps.bluetooth.ui.BluetoothDeviceLabelProvider;
import net.sf.seesea.gps.bluetooth.ui.BluetoothDiscoveryListener;
import net.sf.seesea.gps.bluetooth.ui.GPSBluetoothUIActivator;
import net.sf.seesea.lib.ui.IValidatingPage;
import net.sf.seesea.provider.navigation.nmea.ui.NMEAWizard;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;

public class DevicesPage extends WizardPage implements IValidatingPage {

	private TableViewer tableViewer;
	private ServiceRecord serviceRecord;
	private RemoteDevice _remoteDevice;

	public DevicesPage() {
		super("Devices", Messages.getString("DevicesPage.selectDevice"),ImageDescriptor.createFromURL(GPSBluetoothUIActivator.getDefault().getBundle().getEntry("/res/icons/stock_bluetooth_48x48.png"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setMessage(Messages.getString("DevicesPage.availableDevices")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		IDialogSettings settings = getWizard().getDialogSettings();

		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new BluetoothDeviceLabelProvider());
		List<Object> input = new ArrayList<Object>();
		input.add(Messages.getString("DevicesPage.searchingDevices")); //$NON-NLS-1$
		tableViewer.setInput(input);
		setControl(tableViewer.getControl());
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				getContainer().updateButtons();
			}
		});
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
	
	public void setDiscoveredDevices(List<RemoteDevice> discoveredDevices) {
		tableViewer.setInput(discoveredDevices);
		getContainer().updateButtons();
	}
	
	

	@Override
	public boolean isPageComplete() {
		IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
		return (!selection.isEmpty() && (!(selection.getFirstElement() instanceof String)))  ;
	}

	@Override
	public synchronized IStatus validatePage() {
		IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
		if(selection.isEmpty()) {
			return new Status(IStatus.ERROR, GPSBluetoothUIActivator.PLUGIN_ID, Messages.getString("DevicesPage.noSelection")); //$NON-NLS-1$
		} else {
			RemoteDevice remoteDevice = (RemoteDevice) selection.getFirstElement();
			UUID serviceUUID = new UUID(3); 
	        UUID[] searchUuidSet = new UUID[] { serviceUUID };
	        int[] attrIDs =  new int[] {
	                0x0100 // Service name
	        };
	        BluetoothDiscoveryListener bluetoothDiscoveryListener = new BluetoothDiscoveryListener(this);
			try {
				LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, remoteDevice, bluetoothDiscoveryListener);
				wait();
				if(bluetoothDiscoveryListener.getServiceRecords() == null || bluetoothDiscoveryListener.getServiceRecords().length == 0) {
					return new Status(IStatus.ERROR, GPSBluetoothUIActivator.PLUGIN_ID, Messages.getString("DevicesPage.noRFCOMM")); //$NON-NLS-1$
				} else {
//					serviceRecord = bluetoothDiscoveryListener.getServiceRecords()[0];
//					_remoteDevice = remoteDevice;
					for (ServiceRecord xserviceRecord : bluetoothDiscoveryListener.getServiceRecords()) {
						for(int i : xserviceRecord.getAttributeIDs()) {
							if(i == 0x100) {
								serviceRecord = xserviceRecord;
								_remoteDevice = remoteDevice;
							}
						}
						Logger.getLogger(getClass()).info("Found service " + serviceRecord );
					}
//					if(bluetoothDiscoveryListener.getServiceRecords().length == 1) {
				}
//					serviceRecord = bluetoothDiscoveryListener.getServiceRecords()[0];
//					_remoteDevice = remoteDevice;
//				} else {
//					for (ServiceRecord serviceRecord : bluetoothDiscoveryListener.getServiceRecords()) {
//						Logger.getLogger(getClass()).info("Found service " + serviceRecord );
//					}
//				}
				
			} catch (BluetoothStateException e) {
				Logger.getRootLogger().error(e);
				return new Status(IStatus.ERROR, GPSBluetoothUIActivator.PLUGIN_ID, e.getLocalizedMessage());
			} catch (InterruptedException e) {
				Logger.getRootLogger().error(e);
				return new Status(IStatus.OK, GPSBluetoothUIActivator.PLUGIN_ID, Messages.getString("DevicesPage.abort")); //$NON-NLS-1$
			}

//			remoteDevice.
		}
		return new Status(IStatus.OK, GPSBluetoothUIActivator.PLUGIN_ID, "Ok"); //$NON-NLS-1$
	}
	
	public ServiceRecord getServiceRecord() {
		return serviceRecord;
	}

	public RemoteDevice getRemoteDevice() {
		return _remoteDevice;
	}
	
	
}
