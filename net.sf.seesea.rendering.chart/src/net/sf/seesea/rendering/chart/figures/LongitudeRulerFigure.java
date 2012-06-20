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

import java.text.DecimalFormat;

import net.sf.seesea.model.util.GeoParser;
import net.sf.seesea.tileservice.projections.SphericalMercatorProjection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.widgets.Display;

public class LongitudeRulerFigure extends Figure implements IMapZoomableDependentFigure {

	private final Transposer transposer;
	
	public int textMargin ;

	private int _zoom;
	
	private static final int BORDER_WIDTH = 3;

	private final int _tileSize;
	
	private FontData degreeFontData;

	private FontData minuteFontData;

	private FontData secondsFontData;

	private Font degreeFont;

	private Font minuteFont;

	private Font secondsFont;
	
	public LongitudeRulerFigure(int tileSize) {
		_tileSize = tileSize;
		textMargin = 3;
		transposer = new Transposer();
		transposer.setEnabled(true);
		setBackgroundColor(ColorConstants.listBackground);
		setForegroundColor(ColorConstants.listForeground);
		setOpaque(true);
		setLayoutManager(new XYLayout());
		degreeFontData = new FontData("Segoe UI", 10, SWT.BOLD); //$NON-NLS-1$
		minuteFontData = new FontData("Segoe UI", 8, SWT.BOLD); //$NON-NLS-1$
		secondsFontData = new FontData("Segoe UI", 8, SWT.NONE); //$NON-NLS-1$
		degreeFont = new Font(Display.getDefault(), degreeFontData);
		minuteFont = new Font(Display.getDefault(), minuteFontData);
		secondsFont = new Font(Display.getDefault(), secondsFontData);

	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle clip = transposer.t(graphics.getClip(Rectangle.SINGLETON));
		Rectangle figClientArea = transposer.t(getClientArea());
		// Use the x and width of the client area, but the y and height of the clip as the 
		// bounds of the area which is to be repainted.  This will increase performance as the
		// entire ruler will not be repainted everytime.
		Rectangle clippedBounds = clip;
		clippedBounds.x = figClientArea.x;
		clippedBounds.width = figClientArea.width - BORDER_WIDTH;
		
		// Paint the background
		if (isOpaque()) {
			graphics.fillRectangle(transposer.t(clippedBounds));
		}
		
		int counter = 36;
		double multiplier = 10;
		DecimalFormat decimalFormat = new DecimalFormat("##0.######");
		if(_zoom == 0) {
			counter = 2;
			multiplier = 180;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 1) {
			counter = 3;
			multiplier = 120;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 2) {
			counter = 6;
			multiplier = 60;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 3) {
			counter = 12;
			multiplier = 30;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 4) {
			counter = 24;
			multiplier = 15;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 5) {
			counter = 36;
			multiplier = 10;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 6) {
			counter = 72;
			multiplier = 5;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 7) {
			counter = 180;
			multiplier = 2;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 8) {
			counter = 360;
			multiplier = 1;
			decimalFormat = new DecimalFormat("##0"); 
		} else if(_zoom == 9) {
			counter = 720;
			multiplier = 0.5;
		} else if(_zoom == 10) {
			counter = 2160;
			multiplier = 0.1666666667;
		} else if(_zoom == 11) {
			counter = 3600;
			multiplier = 0.1;
		} else if(_zoom == 12) {
			counter = 7200;
			multiplier = 0.05;
		} else if(_zoom == 13) {
			counter = 9000;
			multiplier = 0.04;
		} else if(_zoom == 14) {
			counter = 18000;
			multiplier = 0.02;
		} else if(_zoom == 15) {
			counter = 36000;
			multiplier = 0.01;
		} else if(_zoom == 16) {
			counter = 72000;
			multiplier = 0.005;
		} else if(_zoom == 17) {
			counter = 180000;
			multiplier = 0.002;
		} else if(_zoom == 18) {
			counter = 360000;
			multiplier = 0.001;
		}
		
		FontMetrics fontMetrics = FigureUtilities.getFontMetrics(getFont());
		int leading = fontMetrics.getLeading();
		
		for(int i = 1 ; i < counter; i ++ ) {
			
			Double value = new Double(i * multiplier - 180);
			Double longitude2Value = SphericalMercatorProjection.longitude2Value(_tileSize, 1 << _zoom , GeoParser.parseLongitude(value));
			long round = Math.round(Math.IEEEremainder(value, 1.0) * 1000);
//			System.out.println(round);
			if(longitude2Value.intValue() < clippedBounds.y || longitude2Value.intValue() > (clippedBounds.y + clippedBounds.height)) {
				continue;
			}
			if(value < 0) {
				value = value * - 1;
			}
			String num = "x";
			decimalFormat = new DecimalFormat("0");
			Dimension numSize = null;
			if(round == 0) {
//				degreeFormat = new DecimalFormat("#0");
				num = decimalFormat.format(Math.abs(value)) + "\u00B0";
				numSize = FigureUtilities.getStringExtents(num, degreeFont);
//				setFont(degreeFont);
			} else {
				value = (value + (Math.floor(Math.abs(value)) * (-1))) * 600 / 10;
				round = Math.round(Math.IEEEremainder(value, 1.0) * 1000);
				if(round == 0) {
					num = decimalFormat.format(value) + "'";
					numSize = FigureUtilities.getStringExtents(num, minuteFont);
//					setFont(minuteFont);
				} else {
					value = (value + (Math.floor(Math.abs(value)) * (-1))) * 600 / 10;
					round = Math.round(Math.IEEEremainder(value, 1.0) * 1000);
					if(round == 0) {
						num = decimalFormat.format(value) + "\""; //$NON-NLS-1$
						numSize = FigureUtilities.getStringExtents(num, secondsFont);
//						setFont(secondsFont);
					} 
				}

			}
			
			if(numSize != null) {
			org.eclipse.draw2d.geometry.Point textLocation = new org.eclipse.draw2d.geometry.Point(longitude2Value - (numSize.width / 2), 
					clippedBounds.x + textMargin - leading); 
			graphics.drawText(num, textLocation);  //$NON-NLS-1$
			org.eclipse.draw2d.geometry.Point lineStartLocation = new org.eclipse.draw2d.geometry.Point(longitude2Value, 
					clippedBounds.x + fontMetrics.getHeight() + 2 ); 
			org.eclipse.draw2d.geometry.Point lineEndLocation = new org.eclipse.draw2d.geometry.Point(longitude2Value, 
					clippedBounds.x + textMargin + fontMetrics.getHeight()); 
			graphics.drawLine(lineStartLocation, lineEndLocation);
			}
		}
		
		/*
		 * If the width is even, we want to increase it by 1.  This will ensure
		 * that when marks are erased because they are too close to the
		 * number, they are erased from both sides of that number.
		 */

//		forbiddenZone.setLocation(textLocation);
//		forbiddenZone.setSize(numSize);
//		forbiddenZone.expand(1, 1);
//		graphics.fillRectangle(forbiddenZone);
		// Uncomment the following line of code if you want to see a line at
		// the exact position of the major mark
//		graphics.drawLine(y, clippedBounds.x, y, clippedBounds.x + clippedBounds.width);


		clippedBounds.expand(BORDER_WIDTH, 0);
		graphics.setForegroundColor(ColorConstants.buttonDarker);
		graphics.drawLine(transposer.t(clippedBounds.getTopRight().translate(-1, -1)),
				transposer.t(clippedBounds.getBottomRight().translate(-1, -1)));

	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {

		Dimension _prefSize = new Dimension();
		_prefSize.height = (textMargin * 2) + BORDER_WIDTH
		+ FigureUtilities.getFontMetrics(degreeFont).getAscent();
		return _prefSize;
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.ui.figures.IMapZoomableDependentFigure#getZoom()
	 */
	public int getZoom() {
		return _zoom;
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.ui.figures.IMapZoomableDependentFigure#setZoom(int)
	 */
	public void setZoom(int zoomLevel) {
		_zoom = zoomLevel;
	}	
}
