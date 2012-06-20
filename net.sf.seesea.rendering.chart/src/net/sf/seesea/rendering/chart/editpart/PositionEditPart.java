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

import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.figures.GeoLocator;
import net.sf.seesea.rendering.chart.figures.TextFigure;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class PositionEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
//		ShipFigure shipFigure = new ShipFigure();
//		shipFigure.setSize(40,40);
//		return shipFigure;
		TextFigure textFigure = new TextFigure();
		textFigure.setSize(18, 27);

		return textFigure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void refreshVisuals() {
		TextFigure buoyFigure = ((TextFigure)getFigure());
		GeoPosition geoPosition = ((GeoPosition)getModel());

		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference serviceReference = bundleContext.getServiceReference(IMapProjection.class.getName());
		IMapProjection service = (IMapProjection)bundleContext.getService(serviceReference);
		
		org.eclipse.swt.graphics.Point project = service.project(geoPosition, getFigure().getParent().getSize().width);
//		
		Point loc =  new Point(project.x,project.y);
//		loc.x = loc.x - (getFigure().getBounds().width / 2);
//		loc.y = loc.y - (getFigure().getBounds().height / 2);
//		Rectangle r = new Rectangle(loc, getFigure().getPreferredSize());
//		
//		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new GeoLocator(buoyFigure, loc));
	}


}
