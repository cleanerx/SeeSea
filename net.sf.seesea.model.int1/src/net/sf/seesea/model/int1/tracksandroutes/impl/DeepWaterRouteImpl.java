/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.impl;

import net.sf.seesea.model.core.geo.ChartArea;

import net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;
import net.sf.seesea.model.int1.tracksandroutes.TrafficDirection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deep Water Route</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.impl.DeepWaterRouteImpl#getTrafficDirection <em>Traffic Direction</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.impl.DeepWaterRouteImpl#getRouteArea <em>Route Area</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeepWaterRouteImpl extends AbstractSeaWayImpl implements DeepWaterRoute {
	/**
	 * The default value of the '{@link #getTrafficDirection() <em>Traffic Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrafficDirection()
	 * @generated
	 * @ordered
	 */
	protected static final TrafficDirection TRAFFIC_DIRECTION_EDEFAULT = TrafficDirection.UNDEFINED;

	/**
	 * The cached value of the '{@link #getTrafficDirection() <em>Traffic Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrafficDirection()
	 * @generated
	 * @ordered
	 */
	protected TrafficDirection trafficDirection = TRAFFIC_DIRECTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRouteArea() <em>Route Area</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRouteArea()
	 * @generated
	 * @ordered
	 */
	protected ChartArea routeArea;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeepWaterRouteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracksandroutesPackage.Literals.DEEP_WATER_ROUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrafficDirection getTrafficDirection() {
		return trafficDirection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrafficDirection(TrafficDirection newTrafficDirection) {
		TrafficDirection oldTrafficDirection = trafficDirection;
		trafficDirection = newTrafficDirection == null ? TRAFFIC_DIRECTION_EDEFAULT : newTrafficDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracksandroutesPackage.DEEP_WATER_ROUTE__TRAFFIC_DIRECTION, oldTrafficDirection, trafficDirection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChartArea getRouteArea() {
		if (routeArea != null && routeArea.eIsProxy()) {
			InternalEObject oldRouteArea = (InternalEObject)routeArea;
			routeArea = (ChartArea)eResolveProxy(oldRouteArea);
			if (routeArea != oldRouteArea) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracksandroutesPackage.DEEP_WATER_ROUTE__ROUTE_AREA, oldRouteArea, routeArea));
			}
		}
		return routeArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChartArea basicGetRouteArea() {
		return routeArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRouteArea(ChartArea newRouteArea) {
		ChartArea oldRouteArea = routeArea;
		routeArea = newRouteArea;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracksandroutesPackage.DEEP_WATER_ROUTE__ROUTE_AREA, oldRouteArea, routeArea));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracksandroutesPackage.DEEP_WATER_ROUTE__TRAFFIC_DIRECTION:
				return getTrafficDirection();
			case TracksandroutesPackage.DEEP_WATER_ROUTE__ROUTE_AREA:
				if (resolve) return getRouteArea();
				return basicGetRouteArea();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracksandroutesPackage.DEEP_WATER_ROUTE__TRAFFIC_DIRECTION:
				setTrafficDirection((TrafficDirection)newValue);
				return;
			case TracksandroutesPackage.DEEP_WATER_ROUTE__ROUTE_AREA:
				setRouteArea((ChartArea)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TracksandroutesPackage.DEEP_WATER_ROUTE__TRAFFIC_DIRECTION:
				setTrafficDirection(TRAFFIC_DIRECTION_EDEFAULT);
				return;
			case TracksandroutesPackage.DEEP_WATER_ROUTE__ROUTE_AREA:
				setRouteArea((ChartArea)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TracksandroutesPackage.DEEP_WATER_ROUTE__TRAFFIC_DIRECTION:
				return trafficDirection != TRAFFIC_DIRECTION_EDEFAULT;
			case TracksandroutesPackage.DEEP_WATER_ROUTE__ROUTE_AREA:
				return routeArea != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (trafficDirection: ");
		result.append(trafficDirection);
		result.append(')');
		return result.toString();
	}

} //DeepWaterRouteImpl
