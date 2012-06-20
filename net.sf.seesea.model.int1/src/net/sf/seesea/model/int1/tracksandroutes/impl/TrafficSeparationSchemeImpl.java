/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.impl;

import java.util.Collection;

import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;
import net.sf.seesea.model.int1.tracksandroutes.TrafficDirection;
import net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation;
import net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Traffic Separation Scheme</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationSchemeImpl#getTrafficSeparations <em>Traffic Separations</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationSchemeImpl#getTrafficDirection <em>Traffic Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TrafficSeparationSchemeImpl extends AbstractSeaWayImpl implements TrafficSeparationScheme {
	/**
	 * The cached value of the '{@link #getTrafficSeparations() <em>Traffic Separations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrafficSeparations()
	 * @generated
	 * @ordered
	 */
	protected EList<TrafficSeparation> trafficSeparations;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrafficSeparationSchemeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracksandroutesPackage.Literals.TRAFFIC_SEPARATION_SCHEME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TrafficSeparation> getTrafficSeparations() {
		if (trafficSeparations == null) {
			trafficSeparations = new EObjectContainmentEList<TrafficSeparation>(TrafficSeparation.class, this, TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS);
		}
		return trafficSeparations;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_DIRECTION, oldTrafficDirection, trafficDirection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS:
				return ((InternalEList<?>)getTrafficSeparations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS:
				return getTrafficSeparations();
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_DIRECTION:
				return getTrafficDirection();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS:
				getTrafficSeparations().clear();
				getTrafficSeparations().addAll((Collection<? extends TrafficSeparation>)newValue);
				return;
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_DIRECTION:
				setTrafficDirection((TrafficDirection)newValue);
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
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS:
				getTrafficSeparations().clear();
				return;
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_DIRECTION:
				setTrafficDirection(TRAFFIC_DIRECTION_EDEFAULT);
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
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_SEPARATIONS:
				return trafficSeparations != null && !trafficSeparations.isEmpty();
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME__TRAFFIC_DIRECTION:
				return trafficDirection != TRAFFIC_DIRECTION_EDEFAULT;
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

} //TrafficSeparationSchemeImpl
