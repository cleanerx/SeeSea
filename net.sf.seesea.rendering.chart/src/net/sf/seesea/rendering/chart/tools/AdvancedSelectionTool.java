package net.sf.seesea.rendering.chart.tools;


import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.swt.events.MouseEvent;

public class AdvancedSelectionTool extends SelectionTool {

	protected void updateTargetRequest() {
		SelectionAndMoveRequest request = (SelectionAndMoveRequest) getTargetRequest();
		Dimension delta = getDragMoveDelta();
		request.setMoveDelta(new Point(delta.width, delta.height));
//		request.setModifiers(getCurrentInput().getModifiers());
		request.setType(getCommandName());
		request.setLocation(getLocation());
		updateHoverRequest();
	}
	/**
	 * Creates a {@link SelectionRequest} for the target request.
	 * 
	 * @see TargetingTool#createTargetRequest()
	 */
	protected Request createTargetRequest() {
		SelectionAndMoveRequest request = new SelectionAndMoveRequest();
		request.setType(getCommandName());
		return request;
	}

	/**
	 * Forwards the mouse up event to the drag tracker, if one exists.
	 * 
	 * @see org.eclipse.gef.Tool#mouseUp(MouseEvent,
	 *      org.eclipse.gef.EditPartViewer)
	 */
	public void mouseUp(MouseEvent e, EditPartViewer viewer) {
//		getTargetEditPart();
//		getCommand();
		if (getDragTracker() != null)
			getDragTracker().mouseUp(e, viewer);
		super.mouseUp(e, viewer);
	}
	@Override
	protected boolean handleDragInProgress() {
		updateTargetUnderMouse();
		return super.handleDragInProgress();
	}
	
	@Override
	protected boolean handleButtonDown(int button) {
		// TODO Auto-generated method stub
		return super.handleButtonDown(button);
	}
}
