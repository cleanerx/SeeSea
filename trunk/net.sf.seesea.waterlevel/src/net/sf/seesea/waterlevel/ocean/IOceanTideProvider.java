package net.sf.seesea.waterlevel.ocean;

import java.util.Date;


public interface IOceanTideProvider {

	double getTideHeight(TideLevel level, LengthUnit unit, double lat, double lon, Date date);
	
}
