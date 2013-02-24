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
package net.sf.seesea.provider.navigation.nmea.ui.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.seesea.provider.navigation.nmea.ui.NMEAUIActivator;
import net.sf.seesea.provider.navigation.nmea.ui.providers.NMEADataContentProvider;
import net.sf.seesea.services.navigation.INMEAReader;
import net.sf.seesea.services.navigation.RawDataEventListener;
import net.sf.seesea.services.navigation.NMEAProcessingException;
import net.sf.seesea.services.navigation.RawDataEvent;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * 
 */
public class NMEARawDataLoggerView extends ViewPart implements RawDataEventListener {

	private ServiceTracker serviceTracker;
	private final List<String> messages;
	private TableViewer listViewer;

	private long lastUpdate = System.currentTimeMillis();
	
	/**
	 * 
	 */
	public NMEARawDataLoggerView() {
		messages = Collections.synchronizedList(new ArrayList<String>(10000));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite arg0) {
		listViewer = new TableViewer(arg0, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		listViewer.setContentProvider(new NMEADataContentProvider());
		TableViewerColumn column = new TableViewerColumn(listViewer, SWT.NONE);
		column.getColumn().setWidth(400);
		column.setLabelProvider(new NMEAColumnLabelProvider());

		// hmm service listener
		final BundleContext bundleContext = NMEAUIActivator.getDefault().getBundle().getBundleContext();
		
		serviceTracker = new ServiceTracker(bundleContext, INMEAReader.class.getName(), new ServiceTrackerCustomizer() {
			
			@Override
			public void removedService(ServiceReference serviceReference, Object arg1) {
				if(arg1 instanceof INMEAReader) {
					INMEAReader nmeaReader = (INMEAReader) arg1;
					nmeaReader.removeNMEAEventListener(NMEARawDataLoggerView.this);
				}
			}
			
			@Override
			public void modifiedService(ServiceReference arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object addingService(ServiceReference serviceReference) {
				INMEAReader nmeaReader = (INMEAReader) bundleContext.getService(serviceReference);
				nmeaReader.addNMEAEventListener(NMEARawDataLoggerView.this);
				return nmeaReader;
			}
		});
		serviceTracker.open();
		listViewer.setInput(messages);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		serviceTracker.close();
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.provider.navigation.nmea.NMEAEventListener#receiveNMEAEvent(net.sf.seesea.provider.navigation.nmea.RawDataEvent)
	 */
	@Override
	public void receiveRawDataEvent(RawDataEvent e) throws NMEAProcessingException {
		if(messages.size() > 10000) {
			while (messages.size() > 9999) {
				messages.remove(9999);
			}
		}
		// log or don't log ! state of the command
		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command = service.getCommand("net.sf.seesea.provider.navigation.nmea.ui.toggleRawLog"); //$NON-NLS-1$
		State state = command.getState(RegistryToggleState.STATE_ID);
		if(state.getValue().equals(true)) {
			String nmeaMessageContent = e.getNmeaMessageContent();
			if(nmeaMessageContent != null && !nmeaMessageContent.isEmpty()) {
				nmeaMessageContent = nmeaMessageContent.replaceAll("\\r\\n", ""); //$NON-NLS-1$ //$NON-NLS-2$
				messages.add(0, nmeaMessageContent);
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						// limit update rate in case of many events
						if(System.currentTimeMillis() - lastUpdate > 1000) {
							listViewer.refresh(true);
							lastUpdate = System.currentTimeMillis();
						}
					}
				});
			}
		}
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

}
