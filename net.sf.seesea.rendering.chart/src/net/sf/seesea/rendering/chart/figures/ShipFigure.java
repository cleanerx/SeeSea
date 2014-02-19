/**
 * 
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.rendering.chart.figures;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.swt.SWT;

/**
 * 
 */
public class ShipFigure extends Figure {

	private double cogOrientation;

	private double compassOrientation;

	public ShipFigure() {
		cogOrientation = 0.0;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		graphics.setForegroundColor(ColorConstants.darkGreen);
		graphics.setBackgroundColor(ColorConstants.orange);
		graphics.setAntialias(SWT.ON);
		setOpaque(false);
		
		graphics.setLineWidth(1);
		Rectangle localBounds = getBounds().getCopy();
		graphics.pushState();
		Point center = localBounds.getCenter();
		graphics.translate(center.x, center.y);
		graphics.rotate((float)cogOrientation);
		graphics.fillPolygon(new int[]{ -5, 5, 0, -7  ,  5, 5 });
		graphics.popState();

	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		Rectangle rectangle = getBounds().getCopy();
		Transform trans = new Transform();
		trans.setRotation(cogOrientation);
		
		List<Point> points = new ArrayList<Point>(4);
		points.add(trans.getTransformed(new Point(-10,-22)));
		points.add(trans.getTransformed(new Point(-10,22)));
		points.add(trans.getTransformed(new Point(10,-22)));
		points.add(trans.getTransformed(new Point(10,22)));

		int minx = Integer.MAX_VALUE;
		int maxx = Integer.MIN_VALUE; 
		int miny = Integer.MAX_VALUE;
		int maxy = Integer.MIN_VALUE;
		for (Point point : points) {
			if(point.x > maxx) {
				maxx = point.x;
			}
			if(point.x < minx) {
				minx = point.x;
			}
			if(point.y > maxy) {
				maxy = point.y;
			}
			if(point.y < miny) {
				miny = point.y();
			}
		}
//		System.out.println(Math.abs(maxx - minx) + ":" + Math.abs(maxy - miny));
		return new Dimension(Math.abs(maxx - minx), Math.abs(maxy - miny));
//		System.out.println(minx + miny);
//		return new Dimension(x.intValue(), y.intValue());
//		return new Dimension(30,40);
	}

	public double getCOGOrientation() {
		return cogOrientation;
	}

	public void setCOGOrientation(double orientation) {
		this.cogOrientation = orientation;
	}

	public double getCompassOrientation() {
		return compassOrientation;
	}

	public void setCompassOrientation(double compassOrientation) {
		this.compassOrientation = compassOrientation;
	}

	
	
	
	
}
