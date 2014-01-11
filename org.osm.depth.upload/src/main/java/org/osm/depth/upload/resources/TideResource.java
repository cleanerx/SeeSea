package org.osm.depth.upload.resources;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.osm.depth.upload.exceptions.DatabaseException;
import org.osm.depth.upload.messages.GaugeMeasurement;
import org.osm.depth.upload.messages.LengthUnit;
import org.osm.depth.upload.messages.Tide;
import org.osm.depth.upload.messages.TideLevel;

@Path("/tide")
public class TideResource {
	
	
	public List<Tide> getTideHeights(double lat, double lon,TideLevel level, Date startDate, Date endDate) {
		JDummy tidePredictor = new JDummy();
		List<Tide> list = new ArrayList<Tide>();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				Statement statement = conn.createStatement();
				try {
					long timespan = endDate.getTime() - startDate.getTime();
					for(long i = 0; i < timespan ; i = i + 60000L ) {
						Date date = new Date(startDate.getTime() +  i);
						double tideHeight = tidePredictor.getTideHeight(lat, lon, date);
						Tide tide = new Tide();
						tide.timestamp = date;
						if(TideLevel.MEANSEALEVEL.equals(level)) {
						} else {
							tide.value = (float) tideHeight;
						}
						
						double restlat = lat - Math.floor(lat / 0.125) * 0.125;
						double lat1 = lat - restlat;
						double lat2 = lat + 0.125;
					}
					
					ResultSet executeQuery = statement.executeQuery("SELECT * FROM gaugemeasurement g ORDER BY time"); //$NON-NLS-1$
					
					while(executeQuery.next()) {
						Tide tide = new Tide();
						tide.value = executeQuery.getFloat("value");
						tide.timestamp = new Date(executeQuery.getTimestamp("time").getTime());
						list.add(tide);
					}
					return list;
				} finally {
					statement.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable");
		}
	}	

}
