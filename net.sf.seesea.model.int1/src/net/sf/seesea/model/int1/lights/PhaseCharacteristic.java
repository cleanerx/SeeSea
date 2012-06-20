/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.lights;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Phase Characteristic</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.lights.LightsPackage#getPhaseCharacteristic()
 * @model
 * @generated
 */
public enum PhaseCharacteristic implements Enumerator {
	/**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	UNKNOWN(0, "Unknown", "UNKNOWN"), /**
	 * The '<em><b>Fixed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIXED_VALUE
	 * @generated
	 * @ordered
	 */
	FIXED(1, "Fixed", "Fixed"),

	/**
	 * The '<em><b>Occulting</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OCCULTING_VALUE
	 * @generated
	 * @ordered
	 */
	OCCULTING(2, "Occulting", "Occulting"),

	/**
	 * The '<em><b>Isophase</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISOPHASE_VALUE
	 * @generated
	 * @ordered
	 */
	ISOPHASE(3, "Isophase", "Isophase"),

	/**
	 * The '<em><b>Flash</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FLASH_VALUE
	 * @generated
	 * @ordered
	 */
	FLASH(4, "Flash", "Flash"),

	/**
	 * The '<em><b>Long Flash</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LONG_FLASH_VALUE
	 * @generated
	 * @ordered
	 */
	LONG_FLASH(5, "LongFlash", "LongFlash"),

	/**
	 * The '<em><b>Quick</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #QUICK_VALUE
	 * @generated
	 * @ordered
	 */
	QUICK(6, "Quick", "Quick"),

	/**
	 * The '<em><b>Interrupted Quick</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTERRUPTED_QUICK_VALUE
	 * @generated
	 * @ordered
	 */
	INTERRUPTED_QUICK(7, "InterruptedQuick", "InterruptedQuick"),

	/**
	 * The '<em><b>Very Quick</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VERY_QUICK_VALUE
	 * @generated
	 * @ordered
	 */
	VERY_QUICK(8, "VeryQuick", "VeryQuick"),

	/**
	 * The '<em><b>Interrupted Very Quick</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTERRUPTED_VERY_QUICK_VALUE
	 * @generated
	 * @ordered
	 */
	INTERRUPTED_VERY_QUICK(9, "InterruptedVeryQuick", ""),

	/**
	 * The '<em><b>Ultra Quick</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ULTRA_QUICK_VALUE
	 * @generated
	 * @ordered
	 */
	ULTRA_QUICK(10, "UltraQuick", "UltraQuick"),

	/**
	 * The '<em><b>Interrupted Ultra Quick</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTERRUPTED_ULTRA_QUICK_VALUE
	 * @generated
	 * @ordered
	 */
	INTERRUPTED_ULTRA_QUICK(11, "InterruptedUltraQuick", "InterruptedUltraQuick");

	/**
	 * The '<em><b>Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @model name="Unknown" literal="UNKNOWN"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN_VALUE = 0;

	/**
	 * The '<em><b>Fixed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Fixed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FIXED
	 * @model name="Fixed"
	 * @generated
	 * @ordered
	 */
	public static final int FIXED_VALUE = 1;

	/**
	 * The '<em><b>Occulting</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Occulting</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OCCULTING
	 * @model name="Occulting"
	 * @generated
	 * @ordered
	 */
	public static final int OCCULTING_VALUE = 2;

	/**
	 * The '<em><b>Isophase</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Isophase</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ISOPHASE
	 * @model name="Isophase"
	 * @generated
	 * @ordered
	 */
	public static final int ISOPHASE_VALUE = 3;

	/**
	 * The '<em><b>Flash</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Flash</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FLASH
	 * @model name="Flash"
	 * @generated
	 * @ordered
	 */
	public static final int FLASH_VALUE = 4;

	/**
	 * The '<em><b>Long Flash</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Long Flash</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LONG_FLASH
	 * @model name="LongFlash"
	 * @generated
	 * @ordered
	 */
	public static final int LONG_FLASH_VALUE = 5;

	/**
	 * The '<em><b>Quick</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Quick</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #QUICK
	 * @model name="Quick"
	 * @generated
	 * @ordered
	 */
	public static final int QUICK_VALUE = 6;

	/**
	 * The '<em><b>Interrupted Quick</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Interrupted Quick</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTERRUPTED_QUICK
	 * @model name="InterruptedQuick"
	 * @generated
	 * @ordered
	 */
	public static final int INTERRUPTED_QUICK_VALUE = 7;

	/**
	 * The '<em><b>Very Quick</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Very Quick</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VERY_QUICK
	 * @model name="VeryQuick"
	 * @generated
	 * @ordered
	 */
	public static final int VERY_QUICK_VALUE = 8;

	/**
	 * The '<em><b>Interrupted Very Quick</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Interrupted Very Quick</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTERRUPTED_VERY_QUICK
	 * @model name="InterruptedVeryQuick" literal=""
	 * @generated
	 * @ordered
	 */
	public static final int INTERRUPTED_VERY_QUICK_VALUE = 9;

	/**
	 * The '<em><b>Ultra Quick</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Ultra Quick</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ULTRA_QUICK
	 * @model name="UltraQuick"
	 * @generated
	 * @ordered
	 */
	public static final int ULTRA_QUICK_VALUE = 10;

	/**
	 * The '<em><b>Interrupted Ultra Quick</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Interrupted Ultra Quick</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTERRUPTED_ULTRA_QUICK
	 * @model name="InterruptedUltraQuick"
	 * @generated
	 * @ordered
	 */
	public static final int INTERRUPTED_ULTRA_QUICK_VALUE = 11;

	/**
	 * An array of all the '<em><b>Phase Characteristic</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PhaseCharacteristic[] VALUES_ARRAY =
		new PhaseCharacteristic[] {
			UNKNOWN,
			FIXED,
			OCCULTING,
			ISOPHASE,
			FLASH,
			LONG_FLASH,
			QUICK,
			INTERRUPTED_QUICK,
			VERY_QUICK,
			INTERRUPTED_VERY_QUICK,
			ULTRA_QUICK,
			INTERRUPTED_ULTRA_QUICK,
		};

	/**
	 * A public read-only list of all the '<em><b>Phase Characteristic</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PhaseCharacteristic> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Phase Characteristic</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PhaseCharacteristic get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PhaseCharacteristic result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Phase Characteristic</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PhaseCharacteristic getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PhaseCharacteristic result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Phase Characteristic</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PhaseCharacteristic get(int value) {
		switch (value) {
			case UNKNOWN_VALUE: return UNKNOWN;
			case FIXED_VALUE: return FIXED;
			case OCCULTING_VALUE: return OCCULTING;
			case ISOPHASE_VALUE: return ISOPHASE;
			case FLASH_VALUE: return FLASH;
			case LONG_FLASH_VALUE: return LONG_FLASH;
			case QUICK_VALUE: return QUICK;
			case INTERRUPTED_QUICK_VALUE: return INTERRUPTED_QUICK;
			case VERY_QUICK_VALUE: return VERY_QUICK;
			case INTERRUPTED_VERY_QUICK_VALUE: return INTERRUPTED_VERY_QUICK;
			case ULTRA_QUICK_VALUE: return ULTRA_QUICK;
			case INTERRUPTED_ULTRA_QUICK_VALUE: return INTERRUPTED_ULTRA_QUICK;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PhaseCharacteristic(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //PhaseCharacteristic
