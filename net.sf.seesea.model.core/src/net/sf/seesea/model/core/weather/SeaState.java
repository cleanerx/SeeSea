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
 * A representation of the literals of the enumeration '<em><b>Sea State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.weather.WeatherPackage#getSeaState()
 * @model
 * @generated
 */
public enum SeaState implements Enumerator {
	/**
	 * The '<em><b>Calm Glassy</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CALM_GLASSY_VALUE
	 * @generated
	 * @ordered
	 */
	CALM_GLASSY(0, "CalmGlassy", "CalmGlassy"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Calm Rippled</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CALM_RIPPLED_VALUE
	 * @generated
	 * @ordered
	 */
	CALM_RIPPLED(1, "CalmRippled", "CalmRippled"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Smooth Wavelets</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SMOOTH_WAVELETS_VALUE
	 * @generated
	 * @ordered
	 */
	SMOOTH_WAVELETS(2, "SmoothWavelets", "SmoothWavelets"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Slight</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SLIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	SLIGHT(3, "Slight", "Slight"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Moderate</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MODERATE_VALUE
	 * @generated
	 * @ordered
	 */
	MODERATE(4, "Moderate", "Moderate"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Rough</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROUGH_VALUE
	 * @generated
	 * @ordered
	 */
	ROUGH(5, "Rough", "Rough"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Very Rough</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VERY_ROUGH_VALUE
	 * @generated
	 * @ordered
	 */
	VERY_ROUGH(6, "VeryRough", "VeryRough"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>High</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HIGH_VALUE
	 * @generated
	 * @ordered
	 */
	HIGH(7, "High", "High"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Very High</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VERY_HIGH_VALUE
	 * @generated
	 * @ordered
	 */
	VERY_HIGH(8, "VeryHigh", "VeryHigh"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Phenomenal</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PHENOMENAL_VALUE
	 * @generated
	 * @ordered
	 */
	PHENOMENAL(9, "Phenomenal", "Phenomenal"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Undefined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDEFINED_VALUE
	 * @generated
	 * @ordered
	 */
	UNDEFINED(10, "Undefined", "Undefined"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Calm Glassy</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Calm Glassy</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CALM_GLASSY
	 * @model name="CalmGlassy"
	 * @generated
	 * @ordered
	 */
	public static final int CALM_GLASSY_VALUE = 0;

	/**
	 * The '<em><b>Calm Rippled</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Calm Rippled</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CALM_RIPPLED
	 * @model name="CalmRippled"
	 * @generated
	 * @ordered
	 */
	public static final int CALM_RIPPLED_VALUE = 1;

	/**
	 * The '<em><b>Smooth Wavelets</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Smooth Wavelets</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SMOOTH_WAVELETS
	 * @model name="SmoothWavelets"
	 * @generated
	 * @ordered
	 */
	public static final int SMOOTH_WAVELETS_VALUE = 2;

	/**
	 * The '<em><b>Slight</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Slight</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SLIGHT
	 * @model name="Slight"
	 * @generated
	 * @ordered
	 */
	public static final int SLIGHT_VALUE = 3;

	/**
	 * The '<em><b>Moderate</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Moderate</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MODERATE
	 * @model name="Moderate"
	 * @generated
	 * @ordered
	 */
	public static final int MODERATE_VALUE = 4;

	/**
	 * The '<em><b>Rough</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Rough</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROUGH
	 * @model name="Rough"
	 * @generated
	 * @ordered
	 */
	public static final int ROUGH_VALUE = 5;

	/**
	 * The '<em><b>Very Rough</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Very Rough</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VERY_ROUGH
	 * @model name="VeryRough"
	 * @generated
	 * @ordered
	 */
	public static final int VERY_ROUGH_VALUE = 6;

	/**
	 * The '<em><b>High</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>High</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HIGH
	 * @model name="High"
	 * @generated
	 * @ordered
	 */
	public static final int HIGH_VALUE = 7;

	/**
	 * The '<em><b>Very High</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Very High</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VERY_HIGH
	 * @model name="VeryHigh"
	 * @generated
	 * @ordered
	 */
	public static final int VERY_HIGH_VALUE = 8;

	/**
	 * The '<em><b>Phenomenal</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Phenomenal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PHENOMENAL
	 * @model name="Phenomenal"
	 * @generated
	 * @ordered
	 */
	public static final int PHENOMENAL_VALUE = 9;

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
	public static final int UNDEFINED_VALUE = 10;

	/**
	 * An array of all the '<em><b>Sea State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SeaState[] VALUES_ARRAY =
		new SeaState[] {
			CALM_GLASSY,
			CALM_RIPPLED,
			SMOOTH_WAVELETS,
			SLIGHT,
			MODERATE,
			ROUGH,
			VERY_ROUGH,
			HIGH,
			VERY_HIGH,
			PHENOMENAL,
			UNDEFINED,
		};

	/**
	 * A public read-only list of all the '<em><b>Sea State</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SeaState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Sea State</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SeaState get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SeaState result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sea State</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SeaState getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SeaState result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sea State</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SeaState get(int value) {
		switch (value) {
			case CALM_GLASSY_VALUE: return CALM_GLASSY;
			case CALM_RIPPLED_VALUE: return CALM_RIPPLED;
			case SMOOTH_WAVELETS_VALUE: return SMOOTH_WAVELETS;
			case SLIGHT_VALUE: return SLIGHT;
			case MODERATE_VALUE: return MODERATE;
			case ROUGH_VALUE: return ROUGH;
			case VERY_ROUGH_VALUE: return VERY_ROUGH;
			case HIGH_VALUE: return HIGH;
			case VERY_HIGH_VALUE: return VERY_HIGH;
			case PHENOMENAL_VALUE: return PHENOMENAL;
			case UNDEFINED_VALUE: return UNDEFINED;
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
	private SeaState(int value, String name, String literal) {
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
	
} //SeaState
