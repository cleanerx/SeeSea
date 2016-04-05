package net.sf.seesea.navigation.sl2.test;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class C {

	private static final double RAD_CONVERSION = 180/Math.PI;
	private static final double EARTH_RADIUS = 6356752.3142;
	private static final float FEET_TO_METERS = 0.3048f;
	private static final float KNOTS = 1.852f;
	
	 public static void main(String[] args) throws IOException {
		 
		 JFrame f = new JFrame();
		    f.setSize(3760, 2064);
		    f.getContentPane().setLayout(new BorderLayout());
		    BufferedImage bi = new BufferedImage(3760, 2064, BufferedImage.TYPE_BYTE_GRAY);
		    WritableRaster wr = bi.getRaster();
//		    for (int col=0; col<bi.getWidth(); col++) {
//		        for (int row=0; row<bi.getHeight(); row++) {
//		            wr.setPixel(col, row, new int[] {(col+row)&0xff});
//		        }
//		    }
		    JLabel l = new JLabel(new ImageIcon(bi));
		    ScrollPane scrollPane = new ScrollPane();
		    scrollPane.add(l);
		    f.getContentPane().add(scrollPane, BorderLayout.CENTER);
		    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		    f.setVisible(true);

		 
//			 File file = new File("S:\\Segeln\\Data\\markus\\sl2\\Sonar0000.sl2"); //$NON-NLS-1$
//			 File file = new File("S:\\Segeln\\Data\\markus\\sl2\\sonar1.sl2"); //$NON-NLS-1$
//		 File file = new File("S:\\8774.dat"); //$NON-NLS-1$
			 File file = new File("C:\\pv4\\e37\\27079.dat"); //$NON-NLS-1$
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			int i = 0;
			
			int k = 0;
			
			int lastI = 0;
			byte[] x = new byte[65536];
			while(true) {
				raf.read(x, 0, 65536);
				raf.seek(i++);

				// longitude - 116 : int but negativly and positively changing arbitrarily 
				// longitude - 112 : int but negativly and positively changing arbitrarily 

				// pretty sure here starts the header
				// longitude - 108 : int values starting at 0 occuring twice with increasing values of ~2072 
				// longitude - 104 : int values starting at 8 and occurring twice increasing by ~ 4000 
				// longitude - 100 : 0 
				// longitude - 96 : int values starting at 0 occuring twice with increasing values of ~2072 
				// longitude - 92 : 0 
				// longitude - 88 : 0 
				// longitude - 84 : 0 
				// longitude - 80 : int constant value of 135268368 
				// longitude - 76 : int toggling values of 125829120 / 125829122 probably sensor ids
				// longitude - 72 : int increasing number (frame number ?) starting at 1
				// longitude - 68 : 0
				// longitude - 64 : (double), relative constant values 20.0, 20.1, 33.4, 53.5, 53.4 , 66.8, 66.9, 100.3, 133.7, 13.3, 13.4 it is the lower limit
				// longitude - 60 : 0 
				// longitude - 56 : 0 
				// longitude - 52 : 0 (integer)
				// longitude - 48 : continous rising values with differences between 10 and 90 (integer) could be time
				// longitude - 48 : continious values from 0.0 up to 51.92900085449219 (double) probably depth in feet?
				// longitude - 40 : 0
				// longitude - 36 : 67 or 100 
				// longitude - 32 : 0
				// longitude - 28 : -1.0 (double)
				// longitude - 24 : -1.0 (double)
				// longitude - 20 : -1.0 (double)
				// longitude - 16 : -1.0 (double)
				// longitude - 12 : 0
				int a11 = toBigEndianShort(x, 2); // 0
				int a12 = toBigEndianShort(x, 4); // 0
				int a121 = toBigEndianShort(x, 6); // 0
				int a122 = toBigEndianShort(x, 8); // 0
				int a123 = toBigEndianShort(x, 10); // 0
				int a124 = toBigEndianShort(x, 12); // 0
				int a125 = toBigEndianShort(x, 14); // 0
				int a126 = toBigEndianShort(x, 16); // 0
				int a127 = toBigEndianShort(x, 18); // 0
				int a128 = toBigEndianShort(x, 20); // 0
				int a129 = toBigEndianShort(x, 22); // 0
				int a130 = toBigEndianShort(x, 24); // 0
				int a131 = toBigEndianShort(x, 26); // 0
				int a132 = toBigEndianShort(x, 28); // 0

				int size1 = toBigEndianShort(x, 34); // 0
				int size = toBigEndianShort(x, 36); // 0
//				int a131 = toBigEndianShort(x, 144 + 1920); // 0
//				int a13 = toBigEndianShort(x, 146 + 1920); // 0
//				int a14 = toBigEndianShort(x, 148 + 1920 ); // 0
//				int a15 = toBigEndianShort(x, 150 + 1920); // 0
//				int a16 = toBigEndianShort(x, 152 + 1920); // 0
//				int a17 = toBigEndianShort(x, 152 + 1920); // 0

				double y = Float.intBitsToFloat(toBigEndianInt(x, 0)); // double -1.0 
				int c = toBigEndianInt(x, 0);

				double lowLimit = Float.intBitsToFloat(toBigEndianInt(x, 4)); // double seems to be 0.0

				double depth = Float.intBitsToFloat(toBigEndianInt(x, 8));
				double temp = Float.intBitsToFloat(toBigEndianInt(x, 12));

				int sensorid = toBigEndianInt(x, 32);

				double longitude = toLongitude(toBigEndianInt(x, 110));
				double latitude = toLatitude(toBigEndianInt(x, 114));
				double surfaceDepth = Float.intBitsToFloat(toBigEndianInt(x, 24));
				double topOfBottomDepth = Float.intBitsToFloat(toBigEndianInt(x, 28)); // strange - double

//				int timeOffset = toBigEndianInt(x, 24);
				double timeOffset1 = Float.intBitsToFloat(toBigEndianInt(x, 32)); // double
//				int timeOffset = toBigEndianInt(x, 24);

				double speed = Float.intBitsToFloat(toBigEndianInt(x, 36));
//				double track = Float.intBitsToFloat(toBigEndianInt(x, 32));
				int track1 = toBigEndianInt(x, 40); // int 8,12,542
//				double altitude = Float.intBitsToFloat(toBigEndianInt(x, 40));
//				int altitude1 = toBigEndianInt(x,40);
//				long alt2 = toBigEndianLong(x, 40);

//				double rate = Float.intBitsToFloat(toBigEndianInt(x, 40));
				int rate2 = toBigEndianInt(x, 44); // int counter with interleaving distances of 90 and 10 differences

//				long y = toBigEndianLong(x, 44); // no real value to make sense of 

				
//				if(longitude > 7 && longitude < 13  && latitude > 40 && latitude < 60) {
				if(longitude > 103 && longitude < 105  && latitude > 0 && latitude < 2) {
					System.out.println("Start:   " + a11 + ":" + a12);
					System.out.println("Next:    " + a121 + ":" + a122);
					System.out.println("Next:    " + a123 + ":" + a124);
					System.out.println("Next:    " + a125 + ":" + a126);
					System.out.println("Next:    " + a127 + ":" + a128);
					System.out.println("Next:    " + a129 + ":" + a130);
					System.out.println("Next:    " + a131 + ":" + a132);
//					System.out.println("EndNext: " + a13 + ":" + a14);
//					System.out.println("EndNext: " + a15 + ":" + a16);
//					System.out.println(a11 + ":" + a12 + ":" + a121 +":" + a13 + ":" + a14 + ":" + size);
					System.out.println(i + ":" + (i - lastI)  + ":" + longitude + " " + latitude + " " + temp + " " + depth);
//					System.out.println(y + ":" + c + " " + latitude + ":" + longitude);
//					System.out.println(sensorid);
					if(sensorid == 125829120) {
						k+=2;
//						System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
//						int o = 0;
						for (int row=0; row<(bi.getHeight() ); row++) {
							wr.setPixel(k / 2, row , new int[] {x[row]&0xff});
//							System.out.println(o++ + ":" +(x[row]&0xff) + ",");
						}
//						wr.setPixel(k / 2, 114, new int[] {255});
						l.repaint();
					}
					
//					System.out.println("sd:" +  surfaceDepth + " td:" + topOfBottomDepth + " time:" + timeOffset  + " time:" + timeOffset1  + " speed " + speed + " track:" + track + " track1:" + track1 + " alt:" + altitude1 + " alt:" + altitude + " rate:" + rate);
					lastI = i;
				}

			}

	 }
	 
	 
	 	public static int toBigEndianShort(byte[] raw, int offset) {
			return 0x0000FF00&(raw[offset+1]<<8) | 0x000000FF&raw[offset];
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
