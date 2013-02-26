package nl.esi.metis.aisparser;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class AisMessageMultiplexer implements HandleAISMessage {
	
	private AISTracker aisTracker;

	public AisMessageMultiplexer() {
		aisTracker = new AISTracker(AISActivator.getDefault().getBundle().getBundleContext());
		aisTracker.open();
	}
	
	
	@Override
	public void handleAISMessage(AISMessage message) {
		Object[] services = aisTracker.getServices();
		if(services != null) {
			for (Object object : services) {
				if(object instanceof HandleAISMessage) {
					HandleAISMessage handleAISMessage = (HandleAISMessage) object;
					handleAISMessage.handleAISMessage(message);
				}
			}
		}

	}

	private class AISTracker extends ServiceTracker<HandleAISMessage,HandleAISMessage> {

		public AISTracker(BundleContext context) {
			super(context, HandleAISMessage.class, null);
		}
		
	}
}
