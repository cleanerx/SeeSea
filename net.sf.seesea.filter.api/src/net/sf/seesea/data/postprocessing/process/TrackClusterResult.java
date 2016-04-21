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
package net.sf.seesea.data.postprocessing.process;

import java.util.List;
import java.util.Set;

import net.sf.seesea.track.api.data.ITrackFile;

/**
 * 
 */
public class TrackClusterResult {
	
	/** the ordered list of tracks */
	private List<List<ITrackFile>> clusterOfTracks;
	
	/** tracks that have been identified as duplicates */
	private Set<ITrackFile> duplicateTrackFiles;
	
	/** tracks that have been identified as corrupt and cannot be read */
	private Set<ITrackFile> corruptTrackFiles;

	/** tracks that have been identified as having no usable data */
	private Set<ITrackFile> nodataTrackFiles;

	private final Set<ITrackFile> noTimeMeasurementFiles;

	public TrackClusterResult(List<List<ITrackFile>> clusterOfTracks,
			Set<ITrackFile> duplicateTrackFiles, Set<ITrackFile> corruptTrackFiles, Set<ITrackFile> nodataTrackFiles, Set<ITrackFile> noTimeMeasurementFiles) {
		super();
		this.clusterOfTracks = clusterOfTracks;
		this.duplicateTrackFiles = duplicateTrackFiles;
		this.corruptTrackFiles = corruptTrackFiles;
		this.nodataTrackFiles = nodataTrackFiles;
		this.noTimeMeasurementFiles = noTimeMeasurementFiles;
	}

	public List<List<ITrackFile>> getOrderedTrackFiles() {
		return clusterOfTracks;
	}

	public Set<ITrackFile> getDuplicateTrackFiles() {
		return duplicateTrackFiles;
	}

	public Set<ITrackFile> getCorruptTrackFiles() {
		return corruptTrackFiles;
	}

	public Set<ITrackFile> getNodataTrackFiles() {
		return nodataTrackFiles;
	}

	public Set<ITrackFile> getNoTimeMeasurementFiles() {
		return noTimeMeasurementFiles;
	}
	
	
	

}
