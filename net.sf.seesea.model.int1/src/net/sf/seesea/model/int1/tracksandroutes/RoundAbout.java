/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Round About</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.RoundAbout#getAttachedSeaWays <em>Attached Sea Ways</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getRoundAbout()
 * @model
 * @generated
 */
public interface RoundAbout extends AbstractSeaWayArtefact {
	/**
	 * Returns the value of the '<em><b>Attached Sea Ways</b></em>' reference list.
	 * The list contents are of type {@link net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWayArtefact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attached Sea Ways</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attached Sea Ways</em>' reference list.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getRoundAbout_AttachedSeaWays()
	 * @model
	 * @generated
	 */
	EList<AbstractSeaWayArtefact> getAttachedSeaWays();

} // RoundAbout
