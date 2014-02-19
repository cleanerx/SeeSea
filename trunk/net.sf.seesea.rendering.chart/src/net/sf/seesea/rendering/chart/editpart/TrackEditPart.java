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

import java.text.NumberFormat;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.Track;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.figures.TrackDataFigure;
import net.sf.seesea.rendering.chart.policies.RouteEditPolicy;
import net.sf.seesea.services.navigation.listener.IDepthListener;
import net.sf.seesea.tileservice.ITileProvider;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ObliqueRouter;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * 
 */
public class TrackEditPart extends TransactionalEditPart implements ConnectionEditPart {

	private ServiceRegistration<IDepthListener> serviceRegistration;


	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		TrackDataFigure trackFigure = new TrackDataFigure();
		trackFigure.setConnectionRouter(new ObliqueRouter());
		return trackFigure;
	}
	
	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
	}
	
	@Override
	public void activate() {
		super.activate();
//		((Track)getModel()).eAdapters().add(this);
//		DepthListener positionListener = new DepthListener();
//		serviceRegistration = SeeSeaUIActivator.getDefault().getBundle().getBundleContext().registerService(IDepthListener.class, positionListener, null);
	}
	
	@Override
	public void deactivate() {
		((Track)getModel()).eAdapters().remove(this);
		super.deactivate();
//		serviceRegistration.unregister();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ROLE,
                new RouteEditPolicy());
	}

	private class DepthListener implements IDepthListener {

		private NumberFormat format = NumberFormat.getNumberInstance();
		
		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#notify(java.lang.Object)
		 */
		public void notify(final Depth sensorData, String source) {
			Display.getDefault().asyncExec(new Runnable() {
				
				public void run() {
					int position = ((TrackDataFigure)getFigure()).getRelativePoints().size() - 2;
					if(position > 1) {
						((TrackDataFigure)getFigure()).addDataPoint(position, format.format(sensorData.getDepth()));
						getFigure().invalidate();
						getFigure().getParent().repaint();
					}
				}
			});
		}

		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#providerEnabled(java.lang.String)
		 */
		public void providerEnabled(String providerID) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#providerDisabled(java.lang.String)
		 */
		public void providerDisabled(String providerID) {
			// TODO Auto-generated method stub
			
		}
	}

	
	
	public Track getTrack() {
		return (Track)getModel();
	}

	public void notifyChanged(Notification notification) {
		if(notification.getNotifier() instanceof Track && notification.getEventType() == Notification.ADD) {
			MeasuredPosition3D position3d = (MeasuredPosition3D) notification.getNewValue();
			BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
			ServiceReference serviceReference = bundleContext.getServiceReference(ITileProvider.class.getName());
			if(serviceReference != null) {
				IMapProjection service = ((ITileProvider)bundleContext.getService(serviceReference)).getProjection();
				org.eclipse.swt.graphics.Point project = service.project(position3d,Integer.MAX_VALUE);
				((TrackDataFigure)getFigure()).addRelativePoint(new Point(project.x, project.y));
				bundleContext.ungetService(serviceReference);
			}
			
		}
//		Display.getDefault().asyncExec(new Runnable() {
//			
//			public void run() {
////				getFigure().getParent().repaint();
////				getFigure().repaint();
//				
//			}
//		});
	}

	public EditPart getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTarget(Notifier newTarget) {
		// TODO Auto-generated method stub
		
	}

	public boolean isAdapterForType(Object type) {
		// TODO Auto-generated method stub
		return false;
	}

	public EditPart getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSource(EditPart source) {
		// TODO Auto-generated method stub
		
	}

	public void setTarget(EditPart target) {
		// TODO Auto-generated method stub
		
	}
	
}
