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
package org.qbang.rxtx.preferences;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.wb.swt.ResourceManager;
import org.qbang.rxtx.DeleteLabelProvider;
import org.qbang.rxtx.RXTXActivator;

public class RXTXPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
	public static final String MANUAL_PORTS = "manualPorts"; //$NON-NLS-1$
	private Table table;
	private Text addPort;
	private Set<String> comPorts;
	private Button btnAutomaticallyScanSerial;

	public RXTXPreferencePage() {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		btnAutomaticallyScanSerial = new Button(composite, SWT.CHECK);
		btnAutomaticallyScanSerial.setBounds(10, 10, 337, 16);
		btnAutomaticallyScanSerial.setText(Messages.getString("RXTXPreferencePage.scan")); //$NON-NLS-1$

		Boolean manualPorts = getPreferenceStore().getBoolean(MANUAL_PORTS);
		btnAutomaticallyScanSerial.setSelection(!manualPorts);

		final TableViewer tableViewer = new TableViewer(composite, SWT.BORDER);
		table = tableViewer.getTable();
		table.setBounds(10, 57, 337, 117);
		table.setEnabled(manualPorts);

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				comPorts.remove(e.item.getData());
				tableViewer.refresh(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// tableViewerColumn.setLabelProvider(new DeleteLabelProvider());
		comPorts = new TreeSet<String>();

		String ports = getPreferenceStore().getString("ports"); //$NON-NLS-1$
		if (ports != null && !ports.isEmpty()) {
			String[] split = ports.split("\\|"); //$NON-NLS-1$
			for (String port : split) {
				if (!port.isEmpty()) {
					comPorts.add(port);
				}
			}
		}
		tableViewer.setContentProvider(new IStructuredContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub

			}

			@Override
			public Object[] getElements(Object inputElement) {
				return ((Collection<?>) inputElement).toArray();
			}
		});
		tableViewer.setLabelProvider(new DeleteLabelProvider());
		tableViewer.setInput(comPorts);

		addPort = new Text(composite, SWT.BORDER);
		addPort.setBounds(10, 187, 308, 16);
		addPort.setEnabled(manualPorts);

		final Button btnNewButton = new Button(composite, SWT.CENTER);
		btnNewButton.setImage(ResourceManager.getPluginImage("org.eclipse.ui", //$NON-NLS-1$
				"/icons/full/obj16/add_obj.gif")); //$NON-NLS-1$
		btnNewButton.setBounds(324, 185, 23, 23);
		btnNewButton.setEnabled(manualPorts);

		Label lblManuallyConfiguredPorts = new Label(composite, SWT.NONE);
		lblManuallyConfiguredPorts.setBounds(10, 38, 213, 13);
		lblManuallyConfiguredPorts.setText(Messages.getString("RXTXPreferencePage.manualports")); //$NON-NLS-1$

		Label lblEnterYourPort = new Label(composite, SWT.WRAP);
		lblEnterYourPort.setBounds(10, 214, 294, 34);
		lblEnterYourPort
				.setText(Messages.getString("RXTXPreferencePage.enterPort")); //$NON-NLS-1$
		btnNewButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (addPort.getText() != null && !addPort.getText().isEmpty()) {
					comPorts.add(addPort.getText());
				}
				tableViewer.refresh(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});

		btnAutomaticallyScanSerial
				.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						boolean enabled = !btnAutomaticallyScanSerial
								.getSelection();
						tableViewer.getControl().setEnabled(enabled);
						addPort.setEnabled(enabled);
						btnNewButton.setEnabled(enabled);
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub

					}
				});

		return composite;
	}

	@Override
	public boolean performOk() {
		String ports = ""; //$NON-NLS-1$
		String systemPorts = ""; //$NON-NLS-1$
		if (comPorts != null) {
			for (Iterator<String> iterator = comPorts.iterator(); iterator
					.hasNext();) {
				String next = iterator.next();
				ports += next.trim();
				systemPorts += next.trim();
				if (iterator.hasNext()) {
					ports += "|"; //$NON-NLS-1$
					systemPorts += ","; //$NON-NLS-1$
				}
			}
			getPreferenceStore().setValue("ports", ports); //$NON-NLS-1$
		}

		if (btnAutomaticallyScanSerial.getSelection() || systemPorts.isEmpty()) {
			System.clearProperty("gnu.io.rxtx.SerialPorts"); //$NON-NLS-1$
		} else {
			System.setProperty("gnu.io.rxtx.SerialPorts", systemPorts); //$NON-NLS-1$
		}
		getPreferenceStore().setValue(MANUAL_PORTS,
				!btnAutomaticallyScanSerial.getSelection());

		return super.performOk();
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(RXTXActivator.getDefault().getPreferenceStore());

	}
}
