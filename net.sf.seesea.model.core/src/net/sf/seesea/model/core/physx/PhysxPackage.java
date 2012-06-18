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
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT__TIME = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPERATURE__TIME = MEASUREMENT__TIME;

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
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.SpeedImpl <em>Speed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.SpeedImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeed()
	 * @generated
	 */
	int SPEED = 1;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED__TIME = MEASUREMENT__TIME;

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
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.HeadingImpl <em>Heading</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.HeadingImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHeading()
	 * @generated
	 */
	int HEADING = 2;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING__TIME = MEASUREMENT__TIME;

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
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.ShipMovementVectorImpl <em>Ship Movement Vector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.ShipMovementVectorImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getShipMovementVector()
	 * @generated
	 */
	int SHIP_MOVEMENT_VECTOR = 4;

	/**
	 * The feature id for the '<em><b>Headings</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIP_MOVEMENT_VECTOR__HEADINGS = 0;

	/**
	 * The feature id for the '<em><b>Speeds</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIP_MOVEMENT_VECTOR__SPEEDS = 1;

	/**
	 * The number of structural features of the '<em>Ship Movement Vector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIP_MOVEMENT_VECTOR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.RelativeWindImpl <em>Relative Wind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.RelativeWindImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getRelativeWind()
	 * @generated
	 */
	int RELATIVE_WIND = 5;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_WIND__TIME = HEADING__TIME;

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
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl <em>Satellite Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSatelliteInfo()
	 * @generated
	 */
	int SATELLITE_INFO = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__ID = 0;

	/**
	 * The feature id for the '<em><b>Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__ELEVATION = 1;

	/**
	 * The feature id for the '<em><b>Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__AZIMUTH = 2;

	/**
	 * The feature id for the '<em><b>Signal Strength</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO__SIGNAL_STRENGTH = 3;

	/**
	 * The number of structural features of the '<em>Satellite Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITE_INFO_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.SatellitesVisibleImpl <em>Satellites Visible</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.SatellitesVisibleImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSatellitesVisible()
	 * @generated
	 */
	int SATELLITES_VISIBLE = 7;

	/**
	 * The feature id for the '<em><b>Satellite Info</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITES_VISIBLE__SATELLITE_INFO = 0;

	/**
	 * The number of structural features of the '<em>Satellites Visible</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SATELLITES_VISIBLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.Heading2DegreesEntryImpl <em>Heading2 Degrees Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.Heading2DegreesEntryImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHeading2DegreesEntry()
	 * @generated
	 */
	int HEADING2_DEGREES_ENTRY = 8;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING2_DEGREES_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING2_DEGREES_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Heading2 Degrees Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADING2_DEGREES_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.SpeedType2SpeedEntryImpl <em>Speed Type2 Speed Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.SpeedType2SpeedEntryImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeedType2SpeedEntry()
	 * @generated
	 */
	int SPEED_TYPE2_SPEED_ENTRY = 9;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_TYPE2_SPEED_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_TYPE2_SPEED_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Speed Type2 Speed Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_TYPE2_SPEED_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.SpeedMapImpl <em>Speed Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.SpeedMapImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeedMap()
	 * @generated
	 */
	int SPEED_MAP = 10;

	/**
	 * The number of structural features of the '<em>Speed Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEED_MAP_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.physx.impl.TimeImpl <em>Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.physx.impl.TimeImpl
	 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getTime()
	 * @generated
	 */
	int TIME = 11;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__DATE = 0;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__TIMEZONE = 1;

	/**
	 * The number of structural features of the '<em>Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FEATURE_COUNT = 2;

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
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.ShipMovementVector <em>Ship Movement Vector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ship Movement Vector</em>'.
	 * @see net.sf.seesea.model.core.physx.ShipMovementVector
	 * @generated
	 */
	EClass getShipMovementVector();

	/**
	 * Returns the meta object for the map '{@link net.sf.seesea.model.core.physx.ShipMovementVector#getHeadings <em>Headings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Headings</em>'.
	 * @see net.sf.seesea.model.core.physx.ShipMovementVector#getHeadings()
	 * @see #getShipMovementVector()
	 * @generated
	 */
	EReference getShipMovementVector_Headings();

	/**
	 * Returns the meta object for the map '{@link net.sf.seesea.model.core.physx.ShipMovementVector#getSpeeds <em>Speeds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Speeds</em>'.
	 * @see net.sf.seesea.model.core.physx.ShipMovementVector#getSpeeds()
	 * @see #getShipMovementVector()
	 * @generated
	 */
	EReference getShipMovementVector_Speeds();

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
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Heading2 Degrees Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Heading2 Degrees Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="net.sf.seesea.model.core.physx.HeadingType"
	 *        valueDataType="org.eclipse.emf.ecore.EDoubleObject"
	 * @generated
	 */
	EClass getHeading2DegreesEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getHeading2DegreesEntry()
	 * @generated
	 */
	EAttribute getHeading2DegreesEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getHeading2DegreesEntry()
	 * @generated
	 */
	EAttribute getHeading2DegreesEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Speed Type2 Speed Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Speed Type2 Speed Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="net.sf.seesea.model.core.physx.SpeedType"
	 *        valueType="net.sf.seesea.model.core.physx.Speed"
	 * @generated
	 */
	EClass getSpeedType2SpeedEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getSpeedType2SpeedEntry()
	 * @generated
	 */
	EAttribute getSpeedType2SpeedEntry_Key();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getSpeedType2SpeedEntry()
	 * @generated
	 */
	EReference getSpeedType2SpeedEntry_Value();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.physx.SpeedMap <em>Speed Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Speed Map</em>'.
	 * @see net.sf.seesea.model.core.physx.SpeedMap
	 * @generated
	 */
	EClass getSpeedMap();

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
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Time#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see net.sf.seesea.model.core.physx.Time#getDate()
	 * @see #getTime()
	 * @generated
	 */
	EAttribute getTime_Date();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.physx.Time#getTimezone <em>Timezone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timezone</em>'.
	 * @see net.sf.seesea.model.core.physx.Time#getTimezone()
	 * @see #getTime()
	 * @generated
	 */
	EAttribute getTime_Timezone();

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
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASUREMENT__TIME = eINSTANCE.getMeasurement_Time();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.ShipMovementVectorImpl <em>Ship Movement Vector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.ShipMovementVectorImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getShipMovementVector()
		 * @generated
		 */
		EClass SHIP_MOVEMENT_VECTOR = eINSTANCE.getShipMovementVector();

		/**
		 * The meta object literal for the '<em><b>Headings</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHIP_MOVEMENT_VECTOR__HEADINGS = eINSTANCE.getShipMovementVector_Headings();

		/**
		 * The meta object literal for the '<em><b>Speeds</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHIP_MOVEMENT_VECTOR__SPEEDS = eINSTANCE.getShipMovementVector_Speeds();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.Heading2DegreesEntryImpl <em>Heading2 Degrees Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.Heading2DegreesEntryImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getHeading2DegreesEntry()
		 * @generated
		 */
		EClass HEADING2_DEGREES_ENTRY = eINSTANCE.getHeading2DegreesEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HEADING2_DEGREES_ENTRY__KEY = eINSTANCE.getHeading2DegreesEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HEADING2_DEGREES_ENTRY__VALUE = eINSTANCE.getHeading2DegreesEntry_Value();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.SpeedType2SpeedEntryImpl <em>Speed Type2 Speed Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.SpeedType2SpeedEntryImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeedType2SpeedEntry()
		 * @generated
		 */
		EClass SPEED_TYPE2_SPEED_ENTRY = eINSTANCE.getSpeedType2SpeedEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEED_TYPE2_SPEED_ENTRY__KEY = eINSTANCE.getSpeedType2SpeedEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEED_TYPE2_SPEED_ENTRY__VALUE = eINSTANCE.getSpeedType2SpeedEntry_Value();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.physx.impl.SpeedMapImpl <em>Speed Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.physx.impl.SpeedMapImpl
		 * @see net.sf.seesea.model.core.physx.impl.PhysxPackageImpl#getSpeedMap()
		 * @generated
		 */
		EClass SPEED_MAP = eINSTANCE.getSpeedMap();

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
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME__DATE = eINSTANCE.getTime_Date();

		/**
		 * The meta object literal for the '<em><b>Timezone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME__TIMEZONE = eINSTANCE.getTime_Timezone();

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

	}

} //PhysxPackage
