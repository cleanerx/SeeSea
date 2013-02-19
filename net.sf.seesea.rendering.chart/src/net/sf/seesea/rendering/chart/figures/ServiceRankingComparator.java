package net.sf.seesea.rendering.chart.figures;

import java.util.Comparator;

import org.osgi.framework.ServiceReference;

public class ServiceRankingComparator<T extends ServiceReference<?>> implements Comparator<ServiceReference<?>> {

	public int compare(ServiceReference<?> o1, ServiceReference<?> o2) {
		String s1 = (String) o1.getProperty(org.osgi.framework.Constants.SERVICE_RANKING);
		String s2 = (String) o2.getProperty(org.osgi.framework.Constants.SERVICE_RANKING);
		Integer i1 = Integer.parseInt(s1);
		Integer i2 = Integer.parseInt(s2);
		return -1 * i1.compareTo(i2);
	}

}
