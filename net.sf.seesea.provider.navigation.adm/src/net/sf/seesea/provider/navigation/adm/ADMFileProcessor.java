package net.sf.seesea.provider.navigation.adm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.services.navigation.IMeasurmentProcessor;
import net.sf.seesea.services.navigation.ITrackFile;
import net.sf.seesea.services.navigation.ITrackFileProcessor;
import net.sf.seesea.services.navigation.InputStreamNotFoundException;
import net.sf.seesea.services.navigation.ProcessingException;
import net.sf.seesea.services.navigation.SensorDescriptionUpdateRate;

public class ADMFileProcessor implements ITrackFileProcessor {

	private IMeasurmentProcessor measurmentProcessor;

	
	@Override
	public void processFile(ITrackFile trackFile)
			throws FileNotFoundException, IOException, ProcessingException {
		try {
			InputStream inputStream = trackFile.getInputStream();
		} catch (InputStreamNotFoundException e) {
			throw new ProcessingException(e);
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
		// TODO Auto-generated method stub
		
	}

}
