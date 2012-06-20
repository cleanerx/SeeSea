/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Buoy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.Buoy#getDesignation <em>Designation</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.Buoy#getShape <em>Shape</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getBuoy()
 * @model
 * @generated
 */
public interface Buoy extends AbstractCommonBuoyBeacon {
	/**
	 * Returns the value of the '<em><b>Designation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Designation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Designation</em>' attribute.
	 * @see #setDesignation(String)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getBuoy_Designation()
	 * @model
	 * @generated
	 */
	String getDesignation();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.Buoy#getDesignation <em>Designation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Designation</em>' attribute.
	 * @see #getDesignation()
	 * @generated
	 */
	void setDesignation(String value);

	/**
	 * Returns the value of the '<em><b>Shape</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.buoysandbeacons.Shape}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shape</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shape</em>' attribute.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Shape
	 * @see #setShape(Shape)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getBuoy_Shape()
	 * @model
	 * @generated
	 */
	Shape getShape();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.Buoy#getShape <em>Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shape</em>' attribute.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Shape
	 * @see #getShape()
	 * @generated
	 */
	void setShape(Shape value);

} // Buoy
