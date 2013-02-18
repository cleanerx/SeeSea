package net.sf.seesea.nmea.rcp.wizard;

import org.eclipse.jface.dialogs.IDialogSettings;
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
import org.eclipse.swt.widgets.Text;

public class UsernamePasswordWizardPage extends WizardPage {

	private Text usernameText;
	private Text passwordText;
	private String username;
	private String password;
	private Button savePasswordButton;

	public UsernamePasswordWizardPage() {
		super("UserPass", "Login Open Sea Map Depth Server", null);
		setMessage("Provide your Open Sea Map depth user name and password");
		username = "";
		password = "";
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout); 
		Label usernameLabel = new Label(composite, SWT.NONE);
		usernameLabel.setText("Username"); 
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
		passwordLabel.setText("Password"); 
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
		
		Label savePasswordLabel = new Label(composite, SWT.NONE);
		savePasswordLabel.setText("Save Password"); 
		savePasswordLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 1, 1));

		savePasswordButton = new Button(composite, SWT.CHECK);
		savePasswordButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, true, 1, 1));

		boolean savePassowrd = settings.getBoolean("savePassword");
		savePasswordButton.setSelection(savePassowrd);
		savePasswordButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				isDataPresent();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
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
				settings.put("savePassword", savePasswordButton.getSelection()); //$NON-NLS-1$
				// FIXME: encryption
				settings.put("password", passwordText.getText()); //$NON-NLS-1$
			} else if(savePasswordButton != null) {
				settings.put("savePassword", savePasswordButton.getSelection()); //$NON-NLS-1$
				settings.put("password", ""); //$NON-NLS-1$
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
