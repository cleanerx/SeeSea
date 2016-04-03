package net.sf.seesea.track.api;

import java.util.List;

import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.exception.ProcessingException;

/**
 * A measurement processor retrieves measurements and may do arbitrary processing i.e. do statistics or filter the measurements.
 * The processor may receive all measurements at once or a single measurement at a time.
 * Consumers must call finish when no more processMeasurment calls appear so that the processor may flush its results, clean caches or fitler contents    
 */
public interface IMeasurmentProcessor {

	/**
	 * 
	 * @param results
	 * @param messageType
	 * @param sourceTrackIdentifier
	 * @param boundingBox
	 * @throws ProcessingException
	 */
	void processMeasurements(List<Measurement> results, String messageType, long sourceTrackIdentifier, GeoBoundingBox boundingBox) throws ProcessingException;

	/**
	 * 
	 * @throws ProcessingException
	 */
	public void finish() throws ProcessingException;

}
