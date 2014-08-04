package net.sf.seesea.services.navigation;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Distance;
import net.sf.seesea.model.core.physx.DistanceType;
import net.sf.seesea.model.core.physx.LengthUnit;
import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.util.GeoUtil;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.services.navigation.listener.ITotalLogListener;
import net.sf.seesea.services.navigation.listener.ITripLogListener;


public class VirtualTotalTrip implements IPositionListener {

	private double tripDistance;
	private MeasuredPosition3D lastPosition;
	private List<ITripLogListener> _tripLogListeners;

	public VirtualTotalTrip() {
		_tripLogListeners = new ArrayList<ITripLogListener>();
		tripDistance = 0.0;
	}
	
	@Override
	public void providerEnabled(String providerID) {
//		tripDistance = 0.0;
//		lastPosition = null;
	}

	@Override
	public void providerDisabled(String providerID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notify(MeasuredPosition3D sensorData, String source) {
		if(lastPosition == null && sensorData != null && sensorData.getLatitude() != null && sensorData.getLongitude() != null)  {
			lastPosition = sensorData;
		} else if(lastPosition != null && sensorData.getLatitude() != null) {
			Double adddistance = GeoUtil.getDistance(lastPosition.getLatitude().getDecimalDegree(), sensorData.getLatitude().getDecimalDegree(), lastPosition.getLongitude().getDecimalDegree(), sensorData.getLongitude().getDecimalDegree());
//			System.out.println(adddistance);
			if(!adddistance.isNaN() && adddistance > 0.00001) {
				tripDistance+= adddistance;
				Distance distance = PhysxFactory.eINSTANCE.createDistance();
				distance.setDistanceType(DistanceType.TRIP);
				distance.setUnit(LengthUnit.NAUTICAL_MILE);
				distance.setValue(tripDistance);
				distance.setSensorID("GPS");
				for (ITripLogListener tripLogListener : _tripLogListeners) {
					tripLogListener.notify(distance, "virtual");
				}
				lastPosition = sensorData;
				
			}
		}
	}
	
	public synchronized void attachTripLogListener(ITripLogListener listener) {
		_tripLogListeners.add(listener);
	}

	public synchronized void detachTripLogListener(ITripLogListener listener) {
//		listener.providerDisabled(getProviderName());
		_tripLogListeners.remove(listener);
	}

}
