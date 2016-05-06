package net.sf.seesea.navigation.son;

import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.track.api.exception.RawDataEventException;

public class SONStreamProcessorTest {

	@Test
	public void testStreamProcessor() throws IOException, RawDataEventException {
		URL fileEntry = SONActivator.getContext().getBundle().findEntries("res", "9171.dat", false).nextElement();
		InputStream fileStream = FileLocator.resolve(fileEntry).openStream();
		BufferedInputStream input = new BufferedInputStream(fileStream);

		int[] buf = new int[2048];
		for(int i = 0; i < 2048 ; i ++) {
			buf[i] = input.read();
		}
		
		SONStreamProcessor serialNMEA0183InputStreamProcessor = new SONStreamProcessor();
		boolean validStreamProcessor = serialNMEA0183InputStreamProcessor.isValidStreamProcessor(buf);
		
		assertTrue(validStreamProcessor);
	}
	
}
