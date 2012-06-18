/**
 * 
 Copyright (c) 2010, Jens Kübler All rights reserved.
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
package net.sf.seesea.navigation.ui.figures;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.navigation.ui.NavigationColorConstants;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public class GraphFigure extends Figure implements IInvalidatableFigure {

	private List<Double> _lastValues;
	
	private NumberFormat decimalFormat;

	private int fontSize;
	
	private String description;

	private Color darkYellow;

	private Color darkRed;

	private Color lightRed;

	private Color lightGreen;

	private boolean validData;
	
	public GraphFigure(LocalResourceManager resourceManager) {
		_lastValues = new ArrayList<Double>();
		decimalFormat = NumberFormat.getNumberInstance();
		decimalFormat.setMaximumFractionDigits(1);
		decimalFormat.setMinimumFractionDigits(1);
		darkYellow = resourceManager.createColor(NavigationColorConstants.DARK_YELLOW);
		lightRed = resourceManager.createColor(NavigationColorConstants.LIGHT_RED);
		darkRed = resourceManager.createColor(NavigationColorConstants.DARK_RED);
		lightGreen = resourceManager.createColor(NavigationColorConstants.LIGHT_GREEN);
		validData = true; // show valid data on start even if that is not possible
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		Rectangle copy = getBounds().getCopy();
		drawFrameBorder(g, copy);
		drawLegend(g, copy);
		drawGraph(g, copy);
		drawDescription(g, copy);
		drawValue(g, copy);
		if(!validData) {
			g.setForegroundColor(ColorConstants.red);
			g.drawLine(copy.x, copy.y, copy.x + copy.width, copy.y + copy.height);
			g.drawLine(copy.x , copy.y + copy.height, copy.x + copy.width, copy.y);
		}
	}

	private void drawGraph(Graphics g, Rectangle copy) {
		g.setForegroundColor(ColorConstants.red);
		int x = 0;
		int legendOffset = 26;
		int startx = copy.x + legendOffset;
		
		int barWidth = 2;
		int drawableDepthCounter = ((copy.width / 2) - legendOffset) / barWidth;
		while(_lastValues.size() >= drawableDepthCounter) {
			_lastValues.remove(0);
		}
		for (Double value : _lastValues) {
			if(value > 30.0 && value <= 50.0) {
				int height = (int) Math.ceil(value);
				g.setBackgroundColor(lightGreen);
				g.fillRectangle(startx + x, copy.y + 1, 1,  height );
				g.setBackgroundColor(ColorConstants.darkGreen);
				g.fillRectangle(startx + x + 1, copy.y + 1, 1,  height );
			} else if(value > 50.0) {
				g.setBackgroundColor(lightGreen);
				g.fillRectangle(startx + x, copy.y + 1, 1, copy.height );
				g.setBackgroundColor(ColorConstants.darkGreen);
				g.fillRectangle(startx + x + 1, copy.y + 1, 1, copy.height );
			} else if(value <= 10.0) {
				int height = (int) Math.ceil(value);
				g.setBackgroundColor(lightRed);
				g.fillRectangle(startx + x, copy.y + 1, 1,  height );
				g.setBackgroundColor(darkRed);
				g.fillRectangle(startx + x + 1, copy.y + 1, 1,  height );
			} else {
				int height = (int) Math.ceil(value);
				g.setBackgroundColor(ColorConstants.orange);
				g.fillRectangle(startx + x, copy.y + 1, 1,  height );
				g.setBackgroundColor(darkYellow);
				g.fillRectangle(startx + x + 1, copy.y + 1, 1,  height );
			}
			x+=2;
		}
	}

	private void drawLegend(Graphics g, Rectangle copy) {
		g.drawText("50m", copy.x + 2, copy.y  + 35); //$NON-NLS-1$
		g.drawText("0m", copy.x + 2, copy.y); //$NON-NLS-1$
		g.drawLine(copy.x + 20, copy.y , copy.x + 30, copy.y );
		g.drawLine(copy.x + 20, copy.y + copy.height -1 , copy.x + 30, copy.y + copy.height -1);
	}

	private void drawFrameBorder(Graphics g, Rectangle copy) {
		if(validData) {
			g.setAlpha(200);
		} else {
			g.setAlpha(100);
		}
		g.setBackgroundColor(ColorConstants.white);
		g.setForegroundColor(ColorConstants.black);
		Rectangle cropped = copy.getCropped(new Insets(0, 25, 1, 1));
		g.fillRectangle(cropped);
		g.drawRectangle(cropped);
	}

	private void drawValue(Graphics g, Rectangle copy) {
		String depth = "---m"; //$NON-NLS-1$
		if(!_lastValues.isEmpty()) {
			depth = decimalFormat.format(_lastValues.get(_lastValues.size() - 1)) + "m"; //$NON-NLS-1$
		}
		
		Font textFont = new Font(Display.getDefault(),"Arial", fontSize , SWT.BOLD);  //$NON-NLS-1$
		g.setFont(textFont);
		Dimension textDimensionDepth = FigureUtilities.getTextExtents(depth, textFont);
		Point point2 = new Point(copy.x + copy.width -  textDimensionDepth.width, copy.y + copy.height -  textDimensionDepth.height);
		g.setForegroundColor(ColorConstants.black);
		g.drawText(depth, point2);
		textFont.dispose();
	}

	private void drawDescription(Graphics g, Rectangle copy) {
		g.setForegroundColor(ColorConstants.black);
		Font font2 = new Font(Display.getDefault(),"Arial", fontSize - 5, SWT.BOLD);  //$NON-NLS-1$
		g.setFont(font2);
		Dimension textDimensionDepthDescription = FigureUtilities.getTextExtents(description, font2); //$NON-NLS-1$
		Point point = new Point(copy.x + copy.width / 2 , copy.y );
		g.drawText(description, point);
		font2.dispose();
	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		return new Dimension(100, 52);
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addValue(Double double1) {
		_lastValues.add(double1);
	}
	
	@Override
	public void erase() {
		super.erase();
		
	}

	public void setValidData(boolean validData) {
		this.validData = validData;
		
	}
}
