package org.osm.depth.upload.messages;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="license")
public class License {

	public long id;
	
	public String name;
	
	public String shortName;
	
	public String text;
	
	/** if null this license may be publically used and only be changed by the admin and the owner */
	public String User;
	
	/** License may be used by others */
	public boolean publicLicense;
}
