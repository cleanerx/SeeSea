package net.sf.seesea.gauge;

import java.util.Date;

public interface IGaugeValueUpdater {

	void retrieveLatestGaugeValues(Date startTime, Date endTime, int gaugeId) throws GaugeUpdateException;

	
}
