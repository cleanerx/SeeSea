package net.sf.seesea.tidemodel.dtu10.java.data;

public class TideConsituents {

	private float[][] modelData;

	private int nY;

	private int nX;

	private double latmin;
	private double latmax;
	private double lonmin;
	private double lonmax;
	private double undefined;
	
	public TideConsituents(float[][] arrayToBeRead, int nY, int nX, double latmin, double latmax, double lonmin, double lonmax, double undefined) {
		super();
		this.modelData = arrayToBeRead;
		this.nY = nY;
		this.nX = nX;
		this.latmin = latmin;
		this.latmax = latmax;
		this.lonmin = lonmin;
		this.lonmax = lonmax;
		this.undefined = undefined;
	}

	public float[][] getModelData() {
		return modelData;
	}

	public int getnY() {
		return nY;
	}

	public int getnX() {
		return nX;
	}

	public double getLatmin() {
		return latmin;
	}

	public double getLatmax() {
		return latmax;
	}

	public double getLonmin() {
		return lonmin;
	}

	public double getLonmax() {
		return lonmax;
	}

	public double getUndefined() {
		return undefined;
	}

	
	
}
