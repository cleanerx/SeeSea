package net.sf.seesea.services.navigation;

import java.util.List;

import net.sf.seesea.model.core.physx.Measurement;

public interface IMeasurmentProcessor {
	
	void processMeasurements(List<Measurement> results, String messageType, long sourceTrackIdentifier) throws ProcessingException;

	public void finish() throws ProcessingException;

}
