package net.sf.seesea.rendering.chart.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class PositionFigure extends Figure {

	@Override
	protected void paintFigure(Graphics graphics) {
		graphics.setForegroundColor(ColorConstants.black);
		graphics.setBackgroundColor(ColorConstants.black);
		graphics.setAntialias(SWT.ON);
		setOpaque(false);
		graphics.setLineWidth(1);
		Rectangle localBounds = getBounds().getCopy();
		graphics.drawLine(localBounds.getCenter().x, localBounds.getTop().y, localBounds.getCenter().x, localBounds.getBottom().y);
		graphics.drawLine(localBounds.getLeft().x, localBounds.getCenter().y, localBounds.getRight().x, localBounds.getCenter().y);
		localBounds.height -= 1;
		localBounds.width -= 1;
		graphics.drawOval(localBounds);
	}
	
}
