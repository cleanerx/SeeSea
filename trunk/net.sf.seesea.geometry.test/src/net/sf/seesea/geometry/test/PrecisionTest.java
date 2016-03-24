/**
Copyright (c) 2013-2015, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package net.sf.seesea.geometry.test;

import net.sf.seesea.geometry.impl.math.DoublePrecision;
import junit.framework.TestCase;

public class PrecisionTest extends TestCase {

	public void testSplit() {
		double[] split = DoublePrecision.split(0);
		assertEquals("Inprecise value expected", 0.0d,split[0]);
		assertEquals("Roundoff error not as before", 0.0d,split[1]);
		
		double[] split2 = DoublePrecision.split(1);
		assertEquals("Inprecise value expected", 1.0d,split2[0]);
		assertEquals("Roundoff error not as before", 0.0d,split2[1]);
		
		double[] split3 = DoublePrecision.split(1.1d);
		assertEquals("Inprecise value expected", 1.0999999940395355d,split3[0]);
		assertEquals("Roundoff error not as before", 5.9604645663569045E-9d,split3[1]);
		assertFalse("Precise value returned where it should not", split3[0] == 1.1d);
	}
	
	public void testDifference() {
		double a = 1.0;
		double b = 0.05;
		double[] two_Diff = DoublePrecision.twoDiff(a, b);
		assertEquals("Inprecise value expected", 0.95d,two_Diff[0]);
		assertEquals("Roundoff error not as before", 4.163336342344337E-17,two_Diff[1]);
	}

	public void testSum() {
		double a = 1.37;
		double b = 0.01;
		double[] two_Diff = DoublePrecision.twoSum(a, b);
		assertEquals("Inprecise value expected", 1.3800000000000001d,two_Diff[0]);
		assertEquals("Roundoff error not as before", -8.673617379884035E-18,two_Diff[1]);
	}

	public void testFastSum() {
		double a = 1.37;
		double b = 0.01;
		double[] two_Diff = DoublePrecision.fastTwoSum(a, b);
		assertEquals("Inprecise value expected", 1.3800000000000001d,two_Diff[0]);
		assertEquals("Roundoff error not as before", -8.673617379884035E-18,two_Diff[1]);
	}

	public void testProduct() {
		double a = 0.6;
		double b = 3;
		double[] two_Diff = DoublePrecision.Two_Product(a, b);
		assertEquals("Inprecise value expected", 1.7999999999999998d,two_Diff[0]);
		assertEquals("Roundoff error not as before", 1.1102230246251565E-16,two_Diff[1]);
	}

	public void testSquare() {
		double a = 1.4;
		double[] two_Diff = DoublePrecision.square(a);
		assertEquals("Inprecise value expected", 1.9599999999999997d,two_Diff[0]);
		assertEquals("Roundoff error not as before", 8.88178419700126E-18,two_Diff[1]);
	}

}
