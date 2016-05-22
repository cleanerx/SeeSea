package net.sf.seesea.data.postprocessing.system.test;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Ignore;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import net.sf.seesea.content.api.ContentDetectionException;

public class FilterSystemTest {

	public FilterSystemTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	@Ignore
	public void testContentDetectionEmptySchema() throws IOException, ContentDetectionException, InterruptedException, SQLException {
//		BundleContext context = Activator.getContext();
//		ServiceReference<ConfigurationAdmin> serviceReference = context.getServiceReference(ConfigurationAdmin.class);
//		ConfigurationAdmin configurationAdmin = context.getService(serviceReference);
//		URL url = context.getBundle().findEntries("res", "data", false).nextElement();
//		URL resolvedURL = FileLocator.resolve(url);
//		
//		Configuration contentConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.content.impl.ContentDetector");
//		Dictionary<String, Object> contentProperties = new Hashtable<String, Object>();
//		contentProperties.put("basedir", resolvedURL.getFile());
//		contentConfiguration.update(contentProperties );
//		
//		Configuration databaseConfiguration = configurationAdmin.getConfiguration("net.sf.seesea.track.persistence.database.DatabaseTrackPersistence");
//		Dictionary<String, Object> databaseProperties = new Hashtable<String, Object>();
//		databaseProperties.put("basedir", resolvedURL.getFile());
//		databaseConfiguration.update(databaseProperties );
	}
	
}
