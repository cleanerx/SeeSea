package net.sf.seesea.navigation.ui.preferences;

import net.sf.seesea.navigation.ui.NavigationUIActivator;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class GeneralPreferencePage extends FieldEditorPreferencePage implements
IWorkbenchPreferencePage {

	private BooleanFieldEditor tidePrediction;
	private StringFieldEditor totalTripEditor;
	private StringFieldEditor tidePredictionUpdateRate;

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createFieldEditors() {
		tidePrediction = new BooleanFieldEditor(IGeneralPreferences.TIDE_PREDICTION, "Enable Tide Prediction", getFieldEditorParent()); //$NON-NLS-1$
		addField(tidePrediction);

		tidePredictionUpdateRate = new StringFieldEditor(IGeneralPreferences.TIDE_UPDATE_RATE, "Tide Prediction Update Rate (seconds)", getFieldEditorParent()); //$NON-NLS-1$
		tidePredictionUpdateRate.setEmptyStringAllowed(false);
		addField(tidePredictionUpdateRate);

		totalTripEditor = new StringFieldEditor(IGeneralPreferences.TOTAL_TRIP, "Total Trip", getFieldEditorParent()); //$NON-NLS-1$
		addField(totalTripEditor);

	}
	
	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return NavigationUIActivator.getDefault().getPreferenceStore();
	}
	

}
