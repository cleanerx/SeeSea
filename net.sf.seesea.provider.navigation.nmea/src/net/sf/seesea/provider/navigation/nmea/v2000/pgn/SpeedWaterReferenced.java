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

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.SpeedWaterReferencedType;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Speed;

public class SpeedWaterReferenced extends PGN {

	private int sequenceID;
	
	private Speed speedWaterReferenced;
	
	private Speed speedGroundReferenced;
	
	private SpeedWaterReferencedType speedWaterReferencedType;
	
	public SpeedWaterReferenced(int[] data) {
		super(128259, true, 2, 1000, 1);
		sequenceID = data[0];
		speedWaterReferenced = new Speed(Arrays.copyOfRange(data, 1, 3));
		speedGroundReferenced = new Speed(Arrays.copyOfRange(data, 3, 5));
		speedWaterReferencedType = SpeedWaterReferencedType.getByIndex(data[5]);
	}
	
	public int getSequenceID() {
		return sequenceID;
	}

	public Double getSpeedWaterReferenced() {
		if(!speedWaterReferenced.isValid()) {
			return Double.NaN;
		}
		return speedWaterReferenced.getValue();
	}

	public Double getSpeedGroundReferenced() {
		if(!speedGroundReferenced.isValid()) {
			return Double.NaN;
		}
		return speedGroundReferenced.getValue();
	}

	public SpeedWaterReferencedType getSpeedWaterReferencedType() {
		return speedWaterReferencedType;
	}
	
	
}
