package net.sf.seesea.provider.navigation.nmea.v2000.pgn;

import java.util.Arrays;

public class EnvironmentalParameters1 extends SequencedPGN {

	private net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature waterTemperature;

	private net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature outsideAirTemperature;

	
	public EnvironmentalParameters1(int[] data) {
		super(data, 130310, true, 5, 500, 2);
		waterTemperature = new net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature(Arrays.copyOfRange(data, 1, 3));
		outsideAirTemperature = new net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature(Arrays.copyOfRange(data, 3, 5));
	}


	public net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature getWaterTemperature() {
		return waterTemperature;
	}


	public net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature getOutsideAirTemperature() {
		return outsideAirTemperature;
	}
	
	

}
