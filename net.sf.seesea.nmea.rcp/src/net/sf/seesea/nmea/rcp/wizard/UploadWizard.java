package net.sf.seesea.nmea.rcp.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.seesea.lib.ResultStatus;
import net.sf.seesea.nmea.rcp.NMEARCPActivator;
import net.sf.seesea.nmea.rcp.preferences.IUploadPreferences;
import net.sf.seesea.provider.navigation.nmea.ui.NMEAUIActivator;
import net.sf.seesea.upload.IDataUpload;
import net.sf.seesea.upload.osm.api.v1.OSMDataUpload;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
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
		final List<File> recordedFiles = new ArrayList<File>();
		final MultiStatus status = new MultiStatus(NMEARCPActivator.PLUGIN_ID, IStatus.OK, Messages.getString("UploadWizard.uploadState"), null); //$NON-NLS-1$
		
		BundleContext bundleContext = NMEARCPActivator.getDefault().getBundle()
				.getBundleContext();
		ServiceReference<ConfigurationAdmin> serviceReference = (ServiceReference<ConfigurationAdmin>) bundleContext
				.getServiceReference(ConfigurationAdmin.class);
		if (serviceReference != null) {
			try {
			ConfigurationAdmin configurationAdmin = bundleContext
					.getService(serviceReference);
			Configuration configuration;
			configuration = configurationAdmin
					.getConfiguration("net.sf.seesea.provider.navigation.nmea.datalogger"); //$NON-NLS-1$
			Dictionary<?, ?> properties = configuration.getProperties();
			String loggingDirectory = (String) properties
					.get("loggingDirectory"); //$NON-NLS-1$
			URL url;
				url = new URL(loggingDirectory);
			File directory = new File(URLDecoder.decode(url.getFile()));
			recordedFiles.addAll(Arrays.asList(directory.listFiles()));
			getContainer().run(true, true, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
				InterruptedException {
					UsernamePasswordWizardPage usernamePasswordWizardPage = (UsernamePasswordWizardPage) getPages()[0];
					usernamePasswordWizardPage.getUsername();
					usernamePasswordWizardPage.getPassword();
					
					IPreferenceStore preferenceStore = NMEARCPActivator.getDefault().getPreferenceStore();
					String uploadServer = preferenceStore.getString(IUploadPreferences.UPLOAD_SERVER);
					if(uploadServer == null || uploadServer.isEmpty()) {
						try {
							new URL(uploadServer);
						} catch (MalformedURLException e) {
							status.add(new Status(IStatus.ERROR,
									NMEARCPActivator.PLUGIN_ID,"The upload server URL from preferences is not valid. Please check the URL for validity"));
							// return from runnable
							return;
						}
					}

					IDataUpload dataUpload = new OSMDataUpload(uploadServer);
					monitor.beginTask(Messages.getString("UploadWizard.dataUploadTask"), recordedFiles.size()); //$NON-NLS-1$
					monitor.subTask(Messages.getString("UploadWizard.loginTask")); //$NON-NLS-1$
					IStatus login = dataUpload.login(
							usernamePasswordWizardPage.getUsername(),
							usernamePasswordWizardPage.getPassword());
					status.add(login);
					if (login.isOK()) {
						monitor.subTask(Messages.getString("UploadWizard.uploadFilesTask")); //$NON-NLS-1$
						IStatus upload = dataUpload.upload(recordedFiles, monitor);
						status.add(upload);
						if (upload.isOK()) {
							monitor.subTask(Messages.getString("UploadWizard.uploadCompletedTask")); //$NON-NLS-1$
							monitor.subTask(Messages.getString("UploadWizard.logoutTask")); //$NON-NLS-1$
							IStatus logout = dataUpload
									.logout(usernamePasswordWizardPage
											.getUsername());
							status.add(logout);
						}
					}
					
				}
			});
			} catch (MalformedURLException e) {
				Logger.getLogger(getClass()).error("Failed URL", e); //$NON-NLS-1$
			} catch (IOException e) {
				Logger.getLogger(UploadWizard.class).error(
						"Failed to read configuration file for service", e); //$NON-NLS-1$
			} catch (InvocationTargetException e) {
				Logger.getLogger(UploadWizard.class).error(
						"Failed to execute upload", e); //$NON-NLS-1$
			} catch (InterruptedException e) {
				return false;
			}
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
		} else if(status.getCode() == IStatus.CANCEL) {
			return false;
		} else {
			ErrorDialog.openError(Display.getDefault().getActiveShell(), Messages.getString("UploadWizard.errorDialog"), Messages.getString("UploadWizard.errorMsg") , status); //$NON-NLS-1$ //$NON-NLS-2$
			Set<File> erronousFiles = new HashSet<File>();
			IStatus[] children = status.getChildren();
			for (IStatus childstatus : children) {
				if(childstatus instanceof ResultStatus) {
					ResultStatus<?> resultStatus = (ResultStatus<?>) childstatus;
					if(resultStatus.getResult() instanceof File) {
						erronousFiles.add((File) resultStatus.getResult());
					}
				}
			}
			for (File file : recordedFiles) {
				if(!erronousFiles.contains(file)) {
					file.delete();
				}
			}
			
			return false;
		}
		
	}

}
