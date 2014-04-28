package net.sf.seesea.services.navigation.provider;

import java.util.Date;

public interface IOceanTideProvider {

	double getTideHeight(TideLevel level, LengthUnit unit, double lat, double lon, Date date);
	
}
