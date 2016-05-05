package net.sf.seesea.data.postprocessing.filter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class FilterActivator implements BundleActivator {

	private static BundleContext context;

	@Override
	public void start(BundleContext context) throws Exception {
		this.context = context;

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.context = null;
	}

	public static BundleContext getContext() {
		return context;
	}
	
	

}
