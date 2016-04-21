package net.sf.seesea.track.api;

import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.sf.seesea.track.api.data.ITrackFile;

/**
 * this is a format specific decompressor. 
 *
 */
public interface ITrackFileDecompressor {

//	Collection<ITrackFile> getTracks(ITrackFile containerTrackFile);

	List<ITrackFile> getUnzippedFiles(ZipFile file, List<ZipEntry> zipEntries, String encoding); 
	
}
