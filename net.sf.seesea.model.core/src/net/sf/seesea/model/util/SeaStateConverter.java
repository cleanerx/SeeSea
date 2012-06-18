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

import net.sf.seesea.model.core.physx.LengthUnit;
import net.sf.seesea.model.core.weather.SeaState;
import net.sf.seesea.model.core.weather.WaveHeight;
import net.sf.seesea.model.core.weather.WeatherFactory;

/**
 * 
 */
public class SeaStateConverter {
	
	public static SeaState getSeaState(WaveHeight waveHeight) {
		double waveHeightValue = waveHeight.getValue();
		
		switch(waveHeight.getUnit()) {
		case FEET:
			waveHeightValue *= 0.3048;
			break;
		case NAUTICAL_MILE:
			waveHeightValue *= 1852;
			break;
		case METERS:
			break;
		case UNDEFINED:	
			return SeaState.UNDEFINED;
		}
		
		if(waveHeightValue == 0.0) {
			return SeaState.CALM_GLASSY;
		} else if(waveHeightValue < 0.1) {
			return SeaState.CALM_RIPPLED;
		} else if(waveHeightValue < 0.5) {
			return SeaState.SMOOTH_WAVELETS;
		} else if(waveHeightValue < 1.25) {
			return SeaState.SLIGHT;
		} else if(waveHeightValue < 2.5) {
			return SeaState.MODERATE;
		} else if(waveHeightValue < 4) {
			return SeaState.ROUGH;
		} else if(waveHeightValue < 6) {
			return SeaState.VERY_ROUGH;
		} else if(waveHeightValue < 9) {
			return SeaState.HIGH;
		} else if(waveHeightValue < 14) {
			return SeaState.VERY_HIGH;
		} else if(waveHeightValue >= 14) {
			return SeaState.PHENOMENAL;
		}
		return SeaState.UNDEFINED;
	}
	
	public static WaveHeight getWaveHeight(SeaState seaState) {
		WaveHeight waveHeight = WeatherFactory.eINSTANCE.createWaveHeight();
		waveHeight.setUnit(LengthUnit.METERS);
		
		switch (seaState) {
		case CALM_GLASSY:
			waveHeight.setValue(0.0);
			break;
		case CALM_RIPPLED:
			waveHeight.setValue(0.09);
			break;
		case SMOOTH_WAVELETS:
			waveHeight.setValue(0.49);
			break;
		case SLIGHT: 
			waveHeight.setValue(1.24);
			break;
		case MODERATE:
			waveHeight.setValue(2.4);
			break;
		case ROUGH:
			waveHeight.setValue(3.9);
			break;
		case VERY_ROUGH:
			waveHeight.setValue(5.9);
			break;
		case HIGH:
			waveHeight.setValue(8.9);
			break;
		case VERY_HIGH:
			waveHeight.setValue(13.9);
			break;
		case PHENOMENAL:
			waveHeight.setValue(14);
			break;
		case UNDEFINED:
			return null;
		}
		return waveHeight;
	}

}
