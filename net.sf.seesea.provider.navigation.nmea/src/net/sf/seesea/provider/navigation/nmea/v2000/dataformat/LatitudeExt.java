package net.sf.seesea.provider.navigation.nmea.v2000.dataformat;

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.Int64;

public class LatitudeExt extends Int64 {

	public LatitudeExt(int[] uintByte) {
		super(uintByte, 10000000000000000L, -90.0, 90.0);
	}

}
