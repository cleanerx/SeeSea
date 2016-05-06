package net.sf.seesea.data.postprocessing.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Test;

import net.sf.seesea.data.postprocessing.process.FilterException;
import net.sf.seesea.data.postprocessing.process.IFilterController;
import net.sf.seesea.data.postprocessing.process.ITrackClustering;
import net.sf.seesea.data.postprocessing.process.TrackClusterResult;
import net.sf.seesea.track.api.ITrackPersistence;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;
import net.sf.seesea.track.model.SimpleTrackFile;

public class FilterEngineTest {


	@Test
	public void testSimpleEngineRun() throws TrackPerssitenceException, FilterException {
		Map<String, List<ITrackFile>> user2TrackFiles = new HashMap<String, List<ITrackFile>>();
		List<ITrackFile> trackFiles = new ArrayList<ITrackFile>();
		trackFiles.add(new SimpleTrackFile());
		user2TrackFiles.put("user", trackFiles);
		
		ITrackPersistence trackPersistence = EasyMock.createNiceMock(ITrackPersistence.class);
		EasyMock.expect(trackPersistence.getUser2PostprocessTrackCluster()).andReturn(user2TrackFiles);
		EasyMock.expectLastCall().anyTimes();
		trackPersistence.storePreprocessingStates(EasyMock.<List<ITrackFile>>anyObject());
		EasyMock.expectLastCall().times(1);
		EasyMock.replay(trackPersistence);

		IFilterController filterController = EasyMock.createNiceMock(IFilterController.class);
//		EasyMock.expect(trackPersistence.getUser2PostprocessTrackCluster()).andReturn(user2TrackFiles);
//		EasyMock.expectLastCall().anyTimes();
//		trackPersistence.storePreprocessingStates(EasyMock.<List<ITrackFile>>anyObject());
//		EasyMock.expectLastCall().times(1);
		EasyMock.replay(filterController);


		List<List<ITrackFile>> cluster = new ArrayList<>();
		cluster.add(trackFiles);
		TrackClusterResult trackClusterResult = new TrackClusterResult(cluster, Collections.<ITrackFile>emptySet(), Collections.<ITrackFile>emptySet(), Collections.<ITrackFile>emptySet(), Collections.<ITrackFile>emptySet());
		
		ITrackClustering trackClustering = EasyMock.createNiceMock(ITrackClustering.class);
		EasyMock.expect(trackClustering.classifyTracks(EasyMock.<List<ITrackFile>>anyObject())).andReturn(trackClusterResult);
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(trackClustering);
		
		FilterEngine filterEngine = new FilterEngine();
		filterEngine.bindTrackPersistence(trackPersistence);
		filterEngine.bindITrackClustering(trackClustering);
		filterEngine.bindFilterController(filterController);
		
		filterEngine.filterTracks();
		
		EasyMock.verify(trackPersistence, trackClustering, filterController);
		
	}
	
}
