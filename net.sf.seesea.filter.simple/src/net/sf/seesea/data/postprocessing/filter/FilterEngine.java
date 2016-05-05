package net.sf.seesea.data.postprocessing.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import net.sf.seesea.data.postprocessing.process.FilterException;
import net.sf.seesea.data.postprocessing.process.IFilterController;
import net.sf.seesea.data.postprocessing.process.IFilterEngine;
import net.sf.seesea.data.postprocessing.process.ITrackClustering;
import net.sf.seesea.data.postprocessing.process.TrackClusterResult;
import net.sf.seesea.gauge.GaugeUpdateException;
import net.sf.seesea.gauge.IGaugeValueUpdater;
import net.sf.seesea.track.api.ITrackPersistence;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.ProcessingState;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;

@Component
public class FilterEngine implements IFilterEngine {

	private AtomicReference<ITrackPersistence> trackPersistenceAR = new AtomicReference<ITrackPersistence>();

	private AtomicReference<IFilterController> filterControllerAR = new AtomicReference<IFilterController>();

	private AtomicReference<IGaugeValueUpdater> gaugeValueUpdaterAR = new AtomicReference<IGaugeValueUpdater>();;

	private AtomicReference<ITrackClustering> trackClusteringAR = new AtomicReference<ITrackClustering>();;

	private List<Map<String, Object>> filterProperties = new ArrayList<Map<String, Object>>();

	// private Object filterProperties;

	public void activate(Map<String, Object> config) {
		preprocessRun = (boolean) config.get("preprocessRun");
		// filterProperties = config.get("filterProperties");
	}

	@Override
	public void filterTracks() {
		ITrackPersistence trackPersistence = trackPersistenceAR.get();
		// cluster the tracks per user
		// Set<String> whitelistUsers = new HashSet<String>();
		// Set<String> blacklistUsers = new HashSet<String>();
		Map<String, List<ITrackFile>> user2PostprocessTrackCluster = trackPersistence.getUser2PostprocessTrackCluster();
		for (Entry<String, List<ITrackFile>> user2TrackListEntry : user2PostprocessTrackCluster.entrySet()) {
			try {
				String user = user2TrackListEntry.getKey();
				List<ITrackFile> trackFiles = user2TrackListEntry.getValue();
				// FIXME set content type directly
				TrackClusterResult trackClusterResult;
				trackClusterResult = trackClustering.classifyTracks(trackFiles);

				// // FIXME: maybe a different method
				// if(preprocessRun) {
				// // update bounding box an internal processing states
				// trackPersistence.storePreprocessingStates(trackFiles);
				// }

				try {
					// those measurements that have no time need to be processed
					// separately
					for (ITrackFile abstractTrackFile : trackClusterResult.getNoTimeMeasurementFiles()) {
						filterController.setTimeout(60000);
						filterController.setFilterProperties(filterProperties);
						List<ITrackFile> singleTrackList = new ArrayList<ITrackFile>();
						singleTrackList.add(abstractTrackFile);
						filterController.process(singleTrackList, false);
						if (preprocessRun) {
							// mark as processed
							abstractTrackFile.setUploadState(ProcessingState.FILE_PROCESSED);
						}
					}
					trackPersistence.storePreprocessingStates(trackClusterResult.getNoTimeMeasurementFiles());
				} catch (TrackPerssitenceException | FilterException e1) {
					e1.printStackTrace();
				}

				// those measurements that logically form a single track that is
				// spread across several files
				for (List<ITrackFile> clusterOfTrackFiles : trackClusterResult.getOrderedTrackFiles()) {
					try {
						if (!clusterOfTrackFiles.isEmpty()) {
							List<ITrackFile> trackFilesCluster = new ArrayList<ITrackFile>(clusterOfTrackFiles);
							try {
								gaugeValueUpdater.updateGaugeValues4Track(trackFilesCluster);
							} catch (GaugeUpdateException e) {
								Logger.getLogger(getClass()).error("Failed to update gauge", e);
							}
							filterController.setTimeout(60000);
							filterController.setFilterProperties(filterProperties);
							filterController.process(clusterOfTrackFiles, true);
							for (ITrackFile iTrackFile : trackFilesCluster) {
								iTrackFile.setUploadState(ProcessingState.FILE_PROCESSED);
							}
						}

						if (preprocessRun) {
							// mark as processed
							trackPersistence.storePreprocessingStates(trackFiles);
						}
					} catch (FilterException | TrackPerssitenceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (FilterException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		}

	}

	private ITrackClustering trackClustering;

	private IFilterController filterController;

	private IGaugeValueUpdater gaugeValueUpdater;

	private boolean preprocessRun;

	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC)
	public void bindTrackPersistence(ITrackPersistence trackPersistence) {
		trackPersistenceAR.set(trackPersistence);
	}

	public void unbindTrackPersistence(ITrackPersistence trackPersistence) {
		trackPersistenceAR.compareAndSet(null, trackPersistence);
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC)
	public void bindFilterController(IFilterController trackPersistence) {
		filterControllerAR.set(trackPersistence);
	}

	public void unbindFilterController(IFilterController trackPersistence) {
		filterControllerAR.compareAndSet(null, trackPersistence);
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL, policy = ReferencePolicy.DYNAMIC)
	public void bindGaugeValueUpdater(IGaugeValueUpdater gaugeValueUpdater) {
		gaugeValueUpdaterAR.set(gaugeValueUpdater);
	}

	public void unbindGaugeValueUpdater(IGaugeValueUpdater gaugeValueUpdater) {
		gaugeValueUpdaterAR.compareAndSet(null, gaugeValueUpdater);
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL, policy = ReferencePolicy.DYNAMIC)
	public void bindITrackClustering(ITrackClustering gaugeValueUpdater) {
		trackClusteringAR.set(gaugeValueUpdater);
	}

	public void unbindITrackClustering(ITrackClustering gaugeValueUpdater) {
		trackClusteringAR.compareAndSet(null, gaugeValueUpdater);
	}

}
