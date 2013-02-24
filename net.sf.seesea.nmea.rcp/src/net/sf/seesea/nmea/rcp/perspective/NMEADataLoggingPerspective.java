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
package net.sf.seesea.nmea.rcp.perspective;

import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.util.IModel;
import net.sf.seesea.nmea.rcp.NMEARCPActivator;
import net.sf.seesea.rendering.chart.editor.MapEditorInput;

import org.apache.log4j.Logger;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * 
 */
public class NMEADataLoggingPerspective implements IPerspectiveFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		IFolderLayout topRight  = layout.createFolder("topRight", IPageLayout.LEFT, 0.3f, editorArea); //$NON-NLS-1$
		topRight.addView("net.sf.seesea.navigation.ui.sensorData"); //$NON-NLS-1$

	    IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.BOTTOM, 0.7f, editorArea); //$NON-NLS-1$
	    topLeft.addView("net.sf.seesea.provider.navigation.nmea.log.view"); //$NON-NLS-1$

		try {
			if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
				BundleContext bundleContext = NMEARCPActivator.getDefault().getBundle().getBundleContext(); 
				ServiceReference<IModel> serviceReference = bundleContext.getServiceReference(IModel.class);
				IModel model = bundleContext.getService(serviceReference);
				World world = model.loadModel();
				bundleContext.ungetService(serviceReference);
				
				MapEditorInput mapEditorInput = new MapEditorInput(world, true, true);
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(mapEditorInput, "net.sf.seesea.ui.editor.map", true); //$NON-NLS-1$
			}
		} catch (PartInitException e) {
			Logger.getRootLogger().error("Failed to open editor", e); //$NON-NLS-1$
		}

	}

}
