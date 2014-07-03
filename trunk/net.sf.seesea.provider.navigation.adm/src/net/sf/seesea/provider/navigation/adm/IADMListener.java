package net.sf.seesea.provider.navigation.adm;

import net.sf.seesea.provider.navigation.adm.data.FAT;
import net.sf.seesea.provider.navigation.adm.data.IMGHeader;
import net.sf.seesea.provider.navigation.adm.data.TRKHeader;
import net.sf.seesea.provider.navigation.adm.data.TrackPointADM;

public interface IADMListener {
	
	void notifyIMGHeader(IMGHeader imgHeader);

	void notifyPreFATHeader(PreFATHeader preFATHeader);

	void notifyFATBlock(FAT fatBlock);

	void notifyTRKHeader(TRKHeader trkHeader);

	void notifyTrackPoint(TrackPointADM trackPointADM);

}
