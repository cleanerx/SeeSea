package net.sf.seesea.tidemodel.dtu10.java;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.seesea.waterlevel.ocean.IOceanTideProvider;
import net.sf.seesea.waterlevel.ocean.ITideProvider;
import net.sf.seesea.waterlevel.ocean.LengthUnit;
import net.sf.seesea.waterlevel.ocean.TideLevel;

/**
 * This is the tide prediction with the ocean tide correction.
 * Note: The mean sea level to LAT correction is still incomplete and some areas are still missing
 * 
 * @author Jens Kübler
 *
 */
@org.osgi.service.component.annotations.Component(configurationPolicy = org.osgi.service.component.annotations.ConfigurationPolicy.REQUIRE)
public class DTUTidePrediction implements IOceanTideProvider {

	private float[][] msl2latCorrection;
	
	private double latDistance = 0.0166;
	private double lonDistance = 0.0166;
	private long timeDistance = 120000;

	private LATTideCorrectionCache latTidePredictionCache;

	private ITideProvider tideProvider;

	@org.osgi.service.component.annotations.Activate
	public void activate(Map<String, Object> properties) throws IOException {
		Object latDistanceConfig = properties.get(IOceanTideProvider.LATDISTANCECACHED);
		if(latDistanceConfig != null) {
			latDistance = (Double) latDistanceConfig;
		}
		Object lonDistanceConfig = properties.get(IOceanTideProvider.LONDISTANCECACHED);
		if(lonDistanceConfig != null) {
			lonDistance = (Double) lonDistanceConfig;
		}
		Object timeDistanceConfig = properties.get(IOceanTideProvider.TIMEDISTANCECACHED);
		if(timeDistanceConfig != null) {
			timeDistance = (Long) timeDistanceConfig;
		}
		msl2latCorrection = new float[2881][1441];
		URL dataLocation = DTUJavaActivator.getDefault().getBundle().getEntry("res/LAT.ser");

		try(ObjectInputStream objectOutputStream = new ObjectInputStream(new BufferedInputStream(dataLocation.openStream(), 16384 * 4))) {
			for(int i = -1440 ; i < 1440; i++ ) {
				for(int j = 720; j > -720 ; j--) {
					float readFloat = objectOutputStream.readFloat();
					msl2latCorrection[i + 1440][((j * (- 1)) + 720)] = readFloat;
				}
			}
		}
	}
	
	public double getTideHeight(TideLevel level, LengthUnit unit, double lat,
			double lon, Date date) {
		LATTideCorrectionCache query = new LATTideCorrectionCache(lat, lon, date);
		if(latTidePredictionCache != null && latTidePredictionCache.isQueryClose(query, latDistance, lonDistance, timeDistance)) {
			return latTidePredictionCache.getLatCorrection();
		}
		Logger logger = Logger.getLogger(getClass());
		long startTime = 0;
		if(logger.isDebugEnabled()) {
			logger.debug("Predicting for " + lat + ":" + lon + ":" + date);
			startTime = System.currentTimeMillis();
		}
		double tideHeight = tideProvider.getTideHeight(lat, lon, date);
		if(logger.isDebugEnabled()) {
			long endTime = System.currentTimeMillis();
			logger.debug("Tide calculated in " + (endTime - startTime) + "ms");
		}
		double tide = 0.0;
		if (TideLevel.MEANSEALEVEL.equals(level)) {
			switch (unit) {
			case FEET:
				tide = (float) (3.2808399 * tideHeight / 100);
				break;
			default:
				tide = (float) tideHeight / 100;
				break;
			}
		} else {
			int x2 = (int) Math.floor((lon + 180) / 0.125);
			int y2 = (int) Math.floor((((-1) * lat) + 90) / 0.125);
			
			double interpolatedmsl2lat = BillinearInterpolation.billinearInterpolation(msl2latCorrection, lon, lat, x2, y2);
			switch (unit) {
			case FEET:
				tide = (3.2808399 * ((tideHeight / 100) - interpolatedmsl2lat));
				break;
			default:
				tide = ((tideHeight / 100) - (float) (interpolatedmsl2lat)) ;
				break;
			}
		}
		query.setLatCorrection(tide);
		latTidePredictionCache = query;
		if(logger.isDebugEnabled()) {
			logger.debug("Tide Prediction: " + tide);
		}

		return tide;
	}
	
	@org.osgi.service.component.annotations.Reference(cardinality = org.osgi.service.component.annotations.ReferenceCardinality.MANDATORY, policy = org.osgi.service.component.annotations.ReferencePolicy.DYNAMIC)
	public void bindTideProvider(ITideProvider tideProvider) {
		this.tideProvider = tideProvider;
	}

	public void unbindTideProvider(ITideProvider tideProvider) {
		this.tideProvider = null;
	}
	
}
