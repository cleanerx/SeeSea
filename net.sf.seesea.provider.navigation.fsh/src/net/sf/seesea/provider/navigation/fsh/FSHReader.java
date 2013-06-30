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
 */package net.sf.seesea.provider.navigation.fsh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.fsh.data.FSHBlock;


public class FSHReader {

	private final Set<Integer> fshToAnalyze;

	public FSHReader(Set<Integer> fshToAnalyze) {
		this.fshToAnalyze = fshToAnalyze;
	}
	
	public FSHReader() {
		this.fshToAnalyze = Collections.emptySet();
	}

	public List<Measurement> extractMeasurementsFromFSH(FSHBlock block,
			byte[] message) {
		List<Measurement> measurements = new ArrayList<Measurement>();
		if (fshToAnalyze.isEmpty() || fshToAnalyze.contains(block.getType())) {

			switch (block.getType()) {
			// waypoints
			case 0x01:
				break;
			// track
			case 0x0d:
				measurements = readTrackData(message);
				break;
			// track meta data
			case 0x0e:
				readTrackMetadata(message);
				break;
			// route
			case 0x21:
				break;
			// route
			case 0x22:
				break;
			default:
				break;
			}
		}
		return measurements;
	}
		private void readTrackMetadata(byte[] message) {
		
	}



	private List<Measurement> readTrackData(byte[] data) {
		List<Measurement> results = new ArrayList<Measurement>();
		
		int trackpointsCount = (data[5] & 0xFF) << 8;
		trackpointsCount |= (data[4] & 0xFF) ;
		
//		for(int j = 0 ; j < trackpointsCount ; j++) {
			for(int i = 8 ; i < data.length; i+= 14) {
				byte[] debugArray = Arrays.copyOfRange(data , i + 8, i + 14);
				long lat = (long)(data[i + 3] & 0xFF) << 24;
				lat |= (long)(data[i + 2] & 0xFF) << 16;
				lat |= (long)(data[i + 1] & 0xFF) << 8;
				lat |= (long)(data[i + 0] & 0xFF) ;
				
				double latitude = phiTterateMercator(lat / 107.1709342) * 180 / Math.PI;
				
				long lon = (long)(data[i + 7] & 0xFF) << 24;
				lon |= (long)(data[i + 6] & 0xFF) << 16;
				lon |= (long)(data[i + 5] & 0xFF) << 8;
				lon |= (long)(data[i + 4] & 0xFF) ;
				double longitude = (((double)lon) / Integer.MAX_VALUE) * 180.0;
				
				int depth = (data[i + 11] & 0xFF) << 8;
				depth |= (data[i + 10] & 0xFF) ;
				
				MeasuredPosition3D position3d = GeoFactory.eINSTANCE.createMeasuredPosition3D();
				Latitude latitude2 = GeoFactory.eINSTANCE.createLatitude();
				latitude2.setDecimalDegree(latitude);
				position3d.setLatitude(latitude2);
				Longitude longitude2 = GeoFactory.eINSTANCE.createLongitude();
				longitude2.setDecimalDegree(longitude);
				position3d.setLongitude(longitude2);
				position3d.setValid(true);
				
//				System.out.println(latitude + ":" + longitude + ":" + depth );
				
				Depth depth2 = GeoFactory.eINSTANCE.createDepth();
				depth2.setDepth(((double)depth) / 100);
				depth2.setValid(true);
				
				results.add(position3d);
				results.add(depth2);
			}
//		}
		return results;
	}
	
	
	private double phiReverseMercator(double N, double phi0)
	{
	  double esin = 0.08181919 * Math.sin(phi0);
	  return Math.PI / 2 - 2.0 * Math.atan(Math.exp(-N / 6378137) * Math.pow((1 - esin) / (1 + esin), 0.08181919 / 2));
	}

	private double phiTterateMercator(double N)
	{
	  double phi, phi0;
	  int i;

	  for (phi = 0, phi0 = Math.PI, i = 0; Math.abs(phi - phi0) > 1.5E-8 && i < 32; i++)
	  {
	     phi0 = phi;
	     phi = phiReverseMercator(N, phi0);
	  }
	  return phi;
	}

}
