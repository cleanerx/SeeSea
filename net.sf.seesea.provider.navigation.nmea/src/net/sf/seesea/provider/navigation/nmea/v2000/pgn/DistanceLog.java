package net.sf.seesea.provider.navigation.nmea.v2000.pgn;

import java.util.Arrays;
import java.util.Date;

import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.DateDayCount;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Distance;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.TimeOfDay;

public class DistanceLog extends PGN {

	private DateDayCount dateDayCount;
	private TimeOfDay timeOfDay;
	private Distance totalDistance;
	private Distance distanceVoyage;

	public DistanceLog(int[] data) {
		super(128275, false, 6, 1000, 1);
		dateDayCount = new DateDayCount(Arrays.copyOfRange(data, 0, 2));
		timeOfDay = new TimeOfDay(Arrays.copyOfRange(data, 2, 6));
		totalDistance = new Distance(Arrays.copyOfRange(data, 6, 10));
		distanceVoyage = new Distance(Arrays.copyOfRange(data, 10, 14));
	}
	
	
	public Date getTime() {
		long millisecondsSince1970 = ((long)dateDayCount.getValue()) * 1000 * 60 * 60 * 24;
		millisecondsSince1970 += ((long)timeOfDay.getValue()) * 1000;
		return new Date(millisecondsSince1970);
	}


	public Distance getTotalDistance() {
		return totalDistance;
	}


	public Distance getDistanceVoyage() {
		return distanceVoyage;
	}
	
	

}
