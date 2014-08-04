package net.sf.seesea.rendering.chart.editpart;

import java.util.List;

import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.commands.CreateRouteCommand;
import net.sf.seesea.rendering.chart.figures.TrackDataFigure;
import net.sf.seesea.rendering.chart.tools.LocationSequenceRequest;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.tileservice.ITileProvider;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ObliqueRouter;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class EditRoutePolicy extends AbstractEditPolicy {

	private IFigure feedbackFigure;

	@Override
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof LocationSequenceRequest) {
			return getHost();
		}
		return super.getTargetEditPart(request);
	}

	@Override
	public Command getCommand(Request request) {
		if (request instanceof LocationSequenceRequest) {
			WorldEditPart editPart = (WorldEditPart) getHost();
			// TransactionalEditingDomain editingDomain =
			// ((TransactionalEditPart)getHost()).getEditingDomain();
			EditingDomain editingDomain2 = ((GeospatialGraphicalViewer) editPart
					.getViewer()).getEditingDomainServiceTracker();
			CreateRouteCommand routeCommand = new CreateRouteCommand(
					(TransactionalEditingDomain) editingDomain2, null,
					editPart.getWorld(),
					((LocationSequenceRequest) request).getLocations());
			// return routeCommand;
			return new ICommandProxy(routeCommand);
			// return super.getCommand(request);
		}
		return null;
	}

	@Override
	public void showSourceFeedback(Request request) {
		System.out.println("YY");
		super.showSourceFeedback(request);
	}
	
	@Override
	public void showTargetFeedback(Request request) {
		// super.showTargetFeedback(request);
		System.out.println("XX");
		if (request instanceof LocationSequenceRequest) {
			LayerManager lm = (LayerManager) getHost().getViewer()
					.getEditPartRegistry().get(LayerManager.ID);
			if (lm == null)
				return;
			if (feedbackFigure == null) {
				feedbackFigure = new TrackDataFigure();
				((TrackDataFigure)feedbackFigure).setConnectionRouter(new ObliqueRouter());
//				feedbackFigure.setBounds(new Rectangle(0, 0, 100, 100));
			}
			LocationSequenceRequest locationSequenceRequest = (LocationSequenceRequest) request;
			List<GeoPosition> locations = locationSequenceRequest
					.getLocations();
			if (locations != null) {
				BundleContext bundleContext = SeeSeaUIActivator.getDefault()
						.getBundle().getBundleContext();
				ServiceReference serviceReference = bundleContext
						.getServiceReference(ITileProvider.class.getName());
				if (serviceReference != null) {
					IMapProjection service = ((ITileProvider) bundleContext
							.getService(serviceReference)).getProjection();
					System.out.println(locations);
					for (GeoPosition geoPosition : locations) {
						org.eclipse.swt.graphics.Point project = service
								.project(geoPosition, Integer.MAX_VALUE);
						((TrackDataFigure)feedbackFigure).addPoint(new Point(project.x, project.y));
						continue;
					}
					System.out.println(locationSequenceRequest.getLocation());
					if(locationSequenceRequest.getLocation() != null) {
						((TrackDataFigure)feedbackFigure).addPoint(locationSequenceRequest.getLocation());
						feedbackFigure.invalidate();
					}
				}
			}

			lm.getLayer(LayerConstants.FEEDBACK_LAYER).add(feedbackFigure);
		}
	}

	@Override
	public void eraseTargetFeedback(Request request) {
		if (request instanceof LocationSequenceRequest) {
			LayerManager lm = (LayerManager) getHost().getViewer()
					.getEditPartRegistry().get(LayerManager.ID);
			if (lm == null)
				return;
			lm.getLayer(LayerConstants.FEEDBACK_LAYER).remove(feedbackFigure);
			feedbackFigure = null;
		}
	}

}
