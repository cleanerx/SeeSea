package net.sf.seesea.rendering.chart.editpart;

import net.sf.seesea.rendering.chart.commands.CreateRouteCommand;
import net.sf.seesea.rendering.chart.tools.LocationSequenceRequest;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;

public class EditRoutePolicy extends AbstractEditPolicy {

	@Override
	public EditPart getTargetEditPart(Request request) {
		if(request instanceof LocationSequenceRequest) {
			return getHost();
		}
		return super.getTargetEditPart(request);
	}
	
	@Override
	public Command getCommand(Request request) {
		if(request instanceof LocationSequenceRequest) {
			WorldEditPart editPart = (WorldEditPart) getHost();
//			TransactionalEditingDomain editingDomain = ((TransactionalEditPart)getHost()).getEditingDomain();
			EditingDomain editingDomain2 = ((GeospatialGraphicalViewer) editPart.getViewer()).getEditingDomainServiceTracker();
			CreateRouteCommand routeCommand = new CreateRouteCommand((TransactionalEditingDomain) editingDomain2, null, editPart.getWorld(), ((LocationSequenceRequest)request).getLocations());
//			return routeCommand;
			return new ICommandProxy(routeCommand);
//			return super.getCommand(request);
		}
		return null;
	}

}
