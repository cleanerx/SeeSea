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
package net.sf.seesea.triangulation.postgis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sf.seesea.contour.ContourLine;
import net.sf.seesea.contour.IContourLine;
import net.sf.seesea.data.io.PersistenceException;
import net.sf.seesea.geometry.IEdge;
import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.ITriangle;
import net.sf.seesea.geometry.impl.Point;
import net.sf.seesea.geometry.impl.Polygon;
import net.sf.seesea.geometry.impl.Triangle;
import net.sf.seesea.triangulation.ITriangulationDescription;
import net.sf.seesea.triangulation.ITriangulationFactory;
import net.sf.seesea.triangulation.ITriangulationPersistence;
import net.sf.seesea.triangulation.ITriangulator;
import net.sf.seesea.triangulation.NeighboringTrianglesOnBoundary;
import net.sf.seesea.triangulation.TriangulationDescription;

import org.apache.log4j.Logger;

/**
 * A persistence implementation for postgis
 *
 */
public class PostgisTriangulationPersistence implements ITriangulationPersistence {

	private Connection triangulationConnection;
	private Connection inshoreConnection;
	
	private ITriangulationFactory triangulationFactory;
	
	private int batchCounter;
	private PreparedStatement lastStatement;

	public PostgisTriangulationPersistence() {
		batchCounter = 0;
	}
	
	@Override
	public Iterator<List<IPolygon>> getRiverBoundaryPolygonsIterator() {
		// List<IPolygon> boundaries = new ArrayList<IPolygon>();
		Statement statement = null;
		try {
			statement = inshoreConnection.createStatement();
			// formatter:off

			// SELECT (ST_DUMPPOINTS(way)).* FROM planet_osm_polygon WHERE way
			// && ST_Transform(ST_MakeEnvelope(8.0, 48.0, 9, 49, 4326), 900913);

			ResultSet resultSet = statement.executeQuery("SELECT path, ST_X(ST_TRANSFORM(geom,4326)) AS lon, ST_Y(ST_TRANSFORM(geom,4326)) as lat, osm_id FROM " + "(SELECT * FROM (" +
			// "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE way && ST_MakeEnvelope(864182.235038886, 6177634.080699317, 865681.4525450426, 6178640.890796364, 900913) ) AS xx  "
			// + //UNION
			// "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE way && ST_Transform(ST_MakeEnvelope(7.7590000, 48.4236938, 7.7765488, 48.4375000, 4326), 900913) AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  "
			// + //UNION
			// "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE way && ST_Transform(ST_MakeEnvelope(8.16501, 48.90696, 8.186699, 48.941, 4326), 900913) AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  "
			// + //UNION
					"SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  " + // UNION
																																												// "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE way && ST_Transform(ST_MakeEnvelope(7.5, 48.0, 8.5, 49, 4326), 900913) AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  "
																																												// +
																																												// //UNION
																																												// "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_line WHERE way && ST_Transform(ST_MakeEnvelope(7.5, 48.0, 8.5, 49, 4326), 900913) ) AS xx "
																																												// +
																																												// "WHERE xx.natural = 'water' OR xx.waterway IS NOT NULL OR xx.water IS NOT NULL) as g ");
					") as g ");
			// formatter:on
			return new PolygonIterator(resultSet);
		} catch (SQLException e) {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}
		return null;
	}

	@Override
	public List<IPolygon> get100mBoundaryPolygons() {
		List<IPolygon> boundaries = new ArrayList<IPolygon>();
		Statement allstatement = null;
		ResultSet allResultSet = null;
		List<IPoint> coordinates = new ArrayList<IPoint>();
		try {
			allstatement = triangulationConnection.createStatement();
			allResultSet = allstatement.executeQuery("SELECT path, ST_X(geom), ST_Y(geom) FROM (SELECT (ST_DUMPPOINTS(g.the_geom)).* FROM coastline AS g WHERE m = 100) as g;"); //$NON-NLS-1$
			int layer = 0;
			int counter = 0;
			IPoint firstpoint = null;
			while (allResultSet.next()) {
				String path = allResultSet.getString(1);
				path = path.substring(path.indexOf("{") + 1, path.indexOf("}")); //$NON-NLS-1$ //$NON-NLS-2$
				String[] split = path.split(","); //$NON-NLS-1$
				layer = Integer.parseInt(split[0]);
				counter = Integer.parseInt(split[1]);
				IPoint point = new Point(allResultSet.getDouble(2), allResultSet.getDouble(3), 100);
				if (counter == 1) {
					if (!coordinates.isEmpty()) {
						boundaries.add(new Polygon(coordinates));
					}
					coordinates = new ArrayList<IPoint>();
					firstpoint = point;
				}
				coordinates.add(point);
			}
			coordinates.add(firstpoint);
			if (!coordinates.isEmpty()) {
				boundaries.add(new Polygon(coordinates));
			}

		} catch (SQLException e) {
			if (allstatement != null) {
				try {
					allstatement.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (allResultSet != null) {
				try {
					allResultSet.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		return boundaries;
	}

	@Override
	public List<IPolygon> getCostalBorderBoundaryHoles(IPolygon boundary100m) {
		List<IPolygon> newHoles = new ArrayList<IPolygon>();

		List<IPoint> points = boundary100m.getPoints();
		StringBuffer b = new StringBuffer();
		b.append("POLYGON("); //$NON-NLS-1$
		b.append("("); //$NON-NLS-1$
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
		otherSymbols.setDecimalSeparator('.');
		DecimalFormat format = new DecimalFormat("#####0.0#########", otherSymbols); //$NON-NLS-1$
		IPoint firstPoint = null;
		int j = 0;
		for (Iterator iterator = points.iterator(); iterator.hasNext();) {
			IPoint triangulationPoint = (IPoint) iterator.next();
			if (j == 0) {
				firstPoint = triangulationPoint;
			}
			b.append(format.format(triangulationPoint.getX()));
			b.append(' ');
			b.append(format.format(triangulationPoint.getY()));
			b.append(',');
		}
		b.append(format.format(firstPoint.getX()));
		b.append(' ');
		b.append(format.format(firstPoint.getY()));
		b.append("))"); //$NON-NLS-1$
		Statement allstatement;
		try {
			allstatement = triangulationConnection.createStatement();
			String sql = "SELECT path, ST_X(geom), ST_Y(geom) FROM (SELECT (ST_Dumppoints((ST_LineMerge(ST_Collect(ST_LineMerge(the_geom)))))).* FROM coastline AS g WHERE m = 0 AND (ST_Contains(ST_GeomFromText('"
					+ b.toString() + "',4326),the_geom)) ) as g;";
			// System.out.println(sql);
			ResultSet allResultSet = allstatement.executeQuery(sql); //$NON-NLS-1$
			int counter = 0;
			Polygon polygon = null;
			IPoint firstpoint = null;
			List<IPoint> coordinates = new ArrayList<IPoint>();
			List<Polygon> holes = new ArrayList<Polygon>();
			while (allResultSet.next()) {
				String path = allResultSet.getString(1);
				path = path.substring(path.indexOf("{") + 1, path.indexOf("}")); //$NON-NLS-1$
				String[] split = path.split(","); //$NON-NLS-1$
				counter = Integer.parseInt(split[1]);
				IPoint point = new Point(allResultSet.getDouble(2), allResultSet.getDouble(3), 0);
				if (counter == 1) {
					firstpoint = point;
					if (!coordinates.isEmpty()) {
						holes.add(new Polygon(coordinates));
					}
					coordinates = new ArrayList<IPoint>();
				}
				coordinates.add(point);
			}
			if (!coordinates.isEmpty()) {
				// coordinates.add(firstPoint);
				holes.add(new Polygon(coordinates));
			}
			// newHoles.add(holes.get(6));
			newHoles.addAll(holes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newHoles;
	}

	// query to find polygon relations
	// SELECT DISTINCT * FROM planet_osm_polygon p1, planet_osm_polygon p2 WHERE
	// p1.way && ST_Transform(ST_MakeEnvelope(8.16501, 48.90696, 8.186699,
	// 48.941, 4326), 900913) AND p1.way && p2.way AND
	// ST_Relate(p1.way,p2.way,'FF2F11212') AND (p1.natural = 'water' OR
	// p1.waterway IS NOT NULL OR p1.water IS NOT NULL) AND (p2.natural =
	// 'water' OR p2.waterway IS NOT NULL OR p2.water IS NOT NULL);

	@Override
	public void persistTriangulation(List<ITriangle> triangulationResult, ITriangulationDescription triangulationDescription) {
		Statement deleteStatement;
		try {
			// previous triangulation exists that needs to be deleted
			if (triangulationDescription != null) {
				deleteStatement = triangulationConnection.createStatement();
				// // restrict this to the area of interest - holes currently do
				// not seem to work and there are tricky cases where islands in
				// rivers exists that have lakes in them...
				String multipolygonString = createMultipolygonString(triangulationDescription.getBorder(), Collections.EMPTY_LIST);
				String x = "ST_Buffer (" + multipolygonString + ", 0.0000001)";
				deleteStatement.execute("DELETE FROM triangulation WHERE ST_Contains(" + x + ",geom)");
			} else {
				deleteStatement = triangulationConnection.createStatement();
				deleteStatement.execute("DELETE FROM triangulation");
			}

			PreparedStatement statementX = triangulationConnection.prepareStatement("INSERT INTO triangulation (geom) VALUES (ST_GeomFromEWKT(?))"); //$NON-NLS-1$
			for (ITriangle polygon : triangulationResult) {
				// System.out.println(polygon);
				StringBuffer b = new StringBuffer();
				b.append("SRID=4326;");
				b.append("POLYGON("); //$NON-NLS-1$
				b.append("("); //$NON-NLS-1$
				b.append((polygon.getPoints().get(0)).getX());
				b.append(' ');
				b.append((polygon.getPoints().get(0)).getY());
				b.append(' ');
				b.append((polygon.getPoints().get(0)).getZ());
				b.append(',');
				b.append((polygon.getPoints().get(1)).getX());
				b.append(' ');
				b.append((polygon.getPoints().get(1)).getY());
				b.append(' ');
				b.append((polygon.getPoints().get(1)).getZ());
				b.append(',');
				b.append((polygon.getPoints().get(2)).getX());
				b.append(' ');
				b.append((polygon.getPoints().get(2)).getY());
				b.append(' ');
				b.append((polygon.getPoints().get(2)).getZ());
				b.append(',');
				b.append((polygon.getPoints().get(0)).getX());
				b.append(' ');
				b.append((polygon.getPoints().get(0)).getY());
				b.append(' ');
				b.append((polygon.getPoints().get(0)).getZ());
				b.append(")"); //$NON-NLS-1$
				statementX.setString(1, b.toString() + ")"); //$NON-NLS-1$
				statementX.addBatch();
			}
			statementX.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ITriangulationDescription> getPartitionizedTriangulations(Long trackId, String trackpointTable) throws PersistenceException {
		List<ITriangulationDescription> descriptions = new ArrayList<ITriangulationDescription>();
		PreparedStatement boundaryStatement;
		try {
			// the bounding box of the points for the track
			boundaryStatement = triangulationConnection
					.prepareStatement("SELECT st_xmin(ST_Extent(the_geom)), st_xmax(ST_Extent(the_geom)), st_ymin(ST_Extent(the_geom)), st_ymax(ST_Extent(the_geom)) FROM " + trackpointTable
							+ " WHERE datasetid = ?");
			boundaryStatement.setLong(1, trackId);
			ResultSet query = boundaryStatement.executeQuery();
			if (query.next()) {
				// double xmin = 8.16490;
				// double xmax = 8.18705;
				// double ymin = 48.906;
				// double ymax = 48.94058;
				double xmin = query.getDouble(1);
				double xmax = query.getDouble(2);
				double ymin = query.getDouble(3);
				double ymax = query.getDouble(4);

				// select all polygons of that bounding box
				Statement xstatement = inshoreConnection.createStatement();
				// formatter:off

				// SELECT (ST_DUMPPOINTS(way)).* FROM planet_osm_polygon WHERE
				// way && ST_Transform(ST_MakeEnvelope(8.0, 48.0, 9, 49, 4326),
				// 900913);

				ResultSet resultSet = xstatement.executeQuery("SELECT path, ST_X(ST_TRANSFORM(geom,4326)) AS lon, ST_Y(ST_TRANSFORM(geom,4326)) as lat, osm_id FROM " + "(SELECT * FROM ("
						+ "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE (ST_Overlaps(way, ST_Transform(ST_MakeEnvelope(" + xmin + ", " + ymin + ", " + xmax + ", " + ymax
						+ ", 4326), 900913)) OR ST_Contains(ST_Transform(ST_MakeEnvelope(" + xmin + ", " + ymin + ", " + xmax + ", " + ymax
						+ ", 4326), 900913), way))  AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  " + // UNION
																																			// +
																																			// "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE way && ST_Transform(ST_MakeEnvelope("
																																			// +
																																			// xmin
																																			// +
																																			// ", "
																																			// +
																																			// ymin
																																			// +
																																			// ", "
																																			// +
																																			// xmax
																																			// +
																																			// ", "
																																			// +
																																			// ymax
																																			// +
																																			// ", 4326), 900913) AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  "
																																			// +
																																			// //UNION
						") as g ");
				// formatter:on
				PolygonIterator polygonIterator = new PolygonIterator(resultSet);

				List<IPolygon> boundaryAndHoles = null;
				while ((boundaryAndHoles = polygonIterator.next()) != null) {
					IPolygon boundary = boundaryAndHoles.get(0);
					if (boundary.getPoints().size() > 2) {
						List<IPolygon> holes = Collections.<IPolygon> emptyList();
						if (boundaryAndHoles.size() > 1) {
							holes = boundaryAndHoles.subList(1, boundaryAndHoles.size());
						}
						// select all triangles that are contained in the
						// boundary excluded by the holes

						String multipolygonString = createMultipolygonString(boundary, holes);
						String x = "ST_Buffer (" + multipolygonString + ", 0.0000001)";
						PreparedStatement polygonTriangleStatement = triangulationConnection.prepareStatement("SELECT ST_AsEWKT(geom) FROM triangulation WHERE ST_Contains(" + x + ", geom)");
						ResultSet polygonTriangleResult = polygonTriangleStatement.executeQuery();
						List<ITriangle> triangulation = new ArrayList<ITriangle>();
						while (polygonTriangleResult.next()) {
							String pointString = polygonTriangleResult.getString(1);
							String[] coordinates = pointString.substring(pointString.indexOf("((") + 2, pointString.indexOf(")")).split(",");
							List<IPoint> points = new ArrayList<IPoint>(3);
							for (int i = 0; i < 3; i++) {
								String[] positions = coordinates[i].split(" ");
								double x1 = Double.parseDouble(positions[0].trim());
								double y1 = Double.parseDouble(positions[1].trim());
								double z1 = Double.parseDouble(positions[2].trim());
								points.add(new Point(x1, y1, z1));
							}
							Triangle triangle = new Triangle(points);
							triangulation.add(triangle);
						}
						descriptions.add(new TriangulationDescription(boundary, holes, triangulation));
					}
				}
			}

			// for each polygon + containment return an

			// buffer.append("SELECT w.gid, w.the_geom, points.the_geom "
			//					+ "FROM water AS w, (SELECT the_geom FROM trackpoints_test1_16 WHERE datasetid = ? ) AS points WHERE ST_Crosses(w.the_geom,points.the_geom)"); //$NON-NLS-1$
			// boundaryStatement.execute(buffer.toString());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return descriptions;
	}

	public Iterator<List<IPolygon>> getHitPartitionizedPolygons(Set<Long> trackIds, String trackpointTable) throws PersistenceException {
		PreparedStatement boundaryStatement;
		try {
			Set<String> inshoreOSMids = new HashSet<String>();
			Set<String> offshoreIds = new HashSet<String>();
			for (Long trackId : trackIds) {
				// the bounding box of the points for the track
				boundaryStatement = triangulationConnection
						.prepareStatement("SELECT st_xmin(ST_Extent(the_geom)), st_xmax(ST_Extent(the_geom)), st_ymin(ST_Extent(the_geom)), st_ymax(ST_Extent(the_geom)) FROM " + trackpointTable
								+ " WHERE datasetid = ?");
				boundaryStatement.setLong(1, trackId);
				ResultSet query = boundaryStatement.executeQuery();
				if (query.next()) {
//				 double xmin = 7.7;
//				 double xmax = 7.8;
//				 double ymin = 54.6;
//				 double ymax = 54.7;
//				 double xmin = 8.16490;
//				 double xmax = 8.18705;
//				 double ymin = 48.906;
//				 double ymax = 48.94058;
					double xmin = query.getDouble(1);
					double xmax = query.getDouble(2);
					double ymin = query.getDouble(3);
					double ymax = query.getDouble(4);
					if(xmin == 0.0 && xmax==0.0 || ymin == 0.0 && ymax == 0.0) {
						continue;
					}
					
					// select all polygons of that bounding box
					Statement inshoreStatement = inshoreConnection.createStatement();
					Statement offshoreStatement = triangulationConnection.createStatement();
					// formatter:off
					
					NumberFormat format = DecimalFormat.getNumberInstance(Locale.ENGLISH);
					String envelope = MessageFormat.format("ST_MakeEnvelope({0}, {1}, {2}, {3}, 4326)",  format.format(xmin), format.format(ymin), format.format(xmax), format.format(ymax));
//				ResultSet inshorePartionizedPolygonResultSet = inshoreStatement.executeQuery(
//						"SELECT path, ST_X(ST_TRANSFORM(geom,4326)) AS lon, ST_Y(ST_TRANSFORM(geom,4326)) as lat, osm_id FROM " 
//				     + "(SELECT * FROM ("
//						+ "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE " +
//						"(ST_Overlaps(way, ST_Transform(" + envelope +", 900913)) OR ST_Contains(ST_Transform(" + envelope + ", 900913), way) OR ST_Contains(way, ST_Transform(" + envelope + ", 900913)))  AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  " + 
//						") as g ");
					// formatter:on
					
					ResultSet inshorePartionizedPolygonResultSet = inshoreStatement.executeQuery("SELECT osm_id FROM " 
							+ "(SELECT * FROM ("
							+ "SELECT osm_id FROM planet_osm_polygon AS poly WHERE " +
							"(ST_Overlaps(way, ST_Transform(" + envelope +", 900913)) OR ST_Contains(ST_Transform(" + envelope + ", 900913), way) OR ST_Contains(way, ST_Transform(" + envelope + ", 900913)))  AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  " + 
							") as g ");
					while(inshorePartionizedPolygonResultSet.next()) {
						inshoreOSMids.add(inshorePartionizedPolygonResultSet.getString(1));
					}
					ResultSet offshorePartionizedPolygonResultSet = offshoreStatement.executeQuery("SELECT gid FROM (SELECT gid FROM gebco_poly_100 WHERE ST_Overlaps(geom," + envelope + ") OR ST_Contains(" + envelope +", geom) OR ST_Contains(geom," + envelope +") ) AS g");
					while(offshorePartionizedPolygonResultSet.next()) {
						offshoreIds.add(offshorePartionizedPolygonResultSet.getString(1));
					}
					Logger.getLogger(getClass()).info((inshoreOSMids.size() + offshoreIds.size()) + "areas are considered for triangulation");
//				ResultSet offshorePartionizedPolygonResultSet = offshoreStatement.executeQuery("SELECT path, ST_X(geom) AS lon, ST_Y(geom) as lat, gid FROM (SELECT (ST_DUMPPOINTS(geom)).*, gid FROM gebco_poly_100 WHERE ST_Overlaps(geom," + envelope + ") OR ST_Contains(" + envelope +", geom) OR ST_Contains(geom," + envelope +") ) AS g");
					
					
//				return new PolygonIterator(inshorePartionizedPolygonResultSet, offshorePartionizedPolygonResultSet);
				}
			}
			if(inshoreOSMids.isEmpty() && offshoreIds.isEmpty()) {
				return null;
			}
			return new IdBasedPolygonIterator(inshoreOSMids, offshoreIds, inshoreConnection, triangulationConnection);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	private String createMultipolygonString(IPolygon boundary, List<IPolygon> holes) {
		// create the multipolygon from the points again
		StringBuffer b = new StringBuffer();
		b.append("ST_GeometryFromText('MULTIPOLYGON("); //$NON-NLS-1$

		// the first polygon as outer one
		b.append("(("); //$NON-NLS-1$
		for (Iterator<IPoint> iterator = boundary.getPoints().iterator(); iterator.hasNext();) {
			IPoint point = iterator.next();
			b.append(point.getX());
			b.append(' ');
			b.append(point.getY());
			// b.append(" 0");
			if (iterator.hasNext()) {
				b.append(',');
			}
		}
		b.append("))"); //$NON-NLS-1$

		if (!holes.isEmpty()) {
			for (IPolygon hole : holes) {
				b.append(",(("); //$NON-NLS-1$
				for (Iterator<IPoint> iterator = hole.getPoints().iterator(); iterator.hasNext();) {
					IPoint point = iterator.next();
					b.append(point.getX());
					b.append(' ');
					b.append(point.getY());
					// b.append(" 0");
					if (iterator.hasNext()) {
						b.append(',');
					}
				}
				b.append("))"); //$NON-NLS-1$
			}
		}
		b.append(")',4326)"); //$NON-NLS-1$
		return b.toString();
	}

	/**
	 * Reads the measures points the the given
	 * @param triangulationDescription
	 * @param trackpointTable
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	private List<IPoint> getMeasuredDepthPoints(IPolygon boundary, List<IPolygon> holes, String trackpointTable) throws PersistenceException {
		List<IPoint> points = new ArrayList<IPoint>();
		Statement statement2;
		try {
			statement2 = triangulationConnection.createStatement();
			String multipolygonString = createMultipolygonString(boundary, holes);
			ResultSet executeQuery = statement2
					.executeQuery("SELECT DISTINCT ST_Y(ST_ASText(the_geom)), ST_X(ST_ASText(the_geom)), dbs FROM " + trackpointTable + " WHERE ST_Contains(" + multipolygonString + ", the_geom)"); //$NON-NLS-1$
			while (executeQuery.next()) {
				double lat = executeQuery.getDouble(1);
				double lon = executeQuery.getDouble(2);
				double dbs = executeQuery.getDouble(3);
				points.add(new Point(lon, lat, dbs));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return points;
	}
	
	@Override
	public void splitMergedContourLines(IPolygon boundary, List<NeighboringTrianglesOnBoundary> boundaryTrianglePairs) throws PersistenceException {
		PreparedStatement existingBoundaryCrossingContourLineStatement;
		try {
			// check for an existing contour line to reattach to
			List<ContourLine> existingBoundaryCrossingContourLines = new ArrayList<ContourLine>();
			for (NeighboringTrianglesOnBoundary neighboringTrianglesOnBoundary : boundaryTrianglePairs) {
				ITriangle outerTriangle = neighboringTrianglesOnBoundary.getTriangleB();
				String triangleA = PostgisHelper.getPostgisLineString(neighboringTrianglesOnBoundary.getTriangleA().getPoints());
				String triangleB = PostgisHelper.getPostgisLineString(neighboringTrianglesOnBoundary.getTriangleB().getPoints());
				existingBoundaryCrossingContourLineStatement = triangulationConnection
						.prepareStatement("SELECT id, m, ST_X(geom), ST_Y(geom) FROM (SELECT id, (ST_DUMPPOINTS(the_geom)).*, m FROM contoursplit WHERE ST_INTERSECTS(the_geom, " + triangleA
								+ ") AND ST_INTERSECTS(the_geom," + triangleB + ") ) as g");
				ResultSet query = existingBoundaryCrossingContourLineStatement.executeQuery();
				// every contour line that is crossing that area must be split to its original position and boundary internal points must be
				// removed if there is no contour line there may be a merge with a previous area required
				Long oldId = -1L;
				ContourLine contourLine = null;
				while (query.next()) {
					long currentId = Long.parseLong(query.getString(1));
					if (oldId != currentId) {
						contourLine = new ContourLine();
						contourLine.setId(currentId);
						contourLine.setDepth(Integer.parseInt(query.getString(2)));
						existingBoundaryCrossingContourLines.add(contourLine);
						oldId = currentId;
					}
					IPoint point = new Point(Double.parseDouble(query.getString(3)), Double.parseDouble(query.getString(4)));
					contourLine.getPoints().add(point);
				}
				// remove last closing polygon points as it may cause trouble during processing
				for (IContourLine contourLineX : existingBoundaryCrossingContourLines) {
					contourLineX.getPoints().remove(contourLineX.getPoints().size() - 1);
				}

				// delete this contour line, close it on both ends and add new contour lines
				for (IContourLine existingContourLine : existingBoundaryCrossingContourLines) {
					IContourLine splitContourLineA = new ContourLine();
					IContourLine splitContourLineB = new ContourLine();
					List<IPoint> pointsA = existingContourLine.getPoints();
					int startIndexSegmentA = -1;
					int endIndexSegmentA = -1;
					for (IPoint point : pointsA) {
						for (IEdge edge : outerTriangle.getEdges()) {
							if (edge.isOnEdge(point)) {
								if (startIndexSegmentA == -1) {
									startIndexSegmentA = pointsA.indexOf(point);
									break;
								} else {
									endIndexSegmentA = pointsA.indexOf(point);
									break;
								}
							}
						}
					}
					splitContourLineA.setDepth(existingContourLine.getDepth());
					splitContourLineB.setDepth(existingContourLine.getDepth());
					if (startIndexSegmentA == 0) {
						splitContourLineA.getPoints().addAll(pointsA.subList(0, endIndexSegmentA));
						splitContourLineB.getPoints().addAll(pointsA.subList(endIndexSegmentA, pointsA.size()));
					} else {
						splitContourLineA.getPoints().addAll(pointsA.subList(startIndexSegmentA + 1 , endIndexSegmentA)); 
						splitContourLineB.getPoints().addAll(pointsA.subList(0, startIndexSegmentA + 1 ));
						splitContourLineB.getPoints().addAll(pointsA.subList(endIndexSegmentA, pointsA.size()));
					}

					Statement updateContourLineStatement = triangulationConnection.createStatement();
					updateContourLineStatement.executeUpdate("DELETE FROM contoursplit WHERE id = " + existingContourLine.getId());
					// do not add old contours that span only the current
					// boundary, since these are old contours that are being
					// updated
					String postgisLineContourA = PostgisHelper.getPostgisLineString(splitContourLineA.getPoints());
					updateContourLineStatement.executeUpdate("INSERT INTO contoursplit (the_geom, m) VALUES (" + postgisLineContourA + ", " + existingContourLine.getDepth() + ")");
					String postgisLineContourB = PostgisHelper.getPostgisLineString(splitContourLineB.getPoints());
					updateContourLineStatement.executeUpdate("INSERT INTO contoursplit (the_geom, m) VALUES (" + postgisLineContourB + ", " + existingContourLine.getDepth() + ")");
				}
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public void addOrUpdateContourLine(List<IPoint> points, Integer depth, IPolygon boundary, List<NeighboringTrianglesOnBoundary> boundaryTrianglePairs) throws PersistenceException {
		PreparedStatement statement;
		try {
			// check for an existing contour line to reattach to

			// ensure that points are always clockwise - this is required in order to have deterministic behavior for contour merge and split
			String postgisMultiLineString = PostgisHelper.getPostgisLineString(points);
			ResultSet executeQuery = triangulationConnection.createStatement().executeQuery("SELECT ST_OrderingEquals(ST_Makepolygon(" + postgisMultiLineString + "), st_forceRHR(ST_Makepolygon(" + postgisMultiLineString + ")))");
			executeQuery.next();
			if(executeQuery.getBoolean(1)) {
				statement = triangulationConnection.prepareStatement("INSERT INTO contoursplit (m, the_geom, source) VALUES (?, " + postgisMultiLineString + ", ?)");
			} else {
				statement = triangulationConnection.prepareStatement("INSERT INTO contoursplit (m, the_geom, source) VALUES (?, ST_REVERSE(" + postgisMultiLineString + "), ?)");
			}
			
			statement.setInt(1, depth);
			statement.setLong(2, 100L); // no unique id available since it may be set by several tracks - we set a non null value to identify this is our contour line

			Logger.getLogger(getClass()).debug(MessageFormat.format("INSERT INTO contoursplit (m, the_geom, source) VALUES ({0}, {1}, " + 100L + ")", //$NON-NLS-1$
					depth, postgisMultiLineString.toString()));
			statement.addBatch();
			lastStatement = statement;
			batchCounter++;
			if(batchCounter > 50) {
				statement.executeBatch();
			}
			
//			mergeBorderCrossingContours(boundary, boundaryTrianglePairs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}
	public void mergeBorderCrossingContours(IPolygon boundary, List<NeighboringTrianglesOnBoundary> boundaryTrianglePairs) throws PersistenceException {
		try {
			String boundaryString = PostgisHelper.getMultipolygonString(boundary, Collections.<IPolygon> emptyList());
			for (NeighboringTrianglesOnBoundary trianglePair : boundaryTrianglePairs) {
				// expecting only one shared edge causes only one single pair per boundary
				ITriangle innertriangle = trianglePair.getTriangleA();
				String triangleA = PostgisHelper.getPostgisPolygon2DTriangle(innertriangle);
				
				ITriangle outertriangle = trianglePair.getTriangleB();
				String triangleB = PostgisHelper.getPostgisPolygon2DTriangle(outertriangle);
				
				List<IContourLine> contourLinesA = readContourLines(triangleA, boundaryString, true);
				List<IContourLine> contourLinesB = readContourLines(triangleB, boundaryString, false);
				
				List<IContourLine> mergedContourLines = new ArrayList<IContourLine>();
				
				// there must be only one contour line per depth in the given triangles
				for (IContourLine contourLineA : contourLinesA) {
					for (IContourLine contourLineB : contourLinesB) {
						if(contourLineA.getDepth() == contourLineB.getDepth()) {
							IContourLine mergedContourLine = new ContourLine();
							mergedContourLines.add(mergedContourLine);
							mergedContourLine.setDepth(contourLineA.getDepth());
							List<IPoint> pointsA = contourLineA.getPoints();
							int startIndexSegmentA = -1;
							int endIndexSegmentA = -1;
							for (IPoint point : pointsA) {
								for (IEdge edge : innertriangle.getEdges()) {
									if(edge.isOnEdge(point)) {
										if(startIndexSegmentA == -1) {
											startIndexSegmentA = pointsA.indexOf(point);
											break;
										} else {
											endIndexSegmentA = pointsA.indexOf(point);
											break;
										}
									}
								}
							}
							List<IPoint> pointsB = contourLineB.getPoints();
							int startIndexSegmentB = -1;
							int endIndexSegmentB = -1;
							for (IPoint point : pointsB) {
								for (IEdge edge : outertriangle.getEdges()) {
									if(edge.isOnEdge(point)) {
										if(startIndexSegmentB == -1) {
											startIndexSegmentB = pointsB.indexOf(point);
											break;
										} else {
											endIndexSegmentB = pointsB.indexOf(point);
											break;
										}
									}
								}
							}
							if(startIndexSegmentA == 0) {
								// the contour line starts and ends on the given triangle, then add all points
								mergedContourLine.getPoints().addAll(contourLineA.getPoints());
							} else {
								mergedContourLine.getPoints().addAll(contourLineA.getPoints().subList(0, startIndexSegmentA + 1));
							}
							if(startIndexSegmentB == 0) {
								// the contour line starts and ends on the given triangle, then add all points
								mergedContourLine.getPoints().addAll(contourLineB.getPoints());
							} else {
								// the contour line is a mid segment
								mergedContourLine.getPoints().addAll(contourLineB.getPoints().subList(endIndexSegmentB, contourLineB.getPoints().size()));
								mergedContourLine.getPoints().addAll(contourLineB.getPoints().subList(0, startIndexSegmentB + 1));
							}
							if(startIndexSegmentA != 0) {
								mergedContourLine.getPoints().addAll(contourLineA.getPoints().subList(endIndexSegmentA, contourLineA.getPoints().size()));
							}
							
							// write the split contours to the database
							Statement updateStatement = triangulationConnection.createStatement();
							String postgisLineString = PostgisHelper.getPostgisLineString(mergedContourLine.getPoints());
							updateStatement.executeUpdate("DELETE FROM contoursplit WHERE id = " + contourLineA.getId());
							updateStatement.executeUpdate("DELETE FROM contoursplit WHERE id = " + contourLineB.getId());
							updateStatement.executeUpdate("INSERT INTO contoursplit (the_geom, m) VALUES (" + postgisLineString +", " + contourLineA.getDepth() + ")");
						}
					}
				}
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * This method reads all contour lines that intersect the given triangle
	 * 
	 * @param triangleA the triangle to test for intersection
	 * @param boundingPolygon the boundary of the polygon
	 * @param positiveSearch
	 * @return a list of contour lines that intersect the given triangle
	 * @throws SQLException
	 */
	private List<IContourLine> readContourLines(String triangleA, String boundingPolygon, Boolean positiveSearch) throws SQLException {
		List<IContourLine> contourLines = new ArrayList<IContourLine>();
		String queryString = "SELECT id, m, ST_X(geom), ST_Y(geom) FROM (SELECT id, (ST_DUMPPOINTS(the_geom)).*, m FROM contoursplit WHERE ST_INTERSECTS(the_geom, " + triangleA +  ") AND ST_Contains(" + boundingPolygon + ", the_geom) IS " + positiveSearch.toString() + ") AS g";
		PreparedStatement contourStatement = triangulationConnection.prepareStatement(queryString);
		ResultSet query = contourStatement.executeQuery();
		Long oldId = -1L;
		ContourLine contourLine = null;
		while(query.next()) {
			long currentId = Long.parseLong(query.getString(1));
			if(oldId != currentId) {
				contourLine = new ContourLine();
				contourLine.setId(currentId);
				contourLine.setDepth(Integer.parseInt(query.getString(2)));
				contourLines.add(contourLine);
				oldId = currentId;
			}
			IPoint point = new Point(Double.parseDouble(query.getString(3)), Double.parseDouble(query.getString(4)));
			contourLine.getPoints().add(point);
		}
		// remove last closing polygon point as it may cause trouble during processing
		for (IContourLine contourLineX : contourLines) {
			contourLineX.getPoints().remove(contourLineX.getPoints().size() - 1);
		}
		return contourLines;
	}

	/**
	 * this method calculates the triangles that share a common edge with the given boundary
	 * 
	 * @param boundaryPolygon
	 * @return
	 * @throws PersistenceException
	 */
	public List<NeighboringTrianglesOnBoundary> getBoundaryTrianglePairs(IPolygon boundaryPolygon) throws PersistenceException {
		List<NeighboringTrianglesOnBoundary> pairwiseTriangles = new ArrayList<NeighboringTrianglesOnBoundary>();

		String multipolygonString = createMultipolygonString(boundaryPolygon, Collections.EMPTY_LIST);
		
		String boundaryLineString = PostgisHelper.getPostgisLineString(boundaryPolygon.getPoints());
		

		// Select all triangles that are touching each other on the boundary.
		// Touching triangles might even share a single point so an additional check for a common edge is required
		try {
//			String query = "SELECT a.id, b.id, ST_ASText(a.geom), ST_ASText(b.geom) FROM triangulation AS a JOIN triangulation AS b ON ST_Touches(a.geom, b.geom) AND a.geom && b.geom AND a.id != b.id AND ST_Contains("
//					+ multipolygonString + ", a.geom) IS TRUE AND ST_Contains(" + multipolygonString + ", b.geom) IS FALSE";System.out.println(query);
//			String query = "SELECT a.id, b.id, ST_ASText(a.geom), ST_ASText(b.geom) FROM triangulation AS a JOIN triangulation AS b ON ST_Touches(a.geom, b.geom) AND a.geom && b.geom AND a.id != b.id AND ST_Contains("
//							+ multipolygonString + ", a.geom) IS TRUE AND ST_Contains(ST_InteriorRingN(" + multipolygonString + ", 1), b.geom) IS FALSE";System.out.println(query);
			String dropPolygon = "DROP TABLE big_polygon";
			String dumpPolygon = "CREATE TABLE big_polygon as SELECT (ST_Dump(" + boundaryLineString + ")).geom as geom";
			String addPrimaryKey = "ALTER table big_polygon ADD Column gid serial PRIMARY KEY";
			String createIndex = "CREATE INDEX idx_big_polygon_geom on big_polygon USING gist(geom)";
			String analyze = "analyze big_polygon";
			try {
				triangulationConnection.createStatement().execute(dropPolygon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			triangulationConnection.createStatement().execute(dumpPolygon);
			triangulationConnection.createStatement().execute(addPrimaryKey);
			triangulationConnection.createStatement().execute(createIndex);
			triangulationConnection.createStatement().execute(analyze);

			String dropPolygon2 = "DROP TABLE big_polygon2";
			String dumpPolygon2 = "CREATE TABLE big_polygon2 as SELECT (ST_Dump(" + multipolygonString + ")).geom as geom";
			String addPrimaryKey2 = "ALTER table big_polygon2 ADD Column gid serial PRIMARY KEY";
			String createIndex2 = "CREATE INDEX idx_big_polygon_geom2 on big_polygon2 USING gist(geom)";
			String analyze2 = "analyze big_polygon2";
			try {
				triangulationConnection.createStatement().execute(dropPolygon2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			triangulationConnection.createStatement().execute(dumpPolygon2);
			triangulationConnection.createStatement().execute(addPrimaryKey2);
			triangulationConnection.createStatement().execute(createIndex2);
			triangulationConnection.createStatement().execute(analyze2);

//			String query = "SELECT a.id, b.id, ST_ASText(a.geom), ST_ASText(b.geom) FROM triangulation AS a JOIN triangulation AS b ON ST_Touches(a.geom, b.geom) AND a.geom && b.geom AND a.id != b.id AND ST_Contains("
//			+ multipolygonString + ", a.geom) IS TRUE AND ST_Contains(" + multipolygonString + ", b.geom) IS FALSE";


			Map<String, ITriangle> innerTris = new HashMap<String, ITriangle>();
			Map<String, ITriangle> outerTris = new HashMap<String, ITriangle>();
			Set<String> innerids = new HashSet<String>();
			Set<String> outerids = new HashSet<String>();
			List<ITriangle> innerTriangles = new ArrayList<ITriangle>();
			List<ITriangle> outerTriangles = new ArrayList<ITriangle>();

			String query = "SELECT id, ST_ASText(triangulation.geom) from triangulation, big_polygon, big_polygon2 WHERE ST_Intersects(big_polygon.geom, triangulation.geom) AND ST_Contains(big_polygon2.geom, triangulation.geom)";
//			String query = "SELECT a.id, b.id, ST_ASText(a.geom), ST_ASText(b.geom) FROM (SELECT id,geom FROM triangulation, big_polygon WHERE ST_Touches(big_polygon.geom, geom)) AS a JOIN (SELECT id,geom FROM triangulation, big_polygon WHERE ST_Touches(" + boundaryLineString + ", geom)) AS b ON ST_Touches(a.geom, b.geom) AND a.geom && b.geom AND a.id != b.id AND ST_Contains("
//									+ multipolygonString + ", a.geom) IS TRUE AND ST_Contains(" + multipolygonString + ", b.geom) IS FALSE";
			PreparedStatement prepareStatement = triangulationConnection.prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				if (!innerids.contains(resultSet.getString(1))) {
					innerids.add(resultSet.getString(1));
					ITriangle triangleFromPolygon = PostgisHelper.getTriangleFromPostgisPolygon2D(resultSet.getString(2));
					innerTriangles.add(triangleFromPolygon);
					innerTris.put(resultSet.getString(1), triangleFromPolygon);
				}

			}
			String queryOuter = "SELECT id, ST_ASText(triangulation.geom) from triangulation, big_polygon, big_polygon2 WHERE ST_Intersects(big_polygon.geom, triangulation.geom) AND ST_Contains(big_polygon2.geom, triangulation.geom) IS FALSE";
//			String query = "SELECT a.id, b.id, ST_ASText(a.geom), ST_ASText(b.geom) FROM (SELECT id,geom FROM triangulation, big_polygon WHERE ST_Touches(big_polygon.geom, geom)) AS a JOIN (SELECT id,geom FROM triangulation, big_polygon WHERE ST_Touches(" + boundaryLineString + ", geom)) AS b ON ST_Touches(a.geom, b.geom) AND a.geom && b.geom AND a.id != b.id AND ST_Contains("
//									+ multipolygonString + ", a.geom) IS TRUE AND ST_Contains(" + multipolygonString + ", b.geom) IS FALSE";
			PreparedStatement outerStatement = triangulationConnection.prepareStatement(queryOuter);
			resultSet = outerStatement.executeQuery();
			while (resultSet.next()) {
				if (!outerids.contains(resultSet.getString(1))) {
					outerids.add(resultSet.getString(1));
					ITriangle triangleFromPolygon = PostgisHelper.getTriangleFromPostgisPolygon2D(resultSet.getString(2));
					outerTriangles.add(triangleFromPolygon);
					outerTris.put(resultSet.getString(1), triangleFromPolygon);
				}

			}
			Map<String, String> reverseMap = new HashMap<String, String>();
			// now check the pairs for a common edge
			for (Entry<String, ITriangle> outerTriangle : outerTris.entrySet()) {
				for (Entry<String, ITriangle> innerTriangle : innerTris.entrySet()) {
					if(!outerTriangle.getKey().equals(innerTriangle.getKey())) {
						IEdge sharedEdge = innerTriangle.getValue().getSharedEdge(outerTriangle.getValue());
						String oppositeTriangelId = reverseMap.get(innerTriangle.getKey());
//						for (int i = 0 ; i < boundaryPolygon.getPoints().size() - 1 ; i++) {
//							System.out.println(							boundaryPolygon.getPoints().get(0).getDistance(sharedEdge.getOrigin()));
//						}
						if (sharedEdge != null && (oppositeTriangelId == null || !oppositeTriangelId.equals(outerTriangle.getKey()))) {
							reverseMap.put(outerTriangle.getKey(), innerTriangle.getKey());
							NeighboringTrianglesOnBoundary neighboringTrianglesOnBoundary = new NeighboringTrianglesOnBoundary(innerTriangle.getKey(), outerTriangle.getKey(), innerTriangle.getValue(),
									outerTriangle.getValue());
							pairwiseTriangles.add(neighboringTrianglesOnBoundary);
						}
					}
				}
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		return pairwiseTriangles;
	}

	@Override
	public List<ITriangulationDescription> updateContainedTriangulations(Iterator<List<IPolygon>> polygonIterator, final String trackpointTable) throws PersistenceException {
		List<ITriangulationDescription> descriptions = new ArrayList<ITriangulationDescription>();
		List<IPolygon> boundaryAndHoles = null;
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		List<Future<TriangulationDescription>> futures = new ArrayList<Future<TriangulationDescription>>();
		while ((boundaryAndHoles = polygonIterator.next()) != null) {
			final List<IPolygon> x = boundaryAndHoles;  
			Callable<TriangulationDescription> callable = new Callable<TriangulationDescription>() {

				@Override
				public TriangulationDescription call() throws Exception {
					IPolygon boundary = x.get(0);
					if (boundary.getPoints().size() > 2) {
						List<IPolygon> holes = Collections.<IPolygon> emptyList();
						if (x.size() > 1) {
							holes = x.subList(1, x.size());
						}
						// select all triangles that are contained in the boundary excluded by the holes
						
						// this method should be responsible for picking the right points and remove duplicates or do any postprocessing for
						// duplicate points based on confidence
						List<IPoint> measuredDepthPoints = getMeasuredDepthPoints(boundary, holes, trackpointTable);
						if (!measuredDepthPoints.isEmpty()) {
							ITriangulator triangulator = triangulationFactory.createTriangulator();
							if (boundary.getPoints().size() > 2) {
								triangulator.addBoundary(boundary);
								if (x.size() > 1) {
									triangulator.addHoles(x.subList(1, x.size()));
								}
								try {
									for (IPoint point : measuredDepthPoints) {
										triangulator.addPoint(point);
									}
									// execute the triangulation and persist it
									triangulator.triangulate(PostgisTriangulationPersistence.this);
									List<ITriangle> triangulation = triangulator.getTriangulation();
									TriangulationDescription triangulationDescription = new TriangulationDescription(boundary, holes, triangulation);
									persistTriangulation(triangulation, triangulationDescription);
									return triangulationDescription;
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					return null;
				}
			};
			Future<TriangulationDescription> future = threadPool.submit(callable);
			futures.add(future);
		}
		for (Future<TriangulationDescription> future : futures) {
			TriangulationDescription triangulationDescription;
			try {
				triangulationDescription = future.get();
				if(triangulationDescription != null) {
					descriptions.add(triangulationDescription);
				}
			} catch (InterruptedException e) {
				Logger.getLogger(getClass()).error("Failed", e);
			} catch (ExecutionException e) {
				Logger.getLogger(getClass()).error("Failed", e);
			}
		}
		return descriptions;
	}

	@Override
	public void removeContourLines(IPolygon border, List<IPolygon> holes) throws PersistenceException {
		Statement deleteStatement = null;
		try {
			deleteStatement = triangulationConnection.createStatement();
			String multipolygonString = createMultipolygonString(border, Collections.EMPTY_LIST);
			String x = "ST_Buffer (" + multipolygonString + ", 0.000000001)";
			deleteStatement.execute("DELETE FROM contoursplit WHERE ST_Contains(" + x + ",the_geom)");
		} catch (SQLException e) {
			try {
				if (deleteStatement != null) {
					deleteStatement.close();
				}
			} catch (SQLException e1) {
				throw new PersistenceException(e1);
			}
			throw new PersistenceException(e);
		}

	}

	public void trianglesOnWaterBoundaries(IPolygon boundaryPolygon, List<IPolygon> holes) {
		String multipolygonString = createMultipolygonString(boundaryPolygon, Collections.EMPTY_LIST);
		try {
			Statement statement = triangulationConnection.createStatement();
			// statement.executeUpdate("DELETE FROM triangulation2");
			// exclude the area of interest - hm the boundary polygon
			// SELECT ALL triangles where triangles not contained in the given
			// polygon and triangles intersect the given polygon
			PreparedStatement prepareStatement = triangulationConnection.prepareStatement("SELECT id, ST_ASText(geom) FROM triangulation WHERE ST_Contains(" + multipolygonString
					+ ", geom) IS FALSE AND ST_Intersects(geom, " + multipolygonString + ")");
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + ":" + resultSet.getString(2));
				statement = triangulationConnection.createStatement();
				statement.executeUpdate("INSERT INTO triangulation2 (geom) VALUES (ST_GeomFRomText('" + resultSet.getString(2) + "',4326))");
			}
			System.out.println(multipolygonString);

			// mehr als ein triangle an der boundary beteiligt - wie detektieren
			// ?
			// ein triangle ist der sonderfall ....

			// select contour lines that intersect the given triangles
			// PreparedStatement contourLineStatement =
			// triangulationConnection.prepareStatement("SELECT ST_ASText(a.geom) FROM triangulation AS a, triangulation AS b WHERE ST_Contains("
			// + multipolygonString +
			// ", b.geom) IS FALSE AND ST_Intersects(b.geom, " +
			// multipolygonString +
			// ") AND ST_TOUCHES(a.geom, b.geom) AND ST_Contains(" +
			// multipolygonString + ", a.geom) IS FALSE");
			// ResultSet contourLineResultSet =
			// contourLineStatement.executeQuery();
			// while(contourLineResultSet.next()) {
			// System.out.println(contourLineResultSet.getString(1));
			// statement = triangulationConnection.createStatement();
			// statement.executeUpdate("INSERT INTO triangulation2 (geom) VALUES (ST_GeomFRomText('"
			// + contourLineResultSet.getString(1) + "',4326))");
			// }
			// get the intersection pouints

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String args[]) throws FileNotFoundException, IOException, SQLException {
//		Properties properties = new Properties();
//		properties.load(new FileInputStream("config.cfg"));
//		Connection connection = PostgresConnectionFactory.getDBConnection(properties, "gebco"); //$NON-NLS-1$
//		Connection inshoreConnection = PostgresConnectionFactory.getDBConnection(properties, "gis"); //$NON-NLS-1$
//		PostgisTriangulationPersistence persistence = new PostgisTriangulationPersistence();
//		persistence.bindTriangulationConnection(connection);
//		persistence.bindInshoreConnection(inshoreConnection);
//		
//		try {
//			PolygonIterator hitPartitionizedPolygons = persistence.getHitPartitionizedPolygons(1L, "xxx");
//			List<IPolygon> next = null;
//			while ((next = hitPartitionizedPolygons.next()) != null) {
//				IPolygon boundary = next.get(0);
//				List<IPolygon> holes = new ArrayList<IPolygon>();
//				if (next.size() > 1) {
//					holes = next.subList(1, next.size() - 1);
//				}
//				System.out.println(boundary);
//			}
//		} catch (PersistenceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_line WHERE way && ST_Transform(ST_MakeEnvelope(7.5, 48.0, 8.5, 49, 4326), 900913) ) AS xx "
		// +
//		double xmin = 8.1630;
//		double ymin = 48.90;
//		double xmax = 8.19;
//		double ymax = 48.94;
//
//		Statement xstatement = inshoreConnection.createStatement();
//
//		ResultSet resultSet = xstatement.executeQuery("SELECT path, ST_X(ST_TRANSFORM(geom,4326)) AS lon, ST_Y(ST_TRANSFORM(geom,4326)) as lat, osm_id FROM " + "(SELECT * FROM ("
//				+ "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE (ST_Overlaps(way, ST_Transform(ST_MakeEnvelope(" + xmin + ", " + ymin + ", " + xmax + ", " + ymax
//				+ ", 4326), 900913)) OR ST_Contains(ST_Transform(ST_MakeEnvelope(" + xmin + ", " + ymin + ", " + xmax + ", " + ymax
//				+ ", 4326), 900913), way))  AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  ) as g ");
//		// formatter:on
//		PolygonIterator waterPolygonIterator = new PolygonIterator(resultSet);
//
//		Statement statement = connection.createStatement();
//		statement.executeUpdate("DELETE FROM triangulation2");
//		// statement.executeUpdate("INSERT INTO triangulation (geom) VALUES (ST_GEOMFromTEXT('POLYGON((8.18556600921013 48.939812274246 0, 8.18513293141165 48.9392652858934 0 , 8.185097821120 48.939464876142 0, 8.18556600921013 48.939812274246 0))', 4326))");
//
//		List<IPolygon> next = null;
//		while ((next = waterPolygonIterator.next()) != null) {
//			IPolygon boundary = next.get(0);
//			List<IPolygon> holes = new ArrayList<IPolygon>();
//			if (next.size() > 1) {
//				holes = next.subList(1, next.size() - 1);
//			}
//			persistence.trianglesOnWaterBoundaries(boundary, holes);
//		}
//	}

	public void bindInshoreConnection(Connection connection) {
		this.inshoreConnection = connection;
	}

	public void unbindInshoreConnection(Connection connection) {
		this.inshoreConnection = null;
	}

	public void bindTriangulationConnection(Connection connection) {
		this.triangulationConnection = connection;
	}

	public void unbindTriangulationConnection(Connection connection) {
		this.triangulationConnection = null;
	}

	public void bindTriangulationFactory(ITriangulationFactory triangulationFactory) {
		this.triangulationFactory = triangulationFactory;
	}

	public void unbindTriangulationFactory(ITriangulationFactory triangulationFactory) {
		this.triangulationFactory = null;
	}

	@Override
	public void finishAddOrUpdateContourLines() throws PersistenceException {
		try {
			if(lastStatement != null) {
				lastStatement.executeBatch();
				lastStatement = null;
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
