package net.sf.seesea.services.navigation;

/**
 * 
 */
public interface IStreamProcessor {

	boolean isValidStreamProcessor(byte[] buffer) throws NMEAProcessingException;
	
	void readByte(int c, String streamProvider) throws NMEAProcessingException ;	
}
