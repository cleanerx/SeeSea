package net.sf.seesea.data.postprocessing.filter;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import net.sf.seesea.data.postprocessing.process.FilterException;
import net.sf.seesea.data.postprocessing.process.IDepthPositionPreProcessor;
import net.sf.seesea.data.postprocessing.process.IFileTypeProcessingFactory;
import net.sf.seesea.data.postprocessing.process.TrackClusterResult;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.model.SimpleTrackFile;

public class TimeBasedTrackClusteringTest {

	@Test
	public void testNoDepthDataClassify() throws FilterException {
		List<ITrackFile> trackFiles = new ArrayList<ITrackFile>();
		SimpleTrackFile simpleTrackFile = new SimpleTrackFile();
		trackFiles.add(simpleTrackFile);
		
		IDepthPositionPreProcessor locationPreProcessor = EasyMock.createNiceMock(IDepthPositionPreProcessor.class);
		EasyMock.expect(locationPreProcessor.hasDepthData()).andReturn(false);
		EasyMock.expectLastCall().times(1);
		EasyMock.replay(locationPreProcessor);
		
		IFileTypeProcessingFactory fileTypeProcessingFactory = EasyMock.createNiceMock(IFileTypeProcessingFactory.class);
		EasyMock.expect(fileTypeProcessingFactory.createLocationPreProcessor(EasyMock.<ITrackFile>anyObject())).andReturn(locationPreProcessor);
		EasyMock.replay(fileTypeProcessingFactory);
		
		TimeBasedTrackClustering timeBasedTrackClustering = new TimeBasedTrackClustering();
		timeBasedTrackClustering.bindFileTypeProcessingFactory(fileTypeProcessingFactory);
		
		TrackClusterResult classifyTracks = timeBasedTrackClustering.classifyTracks(trackFiles);
		assertFalse(classifyTracks.getNodataTrackFiles().isEmpty());
		
		
	}

	@Test
	public void testNoTimeDepthDataClassify() throws FilterException {
		List<ITrackFile> trackFiles = new ArrayList<ITrackFile>();
		SimpleTrackFile simpleTrackFile = new SimpleTrackFile();
		trackFiles.add(simpleTrackFile);
		
		GeoBoundingBox geoBoundingBox = EasyMock.mock(GeoBoundingBox.class);
		EasyMock.expect(geoBoundingBox.getTop()).andReturn(45.0);
		EasyMock.expect(geoBoundingBox.getBottom()).andReturn(30.0);
		EasyMock.expect(geoBoundingBox.getLeft()).andReturn(10.0);
		EasyMock.expect(geoBoundingBox.getRight()).andReturn(20.0);
		
		IDepthPositionPreProcessor locationPreProcessor = EasyMock.createNiceMock(IDepthPositionPreProcessor.class);
		EasyMock.expect(locationPreProcessor.getBoundingBox()).andReturn(geoBoundingBox);
		EasyMock.expectLastCall().times(1);
		EasyMock.expect(locationPreProcessor.hasDepthData()).andReturn(true);
		EasyMock.expectLastCall().times(1);
		EasyMock.expect(locationPreProcessor.getPointCount()).andReturn(100L);
		EasyMock.expectLastCall().times(1);
		EasyMock.replay(locationPreProcessor);
		
		IFileTypeProcessingFactory fileTypeProcessingFactory = EasyMock.createNiceMock(IFileTypeProcessingFactory.class);
		EasyMock.expect(fileTypeProcessingFactory.createLocationPreProcessor(EasyMock.<ITrackFile>anyObject())).andReturn(locationPreProcessor);
		EasyMock.replay(fileTypeProcessingFactory);
		
		TimeBasedTrackClustering timeBasedTrackClustering = new TimeBasedTrackClustering();
		timeBasedTrackClustering.bindFileTypeProcessingFactory(fileTypeProcessingFactory);
		
		TrackClusterResult classifyTracks = timeBasedTrackClustering.classifyTracks(trackFiles);
		assertFalse(classifyTracks.getNoTimeMeasurementFiles().isEmpty());
		
		
	}
}
