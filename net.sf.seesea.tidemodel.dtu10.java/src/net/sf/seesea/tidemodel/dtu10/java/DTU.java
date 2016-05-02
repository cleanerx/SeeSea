/**
 * This is the ocean Tide model from
 * http://www.space.dtu.dk/english/Research/Scientific_data_and_models/Global_Ocean_Tide_Model
 * 
 * It was originally written in Fortran an ported to Java
 * @author R Ray (Initial Implementation)
 * @author Jens Kübler (port to java)
 */

package net.sf.seesea.tidemodel.dtu10.java;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;

import net.sf.seesea.tidemodel.dtu10.java.data.HelperDate;
import net.sf.seesea.tidemodel.dtu10.java.data.ITideHeight;
import net.sf.seesea.tidemodel.dtu10.java.data.InterpolationValue;
import net.sf.seesea.tidemodel.dtu10.java.data.TideConsituents;
import net.sf.seesea.tidemodel.dtu10.java.data.TideHeight;
import net.sf.seesea.waterlevel.ocean.ITideProvider;

/**
 * This is the main DTU10 implementation. It provides a tide prediction based on the DTU 10 model and predicts the sea level at an arbitary point in time (limited by UTC time)
 *
 */
@org.osgi.service.component.annotations.Component(property = { "ocean:Boolean=true" })
public class DTU implements ITideProvider {

	private static int mx = 2881;
	private static int my = 1441;
	private static int ng = 10;
	private static int nt = 28;

	private float[][][][] tidemodelData = new float[mx][my][2][ng];
	boolean wrap;

	double latmin;
	double latmax;
	double lonmin;
	double lonmax;
	double undef;
	private int ny;
	private int nx;
	private URL dataLocation;

	@org.osgi.service.component.annotations.Activate
	public void activate(Map<String, Object> config) throws IOException {
		dataLocation = DTUJavaActivator.getDefault().getBundle().getEntry("res/tidalConsituents.txt.gz");
		loadTidalConsituents();
		double dx = (lonmax - lonmin) / (double) (nx - 1);
		wrap = Math.abs(lonmax - lonmin - 360.0) < 2 * dx;
	}

	public ITideHeight getTideHeight(double dlat, double dlon, double time) {
		double f[] = new double[nt];
		double u[] = new double[nt];
		double arg[] = new double[nt];

		for (int i = 0; i < nt; i++) {
			f[i] = 1.0;
			u[i] = 0.0;
		}

		boolean init = false;
		boolean isdata = false;

		boolean select[] = new boolean[ng];
		boolean isdata0 = false;

		undef = 99999.0f;
		double pi = Math.PI;
		double RAD = Math.PI / 180.0;
		double TWO = 2.0D;
		double THREE = 3.0D;
		double FOUR = 4.0D;

		double FIFETEEN = 15.0D;
		double THRITY = 30.0D;
		double NINETY = 90D;

		double DLAT0 = 4 * 999;
		double DLON0 = 4 * 999;
		double TIME0 = 4 * 999;
		double TIME1 = 4 * 999;

		boolean ISDATA;
		boolean ISDATA0;
		// DATA ? init

		// *
		// * determine tidal constants at this location
		// * ------------------------------------------
		// if((dlat != DLAT0) || dlon != DLON0) {

		double[][] H12 = billinearGridInterpolation(latmin, latmax, lonmin, lonmax, dlat, dlon, nx, ny);
		DLAT0 = dlat;
		DLON0 = dlon;
		// ISDATA0 = ISDATA;
		// *
		// * move "new" tides to end of H12 array (thus avoids some recoding)
		// * ----------------------------------------------------------------
		H12[0][26] = H12[0][3];
		H12[1][26] = H12[1][3];
		H12[0][27] = H12[0][9];
		H12[1][27] = H12[1][9];
		H12[0][3] = H12[0][4];
		H12[1][3] = H12[1][4];
		H12[0][4] = H12[0][5];
		H12[1][4] = H12[1][5];
		H12[0][5] = H12[0][6];
		H12[1][5] = H12[1][6];
		H12[0][6] = H12[0][7];
		H12[1][6] = H12[1][7];
		H12[0][7] = H12[0][8];
		H12[1][7] = H12[1][8];
		// *
		// * infer minor tides at this location
		// * ----------------------------------
		H12[0][8] = 0.263 * H12[0][0] - 0.0252 * H12[0][1];
		H12[1][8] = 0.263 * H12[1][0] - 0.0252 * H12[1][1];
		H12[0][9] = 0.297 * H12[0][0] - 0.0264 * H12[0][1];
		H12[1][9] = 0.297 * H12[1][0] - 0.0264 * H12[1][1];
		H12[0][10] = 0.164 * H12[0][0] + 0.0048 * H12[0][1];
		H12[1][10] = 0.164 * H12[1][0] + 0.0048 * H12[1][1];
		H12[0][11] = 0.0140 * H12[0][1] + 0.0101 * H12[0][3];
		H12[1][11] = 0.0140 * H12[1][1] + 0.0101 * H12[1][3];
		H12[0][12] = 0.0389 * H12[0][1] + 0.0282 * H12[0][3];
		H12[1][12] = 0.0389 * H12[1][1] + 0.0282 * H12[1][3];
		H12[0][13] = 0.0064 * H12[0][2] + 0.0060 * H12[0][3];
		H12[1][13] = 0.0064 * H12[1][1] + 0.0060 * H12[1][3];
		H12[0][14] = 0.0030 * H12[0][1] + 0.0171 * H12[0][3];
		H12[1][14] = 0.0030 * H12[1][1] + 0.0171 * H12[1][3];
		H12[0][15] = -0.0015 * H12[0][1] + 0.0152 * H12[0][3];
		H12[1][15] = -0.0015 * H12[1][1] + 0.0152 * H12[1][3];
		H12[0][16] = -0.0065 * H12[0][1] + 0.0155 * H12[0][3];
		H12[1][16] = -0.0065 * H12[1][1] + 0.0155 * H12[1][3];
		H12[0][17] = -0.0389 * H12[0][1] + 0.0836 * H12[0][3];
		H12[1][17] = -0.0389 * H12[1][1] + 0.0836 * H12[1][3];
		H12[0][18] = -0.0431 * H12[0][1] + 0.0613 * H12[0][3];
		H12[1][18] = -0.0431 * H12[1][1] + 0.0613 * H12[1][3];
		H12[0][19] = 0.264 * H12[0][4] - 0.0253 * H12[0][5];
		H12[1][19] = 0.264 * H12[1][4] - 0.0253 * H12[1][5];
		H12[0][20] = 0.298 * H12[0][4] - 0.0264 * H12[0][5];
		H12[1][20] = 0.298 * H12[1][4] - 0.0264 * H12[1][5];
		H12[0][21] = 0.165 * H12[0][4] + 0.00487 * H12[0][5];
		H12[1][21] = 0.165 * H12[1][4] + 0.00487 * H12[1][5];
		H12[0][22] = 0.0040 * H12[0][5] + 0.0074 * H12[0][6];
		H12[1][22] = 0.0040 * H12[1][5] + 0.0074 * H12[1][6];
		H12[0][23] = 0.0131 * H12[0][5] + 0.0326 * H12[0][6];
		H12[1][23] = 0.0131 * H12[1][5] + 0.0326 * H12[1][6];
		H12[0][24] = 0.0033 * H12[0][5] + 0.0082 * H12[0][6];
		H12[1][24] = 0.0033 * H12[1][5] + 0.0082 * H12[1][6];
		H12[0][25] = 0.0585 * H12[0][6];
		H12[1][25] = 0.0585 * H12[1][6];
		// } else {
		// ISDATA = ISDATA0
		// }
		// if(!ISDATA) {
		// return null;
		// }

		/* determine equilibrium tidal arguments */
		// double lastTime;
		// double time;
		// if(time != lastTime) {
		// lastTime = time;
		double hours = (time - ((int) time)) * 24.0;
		double T1 = FIFETEEN * hours;
		double T2 = THRITY * hours;
		double[] shpn = astro5(time);
		double S = shpn[0];
		double H = shpn[1];
		double P = shpn[2];
		double OMEGA = shpn[3];
		double PP = shpn[4];

		arg[0] = T1 + H - THREE * S + P - NINETY; // ! Q1
		arg[1] = T1 + H - TWO * S - NINETY; // ! O1
		arg[2] = T1 - H - NINETY; // ! P1
		arg[3] = T1 + H + NINETY; // ! K1
		arg[4] = T2 + TWO * H - THREE * S + P; // ! N2
		arg[5] = T2 + TWO * H - TWO * S; // ! M2
		arg[6] = T2; // ! S2
		arg[7] = T2 + TWO * H; // ! K2
		arg[8] = T1 - FOUR * S + H + TWO * P - NINETY; // ! 2Q1
		arg[9] = T1 - FOUR * S + THREE * H - NINETY; // ! sigma1
		arg[10] = T1 - THREE * S + THREE * H - P - NINETY; // ! rho1
		arg[11] = T1 - S + H - P + NINETY; // ! M1
		arg[12] = T1 - S + H + P + NINETY; // ! M1
		arg[13] = T1 - S + THREE * H - P + NINETY; // ! chi1
		arg[14] = T1 - TWO * H + PP - NINETY; // ! pi1
		arg[15] = T1 + THREE * H + NINETY; // ! phi1
		arg[16] = T1 + S - H + P + NINETY; // ! theta1
		arg[17] = T1 + S + H - P + NINETY; // ! J1
		arg[18] = T1 + TWO * S + H + NINETY; // ! OO1
		arg[19] = T2 - FOUR * S + TWO * H + TWO * P; // ! 2N2
		arg[20] = T2 - FOUR * S + FOUR * H; // ! mu2
		arg[21] = T2 - THREE * S + FOUR * H - P; // ! nu2
		arg[22] = T2 - S + P + 180.D; // ! lambda2
		arg[23] = T2 - S + TWO * H - P + 180.D; // ! L2
		arg[24] = T2 - S + TWO * H + P; // ! L2
		arg[25] = T2 - H + PP; // ! T2
		arg[26] = T1 + 180.D; // ! S1 (Doodson's phase)
		arg[27] = TWO * arg[5]; // ! M4
		// }
		// /*
		// *
		// * determine nodal corrections f and u
		// * Note: Update this code next iteration of model -RDR
		// *
		// * /
		// double TIME1 = 4*999.0;
		// if (Math.abs(time-TIME1) > 1) {
		// TIME1 = time;
		double SINN = Math.sin(OMEGA * RAD);
		double COSN = Math.cos(OMEGA * RAD);
		double SIN2N = Math.sin(TWO * OMEGA * RAD);
		double COS2N = Math.cos(TWO * OMEGA * RAD);

		f[0] = 1.009 + 0.187 * COSN - 0.015 * COS2N;
		f[1] = f[0];
		f[3] = 1.006 + 0.115 * COSN - 0.009 * COS2N;
		f[4] = 1.000 - 0.037 * COSN;
		f[5] = f[4];
		f[7] = 1.024 + 0.286 * COSN + 0.008 * COS2N;
		f[8] = Math.sqrt(
				Math.pow((1.0 + 0.189 * COSN - 0.0058 * COS2N), 2) + Math.pow((0.189 * SINN - 0.0058 * SIN2N), 2));
		f[9] = f[8];
		f[10] = f[8];
		f[11] = Math.sqrt(Math.pow((1.0 + 0.185 * COSN), 2) + Math.pow((0.185 * SINN), 2));
		f[12] = Math.sqrt(Math.pow((1.0 + 0.201 * COSN), 2) + Math.pow((0.201 * SINN), 2));
		f[13] = Math.sqrt(Math.pow((1.0 + 0.221 * COSN), 2) + Math.pow((0.221 * SINN), 2));
		f[17] = Math.sqrt(Math.pow((1.0 + 0.198 * COSN), 2) + Math.pow((0.198 * SINN), 2));
		f[18] = Math
				.sqrt(Math.pow((1.0 + 0.640 * COSN + 0.134 * COS2N), 2) + Math.pow((0.640 * SINN + 0.134 * SIN2N), 2));
		f[19] = Math.sqrt(Math.pow((1.0 - 0.0373 * COSN), 2) + Math.pow((0.0373 * SINN), 2));
		f[20] = f[19];
		f[21] = f[19];
		f[23] = f[19];
		f[24] = Math.sqrt(Math.pow((1.0 + 0.441 * COSN), 2) + Math.pow((0.441 * SINN), 2));
		f[27] = f[5] * f[5];

		u[0] = 10.8 * SINN - 1.3 * SIN2N;
		u[1] = u[0];
		u[3] = -8.9 * SINN + 0.7 * SIN2N;
		u[4] = -2.1 * SINN;
		u[5] = u[4];
		u[7] = -17.7 * SINN + 0.7 * SIN2N;
		u[8] = Math.atan2(0.189 * SINN - 0.0058 * SIN2N, 1.0 + 0.189 * COSN - 0.0058 * SIN2N) / RAD;
		u[9] = u[8];
		u[10] = u[8];
		u[11] = Math.atan2(0.185 * SINN, 1.0 + 0.185 * COSN) / RAD;
		u[12] = Math.atan2(-0.201 * SINN, 1.0 + 0.201 * COSN) / RAD;
		u[13] = Math.atan2(-0.221 * SINN, 1.0 + 0.221 * COSN) / RAD;
		u[17] = Math.atan2(-0.198 * SINN, 1.0 + 0.198 * COSN) / RAD;
		u[18] = Math.atan2(-0.640 * SINN - 0.134 * SIN2N, 1.0 + 0.640 * COSN + 0.134 * COS2N) / RAD;
		u[19] = Math.atan2(-0.0373 * SINN, 1.0 - 0.0373 * COSN) / RAD;
		u[20] = u[19];
		u[21] = u[19];
		u[23] = u[19];
		u[24] = Math.atan2(-0.441 * SINN, 1.0 + 0.441 * COSN) / RAD;
		u[27] = TWO * u[5];
		// }

		double sum = 0;
		for (int i = 0; i < nt; i++) {
			double h1 = H12[0][i];
			double h2 = H12[1][i];
			double chiu = (arg[i] + u[i]) * RAD;
			sum = sum + h1 * f[i] * Math.cos(chiu) + h2 * f[i] * Math.sin(chiu);
		}

		return new TideHeight(sum, true);
	}

	/**
	 * this methods reads the tidal consitues file and fills the world grid array with amplitude and phase
	 * @throws IOException
	 */
	private void loadTidalConsituents() throws IOException {
		double degree2radians = Math.toRadians(1);
		InputStream openStream = dataLocation.openStream();
		GZIPInputStream gzipInputStream = new GZIPInputStream(openStream);
		try (DataInputStream dataInputStream = new DataInputStream(
				new BufferedInputStream(gzipInputStream, 65536 * 4))) {

			for (int i = 0; i < 10; i++) {
				TideConsituents amplitude = grdinp(dataInputStream);
				TideConsituents phase = grdinp(dataInputStream);

				latmin = amplitude.getLatmin();
				latmax = amplitude.getLatmax();
				lonmin = amplitude.getLonmin();
				lonmax = amplitude.getLonmax();
				undef = amplitude.getUndefined();

				for (int j = 0; j < phase.getnY(); j++) {
					for (int k = 0; k < phase.getnX(); k++) {
						tidemodelData[k][j][0][i] = (float) (amplitude.getModelData()[k][j]
								* Math.cos(phase.getModelData()[k][j] * degree2radians));
						tidemodelData[k][j][1][i] = (float) (amplitude.getModelData()[k][j]
								* Math.sin(phase.getModelData()[k][j] * degree2radians));
					}
				}
			}
		}

	}

	private void check1() {

	}

	/**
	 * 
	 * Reads an ascii gridfile. This format uses 80-byte card-image records in
	 * ascii.
	 * 
	 * Calling arguments:
	 * 
	 * G - O - two-dimensional array to contain the gridded data. The grid will
	 * be loaded in the following way: G(i,j), with i going west to east and j
	 * south to north. G(1,1) is thus the southwest corner of grid. G(NX,NY) is
	 * the northeast corner. User must make certain G is dimensioned to at least
	 * the expected values of NX,NY.
	 * 
	 * NDIM1,2 - I - dimensions of G in calling program. NDIM must not be less
	 * than the expected value of NX.
	 * 
	 * NX,NY - O - size of the grid G.
	 * 
	 * TITLE - O - 160-byte character-string title that describes data.
	 * 
	 * LU - I - fortran unit to use for reading input data.
	 * 
	 * LATMIN,LATMAX,LONMIN,LONMAX - O - area limits for grid, in decimal
	 * degrees. Note: these are REAL variables. The grid intervals are
	 * therefore: DX = (LONMAX-LONMIN)/(NX-1) and DY = (LATMAX-LATMIN)/(NY-1).
	 * 
	 * UNDEF - O - value to denote a null or missing value in G.
	 * 
	 * 
	 * Written by R. Ray Aug. 1990 Modified 6/3/93 - added NDIM2 in calling
	 * arguments to test size of NY Revised 6/29/07 - if EOF hit, then set grids
	 * to zero. *
	 * 
	 * @param arrayToBeRead
	 * @param dataInputStream
	 * 
	 */
	private TideConsituents grdinp(DataInputStream dataInputStream) {
		int maxx = 2881;
		int maxy = 1441;

		float[][] arrayToBeRead = new float[maxx][maxy];
		byte[] x = new byte[1024];

		try {
			int read = dataInputStream.read(x, 0, 30);
			String titleRead = new String(x);
			System.out.println(titleRead);
			read = dataInputStream.read(x, 0, 32);
			read = dataInputStream.read(x, 0, 16);
			read = dataInputStream.read(x, 0, 5);
			ny = Integer.parseInt(new String(x, 0, 5).trim());
			dataInputStream.skipBytes(16);
			read = dataInputStream.read(x, 0, 5);
			nx = Integer.parseInt(new String(x, 0, 5).trim());
			dataInputStream.skipBytes(16);
			read = dataInputStream.read(x, 0, 8);
			double latmin = Double.parseDouble(new String(x, 0, 8).trim());
			dataInputStream.skipBytes(16);
			read = dataInputStream.read(x, 0, 8);
			double latmax = Double.parseDouble(new String(x, 0, 8).trim());
			dataInputStream.skipBytes(16);
			read = dataInputStream.read(x, 0, 8);
			double lonmin = Double.parseDouble(new String(x, 0, 8).trim());
			dataInputStream.skipBytes(16);
			read = dataInputStream.read(x, 0, 8);
			double lonmax = Double.parseDouble(new String(x, 0, 8).trim());
			dataInputStream.skipBytes(16);
			read = dataInputStream.read(x, 0, 8);
			double undefined = Double.parseDouble(new String(x, 0, 8).trim());
			dataInputStream.skipBytes(16);
			read = dataInputStream.read(x, 0, 14);
			String formatX = new String(x, 0, 14).trim();
			String cleanedFormat = formatX.substring(1, formatX.length() - 1);
			String[] splitFormat = cleanedFormat.split("F");
			int dataconunt = Integer.parseInt(splitFormat[0]);
			String floatformat = splitFormat[1];
			String[] splitFloatFormat = floatformat.split("\\.");
			int bytesToBeRead = Integer.parseInt(splitFloatFormat[0]);
			for (int j = 0; j < ny; j++) {
				for (int i = 0; i < dataconunt; i++) {
					read = dataInputStream.read(x, 0, bytesToBeRead);
					float value = Float.parseFloat(new String(x, 0, bytesToBeRead).trim());
					arrayToBeRead[i][j] = value;
				}
				dataInputStream.read(); // read new line character
			}
			return new TideConsituents(arrayToBeRead, ny, nx, latmin, latmax, lonmin, lonmax, undefined);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * Computes the 5 basic astronomical mean longitudes s, h, p, N, p'.
	 * 
	 * Note N is not N', i.e. N is decreasing with time.
	 * 
	 * TIME is UTC in decimal Modified Julian Day (MJD). All longitudes returned
	 * in degrees.
	 * 
	 * R. D. Ray, NASA/GSFC August 2003
	 * 
	 * Most of the formulae for mean longitudes are extracted from Jean Meeus,
	 * Astronomical Algorithms, 2nd ed., 1998. Page numbers below refer to this
	 * book.
	 * 
	 * Note: This routine uses TIME in UT and does not distinguish between the
	 * subtle differences of UTC, UT1, etc. This is more than adequate for the
	 * calculation of these arguments, especially in tidal studies.
	 */
	private double[] astro5(double TIME) {
		double SHPNP[];
		SHPNP = new double[5];
		double T, CIRCLE = 360, DEL = 0, TJLAST = -1, D;
		double TJD;

		TJD = TIME + 2400000.5;
		if (Math.abs(TJD - TJLAST) > 100.0) {
			DEL = deltat(TJD) / 86400.0;
			TJLAST = TJD;
		}
		TJD = TJD + DEL;

		// * Compute time argument in centuries relative to J2000
		// * ----------------------------------------------------
		T = (TJD - 2451545.0) / 36525.0;
		// *
		// *
		// * mean longitude of moon (p.338)
		// * ------------------------------
		SHPNP[0] = (((Math.pow(-1.53388, -8) * T + Math.pow(1.855835, -6)) * T - Math.pow(1.5786, -3)) * T
				+ 481267.88123421) * T + 218.3164477;
		// *
		// * mean elongation of moon (p.338)
		// * -------------------------------
		D = (((Math.pow(-8.8445, -9) * T + Math.pow(1.83195, -6)) * T - Math.pow(1.8819, -3)) * T + 445267.1114034) * T
				+ 297.8501921;
		// *
		// * mean longitude of sun
		// * ---------------------
		SHPNP[1] = SHPNP[0] - D;
		// *
		// * mean longitude of lunar perigee (p.343)
		// * ---------------------------------------
		SHPNP[2] = ((Math.pow(-1.249172, -5) * T - Math.pow(1.032, -2)) * T + 4069.0137287) * T + 83.3532465;
		// *
		// * mean longitude of ascending lunar node (p.144)
		// * ----------------------------------------------
		SHPNP[3] = ((Math.pow(2.22222, -6) * T + Math.pow(2.0708, -3)) * T - 1934.136261) * T + 125.04452;
		// *
		// * mean longitude of solar perigee (Simon et al., 1994)
		// * ----------------------------------------------------
		SHPNP[4] = 282.94 + 1.7192 * T;

		SHPNP[0] = SHPNP[0] % CIRCLE;
		SHPNP[1] = SHPNP[1] % CIRCLE;
		SHPNP[2] = SHPNP[2] % CIRCLE;
		SHPNP[3] = SHPNP[3] % CIRCLE;

		if (SHPNP[0] < 0) {
			SHPNP[0] = SHPNP[0] + CIRCLE;
		}
		if (SHPNP[1] < 0) {
			SHPNP[1] = SHPNP[1] + CIRCLE;
		}
		if (SHPNP[2] < 0) {
			SHPNP[2] = SHPNP[2] + CIRCLE;
		}
		if (SHPNP[3] < 0) {
			SHPNP[3] = SHPNP[3] + CIRCLE;
		}

		return SHPNP;
	}

	/**
	 * 
	 * Interpolates a value from a grid of data at the desired location.
	 * Interpolation is bilinear. First 12 arguments above all describe the
	 * grid. WRAP is T if grid allows wrap-around in longitude. DLAT,DLON -
	 * location of desired position for interpolation. VAL - returns
	 * interpolated value. ISDATA is returned F if no valid data at position
	 * (DLAT,DLON).
	 * 
	 * R. Ray 3/13/91
	 * 
	 * Revised 6/3/93 to allow multiple grids to be handled. VAL is now returned
	 * as an array; GRID is changed to 3 dimensions. ISDATA is still a scalar
	 * (all grids assumed to have same nulls). Revised 1/10/95 to test longitude
	 * limits when wrap=.false.
	 */
	private double[][] billinearGridInterpolation(double latmin, double latmax, double longmin, double longmax,
			double dlat, double dlon, int nx, int ny) {
		double dx = (longmax - longmin) / (double) (nx - 1);
		double dy = (latmax - latmin) / (double) (ny - 1);
		int jlat1 = (int) ((dlat - latmin) / dy);
		int jlat2 = jlat1 + 1;
		boolean isData = true;
		double val[][] = new double[2][28];

		if (jlat1 < 1 || jlat2 > ny) {
			isData = false;
			return null;
		}
		double xlon = dlon;
		if (wrap && xlon < longmin) {
			xlon = xlon + 360;
		}
		if (wrap && xlon > longmax) {
			xlon = xlon - 360;
		}
		// if (wrap && (xlon + 360.0) >= longmin) {
		// xlon = xlon - 360.0;
		// }
		int ilon1 = ((int) ((xlon - longmin) / dx));
		int ilon2 = ilon1 + 1;
		if (!wrap && (ilon1 < 1 || ilon2 > nx)) {
			isData = false;
			return null;
		}
		if (ilon2 > nx) {
			if (wrap) {
				ilon2 = 1;
			} else {
				isData = false;
				return null;
			}
		}
		if (ilon1 < 1 || ilon1 > nx) {
			System.out.println("Something went terribly wrong");
			System.exit(-1);
		}
		// undefined check
		// if(array[ilon1, jlat1[]])
		double w1 = 0.0;
		double w2 = 0.0;
		double wx1 = (dx - (xlon - ((double) (ilon1)) * dx - longmin)) / dx;
		double wx2 = 1.0 - wx1;
		double wy1 = (dy - (dlat - ((double) (jlat1)) * dy - latmin)) / dy;
		double wy2 = 1.0 - wy1;

		// Interpolation weights:
		// W1,W2,W3,W4 are for northwest,northeast,southeast,southwest corners.
		w1 = wx1 * wy2;
		w2 = wx2 * wy2;
		double w3 = wx2 * wy1;
		double w4 = wx1 * wy1;
		double w = 0.0;
		;

		double undefined = 99999;
		// double val[] = new double[10];
		for (int i = 0; i < 10 * 2; i++) {
			for (int k = 0; k < 2; k++) {
				val[k][i] = 0.0;
			}
		}

		// if grid value not undefined
		if (tidemodelData[ilon1][jlat1][0][0] != undefined) {
			w = w4;
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 2; k++) {
					val[k][j] = w4 * tidemodelData[ilon1][jlat1][k][j];
				}
			}
		}
		w = w + w1;
		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 2; k++) {
				val[k][j] = val[k][j] + w1 * tidemodelData[ilon1][jlat2][k][j];
			}
		}
		w = w + w2;
		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 2; k++) {
				val[k][j] = val[k][j] + w2 * tidemodelData[ilon2][jlat2][k][j];
			}
		}
		w = w + w3;
		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 2; k++) {
				val[k][j] = val[k][j] + w3 * tidemodelData[ilon2][jlat1][k][j];
			}
		}
		if (w > 0.5) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 2; k++) {
					val[k][j] = val[k][j] / w;
				}
			}
		} else {
			// no data;
			return null;
		}

		// }
		return val;
	}

	/**
	 * 
	 * Function - computes the long-period equilibrium ocean tides.
	 * 
	 * Arguments - name type I/O description ---- ---- --- ----------- TS D I
	 * modified Julian day, in seconds, denoting time at which tide is to be
	 * computed.
	 * 
	 * DLAT D I latitude in degrees (positive north) for the position at which
	 * tide is computed.
	 * 
	 * TLP D O computed long-period tide, in centimeters.
	 * 
	 * 
	 * Processing logic - Fifteen tidal spectral lines from the
	 * Cartwright-Tayler-Edden tables are summed over to compute the long-period
	 * tide.
	 * 
	 * Technical references - Cartwright & Tayler, Geophys. J. R.A.S., 23, 45,
	 * 1971. Cartwright & Edden, Geophys. J. R.A.S., 33, 253, 1973.
	 * 
	 * History - version date programmer change description ------- ----
	 * ---------- ------------------ 1.0 11/27/90 D. Cartwright Documented by R.
	 * Ray
	 * 
	 * 
	 */
	private void lpeqmt() {

	}

	/**
	 * 
	 * Reads an ascii gridfile in the "standard" format. This format uses
	 * 80-byte card-image records in ascii.
	 * 
	 * Calling arguments:
	 * 
	 * G - O - two-dimensional array to contain the gridded data. The grid will
	 * be loaded in the following way: G(i,j), with i going west to east and j
	 * south to north. G(1,1) is thus the southwest corner of grid. G(NX,NY) is
	 * the northeast corner. User must make certain G is dimensioned to at least
	 * the expected values of NX,NY.
	 * 
	 * NDIM - I - first dimension of G in calling program. NDIM must not be less
	 * than the expected value of NX.
	 * 
	 * NX,NY - O - size of the grid G.
	 * 
	 * TITLE - O - 160-byte character-string title that describes data.
	 * 
	 * LU - I - fortran unit to use for reading input data.
	 * 
	 * LATMIN,LATMAX,LONMIN,LONMAX - O - area limits for grid, in decimal
	 * degrees. Note: these are REAL variables. The grid intervals are
	 * therefore: DX = (LONMAX-LONMIN)/(NX-1) and DY = (LATMAX-LATMIN)/(NY-1).
	 * 
	 * UNDEF - O - value to denote a null or missing value in G.
	 * 
	 * 
	 * Written by R. Ray Aug. 1990 Revised 8 Apr 1998 - change name. - handle 2
	 * nulls on input file.
	 */
	private void gridin() {

	}

	/**
	 * Outputs an ascii gridfile in "standard" format. This format uses 80-byte
	 * card-image records in ascii.
	 * 
	 * Calling arguments:
	 * 
	 * G - I - input two-dimensional array containing gridded data. The grid
	 * should be loaded in the following way: G(i,j), with i going west to east
	 * and j south to north. (same as for DIUTIL contouring package). G(1,1) is
	 * southwest corner of grid.
	 * 
	 * NDIM - I - first dimension of G in calling program.
	 * 
	 * NX,NY - I - size of G. Thus G(NX,NY) is northeast corner of grid.
	 * 
	 * TITLE - I - 160-byte character-string title for image.
	 * 
	 * LU - I - fortran unit to use for writing output data.
	 * 
	 * LATMIN,LATMAX,LONMIN,LONMAX - I - area limits for grid, in decimal
	 * degrees. Note: these are REAL variables.
	 * 
	 * UNDEF - I - value to denote a null or missing value in G.
	 * 
	 * FORMAT- I - character string containing fortran format to use for writing
	 * G. Example: '(10F8.2)' Warning: If G contains values too large for
	 * FORMAT, '*'s can be written to the output dataset. User should check that
	 * this hasn't happened.
	 * 
	 * 
	 * Written by R. Ray Aug. 1990
	 * 
	 * Revised - R. Ray 5/4/91 1. Included UNDEF in calling arguments. 2.
	 * Removed REWIND & error checking (for '*'s) because of this routine's
	 * possible use in multiple calls for many outputs. 3. Allowed FORMAT
	 * variable length character. Revised 3/8/98 - changed name.
	 */
	private void grindout() {

	}

	public double getTideHeight(double lat, double lon, Date time) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
		calendar.setTime(time);
		double hour = calendar.get(Calendar.HOUR_OF_DAY);
		hour += ((double) calendar.get(Calendar.MINUTE)) / 60;
		hour += ((double) calendar.get(Calendar.SECOND)) / 3600;

		double modifiedJulian = juldat2(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), hour) - 2400000.5;
		try {
			double tideOffset = getTideHeight(lat, lon, modifiedJulian).getHeight();
			return tideOffset;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	private double juldat2(int year, int month, int day, double hoursAfterMidnight) {
		int jd, i;
		int igreg = (15 + 31 * (10 + 12 * 1582));
		double tjd, xy, xm, xa, xb;

		if (month > 2) {
			xy = year;
			xm = month + 1;
		} else {
			xy = year - 1;
			xm = month + 13;
		}
		i = day + 31 * (month + 12 * year);
		if (i >= igreg) {
			xa = (int) (0.01 * xy);
			xb = 2.0 - xa + (int) (0.25 * xa);
		} else {
			xb = 0.0;
		}
		jd = (int) (365.25 * (xy + 4716.0)) + (int) (30.6001 * xm) + day;
		tjd = jd + xb - 1524.5 + hoursAfterMidnight / 24.0;
		return tjd;
	}

	/**
	 * 
	 * @param tjd
	 *            UT julian day number
	 * @return ET - UT in seconds
	 */
	private double deltat(double tjd) {
		double tjde, secdif;
		int tabstart = 1620, tabend = 2004, tabsize = tabend - tabstart;
		double years[] = new double[tabsize];
		int j, iy, im, id, iflag = 0, mp = 2, iat;
		double hr, b, xx, dy;
		double dt[] = new double[] { 124.00, 119.00, 115.00, 110.00, 106.00, 102.00, 98.00, 95.00, 91.00, 88.00, 85.00,
				82.00, 79.00, 77.00, 74.00, 72.00, 70.00, 67.00, 65.00, 63.00, 62.00, 60.00, 58.00, 57.00, 55.00, 54.00,
				53.00, 51.00, 50.00, 49.00, 48.00, 47.00, 46.00, 45.00, 44.00, 43.00, 42.00, 41.00, 40.00, 38.00, 37.00,
				36.00, 35.00, 34.00, 33.00, 32.00, 31.00, 30.00, 28.00, 27.00, 26.00, 25.00, 24.00, 23.00, 22.00, 21.00,
				20.00, 19.00, 18.00, 17.00, 16.00, 15.00, 14.00, 14.00, 13.00, 12.00, 12.00, 11.00, 11.00, 10.00, 10.00,
				10.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00, 9.00,
				10.00, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00, 10.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00,
				11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 11.00, 12.00, 12.00, 12.00, 12.00,
				12.00, 12.00, 12.00, 12.00, 12.00, 12.00, 13.00, 13.00, 13.00, 13.00, 13.00, 13.00, 13.00, 14.00, 14.00,
				14.00, 14.00, 14.00, 14.00, 14.00, 15.00, 15.00, 15.00, 15.00, 15.00, 15.00, 15.00, 16.00, 16.00, 16.00,
				16.00, 16.00, 16.00, 16.00, 16.00, 16.00, 16.00, 17.00, 17.00, 17.00, 17.00, 17.00, 17.00, 17.00, 17.00,
				17.00, 17.00, 17.00, 17.00, 17.00, 17.00, 17.00, 17.00, 17.00, 16.00, 16.00, 16.00, 16.00, 15.00, 15.00,
				14.00, 14.00, 13.70, 13.40, 13.10, 12.90, 12.70, 12.60, 12.50, 12.50, 12.50, 12.50, 12.50, 12.50, 12.50,
				12.50, 12.50, 12.50, 12.50, 12.40, 12.30, 12.20, 12.00, 11.70, 11.40, 11.10, 10.60, 10.20, 9.60, 9.10,
				8.60, 8.00, 7.50, 7.00, 6.60, 6.30, 6.00, 5.80, 5.70, 5.60, 5.60, 5.60, 5.70, 5.80, 5.90, 6.10, 6.20,
				6.30, 6.50, 6.60, 6.80, 6.90, 7.10, 7.20, 7.30, 7.40, 7.50, 7.60, 7.70, 7.70, 7.80, 7.80, 7.88, 7.82,
				7.54, 6.97, 6.40, 6.02, 5.41, 4.10, 2.92, 1.82, 1.61, 0.10, -1.02, -1.28, -2.69, -3.24, -3.64, -4.54,
				-4.71, -5.11, -5.40, -5.42, -5.20, -5.46, -5.46, -5.79, -5.63, -5.64, -5.80, -5.66, -5.87, -6.01, -6.19,
				-6.64, -6.44, -6.47, -6.09, -5.76, -4.66, -3.74, -2.72, -1.54, -0.2, 1.24, 2.64, 3.86, 5.37, 6.14, 7.75,
				9.13, 10.46, 11.53, 13.36, 14.65, 16.01, 17.20, 18.24, 19.06, 20.25, 20.95, 21.16, 22.25, 22.41, 23.03,
				23.49, 23.62, 23.86, 24.49, 24.34, 24.08, 24.02, 24.00, 23.87, 23.95, 23.86, 23.93, 23.73, 23.92, 23.96,
				24.02, 24.33, 24.83, 25.30, 25.70, 26.24, 26.77, 27.28, 27.78, 28.25, 28.71, 29.15, 29.57, 29.97, 30.36,
				30.72, 31.07, 31.35, 31.68, 32.18, 32.68, 33.15, 33.59, 34.00, 34.47, 35.03, 35.73, 36.54, 37.43, 38.29,
				39.20, 40.18, 41.17, 42.23, 43.37, 44.49, 45.48, 46.46, 47.52, 48.53, 49.59, 50.54, 51.38, 52.17, 52.96,
				53.79, 54.34, 54.87, 55.32, 55.82, 56.30, 56.86, 57.57, 58.31, 59.12, 59.98, 60.78, 61.63, 62.29, 62.97,
				63.47, 63.83, 64.09, 64.30, 64.50, 65.00 };
		if (iflag == 0) {
			iflag = 1;
			for (int i = 0; i < tabsize; i++) {
				j = tabstart + i;
				years[i] = juldat2(j, 1, 1, 0.0);
			}
		}
		HelperDate caldat2 = caldat2(tjd);
		iy = caldat2.getYear();
		im = caldat2.getMonth();
		id = caldat2.getDay();
		hr = caldat2.getHoursPastMidnight();

		// if we are outside the table on the low end
		// use the stephenson and morrison expression 948 to 1600,
		// and the borkowski formula for earlier years
		if (iy < tabstart) {
			if (iy > 948) {
				b = 0.01 * (float) (iy - 2000);
				secdif = b * (b * 23.58 + 100.3) + 101.6;
			} else {
				b = 0.01 * (float) (iy - 2000) + 3.75;
				secdif = 35.0 * b * b + 40.0;
			}
		} else if (iy > tabend) {
			// if we are outside the table on the high end
			// use a linear extrapolation into the future
			b = (float) (iy - tabend);
			secdif = dt[tabsize] + b * (dt[tabsize] - dt[tabsize - 1]);
		} else {
			iat = iy - tabstart + 1;
			iat = Math.max(1, Math.min(iat - mp / 2 + 1, tabsize - mp + 1));
			InterpolationValue polint = polint(Arrays.copyOfRange(years, iat - 1, tabsize),
					Arrays.copyOfRange(dt, iat - 1, tabsize), mp, tjd);
			secdif = polint.getY();
			dy = polint.getDy();
			// c..the astronomical almanac table is corrected by adding the
			// expression
			// c.. -0.000091 (ndot + 26)(year-1955)^2 seconds
			// c..to entries prior to 1955 (page K8), where ndot is the secular
			// tidal
			// c..term in the mean motion of the moon. entries after 1955 are
			// referred
			// c..to atomic time standards and are not affected by errors in
			// lunar
			// c..or planetary theory. a value of ndot = -25.8 arcsec per
			// century squared
			// c..is the value used in jpl's de403 ephemeris, the earlier de200
			// ephemeris
			// c..used the value -23.8946. note for years below the table (less
			// than 1620)
			// c..the time difference is not adjusted for small improvements in
			// the
			// c..current estimate of ndot because the formulas were derived
			// from
			// c..studies of ancient eclipses and other historical information,
			// whose
			// c..interpretation depends only partly on ndot.
			// c..here we make the ndot correction.
			if (iy < 1955) {
				b = (float) (iy - 1955);
				secdif = secdif - 0.000091 * (-25.8 + 26.0) * b * b;
			}
		}

		// c..add the difference to the ut julian date to get the dynamical
		// julian date
		// c tjde = tjd + secdif/86400.0d0

		return secdif;
	}

	private HelperDate caldat2(double tjd) {
		int igreg = 2299161;
		double x1, z, f, x2, xa, xb, xc, xd, xe, rd;
		double c1 = 1.0 / 36524.25;
		double c2 = 1.0 / 365.25;
		double c3 = 1.0 / 30.6001;

		double rh;
		int id;
		int iy;
		int im;

		x1 = tjd + 0.5;
		z = (int) (x1);
		f = x1 - z;
		if (x1 >= igreg) {
			x2 = (int) ((x1 - 1867216.25) * c1);
			xa = z + 1.0 + x2 - (int) (0.25 * x2);
		} else {
			xa = z;
		}
		xb = xa + 1524.0;
		xc = (int) ((xb - 122.1) * c2);
		xd = (int) (365.25 * xc);
		xe = (int) ((xb - xd) * c3);

		rd = xb - xd - (int) (30.6001 * xe) + f;
		id = (int) rd;
		rh = 24.0 * (rd - id);
		im = (int) (xe - 1);
		if (im > 12) {
			im = im - 12;
		}
		iy = (int) (xc - 4715);
		if (im > 2) {
			iy = iy - 1;
		}

		return new HelperDate(iy, im, id, rh);
	}

	/**
	 * c..given arrays xa and ya of length n and a value x, this routine returns
	 * a c..value y and an error estimate dy. if p(x) is the polynomial of
	 * degree n-1 c..such that ya = p(xa) ya then the returned value is y = p(x)
	 * 
	 * c..input: c..xa(1:n) = array of x values c..ya(1:n) = array of y values
	 * c..n = order of interpolant, 2=linear, 3=quadratic ... c..x = x value
	 * where interpolation is desired
	 * 
	 * c..output: c..y = interpolated y value c..dy = error esimate
	 */
	private InterpolationValue polint(double[] xa, double[] ya, int n, double x) {
		int nmax = 10, ns, i, m;
		double y, dy = 0, dif, dift, ho, hp, w, den;
		double c[] = new double[nmax];
		double d[] = new double[nmax];

		// find the index ns of the closest table entry; initialize the c and d
		// tables
		ns = 0;
		dif = Math.abs(x - xa[0]);
		for (i = 0; i < n; i++) {
			dift = Math.abs(x - xa[i]);
			if (dift < dif) {
				ns = i;
				dif = dift;
			}
			c[i] = ya[i];
			d[i] = ya[i];
		}

		// first guess for y
		y = ya[ns];

		// for each column of the table, loop over the c's and d's and update
		// them
		ns = ns - 1;
		for (m = 1; m <= (n - 1); m++) {
			for (i = 0; i < n - m; i++) {
				ho = xa[i] - x;
				hp = xa[i + m] - x;
				w = c[i + 1] - d[i];
				den = ho - hp;
				if (den == 0.0) {
					System.out.println("2 xa entries are the same in polint");
					return null;
				}
				den = w / den;
				d[i] = hp * den;
				c[i] = ho * den;
			}
			// after each column is completed, decide which correction c or d,
			// to add
			// to the accumulating value of y, that is, which path to take in
			// the table
			// by forking up or down. ns is updated as we go to keep track of
			// where we
			// are. the last dy added is the error indicator.
			if (2 * ns < (n - m)) {
				dy = c[ns + 1];
			} else {
				dy = d[ns];
				ns = ns - 1;
			}
			y = y + dy;
		}
		return new InterpolationValue(y, dy);
	}

	public static void main(String args[]) throws IOException {
		DTU dtu = new DTU();
		Calendar cal = Calendar.getInstance();
		dtu.dataLocation = new URL("file:///C:/workspaceOpenStreetmap4/net.sf.seesea.tidemodel.dtu10.java/res/tidalConsituents.txt");
		dtu.loadTidalConsituents();
		// Date parse =
		// DateFormat.getDateInstance().parse("Wed Sep 17 13:28:03 CEST 2014");
		cal.set(Calendar.YEAR, 1993);
		cal.set(Calendar.MONTH, 2 - 1);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		cal.set(Calendar.HOUR_OF_DAY, 19);
		cal.set(Calendar.MINUTE, 17);
		cal.set(Calendar.SECOND, 02);
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(dtu.getTideHeight(-21.00, 5.0, cal.getTime()));

	}

}
