package net.sf.seesea.data.postprocessing.system.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

import javax.sql.DataSource;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.jdbc.DataSourceFactory;

import net.sf.seesea.content.api.ContentDetectionException;
import net.sf.seesea.content.api.IContentDetector;

public class ContentDetectionSystemTest {

	/**
	 * tests the content detection if the schema is empty
	 * @throws IOException
	 * @throws ContentDetectionException
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	@Test
	public void testContentDetectionEmptySchema() throws IOException, ContentDetectionException, InterruptedException, SQLException {
		BundleContext context = Activator.getContext();
		ServiceReference<ConfigurationAdmin> serviceReference = context.getServiceReference(ConfigurationAdmin.class);
		ConfigurationAdmin configurationAdmin = context.getService(serviceReference);
		URL url = context.getBundle().findEntries("res", "data", false).nextElement();
		URL resolvedURL = FileLocator.resolve(url);
		
		Configuration contentConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.content.impl.ContentDetector");
		Dictionary<String, Object> contentProperties = new Hashtable<String, Object>();
		contentProperties.put("basedir", resolvedURL.getFile());
		contentConfiguration.update(contentProperties );
		
		Configuration databaseConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.track.persistence.database.DatabaseTrackPersistence");
		Dictionary<String, Object> databaseProperties = new Hashtable<String, Object>();
		databaseProperties.put("basedir", resolvedURL.getFile());
		databaseConfiguration.update(databaseProperties );

		configureUserDataDatasource(configurationAdmin);
		configureDepthDatasource(configurationAdmin);

		DataSource dataSource = createDataSource(context);;
		try(Connection connection = dataSource.getConnection()) {
			ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
			
			try(Statement dropStatement = connection.createStatement()) {
				dropStatement.execute("DROP TABlE IF EXISTS depthsensor, gauge, gaugemeasurement, license, sbassensor, user_profiles, user_tracks, userroles, vesselconfiguration");
			}
			
			URL schemaDump = new URL("http://depth.openseamap.org/dumpSchema.sql.gz");
			InputStream openStream = schemaDump.openStream();
			GZIPInputStream gzipInputStream = new GZIPInputStream(openStream);
			BufferedReader in = new BufferedReader(new InputStreamReader(gzipInputStream));
			scriptRunner.runScript(in);
		}

		
		Thread.sleep(1000);
		ServiceReference<IContentDetector> serviceReference2 = context.getServiceReference(IContentDetector.class);
		IContentDetector contentDetector = context.getService(serviceReference2);
		contentDetector.setContentTypes();
	}

	/**
	 * tests a content detection for nmea0183 file
	 * 
	 * @throws IOException
	 * @throws ContentDetectionException
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	@Test
	public void testContentDetectionFullSchema() throws IOException, ContentDetectionException, InterruptedException, SQLException {
		BundleContext context = Activator.getContext();
		ServiceReference<ConfigurationAdmin> serviceReference = context.getServiceReference(ConfigurationAdmin.class);
		ConfigurationAdmin configurationAdmin = context.getService(serviceReference);
		URL url = context.getBundle().findEntries("res", "data", false).nextElement();
		URL resolvedURL = FileLocator.resolve(url);
		
		Configuration contentConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.content.impl.ContentDetector");
		Dictionary<String, Object> contentProperties = new Hashtable<String, Object>();
		contentProperties.put("basedir", resolvedURL.getFile());
		contentConfiguration.update(contentProperties );
		
		Configuration databaseConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.track.persistence.database.DatabaseTrackPersistence");
		Dictionary<String, Object> databaseProperties = null; 
		if(databaseConfiguration.getProperties() == null) {
			databaseProperties = new Hashtable<String, Object>();
		} else {
			databaseProperties = databaseConfiguration.getProperties();
		}
		
		databaseProperties.put("basedir", resolvedURL.getFile());
		databaseProperties.put("fullprocess", true);
		databaseProperties.put("processTrackIds", "51652");
		databaseConfiguration.update(databaseProperties );

		configureUserDataDatasource(configurationAdmin);

		configureDepthDatasource(configurationAdmin);

		DataSource dataSource = createDataSource(context);
		try(Connection connection = dataSource.getConnection()) {
			ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
			
			try(Statement dropStatement = connection.createStatement()) {
				dropStatement.execute("DROP TABlE IF EXISTS depthsensor, gauge, gaugemeasurement, license, sbassensor, user_profiles, user_tracks, userroles, vesselconfiguration");
			}
			
			URL schemaDump = new URL("http://depth.openseamap.org/dumpAll.sql.gz");
			InputStream openStream = schemaDump.openStream();
			GZIPInputStream gzipInputStream = new GZIPInputStream(openStream);
			BufferedReader in = new BufferedReader(new InputStreamReader(gzipInputStream));
			scriptRunner.runScript(in);
		}

		
		ServiceReference<IContentDetector> serviceReference2 = context.getServiceReference(IContentDetector.class);
		IContentDetector contentDetector = context.getService(serviceReference2);
		contentDetector.setContentTypes();
		
		try(Connection connection = dataSource.getConnection(); 
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT upload_state, filetype FROM user_tracks WHERE track_id = '51652'");) {
			while(resultSet.next()) {
				assertEquals("Should be in preprocessed state",3, resultSet.getInt(1));
				assertEquals("Should be nmea 0183","application/x-nmea0183", resultSet.getString(2));
			}
		}

	}

	private DataSource createDataSource(BundleContext context) throws SQLException {
		ServiceReference<DataSourceFactory> dsfactoryReference = context.getServiceReference(DataSourceFactory.class);
		DataSourceFactory dataSourceFactory = context.getService(dsfactoryReference);
		Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_DATABASE_NAME, "osmapijunit");
		properties.put(DataSourceFactory.JDBC_USER, "osm");
		properties.put(DataSourceFactory.JDBC_SERVER_NAME, "localhost");
		properties.put(DataSourceFactory.JDBC_PORT_NUMBER, "5432");

		DataSource dataSource = dataSourceFactory.createDataSource(properties);
		return dataSource;
	}
	
	private void configureDepthDatasource(ConfigurationAdmin configurationAdmin) throws IOException {
		Configuration postgresConfiguration2 = configurationAdmin.createFactoryConfiguration("net.sf.seesea.data.io.postgis.PostgresDatasourceConfiguration");
		Dictionary<String, Object> postgresUploadProperties2 = new Hashtable<String, Object>();
		postgresUploadProperties2.put("dbname", "osmapijunit");
		postgresUploadProperties2.put("user", "osm");
		postgresUploadProperties2.put("server", "localhost");
		postgresUploadProperties2.put("port", "5432");
		postgresUploadProperties2.put("db", "depth");
		postgresConfiguration2.update(postgresUploadProperties2);
	}

	private void configureUserDataDatasource(ConfigurationAdmin configurationAdmin) throws IOException {
		Configuration postgresConfiguration = configurationAdmin.createFactoryConfiguration("net.sf.seesea.data.io.postgis.PostgresDatasourceConfiguration");
		Dictionary<String, Object> postgresUploadProperties = new Hashtable<String, Object>();
		postgresUploadProperties.put("dbname", "osmapijunit");
		postgresUploadProperties.put("user", "osm");
		postgresUploadProperties.put("server", "localhost");
		postgresUploadProperties.put("port", "5432");
		postgresUploadProperties.put("db", "userData");
		postgresConfiguration.update(postgresUploadProperties);
	}


}

