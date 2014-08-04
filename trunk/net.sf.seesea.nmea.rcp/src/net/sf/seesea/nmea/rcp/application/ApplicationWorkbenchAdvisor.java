/**
 * 
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.nmea.rcp.application;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Properties;

import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.util.IModel;
import net.sf.seesea.nmea.rcp.NMEARCPActivator;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String NMEA_LOGGING = "NMEALogging"; //$NON-NLS-1$
	private static final String PERSPECTIVE_ID = "net.sf.seesea.nmea.perspective"; //$NON-NLS-1$

	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		configurer.setSaveAndRestore(true);
	}

	@Override
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#preStartup()
	 */
	@Override
	public void preStartup() {
		super.preStartup();
		System.setProperty("logDirectory", System.getProperty("user.home")); //$NON-NLS-1$ //$NON-NLS-2$
		Logger.getRootLogger().info("Initializing Application"); //$NON-NLS-1$
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		try {
			IProject project = root.getProject(NMEA_LOGGING);
			if (!project.exists()) {
				project.create(new NullProgressMonitor());
			}
			if (!project.isOpen()) {
				project.open(null);
			}

			IFolder logFolder = project.getFolder("logs"); //$NON-NLS-1$
			if (!logFolder.exists()) {
				logFolder.create(IResource.NONE, true, null);
			}
			IFolder cacheFolder = project.getFolder("tilecache"); //$NON-NLS-1$
			if (!cacheFolder.exists()) {
				cacheFolder.create(IResource.NONE, true, null);
			}

			BundleContext bundleContext = NMEARCPActivator.getDefault().getBundle().getBundleContext();
			ServiceReference<ConfigurationAdmin> serviceReference = (ServiceReference<ConfigurationAdmin>) bundleContext.getServiceReference(ConfigurationAdmin.class);
			if(serviceReference != null) {
				ConfigurationAdmin configurationAdmin = bundleContext.getService(serviceReference);
				Configuration configuration = configurationAdmin.getConfiguration("net.sf.seesea.provider.navigation.nmea.datalogger"); //$NON-NLS-1$
				Properties properties = new Properties();
				properties.put("rotateFileName", true); //$NON-NLS-1$
				properties.put("loggingDirectory", logFolder.getLocationURI().toURL().toString()); //$NON-NLS-1$
				properties.put("cacheDirectory", URLDecoder.decode(cacheFolder.getLocationURI().toURL().toString())); //$NON-NLS-1$
				configuration.update(properties);
			}
		} catch (IOException e) {
			Logger.getRootLogger().error("Failed to create log folder", e); //$NON-NLS-1$
		} catch (CoreException e) {
			Logger.getRootLogger().error("Failed to create NMEA logging resource", e); //$NON-NLS-1$
		}

	}

	@Override
	public void postStartup() {
		super.postStartup();
	}
	
	@Override
	public boolean preShutdown() {
		BundleContext bundleContext = NMEARCPActivator.getDefault().getBundle().getBundleContext(); 
		ServiceReference<IModel> serviceReference = bundleContext.getServiceReference(IModel.class);
		IModel model = bundleContext.getService(serviceReference);
		try {
			model.saveModel();
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("failed to save model", e);
		}
		bundleContext.ungetService(serviceReference);
		return super.preShutdown();
	}
	
}
