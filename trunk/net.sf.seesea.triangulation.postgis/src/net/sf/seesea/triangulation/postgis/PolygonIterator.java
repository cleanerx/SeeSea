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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.impl.Point;
import net.sf.seesea.geometry.impl.Polygon;


public class PolygonIterator implements Iterator<List<IPolygon>> {

	private final ResultSet[] resultSets;
	private String nextPath;
	private int resultSetIndex;
	private ResultSet resultSet;

	public PolygonIterator(ResultSet... resultSets) {
		nextPath = null;
		this.resultSets = resultSets;
		resultSetIndex = 0;
		resultSet = resultSets[resultSetIndex];
	}
	
	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public List<IPolygon> next() {
		List<IPolygon> boundariesAndHoles = new ArrayList<IPolygon>();
		List<IPoint> coordinates = new ArrayList<IPoint>();
		int layer = 0;
		int counter = 0;
		int multiGeometryCounter = 0;
		IPoint firstpoint = null;

		try {
		boolean firstPass = true;
		long lastOSMId = 0L;
		// this is a lookahead path that is not null on any non first run of the iterator
		if(nextPath != null) {
			String[] split = nextPath.split(","); //$NON-NLS-1$
			layer = Integer.parseInt(split[0]);
			counter = Integer.parseInt(split[1]);
			try {
				IPoint point = new Point(resultSet.getDouble(2), resultSet.getDouble(3), 0);
				lastOSMId = resultSet.getLong(4);
//				System.out.println(lastOSMId);
				coordinates = new ArrayList<IPoint>();
				firstpoint = point;
				coordinates.add(point);
				firstPass = false;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		boolean currentResultSetHasValue = resultSet.next() ;
		while(!currentResultSetHasValue && resultSetIndex < resultSets.length - 1) {
			resultSet = resultSets[++resultSetIndex];
			currentResultSetHasValue = resultSet.next();
		}
		
		while (currentResultSetHasValue) {
			String path = resultSet.getString(1);
			path = path.substring(path.indexOf("{") + 1, path.indexOf("}")); //$NON-NLS-1$ //$NON-NLS-2$
			String[] split = path.split(","); //$NON-NLS-1$
			if(split.length == 2) {
				layer = Integer.parseInt(split[0]);
				counter = Integer.parseInt(split[1]);
			} else {
				multiGeometryCounter = Integer.parseInt(split[0]);
				layer = Integer.parseInt(split[1]);
				counter = Integer.parseInt(split[2]);
			}
			IPoint point = new Point(resultSet.getDouble(2), resultSet.getDouble(3), 0);
			long osmId = resultSet.getLong(4);
//			System.out.println(osmId);
			if (counter == 1) { // a new path is present, so create a new polygon
				if(lastOSMId == osmId || firstPass) { // when the osmId changes it is no longer the same relation, so save the path for the next iteration
					if (!coordinates.isEmpty()) {
//						nextPath = path;
						boundariesAndHoles.add(new Polygon(coordinates));
					}
					coordinates = new ArrayList<IPoint>();
					lastOSMId = osmId;
					firstpoint = point;
					firstPass = false;
				} else {
					// save this path for the next iteration
					nextPath = path;
					break;
				}
			}
			coordinates.add(point);
			currentResultSetHasValue = resultSet.next();
		}
		if(firstpoint != null) {
			coordinates.add(firstpoint);
		}
		if (!coordinates.isEmpty()) {
			boundariesAndHoles.add(new Polygon(coordinates));
		}
		if(!currentResultSetHasValue) {
			resultSetIndex++;
			if(resultSetIndex < resultSets.length) {
				resultSet = resultSets[resultSetIndex];
				nextPath = null;
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		if(boundariesAndHoles.isEmpty()) {
			return null;
		}

		
		return boundariesAndHoles;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
