/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.AISMessage;
import nl.esi.metis.aisparser.Sixbit;
import nl.esi.metis.aisparser.VDMMessage;
import nl.esi.metis.aisparser.provenance.VDMMessageProvenance;


public class AISMessageFactory {
	
	static public boolean IsValid (VDMMessage message)
	{
		return message.atEnd() && message.isComplete();
	}
	
	static public AISMessage getAISMessage(VDMMessage message) {
		Sixbit sb = new Sixbit(message.getMessage(), message.getNrOfFillBits());
		VDMMessageProvenance provenance = message.getProvenance();
		
		if (sb.length() < 38)
		{
			AISMessage retval = new AISMessageIllegalImpl(sb, provenance); // the errors are logged here
			return retval;
		}
		else
		{
			int messageid 			= AISMessageImpl.getMessageID(sb);
			switch (messageid)
			{
			case 1:
				if (! AISMessage01Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage01Impl(sb, provenance);
			case 2:
				if (! AISMessage02Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage02Impl(sb, provenance);
			case 3:
				if (! AISMessage03Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage03Impl(sb, provenance);
			case 4:
				if (! AISMessage04Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage04Impl(sb, provenance);
			case 5:
				if (! AISMessage05Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage05Impl(sb, provenance);
			case 6:
				if (! AISMessage06Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage06Impl(sb, provenance);
			case 7:
				if (! AISMessage07Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage07Impl(sb, provenance);
			case 8:
				if (! AISMessage08Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage08Impl(sb, provenance);
			case 9:
				if (! AISMessage09Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage09Impl(sb, provenance);
			case 10:
				if (! AISMessage10Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage10Impl(sb, provenance);
			case 11:
				if (! AISMessage11Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage11Impl(sb, provenance);
			case 12:
				if (! AISMessage12Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage12Impl(sb, provenance);
			case 13:
				if (! AISMessage13Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage13Impl(sb, provenance);
			case 14:
				if (! AISMessage14Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage14Impl(sb, provenance);
			case 15:
				if (! AISMessage15Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage15Impl(sb, provenance);
			case 16:
				if (! AISMessage16Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage16Impl(sb, provenance);
			case 17:
				if (! AISMessage17Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage17Impl(sb, provenance);
			case 18:
				if (! AISMessage18Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage18Impl(sb, provenance);
			case 19:
				if (! AISMessage19Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage19Impl(sb, provenance);
			case 20:
				if (! AISMessage20Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage20Impl(sb, provenance);
			case 21:
				if (! AISMessage21Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage21Impl(sb, provenance);
			case 22:
				if (! AISMessage22Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage22Impl(sb, provenance);
			case 23:
				if (! AISMessage23Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage23Impl(sb, provenance);
			case 24:
			    {
			    	// Distinguish between the different part numbers:
			    	if (AISMessage24PartAImpl.valid(sb))
					{
						return new AISMessage24PartAImpl(sb, provenance);
					}
					else if (AISMessage24PartBImpl.valid(sb))
					{
						return new AISMessage24PartBImpl(sb, provenance);
					}
					else
					{
						return new AISMessageInconsistentImpl(sb, provenance);
					}
			    }
			case 25:
				if (! AISMessage25Impl.valid(sb))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage25Impl(sb, provenance);
			case 26:
				if (! AISMessage26Impl.valid(sb))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage26Impl(sb, provenance);
			case 27:
				if (! AISMessage27Impl.validLength(sb.length()))
				{
					return new AISMessageInconsistentImpl(sb, provenance);
				}
				return new AISMessage27Impl(sb, provenance);
			default:
				return new AISMessageUnknownMessageIdImpl(sb, provenance);
			}
		}
	}
}