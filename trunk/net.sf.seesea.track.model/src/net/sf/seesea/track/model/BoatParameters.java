package net.sf.seesea.track.model;

import java.util.Map;

import net.sf.seesea.geometry.impl.Point3D;
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
		Map<String, Point3D> depthSensorOffsets = vesselConfiguration.getDepthSensorOffsets();
		if(depthSensorOffsets == null) {
			return 0.0D;
		}
		Point3D point3d = depthSensorOffsets.get(sensorId);
		if(point3d == null) {
			return 0.0D;
		}
		double depthOffsetWaterline = point3d.getZ();
		if(depthOffsetWaterline > 0) {
			return depthOffsetWaterline * (-1);
		}
		return depthOffsetWaterline;
	}

}
