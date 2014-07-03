package net.sf.seesea.provider.navigation.nmea.v2000;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.nmea.v2000.ActisenseStreamProcessor;
import net.sf.seesea.provider.navigation.nmea.v2000.INMEA2000Listener;
import net.sf.seesea.provider.navigation.nmea.v2000.NMEA2000Reader;
import net.sf.seesea.services.navigation.IMeasurmentProcessor;
import net.sf.seesea.services.navigation.ITrackFile;
import net.sf.seesea.services.navigation.ITrackFileProcessor;
import net.sf.seesea.services.navigation.InputStreamNotFoundException;
import net.sf.seesea.services.navigation.ProcessingException;
import net.sf.seesea.services.navigation.SensorDescriptionUpdateRate;

import org.apache.log4j.Logger;

public class NMEA2000TrackFileProcessor implements ITrackFileProcessor,
		INMEA2000Listener {

	private NMEA2000Reader nmea2000Reader;
	private ActisenseStreamProcessor actisenseStreamProcessor;
	private IMeasurmentProcessor measurmentProcessor;
	private ITrackFile currentProcessedFile;

	public NMEA2000TrackFileProcessor() {
		Set<Integer> pgns = new HashSet<Integer>();
		pgns.add(129029);
		pgns.add(128267);
		nmea2000Reader = new NMEA2000Reader(pgns);
		actisenseStreamProcessor = new ActisenseStreamProcessor();
		actisenseStreamProcessor.addNMEA2000Listener(this);
	}

	public void processFile(ITrackFile trackFile) throws FileNotFoundException,
			IOException, ProcessingException {

		this.currentProcessedFile = trackFile;
		BufferedInputStream input;
		try {
			input = new BufferedInputStream(trackFile.getInputStream());
			byte[] buffer = new byte[512];
			
			while (input.read(buffer) != -1) {
				for (int i = 0; i < buffer.length; i++) {
					int c = buffer[i] & 0xFF;
					actisenseStreamProcessor.readByte(c, "none");
				}
			}
		} catch (InputStreamNotFoundException e) {
			throw new ProcessingException(e);
		}
	}

	@Override
	public void nmeaEventReceived(int[] data)  {
		List<Measurement> measurements = nmea2000Reader
				.extractMeasurementsFromNMEA(data); 
		try {
			measurmentProcessor.processMeasurements(measurements, null, currentProcessedFile.getTrackId());
		} catch (ProcessingException e) {
			Logger.getLogger(getClass()).error("Failed to process", e);
		}
	}

	@Override
	public void setMeasurementProcessor(IMeasurmentProcessor measurmentProcessor) {
		this.measurmentProcessor = measurmentProcessor;
		
	}

	@Override
	public boolean hasTimedMeasurments() {
		return true;
	}

	@Override
	public void setFilter(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors) {
		
	}

}
