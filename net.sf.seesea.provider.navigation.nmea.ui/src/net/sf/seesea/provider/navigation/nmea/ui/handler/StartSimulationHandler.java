package net.sf.seesea.provider.navigation.nmea.ui.handler;

import net.sf.seesea.provider.navigation.nmea.NMEA0183TrackSimulator;
import net.sf.seesea.provider.navigation.nmea.ui.NMEAUIActivator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.osgi.framework.ServiceRegistration;

public class StartSimulationHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		NMEA0183TrackSimulator nmea0183TrackSimulator = new NMEA0183TrackSimulator(null);
		Thread thread = new Thread(nmea0183TrackSimulator);
		thread.start();
//		ServiceRegistration<NMEA0183TrackSimulator> registerService = NMEAUIActivator.getDefault().getBundle().getBundleContext().registerService(NMEA0183TrackSimulator.class, nmea0183TrackSimulator, null);
		
		return null;
	}

}
