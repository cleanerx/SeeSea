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

package net.sf.seesea.contour.triangulation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.seesea.contour.IContourLineGeneration;
import net.sf.seesea.data.io.PersistenceException;
import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.ITriangle;
import net.sf.seesea.geometry.impl.Point;
import net.sf.seesea.triangulation.ITriangulationDescription;
import net.sf.seesea.triangulation.ITriangulationPersistence;
import net.sf.seesea.triangulation.NeighboringTrianglesOnBoundary;

import org.apache.log4j.Logger;

/**
 * This class generates contour lines from a triangulation and persists the resulting contour lines
 */
public class TriangulationBasedContourLineGeneration implements IContourLineGeneration {

	private ITriangulationPersistence triangulationPersistence;

	public void updateContourLines(Set<Long> trackIds, List<Integer> depths, String trackpointTable, PreparedStatement markTrackFileTriangulated) {
		try {
			// 1 determine which partition areas are hit given the point list (hm or the bounding box for performance)
			Iterator<List<IPolygon>> hitPartitionizedPolygons = triangulationPersistence.getHitPartitionizedPolygons(trackIds, trackpointTable);
			// do a complete retriangulation of the areas at hand
			if(hitPartitionizedPolygons != null) {
				List<ITriangulationDescription> triangulation = triangulationPersistence.updateContainedTriangulations(hitPartitionizedPolygons, trackpointTable);
//				for (Long trackId : trackIds) {
//					try {
//						markTrackFileTriangulated.setLong(1, trackId);
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
				// for each triangulation generate the associated contour lines
				for (ITriangulationDescription triangulationDescription : triangulation) {
					try {
						updateDatabaseContourLines(triangulationDescription, depths);
					} catch (PersistenceException e) {
						Logger.getLogger(getClass()).error("Failed to update contour line with tracks" + trackIds, e);
					}
				}
			}
			
		} catch (PersistenceException e) {
			Logger.getLogger(getClass()).error("Failed to update contour line for track " + trackIds, e);
		}
	}

	/**
	 * This method creates and updates contour lines in the given triangulation area.
	 * 
	 * @param triangulationDescription
	 * @param depths
	 * @return
	 * @throws PersistenceException
	 */
	private List<List<IPoint>> updateDatabaseContourLines(final ITriangulationDescription triangulationDescription, List<Integer> depths) throws PersistenceException {
		final List<List<IPoint>> contourLines = Collections.synchronizedList(new ArrayList<List<IPoint>>());

		final List<NeighboringTrianglesOnBoundary> boundaryTrianglePairs = triangulationPersistence.getBoundaryTrianglePairs(triangulationDescription.getBorder());
		
		if(!Boolean.getBoolean("disableMerge")) {
			// split boundary crossing contour lines again to reflect the partitionized state
			triangulationPersistence.splitMergedContourLines(triangulationDescription.getBorder(), boundaryTrianglePairs);
		}

		// remove all contour lines that are completely contained in this area since a new triangulation is present
		triangulationPersistence.removeContourLines(triangulationDescription.getBorder(), triangulationDescription.getHoles());

		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		// for each depth write the contour line
		for (final Integer depth : depths) {
			Set<ITriangle> visitedTriangles = new HashSet<ITriangle>();
			for (Iterator<ITriangle> iterator = triangulationDescription.getTriangulation().iterator(); iterator.hasNext();) {
				ITriangle triangle = (ITriangle) iterator.next();
				if (!visitedTriangles.contains(triangle)) {
					List<IPoint> points = new ArrayList<IPoint>();
					recursiveContourlineGeneration(visitedTriangles, triangle, null, null, points, depth);
					if (points.size() > 3) {
						triangulationPersistence.addOrUpdateContourLine(points, depth, triangulationDescription.getBorder(), boundaryTrianglePairs);
						contourLines.add(points);
					}
				}
			}
			triangulationPersistence.finishAddOrUpdateContourLines();
//			threadPool.submit(new Callable<Void>() {
//
//				@Override
//				public Void call() throws Exception {
//					return null;
//				}
//			});
		}
		if(!Boolean.getBoolean("disableMerge")) {
			triangulationPersistence.mergeBorderCrossingContours(triangulationDescription.getBorder(), boundaryTrianglePairs);
		}
		return contourLines;
	}
	
	/**
	 * This methods generates contour lines based on the given triangulation
	 * 
	 * @param visitedTriangles the visited triangles for the given depth
	 * @param triangle the current triangle check and interpolate on
	 * @param borderPoint1
	 * @param borderPoint2
	 * @param countourPoints the resulting contour line points
	 * @param depth the depth to create contour lines for
	 */
	public void recursiveContourlineGeneration(Set<ITriangle> visitedTriangles, ITriangle triangle, IPoint borderPoint1, IPoint borderPoint2,
			List<IPoint> countourPoints, double depth) {
		IPoint p1 = (IPoint) triangle.getPoints().get(0);
		IPoint p2 = (IPoint) triangle.getPoints().get(1);
		IPoint p3 = (IPoint) triangle.getPoints().get(2);
		if (visitedTriangles.contains(triangle)) {
			return;
		}
		// intersects between p1 and p2
		if (intersectsPoints(borderPoint1, borderPoint2, depth, p1, p2)) {
			double percent = getLinearPercent(depth, p1, p2);
			double x = getLinearX(p1, p2, percent);
			double y = getLinearY(p1, p2, percent);
			visitedTriangles.add(triangle);
			countourPoints.add(new Point(x, y));
			ITriangle nextTriangle = getNextContourTriangle(triangle, borderPoint1, borderPoint2, p1, p2);
			if (!visitedTriangles.contains(nextTriangle) && nextTriangle != null) {
				recursiveContourlineGeneration(visitedTriangles, nextTriangle, p1, p2, countourPoints, depth);
			}
		} else if (intersectsPoints(borderPoint1, borderPoint2, depth, p2, p1)) {
			double percent = getLinearPercent(depth, p2, p1);
			double x = getLinearX(p2, p1, percent);
			double y = getLinearY(p2, p1, percent);
			countourPoints.add(new Point(x, y));
			visitedTriangles.add(triangle);
			ITriangle nextTriangle = getNextContourTriangle(triangle, borderPoint1, borderPoint2, p2, p1);
			if (!visitedTriangles.contains(nextTriangle) && nextTriangle != null) {
				recursiveContourlineGeneration(visitedTriangles, nextTriangle, p2, p1, countourPoints, depth);
			}
		} else if (intersectsPoints(borderPoint1, borderPoint2, depth, p3, p2)) {
			double percent = getLinearPercent(depth, p3, p2);
			double x = getLinearX(p3, p2, percent);
			double y = getLinearY(p3, p2, percent);
			countourPoints.add(new Point(x, y));
			visitedTriangles.add(triangle);
			ITriangle nextTriangle = getNextContourTriangle(triangle, borderPoint1, borderPoint2, p3, p2);
			if (!visitedTriangles.contains(nextTriangle) && nextTriangle != null) {
				recursiveContourlineGeneration(visitedTriangles, nextTriangle, p3, p2, countourPoints, depth);
			}
		} else if (intersectsPoints(borderPoint1, borderPoint2, depth, p2, p3)) {
			double percent = getLinearPercent(depth, p2, p3);
			double x = getLinearX(p2, p3, percent);
			double y = getLinearY(p2, p3, percent);
			countourPoints.add(new Point(x, y));
			visitedTriangles.add(triangle);
			ITriangle nextTriangle = getNextContourTriangle(triangle, borderPoint1, borderPoint2, p2, p3);
			if (!visitedTriangles.contains(nextTriangle) && nextTriangle != null) {
				recursiveContourlineGeneration(visitedTriangles, nextTriangle, p2, p3, countourPoints, depth);
			}
		} else if (intersectsPoints(borderPoint1, borderPoint2, depth, p3, p1)) {
			double percent = getLinearPercent(depth, p3, p1);
			double x = getLinearX(p3, p1, percent);
			double y = getLinearY(p3, p1, percent);
			countourPoints.add(new Point(x, y));
			visitedTriangles.add(triangle);
			ITriangle nextTriangle = getNextContourTriangle(triangle, borderPoint1, borderPoint2, p3, p1);
			if (!visitedTriangles.contains(nextTriangle) && nextTriangle != null) {
				recursiveContourlineGeneration(visitedTriangles, nextTriangle, p3, p1, countourPoints, depth);
			}
		} else if (intersectsPoints(borderPoint1, borderPoint2, depth, p1, p3)) {
			double percent = getLinearPercent(depth, p1, p3);
			double x = getLinearX(p1, p3, percent);
			double y = getLinearY(p1, p3, percent);
			countourPoints.add(new Point(x, y));
			visitedTriangles.add(triangle);
			ITriangle nextTriangle = getNextContourTriangle(triangle, borderPoint1, borderPoint2, p1, p3);
			if (!visitedTriangles.contains(nextTriangle) && nextTriangle != null) {
				recursiveContourlineGeneration(visitedTriangles, nextTriangle, p1, p3, countourPoints, depth);
			}
		}

	}

	private boolean intersectsPoints(IPoint borderPoint1, IPoint borderPoint2, double depth, IPoint p2, IPoint p3) {
		return p2.getZ() <= depth && p3.getZ() > depth && !(p2.equals(borderPoint1) && p3.equals(borderPoint2));
	}

	public double getLinearPercent(double depth, IPoint p1, IPoint p2) {
		return Math.abs(p1.getZ() - depth) / Math.abs(p1.getZ() - p2.getZ());
	}

	public double getLinearX(IPoint p1, IPoint p2, double percent) {
		return p1.getX() + (percent * (p2.getX() - p1.getX()));
	}

	public double getLinearY(IPoint p1, IPoint p2, double percent) {
		return p1.getY() + (percent * (p2.getY() - p1.getY()));
	}

	private ITriangle getNextContourTriangle(ITriangle triangle_dt, IPoint borderPoint1, IPoint borderPoint2, IPoint p1, IPoint p2) {
		ITriangle nextTriangle = null;
		for (ITriangle triangle : triangle_dt.getNeighbors()) {
			if (triangle != null
					&& (triangle.getPoints().contains(p1) && triangle.getPoints().contains(p2) && !(triangle.getPoints().contains(borderPoint1) && triangle
							.getPoints().contains(borderPoint2)))) {
				nextTriangle = triangle;
				break;
			}
		}
		return nextTriangle;
	}

	public void bindTriangulationPersistence(ITriangulationPersistence triangulationPersistence) {
		this.triangulationPersistence = triangulationPersistence;
	}

	public void unbindTriangulationPersistence(ITriangulationPersistence triangulationPersistence) {
		this.triangulationPersistence = null;
	}
	
//	public static void main(String args[]) throws FileNotFoundException, IOException, SQLException {
//		Properties properties = new Properties();
//		properties.load(new FileInputStream("config.cfg"));
//		Connection connection = PostgresConnectionFactory.getDBConnection(properties, "gebco"); //$NON-NLS-1$
//		Connection inshoreConnection = PostgresConnectionFactory.getDBConnection(properties, "gis"); //$NON-NLS-1$
//		PostgisTriangulationPersistence postgisTriangulationPersistence = new PostgisTriangulationPersistence();
//		postgisTriangulationPersistence.bindTriangulationConnection(connection);
//		postgisTriangulationPersistence.bindInshoreConnection(inshoreConnection);
//		DelaunayTriangulationBasedContourLineGeneration delaunayTriangulationBasedContourLineGeneration = new DelaunayTriangulationBasedContourLineGeneration();
//		delaunayTriangulationBasedContourLineGeneration.bindTriangulationPersistence(postgisTriangulationPersistence);
//		delaunayTriangulationBasedContourLineGeneration.initialize();
////		try {
////			List<ITriangulationDescription> partitionizedTriangulations = postgisTriangulationPersistence.getPartitionizedTriangulations(14969L);
////			return;
////		} catch (PersistenceException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
	
}

