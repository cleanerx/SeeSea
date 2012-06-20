/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Special Buoy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#getPurpose <em>Purpose</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#isPrivate <em>Private</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#getSeasonal <em>Seasonal</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getSpecialBuoy()
 * @model
 * @generated
 */
public interface SpecialBuoy extends Buoy {
	/**
	 * Returns the value of the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Purpose</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Purpose</em>' attribute.
	 * @see #setPurpose(String)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getSpecialBuoy_Purpose()
	 * @model
	 * @generated
	 */
	String getPurpose();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#getPurpose <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Purpose</em>' attribute.
	 * @see #getPurpose()
	 * @generated
	 */
	void setPurpose(String value);

	/**
	 * Returns the value of the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Private</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Private</em>' attribute.
	 * @see #setPrivate(boolean)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getSpecialBuoy_Private()
	 * @model
	 * @generated
	 */
	boolean isPrivate();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#isPrivate <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Private</em>' attribute.
	 * @see #isPrivate()
	 * @generated
	 */
	void setPrivate(boolean value);

	/**
	 * Returns the value of the '<em><b>Seasonal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seasonal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seasonal</em>' attribute.
	 * @see #setSeasonal(String)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getSpecialBuoy_Seasonal()
	 * @model
	 * @generated
	 */
	String getSeasonal();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#getSeasonal <em>Seasonal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seasonal</em>' attribute.
	 * @see #getSeasonal()
	 * @generated
	 */
	void setSeasonal(String value);

} // SpecialBuoy
