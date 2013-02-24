package net.sf.seesea.rendering.chart.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.figures.ShipFigure;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.tileservice.projections.IMapProjection;
import nl.esi.metis.aisparser.AISMessageClassBPositionReport;
import nl.esi.metis.aisparser.AISMessagePositionReport;
import nl.esi.metis.aisparser.PositionInfo;
import nl.esi.metis.aisparser.UtilsNavStatus;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class AISEditPart extends AbstractGraphicalEditPart {

	private IMapProjection mapProjection;
	private ServiceReference<IMapProjection> mapProjectionServiceReference;
	private Label tooltipLabel;
	private net.sf.seesea.rendering.chart.editpart.AISEditPart.UpdateMapZoomLevelPropertyChangeListener propertyChangeListener;

	@Override
	protected IFigure createFigure() {
		ShipFigure _figure = new ShipFigure();
		tooltipLabel = new Label(""); //$NON-NLS-1$
		_figure.setToolTip(tooltipLabel);
		return _figure;
	}

	@Override
	protected void createEditPolicies() {
		// nothing to do
	}
	
	@Override
	public void activate() {
		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		mapProjectionServiceReference = bundleContext.getServiceReference(IMapProjection.class);
		mapProjection = bundleContext.getService(mapProjectionServiceReference);

		propertyChangeListener = new UpdateMapZoomLevelPropertyChangeListener();
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().addPropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().addPropertyChangeListener(propertyChangeListener);
		
		super.activate();
	}
	
	@Override
	public void deactivate() {
		((GeospatialGraphicalViewer)getViewer()).getHorizontalRangeModel().removePropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer)getViewer()).getVerticalRangeModel().removePropertyChangeListener(propertyChangeListener);

		mapProjection = null;
		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		bundleContext.ungetService(mapProjectionServiceReference);
		super.deactivate();
	}
	
	@Override
	protected void refreshVisuals() {
		AISMessageTime messageTime = (AISMessageTime) getModel();
		if(messageTime.getPosition() instanceof PositionInfo) {
			PositionInfo positionReport = (PositionInfo) messageTime.getPosition();
//			System.out.println(positionReport.getLatitudeInDegrees() + ":" + positionReport.getLongitudeInDegrees());
			BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
			ServiceReference<IMapProjection> mapProjectionServiceReferenceX = bundleContext.getServiceReference(IMapProjection.class);
			IMapProjection mapProjectionX = bundleContext.getService(mapProjectionServiceReferenceX);
			double latitude2Value = mapProjectionX.latitude2Value(positionReport.getLatitudeInDegrees(), (1<< ((ScalableZoomableRootEditPart) getRoot()).getZoom()) *  256);
			double longitude2Value = mapProjectionX.longitude2Value(positionReport.getLongitudeInDegrees(), (1<< ((ScalableZoomableRootEditPart) getRoot()).getZoom()) *  256) ;
			bundleContext.ungetService(mapProjectionServiceReferenceX);

			if(positionReport instanceof AISMessagePositionReport) {
				AISMessagePositionReport positionReport2 = (AISMessagePositionReport)positionReport;
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("COG: ");
				stringBuilder.append(positionReport2.getCourseOverGround() / 10.0);
				
				((ShipFigure)getFigure()).setCOGOrientation(positionReport2.getCourseOverGround() / 10.0);
				
				stringBuilder.append("\u00B0\n");
				stringBuilder.append("SOG: ");
				stringBuilder.append(positionReport2.getSpeedOverGround() / 10.0);
				stringBuilder.append("kn\n");
				stringBuilder.append("State:");
				stringBuilder.append(UtilsNavStatus.toString(positionReport2.getNavigationalStatus()));
				if(positionReport2.getRateOfTurn() != 128) {
					stringBuilder.append("\n");
					stringBuilder.append("Rate of turn:");
					stringBuilder.append(positionReport2.getRateOfTurn());
					tooltipLabel.setText(stringBuilder.toString());
				}
			} else if(positionReport instanceof AISMessageClassBPositionReport) {
				AISMessageClassBPositionReport classBPositionReport = (AISMessageClassBPositionReport) positionReport;
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("COG: ");
				stringBuilder.append(classBPositionReport.getCourseOverGround() / 10.0);
				((ShipFigure)getFigure()).setCOGOrientation(classBPositionReport.getCourseOverGround() / 10.0);
				stringBuilder.append("\u00B0\n");
				stringBuilder.append("SOG: ");
				stringBuilder.append(classBPositionReport.getSpeedOverGround() / 10.0);
				stringBuilder.append("kn");
				tooltipLabel.setText(stringBuilder.toString());
			}
//			System.out.println(latitude2Value + ":" + longitude2Value);
			getFigure().setBounds(new PrecisionRectangle(longitude2Value - 10, latitude2Value - 10, 20, 20));
		}
		

		super.refreshVisuals();
	}
	
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


}
