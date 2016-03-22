package net.sf.seesea.tidemodel.dtu10.java;


public class BillinearInterpolation {

	public static double billinearInterpolation(float twoDim[][], double valX, double valY, int xIndex, int yIndex) {
		float x1 = (xIndex * 0.125f) - 180 ; // (xIndex - 180) * 9;
		float y1 = ((yIndex * 0.125f - 90) * (-1));
	    float x2 = (xIndex + 1) * 0.125f - 180;
	    float y2 = (((yIndex + 1) * 0.125f - 90) * (-1));
		double fr1 = ((x2 - valX) / (x2 - x1)) * twoDim[xIndex][yIndex] + ((valX - x1) / (x2 - x1)) * twoDim[xIndex + 1][yIndex]; 
		double fr2 = ((x2 - valX) / (x2 - x1)) * twoDim[xIndex][yIndex + 1] + ((valX - x1) / (x2 - x1)) * twoDim[xIndex + 1][yIndex + 1]; 
//
		double fp  = ((y2 - valY) / (y2 - y1)) * fr1 + ((valY - y1) / (y2 - y1)) * fr2;
		
		double estimate = (1 / ((x2 - x1) * (y2 - y1))) * (twoDim[xIndex][yIndex] * (x2 - valX) * (y2 - valY) + twoDim[xIndex + 1][yIndex] * (valX - x1) * (y2 - valY) + twoDim[xIndex][yIndex +1] * (x2 - valX) * (valY - y1) + twoDim[xIndex +1][yIndex + 1] * (valX - x1) * (valY - y1));
		return fp;
	}
	
	public static void main(String args[]) {
			float[][] f = new float[][] {{0, 0},{0, 10}};
			double billinearInterpolation = BillinearInterpolation.billinearInterpolation(f, 0.5, 0.5, 0, 0);
			System.out.println(billinearInterpolation);
	}


}
