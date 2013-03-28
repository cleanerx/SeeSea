/**
 * 
 Copyright (c) 2010, Jens Kübler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL Jens Kübler BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.provider.navigation.nmea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.nmea.v183.NMEA0183Reader;
import net.sf.seesea.services.navigation.INMEAReader;
import net.sf.seesea.services.navigation.RawDataEventListener;
import net.sf.seesea.services.navigation.RawDataEvent;
import net.sf.seesea.services.navigation.provider.IPositionProvider;
import net.sf.seesea.services.navigation.provider.IShipMovementVectorProvider;
import net.sf.seesea.services.navigation.provider.IWaterTemperatureDataProvider;
import net.sf.seesea.services.navigation.provider.IWindDataProvider;

public class NMEA0183EventProcessor extends NMEAParser implements RawDataEventListener,
		IPositionProvider, IWaterTemperatureDataProvider, IWindDataProvider,
		IShipMovementVectorProvider {

	private List<INMEAReader> _nmeaReaders = Collections
			.synchronizedList(new ArrayList<INMEAReader>(1));

	private NMEA0183Reader nmea0183Reader;

	/**
	 * 
	 */
	public NMEA0183EventProcessor() {
		nmea0183Reader = new NMEA0183Reader();
	}

	public synchronized void bindReader(INMEAReader reader) {
		_nmeaReaders.add(reader);
		reader.addNMEAEventListener(this);
	}

	public synchronized void unbindReader(INMEAReader reader) {
		if (_nmeaReaders.size() == 1) {
			heartbeatThread.interrupt();
		}
		reader.removeNMEAEventListener(this);
		_nmeaReaders.remove(reader);
	}

	/**
	 * 
	 */
	@Override
	public void receiveRawDataEvent(RawDataEvent e) {
		List<Measurement> measurements = nmea0183Reader
				.extractMeasurementsFromNMEA(e.getNmeaMessageContent(),
						new ArrayList<Measurement>(1));
		try {
			for (Measurement measurement : measurements) {
				notifyListeners(measurement);
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error("Failed to notify a listener", ex);
		}
	}

	@Override
	public void disable() {
		heartbeatThread.interrupt();
	}

	@Override
	protected String getProviderName() {
		return "NMEA0183"; //$NON-NLS-1$
	}

}
