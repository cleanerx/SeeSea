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
package net.sf.seesea.provider.navigation.fsh;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.List;

import net.sf.seesea.provider.navigation.fsh.data.FSHBlock;
import net.sf.seesea.provider.navigation.fsh.data.FSHHeader;
import net.sf.seesea.services.navigation.CompressionType;
import net.sf.seesea.services.navigation.IStreamProcessor;
import net.sf.seesea.services.navigation.ITrack;
import net.sf.seesea.services.navigation.NMEAProcessingException;

/**
 * This stream procesor takes binary streams from the actisense ngt1 NMEA2000 to USB converter and assembles
 * the stream to nmea 2000 messages that may be processed by {@link INMEA2000Listener}s.
 */
public class FSHStreamProcessor implements IStreamProcessor, IFSHReader {

	FSHProcessingState state;
	
	private int counter;
	
	byte[] message = new byte[2048];

	private List<IFSHListener> listeners;

	private int dataEnds;

	private FSHBlock lastBlock;

	public FSHStreamProcessor() {
		state = FSHProcessingState.HEADERSTART;
		counter = 1;
	}
	
	public FSHHeader readHeader(InputStream is) throws IOException {
		byte[] header = new byte[42];
		is.read(header, 0, 42);
		return readHeader(header);
	}
	
	public FSHHeader readHeader(byte[] header) {
		StringBuffer flashFile = new StringBuffer();
		if(header.length < 42) 
			return null;
		for(int i = 0 ; i < 16; i++) {
			flashFile.append((char) header[i]);
		}
		StringBuffer rayflob1 = new StringBuffer();
		for(int i = 28; i < 28 + 8; i++) {
			rayflob1.append((char)header[i]);
		}
		return new FSHHeader(flashFile.toString(), rayflob1.toString());
	}

	public FSHBlock readBlock(InputStream is) throws IOException {
		byte[] block = new byte[14];
		int read = is.read(block);
		if(read == -1 || read != 14) {
			return null;
		}
		
		return readBlock(block);
	}
	
	public FSHBlock readBlock(byte[] block) {
		int length = (block[1] & 0xFF) << 8;
		length |= (block[0] & 0xFF) ;
		
		long guid = (long)(block[9] & 0xFF) << 56;
		guid |= (long)(block[8] & 0xFF) << 48;
		guid |= (long)(block[7] & 0xFF) << 40;
		guid |= (long)(block[6] & 0xFF) << 32;
		guid |= (long)(block[5] & 0xFF) << 24;
		guid |= (long)(block[4] & 0xFF) << 16;
		guid |= (long)(block[3] & 0xFF) << 8;
		guid |= (long)(block[2] & 0xFF) ;

		int type = (block[11] & 0xFF) << 8;
		type |= (block[10] & 0xFF) ;
		
		return new FSHBlock(length,guid,type);
	}

	
	
	public boolean readByte(int c, String providerName) {
		if(FSHProcessingState.HEADERSTART.equals(state)) {
			message[counter++] = (byte) c;
			if(counter == 42) {
				readHeader(message);
				state = FSHProcessingState.DATABLOCK_START;
				counter = 0;
				return true;
			}
		} else if(FSHProcessingState.DATABLOCK_START.equals(state)) {
			message[counter++] = (byte) c;
			if(counter == 14) {
				lastBlock = readBlock(message);
				dataEnds = lastBlock.getLength() + lastBlock.getLength() % 2;
				state = FSHProcessingState.DATA_START;
				counter = 0;
				return true;
			}
		} else if(FSHProcessingState.DATABLOCK_START.equals(state)) {
			message[counter++] = (byte) c;
			if(counter == dataEnds) {
				for (IFSHListener listener : listeners) {
					listener.blockReceived(lastBlock, message);
				}
				state = FSHProcessingState.DATABLOCK_START;
				counter = 0;
				return true;
			}
		}
		
		return true;
	}
		
		
	public boolean isValidStreamProcessor(int[] buffer) throws NMEAProcessingException {
		byte[] array =  new byte[buffer.length];
		for (int i = 0 ; i < buffer.length ; i++) {
			array[i] = (byte) buffer[i];
		}
		FSHHeader readHeader = readHeader(array);
		if(readHeader != null && readHeader.getRl90().startsWith("RL90 FLASH FILE")) { //$NON-NLS-1$
			return true;
		}
		return false;
	}

	@Override
	public void close() throws IOException {
		// nothing to do
	}


	@Override
	public void addFSHListener(IFSHListener listener) {
		listeners.add(listener);		
	}

	@Override
	public void removeFSHListener(IFSHListener listener) {
		listeners.remove(listener);	}

	@Override
	public String getMimeType() {
		return "application/x-flashfile"; //$NON-NLS-1$
	}

	@Override
	public List<ITrack> getTracks(CompressionType compressionType, File file) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
