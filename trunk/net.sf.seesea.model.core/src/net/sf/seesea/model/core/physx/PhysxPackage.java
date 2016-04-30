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
package net.sf.seesea.model.core.physx;

import net.sf.seesea.model.core.CorePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see net.sf.seesea.model.core.physx.PhysxFactory
 * @model kind="package"
 * @generated
 */
public interface PhysxPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "physx";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "physx";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "physx";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PhysxPackage eINSTANCE = net.sf.seesea.model.core.physx.impl.PhysxPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.MeasurementImpl <em>Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.MeasurementImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getMeasurement()
	 * @generated
	 */
	int MEASUREMENT = 3;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.TemperatureImpl <em>Temperature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.TemperatureImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getTemperature()
	 * @generated
	 */
	int TEMPERATURE = 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.SpeedImpl <em>Speed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.SpeedImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeed()
	 * @generated
	 */
	int SPEED = 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.HeadingImpl <em>Heading</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.HeadingImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHeading()
	 * @generated
	 */
	int HEADING = 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.RelativeWindImpl <em>Relative Wind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.RelativeWindImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getRelativeWind()
	 * @generated
	 */
	int RELATIVE_WIND = 4;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl <em>Satellite Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSatelliteInfo()
	 * @generated
	 */
	int SATELLITE_INFO = 5;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.SatellitesVisibleImpl <em>Satellites Visible</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.SatellitesVisibleImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSatellitesVisible()
	 * @generated
	 */
	int SATELLITES_VISIBLE = 6;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.TimeImpl <em>Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.TimeImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getTime()
	 * @generated
	 */
	int TIME = 7;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__SENSOR_ID = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__TIME = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__TIMEZONE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__VALID = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPERATURE__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPERATURE__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPERATURE__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPERATURE__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPERATURE__VALUE = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPERATURE__UNIT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Temperature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPERATURE_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED__SPEED = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Speed Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED__SPEED_UNIT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Speed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Degrees</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING__DEGREES = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Heading Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING__HEADING_TYPE = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Heading</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__SENSOR_ID = HEADING__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__TIME = HEADING__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__TIMEZONE = HEADING__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__VALID = HEADING__VALID;

	/**
	 * The feature id for the '<em><b>Degrees</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__DEGREES = HEADING__DEGREES;

	/**
	 * The feature id for the '<em><b>Heading Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__HEADING_TYPE = HEADING__HEADING_TYPE;

	/**
	 * The feature id for the '<em><b>Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__SPEED = HEADING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Speed Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__SPEED_UNIT = HEADING_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bow Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__BOW_ORIENTATION = HEADING_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Relative Wind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND_FEATURE_COUNT = HEADING_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__ID = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__ELEVATION = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__AZIMUTH = MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Signal Strength</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__SIGNAL_STRENGTH = MEASUREMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Satellite Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITES_VISIBLE__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITES_VISIBLE__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITES_VISIBLE__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITES_VISIBLE__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Satellite Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITES_VISIBLE__SATELLITE_INFO = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Satellites Visible</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITES_VISIBLE_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__VALID = MEASUREMENT__VALID;

	/**
	 * The number of structural features of the '<em>Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.DistanceImpl <em>Distance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.DistanceImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getDistance()
	 * @generated
	 */
	int DISTANCE = 8;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTANCE__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTANCE__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTANCE__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTANCE__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTANCE__VALUE = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTANCE__UNIT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Distance Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTANCE__DISTANCE_TYPE = MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Distance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTANCE_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.CompositeMeasurementImpl <em>Composite Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.CompositeMeasurementImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getCompositeMeasurement()
	 * @generated
	 */
	int COMPOSITE_MEASUREMENT = 9;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_MEASUREMENT__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_MEASUREMENT__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_MEASUREMENT__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_MEASUREMENT__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Measurements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_MEASUREMENT__MEASUREMENTS = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_MEASUREMENT_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.RelativeSpeedImpl <em>Relative Speed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.RelativeSpeedImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getRelativeSpeed()
	 * @generated
	 */
	int RELATIVE_SPEED = 10;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_SPEED__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_SPEED__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_SPEED__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_SPEED__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_SPEED__KEY = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_SPEED__VALUE = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Relative Speed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_SPEED_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.AccelerationImpl <em>Acceleration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.AccelerationImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getAcceleration()
	 * @generated
	 */
	int ACCELERATION = 11;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCELERATION__SENSOR_ID = MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCELERATION__TIME = MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCELERATION__TIMEZONE = MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCELERATION__VALID = MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCELERATION__X = MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCELERATION__Y = MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Z</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCELERATION__Z = MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Acceleration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACCELERATION_FEATURE_COUNT = MEASUREMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.TemperatureUnit <em>Temperature Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.TemperatureUnit
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getTemperatureUnit()
	 * @generated
	 */
	int TEMPERATURE_UNIT = 12;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.HeadingType <em>Heading Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.HeadingType
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHeadingType()
	 * @generated
	 */
	int HEADING_TYPE = 13;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.SpeedUnit <em>Speed Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.SpeedUnit
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeedUnit()
	 * @generated
	 */
	int SPEED_UNIT = 14;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.HandOrientation <em>Hand Orientation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.HandOrientation
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHandOrientation()
	 * @generated
	 */
	int HAND_ORIENTATION = 15;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.LengthUnit <em>Length Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.LengthUnit
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getLengthUnit()
	 * @generated
	 */
	int LENGTH_UNIT = 16;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.SpeedType <em>Speed Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.SpeedType
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeedType()
	 * @generated
	 */
	int SPEED_TYPE = 17;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.DistanceType <em>Distance Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.DistanceType
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getDistanceType()
	 * @generated
	 */
	int DISTANCE_TYPE = 18;

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.Temperature <em>Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Temperature</em>'.
	 * @see net.sf.seesea.model.core.physx.Temperature
	 * @generated
	 */
	EClass getTemperature();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Temperature#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.sf.seesea.model.core.physx.Temperature#getValue()
	 * @see #getTemperature()
	 * @generated
	 */
	EAttribute getTemperature_Value();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Temperature#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see net.sf.seesea.model.core.physx.Temperature#getUnit()
	 * @see #getTemperature()
	 * @generated
	 */
	EAttribute getTemperature_Unit();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.Speed <em>Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Speed</em>'.
	 * @see net.sf.seesea.model.core.physx.Speed
	 * @generated
	 */
	EClass getSpeed();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Speed#getSpeed <em>Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Speed</em>'.
	 * @see net.sf.seesea.model.core.physx.Speed#getSpeed()
	 * @see #getSpeed()
	 * @generated
	 */
	EAttribute getSpeed_Speed();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Speed#getSpeedUnit <em>Speed Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Speed Unit</em>'.
	 * @see net.sf.seesea.model.core.physx.Speed#getSpeedUnit()
	 * @see #getSpeed()
	 * @generated
	 */
	EAttribute getSpeed_SpeedUnit();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.Heading <em>Heading</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Heading</em>'.
	 * @see net.sf.seesea.model.core.physx.Heading
	 * @generated
	 */
	EClass getHeading();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Heading#getDegrees <em>Degrees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Degrees</em>'.
	 * @see net.sf.seesea.model.core.physx.Heading#getDegrees()
	 * @see #getHeading()
	 * @generated
	 */
	EAttribute getHeading_Degrees();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Heading#getHeadingType <em>Heading Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Heading Type</em>'.
	 * @see net.sf.seesea.model.core.physx.Heading#getHeadingType()
	 * @see #getHeading()
	 * @generated
	 */
	EAttribute getHeading_HeadingType();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.Measurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurement</em>'.
	 * @see net.sf.seesea.model.core.physx.Measurement
	 * @generated
	 */
	EClass getMeasurement();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Measurement#getSensorID <em>Sensor ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sensor ID</em>'.
	 * @see net.sf.seesea.model.core.physx.Measurement#getSensorID()
	 * @see #getMeasurement()
	 * @generated
	 */
	EAttribute getMeasurement_SensorID();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Measurement#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see net.sf.seesea.model.core.physx.Measurement#getTime()
	 * @see #getMeasurement()
	 * @generated
	 */
	EAttribute getMeasurement_Time();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Measurement#getTimezone <em>Timezone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timezone</em>'.
	 * @see net.sf.seesea.model.core.physx.Measurement#getTimezone()
	 * @see #getMeasurement()
	 * @generated
	 */
	EAttribute getMeasurement_Timezone();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Measurement#isValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid</em>'.
	 * @see net.sf.seesea.model.core.physx.Measurement#isValid()
	 * @see #getMeasurement()
	 * @generated
	 */
	EAttribute getMeasurement_Valid();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.RelativeWind <em>Relative Wind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relative Wind</em>'.
	 * @see net.sf.seesea.model.core.physx.RelativeWind
	 * @generated
	 */
	EClass getRelativeWind();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.RelativeWind#getBowOrientation <em>Bow Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bow Orientation</em>'.
	 * @see net.sf.seesea.model.core.physx.RelativeWind#getBowOrientation()
	 * @see #getRelativeWind()
	 * @generated
	 */
	EAttribute getRelativeWind_BowOrientation();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.SatelliteInfo <em>Satellite Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Satellite Info</em>'.
	 * @see net.sf.seesea.model.core.physx.SatelliteInfo
	 * @generated
	 */
	EClass getSatelliteInfo();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.SatelliteInfo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see net.sf.seesea.model.core.physx.SatelliteInfo#getId()
	 * @see #getSatelliteInfo()
	 * @generated
	 */
	EAttribute getSatelliteInfo_Id();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.SatelliteInfo#getElevation <em>Elevation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Elevation</em>'.
	 * @see net.sf.seesea.model.core.physx.SatelliteInfo#getElevation()
	 * @see #getSatelliteInfo()
	 * @generated
	 */
	EAttribute getSatelliteInfo_Elevation();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.SatelliteInfo#getAzimuth <em>Azimuth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Azimuth</em>'.
	 * @see net.sf.seesea.model.core.physx.SatelliteInfo#getAzimuth()
	 * @see #getSatelliteInfo()
	 * @generated
	 */
	EAttribute getSatelliteInfo_Azimuth();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.SatelliteInfo#getSignalStrength <em>Signal Strength</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signal Strength</em>'.
	 * @see net.sf.seesea.model.core.physx.SatelliteInfo#getSignalStrength()
	 * @see #getSatelliteInfo()
	 * @generated
	 */
	EAttribute getSatelliteInfo_SignalStrength();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.SatellitesVisible <em>Satellites Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Satellites Visible</em>'.
	 * @see net.sf.seesea.model.core.physx.SatellitesVisible
	 * @generated
	 */
	EClass getSatellitesVisible();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.physx.SatellitesVisible#getSatelliteInfo <em>Satellite Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Satellite Info</em>'.
	 * @see net.sf.seesea.model.core.physx.SatellitesVisible#getSatelliteInfo()
	 * @see #getSatellitesVisible()
	 * @generated
	 */
	EReference getSatellitesVisible_SatelliteInfo();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.Time <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time</em>'.
	 * @see net.sf.seesea.model.core.physx.Time
	 * @generated
	 */
	EClass getTime();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.Distance <em>Distance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Distance</em>'.
	 * @see net.sf.seesea.model.core.physx.Distance
	 * @generated
	 */
	EClass getDistance();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Distance#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.sf.seesea.model.core.physx.Distance#getValue()
	 * @see #getDistance()
	 * @generated
	 */
	EAttribute getDistance_Value();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Distance#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see net.sf.seesea.model.core.physx.Distance#getUnit()
	 * @see #getDistance()
	 * @generated
	 */
	EAttribute getDistance_Unit();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Distance#getDistanceType <em>Distance Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distance Type</em>'.
	 * @see net.sf.seesea.model.core.physx.Distance#getDistanceType()
	 * @see #getDistance()
	 * @generated
	 */
	EAttribute getDistance_DistanceType();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.CompositeMeasurement <em>Composite Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Measurement</em>'.
	 * @see net.sf.seesea.model.core.physx.CompositeMeasurement
	 * @generated
	 */
	EClass getCompositeMeasurement();

	/**
	 * Returns the meta object for the reference list '{@link net.sf.seesea.model.core.physx.CompositeMeasurement#getMeasurements <em>Measurements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Measurements</em>'.
	 * @see net.sf.seesea.model.core.physx.CompositeMeasurement#getMeasurements()
	 * @see #getCompositeMeasurement()
	 * @generated
	 */
	EReference getCompositeMeasurement_Measurements();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.RelativeSpeed <em>Relative Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relative Speed</em>'.
	 * @see net.sf.seesea.model.core.physx.RelativeSpeed
	 * @generated
	 */
	EClass getRelativeSpeed();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.RelativeSpeed#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see net.sf.seesea.model.core.physx.RelativeSpeed#getKey()
	 * @see #getRelativeSpeed()
	 * @generated
	 */
	EAttribute getRelativeSpeed_Key();

	/**
	 * Returns the meta object for the reference '{@link net.sf.seesea.model.core.physx.RelativeSpeed#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see net.sf.seesea.model.core.physx.RelativeSpeed#getValue()
	 * @see #getRelativeSpeed()
	 * @generated
	 */
	EReference getRelativeSpeed_Value();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.Acceleration <em>Acceleration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Acceleration</em>'.
	 * @see net.sf.seesea.model.core.physx.Acceleration
	 * @generated
	 */
	EClass getAcceleration();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Acceleration#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see net.sf.seesea.model.core.physx.Acceleration#getX()
	 * @see #getAcceleration()
	 * @generated
	 */
	EAttribute getAcceleration_X();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Acceleration#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see net.sf.seesea.model.core.physx.Acceleration#getY()
	 * @see #getAcceleration()
	 * @generated
	 */
	EAttribute getAcceleration_Y();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Acceleration#getZ <em>Z</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Z</em>'.
	 * @see net.sf.seesea.model.core.physx.Acceleration#getZ()
	 * @see #getAcceleration()
	 * @generated
	 */
	EAttribute getAcceleration_Z();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.physx.TemperatureUnit <em>Temperature Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Temperature Unit</em>'.
	 * @see net.sf.seesea.model.core.physx.TemperatureUnit
	 * @generated
	 */
	EEnum getTemperatureUnit();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.physx.HeadingType <em>Heading Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Heading Type</em>'.
	 * @see net.sf.seesea.model.core.physx.HeadingType
	 * @generated
	 */
	EEnum getHeadingType();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.physx.SpeedUnit <em>Speed Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Speed Unit</em>'.
	 * @see net.sf.seesea.model.core.physx.SpeedUnit
	 * @generated
	 */
	EEnum getSpeedUnit();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.physx.HandOrientation <em>Hand Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Hand Orientation</em>'.
	 * @see net.sf.seesea.model.core.physx.HandOrientation
	 * @generated
	 */
	EEnum getHandOrientation();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.physx.LengthUnit <em>Length Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Length Unit</em>'.
	 * @see net.sf.seesea.model.core.physx.LengthUnit
	 * @generated
	 */
	EEnum getLengthUnit();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.physx.SpeedType <em>Speed Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Speed Type</em>'.
	 * @see net.sf.seesea.model.core.physx.SpeedType
	 * @generated
	 */
	EEnum getSpeedType();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.physx.DistanceType <em>Distance Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Distance Type</em>'.
	 * @see net.sf.seesea.model.core.physx.DistanceType
	 * @generated
	 */
	EEnum getDistanceType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PhysxFactory getPhysxFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.TemperatureImpl <em>Temperature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.TemperatureImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getTemperature()
		 * @generated
		 */
		EClass TEMPERATURE = eINSTANCE.getTemperature();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPERATURE__VALUE = eINSTANCE.getTemperature_Value();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPERATURE__UNIT = eINSTANCE.getTemperature_Unit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.SpeedImpl <em>Speed</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.SpeedImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeed()
		 * @generated
		 */
		EClass SPEED = eINSTANCE.getSpeed();

		/**
		 * The meta object literal for the '<em><b>Speed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED__SPEED = eINSTANCE.getSpeed_Speed();

		/**
		 * The meta object literal for the '<em><b>Speed Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED__SPEED_UNIT = eINSTANCE.getSpeed_SpeedUnit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.HeadingImpl <em>Heading</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.HeadingImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHeading()
		 * @generated
		 */
		EClass HEADING = eINSTANCE.getHeading();

		/**
		 * The meta object literal for the '<em><b>Degrees</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HEADING__DEGREES = eINSTANCE.getHeading_Degrees();

		/**
		 * The meta object literal for the '<em><b>Heading Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HEADING__HEADING_TYPE = eINSTANCE.getHeading_HeadingType();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.MeasurementImpl <em>Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.MeasurementImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getMeasurement()
		 * @generated
		 */
		EClass MEASUREMENT = eINSTANCE.getMeasurement();

		/**
		 * The meta object literal for the '<em><b>Sensor ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__SENSOR_ID = eINSTANCE.getMeasurement_SensorID();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__TIME = eINSTANCE.getMeasurement_Time();

		/**
		 * The meta object literal for the '<em><b>Timezone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__TIMEZONE = eINSTANCE.getMeasurement_Timezone();

		/**
		 * The meta object literal for the '<em><b>Valid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__VALID = eINSTANCE.getMeasurement_Valid();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.RelativeWindImpl <em>Relative Wind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.RelativeWindImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getRelativeWind()
		 * @generated
		 */
		EClass RELATIVE_WIND = eINSTANCE.getRelativeWind();

		/**
		 * The meta object literal for the '<em><b>Bow Orientation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIVE_WIND__BOW_ORIENTATION = eINSTANCE.getRelativeWind_BowOrientation();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl <em>Satellite Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSatelliteInfo()
		 * @generated
		 */
		EClass SATELLITE_INFO = eINSTANCE.getSatelliteInfo();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SATELLITE_INFO__ID = eINSTANCE.getSatelliteInfo_Id();

		/**
		 * The meta object literal for the '<em><b>Elevation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SATELLITE_INFO__ELEVATION = eINSTANCE.getSatelliteInfo_Elevation();

		/**
		 * The meta object literal for the '<em><b>Azimuth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SATELLITE_INFO__AZIMUTH = eINSTANCE.getSatelliteInfo_Azimuth();

		/**
		 * The meta object literal for the '<em><b>Signal Strength</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SATELLITE_INFO__SIGNAL_STRENGTH = eINSTANCE.getSatelliteInfo_SignalStrength();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.SatellitesVisibleImpl <em>Satellites Visible</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.SatellitesVisibleImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSatellitesVisible()
		 * @generated
		 */
		EClass SATELLITES_VISIBLE = eINSTANCE.getSatellitesVisible();

		/**
		 * The meta object literal for the '<em><b>Satellite Info</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SATELLITES_VISIBLE__SATELLITE_INFO = eINSTANCE.getSatellitesVisible_SatelliteInfo();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.TimeImpl <em>Time</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.TimeImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getTime()
		 * @generated
		 */
		EClass TIME = eINSTANCE.getTime();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.DistanceImpl <em>Distance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.DistanceImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getDistance()
		 * @generated
		 */
		EClass DISTANCE = eINSTANCE.getDistance();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISTANCE__VALUE = eINSTANCE.getDistance_Value();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISTANCE__UNIT = eINSTANCE.getDistance_Unit();

		/**
		 * The meta object literal for the '<em><b>Distance Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISTANCE__DISTANCE_TYPE = eINSTANCE.getDistance_DistanceType();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.CompositeMeasurementImpl <em>Composite Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.CompositeMeasurementImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getCompositeMeasurement()
		 * @generated
		 */
		EClass COMPOSITE_MEASUREMENT = eINSTANCE.getCompositeMeasurement();

		/**
		 * The meta object literal for the '<em><b>Measurements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_MEASUREMENT__MEASUREMENTS = eINSTANCE.getCompositeMeasurement_Measurements();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.RelativeSpeedImpl <em>Relative Speed</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.RelativeSpeedImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getRelativeSpeed()
		 * @generated
		 */
		EClass RELATIVE_SPEED = eINSTANCE.getRelativeSpeed();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIVE_SPEED__KEY = eINSTANCE.getRelativeSpeed_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIVE_SPEED__VALUE = eINSTANCE.getRelativeSpeed_Value();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.AccelerationImpl <em>Acceleration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.AccelerationImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getAcceleration()
		 * @generated
		 */
		EClass ACCELERATION = eINSTANCE.getAcceleration();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCELERATION__X = eINSTANCE.getAcceleration_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCELERATION__Y = eINSTANCE.getAcceleration_Y();

		/**
		 * The meta object literal for the '<em><b>Z</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACCELERATION__Z = eINSTANCE.getAcceleration_Z();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.TemperatureUnit <em>Temperature Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.TemperatureUnit
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getTemperatureUnit()
		 * @generated
		 */
		EEnum TEMPERATURE_UNIT = eINSTANCE.getTemperatureUnit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.HeadingType <em>Heading Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.HeadingType
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHeadingType()
		 * @generated
		 */
		EEnum HEADING_TYPE = eINSTANCE.getHeadingType();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.SpeedUnit <em>Speed Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.SpeedUnit
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeedUnit()
		 * @generated
		 */
		EEnum SPEED_UNIT = eINSTANCE.getSpeedUnit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.HandOrientation <em>Hand Orientation</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.HandOrientation
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHandOrientation()
		 * @generated
		 */
		EEnum HAND_ORIENTATION = eINSTANCE.getHandOrientation();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.LengthUnit <em>Length Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.LengthUnit
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getLengthUnit()
		 * @generated
		 */
		EEnum LENGTH_UNIT = eINSTANCE.getLengthUnit();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.SpeedType <em>Speed Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.SpeedType
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeedType()
		 * @generated
		 */
		EEnum SPEED_TYPE = eINSTANCE.getSpeedType();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.DistanceType <em>Distance Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.DistanceType
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getDistanceType()
		 * @generated
		 */
		EEnum DISTANCE_TYPE = eINSTANCE.getDistanceType();

	}

} //PhysxPackage
