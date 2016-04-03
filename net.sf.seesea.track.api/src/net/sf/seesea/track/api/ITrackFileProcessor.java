package net.sf.seesea.track.api;

import java.io.IOException;
import java.util.Set;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.data.SensorDescriptionUpdateRate;
import net.sf.seesea.track.api.exception.ProcessingException;

/**
 * a track file processor proceses the file by attaching listeners or other stuff to it
 * and every time a set of measurement is received it triggers the given measurement processor to process it.
 */
public interface ITrackFileProcessor {

	/**
	 * This method processes a track file. It handles opening of a track file and extracting the initial contents and measurements.
	 * It must pass the contained measurements to the measurement processor. 
	 * 
	 * @param trackFile the track file to process
	 * @throws IOException if something with reading from the track file went wrong
	 * @throws ProcessingException if the processing failed due to an abnormal condition
	 */
	void processFile(ITrackFile trackFile) throws IOException, ProcessingException;

	/**
	 * sets the measruement processor
	 * 
	 * @param measurmentProcessor
	 */
	void setMeasurementProcessor(IMeasurmentProcessor measurmentProcessor);

	/**
	 * the filter may be used to choose a particular sensor or channel in the measurement processor if there is more than one sensor or data available.
	 * @param bestSensors
	 */
	void setFilter(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors);

	/**
	 * 
	 * @return true if the format has a time base. This may be used to filter for timed events
	 */
	boolean hasTimedMeasurments();
	
	/**
	 * @return true if the format does have a consecutive time base which is usually starting at the time of recording  
	 */
	boolean hasRelativeTime();
	
	/**
	 * 
	 * @return true if the format does support a global time base according to UTC
	 */
	boolean hasAbsoluteTime();
}
