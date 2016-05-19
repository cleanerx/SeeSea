/**
 * 
Copyright (c) 2010-2013, Jens Kübler
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

package net.sf.seesea.provider.navigation.fsh;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.fsh.FSHReader;
import net.sf.seesea.provider.navigation.fsh.FSHStreamProcessor;
import net.sf.seesea.provider.navigation.fsh.FlobHeader;
import net.sf.seesea.provider.navigation.fsh.data.FSHBlock;
import net.sf.seesea.provider.navigation.fsh.data.FSHHeader;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.ITrackFileProcessor;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.SensorDescriptionUpdateRate;
import net.sf.seesea.track.api.exception.ProcessingException;

@Component(factory="trackfile.x-flashfile")
public class FSHTrackFileProcessor implements ITrackFileProcessor {

	private IMeasurmentProcessor measurmentProcessor;
	private FSHReader fshReader;
	
	public FSHTrackFileProcessor() {
		fshReader = new FSHReader();
	}

	@Override
	public void processFile(ITrackFile recordedFile) throws FileNotFoundException,
			IOException, ProcessingException {
		try {
			InputStream inputStream = recordedFile.getInputStream();
			FSHStreamProcessor fshStreamProcessor = new FSHStreamProcessor();
			FSHHeader fshHeader = fshStreamProcessor.readHeader(inputStream);
			for(int i = 0 ; i < 128 ; i++) {
				// dummy read the 28 bytes
				if(i > 0) {
					fshStreamProcessor.readHeader(inputStream);
				}
				FlobHeader flobHeader = fshStreamProcessor.readFlobHeader(inputStream);
				long bytesRead = 28 + 14;
				FSHBlock fshBlock = fshStreamProcessor.readBlock(inputStream);
				bytesRead += 14;
				while(fshBlock != null && fshBlock.getType() != 65535) {
					byte[] message = new byte[fshBlock.getLength()];
					int read = inputStream.read(message);
//					System.out.println(read);
					bytesRead += fshBlock.getLength();
					List<Measurement> measurements = fshReader.extractMeasurementsFromFSH(fshBlock, message);
					measurmentProcessor.processMeasurements(measurements, "none", recordedFile.getTrackId(), recordedFile.getBoundingBox(), recordedFile.getBoatParameters());
//					System.out.println("Type" + fshBlock.getType() + " : Length" + fshBlock.getLength() + " measurements : " + measurements.size() / 2);
					fshBlock = fshStreamProcessor.readBlock(inputStream);
					bytesRead += 14;
				}
//				System.out.println((1L << 20) - bytesRead);
				inputStream.skip((1L << 16) - bytesRead );
			}
		} catch (Exception e) {
			throw new ProcessingException(e);
		}
	}

	@Override
	public void setMeasurementProcessor(IMeasurmentProcessor measurmentProcessor) {
		this.measurmentProcessor = measurmentProcessor;
	}

	@Override
	public boolean hasTimedMeasurments() {
		return false;
	}

	@Override
	public void setFilter(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasRelativeTime() {
		return false;
	}

	@Override
	public boolean hasAbsoluteTime() {
		return false;
	}

}
