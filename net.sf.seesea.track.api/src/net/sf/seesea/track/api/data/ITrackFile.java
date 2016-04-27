package net.sf.seesea.track.api.data;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.track.api.exception.InputStreamNotFoundException;

/**
 * This resembles a track file 
 *
 */
public interface ITrackFile {

	URL getTrackURL();
	
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

	void setFileType(String fileType);

	void setCompression(CompressionType compression);

	void setUploadState(ProcessingState uploadState);

	ProcessingState getUploadState();

	String getUsername();
	
	Collection<ITrackFile> getTrackFiles();

	void setBoundingBox(GeoBoundingBox boundingBox);

	void setStart(MeasuredPosition3D start);

	MeasuredPosition3D getEnd();

	void setEnd(MeasuredPosition3D end);

	MeasuredPosition3D getStart();

	void setFirstPositions(List<MeasuredPosition3D> firstTrackPoints);

	void setRelativeTimeMeasurements(boolean hasRelativeTimedMeasurements);

	void setAbsoluteTimeMeasurements(boolean hasAbsoluteTimedMeasurements);
	
	IBoatParameters getBoatParameters();

	void setBoatParameters(IBoatParameters boatParameters);
	
	String getTrackQualifier();

}