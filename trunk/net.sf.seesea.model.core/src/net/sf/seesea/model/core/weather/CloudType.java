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
package net.sf.seesea.model.core.weather;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Cloud Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.weather.WeatherPackage#getCloudType()
 * @model
 * @generated
 */
public enum CloudType implements Enumerator {
	/**
	 * The '<em><b>Undefined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDEFINED_VALUE
	 * @generated
	 * @ordered
	 */
	UNDEFINED(0, "Undefined", "Undefined"),

	/**
	 * The '<em><b>Cirrus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CIRRUS_VALUE
	 * @generated
	 * @ordered
	 */
	CIRRUS(1, "Cirrus", "Cirrus"),

	/**
	 * The '<em><b>Cirrocumulus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CIRROCUMULUS_VALUE
	 * @generated
	 * @ordered
	 */
	CIRROCUMULUS(2, "Cirrocumulus", "Cirrocumulus"),

	/**
	 * The '<em><b>Cirrostratus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CIRROSTRATUS_VALUE
	 * @generated
	 * @ordered
	 */
	CIRROSTRATUS(3, "Cirrostratus", "Cirrostratus"),

	/**
	 * The '<em><b>Altocumulus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALTOCUMULUS_VALUE
	 * @generated
	 * @ordered
	 */
	ALTOCUMULUS(4, "Altocumulus", "Altocumulus"),

	/**
	 * The '<em><b>Altostratus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALTOSTRATUS_VALUE
	 * @generated
	 * @ordered
	 */
	ALTOSTRATUS(5, "Altostratus", "Altostratus"),

	/**
	 * The '<em><b>Nimbostratus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NIMBOSTRATUS_VALUE
	 * @generated
	 * @ordered
	 */
	NIMBOSTRATUS(6, "Nimbostratus", "Nimbostratus"),

	/**
	 * The '<em><b>Stratocumulus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRATOCUMULUS_VALUE
	 * @generated
	 * @ordered
	 */
	STRATOCUMULUS(7, "Stratocumulus", "Stratocumulus"),

	/**
	 * The '<em><b>Stratus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRATUS_VALUE
	 * @generated
	 * @ordered
	 */
	STRATUS(8, "Stratus", "Stratus"),

	/**
	 * The '<em><b>Cumulus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CUMULUS_VALUE
	 * @generated
	 * @ordered
	 */
	CUMULUS(9, "Cumulus", "Cumulus"),

	/**
	 * The '<em><b>Cumulonimbus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CUMULONIMBUS_VALUE
	 * @generated
	 * @ordered
	 */
	CUMULONIMBUS(10, "Cumulonimbus", "Cumulonimbus");

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
	 * The '<em><b>Cirrus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cirrus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CIRRUS
	 * @model name="Cirrus"
	 * @generated
	 * @ordered
	 */
	public static final int CIRRUS_VALUE = 1;

	/**
	 * The '<em><b>Cirrocumulus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cirrocumulus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CIRROCUMULUS
	 * @model name="Cirrocumulus"
	 * @generated
	 * @ordered
	 */
	public static final int CIRROCUMULUS_VALUE = 2;

	/**
	 * The '<em><b>Cirrostratus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cirrostratus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CIRROSTRATUS
	 * @model name="Cirrostratus"
	 * @generated
	 * @ordered
	 */
	public static final int CIRROSTRATUS_VALUE = 3;

	/**
	 * The '<em><b>Altocumulus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Altocumulus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALTOCUMULUS
	 * @model name="Altocumulus"
	 * @generated
	 * @ordered
	 */
	public static final int ALTOCUMULUS_VALUE = 4;

	/**
	 * The '<em><b>Altostratus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Altostratus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALTOSTRATUS
	 * @model name="Altostratus"
	 * @generated
	 * @ordered
	 */
	public static final int ALTOSTRATUS_VALUE = 5;

	/**
	 * The '<em><b>Nimbostratus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Nimbostratus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NIMBOSTRATUS
	 * @model name="Nimbostratus"
	 * @generated
	 * @ordered
	 */
	public static final int NIMBOSTRATUS_VALUE = 6;

	/**
	 * The '<em><b>Stratocumulus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stratocumulus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STRATOCUMULUS
	 * @model name="Stratocumulus"
	 * @generated
	 * @ordered
	 */
	public static final int STRATOCUMULUS_VALUE = 7;

	/**
	 * The '<em><b>Stratus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stratus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STRATUS
	 * @model name="Stratus"
	 * @generated
	 * @ordered
	 */
	public static final int STRATUS_VALUE = 8;

	/**
	 * The '<em><b>Cumulus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cumulus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CUMULUS
	 * @model name="Cumulus"
	 * @generated
	 * @ordered
	 */
	public static final int CUMULUS_VALUE = 9;

	/**
	 * The '<em><b>Cumulonimbus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cumulonimbus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CUMULONIMBUS
	 * @model name="Cumulonimbus"
	 * @generated
	 * @ordered
	 */
	public static final int CUMULONIMBUS_VALUE = 10;

	/**
	 * An array of all the '<em><b>Cloud Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CloudType[] VALUES_ARRAY =
		new CloudType[] {
			UNDEFINED,
			CIRRUS,
			CIRROCUMULUS,
			CIRROSTRATUS,
			ALTOCUMULUS,
			ALTOSTRATUS,
			NIMBOSTRATUS,
			STRATOCUMULUS,
			STRATUS,
			CUMULUS,
			CUMULONIMBUS,
		};

	/**
	 * A public read-only list of all the '<em><b>Cloud Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<CloudType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Cloud Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CloudType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CloudType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Cloud Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CloudType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CloudType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Cloud Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CloudType get(int value) {
		switch (value) {
			case UNDEFINED_VALUE: return UNDEFINED;
			case CIRRUS_VALUE: return CIRRUS;
			case CIRROCUMULUS_VALUE: return CIRROCUMULUS;
			case CIRROSTRATUS_VALUE: return CIRROSTRATUS;
			case ALTOCUMULUS_VALUE: return ALTOCUMULUS;
			case ALTOSTRATUS_VALUE: return ALTOSTRATUS;
			case NIMBOSTRATUS_VALUE: return NIMBOSTRATUS;
			case STRATOCUMULUS_VALUE: return STRATOCUMULUS;
			case STRATUS_VALUE: return STRATUS;
			case CUMULUS_VALUE: return CUMULUS;
			case CUMULONIMBUS_VALUE: return CUMULONIMBUS;
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
	private CloudType(int value, String name, String literal) {
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
	
} //CloudType
