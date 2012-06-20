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
 * A representation of the literals of the enumeration '<em><b>Buoy Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#getBuoyType()
 * @model
 * @generated
 */
public enum BuoyType implements Enumerator {
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
	 * The '<em><b>Cardinal South</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDINAL_SOUTH_VALUE
	 * @generated
	 * @ordered
	 */
	CARDINAL_SOUTH(1, "CardinalSouth", "CardinalSouth"),

	/**
	 * The '<em><b>Cardinal East</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDINAL_EAST_VALUE
	 * @generated
	 * @ordered
	 */
	CARDINAL_EAST(2, "CardinalEast", "CardinalEast"),

	/**
	 * The '<em><b>Cardinal West</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDINAL_WEST_VALUE
	 * @generated
	 * @ordered
	 */
	CARDINAL_WEST(3, "CardinalWest", "CardinalWest"),

	/**
	 * The '<em><b>Lateral Square</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LATERAL_SQUARE_VALUE
	 * @generated
	 * @ordered
	 */
	LATERAL_SQUARE(4, "LateralSquare", "LateralSquare"),

	/**
	 * The '<em><b>Lateral Triangle</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LATERAL_TRIANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	LATERAL_TRIANGLE(5, "LateralTriangle", "LateralTriangle"),

	/**
	 * The '<em><b>Safe Water</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SAFE_WATER_VALUE
	 * @generated
	 * @ordered
	 */
	SAFE_WATER(6, "SafeWater", "SafeWater"),

	/**
	 * The '<em><b>Isolated Danger</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISOLATED_DANGER_VALUE
	 * @generated
	 * @ordered
	 */
	ISOLATED_DANGER(7, "IsolatedDanger", "IsolatedDanger"),

	/**
	 * The '<em><b>Special</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPECIAL_VALUE
	 * @generated
	 * @ordered
	 */
	SPECIAL(8, "Special", "Special"),

	/**
	 * The '<em><b>New Wreck</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEW_WRECK_VALUE
	 * @generated
	 * @ordered
	 */
	NEW_WRECK(9, "NewWreck", "NewWreck"),

	/**
	 * The '<em><b>Cardinal North</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDINAL_NORTH_VALUE
	 * @generated
	 * @ordered
	 */
	CARDINAL_NORTH(10, "CardinalNorth", "CardinalNorth");

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
	 * The '<em><b>Cardinal South</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cardinal South</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CARDINAL_SOUTH
	 * @model name="CardinalSouth"
	 * @generated
	 * @ordered
	 */
	public static final int CARDINAL_SOUTH_VALUE = 1;

	/**
	 * The '<em><b>Cardinal East</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cardinal East</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CARDINAL_EAST
	 * @model name="CardinalEast"
	 * @generated
	 * @ordered
	 */
	public static final int CARDINAL_EAST_VALUE = 2;

	/**
	 * The '<em><b>Cardinal West</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cardinal West</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CARDINAL_WEST
	 * @model name="CardinalWest"
	 * @generated
	 * @ordered
	 */
	public static final int CARDINAL_WEST_VALUE = 3;

	/**
	 * The '<em><b>Lateral Square</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Lateral Square</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LATERAL_SQUARE
	 * @model name="LateralSquare"
	 * @generated
	 * @ordered
	 */
	public static final int LATERAL_SQUARE_VALUE = 4;

	/**
	 * The '<em><b>Lateral Triangle</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Lateral Triangle</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LATERAL_TRIANGLE
	 * @model name="LateralTriangle"
	 * @generated
	 * @ordered
	 */
	public static final int LATERAL_TRIANGLE_VALUE = 5;

	/**
	 * The '<em><b>Safe Water</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Safe Water</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SAFE_WATER
	 * @model name="SafeWater"
	 * @generated
	 * @ordered
	 */
	public static final int SAFE_WATER_VALUE = 6;

	/**
	 * The '<em><b>Isolated Danger</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Isolated Danger</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ISOLATED_DANGER
	 * @model name="IsolatedDanger"
	 * @generated
	 * @ordered
	 */
	public static final int ISOLATED_DANGER_VALUE = 7;

	/**
	 * The '<em><b>Special</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Special</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SPECIAL
	 * @model name="Special"
	 * @generated
	 * @ordered
	 */
	public static final int SPECIAL_VALUE = 8;

	/**
	 * The '<em><b>New Wreck</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>New Wreck</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEW_WRECK
	 * @model name="NewWreck"
	 * @generated
	 * @ordered
	 */
	public static final int NEW_WRECK_VALUE = 9;

	/**
	 * The '<em><b>Cardinal North</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cardinal North</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CARDINAL_NORTH
	 * @model name="CardinalNorth"
	 * @generated
	 * @ordered
	 */
	public static final int CARDINAL_NORTH_VALUE = 10;

	/**
	 * An array of all the '<em><b>Buoy Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BuoyType[] VALUES_ARRAY =
		new BuoyType[] {
			UNKNOWN,
			CARDINAL_SOUTH,
			CARDINAL_EAST,
			CARDINAL_WEST,
			LATERAL_SQUARE,
			LATERAL_TRIANGLE,
			SAFE_WATER,
			ISOLATED_DANGER,
			SPECIAL,
			NEW_WRECK,
			CARDINAL_NORTH,
		};

	/**
	 * A public read-only list of all the '<em><b>Buoy Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<BuoyType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Buoy Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BuoyType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BuoyType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Buoy Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BuoyType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BuoyType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Buoy Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BuoyType get(int value) {
		switch (value) {
			case UNKNOWN_VALUE: return UNKNOWN;
			case CARDINAL_SOUTH_VALUE: return CARDINAL_SOUTH;
			case CARDINAL_EAST_VALUE: return CARDINAL_EAST;
			case CARDINAL_WEST_VALUE: return CARDINAL_WEST;
			case LATERAL_SQUARE_VALUE: return LATERAL_SQUARE;
			case LATERAL_TRIANGLE_VALUE: return LATERAL_TRIANGLE;
			case SAFE_WATER_VALUE: return SAFE_WATER;
			case ISOLATED_DANGER_VALUE: return ISOLATED_DANGER;
			case SPECIAL_VALUE: return SPECIAL;
			case NEW_WRECK_VALUE: return NEW_WRECK;
			case CARDINAL_NORTH_VALUE: return CARDINAL_NORTH;
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
	private BuoyType(int value, String name, String literal) {
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
	
} //BuoyType
