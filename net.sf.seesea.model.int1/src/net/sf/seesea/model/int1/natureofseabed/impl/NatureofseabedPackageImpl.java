/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.natureofseabed.impl;

import net.sf.seesea.model.core.CorePackage;

import net.sf.seesea.model.int1.base.BasePackage;

import net.sf.seesea.model.int1.base.impl.BasePackageImpl;

import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;

import net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl;

import net.sf.seesea.model.int1.lights.LightsPackage;

import net.sf.seesea.model.int1.lights.impl.LightsPackageImpl;

import net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed;
import net.sf.seesea.model.int1.natureofseabed.NatureofseabedFactory;
import net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage;
import net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature;
import net.sf.seesea.model.int1.natureofseabed.SeabedType;
import net.sf.seesea.model.int1.natureofseabed.SpringInSeabed;

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
public class NatureofseabedPackageImpl extends EPackageImpl implements NatureofseabedPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass springInSeabedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass natureOfSeabedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum seabedTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum qualifyingSeabedNatureEEnum = null;

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
	 * @see net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NatureofseabedPackageImpl() {
		super(eNS_URI, NatureofseabedFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link NatureofseabedPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static NatureofseabedPackage init() {
		if (isInited) return (NatureofseabedPackage)EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI);

		// Obtain or create and register package
		NatureofseabedPackageImpl theNatureofseabedPackage = (NatureofseabedPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof NatureofseabedPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new NatureofseabedPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		BuoysandbeaconsPackageImpl theBuoysandbeaconsPackage = (BuoysandbeaconsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI) instanceof BuoysandbeaconsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI) : BuoysandbeaconsPackage.eINSTANCE);
		LightsPackageImpl theLightsPackage = (LightsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI) instanceof LightsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI) : LightsPackage.eINSTANCE);
		BasePackageImpl theBasePackage = (BasePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI) instanceof BasePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI) : BasePackage.eINSTANCE);
		TracksandroutesPackageImpl theTracksandroutesPackage = (TracksandroutesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI) instanceof TracksandroutesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI) : TracksandroutesPackage.eINSTANCE);

		// Create package meta-data objects
		theNatureofseabedPackage.createPackageContents();
		theBuoysandbeaconsPackage.createPackageContents();
		theLightsPackage.createPackageContents();
		theBasePackage.createPackageContents();
		theTracksandroutesPackage.createPackageContents();

		// Initialize created meta-data
		theNatureofseabedPackage.initializePackageContents();
		theBuoysandbeaconsPackage.initializePackageContents();
		theLightsPackage.initializePackageContents();
		theBasePackage.initializePackageContents();
		theTracksandroutesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theNatureofseabedPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(NatureofseabedPackage.eNS_URI, theNatureofseabedPackage);
		return theNatureofseabedPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpringInSeabed() {
		return springInSeabedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNatureOfSeabed() {
		return natureOfSeabedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNatureOfSeabed_SeabedType() {
		return (EAttribute)natureOfSeabedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNatureOfSeabed_SeabedNature() {
		return (EAttribute)natureOfSeabedEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSeabedType() {
		return seabedTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getQualifyingSeabedNature() {
		return qualifyingSeabedNatureEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NatureofseabedFactory getNatureofseabedFactory() {
		return (NatureofseabedFactory)getEFactoryInstance();
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
		springInSeabedEClass = createEClass(SPRING_IN_SEABED);

		natureOfSeabedEClass = createEClass(NATURE_OF_SEABED);
		createEAttribute(natureOfSeabedEClass, NATURE_OF_SEABED__SEABED_TYPE);
		createEAttribute(natureOfSeabedEClass, NATURE_OF_SEABED__SEABED_NATURE);

		// Create enums
		seabedTypeEEnum = createEEnum(SEABED_TYPE);
		qualifyingSeabedNatureEEnum = createEEnum(QUALIFYING_SEABED_NATURE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		springInSeabedEClass.getESuperTypes().add(theCorePackage.getModelObject());
		natureOfSeabedEClass.getESuperTypes().add(theCorePackage.getModelObject());

		// Initialize classes and features; add operations and parameters
		initEClass(springInSeabedEClass, SpringInSeabed.class, "SpringInSeabed", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(natureOfSeabedEClass, NatureOfSeabed.class, "NatureOfSeabed", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNatureOfSeabed_SeabedType(), this.getSeabedType(), "seabedType", null, 1, 1, NatureOfSeabed.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNatureOfSeabed_SeabedNature(), this.getQualifyingSeabedNature(), "seabedNature", null, 0, 1, NatureOfSeabed.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(seabedTypeEEnum, SeabedType.class, "SeabedType");
		addEEnumLiteral(seabedTypeEEnum, SeabedType.UNDEFINED);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.SAND);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.MUD);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.CLAY);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.SILT);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.STONES);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.GRAVEL);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.PEBBLES);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.COBBLES);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.ROCK);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.CORAL);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.SHELLS);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.WEED);
		addEEnumLiteral(seabedTypeEEnum, SeabedType.SANDWAVES);

		initEEnum(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.class, "QualifyingSeabedNature");
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.UNDEFINED);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.FINE);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.MEDIUM);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.COARSE);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.BROKEN);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.STICKY);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.SOFT);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.STIFF);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.VOLCANIC);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.CALCAREOUS);
		addEEnumLiteral(qualifyingSeabedNatureEEnum, QualifyingSeabedNature.HARD);

		// Create resource
		createResource(eNS_URI);
	}

} //NatureofseabedPackageImpl
