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
package net.sf.seesea.geometry.test;

import net.sf.seesea.geometry.impl.Point;
import net.sf.seesea.geometry.impl.Triangle;
import junit.framework.TestCase;

public class TriangleTest extends TestCase {

	public void testSimpleContainment() {
		Point pointa = new Point(0,0);
		Point pointb = new Point(10,0);
		Point pointc = new Point(0,10);
		Triangle triangle = new Triangle(pointa, pointb, pointc);
		assertTrue(triangle.contains(new Point(3,3)));
	}
	
	public void testNoContainment() {
		Point pointa = new Point(0,0);
		Point pointb = new Point(10,0);
		Point pointc = new Point(0,10);
		Triangle triangle = new Triangle(pointa, pointb, pointc);
		assertFalse(triangle.contains(new Point(20,20)));
	}

	public void testEdgeContainment() {
		Point pointa = new Point(0,0);
		Point pointb = new Point(10,0);
		Point pointc = new Point(0,10);
		Triangle triangle = new Triangle(pointa, pointb, pointc);
		assertTrue(triangle.contains(new Point(0,8)));
	}

	public void testCloseEdgeContainment() {
		Point pointa = new Point(0,0);
		Point pointb = new Point(10,0);
		Point pointc = new Point(0,10);
		Triangle triangle = new Triangle(pointa, pointb, pointc);
		assertTrue(triangle.contains(new Point(0,7.9999)));
	}

	public void testCloseEdgeContainmentSkew() {
		Point pointa = new Point(1D/10D,1D/9D);
		Point pointb = new Point(100D/8D,100D/3D);
		Point pointc = new Point(100D/4D,100D/9D);
		Triangle triangle = new Triangle(pointa, pointb, pointc);
		assertTrue(triangle.contains(new Point(pointa.getX() + (3D/7D) * (pointb.getX()- pointa.getX()),pointa.getY() + (3D/7D) * (pointb.getY() - pointa.getY()))));
	}

	
}
