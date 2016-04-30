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

import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.NavigationCompound;
import net.sf.seesea.model.core.geo.POIContainer;
import net.sf.seesea.model.core.geo.RoutingContainer;

import net.sf.seesea.model.core.geo.TracksContainer;
import net.sf.seesea.model.core.impl.ModelObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Compound</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.NavigationCompoundImpl#getPoiContainer <em>Poi Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.NavigationCompoundImpl#getRoutingContainer <em>Routing Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.NavigationCompoundImpl#getTracksContainer <em>Tracks Container</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NavigationCompoundImpl extends ModelObjectImpl implements NavigationCompound {
	/**
	 * The cached value of the '{@link #getPoiContainer() <em>Poi Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoiContainer()
	 * @generated
	 * @ordered
	 */
	protected POIContainer poiContainer;

	/**
	 * The cached value of the '{@link #getRoutingContainer() <em>Routing Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoutingContainer()
	 * @generated
	 * @ordered
	 */
	protected RoutingContainer routingContainer;

	/**
	 * The cached value of the '{@link #getTracksContainer() <em>Tracks Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracksContainer()
	 * @generated
	 * @ordered
	 */
	protected TracksContainer tracksContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationCompoundImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeoPackage.Literals.NAVIGATION_COMPOUND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public POIContainer getPoiContainer() {
		return poiContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPoiContainer(POIContainer newPoiContainer, NotificationChain msgs) {
		POIContainer oldPoiContainer = poiContainer;
		poiContainer = newPoiContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER, oldPoiContainer, newPoiContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPoiContainer(POIContainer newPoiContainer) {
		if (newPoiContainer != poiContainer) {
			NotificationChain msgs = null;
			if (poiContainer != null)
				msgs = ((InternalEObject)poiContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER, null, msgs);
			if (newPoiContainer != null)
				msgs = ((InternalEObject)newPoiContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER, null, msgs);
			msgs = basicSetPoiContainer(newPoiContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER, newPoiContainer, newPoiContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoutingContainer getRoutingContainer() {
		return routingContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoutingContainer(RoutingContainer newRoutingContainer, NotificationChain msgs) {
		RoutingContainer oldRoutingContainer = routingContainer;
		routingContainer = newRoutingContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER, oldRoutingContainer, newRoutingContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoutingContainer(RoutingContainer newRoutingContainer) {
		if (newRoutingContainer != routingContainer) {
			NotificationChain msgs = null;
			if (routingContainer != null)
				msgs = ((InternalEObject)routingContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER, null, msgs);
			if (newRoutingContainer != null)
				msgs = ((InternalEObject)newRoutingContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER, null, msgs);
			msgs = basicSetRoutingContainer(newRoutingContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER, newRoutingContainer, newRoutingContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracksContainer getTracksContainer() {
		return tracksContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTracksContainer(TracksContainer newTracksContainer, NotificationChain msgs) {
		TracksContainer oldTracksContainer = tracksContainer;
		tracksContainer = newTracksContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER, oldTracksContainer, newTracksContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTracksContainer(TracksContainer newTracksContainer) {
		if (newTracksContainer != tracksContainer) {
			NotificationChain msgs = null;
			if (tracksContainer != null)
				msgs = ((InternalEObject)tracksContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER, null, msgs);
			if (newTracksContainer != null)
				msgs = ((InternalEObject)newTracksContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER, null, msgs);
			msgs = basicSetTracksContainer(newTracksContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER, newTracksContainer, newTracksContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER:
				return basicSetPoiContainer(null, msgs);
			case GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER:
				return basicSetRoutingContainer(null, msgs);
			case GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER:
				return basicSetTracksContainer(null, msgs);
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
			case GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER:
				return getPoiContainer();
			case GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER:
				return getRoutingContainer();
			case GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER:
				return getTracksContainer();
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
			case GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER:
				setPoiContainer((POIContainer)newValue);
				return;
			case GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER:
				setRoutingContainer((RoutingContainer)newValue);
				return;
			case GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER:
				setTracksContainer((TracksContainer)newValue);
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
			case GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER:
				setPoiContainer((POIContainer)null);
				return;
			case GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER:
				setRoutingContainer((RoutingContainer)null);
				return;
			case GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER:
				setTracksContainer((TracksContainer)null);
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
			case GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER:
				return poiContainer != null;
			case GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER:
				return routingContainer != null;
			case GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER:
				return tracksContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //NavigationCompoundImpl
