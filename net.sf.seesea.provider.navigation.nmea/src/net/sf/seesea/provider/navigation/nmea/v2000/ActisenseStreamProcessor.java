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
package net.sf.seesea.provider.navigation.nmea.v2000;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import net.sf.seesea.track.api.IStreamProcessor;
import net.sf.seesea.track.api.data.CompressionType;
import net.sf.seesea.track.api.data.ITrack;
import net.sf.seesea.track.api.exception.NMEAProcessingException;

/**
 * This stream procesor takes binary streams from the actisense ngt1 NMEA2000 to USB converter and assembles
 * the stream to nmea 2000 messages that may be processed by {@link INMEA2000Listener}s.
 */
@Component(property={"type:String=binary"})
public class ActisenseStreamProcessor implements IStreamProcessor, INMEA2000Reader {

	private MessageProcessingState state;

	boolean startEscape = false;

	boolean noEscape = false;

	boolean isFile = true;

	int[] message = new int[2048];

	int STARTTX = 0x02;
	int ENDTX = 0x03;
	int DLE = 0x010;
	int ESCAPE = 0x1B;
	
	int N2K_MSG_RECEIVED = 0x93;
	
	private int counter;

	private List<INMEA2000Listener> listeners;

	private boolean processData;
	
	public ActisenseStreamProcessor() {
		state = MessageProcessingState.MESSAGE_START;
		counter = 0;
		listeners = new ArrayList<INMEA2000Listener>(2);
	}
	
	/**
	 * assembles the nmea 2000 message
	 */
	public boolean readByte(int c, String providerName) {
		if (state.equals(MessageProcessingState.MESSAGE_START)) {
			if ((c == 0x1B) && isFile) {
				noEscape = true;
			}
		}

		if (state.equals(MessageProcessingState.MESSAGE_ESACPE)) {
			if (c == ENDTX) {
				messageReceived(Arrays.copyOfRange(message, 0, counter));
//				System.out.println(System.currentTimeMillis() +  ": Read bytes:" + counter);
				counter = 0;
				state = MessageProcessingState.MESSAGE_START;
			} else if (c == STARTTX) {
				state = MessageProcessingState.MESSAGE_MESSAGE;
			} else if ((c == DLE) || ((c == ESCAPE) && isFile) || noEscape) {
				state = MessageProcessingState.MESSAGE_MESSAGE;
			} else {
				state = MessageProcessingState.MESSAGE_START;
			}
		} else if (state == MessageProcessingState.MESSAGE_MESSAGE) {
			if (c == DLE) {
				state = MessageProcessingState.MESSAGE_ESACPE;
			} else if (isFile && (c == ESCAPE) && !noEscape) {
				state = MessageProcessingState.MESSAGE_ESACPE;
			} else {
				message[counter++] = c;
			}
		} else {
			if (c == DLE) {
				state = MessageProcessingState.MESSAGE_ESACPE;
			}
		}
		return processData;
	}

	public void messageReceived(int[] is) {
		int command;
		  if (is.length < 3)
		  {
		    return;
		  } else if(is.length < is[1] + 2) {
			  return;
		  } 
		  command = is[0];
		  
		  long checksum = 0;
		  if(is[1] + 2 >= is.length) {
//			  System.out.println("No checksum");
			  checksum = 0;
		  } else {
			  for (int i = 0; i < is[1] + 2; i++) {
				  checksum += is[i];
			  }
//			  System.out.println(is[is[1] + 2] + ":" + checksum % 256);
			  checksum = 0;
		  }
		  
		  if(checksum % 256 == 0) {
			  if (command == N2K_MSG_RECEIVED) {
				  processNmea2000Message(Arrays.copyOfRange(is, 2, is.length));
			  } else if(command == 160) {
				  processNmea2000Message(Arrays.copyOfRange(is, 2, is.length));
			  }
		  }
		  
	}

	private void processNmea2000Message(int[] data) {
		if(data.length < 11) {
			return;
		}
		for (INMEA2000Listener listener : listeners) {
			listener.nmeaEventReceived(data);
		}
		
	}
	
	public boolean isValidStreamProcessor(int[] buffer) {
		processData = true;
		counter = 0;
		List<INMEA2000Listener> list = new ArrayList<INMEA2000Listener>(listeners);
		listeners.removeAll(list);
		message = new int[65536 * 4];
		NMEA2000StreamDetector detector = new NMEA2000StreamDetector();
		addNMEA2000Listener(detector);
		try {
			for (int i : buffer) {
				readByte(i, "none"); //$NON-NLS-1$
			}
		} finally {
			removeNMEA2000Listener(detector);
			listeners.addAll(list);
			message = new int[2048];
			state = MessageProcessingState.MESSAGE_START;
		}
		return detector.isNMEA2000Stream();
	}

	public void addNMEA2000Listener(INMEA2000Listener listener) {
		listeners.add(listener);
	}

	public void removeNMEA2000Listener(INMEA2000Listener listener) {
		listeners.remove(listener);
	}

	public static void main(String[] args) throws IOException {
		ActisenseStreamProcessor actisense = new ActisenseStreamProcessor();
		String file = "trace.dat"; //$NON-NLS-1$
		BufferedInputStream input = new BufferedInputStream(
				new FileInputStream(file));
		byte[] buffer = new byte[512];

		while (input.read(buffer) != -1) {
			for (int i = 0; i < buffer.length; i++) {
				int c = buffer[i] & 0xFF;
				actisense.readByte(c, "none");
			}
		}
	}

	@Override
	public void close() throws IOException {
		processData = false;
//		Thread.currentThread().interrupt();
	}

	@Override
	public String getMimeType() {
		return "application/x-actisense"; //$NON-NLS-1$
	}

	@Override
	public boolean isBinary() {
		return true;
	}

	
}
