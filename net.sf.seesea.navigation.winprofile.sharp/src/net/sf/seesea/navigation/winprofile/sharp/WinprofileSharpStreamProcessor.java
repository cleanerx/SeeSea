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
package net.sf.seesea.navigation.winprofile.sharp;

import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import net.sf.seesea.track.api.IStreamProcessor;
import net.sf.seesea.track.api.exception.RawDataEventException;

@Component
public class WinprofileSharpStreamProcessor implements IStreamProcessor {

	@Override
	public String getMimeType() {
		return "application/x-sharp"; //$NON-NLS-1$
	}

	@Override
	public boolean isValidStreamProcessor(int[] buffer)
			throws RawDataEventException {
		byte[] byteBuffer = new byte[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			byteBuffer[i] = (byte) buffer[i];
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(byteBuffer);
		InputSource inputSource = new InputSource(bis);
		WinprofileHandler winprofileHandler = new WinprofileHandler();
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			xmlReader.setContentHandler(winprofileHandler);
			xmlReader.parse(inputSource);
		} catch (CharConversionException e3) {
			// don't log it
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("Failed to read from buffer", e); //$NON-NLS-1$
		} catch (SAXException e2) {
			// incomplete parse is valid and throws an exception
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("Failed to read from buffer2"); //$NON-NLS-1$
		}
		return winprofileHandler.isValidStream();
	}

	@Override
	public boolean readByte(int c, String streamProvider)
			throws RawDataEventException {
		return false;
	}

	@Override
	public void close() throws IOException {
		// nothing to do
	}

	@Override
	public boolean isBinary() {
		return false;
	}

}
