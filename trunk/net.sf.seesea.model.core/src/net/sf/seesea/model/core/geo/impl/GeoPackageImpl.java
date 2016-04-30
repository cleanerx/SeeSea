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
package net.sf.seesea.model.core.geo.impl;

import net.sf.seesea.model.core.CorePackage;

import net.sf.seesea.model.core.data.DataPackage;

import net.sf.seesea.model.core.data.impl.DataPackageImpl;

import net.sf.seesea.model.core.diagramInterchange.DiagramInterchangePackage;
import net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl;
import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.model.core.geo.Chart;
import net.sf.seesea.model.core.geo.ChartArea;
import net.sf.seesea.model.core.geo.ChartContainer;
import net.sf.seesea.model.core.geo.ChartSymbol;
import net.sf.seesea.model.core.geo.ChartWay;
import net.sf.seesea.model.core.geo.Coordinate;
import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.Direction;
import net.sf.seesea.model.core.geo.GNSSMeasuredPosition;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.GeoPosition3D;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.LatitudeHemisphere;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.LongitudeHemisphere;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.NamedArtifact;
import net.sf.seesea.model.core.geo.NamedPosition;
import net.sf.seesea.model.core.geo.Navarea;
import net.sf.seesea.model.core.geo.NavigationCompound;
import net.sf.seesea.model.core.geo.POIContainer;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;
import net.sf.seesea.model.core.geo.Route;
import net.sf.seesea.model.core.geo.RoutingContainer;

import net.sf.seesea.model.core.geo.Track;
import net.sf.seesea.model.core.geo.TracksContainer;
import net.sf.seesea.model.core.geo.osm.OsmPackage;

import net.sf.seesea.model.core.geo.osm.impl.OsmPackageImpl;

import net.sf.seesea.model.core.impl.CorePackageImpl;

import net.sf.seesea.model.core.physx.PhysxPackage;

import net.sf.seesea.model.core.physx.impl.PhysxPackageImpl;

import net.sf.seesea.model.core.weather.WeatherPackage;

import net.sf.seesea.model.core.weather.impl.WeatherPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GeoPackageImpl extends EPackageImpl implements GeoPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geoPositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass coordinateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass latitudeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longitudeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass routeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedArtifactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedPositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass routingContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass poiContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navareaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass depthEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gnssMeasuredPositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anchorPositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geoBoundingBoxEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum directionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum latitudeHemisphereEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum longitudeHemisphereEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum relativeDepthMeasurementPositionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass chartContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationCompoundEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass chartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geoPosition3DEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measuredPosition3DEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tracksContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trackEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass chartSymbolEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass chartAreaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass chartWayEClass = null;

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
	 * @see net.sf.seesea.model.core.geo.GeoPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GeoPackageImpl() {
		super(eNS_URI, GeoFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link GeoPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GeoPackage init() {
		if (isInited) return (GeoPackage)EPackage.Registry.INSTANCE.getEPackage(GeoPackage.eNS_URI);

		// Obtain or create and register package
		GeoPackageImpl theGeoPackage = (GeoPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GeoPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GeoPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		OsmPackageImpl theOsmPackage = (OsmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OsmPackage.eNS_URI) instanceof OsmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OsmPackage.eNS_URI) : OsmPackage.eINSTANCE);
		PhysxPackageImpl thePhysxPackage = (PhysxPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PhysxPackage.eNS_URI) instanceof PhysxPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PhysxPackage.eNS_URI) : PhysxPackage.eINSTANCE);
		WeatherPackageImpl theWeatherPackage = (WeatherPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WeatherPackage.eNS_URI) instanceof WeatherPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WeatherPackage.eNS_URI) : WeatherPackage.eINSTANCE);
		DataPackageImpl theDataPackage = (DataPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DataPackage.eNS_URI) instanceof DataPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DataPackage.eNS_URI) : DataPackage.eINSTANCE);
		DiagramInterchangePackageImpl theDiagramInterchangePackage = (DiagramInterchangePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DiagramInterchangePackage.eNS_URI) instanceof DiagramInterchangePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DiagramInterchangePackage.eNS_URI) : DiagramInterchangePackage.eINSTANCE);

		// Create package meta-data objects
		theGeoPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theOsmPackage.createPackageContents();
		thePhysxPackage.createPackageContents();
		theWeatherPackage.createPackageContents();
		theDataPackage.createPackageContents();
		theDiagramInterchangePackage.createPackageContents();

		// Initialize created meta-data
		theGeoPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theOsmPackage.initializePackageContents();
		thePhysxPackage.initializePackageContents();
		theWeatherPackage.initializePackageContents();
		theDataPackage.initializePackageContents();
		theDiagramInterchangePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGeoPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GeoPackage.eNS_URI, theGeoPackage);
		return theGeoPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeoPosition() {
		return geoPositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeoPosition_Longitude() {
		return (EReference)geoPositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeoPosition_Latitude() {
		return (EReference)geoPositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeoPosition_Precision() {
		return (EAttribute)geoPositionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCoordinate() {
		return coordinateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoordinate_DecimalDegree() {
		return (EAttribute)coordinateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLatitude() {
		return latitudeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLongitude() {
		return longitudeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoute() {
		return routeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoute_Waypoints() {
		return (EReference)routeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedArtifact() {
		return namedArtifactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedArtifact_Name() {
		return (EAttribute)namedArtifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedPosition() {
		return namedPositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoutingContainer() {
		return routingContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoutingContainer_Routes() {
		return (EReference)routingContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoutingContainer_Area() {
		return (EReference)routingContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPOIContainer() {
		return poiContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPOIContainer_Pois() {
		return (EReference)poiContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPOIContainer_Area() {
		return (EReference)poiContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavarea() {
		return navareaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNavarea_Areanumber() {
		return (EAttribute)navareaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDepth() {
		return depthEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDepth_MeasurementPosition() {
		return (EAttribute)depthEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDepth_Depth() {
		return (EAttribute)depthEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGNSSMeasuredPosition() {
		return gnssMeasuredPositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGNSSMeasuredPosition_Hdop() {
		return (EAttribute)gnssMeasuredPositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGNSSMeasuredPosition_Vdop() {
		return (EAttribute)gnssMeasuredPositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGNSSMeasuredPosition_Pdop() {
		return (EAttribute)gnssMeasuredPositionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGNSSMeasuredPosition_Augmentation() {
		return (EAttribute)gnssMeasuredPositionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnchorPosition() {
		return anchorPositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnchorPosition_XExtent() {
		return (EAttribute)anchorPositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnchorPosition_YExtent() {
		return (EAttribute)anchorPositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeoBoundingBox() {
		return geoBoundingBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeoBoundingBox_Top() {
		return (EAttribute)geoBoundingBoxEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeoBoundingBox_Bottom() {
		return (EAttribute)geoBoundingBoxEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeoBoundingBox_Left() {
		return (EAttribute)geoBoundingBoxEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeoBoundingBox_Right() {
		return (EAttribute)geoBoundingBoxEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDirection() {
		return directionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLatitudeHemisphere() {
		return latitudeHemisphereEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLongitudeHemisphere() {
		return longitudeHemisphereEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRelativeDepthMeasurementPosition() {
		return relativeDepthMeasurementPositionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChartContainer() {
		return chartContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChartContainer_Charts() {
		return (EReference)chartContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavigationCompound() {
		return navigationCompoundEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationCompound_PoiContainer() {
		return (EReference)navigationCompoundEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationCompound_RoutingContainer() {
		return (EReference)navigationCompoundEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationCompound_TracksContainer() {
		return (EReference)navigationCompoundEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChart() {
		return chartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChart_ChartConfiguration() {
		return (EReference)chartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeoPosition3D() {
		return geoPosition3DEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeoPosition3D_Altitude() {
		return (EAttribute)geoPosition3DEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasuredPosition3D() {
		return measuredPosition3DEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTracksContainer() {
		return tracksContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTracksContainer_Tracks() {
		return (EReference)tracksContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrack() {
		return trackEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrack_MeasuredPosition() {
		return (EReference)trackEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChartSymbol() {
		return chartSymbolEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChartArea() {
		return chartAreaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChartArea_Bounds() {
		return (EReference)chartAreaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChartWay() {
		return chartWayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChartWay_Waypoints() {
		return (EReference)chartWayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoFactory getGeoFactory() {
		return (GeoFactory)getEFactoryInstance();
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
		geoPositionEClass = createEClass(GEO_POSITION);
		createEReference(geoPositionEClass, GEO_POSITION__LONGITUDE);
		createEReference(geoPositionEClass, GEO_POSITION__LATITUDE);
		createEAttribute(geoPositionEClass, GEO_POSITION__PRECISION);

		coordinateEClass = createEClass(COORDINATE);
		createEAttribute(coordinateEClass, COORDINATE__DECIMAL_DEGREE);

		latitudeEClass = createEClass(LATITUDE);

		longitudeEClass = createEClass(LONGITUDE);

		routeEClass = createEClass(ROUTE);
		createEReference(routeEClass, ROUTE__WAYPOINTS);

		namedArtifactEClass = createEClass(NAMED_ARTIFACT);
		createEAttribute(namedArtifactEClass, NAMED_ARTIFACT__NAME);

		namedPositionEClass = createEClass(NAMED_POSITION);

		routingContainerEClass = createEClass(ROUTING_CONTAINER);
		createEReference(routingContainerEClass, ROUTING_CONTAINER__ROUTES);
		createEReference(routingContainerEClass, ROUTING_CONTAINER__AREA);

		poiContainerEClass = createEClass(POI_CONTAINER);
		createEReference(poiContainerEClass, POI_CONTAINER__POIS);
		createEReference(poiContainerEClass, POI_CONTAINER__AREA);

		chartContainerEClass = createEClass(CHART_CONTAINER);
		createEReference(chartContainerEClass, CHART_CONTAINER__CHARTS);

		navigationCompoundEClass = createEClass(NAVIGATION_COMPOUND);
		createEReference(navigationCompoundEClass, NAVIGATION_COMPOUND__POI_CONTAINER);
		createEReference(navigationCompoundEClass, NAVIGATION_COMPOUND__ROUTING_CONTAINER);
		createEReference(navigationCompoundEClass, NAVIGATION_COMPOUND__TRACKS_CONTAINER);

		chartEClass = createEClass(CHART);
		createEReference(chartEClass, CHART__CHART_CONFIGURATION);

		geoPosition3DEClass = createEClass(GEO_POSITION3_D);
		createEAttribute(geoPosition3DEClass, GEO_POSITION3_D__ALTITUDE);

		measuredPosition3DEClass = createEClass(MEASURED_POSITION3_D);

		tracksContainerEClass = createEClass(TRACKS_CONTAINER);
		createEReference(tracksContainerEClass, TRACKS_CONTAINER__TRACKS);

		trackEClass = createEClass(TRACK);
		createEReference(trackEClass, TRACK__MEASURED_POSITION);

		chartSymbolEClass = createEClass(CHART_SYMBOL);

		chartAreaEClass = createEClass(CHART_AREA);
		createEReference(chartAreaEClass, CHART_AREA__BOUNDS);

		chartWayEClass = createEClass(CHART_WAY);
		createEReference(chartWayEClass, CHART_WAY__WAYPOINTS);

		navareaEClass = createEClass(NAVAREA);
		createEAttribute(navareaEClass, NAVAREA__AREANUMBER);

		depthEClass = createEClass(DEPTH);
		createEAttribute(depthEClass, DEPTH__MEASUREMENT_POSITION);
		createEAttribute(depthEClass, DEPTH__DEPTH);

		gnssMeasuredPositionEClass = createEClass(GNSS_MEASURED_POSITION);
		createEAttribute(gnssMeasuredPositionEClass, GNSS_MEASURED_POSITION__HDOP);
		createEAttribute(gnssMeasuredPositionEClass, GNSS_MEASURED_POSITION__VDOP);
		createEAttribute(gnssMeasuredPositionEClass, GNSS_MEASURED_POSITION__PDOP);
		createEAttribute(gnssMeasuredPositionEClass, GNSS_MEASURED_POSITION__AUGMENTATION);

		anchorPositionEClass = createEClass(ANCHOR_POSITION);
		createEAttribute(anchorPositionEClass, ANCHOR_POSITION__XEXTENT);
		createEAttribute(anchorPositionEClass, ANCHOR_POSITION__YEXTENT);

		geoBoundingBoxEClass = createEClass(GEO_BOUNDING_BOX);
		createEAttribute(geoBoundingBoxEClass, GEO_BOUNDING_BOX__TOP);
		createEAttribute(geoBoundingBoxEClass, GEO_BOUNDING_BOX__BOTTOM);
		createEAttribute(geoBoundingBoxEClass, GEO_BOUNDING_BOX__LEFT);
		createEAttribute(geoBoundingBoxEClass, GEO_BOUNDING_BOX__RIGHT);

		// Create enums
		directionEEnum = createEEnum(DIRECTION);
		latitudeHemisphereEEnum = createEEnum(LATITUDE_HEMISPHERE);
		longitudeHemisphereEEnum = createEEnum(LONGITUDE_HEMISPHERE);
		relativeDepthMeasurementPositionEEnum = createEEnum(RELATIVE_DEPTH_MEASUREMENT_POSITION);
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
		OsmPackage theOsmPackage = (OsmPackage)EPackage.Registry.INSTANCE.getEPackage(OsmPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		DiagramInterchangePackage theDiagramInterchangePackage = (DiagramInterchangePackage)EPackage.Registry.INSTANCE.getEPackage(DiagramInterchangePackage.eNS_URI);
		PhysxPackage thePhysxPackage = (PhysxPackage)EPackage.Registry.INSTANCE.getEPackage(PhysxPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theOsmPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		geoPositionEClass.getESuperTypes().add(theCorePackage.getModelObject());
		coordinateEClass.getESuperTypes().add(theCorePackage.getModelObject());
		latitudeEClass.getESuperTypes().add(this.getCoordinate());
		longitudeEClass.getESuperTypes().add(this.getCoordinate());
		routeEClass.getESuperTypes().add(this.getNamedArtifact());
		namedArtifactEClass.getESuperTypes().add(theCorePackage.getModelObject());
		namedPositionEClass.getESuperTypes().add(this.getGeoPosition());
		namedPositionEClass.getESuperTypes().add(this.getNamedArtifact());
		routingContainerEClass.getESuperTypes().add(theCorePackage.getModelObject());
		poiContainerEClass.getESuperTypes().add(theCorePackage.getModelObject());
		chartContainerEClass.getESuperTypes().add(theCorePackage.getModelObject());
		navigationCompoundEClass.getESuperTypes().add(theCorePackage.getModelObject());
		chartEClass.getESuperTypes().add(this.getNamedArtifact());
		geoPosition3DEClass.getESuperTypes().add(this.getGeoPosition());
		measuredPosition3DEClass.getESuperTypes().add(thePhysxPackage.getMeasurement());
		measuredPosition3DEClass.getESuperTypes().add(this.getGeoPosition3D());
		tracksContainerEClass.getESuperTypes().add(theCorePackage.getModelObject());
		trackEClass.getESuperTypes().add(theCorePackage.getModelObject());
		chartAreaEClass.getESuperTypes().add(theCorePackage.getModelObject());
		chartWayEClass.getESuperTypes().add(theCorePackage.getModelObject());
		navareaEClass.getESuperTypes().add(theOsmPackage.getArea());
		depthEClass.getESuperTypes().add(thePhysxPackage.getMeasurement());
		gnssMeasuredPositionEClass.getESuperTypes().add(this.getMeasuredPosition3D());
		anchorPositionEClass.getESuperTypes().add(this.getGeoPosition());
		geoBoundingBoxEClass.getESuperTypes().add(theCorePackage.getModelObject());

		// Initialize classes and features; add operations and parameters
		initEClass(geoPositionEClass, GeoPosition.class, "GeoPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getGeoPosition_Longitude(), this.getLongitude(), null, "longitude", null, 0, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getGeoPosition_Latitude(), this.getLatitude(), null, "latitude", null, 0, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGeoPosition_Precision(), ecorePackage.getEInt(), "precision", null, 0, 1, GeoPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(coordinateEClass, Coordinate.class, "Coordinate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCoordinate_DecimalDegree(), ecorePackage.getEDouble(), "decimalDegree", "0", 0, 1, Coordinate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		addEOperation(coordinateEClass, ecorePackage.getEInt(), "getDegree", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(coordinateEClass, ecorePackage.getEInt(), "getMinute", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(coordinateEClass, ecorePackage.getEDouble(), "getSecond", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		EOperation op = addEOperation(coordinateEClass, null, "setDegree", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEInt(), "degree", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(coordinateEClass, null, "setMinute", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEInt(), "minute", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(coordinateEClass, null, "setSecond", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDouble(), "seconds", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(latitudeEClass, Latitude.class, "Latitude", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(longitudeEClass, Longitude.class, "Longitude", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(routeEClass, Route.class, "Route", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRoute_Waypoints(), this.getNamedPosition(), null, "waypoints", null, 0, -1, Route.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(namedArtifactEClass, NamedArtifact.class, "NamedArtifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getNamedArtifact_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedArtifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(namedPositionEClass, NamedPosition.class, "NamedPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(routingContainerEClass, RoutingContainer.class, "RoutingContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRoutingContainer_Routes(), this.getRoute(), null, "routes", null, 0, -1, RoutingContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getRoutingContainer_Area(), theOsmPackage.getArea(), null, "area", null, 0, 1, RoutingContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(poiContainerEClass, POIContainer.class, "POIContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPOIContainer_Pois(), this.getNamedPosition(), null, "pois", null, 0, -1, POIContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPOIContainer_Area(), theOsmPackage.getArea(), null, "area", null, 0, 1, POIContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(chartContainerEClass, ChartContainer.class, "ChartContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getChartContainer_Charts(), this.getChart(), null, "charts", null, 0, -1, ChartContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(navigationCompoundEClass, NavigationCompound.class, "NavigationCompound", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getNavigationCompound_PoiContainer(), this.getPOIContainer(), null, "poiContainer", null, 0, 1, NavigationCompound.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getNavigationCompound_RoutingContainer(), this.getRoutingContainer(), null, "routingContainer", null, 0, 1, NavigationCompound.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getNavigationCompound_TracksContainer(), this.getTracksContainer(), null, "tracksContainer", null, 0, 1, NavigationCompound.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(chartEClass, Chart.class, "Chart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getChart_ChartConfiguration(), theDiagramInterchangePackage.getDiagram(), null, "chartConfiguration", null, 0, 1, Chart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(geoPosition3DEClass, GeoPosition3D.class, "GeoPosition3D", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getGeoPosition3D_Altitude(), ecorePackage.getEDouble(), "altitude", null, 0, 1, GeoPosition3D.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(measuredPosition3DEClass, MeasuredPosition3D.class, "MeasuredPosition3D", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(tracksContainerEClass, TracksContainer.class, "TracksContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTracksContainer_Tracks(), this.getTrack(), null, "tracks", null, 0, -1, TracksContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(trackEClass, Track.class, "Track", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTrack_MeasuredPosition(), this.getMeasuredPosition3D(), null, "measuredPosition", null, 0, -1, Track.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(chartSymbolEClass, ChartSymbol.class, "ChartSymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(chartAreaEClass, ChartArea.class, "ChartArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getChartArea_Bounds(), this.getGeoPosition(), null, "bounds", null, 0, -1, ChartArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(chartWayEClass, ChartWay.class, "ChartWay", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getChartWay_Waypoints(), this.getGeoPosition(), null, "waypoints", null, 0, -1, ChartWay.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(navareaEClass, Navarea.class, "Navarea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getNavarea_Areanumber(), ecorePackage.getEInt(), "areanumber", null, 0, 1, Navarea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(depthEClass, Depth.class, "Depth", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDepth_MeasurementPosition(), this.getRelativeDepthMeasurementPosition(), "measurementPosition", null, 0, 1, Depth.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDepth_Depth(), ecorePackage.getEDouble(), "depth", null, 0, 1, Depth.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(gnssMeasuredPositionEClass, GNSSMeasuredPosition.class, "GNSSMeasuredPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getGNSSMeasuredPosition_Hdop(), ecorePackage.getEDouble(), "hdop", null, 0, 1, GNSSMeasuredPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGNSSMeasuredPosition_Vdop(), ecorePackage.getEDouble(), "vdop", null, 0, 1, GNSSMeasuredPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGNSSMeasuredPosition_Pdop(), ecorePackage.getEDouble(), "pdop", null, 0, 1, GNSSMeasuredPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGNSSMeasuredPosition_Augmentation(), ecorePackage.getEString(), "augmentation", null, 0, -1, GNSSMeasuredPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(anchorPositionEClass, AnchorPosition.class, "AnchorPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAnchorPosition_XExtent(), ecorePackage.getEDouble(), "xExtent", null, 0, 1, AnchorPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAnchorPosition_YExtent(), ecorePackage.getEDouble(), "yExtent", null, 0, 1, AnchorPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(geoBoundingBoxEClass, GeoBoundingBox.class, "GeoBoundingBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getGeoBoundingBox_Top(), ecorePackage.getEDouble(), "top", null, 0, 1, GeoBoundingBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGeoBoundingBox_Bottom(), ecorePackage.getEDouble(), "bottom", null, 0, 1, GeoBoundingBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGeoBoundingBox_Left(), ecorePackage.getEDouble(), "left", null, 0, 1, GeoBoundingBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGeoBoundingBox_Right(), ecorePackage.getEDouble(), "right", null, 0, 1, GeoBoundingBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(directionEEnum, Direction.class, "Direction"); //$NON-NLS-1$
		addEEnumLiteral(directionEEnum, Direction.UNDEFINED);
		addEEnumLiteral(directionEEnum, Direction.N);
		addEEnumLiteral(directionEEnum, Direction.NNE);
		addEEnumLiteral(directionEEnum, Direction.NE);
		addEEnumLiteral(directionEEnum, Direction.ENE);
		addEEnumLiteral(directionEEnum, Direction.E);
		addEEnumLiteral(directionEEnum, Direction.ESE);
		addEEnumLiteral(directionEEnum, Direction.SE);
		addEEnumLiteral(directionEEnum, Direction.SSE);
		addEEnumLiteral(directionEEnum, Direction.S);
		addEEnumLiteral(directionEEnum, Direction.SSW);
		addEEnumLiteral(directionEEnum, Direction.SW);
		addEEnumLiteral(directionEEnum, Direction.WSW);
		addEEnumLiteral(directionEEnum, Direction.W);
		addEEnumLiteral(directionEEnum, Direction.WNW);
		addEEnumLiteral(directionEEnum, Direction.NW);
		addEEnumLiteral(directionEEnum, Direction.NNW);

		initEEnum(latitudeHemisphereEEnum, LatitudeHemisphere.class, "LatitudeHemisphere"); //$NON-NLS-1$
		addEEnumLiteral(latitudeHemisphereEEnum, LatitudeHemisphere.N);
		addEEnumLiteral(latitudeHemisphereEEnum, LatitudeHemisphere.S);

		initEEnum(longitudeHemisphereEEnum, LongitudeHemisphere.class, "LongitudeHemisphere"); //$NON-NLS-1$
		addEEnumLiteral(longitudeHemisphereEEnum, LongitudeHemisphere.W);
		addEEnumLiteral(longitudeHemisphereEEnum, LongitudeHemisphere.E);

		initEEnum(relativeDepthMeasurementPositionEEnum, RelativeDepthMeasurementPosition.class, "RelativeDepthMeasurementPosition"); //$NON-NLS-1$
		addEEnumLiteral(relativeDepthMeasurementPositionEEnum, RelativeDepthMeasurementPosition.UNKNOWN);
		addEEnumLiteral(relativeDepthMeasurementPositionEEnum, RelativeDepthMeasurementPosition.TRANSDUCER);
		addEEnumLiteral(relativeDepthMeasurementPositionEEnum, RelativeDepthMeasurementPosition.SURFACE);
		addEEnumLiteral(relativeDepthMeasurementPositionEEnum, RelativeDepthMeasurementPosition.KEEL);
	}

} //GeoPackageImpl
