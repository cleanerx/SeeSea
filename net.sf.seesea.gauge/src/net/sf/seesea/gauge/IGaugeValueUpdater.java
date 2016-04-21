package net.sf.seesea.gauge;

import java.util.List;

import net.sf.seesea.track.api.data.ITrackFile;

public interface IGaugeValueUpdater {

//	void retrieveLatestGaugeValues(Date startTime, Date endTime, long gaugeId) throws GaugeUpdateException;

	void updateGaugeValues4Track(List<ITrackFile> clusterOfTrackFiles) throws GaugeUpdateException;

	Long getGaugeId(long polygonId) throws GaugeUpdateException;

	
}
