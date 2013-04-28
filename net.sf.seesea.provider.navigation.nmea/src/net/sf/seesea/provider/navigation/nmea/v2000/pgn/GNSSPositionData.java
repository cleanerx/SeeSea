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
import java.util.Date;

import net.sf.seesea.provider.navigation.nmea.v2000.BitFieldUtil;
import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.GNSSIntegrity;
import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.MethodGNSS;
import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.TypeOfSystem;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Altitude;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.DateDayCount;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Distance;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.LatitudeExt;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.LongitudeExt;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.TimeOfDay;

public class GNSSPositionData extends SequencedPGN {

	private DateDayCount positionDate;
	
	private TimeOfDay timeOfDay;
	
	private LatitudeExt latitude;

	private LongitudeExt longitude;
	
	private Altitude altitude;

	private TypeOfSystem typeOfSystem;
	
	private MethodGNSS methodGNSS;
	
	// Integrity
	// Satelites visibile
	// HDOP 
	// PDOP 
	
	private Distance geodialSeparation;

	private GNSSIntegrity quality;

	private int satelitesVisible;
	
	public GNSSPositionData(int[] data) {
		super(data, 129029, false, 3, 1000, 1);
		if(data.length < 41) {
			return;
		}

		positionDate = new DateDayCount(Arrays.copyOfRange(data, 1, 3));
		timeOfDay = new TimeOfDay(Arrays.copyOfRange(data, 3, 7));
		latitude = new LatitudeExt(Arrays.copyOfRange(data, 7, 15));
		longitude = new LongitudeExt(Arrays.copyOfRange(data, 15, 23));
		altitude = new Altitude(Arrays.copyOfRange(data, 23, 31));
		int[] byte31 = Arrays.copyOfRange(data, 31, 32);
		int bitfields = BitFieldUtil.getBitfield(byte31, 4, 8);
		typeOfSystem = TypeOfSystem.of(bitfields);
		bitfields = BitFieldUtil.getBitfield(byte31, 0, 4);
		methodGNSS = MethodGNSS.of(bitfields);
		int[] byte32 = Arrays.copyOfRange(data, 32, 33);
		bitfields = BitFieldUtil.getBitfield(byte32, 0, 2);
		quality = GNSSIntegrity.of(bitfields);
		satelitesVisible = data[33];
		
		geodialSeparation = new Distance(Arrays.copyOfRange(data, 37, 41));
	}

	public LatitudeExt getLatitude() {
		return latitude;
	}

	public LongitudeExt getLongitude() {
		return longitude;
	}

	public Altitude getAltitude() {
		return altitude;
	}

	public Distance getGeodialSeparation() {
		return geodialSeparation;
	}
	
	public Date getTime() {
		long millisecondsSince1970 = ((long)positionDate.getValue()) * 1000 * 60 * 60 * 24;
		millisecondsSince1970 += ((long)timeOfDay.getValue()) * 1000;
		return new Date(millisecondsSince1970);
	}

	public MethodGNSS getMethodGNSS() {
		return methodGNSS;
	}

	public GNSSIntegrity getQuality() {
		return quality;
	}

	public int getSatelitesVisible() {
		return satelitesVisible;
	}
	
	
}

