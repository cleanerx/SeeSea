/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Topmark Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getTopmarkType()
 * @model
 * @generated
 */
public enum TopmarkType implements Enumerator {
	/**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	UNKNOWN(0, "Unknown", "Unknown"),

	/**
	 * The '<em><b>Cylinder</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CYLINDER_VALUE
	 * @generated
	 * @ordered
	 */
	CYLINDER(1, "Cylinder", "Cylinder"),

	/**
	 * The '<em><b>Cone Up</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONE_UP_VALUE
	 * @generated
	 * @ordered
	 */
	CONE_UP(2, "ConeUp", "ConeUp"),

	/**
	 * The '<em><b>Cone Down</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONE_DOWN_VALUE
	 * @generated
	 * @ordered
	 */
	CONE_DOWN(3, "ConeDown", "ConeDown"),

	/**
	 * The '<em><b>XCross</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XCROSS_VALUE
	 * @generated
	 * @ordered
	 */
	XCROSS(4, "XCross", "XCross"), /**
	 * The '<em><b>Ball</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BALL_VALUE
	 * @generated
	 * @ordered
	 */
	BALL(5, "Ball", "Ball"), /**
	 * The '<em><b>Upright Cross</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPRIGHT_CROSS_VALUE
	 * @generated
	 * @ordered
	 */
	UPRIGHT_CROSS(6, "UprightCross", "UprightCross"), /**
	 * The '<em><b>Rhombus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RHOMBUS_VALUE
	 * @generated
	 * @ordered
	 */
	RHOMBUS(7, "Rhombus", "Rhombus"), /**
	 * The '<em><b>Flag</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FLAG_VALUE
	 * @generated
	 * @ordered
	 */
	FLAG(8, "Flag", "Flag");

	/**
	 * The '<em><b>Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @model name="Unknown"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN_VALUE = 0;

	/**
	 * The '<em><b>Cylinder</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cylinder</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CYLINDER
	 * @model name="Cylinder"
	 * @generated
	 * @ordered
	 */
	public static final int CYLINDER_VALUE = 1;

	/**
	 * The '<em><b>Cone Up</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cone Up</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONE_UP
	 * @model name="ConeUp"
	 * @generated
	 * @ordered
	 */
	public static final int CONE_UP_VALUE = 2;

	/**
	 * The '<em><b>Cone Down</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cone Down</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONE_DOWN
	 * @model name="ConeDown"
	 * @generated
	 * @ordered
	 */
	public static final int CONE_DOWN_VALUE = 3;

	/**
	 * The '<em><b>XCross</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XCross</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XCROSS
	 * @model name="XCross"
	 * @generated
	 * @ordered
	 */
	public static final int XCROSS_VALUE = 4;

	/**
	 * The '<em><b>Ball</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Ball</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BALL
	 * @model name="Ball"
	 * @generated
	 * @ordered
	 */
	public static final int BALL_VALUE = 5;

	/**
	 * The '<em><b>Upright Cross</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Upright Cross</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPRIGHT_CROSS
	 * @model name="UprightCross"
	 * @generated
	 * @ordered
	 */
	public static final int UPRIGHT_CROSS_VALUE = 6;

	/**
	 * The '<em><b>Rhombus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Rhombus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RHOMBUS
	 * @model name="Rhombus"
	 * @generated
	 * @ordered
	 */
	public static final int RHOMBUS_VALUE = 7;

	/**
	 * The '<em><b>Flag</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Flag</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FLAG
	 * @model name="Flag"
	 * @generated
	 * @ordered
	 */
	public static final int FLAG_VALUE = 8;

	/**
	 * An array of all the '<em><b>Topmark Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TopmarkType[] VALUES_ARRAY =
		new TopmarkType[] {
			UNKNOWN,
			CYLINDER,
			CONE_UP,
			CONE_DOWN,
			XCROSS,
			BALL,
			UPRIGHT_CROSS,
			RHOMBUS,
			FLAG,
		};

	/**
	 * A public read-only list of all the '<em><b>Topmark Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TopmarkType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Topmark Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TopmarkType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TopmarkType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Topmark Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TopmarkType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TopmarkType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Topmark Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TopmarkType get(int value) {
		switch (value) {
			case UNKNOWN_VALUE: return UNKNOWN;
			case CYLINDER_VALUE: return CYLINDER;
			case CONE_UP_VALUE: return CONE_UP;
			case CONE_DOWN_VALUE: return CONE_DOWN;
			case XCROSS_VALUE: return XCROSS;
			case BALL_VALUE: return BALL;
			case UPRIGHT_CROSS_VALUE: return UPRIGHT_CROSS;
			case RHOMBUS_VALUE: return RHOMBUS;
			case FLAG_VALUE: return FLAG;
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
	private TopmarkType(int value, String name, String literal) {
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
	
} //TopmarkType
