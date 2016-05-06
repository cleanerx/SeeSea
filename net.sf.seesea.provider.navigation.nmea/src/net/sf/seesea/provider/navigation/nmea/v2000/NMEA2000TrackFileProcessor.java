package net.sf.seesea.provider.navigation.nmea.v2000;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Component;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.ITrackFileProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.SensorDescriptionUpdateRate;
import net.sf.seesea.track.api.exception.InputStreamNotFoundException;
import net.sf.seesea.track.api.exception.ProcessingException;

@Component
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
		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.getLogger(getClass()).error("Failed to process track : " + trackFile.getTrackId() + ":");
		}
	}

	@Override
	public void nmeaEventReceived(int[] data)  {
		List<Measurement> measurements = nmea2000Reader
				.extractMeasurementsFromNMEA(data); 
		try {
			measurmentProcessor.processMeasurements(measurements, null, currentProcessedFile.getTrackId(), currentProcessedFile.getBoundingBox(), currentProcessedFile.getBoatParameters());
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

	@Override
	public boolean hasRelativeTime() {
		return true;
	}

	@Override
	public boolean hasAbsoluteTime() {
		return true;
	}

}
