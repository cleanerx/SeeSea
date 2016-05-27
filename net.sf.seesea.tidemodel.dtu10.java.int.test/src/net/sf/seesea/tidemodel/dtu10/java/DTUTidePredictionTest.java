package net.sf.seesea.tidemodel.dtu10.java;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Ignore;
import org.junit.Test;

import net.sf.seesea.waterlevel.ocean.IOceanTideProvider;
import net.sf.seesea.waterlevel.ocean.LengthUnit;
import net.sf.seesea.waterlevel.ocean.TideLevel;

public class DTUTidePredictionTest {

	
	@Test
	@Ignore
	public void testTidePrediction() throws IOException {
		DTUTidePrediction dtuTidePrediction = new DTUTidePrediction();
		DTU dtu = new DTU();
		dtu.activate(Collections.<String, Object> emptyMap());
		dtuTidePrediction.bindTideProvider(dtu);
		
		Map<String, Object> config = new HashMap<>();
		config.put(IOceanTideProvider.LATDISTANCECACHED, 0.01);
		config.put(IOceanTideProvider.LONDISTANCECACHED, 0.01);
		config.put(IOceanTideProvider.TIMEDISTANCECACHED, 10000L);
		dtuTidePrediction.activate(config);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1993);
		cal.set(Calendar.MONTH, 2 - 1);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		cal.set(Calendar.HOUR_OF_DAY, 19);
		cal.set(Calendar.MINUTE, 17);
		cal.set(Calendar.SECOND, 02);
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));

		
		double tideHeight = dtuTidePrediction.getTideHeight(TideLevel.MEANSEALEVEL, LengthUnit.METERS, -21.00, 5.0, cal.getTime());
		assertEquals("Tide height should be around 26 centimeters ",0.26562345059036485, tideHeight, 0.01);

		// FIMXE: cache kicks in
//		double latTideHeight = dtuTidePrediction.getTideHeight(TideLevel.LOWESTATRONOMICALTIDE, LengthUnit.METERS, -21.00, 5.0, cal.getTime());
//		assertEquals("Tide height should be around 26 centimeters ",0.26562345059036485, latTideHeight, 0.01);
//
//		double latTideHeightFeet = dtuTidePrediction.getTideHeight(TideLevel.LOWESTATRONOMICALTIDE, LengthUnit.FEET, -21.00, 5.0, cal.getTime());
//		assertEquals("Tide height should be around 26 centimeters ",0.26562345059036485, latTideHeightFeet, 0.01);

	}

}
