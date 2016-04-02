/**
 * <copyright>
Copyright (c) 2010-2012, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.core.geo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Direction</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.geo.GeoPackage#getDirection()
 * @model
 * @generated not
 */
public enum Direction implements Enumerator {
	/**
	 * The '<em><b>Undefined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDEFINED_VALUE
	 * @generated not
	 * @ordered
	 */
	UNDEFINED(0, Messages.getString("Direction.undefined"), "Undefined"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>N</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #N_VALUE
	 * @generated not
	 * @ordered
	 */
	N(1, Messages.getString("Direction.n"), "N"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NNE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NNE_VALUE
	 * @generated not
	 * @ordered
	 */
	NNE(2, Messages.getString("Direction.nne"), "NNE"), /** //$NON-NLS-1$ //$NON-NLS-2$
	 * The '<em><b>NE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NE_VALUE
	 * @generated not
	 * @ordered
	 */
	NE(3, Messages.getString("Direction.ne"), "NE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ENE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENE_VALUE
	 * @generated not
	 * @ordered
	 */
	ENE(4, Messages.getString("Direction.ene"), "ENE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>E</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #E_VALUE
	 * @generated not
	 * @ordered
	 */
	E(5, Messages.getString("Direction.e"), "E"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>ESE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ESE_VALUE
	 * @generated not
	 * @ordered
	 */
	ESE(6, Messages.getString("Direction.ese"), "ESE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SE_VALUE
	 * @generated not
	 * @ordered
	 */
	SE(7, Messages.getString("Direction.se"), "SE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SSE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SSE_VALUE
	 * @generated not
	 * @ordered
	 */
	SSE(8, Messages.getString("Direction.sse"), "SSE"), /** //$NON-NLS-1$ //$NON-NLS-2$
	 * The '<em><b>S</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #S_VALUE
	 * @generated not
	 * @ordered
	 */
	S(9, Messages.getString("Direction.s"), "S"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SSW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SSW_VALUE
	 * @generated not
	 * @ordered
	 */
	SSW(10, Messages.getString("Direction.ssw"), "SSW"), /** //$NON-NLS-1$ //$NON-NLS-2$
	 * The '<em><b>SW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SW_VALUE
	 * @generated not
	 * @ordered
	 */
	SW(11, Messages.getString("Direction.sw"), "SW"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>WSW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WSW_VALUE
	 * @generated not
	 * @ordered
	 */
	WSW(12, Messages.getString("Direction.wsw"), "WSW"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>W</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #W_VALUE
	 * @generated not
	 * @ordered
	 */
	W(13, Messages.getString("Direction.w"), "W"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>WNW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WNW_VALUE
	 * @generated not
	 * @ordered
	 */
	WNW(14, Messages.getString("Direction.wnw"), "WNW"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NW_VALUE
	 * @generated not
	 * @ordered
	 */
	NW(15, Messages.getString("Direction.nw"), "NW"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>NNW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NNW_VALUE
	 * @generated not
	 * @ordered
	 */
	NNW(16, Messages.getString("Direction.nnw"), "NNW"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Undefined</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Undefined</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNDEFINED
	 * @model name="Undefined"
	 * @generated
	 * @ordered
	 */
	public static final int UNDEFINED_VALUE = 0;

	/**
	 * The '<em><b>N</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>N</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #N
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int N_VALUE = 1;

	/**
	 * The '<em><b>NNE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NNE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NNE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NNE_VALUE = 2;

	/**
	 * The '<em><b>NE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NE_VALUE = 3;

	/**
	 * The '<em><b>ENE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ENE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ENE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ENE_VALUE = 4;

	/**
	 * The '<em><b>E</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>E</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #E
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int E_VALUE = 5;

	/**
	 * The '<em><b>ESE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ESE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ESE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ESE_VALUE = 6;

	/**
	 * The '<em><b>SE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SE_VALUE = 7;

	/**
	 * The '<em><b>SSE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SSE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SSE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SSE_VALUE = 8;

	/**
	 * The '<em><b>S</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>S</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #S
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int S_VALUE = 9;

	/**
	 * The '<em><b>SSW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SSW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SSW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SSW_VALUE = 10;

	/**
	 * The '<em><b>SW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SW_VALUE = 11;

	/**
	 * The '<em><b>WSW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WSW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WSW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WSW_VALUE = 12;

	/**
	 * The '<em><b>W</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>W</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #W
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int W_VALUE = 13;

	/**
	 * The '<em><b>WNW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WNW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WNW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WNW_VALUE = 14;

	/**
	 * The '<em><b>NW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NW_VALUE = 15;

	/**
	 * The '<em><b>NNW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NNW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NNW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NNW_VALUE = 16;

	/**
	 * An array of all the '<em><b>Direction</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Direction[] VALUES_ARRAY =
		new Direction[] {
			UNDEFINED,
			N,
			NNE,
			NE,
			ENE,
			E,
			ESE,
			SE,
			SSE,
			S,
			SSW,
			SW,
			WSW,
			W,
			WNW,
			NW,
			NNW,
		};

	/**
	 * A public read-only list of all the '<em><b>Direction</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Direction</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Direction get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Direction result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Direction</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Direction getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Direction result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Direction</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Direction get(int value) {
		switch (value) {
			case UNDEFINED_VALUE: return UNDEFINED;
			case N_VALUE: return N;
			case NNE_VALUE: return NNE;
			case NE_VALUE: return NE;
			case ENE_VALUE: return ENE;
			case E_VALUE: return E;
			case ESE_VALUE: return ESE;
			case SE_VALUE: return SE;
			case SSE_VALUE: return SSE;
			case S_VALUE: return S;
			case SSW_VALUE: return SSW;
			case SW_VALUE: return SW;
			case WSW_VALUE: return WSW;
			case W_VALUE: return W;
			case WNW_VALUE: return WNW;
			case NW_VALUE: return NW;
			case NNW_VALUE: return NNW;
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
	private Direction(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	
} //Direction
