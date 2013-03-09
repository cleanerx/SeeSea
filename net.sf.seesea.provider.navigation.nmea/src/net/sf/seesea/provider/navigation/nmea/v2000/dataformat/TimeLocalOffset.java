package net.sf.seesea.provider.navigation.nmea.v2000.dataformat;

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.Int16;

public class TimeLocalOffset extends Int16 {

	public TimeLocalOffset(int[] data) {
		super(data, 1, -32764, 32764);
	}

}
