package net.sf.seesea.navigation.sl2;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.ProcessingException;
import net.sf.seesea.track.model.SimpleTrackFile;

public class SL2TrackFileProcessorTest {

	@Test
	public void testProcessing() throws FileNotFoundException, IOException, ProcessingException {
		URL fileEntry = SL2Activator.getContext().getBundle().findEntries("res", "50127.dat", false).nextElement();

		SimpleTrackFile simpleTrackFile = new SimpleTrackFile();
		simpleTrackFile.setFileReference(FileLocator.resolve(fileEntry).getFile());
		
		final List<Measurement> measurements = new ArrayList<Measurement>();
		IMeasurmentProcessor iMeasurmentProcessor = new IMeasurmentProcessor() {
			
			@Override
			public void processMeasurements(List<Measurement> results, String messageType, long sourceTrackIdentifier,
					GeoBoundingBox boundingBox, ITrackFile trackfile) throws ProcessingException {
				measurements.addAll(results);
				
			}
			
			@Override
			public void finish() throws ProcessingException {
				// TODO Auto-generated method stub
				
			}
		};
		
		SL2TrackFileProcessor nmea2000TrackFileProcessor = new SL2TrackFileProcessor();
		nmea2000TrackFileProcessor.setMeasurementProcessor(iMeasurmentProcessor);
		nmea2000TrackFileProcessor.processFile(simpleTrackFile);
		
		assertFalse(measurements.isEmpty());
		assertEquals(16614, measurements.size());

		Measurement measurement = measurements.get(0);
		assertTrue(measurement instanceof MeasuredPosition3D);
		MeasuredPosition3D gnssMeasuredPosition = (MeasuredPosition3D) measurement;
		assertNull(gnssMeasuredPosition.getSensorID());
		assertEquals(7153887L,gnssMeasuredPosition.getTime().getTime());
		assertEquals(0.0, gnssMeasuredPosition.getAltitude(), 0.0001);
		Latitude latitude = gnssMeasuredPosition.getLatitude();
		assertEquals(47.471064309147096, latitude.getDecimalDegree(), 0.0001);
		Longitude longitude = gnssMeasuredPosition.getLongitude();
		assertEquals(11.711553214266035, longitude.getDecimalDegree(), 0.0001);
		assertNull(gnssMeasuredPosition.getTimezone());
		assertTrue(gnssMeasuredPosition.isValid());

		measurement = measurements.get(1);
		assertTrue(measurement instanceof Depth);
		Depth depth = (Depth) measurement;
		assertEquals(95.75260925292969, depth.getDepth(), 0.0001);
		assertTrue(depth.isValid());
		

	}

}


