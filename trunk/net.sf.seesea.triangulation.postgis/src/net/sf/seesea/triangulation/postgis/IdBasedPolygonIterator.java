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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.impl.Point;
import net.sf.seesea.geometry.impl.Polygon;


public class IdBasedPolygonIterator implements Iterator<List<IPolygon>> {

	private final Set<String> inshoreOSMids;
	private final Set<String> offshoreIds;
	private List<String> combinedIds;
	private Iterator<String> idIterator;
	private final Connection inshoreConnection;
	private final Connection offshoreConnection;

	public IdBasedPolygonIterator(Set<String> inshoreOSMids, Set<String> offshoreIds, Connection inshoreConnection, Connection offshoreConnection) {
		this.inshoreOSMids = inshoreOSMids;
		this.offshoreIds = offshoreIds;
		this.inshoreConnection = inshoreConnection;
		this.offshoreConnection = offshoreConnection;
		this.combinedIds = new ArrayList<String>();
		combinedIds.addAll(inshoreOSMids);
		combinedIds.addAll(offshoreIds);
		idIterator = combinedIds.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return idIterator.hasNext();
	}

	@Override
	public List<IPolygon> next() {
		if(!idIterator.hasNext()) {
			return null;
		}
		String id = idIterator.next();
		try {
			if(inshoreOSMids.contains(id)) {
				Statement inshoreStatement = inshoreConnection.createStatement();
				ResultSet inshorePartionizedPolygonResultSet = inshoreStatement.executeQuery(
						"SELECT path, ST_X(ST_TRANSFORM(geom,4326)) AS lon, ST_Y(ST_TRANSFORM(geom,4326)) as lat, osm_id FROM " 
								+ "(SELECT * FROM ("
								+ "SELECT (ST_DUMPPOINTS(way)).*, * FROM planet_osm_polygon AS poly WHERE osm_id = " + id + ") AS f) AS g");
				return getPolygonFromDatabase(inshorePartionizedPolygonResultSet);
			} else {
				Statement offshoreStatement = offshoreConnection.createStatement();
				ResultSet offshorePartionizedPolygonResultSet = offshoreStatement.executeQuery("SELECT path, ST_X(geom) AS lon, ST_Y(geom) as lat, gid FROM (SELECT (ST_DUMPPOINTS(geom)).*, gid FROM gebco_poly_100 WHERE gid = " + id + ") AS g");
				return getPolygonFromDatabase(offshorePartionizedPolygonResultSet);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<IPolygon> getPolygonFromDatabase(ResultSet points) throws SQLException {
		List<IPolygon> boundariesAndHoles = new ArrayList<IPolygon>();
		List<IPoint> coordinates = new ArrayList<IPoint>();

		int layer;
		int counter;
		int multiGeometryCounter;
		int lastLayer = 1;
		while (points.next()) {
			String path = points.getString(1);
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
//			System.out.println(path);
			if(lastLayer != layer) {
				// a new sub geometry is born
				if (!coordinates.isEmpty()) {
					coordinates.add(coordinates.get(0));
					boundariesAndHoles.add(new Polygon(coordinates));
					coordinates = new ArrayList<>();
					lastLayer = layer;
				}
			}
			IPoint point = new Point(points.getDouble(2), points.getDouble(3), 0);
			long osmId = points.getLong(4);
			coordinates.add(point);
		}
		if (!coordinates.isEmpty()) {
			coordinates.add(coordinates.get(0));
			boundariesAndHoles.add(new Polygon(coordinates));
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
