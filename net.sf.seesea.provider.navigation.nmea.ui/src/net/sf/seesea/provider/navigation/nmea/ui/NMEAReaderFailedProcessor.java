/**
 * 
Copyright (c) 2010,2013 Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.provider.navigation.nmea.ui;

import net.sf.seesea.services.navigation.INMEAReaderFailureNotifier;
import net.sf.seesea.services.navigation.provider.INMEAStreamProvider;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;

public class NMEAReaderFailedProcessor implements INMEAReaderFailureNotifier {

	@Override
	public void notify(final INMEAStreamProvider streamProvider, final Throwable throwable) {
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				IWorkbench workbench = PlatformUI.getWorkbench();
				ICommandService commandService = (ICommandService) workbench.getService(ICommandService.class);
				Command command = commandService.getCommand("net.sf.seesea.nmea.rcp.log.toggle"); //$NON-NLS-1$
				// always toggle back until the service is available
				try {
					HandlerUtil.toggleCommandState(command);
				} catch (ExecutionException e) {
					Logger.getLogger(getClass()).error("Failed to set command state", e); //$NON-NLS-1$
				}

				MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.getString("NMEAReaderFailedProcessor.stopedHeader"), //$NON-NLS-1$
						Messages.getString("NMEAReaderFailedProcessor.toppedMessage") + streamProvider.getName()); //$NON-NLS-1$
			}
		});
		Logger.getLogger(getClass()).error("Stopped stream processing", throwable); //$NON-NLS-1$
	}

}
