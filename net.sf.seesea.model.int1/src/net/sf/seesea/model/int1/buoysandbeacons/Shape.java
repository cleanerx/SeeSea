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
 * A representation of the literals of the enumeration '<em><b>Shape</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getShape()
 * @model
 * @generated
 */
public enum Shape implements Enumerator {
	/**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	UNKNOWN(0, "Unknown", "UNKNOWN"), /**
	 * The '<em><b>Conical</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONICAL_VALUE
	 * @generated
	 * @ordered
	 */
	CONICAL(1, "Conical", "Conical"),

	/**
	 * The '<em><b>Cylindrical</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CYLINDRICAL_VALUE
	 * @generated
	 * @ordered
	 */
	CYLINDRICAL(2, "Cylindrical", "Cylindrical"),

	/**
	 * The '<em><b>Spherical</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPHERICAL_VALUE
	 * @generated
	 * @ordered
	 */
	SPHERICAL(3, "Spherical", "Spherical"),

	/**
	 * The '<em><b>Pillar</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PILLAR_VALUE
	 * @generated
	 * @ordered
	 */
	PILLAR(4, "Pillar", "Pillar"),

	/**
	 * The '<em><b>Spar</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAR_VALUE
	 * @generated
	 * @ordered
	 */
	SPAR(5, "Spar", "Spar"),

	/**
	 * The '<em><b>Barrel</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BARREL_VALUE
	 * @generated
	 * @ordered
	 */
	BARREL(6, "Barrel", "Barrel"),

	/**
	 * The '<em><b>Super</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPER_VALUE
	 * @generated
	 * @ordered
	 */
	SUPER(7, "Super", "Super");

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
	 * The '<em><b>Conical</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Conical</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONICAL
	 * @model name="Conical"
	 * @generated
	 * @ordered
	 */
	public static final int CONICAL_VALUE = 1;

	/**
	 * The '<em><b>Cylindrical</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cylindrical</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CYLINDRICAL
	 * @model name="Cylindrical"
	 * @generated
	 * @ordered
	 */
	public static final int CYLINDRICAL_VALUE = 2;

	/**
	 * The '<em><b>Spherical</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Spherical</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SPHERICAL
	 * @model name="Spherical"
	 * @generated
	 * @ordered
	 */
	public static final int SPHERICAL_VALUE = 3;

	/**
	 * The '<em><b>Pillar</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Pillar</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PILLAR
	 * @model name="Pillar"
	 * @generated
	 * @ordered
	 */
	public static final int PILLAR_VALUE = 4;

	/**
	 * The '<em><b>Spar</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Spar</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SPAR
	 * @model name="Spar"
	 * @generated
	 * @ordered
	 */
	public static final int SPAR_VALUE = 5;

	/**
	 * The '<em><b>Barrel</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Barrel</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BARREL
	 * @model name="Barrel"
	 * @generated
	 * @ordered
	 */
	public static final int BARREL_VALUE = 6;

	/**
	 * The '<em><b>Super</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Super</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUPER
	 * @model name="Super"
	 * @generated
	 * @ordered
	 */
	public static final int SUPER_VALUE = 7;

	/**
	 * An array of all the '<em><b>Shape</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Shape[] VALUES_ARRAY =
		new Shape[] {
			UNKNOWN,
			CONICAL,
			CYLINDRICAL,
			SPHERICAL,
			PILLAR,
			SPAR,
			BARREL,
			SUPER,
		};

	/**
	 * A public read-only list of all the '<em><b>Shape</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Shape> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Shape</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Shape get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Shape result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Shape</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Shape getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Shape result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Shape</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Shape get(int value) {
		switch (value) {
			case UNKNOWN_VALUE: return UNKNOWN;
			case CONICAL_VALUE: return CONICAL;
			case CYLINDRICAL_VALUE: return CYLINDRICAL;
			case SPHERICAL_VALUE: return SPHERICAL;
			case PILLAR_VALUE: return PILLAR;
			case SPAR_VALUE: return SPAR;
			case BARREL_VALUE: return BARREL;
			case SUPER_VALUE: return SUPER;
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
	private Shape(int value, String name, String literal) {
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
	
} //Shape
