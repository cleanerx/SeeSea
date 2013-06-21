package net.sf.seesea.nmea.rcp.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;

public class ExportNoviceWizardPage extends WizardPage {

	public ExportNoviceWizardPage() {
		super("Vessels", "Vessels", null);
		setTitle("Expert Level");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.pack();
		setControl(composite);
		
		Button btnRadioButton = new Button(composite, SWT.RADIO);
		btnRadioButton.setBounds(239, 128, 90, 16);
		btnRadioButton.setText("Novice");
		
		Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
		btnRadioButton_1.setBounds(239, 150, 90, 16);
		btnRadioButton_1.setText("Expert");
	}
}
