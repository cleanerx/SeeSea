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
package org.qbang.rxtx.wizard;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

/**
 * Additional setups for a COM port 
 */
public class ComPortSetupPage extends WizardPage {

	private ComboViewer bitCombo;
	private Integer _baudRate;
	private Spinner spinner;
	private int _timeout;

	/**
	 * @param serialNMEAProvider 
	 * @param pageName
	 */
	protected ComPortSetupPage() {
		super("ComPortSetupPage", Messages.getString("ComPortSetupPage.config"), null); //$NON-NLS-1$ //$NON-NLS-2$
		setMessage(Messages.getString("ComPortSetupPage.configMessage")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout); 
		Label baud = new Label(composite, SWT.NONE);
		baud.setText(Messages.getString("ComPortSetupPage.bitrate")); //$NON-NLS-1$
		baud.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 1, 1));

		
		bitCombo = new ComboViewer(composite);
		List<Integer> bitrates = new ArrayList<Integer>();
//		bitrates.add(1200);
//		bitrates.add(2400);
		bitrates.add(4800);
//		bitrates.add(9600);
//		bitrates.add(19200);
		bitrates.add(38400);
//		bitrates.add(57600);
		bitCombo.setContentProvider(new ArrayContentProvider());
		bitCombo.setInput(bitrates);
		// FIXME: from preferences
		bitCombo.setSelection(new StructuredSelection(4800));
		bitCombo.getCombo().setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, true, 1, 1));
		composite.pack();
		
		setControl(composite);
		
		Label lblTimeout = new Label(composite, SWT.NONE);
		lblTimeout.setToolTipText(Messages.getString("ComPortSetupPage.lblTimeout.toolTipText")); //$NON-NLS-1$
		lblTimeout.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTimeout.setAlignment(SWT.RIGHT);
		lblTimeout.setText(Messages.getString("ComPortSetupPage.lblTimeout.text")); //$NON-NLS-1$
		
		spinner = new Spinner(composite, SWT.BORDER);
		spinner.setMaximum(Integer.MAX_VALUE);
		spinner.setSelection(3600);
		GridData gd_spinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_spinner.widthHint = 100;
		spinner.setLayoutData(gd_spinner);
	}

	@Override
	public boolean isPageComplete() {
		CommPortIdentifier commPort = ((SelectComPortPage) getPreviousPage()).getCommPort();
		if(commPort == null) {
			return false;
		}
		_baudRate =  (Integer) ((IStructuredSelection) bitCombo.getSelection()).getFirstElement();
		_timeout = spinner.getSelection();
		
		
		return super.isPageComplete();
	}

	/**
	 * 
	 */
	public int getBaudRate() {
		return _baudRate;
	}

	public int getTimeout() {
		return _timeout;
	}
	
	

}
