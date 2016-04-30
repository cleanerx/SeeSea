package net.sf.seesea.navigation.winprofile.sharp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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

public class FullWinProfileHandler extends DefaultHandler {

	private final IMeasurmentProcessor measurmentProcessor;
	private SimpleDateFormat dateFormat;
	private final ITrackFile trackFile;

	public FullWinProfileHandler(IMeasurmentProcessor measurmentProcessor, ITrackFile trackFile) {
		this.measurmentProcessor = measurmentProcessor;
		this.trackFile = trackFile;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS"); //$NON-NLS-1$
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("Depth".equals(localName)) { //$NON-NLS-1$
			// TODO: timezone is encoded in header
			String dateString = attributes.getValue("Date"); //$NON-NLS-1$
			String timeString = attributes.getValue("Time"); //$NON-NLS-1$
			try {
				Date date = dateFormat.parse(dateString + " " + timeString);
				Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
				calendar.setTime(date);
				
				Latitude latitude = GeoParser.parseLatitude(attributes.getValue("Corrected_Latitude")); //$NON-NLS-1$
				Longitude longitude = GeoParser.parseLongitude(attributes.getValue("Corrected_Longitude")); //$NON-NLS-1$
				double depthValue = Integer.parseInt(attributes.getValue("Corrected_Depth_high_Freq_onFootprint")) / 100.0; //$NON-NLS-1$
				
				MeasuredPosition3D geoPosition3D = GeoFactory.eINSTANCE.createMeasuredPosition3D();
				geoPosition3D.setLatitude(latitude);
				geoPosition3D.setLongitude(longitude);
				geoPosition3D.setTime(calendar.getTime());
				geoPosition3D.setValid(true);
				Depth depth = GeoFactory.eINSTANCE.createDepth();
				depth.setMeasurementPosition(RelativeDepthMeasurementPosition.SURFACE);
				depth.setDepth(depthValue);
				depth.setValid(true);
				depth.setTime(calendar.getTime());
				
				List<Measurement> measurements = new ArrayList<Measurement>(2);
				measurements.add(geoPosition3D);
				measurements.add(depth);
				measurmentProcessor.processMeasurements(measurements, "", trackFile.getTrackId(), trackFile.getBoundingBox()); //$NON-NLS-1$
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (ProcessingException e) {
				Logger.getLogger(getClass()).error("Failed to log measurments", e);
			}
			
		}
	}

}
