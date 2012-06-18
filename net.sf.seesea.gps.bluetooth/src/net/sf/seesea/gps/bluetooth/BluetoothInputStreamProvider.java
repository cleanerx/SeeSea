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

package net.sf.seesea.gps.bluetooth;

import java.io.IOException;
import java.io.InputStream;

import javax.bluetooth.ServiceRecord;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import org.apache.log4j.Logger;

import net.sf.seesea.services.navigation.provider.INMEAStreamProvider;

/**
 * An input stream provider that receives data via bluetooth 
 */
public class BluetoothInputStreamProvider implements INMEAStreamProvider {

	private final ServiceRecord servRecord;
	private StreamConnection connectionNotifier;
	private InputStream bluetoothInputStream;

	public BluetoothInputStreamProvider(ServiceRecord servRecord) {
		this.servRecord = servRecord;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		String url = servRecord.getConnectionURL(
				ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
		if (url == null) {
			return null;
		}
		connectionNotifier = (StreamConnection) Connector.open(url);
		bluetoothInputStream = connectionNotifier.openInputStream();
		return bluetoothInputStream;
	}

	@Override
	public void close() throws IOException {
		if(bluetoothInputStream != null) {
			bluetoothInputStream.close();
		}
		if(connectionNotifier != null) {
			connectionNotifier.close();
		}
	}

	@Override
	public String getName() {
		try {
			return servRecord.getHostDevice().getFriendlyName(false);
		} catch (IOException e) {
			Logger.getLogger(BluetoothInputStreamProvider.class).error("Failed to get user friendly name", e); //$NON-NLS-1$
		}
		return Messages.getString("BluetoothInputStreamProvider.unnamed"); //$NON-NLS-1$
	}

}
