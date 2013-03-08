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
