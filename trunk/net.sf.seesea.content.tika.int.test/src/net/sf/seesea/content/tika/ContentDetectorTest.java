package net.sf.seesea.content.tika;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import net.sf.seesea.content.api.ContentDetectionException;
import net.sf.seesea.content.impl.ContentDetector;
import net.sf.seesea.track.api.ITrackPersistence;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;
import net.sf.seesea.track.model.SimpleTrackFile;

public class ContentDetectorTest {

	
	@Test
	public void testX() throws ContentDetectionException, TrackPerssitenceException {
		List<ITrackFile> tracks = new ArrayList<ITrackFile>();
		tracks.add(new SimpleTrackFile());
		
		ITrackPersistence trackPersistence = EasyMock.createNiceMock(ITrackPersistence.class);
		EasyMock.expect(trackPersistence.getTrackFiles2Process()).andReturn(tracks);
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(trackPersistence);
		
		ContentDetector contentDetector = new ContentDetector();
		contentDetector.bindTrackPersistence(trackPersistence);
//		contentDetector.activate(properties);
		contentDetector.setContentTypes();
	}

}
