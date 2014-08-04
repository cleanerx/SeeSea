/**
 * 
Copyright (c) 2010-2012, Jens K�bler
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

import net.sf.seesea.services.navigation.INMEAReader;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ToggleLogRawDataHandler extends AbstractHandler {

	public ToggleLogRawDataHandler() {

		BundleContext bundleContext = NMEAUIActivator.getDefault().getBundle()
				.getBundleContext();
		ServiceTracker serviceTracker = new ServiceTracker(bundleContext,
				INMEAReader.class.getName(), new ServiceTrackerCustomizer() {

					@Override
					public Object addingService(ServiceReference reference) {
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {
								ICommandService commandService = (ICommandService) PlatformUI
										.getWorkbench().getService(
												ICommandService.class);
								if(commandService != null) {
									Command command = commandService
											.getCommand("net.sf.seesea.nmea.rcp.log.toggle"); //$NON-NLS-1$
									State state = command
											.getState(RegistryToggleState.STATE_ID);
									state.setValue(true);
								}

							}
						});

						return null;
					}

					@Override
					public void modifiedService(ServiceReference reference,
							Object service) {

					}

					@Override
					public void removedService(ServiceReference reference,
							Object service) {
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {
								ICommandService commandService = (ICommandService) PlatformUI
										.getWorkbench().getService(
												ICommandService.class);
								Command command = commandService
										.getCommand("net.sf.seesea.nmea.rcp.log.toggle"); //$NON-NLS-1$
								State state = command
										.getState(RegistryToggleState.STATE_ID);
								state.setValue(false);

							}
						});

					}
				});
		serviceTracker.open();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		return HandlerUtil.toggleCommandState(event.getCommand());
		// return null;
	}

}
