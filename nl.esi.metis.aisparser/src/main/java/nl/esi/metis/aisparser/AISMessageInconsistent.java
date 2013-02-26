/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents AIS Messages that do not adhere to the standard.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessageInconsistent extends AISMessage {

	/** Returns the length of the message.
	 * @return the length of the message (in bytes)
	 */
	public int getLength();

    /** Generates a <code>String</code> representing the parsed (but inconsistent) AIS message.
     * Format: For these inconsistent message, only the common fields (message ID, repeat indicator, and user ID) and the length are provided.
     */
	public String toString();

}