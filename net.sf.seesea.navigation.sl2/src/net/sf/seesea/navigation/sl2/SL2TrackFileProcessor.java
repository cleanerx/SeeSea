package net.sf.seesea.navigation.sl2;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.services.navigation.IMeasurmentProcessor;
import net.sf.seesea.services.navigation.ITrackFile;
import net.sf.seesea.services.navigation.ITrackFileProcessor;
import net.sf.seesea.services.navigation.InputStreamNotFoundException;
import net.sf.seesea.services.navigation.NMEAProcessingException;
import net.sf.seesea.services.navigation.ProcessingException;
import net.sf.seesea.services.navigation.SensorDescriptionUpdateRate;
import net.sf.seesea.services.navigation.listener.IMeasurementListener;

import org.apache.log4j.Logger;

public class SL2TrackFileProcessor implements ITrackFileProcessor, IMeasurementListener {
	
	private SL2StreamProcessor sl2StreamProcessor;
	private IMeasurmentProcessor measurmentProcessor;
	private ITrackFile currentProcessedFile;

	public SL2TrackFileProcessor() {
		SL2Reader sl2Reader = new SL2Reader();
		sl2Reader.addMeasurementListener(this);
		sl2StreamProcessor = new SL2StreamProcessor();
		sl2StreamProcessor.addSL2Listener(sl2Reader);
		
	}

	@Override
	public void processFile(ITrackFile trackFile)
			throws FileNotFoundException, IOException, ProcessingException {
		this.currentProcessedFile = trackFile;
		BufferedInputStream input;
		try {
			input = new BufferedInputStream(trackFile.getInputStream());
			byte[] buffer = new byte[512];
			
			while (input.read(buffer) != -1) {
				for (int i = 0; i < buffer.length; i++) {
					int c = buffer[i] ;
					sl2StreamProcessor.readByte(c, "none");
				}
			}
		} catch (InputStreamNotFoundException e) {
			throw new ProcessingException(e);
		} catch (NMEAProcessingException e) {
			throw new ProcessingException(e);
		}

	}

	@Override
	public void setMeasurementProcessor(IMeasurmentProcessor measurmentProcessor) {
		this.measurmentProcessor = measurmentProcessor;
	}

	@Override
	public boolean hasTimedMeasurments() {
		return false;
	}

	@Override
	public void notify(List<Measurement> measurements) {
		try {
			measurmentProcessor.processMeasurements(measurements, null, currentProcessedFile.getTrackId(), currentProcessedFile.getBoundingBox());
		} catch (ProcessingException e) {
			Logger.getLogger(getClass()).error("Failed to process", e);
		}
	}

	@Override
	public void setFilter(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasRelativeTime() {
		return true;
	}

	@Override
	public boolean hasAbsoluteTime() {
		return false;
	}

}
