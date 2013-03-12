package net.sf.seesea.services.navigation;

import java.io.IOException;

/**
 * 
 */
public interface IStreamProcessor {

	boolean isValidStreamProcessor(int[] buffer) throws NMEAProcessingException;
	
	/**
	 * 
	 * @param c
	 * @param streamProvider
	 * @return false if processing should stop due user termination
	 * @throws NMEAProcessingException
	 */
	boolean readByte(int c, String streamProvider) throws NMEAProcessingException ;
	
	void close() throws IOException;

}
