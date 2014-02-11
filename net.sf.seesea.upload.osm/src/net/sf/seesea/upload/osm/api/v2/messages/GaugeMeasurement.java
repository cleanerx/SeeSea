package net.sf.seesea.upload.osm.api.v2.messages;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="gaugemeasurement")
public class GaugeMeasurement {

	public long gaugeId;
	
	public float value;
	
	public LengthUnit lengthUnit;

	/** time since 1.1.1970 UTC */
	public long timestamp;
	
}
