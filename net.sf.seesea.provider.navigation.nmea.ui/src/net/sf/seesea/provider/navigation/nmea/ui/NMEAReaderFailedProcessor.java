package net.sf.seesea.provider.navigation.nmea.ui;

import net.sf.seesea.provider.navigation.nmea.INMEAReaderFailureNotifier;
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
					Logger.getLogger(getClass()).error("Failed to set command state", e);
				}

				MessageDialog.openError(Display.getDefault().getActiveShell(), "NMEA Processing stopped",
						"The NMEA data processing stopped because an error occurred during read from provider " + streamProvider.getName());
			}
		});
		Logger.getLogger(getClass()).error("Stopped stream processing", throwable);
	}

}
