package net.sf.seesea.provider.navigation.nmea.v2000.pgn;

import java.util.Arrays;

import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Latitude;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Longitude;

public class PositionRapid extends SequencedPGN {

	private Latitude latitude;
	
	private Longitude longitude;
	
	public PositionRapid(int[] data) {
		super(data, 129029, true, 2, 100, 10);
		latitude = new Latitude(Arrays.copyOfRange(data, 0, 4));
		longitude = new Longitude(Arrays.copyOfRange(data, 4, 8)); 
	}

}
