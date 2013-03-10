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

import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.AngleSigned;

public class Attitude extends SequencedPGN {

	private AngleSigned yaw;
	
	private AngleSigned pitch;
	
	private AngleSigned roll;
	
	public Attitude(int[] data) {
		super(data, 127257, true, 3, 1000, 1);
		yaw = new AngleSigned(Arrays.copyOfRange(data, 1, 3));
		pitch = new AngleSigned(Arrays.copyOfRange(data, 3, 5));
		roll = new AngleSigned(Arrays.copyOfRange(data, 5, 7));
	}

	/**
	 * 
	 * @return yaw in deg
	 */
	public Double getYaw() {
		return yaw.getValue() * 180 / Math.PI;
	}

	/**
	 * 
	 * @return pitch in deg
	 */
	public Double getPitch() {
		return pitch.getValue() * 180 / Math.PI;
	}

	/**
	 * 
	 * @return roll in deg
	 */
	public Double getRoll() {
		return roll.getValue() * 180 / Math.PI;
	}
	
	
	
	


}
