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
package net.sf.seesea.services.navigation;

import net.sf.seesea.services.navigation.IGeoBoundingBox;


public class GeoBoundingBox implements IGeoBoundingBox {

	private double top;

	private double right;

	private double bottom;

	private double left;

	public GeoBoundingBox() {
		
	}

	@Override
	public double getTop() {
		return top;
	}

	@Override
	public void setTop(double top) {
		this.top = top;
	}

	@Override
	public double getRight() {
		return right;
	}

	@Override
	public void setRight(double right) {
		this.right = right;
	}

	@Override
	public double getBottom() {
		return bottom;
	}

	@Override
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}

	@Override
	public double getLeft() {
		return left;
	}

	@Override
	public void setLeft(double left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return "GeoBoundingBox [top=" + top + ", right=" + right + ", bottom="
				+ bottom + ", left=" + left + "]";
	}

	public boolean contains(double y, double x) {
		return y >= this.bottom && y < this.top && x >= this.left
				&& x < this.right;
	}
	
	

}
