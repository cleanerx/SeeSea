package net.sf.seesea.tidemodel.dtu10.java;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class DTUJavaActivator implements BundleActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.sf.seesea.tidemodel.dtu10.java"; //$NON-NLS-1$

	// The shared instance
	private static BundleContext plugin;
	
	/**
	 * The constructor
	 */
	public DTUJavaActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		plugin = context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static BundleContext getDefault() {
		return plugin;
	}

}
