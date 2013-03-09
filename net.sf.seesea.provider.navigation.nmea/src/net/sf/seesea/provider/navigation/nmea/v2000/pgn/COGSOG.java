package net.sf.seesea.provider.navigation.nmea.v2000.pgn;

import java.util.Arrays;

import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.provider.navigation.nmea.v2000.BitFieldUtil;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Angle;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Speed;

public class COGSOG extends SequencedPGN {

	private HeadingType headingType;
	
	private Angle courseOverGround;
	
	private Speed speed;
	
	public COGSOG(int[] data) {
		super(data, 129026, true, 2, 250, 4);
		int bitfield = BitFieldUtil.getBitfield(Arrays.copyOfRange(data, 1, 2), 0, 2);
		if(bitfield == 0) {
			headingType = HeadingType.COG; // TRUE
		} else if(bitfield == 1) {
			headingType = HeadingType.MAGNETIC;
		} else {
			headingType = HeadingType.UNKNOWN;
		}
		courseOverGround = new Angle(Arrays.copyOfRange(data, 2, 4));
		speed = new Speed(Arrays.copyOfRange(data, 4, 6));
	}

	public HeadingType getHeadingType() {
		return headingType;
	}

	public Angle getCourseOverGround() {
		return courseOverGround;
	}

	public Speed getSpeed() {
		return speed;
	}

	
}
