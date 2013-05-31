package net.sf.seesea.services.navigation;

public interface IFeedbackMessageConsumer {
	
	void noProviderAvailable();

	void processingStopped();
	
	void timeout(); 
}
