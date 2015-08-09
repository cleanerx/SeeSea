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

package net.sf.seesea.nmea.rcp.handler;

import java.io.IOException;
import java.util.Collection;

import net.sf.seesea.nmea.rcp.NMEARCPActivator;
import net.sf.seesea.services.navigation.IStreamProcessor;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.State;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class ToggleLogEnablementHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IHandlerService handlerService = (IHandlerService) workbench
				.getService(IHandlerService.class);
		Command command = event.getCommand();

		State state = command.getState(RegistryToggleState.STATE_ID);
		if ((Boolean) state.getValue()) {
			try {
				Object returnedObject = handlerService.executeCommand(
						"net.sf.seesea.provider.navigation.nmea.ui.connect", //$NON-NLS-1$
						null);
				if (returnedObject.equals(Window.CANCEL)) {
					// revoke pressed state
//					HandlerUtil.toggleCommandState(command);
					return null;
				}
			} catch (NotDefinedException e) {
				state.setValue(false);
				Logger.getLogger(getClass()).error("Failed to execute command", //$NON-NLS-1$
						e);
			} catch (NotEnabledException e) {
				state.setValue(false);
				Logger.getLogger(getClass()).error("Failed to execute command", //$NON-NLS-1$
						e);
			} catch (NotHandledException e) {
				state.setValue(false);
				Logger.getLogger(getClass()).error("Failed to execute command", //$NON-NLS-1$
						e);
			}
		} else if (!(Boolean) state.getValue()) {
			BundleContext bundleContext = NMEARCPActivator.getDefault()
					.getBundle().getBundleContext();
			Collection<ServiceReference<IStreamProcessor>> serviceReferences;
			try {
				serviceReferences = bundleContext
						.getServiceReferences(IStreamProcessor.class, null);
				if(serviceReferences != null) {
					for (ServiceReference<IStreamProcessor> serviceReference2 : serviceReferences) {
						IStreamProcessor reader = bundleContext.getService(serviceReference2);
						try {
							reader.close();
						} catch (IOException e) {
							Logger.getLogger(getClass()).error(
									"Failed to close reader", e); //$NON-NLS-1$
						}
					}
					if ((Boolean) state.getValue()) {
						HandlerUtil.toggleCommandState(command);
					}
				} else {
					
					if ((Boolean) state.getValue()) {
						HandlerUtil.toggleCommandState(command);
					}
				}
			} catch (InvalidSyntaxException e1) {
				Logger.getLogger(getClass()).error("Failed filter syntax", e1); //$NON-NLS-1$
			}
		}
		// always toggle back until the service is available
		HandlerUtil.toggleCommandState(command);
		return null;
	}

}
