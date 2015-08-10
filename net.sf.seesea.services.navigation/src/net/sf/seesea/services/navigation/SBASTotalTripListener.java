package net.sf.seesea.services.navigation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import net.sf.seesea.model.core.geo.osm.OsmPackage;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.core.physx.Distance;
import net.sf.seesea.model.core.physx.DistanceType;
import net.sf.seesea.model.core.physx.LengthUnit;
import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.util.IModel;
import net.sf.seesea.services.navigation.listener.ITotalLogListener;

public class SBASTotalTripListener implements Adapter {

	
	private List<ITotalLogListener> _tripLogListeners;
	private IModel model;
	
	public SBASTotalTripListener() {
		_tripLogListeners = new ArrayList<ITotalLogListener>(1);
	}

	public void activate() {
		World loadModel = model.loadModel();
		loadModel.eAdapters().add(this);
		
		Distance distance = PhysxFactory.eINSTANCE.createDistance();
		distance.setDistanceType(DistanceType.TOTAL);
		distance.setUnit(LengthUnit.NAUTICAL_MILE);
		distance.setValue(model.loadModel().getTotalTrip());
		distance.setSensorID("GPS");
		for (ITotalLogListener tripLogListener : _tripLogListeners) {
			tripLogListener.notify(distance, "virtual");
		}
	}
	
	public void deactivate() {
		model.loadModel().eAdapters().remove(this);
	}
	
	public synchronized void bindModel(IModel model){
		this.model = model;
	}
	
	public synchronized void unbindModel(IModel model){
		this.model = null;
	}
	
	public synchronized void attachTripLogListener(ITotalLogListener listener) {
		_tripLogListeners.add(listener);
		if(model != null) {			
			Distance distance = PhysxFactory.eINSTANCE.createDistance();
			distance.setDistanceType(DistanceType.TOTAL);
			distance.setUnit(LengthUnit.NAUTICAL_MILE);
			distance.setValue(model.loadModel().getTotalTrip());
			distance.setSensorID("GPS");
			listener.notify(distance, "virtual");
		}
	}

	public synchronized void detachTripLogListener(ITotalLogListener listener) {
//		listener.providerDisabled(getProviderName());
		_tripLogListeners.remove(listener);
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier() instanceof World
				&& notification.getEventType() == Notification.SET && OsmPackage.WORLD__TOTAL_TRIP == notification.getFeatureID(World.class)) {
			double tripDistance = notification.getNewDoubleValue();
			Distance distance = PhysxFactory.eINSTANCE.createDistance();
			distance.setDistanceType(DistanceType.TOTAL);
			distance.setUnit(LengthUnit.NAUTICAL_MILE);
			distance.setValue(tripDistance);
			distance.setSensorID("GPS");

			for (ITotalLogListener tripLogListener : _tripLogListeners) {
				tripLogListener.notify(distance, "virtual");
			}
		}
		
	}

	@Override
	public Notifier getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAdapterForType(Object type) {
		// TODO Auto-generated method stub
		return false;
	}

}
