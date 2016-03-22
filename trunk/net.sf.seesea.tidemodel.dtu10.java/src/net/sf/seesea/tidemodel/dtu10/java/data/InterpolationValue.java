package net.sf.seesea.tidemodel.dtu10.java.data;

public class InterpolationValue {

	private double y;
	private double dy;

	public InterpolationValue(double y, double dy) {
		super();
		this.y = y;
		this.dy = dy;
	}

	public double getY() {
		return y;
	}

	public double getDy() {
		return dy;
	}

}
