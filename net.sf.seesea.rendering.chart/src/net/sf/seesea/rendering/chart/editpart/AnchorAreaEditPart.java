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

import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.util.GeoUtil;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * 
 */
public class AnchorAreaEditPart extends TransactionalEditPart {

	private long lastUpdate;

	private IMapProjection mapProjection;
	private ServiceReference<IMapProjection> mapProjectionServiceReference;
//	private Label tooltipLabel;
	private UpdateMapZoomLevelPropertyChangeListener propertyChangeListener;
	
	private DecimalFormat format = new DecimalFormat("0.#");

private Double ellipse;

private boolean alert;


private ServiceRegistration<?> positionTrackerRegistration;

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		Ellipse ellipse = new Ellipse();
//		ellipse.setOpaque(true);
		ellipse.setFill(false);
		return ellipse;
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
//		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
//		mapProjectionServiceReference = bundleContext.getServiceReference(IMapProjection.class);
//		mapProjection = bundleContext.getService(mapProjectionServiceReference);

		propertyChangeListener = new UpdateMapZoomLevelPropertyChangeListener();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().addPropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().addPropertyChangeListener(propertyChangeListener);
		positionTrackerRegistration = SeeSeaUIActivator.getDefault().getBundle().getBundleContext().registerService(IPositionListener.class.getName(), new PositionListener(), null);

		super.activate();
	}
	
	@Override
	public void deactivate() {
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().removePropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().removePropertyChangeListener(propertyChangeListener);

		positionTrackerRegistration.unregister();
//		mapProjection = null;
//		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
//		bundleContext.ungetService(mapProjectionServiceReference);
		super.deactivate();
	}

	
	@Override
	protected synchronized void refreshVisuals() {
		super.refreshVisuals();
		
		AnchorPosition positionReport = (AnchorPosition) getModel();
		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference<IMapProjection> mapProjectionServiceReferenceX = bundleContext.getServiceReference(IMapProjection.class);
		IMapProjection mapProjectionX = bundleContext.getService(mapProjectionServiceReferenceX);
		int zoom = ((ScalableZoomableRootEditPart) getRoot()).getZoom();

		double latDegree = 0;
		double latDegree2 = 0;
		
		if(positionReport.getLatitude().getDecimalDegree() > 0) {
			latDegree = positionReport.getLatitude().getDecimalDegree() + positionReport.getYExtent();
			latDegree2 = positionReport.getLatitude().getDecimalDegree() - positionReport.getYExtent() ;
		} else {
			latDegree = positionReport.getLatitude().getDecimalDegree() - positionReport.getYExtent() ;
			latDegree2 = positionReport.getLatitude().getDecimalDegree() + positionReport.getYExtent();
		}
		
		double latitude2Value = mapProjectionX.latitude2Value(latDegree, (1<< zoom) *  256);
		double latitude2Value2 = mapProjectionX.latitude2Value(latDegree2, (1<< zoom) *  256);
		
		double lonDegree = positionReport.getLongitude().getDecimalDegree() - positionReport.getXExtent() ;
		double lonDegree2 = positionReport.getLongitude().getDecimalDegree() + positionReport.getXExtent() ;
		double longitude2Value = mapProjectionX.longitude2Value(lonDegree, (1<< zoom) *  256) ;
		double longitude2Value2 = mapProjectionX.longitude2Value(lonDegree2, (1<< zoom) *  256) ;
		bundleContext.ungetService(mapProjectionServiceReferenceX);
		
		System.out.println("start" + latDegree + ":" + lonDegree);
		System.out.println("end" + latDegree2 + ":" + lonDegree2);
		System.out.println("start" + latitude2Value + ":" + longitude2Value);
		System.out.println("end" + latitude2Value2 + ":" + longitude2Value2);
		System.out.println("Anc:Lat/Lon" + positionReport.getLatitude().getDecimalDegree() + ":" + positionReport.getLongitude().getDecimalDegree() + "/Ext:" + positionReport.getXExtent() + ":" + positionReport.getYExtent());

		ellipse = new Ellipse2D.Double(positionReport.getLatitude().getDecimalDegree() - positionReport.getYExtent(), positionReport.getLongitude().getDecimalDegree() - positionReport.getXExtent(), positionReport.getYExtent() * 2, positionReport.getXExtent() * 2);
		System.out.println(ellipse.getCenterX() + ":" + ellipse.getCenterY() + "/X:" + ellipse.getMinX() + ":" + ellipse.getMaxX() + "/Y:" + ellipse.getMinY() + ":" + ellipse.getMaxY());

		PrecisionRectangle rect = new PrecisionRectangle(longitude2Value, latitude2Value, Math.abs(longitude2Value2 - longitude2Value) - 1, Math.abs(latitude2Value2 - latitude2Value) - 1);
//		System.out.println(rect);
		getFigure().setBounds(rect);
		
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
	
	private class PositionListener implements IPositionListener {

		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#notify(java.lang.Object)
		 */
		public void notify(final MeasuredPosition3D sensorData, String source) {
			
			if(System.currentTimeMillis() - lastUpdate > 1000 ) {
				synchronized (AnchorAreaEditPart.this) {
//					System.out.println("Lat/Lon" + sensorData.getLatitude() + "/" + sensorData.getLongitude());
//				System.out.println("valid" + sensorData.isValid());
					if(ellipse != null && sensorData.getLatitude() != null && sensorData.getLongitude() != null && !ellipse.contains(sensorData.getLatitude().getDecimalDegree(), sensorData.getLongitude().getDecimalDegree()) && alert == false) {
						Display.getDefault().asyncExec(new Runnable() {
							
							
							public void run() {
								alert = true;
								double distance = GeoUtil.getDistance(ellipse.getCenterX(), sensorData.getLatitude().getDecimalDegree(), ellipse.getCenterY(), sensorData.getLongitude().getDecimalDegree());
								MessageDialog.openError(Display.getDefault().getActiveShell(), "Anchor drinfting", "You have moved " + format.format(distance) + "nm from your deftined center position.");
								alert = false;
								positionTrackerRegistration.unregister();
							}
						});
					}
					lastUpdate = System.currentTimeMillis();
//					System.out.println(ellipse.contains(sensorData.getLatitude().getDecimalDegree(), sensorData.getLongitude().getDecimalDegree()));
				}
//				System.out.println(ellipse.getCenterX() + ":" + ellipse.getCenterY() + "/" + ellipse.getMinX() + ":" + ellipse.getMaxX() + "/" + ellipse.getMinY() + ":" + ellipse.getMaxY());
			}
			
			
		}

		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#providerEnabled(java.lang.String)
		 */
		public void providerEnabled(String providerID) {
			//
		}

		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#providerDisabled(java.lang.String)
		 */
		public void providerDisabled(String providerID) {
			//
			
		}
	}

}
