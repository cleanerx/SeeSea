package net.sf.seesea.lib;

public interface IFeedbackMessageConsumer {
	
	void noProviderAvailable();

	void processingStopped();
	
	void timeout(); 
}
