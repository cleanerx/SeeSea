package net.sf.seesea.provider.navigation.nmea.v2000.dataformat;

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.Int16;

public class Percent extends Int16 {

	public Percent(int[] uintByte) {
		super(uintByte, 1, -131.056, 131.072);
	}

}
