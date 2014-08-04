package net.sf.seesea.rendering.chart.editpart;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Display;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.Track;
import net.sf.seesea.model.core.geo.osm.OsmPackage;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.core.physx.RelativeSpeed;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.model.util.GeoPositionFormatter;
import net.sf.seesea.model.util.GeoUtil;
import net.sf.seesea.rendering.chart.figures.MapLayer;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.services.navigation.listener.ISpeedListener;

public class CursorPositionListener implements Adapter,
		ISpeedListener, IPositionListener {

	private DecimalFormat nauticalMileFormat = new DecimalFormat("0.#");

	private DecimalFormat bearingFormat = new DecimalFormat("0");

	private final WorldEditPart worldEditPart;

	
	public CursorPositionListener(WorldEditPart worldEditPart) {
		this.worldEditPart = worldEditPart;
	}
	
	private GeoPosition cursorPosition;
	private GeoPosition shipPosition;

	private Date eta;

	private RelativeSpeed speed;

	private long cursorUpdate;

	public void providerEnabled(String providerID) {
		// TODO Auto-generated method stub

	}

	public void providerDisabled(String providerID) {
		// TODO Auto-generated method stub

	}

	public void notifyChanged(Notification notification) {
		if (notification.getNotifier() instanceof World
				&& notification.getEventType() == Notification.SET
				&& notification.getFeature() instanceof EReference) {
			if(((EReference)notification.getFeature()).getFeatureID() == OsmPackage.WORLD__CURSOR_POSITION) {
				cursorPosition = (GeoPosition) notification
						.getNewValue();
			}
			update();
		}
	}

	private void update() {
		StringBuffer coordinateLat = new StringBuffer(); 
		if(shipPosition != null && cursorPosition != null) {
			GeoPositionFormatter.formatCoordinateMinutesWithSphere(coordinateLat, cursorPosition.getLatitude());
			coordinateLat.append("\n");
			GeoPositionFormatter.formatCoordinateMinutesWithSphere(coordinateLat, cursorPosition.getLongitude());
			double distance = GeoUtil.getDistance(shipPosition, cursorPosition);
			coordinateLat.append("\n");
			coordinateLat.append(nauticalMileFormat.format(distance));
			coordinateLat.append("nm");
			double bearing = GeoUtil.getBearing(shipPosition, cursorPosition);
			coordinateLat.append(" / ");
			coordinateLat.append(bearingFormat.format(bearing));
			coordinateLat.append("\u00B0");
			if(speed != null) {
				double speed2 = speed.getValue().getSpeed();
				double time = GeoUtil.getDistance(shipPosition, cursorPosition) / speed2;
				int hours = (int) time;
				time = (time - hours) * 60;
				int minutes = (int) time;
				time = (time - minutes) * 60;
				int seconds = (int) time;
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.HOUR, hours);
				calendar.add(Calendar.MINUTE, minutes);
				calendar.add(Calendar.SECOND, seconds);
				Date eta = calendar.getTime();
				coordinateLat.append("\n");
				coordinateLat.append("ETA:");
				coordinateLat.append(eta);
			}
		}
		((MapLayer) worldEditPart.getFigure()).setCursorPosition(coordinateLat.toString());
			Display.getDefault().asyncExec(new Runnable() {
				
				public void run() {
					worldEditPart.refreshVisuals();
				}
			});
	}

	public Notifier getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTarget(Notifier newTarget) {
		// TODO Auto-generated method stub

	}

	public boolean isAdapterForType(Object type) {
		// TODO Auto-generated method stub
		return false;
	}

	public void notify(RelativeSpeed speed, String source) {
		String speedUnit = ""; //$NON-NLS-1$
		if (SpeedType.COG.equals(speed.getKey())) {
			if (speed.getValue() != null) {
				if (SpeedUnit.N.equals(speed.getValue().getSpeedUnit())) {
					speedUnit = "kn"; //$NON-NLS-1$

				} else if (SpeedUnit.K.equals(speed.getValue().getSpeedUnit())) {
					speedUnit = "km/h"; //$NON-NLS-1$
				} else if (SpeedUnit.M.equals(speed.getValue().getSpeedUnit())) {
					speedUnit = "m/h"; //$NON-NLS-1$
				}
//				if(shipPosition != null && cursorPosition != null) {
				this.speed = speed;
				update();
//				}
				// speedOverGround.setValue(speedDecimalFormat.format(sensorData.getValue().getSpeed())
				// + speedUnit);
			}
		}
	}

	public void notify(MeasuredPosition3D sensorData, String source) {
		shipPosition = EcoreUtil.copy(sensorData);
	}

}
