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

import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.model.core.weather.Beaufort;

public class WindSpeedConverter {

	public static Beaufort getBeaufort(Speed speed) {
		double factor = 0.0;
		Long round = null;
		if(speed.getSpeed() < 0) {
			return Beaufort.UNDEFINED;
		}
		switch (speed.getSpeedUnit()) {
		case N:
			round = Math.round(Math.pow(speed.getSpeed() / 1.6233 * speed.getSpeed() / 1.6233, 1.0 / 3.0));
			if(round > 12) {
				return Beaufort.HURRICANE_FORCE;
			}
			return Beaufort.get(round.intValue());
//			factor = 1.0;
//			break;
		case K:
			round = Math.round(Math.pow(speed.getSpeed() / 3.0096 * speed.getSpeed() / 3.0096, 1.0 / 3.0));
			if(round > 12) {
				return Beaufort.HURRICANE_FORCE;
			}
			return Beaufort.get(round.intValue());
		case M:
			round = Math.round(Math.pow(speed.getSpeed() / 1.8700787 * speed.getSpeed() / 1.8700787, 1.0 / 3.0));
			if(round > 12) {
				return Beaufort.HURRICANE_FORCE;
			}
			return Beaufort.get(round.intValue());
		default:
			return Beaufort.UNDEFINED;
		}
	}
	
	public static Speed getSpeed(Beaufort beaufort) {
		Speed speed = PhysxFactory.eINSTANCE.createSpeed();

		if(beaufort.equals(Beaufort.UNDEFINED)) {
			speed.setSpeed(-1);
			return speed; 
		}
		double x = Math.pow(beaufort.getValue(), 1.5) * 1.625;
		speed.setSpeed(x);
		speed.setSpeedUnit(SpeedUnit.N);
		return speed;
	}
	
}
