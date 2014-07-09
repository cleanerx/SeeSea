package net.sf.seesea.provider.navigation.adm;

import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.adm.data.FAT;
import net.sf.seesea.provider.navigation.adm.data.IMGHeader;
import net.sf.seesea.provider.navigation.adm.data.TRKHeader;
import net.sf.seesea.provider.navigation.adm.data.TrackPointADM;

public class ADMMeasurementDispatcher implements IADMListener {

	@Override
	public void notifyIMGHeader(IMGHeader imgHeader) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyPreFATHeader(PreFATHeader preFATHeader) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyFATBlock(FAT fatBlock) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTRKHeader(TRKHeader trkHeader) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTrackPoint(TrackPointADM trackPointADM) {
		for (Measurement measurement : extractMeasurements(trackPointADM)) {
//			notifyListeners(measurement);
		}
	}
	
	private List<Measurement> extractMeasurements(TrackPointADM trackPointADM) {
		List<Measurement> measurements = new ArrayList<Measurement>();
		MeasuredPosition3D position3d = GeoFactory.eINSTANCE.createMeasuredPosition3D();
		Latitude latitude2 = GeoFactory.eINSTANCE.createLatitude();
		latitude2.setDecimalDegree(trackPointADM.getLat());
		position3d.setLatitude(latitude2);
		Longitude longitude2 = GeoFactory.eINSTANCE.createLongitude();
		longitude2.setDecimalDegree(trackPointADM.getLon());
		position3d.setLongitude(longitude2);
		position3d.setValid(true);
		
		Depth depth2 = GeoFactory.eINSTANCE.createDepth();
		depth2.setDepth(((double)trackPointADM.getDepth()) / 100);
		depth2.setValid(true);
		
		measurements.add(position3d);
		measurements.add(depth2);
		return measurements;
	}


//	@Override
	protected String getProviderName() {
		return "ADM"; //$NON-NLS-1$
	}

}
