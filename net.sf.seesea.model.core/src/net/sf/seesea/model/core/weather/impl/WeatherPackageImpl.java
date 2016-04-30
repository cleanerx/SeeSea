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
package net.sf.seesea.model.core.weather.impl;

import net.sf.seesea.model.core.CorePackage;
import net.sf.seesea.model.core.data.DataPackage;
import net.sf.seesea.model.core.data.impl.DataPackageImpl;
import net.sf.seesea.model.core.diagramInterchange.DiagramInterchangePackage;
import net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl;
import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.impl.GeoPackageImpl;
import net.sf.seesea.model.core.geo.osm.OsmPackage;
import net.sf.seesea.model.core.geo.osm.impl.OsmPackageImpl;
import net.sf.seesea.model.core.impl.CorePackageImpl;
import net.sf.seesea.model.core.physx.PhysxPackage;
import net.sf.seesea.model.core.physx.impl.PhysxPackageImpl;
import net.sf.seesea.model.core.weather.BarometricPressure;
import net.sf.seesea.model.core.weather.Beaufort;
import net.sf.seesea.model.core.weather.CloudCoverage;
import net.sf.seesea.model.core.weather.CloudMeasurement;
import net.sf.seesea.model.core.weather.CloudType;
import net.sf.seesea.model.core.weather.PressureUnit;
import net.sf.seesea.model.core.weather.Reference;
import net.sf.seesea.model.core.weather.SeaState;
import net.sf.seesea.model.core.weather.Visibility;
import net.sf.seesea.model.core.weather.WaveHeight;
import net.sf.seesea.model.core.weather.WeatherFactory;
import net.sf.seesea.model.core.weather.WeatherPackage;
import net.sf.seesea.model.core.weather.WindMeasurement;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WeatherPackageImpl extends EPackageImpl implements WeatherPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass windMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass barometricPressureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cloudMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass visibilityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass waveHeightEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum referenceEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum pressureUnitEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum cloudTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum cloudCoverageEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum beaufortEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum seaStateEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see net.sf.seesea.model.core.weather.WeatherPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WeatherPackageImpl() {
		super(eNS_URI, WeatherFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link WeatherPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static WeatherPackage init() {
		if (isInited) return (WeatherPackage)EPackage.Registry.INSTANCE.getEPackage(WeatherPackage.eNS_URI);

		// Obtain or create and register package
		WeatherPackageImpl theWeatherPackage = (WeatherPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WeatherPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new WeatherPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		GeoPackageImpl theGeoPackage = (GeoPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeoPackage.eNS_URI) instanceof GeoPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeoPackage.eNS_URI) : GeoPackage.eINSTANCE);
		OsmPackageImpl theOsmPackage = (OsmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OsmPackage.eNS_URI) instanceof OsmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OsmPackage.eNS_URI) : OsmPackage.eINSTANCE);
		PhysxPackageImpl thePhysxPackage = (PhysxPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PhysxPackage.eNS_URI) instanceof PhysxPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PhysxPackage.eNS_URI) : PhysxPackage.eINSTANCE);
		DataPackageImpl theDataPackage = (DataPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DataPackage.eNS_URI) instanceof DataPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DataPackage.eNS_URI) : DataPackage.eINSTANCE);
		DiagramInterchangePackageImpl theDiagramInterchangePackage = (DiagramInterchangePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DiagramInterchangePackage.eNS_URI) instanceof DiagramInterchangePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DiagramInterchangePackage.eNS_URI) : DiagramInterchangePackage.eINSTANCE);

		// Create package meta-data objects
		theWeatherPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theGeoPackage.createPackageContents();
		theOsmPackage.createPackageContents();
		thePhysxPackage.createPackageContents();
		theDataPackage.createPackageContents();
		theDiagramInterchangePackage.createPackageContents();

		// Initialize created meta-data
		theWeatherPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theGeoPackage.initializePackageContents();
		theOsmPackage.initializePackageContents();
		thePhysxPackage.initializePackageContents();
		theDataPackage.initializePackageContents();
		theDiagramInterchangePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWeatherPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WeatherPackage.eNS_URI, theWeatherPackage);
		return theWeatherPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWindMeasurement() {
		return windMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWindMeasurement_Angle() {
		return (EAttribute)windMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWindMeasurement_Reference() {
		return (EAttribute)windMeasurementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBarometricPressure() {
		return barometricPressureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBarometricPressure_Value() {
		return (EAttribute)barometricPressureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBarometricPressure_Unit() {
		return (EAttribute)barometricPressureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCloudMeasurement() {
		return cloudMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCloudMeasurement_CloudCoverage() {
		return (EAttribute)cloudMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCloudMeasurement_CloudTypes() {
		return (EAttribute)cloudMeasurementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVisibility() {
		return visibilityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVisibility_Value() {
		return (EAttribute)visibilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVisibility_Unit() {
		return (EAttribute)visibilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWaveHeight() {
		return waveHeightEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWaveHeight_Value() {
		return (EAttribute)waveHeightEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWaveHeight_Unit() {
		return (EAttribute)waveHeightEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getReference() {
		return referenceEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPressureUnit() {
		return pressureUnitEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCloudType() {
		return cloudTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCloudCoverage() {
		return cloudCoverageEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBeaufort() {
		return beaufortEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSeaState() {
		return seaStateEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeatherFactory getWeatherFactory() {
		return (WeatherFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		windMeasurementEClass = createEClass(WIND_MEASUREMENT);
		createEAttribute(windMeasurementEClass, WIND_MEASUREMENT__ANGLE);
		createEAttribute(windMeasurementEClass, WIND_MEASUREMENT__REFERENCE);

		barometricPressureEClass = createEClass(BAROMETRIC_PRESSURE);
		createEAttribute(barometricPressureEClass, BAROMETRIC_PRESSURE__VALUE);
		createEAttribute(barometricPressureEClass, BAROMETRIC_PRESSURE__UNIT);

		cloudMeasurementEClass = createEClass(CLOUD_MEASUREMENT);
		createEAttribute(cloudMeasurementEClass, CLOUD_MEASUREMENT__CLOUD_COVERAGE);
		createEAttribute(cloudMeasurementEClass, CLOUD_MEASUREMENT__CLOUD_TYPES);

		visibilityEClass = createEClass(VISIBILITY);
		createEAttribute(visibilityEClass, VISIBILITY__VALUE);
		createEAttribute(visibilityEClass, VISIBILITY__UNIT);

		waveHeightEClass = createEClass(WAVE_HEIGHT);
		createEAttribute(waveHeightEClass, WAVE_HEIGHT__VALUE);
		createEAttribute(waveHeightEClass, WAVE_HEIGHT__UNIT);

		// Create enums
		referenceEEnum = createEEnum(REFERENCE);
		pressureUnitEEnum = createEEnum(PRESSURE_UNIT);
		cloudTypeEEnum = createEEnum(CLOUD_TYPE);
		cloudCoverageEEnum = createEEnum(CLOUD_COVERAGE);
		beaufortEEnum = createEEnum(BEAUFORT);
		seaStateEEnum = createEEnum(SEA_STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PhysxPackage thePhysxPackage = (PhysxPackage)EPackage.Registry.INSTANCE.getEPackage(PhysxPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		windMeasurementEClass.getESuperTypes().add(thePhysxPackage.getMeasurement());
		windMeasurementEClass.getESuperTypes().add(thePhysxPackage.getSpeed());
		barometricPressureEClass.getESuperTypes().add(thePhysxPackage.getMeasurement());
		cloudMeasurementEClass.getESuperTypes().add(thePhysxPackage.getMeasurement());
		visibilityEClass.getESuperTypes().add(thePhysxPackage.getMeasurement());

		// Initialize classes and features; add operations and parameters
		initEClass(windMeasurementEClass, WindMeasurement.class, "WindMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getWindMeasurement_Angle(), ecorePackage.getEDouble(), "angle", null, 0, 1, WindMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWindMeasurement_Reference(), this.getReference(), "reference", null, 0, 1, WindMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(barometricPressureEClass, BarometricPressure.class, "BarometricPressure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getBarometricPressure_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, BarometricPressure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getBarometricPressure_Unit(), this.getPressureUnit(), "unit", null, 0, 1, BarometricPressure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(cloudMeasurementEClass, CloudMeasurement.class, "CloudMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCloudMeasurement_CloudCoverage(), this.getCloudCoverage(), "cloudCoverage", null, 0, 1, CloudMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCloudMeasurement_CloudTypes(), this.getCloudType(), "cloudTypes", null, 0, -1, CloudMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(visibilityEClass, Visibility.class, "Visibility", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getVisibility_Value(), ecorePackage.getEDoubleObject(), "value", null, 0, 1, Visibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getVisibility_Unit(), thePhysxPackage.getLengthUnit(), "unit", null, 0, 1, Visibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(waveHeightEClass, WaveHeight.class, "WaveHeight", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getWaveHeight_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, WaveHeight.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWaveHeight_Unit(), thePhysxPackage.getLengthUnit(), "unit", null, 0, 1, WaveHeight.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(referenceEEnum, Reference.class, "Reference"); //$NON-NLS-1$
		addEEnumLiteral(referenceEEnum, Reference.RELATIVE);
		addEEnumLiteral(referenceEEnum, Reference.ABSOLUTE);
		addEEnumLiteral(referenceEEnum, Reference.UNKNOWN);

		initEEnum(pressureUnitEEnum, PressureUnit.class, "PressureUnit"); //$NON-NLS-1$
		addEEnumLiteral(pressureUnitEEnum, PressureUnit.UNDEFINED);
		addEEnumLiteral(pressureUnitEEnum, PressureUnit.TORR);
		addEEnumLiteral(pressureUnitEEnum, PressureUnit.HECTOPASCAL);

		initEEnum(cloudTypeEEnum, CloudType.class, "CloudType"); //$NON-NLS-1$
		addEEnumLiteral(cloudTypeEEnum, CloudType.UNDEFINED);
		addEEnumLiteral(cloudTypeEEnum, CloudType.CIRRUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.CIRROCUMULUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.CIRROSTRATUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.ALTOCUMULUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.ALTOSTRATUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.NIMBOSTRATUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.STRATOCUMULUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.STRATUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.CUMULUS);
		addEEnumLiteral(cloudTypeEEnum, CloudType.CUMULONIMBUS);

		initEEnum(cloudCoverageEEnum, CloudCoverage.class, "CloudCoverage"); //$NON-NLS-1$
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.UNDEFINED);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.CLOUDLESS);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.FAIR);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.PARTLY_CLOUDY_WEAK);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.PARTLY_CLOUDY_MEDIUM);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.PARTLY_CLOUDY_HEAVY);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.WEAKLY_CLOUDED);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.MEDIUM_CLOUDED);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.HEAVILY_CLOUDED);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.DULL);
		addEEnumLiteral(cloudCoverageEEnum, CloudCoverage.OBSCURED);

		initEEnum(beaufortEEnum, Beaufort.class, "Beaufort"); //$NON-NLS-1$
		addEEnumLiteral(beaufortEEnum, Beaufort.UNDEFINED);
		addEEnumLiteral(beaufortEEnum, Beaufort.CALM);
		addEEnumLiteral(beaufortEEnum, Beaufort.LIGHT_AIR);
		addEEnumLiteral(beaufortEEnum, Beaufort.LIGHT_BREEZE);
		addEEnumLiteral(beaufortEEnum, Beaufort.GENTLE_BREEZE);
		addEEnumLiteral(beaufortEEnum, Beaufort.MODERATE_BREEZE);
		addEEnumLiteral(beaufortEEnum, Beaufort.FRESH_BREEZE);
		addEEnumLiteral(beaufortEEnum, Beaufort.STRONG_BREEZE);
		addEEnumLiteral(beaufortEEnum, Beaufort.NEAR_GALE);
		addEEnumLiteral(beaufortEEnum, Beaufort.GALE);
		addEEnumLiteral(beaufortEEnum, Beaufort.STRONG_GALE);
		addEEnumLiteral(beaufortEEnum, Beaufort.STORM);
		addEEnumLiteral(beaufortEEnum, Beaufort.VIOLENT_STORM);
		addEEnumLiteral(beaufortEEnum, Beaufort.HURRICANE_FORCE);

		initEEnum(seaStateEEnum, SeaState.class, "SeaState"); //$NON-NLS-1$
		addEEnumLiteral(seaStateEEnum, SeaState.CALM_GLASSY);
		addEEnumLiteral(seaStateEEnum, SeaState.CALM_RIPPLED);
		addEEnumLiteral(seaStateEEnum, SeaState.SMOOTH_WAVELETS);
		addEEnumLiteral(seaStateEEnum, SeaState.SLIGHT);
		addEEnumLiteral(seaStateEEnum, SeaState.MODERATE);
		addEEnumLiteral(seaStateEEnum, SeaState.ROUGH);
		addEEnumLiteral(seaStateEEnum, SeaState.VERY_ROUGH);
		addEEnumLiteral(seaStateEEnum, SeaState.HIGH);
		addEEnumLiteral(seaStateEEnum, SeaState.VERY_HIGH);
		addEEnumLiteral(seaStateEEnum, SeaState.PHENOMENAL);
		addEEnumLiteral(seaStateEEnum, SeaState.UNDEFINED);
	}

} //WeatherPackageImpl
