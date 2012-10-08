package net.sf.seesea.services.navigation.listener;

import net.sf.seesea.model.core.physx.Distance;

/**
 * listener that sends event from the log since its last reset (which usually is a single trip)
 * @author jens
 *
 */
public interface ITripLogListener extends IDataListener<Distance> {

}
