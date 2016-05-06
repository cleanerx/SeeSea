package net.sf.seesea.provider.navigation.adm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import net.sf.seesea.track.api.data.IBoatParameters;
import net.sf.seesea.track.api.exception.ProcessingException;
import net.sf.seesea.track.model.SimpleTrackFile;

public class ADMTrackFileProcessorTest {

	@Test
	public void testProcessing() throws FileNotFoundException, IOException, ProcessingException {
		URL fileEntry = ADMActivator.getContext().getBundle().findEntries("res", "9152.dat", false).nextElement();

		SimpleTrackFile simpleTrackFile = new SimpleTrackFile();
		simpleTrackFile.setFileReference(FileLocator.resolve(fileEntry).getFile());
		
		final List<Measurement> measurements = new ArrayList<Measurement>();
		IMeasurmentProcessor iMeasurmentProcessor = new IMeasurmentProcessor() {
			
			@Override
			public void processMeasurements(List<Measurement> results, String messageType, long sourceTrackIdentifier,
					GeoBoundingBox boundingBox, IBoatParameters boatParameters) throws ProcessingException {
				measurements.addAll(results);
				
			}
			
			@Override
			public void finish() throws ProcessingException {
				// TODO Auto-generated method stub
				
			}
		};
		
		ADMFileProcessor nmea0183TrackFileProcessor = new ADMFileProcessor();
		nmea0183TrackFileProcessor.setMeasurementProcessor(iMeasurmentProcessor);
		nmea0183TrackFileProcessor.processFile(simpleTrackFile);
		
		assertFalse(measurements.isEmpty());
		assertEquals(94434, measurements.size());
		
		Measurement measurement = measurements.get(0);
		assertTrue(measurement instanceof Depth);
		Depth depth = (Depth) measurement;
		assertNull(depth.getSensorID());
		assertEquals(8.515422821044922,depth.getDepth(), 0.0001);
		
		measurement = measurements.get(1);
		assertTrue(measurement instanceof MeasuredPosition3D);
		MeasuredPosition3D gnssMeasuredPosition = (MeasuredPosition3D) measurement;
		assertEquals(0.0, gnssMeasuredPosition.getAltitude(), 0.0001);
		Latitude latitude = gnssMeasuredPosition.getLatitude();
		assertEquals(26.49753525284333, latitude.getDecimalDegree(), 0.0001);
		Longitude longitude = gnssMeasuredPosition.getLongitude();
		assertEquals(-80.04715120714998, longitude.getDecimalDegree(), 0.0001);
		Date time2 = gnssMeasuredPosition.getTime();
		assertEquals(1404222770000L, time2.getTime());
//		assertEquals("UTC", gnssMeasuredPosition.getTimezone());

	}

}


