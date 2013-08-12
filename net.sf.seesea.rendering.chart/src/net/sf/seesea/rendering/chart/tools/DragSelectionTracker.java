package net.sf.seesea.rendering.chart.tools;

import org.eclipse.gef.tools.MarqueeDragTracker;

public class DragSelectionTracker extends MarqueeDragTracker {
	
	protected boolean handleDragInProgress() {
		return true;
	}
	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonUp(int)
	 */
	protected boolean handleButtonUp(int button) {
		if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) {
//			eraseTargetFeedback();
//			eraseMarqueeFeedback();
//			performMarqueeSelect();
		}
		handleFinished();
		return true;
	}

}
