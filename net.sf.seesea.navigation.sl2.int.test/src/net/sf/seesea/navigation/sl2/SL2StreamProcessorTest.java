package net.sf.seesea.navigation.sl2;

import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.track.api.exception.RawDataEventException;

public class SL2StreamProcessorTest {

	@Test
	public void testStreamProcessor() throws IOException, RawDataEventException {
		URL fileEntry = SL2Activator.getContext().getBundle().findEntries("res", "20001114_10_nmea_trace.dat", false).nextElement();
		InputStream fileStream = FileLocator.resolve(fileEntry).openStream();
		BufferedInputStream input = new BufferedInputStream(fileStream);

		int[] buf = new int[100];
		for(int i = 0; i < 100 ; i ++) {
			buf[i] = input.read();
		}
		
		SL2StreamProcessor serialNMEA0183InputStreamProcessor = new SL2StreamProcessor();
		boolean validStreamProcessor = serialNMEA0183InputStreamProcessor.isValidStreamProcessor(buf);
		
		assertTrue(validStreamProcessor);
	}
	
}
