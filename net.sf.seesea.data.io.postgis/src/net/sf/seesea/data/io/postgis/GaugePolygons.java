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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sf.seesea.data.io.postgis.PostgresConnectionFactory;

public class GaugePolygons {

	public void x(Connection connection, Connection connection2) throws SQLException {
		// either mark all polygons beginning from the gauges or mark the polygons from the tracks (and hence the areas)
		
		Set<Long> gaugeContainingPolygonIds = new HashSet<Long>();
		
		// get all polygon
		Statement statement = connection2.createStatement();
		ResultSet executeQuery = statement.executeQuery("SELECT id, name, ST_X(ST_ASTEXT(geom)), ST_Y(ST_ASTEXT(geom)) FROM gauge");
		while(executeQuery.next()) {
			String lon = executeQuery.getString(3);
			String lat = executeQuery.getString(4);
			String name = executeQuery.getString(2);
			Statement statementGaugePolygon = connection.createStatement();
			// get the polygons which are very close to the gauges, assuming that they are very close to the shore
			ResultSet execute = statementGaugePolygon.executeQuery("SELECT osm_id FROM planet_osm_polygon AS b WHERE ST_DWithin(way, ST_Transform(ST_GeomFromText( 'POINT(" + lon + " " + lat + ")', 4326), 900913), 100) AND (water is not null OR waterway is not null OR \"natural\" = 'water')");
			while(execute.next()) {
				long id = execute.getLong(1);
				gaugeContainingPolygonIds.add(id);
				System.out.println(id + ":" + lat + ":" + lon + ":" + name);
			}
		}
		
////		gaugeNodes.add(3566912L);
////		gaugeNodes.add(4252513L);
//		gaugeNodes.add(144017399L);
		
		Set<Long> set = new HashSet<Long>();
		set.add(27425090L);
		Long neighbors = getNeighbors(connection, set, 0, gaugeContainingPolygonIds, new HashSet<Long>());
		System.out.println("target" + neighbors);
	}

	private Long getNeighbors(Connection connection, Set<Long> nodes, int recursionDepth, Set<Long> gaugeContainingPolygonIds, Set<Long> visitedNodes) throws SQLException {
		Statement statement = connection.createStatement();
		Set<Long> osmIds = new LinkedHashSet<Long>();
		visitedNodes.addAll(nodes);
		// this is a breadth first search. It there fore is related to the distance of reachability and may not always yield the closest point but a first good approximation
		for (Long node : nodes) {
			StringBuffer out = new StringBuffer();
			for (int i = 0 ; i < recursionDepth; i++) {
				out.append(">");
			}
			ResultSet execute = statement.executeQuery("SELECT (b.osm_id) FROM planet_osm_polygon as a,planet_osm_polygon as b WHERE ST_Touches(a.way, b.way) AND a.osm_id != b.osm_id AND (b.water is not null OR b.waterway is not null OR b.natural = 'water') AND a.osm_id = " + node);
			while(execute.next()) {
				long osmId = execute.getLong(1);
				if(visitedNodes.contains(osmId)) {
					continue;
				}
				if(gaugeContainingPolygonIds.contains(osmId)) {
					return osmId;
				} else {
					osmIds.add(osmId);
				}
				System.out.println(out.toString() + node + "->" + osmId);
			}
		}
		if(recursionDepth < 10) {
			Long neighbors = getNeighbors(connection, osmIds, ++recursionDepth, gaugeContainingPolygonIds, visitedNodes);
			if(neighbors != null) {
				return neighbors;
			}
		} else {
			System.out.println("recursion depth reached - no gauge found");
		}
		return null;
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException, SQLException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("config.cfg"));
		Connection connection = PostgresConnectionFactory.getDBConnection(properties, "depthapi"); //$NON-NLS-1$
		Connection inshoreConnection = PostgresConnectionFactory.getDBConnection(properties, "gissmall"); //$NON-NLS-1$
		
		GaugePolygons x = new GaugePolygons();
		x.x(inshoreConnection, connection);
	}

	
}
