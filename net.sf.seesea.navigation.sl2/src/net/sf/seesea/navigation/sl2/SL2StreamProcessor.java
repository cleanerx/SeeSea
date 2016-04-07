/**
 *  Copyright 2013 Samuli Penttila
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.seesea.navigation.sl2;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.sf.seesea.track.api.IStreamProcessor;
import net.sf.seesea.track.api.exception.NMEAProcessingException;


public class SL2StreamProcessor implements IStreamProcessor, ISL2Reader {

	private MessageProcessingState state;

	private int[] message = new int[4096];

	private int counter;

	private short blockEnd;

	private List<ISL2Listener> listeners;

	public SL2StreamProcessor() {
		listeners = new CopyOnWriteArrayList<ISL2Listener>();
		state = MessageProcessingState.HEADER_START;
		blockEnd = -1;
	}
	
	@Override
	public String getMimeType() {
		return "application/sl2"; //$NON-NLS-1$
	}

	@Override
	public boolean isValidStreamProcessor(int[] buffer)
			throws NMEAProcessingException {
		if(buffer.length < 10) {
			return false;
		}
		
		if(buffer[0] == 2 && buffer[1] == 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean readByte(int c, String streamProvider)
			throws NMEAProcessingException {
		if(counter == message.length) {
			throw new NMEAProcessingException("No usable data found within " + counter + "bytes");
		}
		if(state.equals(MessageProcessingState.HEADER_START)) {
			message[counter++] = c;
			if(counter == 8) {
				state = MessageProcessingState.BLOCK_START;
				counter = 0;
			}
		} else {
			if (state.equals(MessageProcessingState.BLOCK_START)) {
				message[counter++] = c;
				if(counter == 30) {
			 		ByteBuffer allocate = ByteBuffer.allocate(2);
			 		allocate.put((byte) message[28]);
			 		allocate.put((byte) message[29]);
			 		allocate.order(ByteOrder.LITTLE_ENDIAN);
			 		allocate.flip();
					blockEnd = allocate.getShort();
					// (short) (((message[26] << 8) &0xFF00) | (message[27] & 0xFF))
				}
				if(counter == blockEnd) {
					int[] copyOfRange = Arrays.copyOfRange(message, 0, blockEnd);
					for (ISL2Listener listener : listeners) {
						listener.notifySL2Block(copyOfRange);
					}
					counter = 0;
					blockEnd = 0;
				}
			}
		}
		
		return true;
	}

	@Override
	public void close() throws IOException {
		counter = 0;
		blockEnd = -1;
		state = MessageProcessingState.BLOCK_START;

	}

	@Override
	public boolean isBinary() {
		return true;
	}

	@Override
	public void addSL2Listener(ISL2Listener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeSL2Listener(ISL2Listener listener) {
		listeners.remove(listener);
	}

}
