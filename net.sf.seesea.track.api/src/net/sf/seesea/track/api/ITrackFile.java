package net.sf.seesea.track.api;

import java.io.InputStream;
import java.util.Date;

import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.track.api.data.CompressionType;
import net.sf.seesea.track.api.exception.InputStreamNotFoundException;

/**
 * This resembles a track file 
 *
 */
public interface ITrackFile {

	/**
	 * 
	 * @return an input stream to read from the track file
	 * @throws InputStreamNotFoundException
	 */
	InputStream getInputStream()
			throws InputStreamNotFoundException;

	/**
	 * a unique track id 
	 */
	long getTrackId();

	/**
	 * the compression of this track file 
	 */
	CompressionType getCompression();
	
	/**
	 * the bounding box in lat / lon coordinates
	 * @return
	 */
	GeoBoundingBox getBoundingBox();

	/**
	 * 
	 * @return true if this track as UTC or timezone measurements
	 */
	boolean isHasAbsoluteTimedMeasurements();

	/**
	 * 
	 * @return true if track as relative time measurements i.e. since the start of a recording
	 */
	boolean isHasRelativeTimedMeasurements();

	/**
	 * @return the start time of the track - relative or absolute
	 */
	Date getStartTime();

	/**
	 * @return the end time of the track - relative or absolute
	 */
	Date getEndTime();

	/**
	 * 
	 * @return the mime type of the track file
	 */
	String getFileType();

}