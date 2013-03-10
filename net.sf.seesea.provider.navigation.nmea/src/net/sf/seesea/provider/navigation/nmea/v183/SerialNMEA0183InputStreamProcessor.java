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
package net.sf.seesea.provider.navigation.nmea.v183;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.seesea.services.navigation.INMEAReader;
import net.sf.seesea.services.navigation.IStreamProcessor;
import net.sf.seesea.services.navigation.NMEAProcessingException;
import net.sf.seesea.services.navigation.RawDataEvent;
import net.sf.seesea.services.navigation.RawDataEventListener;

public class SerialNMEA0183InputStreamProcessor implements IStreamProcessor, INMEAReader {
	
	private StringBuffer stringBuffer;

	private final List<RawDataEventListener> nmeaEventListeners;

	private List<RawDataEventListener> aisEventListeners;
	
	public SerialNMEA0183InputStreamProcessor() {
		stringBuffer = null;	
		nmeaEventListeners = new ArrayList<RawDataEventListener>(1);
		aisEventListeners = new ArrayList<RawDataEventListener>(1);
	}
 
	public boolean isValidStreamProcessor(byte[] buf) throws NMEAProcessingException {
		NMEA0183StreamDetector nmea0183StreamDetector = new NMEA0183StreamDetector();
		nmeaEventListeners.add(nmea0183StreamDetector);
		aisEventListeners.add(nmea0183StreamDetector);
		for (int i : buf) {
			readByte(i, "none"); //$NON-NLS-1$
		}
		aisEventListeners.remove(nmea0183StreamDetector);
		nmeaEventListeners.remove(nmea0183StreamDetector);
		return nmea0183StreamDetector.isNmea0183Stream();
		
	}
	
	public void readByte(int i, String streamProvider) throws NMEAProcessingException {
		switch (i) {
		case '\n':
			if(stringBuffer != null) {
				stringBuffer.append((char) i);
				String line = stringBuffer.toString();
				try {
					if(line.startsWith("!")) { //$NON-NLS-1$
						RawDataEvent nmeaEvent = new RawDataEvent(stringBuffer.toString(), streamProvider);
						for(RawDataEventListener aisEventListener : aisEventListeners) {
							aisEventListener.receiveRawDataEvent(nmeaEvent);
						}
					} else if(line.startsWith("$")) { //$NON-NLS-1$
						RawDataEvent nmeaEvent = new RawDataEvent(stringBuffer.toString(), streamProvider);
						for (RawDataEventListener nmeaEventListener : nmeaEventListeners) {
							nmeaEventListener.receiveRawDataEvent(nmeaEvent);
						}
					}
				} catch (UnsupportedEncodingException e) {
					Logger.getLogger(getClass()).error("No Encoding available", e); //$NON-NLS-1$
				}
			}
			break;
		case '$':
		case '!':
			stringBuffer = new StringBuffer();
			stringBuffer.append((char) i);
			break;
		default :
			if(stringBuffer != null) {
				stringBuffer.append((char) i);
			}
			break;
		}
	}

	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void addNMEAEventListener(RawDataEventListener nmeaEventListener) {
		nmeaEventListeners.add(nmeaEventListener);
	}

	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void removeNMEAEventListener(RawDataEventListener nmeaEventListener) {
		nmeaEventListeners.remove(nmeaEventListener);
	}

	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void addAISEventListener(RawDataEventListener nmeaEventListener) {
		aisEventListeners.add(nmeaEventListener);
	}

	/**
	 * 
	 * @param nmeaEventListener
	 */
	public void removeAISEventListener(RawDataEventListener nmeaEventListener) {
		aisEventListeners.remove(nmeaEventListener);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	
}
