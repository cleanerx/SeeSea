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

import net.sf.seesea.provider.navigation.nmea.v2000.datadictionary.VariationSource;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.AngleSigned;
import net.sf.seesea.provider.navigation.nmea.v2000.dataformat.DateDayCount;

public class MagneticVariation extends SequencedPGN {

	private VariationSource variationSource;
	
	private DateDayCount ageOfService;
	
	private AngleSigned magneticHeadingCorrection;
	
	public MagneticVariation(int[] data) {
		super(data, 127258, true, 7, 1000, 1);
		variationSource = VariationSource.getByIndex(data[1] & 0x0F);
		ageOfService = new DateDayCount(Arrays.copyOfRange(data, 2, 4));
		magneticHeadingCorrection = new AngleSigned(Arrays.copyOfRange(data, 4, 6));
	}

	public VariationSource getVariationSource() {
		return variationSource;
	}

	/**
	 * FIXME: return java date?
	 * 
	 * @return days since 1 Jan 1970 
	 */
	public Integer getAgeOfService() {
		if(!ageOfService.isValid()) {
			return null;
		}
		return (int)ageOfService.getValue();
	}

	public Double getMagneticHeadingCorrection() {
		if(!magneticHeadingCorrection.isValid()) {
			return null;
		}
		return magneticHeadingCorrection.getValue() * 180 / Math.PI;
	}
	
	

}
