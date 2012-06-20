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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomListener;

/**
 * 
 */
public class ScalableZoomableRootEditPart extends ScalableRootEditPart {

	private int _zoom;
	
	private final int minZoom = 0;
	
	private final int maxZoom = 18;

	private final List<ZoomListener> listeners;

	/**
	 * 
	 */
	public ScalableZoomableRootEditPart() {
		listeners = new ArrayList();
	}
	
	/**
	 * Adds the given ZoomListener to this ZoomManager's list of listeners.
	 * 
	 * @param listener
	 *            the ZoomListener to be added
	 */
	public void addZoomListener(ZoomListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Notifies listeners that the zoom level has changed.
	 */
	protected void fireZoomChanged() {
		Iterator iter = listeners.iterator();
		while (iter.hasNext())
			((ZoomListener) iter.next()).zoomChanged(_zoom);
	}
	
	
	/**
	 * returns <code>true</code> if the zoommanager can perform
	 * <code>zoomIn()</code>.
	 * 
	 * @return boolean true if zoomIn can be called
	 */
	public boolean canZoomIn() {
		return getZoom() < getMaxZoom();
	}

	/**
	 * returns <code>true</code> if the zoommanager can perform
	 * <code>zoomOut()</code>.
	 * 
	 * @return boolean true if zoomOut can be called
	 */
	public boolean canZoomOut() {
		return getZoom() > getMinZoom();
	}


	/**
	 * Sets the zoom level to the given value. If the zoom is out of the min-max
	 * range, it will be ignored.
	 * 
	 * @param zoom
	 *            the new zoom level
	 */
	public void setZoom(int zoom) {
		zoom = Math.min(getMaxZoom(), zoom);
		zoom = Math.max(getMinZoom(), zoom);
		if (_zoom != zoom) {
			Point p1 = getViewport().getClientArea().getCenter();
			Point p2 = p1.getCopy();
			Point p = getViewport().getViewLocation();
			int prevZoom = _zoom;
			_zoom = zoom;
			fireZoomChanged();
//			getViewport().getUpdateManager().performUpdate();
//			getViewport().validate();
			getViewport().invalidate();
			if (getViewport().getLayoutManager() != null)
				getViewport().getLayoutManager().invalidate();
			getViewport().getUpdateManager().addInvalidFigure(getViewport());
			RangeModel horizontalRangeModel = getViewport().getHorizontalRangeModel();
			RangeModel verticalRangeModel = getViewport().getVerticalRangeModel();
			if(prevZoom - zoom < 0) {
				horizontalRangeModel.setMaximum(horizontalRangeModel.getMaximum() * 2);
				verticalRangeModel.setMaximum(verticalRangeModel.getMaximum() * 2);
			} else {
				horizontalRangeModel.setMaximum(horizontalRangeModel.getMaximum() / 2 );
				verticalRangeModel.setMaximum(verticalRangeModel.getMaximum() / 2);
			}
//			getFigure().invalidateTree();
//			getViewport().getUpdateManager().performUpdate();
			
//			p2.scale(zoom / prevZoom);
//			Dimension dif = p2.getDifference(p1);
//			p.x += p.x;
//			p.y += p.y;
//			getViewport().setLocation(p);
		}
	}


	/**
	 * @return
	 */
	public Viewport getViewport() {
		return((Viewport) getFigure());
	}

	public int getZoom() {
		return _zoom;
	}

	public int getMinZoom() {
		return minZoom;
	}

	public int getMaxZoom() {
		return maxZoom;
	}

	

}
