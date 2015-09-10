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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoPosition3D;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.RelativeSpeed;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.model.core.physx.Time;

/**
 * This class is used for a single measurement.
 * Thery may be multiple positions or multiple depths per window an it is the responsibility of this class to return only one depth and one position measurement
 */
public class MeasurmentWindow {

	private long windowStartTime; 
	
	private List<Depth> depths;

	private List<Heading> headings;

	private List<RelativeSpeed> speeds;

	private List<GeoPosition3D> positions;

	public MeasurmentWindow() {
		depths = new ArrayList<Depth>(2);
		headings = new ArrayList<Heading>(2);
		speeds = new ArrayList<RelativeSpeed>(2);
		positions = new ArrayList<GeoPosition3D>(2);;
	}
	
	public void addMeasurements(Collection<Measurement> measurements) {
		for (Measurement measurement : measurements) {
			setMeasurement(measurement);
		}
	}
	
	public void setMeasurement(Measurement containedMeasurement) {
		// only process if this is a valid measurement
		if(containedMeasurement.isValid()) {
			if(containedMeasurement instanceof Time && ((Time) containedMeasurement).getTime() != null) {
				Time time = (Time) containedMeasurement;
				windowStartTime = time.getTime().getTime();
			} else if(containedMeasurement instanceof MeasuredPosition3D) {
				positions.add((MeasuredPosition3D) containedMeasurement);
			} else if(containedMeasurement instanceof Depth) {
				depths.add((Depth) containedMeasurement);
			} else if(containedMeasurement instanceof Heading && HeadingType.COMPASS.equals(((Heading)containedMeasurement).getHeadingType())) {
				headings.add((Heading)containedMeasurement);
			} else if(containedMeasurement instanceof RelativeSpeed && SpeedType.SPEEDTHOUGHWATER.equals(((RelativeSpeed)containedMeasurement).getKey())) {
				speeds.add((RelativeSpeed)containedMeasurement);
			} 
			if(windowStartTime == 0 && containedMeasurement.getTime() != null) {
				windowStartTime = containedMeasurement.getTime().getTime();
			}
		}
	}


	protected long getWindowStartTime() {
		return windowStartTime;
	}


	public List<Depth> getDepths() {
		return depths;
	}


	public List<GeoPosition3D> getPositions() {
		return positions;
	}
	
	

}
