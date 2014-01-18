/**
 * 
Copyright (c) 2014, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
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
