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
package net.sf.seesea.track.model;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.track.api.data.CompressionType;
import net.sf.seesea.track.api.data.IBoatParameters;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.ProcessingState;
import net.sf.seesea.track.api.exception.InputStreamNotFoundException;

public abstract class AbstractTrackFile implements ITrackFile {

	private long trackId;
	private String username;
	private ProcessingState uploadState;
	private String fileType;
	private CompressionType compression;
	private MeasuredPosition3D start;
	private MeasuredPosition3D end;
	private List<MeasuredPosition3D> firstPositions;
	private VesselConfiguration vesselConfiguration;
	private String name;
	private boolean hasAbsoluteTimedMeasurements;
	private boolean hasRelativeTimedMeasurements;
	private GeoBoundingBox boundingBox;
	private IBoatParameters boatParameters;

	public AbstractTrackFile() {
		super();
	}

	@Override
	public long getTrackId() {
		return trackId;
	}

	public void setTrackId(long trackId) {
		this.trackId = trackId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.data.postprocessing.data.ITrackFile#getInputStream()
	 */
	@Override
	public abstract InputStream getInputStream() throws InputStreamNotFoundException;

	public ProcessingState getUploadState() {
		return uploadState;
	}

	public void setUploadState(ProcessingState uploadState) {
		this.uploadState = uploadState;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public CompressionType getCompression() {
		return compression;
	}

	public void setCompression(CompressionType compression) {
		this.compression = compression;
	}

	public Date getStartTime() {
		if(start == null) {
			return null;
		}
		return start.getTime();
	}

	public Date getEndTime() {
		if(end == null) {
			return null;
		}
		return end.getTime();
	}
	
	public MeasuredPosition3D getStart() {
		return this.start;
	}

	public void setStart(MeasuredPosition3D start) {
		this.start = start;
	}

	public MeasuredPosition3D getEnd() {
		return end;
	}

	public void setEnd(MeasuredPosition3D end) {
		this.end = end;
	}

	public List<MeasuredPosition3D> getFirstPositions() {
		return firstPositions;
	}

	public void setFirstPositions(List<MeasuredPosition3D> firstPositions) {
		this.firstPositions = firstPositions;
	}

	public VesselConfiguration getVesselConfiguration() {
		return vesselConfiguration;
	}

	public void setVesselConfiguration(VesselConfiguration vesselConfiguration) {
		this.vesselConfiguration = vesselConfiguration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAbsoluteTimeMeasurements(boolean hasAbsoluteTimedMeasurements) {
		this.hasAbsoluteTimedMeasurements = hasAbsoluteTimedMeasurements;
		
	}

	public void setRelativeTimeMeasurements(boolean hasRelativeTimedMeasurements) {
		this.hasRelativeTimedMeasurements = hasRelativeTimedMeasurements;
	}

	public boolean isHasAbsoluteTimedMeasurements() {
		return hasAbsoluteTimedMeasurements;
	}

	public boolean isHasRelativeTimedMeasurements() {
		return hasRelativeTimedMeasurements;
	}

	public void setBoundingBox(GeoBoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	public GeoBoundingBox getBoundingBox() {
		return boundingBox;
	}
	
	public IBoatParameters getBoatParameters() {
		return boatParameters;
	}

	@Override
	public void setBoatParameters(IBoatParameters boatParameters) {
		this.boatParameters = boatParameters;
	}
	
	
	
	
	
	
	

}