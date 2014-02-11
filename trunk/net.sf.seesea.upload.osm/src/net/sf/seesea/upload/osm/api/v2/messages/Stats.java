package net.sf.seesea.upload.osm.api.v2.messages;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stats {
	
	public int usercount;
	
	public int trackscount;
	
	public long contributedpoints;

}
