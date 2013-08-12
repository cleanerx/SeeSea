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
package net.sf.seesea.rendering.chart.editpart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.seesea.rendering.chart.IViewerGestureListener;
import net.sf.seesea.rendering.chart.tools.DragSelectionTracker;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;

import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.GestureEvent;

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
		listeners = new ArrayList<ZoomListener>();
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
	
	public void removeZoomListener(ZoomListener zoomListener) {
		listeners.remove(zoomListener);
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
	
	public class GestureListener implements IViewerGestureListener {
		
//		@Inject
//		private DrillingHelper drillingHelper; 
		
		private ScalableZoomableRootEditPart host;

		private double initialScale;
		
		private double initialSpacing;
		
		public void initialize(ScalableZoomableRootEditPart host) {
			this.host = host;
		}
		
		public void gesturePerformed(GestureEvent gestureEvent, EditPartViewer viewer) {
			Viewport viewport = (Viewport) host.getFigure();
			Point rectangle = new Point(gestureEvent.x, gestureEvent.y).translate(viewport.getViewLocation());
//			DiagramEditPart diagramEditPart = getContainerDiagramEditPart();
			switch (gestureEvent.detail) {
			case SWT.GESTURE_BEGIN:
				initialScale = host.getZoom();
				break;
			case SWT.GESTURE_MAGNIFY: {
//				System.out.println("i:" + initialScale);
				Double zoom = initialScale + Math.log(gestureEvent.magnification) / Math.log(2);
//				System.out.println("z:" + zoom);
				int oldZoom = getZoom();
//				System.out.println("o:" + oldZoom);
				setZoom(zoom.intValue() );
//				System.out.println(rectangle);
				if(oldZoom != host.getZoom()) {
					// magnifaction
					if(oldZoom < host.getZoom()) {
						// gesture events are relative to the figure frame.
						int hextent = gestureEvent.x; 
						int hvalue = (rectangle.x << (host.getZoom() - oldZoom)) - hextent; 
						((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().setValue(hvalue );
						int vextent = gestureEvent.y;
						int vvalue = (rectangle.y << (host.getZoom() - oldZoom)) - vextent; 
						((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().setValue(vvalue );
//						System.out.println(hvalue + ":" + vvalue);
					} else {
						// shrinking
						// if many zoom changes occur at once, the rectangle needs to be scaled properly
						int hvalue = rectangle.x / (1 << (oldZoom - host.getZoom())) - gestureEvent.x; 
						((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().setValue(hvalue );
						int vvalue = rectangle.y / (1 << (oldZoom - host.getZoom())) - gestureEvent.y; 
						((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().setValue(vvalue );
//						System.out.println(hvalue + ":" + vvalue);
					}
//					int hextent = ((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().getExtent() / 2;
//					int hvalue = (rectangle.x << 1) - hextent;
//					((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().setValue(rectangle.x );
//					((GeospatialGraphicalViewer)viewer).getHorizontalRangeModel().setValue(hvalue );
//					int vextent = ((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().getExtent() / 2;
//					int vvalue = (rectangle.y << 1) - vextent; 
//					((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().setValue(vvalue );
//					((GeospatialGraphicalViewer)viewer).getVerticalRangeModel().setValue(rectangle.y );
//					System.out.println(hvalue + ":" + vvalue);
				}
//				if(zoom < host.getZoomManager().getMinZoom()) {
//					if(diagramEditPart != null) {
//						if(drillingHelper.drillUp(diagramEditPart.getModel())) {
//							consumeEventAndResetView(gestureEvent);
//							return;
//						}
//					}
//				}
//				List<?> currentSelection = host.getViewer().getSelectedEditParts();
//				if(gestureEvent.magnification > 1.5 
//						&& currentSelection.size() == 1 
//						&& currentSelection.get(0) instanceof IInstanceModelEditPart) {
//					if(drillingHelper.drillDown(((IInstanceModelEditPart) currentSelection.get(0)).getModel())) {
//						consumeEventAndResetView(gestureEvent);
//						return; 
//					}
//				}
//				
//				Point viewLocation = viewport.getViewLocation();
//				Point mouseLocation = new Point(gestureEvent.x, gestureEvent.y).translate(viewLocation);
//				double prevZoom = host.getZoomManager().getZoom();
//				host.getZoomManager().setZoom(zoom);
//				Point mouseLocationAfterZoom = mouseLocation.getCopy().scale(zoom / prevZoom);
//				Dimension mouseDelta = mouseLocationAfterZoom.getDifference(mouseLocation);
//				viewLocation.translate(mouseDelta);
//				viewport.setViewLocation(viewLocation);
//				diagramEditPart.getFigure().invalidate();
				break;
			}
			case SWT.GESTURE_ROTATE:
//				if(diagramEditPart != null) {
//					diagramEditPart.setSpacing(initialSpacing + gestureEvent.rotation * 0.1);
//					gestureEvent.doit = false;
//				}
				break;
			case SWT.GESTURE_PAN:
				// only fired when not consumed by some parent scrollable as a FigureCanvas
//				Point viewLocation = viewport.getViewLocation();
//				viewLocation.translate(gestureEvent.xDirection, gestureEvent.yDirection);
//				viewport.setViewLocation(viewLocation);
//				gestureEvent.doit = false;
				break;
			default:
				gestureEvent.doit = true;
			}
		}
		
		protected void consumeEventAndResetView(GestureEvent gestureEvent) {
			initialScale = 0.;
			host.getZoomManager().setZoom(1.);			
			gestureEvent.doit = false;
		}

//		protected DiagramEditPart getContainerDiagramEditPart() {
//			return Iterables.getFirst(Iterables.filter(host.getChildren(), DiagramEditPart.class), null);
//		}
	}

	@Override
	public void activate() {
		super.activate();
		GestureListener gestureListener = new GestureListener();
		gestureListener.initialize(this);
		getViewer().setProperty(IViewerGestureListener.KEY, gestureListener);
	}
	
	@Override
	public void deactivate() {
		getViewer().setProperty(IViewerGestureListener.KEY, null);
		super.deactivate();
	}

	@Override
	public DragTracker getDragTracker(Request req) { 
		return new DragSelectionTracker();
	}
	@Override
	public boolean isSelectable() {
		return false;
	}

}
