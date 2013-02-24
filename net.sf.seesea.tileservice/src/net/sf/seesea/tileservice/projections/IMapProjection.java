package net.sf.seesea.tileservice.projections;

import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;

import org.eclipse.swt.graphics.Point;

public interface IMapProjection {

	Point project(GeoPosition coordinate, int scale);
	
	GeoPosition backproject(Point point, int scale);
	
	double longitude2Value(double longitude, int scale);
	
	double latitude2Value(double latitude, int scale); 
	
}
