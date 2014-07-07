package net.sf.seesea.provider.navigation.adm;

import net.sf.seesea.provider.navigation.adm.data.FAT;
import net.sf.seesea.provider.navigation.adm.data.IMGHeader;
import net.sf.seesea.provider.navigation.adm.data.TRKHeader;
import net.sf.seesea.provider.navigation.adm.data.TrackPointADM;

public class TrackPointReader implements IADMListener {

	private boolean trackPointsPresent = false;
	private boolean validReader = false;

	@Override
	public void notifyIMGHeader(IMGHeader imgHeader) {
		validReader  = true;

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
		trackPointsPresent  = true;
	}

	public boolean isTrackPointsPresent() {
		return trackPointsPresent;
	}

	public boolean isValidReader() {
		return validReader;
	}
	
	

}
