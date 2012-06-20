/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons;

import net.sf.seesea.model.core.geo.NamedPosition;

import net.sf.seesea.model.int1.base.AbstractSeamark;

import net.sf.seesea.model.int1.lights.Color;
import net.sf.seesea.model.int1.lights.LightCharacter;
import net.sf.seesea.model.int1.lights.Orientation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Common Buoy Beacon</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getPosition <em>Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getColor <em>Color</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getColorType <em>Color Type</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#isReflecting <em>Reflecting</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#isRadarreflector <em>Radarreflector</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getType <em>Type</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getTopmark <em>Topmark</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getAbstractCommonBuoyBeacon()
 * @model abstract="true"
 * @generated
 */
public interface AbstractCommonBuoyBeacon extends LightCharacter, AbstractSeamark, NamedPosition {
	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(String)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getAbstractCommonBuoyBeacon_Position()
	 * @model
	 * @generated
	 */
	String getPosition();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(String value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute list.
	 * The list contents are of type {@link net.sf.seesea.model.int1.lights.Color}.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.lights.Color}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute list.
	 * @see net.sf.seesea.model.int1.lights.Color
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getAbstractCommonBuoyBeacon_Color()
	 * @model unique="false"
	 * @generated
	 */
	EList<Color> getColor();

	/**
	 * Returns the value of the '<em><b>Color Type</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.lights.Orientation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color Type</em>' attribute.
	 * @see net.sf.seesea.model.int1.lights.Orientation
	 * @see #setColorType(Orientation)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getAbstractCommonBuoyBeacon_ColorType()
	 * @model
	 * @generated
	 */
	Orientation getColorType();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getColorType <em>Color Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color Type</em>' attribute.
	 * @see net.sf.seesea.model.int1.lights.Orientation
	 * @see #getColorType()
	 * @generated
	 */
	void setColorType(Orientation value);

	/**
	 * Returns the value of the '<em><b>Reflecting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reflecting</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reflecting</em>' attribute.
	 * @see #setReflecting(boolean)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getAbstractCommonBuoyBeacon_Reflecting()
	 * @model
	 * @generated
	 */
	boolean isReflecting();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#isReflecting <em>Reflecting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reflecting</em>' attribute.
	 * @see #isReflecting()
	 * @generated
	 */
	void setReflecting(boolean value);

	/**
	 * Returns the value of the '<em><b>Topmark</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topmark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topmark</em>' containment reference.
	 * @see #setTopmark(Topmark)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getAbstractCommonBuoyBeacon_Topmark()
	 * @model containment="true"
	 * @generated
	 */
	Topmark getTopmark();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getTopmark <em>Topmark</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topmark</em>' containment reference.
	 * @see #getTopmark()
	 * @generated
	 */
	void setTopmark(Topmark value);

	/**
	 * Returns the value of the '<em><b>Radarreflector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Radarreflector</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Radarreflector</em>' attribute.
	 * @see #setRadarreflector(boolean)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getAbstractCommonBuoyBeacon_Radarreflector()
	 * @model
	 * @generated
	 */
	boolean isRadarreflector();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#isRadarreflector <em>Radarreflector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Radarreflector</em>' attribute.
	 * @see #isRadarreflector()
	 * @generated
	 */
	void setRadarreflector(boolean value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.buoysandbeacons.BuoyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoyType
	 * @see #setType(BuoyType)
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getAbstractCommonBuoyBeacon_Type()
	 * @model
	 * @generated
	 */
	BuoyType getType();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoyType
	 * @see #getType()
	 * @generated
	 */
	void setType(BuoyType value);

} // AbstractCommonBuoyBeacon
