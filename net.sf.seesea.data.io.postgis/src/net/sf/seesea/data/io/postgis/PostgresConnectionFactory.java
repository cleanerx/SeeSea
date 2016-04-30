package net.sf.seesea.data.io.postgis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 */
public class PostgresConnectionFactory {

	/**
	 * 
	 * @param properties
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(Properties properties, String databaseProperty) throws SQLException {
		String user = (String) properties.get("user"); //$NON-NLS-1$
		String password = (String) properties.get("password"); //$NON-NLS-1$
		String host = (String) properties.get("host"); //$NON-NLS-1$
		if(host == null || host.isEmpty()) {
			host = "localhost"; //$NON-NLS-1$
		}
		String port = (String) properties.get("port"); //$NON-NLS-1$
		if(port == null || port.isEmpty()) {
			port = "5432"; //$NON-NLS-1$
		}
		String database = (String) properties.get(databaseProperty); //$NON-NLS-1$
		
		String url = "jdbc:postgresql:" + (host != null ? ("//" + host) + (port != null ? ":" + port : "") + "/" : "") + database ; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return DriverManager.getConnection (url, user, password);
	}

	/**
	 * 
	 * @param properties
	 * @return
	 * @throws SQLException
	 */
	public static Connection getDBConnection(Properties properties, String database) throws SQLException {
		String user = (String) properties.get("user"); //$NON-NLS-1$
		String password = (String) properties.get("password"); //$NON-NLS-1$
		String host = (String) properties.get("host"); //$NON-NLS-1$
		if(host == null || host.isEmpty()) {
			host = "localhost"; //$NON-NLS-1$
		}
		String port = (String) properties.get("port"); //$NON-NLS-1$
		if(port == null || port.isEmpty()) {
			port = "5432"; //$NON-NLS-1$
		}
		
		String url = "jdbc:postgresql:" + (host != null ? ("//" + host) + (port != null ? ":" + port : "") + "/" : "") + database ; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return DriverManager.getConnection (url, user, password);
	}

	
}
