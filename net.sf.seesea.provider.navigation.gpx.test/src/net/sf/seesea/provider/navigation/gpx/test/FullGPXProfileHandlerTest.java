/**
 * 
Copyright (c) 2010-2016, Jens Kübler
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

package net.sf.seesea.provider.navigation.gpx.test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import junit.framework.TestCase;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.gpx.FullGPXProfileHandler;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.InputStreamNotFoundException;
import net.sf.seesea.track.api.exception.ProcessingException;

public class FullGPXProfileHandlerTest extends TestCase {

	public void testReadGPXFile() throws IOException, InputStreamNotFoundException, ProcessingException, SAXException {
		
		IMeasurmentProcessor measurmentProcessor = EasyMock.createNiceMock(IMeasurmentProcessor.class);
		List<Measurement> list = new ArrayList<Measurement>();
		Capture<List<Measurement>> measurements = new Capture<List<Measurement>>();
		measurmentProcessor.processMeasurements(EasyMock.capture(measurements), EasyMock.anyString(), EasyMock.anyLong(), EasyMock.<GeoBoundingBox>anyObject(), null);
		EasyMock.replay(measurmentProcessor);
		
		URL url = GPXTestActivator.getContext().getBundle().getEntry("/res/8629.dat"); //$NON-NLS-1$
		ITrackFile trackFile = EasyMock.createNiceMock(ITrackFile.class);
		EasyMock.expect(trackFile.getInputStream()).andReturn(url.openStream());
		EasyMock.replay(trackFile);
		
		InputSource inputSource = new InputSource(trackFile.getInputStream());
		FullGPXProfileHandler handler = new FullGPXProfileHandler(measurmentProcessor, trackFile);
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		xmlReader.setContentHandler(handler);
		xmlReader.setErrorHandler(new ErrorHandler() {
			
			@Override
			public void warning(SAXParseException exception) throws SAXException {
//				exception.printStackTrace();
			}
			
			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
//				exception.printStackTrace();
			}
			
			@Override
			public void error(SAXParseException exception) throws SAXException {
//				exception.printStackTrace();
			}
		});
		xmlReader.parse(inputSource);
	
		assertEquals("track should contain measurements", 1,measurements.getValues().size());
//		List<MeasuredPosition3D> track = gpxReader.readTrack(stream);
//		stream.close();
		
		
	}
	
}
