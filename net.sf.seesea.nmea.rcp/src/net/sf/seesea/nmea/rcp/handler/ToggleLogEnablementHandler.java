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

package net.sf.seesea.nmea.rcp.handler;
import net.sf.seesea.provider.navigation.nmea.ui.INMEAConnector;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.State;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;


public class ToggleLogEnablementHandler extends AbstractHandler {

	private final class ActionEnablementListener implements
			ServiceListener {
		@Override
		public void serviceChanged(ServiceEvent event) {
			ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
			Command command = service.getCommand("net.sf.seesea.nmea.rcp.log.toggle");
			State state = command.getState(RegistryToggleState.STATE_ID);
//			try {
//				HandlerUtil.toggleCommandState(command);
//			} catch (ExecutionException e) {
//				Logger.getLogger(getClass()).error("Failed to toggle state", e);
//			}
			if(ServiceEvent.REGISTERED == event.getType()) {
				state.setValue(true);
//				enable = true;
			} else if(ServiceEvent.UNREGISTERING == event.getType()) {
				state.setValue(false);
//				enable = false;
			}
			
		}
	}

	private INMEAConnector connector;
	
	private boolean enable;

	private ActionEnablementListener actionEnablementListener;
	
	public ToggleLogEnablementHandler() {
//		enable = false;
//		BundleContext bundleContext = NMEARCPActivator.getDefault().getBundle().getBundleContext();
//		String filter = "(objectClass=" + NMEAReader.class.getName() + ")" ; //$NON-NLS-1$ //$NON-NLS-2$
//		try {
//			actionEnablementListener = new ActionEnablementListener();
//			bundleContext.addServiceListener(actionEnablementListener, filter);
//		} catch (InvalidSyntaxException e) {
////			Logger.get
//			e.printStackTrace();
//		}
	}

//	@Override
	public void init(IWorkbenchWindow window) {
		// nothing to do
	}
	
//	@Override
//	public void run(IAction action) {
//		IWorkbench workbench = PlatformUI.getWorkbench();
//		IHandlerService handlerService = (IHandlerService) workbench.getService(IHandlerService.class);
//		
//		if(!enable) {
//			try {
//				Object returnedObject = handlerService.executeCommand("net.sf.seesea.provider.navigation.nmea.ui.connect", null); //$NON-NLS-1$i
//				if(returnedObject instanceof INMEAConnector) {
//					connector = (INMEAConnector) returnedObject;
////					enable = true;
//				} else {
////					enable = false;
//				}
//			} catch (ExecutionException e) {
////				enable = false;
//				Logger.getRootLogger().error("Failed to execute command", e); //$NON-NLS-1$
//			} catch (NotDefinedException e) {
////				enable = false;
//				Logger.getRootLogger().error("Failed to execute command", e); //$NON-NLS-1$
//			} catch (NotEnabledException e) {
////				enable = false;
//				Logger.getRootLogger().error("Failed to execute command", e); //$NON-NLS-1$
//			} catch (NotHandledException e) {
////				enable = false;
//				Logger.getRootLogger().error("Failed to execute command", e); //$NON-NLS-1$
//			}
//		} else {
//			connector.disconnect(null);
////			enable = false;
//		}
//		action.setChecked(enable);
//		if(enable) {
//			IWorkspace workspace = ResourcesPlugin.getWorkspace();
//			IWorkspaceRoot root = workspace.getRoot();
//			IProject project = root.getProject("NMEALogging"); //$NON-NLS-1$
//			IFolder folder = project.getFolder("logs"); //$NON-NLS-1$
////			MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.getString("ToggleLogEnablementActionDelegate.logDirectory"), Messages.getString("ToggleLogEnablementActionDelegate.logMessage") + folder.getLocationURI().toString()); //$NON-NLS-1$ //$NON-NLS-2$
//		}
//	}

	@Override
	public void dispose() {
//		BundleContext bundleContext = NMEARCPActivator.getDefault().getBundle().getBundleContext();
//		bundleContext.removeServiceListener(actionEnablementListener);
		super.dispose();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IHandlerService handlerService = (IHandlerService) workbench.getService(IHandlerService.class);
		Command command = event.getCommand();
//		ICommandService commandService = (ICommandService) workbench.getService(ICommandService.class);
//		Command command = commandService.getCommand("net.sf.seesea.provider.navigation.nmea.ui.connect");
		State state = command.getState(RegistryToggleState.STATE_ID);
		if(connector == null) {
			try {
//				HandlerUtil.toggleCommandState(command);
//				state.setValue(true);
//				state.setValue(false);
				Object returnedObject = handlerService.executeCommand("net.sf.seesea.provider.navigation.nmea.ui.connect", null);
//				state.setValue(false);
				if(returnedObject instanceof INMEAConnector) {
					connector = (INMEAConnector) returnedObject;
//					state.setValue(true);
				} else {
					state.setValue(true);
//					Logger.getLogger(getClass()).error("Command did not return a connector");
				}
			} catch (NotDefinedException e) {
				state.setValue(false);
				Logger.getLogger(getClass()).error("Failed to execute command", e);
			} catch (NotEnabledException e) {
				state.setValue(false);
				Logger.getLogger(getClass()).error("Failed to execute command", e);
			} catch (NotHandledException e) {
				state.setValue(false);
				Logger.getLogger(getClass()).error("Failed to execute command", e);
			} 
		} else {
//			state.setValue(false);
			connector.disconnect(null);
			connector = null;
		}
		return null;
	}

}
