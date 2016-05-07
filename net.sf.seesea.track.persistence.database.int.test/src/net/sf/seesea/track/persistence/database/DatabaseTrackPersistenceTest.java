package net.sf.seesea.track.persistence.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.Test;

import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;

public class DatabaseTrackPersistenceTest {

	@Test
	public void testSingleTrack() throws TrackPerssitenceException, SQLException, IOException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");
		
		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false).nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try(Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
	        FileReader fileReader = new FileReader(file);
			ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
			scriptRunner.runScript(fileReader);

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String,Object> properties = new HashMap<String, Object>();
			properties.put("basedir", file.getParentFile().getPath());
			databaseTrackPersistence.activate(properties);
			
			List<ITrackFile> trackFiles2Process = databaseTrackPersistence.getTrackFiles2Process();
			assertEquals(1, trackFiles2Process.size());
			ITrackFile trackFile = trackFiles2Process.get(0);
			assertEquals(1L, trackFile.getTrackId());
			assertTrue(trackFile.getTrackQualifier().endsWith("/000/1.dat"));
			assertEquals("test@test.de", trackFile.getUsername() );
		}
		

	}

	@Test
	public void testBlacklistUser() throws TrackPerssitenceException, SQLException, IOException {
		JDBCDataSource uploadDataSource = new JDBCDataSource();
		uploadDataSource.setDatabase("jdbc:hsqldb:" + "uploadUnitTest");
		
		URL dumpURL = DatabaseActivator.getContext().getBundle().findEntries("res", "dump.sql", false).nextElement();
		try (Connection c = uploadDataSource.getConnection()) {
			try(Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
	        FileReader fileReader = new FileReader(file);
			ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
			scriptRunner.runScript(fileReader);

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String,Object> properties = new HashMap<String, Object>();
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
			try(Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
	        FileReader fileReader = new FileReader(file);
			ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
			scriptRunner.runScript(fileReader);

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String,Object> properties = new HashMap<String, Object>();
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
			try(Statement statement = c.createStatement()) {
				statement.execute("DROP SCHEMA PUBLIC CASCADE");
			}

			URL resolve = FileLocator.resolve(dumpURL);
			File file = new File(resolve.getFile());
	        FileReader fileReader = new FileReader(file);
			ScriptRunner scriptRunner = new ScriptRunner(c, true, true);
			scriptRunner.runScript(fileReader);

			DatabaseTrackPersistence databaseTrackPersistence = new DatabaseTrackPersistence();
			databaseTrackPersistence.bindDepthConnection(uploadDataSource);
			Map<String,Object> properties = new HashMap<String, Object>();
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
}
