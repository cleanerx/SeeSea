package net.sf.seesea.provider.navigation.nmea.v2000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.nmea.MeasurmentDispatcher;
import net.sf.seesea.services.navigation.provider.IPositionProvider;
import net.sf.seesea.services.navigation.provider.IShipMovementVectorProvider;
import net.sf.seesea.services.navigation.provider.IWaterTemperatureDataProvider;
import net.sf.seesea.services.navigation.provider.IWindDataProvider;

import org.apache.log4j.Logger;

/**
 * The main event processor for nmea 2000 events
 */
public class NMEA2000EventProcessor extends MeasurmentDispatcher implements INMEA2000Listener,
IPositionProvider, IWaterTemperatureDataProvider, IWindDataProvider,
IShipMovementVectorProvider {

	private List<INMEA2000Reader> _nmeaReaders  = Collections.synchronizedList(new ArrayList<INMEA2000Reader>(1));

	private NMEA2000Reader nmea2000Reader;

	@Override
	protected String getProviderName() {
		return "NMEA2000"; //$NON-NLS-1$
	}
	
	public NMEA2000EventProcessor() {
//		Set<Integer> pgns = new HashSet<Integer>();
//		pgns.add(129029);
//		pgns.add(128267);
//		pgns.add(130919);
//		pgns.add(128259);
		nmea2000Reader = new NMEA2000Reader();
	}
	
	@Override
	public void nmeaEventReceived(int[] data) {
		List<Measurement> measurements = nmea2000Reader.extractMeasurementsFromNMEA(data);
		try {
			for (Measurement measurement : measurements) {
				notifyListeners(measurement);
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error("Failed to notify a listener", ex); //$NON-NLS-1$
		}
	}

	public synchronized void bindReader(INMEA2000Reader reader) {
		_nmeaReaders.add(reader);
		reader.addNMEA2000Listener(this);
	}

	public synchronized void unbindReader(INMEA2000Reader reader) {
		if (_nmeaReaders.size() == 1) {
			heartbeatThread.interrupt();
		}
		reader.removeNMEA2000Listener(this);
		_nmeaReaders.remove(reader);
	}
	
}
