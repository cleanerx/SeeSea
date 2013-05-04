package net.sf.seesea.services.navigation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;

public class StreamProcessorDetection {

	public static void getCompositeTrack(CompressionType compressionType, File file, Object[] services) throws ZipException, IOException {
		switch (compressionType) {
		case ZIP:
			ZipFile zipFile = new ZipFile(file);
			List<ZipEntry> zipEntries = new ArrayList<ZipEntry>();
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while(entries.hasMoreElements()) {
				ZipEntry nextElement = entries.nextElement();
				zipEntries.add(nextElement);
			}

			break;

		default:
			break;
		}
		
	}
	
	public static IStreamProcessor detectStreamProcessor(InputStream inputStream,
			Object[] services, boolean log) throws IOException,
			NMEAProcessingException {
		IStreamProcessor streamProcessor = null;
		int intialBytesToBeRead = 65536;
		int[] input = new int[intialBytesToBeRead];
		int x;
		int k = 0;
		if (log) {
			Logger.getLogger(StreamProcessorDetection.class).info(
					"Reading initial bytes for stream detection"); //$NON-NLS-1$
		}
		streamcheck: while ((x = inputStream.read()) != -1) {
			input[k] = x;
			k++;
			for (Object object : services) {
				IStreamProcessor currentStreamProcessor = (IStreamProcessor) object;
				if (currentStreamProcessor.isValidStreamProcessor(Arrays
						.copyOf(input, k))) {
					streamProcessor = currentStreamProcessor;
					if (log) {
						Logger.getLogger(StreamProcessorDetection.class)
								.info("Detected provider: " + streamProcessor.getClass().getSimpleName()); //$NON-NLS-1$
					}
					break streamcheck;
				}
			}
			if (k >= intialBytesToBeRead) {
				break;
			}
		}
		return streamProcessor;
	}

}
