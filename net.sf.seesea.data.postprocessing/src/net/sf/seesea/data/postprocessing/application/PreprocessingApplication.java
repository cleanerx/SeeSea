/**
Copyright (c) 2013-2015, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package net.sf.seesea.data.postprocessing.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import net.sf.seesea.content.impl.ContentDetector;
import net.sf.seesea.data.io.postgis.PostgresConnectionFactory;
import net.sf.seesea.data.postprocessing.DataPostprocessingActivator;
import net.sf.seesea.data.postprocessing.database.DatabaseProcessor;
import net.sf.seesea.data.postprocessing.database.IUploadedData2Contours;
import net.sf.seesea.data.postprocessing.database.UploadedData2Contours;
import net.sf.seesea.track.api.exception.NMEAProcessingException;

import org.apache.log4j.Logger;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * This class controls all aspects of the application's execution
 */
public class PreprocessingApplication implements IApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) throws InvalidSyntaxException, SQLException {

		Map arguments = context.getArguments();
		String[] args = (String[]) arguments.get("application.args"); //$NON-NLS-1$
		Properties properties = new Properties();
		String configFile = null;
		if (args.length > 0) {
			configFile = args[0];
		} else {
			configFile = "config.cfg"; //$NON-NLS-1$
		}
		try {
			File file = new File(configFile);
			System.out.println(file.getAbsolutePath());
			properties.load(new FileInputStream(configFile));
			properties.putAll(arguments);

			ServiceReference<ConfigurationAdmin> serviceReference = DataPostprocessingActivator.getContext()
					.getServiceReference(ConfigurationAdmin.class);
			ConfigurationAdmin configurationAdmin = DataPostprocessingActivator.getContext()
					.getService(serviceReference);

				// configure content detector
				Configuration configuration = configurationAdmin
						.getConfiguration("net.sf.seesea.content.impl.ContentDetector");
				Dictionary<String, Object> contentDetector = new Hashtable<String, Object>();
				contentDetector.put("basedir", properties.get("basedir"));
				contentDetector.put("fullprocess", properties.get("fullprocess"));
				if(properties.get("detectContent").equals("true")) {
				configuration.update(contentDetector);
				} else {
					configuration.delete();
				}

//			DataSource s;
//			s.
			// configure track persistence
			
			configureDatabaseConnections(properties);
			
			Configuration configurationDatabase = configurationAdmin
					.getConfiguration("net.sf.seesea.track.persistence.database.DatabaseTrackPersistence");
			Dictionary<String, Object> configDatabase = new Hashtable<String, Object>();
			configDatabase.put("basedir", properties.get("basedir"));
			configDatabase.put("fullprocess", properties.get("fullprocess"));
			configurationDatabase.update(configDatabase);

				Configuration configurationContours = configurationAdmin
						.getConfiguration("net.sf.seesea.contour.triangulation.TriangulationBasedContourLineGeneration");
				Dictionary<String, Object> configContours = new Hashtable<String, Object>();
				if(properties.get("detectContent").equals("true")) {
					configurationContours.update(configContours);
				} else {
					configurationContours.delete();
				}
			
			
			ServiceReference<IUploadedData2Contours> serviceReference2 = DataPostprocessingActivator.getContext()
					.getServiceReference(IUploadedData2Contours.class);
			IUploadedData2Contours uploadedData2Contours = DataPostprocessingActivator.getContext().getService(serviceReference2);
			uploadedData2Contours.processData();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// new ServiceTracker<S, T>(context, clazz, customizer)

		//
		// DatabaseProcessor databaseProcessor;
		// try {
		// File file = new File(configFile);
		// System.out.println(file.getAbsolutePath());
		// properties.load(new FileInputStream(configFile));
		//
		// configureDatabaseConnections(properties);
		// configureTideProvider(properties);
		//
		// configureContourGeneration();
		//
		//
		//
		// databaseProcessor = new DatabaseProcessor();
		// databaseProcessor.createAndFilterTracks();
		// } catch (SQLException e) {
		// Logger.getLogger(getClass()).error("Failed to start processing", e);
		// } catch (IOException e1) {
		// Logger.getLogger(getClass()).error("Failed to start processing", e1);
		// } catch (NMEAProcessingException e2) {
		// Logger.getLogger(getClass()).error("Failed to start processing", e2);
		// }
		return 0;
	}

//	private void configureContourGeneration() {
//		Set<String> contourTracks = getValues("contourTracks"); //$NON-NLS-1$
//
//		List<Integer> contourLineDepths = new ArrayList<Integer>();
//		contourLineDepths.add(2);
//		contourLineDepths.add(5);
//		contourLineDepths.add(10);
//		contourLineDepths.add(20);
//		contourLineDepths.add(30);
//		contourLineDepths.add(40);
//		contourLineDepths.add(50);
//
//	}

	private void configureTideProvider(Properties properties) throws IOException, InvalidSyntaxException {
		ServiceReference<ConfigurationAdmin> configAdminServiceReference = DataPostprocessingActivator.getContext()
				.getServiceReference(ConfigurationAdmin.class);
		ConfigurationAdmin configAdmin = DataPostprocessingActivator.getContext()
				.getService(configAdminServiceReference);
		Configuration[] listConfigurations = configAdmin
				.listConfigurations("(service.pid=net.sf.seesea.tidemodel.dtu10.java.DTUTidePrediction)");
		if (listConfigurations == null || listConfigurations.length == 0) {
			Configuration configuration = configAdmin
					.getConfiguration("net.sf.seesea.tidemodel.dtu10.java.DTUTidePrediction");
			Hashtable<String, Object> oceanTideProviderConfig = new Hashtable<String, Object>();
			configuration.update(oceanTideProviderConfig);
		}
	}

	private void configureDatabaseConnections(Properties properties) throws SQLException {
		Connection connection = PostgresConnectionFactory.getConnection(properties, "database"); //$NON-NLS-1$
		Connection osmConnection = PostgresConnectionFactory.getConnection(properties, "osm"); //$NON-NLS-1$
		Connection coastlineDBConnection = PostgresConnectionFactory.getConnection(properties, "coastlineDB"); //$NON-NLS-1$
		Connection tideDBConnection = PostgresConnectionFactory.getConnection(properties, "tideDB"); //$NON-NLS-1$

		Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
		hashtable.put("db", "coastline");
		ServiceRegistration<Connection> registerService = DataPostprocessingActivator.getContext()
				.registerService(Connection.class, coastlineDBConnection, hashtable);

		hashtable = new Hashtable<String, Object>();
		hashtable.put("db", "osm");
		ServiceRegistration<Connection> osmConnectionService = DataPostprocessingActivator.getContext()
				.registerService(Connection.class, osmConnection, hashtable);

		hashtable = new Hashtable<String, Object>();
		hashtable.put("db", "gauge");
		ServiceRegistration<Connection> gaugeConnectionService = DataPostprocessingActivator.getContext()
				.registerService(Connection.class, connection, hashtable);

		hashtable = new Hashtable<String, Object>();
		hashtable.put("db", "userData");
		ServiceRegistration<Connection> userdataConnectionService = DataPostprocessingActivator.getContext()
				.registerService(Connection.class, connection, hashtable);

		hashtable = new Hashtable<String, Object>();
		hashtable.put("db", "filters");
		ServiceRegistration<Connection> filtersConnectionService = DataPostprocessingActivator.getContext()
				.registerService(Connection.class, coastlineDBConnection, hashtable);

		
		hashtable = new Hashtable<String, Object>();
		hashtable.put("db", "dtu");
		ServiceRegistration<Connection> dtuConnectionService = DataPostprocessingActivator.getContext()
				.registerService(Connection.class, tideDBConnection, hashtable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		// nothing to do
	}

	private Set<String> getValues(String usertype, Properties _properties) {
		String processUsers = (String) _properties.get(usertype);
		Set<String> whitelistUsers = new HashSet<String>();
		if (processUsers != null && !processUsers.isEmpty()) {
			String[] users = processUsers.split(","); //$NON-NLS-1$
			for (String user : users) {
				whitelistUsers.add(user.trim());
			}
		}
		return whitelistUsers;
	}

}
