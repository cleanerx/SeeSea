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
package net.sf.seesea.rendering.chart.view;

import net.sf.seesea.rendering.chart.GestureDomainEventDispatcher;
import net.sf.seesea.rendering.chart.GestureLightweightSystem;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.tileservice.ITileProvider;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.ui.parts.DomainEventDispatcher;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.osgi.util.tracker.ServiceTracker;

public class GeospatialGraphicalViewer extends ScrollingGraphicalViewer {
	
	private final ServiceTracker tileProviderServiceTracker;
	private final ServiceTracker editingDomainServiceTracker;

	private GestureDomainEventDispatcher eventDispatcher;


	/**
	 * 
	 */
	public GeospatialGraphicalViewer() {
		tileProviderServiceTracker = new ServiceTracker(SeeSeaUIActivator.getDefault().getBundle().getBundleContext(), ITileProvider.class.getName(), null);
		tileProviderServiceTracker.open();
		editingDomainServiceTracker = new ServiceTracker(SeeSeaUIActivator.getDefault().getBundle().getBundleContext(), IEditingDomainProvider.class.getName(), null);
		editingDomainServiceTracker.open();
	}
	
	

	public ITileProvider getTileProviderServiceTracker() {
		return (ITileProvider)tileProviderServiceTracker.getService();
	}


	public EditingDomain getEditingDomainServiceTracker() {
		return ((IEditingDomainProvider)editingDomainServiceTracker.getService()).getEditingDomain();
	}


	public Point getScrollingPosition() {
		int valueY = getFigureCanvas().getViewport().getVerticalRangeModel().getValue();
		int valueX = getFigureCanvas().getViewport().getHorizontalRangeModel().getValue();
		return new Point(valueX, valueY);
	}
	
	public RangeModel getHorizontalRangeModel() {
		return getFigureCanvas().getViewport().getHorizontalRangeModel();
	}

	public RangeModel getVerticalRangeModel() {
		return getFigureCanvas().getViewport().getVerticalRangeModel();
	}
	
	public void setScrollingPosition(Point point) {
		getFigureCanvas().scrollTo(point.x, point.y);
	}
	
	public Point getCenterPosition() {
		Point centerPosition = getScrollingPosition();
		centerPosition.x += (getHorizontalRangeModel().getExtent() >> 1);
		centerPosition.y += (getVerticalRangeModel().getExtent() >> 1);
		return centerPosition;
	}

	
	@Override
	protected LightweightSystem createLightweightSystem() {
		return new GestureLightweightSystem();
	}

	@Override
	public void setEditDomain(EditDomain domain) {
		super.setEditDomain(domain);
		getLightweightSystem().setEventDispatcher(eventDispatcher = new GestureDomainEventDispatcher(domain, this));
	}

	@Override
	protected DomainEventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}
}
