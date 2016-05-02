package net.sf.seesea.provider.navigation.nmea.v183;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.provider.navigation.nmea.NMEA0183Activator;
import net.sf.seesea.track.api.exception.RawDataEventException;

public class SerialNMEA0183InputStreamProcessorTest {

	@Test
	public void testStreamProcessor() throws IOException, RawDataEventException {
		URL fileEntry = NMEA0183Activator.getContext().getBundle().findEntries("res", "9220.dat", false).nextElement();
		InputStream fileStream = FileLocator.resolve(fileEntry).openStream();
		BufferedInputStream input = new BufferedInputStream(fileStream);

		int[] buf = new int[100];
		for(int i = 0; i < 100 ; i ++) {
			buf[i] = input.read();
		}
		
		SerialNMEA0183InputStreamProcessor serialNMEA0183InputStreamProcessor = new SerialNMEA0183InputStreamProcessor();
		boolean validStreamProcessor = serialNMEA0183InputStreamProcessor.isValidStreamProcessor(buf);
		
		assertTrue(validStreamProcessor);
	}
	
}
