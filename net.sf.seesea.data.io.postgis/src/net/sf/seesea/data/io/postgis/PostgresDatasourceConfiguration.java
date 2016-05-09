package net.sf.seesea.data.io.postgis;

import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jdbc.DataSourceFactory;

@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
public class PostgresDatasourceConfiguration {

	private DataSourceFactory dataSourceFactory;

	private Map<String, ServiceRegistration<DataSource>> id2Registrations = new ConcurrentHashMap<String, ServiceRegistration<DataSource>>();

	// Reference will get injected in OSGi with the service that implements this interface
	@Reference(target = "(osgi.jdbc.driver.name=PostgreSQL JDBC Driver)")
	public void setDataSourceFactory(DataSourceFactory factory) {
		this.dataSourceFactory = factory;
	}

	public void unsetDataSourceFactory(DataSourceFactory factory) {
		this.dataSourceFactory = null;
	}

	@Activate
	public void activate(ComponentContext context) {
		Dictionary<String, Object> properties = context.getProperties();
		Properties dbProps = new Properties();
		dbProps.put(DataSourceFactory.JDBC_DATABASE_NAME, properties.get("dbname"));
		dbProps.put(DataSourceFactory.JDBC_USER, properties.get("user"));
		dbProps.put(DataSourceFactory.JDBC_PASSWORD, properties.get("password"));
		dbProps.put(DataSourceFactory.JDBC_SERVER_NAME, properties.get("server"));
		dbProps.put(DataSourceFactory.JDBC_PORT_NUMBER, properties.get("port"));
//		dbProps.put("db", properties.get("db"));
		try {
			DataSource dataSource = dataSourceFactory.createDataSource(dbProps);
			Hashtable<String, Object> datasourceProperties = new Hashtable<String, Object>();
			datasourceProperties.put("db", properties.get("db"));
			ServiceRegistration<DataSource> registerService = context.getBundleContext()
					.registerService(DataSource.class, dataSource, datasourceProperties);
			id2Registrations.put((String) properties.get("db"), registerService);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Deactivate
	public void deactivate(ComponentContext context) {
		Dictionary<String, Object> properties = context.getProperties();
		ServiceRegistration<DataSource> serviceRegistration = id2Registrations.remove((String) properties.get("db"));
		serviceRegistration.unregister();
	}

}
