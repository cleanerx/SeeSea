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

import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.provider.navigation.nmea.v2000.BitFieldUtil;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Angle;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Speed;

public class COGSOG extends SequencedPGN {

	private HeadingType headingType;
	
	private Angle courseOverGround;
	
	private Speed speed;
	
	public COGSOG(int[] data) {
		super(data, 129026, true, 2, 250, 4);
		int bitfield = BitFieldUtil.getBitfield(Arrays.copyOfRange(data, 1, 2), 0, 2);
		if(bitfield == 0) {
			headingType = HeadingType.COG; // TRUE
		} else if(bitfield == 1) {
			headingType = HeadingType.MAGNETIC;
		} else {
			headingType = HeadingType.UNKNOWN;
		}
		courseOverGround = new Angle(Arrays.copyOfRange(data, 2, 4));
		speed = new Speed(Arrays.copyOfRange(data, 4, 6));
	}

	public HeadingType getHeadingType() {
		return headingType;
	}

	public Angle getCourseOverGround() {
		return courseOverGround;
	}

	public Speed getSpeed() {
		return speed;
	}

	
}
