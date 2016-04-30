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

package net.sf.seesea.geometry.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.seesea.geometry.ICircle;
import net.sf.seesea.geometry.IEdge;
import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IRectangle;
import net.sf.seesea.geometry.ITriangle;
import net.sf.seesea.geometry.impl.math.ImpreciseGeometric;

public class Triangle implements ITriangle {

	private static final double EPSILON = 0.000001D;
	private static final double EPSILON_SQ = EPSILON * EPSILON;
	private List<IPoint> points;


	
	public Triangle(List<IPoint> points) {
		if (points.get(0).getX() == points.get(1).getX() && points.get(1).getX() == points.get(2).getX()) {
			throw new RuntimeException("Illegal Triangle");
		}
		if (points.get(0).getY() == points.get(1).getY() && points.get(1).getY() == points.get(2).getY()) {
			throw new RuntimeException("Illegal Triangle");
		}
		TriangleComparator triangleComparator = new TriangleComparator(points.get(2));
		this.points = points;
		neighbors = new LinkedHashSet<ITriangle>(3);
		Collections.sort(points, triangleComparator);

	}

	public Triangle(IPoint a, IPoint b, IPoint c) {
		if (a.getX() == b.getX() && b.getX() == c.getX()) {
			throw new RuntimeException("Illegal Triangle");
		}
		if (a.getY() == b.getY() && b.getY() == c.getY()) {
			throw new RuntimeException("Illegal Triangle");
		}
		TriangleComparator triangleComparator = new TriangleComparator(c);
		points = new ArrayList<IPoint>(3);
		points.add(a);
		points.add(b);
		points.add(c);
		Collections.sort(points, triangleComparator);
		neighbors = new LinkedHashSet<ITriangle>(3);
	}

	/** Neighbor pointers */
	private Set<ITriangle> neighbors;

	public Set<ITriangle> getNeighbors() {
		return neighbors;
	}

	@Override
	public boolean contains(final IPoint point) {
		double xMin = Math.min(a().getX(), Math.min(b().getX(), c().getX())) - EPSILON;
		double xMax = Math.max(a().getX(), Math.max(b().getX(), c().getX())) - EPSILON;
		double yMin = Math.min(a().getY(), Math.min(b().getY(), c().getY())) - EPSILON;
		double yMax = Math.max(a().getY(), Math.max(b().getY(), c().getY())) - EPSILON;

		if (point.getX() < xMin || xMax < point.getX() || point.getY() < yMin || yMax < point.getY()) {
			return false;
		}

		if (checkContainment(point)) {
			return true;
		}

		double distanceSquarePointToSegment = distanceSquarePointToSegment(a().getX(), a().getY(), b().getX(), b().getY(), point.getX(), point.getY());
		if (distanceSquarePointToSegment <= EPSILON_SQ)
			return true;
		double distanceSquarePointToSegment2 = distanceSquarePointToSegment(b().getX(), b().getY(), c().getX(), c().getY(), point.getX(), point.getY());
		if (distanceSquarePointToSegment2 <= EPSILON_SQ)
			return true;
		double distanceSquarePointToSegment3 = distanceSquarePointToSegment(c().getX(), c().getY(), a().getX(), a().getY(), point.getX(), point.getY());
		if (distanceSquarePointToSegment3 <= EPSILON_SQ)
			return true;
		// System.out.println(this + ":" + distanceSquarePointToSegment + ":" +
		// distanceSquarePointToSegment2 + ":" + distanceSquarePointToSegment3);

		return false;

		/* Calculate area of triangle ABC */
		// IPoint a = points.get(0);
		// IPoint b = points.get(1);
		// IPoint c = points.get(2);
		//
		// double A = getArea(a.getX(), a.getY(), b.getX(), b.getY(), c.getX(),
		// c.getY());
		//
		// /* Calculate area of triangle PBC */
		// double A1 = getArea(point.getX(), point.getY(), b.getX(), b.getY(),
		// c.getX(), c.getY());
		//
		// /* Calculate area of triangle PAC */
		// double A2 = getArea(a.getX(), a.getY(), point.getX(), point.getY(),
		// c.getX(), c.getY());
		//
		// /* Calculate area of triangle PAB */
		// double A3 = getArea(a.getX(), a.getY(), b.getX(), b.getY(),
		// point.getX(), point.getY());
		//
		// /* Check if sum of A1, A2 and A3 is same as A */
		// return (A == A1 + A2 + A3);
	}

	private boolean checkContainment(final IPoint point) {
		boolean b1, b2, b3;

		b1 = sign(point, a(), b()) < 0.0f;
		b2 = sign(point, b(), c()) < 0.0f;
		b3 = sign(point, c(), a()) < 0.0f;

		if (((b1 == b2) && (b2 == b3))) {
			return true;
		}
		return false;
	}

	private double distanceSquarePointToSegment(double x1, double y1, double x2, double y2, double x, double y) {
		double p1_p2_squareLength = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
		double dotProduct = ((x - x1) * (x2 - x1) + (y - y1) * (y2 - y1)) / p1_p2_squareLength;
		if (dotProduct < 0) {
			return (x - x1) * (x - x1) + (y - y1) * (y - y1);
		} else if (dotProduct <= 1) {
			double p_p1_squareLength = (x1 - x) * (x1 - x) + (y1 - y) * (y1 - y);
			return p_p1_squareLength - dotProduct * dotProduct * p1_p2_squareLength;
		} else {
			return (x - x2) * (x - x2) + (y - y2) * (y - y2);
		}
	}

	private double sign(IPoint p1, IPoint p2, IPoint p3) {
		return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
	}

	private IPoint a() {
		return points.get(0);
	}

	private IPoint b() {
		return points.get(1);
	}

	private IPoint c() {
		return points.get(2);
	}

	@Override
	public boolean intersects(IEdge edge) {
		for (IEdge triangleEdge : getEdges()) {
			if (edge.intersects(triangleEdge)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ITriangle opposedTriangle(IPoint currentPoint) {
		for (ITriangle triangle : neighbors) {
			IEdge sharedEdge = getSharedEdge(triangle);
			if (!(sharedEdge.getOrigin().equals(currentPoint) || sharedEdge.getDestination().equals(currentPoint))) {
				return triangle;
			}
		}
		return null;
	}

	@Override
	public IPoint oppositePoint(ITriangle oppositeTriangle) {
		IEdge sharedEdge = getSharedEdge(oppositeTriangle);
		for (IPoint point : points) {
			if (!(point.equals(sharedEdge.getOrigin()) || point.equals(sharedEdge.getDestination()))) {
				return point;
			}
		}
		return null;
	}

	@Override
	public List<IPoint> getPoints() {
		return points;
	}

	public IPoint getCentroid() {
		double x = (a().getX() + b().getX() + c().getX()) / 3;
		double y = (a().getY() + b().getY() + c().getY()) / 3;
		return new Point(x, y);
	}

	@Override
	public ICircle getCircumcircle() {
		IPoint a = points.get(0);
		IPoint b = points.get(1);
		IPoint c = points.get(2);
		IPoint midIPointAB = a.getMidpoint(b);
		IPoint midIPointBC = b.getMidpoint(c);

		double slopeAB = -1 / a.getSlope(b);
		double slopeBC = -1 / b.getSlope(c);

		double bAB = midIPointAB.getY() - slopeAB * midIPointAB.getX();
		double bBC = midIPointBC.getY() - slopeBC * midIPointBC.getX();

		double x = (bAB - bBC) / (slopeBC - slopeAB);

		IPoint center = new Point(x, (slopeAB * x) + bAB);
		double radius = center.getDistance(a);
		return new Circle(center, radius);
	}

	@Override
	public String toString() {
		return "points=" + points + "";
	}

	public IEdge getSharedEdge(ITriangle triangle) {
		IPoint a1 = this.points.get(0);
		IPoint a2 = this.points.get(1);
		IPoint a3 = this.points.get(2);
		IPoint b1 = triangle.getPoints().get(0);
		IPoint b2 = triangle.getPoints().get(1);
		IPoint b3 = triangle.getPoints().get(2);
		if ((a1.equals(b1) && a3.equals(b2)) || (a1.equals(b2) && a3.equals(b1))) {
			return new DirectedEdge(a1, a3);
		} else if ((a1.equals(b3) && a3.equals(b1)) || (a1.equals(b1) && a3.equals(b3))) {
			return new DirectedEdge(a1, a3);
		} else if ((a1.equals(b2) && a3.equals(b3)) || (a1.equals(b3) && a3.equals(b2))) {
			return new DirectedEdge(a1, a3);
		} else if ((a3.equals(b1) && a2.equals(b2)) || (a3.equals(b2) && a2.equals(b1))) {
			return new DirectedEdge(a2, a3);
		} else if ((a3.equals(b3) && a2.equals(b1)) || (a3.equals(b1) && a2.equals(b3))) {
			return new DirectedEdge(a2, a3);
		} else if ((a3.equals(b2) && a2.equals(b3)) || (a3.equals(b3) && a2.equals(b2))) {
			return new DirectedEdge(a2, a3);
		} else if ((a1.equals(b2) && a2.equals(b1)) || (a1.equals(b1) && a2.equals(b2))) {
			return new DirectedEdge(a1, a2);
		} else if ((a1.equals(b3) && a2.equals(b1)) || (a1.equals(b1) && a2.equals(b3))) {
			return new DirectedEdge(a1, a2);
		} else if ((a1.equals(b2) && a2.equals(b3)) || (a1.equals(b3) && a2.equals(b2))) {
			return new DirectedEdge(a1, a2);
		}
		return null;

	}

	public double getArea() {
		double x1 = points.get(0).getX();
		double y1 = points.get(0).getY();
		double x2 = points.get(1).getX();
		double y2 = points.get(1).getY();
		double x3 = points.get(2).getX();
		double y3 = points.get(2).getY();
		return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
	}

	@Override
	public boolean checkConstraintEdge(IEdge edge) {
		IPoint a1 = this.points.get(0);
		IPoint a2 = this.points.get(1);
		IPoint a3 = this.points.get(2);
		boolean originEQA1 = edge.getOrigin().equals(a1);
		boolean destinationEQA2 = edge.getDestination().equals(a2);
		boolean originEQA2 = edge.getOrigin().equals(a2);
		boolean destinationEQA1 = edge.getDestination().equals(a1);
		boolean originEQA3 = edge.getOrigin().equals(a3);
		boolean destinationEQA3 = edge.getDestination().equals(a3);
		return ((originEQA1 && destinationEQA2) || (originEQA2 && destinationEQA1) || (destinationEQA2 && destinationEQA3) || (originEQA3 && destinationEQA2) || (originEQA3 && destinationEQA1) || (originEQA1 && destinationEQA3));
	}

	@Override
	public void setPoints(List<IPoint> points) {
		TriangleComparator triangleComparator = new TriangleComparator(points.get(2));
		Collections.sort(points, triangleComparator);
		this.points = points;

	}

	@Override
	public Set<IEdge> getEdges() {
		Set<IEdge> edges = new LinkedHashSet<IEdge>();
		DirectedEdge edge = new DirectedEdge(points.get(0), points.get(1));
		edges.add(edge);
		edge = new DirectedEdge(points.get(1), points.get(2));
		edges.add(edge);
		edge = new DirectedEdge(points.get(2), points.get(0));
		edges.add(edge);
		return edges;
	}

	@Override
	public IRectangle getBoundingBox() {
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;
		IPoint pointA = points.get(0);
		if (pointA.getX() < minX) {
			minX = pointA.getX();
		}
		if (pointA.getX() > maxX) {
			maxX = pointA.getX();
		}
		if (pointA.getY() < minY) {
			minY = pointA.getY();
		}
		if (pointA.getY() > maxY) {
			maxY = pointA.getY();
		}

		pointA = points.get(1);
		if (pointA.getX() < minX) {
			minX = pointA.getX();
		}
		if (pointA.getX() > maxX) {
			maxX = pointA.getX();
		}
		if (pointA.getY() < minY) {
			minY = pointA.getY();
		}
		if (pointA.getY() > maxY) {
			maxY = pointA.getY();
		}

		pointA = points.get(2);
		if (pointA.getX() < minX) {
			minX = pointA.getX();
		}
		if (pointA.getX() > maxX) {
			maxX = pointA.getX();
		}
		if (pointA.getY() < minY) {
			minY = pointA.getY();
		}
		if (pointA.getY() > maxY) {
			maxY = pointA.getY();
		}

		return new Rectangle(minX, minY, maxX - minX, maxY - minY);
	}





	@Override
	public boolean inCircumcircle(IPoint point) {
		double in = new ImpreciseGeometric().incircle(points.get(0), points.get(1), points.get(2), point);
		if(in > 0.0) {
			return true;
		} else if(in < 0.0) {
			return false;
		} else {
			System.out.println("Zero edge");
		}
		return false;
	}

}
