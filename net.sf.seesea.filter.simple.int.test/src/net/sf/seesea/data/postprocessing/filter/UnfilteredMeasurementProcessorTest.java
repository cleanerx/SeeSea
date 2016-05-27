package net.sf.seesea.data.postprocessing.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;
import net.sf.seesea.data.io.MemoryDataWriter;
import net.sf.seesea.data.io.WriterException;
import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.exception.ProcessingException;

public class UnfilteredMeasurementProcessorTest {

	@Test
	public void testProcessEmptyMeasurments() throws ProcessingException {

		List<Measurement> measurements = new ArrayList<>();
		UnfilteredMeasurementProcessor unfilteredMeasurementProcessor = new UnfilteredMeasurementProcessor();
		unfilteredMeasurementProcessor.processMeasurements(measurements, "Pos", 1L, null, null);
		
		return;
		
	}

	@Test
	public void testProcessSingleMeasurments() throws ProcessingException, WriterException {
		List<Measurement> measurements = new ArrayList<>();
		GeoFactory geoFactory = GeoFactory.eINSTANCE;
		
		MeasuredPosition3D measuredPosition3D = geoFactory.createMeasuredPosition3D();
		Latitude latitude = geoFactory.createLatitude();
		latitude.setDecimalDegree(10);
		measuredPosition3D.setLatitude(latitude);
		Longitude longitude = geoFactory.createLongitude();
		longitude.setDecimalDegree(54.0);
		measuredPosition3D.setLongitude(longitude);
		measuredPosition3D.setValid(true);
		
		Depth depth = geoFactory.createDepth();
		depth.setValid(true);
		measurements.add(measuredPosition3D);
		measurements.add(depth);
		
		MemoryDataWriter memoryDataWriter = new MemoryDataWriter();
		
		IDataWriter writer = EasyMock.createNiceMock(IDataWriter.class);
		EasyMock.replay(writer);
		
		UnfilteredMeasurementProcessor unfilteredMeasurementProcessor = new UnfilteredMeasurementProcessor();
		unfilteredMeasurementProcessor.bindWriter(writer);
		
		unfilteredMeasurementProcessor.processMeasurements(measurements, "Pos", 1L, null, null);
		unfilteredMeasurementProcessor.finish();
		
		List<Measurement> writtenMeasurements = memoryDataWriter.getMeasurements();
		assertEquals(2, writtenMeasurements.size());
		assertTrue(writtenMeasurements.contains(measuredPosition3D));
		assertTrue(writtenMeasurements.contains(depth));
		
	}

}
