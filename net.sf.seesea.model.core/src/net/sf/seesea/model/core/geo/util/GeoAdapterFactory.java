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
package net.sf.seesea.model.core.geo.util;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.geo.*;

import net.sf.seesea.model.core.geo.osm.Area;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.Time;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.geo.GeoPackage
 * @generated
 */
public class GeoAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GeoPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = GeoPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeoSwitch<Adapter> modelSwitch =
		new GeoSwitch<Adapter>() {
			@Override
			public Adapter caseGeoPosition(GeoPosition object) {
				return createGeoPositionAdapter();
			}
			@Override
			public Adapter caseCoordinate(Coordinate object) {
				return createCoordinateAdapter();
			}
			@Override
			public Adapter caseLatitude(Latitude object) {
				return createLatitudeAdapter();
			}
			@Override
			public Adapter caseLongitude(Longitude object) {
				return createLongitudeAdapter();
			}
			@Override
			public Adapter caseRoute(Route object) {
				return createRouteAdapter();
			}
			@Override
			public Adapter caseNamedArtifact(NamedArtifact object) {
				return createNamedArtifactAdapter();
			}
			@Override
			public Adapter caseNamedPosition(NamedPosition object) {
				return createNamedPositionAdapter();
			}
			@Override
			public Adapter caseRoutingContainer(RoutingContainer object) {
				return createRoutingContainerAdapter();
			}
			@Override
			public Adapter casePOIContainer(POIContainer object) {
				return createPOIContainerAdapter();
			}
			@Override
			public Adapter caseChartContainer(ChartContainer object) {
				return createChartContainerAdapter();
			}
			@Override
			public Adapter caseNavigationCompound(NavigationCompound object) {
				return createNavigationCompoundAdapter();
			}
			@Override
			public Adapter caseChart(Chart object) {
				return createChartAdapter();
			}
			@Override
			public Adapter caseGeoPosition3D(GeoPosition3D object) {
				return createGeoPosition3DAdapter();
			}
			@Override
			public Adapter caseMeasuredPosition3D(MeasuredPosition3D object) {
				return createMeasuredPosition3DAdapter();
			}
			@Override
			public Adapter caseTracksContainer(TracksContainer object) {
				return createTracksContainerAdapter();
			}
			@Override
			public Adapter caseTrack(Track object) {
				return createTrackAdapter();
			}
			@Override
			public Adapter caseChartSymbol(ChartSymbol object) {
				return createChartSymbolAdapter();
			}
			@Override
			public Adapter caseChartArea(ChartArea object) {
				return createChartAreaAdapter();
			}
			@Override
			public Adapter caseChartWay(ChartWay object) {
				return createChartWayAdapter();
			}
			@Override
			public Adapter caseNavarea(Navarea object) {
				return createNavareaAdapter();
			}
			@Override
			public Adapter caseDepth(Depth object) {
				return createDepthAdapter();
			}
			@Override
			public Adapter caseGNSSMeasuredPosition(GNSSMeasuredPosition object) {
				return createGNSSMeasuredPositionAdapter();
			}
			@Override
			public Adapter caseModelObject(ModelObject object) {
				return createModelObjectAdapter();
			}
			@Override
			public Adapter caseMeasurement(Measurement object) {
				return createMeasurementAdapter();
			}
			@Override
			public Adapter caseArea(Area object) {
				return createAreaAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.GeoPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.GeoPosition
	 * @generated
	 */
	public Adapter createGeoPositionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.Coordinate <em>Coordinate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.Coordinate
	 * @generated
	 */
	public Adapter createCoordinateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.Latitude <em>Latitude</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.Latitude
	 * @generated
	 */
	public Adapter createLatitudeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.Longitude <em>Longitude</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.Longitude
	 * @generated
	 */
	public Adapter createLongitudeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.osm.Area <em>Area</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.osm.Area
	 * @generated
	 */
	public Adapter createAreaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.Route <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.Route
	 * @generated
	 */
	public Adapter createRouteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.NamedArtifact <em>Named Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.NamedArtifact
	 * @generated
	 */
	public Adapter createNamedArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.NamedPosition <em>Named Position</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.NamedPosition
	 * @generated
	 */
	public Adapter createNamedPositionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.RoutingContainer <em>Routing Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.RoutingContainer
	 * @generated
	 */
	public Adapter createRoutingContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.POIContainer <em>POI Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.POIContainer
	 * @generated
	 */
	public Adapter createPOIContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.Navarea <em>Navarea</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.Navarea
	 * @generated
	 */
	public Adapter createNavareaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.Depth <em>Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.Depth
	 * @generated
	 */
	public Adapter createDepthAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.GNSSMeasuredPosition <em>GNSS Measured Position</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.GNSSMeasuredPosition
	 * @generated
	 */
	public Adapter createGNSSMeasuredPositionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.ModelObject <em>Model Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.ModelObject
	 * @generated
	 */
	public Adapter createModelObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.ChartContainer <em>Chart Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.ChartContainer
	 * @generated
	 */
	public Adapter createChartContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.NavigationCompound <em>Navigation Compound</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.NavigationCompound
	 * @generated
	 */
	public Adapter createNavigationCompoundAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.Chart <em>Chart</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.Chart
	 * @generated
	 */
	public Adapter createChartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.GeoPosition3D <em>Position3 D</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.GeoPosition3D
	 * @generated
	 */
	public Adapter createGeoPosition3DAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.MeasuredPosition3D <em>Measured Position3 D</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.MeasuredPosition3D
	 * @generated
	 */
	public Adapter createMeasuredPosition3DAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.TracksContainer <em>Tracks Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.TracksContainer
	 * @generated
	 */
	public Adapter createTracksContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.Track <em>Track</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.Track
	 * @generated
	 */
	public Adapter createTrackAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.ChartSymbol <em>Chart Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.ChartSymbol
	 * @generated
	 */
	public Adapter createChartSymbolAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.ChartArea <em>Chart Area</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.ChartArea
	 * @generated
	 */
	public Adapter createChartAreaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.ChartWay <em>Chart Way</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.ChartWay
	 * @generated
	 */
	public Adapter createChartWayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.physx.Measurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.physx.Measurement
	 * @generated
	 */
	public Adapter createMeasurementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //GeoAdapterFactory
