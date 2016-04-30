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

import net.sf.seesea.model.core.physx.PhysxPackage;
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
 * @see net.sf.seesea.model.core.weather.WeatherFactory
 * @model kind="package"
 * @generated
 */
public interface WeatherPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "weather";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "weather";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "weather";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WeatherPackage eINSTANCE = net.sf.seesea.model.core.weather.impl.WeatherPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.impl.WindMeasurementImpl <em>Wind Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.impl.WindMeasurementImpl
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getWindMeasurement()
	 * @generated
	 */
	int WIND_MEASUREMENT = 0;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT__SENSOR_ID = PhysxPackage.MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT__TIME = PhysxPackage.MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT__TIMEZONE = PhysxPackage.MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT__VALID = PhysxPackage.MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT__SPEED = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Speed Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT__SPEED_UNIT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Angle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT__ANGLE = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT__REFERENCE = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Wind Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIND_MEASUREMENT_FEATURE_COUNT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.impl.BarometricPressureImpl <em>Barometric Pressure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.impl.BarometricPressureImpl
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getBarometricPressure()
	 * @generated
	 */
	int BAROMETRIC_PRESSURE = 1;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAROMETRIC_PRESSURE__SENSOR_ID = PhysxPackage.MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAROMETRIC_PRESSURE__TIME = PhysxPackage.MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAROMETRIC_PRESSURE__TIMEZONE = PhysxPackage.MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAROMETRIC_PRESSURE__VALID = PhysxPackage.MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAROMETRIC_PRESSURE__VALUE = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAROMETRIC_PRESSURE__UNIT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Barometric Pressure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAROMETRIC_PRESSURE_FEATURE_COUNT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.impl.CloudMeasurementImpl <em>Cloud Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.impl.CloudMeasurementImpl
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getCloudMeasurement()
	 * @generated
	 */
	int CLOUD_MEASUREMENT = 2;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_MEASUREMENT__SENSOR_ID = PhysxPackage.MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_MEASUREMENT__TIME = PhysxPackage.MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_MEASUREMENT__TIMEZONE = PhysxPackage.MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_MEASUREMENT__VALID = PhysxPackage.MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Cloud Coverage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_MEASUREMENT__CLOUD_COVERAGE = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cloud Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_MEASUREMENT__CLOUD_TYPES = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Cloud Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLOUD_MEASUREMENT_FEATURE_COUNT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.impl.VisibilityImpl <em>Visibility</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.impl.VisibilityImpl
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getVisibility()
	 * @generated
	 */
	int VISIBILITY = 3;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY__SENSOR_ID = PhysxPackage.MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY__TIME = PhysxPackage.MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY__TIMEZONE = PhysxPackage.MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY__VALID = PhysxPackage.MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY__VALUE = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY__UNIT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Visibility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY_FEATURE_COUNT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.impl.WaveHeightImpl <em>Wave Height</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.impl.WaveHeightImpl
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getWaveHeight()
	 * @generated
	 */
	int WAVE_HEIGHT = 4;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAVE_HEIGHT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAVE_HEIGHT__UNIT = 1;

	/**
	 * The number of structural features of the '<em>Wave Height</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAVE_HEIGHT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.Reference <em>Reference</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.Reference
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 5;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.PressureUnit <em>Pressure Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.PressureUnit
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getPressureUnit()
	 * @generated
	 */
	int PRESSURE_UNIT = 6;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.CloudType <em>Cloud Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.CloudType
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getCloudType()
	 * @generated
	 */
	int CLOUD_TYPE = 7;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.CloudCoverage <em>Cloud Coverage</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.CloudCoverage
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getCloudCoverage()
	 * @generated
	 */
	int CLOUD_COVERAGE = 8;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.Beaufort <em>Beaufort</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.Beaufort
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getBeaufort()
	 * @generated
	 */
	int BEAUFORT = 9;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.weather.SeaState <em>Sea State</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.weather.SeaState
	 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getSeaState()
	 * @generated
	 */
	int SEA_STATE = 10;

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.weather.WindMeasurement <em>Wind Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wind Measurement</em>'.
	 * @see net.sf.seesea.model.core.weather.WindMeasurement
	 * @generated
	 */
	EClass getWindMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.WindMeasurement#getAngle <em>Angle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angle</em>'.
	 * @see net.sf.seesea.model.core.weather.WindMeasurement#getAngle()
	 * @see #getWindMeasurement()
	 * @generated
	 */
	EAttribute getWindMeasurement_Angle();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.WindMeasurement#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference</em>'.
	 * @see net.sf.seesea.model.core.weather.WindMeasurement#getReference()
	 * @see #getWindMeasurement()
	 * @generated
	 */
	EAttribute getWindMeasurement_Reference();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.weather.BarometricPressure <em>Barometric Pressure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Barometric Pressure</em>'.
	 * @see net.sf.seesea.model.core.weather.BarometricPressure
	 * @generated
	 */
	EClass getBarometricPressure();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.BarometricPressure#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.sf.seesea.model.core.weather.BarometricPressure#getValue()
	 * @see #getBarometricPressure()
	 * @generated
	 */
	EAttribute getBarometricPressure_Value();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.BarometricPressure#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see net.sf.seesea.model.core.weather.BarometricPressure#getUnit()
	 * @see #getBarometricPressure()
	 * @generated
	 */
	EAttribute getBarometricPressure_Unit();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.weather.CloudMeasurement <em>Cloud Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cloud Measurement</em>'.
	 * @see net.sf.seesea.model.core.weather.CloudMeasurement
	 * @generated
	 */
	EClass getCloudMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.CloudMeasurement#getCloudCoverage <em>Cloud Coverage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cloud Coverage</em>'.
	 * @see net.sf.seesea.model.core.weather.CloudMeasurement#getCloudCoverage()
	 * @see #getCloudMeasurement()
	 * @generated
	 */
	EAttribute getCloudMeasurement_CloudCoverage();

	/**
	 * Returns the meta object for the attribute list '{@link net.sf.seesea.model.core.weather.CloudMeasurement#getCloudTypes <em>Cloud Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cloud Types</em>'.
	 * @see net.sf.seesea.model.core.weather.CloudMeasurement#getCloudTypes()
	 * @see #getCloudMeasurement()
	 * @generated
	 */
	EAttribute getCloudMeasurement_CloudTypes();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.weather.Visibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visibility</em>'.
	 * @see net.sf.seesea.model.core.weather.Visibility
	 * @generated
	 */
	EClass getVisibility();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.Visibility#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.sf.seesea.model.core.weather.Visibility#getValue()
	 * @see #getVisibility()
	 * @generated
	 */
	EAttribute getVisibility_Value();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.Visibility#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see net.sf.seesea.model.core.weather.Visibility#getUnit()
	 * @see #getVisibility()
	 * @generated
	 */
	EAttribute getVisibility_Unit();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.weather.WaveHeight <em>Wave Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wave Height</em>'.
	 * @see net.sf.seesea.model.core.weather.WaveHeight
	 * @generated
	 */
	EClass getWaveHeight();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.WaveHeight#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.sf.seesea.model.core.weather.WaveHeight#getValue()
	 * @see #getWaveHeight()
	 * @generated
	 */
	EAttribute getWaveHeight_Value();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.weather.WaveHeight#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see net.sf.seesea.model.core.weather.WaveHeight#getUnit()
	 * @see #getWaveHeight()
	 * @generated
	 */
	EAttribute getWaveHeight_Unit();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.weather.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Reference</em>'.
	 * @see net.sf.seesea.model.core.weather.Reference
	 * @generated
	 */
	EEnum getReference();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.weather.PressureUnit <em>Pressure Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Pressure Unit</em>'.
	 * @see net.sf.seesea.model.core.weather.PressureUnit
	 * @generated
	 */
	EEnum getPressureUnit();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.weather.CloudType <em>Cloud Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cloud Type</em>'.
	 * @see net.sf.seesea.model.core.weather.CloudType
	 * @generated
	 */
	EEnum getCloudType();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.weather.CloudCoverage <em>Cloud Coverage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cloud Coverage</em>'.
	 * @see net.sf.seesea.model.core.weather.CloudCoverage
	 * @generated
	 */
	EEnum getCloudCoverage();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.weather.Beaufort <em>Beaufort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Beaufort</em>'.
	 * @see net.sf.seesea.model.core.weather.Beaufort
	 * @generated
	 */
	EEnum getBeaufort();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.weather.SeaState <em>Sea State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sea State</em>'.
	 * @see net.sf.seesea.model.core.weather.SeaState
	 * @generated
	 */
	EEnum getSeaState();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WeatherFactory getWeatherFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.impl.WindMeasurementImpl <em>Wind Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.impl.WindMeasurementImpl
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getWindMeasurement()
		 * @generated
		 */
		EClass WIND_MEASUREMENT = eINSTANCE.getWindMeasurement();

		/**
		 * The meta object literal for the '<em><b>Angle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIND_MEASUREMENT__ANGLE = eINSTANCE.getWindMeasurement_Angle();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIND_MEASUREMENT__REFERENCE = eINSTANCE.getWindMeasurement_Reference();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.impl.BarometricPressureImpl <em>Barometric Pressure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.impl.BarometricPressureImpl
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getBarometricPressure()
		 * @generated
		 */
		EClass BAROMETRIC_PRESSURE = eINSTANCE.getBarometricPressure();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BAROMETRIC_PRESSURE__VALUE = eINSTANCE.getBarometricPressure_Value();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BAROMETRIC_PRESSURE__UNIT = eINSTANCE.getBarometricPressure_Unit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.impl.CloudMeasurementImpl <em>Cloud Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.impl.CloudMeasurementImpl
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getCloudMeasurement()
		 * @generated
		 */
		EClass CLOUD_MEASUREMENT = eINSTANCE.getCloudMeasurement();

		/**
		 * The meta object literal for the '<em><b>Cloud Coverage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_MEASUREMENT__CLOUD_COVERAGE = eINSTANCE.getCloudMeasurement_CloudCoverage();

		/**
		 * The meta object literal for the '<em><b>Cloud Types</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLOUD_MEASUREMENT__CLOUD_TYPES = eINSTANCE.getCloudMeasurement_CloudTypes();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.impl.VisibilityImpl <em>Visibility</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.impl.VisibilityImpl
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getVisibility()
		 * @generated
		 */
		EClass VISIBILITY = eINSTANCE.getVisibility();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISIBILITY__VALUE = eINSTANCE.getVisibility_Value();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISIBILITY__UNIT = eINSTANCE.getVisibility_Unit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.impl.WaveHeightImpl <em>Wave Height</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.impl.WaveHeightImpl
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getWaveHeight()
		 * @generated
		 */
		EClass WAVE_HEIGHT = eINSTANCE.getWaveHeight();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAVE_HEIGHT__VALUE = eINSTANCE.getWaveHeight_Value();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAVE_HEIGHT__UNIT = eINSTANCE.getWaveHeight_Unit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.Reference <em>Reference</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.Reference
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getReference()
		 * @generated
		 */
		EEnum REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.PressureUnit <em>Pressure Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.PressureUnit
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getPressureUnit()
		 * @generated
		 */
		EEnum PRESSURE_UNIT = eINSTANCE.getPressureUnit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.CloudType <em>Cloud Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.CloudType
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getCloudType()
		 * @generated
		 */
		EEnum CLOUD_TYPE = eINSTANCE.getCloudType();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.CloudCoverage <em>Cloud Coverage</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.CloudCoverage
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getCloudCoverage()
		 * @generated
		 */
		EEnum CLOUD_COVERAGE = eINSTANCE.getCloudCoverage();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.Beaufort <em>Beaufort</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.Beaufort
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getBeaufort()
		 * @generated
		 */
		EEnum BEAUFORT = eINSTANCE.getBeaufort();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.weather.SeaState <em>Sea State</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.weather.SeaState
		 * @see net.sf.seesea.model.core.weather.impl.WeatherPackageImpl#getSeaState()
		 * @generated
		 */
		EEnum SEA_STATE = eINSTANCE.getSeaState();

	}

} //WeatherPackage
