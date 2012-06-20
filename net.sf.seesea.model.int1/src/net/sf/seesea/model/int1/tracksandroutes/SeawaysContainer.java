/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes;

import net.sf.seesea.model.core.ModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Seaways Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer#getSeaways <em>Seaways</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getSeawaysContainer()
 * @model
 * @generated
 */
public interface SeawaysContainer extends ModelObject {
	/**
	 * Returns the value of the '<em><b>Seaways</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seaways</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seaways</em>' containment reference.
	 * @see #setSeaways(AbstractSeaWayArtefact)
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getSeawaysContainer_Seaways()
	 * @model containment="true"
	 * @generated
	 */
	AbstractSeaWayArtefact getSeaways();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer#getSeaways <em>Seaways</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seaways</em>' containment reference.
	 * @see #getSeaways()
	 * @generated
	 */
	void setSeaways(AbstractSeaWayArtefact value);

} // SeawaysContainer
