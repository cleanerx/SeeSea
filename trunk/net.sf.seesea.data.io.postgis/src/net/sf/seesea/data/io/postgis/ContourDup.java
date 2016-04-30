package net.sf.seesea.data.io.postgis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sf.seesea.data.io.postgis.PostgresConnectionFactory;

/**
 * Utility class to remove duplicate contours from database
 * @author jens
 *
 */
public class ContourDup {

	public void x(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet execute = statement.executeQuery("SELECT a.gid,b.gid FROM contoursplit2 AS a, contoursplit2 AS b WHERE a.the_geom && b.the_geom AND a.the_geom = b.the_geom AND a.gid != b.gid");
		Set<String> duplicates = new HashSet<String>();
		Map<String, String> forward = new HashMap<String, String>();
		while(execute.next()) {
			String from = execute.getString(1);
			String to = execute.getString(2);
			if(duplicates.contains(from)) {
				// do nothing
			} else {
				duplicates.add(to);
			}
		}
		StringBuffer buf = new StringBuffer();
		for (Iterator iterator = duplicates.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			buf.append(string);
			if(iterator.hasNext()) {
				buf.append(",");
			}
		}
		statement.execute("DELETE FROM contoursplit2 WHERE gid IN (" + buf.toString() + ")");
		
		System.out.println(duplicates.size());
		
		// alle polygon geschnitten von der 100m Küstenlinie
//		Statement statement = connection.createStatement();
//		statement.execute("CREATE TABLE shallowwater AS (SELECT ST_MakePolygon(the_geom) AS poly FROM contoursplit WHERE m = 100); ");
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException, SQLException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("config.cfg"));
		Connection connection = PostgresConnectionFactory.getDBConnection(properties, "gebco"); //$NON-NLS-1$
		Connection inshoreConnection = PostgresConnectionFactory.getDBConnection(properties, "gis"); //$NON-NLS-1$
		
		ContourDup x = new ContourDup();
		x.x(connection);
	}

	
}
