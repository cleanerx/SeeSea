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

import java.io.File;
import java.io.IOException;

import net.sf.seesea.osm.OpenSeaMapActivator;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
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
		tileSource = new StringFieldEditor(IOSMPreferences.TILE_SOURCE, Messages.getString("OpenStreetMapPreferencePage.0"), getFieldEditorParent()); //$NON-NLS-1$
		addField(tileSource);

		cacheDirectory = new DirectoryFieldEditor(IOSMPreferences.CACHE_DIRECTORY, Messages.getString("OpenStreetMapPreferencePage.1"), getFieldEditorParent()); //$NON-NLS-1$
		addField(cacheDirectory);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		Composite contents = (Composite) super.createContents(parent);
		Button button = new Button(contents, SWT.PUSH);
		button.setText(Messages.getString("OpenStreetMapPreferencePage.2")); //$NON-NLS-1$
		button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String directory = cacheDirectory.getStringValue();
				if(!directory.isEmpty()) {
					File file = new File(directory);
					if(file.exists()) {
						try {
							delete(file);
						} catch (IOException e1) {
							MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.getString("OpenStreetMapPreferencePage.3"), e1.getLocalizedMessage()); //$NON-NLS-1$
							Logger.getLogger(getClass()).error("Failed to delete dir", e1); //$NON-NLS-1$
						}
					}
					file.mkdirs();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		return contents;
	}

	private void delete(File f) throws IOException {
		  if (f.isDirectory()) {
		    for (File c : f.listFiles())
		      delete(c);
		  }
		  if (!f.delete()) {
			  Logger.getLogger(getClass()).error("Failed to delete file " +f); //$NON-NLS-1$
		  }
		}
	
	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return OpenSeaMapActivator.getDefault().getPreferenceStore();
	}

}
