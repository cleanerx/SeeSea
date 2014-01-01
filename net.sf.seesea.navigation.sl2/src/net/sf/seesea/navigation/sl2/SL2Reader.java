package net.sf.seesea.navigation.sl2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.util.GeoParser;
import net.sf.seesea.services.navigation.listener.IMeasurementListener;

public class SL2Reader implements ISL2Listener {

	private Collection<IMeasurementListener> listeners;

	public SL2Reader() {
		listeners = new ArrayList<IMeasurementListener>();
	}
	
	@Override
	public void notifySL2Block(int[] data) {

		short a11 = getShort(data, 0);
		short a12 = getShort(data, 2);
		// int a2 = toBigEndianInt(dataInputStream.readInt()); // 4
		// short a31 = toBigEndianShort(dataInputStream.readShort()); // 8
		// short a32 = toBigEndianShort(dataInputStream.readShort()); // 10
		// int a4 = toBigEndianInt(dataInputStream.readInt()); // 12
		// int a5 = toBigEndianInt(dataInputStream.readInt()); // 16
		// short a61 = toBigEndianShort(dataInputStream.readShort()); // 20
		// short a62 = toBigEndianShort(dataInputStream.readShort()); // 22
		// short a7 = toBigEndianShort(dataInputStream.readShort()); // 24

		short blockSize = getShort(data, 28);
		short lastBlockSize = getShort(data, 30);
		Short sensorID = getShort(data, 32);
		// // 28
		// short a1 = toBigEndianShort(dataInputStream.readShort()); // 30
		// short dataBlockSize = toBigEndianShort(dataInputStream.readShort());
		// // 32 probably data block size
		// int frameIndex = toBigEndianInt(dataInputStream.readInt()); // 34
		// int c = toBigEndianInt(dataInputStream.readInt()); // 36
		// dataInputStream.read(x, 0, 4);
		// float lowerLimit = Float.intBitsToFloat(toBigEndianInt(x,0)); // 40
		// int e = toBigEndianInt(dataInputStream.readInt()); // 44
		// int f = toBigEndianInt(dataInputStream.readInt()); // 48
		//
		// dataInputStream.read(x, 0, 4);
		// float g = Float.intBitsToFloat(toBigEndianInt(x,0)); // 52
		// int time1 = toBigEndianInt(dataInputStream.readInt()); // 56
//		 float depthValue = Float.intBitsToFloat(getInt(data, 62)) * 0.3048f; // 60
		// dataInputStream.read(x, 0, 4);
		// float j = Float.intBitsToFloat(toBigEndianInt(x,0)); // 64
		// short k1 = toBigEndianShort(dataInputStream.readShort()); // 30
		// short k2 = toBigEndianShort(dataInputStream.readShort()); // 32
		// probably data block size
		//
		// // dataInputStream.read(x, 0, 4);
		// // float k = Float.intBitsToFloat(toBigEndianInt(x,0)); // 20
		// int l = toBigEndianInt(dataInputStream.readInt()); // 68
		// dataInputStream.read(x, 0, 4);
		// float m = Float.intBitsToFloat(toBigEndianInt(x,0)); // 76
		// dataInputStream.read(x, 0, 4);
		// float n = Float.intBitsToFloat(toBigEndianInt(x,0)); // 80
		// dataInputStream.read(x, 0, 4);
		// float o = Float.intBitsToFloat(toBigEndianInt(x,0)); // 84
		// dataInputStream.read(x, 0, 4);
		// float p = Float.intBitsToFloat(toBigEndianInt(x,0)); // 88
		// dataInputStream.read(x, 0, 4);
		// float q = Float.intBitsToFloat(toBigEndianInt(x,0)); // 92
		// dataInputStream.read(x, 0, 4);
		// float depth = Float.intBitsToFloat(toBigEndianInt(x,0)); // 96
		// dataInputStream.read(x, 0, 4);
		// float waterTemp = Float.intBitsToFloat(toBigEndianInt(x,0)); // 100
		 float surfaceDepth = Float.intBitsToFloat(getInt(data,64)) * 0.3048f;

		 float waterTemperature = Float.intBitsToFloat(getInt(data,104));
		 Longitude longitude = GeoParser.parseLongitude(toLongitude(getInt(data, 108))); // 104
		 Latitude latitude = GeoParser.parseLatitude(toLatitude(getInt(data, 112))); // 104
		 float speed = Float.intBitsToFloat(getInt(data,116));
		 float cog = Float.intBitsToFloat(getInt(data,120));
		 
		 int time = getInt(data,140);
		 
		// 112
		// dataInputStream.read(x, 0, 4);
		// double degrees =
		// Math.toDegrees(Float.intBitsToFloat(toBigEndianInt(x,0))); // 116
		// dataInputStream.read(x, 0, 4);
		// float altitude = Float.intBitsToFloat(toBigEndianInt(x,0)); // 120
		// // dataInputStream.read(x, 0, 4);
		// int u = toBigEndianInt(dataInputStream.readInt()); // 128 getting 8
		// or 12
		// // float u = Float.intBitsToFloat(toBigEndianInt(x,0)); // 124
		// // dataInputStream.read(x, 0, 4);
		// int v = toBigEndianInt(dataInputStream.readInt()); // 128 getting 8
		// or 12
		// int w = toBigEndianInt(dataInputStream.readInt()); // 132
		// int time2 = toBigEndianInt(dataInputStream.readInt()); // 136
		//
		// short y = toBigEndianShort(dataInputStream.readShort()); // 26
		 
			MeasuredPosition3D geoPosition3D = GeoFactory.eINSTANCE.createMeasuredPosition3D();
			geoPosition3D.setLatitude(latitude);
			geoPosition3D.setLongitude(longitude);
			geoPosition3D.setValid(true);
			geoPosition3D.setTime(new Date(time));
			Depth depth = GeoFactory.eINSTANCE.createDepth();
			depth.setMeasurementPosition(RelativeDepthMeasurementPosition.SURFACE);
			depth.setDepth(surfaceDepth);
			depth.setSensorID(sensorID.toString());
			depth.setValid(true);
			depth.setTime(new Date(time));
			
			List<Measurement> measurements = new ArrayList<Measurement>(2);
			measurements.add(geoPosition3D);
			measurements.add(depth);

		 
			for (IMeasurementListener measurementListener : listeners) {
				measurementListener.notify(measurements);
			}
			
	}

	private short getShort(int[] data, int start) {
 		ByteBuffer allocate = ByteBuffer.allocate(2);
 		allocate.put((byte) data[start]);
 		allocate.put((byte) data[start + 1]);
 		allocate.order(ByteOrder.LITTLE_ENDIAN);
 		allocate.flip();
		return allocate.getShort();
	}

	private int getInt(int[] data, int start) {
		
		return 0xFF000000&(data[start+3]<<24) | 0x00FF0000&(data[start+2]<<16) | 0x0000FF00&(data[start+1]<<8) | 0x000000FF&data[start];
				
// 		ByteBuffer allocate = ByteBuffer.allocate(4);
// 		allocate.put((byte) data[start]);
// 		allocate.put((byte) data[start + 1]);
// 		allocate.put((byte) data[start + 2]);
// 		allocate.put((byte) data[start + 3]);
// 		allocate.order(ByteOrder.LITTLE_ENDIAN);
// 		allocate.flip();
//		return allocate.getInt();
	}

	public void addMeasurementListener(
			IMeasurementListener measurementListener) {
		listeners.add(measurementListener);
	}
	
	static final double RAD_CONVERSION = 180/Math.PI;
	static final double EARTH_RADIUS = 6356752.3142;

	public double toLongitude(int mercator) {
		return mercator/EARTH_RADIUS * RAD_CONVERSION;
	}
	
	public double toLatitude(int mercator) {
		double temp = mercator/EARTH_RADIUS;
		temp = Math.exp(temp);
		temp = (2*Math.atan(temp))-(Math.PI/2);
		return temp * RAD_CONVERSION;			
	}

}
