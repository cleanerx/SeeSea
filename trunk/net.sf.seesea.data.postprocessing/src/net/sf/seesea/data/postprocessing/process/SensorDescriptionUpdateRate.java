/**
Copyright (c) 2013-2015, Jens Kübler
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
package net.sf.seesea.data.postprocessing.process;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.data.SensorDescription;

public class SensorDescriptionUpdateRate<T extends Measurement> extends SensorDescription<T> {

	private long updateRate;
	
	private int precision;

	public SensorDescriptionUpdateRate(Class<T> measurement, String sensorID,
			String messageType, long updateRate, int precision) {
		super(measurement, sensorID, messageType);
		this.updateRate = updateRate;
		this.precision = precision;
	}

	public int getPrecision() {
		return precision;
	}



	protected long getUpdateRate() {
		return updateRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + precision;
		result = prime * result + (int) (updateRate ^ (updateRate >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorDescriptionUpdateRate other = (SensorDescriptionUpdateRate) obj;
		if (precision != other.precision)
			return false;
		if (updateRate != other.updateRate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SensorDescriptionUpdateRate [updateRate=" + updateRate
				+ ", precision=" + precision + ", toString()="
				+ super.toString() + "]";
	}


	
	
}
