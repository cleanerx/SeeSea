package net.sf.seesea.rendering.chart.editpart;

import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.Track;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.figures.TrackDataFigure;
import net.sf.seesea.tileservice.ITileProvider;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class TrackPointListener implements Adapter {

	private final TrackEditPart trackEditPart;

	public TrackPointListener(TrackEditPart trackEditPart) {
		this.trackEditPart = trackEditPart;
	}

	public void notifyChanged(Notification notification) {
		if(notification.getNotifier() instanceof Track && notification.getEventType() == Notification.ADD) {
			MeasuredPosition3D position3d = (MeasuredPosition3D) notification.getNewValue();
			addPositionToFigure(position3d);
			
		}
//		Display.getDefault().asyncExec(new Runnable() {
//			
//			public void run() {
//				getFigure().getParent().repaint();
//				getFigure().repaint();
//				
//			}
//		});
	}

	public void addPositionToFigure(MeasuredPosition3D position3d) {
		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference serviceReference = bundleContext.getServiceReference(ITileProvider.class.getName());
		if(serviceReference != null) {
			IMapProjection service = ((ITileProvider)bundleContext.getService(serviceReference)).getProjection();
			org.eclipse.swt.graphics.Point project = service.project(position3d,Integer.MAX_VALUE);
			((TrackDataFigure)trackEditPart.getFigure()).addRelativePoint(new Point(project.x, project.y));
			bundleContext.ungetService(serviceReference);
		}
	}

	public Notifier getTarget() {
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

}
