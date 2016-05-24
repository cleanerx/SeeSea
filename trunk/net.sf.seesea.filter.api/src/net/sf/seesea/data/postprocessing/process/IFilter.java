package net.sf.seesea.data.postprocessing.process;

import net.sf.seesea.track.api.IMeasurmentProcessor;

public interface IFilter extends IMeasurmentProcessor {

	boolean requiresAbsoluteTime();

	boolean requiresRelativeTime();

}
