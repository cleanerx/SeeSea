
package net.sf.seesea.geometry.impl.math;

/**
 * Routines for Arbitrary Precision Floating-point Arithmetic and Fast Robust
 * Geometric Predicates.
 * 
 * This is a java port of the functions found in <a
 * href="https://www.cs.cmu.edu/afs/cs/project/quake/public/code/predicates.c"
 * >Geometric Predicates</a>. They are public domain.
 * 
 * This file contains Java implementation of algorithms for exact addition and
 * multiplication of floating-point numbers, and predicates for robustly
 * performing the orientation and incircle tests used in computational
 * geometry. The algorithms and underlying theory are described in Jonathan
 * Richard Shewchuk. "Adaptive Precision Floating-  Point Arithmetic and Fast
 * Robust Geometric Predicates." Technical  Report CMU-CS-96-140, School of
 * Computer Science, Carnegie Mellon  University, Pittsburgh, Pennsylvania, May
 * 1996. (Submitted to * Discrete & Computational Geometry.) This file, the
 * paper listed above, and other information are available from the Web page
 * http://www.cs.cmu.edu/~quake/robust.html . *
 * 
 * This class holds functionality to get error ranges from double precision.
 * @author Richard Shewchuk (C Implementation)
 * @author Jens Kübler (Java port)
 */
public class DoublePrecision {

	private static double SPLITTER = 1.0D;

	public static double EPSILON;

	static {
		double check = 1.0d;
		double half = 0.5d;
		EPSILON = 1.0d;
		double lastcheck;
		boolean every_other = true;
		do {
			lastcheck = check;
			EPSILON *= half;
			if (every_other) {
				SPLITTER *= 2.0d;
			}
			every_other = !every_other;
			check = 1.0d + EPSILON;
		} while ((check != 1.0d) && (check != lastcheck));
		SPLITTER += 1.0d;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static double fastTwoSumTail(double a, double b, double x) {
		double bvirtual = x - a;
		double y = b - bvirtual;
		return y;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] fastTwoSum(double a, double b) {
		double x = (a + b);
		double y = fastTwoSumTail(a, b, x);
		return new double[] { x, y };
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static double twoSumTail(double a, double b, double x) {
		double bvirt = (x - a);
		double avirt = x - bvirt;
		double bround = b - bvirt;
		double around = a - avirt;
		double y = around + bround;
		return y;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] twoSum(double a, double b) {
		double x = (a + b);
		double y = twoSumTail(a, b, x);
		return new double[] { x, y };
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static double twoDiffTail(double a, double b, double x) {
		double bvirt = (a - x);
		double avirt = x + bvirt;
		double bround = bvirt - b;
		double around = a - avirt;
		double y = around + bround;
		return y;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] twoDiff(double a, double b) {
		double x = (a - b);
		double y = twoDiffTail(a, b, x);
		return new double[] { x, y };
	}

	/**
	 * 
	 * @param a
	 * @return an array of two doubles where the first element is the
	 *         representable value and the second is the error range
	 */
	public static double[] split(double a) {
		double c = (SPLITTER * a);
		double abig = (c - a);
		double ahi = c - abig;
		double alo = a - ahi;
		return new double[] { ahi, alo };
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static double twoProductTail(double a, double b, double x) {
		double[] asplit = split(a);
		double ahi = asplit[0];
		double alo = asplit[1];
		double[] bsplit = split(b);
		double bhi = bsplit[0];
		double blo = bsplit[1];
		double err1 = x - (ahi * bhi);
		double err2 = err1 - (alo * bhi);
		double err3 = err2 - (ahi * blo);
		double y = (alo * blo) - err3;
		return y;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] Two_Product(double a, double b) {
		double x = (a * b);
		double y = twoProductTail(a, b, x);
		return new double[] { x, y };
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param bhi
	 * @param blo
	 * @return
	 */
	public static double[] twoProductPresplit(double a, double b, double bhi, double blo) {
		double x = (a * b);
		double[] asplit = split(a);
		double ahi = asplit[0];
		double alo = asplit[1];
		double err1 = x - (ahi * bhi);
		double err2 = err1 - (alo * bhi);
		double err3 = err2 - (ahi * blo);
		double y = (alo * blo) - err3;
		return new double[] { x, y };
	}

	/**
	 * 
	 * @param a
	 * @param x
	 * @return
	 */
	public static double squareTail(double a, double x) {
		double[] asplit = split(a);
		double ahi = asplit[0];
		double alo = asplit[1];
		double err1 = x - (ahi * ahi);
		double err3 = err1 - ((ahi + ahi) * alo);
		double y = (alo * alo) - err3;
		return y;
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	public static double[] square(double a) {
		double x = (a * a);
		double y = squareTail(a, x);
		return new double[] { x, y };
	}

	/**
	 * 
	 * @param a1
	 * @param a0
	 * @param b
	 * @return
	 */
	public static double[] twoOneSum(double a1, double a0, double b) {
		double[] twoSim = twoSum(a0, b);
		double _i = twoSim[0];
		double x0 = twoSim[1];
		twoSim = twoSum(a1, _i);
		double x1 = twoSim[0];
		double x2 = twoSim[1];
		return new double[] { x0, x1, x2 };
	}

	/**
	 * 
	 * @param a1
	 * @param a0
	 * @param b
	 * @return
	 */
	public static double[] twoOneDiff(double a1, double a0, double b) {
		double twoDiff[] = twoDiff(a0, b);
		double _i = twoDiff[0];
		double x0 = twoDiff[1];

		double[] two_Sum = twoSum(a1, _i);
		double x2 = two_Sum[0];
		double x1 = two_Sum[1];
		return new double[] { x2, x1, x0 };
	}

	/**
	 * 
	 * @param a1
	 * @param a0
	 * @param b1
	 * @param b0
	 * @return
	 */
	public static double[] twoTwoSum(double a1, double a0, double b1, double b0) {
		double[] Two_One_Sum = twoOneSum(a1, a0, b0);
		double _j = Two_One_Sum[0];
		double _0 = Two_One_Sum[1];
		double x0 = Two_One_Sum[2];

		Two_One_Sum = twoOneSum(_j, _0, b1);
		double x3 = Two_One_Sum[0];
		double x2 = Two_One_Sum[1];
		double x1 = Two_One_Sum[2];
		return new double[] { x3, x2, x1, x0 };
	}

	/**
	 * 
	 * @param a1
	 * @param a0
	 * @param b1
	 * @param b0
	 * @return
	 */
	public static double[] twoTwoDiff(double a1, double a0, double b1, double b0) {
		double[] two_One_Diff = twoOneDiff(a1, a0, b0);
		double _j = two_One_Diff[0];
		double _0 = two_One_Diff[1];
		double x0 = two_One_Diff[2];
		two_One_Diff = twoOneDiff(_j, _0, b1);
		double x3 = two_One_Diff[0];
		double x2 = two_One_Diff[1];
		double x1 = two_One_Diff[2];
		return new double[] { x3, x2, x1, x0 };
	}

	/**
	 * 
	 * @param a1
	 * @param a0
	 * @param b
	 * @return
	 */
	public static double[] twoOneProduct(double a1, double a0, double b) {
		double[] bsplit = split(b);
		double bhi = bsplit[0];
		double blo = bsplit[1];
		double presplit[] = twoProductPresplit(a0, b, bhi, blo);
		double _i = presplit[0];
		double x0 = presplit[1];
		presplit = twoProductPresplit(a1, b, bhi, blo);
		double _j = presplit[0];
		double _0 = presplit[1];
		double[] two_Sum = twoSum(_i, _0);
		double _k = two_Sum[0];
		double x1 = two_Sum[1];
		double fast_Two_Sum[] = fastTwoSum(_j, _k);
		double x3 = fast_Two_Sum[0];
		double x2 = fast_Two_Sum[1];
		return new double[] { x3, x2, x1, x0 };
	}

	public static int scaleExpansionZeroelim(int elen, double[] e, double b, double[] h) {
		int eindex, hindex;
		double enow;

		double split[] = DoublePrecision.split(b);
		double bhi = split[0];
		double blo = split[1];
		double product_Presplit[] = DoublePrecision.twoProductPresplit(e[0], b, bhi, blo);
		double Q = product_Presplit[0];
		double hh = product_Presplit[1];

		hindex = 0;
		if (hh != 0) {
			h[hindex++] = hh;
		}
		for (eindex = 1; eindex < elen; eindex++) {
			enow = e[eindex];
			product_Presplit = DoublePrecision.twoProductPresplit(enow, b, bhi, blo);
			double product1 = product_Presplit[0];
			double product0 = product_Presplit[1];
			double twoSum[] = DoublePrecision.twoSum(Q, product0);
			double sum = twoSum[0];
			hh = twoSum[1];

			if (hh != 0) {
				h[hindex++] = hh;
			}
			double[] fast_Two_Sum = DoublePrecision.fastTwoSum(product1, sum);
			Q = fast_Two_Sum[0];
			hh = fast_Two_Sum[1];
			if (hh != 0) {
				h[hindex++] = hh;
			}
		}
		if ((Q != 0.0) || (hindex == 0)) {
			h[hindex++] = Q;
		}
		return hindex;
	}

	public static double estimate(int elen, double[] e) {
		double Q;
		int eindex;

		Q = e[0];
		for (eindex = 1; eindex < elen; eindex++) {
			Q += e[eindex];
		}
		return Q;
	}

	/*****************************************************************************/
	/*                                                                           */
	/* fast_expansion_sum_zeroelim() Sum two expansions, eliminating zero */
	/* components from the output expansion. */
	/*                                                                           */
	/* Sets h = e + f. See my Robust Predicates paper for details. */
	/*                                                                           */
	/* If round-to-even is used (as with IEEE 754), maintains the strongly */
	/* nonoverlapping property. (That is, if e is strongly nonoverlapping, h */
	/* will be also.) Does NOT maintain the nonoverlapping or nonadjacent */
	/* properties. */
	/*                                                                           */
	/*****************************************************************************/

	public static int fastExpansionSumZeroelim(int elen, double e[], int flen, double f[], double[] h) {
		double Q;
		double Qnew;
		double hh;
		int eindex, findex, hindex;
		double enow, fnow;

		enow = e[0];
		fnow = f[0];
		eindex = findex = 0;
		if ((fnow > enow) == (fnow > -enow)) {
			Q = enow;
			enow = e[++eindex];
		} else {
			Q = fnow;
			fnow = f[++findex];
		}
		hindex = 0;
		if ((eindex < elen) && (findex < flen)) {
			if ((fnow > enow) == (fnow > -enow)) {
				double[] fast_Two_Sum = DoublePrecision.fastTwoSum(enow, Q);
				Qnew = fast_Two_Sum[0];
				hh = fast_Two_Sum[1];
				enow = e[++eindex];
			} else {
				double[] fast_Two_Sum = DoublePrecision.fastTwoSum(fnow, Q);
				Qnew = fast_Two_Sum[0];
				hh = fast_Two_Sum[1];
				fnow = f[++findex];
			}
			Q = Qnew;
			if (hh != 0.0) {
				h[hindex++] = hh;
			}
			while ((eindex < elen) && (findex < flen)) {
				if ((fnow > enow) == (fnow > -enow)) {
					double[] two_Sum = DoublePrecision.twoSum(Q, enow);
					Qnew = two_Sum[0];
					hh = two_Sum[1];
					enow = e[++eindex];
				} else {
					double[] two_Sum = DoublePrecision.twoSum(Q, fnow);
					Qnew = two_Sum[0];
					hh = two_Sum[1];
					fnow = f[++findex];
				}
				Q = Qnew;
				if (hh != 0.0) {
					h[hindex++] = hh;
				}
			}
		}
		while (eindex < elen) {
			double[] two_Sum = DoublePrecision.twoSum(Q, enow);
			Qnew = two_Sum[0];
			hh = two_Sum[1];
			enow = e[++eindex];
			Q = Qnew;
			if (hh != 0.0) {
				h[hindex++] = hh;
			}
		}
		while (findex < flen) {
			double[] two_Sum = DoublePrecision.twoSum(Q, fnow);
			Qnew = two_Sum[0];
			hh = two_Sum[1];
			fnow = f[++findex];
			Q = Qnew;
			if (hh != 0.0) {
				h[hindex++] = hh;
			}
		}
		if ((Q != 0.0) || (hindex == 0)) {
			h[hindex++] = Q;
		}
		return hindex;
	}
	
	

}
