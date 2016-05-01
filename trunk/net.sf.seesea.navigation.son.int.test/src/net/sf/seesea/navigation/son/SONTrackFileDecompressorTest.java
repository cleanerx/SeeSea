package net.sf.seesea.navigation.son;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

import net.sf.seesea.track.api.data.ITrackFile;

public class SONTrackFileDecompressorTest {

	public SONTrackFileDecompressorTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testExtractTrackFiles() throws IOException, URISyntaxException {
		URL nextElement = SONActivator.getContext().getBundle().findEntries("res", "9171.dat", true).nextElement();
		URL fileURL = FileLocator.resolve(nextElement);
		File file = new File(fileURL.getFile());
		ZipFile zipFile = new ZipFile(file);
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		List<ZipEntry> zipEntires = new ArrayList<ZipEntry>();
		while(entries.hasMoreElements()) {
			ZipEntry archiveEntry = entries.nextElement();
			zipEntires.add(archiveEntry);
		}
		
		SONTrackFileDecompressor sonTrackFIleDecompressor = new SONTrackFileDecompressor();
		List<ITrackFile> unzippedFiles = sonTrackFIleDecompressor.getUnzippedFiles(zipFile, zipEntires, "UTF-8");
		assertEquals("Expecting one track file", 1,unzippedFiles.size());
		ITrackFile trackFile = unzippedFiles.get(0);
		
		assertEquals("application/x-humminbird",trackFile.getFileType());
		assertEquals("R00016.DAT", trackFile.getTrackQualifier());
	}

}
