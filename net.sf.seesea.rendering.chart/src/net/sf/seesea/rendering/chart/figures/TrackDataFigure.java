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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A Figure shown as a connection that draws data values at its side
 * 
 */
public class TrackDataFigure extends PolylineConnection {

	private List<Point> _relativePoints;
	
	private Object syncObject = new Object();

	private Map<Integer, String> _labels;

	private PointList pointList; 
	
	public TrackDataFigure() {
		setToolTip(new Label());
		_relativePoints = new ArrayList<Point>();
		_labels = new HashMap<Integer, String>();
		setOpaque(false);
	}
	
	/**
	 * @param point
	 * @return the inserted index
	 */
	public int addRelativePoint(Point point) {
		synchronized (syncObject) {
			if(_relativePoints.size() > 10) {
				_relativePoints.remove(0);
			}
			_relativePoints.add(point);
			return _relativePoints.size() - 1;
		}
	}
	
	public void removeRelativePoint(Point point) {
		synchronized (syncObject) {
			_relativePoints.remove(point);
		}
	}
	
	public void addDataPoint(int position, String dataValue) {
		synchronized (syncObject) {
		_labels.put(position, dataValue);
		Label dataValueLabel = new Label(dataValue);
		MidpointLocator midpointLocator = new MidpointLocator(this, position);
		midpointLocator.setRelativePosition(PositionConstants.WEST);
		add(dataValueLabel, midpointLocator);
		}
	}

	public void clearDataPoints() {
		synchronized (syncObject) {
			_labels.clear();
			for (Object child : getChildren()) {
				remove((IFigure)child);
			}
		}
	}

	@Override
	protected void outlineShape(Graphics g) {
		synchronized (syncObject) {
			repositionConnectionPoints();
//			super.layout();
		}

		g.setXORMode(false);
		g.drawPolyline(pointList);
	}
	
	@Override
	public Rectangle getBounds() {
		int minx = Integer.MAX_VALUE;
		int maxx = Integer.MIN_VALUE;
		int miny = Integer.MAX_VALUE;
		int maxy = Integer.MIN_VALUE;
		if(getParent() == null || _relativePoints.isEmpty()) {
			bounds = new Rectangle(0, 0, 100, 100);
			return bounds;
		}
		Rectangle worldBounds = getParent().getBounds();
		synchronized (syncObject) {
		for (Point precisionPoint : _relativePoints) {
			int x = (int) Math.round(worldBounds.width * (double)precisionPoint.x / Integer.MAX_VALUE);
			int y = (int) Math.round(worldBounds.height * (double)precisionPoint.y / Integer.MAX_VALUE);
			if(minx > x) {
				minx = x;
			}
			if(maxx < x) {
				maxx = x;
			}
			if(miny > y) {
				miny = y;
			}
			if(maxy < y) {
				maxy = y;
			}
		}
		}
		int width = maxx - minx == 0 ? 1 : maxx - minx;
		int height = maxy - miny == 0 ? 1 : maxy - miny;
		bounds = new Rectangle(minx, miny, width + 10, height + 10);
		for (int i = 0; i < getChildren().size(); i++) {
			IFigure child = (IFigure) getChildren().get(i);
			bounds.union(child.getBounds());
		}
		
		return bounds;
	}

	public List<Point> getRelativePoints() {
		return _relativePoints;
	}

	@Override
	public PointList getPoints() {
		return pointList;
	}
	
	@Override
	protected void paintChildren(Graphics graphics) {
		// a list of bounds that have been painted
		List<Rectangle> paintedFigureBounds = new ArrayList<Rectangle>();
		for (int i = getChildren().size() - 1; i >= 0; i--) {
			IFigure child = (IFigure) getChildren().get(i);
			boolean intersectsPaintedChild = false;
			Rectangle childBounds = child.getBounds();
			for (Rectangle rectangle : paintedFigureBounds) {
				intersectsPaintedChild |= rectangle.intersects(childBounds);
			}
			if (child.isVisible() && !intersectsPaintedChild) {
				// determine clipping areas for child
				Rectangle[] clipping = null;
				if (getClippingStrategy() != null) {
					clipping = getClippingStrategy().getClip(child);
				} else {
					// default clipping behaviour is to clip at bounds
					clipping = new Rectangle[] { child.getBounds() };
				}
				// child may now paint inside the clipping areas
				for (int j = 0; j < clipping.length; j++) {
					if (clipping[j].intersects(graphics
							.getClip(Rectangle.SINGLETON))) {
						graphics.clipRect(clipping[j]);
						child.paint(graphics);
						paintedFigureBounds.add(child.getBounds());
						graphics.restoreState();
					}
				}
			}
		}
	}
	
	@Override
	public void layout() {
		synchronized (syncObject) {
			repositionConnectionPoints();
			super.layout();
		}
	}

	private void repositionConnectionPoints() {
		Dimension preferredSize = getParent().getPreferredSize();
		Rectangle worldBounds = getParent().getBounds().getCopy();
		// if the map is smaller than the rest of the display use the preferred height for locating the figure
		if(worldBounds.width > preferredSize.width || worldBounds.height > preferredSize.height) {
			worldBounds.width = preferredSize.width;
			worldBounds.height = preferredSize.height;
		}
		pointList = new PointList();
		for (Iterator<Point> iterator = _relativePoints.iterator(); iterator.hasNext();) {
			Point point3d = (Point) iterator.next();
			int x = (int) Math.round(worldBounds.width * ((double)point3d.x / Integer.MAX_VALUE));
			int y = (int) Math.round(worldBounds.height * ((double)point3d.y / Integer.MAX_VALUE));
			Point p = new Point(x, y);
			pointList.addPoint(p);
		}
	}
	
}
