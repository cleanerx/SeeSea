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
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * 
 */
public class PositionInstrumentFigure extends FreeformLayer {
	
	private String positionLatitude; 

	private String positionLongitude; 

	/**
	 * 
	 */
	public PositionInstrumentFigure() {
		return;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
//		IFigure p = this;
//		while(!(p instanceof Viewport)) {
//			p = p.getParent();
//		}
//		
//		Viewport viewport = (Viewport) p;
//		Rectangle copyX = new Rectangle(viewport.getViewLocation(), viewport.getSize());
		
//		Dimension prefSize2 = getPreferredSize();
//		Rectangle copy = new Rectangle(copyX.x + copyX.width - prefSize2.width, copyX.y + copyX.height - prefSize2.height, prefSize2.width, prefSize2.height);
		Rectangle copy = getBounds().getCopy();
		
		Color rgb = new Color(Display.getDefault(), new RGB(255,227,181));
		g.setBackgroundColor(rgb);
		g.setForegroundColor(ColorConstants.black);
		g.fillRectangle(copy);
		rgb.dispose();

		Font font2 = new Font(Display.getDefault(),"Arial", copy.width / 8, SWT.BOLD);  //$NON-NLS-1$
		g.setFont(font2);
		Dimension textDimensionLatitude = FigureUtilities.getTextExtents(positionLatitude, font2);

		g.drawText(positionLatitude, copy.x + 100 + copy.width - textDimensionLatitude.width, copy.y);
		Dimension textDimensionLongitude = FigureUtilities.getTextExtents(positionLongitude, font2);

		g.drawText(positionLongitude, copy.x +  copy.width - textDimensionLongitude.width, copy.y + textDimensionLatitude.height);
		font2.dispose();
	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		return new Dimension(200, 80);
	}

	public String getPositionLatitude() {
		return positionLatitude;
	}

	public void setPositionLatitude(String positionLatitude) {
		this.positionLatitude = positionLatitude;
	}

	public String getPositionLongitude() {
		return positionLongitude;
	}

	public void setPositionLongitude(String positionLongitude) {
		this.positionLongitude = positionLongitude;
	}
	
	

}
