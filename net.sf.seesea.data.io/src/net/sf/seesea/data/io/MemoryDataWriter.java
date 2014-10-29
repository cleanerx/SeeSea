package net.sf.seesea.data.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.sf.seesea.model.core.physx.Measurement;

public class MemoryDataWriter implements IDataWriter {

	private List<Measurement> measurements;

	public MemoryDataWriter() {
		measurements = new ArrayList<Measurement>();
	}
	
	
	@Override
	public void write(Measurement data, boolean valid, long sourceTrackIdentifier) {
		measurements.add(data);
	}

	@Override
	public void write(Collection<Measurement> data, boolean valid, long sourceTrackIdentifier) {
		measurements.addAll(data);
	}

	@Override
	public void closeOutput() {
		// TODO Auto-generated method stub

	}


	protected List<Measurement> getMeasurements() {
		return measurements;
	}


	@Override
	public void write(double lat, double lon, double depth, long sourceTrackIdentifier, double latVariance, double lonVariance, double depthVariance, Date time)
			throws WriterException {
		System.out.println("lat:" + lat + ":" + latVariance + " lon:" + lon + ":" + lonVariance);
	}
	
	

}
