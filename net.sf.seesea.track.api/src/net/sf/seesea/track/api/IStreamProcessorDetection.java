package net.sf.seesea.track.api;

import java.io.IOException;
import java.io.InputStream;

import net.sf.seesea.track.api.exception.RawDataEventException;

/**
 * 
 *
 */
public interface IStreamProcessorDetection {

	IStreamProcessor detectStreamProcessorEnblock(InputStream inputStream, boolean log)
			throws IOException, RawDataEventException;

	IStreamProcessor detectBinaryStreamProcessorEnblock(InputStream inputStream, boolean log)
			throws IOException, RawDataEventException;

	IStreamProcessor detectMimeTypeStreamProcessor(String mimeType);
}
