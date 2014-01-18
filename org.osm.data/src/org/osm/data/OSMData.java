package org.osm.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OSMData {

	private List<OSMNode> osmNodes;
	private List<OSMWay> osmWays;
	private List<OSMRelation> osmRelations;

	public OSMData() {
		this(new ArrayList<OSMNode>(), new ArrayList<OSMWay>(), new ArrayList<OSMRelation>());
	}

	public OSMData(List<OSMNode> osmNodes, List<OSMWay> osmWays,
			List<OSMRelation> osmRelations) {
		super();
		this.osmNodes = osmNodes;
		this.osmWays = osmWays;
		this.osmRelations = osmRelations;
	}

	public List<OSMNode> getOsmNodes() {
		return osmNodes;
	}

	public List<OSMWay> getOsmWays() {
		return osmWays;
	}

	public List<OSMRelation> getOSMRelations() {
		return osmRelations;
	}
	
	public List<OSMRelation> getOSMRelations(String key, String value) {
		List<OSMRelation> relations = new ArrayList<>();
		for (OSMRelation relation : osmRelations) {
			Map<String, String> tags = relation.getTags();
			for (Entry<String, String> entry : tags.entrySet()) {
				if(key.equals(entry.getKey()) && value.equals(entry.getValue())) {
					relations.add(relation);
				}
			}
		}
		return relations;
	}

	public List<IOSMElement> getOSMRelationMember(String key, String value) {
		List<IOSMElement> osmElements = new ArrayList<>();
		for (OSMRelation relation : osmRelations) {
			osmElements.addAll(relation.getRelationByTag(key, value));
		}
		return osmElements;
	}

	
	

}
