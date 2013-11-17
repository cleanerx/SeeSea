package net.sf.seesea.navigation.sl2;


public interface ISL2Reader {

	/**
	 * 
	 * @param listener
	 */
	void addSL2Listener(ISL2Listener listener);

	/**
	 * 
	 * @param listener
	 */
	void removeSL2Listener(ISL2Listener listener);
	
}
