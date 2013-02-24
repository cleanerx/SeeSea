package net.sf.seesea.services.navigation;

import java.io.IOException;

public interface INMEAReader {

	void addNMEAEventListener(RawDataEventListener nmeaEventListener);
	
	void removeNMEAEventListener(RawDataEventListener nmeaEventListener);

	void addAISEventListener(RawDataEventListener nmeaEventListener);
	
	void removeAISEventListener(RawDataEventListener nmeaEventListener);

	void close() throws IOException;
}
