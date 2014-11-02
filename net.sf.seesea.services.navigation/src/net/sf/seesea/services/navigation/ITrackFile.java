package net.sf.seesea.services.navigation;

import java.io.InputStream;

public interface ITrackFile {

	InputStream getInputStream()
			throws InputStreamNotFoundException;

	long getTrackId();

	CompressionType getCompression();
	
	IGeoBoundingBox getBoundingBox();

	boolean isHasAbsoluteTimedMeasurements();

	boolean isHasRelativeTimedMeasurements();

}