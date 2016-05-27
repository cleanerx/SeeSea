package net.sf.seesea.tidemodel.dtu10.java;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;

import org.junit.Ignore;
import org.junit.Test;

public class DTU10Test {

	public DTU10Test() {
		// TODO Auto-generated constructor stub
	}

	@Test
	@Ignore
	public void testTide() throws IOException {
		DTU dtu = new DTU();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1993);
		cal.set(Calendar.MONTH, 2 - 1);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		cal.set(Calendar.HOUR_OF_DAY, 19);
		cal.set(Calendar.MINUTE, 17);
		cal.set(Calendar.SECOND, 02);
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		dtu.activate(Collections.<String, Object> emptyMap());
		
		assertEquals("Tide height should be around 26 centimeters ",26.562345059036485, dtu.getTideHeight(-21.00, 5.0, cal.getTime()), 0.01);
		
	}
	
}
