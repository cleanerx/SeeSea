package net.sf.seesea.track.persistence.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.Ignore;
import org.junit.Test;

import net.sf.seesea.track.api.data.CompressionType;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.ProcessingState;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;
import net.sf.seesea.track.model.SimpleTrackFile;

public class DatabaseTrackPersistenceTest {

	@Test
	public void testSingleTrack() throws TrackPerssitenceException, SQLException, IOException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");

		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false).nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try (Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
			try(FileReader fileReader = new FileReader(file)) {
				ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
				scriptRunner.runScript(fileReader);
			}

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			databaseTrackPersistence.activate(properties);

			List<ITrackFile> trackFiles2Process = databaseTrackPersistence.getTrackFiles2Process();
			assertEquals(1, trackFiles2Process.size());
			ITrackFile trackFile = trackFiles2Process.get(0);
			assertEquals(1L, trackFile.getTrackId());
			assertTrue(trackFile.getTrackQualifier().endsWith("/000/1.dat"));
			assertEquals("test@test.de", trackFile.getUsername());
		}

	}

	@Test
	public void testBlacklistUser() throws TrackPerssitenceException, SQLException, IOException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");

		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false).nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try (Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
			try(FileReader fileReader = new FileReader(file)) {
				ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
				scriptRunner.runScript(fileReader);
			}

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			properties.put("blacklistUsers", "test@test.de");
			databaseTrackPersistence.activate(properties);

			List<ITrackFile> trackFiles2Process = databaseTrackPersistence.getTrackFiles2Process();
			assertEquals(0, trackFiles2Process.size());

		}
	}

	@Test
	public void testWhitelistUser() throws TrackPerssitenceException, SQLException, IOException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");

		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false).nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try (Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
			try(FileReader fileReader = new FileReader(file)) {
				ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
				scriptRunner.runScript(fileReader);
			}

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			properties.put("whitelistUsers", "notexistent@test.de");
			databaseTrackPersistence.activate(properties);

			List<ITrackFile> trackFiles2Process = databaseTrackPersistence.getTrackFiles2Process();
			assertEquals(0, trackFiles2Process.size());
		}
	}

	@Test
	public void testFilterId() throws TrackPerssitenceException, SQLException, IOException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");

		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false).nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try (Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
			try(FileReader fileReader = new FileReader(file)) {
				ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
				scriptRunner.runScript(fileReader);
			}

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			properties.put("processTrackIds", "1");
			databaseTrackPersistence.activate(properties);

			List<ITrackFile> trackFiles2Process = databaseTrackPersistence.getTrackFiles2Process();
			assertEquals(1, trackFiles2Process.size());

			properties.put("processTrackIds", "2");
			databaseTrackPersistence.activate(properties);
			trackFiles2Process = databaseTrackPersistence.getTrackFiles2Process();
			assertEquals(0, trackFiles2Process.size());

		}
	}

	@Test
	public void testDelete() throws TrackPerssitenceException, IOException, SQLException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");

		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "deletetest.sql", false)
				.nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try (Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
			try(FileReader fileReader = new FileReader(file)) {
				ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
				scriptRunner.runScript(fileReader);
			}

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			databaseTrackPersistence.activate(properties);

			databaseTrackPersistence.resetAnalyzedData();
			
			try (PreparedStatement statement = c.prepareStatement("SELECT upload_state FROM user_tracks WHERE track_id = 1");
					ResultSet set = statement.executeQuery()) {
				set.next();
				int uploadState = set.getInt(1);
				assertEquals(1, uploadState);
			}

			try (PreparedStatement statement = c.prepareStatement("SELECT upload_state FROM user_tracks WHERE track_id = 2");
					ResultSet set = statement.executeQuery()) {
				boolean next = set.next();
				assertFalse("Container track must have been deleted", next);
			}
		}
	}
	
	@Test
	public void testStoreStates() throws TrackPerssitenceException, IOException, SQLException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");

		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false)
				.nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try (Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
			try(FileReader fileReader = new FileReader(file)) {
				ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
				scriptRunner.runScript(fileReader);
			}

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			databaseTrackPersistence.activate(properties);

			List<ITrackFile> files = new ArrayList<ITrackFile>();
			SimpleTrackFile simpleTrackFile = new SimpleTrackFile();
			simpleTrackFile.setTrackId(1L);
			simpleTrackFile.setUploadState(ProcessingState.PREPROCESSED);
			simpleTrackFile.setCompression(CompressionType.ZIP);
			simpleTrackFile.setFileType("application/myOwnFormat");
			files.add(simpleTrackFile);
			databaseTrackPersistence.storePreprocessingStates(files);
			
			try (PreparedStatement statement = c.prepareStatement("SELECT upload_state, filetype, compression FROM user_tracks WHERE track_id = 1");
					ResultSet set = statement.executeQuery()) {
				set.next();
				int uploadState = set.getInt(1);
				String fileType = set.getString(2);
				String compression = set.getString(3);
				assertEquals(ProcessingState.PREPROCESSED.ordinal(), uploadState);
				assertEquals("application/myOwnFormat", fileType);
				assertEquals(CompressionType.ZIP.getMimeType(), compression);
			}
		}
	}
	
	@Test
	public void testCompositeStoreStates() throws TrackPerssitenceException, IOException, SQLException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");

		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false)
				.nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try (Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
			try(FileReader fileReader = new FileReader(file)) {
				ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
				scriptRunner.runScript(fileReader);
			}
				
			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			databaseTrackPersistence.activate(properties);

			List<ITrackFile> files = new ArrayList<ITrackFile>();
			SimpleTrackFile simpleTrackFile = new SimpleTrackFile();
			simpleTrackFile.setTrackId(1L);
			simpleTrackFile.setUploadState(ProcessingState.PREPROCESSED);
			simpleTrackFile.setCompression(CompressionType.ZIP);
			simpleTrackFile.setFileType(null);
			files.add(simpleTrackFile);
			
			SimpleTrackFile simpleTrackFileContained = new SimpleTrackFile();
			simpleTrackFileContained.setTrackId(2L);
			simpleTrackFileContained.setUploadState(ProcessingState.PREPROCESSED);
			simpleTrackFileContained.setCompression(CompressionType.NONE);
			simpleTrackFileContained.setFileType("application/myOwnFormat");
			simpleTrackFile.getTrackFiles().add(simpleTrackFileContained);
			
			databaseTrackPersistence.storePreprocessingStates(files);
			
			try (PreparedStatement statement = c.prepareStatement("SELECT upload_state, filetype, compression FROM user_tracks WHERE track_id = 1");
					ResultSet set = statement.executeQuery()) {
				set.next();
				int uploadState = set.getInt(1);
				String fileType = set.getString(2);
				String compression = set.getString(3);
				assertEquals(ProcessingState.PREPROCESSED.ordinal(), uploadState);
				assertNull(fileType);
				assertEquals(CompressionType.ZIP.getMimeType(), compression);
			}
//			try (PreparedStatement statement = c.prepareStatement("SELECT upload_state, filetype, compression FROM user_tracks WHERE track_id = 2");
//					ResultSet set = statement.executeQuery()) {
//				set.next();
//				int uploadState = set.getInt(1);
//				String fileType = set.getString(2);
//				String compression = set.getString(3);
//				assertEquals(ProcessingState.PREPROCESSED.ordinal(), uploadState);
//				assertEquals("application/myOwnFormat", fileType);
//				assertEquals(CompressionType.NONE.getMimeType(), compression);
//			}
		}
	}
	
	@Test
	@Ignore
	public void testGetUser2PostprocessTrackCluster() throws TrackPerssitenceException, IOException, SQLException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");
		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false)
				.nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try (Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
			try(FileReader fileReader = new FileReader(file)) {
				ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
				scriptRunner.runScript(fileReader);
			}
			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			databaseTrackPersistence.activate(properties);
			
			Map<String, List<ITrackFile>> user2PostprocessTrackCluster = databaseTrackPersistence.getUser2PostprocessTrackCluster();
			
		}
	}

}
