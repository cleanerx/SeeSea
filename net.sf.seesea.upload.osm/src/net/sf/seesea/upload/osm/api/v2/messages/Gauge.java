package net.sf.seesea.upload.osm.api.v2.messages;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="gauge")
public class Gauge {

	public long id;
	
	public String name;
	
	public double latitude;
	
	public double longitude;

	public GaugeType gaugeType;
}
