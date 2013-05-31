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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.commands.SetPositionCommand;
import net.sf.seesea.rendering.chart.commands.SetZoomLevelCommand;
import net.sf.seesea.rendering.chart.editor.AreaMarker;
import net.sf.seesea.rendering.chart.figures.MapLayer;
import net.sf.seesea.rendering.chart.policies.SeeSeaDelegatingLayoutEditPolicy;
import net.sf.seesea.rendering.chart.policies.WorldComponentEditPolicy;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.tileservice.ITileProvider;
import nl.esi.metis.aisparser.AISMessage;
import nl.esi.metis.aisparser.AISMessageClassBPositionReport;
import nl.esi.metis.aisparser.AISMessagePositionReport;
import nl.esi.metis.aisparser.HandleAISMessage;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class WorldEditPart extends TransactionalEditPart implements Adapter {

	private boolean trackPosition = true; 
	
	private UpdateMapZoomLevelPropertyChangeListener propertyChangeListener;
	private ServiceRegistration positionTrackerRegistration;

	private boolean zoomInOnFirstPosition;

	private long lastUpdate;

	private AISTracker aisTracker;

	private ServiceRegistration<HandleAISMessage> serviceRegistration;
	
	private List<AreaMarker> areaMarkers;
	
	public WorldEditPart() {
		aisTracker = new AISTracker();
		areaMarkers = new ArrayList<AreaMarker>();
	}
	
	@Override
	protected IFigure createFigure() {
		MapLayer f = new MapLayer();
		f.setLayoutManager(new XYLayout());
		return f;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.NODE_ROLE, new WorldComponentEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new WorldComponentEditPolicy());
//		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new SeeSeaDelegatingLayoutEditPolicy());
//		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SeeSeaDelegatingLayoutEditPolicy());
//		installEditPolicy(EditPolicyRoles.POPUPBAR_ROLE, new DiagramPopupBarEditPolicy());

	}

	@Override
	protected void refreshVisuals() {
		refreshVisuals(true);
	}
	
	@Override
	public EditPart getTargetEditPart(Request request) {
//		System.out.println(request);
		EditPart targetEditPart = super.getTargetEditPart(request);
//		System.out.println(targetEditPart);
		return targetEditPart;
	}

	@Override
	protected List getModelChildren() {
		List<Object> modelChildren = new ArrayList<Object>();
		// show the target if we are tracking
		modelChildren.addAll(aisTracker.getAISMessagePositionReport());
//		modelChildren.add(getWorld().getMapCenterPosition());
		modelChildren.addAll(getWorld().getTracksContainer().getTracks());
		modelChildren.addAll(areaMarkers);
		if(getWorld().getAnchorPosition() != null) {
			modelChildren.add(getWorld().getAnchorPosition());
		}
		if(trackPosition) {
			modelChildren.add(getWorld().getMapCenterPosition());
		}
		return modelChildren; 
//		Buoy buoy = BuoysandbeaconsFactory.eINSTANCE.createBuoy();
//		buoy.getLightcolor().add(Color.GREEN);
//		buoy.setTopmark(BuoyTopmark.LATERAL_SQUARE);
//		buoy.setShape(Shape.SUPER);
//		buoy.getColor().add(Color.RED);
//		buoy.getColor().add(Color.GREEN);
//		buoy.setPhaseCharacteristic(PhaseCharacteristic.ISOPHASE);
//		buoy.setPeriod(5);
//		buoy.setTopmark(BuoyTopmark.LATERAL_TRIANGLE);
//		
//		ChartSymbols chartSymbols = BaseFactory.eINSTANCE.createChartSymbols();
//		chartSymbols.getSeamarks().add(buoy);
		
//		return chartSymbols.getSeamarks();
//		MarineChart w = ((MarineChart)getModel());
//		List<Object> childrean = new ArrayList<Object>();
//		childrean.add(w.getMapCenterPosition());
//		childrean.addAll(w.getSeamarks());
//		childrean.addAll(w.getPoiContainer().getPois());
//		childrean.addAll(w.getTracksContainer().getTracks());
//		childrean.addAll(w.getSeamarks());
//		childrean.addAll(w.getChartConfiguration().getChildren());
//		GeoPosition mapCenterPosition = w.getMapCenterPosition();
//		if(mapCenterPosition == null) {
//			Latitude latitude2 = GeoFactory.eINSTANCE.createLatitude();
//			latitude2.setDegree(49);
//			latitude2.setMinute(0);
//			latitude2.setSecond(0.0);
//
//			Longitude longitude2 = GeoFactory.eINSTANCE.createLongitude();
//			longitude2.setDegree(8);
//			longitude2.setMinute(23);
//			longitude2.setSecond(0.0);
//			
//			GeoPosition geoPosition = GeoFactory.eINSTANCE.createGeoPosition();
//			geoPosition.setLatitude(latitude2);
//			geoPosition.setLongitude(longitude2);
//			w.setMapCenterPosition(geoPosition);
//		}
//		childrean.add(mapCenterPosition);
//		return childrean;
	}




	private void refreshVisuals(boolean byScrollbar) {
		
//		World map = (World)getModel();
		MapLayer mapLayer = (MapLayer)getFigure();

		ScalableZoomableRootEditPart scalableZoomableRootEditPart = (ScalableZoomableRootEditPart) getRoot();
		Viewport viewport = (Viewport) scalableZoomableRootEditPart.getFigure();
		int zoom = scalableZoomableRootEditPart.getZoom();
		if(byScrollbar) {
			try {
				EditingDomain editingDomain2 = ((GeospatialGraphicalViewer) getViewer()).getEditingDomainServiceTracker();
				new SetZoomLevelCommand((TransactionalEditingDomain) editingDomain2, getWorld(), zoom).execute(new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				Logger.getLogger(getClass()).error("Unable to set zoom level", e); //$NON-NLS-1$
			}
		}
		
		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference<ITileProvider> serviceReference = bundleContext.getServiceReference(ITileProvider.class);
		if(serviceReference == null) {
			mapLayer.setPreferredSize(256,256);
			mapLayer.setZoomLevel(0);
		} else {
			ITileProvider tileProvider =  (ITileProvider) bundleContext.getService(serviceReference);
			
			Point tileSize = tileProvider.getTileSize();
			
			int numberOfTiles = 1 << zoom;
			mapLayer.setPreferredSize( numberOfTiles * tileSize.x, numberOfTiles * tileSize.y);
			mapLayer.setSize( numberOfTiles * tileSize.x, numberOfTiles * tileSize.y);
			mapLayer.setZoomLevel(zoom);
			mapLayer.setMapPosition(viewport.getViewLocation());
			bundleContext.ungetService(serviceReference);
		}

		mapLayer.invalidateTree();
		mapLayer.repaint();
	}

	@Override
	public void activate() {
		super.activate();
		propertyChangeListener = new UpdateMapZoomLevelPropertyChangeListener();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().addPropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().addPropertyChangeListener(propertyChangeListener);

		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		serviceRegistration = bundleContext.registerService(HandleAISMessage.class, aisTracker, null);

//		positionTrackerRegistration = SeeSeaUIActivator.getDefault().getBundle().getBundleContext().registerService(IPositionListener.class.getName(), new PositionListener(), null);
		getWorld().eAdapters().add(this);
		getWorld().getTracksContainer().eAdapters().add(this);
		enablePositionTracking(true);
		aisTracker.start();
	}

	@Override
	public void deactivate() {
		getWorld().eAdapters().remove(this);
		getWorld().getTracksContainer().eAdapters().remove(this);
		serviceRegistration.unregister();
		super.deactivate();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().removePropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().removePropertyChangeListener(propertyChangeListener);
		disablePositionTracking();
		aisTracker.dispose();
//		getWorld().eAdapters().remove(this);
//		positionTrackerRegistration.unregister();
	}

	private class UpdateMapZoomLevelPropertyChangeListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			refreshVisuals();
		}
		
	}

	public World getWorld() {
		return (World) getModel();
	}

	public Notifier getTarget() {
		return null;
	}

	public boolean isAdapterForType(Object type) {
		return false;
	}

	public void notifyChanged(Notification notification) {
		Display.getDefault().asyncExec(new Runnable() {
			
			public void run() {
				refreshChildren();
			}
		});
	}

	public void setTarget(Notifier newTarget) {
		
	}
	
	public void notify(MeasuredPosition3D sensorData) {
		
		((World)getModel()).setMapCenterPosition(sensorData);
		Display.getDefault().asyncExec(new Runnable() {
			
			public void run() {
				refresh();
			}
		});
	}

	/**
	 * 
	 */
	public void enablePositionTracking(boolean zoomIn) {
		positionTrackerRegistration = SeeSeaUIActivator.getDefault().getBundle().getBundleContext().registerService(IPositionListener.class.getName(), new PositionListener(), null);
		zoomInOnFirstPosition = zoomIn;
	}

	/**
	 * 
	 */
	public void disablePositionTracking() {
		positionTrackerRegistration.unregister();
	}

	private class PositionListener implements IPositionListener {

		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#notify(java.lang.Object)
		 */
		public void notify(final MeasuredPosition3D sensorData, String source) {
			
			if(System.currentTimeMillis() - lastUpdate > 1000) {
				Display.getDefault().asyncExec(new Runnable() {
					
					public void run() {
						try {
							EditingDomain editingDomain2 = ((GeospatialGraphicalViewer) getViewer()).getEditingDomainServiceTracker();
							SetPositionCommand setPositionCommand = new SetPositionCommand((TransactionalEditingDomain) editingDomain2, ((World)getModel()), sensorData);
							setPositionCommand.execute(new NullProgressMonitor(), null);
							ScalableZoomableRootEditPart scalableZoomableRootEditPart = (ScalableZoomableRootEditPart) getRoot();
							
							BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
							ServiceReference<ITileProvider> serviceReference = bundleContext.getServiceReference(ITileProvider.class);
							if(serviceReference != null) {
								ITileProvider tileProvider =  (ITileProvider) bundleContext.getService(serviceReference);
								org.eclipse.swt.graphics.Point tileSize = tileProvider.getTileSize();
								if(zoomInOnFirstPosition) {
									scalableZoomableRootEditPart.setZoom(tileProvider.getMaxZoomLevel());
									zoomInOnFirstPosition = false;
								}
								int zoom = scalableZoomableRootEditPart.getZoom();
//						org.eclipse.swt.graphics.Point point = new org.eclipse.swt.graphics.Point(scrollingPosition.x, scrollingPosition.y);
								org.eclipse.swt.graphics.Point point = tileProvider.getProjection().project(getWorld().getMapCenterPosition(), (1<< zoom) *  tileSize.x);
								Rectangle clientArea = ((MapLayer)getFigure()).getPaintBounds();
								int x = point.x - clientArea.width / 2;
								int y = point.y - clientArea.height / 2;
								((GeospatialGraphicalViewer)getViewer()).setScrollingPosition(new org.eclipse.draw2d.geometry.Point(x, y));
							}
						} catch (ExecutionException e) {
							Logger.getLogger(WorldEditPart.class).error("Failed to set position", e); //$NON-NLS-1$
						}
					}
				});
				lastUpdate = System.currentTimeMillis();
			}
			
			
		}

		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#providerEnabled(java.lang.String)
		 */
		public void providerEnabled(String providerID) {
			zoomInOnFirstPosition = true;
		}

		/* (non-Javadoc)
		 * @see net.sf.seesea.services.navigation.listener.IDataListener#providerDisabled(java.lang.String)
		 */
		public void providerDisabled(String providerID) {
			zoomInOnFirstPosition = true;
			
		}
	}
	
	private class AISTracker implements HandleAISMessage {

		private static final int CLASS_A_POSITION_UPDATE_RATE = 10000; // 10 seconds
		private static final int CLASS_A_ANCHOR_UPDATE_RATE = 3 * 60 * 1000; // 3 minutes

		private static final int CLASS_B_POSITION_UPDATE_RATE = 3 * 60 * 1000; // 10 seconds

		
		private Map<Integer, AISMessageTime> shipIdentifications;
		private Thread aisTrackerThread; 

		public AISTracker() {
			shipIdentifications = Collections.synchronizedMap(new HashMap<Integer, AISMessageTime>());
		}

		public void start() {
			aisTrackerThread = new Thread(new Runnable() {
				
				public void run() {
					try {
					while(true) {
						long currentTime = Calendar.getInstance().getTime().getTime();
						synchronized (AISTracker.this) {
							for (Iterator<Entry<Integer, AISMessageTime>> iterator = shipIdentifications.entrySet().iterator(); iterator.hasNext();) {
								Entry<Integer, AISMessageTime> entry = iterator.next();
								AISMessageTime aisMessageTime = entry.getValue();
								AISMessage aisMessage = aisMessageTime.getPosition();
								if(aisMessage instanceof AISMessagePositionReport) {
									AISMessagePositionReport aisMessagePositionReport = (AISMessagePositionReport) aisMessage;
									int navState = aisMessagePositionReport.getNavigationalStatus();
									if((currentTime  - aisMessageTime.getUtcTime()) > (CLASS_A_POSITION_UPDATE_RATE + 10000) && (navState == 0 || navState == 3 || navState == 4 || navState == 7 || navState == 8)) {
//										System.out.println(aisMessage);
										removePosition(iterator);
									} else if((currentTime  - aisMessageTime.getUtcTime()) > (CLASS_A_ANCHOR_UPDATE_RATE + 5000) && (navState == 1 || navState == 2 || navState == 5 || navState == 6)) {
//										System.out.println(aisMessage);
										removePosition(iterator);
									} else if((currentTime  - aisMessageTime.getUtcTime()) > (CLASS_A_ANCHOR_UPDATE_RATE + 5000)) {
//										System.out.println(aisMessage);
										removePosition(iterator);
									}
								} else if(aisMessage instanceof AISMessageClassBPositionReport) {
									if((currentTime  - aisMessageTime.getUtcTime()) > (CLASS_B_POSITION_UPDATE_RATE + 5000)) {
//										System.out.println(aisMessage);
										removePosition(iterator);
									}
								} else {
//									System.out.println(aisMessage);
									if((currentTime  - aisMessageTime.getUtcTime()) > (CLASS_B_POSITION_UPDATE_RATE + 5000)) {
										removePosition(iterator);
									}
								}
							}
						}
							Thread.sleep(1000);
					}
					} catch (InterruptedException e) {
						// nothing to do;
					}
				}

				private void removePosition(
						Iterator<Entry<Integer, AISMessageTime>> iterator) {
					iterator.remove();
					Display.getDefault().asyncExec(new Runnable() {
						
						public void run() {
							refreshChildren();
						}
					});
				}
			});
			aisTrackerThread.start();
		}
		
		public void dispose() {
			aisTrackerThread.interrupt();
		}
		
		public void handleAISMessage(final AISMessage message) {
			Display.getDefault().asyncExec(new Runnable() {

				public void run() {
					if(message instanceof AISMessage) {
						AISMessage positionReport = (AISMessage) message;
						long time = Calendar.getInstance().getTime().getTime();
						synchronized (AISTracker.this) {
							shipIdentifications.put(positionReport.getUserID(), new AISMessageTime(positionReport, time));
							refreshChildren();
						}
					
					}
				}
			});
			
		}
		
		public synchronized Collection<AISMessageTime> getAISMessagePositionReport() {
			return shipIdentifications.values();
		}
	}

//	public void addAreaMarker(AreaMarker areaMarker) {
//		areaMarkers.add(areaMarker);
//	}
//	
//	public void removeAreaMarker(AreaMarker areaMarker) {
//		areaMarkers.remove(areaMarker);
//	}
	
	public List<AreaMarker> getAreaMarkers() {
		return areaMarkers;
	}

}