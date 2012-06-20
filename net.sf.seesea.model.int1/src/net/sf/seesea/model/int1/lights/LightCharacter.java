/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.lights;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Light Character</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.lights.LightCharacter#getLightcolor <em>Lightcolor</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.lights.LightCharacter#getPeriod <em>Period</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.lights.LightCharacter#getPhaseCharacteristic <em>Phase Characteristic</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.lights.LightCharacter#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.lights.LightsPackage#getLightCharacter()
 * @model
 * @generated
 */
public interface LightCharacter extends EObject {
	/**
	 * Returns the value of the '<em><b>Lightcolor</b></em>' attribute list.
	 * The list contents are of type {@link net.sf.seesea.model.int1.lights.Color}.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.lights.Color}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lightcolor</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lightcolor</em>' attribute list.
	 * @see net.sf.seesea.model.int1.lights.Color
	 * @see net.sf.seesea.model.int1.lights.LightsPackage#getLightCharacter_Lightcolor()
	 * @model
	 * @generated
	 */
	EList<Color> getLightcolor();

	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Period</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see #setPeriod(int)
	 * @see net.sf.seesea.model.int1.lights.LightsPackage#getLightCharacter_Period()
	 * @model
	 * @generated
	 */
	int getPeriod();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.lights.LightCharacter#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(int value);

	/**
	 * Returns the value of the '<em><b>Phase Characteristic</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.lights.PhaseCharacteristic}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phase Characteristic</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Phase Characteristic</em>' attribute.
	 * @see net.sf.seesea.model.int1.lights.PhaseCharacteristic
	 * @see #setPhaseCharacteristic(PhaseCharacteristic)
	 * @see net.sf.seesea.model.int1.lights.LightsPackage#getLightCharacter_PhaseCharacteristic()
	 * @model
	 * @generated
	 */
	PhaseCharacteristic getPhaseCharacteristic();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.lights.LightCharacter#getPhaseCharacteristic <em>Phase Characteristic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase Characteristic</em>' attribute.
	 * @see net.sf.seesea.model.int1.lights.PhaseCharacteristic
	 * @see #getPhaseCharacteristic()
	 * @generated
	 */
	void setPhaseCharacteristic(PhaseCharacteristic value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see net.sf.seesea.model.int1.lights.LightsPackage#getLightCharacter_Group()
	 * @model
	 * @generated
	 */
	EList<Integer> getGroup();

} // LightCharacter
