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
package net.sf.seesea.track.api;

import java.io.IOException;

import net.sf.seesea.track.api.exception.NMEAProcessingException;

/**
 * A stream processor is used to read single bytes 
 */
public interface IStreamProcessor {

	/**
	 * 
	 * @return the mime type the stream processor can detect
	 */
	String getMimeType();
	
	/**
	 * This method is used to detect for an array of bytes if this stream processor can handle that data 
	 * 
	 * @param buffer the input data
	 * @return true if the buffer contains data this stream processor is able to process  
	 * @throws NMEAProcessingException
	 */
	boolean isValidStreamProcessor(int[] buffer) throws NMEAProcessingException;
	
	/**
	 * reads a single byte from an underlying stream or source
	 * 
	 * @param c
	 * @param streamProvider
	 * @return false if processing should stop due user termination
	 * @throws NMEAProcessingException
	 */
	boolean readByte(int c, String streamProvider) throws NMEAProcessingException ;
	
	/**
	 * actively closes the stream
	 * @throws IOException
	 */
	void close() throws IOException;

	/**
	 * This may be used to aid misinterpretations in stream processor detection
	 * 
	 * @return if the stream processor accepts binary data or ascii data.
	 */
	boolean isBinary();
}
