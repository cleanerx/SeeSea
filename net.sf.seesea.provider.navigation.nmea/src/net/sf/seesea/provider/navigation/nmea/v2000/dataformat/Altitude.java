package net.sf.seesea.provider.navigation.nmea.v2000.dataformat;

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.Int64;

public class Altitude extends Int64 {

	public Altitude(int[] uintByte) {
		super(uintByte, 1000000L, -9223000000000.0, 9223000000000.0);
	}

}
