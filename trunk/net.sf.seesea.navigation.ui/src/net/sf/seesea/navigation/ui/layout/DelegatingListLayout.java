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
package net.sf.seesea.navigation.ui.layout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Places the _child figures in a row or a column. Supports growing of the children.
 * 
 */
public class DelegatingListLayout extends DelegatingLayout {

  protected boolean _horizontal = false;

  protected int _spacing = 0;

  public DelegatingListLayout(boolean horizontal, int spacing) {
    _horizontal = horizontal;
    _spacing = spacing;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.draw2d.AbstractLayout#calculatePreferredSize(org.eclipse.draw2d.IFigure, int,
   *      int)
   */
  @Override
  protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {

    int width = 0;
    int height = 0;

    List<?> children = container.getChildren();

    for (Iterator<?> iter = children.iterator(); iter.hasNext();) {
      IFigure child = (IFigure) iter.next();
      if (child.isVisible()) {
        Dimension childSize = getChildSize(child);
        if (_horizontal) {
          width += childSize.width;
          if (iter.hasNext()) {
            width += _spacing;
          }
          height = Math.max(height, childSize.height);
        } else {
          height += childSize.height;
          if (iter.hasNext()) {
            height += _spacing;
          }
          width = Math.max(width, childSize.width);
        }
      }
    }
    return new Dimension(Math.max(wHint, width), Math.max(hHint, height));
  }

  protected Dimension getChildSize(IFigure child) {
    return child.getPreferredSize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
   */
  @Override
public void layout(IFigure container) {
    List<?> children = container.getChildren();
    Rectangle clientArea = container.getClientArea();
    int x = clientArea.x ;
    int y = clientArea.y;
    int width = clientArea.width ;
    int height = clientArea.height;

    int sum = 0;

    List<Dimension> childSizes = new ArrayList<Dimension>();

    int maxExtent = 0;
    
    // first evaluation, add heights/widths of the children
    for (Iterator<?> iter = children.iterator(); iter.hasNext();) {
      IFigure child = (IFigure) iter.next();
      
      if (child.isVisible()) {
        Dimension childSize;
        if (_horizontal) {
          childSize = child.getPreferredSize(0, height);
          childSizes.add(childSize);
          maxExtent = Math.max(maxExtent, childSize.height);
          sum += childSize.width;
        } else {
          childSize = child.getPreferredSize(width, 0);
          childSizes.add(childSize);
          maxExtent = Math.max(maxExtent, childSize.width);
          sum += childSize.height;
        }
      }
    }

    // layout
    Iterator<?> sizeIter = childSizes.iterator();
    boolean split = false;
    for (Iterator<?> iter = children.iterator(); iter.hasNext();) {
      IFigure child = (IFigure) iter.next();
      
      if (child.isVisible()) {
          Object constraint = getConstraint(child);
          Double sizeDivisor = 1.0;
          if(constraint instanceof Double) {
          	sizeDivisor = (Double) constraint;
          }
          if(sizeDivisor != 1.0) {
        	  split = split ^ true;
          } else {
        	  split = false;
          }
        Dimension childSize = (Dimension) sizeIter.next();
        if (_horizontal) {
          int splitHeight = ((Double)(maxExtent / sizeDivisor)).intValue();
		child.setBounds(new Rectangle(x, y, childSize.width, splitHeight));
          x += childSize.width;
          x += _spacing;
        } else {
          int splitWidth = ((Double)(maxExtent / sizeDivisor)).intValue();
          if(split) {
              child.setBounds(new Rectangle(x , y, splitWidth, childSize.height));
          } else if(sizeDivisor != 1.0) {
        	  child.setBounds(new Rectangle(x + splitWidth, y, splitWidth, childSize.height));
              y += childSize.height;
              y += _spacing;
          } else {
        	  child.setBounds(new Rectangle(x , y, splitWidth, childSize.height));
              y += childSize.height;
              y += _spacing;
          }
        }
      }
    }
    if(_horizontal) {
        container.setBounds(new Rectangle(clientArea.x,clientArea.y, x , maxExtent));
    } else {
    	container.setBounds(new Rectangle(clientArea.x,clientArea.y, maxExtent, y));
    }
  }
  
  
}
