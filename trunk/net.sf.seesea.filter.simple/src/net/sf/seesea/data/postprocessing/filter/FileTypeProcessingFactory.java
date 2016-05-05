/**
Copyright (c) 2013-2015, Jens Kübler
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
package net.sf.seesea.data.postprocessing.filter;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import net.sf.seesea.data.postprocessing.process.IDepthPositionPreProcessor;
import net.sf.seesea.data.postprocessing.process.IFileTypeProcessingFactory;
import net.sf.seesea.data.postprocessing.process.IStatisticsPreprocessor;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.ITrackFileProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.SensorDescriptionUpdateRate;

@Component
public class FileTypeProcessingFactory implements IFileTypeProcessingFactory {
	
	private Map<ITrackFile, ComponentInstance> map;
	private BundleContext bundleContext;

	public FileTypeProcessingFactory() {
		map = new HashMap<ITrackFile, ComponentInstance>();
	}
	
	@Activate
	public void activate(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public IDepthPositionPreProcessor createLocationPreProcessor(ITrackFile trackFile) {
		if(trackFile.getFileType() != null) {
			ComponentInstance component = getComponent(trackFile.getFileType());
			map.put(trackFile, component);
			ITrackFileProcessor trackFileProcessor = (ITrackFileProcessor) component.getInstance();
			if(trackFileProcessor != null) {
				return new DepthPositionPreprocessor(trackFileProcessor);
			}
		}
		return null;
	}
	
	public void disposeLocationPreProcessor(ITrackFile trackFile) {
		ComponentInstance componentInstance = map.remove(trackFile);
		if(componentInstance != null) {
			componentInstance.dispose();
		}
	}

	public IStatisticsPreprocessor getPreprocessor(ITrackFile trackFile) {
		if(trackFile.getFileType() != null) {
			ITrackFileProcessor trackFileProcessor = (ITrackFileProcessor) getComponent(trackFile.getFileType()).getInstance();
			if(trackFileProcessor != null) {
				return new DepthPositionMeasurementStatisticsProcessor<Measurement>(trackFileProcessor);
			}
		}
		return null;
	}
	
	public ITrackFileProcessor createProcessor(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors, ITrackFile file) {
		ITrackFileProcessor service = (ITrackFileProcessor) getComponent(file.getFileType()).getInstance();
		service.setFilter(bestSensors);
		return service;
	}

	private ComponentInstance getComponent(String fileType) {
		if(fileType != null) {
			String type = fileType.substring(fileType.lastIndexOf("/") + 1, fileType.length()); //$NON-NLS-1$
			String filter = "(component.factory=trackfile." + type + ")"; //$NON-NLS-1$ //$NON-NLS-2$
			try {
				ServiceReference<?>[] serviceReferences = bundleContext.getServiceReferences(ComponentFactory.class.getName(), filter);
				if(serviceReferences != null && serviceReferences.length > 0) {
					ComponentFactory componentFactory = (ComponentFactory) bundleContext.getService(serviceReferences[0]);
					Hashtable properties = new Hashtable();
					ComponentInstance componentInstance = componentFactory.newInstance(properties);
					return componentInstance;
					
				}
			} catch (InvalidSyntaxException e) {
				Logger.getLogger(FileTypeProcessingFactory.class).error("Failed to aquire service", e); //$NON-NLS-1$
			}
		}
		return null;
	}

}
