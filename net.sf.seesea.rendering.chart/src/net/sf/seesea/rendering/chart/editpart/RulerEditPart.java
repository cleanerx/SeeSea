/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
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
import java.util.Collections;
import java.util.List;

import net.sf.seesea.rendering.chart.figures.IMapZoomableDependentFigure;
import net.sf.seesea.rendering.chart.figures.LatitudeRulerFigure;
import net.sf.seesea.rendering.chart.figures.LongitudeRulerFigure;
import net.sf.seesea.rendering.chart.rulers.RulerModel;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.swt.accessibility.AccessibleEvent;

public class RulerEditPart extends AbstractGraphicalEditPart
{

	protected GraphicalViewer diagramViewer;
	private AccessibleEditPart accPart;
	private RulerProvider rulerProvider;
	
	private UpdateMapZoomLevelPropertyChangeListener propertyChangeListener;


	public RulerEditPart(Object model) {
		setModel(model);
	}

	/* (non-Javadoc)
	* @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	*/
	@Override
	protected void createEditPolicies() {
//		installEditPolicy(EditPolicy.CONTAINER_ROLE, );
//		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new RulerSelectionPolicy());
	}

	/* (non-Javadoc)
	* @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	*/
	@Override
	protected IFigure createFigure() {
		if(!isHorizontal()) {
			return new LatitudeRulerFigure(256);
		} 
		else {
			return new LongitudeRulerFigure(256);
		}
	}

	@Override
	protected AccessibleEditPart getAccessibleEditPart() {
		if (accPart == null)
			accPart = new AccessibleGraphicalEditPart() {
				@Override
				public void getName(AccessibleEvent e) {
					e.result = isHorizontal() ? GEFMessages.Ruler_Horizontal_Label
											  : GEFMessages.Ruler_Vertical_Label;
				}
				@Override
				public void getDescription(AccessibleEvent e) {
					e.result = GEFMessages.Ruler_Desc;
				}
			};
		return accPart;
	}

	/**
	 * Returns the GraphicalViewer associated with the diagram.
	 * 
	 * @return graphical viewer associated with the diagram.
	 */
	protected GraphicalViewer getDiagramViewer() {
		return diagramViewer;
	}

	public IFigure getGuideLayer() {
		LayerManager lm = (LayerManager)diagramViewer
			.getEditPartRegistry()
			.get(LayerManager.ID);
		if (lm != null)
			return lm.getLayer(LayerConstants.GUIDE_LAYER);
		return null;
	}

	/* (non-Javadoc)
	* @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	*/
	@Override
	protected List getModelChildren() {
		return Collections.EMPTY_LIST;
	}

//	protected RulerFigure getRulerFigure() {
//		if(getFigure() instanceof RulerFigure) {
//			return (RulerFigure)getFigure();
//		}
//		return null;
//	}

	public RulerProvider getRulerProvider() {
		return rulerProvider;
	}

	/* (non-Javadoc)
	* @see org.eclipse.gef.EditPart#getTargetEditPart(org.eclipse.gef.Request)
	*/
	@Override
	public EditPart getTargetEditPart(Request request) {
		if (request.getType().equals(REQ_MOVE)) {
			return this;
		} else {
			return super.getTargetEditPart(request);
		}
	}

	public ZoomManager getZoomManager() {
		return (ZoomManager)diagramViewer.getProperty(ZoomManager.class.toString());
	}

	public boolean isHorizontal() {
		return ((RulerModel)getModel()).isHorizontal();
	}

	@Override
	public void setParent(EditPart parent) {
		super.setParent(parent);
		if (getParent() != null && diagramViewer == null) {
			diagramViewer = (GraphicalViewer)getViewer()
					.getProperty(GraphicalViewer.class.toString());
			RulerProvider hProvider = (RulerProvider)diagramViewer
					.getProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER);
			if (hProvider != null && hProvider.getRuler() == getModel()) {
				rulerProvider = hProvider;
			} else {
				rulerProvider = (RulerProvider)diagramViewer
						.getProperty(RulerProvider.PROPERTY_VERTICAL_RULER);
			}
		}
	}

	public static class RulerSelectionPolicy extends SelectionEditPolicy {
		@Override
		protected void hideFocus() {
//			((RulerFigure)getHostFigure()).setDrawFocus(false);
		}
		@Override
		protected void hideSelection() {
//			((RulerFigure)getHostFigure()).setDrawFocus(false);
		}
		@Override
		protected void showFocus() {
//			((RulerFigure)getHostFigure()).setDrawFocus(true);
		}	
		@Override
		protected void showSelection() {
//			((RulerFigure)getHostFigure()).setDrawFocus(true);
		}
	}
	
	

	@Override
	public void activate() {
		super.activate();
		propertyChangeListener = new UpdateMapZoomLevelPropertyChangeListener();
		Viewport viewport = ((Viewport)getFigure().getParent());
		if(isHorizontal()) {
			viewport.getHorizontalRangeModel().addPropertyChangeListener(propertyChangeListener);
		} else {
			viewport.getVerticalRangeModel().addPropertyChangeListener(propertyChangeListener);
		}
	}

	@Override
	public void deactivate() {
		super.deactivate();
		Viewport viewport = ((Viewport)getFigure().getParent());
		if(isHorizontal()) {
			viewport.getHorizontalRangeModel().removePropertyChangeListener(propertyChangeListener);
		} else {
			viewport.getVerticalRangeModel().removePropertyChangeListener(propertyChangeListener);
		}
	}
	
	

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		if(getFigure() instanceof IMapZoomableDependentFigure) {
			IMapZoomableDependentFigure mapZoomableDependentFigure = (IMapZoomableDependentFigure) getFigure();
			mapZoomableDependentFigure.setZoom(((RulerModel)getModel()).getZoom());
		}
	}

	private class UpdateMapZoomLevelPropertyChangeListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			refreshVisuals();
		}
		
	}


	

}
