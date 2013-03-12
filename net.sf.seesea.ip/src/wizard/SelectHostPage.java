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
package wizard;

import net.sf.seesea.lib.IValidatingPage;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class SelectHostPage extends WizardPage implements IValidatingPage {

	private String host;
	private int port;
	private Text text;
	private Text text_1;
	private Text text_2;
	private int timeout;

	/**
	 * @param serialNMEAProvider 
	 * @param pageName
	 */
	public SelectHostPage() {
		super("HostSetupPage", Messages.getString("SelectHostPage.IPConnection"), null); //$NON-NLS-1$ //$NON-NLS-2$
		setMessage(Messages.getString("SelectHostPage.message")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new FormLayout());
		
		final IDialogSettings settings = getWizard().getDialogSettings();
		
		Label lblHost = new Label(container, SWT.NONE);
		FormData fd_lblHost = new FormData();
		fd_lblHost.left = new FormAttachment(0, 10);
		lblHost.setLayoutData(fd_lblHost);
		lblHost.setText(Messages.getString("SelectHostPage.host")); //$NON-NLS-1$
		
		text = new Text(container, SWT.BORDER);
		fd_lblHost.top = new FormAttachment(text, 3, SWT.TOP);
		FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(100, -476);
		fd_text.top = new FormAttachment(0, 10);
		fd_text.right = new FormAttachment(100, -10);
		text.setLayoutData(fd_text);
		String lastHost = settings.get("lastHost"); //$NON-NLS-1$
		if(lastHost != null) {
			text.setText(lastHost);
			host = lastHost;
		}

		text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				host = ((Text)e.widget).getText();
				settings.put("lastHost", host); //$NON-NLS-1$
			}
		});
		
		Label lblPort = new Label(container, SWT.NONE);
		FormData fd_lblPort = new FormData();
		fd_lblPort.left = new FormAttachment(lblHost, 0, SWT.LEFT);
		lblPort.setLayoutData(fd_lblPort);
		lblPort.setText(Messages.getString("SelectHostPage.port")); //$NON-NLS-1$
		
		text_1 = new Text(container, SWT.BORDER);
		fd_lblPort.top = new FormAttachment(text_1, 3, SWT.TOP);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(text, 6);
		fd_text_1.left = new FormAttachment(text, 0, SWT.LEFT);
		text_1.setLayoutData(fd_text_1);
		
		Label lblTimeout = new Label(container, SWT.NONE);
		FormData fd_lblTimeout = new FormData();
		fd_lblTimeout.top = new FormAttachment(lblPort, 15);
		fd_lblTimeout.left = new FormAttachment(0, 10);
		lblTimeout.setLayoutData(fd_lblTimeout);
		lblTimeout.setText(Messages.getString("SelectHostPage.lblTimeout.text")); //$NON-NLS-1$
		
		text_2 = new Text(container, SWT.BORDER);
		try {
			int lasttimeout = settings.getInt("timeout"); //$NON-NLS-1$
			if(timeout != 0) {
				text_2.setText(new Integer(lasttimeout).toString());
				timeout = lasttimeout;
			} else {
				text_2.setText("10000"); //$NON-NLS-1$
			}
		} catch (NumberFormatException e) {
			text_2.setText("10000"); //$NON-NLS-1$
		}
		text_2.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				timeout = Integer.parseInt(((Text)e.widget).getText());
				settings.put("timeout", timeout); //$NON-NLS-1$
			}
		});
		
		FormData fd_text_2 = new FormData();
		fd_text_2.top = new FormAttachment(text_1, 6);
		fd_text_2.left = new FormAttachment(text, 0, SWT.LEFT);
		text_2.setLayoutData(fd_text_2);
		try {
			int lastPort = settings.getInt("lastPort"); //$NON-NLS-1$
			if(lastPort != 0) {
				text_1.setText(new Integer(lastPort).toString());
				port = lastPort;
			}
		} catch (NumberFormatException e) {
			// nothing to do
		}
		text_1.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					port = Integer.parseInt(((Text)e.widget).getText());
					settings.put("lastPort", port); //$NON-NLS-1$
				} catch (NumberFormatException e2) {
					// nothing to do;
				}
			}
		});

		
	}
	
	/* (non-Javadoc)
	 * @see net.sf.seesea.provider.navigation.nmea.ui.IValidatingPage#getStatus()
	 */
	@Override
	public IStatus validatePage() {
		if(host == null || host.isEmpty()) {
			setErrorMessage(Messages.getString("SelectHostPage.invalidHost")); //$NON-NLS-1$
			return new Status(IStatus.ERROR, "net.sf.seesea.ip", Messages.getString("SelectHostPage.invalidHost1")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if(port < 1 || port > 65535) {
			setErrorMessage(Messages.getString("SelectHostPage.invalidPort")); //$NON-NLS-1$
			return new Status(IStatus.ERROR, "net.sf.seesea.ip", Messages.getString("SelectHostPage.invalidPort1")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return new Status(IStatus.OK, "net.sf.seesea.ip", "FIXME"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public int getTimeout() {
		return timeout;
	}
	
	
}
