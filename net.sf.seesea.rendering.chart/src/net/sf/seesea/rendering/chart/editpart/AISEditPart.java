package net.sf.seesea.rendering.chart.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.figures.ShipFigure;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.tileservice.projections.IMapProjection;
import nl.esi.metis.aisparser.AISMessage04;
import nl.esi.metis.aisparser.AISMessage05;
import nl.esi.metis.aisparser.AISMessage21;
import nl.esi.metis.aisparser.AISMessage24PartA;
import nl.esi.metis.aisparser.AISMessage24PartB;
import nl.esi.metis.aisparser.AISMessageClassBPositionReport;
import nl.esi.metis.aisparser.AISMessagePositionReport;
import nl.esi.metis.aisparser.PositionInfo;
import nl.esi.metis.aisparser.UtilsDimensions30;
import nl.esi.metis.aisparser.UtilsEta;
import nl.esi.metis.aisparser.UtilsNavStatus;
import nl.esi.metis.aisparser.UtilsShipType8;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.internal.figures.CircleFigure;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * This edit part shows ships received by AIS and manages static data that is being received
 *
 */
public class AISEditPart extends AbstractGraphicalEditPart {

	private net.sf.seesea.rendering.chart.editpart.AISEditPart.UpdateMapZoomLevelPropertyChangeListener propertyChangeListener;
	private int mmsi;
	private String callSign;
	private String name;
	private String destination;
	private String dimension;
	private int maximumPresentStaticDraught;
	private String shipTypeToString;
	private Date eta;
	private ServiceTracker<IMapProjection,IMapProjection> mapProjectionTracker;

	@Override
	protected IFigure createFigure() {
		mmsi = ((AISMessageTime) getModel()).getPosition().getUserID();
		// this is a UTC report station
		if (((AISMessageTime) getModel()).getPosition() instanceof AISMessage04) {
			RoundedRectangle figure = new RoundedRectangle();
			figure.setForegroundColor(ColorConstants.red);
			figure.setFill(false);
			return figure;
		} else if (((AISMessageTime) getModel()).getPosition() instanceof AISMessage21) {
			CircleFigure figure = new CircleFigure(10);
			figure.setForegroundColor(ColorConstants.orange);
			return figure;
		} else {
			ShipFigure figure = new ShipFigure();
			return figure;
		}
	}

	@Override
	protected void createEditPolicies() {
		// nothing to do
	}

	@Override
	public void activate() {
		BundleContext bundleContext = SeeSeaUIActivator.getDefault()
				.getBundle().getBundleContext();
		mapProjectionTracker = new ServiceTracker<IMapProjection,IMapProjection>(bundleContext, IMapProjection.class, null);
		mapProjectionTracker.open();

		propertyChangeListener = new UpdateMapZoomLevelPropertyChangeListener();
		((GeospatialGraphicalViewer) getViewer()).getHorizontalRangeModel()
				.addPropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer) getViewer()).getVerticalRangeModel()
				.addPropertyChangeListener(propertyChangeListener);

		super.activate();
	}

	@Override
	public void deactivate() {
		mapProjectionTracker.close();
		((GeospatialGraphicalViewer) getViewer()).getHorizontalRangeModel()
				.removePropertyChangeListener(propertyChangeListener);
		((GeospatialGraphicalViewer) getViewer()).getVerticalRangeModel()
				.removePropertyChangeListener(propertyChangeListener);

		super.deactivate();
	}

	@Override
	protected void refreshVisuals() {
		AISMessageTime messageTime = (AISMessageTime) getModel();
		if(mapProjectionTracker != null ){
			IMapProjection mapProjectionX = mapProjectionTracker.getService();
		if (messageTime.getPosition() instanceof PositionInfo && mapProjectionX != null) {
			PositionInfo positionReport = (PositionInfo) messageTime
					.getPosition();
			int zoom = ((ScalableZoomableRootEditPart) getRoot()).getZoom();
			double latitude2Value = mapProjectionX.latitude2Value(
					positionReport.getLatitudeInDegrees(), (1 << zoom) * 256);
			double longitude2Value = mapProjectionX.longitude2Value(
					positionReport.getLongitudeInDegrees(), (1 << zoom) * 256);

			// build the tooltip for the figure
			if (positionReport instanceof AISMessagePositionReport) {
				AISMessagePositionReport positionReport2 = (AISMessagePositionReport) positionReport;
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(Messages.getString("AISEditPart.mmsi")); //$NON-NLS-1$
				stringBuilder.append(positionReport2.getUserID());
				stringBuilder.append(" (");//$NON-NLS-1$
				stringBuilder.append(MMSI.getCountry(positionReport2
						.getUserID()));
				stringBuilder.append(")"); //$NON-NLS-1$
				if(name != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.name"));  //$NON-NLS-1$
					stringBuilder.append(name); //$NON-NLS-1$
				}
				if(callSign != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.callsign"));  //$NON-NLS-1$
					stringBuilder.append(callSign); //$NON-NLS-1$
				}
				stringBuilder.append("\n"); //$NON-NLS-1$
				stringBuilder.append(Messages.getString("AISEditPart.cog")); //$NON-NLS-1$
				stringBuilder
						.append(positionReport2.getCourseOverGround() / 10.0);

				((ShipFigure) getFigure()).setCOGOrientation(positionReport2
						.getCourseOverGround() / 10.0);

				stringBuilder.append("\u00B0"); //$NON-NLS-1$
				stringBuilder.append("\n"); //$NON-NLS-1$
				stringBuilder.append(Messages.getString("AISEditPart.sog")); //$NON-NLS-1$
				stringBuilder
						.append(positionReport2.getSpeedOverGround() / 10.0);
				stringBuilder.append(Messages.getString("AISEditPart.knots")); //$NON-NLS-1$
				stringBuilder.append("\n"); //$NON-NLS-1$
				stringBuilder.append(Messages.getString("AISEditPart.state")); //$NON-NLS-1$
				stringBuilder.append(UtilsNavStatus.toString(positionReport2
						.getNavigationalStatus()));
				if (positionReport2.getRateOfTurn() != 128) {
					int rateOfTurn = positionReport2.getRateOfTurn();
					if (rateOfTurn != -128) {
						stringBuilder.append("\n"); //$NON-NLS-1$
						stringBuilder.append(Messages
								.getString("AISEditPart.rateofturn")); //$NON-NLS-1$
						if (rateOfTurn == -127) {
							stringBuilder.append("< 5\u00B0 / 30sec"); //$NON-NLS-1$
						} else if (rateOfTurn == 127) {
							stringBuilder.append("> 5\u00B0 / 30sec"); //$NON-NLS-1$
						} else {
							stringBuilder.append(Math.sqrt(rateOfTurn) * 4.733);
							stringBuilder.append("\u00B0/min"); //$NON-NLS-1$
						}
					}
				}
				if(dimension != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.dimension"));  //$NON-NLS-1$
					stringBuilder.append(dimension); //$NON-NLS-1$
				}
				if(eta != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.eta"));  //$NON-NLS-1$
					stringBuilder.append(eta); //$NON-NLS-1$
				}
				if(maximumPresentStaticDraught != 0.0) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.draught"));  //$NON-NLS-1$
					stringBuilder.append(new DecimalFormat("##.#").format(maximumPresentStaticDraught / 10.0)); //$NON-NLS-1$
					stringBuilder.append("m"); //$NON-NLS-1$
				}
				if(destination != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.destination")); //$NON-NLS-1$
					stringBuilder.append(destination); //$NON-NLS-1$
				}
				if(shipTypeToString != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.shiptype"));  //$NON-NLS-1$
					stringBuilder.append(shipTypeToString); //$NON-NLS-1$
				}

				
				if (getFigure().getToolTip() instanceof Label) {
					((Label) getFigure().getToolTip()).setText(stringBuilder
							.toString());
				}
				getFigure().setBounds(
						new PrecisionRectangle(longitude2Value - 13,
								latitude2Value - 13, 26, 26));
			} else if (positionReport instanceof AISMessageClassBPositionReport) {
				AISMessageClassBPositionReport classBPositionReport = (AISMessageClassBPositionReport) positionReport;
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(Messages.getString("AISEditPart.mmsi")); //$NON-NLS-1$
				stringBuilder.append(classBPositionReport.getUserID());
				stringBuilder.append(" (");//$NON-NLS-1$
				stringBuilder.append(MMSI.getCountry(classBPositionReport
						.getUserID()));
				stringBuilder.append(")"); //$NON-NLS-1$
				if(name != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.name"));  //$NON-NLS-1$
					stringBuilder.append(name); //$NON-NLS-1$
				}
				if(callSign != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.callsign"));  //$NON-NLS-1$
					stringBuilder.append(callSign); //$NON-NLS-1$
				}
				stringBuilder.append("\n"); //$NON-NLS-1$
				stringBuilder.append(Messages.getString("AISEditPart.cog")); //$NON-NLS-1$
				stringBuilder
						.append(classBPositionReport.getCourseOverGround() / 10.0);
				((ShipFigure) getFigure())
						.setCOGOrientation(classBPositionReport
								.getCourseOverGround() / 10.0);
				stringBuilder.append("\u00B0"); //$NON-NLS-1$
				stringBuilder.append("\n"); //$NON-NLS-1$
				stringBuilder.append(Messages.getString("AISEditPart.sog")); //$NON-NLS-1$
				stringBuilder
						.append(classBPositionReport.getSpeedOverGround() / 10.0);
				stringBuilder.append(Messages.getString("AISEditPart.knots")); //$NON-NLS-1$
				if(classBPositionReport.getTrueHeading() != 511) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.trueheading")); //$NON-NLS-1$
					stringBuilder
							.append(classBPositionReport.getTrueHeading() / 10.0);
					stringBuilder.append("\u00B0"); //$NON-NLS-1$
				}
				if(dimension != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.dimension")); //$NON-NLS-1$
					stringBuilder.append(dimension); //$NON-NLS-1$
				}
				if(eta != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.eta"));  //$NON-NLS-1$
					stringBuilder.append(eta); //$NON-NLS-1$
				}
				if(maximumPresentStaticDraught != 0.0) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.draught")); //$NON-NLS-1$
					stringBuilder.append(new DecimalFormat("##.#").format(maximumPresentStaticDraught / 10.0)); //$NON-NLS-1$
					stringBuilder.append("m"); //$NON-NLS-1$
				}
				if(destination != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.destination"));  //$NON-NLS-1$
					stringBuilder.append(destination); //$NON-NLS-1$
				}
				if(shipTypeToString != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.shiptype")); //$NON-NLS-1$
					stringBuilder.append(shipTypeToString); //$NON-NLS-1$
				}
				
				if (getFigure().getToolTip() instanceof Label) {
					((Label) getFigure().getToolTip()).setText(stringBuilder
							.toString());
				}
				getFigure().setBounds(
						new PrecisionRectangle(longitude2Value - 13,
								latitude2Value - 13, 26, 26));
			} else if (mmsi == messageTime.getPosition().getUserID()) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(Messages.getString("AISEditPart.mmsi")); //$NON-NLS-1$
				stringBuilder.append(messageTime.getPosition().getUserID());
				if (messageTime.getPosition() instanceof AISMessage24PartA) {
					AISMessage24PartA msg24b  = (AISMessage24PartA) messageTime.getPosition();
					name = msg24b.getName();
				} else if (messageTime.getPosition() instanceof AISMessage24PartB) {
					AISMessage24PartB msg24b  = (AISMessage24PartB) messageTime.getPosition();
					shipTypeToString = UtilsShipType8.shipTypeToString(msg24b.getTypeOfShipAndCargoType());
				}
				if(name != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.name"));  //$NON-NLS-1$
					stringBuilder.append(name); //$NON-NLS-1$
				}
				if(shipTypeToString != null) {
					stringBuilder.append("\n"); //$NON-NLS-1$
					stringBuilder.append(Messages.getString("AISEditPart.shiptype")); //$NON-NLS-1$
					stringBuilder.append(shipTypeToString); //$NON-NLS-1$
				}
				if (getFigure().getToolTip() instanceof Label) {
					((Label) getFigure().getToolTip()).setText(stringBuilder
							.toString());
				}

				getFigure().setBounds(
						new PrecisionRectangle(longitude2Value - 13,
								latitude2Value - 13, 26, 26));
			}
		} else if (messageTime.getPosition() instanceof AISMessage05 && mmsi == messageTime.getPosition().getUserID()) {
			AISMessage05 aisMessage05 = (AISMessage05) messageTime.getPosition();
			callSign = aisMessage05.getCallSign();
			name = aisMessage05.getName();
			destination = aisMessage05.getDestination();
			dimension = UtilsDimensions30.toString(aisMessage05.getDimension());
			maximumPresentStaticDraught = aisMessage05.getMaximumPresentStaticDraught();
			shipTypeToString = UtilsShipType8.shipTypeToString(aisMessage05.getTypeOfShipAndCargoType());
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, UtilsEta.getDay(aisMessage05.getEta()));
			calendar.set(Calendar.MONTH, UtilsEta.getMonth(aisMessage05.getEta()) -1);
			calendar.set(Calendar.HOUR_OF_DAY, UtilsEta.getHour(aisMessage05.getEta()));
			calendar.set(Calendar.MINUTE, UtilsEta.getMinute(aisMessage05.getEta()));
			eta = calendar.getTime();
		}
		}

		super.refreshVisuals();
	}

	/**
	 * repaints the figure to draw its position
	 */
	private class UpdateMapZoomLevelPropertyChangeListener implements
			PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			if ("minimum".equals(evt.getPropertyName()) || "maximum".equals(evt.getPropertyName())) { //$NON-NLS-1$//$NON-NLS-2$
				refreshVisuals();
			}
		}
	}

}
