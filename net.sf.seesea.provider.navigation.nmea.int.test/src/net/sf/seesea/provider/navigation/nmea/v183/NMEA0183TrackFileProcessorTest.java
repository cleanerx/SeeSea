package net.sf.seesea.provider.navigation.nmea.v183;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GNSSMeasuredPosition;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.physx.CompositeMeasurement;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.RelativeSpeed;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.model.core.physx.Time;
import net.sf.seesea.provider.navigation.nmea.NMEA0183Activator;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.exception.ProcessingException;
import net.sf.seesea.track.model.SimpleTrackFile;

public class NMEA0183TrackFileProcessorTest {

	@Test
	public void testProcessing() throws FileNotFoundException, IOException, ProcessingException {
		URL fileEntry = NMEA0183Activator.getContext().getBundle().findEntries("res", "9220.dat", false).nextElement();

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
		
		NMEA0183TrackFileProcessor nmea0183TrackFileProcessor = new NMEA0183TrackFileProcessor();
		nmea0183TrackFileProcessor.setMeasurementProcessor(iMeasurmentProcessor);
		nmea0183TrackFileProcessor.processFile(simpleTrackFile);
		
		assertFalse(measurements.isEmpty());
		assertEquals(4152, measurements.size());
		
		Measurement measurement = measurements.get(0);
		assertTrue(measurement instanceof Depth);
		Depth depth = (Depth) measurement;
		assertEquals("SD",depth.getSensorID());
		assertEquals(3.85,depth.getDepth(), 0.0001);
		
		measurement = measurements.get(1);
		assertTrue(measurement instanceof CompositeMeasurement);
		CompositeMeasurement compositeMeasurement = (CompositeMeasurement) measurement;
		assertEquals("GP", compositeMeasurement.getSensorID());
		List<Measurement> submeasurements = compositeMeasurement.getMeasurements();
		assertEquals(4, submeasurements.size());
		Measurement subOne = submeasurements.get(0);
		assertTrue(subOne instanceof GNSSMeasuredPosition);
		GNSSMeasuredPosition gnssMeasuredPosition = (GNSSMeasuredPosition) subOne;
		double altitude = gnssMeasuredPosition.getAltitude();assertEquals(0.0, gnssMeasuredPosition.getAltitude(), 0.0001);
		Latitude latitude = gnssMeasuredPosition.getLatitude();assertEquals(52.31036, latitude.getDecimalDegree(), 0.0001);
		Longitude longitude = gnssMeasuredPosition.getLongitude();assertEquals(10.306433333333333, longitude.getDecimalDegree(), 0.0001);
		Date time2 = gnssMeasuredPosition.getTime();
		assertEquals(1405256685000L, time2.getTime());
		assertEquals("UTC", gnssMeasuredPosition.getTimezone());

		Measurement subTwo = submeasurements.get(1);
		assertTrue(subTwo instanceof RelativeSpeed);
		RelativeSpeed relativeSpeed = (RelativeSpeed) subTwo;
		assertEquals("GP", relativeSpeed.getSensorID());
		assertEquals(1405256685000L, relativeSpeed.getTime().getTime());
		assertEquals(6.3, relativeSpeed.getValue().getSpeed(), 0.00001);
		assertEquals(SpeedUnit.N, relativeSpeed.getValue().getSpeedUnit());
		assertEquals("UTC", relativeSpeed.getTimezone());
		
		Measurement subThree = submeasurements.get(2);
		assertTrue(subThree instanceof Heading);
		Heading heading = (Heading) subThree;
		assertEquals(94.1, heading.getDegrees(), 0.00001);
		assertEquals("COG", heading.getHeadingType().getName());
		assertEquals("GP", heading.getSensorID());
		assertTrue(heading.isValid());
		assertEquals(1405256685000L, heading.getTime().getTime());
		assertEquals("UTC", heading.getTimezone());
		
		Measurement subFour = submeasurements.get(3);
		assertTrue(subFour instanceof Time);
		Time time = (Time) subFour;
		assertEquals(1405256685000L, time.getTime().getTime());
		assertEquals("UTC", time.getTimezone());

	}

}


