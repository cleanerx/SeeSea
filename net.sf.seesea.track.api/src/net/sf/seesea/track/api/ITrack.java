package net.sf.seesea.track.api;

import java.io.IOException;
import java.io.InputStream;

public interface ITrack {
	
	String getTrackQualifier();
	
	InputStream getInputStream() throws IOException;

	String getMimeType();
	
}
