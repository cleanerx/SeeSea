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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.ITriangle;
import net.sf.seesea.geometry.impl.Point;
import net.sf.seesea.geometry.impl.Triangle;

public class PostgisHelper {

	
	public static ITriangle getTriangleFromPostgisPolygon2D(String polygonString) {
		String[] coordinates = polygonString.substring(polygonString.lastIndexOf("(") + 1, polygonString.indexOf(")")).split(",");
		List<IPoint> points = new ArrayList<IPoint>();
		for (String coodinate : coordinates) {
			String[] xy = coodinate.split(" ");
			Point point = new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
			points.add(point);
		}
		Triangle triangle = new Triangle(points.subList(0, 3));
		return triangle;
	}

	public static String getPostgisPolygon2DTriangle(ITriangle triangle) {
		// create the multipolygon from the points again
		StringBuffer b = new StringBuffer();
		b.append("ST_GeometryFromText('MULTIPOLYGON("); //$NON-NLS-1$

		// the first polygon as outer one
		b.append("(("); //$NON-NLS-1$
		for (Iterator<IPoint> iterator = triangle.getPoints().iterator(); iterator
				.hasNext();) {
			IPoint point = iterator.next();
			b.append(point.getX());
			b.append(' ');
			b.append(point.getY());
			b.append(',');
		}
		IPoint point = triangle.getPoints().get(0);
		b.append(point.getX());
		b.append(' ');
		b.append(point.getY());
		
		b.append("))"); //$NON-NLS-1$
		b.append(")',4326)"); //$NON-NLS-1$
		return b.toString();
	}

	public static String getPostgisLineString(List<IPoint> points) {
		// create the multipolygon from the points again
		StringBuffer b = new StringBuffer();
		b.append("ST_GeometryFromText('LINESTRING("); //$NON-NLS-1$

		// the first polygon as outer one
//		b.append("("); //$NON-NLS-1$
		for (Iterator<IPoint> iterator = points.iterator(); iterator
				.hasNext();) {
			IPoint point = iterator.next();
			b.append(point.getX());
			b.append(' ');
			b.append(point.getY());
			b.append(',');
		}
		IPoint point = points.get(0);
		b.append(point.getX());
		b.append(' ');
		b.append(point.getY());
		
//		b.append(")"); //$NON-NLS-1$
		b.append(")',4326)"); //$NON-NLS-1$
		return b.toString();
	}

	public static String getPostgisMultiLineString(List<IPoint> points) {
		// create the multipolygon from the points again
		StringBuffer b = new StringBuffer();
		b.append("ST_GeometryFromText('MULTILINESTRING(("); //$NON-NLS-1$

		// the first polygon as outer one
//		b.append("("); //$NON-NLS-1$
		for (Iterator<IPoint> iterator = points.iterator(); iterator
				.hasNext();) {
			IPoint point = iterator.next();
			b.append(point.getX());
			b.append(' ');
			b.append(point.getY());
			b.append(',');
		}
		IPoint point = points.get(0);
		b.append(point.getX());
		b.append(' ');
		b.append(point.getY());
		
//		b.append(")"); //$NON-NLS-1$
		b.append("))',4326)"); //$NON-NLS-1$
		return b.toString();
	}
	
	public static String getMultipolygonString(IPolygon boundary, List<IPolygon> holes) {
		// create the multipolygon from the points again
		StringBuffer b = new StringBuffer();
		b.append("ST_GeometryFromText('MULTIPOLYGON("); //$NON-NLS-1$

		// the first polygon as outer one
		b.append("(("); //$NON-NLS-1$
		for (Iterator<IPoint> iterator = boundary.getPoints().iterator(); iterator
				.hasNext();) {
			IPoint point = iterator.next();
			b.append(point.getX());
			b.append(' ');
			b.append(point.getY());
//			b.append(" 0");
			if(iterator.hasNext()) {
				b.append(',');
			}
		}
		b.append("))"); //$NON-NLS-1$
		
		if(!holes.isEmpty()) {
			for (IPolygon hole : holes) {
				b.append(",(("); //$NON-NLS-1$
				for (Iterator<IPoint> iterator = hole.getPoints().iterator(); iterator
						.hasNext();) {
					IPoint point = iterator.next();
					b.append(point.getX());
					b.append(' ');
					b.append(point.getY());
//				b.append(" 0");
					if(iterator.hasNext()) {
						b.append(',');
					}
				}
				b.append("))"); //$NON-NLS-1$
			}
		}
		b.append(")',4326)"); //$NON-NLS-1$
		return b.toString();
	}

}
