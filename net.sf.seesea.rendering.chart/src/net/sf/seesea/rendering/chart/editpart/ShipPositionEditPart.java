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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.figures.ShipFigure;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.services.navigation.listener.IHeadingListener;
import net.sf.seesea.tileservice.ITileProvider;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * 
 */
public class ShipPositionEditPart extends TransactionalEditPart  implements Adapter, IHeadingListener {

	private ServiceRegistration<IHeadingListener> serviceRegistration;
	private net.sf.seesea.rendering.chart.editpart.ShipPositionEditPart.UpdateMapZoomLevelPropertyChangeListener propertyChangeListener;

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		return new ShipFigure();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void activate() {
		super.activate();
		propertyChangeListener = new UpdateMapZoomLevelPropertyChangeListener();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().addPropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().addPropertyChangeListener(propertyChangeListener);

		((GeoPosition) getModel()).eAdapters().add(this);
		serviceRegistration = SeeSeaUIActivator.getDefault().getBundle().getBundleContext().registerService(IHeadingListener.class, this, null);
	}

	@Override
	public void deactivate() {
		((GeoPosition) getModel()).eAdapters().remove(this);
		serviceRegistration.unregister();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().removePropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().removePropertyChangeListener(propertyChangeListener);

		super.deactivate();
	}

//	/** calls a layout */
	@Override
	protected void refreshVisuals() {
		GeoPosition geoPosition = (GeoPosition) getModel();
		if(geoPosition != null && System.currentTimeMillis() - lastUpdate > 1000) {
			BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
			ServiceReference<ITileProvider> serviceReference = bundleContext.getServiceReference(ITileProvider.class);
			if(serviceReference != null) {
				ITileProvider tileProvider =  (ITileProvider) bundleContext.getService(serviceReference);
				org.eclipse.swt.graphics.Point tileSize = tileProvider.getTileSize();
				int zoom = ((ScalableZoomableRootEditPart) getRoot()).getZoom();
				org.eclipse.swt.graphics.Point point = tileProvider.getProjection().project(geoPosition, (1<< zoom) *  tileSize.x);
				getFigure().setBounds(new PrecisionRectangle(point.x - 10, point.y - 10, 20, 20));
//				refreshVisuals();
			}
			lastUpdate = System.currentTimeMillis();
		}
	}
	
	public void notifyChanged(Notification notification) {
		// listen for zoom and position changes
		// (notification.getNotifier() instanceof World && notification.getFeatureID(World.class) == GeoPackage.NAVAREA__ZOOM_LEVEL) 
		if((notification.getNotifier() instanceof GeoPosition && notification.getFeatureID(GeoPosition.class) == GeoPackage.GEO_POSITION3_D__LATITUDE) ) { //|| (notification.getNotifier() instanceof GeoPosition && notification.getFeatureID(GeoPosition.class) == GeoPackage.GEO_POSITION3_D__LONGITUDE)) {
			Display.getDefault().asyncExec(new Runnable() {
				
				public void run() {
					refreshVisuals();
				}
			});
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		//
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return false;
	}

	public void providerEnabled(String providerID) {
		// nothing to do
	}

	public void notify(Heading sensorData, String source) {
		if(HeadingType.COG.equals(sensorData.getHeadingType())) {
			((ShipFigure)getFigure()).setCOGOrientation(sensorData.getDegrees());
		} else if(HeadingType.MAGNETIC.equals(sensorData.getHeadingType())) {
			((ShipFigure)getFigure()).setCompassOrientation(sensorData.getDegrees());
		}
	}

	public void providerDisabled(String providerID) {
		// nothing to do
	}
	
	/**
	 * repaints the figure to draw its position
	 */
	private class UpdateMapZoomLevelPropertyChangeListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			if("minimum".equals(evt.getPropertyName()) || "maximum".equals(evt.getPropertyName())) {  //$NON-NLS-1$//$NON-NLS-2$
				refreshVisuals();
			}
		}
	}
	
	private long lastUpdate;
	
}
