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
package net.sf.seesea.osm.preferences;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import net.sf.seesea.osm.OpenSeaMapActivator;

import org.apache.log4j.Logger;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * this is a bridge between eclipse configuration and config admin service 
 * @author jens
 *
 */
public class OSMConfiguration {

	private final class PreferenceChangeListener implements
			IPropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			String tileSource = OpenSeaMapActivator.getDefault().getPreferenceStore().getString(IOSMPreferences.TILE_SOURCE);
			String cacheDir = OpenSeaMapActivator.getDefault().getPreferenceStore().getString(IOSMPreferences.CACHE_DIRECTORY);
			
			try {
				Configuration configuration = _configAdmin.getConfiguration("net.sf.seesea.osm.tileprovider"); //$NON-NLS-1$
				Dictionary<String, Object> properties = new Hashtable<String, Object>();
				properties.put(IOSMPreferences.CACHE_DIRECTORY, cacheDir);
				properties.put(IOSMPreferences.TILE_SOURCE, tileSource);
				properties.put(IOSMPreferences.OVERLAY, false);
				configuration.update(properties);
			} catch (IOException e) {
				Logger.getLogger(getClass()).error("Failed to update configuration", e); //$NON-NLS-1$
			}
		}
	}

	private ConfigurationAdmin _configAdmin;
	private PreferenceChangeListener preferenceChangeListener;
	
	/**
	 * 
	 * @param bundleContext
	 */
	public void activate(BundleContext bundleContext) {
		String tileSource = OpenSeaMapActivator.getDefault().getPreferenceStore().getString(IOSMPreferences.TILE_SOURCE);
		String cacheDir = OpenSeaMapActivator.getDefault().getPreferenceStore().getString(IOSMPreferences.CACHE_DIRECTORY);
		
		try {
			Configuration configuration = _configAdmin.createFactoryConfiguration("net.sf.seesea.osm.tileprovider"); //$NON-NLS-1$
			Dictionary<String, Object> properties = new Hashtable<String, Object>();
			properties.put(IOSMPreferences.CACHE_DIRECTORY, cacheDir);
			properties.put(IOSMPreferences.TILE_SOURCE, tileSource);
			properties.put(IOSMPreferences.OVERLAY, false);
			configuration.update(properties);
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("Failed to update configuration", e); //$NON-NLS-1$
		}
		
		preferenceChangeListener = new PreferenceChangeListener();
		OpenSeaMapActivator.getDefault().getPreferenceStore().addPropertyChangeListener(preferenceChangeListener);
	}
	
	/**
	 * 
	 * @param bundleContext
	 */
	public void deactivate(BundleContext bundleContext) {
		OpenSeaMapActivator.getDefault().getPreferenceStore().removePropertyChangeListener(preferenceChangeListener);
	}

	
	public synchronized void bindConfigAdmin(ConfigurationAdmin configAdmin) {
		this._configAdmin = configAdmin;
	}

	/**
	 * 
	 * @param configAdmin
	 */
	public synchronized void unbindConfigAdmin(ConfigurationAdmin configAdmin) {
		this._configAdmin = null;
	}

}
