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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.services.navigation.INMEAReader;
import net.sf.seesea.services.navigation.RawDataEvent;
import net.sf.seesea.services.navigation.RawDataEventListener;
import net.sf.seesea.track.api.exception.NMEAProcessingException;
import net.sf.seesea.track.api.exception.RawDataEventException;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class NMEA0183TrackSimulator implements Runnable, INMEAReader {

	private final List<InputStream> inputStreams;
	private List<RawDataEventListener> nmeaEventListeners;
	private List<RawDataEventListener> aisEventListeners;

	private boolean _paused;
	private boolean _stopped;

	public NMEA0183TrackSimulator(List<InputStream> fileInputStream) {
		inputStreams = fileInputStream;
		nmeaEventListeners = new ArrayList<RawDataEventListener>(2);
		aisEventListeners = new ArrayList<RawDataEventListener>(2);
		_paused = false;
		_stopped = false;
	}

	public void run() {
		try {
			String currentLine = ""; //$NON-NLS-1$

			BundleContext bundleContext = NMEA0183Activator.getContext()
					.getBundle().getBundleContext();
			ServiceRegistration<INMEAReader> serviceRegistration = bundleContext.registerService(INMEAReader.class, this, null);
//			AISParser aisParser = new AISParser(new AisMessageMultiplexer(), new DummyErrorHandler());
//			File file = new File(".");
			for (InputStream stream : inputStreams) {
//				int lineNumber = 0;
				InputStreamReader inputStreamReader = new InputStreamReader(stream);
				BufferedReader in = new BufferedReader(inputStreamReader);
				currentLine = ""; //$NON-NLS-1$
				while (currentLine != null) {
					currentLine = in.readLine();
//					lineNumber++;
					while(_paused && !_stopped) {
						Thread.sleep(1000);
					}
					if(_stopped) {
						serviceRegistration.unregister();
						return;
					}
					if (currentLine != null) {
						if(currentLine.startsWith("$")) { //$NON-NLS-1$
							RawDataEvent nmeaEvent = new RawDataEvent(currentLine, null);
							try {
								for (RawDataEventListener eventListener : nmeaEventListeners) {
									eventListener.receiveRawDataEvent(nmeaEvent);
								}
							} catch (RawDataEventException e) {
								Logger.getLogger(getClass()).debug("Failed event " + nmeaEvent.getNmeaMessageContent(), e); //$NON-NLS-1$
							}
							Thread.sleep(5);
						} else if(currentLine.startsWith("!")) { //$NON-NLS-1$
							RawDataEvent nmeaEvent = new RawDataEvent(currentLine, null);
							try {
								for (RawDataEventListener eventListener : aisEventListeners) {
									eventListener.receiveRawDataEvent(nmeaEvent);
								}
							} catch (RawDataEventException e) {
								Logger.getLogger(getClass()).debug("Failed event " + nmeaEvent.getNmeaMessageContent(), e); //$NON-NLS-1$
							}
//							Thread.sleep(5);
							
//							aisParser.handleSensorData(new FileSource(file, lineNumber, currentLine, Calendar.getInstance().getTime().getTime()), currentLine);
						}
						
					}
				}
			}
		} catch (IOException e) {
			Logger.getLogger(NMEA0183TrackSimulator.class).debug("IO Exception while simulate track", e); //$NON-NLS-1$
		 } catch (InterruptedException e) {
			 Logger.getLogger(NMEA0183TrackSimulator.class).debug("Simulator interrupted", e); //$NON-NLS-1$
		 }

		// return track;
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

	public void pause() {
		_paused = true;
	}
	
	public void resume() {
		_paused = false;
	}
	
	public void stop() {
		_stopped = true;
	}

	@Override
	public void addAISEventListener(RawDataEventListener nmeaEventListener) {
		aisEventListeners.add(nmeaEventListener);
	}

	@Override
	public void removeAISEventListener(RawDataEventListener nmeaEventListener) {
		aisEventListeners.remove(nmeaEventListener);
	}
	
}
