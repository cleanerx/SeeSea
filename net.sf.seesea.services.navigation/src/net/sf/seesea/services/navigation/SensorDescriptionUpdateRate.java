package net.sf.seesea.services.navigation;

import net.sf.seesea.model.core.physx.Measurement;

public class SensorDescriptionUpdateRate<T extends Measurement> extends SensorDescription<T> {

	private long updateRate;
	
	private int precision;

	public SensorDescriptionUpdateRate(Class<T> measurement, String sensorID,
			String messageType, long updateRate, int precision) {
		super(measurement, sensorID, messageType);
		this.updateRate = updateRate;
		this.precision = precision;
	}

	public int getPrecision() {
		return precision;
	}



	public long getUpdateRate() {
		return updateRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + precision;
		result = prime * result + (int) (updateRate ^ (updateRate >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorDescriptionUpdateRate other = (SensorDescriptionUpdateRate) obj;
		if (precision != other.precision)
			return false;
		if (updateRate != other.updateRate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SensorDescriptionUpdateRate [updateRate=" + updateRate
				+ ", precision=" + precision + ", toString()="
				+ super.toString() + "]";
	}


	
	
}
