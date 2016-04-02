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

import java.util.List;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.geo.*;

import net.sf.seesea.model.core.geo.osm.Area;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.Time;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.geo.GeoPackage
 * @generated
 */
public class GeoSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GeoPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoSwitch() {
		if (modelPackage == null) {
			modelPackage = GeoPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case GeoPackage.GEO_POSITION: {
				GeoPosition geoPosition = (GeoPosition)theEObject;
				T result = caseGeoPosition(geoPosition);
				if (result == null) result = caseModelObject(geoPosition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.COORDINATE: {
				Coordinate coordinate = (Coordinate)theEObject;
				T result = caseCoordinate(coordinate);
				if (result == null) result = caseModelObject(coordinate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.LATITUDE: {
				Latitude latitude = (Latitude)theEObject;
				T result = caseLatitude(latitude);
				if (result == null) result = caseCoordinate(latitude);
				if (result == null) result = caseModelObject(latitude);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.LONGITUDE: {
				Longitude longitude = (Longitude)theEObject;
				T result = caseLongitude(longitude);
				if (result == null) result = caseCoordinate(longitude);
				if (result == null) result = caseModelObject(longitude);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.ROUTE: {
				Route route = (Route)theEObject;
				T result = caseRoute(route);
				if (result == null) result = caseNamedArtifact(route);
				if (result == null) result = caseModelObject(route);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.NAMED_ARTIFACT: {
				NamedArtifact namedArtifact = (NamedArtifact)theEObject;
				T result = caseNamedArtifact(namedArtifact);
				if (result == null) result = caseModelObject(namedArtifact);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.NAMED_POSITION: {
				NamedPosition namedPosition = (NamedPosition)theEObject;
				T result = caseNamedPosition(namedPosition);
				if (result == null) result = caseGeoPosition(namedPosition);
				if (result == null) result = caseNamedArtifact(namedPosition);
				if (result == null) result = caseModelObject(namedPosition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.ROUTING_CONTAINER: {
				RoutingContainer routingContainer = (RoutingContainer)theEObject;
				T result = caseRoutingContainer(routingContainer);
				if (result == null) result = caseModelObject(routingContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.POI_CONTAINER: {
				POIContainer poiContainer = (POIContainer)theEObject;
				T result = casePOIContainer(poiContainer);
				if (result == null) result = caseModelObject(poiContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.CHART_CONTAINER: {
				ChartContainer chartContainer = (ChartContainer)theEObject;
				T result = caseChartContainer(chartContainer);
				if (result == null) result = caseModelObject(chartContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.NAVIGATION_COMPOUND: {
				NavigationCompound navigationCompound = (NavigationCompound)theEObject;
				T result = caseNavigationCompound(navigationCompound);
				if (result == null) result = caseModelObject(navigationCompound);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.CHART: {
				Chart chart = (Chart)theEObject;
				T result = caseChart(chart);
				if (result == null) result = caseNamedArtifact(chart);
				if (result == null) result = caseModelObject(chart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.GEO_POSITION3_D: {
				GeoPosition3D geoPosition3D = (GeoPosition3D)theEObject;
				T result = caseGeoPosition3D(geoPosition3D);
				if (result == null) result = caseGeoPosition(geoPosition3D);
				if (result == null) result = caseModelObject(geoPosition3D);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.MEASURED_POSITION3_D: {
				MeasuredPosition3D measuredPosition3D = (MeasuredPosition3D)theEObject;
				T result = caseMeasuredPosition3D(measuredPosition3D);
				if (result == null) result = caseMeasurement(measuredPosition3D);
				if (result == null) result = caseGeoPosition3D(measuredPosition3D);
				if (result == null) result = caseGeoPosition(measuredPosition3D);
				if (result == null) result = caseModelObject(measuredPosition3D);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.TRACKS_CONTAINER: {
				TracksContainer tracksContainer = (TracksContainer)theEObject;
				T result = caseTracksContainer(tracksContainer);
				if (result == null) result = caseModelObject(tracksContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.TRACK: {
				Track track = (Track)theEObject;
				T result = caseTrack(track);
				if (result == null) result = caseModelObject(track);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.CHART_SYMBOL: {
				ChartSymbol chartSymbol = (ChartSymbol)theEObject;
				T result = caseChartSymbol(chartSymbol);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.CHART_AREA: {
				ChartArea chartArea = (ChartArea)theEObject;
				T result = caseChartArea(chartArea);
				if (result == null) result = caseModelObject(chartArea);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.CHART_WAY: {
				ChartWay chartWay = (ChartWay)theEObject;
				T result = caseChartWay(chartWay);
				if (result == null) result = caseModelObject(chartWay);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.NAVAREA: {
				Navarea navarea = (Navarea)theEObject;
				T result = caseNavarea(navarea);
				if (result == null) result = caseArea(navarea);
				if (result == null) result = caseChart(navarea);
				if (result == null) result = caseNavigationCompound(navarea);
				if (result == null) result = caseNamedArtifact(navarea);
				if (result == null) result = caseModelObject(navarea);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.DEPTH: {
				Depth depth = (Depth)theEObject;
				T result = caseDepth(depth);
				if (result == null) result = caseMeasurement(depth);
				if (result == null) result = caseModelObject(depth);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.GNSS_MEASURED_POSITION: {
				GNSSMeasuredPosition gnssMeasuredPosition = (GNSSMeasuredPosition)theEObject;
				T result = caseGNSSMeasuredPosition(gnssMeasuredPosition);
				if (result == null) result = caseMeasuredPosition3D(gnssMeasuredPosition);
				if (result == null) result = caseMeasurement(gnssMeasuredPosition);
				if (result == null) result = caseGeoPosition3D(gnssMeasuredPosition);
				if (result == null) result = caseGeoPosition(gnssMeasuredPosition);
				if (result == null) result = caseModelObject(gnssMeasuredPosition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.ANCHOR_POSITION: {
				AnchorPosition anchorPosition = (AnchorPosition)theEObject;
				T result = caseAnchorPosition(anchorPosition);
				if (result == null) result = caseGeoPosition(anchorPosition);
				if (result == null) result = caseModelObject(anchorPosition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GeoPackage.GEO_BOUNDING_BOX: {
				GeoBoundingBox geoBoundingBox = (GeoBoundingBox)theEObject;
				T result = caseGeoBoundingBox(geoBoundingBox);
				if (result == null) result = caseModelObject(geoBoundingBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeoPosition(GeoPosition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Coordinate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Coordinate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCoordinate(Coordinate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Latitude</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Latitude</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLatitude(Latitude object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Longitude</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Longitude</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLongitude(Longitude object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Area</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Area</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArea(Area object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Route</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Route</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoute(Route object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Artifact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedArtifact(NamedArtifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedPosition(NamedPosition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Routing Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Routing Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoutingContainer(RoutingContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>POI Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>POI Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePOIContainer(POIContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navarea</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navarea</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavarea(Navarea object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Depth</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Depth</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDepth(Depth object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>GNSS Measured Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>GNSS Measured Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGNSSMeasuredPosition(GNSSMeasuredPosition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Anchor Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anchor Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnchorPosition(AnchorPosition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bounding Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bounding Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeoBoundingBox(GeoBoundingBox object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelObject(ModelObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chart Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chart Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChartContainer(ChartContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation Compound</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation Compound</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationCompound(NavigationCompound object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chart</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chart</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChart(Chart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Position3 D</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Position3 D</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeoPosition3D(GeoPosition3D object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measured Position3 D</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measured Position3 D</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasuredPosition3D(MeasuredPosition3D object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tracks Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tracks Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTracksContainer(TracksContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Track</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Track</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrack(Track object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chart Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chart Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChartSymbol(ChartSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chart Area</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chart Area</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChartArea(ChartArea object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chart Way</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chart Way</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChartWay(ChartWay object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurement(Measurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //GeoSwitch
