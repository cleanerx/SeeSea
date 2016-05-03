package net.sf.seesea.navigation.sl2;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Test;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.IMeasurementListener;

public class SL2ReaderTest {


	@Test
	public void testReadNoData() {
		SL2Reader sl2Reader = new SL2Reader();
		IMeasurementListener measurementListener = EasyMock.createNiceMock(IMeasurementListener.class);
		Capture<List<Measurement>> measurements = new Capture<List<Measurement>>();
		measurementListener.notify(EasyMock.capture(measurements));
		sl2Reader.addMeasurementListener(measurementListener);
		int[] data = new int[144];
		sl2Reader.notifySL2Block(data);
		assertFalse(measurements.hasCaptured());
	}
	
}
