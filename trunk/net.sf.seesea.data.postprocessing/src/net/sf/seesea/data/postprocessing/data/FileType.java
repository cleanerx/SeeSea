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
package net.sf.seesea.data.postprocessing.data;

public enum FileType {

	NMEA0183("application/x-nmea0183"),  //$NON-NLS-1$
	NMEA2000("application/x-nmea2000"),  //$NON-NLS-1$
	ACTISENSE("application/x-actisense"),  //$NON-NLS-1$
	HUMMINBIRD_SON("application/x-humminbird"),  //$NON-NLS-1$
	RAYMARINE_FSH("application/x-flashfile"), //$NON-NLS-1$
	WINPROFILE_SHARP("application/x-sharp"), //$NON-NLS-1$
	LOWRANCE_SL2("application/sl2"), //$NON-NLS-1$
	GPX("application/x-gpx"), //$NON-NLS-1$
	GARMIN_ADM("application/x-adm"), //$NON-NLS-1$
	UNKNOWN(null); 
	
	
	private final String mimeType;

	FileType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public static FileType getFileType(String type) {
		if("application/x-nmea0183".equals(type)) { //$NON-NLS-1$
			return NMEA0183;
		} else if("application/x-actisense".equals(type)) { //$NON-NLS-1$
			return ACTISENSE;
		} else if("application/x-flashfile".equals(type)) { //$NON-NLS-1$
			return RAYMARINE_FSH;
		} else if("application/x-humminbird".equals(type)) { //$NON-NLS-1$
			return HUMMINBIRD_SON;
		} else if("application/x-sharp".equals(type)) { //$NON-NLS-1$
			return WINPROFILE_SHARP;
		} else if("application/sl2".equals(type)) { //$NON-NLS-1$
			return LOWRANCE_SL2;
		} else if("application/x-gpx".equals(type)) { //$NON-NLS-1$
			return GPX;
		} else if("application/x-adm".equals(type)) { //$NON-NLS-1$
			return GARMIN_ADM;
		} else {
			return UNKNOWN;
		}
	}

	public String getMimeType() {
		return mimeType;
	}
	
	

}
