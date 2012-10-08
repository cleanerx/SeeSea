/**
 * <copyright>
Copyright (c) 2010-2012, Jens K�bler
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
package net.sf.seesea.model.core.geo;

import net.sf.seesea.model.core.CorePackage;
import net.sf.seesea.model.core.geo.osm.OsmPackage;
import net.sf.seesea.model.core.physx.PhysxPackage;
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
 * @see net.sf.seesea.model.core.geo.GeoFactory
 * @model kind="package"
 * @generated
 */
public interface GeoPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "geo";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "b";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "b";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GeoPackage eINSTANCE = net.sf.seesea.model.core.geo.impl.GeoPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.GeoPositionImpl <em>Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.GeoPositionImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getGeoPosition()
	 * @generated
	 */
	int GEO_POSITION = 0;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION__LONGITUDE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION__LATITUDE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.CoordinateImpl <em>Coordinate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.CoordinateImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getCoordinate()
	 * @generated
	 */
	int COORDINATE = 1;

	/**
	 * The feature id for the '<em><b>Degree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATE__DEGREE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Minute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATE__MINUTE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Second</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATE__SECOND = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Coordinate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATE_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.LatitudeImpl <em>Latitude</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.LatitudeImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getLatitude()
	 * @generated
	 */
	int LATITUDE = 2;

	/**
	 * The feature id for the '<em><b>Degree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATITUDE__DEGREE = COORDINATE__DEGREE;

	/**
	 * The feature id for the '<em><b>Minute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATITUDE__MINUTE = COORDINATE__MINUTE;

	/**
	 * The feature id for the '<em><b>Second</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATITUDE__SECOND = COORDINATE__SECOND;

	/**
	 * The number of structural features of the '<em>Latitude</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LATITUDE_FEATURE_COUNT = COORDINATE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.LongitudeImpl <em>Longitude</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.LongitudeImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getLongitude()
	 * @generated
	 */
	int LONGITUDE = 3;

	/**
	 * The feature id for the '<em><b>Degree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONGITUDE__DEGREE = COORDINATE__DEGREE;

	/**
	 * The feature id for the '<em><b>Minute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONGITUDE__MINUTE = COORDINATE__MINUTE;

	/**
	 * The feature id for the '<em><b>Second</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONGITUDE__SECOND = COORDINATE__SECOND;

	/**
	 * The number of structural features of the '<em>Longitude</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONGITUDE_FEATURE_COUNT = COORDINATE_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.NamedArtifactImpl <em>Named Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.NamedArtifactImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getNamedArtifact()
	 * @generated
	 */
	int NAMED_ARTIFACT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ARTIFACT__NAME = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ARTIFACT_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.RouteImpl <em>Route</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.RouteImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getRoute()
	 * @generated
	 */
	int ROUTE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__NAME = NAMED_ARTIFACT__NAME;

	/**
	 * The feature id for the '<em><b>Waypoints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__WAYPOINTS = NAMED_ARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Route</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE_FEATURE_COUNT = NAMED_ARTIFACT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.NamedPositionImpl <em>Named Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.NamedPositionImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getNamedPosition()
	 * @generated
	 */
	int NAMED_POSITION = 6;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_POSITION__LONGITUDE = GEO_POSITION__LONGITUDE;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_POSITION__LATITUDE = GEO_POSITION__LATITUDE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_POSITION__NAME = GEO_POSITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_POSITION_FEATURE_COUNT = GEO_POSITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.RoutingContainerImpl <em>Routing Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.RoutingContainerImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getRoutingContainer()
	 * @generated
	 */
	int ROUTING_CONTAINER = 7;

	/**
	 * The feature id for the '<em><b>Routes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_CONTAINER__ROUTES = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Area</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_CONTAINER__AREA = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Routing Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTING_CONTAINER_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.POIContainerImpl <em>POI Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.POIContainerImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getPOIContainer()
	 * @generated
	 */
	int POI_CONTAINER = 8;

	/**
	 * The feature id for the '<em><b>Pois</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POI_CONTAINER__POIS = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Area</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POI_CONTAINER__AREA = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>POI Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POI_CONTAINER_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.ChartImpl <em>Chart</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.ChartImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChart()
	 * @generated
	 */
	int CHART = 11;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.NavareaImpl <em>Navarea</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.NavareaImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getNavarea()
	 * @generated
	 */
	int NAVAREA = 19;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.ChartContainerImpl <em>Chart Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.ChartContainerImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChartContainer()
	 * @generated
	 */
	int CHART_CONTAINER = 9;

	/**
	 * The feature id for the '<em><b>Charts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART_CONTAINER__CHARTS = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Chart Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART_CONTAINER_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.NavigationCompoundImpl <em>Navigation Compound</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.NavigationCompoundImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getNavigationCompound()
	 * @generated
	 */
	int NAVIGATION_COMPOUND = 10;

	/**
	 * The feature id for the '<em><b>Poi Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_COMPOUND__POI_CONTAINER = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Routing Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_COMPOUND__ROUTING_CONTAINER = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tracks Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_COMPOUND__TRACKS_CONTAINER = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Navigation Compound</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_COMPOUND_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART__NAME = NAMED_ARTIFACT__NAME;

	/**
	 * The feature id for the '<em><b>Chart Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART__CHART_CONFIGURATION = NAMED_ARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Chart</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART_FEATURE_COUNT = NAMED_ARTIFACT_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.GeoPosition3DImpl <em>Position3 D</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.GeoPosition3DImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getGeoPosition3D()
	 * @generated
	 */
	int GEO_POSITION3_D = 12;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION3_D__LONGITUDE = GEO_POSITION__LONGITUDE;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION3_D__LATITUDE = GEO_POSITION__LATITUDE;

	/**
	 * The feature id for the '<em><b>Altitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION3_D__ALTITUDE = GEO_POSITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Position3 D</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_POSITION3_D_FEATURE_COUNT = GEO_POSITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.MeasuredPosition3DImpl <em>Measured Position3 D</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.MeasuredPosition3DImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getMeasuredPosition3D()
	 * @generated
	 */
	int MEASURED_POSITION3_D = 13;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_POSITION3_D__SENSOR_ID = PhysxPackage.MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_POSITION3_D__TIME = PhysxPackage.MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_POSITION3_D__TIMEZONE = PhysxPackage.MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_POSITION3_D__VALID = PhysxPackage.MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_POSITION3_D__LONGITUDE = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_POSITION3_D__LATITUDE = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Altitude</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_POSITION3_D__ALTITUDE = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Measured Position3 D</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_POSITION3_D_FEATURE_COUNT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 3;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.TracksContainerImpl <em>Tracks Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.TracksContainerImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getTracksContainer()
	 * @generated
	 */
	int TRACKS_CONTAINER = 14;

	/**
	 * The feature id for the '<em><b>Tracks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKS_CONTAINER__TRACKS = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tracks Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKS_CONTAINER_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.TrackImpl <em>Track</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.TrackImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getTrack()
	 * @generated
	 */
	int TRACK = 15;

	/**
	 * The feature id for the '<em><b>Measured Position</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK__MEASURED_POSITION = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Track</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.ChartSymbolImpl <em>Chart Symbol</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.ChartSymbolImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChartSymbol()
	 * @generated
	 */
	int CHART_SYMBOL = 16;

	/**
	 * The number of structural features of the '<em>Chart Symbol</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART_SYMBOL_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.ChartAreaImpl <em>Chart Area</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.ChartAreaImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChartArea()
	 * @generated
	 */
	int CHART_AREA = 17;

	/**
	 * The feature id for the '<em><b>Bounds</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART_AREA__BOUNDS = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Chart Area</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART_AREA_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.ChartWayImpl <em>Chart Way</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.ChartWayImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChartWay()
	 * @generated
	 */
	int CHART_WAY = 18;

	/**
	 * The feature id for the '<em><b>Waypoints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART_WAY__WAYPOINTS = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Chart Way</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHART_WAY_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__NAME = OsmPackage.AREA__NAME;

	/**
	 * The feature id for the '<em><b>Chart Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__CHART_CONFIGURATION = OsmPackage.AREA__CHART_CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Poi Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__POI_CONTAINER = OsmPackage.AREA__POI_CONTAINER;

	/**
	 * The feature id for the '<em><b>Routing Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__ROUTING_CONTAINER = OsmPackage.AREA__ROUTING_CONTAINER;

	/**
	 * The feature id for the '<em><b>Tracks Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__TRACKS_CONTAINER = OsmPackage.AREA__TRACKS_CONTAINER;

	/**
	 * The feature id for the '<em><b>Zoom Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__ZOOM_LEVEL = OsmPackage.AREA__ZOOM_LEVEL;

	/**
	 * The feature id for the '<em><b>Map Center Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__MAP_CENTER_POSITION = OsmPackage.AREA__MAP_CENTER_POSITION;

	/**
	 * The feature id for the '<em><b>Sub Area</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__SUB_AREA = OsmPackage.AREA__SUB_AREA;

	/**
	 * The feature id for the '<em><b>Areanumber</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA__AREANUMBER = OsmPackage.AREA_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Navarea</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVAREA_FEATURE_COUNT = OsmPackage.AREA_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.impl.DepthImpl <em>Depth</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.impl.DepthImpl
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getDepth()
	 * @generated
	 */
	int DEPTH = 20;

	/**
	 * The feature id for the '<em><b>Sensor ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPTH__SENSOR_ID = PhysxPackage.MEASUREMENT__SENSOR_ID;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPTH__TIME = PhysxPackage.MEASUREMENT__TIME;

	/**
	 * The feature id for the '<em><b>Timezone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPTH__TIMEZONE = PhysxPackage.MEASUREMENT__TIMEZONE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPTH__VALID = PhysxPackage.MEASUREMENT__VALID;

	/**
	 * The feature id for the '<em><b>Measurement Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPTH__MEASUREMENT_POSITION = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPTH__DEPTH = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Depth</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPTH_FEATURE_COUNT = PhysxPackage.MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.Direction <em>Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.Direction
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 21;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.LatitudeHemisphere <em>Latitude Hemisphere</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.LatitudeHemisphere
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getLatitudeHemisphere()
	 * @generated
	 */
	int LATITUDE_HEMISPHERE = 22;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.LongitudeHemisphere <em>Longitude Hemisphere</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.LongitudeHemisphere
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getLongitudeHemisphere()
	 * @generated
	 */
	int LONGITUDE_HEMISPHERE = 23;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition <em>Relative Depth Measurement Position</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition
	 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getRelativeDepthMeasurementPosition()
	 * @generated
	 */
	int RELATIVE_DEPTH_MEASUREMENT_POSITION = 24;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.GeoPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Position</em>'.
	 * @see net.sf.seesea.model.core.geo.GeoPosition
	 * @generated
	 */
	EClass getGeoPosition();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.GeoPosition#getLongitude <em>Longitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Longitude</em>'.
	 * @see net.sf.seesea.model.core.geo.GeoPosition#getLongitude()
	 * @see #getGeoPosition()
	 * @generated
	 */
	EReference getGeoPosition_Longitude();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.GeoPosition#getLatitude <em>Latitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Latitude</em>'.
	 * @see net.sf.seesea.model.core.geo.GeoPosition#getLatitude()
	 * @see #getGeoPosition()
	 * @generated
	 */
	EReference getGeoPosition_Latitude();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.Coordinate <em>Coordinate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Coordinate</em>'.
	 * @see net.sf.seesea.model.core.geo.Coordinate
	 * @generated
	 */
	EClass getCoordinate();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.Coordinate#getDegree <em>Degree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Degree</em>'.
	 * @see net.sf.seesea.model.core.geo.Coordinate#getDegree()
	 * @see #getCoordinate()
	 * @generated
	 */
	EAttribute getCoordinate_Degree();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.Coordinate#getMinute <em>Minute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minute</em>'.
	 * @see net.sf.seesea.model.core.geo.Coordinate#getMinute()
	 * @see #getCoordinate()
	 * @generated
	 */
	EAttribute getCoordinate_Minute();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.Coordinate#getSecond <em>Second</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Second</em>'.
	 * @see net.sf.seesea.model.core.geo.Coordinate#getSecond()
	 * @see #getCoordinate()
	 * @generated
	 */
	EAttribute getCoordinate_Second();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.Latitude <em>Latitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Latitude</em>'.
	 * @see net.sf.seesea.model.core.geo.Latitude
	 * @generated
	 */
	EClass getLatitude();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.Longitude <em>Longitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Longitude</em>'.
	 * @see net.sf.seesea.model.core.geo.Longitude
	 * @generated
	 */
	EClass getLongitude();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.Route <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Route</em>'.
	 * @see net.sf.seesea.model.core.geo.Route
	 * @generated
	 */
	EClass getRoute();

	/**
	 * Returns the meta object for the reference list '{@link net.sf.seesea.model.core.geo.Route#getWaypoints <em>Waypoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Waypoints</em>'.
	 * @see net.sf.seesea.model.core.geo.Route#getWaypoints()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Waypoints();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.NamedArtifact <em>Named Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Artifact</em>'.
	 * @see net.sf.seesea.model.core.geo.NamedArtifact
	 * @generated
	 */
	EClass getNamedArtifact();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.NamedArtifact#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see net.sf.seesea.model.core.geo.NamedArtifact#getName()
	 * @see #getNamedArtifact()
	 * @generated
	 */
	EAttribute getNamedArtifact_Name();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.NamedPosition <em>Named Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Position</em>'.
	 * @see net.sf.seesea.model.core.geo.NamedPosition
	 * @generated
	 */
	EClass getNamedPosition();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.RoutingContainer <em>Routing Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Routing Container</em>'.
	 * @see net.sf.seesea.model.core.geo.RoutingContainer
	 * @generated
	 */
	EClass getRoutingContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.geo.RoutingContainer#getRoutes <em>Routes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Routes</em>'.
	 * @see net.sf.seesea.model.core.geo.RoutingContainer#getRoutes()
	 * @see #getRoutingContainer()
	 * @generated
	 */
	EReference getRoutingContainer_Routes();

	/**
	 * Returns the meta object for the reference '{@link net.sf.seesea.model.core.geo.RoutingContainer#getArea <em>Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Area</em>'.
	 * @see net.sf.seesea.model.core.geo.RoutingContainer#getArea()
	 * @see #getRoutingContainer()
	 * @generated
	 */
	EReference getRoutingContainer_Area();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.POIContainer <em>POI Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>POI Container</em>'.
	 * @see net.sf.seesea.model.core.geo.POIContainer
	 * @generated
	 */
	EClass getPOIContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.geo.POIContainer#getPois <em>Pois</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pois</em>'.
	 * @see net.sf.seesea.model.core.geo.POIContainer#getPois()
	 * @see #getPOIContainer()
	 * @generated
	 */
	EReference getPOIContainer_Pois();

	/**
	 * Returns the meta object for the reference '{@link net.sf.seesea.model.core.geo.POIContainer#getArea <em>Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Area</em>'.
	 * @see net.sf.seesea.model.core.geo.POIContainer#getArea()
	 * @see #getPOIContainer()
	 * @generated
	 */
	EReference getPOIContainer_Area();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.Navarea <em>Navarea</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navarea</em>'.
	 * @see net.sf.seesea.model.core.geo.Navarea
	 * @generated
	 */
	EClass getNavarea();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.Navarea#getAreanumber <em>Areanumber</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Areanumber</em>'.
	 * @see net.sf.seesea.model.core.geo.Navarea#getAreanumber()
	 * @see #getNavarea()
	 * @generated
	 */
	EAttribute getNavarea_Areanumber();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.Depth <em>Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Depth</em>'.
	 * @see net.sf.seesea.model.core.geo.Depth
	 * @generated
	 */
	EClass getDepth();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.Depth#getMeasurementPosition <em>Measurement Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Measurement Position</em>'.
	 * @see net.sf.seesea.model.core.geo.Depth#getMeasurementPosition()
	 * @see #getDepth()
	 * @generated
	 */
	EAttribute getDepth_MeasurementPosition();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.Depth#getDepth <em>Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Depth</em>'.
	 * @see net.sf.seesea.model.core.geo.Depth#getDepth()
	 * @see #getDepth()
	 * @generated
	 */
	EAttribute getDepth_Depth();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.geo.Direction <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Direction</em>'.
	 * @see net.sf.seesea.model.core.geo.Direction
	 * @generated
	 */
	EEnum getDirection();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.geo.LatitudeHemisphere <em>Latitude Hemisphere</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Latitude Hemisphere</em>'.
	 * @see net.sf.seesea.model.core.geo.LatitudeHemisphere
	 * @generated
	 */
	EEnum getLatitudeHemisphere();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.geo.LongitudeHemisphere <em>Longitude Hemisphere</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Longitude Hemisphere</em>'.
	 * @see net.sf.seesea.model.core.geo.LongitudeHemisphere
	 * @generated
	 */
	EEnum getLongitudeHemisphere();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition <em>Relative Depth Measurement Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Relative Depth Measurement Position</em>'.
	 * @see net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition
	 * @generated
	 */
	EEnum getRelativeDepthMeasurementPosition();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.ChartContainer <em>Chart Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Chart Container</em>'.
	 * @see net.sf.seesea.model.core.geo.ChartContainer
	 * @generated
	 */
	EClass getChartContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.geo.ChartContainer#getCharts <em>Charts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Charts</em>'.
	 * @see net.sf.seesea.model.core.geo.ChartContainer#getCharts()
	 * @see #getChartContainer()
	 * @generated
	 */
	EReference getChartContainer_Charts();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.NavigationCompound <em>Navigation Compound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Compound</em>'.
	 * @see net.sf.seesea.model.core.geo.NavigationCompound
	 * @generated
	 */
	EClass getNavigationCompound();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.NavigationCompound#getPoiContainer <em>Poi Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Poi Container</em>'.
	 * @see net.sf.seesea.model.core.geo.NavigationCompound#getPoiContainer()
	 * @see #getNavigationCompound()
	 * @generated
	 */
	EReference getNavigationCompound_PoiContainer();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.NavigationCompound#getRoutingContainer <em>Routing Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Routing Container</em>'.
	 * @see net.sf.seesea.model.core.geo.NavigationCompound#getRoutingContainer()
	 * @see #getNavigationCompound()
	 * @generated
	 */
	EReference getNavigationCompound_RoutingContainer();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.NavigationCompound#getTracksContainer <em>Tracks Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tracks Container</em>'.
	 * @see net.sf.seesea.model.core.geo.NavigationCompound#getTracksContainer()
	 * @see #getNavigationCompound()
	 * @generated
	 */
	EReference getNavigationCompound_TracksContainer();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.Chart <em>Chart</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Chart</em>'.
	 * @see net.sf.seesea.model.core.geo.Chart
	 * @generated
	 */
	EClass getChart();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.Chart#getChartConfiguration <em>Chart Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Chart Configuration</em>'.
	 * @see net.sf.seesea.model.core.geo.Chart#getChartConfiguration()
	 * @see #getChart()
	 * @generated
	 */
	EReference getChart_ChartConfiguration();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.GeoPosition3D <em>Position3 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Position3 D</em>'.
	 * @see net.sf.seesea.model.core.geo.GeoPosition3D
	 * @generated
	 */
	EClass getGeoPosition3D();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.GeoPosition3D#getAltitude <em>Altitude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Altitude</em>'.
	 * @see net.sf.seesea.model.core.geo.GeoPosition3D#getAltitude()
	 * @see #getGeoPosition3D()
	 * @generated
	 */
	EAttribute getGeoPosition3D_Altitude();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.MeasuredPosition3D <em>Measured Position3 D</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measured Position3 D</em>'.
	 * @see net.sf.seesea.model.core.geo.MeasuredPosition3D
	 * @generated
	 */
	EClass getMeasuredPosition3D();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.TracksContainer <em>Tracks Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tracks Container</em>'.
	 * @see net.sf.seesea.model.core.geo.TracksContainer
	 * @generated
	 */
	EClass getTracksContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.geo.TracksContainer#getTracks <em>Tracks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tracks</em>'.
	 * @see net.sf.seesea.model.core.geo.TracksContainer#getTracks()
	 * @see #getTracksContainer()
	 * @generated
	 */
	EReference getTracksContainer_Tracks();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.Track <em>Track</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Track</em>'.
	 * @see net.sf.seesea.model.core.geo.Track
	 * @generated
	 */
	EClass getTrack();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.geo.Track#getMeasuredPosition <em>Measured Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measured Position</em>'.
	 * @see net.sf.seesea.model.core.geo.Track#getMeasuredPosition()
	 * @see #getTrack()
	 * @generated
	 */
	EReference getTrack_MeasuredPosition();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.ChartSymbol <em>Chart Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Chart Symbol</em>'.
	 * @see net.sf.seesea.model.core.geo.ChartSymbol
	 * @generated
	 */
	EClass getChartSymbol();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.ChartArea <em>Chart Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Chart Area</em>'.
	 * @see net.sf.seesea.model.core.geo.ChartArea
	 * @generated
	 */
	EClass getChartArea();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.geo.ChartArea#getBounds <em>Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bounds</em>'.
	 * @see net.sf.seesea.model.core.geo.ChartArea#getBounds()
	 * @see #getChartArea()
	 * @generated
	 */
	EReference getChartArea_Bounds();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.ChartWay <em>Chart Way</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Chart Way</em>'.
	 * @see net.sf.seesea.model.core.geo.ChartWay
	 * @generated
	 */
	EClass getChartWay();

	/**
	 * Returns the meta object for the reference list '{@link net.sf.seesea.model.core.geo.ChartWay#getWaypoints <em>Waypoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Waypoints</em>'.
	 * @see net.sf.seesea.model.core.geo.ChartWay#getWaypoints()
	 * @see #getChartWay()
	 * @generated
	 */
	EReference getChartWay_Waypoints();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GeoFactory getGeoFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.GeoPositionImpl <em>Position</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.GeoPositionImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getGeoPosition()
		 * @generated
		 */
		EClass GEO_POSITION = eINSTANCE.getGeoPosition();

		/**
		 * The meta object literal for the '<em><b>Longitude</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEO_POSITION__LONGITUDE = eINSTANCE.getGeoPosition_Longitude();

		/**
		 * The meta object literal for the '<em><b>Latitude</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEO_POSITION__LATITUDE = eINSTANCE.getGeoPosition_Latitude();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.CoordinateImpl <em>Coordinate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.CoordinateImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getCoordinate()
		 * @generated
		 */
		EClass COORDINATE = eINSTANCE.getCoordinate();

		/**
		 * The meta object literal for the '<em><b>Degree</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COORDINATE__DEGREE = eINSTANCE.getCoordinate_Degree();

		/**
		 * The meta object literal for the '<em><b>Minute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COORDINATE__MINUTE = eINSTANCE.getCoordinate_Minute();

		/**
		 * The meta object literal for the '<em><b>Second</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COORDINATE__SECOND = eINSTANCE.getCoordinate_Second();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.LatitudeImpl <em>Latitude</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.LatitudeImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getLatitude()
		 * @generated
		 */
		EClass LATITUDE = eINSTANCE.getLatitude();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.LongitudeImpl <em>Longitude</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.LongitudeImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getLongitude()
		 * @generated
		 */
		EClass LONGITUDE = eINSTANCE.getLongitude();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.RouteImpl <em>Route</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.RouteImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getRoute()
		 * @generated
		 */
		EClass ROUTE = eINSTANCE.getRoute();

		/**
		 * The meta object literal for the '<em><b>Waypoints</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__WAYPOINTS = eINSTANCE.getRoute_Waypoints();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.NamedArtifactImpl <em>Named Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.NamedArtifactImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getNamedArtifact()
		 * @generated
		 */
		EClass NAMED_ARTIFACT = eINSTANCE.getNamedArtifact();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ARTIFACT__NAME = eINSTANCE.getNamedArtifact_Name();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.NamedPositionImpl <em>Named Position</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.NamedPositionImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getNamedPosition()
		 * @generated
		 */
		EClass NAMED_POSITION = eINSTANCE.getNamedPosition();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.RoutingContainerImpl <em>Routing Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.RoutingContainerImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getRoutingContainer()
		 * @generated
		 */
		EClass ROUTING_CONTAINER = eINSTANCE.getRoutingContainer();

		/**
		 * The meta object literal for the '<em><b>Routes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTING_CONTAINER__ROUTES = eINSTANCE.getRoutingContainer_Routes();

		/**
		 * The meta object literal for the '<em><b>Area</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTING_CONTAINER__AREA = eINSTANCE.getRoutingContainer_Area();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.POIContainerImpl <em>POI Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.POIContainerImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getPOIContainer()
		 * @generated
		 */
		EClass POI_CONTAINER = eINSTANCE.getPOIContainer();

		/**
		 * The meta object literal for the '<em><b>Pois</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POI_CONTAINER__POIS = eINSTANCE.getPOIContainer_Pois();

		/**
		 * The meta object literal for the '<em><b>Area</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POI_CONTAINER__AREA = eINSTANCE.getPOIContainer_Area();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.NavareaImpl <em>Navarea</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.NavareaImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getNavarea()
		 * @generated
		 */
		EClass NAVAREA = eINSTANCE.getNavarea();

		/**
		 * The meta object literal for the '<em><b>Areanumber</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVAREA__AREANUMBER = eINSTANCE.getNavarea_Areanumber();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.DepthImpl <em>Depth</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.DepthImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getDepth()
		 * @generated
		 */
		EClass DEPTH = eINSTANCE.getDepth();

		/**
		 * The meta object literal for the '<em><b>Measurement Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPTH__MEASUREMENT_POSITION = eINSTANCE.getDepth_MeasurementPosition();

		/**
		 * The meta object literal for the '<em><b>Depth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPTH__DEPTH = eINSTANCE.getDepth_Depth();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.Direction <em>Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.Direction
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getDirection()
		 * @generated
		 */
		EEnum DIRECTION = eINSTANCE.getDirection();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.LatitudeHemisphere <em>Latitude Hemisphere</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.LatitudeHemisphere
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getLatitudeHemisphere()
		 * @generated
		 */
		EEnum LATITUDE_HEMISPHERE = eINSTANCE.getLatitudeHemisphere();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.LongitudeHemisphere <em>Longitude Hemisphere</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.LongitudeHemisphere
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getLongitudeHemisphere()
		 * @generated
		 */
		EEnum LONGITUDE_HEMISPHERE = eINSTANCE.getLongitudeHemisphere();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition <em>Relative Depth Measurement Position</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getRelativeDepthMeasurementPosition()
		 * @generated
		 */
		EEnum RELATIVE_DEPTH_MEASUREMENT_POSITION = eINSTANCE.getRelativeDepthMeasurementPosition();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.ChartContainerImpl <em>Chart Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.ChartContainerImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChartContainer()
		 * @generated
		 */
		EClass CHART_CONTAINER = eINSTANCE.getChartContainer();

		/**
		 * The meta object literal for the '<em><b>Charts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHART_CONTAINER__CHARTS = eINSTANCE.getChartContainer_Charts();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.NavigationCompoundImpl <em>Navigation Compound</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.NavigationCompoundImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getNavigationCompound()
		 * @generated
		 */
		EClass NAVIGATION_COMPOUND = eINSTANCE.getNavigationCompound();

		/**
		 * The meta object literal for the '<em><b>Poi Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_COMPOUND__POI_CONTAINER = eINSTANCE.getNavigationCompound_PoiContainer();

		/**
		 * The meta object literal for the '<em><b>Routing Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_COMPOUND__ROUTING_CONTAINER = eINSTANCE.getNavigationCompound_RoutingContainer();

		/**
		 * The meta object literal for the '<em><b>Tracks Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_COMPOUND__TRACKS_CONTAINER = eINSTANCE.getNavigationCompound_TracksContainer();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.ChartImpl <em>Chart</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.ChartImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChart()
		 * @generated
		 */
		EClass CHART = eINSTANCE.getChart();

		/**
		 * The meta object literal for the '<em><b>Chart Configuration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHART__CHART_CONFIGURATION = eINSTANCE.getChart_ChartConfiguration();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.GeoPosition3DImpl <em>Position3 D</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.GeoPosition3DImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getGeoPosition3D()
		 * @generated
		 */
		EClass GEO_POSITION3_D = eINSTANCE.getGeoPosition3D();

		/**
		 * The meta object literal for the '<em><b>Altitude</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_POSITION3_D__ALTITUDE = eINSTANCE.getGeoPosition3D_Altitude();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.MeasuredPosition3DImpl <em>Measured Position3 D</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.MeasuredPosition3DImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getMeasuredPosition3D()
		 * @generated
		 */
		EClass MEASURED_POSITION3_D = eINSTANCE.getMeasuredPosition3D();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.TracksContainerImpl <em>Tracks Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.TracksContainerImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getTracksContainer()
		 * @generated
		 */
		EClass TRACKS_CONTAINER = eINSTANCE.getTracksContainer();

		/**
		 * The meta object literal for the '<em><b>Tracks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACKS_CONTAINER__TRACKS = eINSTANCE.getTracksContainer_Tracks();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.TrackImpl <em>Track</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.TrackImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getTrack()
		 * @generated
		 */
		EClass TRACK = eINSTANCE.getTrack();

		/**
		 * The meta object literal for the '<em><b>Measured Position</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK__MEASURED_POSITION = eINSTANCE.getTrack_MeasuredPosition();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.ChartSymbolImpl <em>Chart Symbol</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.ChartSymbolImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChartSymbol()
		 * @generated
		 */
		EClass CHART_SYMBOL = eINSTANCE.getChartSymbol();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.ChartAreaImpl <em>Chart Area</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.ChartAreaImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChartArea()
		 * @generated
		 */
		EClass CHART_AREA = eINSTANCE.getChartArea();

		/**
		 * The meta object literal for the '<em><b>Bounds</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHART_AREA__BOUNDS = eINSTANCE.getChartArea_Bounds();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.impl.ChartWayImpl <em>Chart Way</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.impl.ChartWayImpl
		 * @see net.sf.seesea.model.core.geo.impl.GeoPackageImpl#getChartWay()
		 * @generated
		 */
		EClass CHART_WAY = eINSTANCE.getChartWay();

		/**
		 * The meta object literal for the '<em><b>Waypoints</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHART_WAY__WAYPOINTS = eINSTANCE.getChartWay_Waypoints();

	}

} //GeoPackage
