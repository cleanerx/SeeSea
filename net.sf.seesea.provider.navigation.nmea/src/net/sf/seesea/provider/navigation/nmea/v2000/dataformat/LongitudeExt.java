package net.sf.seesea.provider.navigation.nmea.v2000.dataformat;

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.Int64;

public class LongitudeExt extends Int64 {

	public LongitudeExt(int[] uintByte) {
		super(uintByte, 10000000000000000L, -180.0, 180.0);
	}

}
