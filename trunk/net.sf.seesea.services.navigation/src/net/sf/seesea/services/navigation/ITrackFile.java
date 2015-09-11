package net.sf.seesea.services.navigation;

import java.io.InputStream;
import java.util.Date;

import net.sf.seesea.model.core.geo.MeasuredPosition3D;

public interface ITrackFile {

	InputStream getInputStream()
			throws InputStreamNotFoundException;

	long getTrackId();

	CompressionType getCompression();
	
	IGeoBoundingBox getBoundingBox();

	boolean isHasAbsoluteTimedMeasurements();

	boolean isHasRelativeTimedMeasurements();

	Date getStartTime();

	Date getEndTime();

	String getFileType();

}