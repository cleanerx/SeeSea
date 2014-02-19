package net.sf.seesea.navigation.ui.figures;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public class TextDoubleLinedInstrumentFigure extends
		DoubleLinedInstrumentFigure {

	private String sateliteAugmentation;

	public String getSateliteAugmentation() {
		return sateliteAugmentation;
	}

	public void setSateliteAugmentation(String sateliteAugmentation) {
		this.sateliteAugmentation = sateliteAugmentation;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		Rectangle copy = paintDoubleLine(g);
		
		if(sateliteAugmentation != null && !sateliteAugmentation.isEmpty()) {
			Font font2 = new Font(Display.getDefault(),"Times", 6, SWT.NONE);  //$NON-NLS-1$
			g.setFont(font2);
			Dimension textDimensionLongitude = FigureUtilities.getTextExtents(sateliteAugmentation, font2);
			g.drawText(sateliteAugmentation, copy.x +  copy.width - 2 - textDimensionLongitude.width , copy.y + copy.height / 2 - textDimensionLongitude.height + 4);
			font2.dispose();
		}

	}

}
