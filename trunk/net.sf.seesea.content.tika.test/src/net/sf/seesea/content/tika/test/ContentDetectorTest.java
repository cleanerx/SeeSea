package net.sf.seesea.content.tika.test;

import org.easymock.EasyMock;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.seesea.content.api.ContentDetectionException;
import net.sf.seesea.content.impl.ContentDetector;
import net.sf.seesea.track.api.ITrackPersistence;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;

public class ContentDetectorTest {

	@Test
	public void testNothingToProcess() throws ContentDetectionException, TrackPerssitenceException {
		
		ITrackPersistence trackPersistence = EasyMock.createNiceMock(ITrackPersistence.class);
		List<ITrackFile> trackFiles = new ArrayList<ITrackFile>();
		EasyMock.expect(trackPersistence.getTrackFiles2Process()).andReturn(trackFiles);
		EasyMock.expectLastCall().times(1);
		EasyMock.replay(trackPersistence);
		
		ContentDetector contentDetector = new ContentDetector();
		
		contentDetector.bindTrackPersistence(trackPersistence);
		
		Map<String, Object> properties = new HashMap<String, Object>();
		contentDetector.activate(properties);
		contentDetector.setContentTypes();
	}

}
