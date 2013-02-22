package org.qbang.rxtx.wizard;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.qbang.rxtx.RXTXActivator;

public class SerialPageChangeListener implements IPageChangingListener {

	@Override
	public void handlePageChanging(PageChangingEvent event) {

		if(event.getTargetPage() instanceof SelectComPortPage) {
			SelectComPortPage page = (SelectComPortPage) event.getTargetPage();
			IDialogSettings settings = page.getWizard().getDialogSettings();
			List<CommPortIdentifier> ports = getPorts();
			page.getTableViewer().setInput(ports);
			if(getPorts().isEmpty()) {
				page.setErrorMessage(Messages.getString("SelectComPortPage.noComPorts")); //$NON-NLS-1$
			} else {
				page.setErrorMessage(null);
				if(settings.get("lastComPort") != null) { //$NON-NLS-1$
					for (CommPortIdentifier comm : ports) {
						if(comm.getName().equals(settings.get("lastComPort"))) { //$NON-NLS-1$
							page.getTableViewer().setSelection(new StructuredSelection(comm));
						}
					}
				} else {
					page.getTableViewer().setSelection(new StructuredSelection(getPorts().get(0)));
				}
			}

		}
	}
	
	
	private List<CommPortIdentifier> getPorts() {
		boolean manualPorts = RXTXActivator.getDefault().getPreferenceStore().getBoolean("manualPorts"); //$NON-NLS-1$
		List<CommPortIdentifier> cports = new ArrayList<CommPortIdentifier>();
		
		Enumeration<?> ports = CommPortIdentifier.getPortIdentifiers();
		while (ports.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier)ports.nextElement();
			if(!portId.isCurrentlyOwned()) {
				cports.add(portId);
			}
		}
		return cports;
	}

}
