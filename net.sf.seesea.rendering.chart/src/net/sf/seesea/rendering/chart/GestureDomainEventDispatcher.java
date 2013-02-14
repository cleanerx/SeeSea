package net.sf.seesea.rendering.chart;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.parts.DomainEventDispatcher;
import org.eclipse.swt.events.GestureEvent;

public class GestureDomainEventDispatcher extends DomainEventDispatcher {

	public GestureDomainEventDispatcher(EditDomain d, EditPartViewer v) {
		super(d, v);
	}

	public void dispatchGesture(GestureEvent event) {
		if (okToDispatch() && domain instanceof IViewerGestureListener)
			((IViewerGestureListener) domain).gesturePerformed(event, viewer);
	}

	// super.okToDispatch is private
	private boolean okToDispatch() {
		return domain != null;
	}
}
