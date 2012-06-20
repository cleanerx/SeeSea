package net.sf.seesea.tileservice.projections;

/**
 * @author Christopher Schmidt
 * 
 * @see http://wiki.openstreetmap.org/wiki/User:Crschmidt
 * <pre>
 * All my contributions to OpenStreetMap are released into the public domain. This applies worldwide.
 * In case this is not legally possible, I grant anyone the right to use my contributions for any purpose, without any conditions, unless such conditions are required by law.
 * </pre> 
 */
public class Mercator {
	final private static double R_MAJOR = 6378137.0;
	final private static double R_MINOR = 6356752.3142;

	public double[] merc(double x, double y) {
		return new double[] { mercX(x), mercY(y) };
	}

	private double mercX(double lon) {
		return R_MAJOR * Math.toRadians(lon);
	}

	private double mercY(double lat) {
		if (lat > 89.5) {
			lat = 89.5;
		}
		if (lat < -89.5) {
			lat = -89.5;
		}
		double temp = R_MINOR / R_MAJOR;
		double es = 1.0 - (temp * temp);
		double eccent = Math.sqrt(es);
		double phi = Math.toRadians(lat);
		double sinphi = Math.sin(phi);
		double con = eccent * sinphi;
		double com = 0.5 * eccent;
		con = Math.pow(((1.0 - con) / (1.0 + con)), com);
		double ts = Math.tan(0.5 * ((Math.PI * 0.5) - phi)) / con;
		double y = 0 - R_MAJOR * Math.log(ts);
		return y;
	}
}
