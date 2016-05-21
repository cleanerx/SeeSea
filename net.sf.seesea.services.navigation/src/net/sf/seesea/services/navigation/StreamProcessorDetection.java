package net.sf.seesea.services.navigation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import net.sf.seesea.track.api.IStreamProcessor;
import net.sf.seesea.track.api.IStreamProcessorDetection;
import net.sf.seesea.track.api.exception.RawDataEventException;

@Component
public class StreamProcessorDetection implements IStreamProcessorDetection {

	private Map<IStreamProcessor, Map<String,Object>> processors = new ConcurrentHashMap<IStreamProcessor, Map<String,Object>>();
	
	@Activate
	public void activate(Map<String,Object> properties) {
		
	}
	
	public IStreamProcessor detectStreamProcessorEnblock(InputStream inputStream, boolean log) throws IOException, RawDataEventException {
		return detectStreamProcessorEnblock(inputStream, processors.keySet(), log);
	}

	public IStreamProcessor detectBinaryStreamProcessorEnblock(InputStream inputStream, boolean log)
			throws IOException, RawDataEventException {
		List<IStreamProcessor> binaryProcessors = new ArrayList<IStreamProcessor>();
		for (IStreamProcessor streamProcessor : processors.keySet()) {
			if(streamProcessor.isBinary()) {
				binaryProcessors.add(streamProcessor);
			}
		}
		return detectStreamProcessorEnblock(inputStream, binaryProcessors, log);
	}

	private IStreamProcessor detectStreamProcessorEnblock(InputStream inputStream, Collection<IStreamProcessor> services, boolean log)
			throws IOException, RawDataEventException {
		IStreamProcessor streamProcessor = null;
		int intialBytesToBeRead = 65536 * 4;
		int[] input = new int[intialBytesToBeRead];
		int x;
		int k = 0;
		if (log) {
			Logger.getLogger(StreamProcessorDetection.class).info("Reading initial bytes for stream detection"); //$NON-NLS-1$
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
			if (currentStreamProcessor.isValidStreamProcessor(Arrays.copyOf(input, k))) {
				streamProcessor = currentStreamProcessor;
				if (log) {
					Logger.getLogger(StreamProcessorDetection.class)
							.info("Detected provider: " + streamProcessor.getClass().getSimpleName()); //$NON-NLS-1$
				}
				return streamProcessor;
			}
		}
		return streamProcessor;
	}

	private IStreamProcessor detectStreamProcessor(InputStream inputStream, Object[] services, boolean log)
			throws IOException, RawDataEventException {
		IStreamProcessor streamProcessor = null;
		int intialBytesToBeRead = 65536;
		int[] input = new int[intialBytesToBeRead];
		int x;
		int k = 0;
		if (log) {
			Logger.getLogger(StreamProcessorDetection.class).info("Reading initial bytes for stream detection"); //$NON-NLS-1$
		}
		streamcheck: while ((x = inputStream.read()) != -1) {
			input[k] = x;
			k++;
			for (Object object : services) {
				IStreamProcessor currentStreamProcessor = (IStreamProcessor) object;
				if (currentStreamProcessor.isValidStreamProcessor(Arrays.copyOf(input, k))) {
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

	@Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MULTIPLE)
	public void bindStreamProcessor(IStreamProcessor streamProcessor, Map<String, Object> properties) {
		processors.put(streamProcessor, properties);
	}

	@Override
	public IStreamProcessor detectMimeTypeStreamProcessor(String mimeType) {
		if(mimeType != null) {
			for (IStreamProcessor streamProcessor : processors.keySet()) {
				if(mimeType.equals(streamProcessor.getMimeType())) {
					return streamProcessor;
				}
			}
		}
		return null;
	}

}
