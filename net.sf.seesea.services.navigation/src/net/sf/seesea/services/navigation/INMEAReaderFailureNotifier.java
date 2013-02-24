package net.sf.seesea.services.navigation;

import net.sf.seesea.services.navigation.provider.INMEAStreamProvider;

public interface INMEAReaderFailureNotifier {
	
	void notify(INMEAStreamProvider streamProvider, Throwable thorwable);

}
