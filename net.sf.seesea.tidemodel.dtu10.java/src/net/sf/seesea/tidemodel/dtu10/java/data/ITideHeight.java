package net.sf.seesea.tidemodel.dtu10.java.data;

public interface ITideHeight {

	double getHeight();
	
	/** logical denoting whether tide data exist at desired location.  If FALSE, then TIDE is not modified. */
	boolean isdata();
}
