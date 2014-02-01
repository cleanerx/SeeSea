package org.osm.depth.upload.messages;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stats {
	
	public int usercount;
	
	public int trackscount;
	
	public long contributedpoints;

}
