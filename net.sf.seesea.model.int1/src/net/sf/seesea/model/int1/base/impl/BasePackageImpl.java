/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.base.impl;

import net.sf.seesea.model.core.CorePackage;

import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.osm.OsmPackage;
import net.sf.seesea.model.int1.base.AbstractSeamark;
import net.sf.seesea.model.int1.base.BaseFactory;
import net.sf.seesea.model.int1.base.BasePackage;
import net.sf.seesea.model.int1.base.MarineChart;
import net.sf.seesea.model.int1.base.ChartSymbols;

import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;

import net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl;

import net.sf.seesea.model.int1.lights.LightsPackage;

import net.sf.seesea.model.int1.lights.impl.LightsPackageImpl;

import net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage;
import net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;
import net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BasePackageImpl extends EPackageImpl implements BasePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractSeamarkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass marineChartEClass = null;

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
	 * @see net.sf.seesea.model.int1.base.BasePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BasePackageImpl() {
		super(eNS_URI, BaseFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link BasePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BasePackage init() {
		if (isInited) return (BasePackage)EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI);

		// Obtain or create and register package
		BasePackageImpl theBasePackage = (BasePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BasePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BasePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		BuoysandbeaconsPackageImpl theBuoysandbeaconsPackage = (BuoysandbeaconsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI) instanceof BuoysandbeaconsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BuoysandbeaconsPackage.eNS_URI) : BuoysandbeaconsPackage.eINSTANCE);
		LightsPackageImpl theLightsPackage = (LightsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI) instanceof LightsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LightsPackage.eNS_URI) : LightsPackage.eINSTANCE);
		NatureofseabedPackageImpl theNatureofseabedPackage = (NatureofseabedPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI) instanceof NatureofseabedPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NatureofseabedPackage.eNS_URI) : NatureofseabedPackage.eINSTANCE);
		TracksandroutesPackageImpl theTracksandroutesPackage = (TracksandroutesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI) instanceof TracksandroutesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TracksandroutesPackage.eNS_URI) : TracksandroutesPackage.eINSTANCE);

		// Create package meta-data objects
		theBasePackage.createPackageContents();
		theBuoysandbeaconsPackage.createPackageContents();
		theLightsPackage.createPackageContents();
		theNatureofseabedPackage.createPackageContents();
		theTracksandroutesPackage.createPackageContents();

		// Initialize created meta-data
		theBasePackage.initializePackageContents();
		theBuoysandbeaconsPackage.initializePackageContents();
		theLightsPackage.initializePackageContents();
		theNatureofseabedPackage.initializePackageContents();
		theTracksandroutesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBasePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BasePackage.eNS_URI, theBasePackage);
		return theBasePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractSeamark() {
		return abstractSeamarkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMarineChart() {
		return marineChartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMarineChart_Seamarks() {
		return (EReference)marineChartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseFactory getBaseFactory() {
		return (BaseFactory)getEFactoryInstance();
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
		abstractSeamarkEClass = createEClass(ABSTRACT_SEAMARK);

		marineChartEClass = createEClass(MARINE_CHART);
		createEReference(marineChartEClass, MARINE_CHART__SEAMARKS);
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
		GeoPackage theGeoPackage = (GeoPackage)EPackage.Registry.INSTANCE.getEPackage(GeoPackage.eNS_URI);
		OsmPackage theOsmPackage = (OsmPackage)EPackage.Registry.INSTANCE.getEPackage(OsmPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractSeamarkEClass.getESuperTypes().add(theGeoPackage.getGeoPosition());
		marineChartEClass.getESuperTypes().add(theGeoPackage.getChart());
		marineChartEClass.getESuperTypes().add(theOsmPackage.getWorld());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractSeamarkEClass, AbstractSeamark.class, "AbstractSeamark", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(marineChartEClass, MarineChart.class, "MarineChart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMarineChart_Seamarks(), this.getAbstractSeamark(), null, "seamarks", null, 0, -1, MarineChart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //BasePackageImpl
