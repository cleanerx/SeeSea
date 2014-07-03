package net.sf.seesea.provider.navigation.fsh;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.fsh.FSHReader;
import net.sf.seesea.provider.navigation.fsh.FSHStreamProcessor;
import net.sf.seesea.provider.navigation.fsh.FlobHeader;
import net.sf.seesea.provider.navigation.fsh.data.FSHBlock;
import net.sf.seesea.provider.navigation.fsh.data.FSHHeader;
import net.sf.seesea.services.navigation.IMeasurmentProcessor;
import net.sf.seesea.services.navigation.ITrackFile;
import net.sf.seesea.services.navigation.ITrackFileProcessor;
import net.sf.seesea.services.navigation.ProcessingException;
import net.sf.seesea.services.navigation.SensorDescriptionUpdateRate;

public class FSHTrackFileProcessor implements ITrackFileProcessor {

	private IMeasurmentProcessor measurmentProcessor;
	private FSHReader fshReader;
	
	public FSHTrackFileProcessor() {
		fshReader = new FSHReader();
	}

	@Override
	public void processFile(ITrackFile recordedFile) throws FileNotFoundException,
			IOException, ProcessingException {
		try {
			InputStream inputStream = recordedFile.getInputStream();
			FSHStreamProcessor fshStreamProcessor = new FSHStreamProcessor();
			FSHHeader fshHeader = fshStreamProcessor.readHeader(inputStream);
			for(int i = 0 ; i < 128 ; i++) {
				// dummy read the 28 bytes
				if(i > 0) {
					fshStreamProcessor.readHeader(inputStream);
				}
				FlobHeader flobHeader = fshStreamProcessor.readFlobHeader(inputStream);
				long bytesRead = 28 + 14;
				FSHBlock fshBlock = fshStreamProcessor.readBlock(inputStream);
				bytesRead += 14;
				while(fshBlock != null && fshBlock.getType() != 65535) {
					byte[] message = new byte[fshBlock.getLength()];
					int read = inputStream.read(message);
//					System.out.println(read);
					bytesRead += fshBlock.getLength();
					List<Measurement> measurements = fshReader.extractMeasurementsFromFSH(fshBlock, message);
					measurmentProcessor.processMeasurements(measurements, "none", recordedFile.getTrackId());
//					System.out.println("Type" + fshBlock.getType() + " : Length" + fshBlock.getLength() + " measurements : " + measurements.size() / 2);
					fshBlock = fshStreamProcessor.readBlock(inputStream);
					bytesRead += 14;
				}
//				System.out.println((1L << 20) - bytesRead);
				inputStream.skip((1L << 16) - bytesRead );
			}
		} catch (Exception e) {
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
	public void setFilter(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors) {
		// TODO Auto-generated method stub
		
	}

}
