package net.sf.seesea.nmea.rcp.wizard;

import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Group;

public class OrganizationWizardPage extends WizardPage {
	private Text organisationName;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	public OrganizationWizardPage() {
		super("Organization", "Contact", null);
		setMessage("Update you contact information and terms for the data you will upload");
		setTitle("Contact Information");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.pack();
		setControl(composite);
		
		Group grpOrganisation = new Group(composite, SWT.NONE);
		grpOrganisation.setText("Organisation");
		grpOrganisation.setBounds(10, 0, 554, 100);
		
		Button btnNoOrganization = new Button(grpOrganisation, SWT.RADIO);
		btnNoOrganization.setToolTipText("Choose this option if you do not belong to an organization like a hydrographic office");
		btnNoOrganization.setBounds(10, 28, 131, 16);
		btnNoOrganization.setText("No Organization");
		btnNoOrganization.setSelection(true);
		
		Button btnOrganization = new Button(grpOrganisation, SWT.RADIO);
		btnOrganization.setToolTipText("Choose this option if you belong to an organization like a hydrographic office");
		btnOrganization.setBounds(10, 50, 90, 16);
		btnOrganization.setText("Organization");

		final Label lblOrganizationName = new Label(grpOrganisation, SWT.NONE);
		lblOrganizationName.setBounds(44, 72, 131, 15);
		lblOrganizationName.setText("Organization Name");
		lblOrganizationName.setEnabled(false);

		organisationName = new Text(grpOrganisation, SWT.BORDER);
		organisationName.setToolTipText("An organization name i.e Hydrographic Office of Spain");
		organisationName.setBounds(241, 69, 303, 21);
		organisationName.setEnabled(false);
		
		btnNoOrganization.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				organisationName.setEnabled(false);
				lblOrganizationName.setEnabled(false);
			};
		});
		btnOrganization.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				organisationName.setEnabled(true);
				lblOrganizationName.setEnabled(true);
			}
		});
		
		Group grpContact = new Group(composite, SWT.NONE);
		grpContact.setText("Contact");
		grpContact.setBounds(10, 106, 554, 182);
		
		Label lblName = new Label(grpContact, SWT.NONE);
		lblName.setBounds(10, 22, 131, 15);
		lblName.setText("Name");
		
		text_1 = new Text(grpContact, SWT.BORDER);
		text_1.setToolTipText("Your realname");
		text_1.setBounds(241, 19, 303, 21);
		
		Label lblNewLabel = new Label(grpContact, SWT.NONE);
		lblNewLabel.setBounds(10, 49, 131, 15);
		lblNewLabel.setText("Email");
		
		text_2 = new Text(grpContact, SWT.BORDER);
		text_2.setToolTipText("Provide an email address where you be be contacted");
		text_2.setBounds(241, 46, 303, 21);
		
		text_3 = new Text(grpContact, SWT.BORDER);
		text_3.setBounds(241, 75, 303, 21);
		
		Label lblTelephone = new Label(grpContact, SWT.NONE);
		lblTelephone.setBounds(10, 78, 225, 15);
		lblTelephone.setText("Telephone (e.g. +49 123 45689)");
		
		Label lblDataLicense = new Label(grpContact, SWT.NONE);
		lblDataLicense.setBounds(10, 113, 131, 15);
		lblDataLicense.setText("Data License*");
		
		ComboViewer comboViewer = new ComboViewer(grpContact, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.setBounds(241, 110, 303, 23);
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setLabelProvider(new LabelProvider());
		String[] licenses = new String[] {"Public Domain", "Proprietary"};
		comboViewer.setInput(licenses);
		comboViewer.setSelection(new StructuredSelection(licenses[0]));
		DefaultToolTip defaultToolTip = new DefaultToolTip(comboViewer.getCombo());
		defaultToolTip.setText(Messages.getString("Text"));
//		defaultToolTip.

		Button btnCititationRequired = new Button(grpContact, SWT.CHECK);
		btnCititationRequired.setToolTipText("Check this box if your data must cited if products are made out of it");
		btnCititationRequired.setBounds(241, 139, 303, 16);
		btnCititationRequired.setText("Cititation as data source required");
		
		Label lblRequiredField = new Label(grpContact, SWT.NONE);
		lblRequiredField.setBounds(10, 157, 108, 15);
		lblRequiredField.setText("* Required Field");
		
		
	}
}
