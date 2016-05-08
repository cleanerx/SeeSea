package net.sf.seesea.track.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;

/**
 * this interface is being used persist the state of track analysis  
 */
public interface ITrackPersistence {

	/**
	 * resets the detected track data 
	 * @param trackIds
	 * @throws TrackPerssitenceException 
	 */
	void resetAnalyzedData() throws TrackPerssitenceException;

	/**
	 * Determines the track ids that need reprocessing. Due to configuration this may be even more that what was recently uploaded.
	 *  
	 * @return a list of track files that need content detection
	 * @throws TrackPerssitenceException if it unable to retrieve that data due to an IO error or internal problem
	 */
	List<ITrackFile> getTrackFiles2Process() throws TrackPerssitenceException;

	/**
	 * in new uploaded tracks -> out track clusters to be calculated 
	 * 
	 * @return a map of users to track files that are considered for data processing
	 * @throws TrackPerssitenceException if it unable to retrieve that data due to an IO error or internal problem
	 */
	Map<String, List<ITrackFile>> getUser2PostprocessTrackCluster() throws TrackPerssitenceException;


	/**
	 * stores the current state of track files to the persistent storage
	 * 
	 * @param trackFiles
	 * @throws TrackPerssitenceException
	 */
	void storePreprocessingStates(Collection<ITrackFile> trackFiles) throws TrackPerssitenceException;

}
