package net.sf.seesea.navigation.sl2;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class D {

	private static final double RAD_CONVERSION = 180/Math.PI;
	private static final double EARTH_RADIUS = 6356752.3142;
	private static final float FEET_TO_METERS = 0.3048f;
	private static final float KNOTS = 1.852f;

	
	public static void main(String[] args) throws IOException {
//				 File file = new File("S:\\Segeln\\Data\\markus\\sl2\\Sonar0000.sl2"); //$NON-NLS-1$
//				 File file = new File("S:\\Segeln\\Data\\markus\\sl2\\sonar1.sl2"); //$NON-NLS-1$
		File file = new File("S:\\7381.dat"); //$NON-NLS-1$
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
		byte[] x = new byte[4096];
		// header is 8 bytes;
		dataInputStream.read(x, 0, 8);
		
		
		 JFrame fr = new JFrame();
		    fr.setSize(3760, 1600);
		    fr.getContentPane().setLayout(new BorderLayout());
		    BufferedImage bi = new BufferedImage(3760, 3072, BufferedImage.TYPE_BYTE_GRAY);
		    WritableRaster wr = bi.getRaster();
		    JLabel jl = new JLabel(new ImageIcon(bi));
		    ScrollPane scrollPane = new ScrollPane();
		    scrollPane.add(jl);
		    fr.getContentPane().add(scrollPane, BorderLayout.CENTER);
		    fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		    fr.setVisible(true);
			int ox = 0;

		int blockcounter = 0;
		while(true) {
//			short y = toBigEndianShort(dataInputStream.readShort()); // 142
			int a2 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 0
			int a11 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 2
			long position1 = a2 + (a11 * 65536);

			int a12 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 4
			int a21 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 6
			long position2 = a12 + (a21 * 65536);

			int a31 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 8
			int a32 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 10

			int a34 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 12
			int a36 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 14
			long position3 = a34 + (a36 * 65536);
			
			int a38 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 16
			
			short a5 = toBigEndianShort(dataInputStream.readShort()); // 18
			short a51 = toBigEndianShort(dataInputStream.readShort()); // 20
			short a61 = toBigEndianShort(dataInputStream.readShort()); // 22
			short a62 = toBigEndianShort(dataInputStream.readShort()); // 24
			short a7 = toBigEndianShort(dataInputStream.readShort()); // 26
			short blockSize = toBigEndianShort(dataInputStream.readShort()); // 28
			short lastBlockSize = toBigEndianShort(dataInputStream.readShort()); // 30
			short sensorType = toBigEndianShort(dataInputStream.readShort()); // 32
			short dataBlockSize = toBigEndianShort(dataInputStream.readShort()); // 34 probably data block size
			int frameIndex = toBigEndianInt(dataInputStream.readInt()); // 36
			dataInputStream.read(x, 0, 4); // 40
			float upperLimit = Float.intBitsToFloat(toBigEndianInt(x,0));
			dataInputStream.read(x, 0, 4); // 44
			float lowerLimit = Float.intBitsToFloat(toBigEndianInt(x,0)); 
			int c = toBigEndianInt(dataInputStream.readInt()); // 48
			int f = toBigEndianInt(dataInputStream.readInt()); // 52
			int byte1 = dataInputStream.readByte(); // 56
			int frequency = dataInputStream.readByte(); // 57
			int byte3 = dataInputStream.readByte(); //58
			int byte4 = dataInputStream.readByte(); //59

			int timeX = toBigEndianInt(dataInputStream.readInt()); // 60
			dataInputStream.read(x, 0, 4);
			float depth3 = Float.intBitsToFloat(toBigEndianInt(x,0)) * 0.3048f ; // 64
//			double cog = (course / Math.PI) * 180;
			int time1 = toBigEndianInt(dataInputStream.readInt()); // 68
			int f2 = toBigEndianInt(dataInputStream.readInt()); // 72
			dataInputStream.read(x, 0, 4); // 76
			float keelDepth = Float.intBitsToFloat(toBigEndianInt(x,0)); 

			dataInputStream.read(x, 0, 4);
			float k = Float.intBitsToFloat(toBigEndianInt(x,0)); // 80
			dataInputStream.read(x, 0, 4);
			float l = Float.intBitsToFloat(toBigEndianInt(x,0)); // 84
			dataInputStream.read(x, 0, 4);
			float m = Float.intBitsToFloat(toBigEndianInt(x,0)); // 88
			dataInputStream.read(x, 0, 4);
			float n = Float.intBitsToFloat(toBigEndianInt(x,0)); // 92
			dataInputStream.read(x, 0, 4);
			float o = Float.intBitsToFloat(toBigEndianInt(x,0)); // 96
			dataInputStream.read(x, 0, 4);
			float speed = Float.intBitsToFloat(toBigEndianInt(x,0)); // 100
			dataInputStream.read(x, 0, 4);
			float waterTemp = Float.intBitsToFloat(toBigEndianInt(x,0)); // 104
			double longitude = toLongitude(toBigEndianInt(dataInputStream.readInt())); // 108
			double latitude = toLatitude(toBigEndianInt(dataInputStream.readInt())); // 112
			dataInputStream.read(x, 0, 4);
			float speedthroughwater = Float.intBitsToFloat(toBigEndianInt(x,0)); // 116
			dataInputStream.read(x, 0, 4);
			double cog = Math.toDegrees(Float.intBitsToFloat(toBigEndianInt(x,0))); // 120
			dataInputStream.read(x, 0, 4);
			float altitude = Float.intBitsToFloat(toBigEndianInt(x,0)) * 0.3048f; // 124
			dataInputStream.read(x, 0, 4); // 128
			double heading = Math.toDegrees(Float.intBitsToFloat(toBigEndianInt(x,0)));
			int w3 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 130
			int w4 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 132
			int w1 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 134
			int w2 = toBigEndianShortAsInt(dataInputStream.readUnsignedShort()); // 136
			int compare = w3 & 1;
			boolean trackValid = (compare == 1 ? true : false);
			compare = w3 & 2;
			boolean depthValid = (compare == 2 ? true : false);
			compare = w3 & 4;
			boolean waterTempValid = (compare == 4 ? true : false);
			compare = w3 & 8;
			boolean positionValid = (compare == 8 ? true : false);
			compare = w3 & 16;
			boolean cogValid = (compare == 16 ? true : false);
			compare = w3 & 32;
			boolean x1Valid = (compare == 32 ? true : false);
			compare = w3 & 64;
			boolean speedValid = (compare == 64 ? true : false);
			compare = w3 & 128;
			boolean x3Valid = (compare == 128 ? true : false);
			compare = w3 & 256;
			boolean x4Valid = (compare == 256 ? true : false);
			compare = w3 & 512;
			boolean longitudeValid = (compare == 512 ? true : false);
			compare = w3 & 1024;
			boolean x6Valid = (compare == 1024 ? true : false);
			compare = w3 & 2048;
			boolean x7Valid = (compare == 2048 ? true : false);
			compare = w3 & 4096;
			boolean x8Valid = (compare == 4096 ? true : false);
			compare = w3 & 8192;
			boolean altitudeValid = (compare == 8192 ? true : false);
			compare = w3 & 16384;
			boolean headingValid = (compare == 16384 ? true : false);

			int time2 = toBigEndianInt(dataInputStream.readInt()); // 140
			
			if(frameIndex == 400) {
				System.out.println("x");
			}
//			System.out.println(frameIndex + ":" + y);
			dataInputStream.read(x, 0, dataBlockSize);

//			short y = toBigEndianShort(dataInputStream.readShort()); // 142
//			int x2 = toBigEndianInt(dataInputStream.readInt()); // 140

			if(sensorType == 0) {
//				System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
				for (int row=0; row<dataBlockSize; row++) {
					if(ox >= wr.getWidth()) {
						ox = 0;
					}
					wr.setPixel(ox, row , new int[] {x[row]&0xff});
//					System.out.println(ox + ":" +(x[row]&0xff) + ",");
				}
				ox++;
//				wr.setPixel(k / 2, 114, new int[] {255});
				if(ox % 8 == 0) {
					jl.repaint();
				}
			}
			blockcounter++;
			System.out.println(depth3 + ":" + waterTemp);
//			System.out.println(blockcounter + ":" + frameIndex);

			
			// header is 10 bytes;

		}
		

	}
	public static int toBigEndianShortAsInt(int littleendian) {
		return (int) (((0xFF00&littleendian)>>8)&0x00FF |
				((0x00FF&littleendian)<<8)&0xFF00);
	}
	
	public static short toBigEndianShort(short littleendian) {
		return (short) (((0xFF00&littleendian)>>8)&0x00FF |
				((0x00FF&littleendian)<<8)&0xFF00);
	}

 	public static int toBigEndianInt(int raw) {
 		ByteBuffer allocate = ByteBuffer.allocate(4);
 		allocate.putInt(raw);
 		allocate.flip();
 		allocate.order(ByteOrder.LITTLE_ENDIAN);
 		return allocate.getInt();
	}

 	public static int toBigEndianInt(byte[] raw, int offset) {
		return 0xFF000000&(raw[offset+3]<<24) | 0x00FF0000&(raw[offset+2]<<16) | 0x0000FF00&(raw[offset+1]<<8) | 0x000000FF&raw[offset];
	}

 	public static long toBigEndianLong(byte[] raw, int offset) {
 		long x = 0xFF000000&(raw[offset+3]<<24);
 		x |= 0x00FF0000&(raw[offset+2]<<16);
 		x |= 0x0000FF00&(raw[offset+1]<<8);
 		x |= 0x000000FF&raw[offset];
		return x;
	}

	/**
	 * Convert Lowrance mercator meter format into WGS84.
	 * Used this article as a reference: http://www.oziexplorer3.com/eng/eagle.html
	 * @return
	 */
	public static double toLongitude(int mercator) {
		return mercator/EARTH_RADIUS * RAD_CONVERSION;
	}
	
	public static double toLatitude(int mercator) {
		double temp = mercator/EARTH_RADIUS;
		temp = Math.exp(temp);
		temp = (2*Math.atan(temp))-(Math.PI/2);
		return temp * RAD_CONVERSION;			
	}

}
