/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
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

import net.sf.seesea.osm.OpenSeaMapActivator;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class OpenStreetMapPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public OpenStreetMapPreferencePage() {
		
		IPreferenceStore store = OpenSeaMapActivator.getDefault().getPreferenceStore();
		setPreferenceStore(store);
	}
	
	private StringFieldEditor tileSource;
	private StringFieldEditor cacheDirectory;

	@Override
	public void init(IWorkbench workbench) {
		// Auto-generated method stub
	}

	@Override
	protected void createFieldEditors() {
		tileSource = new StringFieldEditor(IOSMPreferences.TILE_SOURCE, "Tile source", getFieldEditorParent());
		addField(tileSource);

		cacheDirectory = new DirectoryFieldEditor(IOSMPreferences.CACHE_DIRECTORY, "Cache directory", getFieldEditorParent());
		addField(cacheDirectory);
		
	}
	
	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return OpenSeaMapActivator.getDefault().getPreferenceStore();
	}

}
