package net.sf.seesea.tileservice.projections;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.util.GeoParser;

import org.eclipse.swt.graphics.Point;

public class SphericalMercatorProjection implements IMapProjection {

	public SphericalMercatorProjection() {
		// 
	}
	
	public static Point project(GeoPosition coordinate, int zoom, int tilesize) {
		long numberOfTiles = 1 << zoom;

		Longitude longitude = coordinate.getLongitude();
		Double x = longitude2Value(tilesize, numberOfTiles, longitude);

		Latitude latitude = coordinate.getLatitude();
		Double y = latitude2Value(tilesize, numberOfTiles, latitude);

		return new Point(x.intValue(), y.intValue());
	}

	public static Double longitude2Value(int tilesize, long numberOfTiles, Longitude longitude) {
		Double x = (longitude.getDecimalDegree() + 180) * (numberOfTiles * tilesize) / 360.0;
		return x;
	}

	public static Double latitude2Value(int tilesize, long numberOfTiles, Latitude latitude) {
		double radians = Math.toRadians(latitude.getDecimalDegree());
		Double y = 1 - Math.log(Math.tan(Math.PI / 4 + radians / 2)) / Math.PI;
		y = y / 2 * (numberOfTiles * tilesize);
		return y;
	}

	public static GeoPosition backproject(Point point, int zoom, int tilesize) {
		long nrOfTiles = 1 << zoom;
		double value2Longitude = value2Longitude(point, tilesize, nrOfTiles);
		Longitude longitude = GeoParser.parseLongitude(value2Longitude);
		double value2latitude = valueToLatitude(point.y, tilesize, nrOfTiles);
		Latitude latitude = GeoParser.parseLatitude(value2latitude);
		GeoPosition geoPosition = GeoFactory.eINSTANCE.createGeoPosition();
		geoPosition.setLatitude(latitude);
		geoPosition.setLongitude(longitude);
		return geoPosition;
	}

	public static double value2Longitude(Point point, int tilesize, long nrOfTiles) {
		return (point.x * (360.0 /  (nrOfTiles * tilesize))) - 180;
	}

	public static double valueToLatitude(int y, int tilesize, long nrOfTiles) {
		double latitude = y * (2.0 / (nrOfTiles * tilesize));
		latitude = 1 - latitude;
		latitude = latitude * Math.PI;
		latitude = Math.toDegrees(Math.atan(Math.sinh(latitude)));
		return latitude;
	}

	@Override
	public GeoPosition backproject(Point point, int scale) {
		GeoFactory geoFactory = GeoFactory.eINSTANCE;
		
		net.sf.seesea.model.core.geo.Longitude longitude = GeoParser.parseLongitude((point.x * (360.0 /  scale)) - 180);
		
		double latitude = point.y * (2.0 / scale);
		latitude = 1 - latitude;
		latitude = latitude * Math.PI;
		latitude = Math.toDegrees(Math.atan(Math.sinh(latitude)));

		Latitude latitude2 = GeoParser.parseLatitude(latitude);
		
		GeoPosition geoPosition = geoFactory.createGeoPosition();
		geoPosition.setLatitude(latitude2);
		geoPosition.setLongitude(longitude);
		
		return geoPosition;
	}

	@Override
	public Point project(GeoPosition coordinate, int scale) {
		net.sf.seesea.model.core.geo.Longitude longitude = coordinate.getLongitude();
		Double x = longitude2Value(longitude, scale);

		net.sf.seesea.model.core.geo.Latitude latitude = coordinate.getLatitude();
		Double y = latitude2Value(latitude, scale);

		return new Point(x.intValue(), y.intValue());
	}

	@Override
	public double latitude2Value(net.sf.seesea.model.core.geo.Latitude latitude, int scale) {
        return (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(latitude.getDecimalDegree())) + 1 / Math.cos(Math.toRadians(latitude.getDecimalDegree()))) / Math.PI) / 2 * scale);
//		double radians = Math.toRadians(latitude.getDegree());
//		Double y = 1 - Math.log(Math.tan(Math.PI / 4 + radians / 2)) / Math.PI;
//		y = y / 2 * (scale);
//		return y;
	}

	@Override
	public double longitude2Value(net.sf.seesea.model.core.geo.Longitude longitude, int scale) {
		return (int) Math.floor((longitude.getDecimalDegree() + 180) / 360 * scale);
	}


}
