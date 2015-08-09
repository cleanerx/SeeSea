package net.sf.seesea.rendering.chart.editpolicies;

import net.sf.seesea.rendering.chart.editpart.WorldEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.SelectionRequest;

public class CursorPostionEditPolicy extends AbstractEditPolicy implements
		EditPolicy {

	@Override
	public void showTargetFeedback(Request request) {
//		System.out.println("targetFeedback" + request);
		if(request instanceof SelectionRequest) {
			SelectionRequest selectionRequest = (SelectionRequest) request;
			Point location = selectionRequest.getLocation();
			((WorldEditPart)getHost()).setCursorPosition(location);
		}
		super.showSourceFeedback(request);
	}
	
}
