package net.sf.seesea.provider.navigation.adm.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TrackPointADM {

	private double lat;
	private double lon;
	private long timestamp;
	private float depth;
	private float waterTemperature;

	public TrackPointADM(int[] contents) {
		lat = getInt(contents,0) / 11930463.0783;
		lon = getInt(contents,4) / 11930463.0783;
		timestamp = getInt(contents,8);
		depth = Float.intBitsToFloat(getInt(contents,12));
		waterTemperature = Float.intBitsToFloat(getInt(contents,17));
	}
	
	private int getInt(int[] data, int start) {
 		ByteBuffer allocate = ByteBuffer.allocate(4);
 		allocate.put((byte) data[start]);
 		allocate.put((byte) data[start + 1]);
 		allocate.put((byte) data[start + 2]);
 		allocate.put((byte) data[start + 3]);
 		allocate.order(ByteOrder.LITTLE_ENDIAN);
 		allocate.flip();
		return allocate.getInt();
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public float getDepth() {
		return depth;
	}

	public float getWaterTemperature() {
		return waterTemperature;
	}
	
	


}
