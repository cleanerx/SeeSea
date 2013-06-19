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

import net.sf.seesea.provider.navigation.nmea.v2000.BitFieldUtil;
import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.HumidityType;
import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.TemperatureSource;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Percent;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Pressure;

public class EnvironmentalParameters2 extends SequencedPGN {

	private net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature temperature;

	private TemperatureSource temperatureSource;

	private HumidityType humidityType;

	private Percent humidity;

	private Pressure atmosphericPressure;
	
	public EnvironmentalParameters2(int[] data) {
		super(data, 130311, true, 5, 500, 2);
		int temperatureInstanceIndex = BitFieldUtil.getBitfield(Arrays.copyOfRange(data, 1, 2), 0, 6);
		temperatureSource = TemperatureSource.getByIndex(temperatureInstanceIndex);
		temperature = new net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature(Arrays.copyOfRange(data, 2, 4));
		int humidityInstanceIndex = BitFieldUtil.getBitfield(Arrays.copyOfRange(data, 1, 2), 6, 8);
		humidityType = HumidityType.of(humidityInstanceIndex);
		humidity = new Percent(Arrays.copyOfRange(data, 4, 6));
		atmosphericPressure = new Pressure(Arrays.copyOfRange(data, 6, 8));
	}


	public net.sf.seesea.provider.navigation.nmea.v2000.dataformat.Temperature getTemperature() {
		return temperature;
	}


	public TemperatureSource getTemperatureSource() {
		return temperatureSource;
	}


	public HumidityType getHumidityType() {
		return humidityType;
	}


	public Percent getHumidity() {
		return humidity;
	}


	public Pressure getAtmosphericPressure() {
		return atmosphericPressure;
	}
	
	


}
