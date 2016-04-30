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

import net.sf.seesea.geometry.IEdge;
import net.sf.seesea.geometry.IPoint;


public class DirectedEdge implements IEdge {

	public DirectedEdge(IPoint origin, IPoint destination) {
		super();
		this.origin = origin;
		this.destination = destination;
	}

	private IPoint origin;

	private IPoint destination;

	@Override
	public IPoint getOrigin() {
		return origin;
	}

	@Override
	public IPoint getDestination() {
		return destination;
	}

	@Override
	public boolean isOnEdge(IPoint point, double distance) {
		double lengthOfSegment = origin.getDistance(destination);
		double lengthFromOriginToPoint = origin.getDistance(point);
		double lengthFromTerminusToPoint = destination.getDistance(point);

		// double d = lengthFromTerminusToPoint + lengthFromOriginToPoint -
		// lengthOfSegment;
		// System.out.println("D" + d);
		// return lengthFromTerminusToPoint + lengthFromOriginToPoint -
		// lengthOfSegment <= 0.00000001;
		// if(d < 0.00001) {
		// }
		return lengthFromTerminusToPoint + lengthFromOriginToPoint
				- lengthOfSegment <= distance;
	}

	@Override
	public boolean isOnEdge(IPoint point) {
		double lengthOfSegment = origin.getDistance(destination);
		double lengthFromOriginToPoint = origin.getDistance(point);
		double lengthFromTerminusToPoint = destination.getDistance(point);

		// double d = lengthFromTerminusToPoint + lengthFromOriginToPoint -
		// lengthOfSegment;
		// System.out.println("D" + d);
		return lengthFromTerminusToPoint + lengthFromOriginToPoint
				- lengthOfSegment <= 0.00000001;
		// if(d < 0.00001) {
		// }
		// return lengthFromTerminusToPoint + lengthFromOriginToPoint -
		// lengthOfSegment <= 0.0001;
	}

	@Override
	public boolean isAbove(IPoint oppositePoint) {
		IPoint a = getOrigin();
		IPoint b = getDestination();
		return ((b.getX() - a.getX()) * (oppositePoint.getY() - a.getY()) - (b
				.getY() - a.getY()) * (oppositePoint.getX() - a.getX())) > 0;
	}

	@Override
	public String toString() {
		return "origin=" + origin + ", destination=" + destination + "";
	}

	public boolean intersects(IEdge edge) {
		double l1x1 = getOrigin().getX();
		double l1y1 = getOrigin().getY();
		double l1x2 = getDestination().getX();
		double l1y2 = getDestination().getY();
		double l2x1 = edge.getOrigin().getX();
		double l2y1 = edge.getOrigin().getY();
		double l2x2 = edge.getDestination().getX();
		double l2y2 = edge.getDestination().getY();

		double denom = ((l2y2 - l2y1) * (l1x2 - l1x1))
				- ((l2x2 - l2x1) * (l1y2 - l1y1));

		if (denom == 0.0f) {
			return false;
		}

		double ua = (((l2x2 - l2x1) * (l1y1 - l2y1)) - ((l2y2 - l2y1) * (l1x1 - l2x1)))
				/ denom;
		double ub = (((l1x2 - l1x1) * (l1y1 - l2y1)) - ((l1y2 - l1y1) * (l1x1 - l2x1)))
				/ denom;

		return ((ua > 0.0d) && (ua < 1.0d) && (ub > 0.0d) && (ub < 1.0d));

		// double x1 = getOrigin().getX();
		// double y1 = getOrigin().getY();
		// double x2 = getDestination().getX();
		// double y2 = getDestination().getY();
		// double x3 = edge.getOrigin().getX();
		// double y3 = edge.getOrigin().getY();
		// double x4 = edge.getDestination().getX();
		// double y4 = edge.getDestination().getY();
		//
		// // Return false if either of the lines have zero length
		// if (x1 == x2 && y1 == y2 ||
		// x3 == x4 && y3 == y4){
		// return false;
		// }
		// // Fastest method, based on Franklin Antonio's
		// "Faster Line Segment Intersection" topic "in Graphics Gems III" book
		// (http://www.graphicsgems.org/)
		// double ax = x2-x1;
		// double ay = y2-y1;
		// double bx = x3-x4;
		// double by = y3-y4;
		// double cx = x1-x3;
		// double cy = y1-y3;
		//
		// double alphaNumerator = by*cx - bx*cy;
		// double commonDenominator = ay*bx - ax*by;
		// if (commonDenominator > 0){
		// if (alphaNumerator < 0 || alphaNumerator > commonDenominator){
		// return false;
		// }
		// }else if (commonDenominator < 0){
		// if (alphaNumerator > 0 || alphaNumerator < commonDenominator){
		// return false;
		// }
		// }
		// double betaNumerator = ax*cy - ay*cx;
		// if (commonDenominator > 0){
		// if (betaNumerator < 0 || betaNumerator > commonDenominator){
		// return false;
		// }
		// }else if (commonDenominator < 0){
		// if (betaNumerator > 0 || betaNumerator < commonDenominator){
		// return false;
		// }
		// }
		// if (commonDenominator == 0){
		// // This code wasn't in Franklin Antonio's method. It was added by
		// Keith Woodward.
		// // The lines are parallel.
		// // Check if they're collinear.
		// double y3LessY1 = y3-y1;
		// double collinearityTestForP3 = x1*(y2-y3) + x2*(y3LessY1) +
		// x3*(y1-y2); // see http://mathworld.wolfram.com/Collinear.html
		// // If p3 is collinear with p1 and p2 then p4 will also be collinear,
		// since p1-p2 is parallel with p3-p4
		// if (collinearityTestForP3 == 0){
		// // The lines are collinear. Now check if they overlap.
		// if (x1 >= x3 && x1 <= x4 || x1 <= x3 && x1 >= x4 ||
		// x2 >= x3 && x2 <= x4 || x2 <= x3 && x2 >= x4 ||
		// x3 >= x1 && x3 <= x2 || x3 <= x1 && x3 >= x2){
		// if (y1 >= y3 && y1 <= y4 || y1 <= y3 && y1 >= y4 ||
		// y2 >= y3 && y2 <= y4 || y2 <= y3 && y2 >= y4 ||
		// y3 >= y1 && y3 <= y2 || y3 <= y1 && y3 >= y2){
		// return true;
		// }
		// }
		// }
		// return false;
		// }
		// return true;
	}

	/**
	 * WARNING WARNING WARNING.
	 * this implementation is not safe for bidirectional comparision. It violates the java contract
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode()) + ((origin == null) ? 0 : origin.hashCode());
//		result = prime * result ;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectedEdge other = (DirectedEdge) obj;
		if (destination == null) {
			if (other.destination != null) {
				return false;
			}
		} else if (origin == null) {
			if (other.origin != null) {
				return false;
			}
		}
		if(destination.equals(other.destination) && origin.equals(other.origin)) {
			return true;
		} else if(origin.equals(other.destination) && destination.equals(other.origin)) {
			return true;
		}
		
		return false;
//		if (!(destination.equals(other.destination) && !destination
//				.equals(other.origin))) {
//			return false;
//		} else if (!(origin.equals(other.origin) || !origin
//				.equals(other.destination)))
//			return false;
//		return true;
	}

}
