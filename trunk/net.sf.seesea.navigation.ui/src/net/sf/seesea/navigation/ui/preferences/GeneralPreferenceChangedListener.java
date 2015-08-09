package net.sf.seesea.navigation.ui.preferences;

import java.io.IOException;
import java.util.Hashtable;

import javax.swing.text.StyledEditorKit.BoldAction;

import net.sf.seesea.model.util.IModel;
import net.sf.seesea.navigation.ui.NavigationUIActivator;
import net.sf.seesea.waterlevel.ocean.IOceanTideProvider;

import org.apache.log4j.Logger;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class GeneralPreferenceChangedListener {

	private PreferenceChangeListener preferenceChangeListener;

	private final class PreferenceChangeListener implements
	IPropertyChangeListener {

@Override
public void propertyChange(PropertyChangeEvent event) {
	if(event.getProperty().equals(IGeneralPreferences.TIDE_PREDICTION)) {
		try {
			Configuration configuration = configAdmin.getConfiguration("net.sf.seesea.tidemodel.dtu10.oceantide.java");
			if(event.getNewValue().equals(Boolean.FALSE)) {
				configuration.delete();
			} else {
				Hashtable<String, Object> properties = new Hashtable<>();
				IPreferenceStore preferenceStore = NavigationUIActivator.getDefault().getPreferenceStore();
				long updateRate = Long.parseLong(preferenceStore.getString(IGeneralPreferences.TIDE_UPDATE_RATE)) * 1000;
				properties.put(IOceanTideProvider.TIMEDISTANCECACHED, updateRate);
				configuration.update(properties);
			}
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("Failed to read configuration",e);
		}
	} else if(event.getProperty().equals(IGeneralPreferences.TOTAL_TRIP)) {
		model.loadModel().setTotalTrip(Double.parseDouble((String) event.getNewValue()));
	}
}
	}

	public void activate() {
		preferenceChangeListener = new PreferenceChangeListener();
		NavigationUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(preferenceChangeListener);
	}
	
	public void deactivate() {
		NavigationUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(preferenceChangeListener);
	}
	
	private IModel model;
	private ConfigurationAdmin configAdmin;

	public void bindModel(IModel model) {
		this.model = model;
		
	}

	public void unbindModel(IModel model) {
		this.model = null;
	}

	
	public void bindAdmin(ConfigurationAdmin configAdmin) {
		this.configAdmin = configAdmin;
		
	}

	public void unbindAdmin(ConfigurationAdmin configAdmin) {
		this.configAdmin = null;
	}

}
