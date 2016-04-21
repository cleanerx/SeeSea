package net.sf.seesea.track.api;

import java.util.List;

import net.sf.seesea.model.core.physx.Measurement;

public interface IMeasurementListener {
	
	void notify(List<Measurement> measurements);

}
