package net.sf.seesea.data.io;

import java.util.Collection;
import java.util.Date;

import net.sf.seesea.model.core.physx.Measurement;

/**
 * a generic interface to write measurement data
 */
public interface IDataWriter {

	/**
	 * 
	 * @param data write a single measurment
	 * @param valid true, if this measurment is valid, false if measurment contains an error
	 * @throws WriterException
	 */
	void write(Measurement data, boolean valid, long sourceTrackIdentifier) throws WriterException;

	/**
	 * 
	 * @param data write a single measurment
	 * @param valid true, if this measurment is valid, false if measurment contains an error
	 * @throws WriterException
	 */
	void write(Collection<Measurement> data, boolean valid, long sourceTrackIdentifier) throws WriterException;
	
	void closeOutput() throws WriterException;
	
	void write(double lat, double lon, double depth, long sourceTrackIdentifier, double latVariance, double lonVariance, double depthVariance, Date time) throws WriterException;

}
