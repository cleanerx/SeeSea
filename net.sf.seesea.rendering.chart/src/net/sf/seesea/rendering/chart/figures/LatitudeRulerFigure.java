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
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class LatitudeRulerFigure extends Figure implements IMapZoomableDependentFigure {

	private final Transposer transposer;
	
	public int textMargin ;

	private static final int BORDER_WIDTH = 3;
	
	private int _tileSize;

	private int _zoom;

	private FontData degreeFontData;

	private FontData minuteFontData;

	private FontData secondsFontData;

	private Font degreeFont;

	private Font minuteFont;

	private Font secondsFont;
	
	public LatitudeRulerFigure(int tileSize) {
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
		double a = clip.width / 80;
		double valueToLatitude = SphericalMercatorProjection.valueToLatitude(clippedBounds.x , 256, 1 << _zoom);
		double valueToLatitude2 = SphericalMercatorProjection.valueToLatitude(clippedBounds.x + clippedBounds.width, 256, 1 << _zoom);
		clippedBounds.x = figClientArea.x;
		clippedBounds.width = figClientArea.width - BORDER_WIDTH;
		
		// Paint the background
		if (isOpaque()) {
			graphics.fillRectangle(transposer.t(clippedBounds));
		}
		
		 
		double distance = Math.abs(valueToLatitude2 - valueToLatitude);
//		System.out.println(valueToLatitude + ":" + valueToLatitude2 + ":" + distance);
		
		double d = distance / a;
		double countingDistance = 100;
		DecimalFormat decimalFormat = new DecimalFormat("#0");
		DecimalFormat degreeFormat = new DecimalFormat("#0");
		DecimalFormat minuteFormat1 = new DecimalFormat("0.0");
		DecimalFormat minuteFormat2 = new DecimalFormat("0.00");
		if(d > 75.0) {
			countingDistance = 100;
		} else if(d > 35.0 && d < 75.0 ) {
			countingDistance = 50;
		} else if(d > 15.0 && d < 35.0) {
			countingDistance = 20;
		} else if(d > 7.5 && d < 15.0) {
			countingDistance = 10;
		} else if(d > 3.5 && d < 7.5) {
			countingDistance = 5;
		} else if(d > 1.5 && d < 3.5) {
			countingDistance = 2;
		} else if(d > 0.75 && d < 1.5) {
			countingDistance = 1;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.35 && d < 0.75) {
			countingDistance = 0.5;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.15 && d < 0.35) {
			countingDistance = 0.16666667;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.075 && d < 0.15) {
			countingDistance = 0.083333333;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.035 && d < 0.075) {
			countingDistance = 0.05;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.015 && d < 0.035) {
			countingDistance = 0.0166666667;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.0075 && d < 0.015) {
			countingDistance = 0.00833333333;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.0035 && d < 0.0075) {
			countingDistance = 0.005;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.0015 && d < 0.0035) {
			countingDistance = 0.00166666667;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.00075 && d < 0.0015) {
			countingDistance = 0.0008333333333;
			decimalFormat = new DecimalFormat("#0.###");
		} else if(d > 0.00035 && d < 0.00075) {
			countingDistance = 0.0005;
			decimalFormat = new DecimalFormat("#0.###");
		}  else {
			countingDistance = 0.000166666667;
			decimalFormat = new DecimalFormat("#0.####");
		}
		
		double firstLatitudeValue = (((Double)(valueToLatitude2 / countingDistance)).intValue() ) * countingDistance;
		double endLatitudeValue = (((Double)(valueToLatitude / countingDistance)).intValue() + 1 ) * countingDistance;
		if(endLatitudeValue >= 90.0) {
			endLatitudeValue = (((Double)(valueToLatitude / countingDistance)).intValue()) * countingDistance;
		}

		int counter = ((Double) ((endLatitudeValue - firstLatitudeValue) / countingDistance)).intValue(); 
		
		for(int i = 0 ; i <= counter ; i++) {
			Image numImage = null; 

			Double decimalDegree = firstLatitudeValue + (i * countingDistance);
			Double latitude2Value = SphericalMercatorProjection.latitude2Value(256, 1 << _zoom , GeoParser.parseLatitude(decimalDegree));
			long round = Math.round(Math.IEEEremainder(decimalDegree, 1.0) * 1000);
			String num = " ";
			
			// if it is a full numerical value show that i.e. 49°
			// if the remainder is less than x, show minutes
			// if the remainder is less than y, show seconds
			
			if(round == 0) {
				num = degreeFormat.format(Math.abs(decimalDegree)) + "\u00B0"; //$NON-NLS-1$
				numImage = ImageUtilities.createRotatedImageOfString(num, degreeFont,
						getForegroundColor(), getBackgroundColor());
			} else {
				decimalDegree = (decimalDegree + (Math.floor(Math.abs(decimalDegree)) * (-1))) * 600 / 10;
				round = Math.round(Math.IEEEremainder(decimalDegree, 1.0) * 1000);
				if(round == 0) {
					num = degreeFormat.format(decimalDegree) + "'"; //$NON-NLS-1$
					numImage = ImageUtilities.createRotatedImageOfString(num, minuteFont,getForegroundColor(), getBackgroundColor());
				} else {
					decimalDegree = (decimalDegree + (Math.floor(Math.abs(decimalDegree)) * (-1))) * 600 / 10;
					round = Math.round(Math.IEEEremainder(decimalDegree, 1.0) * 1000);
					if(round == 0) {
						num = degreeFormat.format(decimalDegree) + "\""; //$NON-NLS-1$
						numImage = ImageUtilities.createRotatedImageOfString(num, secondsFont,getForegroundColor(), getBackgroundColor());
					} 
				}
			}
			
			if(numImage !=null) {
				org.eclipse.draw2d.geometry.Point textLocation = new org.eclipse.draw2d.geometry.Point(clippedBounds.x + textMargin,
						Math.round(latitude2Value) - (numImage.getBounds().height / 2));
				graphics.drawImage(numImage, textLocation);
				
				org.eclipse.draw2d.geometry.Point lineStartLocation = new org.eclipse.draw2d.geometry.Point(clippedBounds.x + numImage.getBounds().width + textMargin + 1, Math.round(latitude2Value)); 
				org.eclipse.draw2d.geometry.Point lineEndLocation = new org.eclipse.draw2d.geometry.Point(clippedBounds.x + numImage.getBounds().width + textMargin * 2, Math.round(latitude2Value)); 
				graphics.drawLine(lineStartLocation, lineEndLocation);
				
				numImage.dispose();
			}

		}
		
		clippedBounds.expand(BORDER_WIDTH, 0);
		graphics.setForegroundColor(ColorConstants.buttonDarker);
		graphics.drawLine(transposer.t(clippedBounds.getTopRight().translate(-1, -1)),
				transposer.t(clippedBounds.getBottomRight().translate(-1, -1)));

	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		Dimension _prefSize = new Dimension();
		_prefSize.width = (textMargin * 2) + BORDER_WIDTH
		+ FigureUtilities.getFontMetrics(degreeFont).getAscent();
		return _prefSize;
	}

	public int getTileSize() {
		return _tileSize;
	}

	public void setTileSize(int tileSize) {
		this._tileSize = tileSize;
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
