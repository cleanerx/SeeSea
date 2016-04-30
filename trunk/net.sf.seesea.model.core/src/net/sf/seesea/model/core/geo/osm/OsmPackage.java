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
package net.sf.seesea.model.core.geo.osm;

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
 * @see net.sf.seesea.model.core.geo.osm.OsmFactory
 * @model kind="package"
 * @generated
 */
public interface OsmPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "osm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "osm";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "osm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OsmPackage eINSTANCE = net.sf.seesea.model.core.geo.osm.impl.OsmPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.osm.impl.AreaImpl <em>Area</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.osm.impl.AreaImpl
	 * @see net.sf.seesea.model.core.geo.osm.impl.OsmPackageImpl#getArea()
	 * @generated
	 */
	int AREA = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA__NAME = GeoPackage.CHART__NAME;

	/**
	 * The feature id for the '<em><b>Chart Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA__CHART_CONFIGURATION = GeoPackage.CHART__CHART_CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Poi Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA__POI_CONTAINER = GeoPackage.CHART_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Routing Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA__ROUTING_CONTAINER = GeoPackage.CHART_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tracks Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA__TRACKS_CONTAINER = GeoPackage.CHART_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Zoom Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA__ZOOM_LEVEL = GeoPackage.CHART_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Map Center Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA__MAP_CENTER_POSITION = GeoPackage.CHART_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Sub Area</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA__SUB_AREA = GeoPackage.CHART_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Area</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AREA_FEATURE_COUNT = GeoPackage.CHART_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.geo.osm.impl.WorldImpl <em>World</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.geo.osm.impl.WorldImpl
	 * @see net.sf.seesea.model.core.geo.osm.impl.OsmPackageImpl#getWorld()
	 * @generated
	 */
	int WORLD = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__NAME = AREA__NAME;

	/**
	 * The feature id for the '<em><b>Chart Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__CHART_CONFIGURATION = AREA__CHART_CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Poi Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__POI_CONTAINER = AREA__POI_CONTAINER;

	/**
	 * The feature id for the '<em><b>Routing Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__ROUTING_CONTAINER = AREA__ROUTING_CONTAINER;

	/**
	 * The feature id for the '<em><b>Tracks Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__TRACKS_CONTAINER = AREA__TRACKS_CONTAINER;

	/**
	 * The feature id for the '<em><b>Zoom Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__ZOOM_LEVEL = AREA__ZOOM_LEVEL;

	/**
	 * The feature id for the '<em><b>Map Center Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__MAP_CENTER_POSITION = AREA__MAP_CENTER_POSITION;

	/**
	 * The feature id for the '<em><b>Sub Area</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__SUB_AREA = AREA__SUB_AREA;

	/**
	 * The feature id for the '<em><b>Longitude Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__LONGITUDE_SCALE = AREA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Latitude Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__LATITUDE_SCALE = AREA_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Anchor Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__ANCHOR_POSITION = AREA_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cursor Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__CURSOR_POSITION = AREA_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Trip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__TRIP = AREA_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Total Trip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__TOTAL_TRIP = AREA_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>World</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_FEATURE_COUNT = AREA_FEATURE_COUNT + 6;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.osm.Area <em>Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Area</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.Area
	 * @generated
	 */
	EClass getArea();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.osm.Area#getZoomLevel <em>Zoom Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zoom Level</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.Area#getZoomLevel()
	 * @see #getArea()
	 * @generated
	 */
	EAttribute getArea_ZoomLevel();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.osm.Area#getMapCenterPosition <em>Map Center Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Map Center Position</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.Area#getMapCenterPosition()
	 * @see #getArea()
	 * @generated
	 */
	EReference getArea_MapCenterPosition();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.geo.osm.Area#getSubArea <em>Sub Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Area</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.Area#getSubArea()
	 * @see #getArea()
	 * @generated
	 */
	EReference getArea_SubArea();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.geo.osm.World <em>World</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>World</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.World
	 * @generated
	 */
	EClass getWorld();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.osm.World#isLongitudeScale <em>Longitude Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Longitude Scale</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.World#isLongitudeScale()
	 * @see #getWorld()
	 * @generated
	 */
	EAttribute getWorld_LongitudeScale();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.osm.World#isLatitudeScale <em>Latitude Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latitude Scale</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.World#isLatitudeScale()
	 * @see #getWorld()
	 * @generated
	 */
	EAttribute getWorld_LatitudeScale();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.osm.World#getAnchorPosition <em>Anchor Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Anchor Position</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.World#getAnchorPosition()
	 * @see #getWorld()
	 * @generated
	 */
	EReference getWorld_AnchorPosition();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.geo.osm.World#getCursorPosition <em>Cursor Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cursor Position</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.World#getCursorPosition()
	 * @see #getWorld()
	 * @generated
	 */
	EReference getWorld_CursorPosition();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.osm.World#getTrip <em>Trip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trip</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.World#getTrip()
	 * @see #getWorld()
	 * @generated
	 */
	EAttribute getWorld_Trip();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.core.geo.osm.World#getTotalTrip <em>Total Trip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Trip</em>'.
	 * @see net.sf.seesea.model.core.geo.osm.World#getTotalTrip()
	 * @see #getWorld()
	 * @generated
	 */
	EAttribute getWorld_TotalTrip();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OsmFactory getOsmFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.osm.impl.AreaImpl <em>Area</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.osm.impl.AreaImpl
		 * @see net.sf.seesea.model.core.geo.osm.impl.OsmPackageImpl#getArea()
		 * @generated
		 */
		EClass AREA = eINSTANCE.getArea();

		/**
		 * The meta object literal for the '<em><b>Zoom Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AREA__ZOOM_LEVEL = eINSTANCE.getArea_ZoomLevel();

		/**
		 * The meta object literal for the '<em><b>Map Center Position</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AREA__MAP_CENTER_POSITION = eINSTANCE.getArea_MapCenterPosition();

		/**
		 * The meta object literal for the '<em><b>Sub Area</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AREA__SUB_AREA = eINSTANCE.getArea_SubArea();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.geo.osm.impl.WorldImpl <em>World</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.geo.osm.impl.WorldImpl
		 * @see net.sf.seesea.model.core.geo.osm.impl.OsmPackageImpl#getWorld()
		 * @generated
		 */
		EClass WORLD = eINSTANCE.getWorld();

		/**
		 * The meta object literal for the '<em><b>Longitude Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORLD__LONGITUDE_SCALE = eINSTANCE.getWorld_LongitudeScale();

		/**
		 * The meta object literal for the '<em><b>Latitude Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORLD__LATITUDE_SCALE = eINSTANCE.getWorld_LatitudeScale();

		/**
		 * The meta object literal for the '<em><b>Anchor Position</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD__ANCHOR_POSITION = eINSTANCE.getWorld_AnchorPosition();

		/**
		 * The meta object literal for the '<em><b>Cursor Position</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD__CURSOR_POSITION = eINSTANCE.getWorld_CursorPosition();

		/**
		 * The meta object literal for the '<em><b>Trip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORLD__TRIP = eINSTANCE.getWorld_Trip();

		/**
		 * The meta object literal for the '<em><b>Total Trip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORLD__TOTAL_TRIP = eINSTANCE.getWorld_TotalTrip();

	}

} //OsmPackage
