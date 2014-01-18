package org.osm.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OSMRelation extends OSMElement {
	
	private Map<IOSMElement, Map<String,String>> relation;

	public OSMRelation(String id) {
		super(id);
	}

	public OSMRelation(String id, Map<String, String> tags) {
		super(id, tags);
	}

	public OSMRelation(String id, Map<IOSMElement, Map<String,String>> relation, Map<String, String> tags) {
		super(id, tags);
		this.relation = relation;
	}

	public Map<IOSMElement, Map<String, String>> getRelation() {
		return relation;
	}
	
	public List<IOSMElement> getRelationByTag(String key, String value) {
		List<IOSMElement> osmElements = new ArrayList<>();
		
		for (Entry<IOSMElement, Map<String, String>> entry : relation.entrySet()) {
			for(Entry<String, String> tagEntry : entry.getValue().entrySet()) {
				if(tagEntry.getKey().equals(key) && tagEntry.getValue().equals(value)) {
					osmElements.add(entry.getKey());
				}
			}
		}
		return osmElements;
	}
	

}
