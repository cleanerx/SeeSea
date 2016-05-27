package net.sf.seesea.data.postprocessing.system.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.sql.DataSource;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Ignore;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.jdbc.DataSourceFactory;

import net.sf.seesea.content.api.ContentDetectionException;
import net.sf.seesea.data.postprocessing.process.IFilterEngine;

public class FilterSystemTest {

	@Test
	@Ignore
	public void testSimpleFilterRun() throws IOException, ContentDetectionException, InterruptedException, SQLException {
		BundleContext context = Activator.getContext();
		ServiceReference<ConfigurationAdmin> serviceReference = context.getServiceReference(ConfigurationAdmin.class);
		ConfigurationAdmin configurationAdmin = context.getService(serviceReference);
//		URL url = context.getBundle().findEntries("res", "data", false).nextElement();
//		URL resolvedURL = FileLocator.resolve(url);
//		
		Configuration filterConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.data.postprocessing.filter.FilterEngine");
		Dictionary<String, Object> filterEngineProperties = new Hashtable<String, Object>();
		//		contentProperties.put("basedir", resolvedURL.getFile());
		filterConfiguration.update(filterEngineProperties);
		
		String database = "osmapijunit";
		DataSource dataSource = createDataSource(context, database);

		String depth = "depthjunit";
		DataSource depthDataSource = createDataSource(context, depth);

//		try(Connection connection = depthDataSource.getConnection();
//				Statement statement = connection.createStatement();
//				statement.execute("CREATE TABLE depth)) {"
//						+ "

		try(Connection connection = depthDataSource.getConnection()) {
			ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
			
			try(Statement dropStatement = connection.createStatement()) {
				dropStatement.execute("DROP TABlE IF EXISTS trackpoints_raw_filter_8, trackpoints_raw_filter_10, trackpoints_raw_filter_12, trackpoints_raw_filter_16");
			}
			
			URL schemaDump = new URL("http://depth.openseamap.org/dumpDepthSchema.sql");
			InputStream openStream = schemaDump.openStream();
//			GZIPInputStream gzipInputStream = new GZIPInputStream(openStream);
			BufferedReader in = new BufferedReader(new InputStreamReader(openStream));
			scriptRunner.runScript(in);
		}

		
		configureUserDataDatasource(configurationAdmin);
		configureDepthDatasource(configurationAdmin);

		Configuration databaseConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.track.persistence.database.DatabaseTrackPersistence");
		Dictionary<String, Object> databaseProperties = null; 
		if(databaseConfiguration.getProperties() == null) {
			databaseProperties = new Hashtable<String, Object>();
		} else {
			databaseProperties = databaseConfiguration.getProperties();
		}

		URL url = context.getBundle().findEntries("res", "data", false).nextElement();
		URL resolvedURL = FileLocator.resolve(url);
		databaseProperties.put("basedir", resolvedURL.getFile());
		databaseConfiguration.update(databaseProperties );

		String uuid = UUID.randomUUID().toString();
		
		Configuration filter1 = configurationAdmin.getConfiguration("net.sf.seesea.data.postprocessing.filter.UnfilteredMeasurementProcessor");
		Dictionary<String, Object> filter1Properties = new Hashtable<String, Object>();
		filter1Properties.put("writer.target" , "(writer=" + uuid +")");
//		contentProperties.put("basedir", resolvedURL.getFile());
		filter1.update(filter1Properties);
		
		Configuration writer = configurationAdmin.getConfiguration("net.sf.seesea.data.io.postgis.PostInsertGISWriter");
		Dictionary<String, Object> writerProperties = new Hashtable<String, Object>();
		List<String> tables = new ArrayList<String>();
		tables.add("trackpoints_raw_filter_16");
		tables.add("trackpoints_raw_filter_12");
		tables.add("trackpoints_raw_filter_10");
		tables.add("trackpoints_raw_filter_8");
		writerProperties.put("outputTables", tables);
		writerProperties.put("writer", uuid);
		writer.update(writerProperties);
		
		Thread.sleep(1000);
		ServiceReference<IFilterEngine> serviceReference2 = context.getServiceReference(IFilterEngine.class);
		IFilterEngine filterEngine = context.getService(serviceReference2);
		filterEngine.filterTracks();


//		
//		Configuration databaseConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.track.persistence.database.DatabaseTrackPersistence");
//		Dictionary<String, Object> databaseProperties = new Hashtable<String, Object>();
//		databaseProperties.put("basedir", resolvedURL.getFile());
//		databaseConfiguration.update(databaseProperties );
	}
	
	private void configureDepthDatasource(ConfigurationAdmin configurationAdmin) throws IOException {
		Configuration postgresConfiguration2 = configurationAdmin.createFactoryConfiguration("net.sf.seesea.data.io.postgis.PostgresDatasourceConfiguration");
		Dictionary<String, Object> postgresUploadProperties2 = new Hashtable<String, Object>();
		postgresUploadProperties2.put("dbname", "depthjunit");
		postgresUploadProperties2.put("user", "osm");
		postgresUploadProperties2.put("password", "");
		postgresUploadProperties2.put("server", "192.168.178.45");
		postgresUploadProperties2.put("port", "5432");
		postgresUploadProperties2.put("db", "depth");
		postgresConfiguration2.update(postgresUploadProperties2);
	}

	private void configureUserDataDatasource(ConfigurationAdmin configurationAdmin) throws IOException {
		Configuration postgresConfiguration = configurationAdmin.createFactoryConfiguration("net.sf.seesea.data.io.postgis.PostgresDatasourceConfiguration");
		Dictionary<String, Object> postgresUploadProperties = new Hashtable<String, Object>();
		postgresUploadProperties.put("dbname", "osmapijunit");
		postgresUploadProperties.put("user", "osm");
		postgresUploadProperties.put("password", "");
		postgresUploadProperties.put("server", "192.168.178.45");
		postgresUploadProperties.put("port", "5432");
		postgresUploadProperties.put("db", "userData");
		postgresConfiguration.update(postgresUploadProperties);
	}
	
	private DataSource createDataSource(BundleContext context, String database) throws SQLException {
		ServiceReference<DataSourceFactory> dsfactoryReference = context.getServiceReference(DataSourceFactory.class);
		DataSourceFactory dataSourceFactory = context.getService(dsfactoryReference);
		Properties properties = new Properties();
		properties.put(DataSourceFactory.JDBC_DATABASE_NAME, database);
		properties.put(DataSourceFactory.JDBC_USER, "osm");
		properties.put(DataSourceFactory.JDBC_PASSWORD, "");
		properties.put(DataSourceFactory.JDBC_SERVER_NAME, "192.168.178.45");
		properties.put(DataSourceFactory.JDBC_PORT_NUMBER, "5432");

		DataSource dataSource = dataSourceFactory.createDataSource(properties);
		return dataSource;
	}

	
}
