/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.lights.impl;

import net.sf.seesea.model.core.CorePackage;

import net.sf.seesea.model.int1.base.BasePackage;

import net.sf.seesea.model.int1.base.impl.BasePackageImpl;

import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;

import net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl;

import net.sf.seesea.model.int1.lights.Color;
import net.sf.seesea.model.int1.lights.LightCharacter;
import net.sf.seesea.model.int1.lights.LightsFactory;
import net.sf.seesea.model.int1.lights.LightsPackage;
import net.sf.seesea.model.int1.lights.Orientation;
import net.sf.seesea.model.int1.lights.PhaseCharacteristic;

import net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage;
import net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;
import net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl;
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
public class LightsPackageImpl extends EPackageImpl implements LightsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lightCharacterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum phaseCharacteristicEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum colorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orientationEEnum = null;

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
	 * @see net.sf.seesea.model.int1.lights.LightsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LightsPackageImpl() {
		super(eNS_URI, LightsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LightsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LightsPackage init() {
		if (isInited) return (LightsPackage)EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI);

		// Obtain or create and register package
		LightsPackageImpl theLightsPackage = (LightsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LightsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LightsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		BuoysandbeaconsPackageImpl theBuoysandbeaconsPackage = (BuoysandbeaconsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI) instanceof BuoysandbeaconsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI) : BuoysandbeaconsPackage.eINSTANCE);
		BasePackageImpl theBasePackage = (BasePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI) instanceof BasePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI) : BasePackage.eINSTANCE);
		NatureofseabedPackageImpl theNatureofseabedPackage = (NatureofseabedPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI) instanceof NatureofseabedPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI) : NatureofseabedPackage.eINSTANCE);
		TracksandroutesPackageImpl theTracksandroutesPackage = (TracksandroutesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI) instanceof TracksandroutesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI) : TracksandroutesPackage.eINSTANCE);

		// Create package meta-data objects
		theLightsPackage.createPackageContents();
		theBuoysandbeaconsPackage.createPackageContents();
		theBasePackage.createPackageContents();
		theNatureofseabedPackage.createPackageContents();
		theTracksandroutesPackage.createPackageContents();

		// Initialize created meta-data
		theLightsPackage.initializePackageContents();
		theBuoysandbeaconsPackage.initializePackageContents();
		theBasePackage.initializePackageContents();
		theNatureofseabedPackage.initializePackageContents();
		theTracksandroutesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLightsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LightsPackage.eNS_URI, theLightsPackage);
		return theLightsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLightCharacter() {
		return lightCharacterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLightCharacter_Lightcolor() {
		return (EAttribute)lightCharacterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLightCharacter_Period() {
		return (EAttribute)lightCharacterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLightCharacter_PhaseCharacteristic() {
		return (EAttribute)lightCharacterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLightCharacter_Group() {
		return (EAttribute)lightCharacterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPhaseCharacteristic() {
		return phaseCharacteristicEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getColor() {
		return colorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrientation() {
		return orientationEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LightsFactory getLightsFactory() {
		return (LightsFactory)getEFactoryInstance();
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
		lightCharacterEClass = createEClass(LIGHT_CHARACTER);
		createEAttribute(lightCharacterEClass, LIGHT_CHARACTER__LIGHTCOLOR);
		createEAttribute(lightCharacterEClass, LIGHT_CHARACTER__PERIOD);
		createEAttribute(lightCharacterEClass, LIGHT_CHARACTER__PHASE_CHARACTERISTIC);
		createEAttribute(lightCharacterEClass, LIGHT_CHARACTER__GROUP);

		// Create enums
		phaseCharacteristicEEnum = createEEnum(PHASE_CHARACTERISTIC);
		colorEEnum = createEEnum(COLOR);
		orientationEEnum = createEEnum(ORIENTATION);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(lightCharacterEClass, LightCharacter.class, "LightCharacter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLightCharacter_Lightcolor(), this.getColor(), "lightcolor", null, 0, -1, LightCharacter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLightCharacter_Period(), ecorePackage.getEInt(), "period", null, 0, 1, LightCharacter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLightCharacter_PhaseCharacteristic(), this.getPhaseCharacteristic(), "phaseCharacteristic", null, 0, 1, LightCharacter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLightCharacter_Group(), ecorePackage.getEInt(), "group", null, 0, -1, LightCharacter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(phaseCharacteristicEEnum, PhaseCharacteristic.class, "PhaseCharacteristic");
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.UNKNOWN);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.FIXED);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.OCCULTING);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.ISOPHASE);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.FLASH);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.LONG_FLASH);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.QUICK);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.INTERRUPTED_QUICK);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.VERY_QUICK);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.INTERRUPTED_VERY_QUICK);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.ULTRA_QUICK);
		addEEnumLiteral(phaseCharacteristicEEnum, PhaseCharacteristic.INTERRUPTED_ULTRA_QUICK);

		initEEnum(colorEEnum, Color.class, "Color");
		addEEnumLiteral(colorEEnum, Color.UNKNOWN);
		addEEnumLiteral(colorEEnum, Color.RED);
		addEEnumLiteral(colorEEnum, Color.GREEN);
		addEEnumLiteral(colorEEnum, Color.YELLOW);
		addEEnumLiteral(colorEEnum, Color.WHITE);
		addEEnumLiteral(colorEEnum, Color.BLACK);
		addEEnumLiteral(colorEEnum, Color.ORANGE);
		addEEnumLiteral(colorEEnum, Color.BLUE);
		addEEnumLiteral(colorEEnum, Color.VIOLET);

		initEEnum(orientationEEnum, Orientation.class, "Orientation");
		addEEnumLiteral(orientationEEnum, Orientation.VERTICAL);
		addEEnumLiteral(orientationEEnum, Orientation.HORIZONTAL);
		addEEnumLiteral(orientationEEnum, Orientation.DIAGONAL);

		// Create resource
		createResource(eNS_URI);
	}

} //LightsPackageImpl
