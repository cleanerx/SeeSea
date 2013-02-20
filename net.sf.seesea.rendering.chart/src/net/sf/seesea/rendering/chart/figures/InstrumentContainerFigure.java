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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * 
 */
public class InstrumentContainerFigure extends Figure {


protected IFigure childArea;

  private static Dimension minSize = new Dimension(100, 40);

private RGB color1;

  /**
 * 
 */
public InstrumentContainerFigure() {
    setMinimumSize(minSize);
    setPreferredSize(minSize);
    setForegroundColor(ColorConstants.white);
    setLayoutManager(new DelegatingLayout());
    createChildAreaFigure();
    color1 = new RGB(64,54,54);
}
  
	@Override
	protected void paintFigure(Graphics g) {
		
//		System.out.println("Instrument COntainer" +  getBounds());
		
		IFigure p = this;
		while(!(p instanceof Viewport)) {
			p = p.getParent();
		}
		
		Viewport viewport = (Viewport) p;
		Rectangle copyX = new Rectangle(viewport.getViewLocation(), viewport.getSize());
		
		Dimension prefSize2 = getPreferredSize();
		Rectangle copy = new Rectangle(copyX.x + copyX.width - prefSize2.width, copyX.y + copyX.height - prefSize2.height, prefSize2.width, prefSize2.height);
		
		Color rgb = new Color(Display.getDefault(), color1);
		g.setBackgroundColor(rgb);
		g.fillRectangle(copy);
		rgb.dispose();

	}
	
//	@Override
//	public Dimension getPreferredSize(int wHint, int hHint) {
//		return new Dimension(200, 80);
//	}

	  private void createChildAreaFigure() {
		    childArea = new ChildFigure();
		    childArea.setLayoutManager(new DelegatingLayout());
		    add(childArea);
		  }

	
}
