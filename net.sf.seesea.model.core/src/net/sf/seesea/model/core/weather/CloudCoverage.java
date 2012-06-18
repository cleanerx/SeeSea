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
 * A representation of the literals of the enumeration '<em><b>Cloud Coverage</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.weather.WeatherPackage#getCloudCoverage()
 * @model
 * @generated
 */
public enum CloudCoverage implements Enumerator {
	/**
	 * The '<em><b>Undefined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDEFINED_VALUE
	 * @generated
	 * @ordered
	 */
	UNDEFINED(0, "Undefined", "Undefined"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Cloudless</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOUDLESS_VALUE
	 * @generated
	 * @ordered
	 */
	CLOUDLESS(1, "Cloudless", "Cloudless"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Fair</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FAIR_VALUE
	 * @generated
	 * @ordered
	 */
	FAIR(2, "Fair", "Fair"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Partly Cloudy Weak</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTLY_CLOUDY_WEAK_VALUE
	 * @generated
	 * @ordered
	 */
	PARTLY_CLOUDY_WEAK(3, "PartlyCloudyWeak", "PartlyCloudyWeak"), /**
	 * The '<em><b>Partly Cloudy Medium</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTLY_CLOUDY_MEDIUM_VALUE
	 * @generated
	 * @ordered
	 */
	PARTLY_CLOUDY_MEDIUM(4, "PartlyCloudyMedium", "PartlyCloudyMedium"), /**
	 * The '<em><b>Partly Cloudy Heavy</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTLY_CLOUDY_HEAVY_VALUE
	 * @generated
	 * @ordered
	 */
	PARTLY_CLOUDY_HEAVY(5, "PartlyCloudyHeavy", "PartlyCloudyHeavy"), /**
	 * The '<em><b>Weakly Clouded</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEAKLY_CLOUDED_VALUE
	 * @generated
	 * @ordered
	 */
	WEAKLY_CLOUDED(6, "WeaklyClouded", "WeaklyClouded"), /**
	 * The '<em><b>Medium Clouded</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEDIUM_CLOUDED_VALUE
	 * @generated
	 * @ordered
	 */
	MEDIUM_CLOUDED(7, "MediumClouded", "MediumClouded"), /**
	 * The '<em><b>Heavily Clouded</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HEAVILY_CLOUDED_VALUE
	 * @generated
	 * @ordered
	 */
	HEAVILY_CLOUDED(8, "HeavilyClouded", "HeavilyClouded"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Dull</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DULL_VALUE
	 * @generated
	 * @ordered
	 */
	DULL(9, "Dull", "Dull"), /**
	 * The '<em><b>Obscured</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OBSCURED_VALUE
	 * @generated
	 * @ordered
	 */
	OBSCURED(10, "Obscured", "Obscured"); //$NON-NLS-1$ //$NON-NLS-2$

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
	 * The '<em><b>Cloudless</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cloudless</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CLOUDLESS
	 * @model name="Cloudless"
	 * @generated
	 * @ordered
	 */
	public static final int CLOUDLESS_VALUE = 1;

	/**
	 * The '<em><b>Fair</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Fair</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FAIR
	 * @model name="Fair"
	 * @generated
	 * @ordered
	 */
	public static final int FAIR_VALUE = 2;

	/**
	 * The '<em><b>Partly Cloudy Weak</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Partly Cloudy Weak</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARTLY_CLOUDY_WEAK
	 * @model name="PartlyCloudyWeak"
	 * @generated
	 * @ordered
	 */
	public static final int PARTLY_CLOUDY_WEAK_VALUE = 3;

	/**
	 * The '<em><b>Partly Cloudy Medium</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Partly Cloudy Medium</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARTLY_CLOUDY_MEDIUM
	 * @model name="PartlyCloudyMedium"
	 * @generated
	 * @ordered
	 */
	public static final int PARTLY_CLOUDY_MEDIUM_VALUE = 4;

	/**
	 * The '<em><b>Partly Cloudy Heavy</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Partly Cloudy Heavy</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARTLY_CLOUDY_HEAVY
	 * @model name="PartlyCloudyHeavy"
	 * @generated
	 * @ordered
	 */
	public static final int PARTLY_CLOUDY_HEAVY_VALUE = 5;

	/**
	 * The '<em><b>Weakly Clouded</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Weakly Clouded</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEAKLY_CLOUDED
	 * @model name="WeaklyClouded"
	 * @generated
	 * @ordered
	 */
	public static final int WEAKLY_CLOUDED_VALUE = 6;

	/**
	 * The '<em><b>Medium Clouded</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Medium Clouded</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MEDIUM_CLOUDED
	 * @model name="MediumClouded"
	 * @generated
	 * @ordered
	 */
	public static final int MEDIUM_CLOUDED_VALUE = 7;

	/**
	 * The '<em><b>Heavily Clouded</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Heavily Clouded</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HEAVILY_CLOUDED
	 * @model name="HeavilyClouded"
	 * @generated
	 * @ordered
	 */
	public static final int HEAVILY_CLOUDED_VALUE = 8;

	/**
	 * The '<em><b>Dull</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Dull</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DULL
	 * @model name="Dull"
	 * @generated
	 * @ordered
	 */
	public static final int DULL_VALUE = 9;

	/**
	 * The '<em><b>Obscured</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Obscured</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OBSCURED
	 * @model name="Obscured"
	 * @generated
	 * @ordered
	 */
	public static final int OBSCURED_VALUE = 10;

	/**
	 * An array of all the '<em><b>Cloud Coverage</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CloudCoverage[] VALUES_ARRAY =
		new CloudCoverage[] {
			UNDEFINED,
			CLOUDLESS,
			FAIR,
			PARTLY_CLOUDY_WEAK,
			PARTLY_CLOUDY_MEDIUM,
			PARTLY_CLOUDY_HEAVY,
			WEAKLY_CLOUDED,
			MEDIUM_CLOUDED,
			HEAVILY_CLOUDED,
			DULL,
			OBSCURED,
		};

	/**
	 * A public read-only list of all the '<em><b>Cloud Coverage</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<CloudCoverage> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Cloud Coverage</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CloudCoverage get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CloudCoverage result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Cloud Coverage</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CloudCoverage getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CloudCoverage result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Cloud Coverage</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CloudCoverage get(int value) {
		switch (value) {
			case UNDEFINED_VALUE: return UNDEFINED;
			case CLOUDLESS_VALUE: return CLOUDLESS;
			case FAIR_VALUE: return FAIR;
			case PARTLY_CLOUDY_WEAK_VALUE: return PARTLY_CLOUDY_WEAK;
			case PARTLY_CLOUDY_MEDIUM_VALUE: return PARTLY_CLOUDY_MEDIUM;
			case PARTLY_CLOUDY_HEAVY_VALUE: return PARTLY_CLOUDY_HEAVY;
			case WEAKLY_CLOUDED_VALUE: return WEAKLY_CLOUDED;
			case MEDIUM_CLOUDED_VALUE: return MEDIUM_CLOUDED;
			case HEAVILY_CLOUDED_VALUE: return HEAVILY_CLOUDED;
			case DULL_VALUE: return DULL;
			case OBSCURED_VALUE: return OBSCURED;
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
	private CloudCoverage(int value, String name, String literal) {
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
	
} //CloudCoverage
