/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.impl;

import net.sf.seesea.model.core.CorePackage;

import net.sf.seesea.model.core.geo.GeoPackage;

import net.sf.seesea.model.int1.base.BasePackage;

import net.sf.seesea.model.int1.base.impl.BasePackageImpl;

import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;

import net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl;

import net.sf.seesea.model.int1.lights.LightsPackage;

import net.sf.seesea.model.int1.lights.impl.LightsPackageImpl;

import net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage;

import net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl;

import net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWay;
import net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWayArtefact;
import net.sf.seesea.model.int1.tracksandroutes.AreaToBeAvoided;
import net.sf.seesea.model.int1.tracksandroutes.BorderType;
import net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute;
import net.sf.seesea.model.int1.tracksandroutes.InshoreTrafficZone;
import net.sf.seesea.model.int1.tracksandroutes.RoundAbout;
import net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesFactory;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;
import net.sf.seesea.model.int1.tracksandroutes.TrafficDirection;
import net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation;
import net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TracksandroutesPackageImpl extends EPackageImpl implements TracksandroutesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractSeaWayArtefactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trafficSeparationSchemeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trafficSeparationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deepWaterRouteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roundAboutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass areaToBeAvoidedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass seawaysContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inshoreTrafficZoneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractSeaWayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum borderTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum trafficDirectionEEnum = null;

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
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TracksandroutesPackageImpl() {
		super(eNS_URI, TracksandroutesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link TracksandroutesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TracksandroutesPackage init() {
		if (isInited) return (TracksandroutesPackage)EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI);

		// Obtain or create and register package
		TracksandroutesPackageImpl theTracksandroutesPackage = (TracksandroutesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TracksandroutesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TracksandroutesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		BuoysandbeaconsPackageImpl theBuoysandbeaconsPackage = (BuoysandbeaconsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI) instanceof BuoysandbeaconsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI) : BuoysandbeaconsPackage.eINSTANCE);
		LightsPackageImpl theLightsPackage = (LightsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI) instanceof LightsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI) : LightsPackage.eINSTANCE);
		BasePackageImpl theBasePackage = (BasePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI) instanceof BasePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI) : BasePackage.eINSTANCE);
		NatureofseabedPackageImpl theNatureofseabedPackage = (NatureofseabedPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI) instanceof NatureofseabedPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI) : NatureofseabedPackage.eINSTANCE);

		// Create package meta-data objects
		theTracksandroutesPackage.createPackageContents();
		theBuoysandbeaconsPackage.createPackageContents();
		theLightsPackage.createPackageContents();
		theBasePackage.createPackageContents();
		theNatureofseabedPackage.createPackageContents();

		// Initialize created meta-data
		theTracksandroutesPackage.initializePackageContents();
		theBuoysandbeaconsPackage.initializePackageContents();
		theLightsPackage.initializePackageContents();
		theBasePackage.initializePackageContents();
		theNatureofseabedPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTracksandroutesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TracksandroutesPackage.eNS_URI, theTracksandroutesPackage);
		return theTracksandroutesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractSeaWayArtefact() {
		return abstractSeaWayArtefactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrafficSeparationScheme() {
		return trafficSeparationSchemeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrafficSeparationScheme_TrafficSeparations() {
		return (EReference)trafficSeparationSchemeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrafficSeparationScheme_TrafficDirection() {
		return (EAttribute)trafficSeparationSchemeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrafficSeparation() {
		return trafficSeparationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrafficSeparation_BorderType() {
		return (EAttribute)trafficSeparationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrafficSeparation_IsNaturallyDivided() {
		return (EAttribute)trafficSeparationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeepWaterRoute() {
		return deepWaterRouteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeepWaterRoute_TrafficDirection() {
		return (EAttribute)deepWaterRouteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeepWaterRoute_RouteArea() {
		return (EReference)deepWaterRouteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoundAbout() {
		return roundAboutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoundAbout_AttachedSeaWays() {
		return (EReference)roundAboutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAreaToBeAvoided() {
		return areaToBeAvoidedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSeawaysContainer() {
		return seawaysContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSeawaysContainer_Seaways() {
		return (EReference)seawaysContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInshoreTrafficZone() {
		return inshoreTrafficZoneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractSeaWay() {
		return abstractSeaWayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBorderType() {
		return borderTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTrafficDirection() {
		return trafficDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracksandroutesFactory getTracksandroutesFactory() {
		return (TracksandroutesFactory)getEFactoryInstance();
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
		abstractSeaWayArtefactEClass = createEClass(ABSTRACT_SEA_WAY_ARTEFACT);

		trafficSeparationSchemeEClass = createEClass(TRAFFIC_SEPARATION_SCHEME);
		createEReference(trafficSeparationSchemeEClass, TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS);
		createEAttribute(trafficSeparationSchemeEClass, TRAFFIC_SEPARATION_SCHEME__TRAFFIC_DIRECTION);

		trafficSeparationEClass = createEClass(TRAFFIC_SEPARATION);
		createEAttribute(trafficSeparationEClass, TRAFFIC_SEPARATION__BORDER_TYPE);
		createEAttribute(trafficSeparationEClass, TRAFFIC_SEPARATION__IS_NATURALLY_DIVIDED);

		deepWaterRouteEClass = createEClass(DEEP_WATER_ROUTE);
		createEAttribute(deepWaterRouteEClass, DEEP_WATER_ROUTE__TRAFFIC_DIRECTION);
		createEReference(deepWaterRouteEClass, DEEP_WATER_ROUTE__ROUTE_AREA);

		roundAboutEClass = createEClass(ROUND_ABOUT);
		createEReference(roundAboutEClass, ROUND_ABOUT__ATTACHED_SEA_WAYS);

		areaToBeAvoidedEClass = createEClass(AREA_TO_BE_AVOIDED);

		seawaysContainerEClass = createEClass(SEAWAYS_CONTAINER);
		createEReference(seawaysContainerEClass, SEAWAYS_CONTAINER__SEAWAYS);

		inshoreTrafficZoneEClass = createEClass(INSHORE_TRAFFIC_ZONE);

		abstractSeaWayEClass = createEClass(ABSTRACT_SEA_WAY);

		// Create enums
		borderTypeEEnum = createEEnum(BORDER_TYPE);
		trafficDirectionEEnum = createEEnum(TRAFFIC_DIRECTION);
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
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		GeoPackage theGeoPackage = (GeoPackage)EPackage.Registry.INSTANCE.getEPackage(GeoPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractSeaWayArtefactEClass.getESuperTypes().add(theCorePackage.getModelObject());
		trafficSeparationSchemeEClass.getESuperTypes().add(this.getAbstractSeaWay());
		trafficSeparationEClass.getESuperTypes().add(theGeoPackage.getChartArea());
		trafficSeparationEClass.getESuperTypes().add(this.getAbstractSeaWayArtefact());
		deepWaterRouteEClass.getESuperTypes().add(this.getAbstractSeaWay());
		roundAboutEClass.getESuperTypes().add(this.getAbstractSeaWayArtefact());
		areaToBeAvoidedEClass.getESuperTypes().add(theGeoPackage.getChartArea());
		areaToBeAvoidedEClass.getESuperTypes().add(this.getAbstractSeaWayArtefact());
		seawaysContainerEClass.getESuperTypes().add(theCorePackage.getModelObject());
		inshoreTrafficZoneEClass.getESuperTypes().add(theGeoPackage.getChartArea());
		inshoreTrafficZoneEClass.getESuperTypes().add(this.getAbstractSeaWayArtefact());
		abstractSeaWayEClass.getESuperTypes().add(this.getAbstractSeaWayArtefact());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractSeaWayArtefactEClass, AbstractSeaWayArtefact.class, "AbstractSeaWayArtefact", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(trafficSeparationSchemeEClass, TrafficSeparationScheme.class, "TrafficSeparationScheme", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrafficSeparationScheme_TrafficSeparations(), this.getTrafficSeparation(), null, "trafficSeparations", null, 0, -1, TrafficSeparationScheme.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrafficSeparationScheme_TrafficDirection(), this.getTrafficDirection(), "trafficDirection", null, 0, 1, TrafficSeparationScheme.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(trafficSeparationEClass, TrafficSeparation.class, "TrafficSeparation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTrafficSeparation_BorderType(), this.getBorderType(), "borderType", null, 0, 1, TrafficSeparation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrafficSeparation_IsNaturallyDivided(), ecorePackage.getEBoolean(), "isNaturallyDivided", null, 0, 1, TrafficSeparation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deepWaterRouteEClass, DeepWaterRoute.class, "DeepWaterRoute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeepWaterRoute_TrafficDirection(), this.getTrafficDirection(), "trafficDirection", null, 0, 1, DeepWaterRoute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeepWaterRoute_RouteArea(), theGeoPackage.getChartArea(), null, "routeArea", null, 1, 1, DeepWaterRoute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roundAboutEClass, RoundAbout.class, "RoundAbout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRoundAbout_AttachedSeaWays(), this.getAbstractSeaWayArtefact(), null, "attachedSeaWays", null, 0, -1, RoundAbout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(areaToBeAvoidedEClass, AreaToBeAvoided.class, "AreaToBeAvoided", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(seawaysContainerEClass, SeawaysContainer.class, "SeawaysContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSeawaysContainer_Seaways(), this.getAbstractSeaWayArtefact(), null, "seaways", null, 0, 1, SeawaysContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inshoreTrafficZoneEClass, InshoreTrafficZone.class, "InshoreTrafficZone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractSeaWayEClass, AbstractSeaWay.class, "AbstractSeaWay", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(borderTypeEEnum, BorderType.class, "BorderType");
		addEEnumLiteral(borderTypeEEnum, BorderType.UNDEFINED);
		addEEnumLiteral(borderTypeEEnum, BorderType.INNER);
		addEEnumLiteral(borderTypeEEnum, BorderType.OUTER);

		initEEnum(trafficDirectionEEnum, TrafficDirection.class, "TrafficDirection");
		addEEnumLiteral(trafficDirectionEEnum, TrafficDirection.UNDEFINED);
		addEEnumLiteral(trafficDirectionEEnum, TrafficDirection.RIGHT_SIDE);
		addEEnumLiteral(trafficDirectionEEnum, TrafficDirection.LEFT_SIDE);

		// Create resource
		createResource(eNS_URI);
	}

} //TracksandroutesPackageImpl
