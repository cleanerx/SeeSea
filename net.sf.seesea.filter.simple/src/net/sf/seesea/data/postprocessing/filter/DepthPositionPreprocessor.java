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
import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.data.postprocessing.process.IDepthPositionPreProcessor;
import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.CompositeMeasurement;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.PhysxFactory;
//import net.sf.seesea.services.navigation.GeoBoundingBox;
//import net.sf.seesea.services.navigation.IGeoBoundingBox;
import net.sf.seesea.track.api.ITrackFileProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.ProcessingException;

public class DepthPositionPreprocessor implements IDepthPositionPreProcessor {

	private final ITrackFileProcessor trackFileProcessor;

	public DepthPositionPreprocessor(ITrackFileProcessor trackFileProcessor) {
		this.trackFileProcessor = trackFileProcessor;
		trackFileProcessor.setMeasurementProcessor(this);
		timedRelativeMeasurements = false;
		pointCount = 0;
	}

	private GeoBoundingBox boundingBox;

	private MeasuredPosition3D start;
	
	private MeasuredPosition3D end;
	
	private boolean containsDepthData = false;
	
	private boolean timedRelativeMeasurements;
	
	private long pointCount;

	/** used to compare the first 10 points */
	private List<MeasuredPosition3D> firstTrackPoints = new ArrayList<MeasuredPosition3D>();

	private boolean timedAbsoluteMeasurements;

	@Override
	public MeasuredPosition3D getStart() {
		return start;
	}

	@Override
	public MeasuredPosition3D getEnd() {
		return end;
	}

	@Override
	public List<MeasuredPosition3D> getFirstTrackPoints() {
		return firstTrackPoints;
	}

	@Override
	public boolean hasDepthData() {
		return containsDepthData;
	}

	@Override
	public void processFiles(ITrackFile trackFile) throws FileNotFoundException,
			IOException, ProcessingException {
		trackFileProcessor.processFile(trackFile);
		trackFile.setStart(start);
		trackFile.setEnd(end);
	}

	public void processMeasurements(List<Measurement> results, String messageType, long sourceTrackIdentifier,
			GeoBoundingBox boundingBox) {
		for (Measurement measurement : results) {
			if(measurement instanceof CompositeMeasurement) {
				processMeasurements(((CompositeMeasurement) measurement).getMeasurements(), messageType, sourceTrackIdentifier, boundingBox);
				
			} else if (measurement instanceof MeasuredPosition3D && measurement.isValid()) {
				// && (measurement.getTime() != null || !trackFileProcessor.hasTimedMeasurments())  &&
				if(measurement.getTime() != null && measurement.getTime().getTime() > 0L) {
					if(trackFileProcessor.hasAbsoluteTime()) {
						timedAbsoluteMeasurements = true;
					}
					if(trackFileProcessor.hasRelativeTime()) {
						timedRelativeMeasurements = true;
					}
				}
				MeasuredPosition3D measuredPosition3D = (MeasuredPosition3D) measurement;
				if(start == null) {
					start = measuredPosition3D;
				}
				if(firstTrackPoints.size() < 10) {
					firstTrackPoints.add(measuredPosition3D);
				}
				end = measuredPosition3D;
				if(this.boundingBox == null) {
					this.boundingBox = GeoFactory.eINSTANCE.createGeoBoundingBox();
					this.boundingBox.setLeft(measuredPosition3D.getLongitude().getDecimalDegree());
					this.boundingBox.setRight(measuredPosition3D.getLongitude().getDecimalDegree());
					this.boundingBox.setTop(measuredPosition3D.getLatitude().getDecimalDegree());
					this.boundingBox.setBottom(measuredPosition3D.getLatitude().getDecimalDegree());
				}
				if(measuredPosition3D.getLatitude().getDecimalDegree() > this.boundingBox.getTop()) {
					this.boundingBox.setTop(measuredPosition3D.getLatitude().getDecimalDegree());
				}
				if(measuredPosition3D.getLatitude().getDecimalDegree() < this.boundingBox.getBottom()) {
					this.boundingBox.setBottom(measuredPosition3D.getLatitude().getDecimalDegree());
				}
				if(measuredPosition3D.getLongitude().getDecimalDegree() < this.boundingBox.getLeft()) {
					this.boundingBox.setLeft(measuredPosition3D.getLongitude().getDecimalDegree());
				}
				if(measuredPosition3D.getLongitude().getDecimalDegree() > this.boundingBox.getRight()) {
					this.boundingBox.setRight(measuredPosition3D.getLongitude().getDecimalDegree());
				}
				pointCount = pointCount + 1L;
			} else if (measurement instanceof Depth && measurement.isValid()) {
				containsDepthData = true;
			}
		}
	}

	@Override
	public void finish() throws ProcessingException {
		// nothing to be done
	}

	public GeoBoundingBox getBoundingBox() {
		return boundingBox;
	}

	public boolean hasRelativeTimedMeasurements() {
		return timedRelativeMeasurements;
	}

	public long getPointCount() {
		return pointCount;
	}

	@Override
	public boolean hasAbsoluteTimedMeasurements() {
		return timedAbsoluteMeasurements;
	}


}
