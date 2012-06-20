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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.seesea.model.int1.buoysandbeacons.Buoy;
import net.sf.seesea.model.int1.lights.Color;
import net.sf.seesea.model.int1.lights.PhaseCharacteristic;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.figures.BuoyFigure;
import net.sf.seesea.rendering.chart.figures.GeoLocator;
import net.sf.seesea.rendering.chart.util.ColorMap;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class BuoyEditPart extends TransactionalEditPart implements EditPart {

	@Override
	protected IFigure createFigure() {
		return new BuoyFigure();
	}

	@Override
	protected void createEditPolicies() {
		// 
	}

	@Override
	protected void refreshVisuals() {
		BuoyFigure buoyFigure = ((BuoyFigure)getFigure());
		Buoy buoy = ((Buoy)getModel());

		buoyFigure.setTopmark(buoy.getTopmark());
		buoyFigure.setShape(buoy.getShape());
		buoyFigure.setColorOrientation(buoy.getColorType());
		setColor(buoyFigure, buoy);
		setLightingID(buoyFigure, buoy);
		if(buoy.getLightcolor().size() == 1) {
			org.eclipse.swt.graphics.Color color = ColorMap.getColor(buoy.getLightcolor().get(0));
			buoyFigure.setLightingColor(color);
		}
		
		buoyFigure.setRadarReflecting(buoy.isRadarreflector());

		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference serviceReference = bundleContext.getServiceReference(IMapProjection.class.getName());
		IMapProjection service = (IMapProjection)bundleContext.getService(serviceReference);
		
		org.eclipse.swt.graphics.Point project = service.project(buoy, getFigure().getParent().getSize().width);

		Point loc =  new Point(project.x,project.y);
//		loc.x = loc.x - (getFigure().getBounds().width / 2);
//		loc.y = loc.y - (getFigure().getBounds().height / 2);
//		Rectangle r = new Rectangle(loc, getFigure().getPreferredSize());
		
//		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, buoyFigure, new GeoLocator(buoyFigure, loc));
	}

	private void setColor(BuoyFigure buoyFigure, Buoy buoy) {
		EList<Color> colors = buoy.getColor();
		StringBuilder colorShortCutBuilder = new StringBuilder(); 
		for (Color color : colors) {
			colorShortCutBuilder.append(Messages.getString(color.getLiteral()));
		}
		buoyFigure.setColorText(colorShortCutBuilder.toString());
	}

	private void setLightingID(BuoyFigure buoyFigure, Buoy buoy) {
		StringBuilder lightingIDBuilder = new StringBuilder(); 
		if(!buoy.getPhaseCharacteristic().equals(PhaseCharacteristic.UNKNOWN)) {
			lightingIDBuilder.append(Messages.getString(buoy.getPhaseCharacteristic().getLiteral()));
			// hoping this is not an i18n issue
			Iterator<Integer> group = buoy.getGroup().iterator();
			if(!buoy.getGroup().isEmpty()) {
				lightingIDBuilder.append("("); //$NON-NLS-1$
				lightingIDBuilder.append(group.next());
				while (group.hasNext()) {
					lightingIDBuilder.append("+"); //$NON-NLS-1$
					lightingIDBuilder.append(group.next());
				}
				lightingIDBuilder.append(")"); //$NON-NLS-1$
			} else {
				lightingIDBuilder.append("."); //$NON-NLS-1$
			}
			for (Color color : buoy.getLightcolor()) {
				lightingIDBuilder.append(Messages.getString(color.getLiteral()));
			}
			}
			if(buoy.getPeriod() != 0) {
				lightingIDBuilder.append(buoy.getPeriod());
				lightingIDBuilder.append(Messages.getString("secondsShort")); //$NON-NLS-1$
			}
			buoyFigure.setLightingID(lightingIDBuilder.toString());
			List<org.eclipse.swt.graphics.Color> list = new ArrayList<org.eclipse.swt.graphics.Color>(buoy.getColor().size());
			for (Color color : buoy.getColor()) {
				switch (color) {
				case RED:
					list.add(ColorConstants.red);
					break;
				case GREEN:
					list.add(ColorConstants.darkGreen);
					break;
				case YELLOW:
					list.add(ColorConstants.yellow);
					break;
				case ORANGE:
					list.add(ColorConstants.orange);
					break;
				case BLACK:
					list.add(ColorConstants.black);
					break;
				case WHITE:
					list.add(ColorConstants.white);
					break;
				case BLUE:
					list.add(ColorConstants.blue);
					break;
				}
			}
			buoyFigure.setBuoyColor(list);
	}
	
	

}
