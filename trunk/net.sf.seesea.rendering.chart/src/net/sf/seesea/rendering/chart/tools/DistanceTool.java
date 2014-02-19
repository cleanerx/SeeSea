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

import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.rendering.chart.IViewerGestureListener;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.editpart.WorldEditPart;
import net.sf.seesea.tileservice.ITileProvider;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.ToolTipHelper;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gef.tools.AbstractConnectionCreationTool;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.swt.events.GestureEvent;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class DistanceTool extends AbstractConnectionCreationTool implements IViewerGestureListener {

	public static final String MOVE_CHART = "EditRoute"; //$NON-NLS-1$
	private EditPartViewer viewer;

	@Override
	protected String getCommandName() {
		return MOVE_CHART;
	}
	
	@Override
	protected boolean handleDragInProgress() {
//		if(isInState(STATE_DRAG_IN_PROGRESS
//				| STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {

//		}
		return true;
	}
	
	
	@Override
	protected boolean handleButtonDown(int button) {
		if (isInState(STATE_INITIAL) && button == 1) {
			updateTargetRequest();
			updateTargetUnderMouse();
//			setConnectionSource(getTargetEditPart());
			Command command = getCommand();
//			((CreateConnectionRequest) getTargetRequest())
//					.setSourceEditPart(getTargetEditPart());
			if (command != null) {
				setState(STATE_CONNECTION_STARTED);
				setCurrentCommand(command);
				viewer = getCurrentViewer();
			}
		}

		if (isInState(STATE_INITIAL) && button != 1) {
			setState(STATE_INVALID);
			handleInvalidInput();
		}
		return true;
		
	}
	
	@Override
	protected boolean handleButtonUp(int button) {
		if(isInState(STATE_CONNECTION_STARTED)) {
			LocationSequenceRequest locationRequest = (LocationSequenceRequest) getTargetRequest();
			
			
			updateTargetRequest();
			updateTargetUnderMouse();
			EditPart targetEditPart2 = getTargetEditPart();
			if( targetEditPart2 != null  ) {
				WorldEditPart worldEditPart = (WorldEditPart) targetEditPart2;
				BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
				ServiceReference<ITileProvider> serviceReference = bundleContext.getServiceReference(ITileProvider.class);
				final ITileProvider tileProvider = (ITileProvider) bundleContext.getService(serviceReference);
				IMapProjection mapProjection = tileProvider.getProjection();

				Point location = getLocation();
				worldEditPart.getFigure().translateToRelative(location);
				GeoPosition position = mapProjection.backproject(new org.eclipse.swt.graphics.Point(location.x, location.y), (1<<worldEditPart.getWorld().getZoomLevel()) * 256);
				locationRequest.addLocation(position);
			}
			setCurrentCommand(getCommand());
		}
		
		
		
		WorldEditPart targetEditPart2 = (WorldEditPart) getTargetEditPart();
		
//		.getCommand(getTargetRequest());
		
//		executeCurrentCommand();
		return true;
	}
	
	@Override
	protected boolean handleDoubleClick(int button) {
		if(isInState(STATE_CONNECTION_STARTED)) {
			executeCurrentCommand();
			handleFinished();
		}
		// TODO Auto-generated method stub
		return super.handleDoubleClick(button);
	}
	
	@Override
	protected void updateTargetRequest() {
		LocationSequenceRequest locationRequest = (LocationSequenceRequest) getTargetRequest();
//		locationRequest.addLocation(getLocation());
	}
	
	@Override
	protected Request createTargetRequest() {
		LocationSequenceRequest locationRequest = new LocationSequenceRequest();
		
		return locationRequest;
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
