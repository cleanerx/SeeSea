package net.sf.seesea.services.navigation;

import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.util.GeoUtil;
import net.sf.seesea.model.util.IModel;
import net.sf.seesea.services.navigation.listener.IPositionListener;

public class VirtualTrip implements IPositionListener {

	private MeasuredPosition3D lastPosition;
	private IModel model;

	public VirtualTrip() {
	}
	
	@Override
	public void providerEnabled(String providerID) {
		lastPosition = null;
		World world = model.loadModel();
		world.setTrip(0.0);
	}

	@Override
	public void providerDisabled(String providerID) {

	}

	@Override
	public void notify(MeasuredPosition3D sensorData, String source) {
		if(lastPosition == null && sensorData != null && sensorData.getLatitude() != null && sensorData.getLongitude() != null)  {
			lastPosition = sensorData;
		} else if(lastPosition != null && sensorData.getLatitude() != null && model != null) {
			Double adddistance = GeoUtil.getDistance(lastPosition.getLatitude().getDecimalDegree(), sensorData.getLatitude().getDecimalDegree(), lastPosition.getLongitude().getDecimalDegree(), sensorData.getLongitude().getDecimalDegree());
			if(!adddistance.isNaN() && adddistance > 0.00001) {
				World world = model.loadModel();
				world.setTrip(world.getTrip() + adddistance);
				lastPosition = sensorData;
				
			}
		}
	}
	
	public void bindModel(IModel model){
		this.model = model;
	}
	
	public void unbindModel(IModel model){
		this.model = null;
	}


}
