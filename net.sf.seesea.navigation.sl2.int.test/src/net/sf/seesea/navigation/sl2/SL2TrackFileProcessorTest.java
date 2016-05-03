package net.sf.seesea.navigation.sl2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.exception.ProcessingException;
import net.sf.seesea.track.model.SimpleTrackFile;

public class SL2TrackFileProcessorTest {

	@Test
	public void testProcessing() throws FileNotFoundException, IOException, ProcessingException {
		URL fileEntry = SL2Activator.getContext().getBundle().findEntries("res", "20001114_10_nmea_trace.dat", false).nextElement();

		SimpleTrackFile simpleTrackFile = new SimpleTrackFile();
		simpleTrackFile.setFileReference(FileLocator.resolve(fileEntry).getFile());
		
		final List<Measurement> measurements = new ArrayList<Measurement>();
		IMeasurmentProcessor iMeasurmentProcessor = new IMeasurmentProcessor() {
			
			@Override
			public void processMeasurements(List<Measurement> results, String messageType, long sourceTrackIdentifier,
					GeoBoundingBox boundingBox) throws ProcessingException {
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
		assertEquals(8373, measurements.size());

		Measurement measurement = measurements.get(0);
		assertTrue(measurement instanceof MeasuredPosition3D);
		MeasuredPosition3D gnssMeasuredPosition = (MeasuredPosition3D) measurement;
		assertEquals("245", gnssMeasuredPosition.getSensorID());
		assertEquals(1346678690000L,gnssMeasuredPosition.getTime().getTime());
		assertEquals(-27.01, gnssMeasuredPosition.getAltitude(), 0.0001);
		Latitude latitude = gnssMeasuredPosition.getLatitude();
		assertEquals(36.541331357774645, latitude.getDecimalDegree(), 0.0001);
		Longitude longitude = gnssMeasuredPosition.getLongitude();
		assertEquals(29.051541471730072, longitude.getDecimalDegree(), 0.0001);
		assertEquals("UTC", gnssMeasuredPosition.getTimezone());
		assertTrue(gnssMeasuredPosition.isValid());

	}

}


