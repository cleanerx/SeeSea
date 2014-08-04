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

import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.Route;
import net.sf.seesea.model.core.geo.Track;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.int1.base.MarineChart;
import net.sf.seesea.model.int1.buoysandbeacons.Buoy;
import net.sf.seesea.rendering.chart.editor.AreaMarker;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.Adaptable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

/**
 * 
 */
public class SeeSeaGraphicalPartFactory implements EditPartFactory {

	private EditPart child = null;

	/**
	 * 
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if(model instanceof World) {
			child = new WorldEditPart();
//			} else if(model instanceof Buoy) {
//			child = new BuoyEditPart();
		} else if(model instanceof AnchorPosition) {
			child = new AnchorAreaEditPart();
		} else if(model instanceof GeoPosition && ((EObject)model).eContainer() instanceof World && ((World)((EObject)model).eContainer()).getCursorPosition().equals(model)) {
			child = new DotEditPart();
		} else if(model instanceof GeoPosition && ((EObject)model).eContainer() instanceof World) {
			child = new ShipPositionEditPart();
		} else if(model instanceof AISMessageTime) {
			child = new AISEditPart();
		} else if(model instanceof AreaMarker) {
			child = new AreaOverlayEditPart();
//		}
//		    child = new WorldEditPart();
//		} else if(model instanceof Diagram) {
//			child = new WorldEditPart();
//		} else if(model instanceof Instruments) {
//			child = new InstrumentEditPart();
//		} else if(model instanceof MeasuredPosition3D) {
//			child = new MeasuredGeoPositionEditPart();
//		} else if(model instanceof Buoy) {
//			child = new BuoyEditPart();
//		} else if(model instanceof NamedPosition) {
//			child = new PositionEditPart();
//		} else if(model instanceof GeoPosition) {
//			child = new PositionEditPart();
//		} else if(model instanceof GraphicalNode && ((GraphicalNode)model).getRepresents() instanceof Instruments) {
//			child = new InstrumentEditPart();
		} else if(model instanceof Track) {
			child = new TrackEditPart();
		} else if(model instanceof Route) {
			child = new RouteEditPart();
		}
		if(child != null) {
			child.setModel(model);
		}

		return child;
	}

}
