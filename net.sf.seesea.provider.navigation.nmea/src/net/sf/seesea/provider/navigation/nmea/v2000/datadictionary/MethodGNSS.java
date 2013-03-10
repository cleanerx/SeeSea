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
package net.sf.seesea.provider.navigation.nmea.v2000.datadictionary;


public enum MethodGNSS {

	NOGPS,
	GNSS,
	DGNSS,
	PRECISEGNSS,
	RTKFIXEDINTEGER,
	RTKFLOAT,
	ESTIMATEDDRMODE,
	MANUALINPUT,
	SIMULATEMODE,
	RESERVED,
	ERROR,
	NULL;
	
	public static MethodGNSS of(int linkType) {

	    switch (linkType) {
	        case 0: return NOGPS;
	        case 1: return GNSS;
	        case 2: return DGNSS;
	        case 3: return PRECISEGNSS;
	        case 4: return RTKFIXEDINTEGER;
	        case 5: return RTKFLOAT;
	        case 6: return ESTIMATEDDRMODE;
	        case 7: return MANUALINPUT;
	        case 8: return SIMULATEMODE;
	        case 9: 
	        case 10: 
	        case 11: 
	        case 12: 
	        case 13: 
	        	return RESERVED;
	        case 14: return ERROR;
	        case 15: return NULL;

	        default: return NULL;

	    }
	}

}
