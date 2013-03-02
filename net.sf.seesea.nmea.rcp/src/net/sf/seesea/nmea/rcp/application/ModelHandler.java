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

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import net.sf.seesea.model.core.CoreFactory;
import net.sf.seesea.model.core.ModelRoot;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.Track;
import net.sf.seesea.model.core.geo.TracksContainer;
import net.sf.seesea.model.core.geo.osm.OsmFactory;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.util.IModel;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ModelHandler implements IModel {

//	private File baseDirectory;

	private static final String VIEW_CONFIG_CHT = "viewConfig.cht"; //$NON-NLS-1$
	private static final String CHT = "cht"; //$NON-NLS-1$
	private World world;
	
	public World createDefaultModel() {
		ResourceSet resourceSet = new ResourceSetImpl();

		// Register the default resource factory -- only needed for stand-alone!
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());

		// Get the URI of the model file.
		URI fileURI = URI.createFileURI(new File(System.getProperty("user.home") + File.separator + "NMEALogging" + File.separator + VIEW_CONFIG_CHT) //$NON-NLS-1$ //$NON-NLS-2$
				.getAbsolutePath());

		// Create a resource for this file.
		Resource resource = resourceSet.createResource(fileURI);

		CoreFactory coreFactory = net.sf.seesea.model.core.CoreFactory.eINSTANCE;
		GeoFactory factory = net.sf.seesea.model.core.geo.GeoFactory.eINSTANCE;
		ModelRoot modelRoot = coreFactory.createModelRoot();
		modelRoot.setChartContainer(factory.createChartContainer());

		Latitude latitude2 = factory.createLatitude();
		latitude2.setDecimalDegree(49.0);
		resource.getContents().add(latitude2);

		Longitude longitude2 = factory.createLongitude();
		longitude2.setDecimalDegree(8.23);
		resource.getContents().add(longitude2);

		GeoPosition geoPosition = factory.createGeoPosition();
		geoPosition.setLatitude(latitude2);
		geoPosition.setLongitude(longitude2);
		resource.getContents().add(geoPosition);

		world = OsmFactory.eINSTANCE.createWorld();
		world.setMapCenterPosition(geoPosition);
		world.setZoomLevel(4);
		world.setLatitudeScale(true);
		world.setLongitudeScale(true);
		resource.getContents().add(world);

		TracksContainer tracksContainer = GeoFactory.eINSTANCE
				.createTracksContainer();
		resource.getContents().add(tracksContainer);
		world.setTracksContainer(tracksContainer);

		Track track = GeoFactory.eINSTANCE.createTrack();
		resource.getContents().add(track);
		tracksContainer.getTracks().add(track);

		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e1) {
			Logger.getLogger(ModelHandler.class).error(
					"Failed to save initial model", e1); //$NON-NLS-1$
		}
		return world;
	}

	public World loadModel() {
		if(world == null) {
			
			// Get the URI of the model file.
			File file = new File(VIEW_CONFIG_CHT);
			if(!file.exists()) {
			    world = createDefaultModel();
			} else {
				GeoPackage.eINSTANCE.eClass();
				Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
				Map<String, Object> m = reg.getExtensionToFactoryMap();
				m.put(CHT, new XMIResourceFactoryImpl());
				
				ResourceSet resourceSet = new ResourceSetImpl();
				
				// Register the default resource factory -- only needed for stand-alone!
				resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,new XMIResourceFactoryImpl());
				
				URI fileURI = URI.createFileURI(file.getAbsolutePath());
				
				// Create a resource for this file.
				Resource resource = resourceSet.getResource(fileURI,true);
				world = (World) resource.getContents().get(0);
			}
		}
		return world;
	}

	public void saveModel() throws IOException {
		Resource resource = world.eResource();
		resource.save(Collections.EMPTY_MAP);
	}
	
	public void closeModel() {
		world = null;
	}
	
}

