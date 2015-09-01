package net.sf.seesea.gauge;

import java.util.List;

import net.sf.seesea.services.navigation.ITrackFile;

public interface IGaugeValueUpdater {

//	void retrieveLatestGaugeValues(Date startTime, Date endTime, long gaugeId) throws GaugeUpdateException;

	void updateGaugeValues4Track(List<ITrackFile> clusterOfTrackFiles) throws GaugeUpdateException;

	
}
