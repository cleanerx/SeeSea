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
package net.sf.seesea.nmea.rcp.actions;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;

/**
 * 
 */
public class UploadDataActionDelegate extends ActionDelegate implements IWorkbenchWindowActionDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		// nothing to do
	}
	
	@Override
	public void run(IAction action) {
		ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
		try {
			progressMonitorDialog.run(false, true, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.getString("UploadDataActionDelegate.subtaks"), 100); //$NON-NLS-1$
					monitor.subTask(""); //$NON-NLS-1$
					for (int i = 0; i < 10; i++) {
						if(monitor.isCanceled()) {
							return;
						}
						Thread.sleep(100);
						monitor.worked(1);
					}
					monitor.subTask(Messages.getString("UploadDataActionDelegate.fileUpload")); //$NON-NLS-1$
					for (int i = 0; i < 90; i++) {
						if(monitor.isCanceled()) {
							return;
						}
						Thread.sleep(100);
						monitor.worked(1);
					}
				}
			});
		} catch (InvocationTargetException e) {
			Logger.getRootLogger().error("Failed to execute command", e); //$NON-NLS-1$
		} catch (InterruptedException e) {
			Logger.getRootLogger().error("Failed to execute command", e); //$NON-NLS-1$
		}
		
		MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.getString("UploadDataActionDelegate.complete"), Messages.getString("UploadDataActionDelegate.success")); //$NON-NLS-1$ //$NON-NLS-2$
	}


}
