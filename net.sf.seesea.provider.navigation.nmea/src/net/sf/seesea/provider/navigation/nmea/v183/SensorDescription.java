package net.sf.seesea.provider.navigation.nmea.v183;

import net.sf.seesea.model.core.physx.Measurement;

public class SensorDescription<T extends Measurement> {

	private Class<T> measurement;
	
	private String sensorID;
	
	private String messageType;

	public SensorDescription(Class<T> measurement, String sensorID,
			String messageType) {
		super();
		this.measurement = measurement;
		this.sensorID = sensorID;
		this.messageType = messageType;
	}

	public Class<?> getMeasurement() {
		return measurement;
	}

	public String getSensorID() {
		return sensorID;
	}

	public String getMessageType() {
		return messageType;
	}

	@Override
	public String toString() {
		return "SensorDescription [measurement=" + measurement.getSimpleName()
				+ ", sensorID=" + sensorID + ", messageType=" + messageType
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((measurement == null) ? 0 : measurement.hashCode());
		result = prime * result
				+ ((messageType == null) ? 0 : messageType.hashCode());
		result = prime * result
				+ ((sensorID == null) ? 0 : sensorID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorDescription other = (SensorDescription) obj;
		if (measurement == null) {
			if (other.measurement != null)
				return false;
		} else if (!measurement.getName().equals(other.measurement.getName()))
			return false;
		if (messageType == null) {
			if (other.messageType != null)
				return false;
		} else if (!messageType.equals(other.messageType))
			return false;
		if (sensorID == null) {
			if (other.sensorID != null)
				return false;
		} else if (!sensorID.equals(other.sensorID))
			return false;
		return true;
	}
	
	
	
	
	
	
}