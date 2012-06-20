/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons;

import net.sf.seesea.model.core.ModelObject;

import net.sf.seesea.model.int1.lights.Color;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Topmark</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.Topmark#getTopmarkParts <em>Topmark Parts</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.Topmark#getTopmarkColor <em>Topmark Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getTopmark()
 * @model
 * @generated
 */
public interface Topmark extends ModelObject {
	/**
	 * Returns the value of the '<em><b>Topmark Parts</b></em>' attribute list.
	 * The list contents are of type {@link net.sf.seesea.model.int1.buoysandbeacons.TopmarkType}.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.buoysandbeacons.TopmarkType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topmark Parts</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topmark Parts</em>' attribute list.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.TopmarkType
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getTopmark_TopmarkParts()
	 * @model
	 * @generated
	 */
	EList<TopmarkType> getTopmarkParts();

	/**
	 * Returns the value of the '<em><b>Topmark Color</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.lights.Color}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topmark Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topmark Color</em>' attribute.
	 * @see net.sf.seesea.model.int1.lights.Color
	 * @see #setTopmarkColor(Color)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getTopmark_TopmarkColor()
	 * @model
	 * @generated
	 */
	Color getTopmarkColor();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.Topmark#getTopmarkColor <em>Topmark Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topmark Color</em>' attribute.
	 * @see net.sf.seesea.model.int1.lights.Color
	 * @see #getTopmarkColor()
	 * @generated
	 */
	void setTopmarkColor(Color value);

} // Topmark
