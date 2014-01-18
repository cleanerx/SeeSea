/**
 * 
Copyright (c) 2014, Jens Kübler
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
package org.osm.depth.contours;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.seesea.data.io.postgis.PostgresConnectionFactory;

import org.osm.data.IOSMElement;
import org.osm.data.OSMData;
import org.osm.data.OSMNode;
import org.osm.data.OSMWay;
import org.osm.data.OSMWrapperAPI;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ContoursCalculator {
	
	public void run(Properties properties) throws SQLException, IOException, ParserConfigurationException, SAXException {
		OSMWrapperAPI wrapperAPI = new OSMWrapperAPI();
		Document nodesViaOverpass = wrapperAPI.getNodesViaOverpass("query2.xml"); //$NON-NLS-1$
		OSMData osmData = wrapperAPI.getOSMData(nodesViaOverpass);

		PostgresConnectionFactory factory = new PostgresConnectionFactory();
		File file = new File("config.cfg"); //$NON-NLS-1$
		System.out.println(file.getAbsolutePath());
		Connection connection = factory.getConnection(properties,"database");
		PreparedStatement statement = connection.prepareStatement("INSERT INTO border (the_geom) VALUES (ST_SetSRID(ST_GeomFromText(?), 4326))");
	
		for (IOSMElement osmElement : osmData.getOsmWays()) {
			StringBuffer b = new StringBuffer();
			b.append("LINESTRING(");
			if(osmElement instanceof OSMWay) {
				OSMWay way = (OSMWay) osmElement;
				for(OSMNode osmNode : way.getNodes()) {
					b.append( Double.parseDouble(osmNode.getLon()) );
					b.append(' ');
					b.append( Double.parseDouble(osmNode.getLat()) );
					b.append(',');
				}
				OSMNode osmNode = way.getNodes().get(0);
				b.append( Double.parseDouble(osmNode.getLon()) );
				b.append(' ');
				b.append( Double.parseDouble(osmNode.getLat()) );
				statement.setString(1, b.toString() + ")");
				statement.execute();
			}
		}
		
		Statement generateBorderPointsStatement = connection.createStatement();
		ResultSet resultSet = generateBorderPointsStatement.executeQuery("SELECT ST_AsText((dp).geom) FROM (SELECT ST_DumpPoints(ST_Segmentize(the_geom,0.000001)) as dp FROM border) AS foo"); //$NON-NLS-1$
		PreparedStatement insertBorderPointsStatement = connection.prepareStatement("INSERT INTO trackpoints_raw_16 (the_geom, dbs, datasetid) VALUES (ST_GeomFromText(?,4326), 0, -10)");
		while(resultSet.next()) {
			insertBorderPointsStatement.setString(1, resultSet.getString(1));
			insertBorderPointsStatement.addBatch();
		}
		insertBorderPointsStatement.executeBatch();
	}
	
}
