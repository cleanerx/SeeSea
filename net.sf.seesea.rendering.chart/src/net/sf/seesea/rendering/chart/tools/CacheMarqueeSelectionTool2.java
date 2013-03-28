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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.util.GeoParser;
import net.sf.seesea.rendering.chart.editor.AreaMarker;
import net.sf.seesea.rendering.chart.editpart.ScalableZoomableRootEditPart;
import net.sf.seesea.rendering.chart.editpart.WorldEditPart;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Handle;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

public class CacheMarqueeSelectionTool2 extends ChartSelectionTool {

	private Request hoverRequest;

	@Override
	public void viewerEntered(MouseEvent me, EditPartViewer viewer) {
		super.viewerEntered(me, viewer);
		HttpClient httpClient = new HttpClient();
		try {
			GetMethod method = new GetMethod("http://192.168.1.1/segeln/cachedAreas.csv");
			int executeMethod = httpClient.executeMethod(method);
			if(executeMethod == 200) {
				InputStream responseBodyAsStream = method.getResponseBodyAsStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(responseBodyAsStream));
				String line;
				ScalableZoomableRootEditPart editPart = (ScalableZoomableRootEditPart) viewer.getRootEditPart();
				WorldEditPart worldEditPart = (WorldEditPart) editPart.getChildren().iterator().next();
				List<AreaMarker> areaMarkers = worldEditPart.getAreaMarkers();
				areaMarkers.clear();
				while((line = reader.readLine()) != null) {
					String[] split = line.split(";"); //$NON-NLS-1$
					
					List<GeoPosition> areaBounds = new ArrayList<GeoPosition>();
					for (int i = 3; i < split.length; i+=2) {
						GeoPosition position1 = GeoFactory.eINSTANCE.createGeoPosition();
						Latitude latitude1 = GeoParser.parseLatitude(split[i]);
						Longitude longitude1 = GeoParser.parseLongitude(split[i + 1]);
						position1.setLatitude(latitude1);
						position1.setLongitude(longitude1);
						areaBounds.add(position1);
					}
					
					long id = Long.parseLong(split[0]);
					areaMarkers.add(new AreaMarker(id, areaBounds , split[1], split[2]));
					
				}
				worldEditPart.refresh();
			}
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void viewerExited(MouseEvent me, EditPartViewer viewer) {
		super.viewerExited(me, viewer);
		ScalableZoomableRootEditPart editPart = (ScalableZoomableRootEditPart) viewer.getRootEditPart();
		WorldEditPart worldEditPart = (WorldEditPart) editPart.getChildren().iterator().next();
		List<AreaMarker> areaMarkers = worldEditPart.getAreaMarkers();
		areaMarkers.clear();
		worldEditPart.refresh();
	}
	
	@Override
	protected boolean handleDragInProgress() {
		return true;
	}
	
	/**
	 * Creates a {@link SelectionRequest} for the target request.
	 * 
	 * @see TargetingTool#createTargetRequest()
	 */
	protected Request createTargetRequest() {
		SelectionRequest request = new SelectionRequest();
		request.setType(getCommandName());
		return request;
	}
	
	/**
	 * Erases the hover feedback by calling
	 * {@link EditPart#eraseTargetFeedback(Request)}.
	 */
	protected void eraseHoverFeedback() {
		if (getTargetEditPart() == null)
			return;
		if (getTargetHoverRequest() == null)
			return;
		getTargetEditPart().eraseTargetFeedback(getTargetHoverRequest());
	}

	/**
	 * If in the initial state, updates the request and the mouse target and
	 * asks to show target feedback. If in the traverse handle state, finds the
	 * next handle, moves the mouse cursor to that handle, and gets a drag
	 * tracker from the handle.
	 * 
	 * @see AbstractTool#handleMove()
	 */
	protected boolean handleMove() {
		updateTargetRequest();

		EditPart editPart = getCurrentViewer()
				.findObjectAtExcluding(getLocation(), getExclusionSet(),
						getTargetingConditional());
		if (editPart != null)
			editPart = editPart.getTargetEditPart(getTargetRequest());
//		boolean changed = getTargetEditPart() != editPart;
		setTargetEditPart(editPart);

//		updateTargetUnderMouse();
		showTargetFeedback();
		return true;
	}

	/**
	 * Creates the hover request (a {@link LocationRequest}) and sets its type
	 * to {@link RequestConstants#REQ_SELECTION_HOVER}.
	 */
	protected void createHoverRequest() {
		hoverRequest = new LocationRequest();
		hoverRequest.setType(RequestConstants.REQ_SELECTION_HOVER);
	}

	/**
	 * Returns the target hover request. If <code>null</code>, it will be
	 * created via {@link #createHoverRequest()}.
	 * 
	 * @return the hover request
	 */
	protected Request getTargetHoverRequest() {
		if (hoverRequest == null)
			createHoverRequest();
		return hoverRequest;
	}

	/**
	 * Called when the mouse hovers. Calls {@link #showHoverFeedback()}.
	 * 
	 * @see AbstractTool#handleHover()
	 */
	protected boolean handleHover() {
		setHoverActive(true);
		showHoverFeedback();
		return true;
	}

	/**
	 * Called when the mouse hover stops (i.e. the mouse moves or a button is
	 * clicked). Calls {@link #eraseHoverFeedback()}.
	 * 
	 * @see TargetingTool#handleHoverStop()
	 */
	protected boolean handleHoverStop() {
		eraseHoverFeedback();
		return true;
	}

	/**
	 * Asks the target edit part (if there is one) to show hover feedback via
	 * {@link EditPart#showTargetFeedback(Request)} with a hover request.
	 */
	protected void showHoverFeedback() {
		if (getTargetEditPart() == null)
			return;
		if (getTargetHoverRequest() == null)
			return;
		getTargetEditPart().showTargetFeedback(getTargetHoverRequest());
	}

	/**
	 * Updates the location of the hover request.
	 */
	protected void updateHoverRequest() {
		LocationRequest request = (LocationRequest) getTargetHoverRequest();
		request.setLocation(getLocation());
	}

	/**
	 * Sets the modifiers , type and location of the target request (which is a
	 * {@link SelectionRequest}) and then calls {@link #updateHoverRequest()}.
	 * 
	 * @see TargetingTool#updateTargetRequest()
	 */
	protected void updateTargetRequest() {
		SelectionRequest request = (SelectionRequest) getTargetRequest();
//		request.setModifiers(getCurrentInput().getModifiers());
		request.setType(getCommandName());
		request.setLocation(getLocation());
		updateHoverRequest();
	}
	
	/**
	 * If there is a {@link Handle} under the mouse, this method sets the drag
	 * tracker returned from the handle. If there's an {@link EditPart} under
	 * the mouse, this method sets the drag tracker returned from the edit part.
	 * 
	 * @see AbstractTool#handleButtonDown(int)
	 */
	protected boolean handleButtonDown(int button) {
		resetHover();
//		EditPartViewer viewer = getCurrentViewer();
		updateTargetRequest();
		((SelectionRequest) getTargetRequest()).setLastButtonPressed(button);
		updateTargetUnderMouse();
		EditPart editpart = getTargetEditPart();
		if (editpart != null) {
//			setDragTracker(editpart.getDragTracker(getTargetRequest()));
			lockTargetEditPart(editpart);
			return true;
		}
		return false;
	}


	
	@Override
	protected boolean handleButtonUp(int button) {
		EditPart editPart = getTargetEditPart();
		Command command2 = editPart.getCommand(getTargetRequest());
		getDomain().getCommandStack().execute(command2);
		unlockTargetEditPart();
		return true;
	}
	
	public class TileDownloadSpecification {
		
		Point startPosition;
		
		int x;
		
		int y;
		
		int zoomLevel;
	}

	@Override
	protected String getCommandName() {
		return REQ_SELECTION;
	}
	
}
