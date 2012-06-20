/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.osm.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.int1.base.AbstractSeamark;
import net.sf.seesea.model.int1.base.BaseFactory;
import net.sf.seesea.model.int1.base.MarineChart;
import net.sf.seesea.model.int1.buoysandbeacons.Buoy;
import net.sf.seesea.model.int1.buoysandbeacons.BuoyType;
import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsFactory;
import net.sf.seesea.model.int1.buoysandbeacons.Shape;
import net.sf.seesea.model.int1.buoysandbeacons.Topmark;
import net.sf.seesea.model.int1.buoysandbeacons.TopmarkType;
import net.sf.seesea.model.int1.lights.Color;
import net.sf.seesea.model.int1.lights.Orientation;
import net.sf.seesea.model.int1.lights.PhaseCharacteristic;
import net.sf.seesea.model.util.GeoPositionFormatter;

import org.eclipse.emf.common.util.EList;

/**
 * 
 */
public class OpenStreetMapReader {

	public EList<AbstractSeamark> read(InputStream in) throws XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = factory.createXMLStreamReader(in);
		MarineChart chartSymbols = BaseFactory.eINSTANCE.createMarineChart();

		Map<String, String> tagMap = new HashMap<String, String>();
		for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser
				.next()) {
			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				if (parser.getLocalName().equals("node")) { //$NON-NLS-1$
					String longitude = null;
					String latitude = null;
					for (int i = 0; i < parser.getAttributeCount(); i++) {
						String attributeLocalName = parser.getAttributeLocalName(i);
						if (attributeLocalName.equals("lat")) { //$NON-NLS-1$
							latitude = parser.getAttributeValue(i);
						} else if (attributeLocalName.equals("lon")) { //$NON-NLS-1$
							longitude = parser.getAttributeValue(i);
						}
					}

					tagMap.clear();
					int nextEvent = parser.next();
					while (nextEvent != XMLStreamConstants.END_ELEMENT || !parser.hasName()
							|| !parser.getLocalName().equals("node")) { //$NON-NLS-1$
						switch (nextEvent) {
						case XMLStreamConstants.START_ELEMENT:
							if (parser.getLocalName().equals("tag")) { //$NON-NLS-1$
								String key = null;
								String value = null;
								for (int i = 0; i < parser.getAttributeCount(); i++) {
									String attributeLocalName = parser.getAttributeLocalName(i);
									if (attributeLocalName.equals("k")) { //$NON-NLS-1$
										key = parser.getAttributeValue(i);
									} else if (attributeLocalName.equals("v")) { //$NON-NLS-1$
										value = parser.getAttributeValue(i);
									}
								}
								if (key != null && value != null) {
									tagMap.put(key, value);
								}
							}
							break;

						default:
							break;
						}
						nextEvent = parser.next();
					}
					AbstractSeamark createNode = createNode(latitude, longitude, tagMap);
					if(createNode != null) {
						chartSymbols.getSeamarks().add(createNode);
					}
				}
				break;
			} // end switch
		} // end while
		parser.close();
		return chartSymbols.getSeamarks();
	}

	/**
	 * @param latitude
	 * @param longitude
	 * @param tagMap
	 */
	@SuppressWarnings("nls")
	private AbstractSeamark createNode(String latitude, String longitude, Map<String, String> tagMap) {
		Buoy buoy = null;
		if (tagMap.containsKey("buoy") || (tagMap.containsKey("seamark") && tagMap.get("seamark").equals("buoy"))) {
			buoy = BuoysandbeaconsFactory.eINSTANCE.createBuoy();
			
			Latitude latitude2 = GeoFactory.eINSTANCE.createLatitude();
			GeoPositionFormatter.parseCoordinateNumerical(latitude2, latitude);
			Longitude longitude2 = GeoFactory.eINSTANCE.createLongitude();
			GeoPositionFormatter.parseCoordinateNumerical(longitude2, longitude);
			
			buoy.setLatitude(latitude2);
			buoy.setLongitude(longitude2);
			
			if(tagMap.containsKey("buoy:colour") || tagMap.containsKey("seamark:buoy_lateral:colour")) {
				try {
					String colorString = tagMap.get("buoy:colour");
					if(colorString == null) {
						colorString = tagMap.get("seamark:buoy_lateral:colour");
					}
					Color valueOf = Color.valueOf(colorString.toUpperCase());
					buoy.getColor().add(valueOf);
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_lateral:shape")) {
				try {
					Shape shape;
					if("CAN".equals(tagMap.get("seamark:buoy_lateral:shape").toUpperCase())) {
						shape = Shape.CYLINDRICAL;
					} else {
						shape = Shape.valueOf(tagMap.get("seamark:buoy_lateral:shape").toUpperCase());
					}
					if(shape != null) {
						buoy.setShape(shape);
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_special_purpose:shape")) {
				try {
					Shape shape;
					if("CAN".equals(tagMap.get("seamark:buoy_special_purpose:shape").toUpperCase())) {
						shape = Shape.CYLINDRICAL;
					} else {
						shape = Shape.valueOf(tagMap.get("seamark:buoy_special_purpose:shape").toUpperCase());
					}
					if(shape != null) {
						buoy.setShape(shape);
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_safe_water:shape")) {
				try {
					Shape shape;
					if("CAN".equals(tagMap.get("seamark:buoy_safe_water:shape").toUpperCase())) {
						shape = Shape.CYLINDRICAL;
					} else {
						shape = Shape.valueOf(tagMap.get("seamark:buoy_safe_water:shape").toUpperCase());
					}
					if(shape != null) {
						buoy.setShape(shape);
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_cardinal:shape")) {
				try {
					Shape shape;
					if("CAN".equals(tagMap.get("seamark:buoy_cardinal:shape").toUpperCase())) {
						shape = Shape.CYLINDRICAL;
					} else {
						shape = Shape.valueOf(tagMap.get("seamark:buoy_cardinal:shape").toUpperCase());
					}
					if(shape != null) {
						buoy.setShape(shape);
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("buoy:shape")) {
				try {
					Shape shape;
					if("CAN".equals(tagMap.get("buoy:shape").toUpperCase())) {
						shape = Shape.CYLINDRICAL;
					} else {
						shape = Shape.valueOf(tagMap.get("buoy:shape").toUpperCase());
					}
					if(shape != null) {
						buoy.setShape(shape);
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:name")) {
				try {
					buoy.setName(tagMap.get("seamark:name"));
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_lateral:name")) {
				try {
					buoy.setName(tagMap.get("seamark:buoy_lateral:name"));
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("light:period")) {
				try {
					buoy.setPeriod(Integer.parseInt(tagMap.get("light:period")));
				} catch (NumberFormatException e) {
					
				}
			}
			if(tagMap.containsKey("light:character")) {
				try {
					String lightCharacter = tagMap.get("light:character"); //$NON-NLS-1$
					if("FL".equals(lightCharacter.toUpperCase())) { //$NON-NLS-1$ 
						buoy.setPhaseCharacteristic(PhaseCharacteristic.FLASH);
					} else if("OC".equals(lightCharacter.toUpperCase())) {
						buoy.setPhaseCharacteristic(PhaseCharacteristic.OCCULTING);
					} else if("Q".equals(lightCharacter.toUpperCase())) {
						buoy.setPhaseCharacteristic(PhaseCharacteristic.QUICK);
					}
				} catch (NumberFormatException e) {
					
				}
			}
			if(tagMap.containsKey("light:colour")) {
				try {
					Color valueOf = Color.valueOf(tagMap.get("light:colour").toUpperCase());
					buoy.getLightcolor().add(valueOf);
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:light:color")) {
				try {
					Color valueOf = Color.valueOf(tagMap.get("light:colour").toUpperCase());
					buoy.getLightcolor().add(valueOf);
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("light:signal_group")) {
				try {
					String groups = tagMap.get("light:signal_group");
					buoy.getGroup().add(Integer.parseInt(groups));
				} catch (NumberFormatException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_safe_water:colour")) {
				try {
					String colours = tagMap.get("seamark:buoy_safe_water:colour");
					String[] split;
					if(colours.contains(",")) {
						split = colours.split(",");
					} else {
						split = colours.split(" ");
					}
					for (String colour : split) {
						buoy.getColor().add(Color.valueOf(colour.toUpperCase()));
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_special_purpose:colour")) {
				try {
					String colours = tagMap.get("seamark:buoy_special_purpose:colour");
					String[] split;
					if(colours.contains(",")) {
						split = colours.split(",");
					} else {
						split = colours.split(" ");
					}
					for (String colour : split) {
						buoy.getColor().add(Color.valueOf(colour.toUpperCase()));
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_cardinal:colour")) {
				try {
					String colours = tagMap.get("seamark:buoy_cardinal:colour");
					String[] split;
					if(colours.contains(",")) {
						split = colours.split(",");
					} else {
						split = colours.split(" ");
					}
					for (String colour : split) {
						Color valueOf = Color.valueOf(colour.toUpperCase());
						if(valueOf != null) {
							buoy.getColor().add(valueOf);
						}
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_lateral:category")) {
				try {
					if("starboard".equals(tagMap.get("seamark:buoy_lateral:category"))) {
						// FIXME: not to good
						buoy.setType(BuoyType.LATERAL_TRIANGLE);
					} else if("port".equals(tagMap.get("seamark:buoy_lateral:category"))) {
						// FIXME: not to good
						buoy.setType(BuoyType.LATERAL_SQUARE);
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			checkTopmarkShape(tagMap, buoy, "topmark:shape");
			checkTopmarkShape(tagMap, buoy, "seamark:topmark:shape");
			checkTopmarkColor(tagMap, buoy, "topmark:color");
			checkTopmarkColor(tagMap, buoy, "seamark:topmark:colour");
			if(tagMap.containsKey("seamark:buoy_cardinal:category") && tagMap.containsKey("seamark:type")) {
				try {
					String cardinalCat = tagMap.get("seamark:buoy_cardinal:category");
					if("south".equals(cardinalCat)) {
						buoy.setType(BuoyType.CARDINAL_SOUTH);
					} else if("north".equals(cardinalCat)) {
						buoy.setType(BuoyType.CARDINAL_NORTH);
					} else if("west".equals(cardinalCat)) {
						buoy.setType(BuoyType.CARDINAL_WEST);
					} else if("east".equals(cardinalCat)) {
						buoy.setType(BuoyType.CARDINAL_EAST);
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			if(tagMap.containsKey("seamark:buoy_safe_water:colour_pattern")) {
				String colourpattern = tagMap.get("seamark:buoy_safe_water:colour_pattern");
				if("vertical stripes".equals(colourpattern)) {
					buoy.setColorType(Orientation.VERTICAL);
				}
			}

		}
		return buoy;

	}

	private void checkTopmarkColor(Map<String, String> tagMap, Buoy buoy, String id) {
		if(tagMap.containsKey(id)) {
			if(buoy.getTopmark() == null) {
				Topmark topmark = BuoysandbeaconsFactory.eINSTANCE.createTopmark();
				buoy.setTopmark(topmark);
			}
			Color valueOf = Color.valueOf(tagMap.get(id).toUpperCase());
			if(buoy.getTopmark().getTopmarkColor() != null) {
				buoy.getTopmark().setTopmarkColor(valueOf);
			}
		}
	}

	private void checkTopmarkShape(Map<String, String> tagMap, Buoy buoy, String id) {
		if(tagMap.containsKey(id)) {
			if(buoy.getTopmark() == null) {
				Topmark topmark = BuoysandbeaconsFactory.eINSTANCE.createTopmark();
				buoy.setTopmark(topmark);
			}
			if(buoy.getTopmark().getTopmarkParts().isEmpty()) {
				String value = tagMap.get(id).toUpperCase();
				if("SPEHRE".equals(value)) {
					// FIXME: not to good
					buoy.getTopmark().getTopmarkParts().add(TopmarkType.BALL);
				} else if("CYLINDER".equals(value)) {
					buoy.getTopmark().getTopmarkParts().add(TopmarkType.CYLINDER);
				} else if("CONE".equals(value)) {
					buoy.getTopmark().getTopmarkParts().add(TopmarkType.CONE_UP);
				} else {
					if("CROSS".equals(value) || "X-CROSS".equals(value) || "X-SHAPE".equals(value)) {
						buoy.getTopmark().getTopmarkParts().add(TopmarkType.XCROSS);
					} else if("2 CONES BASE TOGETHER".equals(value)) { // east
						buoy.getTopmark().getTopmarkParts().add(TopmarkType.CONE_UP);
						buoy.getTopmark().getTopmarkParts().add(TopmarkType.CONE_DOWN);
					} else if("2 CONES DOWN".equals(value)) { // south
						buoy.getTopmark().getTopmarkParts().add(TopmarkType.CONE_DOWN);
						buoy.getTopmark().getTopmarkParts().add(TopmarkType.CONE_DOWN);
					} else if("2 CONES UP".equals(value)) { // north
						buoy.getTopmark().getTopmarkParts().add(TopmarkType.CONE_UP);
						buoy.getTopmark().getTopmarkParts().add(TopmarkType.CONE_UP);
//				} else if("2 cones base together".equals(tagMap.get("seamark:topmark:shape"))) {
//					buoy.getTopmark().add(TopmarkType.CONE_UP);
//					buoy.getTopmark().add(TopmarkType.CONE_DOWN);
					}
				} 
				
			}
		}
	}
}
