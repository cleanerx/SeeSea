/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.impl;

import net.sf.seesea.model.core.CorePackage;
import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.int1.base.BasePackage;
import net.sf.seesea.model.int1.base.impl.BasePackageImpl;
import net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon;
import net.sf.seesea.model.int1.buoysandbeacons.Beacon;
import net.sf.seesea.model.int1.buoysandbeacons.Buoy;
import net.sf.seesea.model.int1.buoysandbeacons.BuoyType;
import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsFactory;
import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;
import net.sf.seesea.model.int1.buoysandbeacons.Shape;
import net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy;
import net.sf.seesea.model.int1.buoysandbeacons.Topmark;
import net.sf.seesea.model.int1.buoysandbeacons.TopmarkType;
import net.sf.seesea.model.int1.lights.LightsPackage;
import net.sf.seesea.model.int1.lights.impl.LightsPackageImpl;

import net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage;
import net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;
import net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl;
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
public class BuoysandbeaconsPackageImpl extends EPackageImpl implements BuoysandbeaconsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractCommonBuoyBeaconEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass buoyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass beaconEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specialBuoyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass topmarkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum buoyTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum shapeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum topmarkTypeEEnum = null;

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
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BuoysandbeaconsPackageImpl() {
		super(eNS_URI, BuoysandbeaconsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link BuoysandbeaconsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BuoysandbeaconsPackage init() {
		if (isInited) return (BuoysandbeaconsPackage)EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI);

		// Obtain or create and register package
		BuoysandbeaconsPackageImpl theBuoysandbeaconsPackage = (BuoysandbeaconsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BuoysandbeaconsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BuoysandbeaconsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		LightsPackageImpl theLightsPackage = (LightsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI) instanceof LightsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI) : LightsPackage.eINSTANCE);
		BasePackageImpl theBasePackage = (BasePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI) instanceof BasePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI) : BasePackage.eINSTANCE);
		NatureofseabedPackageImpl theNatureofseabedPackage = (NatureofseabedPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI) instanceof NatureofseabedPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI) : NatureofseabedPackage.eINSTANCE);
		TracksandroutesPackageImpl theTracksandroutesPackage = (TracksandroutesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI) instanceof TracksandroutesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI) : TracksandroutesPackage.eINSTANCE);

		// Create package meta-data objects
		theBuoysandbeaconsPackage.createPackageContents();
		theLightsPackage.createPackageContents();
		theBasePackage.createPackageContents();
		theNatureofseabedPackage.createPackageContents();
		theTracksandroutesPackage.createPackageContents();

		// Initialize created meta-data
		theBuoysandbeaconsPackage.initializePackageContents();
		theLightsPackage.initializePackageContents();
		theBasePackage.initializePackageContents();
		theNatureofseabedPackage.initializePackageContents();
		theTracksandroutesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBuoysandbeaconsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BuoysandbeaconsPackage.eNS_URI, theBuoysandbeaconsPackage);
		return theBuoysandbeaconsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractCommonBuoyBeacon() {
		return abstractCommonBuoyBeaconEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCommonBuoyBeacon_Position() {
		return (EAttribute)abstractCommonBuoyBeaconEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCommonBuoyBeacon_Color() {
		return (EAttribute)abstractCommonBuoyBeaconEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCommonBuoyBeacon_ColorType() {
		return (EAttribute)abstractCommonBuoyBeaconEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCommonBuoyBeacon_Reflecting() {
		return (EAttribute)abstractCommonBuoyBeaconEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractCommonBuoyBeacon_Topmark() {
		return (EReference)abstractCommonBuoyBeaconEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCommonBuoyBeacon_Radarreflector() {
		return (EAttribute)abstractCommonBuoyBeaconEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCommonBuoyBeacon_Type() {
		return (EAttribute)abstractCommonBuoyBeaconEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBuoy() {
		return buoyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBuoy_Designation() {
		return (EAttribute)buoyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBuoy_Shape() {
		return (EAttribute)buoyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBeacon() {
		return beaconEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeacon_OnSubmergedRock() {
		return (EAttribute)beaconEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecialBuoy() {
		return specialBuoyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecialBuoy_Purpose() {
		return (EAttribute)specialBuoyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecialBuoy_Private() {
		return (EAttribute)specialBuoyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecialBuoy_Seasonal() {
		return (EAttribute)specialBuoyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTopmark() {
		return topmarkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopmark_TopmarkParts() {
		return (EAttribute)topmarkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopmark_TopmarkColor() {
		return (EAttribute)topmarkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBuoyType() {
		return buoyTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getShape() {
		return shapeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTopmarkType() {
		return topmarkTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuoysandbeaconsFactory getBuoysandbeaconsFactory() {
		return (BuoysandbeaconsFactory)getEFactoryInstance();
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
		abstractCommonBuoyBeaconEClass = createEClass(ABSTRACT_COMMON_BUOY_BEACON);
		createEAttribute(abstractCommonBuoyBeaconEClass, ABSTRACT_COMMON_BUOY_BEACON__POSITION);
		createEAttribute(abstractCommonBuoyBeaconEClass, ABSTRACT_COMMON_BUOY_BEACON__COLOR);
		createEAttribute(abstractCommonBuoyBeaconEClass, ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE);
		createEAttribute(abstractCommonBuoyBeaconEClass, ABSTRACT_COMMON_BUOY_BEACON__REFLECTING);
		createEAttribute(abstractCommonBuoyBeaconEClass, ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR);
		createEAttribute(abstractCommonBuoyBeaconEClass, ABSTRACT_COMMON_BUOY_BEACON__TYPE);
		createEReference(abstractCommonBuoyBeaconEClass, ABSTRACT_COMMON_BUOY_BEACON__TOPMARK);

		buoyEClass = createEClass(BUOY);
		createEAttribute(buoyEClass, BUOY__DESIGNATION);
		createEAttribute(buoyEClass, BUOY__SHAPE);

		beaconEClass = createEClass(BEACON);
		createEAttribute(beaconEClass, BEACON__ON_SUBMERGED_ROCK);

		specialBuoyEClass = createEClass(SPECIAL_BUOY);
		createEAttribute(specialBuoyEClass, SPECIAL_BUOY__PURPOSE);
		createEAttribute(specialBuoyEClass, SPECIAL_BUOY__PRIVATE);
		createEAttribute(specialBuoyEClass, SPECIAL_BUOY__SEASONAL);

		topmarkEClass = createEClass(TOPMARK);
		createEAttribute(topmarkEClass, TOPMARK__TOPMARK_PARTS);
		createEAttribute(topmarkEClass, TOPMARK__TOPMARK_COLOR);

		// Create enums
		buoyTypeEEnum = createEEnum(BUOY_TYPE);
		shapeEEnum = createEEnum(SHAPE);
		topmarkTypeEEnum = createEEnum(TOPMARK_TYPE);
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
		LightsPackage theLightsPackage = (LightsPackage)EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI);
		BasePackage theBasePackage = (BasePackage)EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI);
		GeoPackage theGeoPackage = (GeoPackage)EPackage.Registry.INSTANCE.getEPackage(GeoPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractCommonBuoyBeaconEClass.getESuperTypes().add(theLightsPackage.getLightCharacter());
		abstractCommonBuoyBeaconEClass.getESuperTypes().add(theBasePackage.getAbstractSeamark());
		abstractCommonBuoyBeaconEClass.getESuperTypes().add(theGeoPackage.getNamedPosition());
		buoyEClass.getESuperTypes().add(this.getAbstractCommonBuoyBeacon());
		beaconEClass.getESuperTypes().add(this.getAbstractCommonBuoyBeacon());
		specialBuoyEClass.getESuperTypes().add(this.getBuoy());
		topmarkEClass.getESuperTypes().add(theCorePackage.getModelObject());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractCommonBuoyBeaconEClass, AbstractCommonBuoyBeacon.class, "AbstractCommonBuoyBeacon", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractCommonBuoyBeacon_Position(), ecorePackage.getEString(), "position", null, 0, 1, AbstractCommonBuoyBeacon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractCommonBuoyBeacon_Color(), theLightsPackage.getColor(), "color", null, 0, -1, AbstractCommonBuoyBeacon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractCommonBuoyBeacon_ColorType(), theLightsPackage.getOrientation(), "colorType", null, 0, 1, AbstractCommonBuoyBeacon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractCommonBuoyBeacon_Reflecting(), ecorePackage.getEBoolean(), "reflecting", null, 0, 1, AbstractCommonBuoyBeacon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractCommonBuoyBeacon_Radarreflector(), ecorePackage.getEBoolean(), "radarreflector", null, 0, 1, AbstractCommonBuoyBeacon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractCommonBuoyBeacon_Type(), this.getBuoyType(), "type", null, 0, 1, AbstractCommonBuoyBeacon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractCommonBuoyBeacon_Topmark(), this.getTopmark(), null, "topmark", null, 0, 1, AbstractCommonBuoyBeacon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(buoyEClass, Buoy.class, "Buoy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuoy_Designation(), ecorePackage.getEString(), "designation", null, 0, 1, Buoy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuoy_Shape(), this.getShape(), "shape", null, 0, 1, Buoy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(beaconEClass, Beacon.class, "Beacon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBeacon_OnSubmergedRock(), ecorePackage.getEBoolean(), "onSubmergedRock", null, 0, 1, Beacon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(specialBuoyEClass, SpecialBuoy.class, "SpecialBuoy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpecialBuoy_Purpose(), ecorePackage.getEString(), "purpose", null, 0, 1, SpecialBuoy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecialBuoy_Private(), ecorePackage.getEBoolean(), "private", null, 0, 1, SpecialBuoy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecialBuoy_Seasonal(), ecorePackage.getEString(), "seasonal", null, 0, 1, SpecialBuoy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(topmarkEClass, Topmark.class, "Topmark", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTopmark_TopmarkParts(), this.getTopmarkType(), "topmarkParts", null, 0, -1, Topmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopmark_TopmarkColor(), theLightsPackage.getColor(), "topmarkColor", null, 0, 1, Topmark.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(buoyTypeEEnum, BuoyType.class, "BuoyType");
		addEEnumLiteral(buoyTypeEEnum, BuoyType.UNKNOWN);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.CARDINAL_SOUTH);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.CARDINAL_EAST);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.CARDINAL_WEST);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.LATERAL_SQUARE);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.LATERAL_TRIANGLE);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.SAFE_WATER);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.ISOLATED_DANGER);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.SPECIAL);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.NEW_WRECK);
		addEEnumLiteral(buoyTypeEEnum, BuoyType.CARDINAL_NORTH);

		initEEnum(shapeEEnum, Shape.class, "Shape");
		addEEnumLiteral(shapeEEnum, Shape.UNKNOWN);
		addEEnumLiteral(shapeEEnum, Shape.CONICAL);
		addEEnumLiteral(shapeEEnum, Shape.CYLINDRICAL);
		addEEnumLiteral(shapeEEnum, Shape.SPHERICAL);
		addEEnumLiteral(shapeEEnum, Shape.PILLAR);
		addEEnumLiteral(shapeEEnum, Shape.SPAR);
		addEEnumLiteral(shapeEEnum, Shape.BARREL);
		addEEnumLiteral(shapeEEnum, Shape.SUPER);

		initEEnum(topmarkTypeEEnum, TopmarkType.class, "TopmarkType");
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.UNKNOWN);
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.CYLINDER);
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.CONE_UP);
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.CONE_DOWN);
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.XCROSS);
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.BALL);
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.UPRIGHT_CROSS);
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.RHOMBUS);
		addEEnumLiteral(topmarkTypeEEnum, TopmarkType.FLAG);

		// Create resource
		createResource(eNS_URI);
	}

} //BuoysandbeaconsPackageImpl
