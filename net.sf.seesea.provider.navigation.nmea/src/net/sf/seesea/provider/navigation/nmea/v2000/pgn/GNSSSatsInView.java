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

import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.core.physx.SatelliteInfo;
import net.sf.seesea.model.core.physx.SatellitesVisible;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Angle;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.AngleSigned;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.DBRelative;

public class GNSSSatsInView extends SequencedPGN {

	private SatellitesVisible satellitesVisible;
	
	public GNSSSatsInView(int[] data) {
		super(data, 129540, false, 6, 1000, 1);
		int countVisible = data[2];
		satellitesVisible = PhysxFactory.eINSTANCE.createSatellitesVisible();
		if(data.length < countVisible * 12 + 3) {
//			System.out.println("incomplete");
			return;
		}
		for (int i = 0 ; i < countVisible ; i++) {
			SatelliteInfo satelliteInfo = PhysxFactory.eINSTANCE.createSatelliteInfo();
			satelliteInfo.setId(data[3 + i * 12]);
			// high value satellites are not specified
			if(satelliteInfo.getId() > 0 && satelliteInfo.getId() <= 96) {
				AngleSigned angleSigned = new AngleSigned(Arrays.copyOfRange(data, 3 + i * 12 + 1, 3 + i * 12 + 3));
				satelliteInfo.setElevation((int) angleSigned.getValue());
				Angle azimuth = new Angle(Arrays.copyOfRange(data, 3 + i * 12 + 3, 3 + i * 12 + 5));
				satelliteInfo.setAzimuth((int) azimuth.getValue());
				DBRelative dbRelative = new DBRelative(Arrays.copyOfRange(data, 3 + i * 12 + 5, 3 + i * 12 + 7));
				satelliteInfo.setSignalStrength((int) dbRelative.getValue());
				satellitesVisible.getSatelliteInfo().add(satelliteInfo);
			}
		}
	}

	public SatellitesVisible getSatellitesVisible() {
		return satellitesVisible;
	}
	
	
	

}
