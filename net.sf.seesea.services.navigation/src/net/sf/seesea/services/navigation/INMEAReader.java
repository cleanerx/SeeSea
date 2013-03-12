package net.sf.seesea.services.navigation;


public interface INMEAReader {

	void addNMEAEventListener(RawDataEventListener nmeaEventListener);
	
	void removeNMEAEventListener(RawDataEventListener nmeaEventListener);

	void addAISEventListener(RawDataEventListener nmeaEventListener);
	
	void removeAISEventListener(RawDataEventListener nmeaEventListener);

}
