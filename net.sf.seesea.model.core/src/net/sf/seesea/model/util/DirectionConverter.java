/**
Copyright (c) 2010-2012, Jens Kübler
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
package net.sf.seesea.model.util;

import net.sf.seesea.model.core.geo.Direction;

public class DirectionConverter {

	public static Direction getDirection16(double degrees) {
		degrees -= 11.25;
		if(degrees < 0) {
			return Direction.UNDEFINED;
		} else if(degrees > 360) {
			return Direction.UNDEFINED;
		} else if(degrees == 360) {
			return Direction.N;
		}
		int intValue = ((Long)Math.round(degrees / 22.5)).intValue() + 1;
		if(intValue == 17) {
			return Direction.N;
		}
		return Direction.get(intValue);
	}
	
	public static Direction getDirection8(double degrees) {
		if(degrees < 0) {
			return Direction.UNDEFINED;
		} else if(degrees > 360) {
			return Direction.UNDEFINED;
		} else if(degrees == 360) {
			return Direction.N;
		}
		int intValue = ((Long)Math.round(degrees / 45)).intValue() * 2 + 1;
		if(intValue == 17) {
			return Direction.N;
		}
		return Direction.get(intValue);
	}
	
	public static double getDegrees(Direction direction) {
		return (direction.getValue() - 1) * 22.5;
	}
}
