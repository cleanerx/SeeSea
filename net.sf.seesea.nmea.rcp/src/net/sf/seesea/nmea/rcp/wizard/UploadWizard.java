package net.sf.seesea.nmea.rcp.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

import net.sf.seesea.nmea.rcp.NMEARCPActivator;
import net.sf.seesea.provider.navigation.nmea.ui.NMEAUIActivator;
import net.sf.seesea.upload.IDataUpload;
import net.sf.seesea.upload.osm.api.v1.OSMDataUpload;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class UploadWizard extends Wizard {

	private static final String DIALOG_SETTING_FILE = "uploadDialog.xml"; //$NON-NLS-1$

	public UploadWizard() {
		DialogSettings dialogSettings = new DialogSettings("uploadDialog"); //$NON-NLS-1$
		try {
			// loads existing settings if any.
			IPath path = NMEAUIActivator.getDefault().getStateLocation();
			String filename = path.append(DIALOG_SETTING_FILE).toOSString();
			File file = new File(filename);
			if (file.exists()) {
				dialogSettings.load(filename);
			}
		} catch (IOException e) {
			Logger.getLogger(getClass()).error(
					"Failed to load dialog settings", e); //$NON-NLS-1$
		}
		setDialogSettings(dialogSettings);
		setNeedsProgressMonitor(true);
		setForcePreviousAndNextButtons(false);
	}

	@Override
	public boolean performFinish() {
		final MultiStatus status = new MultiStatus(NMEARCPActivator.PLUGIN_ID, IStatus.OK, "Upload status", null);
		
		try {
			getContainer().run(false, true, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					UsernamePasswordWizardPage usernamePasswordWizardPage = (UsernamePasswordWizardPage) getPages()[0];
					usernamePasswordWizardPage.getUsername();
					usernamePasswordWizardPage.getPassword();

					
					String server = "http://localhost"; //$NON-NLS-1$
					//
					// IPreferenceStore preferenceStore =
					// OpenSeaMapActivator.getDefault().getPreferenceStore();
					// preferenceStore.get
					// BundleContext bundleContext =
					// NMEARCPActivator.getDefault().getBundle().getBundleContext();
					// ServiceReference<ConfigurationAdmin> serviceReference =
					// bundleContext.getServiceReference(ConfigurationAdmin.class);
					// ConfigurationAdmin configurationAdmin =
					// bundleContext.getService(serviceReference);
					// configurationAdmin.get
					//		loggingDirectory = (String) config.get("loggingDirectory"); //$NON-NLS-1$

					BundleContext bundleContext = NMEARCPActivator.getDefault().getBundle()
							.getBundleContext();
					ServiceReference<ConfigurationAdmin> serviceReference = (ServiceReference<ConfigurationAdmin>) bundleContext
							.getServiceReference(ConfigurationAdmin.class);
					if (serviceReference != null) {
						ConfigurationAdmin configurationAdmin = bundleContext
								.getService(serviceReference);
						Configuration configuration;
						try {
							configuration = configurationAdmin
									.getConfiguration("net.sf.seesea.provider.navigation.nmea.datalogger"); //$NON-NLS-1$
							Dictionary<?, ?> properties = configuration.getProperties();
							String loggingDirectory = (String) properties
									.get("loggingDirectory"); //$NON-NLS-1$
							URL url = new URL(loggingDirectory);
							File directory = new File(URLDecoder.decode(url.getFile()));
							List<File> recordedFiles = Arrays.asList(directory.listFiles());

							IDataUpload dataUpload = new OSMDataUpload(server);
							monitor.setTaskName("Login");
							IStatus login = dataUpload.login(
									usernamePasswordWizardPage.getUsername(),
									usernamePasswordWizardPage.getPassword());
							status.add(login);
							if (login.isOK()) {
								monitor.setTaskName("Uploading files");
								IStatus upload = dataUpload.upload(recordedFiles, monitor);
								status.add(upload);
								if (upload.isOK()) {
									IStatus logout = dataUpload
											.logout(usernamePasswordWizardPage
													.getUsername());
									status.add(logout);
									}
							}

						} catch (IOException e) {
							Logger.getLogger(UploadWizard.class).error(
									"Failed to read configuration file for service", e);
						}
					}
				}
			});
		} catch (InvocationTargetException e) {
			Logger.getLogger(UploadWizard.class).error(
					"Failed to execute upload", e); //$NON-NLS-1$
		} catch (InterruptedException e) {
			// upload interrupted
//			e.printStackTrace();
		}
		try {
			// loads existing settings if any.
			IPath path = NMEAUIActivator.getDefault().getStateLocation();
			String filename = path.append(DIALOG_SETTING_FILE).toOSString();
			getDialogSettings().save(filename);
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("Failed to load dialog settings", e); //$NON-NLS-1$
		}    
		
		if(status.isOK()) {
			return true;
		} else {
			return false;
		}
		
	}

}
