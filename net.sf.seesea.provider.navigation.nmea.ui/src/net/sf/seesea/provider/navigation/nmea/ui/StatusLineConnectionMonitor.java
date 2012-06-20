package net.sf.seesea.provider.navigation.nmea.ui;

import net.sf.seesea.provider.navigation.nmea.NMEA0183Reader;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class StatusLineConnectionMonitor {

	public StatusLineConnectionMonitor() {
//		BundleContext bundleContext = NMEAUIActivator.getDefault().getBundle().getBundleContext();
//		ServiceTracker serviceTracker = new ServiceTracker(bundleContext, NMEAReader.class.getName(), new ServiceTrackerCustomizer() {
//
//			@Override
//			public Object addingService(ServiceReference reference) {
//
//				ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
//				Command command = commandService.getCommand("net.sf.seesea.nmea.rcp.log.toggle"); //$NON-NLS-1$
//				State state = command.getState(RegistryToggleState.STATE_ID);
//				state.setValue(true);
//				
//				return null;
//			}
//
//			@Override
//			public void modifiedService(ServiceReference reference,
//					Object service) {
//				
//			}
//
//			@Override
//			public void removedService(ServiceReference reference,
//					Object service) {
//				ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
//				Command command = commandService.getCommand("net.sf.seesea.nmea.rcp.log.toggle"); //$NON-NLS-1$
//				State state = command.getState(RegistryToggleState.STATE_ID);
//				state.setValue(false);
//				
//				
//			}
//		});
//		serviceTracker.open();
	}
	
	/**
	 * 
	 * @param reader
	 */
	public synchronized void activate() {
		Display.getDefault().asyncExec(new Runnable() {
			
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
			Command command = commandService.getCommand("net.sf.seesea.nmea.rcp.log.toggle"); //$NON-NLS-1$
			State state = command.getState(RegistryToggleState.STATE_ID);
			state.setValue(true);
		}
		});

//		setMessage("Device Connected");
	}
	
	public synchronized void deactivate() {
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
				Command command = commandService.getCommand("net.sf.seesea.nmea.rcp.log.toggle"); //$NON-NLS-1$
				State state = command.getState(RegistryToggleState.STATE_ID);
				state.setValue(false);
			}
		});
//		setMessage("Device Disconnected");
	}
	
	
	public void setMessage(String text) {
		
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();

		IWorkbenchPage page = win.getActivePage();

		IWorkbenchPart part = page.getActivePart();
		IWorkbenchPartSite site = part.getSite();

//		IEditorSite vSite = ( IEditorSite ) site;
//		IActionBars actionBars =  vSite.getActionBars();
//
//		if( actionBars == null )
//		return ;
//
//		IStatusLineManager statusLineManager =
//		 actionBars.getStatusLineManager();
//
//		if( statusLineManager == null )
//		return ;
//
//		statusLineManager.setMessage(text);
		}	
}
