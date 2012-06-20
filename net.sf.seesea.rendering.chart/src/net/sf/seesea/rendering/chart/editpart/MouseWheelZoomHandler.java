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
package net.sf.seesea.rendering.chart.editpart;


import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.swt.widgets.Event;

public final class MouseWheelZoomHandler 
	implements MouseWheelHandler
{

/**
 * The Singleton
 */
public static final MouseWheelHandler SINGLETON = new MouseWheelZoomHandler();

private MouseWheelZoomHandler() { }

/**
 * Zooms the given viewer.
 * @see MouseWheelHandler#handleMouseWheel(Event, EditPartViewer)
 */
public void handleMouseWheel(Event event, EditPartViewer viewer) {
	WorldEditPart mapEditPart = (WorldEditPart) viewer.getContents();
	Rectangle rectangle = new Rectangle(event.x, event.y, 0, 0);
	Viewport viewport = ((FigureCanvas) event.widget).getViewport();
	viewport.translateFromParent(rectangle);
	if (event.count > 0) {
		ScalableZoomableRootEditPart scalableZoomableRootEditPart = (ScalableZoomableRootEditPart)mapEditPart.getRoot();
		int oldZoom = scalableZoomableRootEditPart.getZoom(); 
		scalableZoomableRootEditPart.setZoom(oldZoom + 1);
		if(oldZoom != scalableZoomableRootEditPart.getZoom()) {
			int hextent = ((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().getExtent() / 2;
			int hvalue = (rectangle.x << 1) - hextent; 
			((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().setValue(hvalue );
			int vextent = ((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().getExtent() / 2;
			int vvalue = (rectangle.y << 1) - vextent; 
			((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().setValue(vvalue );
//			viewport.invalidateTree();
		}
	}
	else {
		int hextent = ((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().getExtent() / 4;
		int hvalue = ((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().getValue() >> 1;
		((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().setValue(hvalue - hextent);
		int vextent = ((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().getExtent() / 4;
		int vvalue = ((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().getValue() >> 1;
		((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().setValue(vvalue - vextent);
		ScalableZoomableRootEditPart scalableZoomableRootEditPart = (ScalableZoomableRootEditPart)mapEditPart.getRoot();
		scalableZoomableRootEditPart.setZoom(scalableZoomableRootEditPart.getZoom() - 1);
//		viewport.invalidateTree();
	}
	event.doit = false;
}

}
