package net.sf.seesea.provider.navigation.nmea;

import java.io.IOException;

public interface INMEAReader {

	void addNMEAEventListener(NMEAEventListener nmeaEventListener);
	
	void removeNMEAEventListener(NMEAEventListener nmeaEventListener);
	
	void close() throws IOException;
}
