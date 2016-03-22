package net.sf.seesea.tidemodel.dtu10.java.data;

public class TideHeight implements ITideHeight {

	private double tideHeight;
	
	private boolean valid;
	
	
	
	public TideHeight(double tideHeight, boolean valid) {
		super();
		this.tideHeight = tideHeight;
		this.valid = valid;
	}

	@Override
	public double getHeight() {
		return tideHeight;
	}

	@Override
	public boolean isdata() {
		return valid;
	}

}
