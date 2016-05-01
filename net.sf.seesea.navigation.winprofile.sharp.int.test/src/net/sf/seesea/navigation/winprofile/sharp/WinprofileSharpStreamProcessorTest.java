package net.sf.seesea.navigation.winprofile.sharp;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.track.api.exception.RawDataEventException;

public class WinprofileSharpStreamProcessorTest {

	@Test
	public void testStreamProcessor() throws IOException, URISyntaxException, RawDataEventException {
		URL nextElement = WinprofileSharpActivator.getContext().getBundle().findEntries("res", "4180.dat", true).nextElement();
		URL fileURL = FileLocator.resolve(nextElement);
		File file = new File(fileURL.getFile());
		FileInputStream fileInputStream = new FileInputStream(file);
		int[] buffer = new int[1024];
		for(int i = 0; i < 1024; i++) {
			buffer[i] = fileInputStream.read();
		}
		
		WinprofileSharpStreamProcessor winprofileSharpStreamProcessor = new WinprofileSharpStreamProcessor();
		boolean valid = winprofileSharpStreamProcessor.isValidStreamProcessor(buffer);
		assertTrue(valid);
	}

}
