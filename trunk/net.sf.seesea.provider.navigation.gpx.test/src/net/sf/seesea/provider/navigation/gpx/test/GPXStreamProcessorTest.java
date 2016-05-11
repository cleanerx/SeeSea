package net.sf.seesea.provider.navigation.gpx.test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.provider.navigation.gpx.GPXActivator;
import net.sf.seesea.provider.navigation.gpx.GPXStreamProcessor;
import net.sf.seesea.track.api.exception.RawDataEventException;

public class GPXStreamProcessorTest {


	@Test
	public void testX() throws RawDataEventException, IOException {
		URL fileEntry = GPXActivator.getContext().getBundle().findEntries("res", "8629.dat", false).nextElement();
		InputStream fileStream = FileLocator.resolve(fileEntry).openStream();
		BufferedInputStream input = new BufferedInputStream(fileStream);

		int[] buf = new int[2048];
		for(int i = 0; i < 2048 ; i ++) {
			buf[i] = input.read();
		}

		GPXStreamProcessor gpxStreamProcessor = new GPXStreamProcessor();
		assertTrue(gpxStreamProcessor.isValidStreamProcessor(buf));
	}

}
