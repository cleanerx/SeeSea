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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * 
 */
public class DescriptiveInstrumentFigure extends FontFigure implements IInvalidatableFigure {

	private String description;
	
	private String value;

	private boolean validData;
	
	/**
	 * 
	 */
	public DescriptiveInstrumentFigure() {
		fontSize = 8;
		validData = true;
	}
	
	@Override
	protected void paintFigure(Graphics g) {

		Rectangle copy = getBounds().getCopy();
		
		if(validData) {
			g.setAlpha(200);
		} else {
			g.setAlpha(100);
		}
//		Color rgb = new Color(Display.getDefault(), new RGB(255,227,181));
		g.setBackgroundColor(ColorConstants.white);
		g.setForegroundColor(ColorConstants.black);
		Rectangle cropped = copy.getCropped(new Insets(0, 0, 2, 1));
		g.fillRectangle(cropped);
		g.drawRectangle(cropped);
		g.setForegroundColor(ColorConstants.lightGray);
		g.drawLine(copy.x + 1, copy.y + copy.height - 1, copy.x + copy.width - 1, copy.y + copy.height - 1);
		g.drawLine(copy.x + 1, copy.y + 1, copy.x + copy.width - 1, copy.y + 1);
		g.setForegroundColor(ColorConstants.black);

		Font font2 = new Font(Display.getDefault(),"Arial", fontSize - 5, SWT.BOLD);  //$NON-NLS-1$
		g.setFont(font2);
		Dimension textDimensionLatitude = FigureUtilities.getTextExtents(description, font2);
		g.drawText(description, copy.x, copy.y);
		font2.dispose();

		font2 = new Font(Display.getDefault(),"Arial", fontSize, SWT.BOLD);  //$NON-NLS-1$
		g.setFont(font2);
		Dimension textDimensionLatitude2 = FigureUtilities.getTextExtents(value, font2);
		g.drawText(value, copy.x + copy.width - textDimensionLatitude2.width , copy.y + textDimensionLatitude.height);
		font2.dispose();

		if(!validData) {
			g.setForegroundColor(ColorConstants.red);
			g.drawLine(copy.x, copy.y, copy.x + copy.width, copy.y + copy.height);
			g.drawLine(copy.x , copy.y + copy.height, copy.x + copy.width, copy.y);
		}

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		Font font2 = new Font(Display.getDefault(),"Arial", fontSize, SWT.BOLD);  //$NON-NLS-1$
		Dimension textDimensionLatitude = FigureUtilities.getTextExtents(description + value, font2);
		font2.dispose();
		return new Dimension(textDimensionLatitude.width, textDimensionLatitude.height * 2);
	}
	
	@Override
	public int getFontSize() {
		return fontSize;
	}

	@Override
	public void setFontSize(int fontSize) {
		if(fontSize < 8) {
			this.fontSize = 8;
		} else {
			this.fontSize = fontSize;
		}
	}

	@Override
	public void setValidData(boolean validData) {
		this.validData = validData;
	}

}
