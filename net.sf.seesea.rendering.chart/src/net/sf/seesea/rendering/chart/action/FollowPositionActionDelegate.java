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
package net.sf.seesea.rendering.chart.action;

import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.rendering.chart.editor.ChartEditor;
import net.sf.seesea.rendering.chart.editor.MapEditorInput;
import net.sf.seesea.rendering.chart.editpart.WorldEditPart;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionDelegate;

public class FollowPositionActionDelegate extends ActionDelegate implements
		IEditorActionDelegate {

	private ChartEditor targetEditor;

	boolean followEnabled = true;

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (ChartEditor) targetEditor;
	}
	

	@Override
	public void runWithEvent(IAction action, Event event) {
		World world = ((MapEditorInput)targetEditor.getEditorInput()).getWorld();
		WorldEditPart worldEditPart = (WorldEditPart) targetEditor.getGraphicalViewer().getEditPartRegistry().get(world);
		if(!followEnabled) {
			worldEditPart.enablePositionTracking(true);
		} else {
			worldEditPart.disablePositionTracking();
		}
		followEnabled = !followEnabled;
	}
}
