package net.sf.seesea.nmea.rcp.wizard;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import net.sf.seesea.nmea.rcp.NMEARCPActivator;
import net.sf.seesea.nmea.rcp.preferences.IUploadPreferences;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class UsernamePasswordWizardPage extends WizardPage {

	private static final String SAVE_PASSWORD = "savePassword"; //$NON-NLS-1$
	
	private Text usernameText;
	private Text passwordText;
	private String username;
	private String password;
	private Button savePasswordButton;

	public UsernamePasswordWizardPage() {
		super("UserPass", Messages.getString("UsernamePasswordWizardPage.loginWizard"), ImageDescriptor.createFromURL(NMEARCPActivator.getDefault().getBundle().getEntry("/icons/Upload64.png"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setMessage(Messages.getString("UsernamePasswordWizardPage.loginDescription")); //$NON-NLS-1$
		username = ""; //$NON-NLS-1$
		password = ""; //$NON-NLS-1$
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout); 
		Label usernameLabel = new Label(composite, SWT.NONE);
		usernameLabel.setText(Messages.getString("UsernamePasswordWizardPage.usernameWizard"));  //$NON-NLS-1$
		usernameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 1, 1));

		IDialogSettings settings = getWizard().getDialogSettings();

		usernameText = new Text(composite, SWT.BORDER);
		GridData gd_usernameText = new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1);
		gd_usernameText.widthHint = 187;
		usernameText.setLayoutData(gd_usernameText);
		usernameText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				isDataPresent();
			}
		});

		String usernamePref = settings.get("username"); //$NON-NLS-1$
		if(usernamePref != null) {
			usernameText.setText(usernamePref);
		}

		Label passwordLabel = new Label(composite, SWT.NONE);
		passwordLabel.setText(Messages.getString("UsernamePasswordWizardPage.passwordWizard"));  //$NON-NLS-1$
		passwordLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 1, 1));

		passwordText = new Text(composite, SWT.BORDER);
		GridData gd_passwordText = new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1);
		gd_passwordText.widthHint = 196;
		passwordText.setLayoutData(gd_passwordText);
		passwordText.setEchoChar('*');
		passwordText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				isDataPresent();
			}
		});
		
		String passwordPref = settings.get("password"); //$NON-NLS-1$
		if(passwordPref != null) {
			passwordText.setText(passwordPref);
		}
		IPreferenceStore preferenceStore = NMEARCPActivator.getDefault().getPreferenceStore();
		String registerURL = preferenceStore.getString(IUploadPreferences.REGISTER_URL);
		if(registerURL != null && !registerURL.isEmpty()) {
			new Label(composite, SWT.NONE);
			
			Link lblRegisterAccount = new Link(composite, SWT.NONE);
			
			String registerText = Messages.getString("UsernamePasswordWizardPage.registerAccount"); //$NON-NLS-1$
			lblRegisterAccount.setText(MessageFormat.format("<a href=\"{0}\">{1}</a>", registerURL, registerText)); //$NON-NLS-1$
			lblRegisterAccount.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(e.text));
					} catch (PartInitException e1) {
						Logger.getLogger(getClass()).error("Failed to open browser", e1); //$NON-NLS-1$
					} catch (MalformedURLException e1) {
						Logger.getLogger(getClass()).error("Failed to open browser", e1); //$NON-NLS-1$
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					//
				}
			});
		}
		
		Label savePasswordLabel = new Label(composite, SWT.NONE);
		savePasswordLabel.setText(Messages.getString("UsernamePasswordWizardPage.savePassword"));  //$NON-NLS-1$
		savePasswordLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 1, 1));

		savePasswordButton = new Button(composite, SWT.CHECK);
		savePasswordButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, true, 1, 1));

		boolean savePassowrd = settings.getBoolean(SAVE_PASSWORD);
		savePasswordButton.setSelection(savePassowrd);
		savePasswordButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				isDataPresent();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				//
			}
		});

		composite.pack();
		setControl(composite);
		isDataPresent();
	}

	public boolean isDataPresent() {
		
		if(!usernameText.getText().isEmpty() && passwordText != null && !passwordText.getText().isEmpty()) {
			username = usernameText.getText();
			password = passwordText.getText();
			setPageComplete(true);
			IDialogSettings settings = getWizard().getDialogSettings();
			settings.put("username", usernameText.getText()); //$NON-NLS-1$
			if(savePasswordButton != null && savePasswordButton.getSelection()) {
				settings.put(SAVE_PASSWORD, savePasswordButton.getSelection());
				// FIXME: encryption
				settings.put("password", passwordText.getText()); //$NON-NLS-1$
			} else if(savePasswordButton != null) {
				settings.put(SAVE_PASSWORD, savePasswordButton.getSelection());
				settings.put("password", ""); //$NON-NLS-1$ //$NON-NLS-2$
			}

		} else {
			setPageComplete(false);
		}
		return true;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	

}
