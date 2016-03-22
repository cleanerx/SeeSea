package net.sf.seesea.tidemodel.dtu10.java;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class DTUJavaActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.sf.seesea.tidemodel.dtu10.java"; //$NON-NLS-1$

	// The shared instance
	private static DTUJavaActivator plugin;
	
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
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DTUJavaActivator getDefault() {
		return plugin;
	}

}
