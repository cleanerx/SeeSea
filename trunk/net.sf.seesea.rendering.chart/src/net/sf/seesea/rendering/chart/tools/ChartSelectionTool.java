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
package net.sf.seesea.rendering.chart.tools;

import net.sf.seesea.rendering.chart.IViewerGestureListener;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.GestureEvent;

public class ChartSelectionTool extends TargetingTool implements IViewerGestureListener {

	public static final String MOVE_CHART = "Move Chart"; //$NON-NLS-1$

	@Override
	protected String getCommandName() {
		return MOVE_CHART;
	}
	
	@Override
	protected boolean handleDragStarted() {
		setTargetRequest(createTargetRequest());
		return false;
	}
	
	@Override
	protected boolean handleDragInProgress() {
//		if(isInState(STATE_DRAG_IN_PROGRESS
//				| STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
			updateTargetRequest();
			updateTargetUnderMouse();
			setCurrentCommand(getCommand());
//		}
		return true;
	}
	
	@Override
	protected boolean handleButtonDown(int button) {
		SelectionRequest selectionRequest = new SelectionRequest();
		selectionRequest.setLastButtonPressed(button);
		selectionRequest.setLocation(getLocation());
		selectionRequest.setType(RequestConstants.REQ_SELECTION);
		setTargetRequest(selectionRequest);
		updateTargetUnderMouse();
		EditPart targetEditPart2 = getTargetEditPart();
		setCurrentCommand(getCommand());

		return super.handleButtonDown(button);
	}
	
	@Override
	protected boolean handleButtonUp(int button) {
		if(getTargetRequest() instanceof SelectionRequest) {
			getCurrentViewer().setSelection(
					new StructuredSelection(getTargetEditPart()));
		} else {
			executeCurrentCommand();
		}

		resetFlags();
		return true;
	}
	
	@Override
	protected void updateTargetRequest() {
		if(getTargetRequest() instanceof ChangeBoundsRequest) {
			Dimension delta = getDragMoveDelta();
			ChangeBoundsRequest request = (ChangeBoundsRequest) getTargetRequest();
			request.setMoveDelta(new Point(delta.width, delta.height));
			request.setLocation(getLocation());
			request.setType(getCommandName());
		}

//		super.updateTargetRequest();
	}
	
	@Override
	protected Request createTargetRequest() {
		return new ChangeBoundsRequest(REQ_MOVE);
	}
	
	@Override
	protected Command getCommand() {
		CompoundCommand command = new CompoundCommand();
		if (getTargetEditPart() == null)
			command.add(UnexecutableCommand.INSTANCE);
		else
			command.add(getTargetEditPart().getCommand(getTargetRequest()));
		
		return command.unwrap();
	}

	public void gesturePerformed(GestureEvent gestureEvent, EditPartViewer viewer) {
//		if (getDragTracker() instanceof IViewerGestureListener) {
//			((IViewerGestureListener) getDragTracker()).gesturePerformed(gestureEvent, viewer);
//			gestureEvent.doit = false;
//		}
		if (isInState(STATE_INITIAL))
			performViewerGesture(gestureEvent, viewer);
	}

	protected void performViewerGesture(GestureEvent gestureEvent, EditPartViewer viewer) {
		IViewerGestureListener handler = (IViewerGestureListener) viewer.getProperty(IViewerGestureListener.KEY);
		if (handler != null)
			handler.gesturePerformed(gestureEvent, viewer);
	}

}
