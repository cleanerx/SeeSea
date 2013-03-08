/**
 * 
Copyright (c) 2010-2012, Jens K�bler
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
import java.util.Date;

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.TimeSource;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.DateDayCount;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.TimeOfDay;

public class SystemTime extends SequencedPGN {

	private TimeSource timeSource;
	
	private DateDayCount date;
	
	private TimeOfDay timeOfDay;
	
	public SystemTime(int[] data) {
		super(data, 126992, true, 3, 1000, 1);
		timeSource = TimeSource.getByIndex(data[1] & 0x0F);
		date = new DateDayCount(Arrays.copyOfRange(data, 2, 4));
		timeOfDay = new TimeOfDay(Arrays.copyOfRange(data, 4, 8));
	}
	
	public TimeSource getTimeSource() {
		return timeSource;
	}

	public Date getTime() {
		long millisecondsSince1970 = ((long)date.getValue()) * 1000 * 60 * 60 * 24;
		millisecondsSince1970 += ((long)timeOfDay.getValue()) * 1000;
		return new Date(millisecondsSince1970);
	}
	
	

}
