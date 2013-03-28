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

import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.rendering.chart.IViewerGestureListener;
import net.sf.seesea.rendering.chart.factory.CreationFactoryImplementation;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.widgets.Display;

public class AnchorWarnTool extends TargetingTool implements IViewerGestureListener {

	private Figure marqueeRectangleFigure;

	public static final String CREATE_ANCHOR = "Create Anchor Alert"; //$NON-NLS-1$

	@Override
	protected String getCommandName() {
		return CREATE_ANCHOR;
	}
	
	public AnchorWarnTool() {
		setDefaultCursor(SharedCursors.CROSS);
		setUnloadWhenFinished(false);
	}
	
	/**
	 * Erases feedback if necessary and puts the tool into the terminal state.
	 */
	public void deactivate() {
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			eraseMarqueeFeedback();
			eraseTargetFeedback();
		}
		super.deactivate();
		setState(STATE_TERMINAL);
	}

//	@Override
//	protected boolean handleDragInProgress() {
////		if(isInState(STATE_DRAG_IN_PROGRESS
////				| STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
//			updateTargetRequest();
//			updateTargetUnderMouse();
//			setCurrentCommand(getCommand());
////		}
//		return true;
//	}
//	
	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonDown(int)
	 */
	protected boolean handleButtonDown(int button) {
		if (button != 1) {
			setState(STATE_INVALID);
			handleInvalidInput();
		}
		if (stateTransition(STATE_INITIAL, STATE_DRAG_IN_PROGRESS)) {
//			((CreateRequest)getTargetRequest()).setStartLocation(getLocation());
			updateTargetRequest();
			updateTargetUnderMouse();
//			setStartLocation(getLocation());
//			setConnectionSource(getTargetEditPart());
			Command command = getCommand();
			if (command != null) {
				setCurrentCommand(command);
//				viewer = getCurrentViewer();
			}
			// nothig to do
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonUp(int)
	 */
	protected boolean handleButtonUp(int button) {
		if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) {
			eraseTargetFeedback();
			eraseMarqueeFeedback();
//			performMarqueeSelect();
		}
//		System.out.println(getCurrentCommand());
		getDomain().getCommandStack().execute(getCurrentCommand());
		handleFinished();

		return true;
	}
	
	private void eraseMarqueeFeedback() {
		if (marqueeRectangleFigure != null) {
			removeFeedback(marqueeRectangleFigure);
			marqueeRectangleFigure = null;
		}
	}
	
	/**
	 * @see org.eclipse.gef.Tool#setViewer(org.eclipse.gef.EditPartViewer)
	 */
	public void setViewer(EditPartViewer viewer) {
		if (viewer == getCurrentViewer())
			return;
		super.setViewer(viewer);
		if (viewer instanceof GraphicalViewer)
			setDefaultCursor(SharedCursors.CROSS);
		else
			setDefaultCursor(SharedCursors.NO);
	}


	/**
	 * Returns the current marquee selection rectangle.
	 * 
	 * @return A {@link Rectangle} representing the current marquee selection.
	 * @since 3.7
	 */
	protected Rectangle getCurrentMarqueeSelectionRectangle() {
		return new Rectangle(getStartLocation(), getLocation());
	}

	private IFigure getMarqueeFeedbackFigure() {
		if (marqueeRectangleFigure == null) {
			marqueeRectangleFigure = new MarqueeRectangleFigure();
			addFeedback(marqueeRectangleFigure);
		}
		return marqueeRectangleFigure;
	}

//	private Request getTargetRequest() {
//		if (targetRequest == null)
//			targetRequest = createTargetRequest();
//		return targetRequest;
//	}
	
	@Override
	protected Request createTargetRequest() {
		CreateRequest request = new CreateRequest();
		request.setFactory(new CreationFactoryImplementation(AnchorPosition.class));
		return request;
	}
	
	@Override
	protected void updateTargetRequest() {
		CreateRequest request = (CreateRequest) getTargetRequest();
		request.setLocation(getLocation());
//		System.out.println(getDragMoveDelta());
		request.setSize(getDragMoveDelta());
	}
	
	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleDragInProgress()
	 */
	protected boolean handleDragInProgress() {
		if (isInState(STATE_DRAG | STATE_DRAG_IN_PROGRESS)) {
			updateTargetRequest();
			setCurrentCommand(getCommand());
			showMarqueeFeedback();
			eraseTargetFeedback();
			showTargetFeedback();
		}
		return true;
	}

	private void showMarqueeFeedback() {
		Rectangle rect = getCurrentMarqueeSelectionRectangle().getCopy();
		IFigure marqueeFeedbackFigure = getMarqueeFeedbackFigure();
		marqueeFeedbackFigure.translateToRelative(rect);
		marqueeFeedbackFigure.setBounds(rect);
		marqueeFeedbackFigure.validate();
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleFocusLost()
	 */
	protected boolean handleFocusLost() {
		if (isInState(STATE_DRAG | STATE_DRAG_IN_PROGRESS)) {
			handleFinished();
			return true;
		}
		return false;
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
	
	class MarqueeRectangleFigure extends Figure {

		private static final int DELAY = 110; // animation delay in millisecond
		private int offset = 0;
		private boolean schedulePaint = true;

		/**
		 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
		 */
		protected void paintFigure(Graphics graphics) {
			Rectangle bounds = getBounds().getCopy();
			bounds.width -= 1;
			bounds.height -= 1;
			graphics.setXORMode(true);
			setOpaque(true);
			graphics.setForegroundColor(ColorConstants.white);
			graphics.setBackgroundColor(ColorConstants.black);
			graphics.setLineStyle(Graphics.LINE_DOT);
			graphics.drawArc(bounds, 0, 360);

			if (schedulePaint) {
				Display.getCurrent().timerExec(DELAY, new Runnable() {
					public void run() {
						offset++;
						if (offset > 5)
							offset = 0;

						schedulePaint = true;
						repaint();
					}
				});
			}

			schedulePaint = false;
		}
	}

}
