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
package net.sf.seesea.rendering.chart.policies;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.diagramInterchange.Diagram;
import net.sf.seesea.rendering.chart.commands.CreateNodeCommand;
import net.sf.seesea.rendering.chart.commands.SetViewerPositionCommand;
import net.sf.seesea.rendering.chart.dnd.ModelObjectsDropRequest;
import net.sf.seesea.rendering.chart.editpart.TransactionalEditPart;
import net.sf.seesea.rendering.chart.editpart.WorldEditPart;
import net.sf.seesea.rendering.chart.tools.ChartSelectionTool;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;

/**
 * 
 */
public class SeeSeaDelegatingLayoutEditPolicy extends ConstrainedLayoutEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#getConstraintFor(org.eclipse.draw2d.geometry.Point)
	 */
	@Override
	protected Object getConstraintFor(Point point) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#getConstraintFor(org.eclipse.draw2d.geometry.Rectangle)
	 */
	@Override
	protected Object getConstraintFor(Rectangle rect) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
//	    Class<? extends ModelObject> typeClass = (Class) request.getNewObjectType();

		return null;
	}

	@Override
	public Command getCommand(Request request) {
		if ("DropModelObjects".equals(request.getType())) {
			return getDropCommand((ModelObjectsDropRequest) request);
		} else if(request instanceof ChangeBoundsRequest) {
			return getMoveChartCommand((ChangeBoundsRequest)request);
		}
		
		return super.getCommand(request);
	}

	private Command getMoveChartCommand(ChangeBoundsRequest request) {
		WorldEditPart editPart = (WorldEditPart) getHost();
		TransactionalEditingDomain editingDomain = ((TransactionalEditPart)getHost()).getEditingDomain();

		GeospatialGraphicalViewer geospatialGraphicalViewer = (GeospatialGraphicalViewer) editPart.getViewer();
		Point centerPosition = geospatialGraphicalViewer.getScrollingPosition();
		Point point = new Point(centerPosition);
		point.x -= request.getMoveDelta().x;
		point.y -= request.getMoveDelta().y;
		SetViewerPositionCommand setPositionCommand = new SetViewerPositionCommand(geospatialGraphicalViewer, point);
		return setPositionCommand;
	}

	/**
	 * @param request
	 * @return
	 */
	private Command getDropCommand(ModelObjectsDropRequest request) {
		
		// not good as it is not reusable
		WorldEditPart editPart = (WorldEditPart) getHost();
		Diagram diagram = editPart.getWorld().getChartConfiguration();
		TransactionalEditingDomain editingDomain = ((TransactionalEditPart)getHost()).getEditingDomain();
		ModelObject next = request.getModelObjects().iterator().next();
		CreateNodeCommand createNodeCommand = new CreateNodeCommand(editingDomain, diagram, next);
		return new ICommandProxy(createNodeCommand);
		
	}

	@Override
	public EditPart getTargetEditPart(Request request) {
		if("DropModelObjects".equals(request.getType())) {
			return getHost();
		} else if(ChartSelectionTool.MOVE_CHART.equals(request.getType())) {
			return getHost();
		}
		return super.getTargetEditPart(request);
	}
	
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return null;
	};
	

}
