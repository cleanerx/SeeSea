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

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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

public class BarFigure extends Figure implements IInvalidatableFigure {

	private boolean validData;
	private Map<Integer,Double> description2heights;
	private Color lightGreen;
	private int fontSize;

	public BarFigure(LocalResourceManager resourceManager) {
		lightGreen = resourceManager.createColor(NavigationColorConstants.LIGHT_GREEN);
		description2heights = new TreeMap<Integer, Double>();
//		for(int i = 0 ; i < numberOfHeights; i++) {
			description2heights.put(0, 0.0);
//		}
		validData = true; // show valid data on start even if that is not possible
	}

	@Override
	public void setValidData(boolean validData) {
		this.validData = validData;
	}

	@Override
	protected void paintFigure(Graphics g) {
		Rectangle copy = getBounds().getCopy();
		
		drawFrameBorder(g, copy);
		
		int x = 0;
		int legendOffset = 0;
		int startx = copy.x + legendOffset;
		g.drawText(Messages.getString("BarFigure.signalStrength"), new Point(copy.x, copy.y)); //$NON-NLS-1$

		if(description2heights.size() > 0) {
			
		int offset = copy.width / description2heights.size();
		for (Entry<Integer, Double> entry : description2heights.entrySet()) {
			int heightY = (int) ((copy.height) * Math.ceil(entry.getValue())) / 100;
			g.setForegroundColor(lightGreen);
			g.setBackgroundColor(ColorConstants.darkGreen);
			g.fillGradient(startx + x, copy.y + copy.height - heightY - 21, offset, heightY , false);
			g.setForegroundColor(ColorConstants.black);
			Font font2 = new Font(Display.getDefault(),"Arial", 8, SWT.BOLD);  //$NON-NLS-1$
			Dimension textExtents = FigureUtilities.getTextExtents(entry.getKey().toString(), font2);
			int centerOffset = (offset - textExtents.width) / 2;
			g.drawText(entry.getKey().toString(), new Point(startx + x + centerOffset , copy.y + copy.height - 20));
			font2.dispose();
//			g.fillRectangle(startx + x, copy.y + 1, 1,  heightY );
//			g.fillRectangle(startx + x + 1, copy.y + 1, 1,  heightY );
			x+=offset;
		}
		g.drawLine(0, copy.y + copy.height - 21, copy.width + 10, copy.y + copy.height - 21);
		}
		if(!validData) {
			g.setForegroundColor(ColorConstants.red);
			g.drawLine(copy.x, copy.y, copy.x + copy.width, copy.y + copy.height);
			g.drawLine(copy.x , copy.y + copy.height, copy.x + copy.width, copy.y);
		}
	}
	
	public void setBarHeights(Map<Integer,Double> description2heights) {
		this.description2heights = description2heights;
	}
	
	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		return new Dimension(100, 52);
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	private void drawFrameBorder(Graphics g, Rectangle copy) {
		if(validData) {
			g.setAlpha(200);
		} else {
			g.setAlpha(100);
		}
		g.setBackgroundColor(ColorConstants.white);
		g.setForegroundColor(ColorConstants.black);
		Rectangle cropped = copy.getCropped(new Insets(0, 0, 1, 1));
		g.fillRectangle(cropped);
		g.drawRectangle(cropped);
	}

	
}
