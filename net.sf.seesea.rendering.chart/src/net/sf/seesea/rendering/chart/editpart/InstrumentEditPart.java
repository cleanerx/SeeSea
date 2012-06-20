/**
 * 
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.rendering.chart.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.model.core.data.Instruments;
import net.sf.seesea.rendering.chart.figures.ClientAreaLocator;
import net.sf.seesea.rendering.chart.figures.InstrumentContainerFigure;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;

/**
 * 
 */
public class InstrumentEditPart extends TransactionalEditPart {

	private UpdateMapZoomLevelPropertyChangeListener propertyChangeListener;
	
	
	
	@Override
	public void activate() {
		super.activate();
		propertyChangeListener = new UpdateMapZoomLevelPropertyChangeListener();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().addPropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().addPropertyChangeListener(propertyChangeListener);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().removePropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().removePropertyChangeListener(propertyChangeListener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		InstrumentContainerFigure instrumentContainerFigure = new InstrumentContainerFigure();
		return instrumentContainerFigure;
//		PositionInstrumentFigure positionInstrumentFigure = new PositionInstrumentFigure();
//		int width = 80;
//		positionInstrumentFigure.setBounds(new Rectangle(0,0,width, new Double(width / 2.5).intValue()));
//		return positionInstrumentFigure;
//		WindFigure windFigure = new WindFigure();
//		return windFigure;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
//		Wind model2 = getModel();
//		WindFigure windFigure = (WindFigure) getFigure();
//		windFigure.setWindDirection(-160.0);
//		windFigure.setWindSpeedBeaufort(7.0);
//		windFigure.setWindSpeed(37.2);
		
//		getFigure().get
//		System.out.println(((PositionInstrumentFigure) getFigure()).getParent().getParent().getParent().getClientArea());

		
//		PositionInstrumentFigure positionInstrumentFigure = (PositionInstrumentFigure) getFigure();
//		positionInstrumentFigure.setPositionLatitude("54° 45.2' N");
//		positionInstrumentFigure.setPositionLongitude("008° 12.2' E");
//		
		Viewport viewport = ((ScalableZoomableRootEditPart) getRoot()).getViewport();
		Rectangle rectangle = new Rectangle(viewport.getViewLocation(), viewport.getSize());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new ClientAreaLocator(rectangle, 1.0, 0.0));
		getFigure().invalidate();
	}

	@Override
	protected List getModelChildren() {
		List<EObject> x = new ArrayList<EObject>();
		Instruments instruments = (Instruments) getModel();
		x.add(instruments.getPosition());
		return x;
	}
	
	private class UpdateMapZoomLevelPropertyChangeListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			refreshVisuals();
		}
		
	}


}
