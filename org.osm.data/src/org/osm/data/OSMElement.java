package org.osm.data;

import java.util.HashMap;
import java.util.Map;

public class OSMElement implements IOSMElement {

	private Map<String, String> tags;
	protected final String id;

	public OSMElement(String id) {
		this(id, new HashMap<String,String>());
	}
	
	public OSMElement(String id, Map<String, String> tags) {
		this.id = id;
		this.tags = tags;
	}
	
	@Override
	public Map<String, String> getTags() {
		return tags;
	}

	@Override
	public String getValue(String key) {
		return tags.get(key);
	}

	public String getId() {
		return id;
	}
	
	

}
