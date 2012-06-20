/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes;

import net.sf.seesea.model.core.geo.ChartArea;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Traffic Separation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation#getBorderType <em>Border Type</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation#isIsNaturallyDivided <em>Is Naturally Divided</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getTrafficSeparation()
 * @model
 * @generated
 */
public interface TrafficSeparation extends ChartArea, AbstractSeaWayArtefact {
	/**
	 * Returns the value of the '<em><b>Border Type</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.tracksandroutes.BorderType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Border Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Border Type</em>' attribute.
	 * @see net.sf.seesea.model.int1.tracksandroutes.BorderType
	 * @see #setBorderType(BorderType)
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getTrafficSeparation_BorderType()
	 * @model
	 * @generated
	 */
	BorderType getBorderType();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation#getBorderType <em>Border Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Border Type</em>' attribute.
	 * @see net.sf.seesea.model.int1.tracksandroutes.BorderType
	 * @see #getBorderType()
	 * @generated
	 */
	void setBorderType(BorderType value);

	/**
	 * Returns the value of the '<em><b>Is Naturally Divided</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Naturally Divided</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Naturally Divided</em>' attribute.
	 * @see #setIsNaturallyDivided(boolean)
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getTrafficSeparation_IsNaturallyDivided()
	 * @model
	 * @generated
	 */
	boolean isIsNaturallyDivided();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation#isIsNaturallyDivided <em>Is Naturally Divided</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Naturally Divided</em>' attribute.
	 * @see #isIsNaturallyDivided()
	 * @generated
	 */
	void setIsNaturallyDivided(boolean value);

} // TrafficSeparation
