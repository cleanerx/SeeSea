package net.sf.seesea.services.navigation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class StreamProcessorDetection {

	public static IStreamProcessor detectStreamProcessor(InputStream inputStream,
			Object[] services, boolean log) throws IOException,
			NMEAProcessingException {
		IStreamProcessor streamProcessor = null;
		int intialBytesToBeRead = 16384;
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
