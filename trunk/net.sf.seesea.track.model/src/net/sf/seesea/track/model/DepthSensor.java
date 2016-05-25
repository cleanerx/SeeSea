package net.sf.seesea.track.model;

import net.sf.seesea.geometry.impl.Point3D;

public class DepthSensor extends Point3D {

	private double frequency;

	private double angleofbeam;

	private  double offsetKeel;
	
	private  String offsetType;
	
	public DepthSensor(double x, double y, double z, double offsetKeel, String offsetType) {
		super(x, y, z);
		this.offsetKeel = offsetKeel;
		this.offsetType = offsetType;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getAngleofbeam() {
		return angleofbeam;
	}

	public void setAngleofbeam(double angleofbeam) {
		this.angleofbeam = angleofbeam;
	}

	public double getOffsetKeel() {
		return offsetKeel;
	}

	public void setOffsetKeel(double offsetKeel) {
		this.offsetKeel = offsetKeel;
	}

	public String getOffsetType() {
		return offsetType;
	}

	public void setOffsetType(String offsetType) {
		this.offsetType = offsetType;
	}

	


}
