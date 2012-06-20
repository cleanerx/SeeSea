/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Beacon</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.Beacon#isOnSubmergedRock <em>On Submerged Rock</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getBeacon()
 * @model
 * @generated
 */
public interface Beacon extends AbstractCommonBuoyBeacon {
	/**
	 * Returns the value of the '<em><b>On Submerged Rock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Submerged Rock</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Submerged Rock</em>' attribute.
	 * @see #setOnSubmergedRock(boolean)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getBeacon_OnSubmergedRock()
	 * @model
	 * @generated
	 */
	boolean isOnSubmergedRock();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.Beacon#isOnSubmergedRock <em>On Submerged Rock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Submerged Rock</em>' attribute.
	 * @see #isOnSubmergedRock()
	 * @generated
	 */
	void setOnSubmergedRock(boolean value);

} // Beacon
