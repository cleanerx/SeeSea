package net.sf.seesea.rendering.chart.tools;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.requests.SelectionRequest;

public class SelectionAndMoveRequest extends SelectionRequest {

	private Point moveDelta;

	/**
	 * Returns a Point representing the distance the EditPart has moved.
	 * 
	 * @return A Point representing the distance the EditPart has moved.
	 */
	public Point getMoveDelta() {
		return moveDelta;
	}
	
	/**
	 * Sets the move delta.
	 * 
	 * @param p
	 *            The Point representing the move delta
	 */
	public void setMoveDelta(Point p) {
		moveDelta = p;
	}
}
