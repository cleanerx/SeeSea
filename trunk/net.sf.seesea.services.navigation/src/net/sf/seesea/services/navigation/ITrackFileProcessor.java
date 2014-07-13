package net.sf.seesea.services.navigation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import net.sf.seesea.model.core.physx.Measurement;

/**
 * a track file processor proceses the file by attaching listeners or other stuff to it
 * and every time a set of measurement is received it triggers the given measurement processor to process it.
 */
public interface ITrackFileProcessor {

	void processFile(ITrackFile trackFile) throws FileNotFoundException,
	IOException, ProcessingException;
	
	void setMeasurementProcessor(IMeasurmentProcessor measurmentProcessor);

	boolean hasTimedMeasurments();
	
	void setFilter(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors);
	
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
