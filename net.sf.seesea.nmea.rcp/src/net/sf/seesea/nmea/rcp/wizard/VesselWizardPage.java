package net.sf.seesea.nmea.rcp.wizard;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.widgets.Button;

public class VesselWizardPage extends WizardPage {
	private Text lengthOfHull;
	private Text beam;
	private Text draft;
	private Text configName;
	private Text description;

	private String configurationName;
	private double lengthOfHullValue;
	private double beamValue;
	private double draftValue;

	public VesselWizardPage() {
		super("Vessels", "Vessels", null);
		setMessage("Manage your vessel configurations for which you provide data");
		setTitle("Vessel Configuration");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.pack();
		setControl(composite);
		setPageComplete(false);
		
		Label lblConfiguration = new Label(composite, SWT.NONE);
		lblConfiguration.setText("Configuration");
		ComboViewer comboViewer = new ComboViewer(composite, SWT.NONE);
		Combo combo = comboViewer.getCombo();

		Button btnDelete = new Button(composite, SWT.NONE);
		btnDelete.setText("Delete");
		
		Label lblName = new Label(composite, SWT.NONE);
		lblName.setText("Configuration Name");
		configName = new Text(composite, SWT.BORDER);
		configName.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				configurationName = configName.getText();
				isDataPresent();
			}
		});
		
		Label lblDescriptionNotes = new Label(composite, SWT.NONE);
		lblDescriptionNotes.setText("Description / Notes");
		description = new Text(composite, SWT.BORDER);
		description.setToolTipText("Describe anything that may be of additional interest");

		Label lblMeasurementUnit = new Label(composite, SWT.NONE);
		lblMeasurementUnit.setText("Measurement Unit");
		ComboViewer comboViewer_1 = new ComboViewer(composite, SWT.NONE);

		final String[] units = new String[]{"Metric","Imperial"};
		final Map<String, String> shortFormUnit = new HashMap<String, String>();
		shortFormUnit.put(units[0], " (m)");
		shortFormUnit.put(units[1], " (yd)");

		final Label lblVesselLengh = new Label(composite, SWT.NONE);
		final String loh = "Length of Hull*";
		lblVesselLengh.setText(loh + shortFormUnit.get(units[0]));
		lengthOfHull = new Text(composite, SWT.BORDER);
		lengthOfHull.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				 try {
					 Double.parseDouble(lengthOfHull.getText());
					 setErrorMessage(null);
					 isDataPresent();
				 } catch (NumberFormatException e2) {
					 setErrorMessage(loh + " is not a number");
				}
			}

		});
		
		final Label lblVesselWidth = new Label(composite, SWT.NONE);
		final String beamLabel = "Beam*";
		lblVesselWidth.setText(beamLabel + shortFormUnit.get(units[0]));
		beam = new Text(composite, SWT.BORDER);
		beam.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				 try {
					 Double.parseDouble(beam.getText());
					 setErrorMessage(null);
					 isDataPresent();
				 } catch (NumberFormatException e2) {
					 setErrorMessage(lblVesselWidth + " is not a number");
				}
			}
		});
		
		final Label lblDraft = new Label(composite, SWT.NONE);
		final String draftLabel = "Draft*";
		lblDraft.setText(draftLabel + shortFormUnit.get(units[0]));
		draft = new Text(composite, SWT.BORDER);
		draft.setToolTipText("The name of  your configuration");
		draft.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				 try {
					 Double.parseDouble(lengthOfHull.getText());
					 setErrorMessage(null);
					 isDataPresent();
				 } catch (NumberFormatException e2) {
					 setErrorMessage(draftLabel + " is not a number");
				}
			}
		});
		
		comboViewer_1.setContentProvider(new ArrayContentProvider());
		comboViewer_1.setLabelProvider(new LabelProvider());
		comboViewer_1.setInput(units);
		comboViewer_1.setSelection(new StructuredSelection(units[0]));
		Combo combo_1 = comboViewer_1.getCombo();
		comboViewer_1.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				lblVesselLengh.setText(loh + shortFormUnit.get(((IStructuredSelection)event.getSelection()).getFirstElement()));
				lblVesselWidth.setText(beamLabel + shortFormUnit.get(((IStructuredSelection)event.getSelection()).getFirstElement()));
				lblDraft.setText(draftLabel + shortFormUnit.get(((IStructuredSelection)event.getSelection()).getFirstElement()));
			}
		});
		
		Label lblRequiredField = new Label(composite, SWT.NONE);
		lblRequiredField.setText("* Required Field");
		
		Button btnCopy = new Button(composite, SWT.NONE);
		btnCopy.setText("Copy to new");
		

		GroupLayout gl_composite = new GroupLayout(composite);
		gl_composite.setHorizontalGroup(
			gl_composite.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_composite.createSequentialGroup()
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(gl_composite.createSequentialGroup()
							.add(10)
							.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
								.add(gl_composite.createSequentialGroup()
									.add(lblConfiguration, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(combo, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED, 15, Short.MAX_VALUE)
									.add(btnCopy)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(btnDelete))
								.add(gl_composite.createSequentialGroup()
									.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
										.add(gl_composite.createSequentialGroup()
											.addPreferredGap(LayoutStyle.RELATED)
											.add(lblMeasurementUnit))
										.add(gl_composite.createSequentialGroup()
											.addPreferredGap(LayoutStyle.RELATED)
											.add(lblVesselLengh, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
										.add(gl_composite.createSequentialGroup()
											.addPreferredGap(LayoutStyle.RELATED)
											.add(lblVesselWidth, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
										.add(gl_composite.createSequentialGroup()
											.addPreferredGap(LayoutStyle.RELATED)
											.add(lblDescriptionNotes))
										.add(lblName, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.add(gl_composite.createSequentialGroup()
											.addPreferredGap(LayoutStyle.RELATED)
											.add(lblDraft, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
									.add(27)
									.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
										.add(combo_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.add(lengthOfHull, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.add(description, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
										.add(configName, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
										.add(draft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.add(beam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.add(gl_composite.createSequentialGroup()
							.addContainerGap()
							.add(lblRequiredField)))
					.addContainerGap())
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(gl_composite.createSequentialGroup()
							.add(13)
							.add(lblConfiguration))
						.add(gl_composite.createSequentialGroup()
							.add(10)
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.add(btnDelete)
								.add(btnCopy))))
					.add(19)
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(lblName)
						.add(configName, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
						.add(description, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.add(lblDescriptionNotes))
					.addPreferredGap(LayoutStyle.RELATED, 15, Short.MAX_VALUE)
					.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
						.add(lblMeasurementUnit)
						.add(combo_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(5)
					.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
						.add(lblVesselLengh)
						.add(lengthOfHull, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
						.add(beam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblVesselWidth))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
						.add(draft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblDraft))
					.add(26)
					.add(lblRequiredField)
					.addContainerGap())
		);
		composite.setLayout(gl_composite);
	}
	
	private void isDataPresent() {
		if(lengthOfHull != null && !lengthOfHull.getText().isEmpty() 
				&& beam != null && !beam.getText().isEmpty()
				&& draft != null && !draft.getText().isEmpty()
				&& configurationName != null && !configurationName.isEmpty()
				) {
			try {
				lengthOfHullValue = Double.parseDouble(lengthOfHull.getText());
				beamValue = Double.parseDouble(beam.getText());
				draftValue = Double.parseDouble(draft.getText());
			} catch (NumberFormatException e) {
				setPageComplete(false);
			}
			setPageComplete(true);
		} else {
			setPageComplete(false);
		}
	}

	public double getLength() {
		return lengthOfHullValue;
	}

	public double getBeam() {
		return beamValue;
	}

	public double getDraft() {
		return draftValue;
	}

	public String getConfigurationName() {
		return configurationName;
	}
	
	
}
