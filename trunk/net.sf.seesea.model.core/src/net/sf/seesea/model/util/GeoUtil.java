package net.sf.seesea.model.util;

public class GeoUtil {

	public static double getDistance(double latA, double latB,double  lonA,double lonB) {
		double dLat = Math.toRadians(latB - latA);
		double dLon = Math.toRadians(lonB - lonA);
		double lat1 = Math.toRadians(latA);
		double lat2 = Math.toRadians(latB);

		double earthRadius = 6371.221; //km
		
		double a = Math.PI/2-lat2;
		double b = Math.PI/2-lat1;
		double c = Math.acos(Math.cos(a)*Math.cos(b)+Math.sin(a)*Math.sin(b)*Math.cos(dLon));
		double d = earthRadius * c * 0.540;
		
		return d;
	}
	
	public static double getBearing(double latA,double latB,double lonA,double lonB) {
		double dLat = Math.toRadians(latB-latA);
		double dLon = Math.toRadians(lonB-lonA);
		double lat1 = Math.toRadians(latA);
		double lat2 = Math.toRadians(latB);
		
		double y = Math.sin(dLon) * Math.cos(lat2);
		double x = Math.cos(lat1)*Math.sin(lat2) -
			Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
		double brng = Math.toDegrees(Math.atan2(y, x));
		
		return (brng + 360) % 360;
	}
}