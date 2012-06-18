/**
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

package net.sf.seesea.gps.bluetooth.ui;

import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import net.sf.seesea.gps.bluetooth.ui.wizard.DevicesPage;

import org.eclipse.swt.widgets.Display;

public class BluetoothDiscoveryListener implements DiscoveryListener {
	
	private List<RemoteDevice> devicesDiscovered;
	private ServiceRecord[] servRecord;
	private final DevicesPage devicesPage;
	
	public BluetoothDiscoveryListener(DevicesPage devicesPage) {
		this.devicesPage = devicesPage;
		devicesDiscovered = new ArrayList<RemoteDevice>();
	}

    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        devicesDiscovered.add(btDevice);
    }

    public void inquiryCompleted(int discType) {
    	Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				devicesPage.setDiscoveredDevices(devicesDiscovered);
			}
		});
    }

    public void serviceSearchCompleted(int transID, int respCode) {
    	synchronized (devicesPage) {
    		devicesPage.notify();
		}
    }

    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
    	this.servRecord = servRecord;
    }

	public List<RemoteDevice> getDevicesDiscovered() {
		return devicesDiscovered;
	}

	public ServiceRecord[] getServiceRecords() {
		return this.servRecord;
	}

    
}
