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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.ProcessingException;

/**
 * This preprocessor gathers statistics about the track file.
 * They may be used for clustering, duplicate detection and bounding box calculations
 */
public interface IDepthPositionPreProcessor extends IMeasurmentProcessor {

	/**
	 * 
	 * @return the start position of the track
	 */
	MeasuredPosition3D getStart();
	
	/**
	 * 
	 * @return the end position of the track
	 */
	MeasuredPosition3D getEnd();
	
	/**
	 * 
	 * @return an arbitrary amount of the first track points
	 */
	List<MeasuredPosition3D> getFirstTrackPoints();
	
	/** returns if this data contains any depth data */
	boolean hasDepthData();

	/**
	 * 
	 * @param trackFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ProcessingException
	 */
	void processFiles(ITrackFile trackFile) throws FileNotFoundException,
			IOException, ProcessingException;

	/**
	 * 
	 * @return true if this track contains timed measurements
	 */
	boolean hasRelativeTimedMeasurements();

	/**
	 * 
	 * @return true if this track contains timed measurements
	 */
	boolean hasAbsoluteTimedMeasurements();
	
	/**
	 * 
	 * @return how many valid raw points are contained
	 */
	long getPointCount();

	/**
	 * 
	 * 
	 * @return retrieves the bounding box of the track
	 */
	GeoBoundingBox getBoundingBox();
	
}
