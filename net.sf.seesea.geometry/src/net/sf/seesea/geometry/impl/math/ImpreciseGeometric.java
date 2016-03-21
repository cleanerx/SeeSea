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
package net.sf.seesea.geometry.impl.math;

import net.sf.seesea.geometry.IGeometric;
import net.sf.seesea.geometry.IPoint;

public class ImpreciseGeometric implements IGeometric {
	
	private static double resulterrbound;
	private static double ccwerrboundA;
	private static double ccwerrboundB;
	private static double ccwerrboundC;
	private static double iccerrboundA;
	private static double iccerrboundB;
	private static double iccerrboundC;
	private static double o3derrboundA;
	private static double o3derrboundB;
	private static double o3derrboundC;
	
	static {
		  resulterrbound = (3.0d + 8.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON;
		  ccwerrboundA = (3.0d + 16.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON;
		  ccwerrboundB = (2.0d + 12.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON;
		  ccwerrboundC = (9.0d + 64.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON * DoublePrecision.EPSILON;
		  iccerrboundA = (10.0d + 96.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON;
		  iccerrboundB = (4.0d + 48.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON;
		  iccerrboundC = (44.0d + 576.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON * DoublePrecision.EPSILON;
		  o3derrboundA = (7.0d + 56.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON;
		  o3derrboundB = (3.0d + 28.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON;
		  o3derrboundC = (26.0d + 288.0d * DoublePrecision.EPSILON) * DoublePrecision.EPSILON * DoublePrecision.EPSILON;
	}
	
	public double incircle(IPoint pa, IPoint pb, IPoint pc, IPoint pd) {
		double adx, bdx, cdx, ady, bdy, cdy;
		double bdxcdy, cdxbdy, cdxady, adxcdy, adxbdy, bdxady;
		double alift, blift, clift;
		double det;
		double permanent, errbound;

		// m->incirclecount++;

		adx = pa.getX() - pd.getX();
		bdx = pb.getX() - pd.getX();
		cdx = pc.getX() - pd.getX();
		ady = pa.getY() - pd.getY();
		bdy = pb.getY() - pd.getY();
		cdy = pc.getY() - pd.getY();

		bdxcdy = bdx * cdy;
		cdxbdy = cdx * bdy;
		alift = adx * adx + ady * ady;

		cdxady = cdx * ady;
		adxcdy = adx * cdy;
		blift = bdx * bdx + bdy * bdy;

		adxbdy = adx * bdy;
		bdxady = bdx * ady;
		clift = cdx * cdx + cdy * cdy;

		det = alift * (bdxcdy - cdxbdy) + blift * (cdxady - adxcdy) + clift * (adxbdy - bdxady);

		// if (b->noexact) {
		// return det;
		// }

		permanent = (Math.abs(bdxcdy) + Math.abs(cdxbdy)) * alift + (Math.abs(cdxady) + Math.abs(adxcdy)) * blift + (Math.abs(adxbdy) + Math.abs(bdxady)) * clift;
		errbound = iccerrboundA * permanent;
		if ((det > errbound) || (-det > errbound)) {
			return det;
		}

		return incircleadapt(pa, pb, pc, pd, permanent);
	}
	
	public double incircleadapt(IPoint pa, IPoint pb, IPoint pc, IPoint pd, double permanent) {
		double adx, bdx, cdx, ady, bdy, cdy;
		double det, errbound;

		double bdxcdy1, cdxbdy1, cdxady1, adxcdy1, adxbdy1, bdxady1;
		double bdxcdy0, cdxbdy0, cdxady0, adxcdy0, adxbdy0, bdxady0;
		double bc[] = new double[4], ca[] = new double[4], ab[] = new double[4];
		double bc3, ca3, ab3;
		double axbc[] = new double[8], axxbc[] = new double[16], aybc[] = new double[8], ayybc[] = new double[16], adet[] = new double[32];
		int axbclen, axxbclen, aybclen, ayybclen, alen;
		double bxca[] = new double[8], bxxca[] = new double[16], byca[] = new double[8], byyca[] = new double[16], bdet[] = new double[32];
		int bxcalen, bxxcalen, bycalen, byycalen, blen;
		double cxab[] = new double[8], cxxab[] = new double[16], cyab[] = new double[8], cyyab[] = new double[16], cdet[] = new double[32];
		int cxablen, cxxablen, cyablen, cyyablen, clen;
		double abdet[] = new double[64];
		int ablen;
		double fin1[] = new double[1152], fin2[] = new double[1152];
		double finnow[], finother[], finswap[];
		int finlength;

		double adxtail, bdxtail, cdxtail, adytail, bdytail, cdytail;
		double adxadx1, adyady1, bdxbdx1, bdybdy1, cdxcdx1, cdycdy1;
		double adxadx0, adyady0, bdxbdx0, bdybdy0, cdxcdx0, cdycdy0;
		double aa[] = new double[4], bb[] = new double[4], cc[] = new double[4];
		double aa3, bb3, cc3;
		double ti1, tj1;
		double ti0, tj0;
		double u[] = new double[4], v[] = new double[4];
		double u3, v3;
		double temp8[] = new double[8], temp16a[] = new double[16], temp16b[] = new double[16], temp16c[] = new double[16];
		double temp32a[] = new double[32], temp32b[] = new double[32], temp48[] = new double[48], temp64[] = new double[64];
		int temp8len, temp16alen, temp16blen, temp16clen;
		int temp32alen, temp32blen, temp48len, temp64len;
		double axtbb[] = new double[8], axtcc[] = new double[8], aytbb[] = new double[8], aytcc[] = new double[8];
		int axtbblen, axtcclen, aytbblen, aytcclen;
		double bxtaa[] = new double[8], bxtcc[] = new double[8], bytaa[] = new double[8], bytcc[] = new double[8];
		int bxtaalen, bxtcclen, bytaalen, bytcclen;
		double cxtaa[] = new double[8], cxtbb[] = new double[8], cytaa[] = new double[8], cytbb[] = new double[8];
		int cxtaalen, cxtbblen, cytaalen, cytbblen;
		double axtbc[] = new double[8], aytbc[] = new double[8], bxtca[] = new double[8], bytca[] = new double[8], cxtab[] = new double[8], cytab[] = new double[8];
		int axtbclen = 0, aytbclen = 0, bxtcalen = 0, bytcalen = 0, cxtablen = 0, cytablen = 0;
		double axtbct[] = new double[16], aytbct[] = new double[16], bxtcat[] = new double[16], bytcat[] = new double[16], cxtabt[] = new double[16], cytabt[] = new double[16];
		int axtbctlen, aytbctlen, bxtcatlen, bytcatlen, cxtabtlen, cytabtlen;
		double axtbctt[] = new double[8], aytbctt[] = new double[8], bxtcatt[] = new double[8];
		double bytcatt[] = new double[8], cxtabtt[] = new double[8], cytabtt[] = new double[8];
		int axtbcttlen, aytbcttlen, bxtcattlen, bytcattlen, cxtabttlen, cytabttlen;
		double abt[] = new double[8], bct[] = new double[8], cat[] = new double[8];
		int abtlen, bctlen, catlen;
		double abtt[] = new double[4], bctt[] = new double[4], catt[] = new double[4];
		int abttlen, bcttlen, cattlen;
		double abtt3, bctt3, catt3;
		double negate;

		adx = (double) (pa.getX() - pd.getX());
		bdx = (double) (pb.getX() - pd.getX());
		cdx = (double) (pc.getX() - pd.getX());
		ady = (double) (pa.getY() - pd.getY());
		bdy = (double) (pb.getY() - pd.getY());
		cdy = (double) (pc.getY() - pd.getY());

		double[] twoProduct = DoublePrecision.Two_Product(bdx, cdy);
		bdxcdy1 = twoProduct[0];
		bdxcdy0 = twoProduct[1];
		twoProduct = DoublePrecision.Two_Product(cdx, bdy);
		cdxbdy1 = twoProduct[0];
		cdxbdy0 = twoProduct[1];
		double[] two_Two_Diff = DoublePrecision.twoTwoDiff(bdxcdy1, bdxcdy0, cdxbdy1, cdxbdy0);
		bc3 = two_Two_Diff[0];
		bc[2] = two_Two_Diff[1];
		bc[1] = two_Two_Diff[2];
		bc[0] = two_Two_Diff[3];
		bc[3] = bc3;
		axbclen = DoublePrecision.scaleExpansionZeroelim(4, bc, adx, axbc);
		axxbclen = DoublePrecision.scaleExpansionZeroelim(axbclen, axbc, adx, axxbc);
		aybclen = DoublePrecision.scaleExpansionZeroelim(4, bc, ady, aybc);
		ayybclen = DoublePrecision.scaleExpansionZeroelim(aybclen, aybc, ady, ayybc);
		alen = DoublePrecision.fastExpansionSumZeroelim(axxbclen, axxbc, ayybclen, ayybc, adet);

		twoProduct = DoublePrecision.Two_Product(cdx, ady);
		cdxady1 = twoProduct[0];
		cdxady0 = twoProduct[1];
		twoProduct = DoublePrecision.Two_Product(adx, cdy);
		adxcdy1 = twoProduct[0];
		adxcdy0 = twoProduct[1];
		two_Two_Diff = DoublePrecision.twoTwoDiff(cdxady1, cdxady0, adxcdy1, adxcdy0);
		ca3 = two_Two_Diff[0];
		ca[2] = two_Two_Diff[1];
		ca[1] = two_Two_Diff[2];
		ca[0] = two_Two_Diff[3];
		ca[3] = ca3;
		bxcalen = DoublePrecision.scaleExpansionZeroelim(4, ca, bdx, bxca);
		bxxcalen = DoublePrecision.scaleExpansionZeroelim(bxcalen, bxca, bdx, bxxca);
		bycalen = DoublePrecision.scaleExpansionZeroelim(4, ca, bdy, byca);
		byycalen = DoublePrecision.scaleExpansionZeroelim(bycalen, byca, bdy, byyca);
		blen = DoublePrecision.fastExpansionSumZeroelim(bxxcalen, bxxca, byycalen, byyca, bdet);

		twoProduct = DoublePrecision.Two_Product(adx, bdy);
		adxbdy1 = twoProduct[0];
		adxbdy0 = twoProduct[1];

		twoProduct = DoublePrecision.Two_Product(bdx, ady);
		bdxady1 = twoProduct[0];
		bdxady0 = twoProduct[1];

		two_Two_Diff = DoublePrecision.twoTwoDiff(adxbdy1, adxbdy0, bdxady1, bdxady0);
		ab3 = two_Two_Diff[0];
		ab[2] = two_Two_Diff[1];
		ab[1] = two_Two_Diff[2];
		ab[0] = two_Two_Diff[3];
		ab[3] = ab3;
		cxablen = DoublePrecision.scaleExpansionZeroelim(4, ab, cdx, cxab);
		cxxablen = DoublePrecision.scaleExpansionZeroelim(cxablen, cxab, cdx, cxxab);
		cyablen = DoublePrecision.scaleExpansionZeroelim(4, ab, cdy, cyab);
		cyyablen = DoublePrecision.scaleExpansionZeroelim(cyablen, cyab, cdy, cyyab);
		clen = DoublePrecision.fastExpansionSumZeroelim(cxxablen, cxxab, cyyablen, cyyab, cdet);

		ablen = DoublePrecision.fastExpansionSumZeroelim(alen, adet, blen, bdet, abdet);
		finlength = DoublePrecision.fastExpansionSumZeroelim(ablen, abdet, clen, cdet, fin1);

		det = DoublePrecision.estimate(finlength, fin1);
		errbound = iccerrboundB * permanent;
		if ((det >= errbound) || (-det >= errbound)) {
			return det;
		}

		adxtail = DoublePrecision.twoDiffTail(pa.getX(), pd.getX(), adx);
		adytail = DoublePrecision.twoDiffTail(pa.getY(), pd.getY(), ady);
		bdxtail = DoublePrecision.twoDiffTail(pb.getX(), pd.getX(), bdx);
		bdytail = DoublePrecision.twoDiffTail(pb.getY(), pd.getY(), bdy);
		cdxtail = DoublePrecision.twoDiffTail(pc.getX(), pd.getX(), cdx);
		cdytail = DoublePrecision.twoDiffTail(pc.getY(), pd.getY(), cdy);
		if ((adxtail == 0.0) && (bdxtail == 0.0) && (cdxtail == 0.0) && (adytail == 0.0) && (bdytail == 0.0) && (cdytail == 0.0)) {
			return det;
		}

		errbound = iccerrboundC * permanent + resulterrbound * Math.abs(det);
		det += ((adx * adx + ady * ady) * ((bdx * cdytail + cdy * bdxtail) - (bdy * cdxtail + cdx * bdytail)) + 2.0 * (adx * adxtail + ady * adytail) * (bdx * cdy - bdy * cdx))
				+ ((bdx * bdx + bdy * bdy) * ((cdx * adytail + ady * cdxtail) - (cdy * adxtail + adx * cdytail)) + 2.0 * (bdx * bdxtail + bdy * bdytail) * (cdx * ady - cdy * adx))
				+ ((cdx * cdx + cdy * cdy) * ((adx * bdytail + bdy * adxtail) - (ady * bdxtail + bdx * adytail)) + 2.0 * (cdx * cdxtail + cdy * cdytail) * (adx * bdy - ady * bdx));
		if ((det >= errbound) || (-det >= errbound)) {
			return det;
		}

		finnow = fin1;
		finother = fin2;

		if ((bdxtail != 0.0) || (bdytail != 0.0) || (cdxtail != 0.0) || (cdytail != 0.0)) {
			double square[] = DoublePrecision.square(adx);
			adxadx1 = square[0];
			adxadx0 = square[1];
			square = DoublePrecision.square(ady);
			adyady1 = square[0];
			adyady0 = square[1];
			double[] two_Two_Sum = DoublePrecision.twoTwoSum(adxadx1, adxadx0, adyady1, adyady0);
			aa3 = two_Two_Sum[0];
			aa[2] = two_Two_Sum[1];
			aa[1] = two_Two_Sum[2];
			aa[0] = two_Two_Sum[3];
			aa[3] = aa3;
		}
		if ((cdxtail != 0.0) || (cdytail != 0.0) || (adxtail != 0.0) || (adytail != 0.0)) {
			double[] square = DoublePrecision.square(bdx);
			bdxbdx1 = square[0];
			bdxbdx0 = square[1];
			square = DoublePrecision.square(bdy);
			bdybdy1 = square[0];
			bdybdy0 = square[1];
			double[] two_Two_Sum = DoublePrecision.twoTwoSum(bdxbdx1, bdxbdx0, bdybdy1, bdybdy0);
			bb3 = two_Two_Sum[0];
			bb[2] = two_Two_Sum[1];
			bb[1] = two_Two_Sum[2];
			bb[0] = two_Two_Sum[3];
			bb[3] = bb3;
		}
		if ((adxtail != 0.0) || (adytail != 0.0) || (bdxtail != 0.0) || (bdytail != 0.0)) {
			double[] square = DoublePrecision.square(cdx);
			cdxcdx1 = square[0];
			cdxcdx0 = square[1];
			square = DoublePrecision.square(cdy);
			cdycdy1 = square[0];
			cdycdy0 = square[1];
			double[] two_Two_Sum = DoublePrecision.twoTwoSum(cdxcdx1, cdxcdx0, cdycdy1, cdycdy0);
			cc3 = two_Two_Sum[0];
			cc[2] = two_Two_Sum[1];
			cc[1] = two_Two_Sum[2];
			cc[0] = two_Two_Sum[3];
			cc[3] = cc3;
		}

		if (adxtail != 0.0) {
			axtbclen = DoublePrecision.scaleExpansionZeroelim(4, bc, adxtail, axtbc);
			temp16alen = DoublePrecision.scaleExpansionZeroelim(axtbclen, axtbc, 2.0 * adx, temp16a);

			axtcclen = DoublePrecision.scaleExpansionZeroelim(4, cc, adxtail, axtcc);
			temp16blen = DoublePrecision.scaleExpansionZeroelim(axtcclen, axtcc, bdy, temp16b);

			axtbblen = DoublePrecision.scaleExpansionZeroelim(4, bb, adxtail, axtbb);
			temp16clen = DoublePrecision.scaleExpansionZeroelim(axtbblen, axtbb, -cdy, temp16c);

			temp32alen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32a);
			temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16clen, temp16c, temp32alen, temp32a, temp48);
			finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
			finswap = finnow;
			finnow = finother;
			finother = finswap;
		}
		if (adytail != 0.0) {
			aytbclen = DoublePrecision.scaleExpansionZeroelim(4, bc, adytail, aytbc);
			temp16alen = DoublePrecision.scaleExpansionZeroelim(aytbclen, aytbc, 2.0 * ady, temp16a);

			aytbblen = DoublePrecision.scaleExpansionZeroelim(4, bb, adytail, aytbb);
			temp16blen = DoublePrecision.scaleExpansionZeroelim(aytbblen, aytbb, cdx, temp16b);

			aytcclen = DoublePrecision.scaleExpansionZeroelim(4, cc, adytail, aytcc);
			temp16clen = DoublePrecision.scaleExpansionZeroelim(aytcclen, aytcc, -bdx, temp16c);

			temp32alen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32a);
			temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16clen, temp16c, temp32alen, temp32a, temp48);
			finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
			finswap = finnow;
			finnow = finother;
			finother = finswap;
		}
		if (bdxtail != 0.0) {
			bxtcalen = DoublePrecision.scaleExpansionZeroelim(4, ca, bdxtail, bxtca);
			temp16alen = DoublePrecision.scaleExpansionZeroelim(bxtcalen, bxtca, 2.0 * bdx, temp16a);

			bxtaalen = DoublePrecision.scaleExpansionZeroelim(4, aa, bdxtail, bxtaa);
			temp16blen = DoublePrecision.scaleExpansionZeroelim(bxtaalen, bxtaa, cdy, temp16b);

			bxtcclen = DoublePrecision.scaleExpansionZeroelim(4, cc, bdxtail, bxtcc);
			temp16clen = DoublePrecision.scaleExpansionZeroelim(bxtcclen, bxtcc, -ady, temp16c);

			temp32alen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32a);
			temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16clen, temp16c, temp32alen, temp32a, temp48);
			finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
			finswap = finnow;
			finnow = finother;
			finother = finswap;
		}
		if (bdytail != 0.0) {
			bytcalen = DoublePrecision.scaleExpansionZeroelim(4, ca, bdytail, bytca);
			temp16alen = DoublePrecision.scaleExpansionZeroelim(bytcalen, bytca, 2.0 * bdy, temp16a);

			bytcclen = DoublePrecision.scaleExpansionZeroelim(4, cc, bdytail, bytcc);
			temp16blen = DoublePrecision.scaleExpansionZeroelim(bytcclen, bytcc, adx, temp16b);

			bytaalen = DoublePrecision.scaleExpansionZeroelim(4, aa, bdytail, bytaa);
			temp16clen = DoublePrecision.scaleExpansionZeroelim(bytaalen, bytaa, -cdx, temp16c);

			temp32alen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32a);
			temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16clen, temp16c, temp32alen, temp32a, temp48);
			finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
			finswap = finnow;
			finnow = finother;
			finother = finswap;
		}
		if (cdxtail != 0.0) {
			cxtablen = DoublePrecision.scaleExpansionZeroelim(4, ab, cdxtail, cxtab);
			temp16alen = DoublePrecision.scaleExpansionZeroelim(cxtablen, cxtab, 2.0 * cdx, temp16a);

			cxtbblen = DoublePrecision.scaleExpansionZeroelim(4, bb, cdxtail, cxtbb);
			temp16blen = DoublePrecision.scaleExpansionZeroelim(cxtbblen, cxtbb, ady, temp16b);

			cxtaalen = DoublePrecision.scaleExpansionZeroelim(4, aa, cdxtail, cxtaa);
			temp16clen = DoublePrecision.scaleExpansionZeroelim(cxtaalen, cxtaa, -bdy, temp16c);

			temp32alen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32a);
			temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16clen, temp16c, temp32alen, temp32a, temp48);
			finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
			finswap = finnow;
			finnow = finother;
			finother = finswap;
		}
		if (cdytail != 0.0) {
			cytablen = DoublePrecision.scaleExpansionZeroelim(4, ab, cdytail, cytab);
			temp16alen = DoublePrecision.scaleExpansionZeroelim(cytablen, cytab, 2.0 * cdy, temp16a);

			cytaalen = DoublePrecision.scaleExpansionZeroelim(4, aa, cdytail, cytaa);
			temp16blen = DoublePrecision.scaleExpansionZeroelim(cytaalen, cytaa, bdx, temp16b);

			cytbblen = DoublePrecision.scaleExpansionZeroelim(4, bb, cdytail, cytbb);
			temp16clen = DoublePrecision.scaleExpansionZeroelim(cytbblen, cytbb, -adx, temp16c);

			temp32alen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32a);
			temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16clen, temp16c, temp32alen, temp32a, temp48);
			finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
			finswap = finnow;
			finnow = finother;
			finother = finswap;
		}

		if ((adxtail != 0.0) || (adytail != 0.0)) {
			if ((bdxtail != 0.0) || (bdytail != 0.0) || (cdxtail != 0.0) || (cdytail != 0.0)) {
				twoProduct = DoublePrecision.Two_Product(bdxtail, cdy);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];

				twoProduct = DoublePrecision.Two_Product(bdx, cdytail);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				double[] two_Two_Sum = DoublePrecision.twoTwoSum(ti1, ti0, tj1, tj0);
				u3 = two_Two_Sum[0];
				u[2] = two_Two_Sum[1];
				u[1] = two_Two_Sum[2];
				u[0] = two_Two_Sum[3];
				u[3] = u3;
				negate = -bdy;
				twoProduct = DoublePrecision.Two_Product(cdxtail, negate);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];
				negate = -bdytail;
				twoProduct = DoublePrecision.Two_Product(cdx, negate);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				two_Two_Sum = DoublePrecision.twoTwoSum(ti1, ti0, tj1, tj0);
				v3 = two_Two_Sum[0];
				v[2] = two_Two_Sum[1];
				v[1] = two_Two_Sum[2];
				v[0] = two_Two_Sum[3];
				v[3] = v3;
				bctlen = DoublePrecision.fastExpansionSumZeroelim(4, u, 4, v, bct);

				twoProduct = DoublePrecision.Two_Product(bdxtail, cdytail);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];
				twoProduct = DoublePrecision.Two_Product(cdxtail, bdytail);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				double[] two_Two_Diff2 = DoublePrecision.twoTwoDiff(ti1, ti0, tj1, tj0);
				bctt3 = two_Two_Diff2[0];
				bctt[2] = two_Two_Diff2[1];
				bctt[1] = two_Two_Diff2[2];
				bctt[0] = two_Two_Diff2[3];
				bctt[3] = bctt3;
				bcttlen = 4;
			} else {
				bct[0] = 0.0;
				bctlen = 1;
				bctt[0] = 0.0;
				bcttlen = 1;
			}

			if (adxtail != 0.0) {
				temp16alen = DoublePrecision.scaleExpansionZeroelim(axtbclen, axtbc, adxtail, temp16a);
				axtbctlen = DoublePrecision.scaleExpansionZeroelim(bctlen, bct, adxtail, axtbct);
				temp32alen = DoublePrecision.scaleExpansionZeroelim(axtbctlen, axtbct, 2.0 * adx, temp32a);
				temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp32alen, temp32a, temp48);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
				if (bdytail != 0.0) {
					temp8len = DoublePrecision.scaleExpansionZeroelim(4, cc, adxtail, temp8);
					temp16alen = DoublePrecision.scaleExpansionZeroelim(temp8len, temp8, bdytail, temp16a);
					finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp16alen, temp16a, finother);
					finswap = finnow;
					finnow = finother;
					finother = finswap;
				}
				if (cdytail != 0.0) {
					temp8len = DoublePrecision.scaleExpansionZeroelim(4, bb, -adxtail, temp8);
					temp16alen = DoublePrecision.scaleExpansionZeroelim(temp8len, temp8, cdytail, temp16a);
					finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp16alen, temp16a, finother);
					finswap = finnow;
					finnow = finother;
					finother = finswap;
				}

				temp32alen = DoublePrecision.scaleExpansionZeroelim(axtbctlen, axtbct, adxtail, temp32a);
				axtbcttlen = DoublePrecision.scaleExpansionZeroelim(bcttlen, bctt, adxtail, axtbctt);
				temp16alen = DoublePrecision.scaleExpansionZeroelim(axtbcttlen, axtbctt, 2.0 * adx, temp16a);
				temp16blen = DoublePrecision.scaleExpansionZeroelim(axtbcttlen, axtbctt, adxtail, temp16b);
				temp32blen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32b);
				temp64len = DoublePrecision.fastExpansionSumZeroelim(temp32alen, temp32a, temp32blen, temp32b, temp64);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp64len, temp64, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
			}
			if (adytail != 0.0) {
				temp16alen = DoublePrecision.scaleExpansionZeroelim(aytbclen, aytbc, adytail, temp16a);
				aytbctlen = DoublePrecision.scaleExpansionZeroelim(bctlen, bct, adytail, aytbct);
				temp32alen = DoublePrecision.scaleExpansionZeroelim(aytbctlen, aytbct, 2.0 * ady, temp32a);
				temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp32alen, temp32a, temp48);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;

				temp32alen = DoublePrecision.scaleExpansionZeroelim(aytbctlen, aytbct, adytail, temp32a);
				aytbcttlen = DoublePrecision.scaleExpansionZeroelim(bcttlen, bctt, adytail, aytbctt);
				temp16alen = DoublePrecision.scaleExpansionZeroelim(aytbcttlen, aytbctt, 2.0 * ady, temp16a);
				temp16blen = DoublePrecision.scaleExpansionZeroelim(aytbcttlen, aytbctt, adytail, temp16b);
				temp32blen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32b);
				temp64len = DoublePrecision.fastExpansionSumZeroelim(temp32alen, temp32a, temp32blen, temp32b, temp64);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp64len, temp64, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
			}
		}
		if ((bdxtail != 0.0) || (bdytail != 0.0)) {
			if ((cdxtail != 0.0) || (cdytail != 0.0) || (adxtail != 0.0) || (adytail != 0.0)) {
				twoProduct = DoublePrecision.Two_Product(cdxtail, ady);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];
				twoProduct = DoublePrecision.Two_Product(cdx, adytail);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				double[] two_Two_Sum = DoublePrecision.twoTwoSum(ti1, ti0, tj1, tj0);
				u3 = two_Two_Sum[0];
				u[2] = two_Two_Sum[1];
				u[1] = two_Two_Sum[2];
				u[0] = two_Two_Sum[3];
				u[3] = u3;
				negate = -cdy;
				twoProduct = DoublePrecision.Two_Product(adxtail, negate);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];
				negate = -cdytail;
				twoProduct = DoublePrecision.Two_Product(adx, negate);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				two_Two_Sum = DoublePrecision.twoTwoSum(ti1, ti0, tj1, tj0);
				v3 = two_Two_Sum[0];
				v[2] = two_Two_Sum[1];
				v[1] = two_Two_Sum[2];
				v[0] = two_Two_Sum[3];
				v[3] = v3;
				catlen = DoublePrecision.fastExpansionSumZeroelim(4, u, 4, v, cat);

				twoProduct = DoublePrecision.Two_Product(cdxtail, adytail);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];
				twoProduct = DoublePrecision.Two_Product(adxtail, cdytail);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				double[] two_Two_Diff2 = DoublePrecision.twoTwoDiff(ti1, ti0, tj1, tj0);
				catt3 = two_Two_Diff2[0];
				catt[2] = two_Two_Diff2[1];
				catt[1] = two_Two_Diff2[2];
				catt[0] = two_Two_Diff2[3];
				catt[3] = catt3;
				cattlen = 4;
			} else {
				cat[0] = 0.0;
				catlen = 1;
				catt[0] = 0.0;
				cattlen = 1;
			}

			if (bdxtail != 0.0) {
				temp16alen = DoublePrecision.scaleExpansionZeroelim(bxtcalen, bxtca, bdxtail, temp16a);
				bxtcatlen = DoublePrecision.scaleExpansionZeroelim(catlen, cat, bdxtail, bxtcat);
				temp32alen = DoublePrecision.scaleExpansionZeroelim(bxtcatlen, bxtcat, 2.0 * bdx, temp32a);
				temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp32alen, temp32a, temp48);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
				if (cdytail != 0.0) {
					temp8len = DoublePrecision.scaleExpansionZeroelim(4, aa, bdxtail, temp8);
					temp16alen = DoublePrecision.scaleExpansionZeroelim(temp8len, temp8, cdytail, temp16a);
					finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp16alen, temp16a, finother);
					finswap = finnow;
					finnow = finother;
					finother = finswap;
				}
				if (adytail != 0.0) {
					temp8len = DoublePrecision.scaleExpansionZeroelim(4, cc, -bdxtail, temp8);
					temp16alen = DoublePrecision.scaleExpansionZeroelim(temp8len, temp8, adytail, temp16a);
					finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp16alen, temp16a, finother);
					finswap = finnow;
					finnow = finother;
					finother = finswap;
				}

				temp32alen = DoublePrecision.scaleExpansionZeroelim(bxtcatlen, bxtcat, bdxtail, temp32a);
				bxtcattlen = DoublePrecision.scaleExpansionZeroelim(cattlen, catt, bdxtail, bxtcatt);
				temp16alen = DoublePrecision.scaleExpansionZeroelim(bxtcattlen, bxtcatt, 2.0 * bdx, temp16a);
				temp16blen = DoublePrecision.scaleExpansionZeroelim(bxtcattlen, bxtcatt, bdxtail, temp16b);
				temp32blen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32b);
				temp64len = DoublePrecision.fastExpansionSumZeroelim(temp32alen, temp32a, temp32blen, temp32b, temp64);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp64len, temp64, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
			}
			if (bdytail != 0.0) {
				temp16alen = DoublePrecision.scaleExpansionZeroelim(bytcalen, bytca, bdytail, temp16a);
				bytcatlen = DoublePrecision.scaleExpansionZeroelim(catlen, cat, bdytail, bytcat);
				temp32alen = DoublePrecision.scaleExpansionZeroelim(bytcatlen, bytcat, 2.0 * bdy, temp32a);
				temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp32alen, temp32a, temp48);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;

				temp32alen = DoublePrecision.scaleExpansionZeroelim(bytcatlen, bytcat, bdytail, temp32a);
				bytcattlen = DoublePrecision.scaleExpansionZeroelim(cattlen, catt, bdytail, bytcatt);
				temp16alen = DoublePrecision.scaleExpansionZeroelim(bytcattlen, bytcatt, 2.0 * bdy, temp16a);
				temp16blen = DoublePrecision.scaleExpansionZeroelim(bytcattlen, bytcatt, bdytail, temp16b);
				temp32blen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32b);
				temp64len = DoublePrecision.fastExpansionSumZeroelim(temp32alen, temp32a, temp32blen, temp32b, temp64);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp64len, temp64, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
			}
		}
		if ((cdxtail != 0.0) || (cdytail != 0.0)) {
			if ((adxtail != 0.0) || (adytail != 0.0) || (bdxtail != 0.0) || (bdytail != 0.0)) {
				twoProduct = DoublePrecision.Two_Product(adxtail, bdy);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];
				twoProduct = DoublePrecision.Two_Product(adx, bdytail);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				double[] two_Two_Sum = DoublePrecision.twoTwoSum(ti1, ti0, tj1, tj0);
				u3 = two_Two_Sum[0];
				u[2] = two_Two_Sum[1];
				u[1] = two_Two_Sum[2];
				u[0] = two_Two_Sum[3];
				u[3] = u3;
				negate = -ady;
				twoProduct = DoublePrecision.Two_Product(bdxtail, negate);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];
				negate = -adytail;
				twoProduct = DoublePrecision.Two_Product(bdx, negate);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				two_Two_Sum = DoublePrecision.twoTwoSum(ti1, ti0, tj1, tj0);
				v3 = two_Two_Sum[0];
				v[2] = two_Two_Sum[1];
				v[1] = two_Two_Sum[2];
				v[0] = two_Two_Sum[3];
				v[3] = v3;
				abtlen = DoublePrecision.fastExpansionSumZeroelim(4, u, 4, v, abt);

				twoProduct = DoublePrecision.Two_Product(adxtail, bdytail);
				ti1 = twoProduct[0];
				ti0 = twoProduct[1];
				twoProduct = DoublePrecision.Two_Product(bdxtail, adytail);
				tj1 = twoProduct[0];
				tj0 = twoProduct[1];
				DoublePrecision.twoTwoDiff(ti1, ti0, tj1, tj0);
				abtt3 = two_Two_Sum[0];
				abtt[2] = two_Two_Sum[1];
				abtt[1] = two_Two_Sum[2];
				abtt[0] = two_Two_Sum[3];
				abtt[3] = abtt3;
				abttlen = 4;
			} else {
				abt[0] = 0.0;
				abtlen = 1;
				abtt[0] = 0.0;
				abttlen = 1;
			}

			if (cdxtail != 0.0) {
				temp16alen = DoublePrecision.scaleExpansionZeroelim(cxtablen, cxtab, cdxtail, temp16a);
				cxtabtlen = DoublePrecision.scaleExpansionZeroelim(abtlen, abt, cdxtail, cxtabt);
				temp32alen = DoublePrecision.scaleExpansionZeroelim(cxtabtlen, cxtabt, 2.0 * cdx, temp32a);
				temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp32alen, temp32a, temp48);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
				if (adytail != 0.0) {
					temp8len = DoublePrecision.scaleExpansionZeroelim(4, bb, cdxtail, temp8);
					temp16alen = DoublePrecision.scaleExpansionZeroelim(temp8len, temp8, adytail, temp16a);
					finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp16alen, temp16a, finother);
					finswap = finnow;
					finnow = finother;
					finother = finswap;
				}
				if (bdytail != 0.0) {
					temp8len = DoublePrecision.scaleExpansionZeroelim(4, aa, -cdxtail, temp8);
					temp16alen = DoublePrecision.scaleExpansionZeroelim(temp8len, temp8, bdytail, temp16a);
					finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp16alen, temp16a, finother);
					finswap = finnow;
					finnow = finother;
					finother = finswap;
				}

				temp32alen = DoublePrecision.scaleExpansionZeroelim(cxtabtlen, cxtabt, cdxtail, temp32a);
				cxtabttlen = DoublePrecision.scaleExpansionZeroelim(abttlen, abtt, cdxtail, cxtabtt);
				temp16alen = DoublePrecision.scaleExpansionZeroelim(cxtabttlen, cxtabtt, 2.0 * cdx, temp16a);
				temp16blen = DoublePrecision.scaleExpansionZeroelim(cxtabttlen, cxtabtt, cdxtail, temp16b);
				temp32blen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32b);
				temp64len = DoublePrecision.fastExpansionSumZeroelim(temp32alen, temp32a, temp32blen, temp32b, temp64);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp64len, temp64, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
			}
			if (cdytail != 0.0) {
				temp16alen = DoublePrecision.scaleExpansionZeroelim(cytablen, cytab, cdytail, temp16a);
				cytabtlen = DoublePrecision.scaleExpansionZeroelim(abtlen, abt, cdytail, cytabt);
				temp32alen = DoublePrecision.scaleExpansionZeroelim(cytabtlen, cytabt, 2.0 * cdy, temp32a);
				temp48len = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp32alen, temp32a, temp48);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp48len, temp48, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;

				temp32alen = DoublePrecision.scaleExpansionZeroelim(cytabtlen, cytabt, cdytail, temp32a);
				cytabttlen = DoublePrecision.scaleExpansionZeroelim(abttlen, abtt, cdytail, cytabtt);
				temp16alen = DoublePrecision.scaleExpansionZeroelim(cytabttlen, cytabtt, 2.0 * cdy, temp16a);
				temp16blen = DoublePrecision.scaleExpansionZeroelim(cytabttlen, cytabtt, cdytail, temp16b);
				temp32blen = DoublePrecision.fastExpansionSumZeroelim(temp16alen, temp16a, temp16blen, temp16b, temp32b);
				temp64len = DoublePrecision.fastExpansionSumZeroelim(temp32alen, temp32a, temp32blen, temp32b, temp64);
				finlength = DoublePrecision.fastExpansionSumZeroelim(finlength, finnow, temp64len, temp64, finother);
				finswap = finnow;
				finnow = finother;
				finother = finswap;
			}
		}

		return finnow[finlength - 1];
	}

}
