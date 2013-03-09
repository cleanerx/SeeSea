package net.sf.seesea.provider.navigation.nmea.v2000.dataformat;

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.Int16;

public class DBRelative extends Int16 {

	public DBRelative(int[] uintByte) {
		super(uintByte, 100, -327.64, 327.64);
	}

}
