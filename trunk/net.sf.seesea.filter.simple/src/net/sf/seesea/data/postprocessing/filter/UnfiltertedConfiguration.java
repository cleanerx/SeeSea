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

import java.util.Map;
import java.util.Map.Entry;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import net.sf.seesea.data.io.IWriterFactory;
import net.sf.seesea.data.postprocessing.process.IFilterConfiguration;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.data.IBoatParameters;
import net.sf.seesea.waterlevel.IWaterLevelCorrection;

@Component(property = {"type:String=unfiltered"}, configurationPolicy = ConfigurationPolicy.REQUIRE) 
public class UnfiltertedConfiguration implements IFilterConfiguration {
	
	private IWriterFactory writerFactory;
	private Map<String, Object> properties;
	private IBoatParameters boatParameters;
	private IWaterLevelCorrection waterLevelCorrection;

	@Activate
	public void activate(Map<String, Object> properties) {
		this.properties = properties;
		System.out.println("activating " + this);
	}

	@Deactivate
	public void deactivate(Map<String, Object> properties) {
		this.properties = null;
	}

	@Override
	public IMeasurmentProcessor createFilter(long updateRate, int positionPrecision) {
		
		// let noise depend on the ship type
//		NoiseKalmanSetup noiseKalmanSetup = getNoiseSetup(updateRate, positionPrecision);
//		UnfilteredMeasurementProcessor smoother = new UnfilteredMeasurementProcessor(writerFactory, properties, waterLevelCorrection, boatParameters);
//		StaticRateFilteringNoTimeBase smoother = new StaticRateFilteringNoTimeBase(writerFactory, outputOptions, noiseKalmanSetup, tideProvider);
//		StaticRateFiltering smoother = new StaticRateFiltering(writerFactory, outputOptions, updateRate, timeout, noiseKalmanSetup, tideProvider);

		// TODO Auto-generated method stub
		return null;
	}

	@Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MANDATORY)
	public synchronized void bindWriter(IWriterFactory writerFactory) {
		this.writerFactory = writerFactory;
	}

	public synchronized void unbindWriter(IWriterFactory writerFactory) {
		this.writerFactory = null;
	}

	@Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.OPTIONAL)
	public synchronized void bindBoatParameters(IBoatParameters boatParameters) {
		this.boatParameters = boatParameters;
	}

	public synchronized void unbindBoatParameters(IBoatParameters boatParameters) {
		this.boatParameters = null;
	}

	@Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.OPTIONAL)
	public synchronized void bindWaterLevelCorrection(IWaterLevelCorrection waterLevelCorrection) {
		this.waterLevelCorrection = waterLevelCorrection;
	}

	public synchronized void unbindWaterLevelCorrection(IWaterLevelCorrection waterLevelCorrection) {
		this.waterLevelCorrection = null;
	}

	@Override
	public boolean requiresRelativeTime() {
		return false;
	}

	/**
	 * if water level correction if configured that absolute time is required for this filter
	 */
	@Override
	public boolean requiresAbsoluteTime() {
		if(waterLevelCorrection != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, Object> entries : properties.entrySet()) {
			if(!entries.getKey().contains("password")) { //$NON-NLS-1$
				buffer.append(entries.getKey());
				buffer.append(":"); //$NON-NLS-1$
				buffer.append(entries.getValue());
				buffer.append(" "); //$NON-NLS-1$
			}
		}
		return  super.toString() + buffer.toString() ;
	}
	

}
