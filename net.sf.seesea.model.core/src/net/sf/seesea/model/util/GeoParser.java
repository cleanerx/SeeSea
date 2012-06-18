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

import net.sf.seesea.model.core.geo.Coordinate;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;

public class GeoParser {
	
	public static Latitude parseLatitude(String latitude) {
		return parseLatitude(Double.parseDouble(latitude));
	}

	public static Longitude parseLongitude(String longitude) {
		return parseLongitude(Double.parseDouble(longitude));
	}

	public static Latitude parseLatitude(Double decimalDegree) {
		if(decimalDegree < -90.0 || decimalDegree > 90.0) {
			throw new IllegalArgumentException("Decimal degree must be between -90.0 and 90.0"); //$NON-NLS-1$
		}
		net.sf.seesea.model.core.geo.Latitude latitude = GeoFactory.eINSTANCE.createLatitude();
		setCoordinate(decimalDegree, latitude);
		return latitude;
	}

	public static Longitude parseLongitude(Double decimalDegree) {
		if(decimalDegree < -180.0 || decimalDegree > 180.0) {
			throw new IllegalArgumentException("Decimal degree must be between -180.0 and 180.0"); //$NON-NLS-1$
		}
		Longitude longitude = GeoFactory.eINSTANCE.createLongitude();
		setCoordinate(decimalDegree, longitude);
		return longitude;
	}

	private static void setCoordinate(Double decimalDegree,
			net.sf.seesea.model.core.geo.Coordinate latitude) {
		double decDegree = decimalDegree;
		latitude.setDegree((int) Math.floor(decDegree));
		decDegree = (decDegree - latitude.getDegree()) * 60;
		latitude.setMinute((int) Math.floor(decDegree));
		decDegree = (decDegree - latitude.getMinute()) * 60;
		latitude.setSecond((int) Math.floor(decDegree));
	}
	
	/**
	 * 
	 * @param coordinate 
	 * @return the coordinate given in decimal degrees 
	 */
	public double getDecimalDegrees(Coordinate coordinate) {
		double decimalminutes = coordinate.getMinute() + (((double)coordinate.getSecond()) / 60);
		double decimalDegrees = coordinate.getDegree() + (decimalminutes / 60);
		return decimalDegrees;
	}


}
