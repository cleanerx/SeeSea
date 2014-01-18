package org.osm.data;

import delaunay_triangulation.Point_dt;

public class DepthPoint extends Point_dt {
	
	private double depth ;

	public DepthPoint(double lat, double lon, double depth) {
		super(lon, lat);
		this.depth = depth;
	}

	public double getDepth() {
		return depth;
	}

	@Override
	public String toString() {
		return "DepthPoint [depth=" + depth + ", x=" + x() + ", y=" + y() + "]";
	}
	
	
	

}
