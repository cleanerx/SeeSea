package net.sf.seesea.services.navigation;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.sf.seesea.track.api.data.ITrack;

public class ZippedTrack implements ITrack {

	private ZipFile zipFile;
	private ZipEntry key;
	private final String mimeType;

	public ZippedTrack(ZipFile zipFile, ZipEntry key, String mimeType) {
		this.zipFile = zipFile;
		this.key = key;
		this.mimeType = mimeType;
	}
	
	@Override
	public String getTrackQualifier() {
		return key.getName();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return zipFile.getInputStream(key);
	}

	@Override
	public String getMimeType() {
		return mimeType;
	}

}
