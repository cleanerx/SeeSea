/**
 * 
Copyright (c) 2010-2013, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
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
