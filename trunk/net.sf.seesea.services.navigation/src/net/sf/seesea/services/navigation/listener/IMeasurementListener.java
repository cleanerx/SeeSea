package net.sf.seesea.services.navigation.listener;

import java.util.List;

import net.sf.seesea.model.core.physx.Measurement;

public interface IMeasurementListener {
	
	void notify(List<Measurement> measurements);

}
