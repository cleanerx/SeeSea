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
package net.sf.seesea.nmea.rcp.application;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.util.IModel;
import net.sf.seesea.nmea.rcp.Messages;
import net.sf.seesea.nmea.rcp.NMEARCPActivator;
import net.sf.seesea.rendering.chart.editor.MapEditorInput;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private static final String VERSION = "1.0.0 Alpha 7"; //$NON-NLS-1$

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    @Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    @Override
	public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
//        configurer.setInitialSize(new Point(2000, 2000));
        PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, IWorkbenchPreferenceConstants.TOP_RIGHT);
        
        configurer.setTitle(MessageFormat.format(Messages.getString("ApplicationWorkbenchWindowAdvisor.title"), VERSION) ); //$NON-NLS-1$
        configurer.setShowMenuBar(false);
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setShowPerspectiveBar(true);
        configurer.setShowProgressIndicator(true);
        configurer.setShellStyle(SWT.SHELL_TRIM);
    }
    
    @Override
    public void postWindowOpen() {
    	final BundleContext bundleContext = NMEARCPActivator.getDefault().getBundle().getBundleContext();
    	ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(getWindowConfigurer().getWindow().getShell());
    	final AtomicReference<World> atomicReference = new AtomicReference<World>();
    	try {
			progressMonitorDialog.run(true, false, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Loading recorded tracks", IProgressMonitor.UNKNOWN);
				    ServiceReference<IModel> serviceReference = bundleContext.getServiceReference(IModel.class);
				    IModel model = bundleContext.getService(serviceReference);
				    World world = model.loadModel();
				    atomicReference.set(world);
				    bundleContext.ungetService(serviceReference);
				    Display.getDefault().asyncExec(new Runnable() {
						
						@Override
						public void run() {
					    	WorkbenchWindow workbenchWin = (WorkbenchWindow)PlatformUI.getWorkbench().getActiveWorkbenchWindow();
					    	MenuManager menuManager = workbenchWin.getMenuManager();
					    	IContributionItem[] items = menuManager.getItems();

					    	for(IContributionItem item : items) {
					    	  item.setVisible(false);
					    	}

						    MapEditorInput mapEditorInput = new MapEditorInput(atomicReference.get(), true, true);
						    IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
						    boolean editorPresent = false;
						    for (IEditorReference editorReference : editorReferences) {
								if(editorReference.getId().equals("net.sf.seesea.ui.editor.map")) { //$NON-NLS-1$
									editorPresent = true;
									break;
								}
							}
						    if(!editorPresent) {
						    	try {
						    		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(mapEditorInput, "net.sf.seesea.ui.editor.map", true); //$NON-NLS-1$
						    	} catch (PartInitException e) {
						    		Logger.getRootLogger().error("Failed to open editor", e); //$NON-NLS-1$
						    	}
						    }
							
						}
					});
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void postWindowCreate() {
    	super.postWindowCreate();
    	PreferenceManager pm = PlatformUI.getWorkbench( ).getPreferenceManager();
    	pm.remove("org.eclipse.ui.preferencePages.Workbench");
    }
}
