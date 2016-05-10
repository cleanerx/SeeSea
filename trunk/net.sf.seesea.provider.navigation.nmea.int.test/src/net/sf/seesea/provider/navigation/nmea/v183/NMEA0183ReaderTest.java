package net.sf.seesea.provider.navigation.nmea.v183;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.nmea.NMEA0183Activator;
import net.sf.seesea.provider.navigation.nmea.NMEA0183EventProcessor;

public class NMEA0183ReaderTest {

	public NMEA0183ReaderTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testReaderContent() throws IOException {
		URL fileEntry = NMEA0183Activator.getContext().getBundle().findEntries("res", "9220.dat", false).nextElement();
		String file = FileLocator.resolve(fileEntry).getFile();
		
		NMEA0183Reader nmea0183Reader = new NMEA0183Reader(new FileInputStream(file));
		Collection<Measurement> measurements = nmea0183Reader.read();
		assertEquals(6228, measurements.size());
	}

}
