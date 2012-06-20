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
import org.eclipse.draw2d.Figure;
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
public class WindFigure extends Figure {

	private Double windDirection;
	
	private Double windSpeed;
	
	private Double windSpeedBeaufort;
	
	@Override
	protected void paintFigure(Graphics g) {
		Rectangle copy = getBounds().getCopy();
		g.setAntialias(SWT.ON);

		g.setForegroundColor(ColorConstants.black);
		Color rgb = new Color(Display.getDefault(), new RGB(255,227,181));
		g.setBackgroundColor(rgb);

		// background
		g.fillRectangle(copy);
		g.setLineWidth(3);
		rgb.dispose();

		// degree lines
		g.drawArc(copy.x + 10 , copy.y + 10, copy.width - 20 , copy.height - 20, 120, 300);

		Dimension offset = new Dimension(copy.width / 2, copy.height / 2);
		drawDegreeLines(g, -30, offset);
		drawDegreeLines(g, 0, offset);
		drawDegreeLines(g, 30, offset);
		drawDegreeLines(g, 60, offset);
		drawDegreeLines(g, 90, offset);
		drawDegreeLines(g, 120, offset);
		drawDegreeLines(g, 150, offset);
		drawDegreeLines(g, 180, offset);
		drawDegreeLines(g, 210, offset);
		
		// boat
		g.drawLine(copy.width * 3 / 10, copy.height * 17 / 20, copy.width * 7 / 10, copy.height * 17 / 20);
		g.drawArc(50 , 20, 150 , 200, 110, 100);
		g.drawArc(-2 , 20, 150 , 200, -30, 100);

		// wind speed
		Font font2 = new Font(Display.getDefault(),"Arial",32,SWT.BOLD);  //$NON-NLS-1$
		g.setFont(font2);
		g.drawText(windSpeed.toString(), 55, 90);
		font2.dispose();

		// beaufort
		Font font1 = new Font(Display.getDefault(),"Arial",14,SWT.BOLD);  //$NON-NLS-1$
		g.setFont(font1);
		g.drawText(windSpeedBeaufort.toString(), 80, 150);
		font1.dispose();

		// red and green sector of degree
		g.setLineWidth(8);
		g.setForegroundColor(ColorConstants.red);
		g.drawArc(copy.x + 10 , copy.y + 10, copy.width - 20 , copy.height - 20, 120, 30);
		g.setForegroundColor(ColorConstants.darkGreen);
		g.drawArc(copy.x + 10 , copy.y + 10, copy.width - 20 , copy.height - 20, 30, 30);

		// draw wind direction pointer
		g.setForegroundColor(ColorConstants.black);
		
		if(windDirection != null) {
			Double x =  65 * Math.cos(Math.toRadians(windDirection - 90.0));
			Double y =  65 * Math.sin(Math.toRadians(windDirection - 90.0));
			Double x2 =  110 * Math.cos(Math.toRadians(windDirection - 90.0));
			Double y2 =  110 * Math.sin(Math.toRadians(windDirection - 90.0));
			g.drawLine(offset.width + x2.intValue() , offset.height + y2.intValue(), offset.width + x.intValue(), offset.height + y.intValue());
		} 
		
	}

	private void drawDegreeLines(Graphics g, int i, Dimension offset) {
		Double x =  90 * Math.cos(Math.toRadians(i));
		Double y =  90 * Math.sin(Math.toRadians(i));
		Double x2 =  100 * Math.cos(Math.toRadians(i));
		Double y2 =  100 * Math.sin(Math.toRadians(i));
		g.drawLine(offset.width + x2.intValue() , offset.height + y2.intValue(), offset.width + x.intValue(), offset.height + y.intValue());
	}

	public Double getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(Double windDirection) {
		this.windDirection = windDirection;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Double getWindSpeedBeaufort() {
		return windSpeedBeaufort;
	}

	public void setWindSpeedBeaufort(Double windSpeedBeaufort) {
		this.windSpeedBeaufort = windSpeedBeaufort;
	}


	
	
}
