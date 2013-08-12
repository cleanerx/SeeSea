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
package net.sf.seesea.rendering.chart.editor;



import java.net.URL;

import net.sf.seesea.model.core.provider.XEditPlugin;
import net.sf.seesea.rendering.chart.tools.AdvancedSelectionToolEntry;
import net.sf.seesea.rendering.chart.tools.AnchorWarnToolEntry;
import net.sf.seesea.rendering.chart.tools.Cache2ToolEntry;
import net.sf.seesea.rendering.chart.tools.CacheToolEntry;
import net.sf.seesea.rendering.chart.tools.ChartSelectionToolEntry;
import net.sf.seesea.rendering.chart.tools.DistanceToolEntry;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * 
 */
public class SeeSeaPaletteFactory {

	public static PaletteRoot createPalette() {
		PaletteRoot seeseaPalette = new PaletteRoot();
		PaletteGroup controlGroup = new PaletteGroup("Control Group"); //$NON-NLS-1$
		seeseaPalette.add(controlGroup);
//		controlGroup.add(new MarqueeToolEntry());
//		ChartSelectionToolEntry toolEntry = new ChartSelectionToolEntry();
//		controlGroup.add(toolEntry);
		AdvancedSelectionToolEntry toolEntry3 = new AdvancedSelectionToolEntry();
		controlGroup.add(toolEntry3);
//		SelectionToolEntry toolEntry2 = new SelectionToolEntry();
//		controlGroup.add(toolEntry2);
		
		seeseaPalette.add(new PaletteDrawer("Navigation Group"));

		PaletteGroup navigationGroup = new PaletteGroup("Navigation Group"); //$NON-NLS-1$
		seeseaPalette.add(navigationGroup);
		seeseaPalette.setDefaultEntry(toolEntry3);

//		URL entry = XEditPlugin.getPlugin().getBundle().getEntry("icons/full/obj16/Route.gif"); //$NON-NLS-1$
//		ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(entry);
//		creationFactory = new CreationFactoryImplementation(GeoPackage.eINSTANCE.getRoute());
		ToolEntry anchorToolEntry = new AnchorWarnToolEntry("Anchor Alert", "Defines an area for anchor alert");
		navigationGroup.add(anchorToolEntry);
		ToolEntry distanceToolEntry = new DistanceToolEntry();
		navigationGroup.add(distanceToolEntry);
//		URL entry = XEditPlugin.getPlugin().getBundle().getEntry("icons/full/obj16/NamedPosition.gif"); //$NON-NLS-1$
//		ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(entry);
//		CreationFactory creationFactory = new CreationFactoryImplementation(GeoPackage.eINSTANCE.getNamedPosition());
//		CreationToolEntry positionCreation = new CreationToolEntry("Mark Position", "Creates a position at the mouse cursor", creationFactory, imageDescriptor, imageDescriptor);
//		navigationGroup.add(positionCreation);
//
//		entry = XEditPlugin.getPlugin().getBundle().getEntry("icons/full/obj16/Route.gif"); //$NON-NLS-1$
//		imageDescriptor = ImageDescriptor.createFromURL(entry);
//		creationFactory = new CreationFactoryImplementation(GeoPackage.eINSTANCE.getRoute());
//		ConnectionCreationToolEntry connectionCreationToolEntry = new ConnectionCreationToolEntry("Create Route", "Creates a route at each clicked position of the mouse cursor", creationFactory, imageDescriptor, imageDescriptor);
//		navigationGroup.add(connectionCreationToolEntry);
		
		URL entry = XEditPlugin.getPlugin().getBundle().getEntry("icons/full/obj16/Route.gif"); //$NON-NLS-1$
		ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(entry);
//		creationFactory = new CreationFactoryImplementation(GeoPackage.eINSTANCE.getRoute());
		ToolEntry cacheToolEntry = new CacheToolEntry("Cache Area", "Caches the chosen area on hard disk");
		navigationGroup.add(cacheToolEntry);

		ToolEntry cacheToolEntry2 = new Cache2ToolEntry("Cache Area2", "Caches the chosen area on hard disk");
		navigationGroup.add(cacheToolEntry2);

		return seeseaPalette;
	}


}
