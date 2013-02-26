package nl.esi.metis.aisparser;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


public class AISActivator extends Plugin {

	private static AISActivator plugin;

	public static AISActivator getPlugin() {
		if(plugin == null) {
			plugin = new AISActivator();
		}
		return plugin;
	}
	
	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AISActivator getDefault() {
		return plugin;
	}
	

}
