package net.sf.seesea.services.navigation.listener;

public interface IDataProviderNotification {

	/**
	 * 
	 * @param providerID
	 */
	public abstract void providerEnabled(String providerID);

	/**
	 * 
	 * @param providerID
	 */
	public abstract void providerDisabled(String providerID);

}