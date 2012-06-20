/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes;

import net.sf.seesea.model.core.CorePackage;

import net.sf.seesea.model.core.geo.GeoPackage;

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
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesFactory
 * @model kind="package"
 * @generated
 */
public interface TracksandroutesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tracksandroutes";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "tracksandroutes";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tracksandroutes";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracksandroutesPackage eINSTANCE = net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.AbstractSeaWayArtefactImpl <em>Abstract Sea Way Artefact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.AbstractSeaWayArtefactImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getAbstractSeaWayArtefact()
	 * @generated
	 */
	int ABSTRACT_SEA_WAY_ARTEFACT = 0;

	/**
	 * The number of structural features of the '<em>Abstract Sea Way Artefact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SEA_WAY_ARTEFACT_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.AbstractSeaWayImpl <em>Abstract Sea Way</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.AbstractSeaWayImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getAbstractSeaWay()
	 * @generated
	 */
	int ABSTRACT_SEA_WAY = 8;

	/**
	 * The number of structural features of the '<em>Abstract Sea Way</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_SEA_WAY_FEATURE_COUNT = ABSTRACT_SEA_WAY_ARTEFACT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationSchemeImpl <em>Traffic Separation Scheme</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationSchemeImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getTrafficSeparationScheme()
	 * @generated
	 */
	int TRAFFIC_SEPARATION_SCHEME = 1;

	/**
	 * The feature id for the '<em><b>Traffic Separations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS = ABSTRACT_SEA_WAY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Traffic Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAFFIC_SEPARATION_SCHEME__TRAFFIC_DIRECTION = ABSTRACT_SEA_WAY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Traffic Separation Scheme</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAFFIC_SEPARATION_SCHEME_FEATURE_COUNT = ABSTRACT_SEA_WAY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationImpl <em>Traffic Separation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getTrafficSeparation()
	 * @generated
	 */
	int TRAFFIC_SEPARATION = 2;

	/**
	 * The feature id for the '<em><b>Bounds</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAFFIC_SEPARATION__BOUNDS = GeoPackage.CHART_AREA__BOUNDS;

	/**
	 * The feature id for the '<em><b>Border Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAFFIC_SEPARATION__BORDER_TYPE = GeoPackage.CHART_AREA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Naturally Divided</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAFFIC_SEPARATION__IS_NATURALLY_DIVIDED = GeoPackage.CHART_AREA_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Traffic Separation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAFFIC_SEPARATION_FEATURE_COUNT = GeoPackage.CHART_AREA_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.DeepWaterRouteImpl <em>Deep Water Route</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.DeepWaterRouteImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getDeepWaterRoute()
	 * @generated
	 */
	int DEEP_WATER_ROUTE = 3;

	/**
	 * The feature id for the '<em><b>Traffic Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEEP_WATER_ROUTE__TRAFFIC_DIRECTION = ABSTRACT_SEA_WAY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Route Area</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEEP_WATER_ROUTE__ROUTE_AREA = ABSTRACT_SEA_WAY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Deep Water Route</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEEP_WATER_ROUTE_FEATURE_COUNT = ABSTRACT_SEA_WAY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.RoundAboutImpl <em>Round About</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.RoundAboutImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getRoundAbout()
	 * @generated
	 */
	int ROUND_ABOUT = 4;

	/**
	 * The feature id for the '<em><b>Attached Sea Ways</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUND_ABOUT__ATTACHED_SEA_WAYS = ABSTRACT_SEA_WAY_ARTEFACT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Round About</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUND_ABOUT_FEATURE_COUNT = ABSTRACT_SEA_WAY_ARTEFACT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.AreaToBeAvoidedImpl <em>Area To Be Avoided</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.AreaToBeAvoidedImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getAreaToBeAvoided()
	 * @generated
	 */
	int AREA_TO_BE_AVOIDED = 5;

	/**
	 * The feature id for the '<em><b>Bounds</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA_TO_BE_AVOIDED__BOUNDS = GeoPackage.CHART_AREA__BOUNDS;

	/**
	 * The number of structural features of the '<em>Area To Be Avoided</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA_TO_BE_AVOIDED_FEATURE_COUNT = GeoPackage.CHART_AREA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.SeawaysContainerImpl <em>Seaways Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.SeawaysContainerImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getSeawaysContainer()
	 * @generated
	 */
	int SEAWAYS_CONTAINER = 6;

	/**
	 * The feature id for the '<em><b>Seaways</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEAWAYS_CONTAINER__SEAWAYS = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Seaways Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEAWAYS_CONTAINER_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.InshoreTrafficZoneImpl <em>Inshore Traffic Zone</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.InshoreTrafficZoneImpl
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getInshoreTrafficZone()
	 * @generated
	 */
	int INSHORE_TRAFFIC_ZONE = 7;

	/**
	 * The feature id for the '<em><b>Bounds</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSHORE_TRAFFIC_ZONE__BOUNDS = GeoPackage.CHART_AREA__BOUNDS;

	/**
	 * The number of structural features of the '<em>Inshore Traffic Zone</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSHORE_TRAFFIC_ZONE_FEATURE_COUNT = GeoPackage.CHART_AREA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.BorderType <em>Border Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.BorderType
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getBorderType()
	 * @generated
	 */
	int BORDER_TYPE = 9;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficDirection <em>Traffic Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficDirection
	 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getTrafficDirection()
	 * @generated
	 */
	int TRAFFIC_DIRECTION = 10;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWayArtefact <em>Abstract Sea Way Artefact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Sea Way Artefact</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWayArtefact
	 * @generated
	 */
	EClass getAbstractSeaWayArtefact();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme <em>Traffic Separation Scheme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Traffic Separation Scheme</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme
	 * @generated
	 */
	EClass getTrafficSeparationScheme();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme#getTrafficSeparations <em>Traffic Separations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traffic Separations</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme#getTrafficSeparations()
	 * @see #getTrafficSeparationScheme()
	 * @generated
	 */
	EReference getTrafficSeparationScheme_TrafficSeparations();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme#getTrafficDirection <em>Traffic Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Traffic Direction</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme#getTrafficDirection()
	 * @see #getTrafficSeparationScheme()
	 * @generated
	 */
	EAttribute getTrafficSeparationScheme_TrafficDirection();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation <em>Traffic Separation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Traffic Separation</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation
	 * @generated
	 */
	EClass getTrafficSeparation();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation#getBorderType <em>Border Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Border Type</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation#getBorderType()
	 * @see #getTrafficSeparation()
	 * @generated
	 */
	EAttribute getTrafficSeparation_BorderType();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation#isIsNaturallyDivided <em>Is Naturally Divided</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Naturally Divided</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation#isIsNaturallyDivided()
	 * @see #getTrafficSeparation()
	 * @generated
	 */
	EAttribute getTrafficSeparation_IsNaturallyDivided();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute <em>Deep Water Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deep Water Route</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute
	 * @generated
	 */
	EClass getDeepWaterRoute();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute#getTrafficDirection <em>Traffic Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Traffic Direction</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute#getTrafficDirection()
	 * @see #getDeepWaterRoute()
	 * @generated
	 */
	EAttribute getDeepWaterRoute_TrafficDirection();

	/**
	 * Returns the meta object for the reference '{@link net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute#getRouteArea <em>Route Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Route Area</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute#getRouteArea()
	 * @see #getDeepWaterRoute()
	 * @generated
	 */
	EReference getDeepWaterRoute_RouteArea();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.RoundAbout <em>Round About</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Round About</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.RoundAbout
	 * @generated
	 */
	EClass getRoundAbout();

	/**
	 * Returns the meta object for the reference list '{@link net.sf.seesea.model.int1.tracksandroutes.RoundAbout#getAttachedSeaWays <em>Attached Sea Ways</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attached Sea Ways</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.RoundAbout#getAttachedSeaWays()
	 * @see #getRoundAbout()
	 * @generated
	 */
	EReference getRoundAbout_AttachedSeaWays();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.AreaToBeAvoided <em>Area To Be Avoided</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Area To Be Avoided</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.AreaToBeAvoided
	 * @generated
	 */
	EClass getAreaToBeAvoided();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer <em>Seaways Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Seaways Container</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer
	 * @generated
	 */
	EClass getSeawaysContainer();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer#getSeaways <em>Seaways</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Seaways</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer#getSeaways()
	 * @see #getSeawaysContainer()
	 * @generated
	 */
	EReference getSeawaysContainer_Seaways();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.InshoreTrafficZone <em>Inshore Traffic Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inshore Traffic Zone</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.InshoreTrafficZone
	 * @generated
	 */
	EClass getInshoreTrafficZone();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWay <em>Abstract Sea Way</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Sea Way</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWay
	 * @generated
	 */
	EClass getAbstractSeaWay();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.tracksandroutes.BorderType <em>Border Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Border Type</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.BorderType
	 * @generated
	 */
	EEnum getBorderType();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficDirection <em>Traffic Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Traffic Direction</em>'.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficDirection
	 * @generated
	 */
	EEnum getTrafficDirection();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TracksandroutesFactory getTracksandroutesFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.AbstractSeaWayArtefactImpl <em>Abstract Sea Way Artefact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.AbstractSeaWayArtefactImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getAbstractSeaWayArtefact()
		 * @generated
		 */
		EClass ABSTRACT_SEA_WAY_ARTEFACT = eINSTANCE.getAbstractSeaWayArtefact();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationSchemeImpl <em>Traffic Separation Scheme</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationSchemeImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getTrafficSeparationScheme()
		 * @generated
		 */
		EClass TRAFFIC_SEPARATION_SCHEME = eINSTANCE.getTrafficSeparationScheme();

		/**
		 * The meta object literal for the '<em><b>Traffic Separations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS = eINSTANCE.getTrafficSeparationScheme_TrafficSeparations();

		/**
		 * The meta object literal for the '<em><b>Traffic Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRAFFIC_SEPARATION_SCHEME__TRAFFIC_DIRECTION = eINSTANCE.getTrafficSeparationScheme_TrafficDirection();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationImpl <em>Traffic Separation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getTrafficSeparation()
		 * @generated
		 */
		EClass TRAFFIC_SEPARATION = eINSTANCE.getTrafficSeparation();

		/**
		 * The meta object literal for the '<em><b>Border Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRAFFIC_SEPARATION__BORDER_TYPE = eINSTANCE.getTrafficSeparation_BorderType();

		/**
		 * The meta object literal for the '<em><b>Is Naturally Divided</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRAFFIC_SEPARATION__IS_NATURALLY_DIVIDED = eINSTANCE.getTrafficSeparation_IsNaturallyDivided();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.DeepWaterRouteImpl <em>Deep Water Route</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.DeepWaterRouteImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getDeepWaterRoute()
		 * @generated
		 */
		EClass DEEP_WATER_ROUTE = eINSTANCE.getDeepWaterRoute();

		/**
		 * The meta object literal for the '<em><b>Traffic Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEEP_WATER_ROUTE__TRAFFIC_DIRECTION = eINSTANCE.getDeepWaterRoute_TrafficDirection();

		/**
		 * The meta object literal for the '<em><b>Route Area</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEEP_WATER_ROUTE__ROUTE_AREA = eINSTANCE.getDeepWaterRoute_RouteArea();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.RoundAboutImpl <em>Round About</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.RoundAboutImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getRoundAbout()
		 * @generated
		 */
		EClass ROUND_ABOUT = eINSTANCE.getRoundAbout();

		/**
		 * The meta object literal for the '<em><b>Attached Sea Ways</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUND_ABOUT__ATTACHED_SEA_WAYS = eINSTANCE.getRoundAbout_AttachedSeaWays();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.AreaToBeAvoidedImpl <em>Area To Be Avoided</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.AreaToBeAvoidedImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getAreaToBeAvoided()
		 * @generated
		 */
		EClass AREA_TO_BE_AVOIDED = eINSTANCE.getAreaToBeAvoided();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.SeawaysContainerImpl <em>Seaways Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.SeawaysContainerImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getSeawaysContainer()
		 * @generated
		 */
		EClass SEAWAYS_CONTAINER = eINSTANCE.getSeawaysContainer();

		/**
		 * The meta object literal for the '<em><b>Seaways</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEAWAYS_CONTAINER__SEAWAYS = eINSTANCE.getSeawaysContainer_Seaways();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.InshoreTrafficZoneImpl <em>Inshore Traffic Zone</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.InshoreTrafficZoneImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getInshoreTrafficZone()
		 * @generated
		 */
		EClass INSHORE_TRAFFIC_ZONE = eINSTANCE.getInshoreTrafficZone();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.impl.AbstractSeaWayImpl <em>Abstract Sea Way</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.AbstractSeaWayImpl
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getAbstractSeaWay()
		 * @generated
		 */
		EClass ABSTRACT_SEA_WAY = eINSTANCE.getAbstractSeaWay();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.BorderType <em>Border Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.BorderType
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getBorderType()
		 * @generated
		 */
		EEnum BORDER_TYPE = eINSTANCE.getBorderType();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficDirection <em>Traffic Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficDirection
		 * @see net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesPackageImpl#getTrafficDirection()
		 * @generated
		 */
		EEnum TRAFFIC_DIRECTION = eINSTANCE.getTrafficDirection();

	}

} //TracksandroutesPackage
