/**
 * 
Copyright (c) 2010, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.navigation.ui.figures;

import net.sf.seesea.navigation.ui.layout.DelegatingListLayout;
import net.sf.seesea.navigation.ui.layout.FormAttachment;
import net.sf.seesea.navigation.ui.layout.FormData;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
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

  private static Dimension minSize = new Dimension(50, 30);

  /**
 * 
 */
public InstrumentContainerFigure() {
    setMinimumSize(minSize);
    setPreferredSize(minSize);
    setForegroundColor(ColorConstants.white);
    setBounds(new Rectangle(0,0,280,700));
//    GridLayout gridLayout = new GridLayout(2, true);
//    gridLayout.verticalSpacing = 5;
    
    ToolbarLayout layout = new ToolbarLayout(true);
//    layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
    layout.setStretchMinorAxis(true);
    layout.setSpacing(3);
//    FlowLayout layout = new FlowLayout();
//    layout.
//    DelegatingListLayout layout = new DelegatingListLayout(false, 5);
//    FormLayout layout = new FormLayout();

    setLayoutManager(layout);
    createChildAreaFigure();
}
  
	@Override
	protected void paintFigure(Graphics g) {
		
//		System.out.println("Instrument COntainer" +  getBounds());
		
//		IFigure p = this;
//		while(!(p instanceof Viewport)) {
//			p = p.getParent();
//		}
//		
//		Viewport viewport = (Viewport) p;
//		Rectangle copyX = new Rectangle(viewport.getViewLocation(), viewport.getSize());
//		
//		Dimension prefSize2 = getPreferredSize();
//		Rectangle copy = new Rectangle(copyX.x + copyX.width - prefSize2.width, copyX.y + copyX.height - prefSize2.height, prefSize2.width, prefSize2.height);
		
		Rectangle copy = getBounds().getCopy();
//		Color rgb = new Color(Display.getDefault(), new RGB(130,173,231));
//		Color rgb2 = new Color(Display.getDefault(), new RGB(98,133,240));
		Color rgb2 = new Color(Display.getDefault(), new RGB(211,211,211));
		Color rgb = new Color(Display.getDefault(), new RGB(255,255,255));
		g.setForegroundColor(rgb);
		g.setBackgroundColor(rgb2);
		g.fillGradient(copy, true);
		rgb.dispose();
		rgb2.dispose();

	}
	
//	@Override
//	public Dimension getPreferredSize(int wHint, int hHint) {
//		return new Dimension(200, 80);
//	}

	  private void createChildAreaFigure() {
		    childArea = new ChildFigure();
		    DelegatingListLayout layout2 = new DelegatingListLayout(false, 5);
		    childArea.setLayoutManager(layout2);
		    FormData layout = new FormData();
		    layout.top = new FormAttachment(0, 20);
		    layout.bottom = new FormAttachment(1, 10);
		    layout.left = new FormAttachment(0, 10);
		    layout.right = new FormAttachment(1, 10);
		    add(childArea, layout);
		  }

	public IFigure getChildArea() {
		return childArea;
	}

//	@Override
//	public Rectangle getBounds() {
//		if (bounds == null) {
//			super.getBounds();
//			for (int i = 0; i < getChildren().size(); i++) {
//				IFigure child = (IFigure) getChildren().get(i);
//				bounds.union(child.getBounds());
//			}
//		}
//		return bounds;
//	}
	  
	 @Override
	  public Dimension getPreferredSize(int wHint, int hHint) {
	    Dimension layoutPrefSize = getLayoutManager().getPreferredSize(this, wHint, hHint).getCopy();
	    return layoutPrefSize.union(prefSize);
	  }
	  
	  @Override
	  public Dimension getMinimumSize(int wHint, int hHint) {
	    Dimension layoutPrefSize = getLayoutManager().getMinimumSize(this, wHint, hHint).getCopy();
	    return layoutPrefSize.union(minSize);
	  }

}
