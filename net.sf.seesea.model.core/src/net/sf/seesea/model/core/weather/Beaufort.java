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
 * A representation of the literals of the enumeration '<em><b>Beaufort</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.weather.WeatherPackage#getBeaufort()
 * @model
 * @generated
 */
public enum Beaufort implements Enumerator {
	/**
	 * The '<em><b>Undefined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDEFINED_VALUE
	 * @generated not
	 * @ordered
	 */
	UNDEFINED(13, Messages.getString("Beaufort.undefined"), "Undefined"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Calm</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CALM_VALUE
	 * @generated not
	 * @ordered
	 */
	CALM(0, Messages.getString("Beaufort.calm"), "Calm"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Light Air</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIGHT_AIR_VALUE
	 * @generated  not
	 * @ordered
	 */
	LIGHT_AIR(1, Messages.getString("Beaufort.lightAir"), "LightAir"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Light Breeze</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIGHT_BREEZE_VALUE
	 * @generated not
	 * @ordered
	 */
	LIGHT_BREEZE(2, Messages.getString("Beaufort.lightBreeze"), "LightBreeze"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Gentle Breeze</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENTLE_BREEZE_VALUE
	 * @generated not
	 * @ordered
	 */
	GENTLE_BREEZE(3, Messages.getString("Beaufort.gentleBreeze"), "GentleBreeze"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Moderate Breeze</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MODERATE_BREEZE_VALUE
	 * @generated not
	 * @ordered
	 */
	MODERATE_BREEZE(4, Messages.getString("Beaufort.moderateBreeze"), "ModerateBreeze"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Fresh Breeze</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FRESH_BREEZE_VALUE
	 * @generated not
	 * @ordered
	 */
	FRESH_BREEZE(5, Messages.getString("Beaufort.freshBreeze"), "FreshBreeze"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Strong Breeze</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRONG_BREEZE_VALUE
	 * @generated not
	 * @ordered
	 */
	STRONG_BREEZE(6, Messages.getString("Beaufort.strongBreeze"), "StrongBreeze"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Near Gale</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEAR_GALE_VALUE
	 * @generated not
	 * @ordered
	 */
	NEAR_GALE(7, Messages.getString("Beaufort.nearGale"), "NearGale"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Gale</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GALE_VALUE
	 * @generated not
	 * @ordered
	 */
	GALE(8, Messages.getString("Beaufort.gale"), "Gale"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Strong Gale</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRONG_GALE_VALUE
	 * @generated not
	 * @ordered
	 */
	STRONG_GALE(9, Messages.getString("Beaufort.strongGale"), "StrongGale"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Storm</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STORM_VALUE
	 * @generated not
	 * @ordered
	 */
	STORM(10, Messages.getString("Beaufort.storm"), "Storm"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Violent Storm</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VIOLENT_STORM_VALUE
	 * @generated not
	 * @ordered
	 */
	VIOLENT_STORM(11, Messages.getString("Beaufort.violentStorm"), "ViolentStorm"), //$NON-NLS-1$ //$NON-NLS-2$
	
	 /**
	 * The '<em><b>Hurricane Force</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HURRICANE_FORCE_VALUE
	 * @generated not
	 * @ordered
	 */
	HURRICANE_FORCE(12, Messages.getString("Beaufort.hurricaneForce"), "HurricaneForce"); //$NON-NLS-1$ //$NON-NLS-2$

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
	public static final int UNDEFINED_VALUE = 13;

	/**
	 * The '<em><b>Calm</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Calm</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CALM
	 * @model name="Calm"
	 * @generated
	 * @ordered
	 */
	public static final int CALM_VALUE = 0;

	/**
	 * The '<em><b>Light Air</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Light Air</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LIGHT_AIR
	 * @model name="LightAir"
	 * @generated
	 * @ordered
	 */
	public static final int LIGHT_AIR_VALUE = 1;

	/**
	 * The '<em><b>Light Breeze</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Light Breeze</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LIGHT_BREEZE
	 * @model name="LightBreeze"
	 * @generated
	 * @ordered
	 */
	public static final int LIGHT_BREEZE_VALUE = 2;

	/**
	 * The '<em><b>Gentle Breeze</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Gentle Breeze</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENTLE_BREEZE
	 * @model name="GentleBreeze"
	 * @generated
	 * @ordered
	 */
	public static final int GENTLE_BREEZE_VALUE = 3;

	/**
	 * The '<em><b>Moderate Breeze</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Moderate Breeze</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MODERATE_BREEZE
	 * @model name="ModerateBreeze"
	 * @generated
	 * @ordered
	 */
	public static final int MODERATE_BREEZE_VALUE = 4;

	/**
	 * The '<em><b>Fresh Breeze</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Fresh Breeze</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FRESH_BREEZE
	 * @model name="FreshBreeze"
	 * @generated
	 * @ordered
	 */
	public static final int FRESH_BREEZE_VALUE = 5;

	/**
	 * The '<em><b>Strong Breeze</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Strong Breeze</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STRONG_BREEZE
	 * @model name="StrongBreeze"
	 * @generated
	 * @ordered
	 */
	public static final int STRONG_BREEZE_VALUE = 6;

	/**
	 * The '<em><b>Near Gale</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Near Gale</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEAR_GALE
	 * @model name="NearGale"
	 * @generated
	 * @ordered
	 */
	public static final int NEAR_GALE_VALUE = 7;

	/**
	 * The '<em><b>Gale</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Gale</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GALE
	 * @model name="Gale"
	 * @generated
	 * @ordered
	 */
	public static final int GALE_VALUE = 8;

	/**
	 * The '<em><b>Strong Gale</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Strong Gale</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STRONG_GALE
	 * @model name="StrongGale"
	 * @generated
	 * @ordered
	 */
	public static final int STRONG_GALE_VALUE = 9;

	/**
	 * The '<em><b>Storm</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Storm</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STORM
	 * @model name="Storm"
	 * @generated
	 * @ordered
	 */
	public static final int STORM_VALUE = 10;

	/**
	 * The '<em><b>Violent Storm</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Violent Storm</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VIOLENT_STORM
	 * @model name="ViolentStorm"
	 * @generated
	 * @ordered
	 */
	public static final int VIOLENT_STORM_VALUE = 11;

	/**
	 * The '<em><b>Hurricane Force</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Hurricane Force</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HURRICANE_FORCE
	 * @model name="HurricaneForce"
	 * @generated
	 * @ordered
	 */
	public static final int HURRICANE_FORCE_VALUE = 12;

	/**
	 * An array of all the '<em><b>Beaufort</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Beaufort[] VALUES_ARRAY =
		new Beaufort[] {
			UNDEFINED,
			CALM,
			LIGHT_AIR,
			LIGHT_BREEZE,
			GENTLE_BREEZE,
			MODERATE_BREEZE,
			FRESH_BREEZE,
			STRONG_BREEZE,
			NEAR_GALE,
			GALE,
			STRONG_GALE,
			STORM,
			VIOLENT_STORM,
			HURRICANE_FORCE,
		};

	/**
	 * A public read-only list of all the '<em><b>Beaufort</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Beaufort> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Beaufort</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Beaufort get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Beaufort result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Beaufort</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Beaufort getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Beaufort result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Beaufort</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Beaufort get(int value) {
		switch (value) {
			case UNDEFINED_VALUE: return UNDEFINED;
			case CALM_VALUE: return CALM;
			case LIGHT_AIR_VALUE: return LIGHT_AIR;
			case LIGHT_BREEZE_VALUE: return LIGHT_BREEZE;
			case GENTLE_BREEZE_VALUE: return GENTLE_BREEZE;
			case MODERATE_BREEZE_VALUE: return MODERATE_BREEZE;
			case FRESH_BREEZE_VALUE: return FRESH_BREEZE;
			case STRONG_BREEZE_VALUE: return STRONG_BREEZE;
			case NEAR_GALE_VALUE: return NEAR_GALE;
			case GALE_VALUE: return GALE;
			case STRONG_GALE_VALUE: return STRONG_GALE;
			case STORM_VALUE: return STORM;
			case VIOLENT_STORM_VALUE: return VIOLENT_STORM;
			case HURRICANE_FORCE_VALUE: return HURRICANE_FORCE;
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
	private Beaufort(int value, String name, String literal) {
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
	
} //Beaufort
