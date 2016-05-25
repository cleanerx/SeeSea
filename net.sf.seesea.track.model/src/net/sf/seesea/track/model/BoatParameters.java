package net.sf.seesea.track.model;

import java.util.Map;

import net.sf.seesea.track.api.data.IBoatParameters;

public class BoatParameters implements IBoatParameters {

	private VesselConfiguration vesselConfiguration;

	public BoatParameters(VesselConfiguration vesselConfiguration) {
		this.vesselConfiguration = vesselConfiguration;
	}

	@Override
	public double getLatOffset2DepthSensor(double magneticBearing) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLonOffset2DepthSensor(double magneticBearing) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSensorOffsetToWaterline(String sensorId) {
		if(vesselConfiguration == null) {
			return 0.0D;
		}
		Map<String, DepthSensor> depthSensorOffsets = vesselConfiguration.getDepthSensorOffsets();
		if(depthSensorOffsets == null) {
			return 0.0D;
		}
		DepthSensor point3d = depthSensorOffsets.get(sensorId);
		if(point3d == null) {
			return 0.0D;
		}
		double depthOffsetWaterline;
		if(point3d.getOffsetType() != null && point3d.getOffsetType().equals("KEEL") && point3d.getOffsetKeel() != 0.0) {
			depthOffsetWaterline = point3d.getZ();
			depthOffsetWaterline+=point3d.getOffsetKeel();
		} else {
			depthOffsetWaterline = point3d.getZ();
		}
		
		if(depthOffsetWaterline > 0) {
			return depthOffsetWaterline * (-1);
		}
		return depthOffsetWaterline;
	}

}
