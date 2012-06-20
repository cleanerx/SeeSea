/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.lights;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.lights.LightsFactory
 * @model kind="package"
 * @generated
 */
public interface LightsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "lights";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "lights";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "lights";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LightsPackage eINSTANCE = net.sf.seesea.model.int1.lights.impl.LightsPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.lights.impl.LightCharacterImpl <em>Light Character</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.lights.impl.LightCharacterImpl
	 * @see net.sf.seesea.model.int1.lights.impl.LightsPackageImpl#getLightCharacter()
	 * @generated
	 */
	int LIGHT_CHARACTER = 0;

	/**
	 * The feature id for the '<em><b>Lightcolor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIGHT_CHARACTER__LIGHTCOLOR = 0;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIGHT_CHARACTER__PERIOD = 1;

	/**
	 * The feature id for the '<em><b>Phase Characteristic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIGHT_CHARACTER__PHASE_CHARACTERISTIC = 2;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIGHT_CHARACTER__GROUP = 3;

	/**
	 * The number of structural features of the '<em>Light Character</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIGHT_CHARACTER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.lights.PhaseCharacteristic <em>Phase Characteristic</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.lights.PhaseCharacteristic
	 * @see net.sf.seesea.model.int1.lights.impl.LightsPackageImpl#getPhaseCharacteristic()
	 * @generated
	 */
	int PHASE_CHARACTERISTIC = 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.lights.Color <em>Color</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.lights.Color
	 * @see net.sf.seesea.model.int1.lights.impl.LightsPackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.lights.Orientation <em>Orientation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.lights.Orientation
	 * @see net.sf.seesea.model.int1.lights.impl.LightsPackageImpl#getOrientation()
	 * @generated
	 */
	int ORIENTATION = 3;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.lights.LightCharacter <em>Light Character</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Light Character</em>'.
	 * @see net.sf.seesea.model.int1.lights.LightCharacter
	 * @generated
	 */
	EClass getLightCharacter();

	/**
	 * Returns the meta object for the attribute list '{@link net.sf.seesea.model.int1.lights.LightCharacter#getLightcolor <em>Lightcolor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Lightcolor</em>'.
	 * @see net.sf.seesea.model.int1.lights.LightCharacter#getLightcolor()
	 * @see #getLightCharacter()
	 * @generated
	 */
	EAttribute getLightCharacter_Lightcolor();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.lights.LightCharacter#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see net.sf.seesea.model.int1.lights.LightCharacter#getPeriod()
	 * @see #getLightCharacter()
	 * @generated
	 */
	EAttribute getLightCharacter_Period();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.lights.LightCharacter#getPhaseCharacteristic <em>Phase Characteristic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase Characteristic</em>'.
	 * @see net.sf.seesea.model.int1.lights.LightCharacter#getPhaseCharacteristic()
	 * @see #getLightCharacter()
	 * @generated
	 */
	EAttribute getLightCharacter_PhaseCharacteristic();

	/**
	 * Returns the meta object for the attribute list '{@link net.sf.seesea.model.int1.lights.LightCharacter#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see net.sf.seesea.model.int1.lights.LightCharacter#getGroup()
	 * @see #getLightCharacter()
	 * @generated
	 */
	EAttribute getLightCharacter_Group();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.lights.PhaseCharacteristic <em>Phase Characteristic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Phase Characteristic</em>'.
	 * @see net.sf.seesea.model.int1.lights.PhaseCharacteristic
	 * @generated
	 */
	EEnum getPhaseCharacteristic();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.lights.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Color</em>'.
	 * @see net.sf.seesea.model.int1.lights.Color
	 * @generated
	 */
	EEnum getColor();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.lights.Orientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation</em>'.
	 * @see net.sf.seesea.model.int1.lights.Orientation
	 * @generated
	 */
	EEnum getOrientation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LightsFactory getLightsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.lights.impl.LightCharacterImpl <em>Light Character</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.lights.impl.LightCharacterImpl
		 * @see net.sf.seesea.model.int1.lights.impl.LightsPackageImpl#getLightCharacter()
		 * @generated
		 */
		EClass LIGHT_CHARACTER = eINSTANCE.getLightCharacter();

		/**
		 * The meta object literal for the '<em><b>Lightcolor</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIGHT_CHARACTER__LIGHTCOLOR = eINSTANCE.getLightCharacter_Lightcolor();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIGHT_CHARACTER__PERIOD = eINSTANCE.getLightCharacter_Period();

		/**
		 * The meta object literal for the '<em><b>Phase Characteristic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIGHT_CHARACTER__PHASE_CHARACTERISTIC = eINSTANCE.getLightCharacter_PhaseCharacteristic();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIGHT_CHARACTER__GROUP = eINSTANCE.getLightCharacter_Group();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.lights.PhaseCharacteristic <em>Phase Characteristic</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.lights.PhaseCharacteristic
		 * @see net.sf.seesea.model.int1.lights.impl.LightsPackageImpl#getPhaseCharacteristic()
		 * @generated
		 */
		EEnum PHASE_CHARACTERISTIC = eINSTANCE.getPhaseCharacteristic();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.lights.Color <em>Color</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.lights.Color
		 * @see net.sf.seesea.model.int1.lights.impl.LightsPackageImpl#getColor()
		 * @generated
		 */
		EEnum COLOR = eINSTANCE.getColor();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.lights.Orientation <em>Orientation</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.lights.Orientation
		 * @see net.sf.seesea.model.int1.lights.impl.LightsPackageImpl#getOrientation()
		 * @generated
		 */
		EEnum ORIENTATION = eINSTANCE.getOrientation();

	}

} //LightsPackage
