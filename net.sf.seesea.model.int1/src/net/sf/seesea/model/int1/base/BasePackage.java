/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.base;

import net.sf.seesea.model.core.geo.GeoPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see net.sf.seesea.model.int1.base.BaseFactory
 * @model kind="package"
 * @generated
 */
public interface BasePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "base";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "base";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "base";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BasePackage eINSTANCE = net.sf.seesea.model.int1.base.impl.BasePackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.base.impl.AbstractSeamarkImpl <em>Abstract Seamark</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.base.impl.AbstractSeamarkImpl
	 * @see net.sf.seesea.model.int1.base.impl.BasePackageImpl#getAbstractSeamark()
	 * @generated
	 */
	int ABSTRACT_SEAMARK = 0;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SEAMARK__LONGITUDE = GeoPackage.GEO_POSITION__LONGITUDE;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SEAMARK__LATITUDE = GeoPackage.GEO_POSITION__LATITUDE;

	/**
	 * The number of structural features of the '<em>Abstract Seamark</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SEAMARK_FEATURE_COUNT = GeoPackage.GEO_POSITION_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl <em>Marine Chart</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.base.impl.MarineChartImpl
	 * @see net.sf.seesea.model.int1.base.impl.BasePackageImpl#getMarineChart()
	 * @generated
	 */
	int MARINE_CHART = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__NAME = GeoPackage.CHART__NAME;

	/**
	 * The feature id for the '<em><b>Chart Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__CHART_CONFIGURATION = GeoPackage.CHART__CHART_CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Poi Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__POI_CONTAINER = GeoPackage.CHART_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Routing Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__ROUTING_CONTAINER = GeoPackage.CHART_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tracks Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__TRACKS_CONTAINER = GeoPackage.CHART_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Zoom Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__ZOOM_LEVEL = GeoPackage.CHART_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Map Center Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__MAP_CENTER_POSITION = GeoPackage.CHART_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Sub Area</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__SUB_AREA = GeoPackage.CHART_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Longitude Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__LONGITUDE_SCALE = GeoPackage.CHART_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Latitude Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__LATITUDE_SCALE = GeoPackage.CHART_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Seamarks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART__SEAMARKS = GeoPackage.CHART_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Marine Chart</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARINE_CHART_FEATURE_COUNT = GeoPackage.CHART_FEATURE_COUNT + 9;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.base.AbstractSeamark <em>Abstract Seamark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Seamark</em>'.
	 * @see net.sf.seesea.model.int1.base.AbstractSeamark
	 * @generated
	 */
	EClass getAbstractSeamark();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.base.MarineChart <em>Marine Chart</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Marine Chart</em>'.
	 * @see net.sf.seesea.model.int1.base.MarineChart
	 * @generated
	 */
	EClass getMarineChart();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.int1.base.MarineChart#getSeamarks <em>Seamarks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Seamarks</em>'.
	 * @see net.sf.seesea.model.int1.base.MarineChart#getSeamarks()
	 * @see #getMarineChart()
	 * @generated
	 */
	EReference getMarineChart_Seamarks();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BaseFactory getBaseFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.base.impl.AbstractSeamarkImpl <em>Abstract Seamark</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.base.impl.AbstractSeamarkImpl
		 * @see net.sf.seesea.model.int1.base.impl.BasePackageImpl#getAbstractSeamark()
		 * @generated
		 */
		EClass ABSTRACT_SEAMARK = eINSTANCE.getAbstractSeamark();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl <em>Marine Chart</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.base.impl.MarineChartImpl
		 * @see net.sf.seesea.model.int1.base.impl.BasePackageImpl#getMarineChart()
		 * @generated
		 */
		EClass MARINE_CHART = eINSTANCE.getMarineChart();

		/**
		 * The meta object literal for the '<em><b>Seamarks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MARINE_CHART__SEAMARKS = eINSTANCE.getMarineChart_Seamarks();

	}

} //BasePackage
