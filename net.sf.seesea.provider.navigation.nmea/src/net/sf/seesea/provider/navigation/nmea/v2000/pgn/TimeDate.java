package net.sf.seesea.provider.navigation.nmea.v2000.pgn;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.DateDayCount;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.TimeLocalOffset;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.TimeOfDay;

public class TimeDate extends PGN {

	private DateDayCount dateDayCount;
	
	private TimeOfDay timeOfDay;
	
	private TimeLocalOffset timeLocalOffset;
	
	public TimeDate(int[] data) {
		super(129033,true, 3, 1000, 1);
		dateDayCount = new DateDayCount(Arrays.copyOfRange(data, 0, 2));
		timeOfDay = new TimeOfDay(Arrays.copyOfRange(data, 2, 6));
		timeLocalOffset = new TimeLocalOffset(Arrays.copyOfRange(data, 6, 8));
	}
	
	public Date getDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis(0);
		calendar.set(Calendar.DAY_OF_YEAR, (int) dateDayCount.getValue());
		calendar.set(Calendar.SECOND, (int) timeOfDay.getValue());
//		calendar.getTimeZone().get
//		calendar.setTimeZone(value);
		return calendar.getTime();
	}

}
