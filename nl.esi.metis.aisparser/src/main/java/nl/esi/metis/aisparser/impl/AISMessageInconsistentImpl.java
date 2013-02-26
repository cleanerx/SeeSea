package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.AISMessageInconsistent;
import nl.esi.metis.aisparser.Sixbit;
import nl.esi.metis.aisparser.provenance.VDMMessageProvenance;
/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */

/** AIS Message Inconsistent: Class to store AIS Messages that do not adhere to the standard.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
class AISMessageInconsistentImpl extends AISMessageImpl implements AISMessageInconsistent {
    private int length;

    /** Returns the length of the message.
	 * @return the length of the message (in bytes)
	 */
	public int getLength() { return length; }
	
    /** Generates a <code>String</code> representing the parsed (but inconsistent) AIS message.
     * Format: For these inconsistent message, only the common fields (message ID, repeat indicator, and user ID) and the length are provided.
     */
    @Override
	public String toString()
	{
		return super.toString() +  " length = " + length;
	}

    // TODO also store content of inconsistent message?
    
	/** Constructs an object representing an inconsistent message.
	 * @param content the content of the message
	 * @param prov the provenance of the message
	 */
    public AISMessageInconsistentImpl (Sixbit content, VDMMessageProvenance prov)
    {
    	super(content, prov);
    	length = content.length();
    }
}