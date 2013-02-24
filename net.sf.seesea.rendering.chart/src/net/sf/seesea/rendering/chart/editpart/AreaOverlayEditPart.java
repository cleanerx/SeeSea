package net.sf.seesea.rendering.chart.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.editor.AreaMarker;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class AreaOverlayEditPart extends AbstractGraphicalEditPart {

	private net.sf.seesea.rendering.chart.editpart.AreaOverlayEditPart.UpdateMapZoomLevelPropertyChangeListener propertyChangeListener;
	private Label tooltipLabel;

	/**
	 * repaints the figure to draw its position
	 */
	private class UpdateMapZoomLevelPropertyChangeListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			if("minimum".equals(evt.getPropertyName()) || "maximum".equals(evt.getPropertyName())) {  //$NON-NLS-1$//$NON-NLS-2$
				refreshVisuals();
			}
		}
	}

	@Override
	protected IFigure createFigure() {
		Polygon polygon = new Polygon();
		polygon.setFill(true);
		polygon.setAlpha(100);
		polygon.setForegroundColor(ColorConstants.red);
		polygon.setBackgroundColor(ColorConstants.orange);
		tooltipLabel = new Label();
		tooltipLabel.setText(((AreaMarker)getModel()).getName());
		polygon.setToolTip(tooltipLabel);
		return polygon;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy("selection", new DownloadEditPolicy());
	}
	
	@Override
	protected void refreshVisuals() {
		ScalableZoomableRootEditPart scalableZoomableRootEditPart = (ScalableZoomableRootEditPart) getRoot();
		Viewport viewport = (Viewport) scalableZoomableRootEditPart.getFigure();
		int zoom = scalableZoomableRootEditPart.getZoom();

		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference<IMapProjection> mapProjectionServiceReferenceX = bundleContext.getServiceReference(IMapProjection.class);
		IMapProjection mapProjectionX = bundleContext.getService(mapProjectionServiceReferenceX);
		
		Polygon polygon = (Polygon)getFigure();
		polygon.getPoints().removeAllPoints();

		AreaMarker areaMarker = (AreaMarker) getModel();
		List<GeoPosition> areaBounds = areaMarker.getAreaBounds();
		if(areaBounds.size() == 2) {
			double lat1 = areaBounds.get(0).getLatitude().getDecimalDegree();
			double lon1 = areaBounds.get(0).getLongitude().getDecimalDegree();
			double latitude2Value1 = mapProjectionX.latitude2Value(lat1, (1<< zoom) *  256);
			double longitude2Value1 = mapProjectionX.longitude2Value(lon1, (1<< zoom) *  256) ;

			double lat2 = areaBounds.get(1).getLatitude().getDecimalDegree();
			double lon2 = areaBounds.get(1).getLongitude().getDecimalDegree();
			double latitude2Value2 = mapProjectionX.latitude2Value(lat2, (1<< zoom) *  256);
			double longitude2Value2 = mapProjectionX.longitude2Value(lon2, (1<< zoom) *  256) ;
			polygon.addPoint(new Point(longitude2Value1, latitude2Value2));
			polygon.addPoint(new Point(longitude2Value1, latitude2Value1));
			polygon.addPoint(new Point(longitude2Value2, latitude2Value1));
			polygon.addPoint(new Point(longitude2Value2, latitude2Value2));
		} else {
			for (GeoPosition geoPosition : areaBounds) {
				double lat1 = geoPosition.getLatitude().getDecimalDegree();
				double lon1 = geoPosition.getLongitude().getDecimalDegree();
				double latitude2Value1 = mapProjectionX.latitude2Value(lat1, (1<< zoom) *  256);
				double longitude2Value1 = mapProjectionX.longitude2Value(lon1, (1<< zoom) *  256) ;
				polygon.addPoint(new Point(longitude2Value1, latitude2Value1));
			}
		}
		
		polygon.invalidateTree();
		polygon.repaint();

	}
	
	@Override
	public void activate() {
		propertyChangeListener = new UpdateMapZoomLevelPropertyChangeListener();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().addPropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().addPropertyChangeListener(propertyChangeListener);

		super.activate();
	}

	@Override
	public void deactivate() {
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().removePropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().removePropertyChangeListener(propertyChangeListener);
		super.deactivate();
	}
	
	@Override
	public Command getCommand(Request request) {
		AreaMarker areaMarker = (AreaMarker) getModel();
		return new DownloadCacheTilesCommand(areaMarker.getId(), areaMarker.getUrl());
	}
	
}
