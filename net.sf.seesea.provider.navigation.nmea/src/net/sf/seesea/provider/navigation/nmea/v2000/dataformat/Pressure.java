package net.sf.seesea.provider.navigation.nmea.v2000.dataformat;

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.UInt16;

public class Pressure extends UInt16 {

	public Pressure(int[] data) {
		super(data, 100, 0, 6553200);
	}

	
}
