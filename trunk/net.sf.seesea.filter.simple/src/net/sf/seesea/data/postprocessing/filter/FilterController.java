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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.util.tracker.ServiceTracker;

import net.sf.seesea.data.io.WriterException;
import net.sf.seesea.data.postprocessing.process.FilterException;
import net.sf.seesea.data.postprocessing.process.IFileTypeProcessingFactory;
import net.sf.seesea.data.postprocessing.process.IFilterConfiguration;
import net.sf.seesea.data.postprocessing.process.IFilterController;
import net.sf.seesea.data.postprocessing.process.IStatisticsPreprocessor;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.track.api.ITrackFileProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.SensorDescriptionUpdateRate;
import net.sf.seesea.track.api.exception.ProcessingException;

/**
 * The main class that controls the filtering process 
 */
@Component
public class FilterController implements IFilterController {

	private long timeout;

	private List<Map<String, Object>> filterProperties;

	private IFileTypeProcessingFactory processingFactory;

	private BundleContext context;
	
	private AtomicReference<IFileTypeProcessingFactory> fileTypeProcessingFactoryAR = new AtomicReference<IFileTypeProcessingFactory>();

	public void setFilterProperties(List<Map<String, Object>> filterProperties) {
		this.filterProperties = filterProperties;
	}
	
	public void activate(BundleContext context) {
		this.context = context;
	}

	public void deactivate(BundleContext context) {
		this.context = null;
	}

	/**
	 * process this list of tracks as batch
	 * 
	 * @param orderedFiles the tracks to process at once
	 * @param executeSensorDistribution true, if multiple sensor are available and the best one needs to be chosen
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws WriterException
	 * @throws ProcessingException
	 */
	public void process(Collection<ITrackFile> orderedFiles, boolean executeSensorDistribution) throws FilterException {

		// when the file type changes, consider it a new track
		String lastFileType = null;
		IStatisticsPreprocessor preprocessor = null;
		List<ITrackFile> trackFiles = new ArrayList<ITrackFile>(); 
		for (ITrackFile trackFile : orderedFiles) {
			try {
				if(trackFile.getFileType() == null) {
					// hm must be a zip file
				} else if(!trackFile.getFileType().equals(lastFileType)) {
					// file type changed -> run statistics again
					if(preprocessor != null) {
						Set<SensorDescriptionUpdateRate<Measurement>> bestSensors = preprocessor.getBestSensors();
						runFilters(trackFiles, bestSensors);
					}
					preprocessor = processingFactory.getPreprocessor(trackFile);
					trackFiles.clear();
					lastFileType = trackFile.getFileType();
					if(executeSensorDistribution) {
						try {
							preprocessor.processFiles(trackFile);
						} catch (ProcessingException e) {
							// must be correct at this point since the preprocessor passed it with depth points
							Logger.getLogger(getClass()).error("Partially correct data for for track id " + trackFile.getTrackId());
						}
					}
					trackFiles.add(trackFile);
				} else {
					// continue processing this file
					if(executeSensorDistribution) {
						preprocessor.processFiles(trackFile);
					}
					trackFiles.add(trackFile);
				}
			} catch (Exception e) {
				Logger.getLogger(getClass()).error("Failed to process file", e); //$NON-NLS-1$
			}
		}
		try {
		// trigger any open filter run
		if(!trackFiles.isEmpty() && executeSensorDistribution) {
			Set<SensorDescriptionUpdateRate<Measurement>> bestSensors = preprocessor.getBestSensors();
			runFilters(trackFiles, bestSensors);
		} else if(!trackFiles.isEmpty()) {
			Set<SensorDescriptionUpdateRate<Measurement>> noSensors = new HashSet<SensorDescriptionUpdateRate<Measurement>>();
				runFilters(trackFiles, noSensors);
		}
		} catch (IOException | ProcessingException e) {
			throw new FilterException(e);
		}
	}
	
	/**
	 * 
	 * @param orderedFiles the list of tracks to process as batch
	 * @param bestSensors the sensors to be used for filterung. may be an empty set if there is only one sensor per measurement
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public void runFilters(List<ITrackFile> orderedFiles, Set<SensorDescriptionUpdateRate<Measurement>> bestSensors) throws IOException, ProcessingException {
		logBestSensorChoice(bestSensors);
//		System.out.println("Wrinting Filter data to " + format);
		
		// determine best setup for track files that are no more than x seconds apart
		long updateRate = 1000;
		int positionPrecision = 0;
		
		if(!bestSensors.isEmpty()) {
			for(SensorDescriptionUpdateRate<Measurement> sensor :  bestSensors) {
				if(MeasuredPosition3D.class.isAssignableFrom(sensor.getMeasurement())) {
					updateRate = sensor.getUpdateRate();
					positionPrecision = sensor.getPrecision();
					break;
				}
			}
		}
		
		System.out.println("Starting filter process");
		
		List<Configuration> configurations = new ArrayList<Configuration>(); 
		try {
			ServiceTracker<IFilterConfiguration, IFilterConfiguration> serviceTracker = new ServiceTracker<IFilterConfiguration, IFilterConfiguration>(context, IFilterConfiguration.class, null);
			serviceTracker.open();
			int i = 0;
			for (Map<String, Object> filterProperty : filterProperties) {
				// configure output writer (like csv, postgis)
				String writerFactoryType = (String) filterProperty.get("writer"); //$NON-NLS-1$
				filterProperty.put("IWriterFactory.target", MessageFormat.format("(type={0})", writerFactoryType));  //$NON-NLS-1$//$NON-NLS-2$

				// configure water level correction if any
				String waterlevelcorrectionType = (String) filterProperty.get("waterlevelcorrection"); //$NON-NLS-1$
				filterProperty.put("IWaterLevelCorrection.target", MessageFormat.format("(type={0})", waterlevelcorrectionType));  //$NON-NLS-1$//$NON-NLS-2$

				// create the kind of filter to run
				String filterType = (String) filterProperty.get("type"); //$NON-NLS-1$
				ServiceReference<ConfigurationAdmin> caServiceReference = context.getServiceReference(ConfigurationAdmin.class);
				ConfigurationAdmin configurationAdmin = context.getService(caServiceReference);
				Configuration[] configurationsPID = configurationAdmin.listConfigurations(MessageFormat.format("(service.factoryPid={0})", filterType) ); //$NON-NLS-1$
				if(configurationsPID != null) {
					for (Configuration configuration : configurationsPID) {
						configuration.delete();
					}
				}
//				configurationsPID[0].delete()
				Configuration configuration = null;
//				if(configurationsPID == null || configurationsPID.length == 0) {
					configuration = configurationAdmin.createFactoryConfiguration(filterType);
//				} else {
//					configuration = configurationsPID[i++];
//				}
				Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
				hashtable.putAll(filterProperty);
				configuration.update(hashtable);
				configurations.add(configuration);
			}
			

			// let the components come up
			while(serviceTracker.getServiceReferences() == null || serviceTracker.getServiceReferences().length < filterProperties.size()) {
				Logger.getLogger(getClass()).info("Waiting for service references to become online");
				Thread.sleep(50);
			}
			
			IFileTypeProcessingFactory fileTypeProcessingFactory = fileTypeProcessingFactoryAR.get();
			for (ServiceReference<IFilterConfiguration> serviceReference : serviceTracker.getServiceReferences()) {
				ITrackFileProcessor trackFileProcessor = fileTypeProcessingFactory.createProcessor(bestSensors, orderedFiles.iterator().next());
				IFilterConfiguration filterConfiguration = (IFilterConfiguration) context.getService(serviceReference);
				System.out.println("Running filter " + filterConfiguration);
				net.sf.seesea.track.api.IMeasurmentProcessor measurementProcessor = filterConfiguration.createFilter(updateRate, positionPrecision);
				// tie the reading processor to the filter
				trackFileProcessor.setMeasurementProcessor(measurementProcessor);

				// only run compatible filters
				boolean processFiles4Filter = false;
				if(filterConfiguration.requiresAbsoluteTime() && trackFileProcessor.hasAbsoluteTime()) {
					processFiles4Filter = true;
				} else if(filterConfiguration.requiresRelativeTime() && trackFileProcessor.hasRelativeTime()) {
					processFiles4Filter = true;
				} else if(!filterConfiguration.requiresAbsoluteTime() && !filterConfiguration.requiresRelativeTime()) {
					processFiles4Filter = true;
				}
				if(!processFiles4Filter) {
					Logger.getLogger(getClass()).info("Skipping filter run for track file processor " + trackFileProcessor + " with filter " + filterConfiguration);
				}
				if(processFiles4Filter) {
					System.out.println("Running filter run for track file processor " + trackFileProcessor + " with filter " + filterConfiguration);
					for (ITrackFile trackFile : orderedFiles) {
						try {
							System.out.println("Processing track id:" + trackFile.getTrackId());
							trackFileProcessor.processFile(trackFile);
						} catch (ProcessingException e) {
							Logger.getLogger(getClass()).error("Partially correct data for for track id " + trackFile.getTrackId());
						}
					}
					measurementProcessor.finish();
				}
				
			}
			serviceTracker.close();
		} catch (InvalidSyntaxException e) {
			Logger.getLogger(getClass()).error("OSGi filter failed", e); //$NON-NLS-1$
		} catch (InterruptedException e) {
			Logger.getLogger(getClass()).error("Interrupted while waiting for configuration services to come up", e); //$NON-NLS-1$
		} finally {
			for (Configuration configuration : configurations) {
				configuration.delete();
			}
		}
	}

	private void logBestSensorChoice(
			Set<SensorDescriptionUpdateRate<Measurement>> bestSensors) {
		System.out.println("Best sensors chosen for processing are");
		for (SensorDescriptionUpdateRate<Measurement> sensorDescriptionUpdateRate : bestSensors) {
			if(sensorDescriptionUpdateRate != null) {
				System.out.println(sensorDescriptionUpdateRate.toString());
			}
		}
	}
	
	@Reference(cardinality = ReferenceCardinality.OPTIONAL, policy = ReferencePolicy.DYNAMIC)
	public void bindFileTypeProcessingFactory(IFileTypeProcessingFactory fileTypeProcessingFactory) {
		fileTypeProcessingFactoryAR.set(fileTypeProcessingFactory);
	}

	public void unbindFileTypeProcessingFactory(IFileTypeProcessingFactory fileTypeProcessingFactory) {
		fileTypeProcessingFactoryAR.compareAndSet(null, fileTypeProcessingFactory);
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

}
