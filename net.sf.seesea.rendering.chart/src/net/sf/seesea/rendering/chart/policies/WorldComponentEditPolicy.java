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

import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.diagramInterchange.Diagram;
import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.osm.Area;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.util.GeoParser;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.commands.AddAnchorPositionToAreaCommand;
import net.sf.seesea.rendering.chart.commands.AddPositionToAreaCommand;
import net.sf.seesea.rendering.chart.commands.CreateNodeCommand;
import net.sf.seesea.rendering.chart.commands.CreatePositionCommand;
import net.sf.seesea.rendering.chart.editpart.TransactionalEditPart;
import net.sf.seesea.rendering.chart.editpart.WorldEditPart;
import net.sf.seesea.rendering.chart.figures.MapLayer;
import net.sf.seesea.rendering.chart.figures.Messages;
import net.sf.seesea.rendering.chart.figures.ServiceRankingComparator;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.tileservice.ITileProvider;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.swt.graphics.Point;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * 
 */
public class WorldComponentEditPolicy extends ComponentEditPolicy {

	@Override
	public Command getCommand(Request request) {
		if(RequestConstants.REQ_CREATE.equals(request.getType())) {
			CreateRequest createRequest = (CreateRequest) request;
			
//			System.out.println(createRequest.getSize());
			
			WorldEditPart editPart = (WorldEditPart) getHost();
			TransactionalEditingDomain editingDomain = ((TransactionalEditPart)getHost()).getEditingDomain();
			AnchorPosition anchorPosition = (AnchorPosition) createRequest.getNewObject();
			Rectangle rectangle = null;
			if(createRequest.getSize().width < 0 && createRequest.getSize().height < 0) {
				rectangle = new Rectangle(createRequest.getLocation().x, createRequest.getLocation().y, Math.abs(createRequest.getSize().width), Math.abs(createRequest.getSize().height));
			} else if(createRequest.getSize().width < 0 && createRequest.getSize().height >= 0) {
				rectangle = new Rectangle(createRequest.getLocation().x, createRequest.getLocation().y + createRequest.getSize().height, Math.abs(createRequest.getSize().width), Math.abs(createRequest.getSize().height));
			} else if(createRequest.getSize().width >= 0 && createRequest.getSize().height >= 0) {
				rectangle = new Rectangle(createRequest.getLocation().x - createRequest.getSize().width, createRequest.getLocation().y - createRequest.getSize().height, Math.abs(createRequest.getSize().width), Math.abs(createRequest.getSize().height));
			} else { //if(createRequest.getSize().width >= 0 && createRequest.getSize().height < 0) {
				rectangle = new Rectangle(createRequest.getLocation().x - createRequest.getSize().width, createRequest.getLocation().y , Math.abs(createRequest.getSize().width), Math.abs(createRequest.getSize().height));
			}
			System.out.println(rectangle);
			
//			System.out.println("ReqLoc:" + createRequest.getLocation() + "/" +createRequest.getSize());
			MapLayer mapLayer = (MapLayer) ((GraphicalEditPart)getHost()).getFigure();
			mapLayer.translateToRelative(rectangle);
			BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
			ServiceReference<IMapProjection> serviceReference2 = bundleContext.getServiceReference(IMapProjection.class);
			IMapProjection mapProjection = bundleContext.getService(serviceReference2);
			
			int scale = (1 << ((Area)(editPart.getModel())).getZoomLevel()) * 256;
//			rectangle.getCenter();
			org.eclipse.draw2d.geometry.Point center = rectangle.getCenter();
			GeoPosition positionTopLeft = mapProjection.backproject(new Point(center.x, center.y), scale);
			
			GeoPosition positionEnd = mapProjection.backproject(new Point(center.x + rectangle.width / 2, center.y + rectangle.height / 2), scale);

			System.out.println("TL:Lat/Lon" + positionTopLeft.getLatitude().getDecimalDegree() + ":" + positionTopLeft.getLongitude().getDecimalDegree());
			System.out.println("LR:Lat/Lon" + positionEnd.getLatitude().getDecimalDegree() + ":" + positionEnd.getLongitude().getDecimalDegree());

//			rectangle.getCenter();
			
			double extentY = Math.abs(positionTopLeft.getLatitude().getDecimalDegree() - positionEnd.getLatitude().getDecimalDegree());
			double extentX = Math.abs(positionTopLeft.getLongitude().getDecimalDegree() - positionEnd.getLongitude().getDecimalDegree());
			System.out.println("EX:" + extentX + ":" + extentY);
			
			anchorPosition.setLatitude(positionTopLeft.getLatitude());
			anchorPosition.setLongitude(positionTopLeft.getLongitude());
//			anchorPosition.setLatitude(GeoParser.parseLatitude(positionTopLeft.getLatitude().getDecimalDegree() ));
//			anchorPosition.setLongitude(GeoParser.parseLongitude(positionTopLeft.getLongitude().getDecimalDegree() ));

			anchorPosition.setYExtent(Math.abs(extentY));
			anchorPosition.setXExtent(Math.abs(extentX));

			System.out.println("Rec:Cen/rect" + rectangle.getTopLeft() + ":" + rectangle);
			System.out.println("Anc:Lat/Lon" + anchorPosition.getLatitude().getDecimalDegree() + ":" + anchorPosition.getLongitude().getDecimalDegree() + "/Ext:" + anchorPosition.getXExtent() + ":" + anchorPosition.getYExtent());
			EditingDomain editingDomain2 = ((GeospatialGraphicalViewer) getHost().getViewer()).getEditingDomainServiceTracker();
			AddAnchorPositionToAreaCommand createNodeCommand = new AddAnchorPositionToAreaCommand((TransactionalEditingDomain) editingDomain2, (World) editPart.getModel(), anchorPosition);
			return new ICommandProxy(createNodeCommand);
		}
		return super.getCommand(request);
	}
	
}
