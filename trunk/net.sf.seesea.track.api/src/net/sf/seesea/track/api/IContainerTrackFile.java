package net.sf.seesea.track.api;

import java.util.List;

/**
 * A container track file contains other track files. This may be a track file that is compressed and contains other tracks
 */
public interface IContainerTrackFile extends ITrackFile {

	/**
	 * 
	 * @return the contained track file
	 */
	List<ITrackFile> getTrackFiles();
	
}
