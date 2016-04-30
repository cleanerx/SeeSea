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
		
		// alle polygon geschnitten von der 100m K�stenlinie
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
