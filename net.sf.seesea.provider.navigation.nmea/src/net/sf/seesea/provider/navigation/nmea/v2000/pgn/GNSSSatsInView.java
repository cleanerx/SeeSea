package net.sf.seesea.provider.navigation.nmea.v2000.pgn;

import java.util.Arrays;

import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.core.physx.SatelliteInfo;
import net.sf.seesea.model.core.physx.SatellitesVisible;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Angle;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.AngleSigned;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.DBRelative;

public class GNSSSatsInView extends SequencedPGN {

	private SatellitesVisible satellitesVisible;
	
	public GNSSSatsInView(int[] data) {
		super(data, 129540, false, 6, 1000, 1);
		int countVisible = data[2];
		satellitesVisible = PhysxFactory.eINSTANCE.createSatellitesVisible();
		if(data.length < countVisible * 12 + 3) {
			System.out.println("incomplete");
			return;
		}
		for (int i = 0 ; i < countVisible ; i++) {
			SatelliteInfo satelliteInfo = PhysxFactory.eINSTANCE.createSatelliteInfo();
			satelliteInfo.setId(data[3 + i * 12]);
			if(satelliteInfo.getId() > 0 && satelliteInfo.getId() <= 96) {
				AngleSigned angleSigned = new AngleSigned(Arrays.copyOfRange(data, 3 + i * 12 + 1, 3 + i * 12 + 3));
				satelliteInfo.setElevation((int) angleSigned.getValue());
				Angle azimuth = new Angle(Arrays.copyOfRange(data, 3 + i * 12 + 3, 3 + i * 12 + 5));
				satelliteInfo.setAzimuth((int) azimuth.getValue());
				DBRelative dbRelative = new DBRelative(Arrays.copyOfRange(data, 3 + i * 12 + 5, 3 + i * 12 + 7));
				satelliteInfo.setSignalStrength((int) dbRelative.getValue());
				satellitesVisible.getSatelliteInfo().add(satelliteInfo);
			}
		}
	}

	public SatellitesVisible getSatellitesVisible() {
		return satellitesVisible;
	}
	
	
	

}
