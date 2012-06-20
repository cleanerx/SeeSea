package net.sf.seesea.tileservice.projections;

import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;

import org.eclipse.swt.graphics.Point;

public interface IMapProjection {

	Point project(GeoPosition coordinate, int scale);
	
	GeoPosition backproject(Point point, int scale);
	
	double longitude2Value(Longitude longitude, int scale);
	
	double latitude2Value(Latitude latitude, int scale); 
	
}
