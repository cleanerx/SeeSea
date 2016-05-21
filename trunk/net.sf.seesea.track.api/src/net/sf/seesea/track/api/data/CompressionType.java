package net.sf.seesea.track.api.data;

/**
 * the type of compression and a map to the associated mime type 
 */
@SuppressWarnings("nls")
public enum CompressionType {

	ZIP("application/zip"), 
	TAR("application/x-tar"), 
	GZ("application/x-gzip"), 
	TARGZ("application/x-targz"), 
	RAR("application/x-rar"), 
	SEVEN_ZIP("application/x-7z"), 
	NONE(null);
	
	private final String mimeType;

	private CompressionType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getMimeType() {
		return mimeType;
	}
	
	public static CompressionType getCompressionType(String type) {
		if("application/zip".equals(type)) {
			return CompressionType.ZIP;
		} else if("application/x-tar".equals(type)) {
			return CompressionType.TAR;
		} else if("application/x-gzip".equals(type)) {
			return CompressionType.GZ;
		} else if("application/gzip".equals(type)) {
			return CompressionType.GZ;
		} else if("application/x-targz".equals(type)) {
			return CompressionType.TARGZ;
		} else if("application/x-rar".equals(type)) {
			return CompressionType.RAR;
		} else if("application/x-7z".equals(type)) {
			return CompressionType.SEVEN_ZIP;
		} else {
			return CompressionType.NONE;
		}
	}
	
}
