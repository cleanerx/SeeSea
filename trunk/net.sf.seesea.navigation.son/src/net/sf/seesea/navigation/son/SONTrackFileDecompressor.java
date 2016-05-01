package net.sf.seesea.navigation.son;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Component;

import net.sf.seesea.navigation.son.data.ZippedSonTrack;
import net.sf.seesea.track.api.ITrackFileDecompressor;
import net.sf.seesea.track.api.data.CompressionType;
import net.sf.seesea.track.api.data.ITrackFile;

@Component
public class SONTrackFileDecompressor implements ITrackFileDecompressor {

	public SONTrackFileDecompressor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ITrackFile> getUnzippedFiles(ZipFile file, List<ZipEntry> zipEntries, String encoding) {
		List<ITrackFile> tracks = new ArrayList<ITrackFile>();
		List<ITrackFile> sonFilesFromFile = getSonFilesFromFile(file, tracks, zipEntries, encoding);
		
		return sonFilesFromFile;
	}
	
//	public List<ITrack> getTracks(CompressionType compression, ZipFile zipFile) {
//		List<ITrack> tracks = new ArrayList<ITrack>(); 
//		List<ZipEntry> zipEntries = new ArrayList<ZipEntry>(1);
//		zipEntries = getZipEntries(zipFile);
//		return getSonFilesFromFile(zipFile, tracks, zipEntries, "ISO-8859-1");
//	}

//	@Override
	public List<ITrackFile> getTracks(CompressionType compressionType, File file) throws ZipException, IOException {
		List<ITrackFile> tracks = new ArrayList<ITrackFile>();
		ZipFile zipFile = null;
		switch (compressionType) {
		case ZIP:
			List<ZipEntry> zipEntries = new ArrayList<ZipEntry>(1);
			String encoding = null;
			try {
				zipFile = new ZipFile(file, Charset.forName("UTF-8"));
				zipEntries = getZipEntries(zipFile); //$NON-NLS-1$
				encoding = "UTF-8";
			} catch (IllegalArgumentException e) {
				Logger.getLogger(this.getClass()).error("Failed to open zip entry. May it is not UTF-8 encoded:" + file.getAbsolutePath());
				try {
					zipFile = new ZipFile(file, Charset.forName("ISO-8859-1"));
					zipEntries = getZipEntries(zipFile); //$NON-NLS-1$
					encoding = "ISO-8859-1"; //$NON-NLS-1$
				} catch (IllegalArgumentException e2) {
					Logger.getLogger(this.getClass()).error("Failed to open zip entry. May it is not ISO-8859-1 encoded:" + file.getAbsolutePath());
					return Collections.emptyList();
				}
			}

			return getSonFilesFromFile(zipFile, tracks, zipEntries, encoding);

		default:
			break;
		}
		return tracks;
	}

	private List<ITrackFile> getSonFilesFromFile(ZipFile file, List<ITrackFile> tracks,
			List<ZipEntry> zipEntries, String encoding) {
		Map<ZipEntry, Map<ZipEntry,ZipEntry>> sonFiles = new HashMap<ZipEntry, Map<ZipEntry,ZipEntry>>();
		for (ZipEntry zipEntry : zipEntries) {
			// root file
			if(zipEntry.getName().toLowerCase().endsWith(".dat")) {
				Map<String,ZipEntry> nameMap = new HashMap<String, ZipEntry>();
				String directory = zipEntry.getName().substring(0, zipEntry.getName().length() - 4) + "/"; //$NON-NLS-1$
				Map<ZipEntry,ZipEntry> index2Dat = new HashMap<ZipEntry,ZipEntry>();
				for (ZipEntry zipEntry2 : zipEntries) {
					if(zipEntry2.getName().startsWith(directory) && zipEntry2.getName().toLowerCase().endsWith(".idx")) {
						String indexFile = zipEntry2.getName().substring(zipEntry2.getName().lastIndexOf("/") + 1, zipEntry2.getName().length() - 4); //$NON-NLS-1$
						if(nameMap.get(indexFile) == null) {
							nameMap.put(indexFile, zipEntry2);
						} else {
							index2Dat.put(zipEntry2, nameMap.get(indexFile));
						}
					} else if(zipEntry2.getName().startsWith(directory) && (zipEntry2.getName().toLowerCase().endsWith(".son") || zipEntry2.getName().toLowerCase().endsWith(".dri"))) {
						String indexFile = zipEntry2.getName().substring(zipEntry2.getName().lastIndexOf("/") + 1, zipEntry2.getName().length() - 4); //$NON-NLS-1$
						if(nameMap.get(indexFile) == null) {
							nameMap.put(indexFile, zipEntry2);
						} else {
							// TODO check son integrity
							index2Dat.put(nameMap.get(indexFile), zipEntry2);
						}
					}
				}	
				sonFiles.put(zipEntry, index2Dat);
			}
				
		}
		if(!sonFiles.values().isEmpty()) {
			for (Entry<ZipEntry, Map<ZipEntry, ZipEntry>> zipEntry : sonFiles.entrySet()) {
				if(!zipEntry.getValue().isEmpty()) {
					tracks.add(new ZippedSonTrack(file, zipEntry.getKey(), zipEntry.getValue(), encoding));
				}
			}
		}
		return tracks;
	}

private List<ZipEntry> getZipEntries(ZipFile zipFile) {
	List<ZipEntry> zipEntries = new ArrayList<ZipEntry>();
	Enumeration<? extends ZipEntry> entries = zipFile.entries();
	while(entries.hasMoreElements()) {
		ZipEntry nextElement = entries.nextElement();
		if(!nextElement.isDirectory()) {
			zipEntries.add(nextElement);
		}
	}
	return zipEntries;
}


}
