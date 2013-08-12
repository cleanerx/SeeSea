package net.sf.seesea.rendering.chart.tools;

import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoPosition;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.requests.LocationRequest;

public class LocationSequenceRequest extends LocationRequest {

	private List<GeoPosition> locations;
	
	public LocationSequenceRequest() {
		locations = new ArrayList<GeoPosition>();
	}
	
	public void addLocation(GeoPosition position) {
		locations.add(position);
		
	}

	public List<GeoPosition> getLocations() {
		return locations;
	}
	
}
