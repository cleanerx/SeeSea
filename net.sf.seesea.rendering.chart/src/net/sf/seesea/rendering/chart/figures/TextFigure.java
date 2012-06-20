/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class TextFigure extends GeoFigure {

	public TextFigure() {
//		setOpaque(true);
//		super(0,-10);
//		setBounds(new Rectangle(5,5,200,200));
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		Rectangle r = getBounds().getCopy();
		g.setBackgroundColor(new Color(Display.getDefault(), new RGB(248, 216, 4)));
		g.setForegroundColor(ColorConstants.black);
		PointList pointList = new PointList();
		pointList.addPoint(new Point(r.x + 5, r.y + 0));
		pointList.addPoint(new Point(r.x + 13,r.y + 0));
		pointList.addPoint(new Point(r.x + 17,r.y + 5));
		pointList.addPoint(new Point(r.x + 17,r.y + 12));
		pointList.addPoint(new Point(r.x + 10,r.y + 24));
		pointList.addPoint(new Point(r.x + 10,r.y + 26));
		pointList.addPoint(new Point(r.x + 7,r.y + 26));
		pointList.addPoint(new Point(r.x + 0,r.y + 12));
		pointList.addPoint(new Point(r.x + 0,r.y + 5));
		pointList.addPoint(new Point(r.x + 5,r.y + 0));
		
//		int[] boundPoints = new int[] {r.x + 5,r.y + 0,  r.x + 13,r.y + 0,  r.x + 17,r.y + 5,  r.x + 17,r.y + 12, r.x + 10,r.y + 24,  r.x + 10,r.y + 26, r.x + 7,r.y + 26 ,  r.x + 0,r.y + 12,  r.x + 0,r.y + 5 , r.x + 5,r.y + 0};
		int[] fillPoints = new int[]  {r.x + 5,r.y + 1,  r.x + 13,r.y + 1,  r.x + 16,r.y + 6,  r.x + 16,r.y + 11,      r.x + 9,r.y + 25,             r.x + 1,r.y + 12,  r.x + 1,r.y + 5 , r.x + 5,r.y + 1};

		g.drawPolygon(pointList);
		g.fillPolygon(fillPoints);
		
	}

	@Override
	public Dimension getPreferredSize(int w, int h) {
//		return new Dimension(20,20);
		Dimension prefSize = super.getPreferredSize(w, h);
//		Dimension defaultSize = new Dimension(100,100);
//		prefSize.union(defaultSize);
		return prefSize;
	}
	
	
	
}
