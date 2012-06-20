/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.base;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Chart Symbols</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.base.ChartSymbols#getSeamarks <em>Seamarks</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.ChartSymbols#isVisible <em>Visible</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.base.BasePackage#getChartSymbols()
 * @model
 * @generated
 */
public interface ChartSymbols extends EObject {
	/**
	 * Returns the value of the '<em><b>Seamarks</b></em>' containment reference list.
	 * The list contents are of type {@link net.sf.seesea.model.int1.base.AbstractSeamark}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seamarks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seamarks</em>' containment reference list.
	 * @see net.sf.seesea.model.int1.base.BasePackage#getChartSymbols_Seamarks()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractSeamark> getSeamarks();

	/**
	 * Returns the value of the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible</em>' attribute.
	 * @see #setVisible(boolean)
	 * @see net.sf.seesea.model.int1.base.BasePackage#getChartSymbols_Visible()
	 * @model
	 * @generated
	 */
	boolean isVisible();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.base.ChartSymbols#isVisible <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visible</em>' attribute.
	 * @see #isVisible()
	 * @generated
	 */
	void setVisible(boolean value);

} // ChartSymbols
