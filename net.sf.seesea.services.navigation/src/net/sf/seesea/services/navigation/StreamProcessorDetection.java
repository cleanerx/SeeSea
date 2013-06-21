package net.sf.seesea.services.navigation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;

public class StreamProcessorDetection {

	public static List<ITrack> getCompositeTrack(CompressionType compressionType, File file, Collection<IStreamProcessor> streamProcessors) throws ZipException, IOException, NMEAProcessingException {
		List<ITrack> tracks = new ArrayList<ITrack>();
		switch (compressionType) {
		case ZIP:
			Map<ZipFile, List<ZipEntry>> zip = new HashMap<ZipFile, List<ZipEntry>>();
			try {
				zip = getZipEntries(file,"UTF-8"); //$NON-NLS-1$
			} catch (IllegalArgumentException e) {
				Logger.getLogger(StreamProcessorDetection.class).error("Failed to open zip entry. May it is not UTF-8 encoded:" + file.getAbsolutePath());
				try {
					zip = getZipEntries(file,"ISO-8859-1"); //$NON-NLS-1$
				} catch (IllegalArgumentException e2) {
					Logger.getLogger(StreamProcessorDetection.class).error("Failed to open zip entry. May it is not ISO-8859-1 encoded:" + file.getAbsolutePath());
				}
			}

			// a multi track compression is at hand
			ZipFile zipFile = zip.entrySet().iterator().next().getKey();
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while(entries.hasMoreElements()) {
				ZipEntry nextElement = entries.nextElement();
				InputStream inputStream = zipFile.getInputStream(nextElement);
				IStreamProcessor streamProcessor = detectStreamProcessorEnblock(inputStream, streamProcessors.toArray(), false);
				if(streamProcessor != null)
				tracks.add(new ZippedTrack(zipFile, nextElement, streamProcessor.getMimeType()));
			}
			
			// a compression with several files make up the measurement
			for (IStreamProcessor processor : streamProcessors) {
				tracks.addAll(processor.getTracks(compressionType, file));
			}
			
			break;

		default:
			break;
		}
		return tracks;
	}
	
	public static IStreamProcessor detectStreamProcessorEnblock(InputStream inputStream,
			Object[] services, boolean log) throws IOException,
			NMEAProcessingException {
		IStreamProcessor streamProcessor = null;
		int intialBytesToBeRead = 65536 * 4;
		int[] input = new int[intialBytesToBeRead];
		int x;
		int k = 0;
		if (log) {
			Logger.getLogger(StreamProcessorDetection.class).info(
					"Reading initial bytes for stream detection"); //$NON-NLS-1$
		}
		while ((x = inputStream.read()) != -1) {
			input[k] = x;
			k++;
			if (k >= intialBytesToBeRead) {
				break;
			}
		}
		for (Object object : services) {
			IStreamProcessor currentStreamProcessor = (IStreamProcessor) object;
			if (currentStreamProcessor.isValidStreamProcessor(Arrays
					.copyOf(input, k))) {
				streamProcessor = currentStreamProcessor;
				if (log) {
					Logger.getLogger(StreamProcessorDetection.class)
							.info("Detected provider: " + streamProcessor.getClass().getSimpleName()); //$NON-NLS-1$
				}
			}
		}
		return streamProcessor;
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

	private static Map<ZipFile,List<ZipEntry>> getZipEntries(File file, String charset) throws IOException {
		ZipFile zipFile = new ZipFile(file, Charset.forName(charset));
		List<ZipEntry> zipEntries = new ArrayList<ZipEntry>();
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while(entries.hasMoreElements()) {
			ZipEntry nextElement = entries.nextElement();
			if(!nextElement.isDirectory()) {
				zipEntries.add(nextElement);
			}
		}
		Map<ZipFile, List<ZipEntry>> map = new HashMap<ZipFile, List<ZipEntry>>();
		map.put(zipFile, zipEntries);
		return map;
}

	
}
