package org.osm.data;

import java.util.Map;

public interface IOSMElement {

	Map<String,String> getTags();
	
	String getValue(String key);
	
}
