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
				 File file = new File("S:\\Segeln\\Data\\markus\\sl2\\sonar1.sl2"); //$NON-NLS-1$
//		File file = new File("S:\\4077.dat"); //$NON-NLS-1$
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
		byte[] x = new byte[4096];
		// header is 10 bytes;
		dataInputStream.read(x, 0, 10);
		
		
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
			short a11 = toBigEndianShort(dataInputStream.readShort()); // 0
			short a12 = toBigEndianShort(dataInputStream.readShort()); // 2
			int a2 = toBigEndianInt(dataInputStream.readInt()); // 4
//			dataInputStream.read(x, 0, 4);
//			float a3 = Float.intBitsToFloat(toBigEndianInt(x,0)); // 40
			short a31 = toBigEndianShort(dataInputStream.readShort()); // 8
			short a32 = toBigEndianShort(dataInputStream.readShort()); // 10
//			int a3 = toBigEndianInt(dataInputStream.readInt()); // 36
			int a4 = toBigEndianInt(dataInputStream.readInt()); // 12
			int a5 = toBigEndianInt(dataInputStream.readInt()); // 16
			short a61 = toBigEndianShort(dataInputStream.readShort()); // 20
			short a62 = toBigEndianShort(dataInputStream.readShort()); // 22
			short a7 = toBigEndianShort(dataInputStream.readShort()); // 24
			short blockSize = toBigEndianShort(dataInputStream.readShort()); // 26
			short lastBlockSize = toBigEndianShort(dataInputStream.readShort()); // 28
			short a1 = toBigEndianShort(dataInputStream.readShort()); // 30
			short dataBlockSize = toBigEndianShort(dataInputStream.readShort()); // 32 probably data block size
			int frameIndex = toBigEndianInt(dataInputStream.readInt()); // 34
			int c = toBigEndianInt(dataInputStream.readInt()); // 36
			dataInputStream.read(x, 0, 4);
			float lowerLimit = Float.intBitsToFloat(toBigEndianInt(x,0)); // 40
			int e = toBigEndianInt(dataInputStream.readInt()); // 44
			int f = toBigEndianInt(dataInputStream.readInt()); // 48

			dataInputStream.read(x, 0, 4);
			float g = Float.intBitsToFloat(toBigEndianInt(x,0)); // 52
			int time1 = toBigEndianInt(dataInputStream.readInt()); // 56
			dataInputStream.read(x, 0, 4);
			float speed = Float.intBitsToFloat(toBigEndianInt(x,0)); // 60
			dataInputStream.read(x, 0, 4);
			float j = Float.intBitsToFloat(toBigEndianInt(x,0)); // 64
			short k1 = toBigEndianShort(dataInputStream.readShort()); // 68
			short k2 = toBigEndianShort(dataInputStream.readShort()); // 70

//			dataInputStream.read(x, 0, 4);
//			float k = Float.intBitsToFloat(toBigEndianInt(x,0)); // 20
			int l = toBigEndianInt(dataInputStream.readInt()); // 72
			dataInputStream.read(x, 0, 4);
			float m = Float.intBitsToFloat(toBigEndianInt(x,0)); // 76
			dataInputStream.read(x, 0, 4);
			float n = Float.intBitsToFloat(toBigEndianInt(x,0)); // 80
			dataInputStream.read(x, 0, 4);
			float o = Float.intBitsToFloat(toBigEndianInt(x,0)); // 84
			dataInputStream.read(x, 0, 4);
			float p = Float.intBitsToFloat(toBigEndianInt(x,0)); // 88
			dataInputStream.read(x, 0, 4);
			float q = Float.intBitsToFloat(toBigEndianInt(x,0)); // 92
			dataInputStream.read(x, 0, 4);
			float depth = Float.intBitsToFloat(toBigEndianInt(x,0)); // 96
			dataInputStream.read(x, 0, 4);
			float waterTemp = Float.intBitsToFloat(toBigEndianInt(x,0)); // 100
			double longitude = toLongitude(toBigEndianInt(dataInputStream.readInt())); // 104
			double latitude = toLatitude(toBigEndianInt(dataInputStream.readInt())); // 108
			dataInputStream.read(x, 0, 4);
			float surfaceDepth = Float.intBitsToFloat(toBigEndianInt(x,0)); // 112
			dataInputStream.read(x, 0, 4);
			double degrees = Math.toDegrees(Float.intBitsToFloat(toBigEndianInt(x,0))); // 116
			dataInputStream.read(x, 0, 4);
			float altitude = Float.intBitsToFloat(toBigEndianInt(x,0)); // 120
//			dataInputStream.read(x, 0, 4);
			int u = toBigEndianInt(dataInputStream.readInt()); // 124 getting 8 or 12
//			float u = Float.intBitsToFloat(toBigEndianInt(x,0)); // 124
//			dataInputStream.read(x, 0, 4);
			int v = toBigEndianInt(dataInputStream.readInt()); // 128 getting 8 or 12
			int w = toBigEndianInt(dataInputStream.readInt()); // 132
			int time2 = toBigEndianInt(dataInputStream.readInt()); // 136
			
			short y = toBigEndianShort(dataInputStream.readShort()); // 26

//			int y = toBigEndianInt(dataInputStream.readInt()); // 140
//			int h = toBigEndianInt(dataInputStream.readInt()); // 48
//			int i = toBigEndianInt(dataInputStream.readInt()); // 48
//			int j = toBigEndianInt(dataInputStream.readInt()); // 48
//			int k = toBigEndianInt(dataInputStream.readInt()); // 48
//			int l = toBigEndianInt(dataInputStream.readInt()); // 48
//			int m = toBigEndianInt(dataInputStream.readInt()); // 48
//			int n = toBigEndianInt(dataInputStream.readInt()); // 48
//			int o = toBigEndianInt(dataInputStream.readInt()); // 48
//			int p = toBigEndianInt(dataInputStream.readInt()); // 48
//			double i = Float.intBitsToFloat(toBigEndianInt(dataInputStream.read(x, 0, 4))); // 20
			
//			dataInputStream.read(x, 0, 2);;
//			ByteBuffer buffer = ByteBuffer.allocate(2);
//					buffer.put
//			short readShort = x[0] <<  
			if(frameIndex == 400) {
				System.out.println("x");
			}
//			System.out.println(frameIndex + ":" + y);
			dataInputStream.read(x, 0, blockSize - 144);

			if(a1 == 0) {
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
			System.out.println(blockcounter + ":" + frameIndex);

			
			// header is 10 bytes;

		}
		

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
