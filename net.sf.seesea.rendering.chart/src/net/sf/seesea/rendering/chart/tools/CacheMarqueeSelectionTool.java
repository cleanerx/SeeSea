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
package net.sf.seesea.rendering.chart.tools;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.osm.Area;
import net.sf.seesea.model.util.GeoParser;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.wizard.ConfigureCacheDownloadPage;
import net.sf.seesea.tileservice.ITileCacheProcessor;
import net.sf.seesea.tileservice.ITileProvider;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class CacheMarqueeSelectionTool extends MarqueeSelectionTool {

	@Override
	protected boolean handleDragInProgress() {
		return true;
	}
	
	@Override
	protected boolean handleButtonUp(int button) {
		Rectangle copy = getCurrentMarqueeSelectionRectangle().getCopy();
		
		GraphicalEditPart g = ((GraphicalEditPart)getCurrentViewer().getRootEditPart());
		GraphicalEditPart world = (GraphicalEditPart) g.getChildren().get(0);
		IFigure figure = world.getFigure();

		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference<ITileProvider> serviceReference = bundleContext.getServiceReference(ITileProvider.class);
		final ITileProvider tileProvider = (ITileProvider) bundleContext.getService(serviceReference);
		IMapProjection mapProjection = tileProvider.getProjection();

		if(world.getModel() instanceof Area) {
			Area area = (Area) world.getModel();
			org.eclipse.draw2d.geometry.Point location = copy.getTopLeft();
			figure.translateToRelative(location);
			GeoPosition topLeft = mapProjection.backproject(new Point(location.x, location.y), (1<<area.getZoomLevel()) * 256);

			location = copy.getBottomRight();
			figure.translateToRelative(location);
			GeoPosition bottomRight = mapProjection.backproject(new Point(location.x, location.y), (1<<area.getZoomLevel()) * 256);

			Wizard wizard = new Wizard() {
				
				@Override
				public boolean performFinish() {
					// TODO Auto-generated method stub
					return true;
				}
			};
			ConfigureCacheDownloadPage configureCacheDownloadPage = new ConfigureCacheDownloadPage();
			configureCacheDownloadPage.setLatitudeStart(topLeft.getLatitude().getDecimalDegree());
			configureCacheDownloadPage.setLongitudeStart(topLeft.getLongitude().getDecimalDegree());
			configureCacheDownloadPage.setLatitudeEnd(bottomRight.getLatitude().getDecimalDegree());
			configureCacheDownloadPage.setLongitudeEnd(bottomRight.getLongitude().getDecimalDegree());
			
			wizard.addPage(configureCacheDownloadPage);
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
			
			if(wizardDialog.open() == org.eclipse.jface.window.Window.OK) {
				final List<TileDownloadSpecification> downloadSpecifications = new ArrayList<CacheMarqueeSelectionTool.TileDownloadSpecification>();
				for (int i = configureCacheDownloadPage.getMinZoom(); i < configureCacheDownloadPage.getMaxZoom(); i++) {
					Latitude latitude = GeoParser.parseLatitude(configureCacheDownloadPage.getLatutideStartText());
					Longitude longitude = GeoParser.parseLongitude(configureCacheDownloadPage.getLongitudeStartText());
					GeoPosition startGeoPosition = GeoFactory.eINSTANCE.createGeoPosition();
					startGeoPosition.setLatitude(latitude);
					startGeoPosition.setLongitude(longitude);
					Point startPoint = mapProjection.project(startGeoPosition, (1<< i) *  tileProvider.getTileSize().x );

					latitude = GeoParser.parseLatitude(configureCacheDownloadPage.getLatutideEndText());
					longitude = GeoParser.parseLongitude(configureCacheDownloadPage.getLongitudeEndText());
					GeoPosition endGeoPosition = GeoFactory.eINSTANCE.createGeoPosition();
					endGeoPosition.setLatitude(latitude);
					endGeoPosition.setLongitude(longitude);
					Point endPoint = mapProjection.project(endGeoPosition, (1<< i) *  tileProvider.getTileSize().x);
					int x = (endPoint.x - startPoint.x) / tileProvider.getTileSize().x;
					int y = (endPoint.y - startPoint.y) / tileProvider.getTileSize().x;
					
					TileDownloadSpecification tileDownloadSpecification = new TileDownloadSpecification();
					tileDownloadSpecification.startPosition = startPoint;
					tileDownloadSpecification.x = x;
					tileDownloadSpecification.y = y;
					tileDownloadSpecification.zoomLevel = i;
					downloadSpecifications.add(tileDownloadSpecification);
					
//					System.out.println(startPoint + ":" +endPoint + "x:" + x + " y:" + y);
					
				}
				
				ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
				try {
					progressMonitorDialog.run(true, true, new IRunnableWithProgress() {
						
						public void run(final IProgressMonitor monitor) throws InvocationTargetException,
								InterruptedException {
							int amountofWork = 0;
							for (TileDownloadSpecification tileDownloadSpecification : downloadSpecifications) {
								amountofWork += (tileDownloadSpecification.x + 1)  * (tileDownloadSpecification.y + 1);
							}
							monitor.beginTask("Downloading tiles", amountofWork);
							ITileCacheProcessor tileCacheProcessor = tileProvider.getTileCacheProcessor();
							for (TileDownloadSpecification tileDownloadSpecification : downloadSpecifications) {
								monitor.subTask("Processing zoom level " + tileDownloadSpecification.zoomLevel);
								tileCacheProcessor.cacheTiles(tileDownloadSpecification.startPosition, tileDownloadSpecification.x, tileDownloadSpecification.y, tileDownloadSpecification.zoomLevel, null, monitor);
							}
							monitor.done();
						}
					});
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return true;
	}
	
	public class TileDownloadSpecification {
		
		Point startPosition;
		
		int x;
		
		int y;
		
		int zoomLevel;
	}
	
}
