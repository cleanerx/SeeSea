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
package net.sf.seesea.triangulation.cdelaunay;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import net.sf.seesea.data.io.PersistenceException;
import net.sf.seesea.geometry.IBoxExtends;
import net.sf.seesea.geometry.ICircle;
import net.sf.seesea.geometry.IEdge;
import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.ITriangle;
import net.sf.seesea.geometry.impl.DirectedEdge;
import net.sf.seesea.geometry.impl.MultiArrrayList;
import net.sf.seesea.geometry.impl.Point;
import net.sf.seesea.geometry.impl.Triangle;
import net.sf.seesea.geometry.impl.TriangleComparator;
import net.sf.seesea.triangulation.ITriangulationPersistence;
import net.sf.seesea.triangulation.ITriangulator;

import org.eclipse.core.runtime.Assert;

/**
 * This implementation is based upon:<br>
 * An improved incremental algorithm for constructing restricted Delaunay triangulations<br>
 * by Marc Vigo Anglada<br>
 * <br>
 * For improved search capabilites a quadtree structure is being used.<br>
 * The data structure used for triangles differs slightly from what is presented in the paper but still does the same trick. 
 * <br>
 *
 */
public class IncrementalConstrainedDelaunayTriangulation implements
		ITriangulator {
	
	private List<ITriangle> triangles = new ArrayList<ITriangle>();
	
	private List<IPoint> points;
	
	private Set<IPoint> triangulationPoints = new LinkedHashSet<IPoint>();

	private IPolygon boundary;

	private List<IPolygon> holes;

	private Collection<IEdge> constraintEdges;

	private long startTime;

	private Quadtree<IBoxExtends> quadtree;

	private ITriangulationPersistence triangulationPersistence;
	
	private int edgeCounter = 0;
	
	public IncrementalConstrainedDelaunayTriangulation() {
		points = new MultiArrrayList();
		constraintEdges = new HashSet<IEdge>();
		
	}

	@Override
	public void addPoint(IPoint point) {
		points.add(point);
	}

	@Override
	public void addHoles(List<IPolygon> holes) {
		this.holes = holes;
	}

	@Override
	public void addBoundary(IPolygon boundary) {
		this.boundary = boundary;
	}
	
	private Collection<IEdge> getContraintEdges() {
		return constraintEdges;
	}
	
	public void triangulate(ITriangulationPersistence postgisTriangulationPersistence) {
		long start = System.currentTimeMillis(); 
		this.triangulationPersistence = postgisTriangulationPersistence;
		startTime = System.currentTimeMillis();
		triangles = new ArrayList<ITriangle>();
		triangulationPoints = new HashSet<IPoint>();
		// create super triangle
		ITriangle superTriangle = createSuperTriangle();
		quadtree = new Quadtree<IBoxExtends>(1, superTriangle.getBoundingBox());
		addTriangle(triangles,superTriangle);
		System.out.println("Initial triangles count:" + triangles.size() + " Quadtree size:" + quadtree.size());
		long time = System.currentTimeMillis();
		
		if(holes != null) {
			x : for (IPolygon polygon : holes) {
				List<IPoint> boundaryPoints = polygon.getPoints();
				for (int i = 0; i < boundaryPoints.size() - 1; i++) {
					IPoint point = boundaryPoints.get(i);
					IPoint point2 = boundaryPoints.get(i + 1);
					IEdge edge = new DirectedEdge(point, point2);
					addPoint2Triangulation(edge.getOrigin(), false);
					triangulationPoints.add(point);
					addPoint2Triangulation(edge.getDestination(), false);
					triangulationPoints.add(point2);
					if(i % 100 == 0) {
//					List<Integer> sizes = new ArrayList<Integer>();
//					quadtree.stats(sizes);
//					double x = 0;
//					for (Integer integer : sizes) {
//						x += integer;
//					} + ": Average node fill:" + (x / sizes.size())
						System.out.println(i + ":" + (System.currentTimeMillis() - time) );
						time = System.currentTimeMillis();
					}
					addEdge2Triangulation(edge, triangles, superTriangle);
				}
			}
		}
		
		List<IPoint> boundaryPoints = boundary.getPoints();
//		for (IPoint a : boundaryPoints) {
//			System.out.println(a);
//			for (IPoint b : boundaryPoints) {
//				if(b.equals(a) && a != b) {
//					System.out.println("what ?" + boundaryPoints.indexOf(a) + ":" + boundaryPoints.lastIndexOf(b));
//				}
//			}
//		}
		for (int i = 0; i < boundaryPoints.size() - 1; i++) {
			IPoint point = boundaryPoints.get(i);
			IPoint point2 = boundaryPoints.get(i + 1);
			boolean addedPointA = false;
			boolean addedPointB = false;
			if(!triangulationPoints.contains(point)) {
				addedPointA = addPoint2Triangulation(point, false);
				if(addedPointA) {
					triangulationPoints.add(point);
				}
			}
			if(!triangulationPoints.contains(point2)) {
				addedPointB = addPoint2Triangulation(point2, false);	
				if(addedPointB) {
					triangulationPoints.add(point2);
				}
			}
			while(!addedPointB) {
				System.out.println("Skipped point " + point2 );
				point2 = boundaryPoints.get(++i + 1);
				if(!triangulationPoints.contains(point2)) {
					addedPointB = addPoint2Triangulation(point2, false);	
					if(addedPointB) {
						triangulationPoints.add(point2);
					}
				}
			}
			IEdge edge = new DirectedEdge(point, point2);
			addEdge2Triangulation(edge, triangles, superTriangle);
			if((i % 1000) == 0) {
				System.out.println("processed " + i + " boundary points" + (System.currentTimeMillis() - start));
			}
		}
		for (ITriangle triangle : triangles) {
			IPoint a = triangle.getPoints().get(0);
			IPoint b = triangle.getPoints().get(1);
			IPoint c = triangle.getPoints().get(2);
			if(boundCheck(a, b) || boundCheck(b, a) || boundCheck(a, c) || boundCheck(c, a) || boundCheck(b, c) || boundCheck(c, a)) {
//				postgisTriangulationPersistence.persistTriangulation(triangles, null);
				System.out.println("x");
			}
//			System.out.println(triangle);
		}

		triangulateOnInputPoints();
		
		y : for (Iterator<ITriangle> iterator = triangles.iterator(); iterator.hasNext();) {
		ITriangle triangle = iterator.next();
			if(!boundary.contains(triangle.getCentroid())) {
				iterator.remove();
				continue;
			}
			if(holes != null) {
				for (IPolygon polygon : holes) {
					if(polygon.contains(triangle.getCentroid())) {
						iterator.remove();
						continue y;
					}
				}
			}
	}

		
		System.out.println("Completed triangulation in " + (System.currentTimeMillis() - startTime) + "ms with " + triangles.size() + " triangles");
	}

	private void triangulateOnInputPoints() {
		int j = 0;
		for (Iterator<IPoint> iterator = points.iterator(); iterator.hasNext();) {
			IPoint point = (IPoint) iterator.next();
			addPoint2Triangulation(point, false);
			triangulationPoints.add(point);
			j++;
			if(j % 1000 == 0) {
				System.out.println("Processed points:" + j);
			}
		}
	}
	
	private void addTriangle(List<ITriangle> triangles2,
			ITriangle createSuperTriangle) {
		triangles2.add(createSuperTriangle);
		quadtree.insert(createSuperTriangle);
	}

	private boolean removeTriangle(List<ITriangle> triangles2,
			ITriangle triangle) {
		boolean remove = triangles2.remove(triangle);
		quadtree.remove(triangle);
		return remove;
	}

	private ITriangle createSuperTriangle() {
		double xMin = boundary.getPoints().get(0).getX();
		double yMin = boundary.getPoints().get(0).getY();
		double xMax = xMin;
		double yMax = yMin;

		for (IPoint p : boundary.getPoints()) {
			if (p.getX() < xMin) {
				xMin = p.getX();
			}
			if (p.getX() > xMax) {
				xMax = p.getX();
			}
			if (p.getY() < yMin) {
				yMin = p.getY();
			}
			if (p.getY() > yMax) {
				yMax = p.getY();
			}
		}

		// build triangle that contains the min and max values
		double dx = xMax - xMin;
		double dy = yMax - yMin;
		double dMax = dx > dy ? dx : dy;
		double xMid = (xMin + xMax) / 2f;
		double yMid = (yMin + yMax) / 2f;

		Triangle superTriangle = new Triangle(
			new Point(xMid - 2f * dMax, yMid - dMax, 0),
			new Point(xMid, yMid + 2f * dMax, 0),
			new Point(xMid + 2f * dMax, yMid - dMax, 0)
		);

		return superTriangle;
	}
	
	private boolean addPoint2Triangulation(IPoint point, boolean containmentCheck) {
		// TODO: point must be in interor 
		// not existing
//		if(triangulationPoints.contains(point)) {
//			return false;
//		}
//		System.out.println(point);
		if(containmentCheck) {
			for(IEdge e : constraintEdges) {
				if(e.isOnEdge(point, 0.00001)) {
					return false;
				}
			}
		}
		
//		Map<ITriangle, Integer> neighbourCount = new HashMap<ITriangle, Integer>();
		
		Stack<ITriangle> stack = new Stack<ITriangle>();
		List<ITriangle> foundTriangles = getTriangle(point);
		// damn its lying on a line of both triangles, resolve both of them in a coordinated fashion
		if(foundTriangles.size() == 2) {
			ITriangle triangleA = foundTriangles.get(0);
			ITriangle triangleB = foundTriangles.get(1);
			removeTriangle(triangles, triangleA);
			removeTriangle(triangles, triangleB);

//			try {
//				triangulationPersistence.persistTriangulation(getTriangulation(), null);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			IEdge sharedEdge = triangleA.getSharedEdge(triangleB);
			IPoint nonSharedPointA = triangleA.oppositePoint(triangleB);
			IPoint nonSharedPointB = triangleB.oppositePoint(triangleA);
			IPoint origin = sharedEdge.getOrigin();
			IPoint destination = sharedEdge.getDestination();
				
			Triangle t1 = new Triangle(nonSharedPointA, origin, point);
			Triangle t2 = new Triangle(nonSharedPointA, point, destination);
			Triangle t3 = new Triangle(nonSharedPointB, origin, point);
			Triangle t4 = new Triangle(nonSharedPointB, point, destination);
			
			Set<ITriangle> neighbors = new LinkedHashSet<ITriangle>();
			neighbors.addAll(triangleA.getNeighbors());
			neighbors.addAll(triangleB.getNeighbors());
			neighbors.remove(triangleA);
			neighbors.remove(triangleB);
			List<ITriangle> tris = new ArrayList<>(4);
			tris.add(t1);
			tris.add(t2);
			tris.add(t3);
			tris.add(t4);

			neighbors.addAll(tris);
			for (ITriangle neighbour : neighbors) {
//				neighbourCount.put(neighbour, neighbour.getNeighbors().size());
				neighbour.getNeighbors().remove(triangleA);
				neighbour.getNeighbors().remove(triangleB);
				for (ITriangle n : neighbors) {
					if(neighbour != n && neighbour.getSharedEdge(n) != null) {
						n.getNeighbors().add(neighbour);
						neighbour.getNeighbors().add(n);
					}
				}
			}
			addTriangle(triangles, t1);
//			try {
//				triangulationPersistence.persistTriangulation(getTriangulation(), null);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			addTriangle(triangles, t2);
//			try {
//				triangulationPersistence.persistTriangulation(getTriangulation(), null);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			addTriangle(triangles, t3);
//			try {
//				triangulationPersistence.persistTriangulation(getTriangulation(), null);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			addTriangle(triangles, t4);
			

			stack.addAll(tris);
			while(!stack.isEmpty()) {
				ITriangle t = stack.pop();
				ITriangle oppositeTriangle = t.opposedTriangle(point);
//			System.out.println("Cur:"+  t);
//			System.out.println("Opp:" + oppositeTriangle);
				// edge not fixed? 
				if(oppositeTriangle != null && !isSharedEdgeConstrained(t, oppositeTriangle) && oppositeTriangle.getCircumcircle().contains(point)) {
					// swap
					swapEdges(t, oppositeTriangle);
					stack.push(t);
					stack.push(oppositeTriangle);
				}
			}

//			try {
//				triangulationPersistence.persistTriangulation(getTriangulation(), null);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


			
		} else if(foundTriangles.size() == 1) {
			ITriangle triangle = foundTriangles.iterator().next();
			IPoint a = triangle.getPoints().get(0);
			IPoint b = triangle.getPoints().get(1);
			IPoint c = triangle.getPoints().get(2);
//		if(a.getX() == 8.18539218520265 && a.getY == 48.9334509071769 && b.getX() x=8.186452197237912, y=48.93100338065217, z=0.0], [x=8.18211755649745, y=48.930859616532324, z=0.0]])
//		if(boundCheck(a, b) || boundCheck(b, a) || boundCheck(a, c) || boundCheck(c, a) || boundCheck(b, c) || boundCheck(c, a)) {
//			postgisTriangulationPersistence.persistTriangulation(triangles, null);
//			System.out.println("x");
//		}
			int oldNeighbors = triangle.getNeighbors().size();
			
			// temporary fix. do not add points that are on edges. This should done differently
			for(IEdge e : triangle.getEdges()) {
				if(e.isOnEdge(point, 0.00001)) {
					return false;
				}
			}
			
			Triangle t1 = new Triangle(a, b, point);
			Triangle t2 = new Triangle(b, c, point);
			Triangle t3 = new Triangle(c, a, point);
			removeTriangle(triangles, triangle);
			t1.getNeighbors().add(t2);
			t1.getNeighbors().add(t3);
			t2.getNeighbors().add(t3);
			t2.getNeighbors().add(t1);
			t3.getNeighbors().add(t1);
			t3.getNeighbors().add(t2);
			
			// add old neighbours
			Set<ITriangle> neighbors = new LinkedHashSet<ITriangle>();
			neighbors.addAll(triangle.getNeighbors());
			for (ITriangle neighbour : neighbors) {
//				neighbourCount.put(neighbour, neighbour.getNeighbors().size());
				neighbour.getNeighbors().remove(triangle);
				if(neighbour.getSharedEdge(t1) != null) {
					t1.getNeighbors().add(neighbour);
					neighbour.getNeighbors().add(t1);
//				System.out.println("shared edge t1");
				} 
				if(neighbour.getSharedEdge(t2) != null) {
					t2.getNeighbors().add(neighbour);
					neighbour.getNeighbors().add(t2);
//				System.out.println("shared edge t2");
				} 
				if(neighbour.getSharedEdge(t3) != null) {
					t3.getNeighbors().add(neighbour);
					neighbour.getNeighbors().add(t3);
//				System.out.println("shared edge t3");
				}
			}
			int newNeighbors = t1.getNeighbors().size() + t2.getNeighbors().size() + t3.getNeighbors().size();
			if(newNeighbors - oldNeighbors - 6 != 0) {
				throw new RuntimeException();
			}

//		System.out.println("After Neighbors: " + t1.getNeighbors().size() + ":" + t1.getNeighbors());
//		System.out.println("After Neighbors: " + t2.getNeighbors().size() + ":" + t2.getNeighbors());
//		System.out.println("After Neighbors: " + t3.getNeighbors().size() + ":" + t3.getNeighbors());
//
//		for (ITriangle neighbor : triangle.getNeighbors()) {
//			if(neighbor.getNeighbors().size() < 3) {
//				System.out.println("neigh");
//				for (ITriangle nn : neighbor.getNeighbors()) {
//					System.out.println("sharing edge:" + nn.getSharedEdge(neighbor));
//				}
//				System.out.println("XXX:" + neighbor.getPoints());
//			}
//		}
			
//		for (ITriangle neigh : neighbors) {
//			if(!triangles.contains(neigh)) {
//				System.out.println("neighbor gone:" + neigh );
//			}
//		}
			
			
			addTriangle(triangles, t1);
			addTriangle(triangles, t2);
			addTriangle(triangles, t3);
//		System.out.println(triangles.size() + ":" + quadtree.size());
			
//		for(ITriangle t : t3.getNeighbors()) {
//			System.out.println(t.getSharedEdge(t3));
//		}
			stack.add(t1);
			stack.add(t2);
			stack.add(t3);
			while(!stack.isEmpty()) {
				ITriangle t = stack.pop();
				ITriangle oppositeTriangle = t.opposedTriangle(point);
//			System.out.println("Cur:"+  t);
//			System.out.println("Opp:" + oppositeTriangle);
				// edge not fixed? 
				if(oppositeTriangle != null && !isSharedEdgeConstrained(t, oppositeTriangle) && oppositeTriangle.getCircumcircle().contains(point)) {
					// swap
					swapEdges(t, oppositeTriangle);
					stack.push(t);
					stack.push(oppositeTriangle);
				}
			}
//			try {
//				triangulationPersistence.persistTriangulation(getTriangulation(), null);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
//		System.out.println("After Neighbors: " + t1.getNeighbors().size() + ":" + t1.getNeighbors());
//		System.out.println("After Neighbors: " + t2.getNeighbors().size() + ":" + t2.getNeighbors());
//		System.out.println("After Neighbors: " + t3.getNeighbors().size() + ":" + t3.getNeighbors());
//
//		for (ITriangle neighbour : neighbors) {
//			System.out.println("Before: " + neighbourCount.get(neighbour) + " Afer:" + neighbour.getNeighbors().size());
//		}
		
//		System.out.println("no neighbours");
//		for (ITriangle iTriangle : triangles) {
//			for (IPoint point2 : iTriangle.getPoints()) {
//				if(iTriangle.getCircumcircle().contains(point2)) {
////					System.out.println("nope: " + point2 + ":" + iTriangle);
//				}
//			}
//			if(iTriangle.getNeighbors().size() < 3) {
//				System.out.println(iTriangle);
//			}
//		}
//		for (ITriangle neigh : neighbors) {
//			if(!triangles.contains(neigh)) {
//				System.out.println("neighbor gone:" + neigh );
//			}
//		}
//		postgisTriangulationPersistence.persistTriangulation(triangles, null);
	else {
//		try {
//			triangulationPersistence.persistTriangulation(getTriangulation(), null);
//		} catch (PersistenceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("multi triangles : " + point);
		for (ITriangle iTriangle : foundTriangles) {
			System.out.println(iTriangle + ":" + iTriangle.contains(point));
		}
		return false;
	}
		
		return true;
	}

	private boolean boundCheck(IPoint a, IPoint b) {
		// points=[[x=8.18539218520265, y=48.9334509071769, z=0.0], [x=8.186452197237912, y=48.93100338065217, z=0.0], [x=8.18211755649745, y=48.930859616532324, z=0.0]]
		return a.getX() > 8.18211691 && a.getY() > 48.93085862 && a.getX() < 8.1821243 && a.getY() < 48.9308679 && b.getX() > 8.18539200 && b.getY() > 48.933419 && b.getX() < 8.1854194 && b.getY() < 48.9334696;
	}
	
	protected void swapEdges(ITriangle triangle, ITriangle oppositeTriangle) {
//		System.out.println("swap");
//		System.out.println(triangle);
//		System.out.println("Neighbors: " + triangle.getNeighbors().size() + ":" + triangle.getNeighbors());
//		System.out.println(oppositeTriangle);
//		System.out.println("Neighbors: " + oppositeTriangle.getNeighbors().size() + ":" + oppositeTriangle.getNeighbors());
		List<IPoint> aPoints = triangle.getPoints();
		List<IPoint> bPoints = oppositeTriangle.getPoints();
		if(quadtree != null) {
			quadtree.remove(triangle);
			quadtree.remove(oppositeTriangle);
		}
		Set<ITriangle> oldNeighbors = new LinkedHashSet<ITriangle>(4); 

		int oldSize = triangle.getNeighbors().size() + oppositeTriangle.getNeighbors().size();
		// remove the mutual neighbors
		if(!triangle.getNeighbors().remove(oppositeTriangle)) {
			throw new RuntimeException("Failed");
		}
		if(!oppositeTriangle.getNeighbors().remove(triangle)) {
			throw new RuntimeException("Failed");
		}

		// store the old neighbors of both surroundings
		Set<ITriangle> t1neighbors = triangle.getNeighbors();
		oldNeighbors.addAll(t1neighbors);
		Set<ITriangle> o1neighbors = oppositeTriangle.getNeighbors();
		oldNeighbors.addAll(o1neighbors);

		// remove the swapping triangles as neighbors since they will be changed
		for (ITriangle neighbor : t1neighbors) {
//			System.out.println(neighbor + "Bsize" + neighbor.getNeighbors().size());
			neighbor.getNeighbors().remove(triangle);
//			System.out.println(neighbor + "Asize" + neighbor.getNeighbors().size());
		}
		for (ITriangle neighbor : o1neighbors) {
//			System.out.println(neighbor +  "Bsize" + neighbor.getNeighbors().size());
			neighbor.getNeighbors().remove(oppositeTriangle);
//			System.out.println(neighbor + "Asize" + neighbor.getNeighbors().size());
		}
		t1neighbors.clear();
		o1neighbors.clear();
		
		IPoint a1 = aPoints.get(0);
		IPoint a2 = aPoints.get(1);
		IPoint a3 = aPoints.get(2);
		IPoint b1 = bPoints.get(0);
		IPoint b2 = bPoints.get(1);
		IPoint b3 = bPoints.get(2);
		
		IEdge sharedEdge = triangle.getSharedEdge(oppositeTriangle);
		Assert.isNotNull(sharedEdge);
		IPoint pointNotOnEdge = getPointNotOnEdge(triangle, sharedEdge);
		Assert.isNotNull(pointNotOnEdge);
		IPoint oppositePointNotOnEdge = getPointNotOnEdge(oppositeTriangle, sharedEdge);
		Assert.isNotNull(oppositePointNotOnEdge);
		aPoints.clear();
		aPoints.add(pointNotOnEdge);
		aPoints.add(sharedEdge.getOrigin());
		aPoints.add(oppositePointNotOnEdge);
		TriangleComparator triangleComparator = new TriangleComparator(oppositePointNotOnEdge);
		Collections.sort(aPoints, triangleComparator);
		bPoints.clear();
		bPoints.add(pointNotOnEdge);
		bPoints.add(sharedEdge.getDestination());
		bPoints.add(oppositePointNotOnEdge);
		triangleComparator = new TriangleComparator(oppositePointNotOnEdge);
		Collections.sort(bPoints, triangleComparator);

		restoreNeighbors(triangle, oldNeighbors);
		restoreNeighbors(oppositeTriangle, oldNeighbors);
		oppositeTriangle.getNeighbors().add(triangle);
		triangle.getNeighbors().add(oppositeTriangle);
		if(quadtree != null) {
			quadtree.insert(triangle);
			quadtree.insert(oppositeTriangle);
		}
		int newSize = triangle.getNeighbors().size() + oppositeTriangle.getNeighbors().size();
		Assert.isTrue(oldSize == newSize);
	}

	private IPoint getPointNotOnEdge(ITriangle triangle, IEdge sharedEdge) {
		List<IPoint> list = triangle.getPoints();
		for (IPoint point : list) {
			if(!(sharedEdge.getOrigin().equals(point) || sharedEdge.getDestination().equals(point))) {
				return point;
			}
		}
		return null;
	}

	private void restoreNeighbors(ITriangle triangle, Set<ITriangle> allOldNeighbours) {
//		triangle.getNeighbors().clear();
		for (Iterator<ITriangle> iterator = allOldNeighbours.iterator(); iterator.hasNext();) {
			ITriangle neighbour = iterator.next();
			if(neighbour.getSharedEdge(triangle) != null && !triangle.getNeighbors().contains(neighbour)) {
				triangle.getNeighbors().add(neighbour);
				neighbour.getNeighbors().add(triangle);
			}
		}
	}

	private boolean isSharedEdgeConstrained(ITriangle t,
			ITriangle oppositeTriangle) {
		IEdge sharedEdge = t.getSharedEdge(oppositeTriangle);
		return getContraintEdges().contains(sharedEdge);
	}

	protected void addEdge2Triangulation(IEdge edge, List<ITriangle> triangles2, ITriangle superTriangle) {
		edgeCounter++;
		if(edgeCounter == 27500) {
			System.out.println("x");
//			try {
//				triangulationPersistence.persistTriangulation(getTriangulation(), null);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		// punkte sind in der triangulation und die kante ist nicht in constrained segments
		List<IPoint> pseudoUpperPolygon = new ArrayList<IPoint>();
		List<IPoint> pseudoLowerPolygon = new ArrayList<IPoint>();
		Set<ITriangle> oldTriangleNeighbours = new LinkedHashSet<ITriangle>();
		Set<ITriangle> oldTriangles = new LinkedHashSet<ITriangle>();
		
		ITriangle currentTriangle = null;
		boolean constraintEdge = getContraintEdges().contains(edge);
		for (ITriangle triangle : triangles2) {
			if(triangle.checkConstraintEdge(edge)) {
				getContraintEdges().add(edge);
				break;
			}
			if(triangle.getPoints().contains(edge.getOrigin()) && triangle.intersects(edge) && !constraintEdge) {
				currentTriangle = triangle;
				break;
			}
		}
		IPoint currentPoint = edge.getOrigin();
		if(currentTriangle == null) {
			// triangle already an edge
			return ;
		}
		ITriangle oppositeTriangle = currentTriangle.opposedTriangle(currentPoint);
		IEdge sharedEdge = currentTriangle.getSharedEdge(oppositeTriangle);
		if(edge.isAbove(sharedEdge.getOrigin())) {
			pseudoUpperPolygon.add(sharedEdge.getOrigin());
			pseudoLowerPolygon.add(sharedEdge.getDestination());
		} else {
			pseudoLowerPolygon.add(sharedEdge.getOrigin());
			pseudoUpperPolygon.add(sharedEdge.getDestination());
		}
		IPoint oppositePoint = oppositeTriangle.oppositePoint(currentTriangle);
		
		while(!currentTriangle.getPoints().contains(edge.getDestination())) {
			oppositeTriangle = opposedTrianlgeIntersectingEdge(currentTriangle, currentPoint, edge);
			if(oppositeTriangle == null) {
				try {
					triangulationPersistence.persistTriangulation(getTriangulation(), null);
				} catch (PersistenceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			oppositePoint = oppositeTriangle.oppositePoint(currentTriangle);
			if(!oppositePoint.equals(edge.getDestination())) {
				if(edge.isAbove(oppositePoint)) {
					pseudoUpperPolygon.add(oppositePoint);
					sharedEdge = currentTriangle.getSharedEdge(oppositeTriangle);
					if(edge.isAbove(sharedEdge.getOrigin())) {
						currentPoint = sharedEdge.getOrigin();
					} else {
						currentPoint = sharedEdge.getDestination();
					}
				} else {
					pseudoLowerPolygon.add(oppositePoint);
					sharedEdge = currentTriangle.getSharedEdge(oppositeTriangle);
					if(!edge.isAbove(sharedEdge.getOrigin())) {
						currentPoint = sharedEdge.getOrigin();
					} else {
						currentPoint = sharedEdge.getDestination();
					}
				}
			}
			System.out.println("old:" + currentTriangle);
			if(removeTriangle(triangles2,currentTriangle)) {
				oldTriangleNeighbours.addAll(currentTriangle.getNeighbors());
				for (ITriangle neighbor : currentTriangle.getNeighbors()) {
					neighbor.getNeighbors().remove(currentTriangle);
				}
				oldTriangles.add(currentTriangle);
			}
			currentTriangle = oppositeTriangle;
		}
		System.out.println("old:" + currentTriangle);
		if(removeTriangle(triangles2,currentTriangle)) {
			oldTriangleNeighbours.addAll(currentTriangle.getNeighbors());
			for (ITriangle neighbor : currentTriangle.getNeighbors()) {
				neighbor.getNeighbors().remove(currentTriangle);
			}
			oldTriangles.add(currentTriangle);
		}
		List<ITriangle> newTriangles = new ArrayList<ITriangle>();
		triangulatePsedopolygonDelaunay(pseudoUpperPolygon, edge, newTriangles);
		triangulatePsedopolygonDelaunay(pseudoLowerPolygon, edge, newTriangles);
		
		for (ITriangle triangleA : newTriangles) {
			for (ITriangle triangleB : newTriangles) {
				if(!triangleA.equals(triangleB) && triangleA.getSharedEdge(triangleB) != null) {
					if(!triangles.contains(triangleB)) {
//						System.out.println("neighbor gone:" + triangleB );
					}
					triangleA.getNeighbors().add(triangleB);
				}
			}
		}
		oldTriangleNeighbours.removeAll(oldTriangles);
		for (ITriangle triangleB : oldTriangleNeighbours) {
			for (ITriangle triangleA : newTriangles) {
				if(!triangles.contains(triangleB)) {
					System.out.println("neighbor gone:" + triangleB );
				}

				if(triangleA.getSharedEdge(triangleB) != null) {
					triangleA.getNeighbors().add(triangleB);
					triangleB.getNeighbors().add(triangleA);
				}
			}
		}
		double oldArea = 0;
		double newArea = 0;
		for (ITriangle iTriangle : oldTriangles) {
			oldArea += iTriangle.getArea();
		}
		for (ITriangle iTriangle : newTriangles) {
//			System.out.println("new:" + iTriangle);
			newArea += iTriangle.getArea();
			for (ITriangle neigh : iTriangle.getNeighbors()) {
				if(!triangles.contains(neigh) && !newTriangles.contains(neigh)) {
					System.out.println("neighbor gone:" + neigh );
				}
			}
			if(iTriangle.getNeighbors().size() < 3) {
				System.out.println("To few neigh:"+ iTriangle);
				System.out.println("N:To few neigh:"+  iTriangle.getNeighbors());
				for (ITriangle existingTris : triangles) {
					if(existingTris.getSharedEdge(iTriangle) != null) {
						IEdge sharedEdge2 = iTriangle.getSharedEdge(superTriangle);
						try {
							triangulationPersistence.persistTriangulation(getTriangulation(), null);
						} catch (PersistenceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("forcefully adding neighbr");
						iTriangle.getNeighbors().add(existingTris);
						existingTris.getNeighbors().add(iTriangle);
					}
					
				}
			}
		}

		if(Math.abs(oldArea - newArea) > 0.00000001) {
			for (ITriangle iTriangle : oldTriangles) {
				System.out.println("old:" + iTriangle);
//				oldArea += iTriangle.getArea();
			}
			for (ITriangle iTriangle : newTriangles) {
				System.out.println("new:" + iTriangle);
//				newArea += iTriangle.getArea();
			}
			System.out.println("warning");
		}
		for (ITriangle triangle : newTriangles) {
			addTriangle(triangles2, triangle);
		}
		
		// triangle adjazenzen
		constraintEdges.add(edge);
		// mark as fixed edge
	}

	private ITriangle opposedTrianlgeIntersectingEdge(ITriangle currentTriangle,
			IPoint currentPoint, IEdge edge) {
		for (ITriangle triangle : currentTriangle.getNeighbors()) {
			IEdge sharedEdge = currentTriangle.getSharedEdge(triangle);
			if(!(sharedEdge.getOrigin().equals(currentPoint) || sharedEdge.getDestination().equals(currentPoint)) && triangle.intersects(edge)) {
				return triangle;
			}
		}
		return null;
	}

	protected void triangulatePsedopolygonDelaunay(List<IPoint> vertices, IEdge edge,
			List<ITriangle> triangles2) {
		IPoint c = null;
		List<IPoint> allPoints = new ArrayList<>(vertices);
		allPoints.add(edge.getOrigin());
		allPoints.add(edge.getDestination());
		
		if(vertices.size() > 1) {
			point : for (IPoint vertex : vertices) {
				ICircle circumcircle = new Triangle(edge.getOrigin(), edge.getDestination(), vertex).getCircumcircle();
				for (IPoint iPoint : allPoints) {
					if(!circumcircle.contains(iPoint)) {
						c = vertex;
						break point;
					}
				}
			}
//			c = vertices.get(0);
//			for (IPoint point : vertices) {
//				if(!circumcircle.contains(point)) {
//					c = point;
////					break;
//				}
//			}
			// divide P into PE and PD
			List<IPoint> pseudoPolygonD = new ArrayList<IPoint>();
			List<IPoint> pseudoPolygonE = new ArrayList<IPoint>();
			pseudoPolygonD.addAll(vertices.subList(0, vertices.indexOf(c)));
			pseudoPolygonE.addAll(vertices.subList(vertices.indexOf(c) + 1, vertices.size()));
			DirectedEdge edge1 = new DirectedEdge(edge.getOrigin(), c);
			triangulatePsedopolygonDelaunay(pseudoPolygonD, edge1, triangles2);
			DirectedEdge edge2 = new DirectedEdge(c, edge.getDestination());
			triangulatePsedopolygonDelaunay(pseudoPolygonE, edge2, triangles2);
		}
		if(!vertices.isEmpty()) {
			if(c == null) {
				c = vertices.get(0);
			}
			List<IPoint> trianglePoints = new ArrayList<IPoint>(3);
			trianglePoints.add(edge.getOrigin());
			trianglePoints.add(edge.getDestination());
			trianglePoints.add(c);
			Triangle triangle = new Triangle(trianglePoints);
			System.out.println("new:" + triangle);
			triangles2.add(triangle);
		}
		
	}

	@Override
	public List<ITriangle> getTriangulation() {
//		System.out.println("triangles:" +  triangles.size());
//		for (Iterator<ITriangle> iterator = triangles.iterator(); iterator.hasNext();) {
//			ITriangle triangle = iterator.next();
//			List<IPoint> points2 = triangle.getPoints();
//			System.out.println("Area:" + triangle.getArea() + ":" + triangle.getPoints()); 
//			TriangleComparator comparator = new TriangleComparator(triangle.getCircumcircle().getCenter());
//			Collections.sort(points2, comparator);
//			for (IPoint point : triangle.getPoints()) {
//				if(!boundary.contains(point)) {
//					iterator.remove();
//					break;
//				}
//			}
//		}
		return triangles;
	}
	
	private List<ITriangle> getTriangle(IPoint point) {
		List<ITriangle> indexTriangles = new ArrayList<ITriangle>();
		List<ITriangle> results = new ArrayList<>(1);
//		long currentTimeMillis = System.currentTimeMillis();
//		System.out.println("next: " + currentTimeMillis);
		quadtree.retrieve((List)indexTriangles, point);
		ITriangle quadTriangle = null;
//		System.out.println(indexTriangles.size());
		for (ITriangle triangle : indexTriangles) {
			if(triangle.contains(point)) { 
				results.add(triangle);
//				break;
			}
		}
//		long l = System.currentTimeMillis() - currentTimeMillis;
//		currentTimeMillis = System.currentTimeMillis();
//		System.out.println("next: " + l);
//		
//		ITriangle normalSearch = null;
////		System.out.println("q:" + quadTriangle);
//		for (int i = triangles.size() - 1; i > -1 ; i--) {
//			ITriangle triangle = triangles.get(i);
//			if(triangle.contains(point)) {
//				normalSearch = triangle;
//				break;
//			}
//		}
//		for (ITriangle triangle : triangles) {
//			if(triangle.contains(point)) {
////				System.out.println(triangle);
////				if(!triangle.equals(quadTriangle)) {
////					System.out.println("what");
////					quadtree.getObjectLocation2(triangle);
////				}
//				normalSearch = triangle;
//				break;
//			}
//		}
//		l = System.currentTimeMillis() - currentTimeMillis;
//		System.out.println("next: " + l);
		return results;
//		return normalSearch;
	}

	@Override
	public void loadTriangulation(List<ITriangle> triangles, IPolygon boundary, List<IPolygon> holes) {
		this.boundary = boundary;
		this.holes = holes;
		ITriangle createSuperTriangle = createSuperTriangle();
		quadtree = new Quadtree<IBoxExtends>(1, createSuperTriangle.getBoundingBox());
		constraintEdges = new ArrayList<IEdge>();
		triangulationPoints = new LinkedHashSet<IPoint>();
		this.triangles = triangles;
		for (ITriangle triangle : triangles) {
			quadtree.insert(triangle);
			for (ITriangle borderTriangle : triangles) {
				if(triangle != borderTriangle && triangle.getSharedEdge(borderTriangle) != null) {
					triangle.getNeighbors().add(borderTriangle);
				}
			}
		}
		
		List<IPoint> boundaryPoints = boundary.getPoints();
		for (int i = 0; i < boundaryPoints.size() - 1; i++) {
			IPoint point = boundaryPoints.get(i);
			IPoint point2 = boundaryPoints.get(i + 1);
			IEdge edge = new DirectedEdge(point, point2);
			triangulationPoints.add(point);
			triangulationPoints.add(point2);
			constraintEdges.add(edge);
		}
		for (IPolygon hole : holes) {
			boundaryPoints = hole.getPoints();
			for (int i = 0; i < boundaryPoints.size() - 1; i++) {
				IPoint point = boundaryPoints.get(i);
				IPoint point2 = boundaryPoints.get(i + 1);
				IEdge edge = new DirectedEdge(point, point2);
				triangulationPoints.add(point);
				triangulationPoints.add(point2);
				constraintEdges.add(edge);
			}
		}
	}

	@Override
	public void updateTriangulation(List<IPoint> points) {
		for (IPoint point : points) {
			this.points.add(point);
		}
		triangulateOnInputPoints();
	}

}

