/**
 * 
Copyright (c) 2010-2016, Jens Kübler
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

package net.sf.seesea.provider.navigation.gpx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.util.GeoParser;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.ProcessingException;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FullGPXProfileHandler extends DefaultHandler {

	private final IMeasurmentProcessor measurmentProcessor;
	private SimpleDateFormat dateFormat;
	private final ITrackFile trackFile;
	private MeasuredPosition3D geoPositionInTrackPoint;
	private boolean depthTag;
	private Depth depth;
	private boolean timeTag;
	private SimpleDateFormat dateFormatMS;
	private boolean divideDepthBy100;

	public FullGPXProfileHandler(IMeasurmentProcessor measurmentProcessor, ITrackFile trackFile) {
		this.measurmentProcessor = measurmentProcessor;  
		this.trackFile = trackFile;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); //$NON-NLS-1$
		dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("Z")); //$NON-NLS-1$
		dateFormatMS = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); //$NON-NLS-1$
		dateFormatMS.setTimeZone(java.util.TimeZone.getTimeZone("Z")); //$NON-NLS-1$
		divideDepthBy100 = false;
	}
	
	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		if(uri.equals("http://www.humminbird.com")) {
			divideDepthBy100 = true;
		}
		super.startPrefixMapping(prefix, uri);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException { 
		if("trkpt".equals(localName)) { //$NON-NLS-1$
			// TODO: timezone is encoded in header
			Latitude latitude = GeoParser.parseLatitude(attributes.getValue("lat")); //$NON-NLS-1$
			Longitude longitude = GeoParser.parseLongitude(attributes.getValue("lon")); //$NON-NLS-1$
			geoPositionInTrackPoint = GeoFactory.eINSTANCE.createMeasuredPosition3D();
			geoPositionInTrackPoint.setLatitude(latitude);
			geoPositionInTrackPoint.setLongitude(longitude);
			geoPositionInTrackPoint.setTime(Calendar.getInstance().getTime());
			geoPositionInTrackPoint.setValid(true);
		} else if("WaterDepth".equals(localName)) { //$NON-NLS-1$
			depthTag = true;
		} else if("depth".equals(localName)) { //$NON-NLS-1$
			depthTag = true;
		} else if("time".equals(localName)) { //$NON-NLS-1$
			timeTag = true;
		} else if("lgb".equals(localName)) { //$NON-NLS-1$
			Latitude latitude = GeoParser.parseLatitude(attributes.getValue("Lat")); //$NON-NLS-1$
			Longitude longitude = GeoParser.parseLongitude(attributes.getValue("Lon")); //$NON-NLS-1$
			geoPositionInTrackPoint = GeoFactory.eINSTANCE.createMeasuredPosition3D();
			geoPositionInTrackPoint.setLatitude(latitude);
			geoPositionInTrackPoint.setLongitude(longitude);
		} else if("CreationDate".equals(localName)) { //$NON-NLS-1$
			timeTag = true;
		} else if("Depth".equals(localName)) { //$NON-NLS-1$
			depthTag = true;
		}

	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if(depthTag) {
			String string = new String(ch, start, length);
			if(!string.isEmpty()) {
				depth = GeoFactory.eINSTANCE.createDepth();
				double depthValue = Double.parseDouble(string);
				if(divideDepthBy100) {
					depth.setDepth(depthValue / 100);
				} else {
					depth.setDepth(depthValue);
				}
				depth.setMeasurementPosition(RelativeDepthMeasurementPosition.TRANSDUCER);
				depth.setSensorID("unknown");
				depth.setValid(true);
			}
		} else if(timeTag && geoPositionInTrackPoint != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeZone(TimeZone.getTimeZone("Z"));
				Date parse = null;
				String xml = new String(ch, start, length);
				try {
				parse = dateFormat.parse(xml);
				} catch (ParseException e) {
					try {
						parse = dateFormatMS.parse(xml);
					} catch (ParseException e2) {
						Logger.getLogger(getClass()).error("Failed to parse value " + xml);
					}
					
				}
				if(parse != null) {
					calendar.setTime(parse);
					geoPositionInTrackPoint.setTime(calendar.getTime());
					geoPositionInTrackPoint.setTimezone(dateFormat.getTimeZone().getID());
					geoPositionInTrackPoint.setSensorID("unknown");
					geoPositionInTrackPoint.setValid(true);
				} else {
					geoPositionInTrackPoint = null;
				}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("trkpt".equals(localName)) {
			List<Measurement> list = new ArrayList<Measurement>();
			if(geoPositionInTrackPoint != null) {
				list.add(geoPositionInTrackPoint);
			}
			if(depth != null) {
				list.add(depth);
			}
			geoPositionInTrackPoint = null;
			try {
				if(list.size() > 1) {
					measurmentProcessor.processMeasurements(list, "trkpt", trackFile.getTrackId(), trackFile.getBoundingBox(), trackFile);
				}
			} catch (ProcessingException e) {
				e.printStackTrace();
			}
		} else if("time".equals(localName)) {
			timeTag = false;
		} else if("WaterDepth".equals(localName)) {
			depthTag = false;
		} else if("depth".equals(localName)) {
			depthTag = false;
		} else if("lgb".equals(localName)) { //$NON-NLS-1$
			List<Measurement> list = new ArrayList<Measurement>();
			list.add(geoPositionInTrackPoint);
			list.add(depth);
			geoPositionInTrackPoint = null;
			try {
				measurmentProcessor.processMeasurements(list, "lgb", trackFile.getTrackId(), trackFile.getBoundingBox(), null);
			} catch (ProcessingException e) {
				e.printStackTrace();
			}
		} else if("CreationDate".equals(localName)) { //$NON-NLS-1$
			timeTag = false;
		} else if("Depth".equals(localName)) { //$NON-NLS-1$
			depthTag = false;
		}
	}

}
