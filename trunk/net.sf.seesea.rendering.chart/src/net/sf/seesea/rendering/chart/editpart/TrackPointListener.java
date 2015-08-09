package net.sf.seesea.rendering.chart.editpart;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.Track;
import net.sf.seesea.model.util.GeoUtil;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.figures.TrackDataFigure;
import net.sf.seesea.tileservice.ITileProvider;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.Label;
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
		if (notification.getNotifier() instanceof Track
				&& notification.getEventType() == Notification.ADD) {
			MeasuredPosition3D position3d = (MeasuredPosition3D) notification
					.getNewValue();
			addPositionToFigure(position3d);

		}
		// Display.getDefault().asyncExec(new Runnable() {
		//
		// public void run() {
		// getFigure().getParent().repaint();
		// getFigure().repaint();
		//
		// }
		// });
	}

	public void init(List<MeasuredPosition3D> position3d) {
		BundleContext bundleContext = SeeSeaUIActivator.getDefault()
				.getBundle().getBundleContext();
		ServiceReference serviceReference = bundleContext
				.getServiceReference(ITileProvider.class.getName());
		if (serviceReference != null) {
			IMapProjection service = ((ITileProvider) bundleContext
					.getService(serviceReference)).getProjection();
			TrackDataFigure trackDataFigure = (TrackDataFigure) trackEditPart
					.getFigure();

			Label label = ((Label) trackDataFigure.getToolTip());
			NumberFormat format = new DecimalFormat("0.#");
			double totalDistance = 0.0;

			for (MeasuredPosition3D measuredPosition3D : position3d) {
				org.eclipse.swt.graphics.Point project = service.project(
						measuredPosition3D, Integer.MAX_VALUE);
				trackDataFigure
						.addRelativePoint(new Point(project.x, project.y));
			}
			Track track = (Track) trackEditPart.getModel();

//			for (int i = 1; i < track.getMeasuredPosition().size(); i++) {
//				Double distance = GeoUtil.getDistance(track
//						.getMeasuredPosition().get(i - 1).getLatitude()
//						.getDecimalDegree(), track.getMeasuredPosition().get(i)
//						.getLatitude().getDecimalDegree(), track
//						.getMeasuredPosition().get(i - 1).getLongitude()
//						.getDecimalDegree(), track.getMeasuredPosition().get(i)
//						.getLongitude().getDecimalDegree());
//				totalDistance += distance;
//			}
//			label.setText(format.format(totalDistance) + "nm/\n");
			bundleContext.ungetService(serviceReference);
		}
	}

	public void addPositionToFigure(MeasuredPosition3D position3d) {
		BundleContext bundleContext = SeeSeaUIActivator.getDefault()
				.getBundle().getBundleContext();
		ServiceReference serviceReference = bundleContext
				.getServiceReference(ITileProvider.class.getName());
		if (serviceReference != null) {
			IMapProjection service = ((ITileProvider) bundleContext
					.getService(serviceReference)).getProjection();
			org.eclipse.swt.graphics.Point project = service.project(
					position3d, Integer.MAX_VALUE);
			TrackDataFigure trackDataFigure = (TrackDataFigure) trackEditPart
					.getFigure();
			trackDataFigure.addRelativePoint(new Point(project.x, project.y));

			Label label = ((Label) trackDataFigure.getToolTip());
			NumberFormat format = new DecimalFormat("0.#");

			double totalDistance = 0.0;
			Track track = (Track) trackEditPart.getModel();

			for (int i = 1; i < track.getMeasuredPosition().size(); i++) {
				Double distance = GeoUtil.getDistance(track
						.getMeasuredPosition().get(i - 1).getLatitude()
						.getDecimalDegree(), track.getMeasuredPosition().get(i)
						.getLatitude().getDecimalDegree(), track
						.getMeasuredPosition().get(i - 1).getLongitude()
						.getDecimalDegree(), track.getMeasuredPosition().get(i)
						.getLongitude().getDecimalDegree());
				totalDistance += distance;
			}
			label.setText(format.format(totalDistance) + "nm/\n");
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
