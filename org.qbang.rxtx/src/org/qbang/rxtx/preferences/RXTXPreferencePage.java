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
package org.qbang.rxtx.preferences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.qbang.rxtx.DeleteLabelProvider;
import org.qbang.rxtx.RXTXActivator;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.jface.viewers.TableViewerColumn;

public class RXTXPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
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
		btnAutomaticallyScanSerial.setBounds(10, 10, 175, 16);
		btnAutomaticallyScanSerial.setText("Automatically scan serial ports");

		Boolean manualPorts = getPreferenceStore().getBoolean("manualPorts");
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

		String ports = getPreferenceStore().getString("ports");
		if (ports != null && !ports.isEmpty()) {
			String[] split = ports.split("\\|");
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
		btnNewButton.setImage(ResourceManager.getPluginImage("org.eclipse.ui",
				"/icons/full/obj16/add_obj.gif"));
		btnNewButton.setBounds(324, 185, 23, 23);
		btnNewButton.setEnabled(manualPorts);

		Label lblManuallyConfiguredPorts = new Label(composite, SWT.NONE);
		lblManuallyConfiguredPorts.setBounds(10, 38, 213, 13);
		lblManuallyConfiguredPorts.setText("Manually Configured Ports");

		Label lblEnterYourPort = new Label(composite, SWT.WRAP);
		lblEnterYourPort.setBounds(10, 214, 294, 34);
		lblEnterYourPort
				.setText("Enter your Port i.e. COM1 on Windows or /dev/ttyS0 on Linux");
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
		String ports = "";
		String systemPorts = "";
		// Platform.getPreferencesService().setString("org.qbang.rxtx.preferences",
		// "ports", "", null);
		if (comPorts != null) {
			for (Iterator<String> iterator = comPorts.iterator(); iterator
					.hasNext();) {
				String next = iterator.next();
				ports += next.trim();
				systemPorts += next.trim();
				if (iterator.hasNext()) {
					ports += "|";
					systemPorts += ",";
				}
			}
			getPreferenceStore().setValue("ports", ports);
		}

		if (btnAutomaticallyScanSerial.getSelection() || systemPorts.isEmpty()) {
			System.clearProperty("gnu.io.rxtx.SerialPorts");
		} else {
			System.setProperty("gnu.io.rxtx.SerialPorts", systemPorts);
		}
		getPreferenceStore().setValue("manualPorts",
				!btnAutomaticallyScanSerial.getSelection());

		return super.performOk();
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(RXTXActivator.getDefault().getPreferenceStore());

	}
}
