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
package net.sf.seesea.provider.navigation.nmea;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.ServiceRegistration;

/**
 * A reader that processes nmea input streams
 */
public class NMEA0183Reader extends InputStreamReader {

	private final List<NMEAEventListener> nmeaEventListeners;
	private final ServiceRegistration serviceRegistration;

	/**
	 * @param in
	 */
	public NMEA0183Reader(InputStream in) {
		super(in);
		nmeaEventListeners = new ArrayList<NMEAEventListener>(1);
		serviceRegistration = NMEA0183Activator.getDefault().getBundle().getBundleContext().registerService(NMEA0183Reader.class.getName(), this, null);
	}
	
	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void addNMEAEventListener(NMEAEventListener nmeaEventListener) {
		nmeaEventListeners.add(nmeaEventListener);
	}

	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void removeNMEAEventListener(NMEAEventListener nmeaEventListener) {
		nmeaEventListeners.remove(nmeaEventListener);
	}

	/**
	 * 
	 * @throws IOException
	 * @throws NMEAProcessingException 
	 */
	public void readLine() throws IOException, NMEAProcessingException {
		StringBuffer stringBuffer = null;
		for (int i = read(); i != -1 ;) {
			switch (i) {
				case '\n':
					if(stringBuffer != null) {
						stringBuffer.append((char) i);
						NMEAEvent nmeaEvent = new NMEAEvent(stringBuffer.toString());
						for (NMEAEventListener nmeaEventListener : nmeaEventListeners) {
							nmeaEventListener.receiveNMEAEvent(nmeaEvent);
						}
					}
					i = read();
					break;
				case '$':
					stringBuffer = new StringBuffer();
					stringBuffer.append((char) i);
					i = read();
					break;
				default :
					if(stringBuffer != null) {
						stringBuffer.append((char) i);
					}
					i = read();
					break;
			}
			if(Thread.interrupted()) {
				unregisterService();
				return;
			}
		}
	}
	
	public void unregisterService() {
		serviceRegistration.unregister();
	}


}