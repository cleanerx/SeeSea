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
package net.sf.seesea.model.core.geo.osm.impl;

import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.osm.OsmPackage;
import net.sf.seesea.model.core.geo.osm.World;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>World</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.impl.WorldImpl#isLongitudeScale <em>Longitude Scale</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.impl.WorldImpl#isLatitudeScale <em>Latitude Scale</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.impl.WorldImpl#getAnchorPosition <em>Anchor Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.impl.WorldImpl#getCursorPosition <em>Cursor Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.impl.WorldImpl#getTrip <em>Trip</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.impl.WorldImpl#getTotalTrip <em>Total Trip</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorldImpl extends AreaImpl implements World {
	/**
	 * The default value of the '{@link #isLongitudeScale() <em>Longitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLongitudeScale()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LONGITUDE_SCALE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isLongitudeScale() <em>Longitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLongitudeScale()
	 * @generated
	 * @ordered
	 */
	protected boolean longitudeScale = LONGITUDE_SCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #isLatitudeScale() <em>Latitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLatitudeScale()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LATITUDE_SCALE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isLatitudeScale() <em>Latitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLatitudeScale()
	 * @generated
	 * @ordered
	 */
	protected boolean latitudeScale = LATITUDE_SCALE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAnchorPosition() <em>Anchor Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnchorPosition()
	 * @generated
	 * @ordered
	 */
	protected AnchorPosition anchorPosition;

	/**
	 * The cached value of the '{@link #getCursorPosition() <em>Cursor Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCursorPosition()
	 * @generated
	 * @ordered
	 */
	protected GeoPosition cursorPosition;

	/**
	 * The default value of the '{@link #getTrip() <em>Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrip()
	 * @generated
	 * @ordered
	 */
	protected static final double TRIP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTrip() <em>Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrip()
	 * @generated
	 * @ordered
	 */
	protected double trip = TRIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getTotalTrip() <em>Total Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalTrip()
	 * @generated
	 * @ordered
	 */
	protected static final double TOTAL_TRIP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTotalTrip() <em>Total Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalTrip()
	 * @generated
	 * @ordered
	 */
	protected double totalTrip = TOTAL_TRIP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OsmPackage.Literals.WORLD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLongitudeScale() {
		return longitudeScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongitudeScale(boolean newLongitudeScale) {
		boolean oldLongitudeScale = longitudeScale;
		longitudeScale = newLongitudeScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsmPackage.WORLD__LONGITUDE_SCALE, oldLongitudeScale, longitudeScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLatitudeScale() {
		return latitudeScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLatitudeScale(boolean newLatitudeScale) {
		boolean oldLatitudeScale = latitudeScale;
		latitudeScale = newLatitudeScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsmPackage.WORLD__LATITUDE_SCALE, oldLatitudeScale, latitudeScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnchorPosition getAnchorPosition() {
		return anchorPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnchorPosition(AnchorPosition newAnchorPosition, NotificationChain msgs) {
		AnchorPosition oldAnchorPosition = anchorPosition;
		anchorPosition = newAnchorPosition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OsmPackage.WORLD__ANCHOR_POSITION, oldAnchorPosition, newAnchorPosition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnchorPosition(AnchorPosition newAnchorPosition) {
		if (newAnchorPosition != anchorPosition) {
			NotificationChain msgs = null;
			if (anchorPosition != null)
				msgs = ((InternalEObject)anchorPosition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OsmPackage.WORLD__ANCHOR_POSITION, null, msgs);
			if (newAnchorPosition != null)
				msgs = ((InternalEObject)newAnchorPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OsmPackage.WORLD__ANCHOR_POSITION, null, msgs);
			msgs = basicSetAnchorPosition(newAnchorPosition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsmPackage.WORLD__ANCHOR_POSITION, newAnchorPosition, newAnchorPosition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoPosition getCursorPosition() {
		return cursorPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCursorPosition(GeoPosition newCursorPosition, NotificationChain msgs) {
		GeoPosition oldCursorPosition = cursorPosition;
		cursorPosition = newCursorPosition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OsmPackage.WORLD__CURSOR_POSITION, oldCursorPosition, newCursorPosition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCursorPosition(GeoPosition newCursorPosition) {
		if (newCursorPosition != cursorPosition) {
			NotificationChain msgs = null;
			if (cursorPosition != null)
				msgs = ((InternalEObject)cursorPosition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OsmPackage.WORLD__CURSOR_POSITION, null, msgs);
			if (newCursorPosition != null)
				msgs = ((InternalEObject)newCursorPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OsmPackage.WORLD__CURSOR_POSITION, null, msgs);
			msgs = basicSetCursorPosition(newCursorPosition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsmPackage.WORLD__CURSOR_POSITION, newCursorPosition, newCursorPosition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTrip() {
		return trip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrip(double newTrip) {
		double oldTrip = trip;
		trip = newTrip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsmPackage.WORLD__TRIP, oldTrip, trip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTotalTrip() {
		return totalTrip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalTrip(double newTotalTrip) {
		double oldTotalTrip = totalTrip;
		totalTrip = newTotalTrip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsmPackage.WORLD__TOTAL_TRIP, oldTotalTrip, totalTrip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OsmPackage.WORLD__ANCHOR_POSITION:
				return basicSetAnchorPosition(null, msgs);
			case OsmPackage.WORLD__CURSOR_POSITION:
				return basicSetCursorPosition(null, msgs);
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
			case OsmPackage.WORLD__LONGITUDE_SCALE:
				return isLongitudeScale();
			case OsmPackage.WORLD__LATITUDE_SCALE:
				return isLatitudeScale();
			case OsmPackage.WORLD__ANCHOR_POSITION:
				return getAnchorPosition();
			case OsmPackage.WORLD__CURSOR_POSITION:
				return getCursorPosition();
			case OsmPackage.WORLD__TRIP:
				return getTrip();
			case OsmPackage.WORLD__TOTAL_TRIP:
				return getTotalTrip();
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
			case OsmPackage.WORLD__LONGITUDE_SCALE:
				setLongitudeScale((Boolean)newValue);
				return;
			case OsmPackage.WORLD__LATITUDE_SCALE:
				setLatitudeScale((Boolean)newValue);
				return;
			case OsmPackage.WORLD__ANCHOR_POSITION:
				setAnchorPosition((AnchorPosition)newValue);
				return;
			case OsmPackage.WORLD__CURSOR_POSITION:
				setCursorPosition((GeoPosition)newValue);
				return;
			case OsmPackage.WORLD__TRIP:
				setTrip((Double)newValue);
				return;
			case OsmPackage.WORLD__TOTAL_TRIP:
				setTotalTrip((Double)newValue);
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
			case OsmPackage.WORLD__LONGITUDE_SCALE:
				setLongitudeScale(LONGITUDE_SCALE_EDEFAULT);
				return;
			case OsmPackage.WORLD__LATITUDE_SCALE:
				setLatitudeScale(LATITUDE_SCALE_EDEFAULT);
				return;
			case OsmPackage.WORLD__ANCHOR_POSITION:
				setAnchorPosition((AnchorPosition)null);
				return;
			case OsmPackage.WORLD__CURSOR_POSITION:
				setCursorPosition((GeoPosition)null);
				return;
			case OsmPackage.WORLD__TRIP:
				setTrip(TRIP_EDEFAULT);
				return;
			case OsmPackage.WORLD__TOTAL_TRIP:
				setTotalTrip(TOTAL_TRIP_EDEFAULT);
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
			case OsmPackage.WORLD__LONGITUDE_SCALE:
				return longitudeScale != LONGITUDE_SCALE_EDEFAULT;
			case OsmPackage.WORLD__LATITUDE_SCALE:
				return latitudeScale != LATITUDE_SCALE_EDEFAULT;
			case OsmPackage.WORLD__ANCHOR_POSITION:
				return anchorPosition != null;
			case OsmPackage.WORLD__CURSOR_POSITION:
				return cursorPosition != null;
			case OsmPackage.WORLD__TRIP:
				return trip != TRIP_EDEFAULT;
			case OsmPackage.WORLD__TOTAL_TRIP:
				return totalTrip != TOTAL_TRIP_EDEFAULT;
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
		result.append(" (longitudeScale: "); //$NON-NLS-1$
		result.append(longitudeScale);
		result.append(", latitudeScale: "); //$NON-NLS-1$
		result.append(latitudeScale);
		result.append(", trip: "); //$NON-NLS-1$
		result.append(trip);
		result.append(", totalTrip: "); //$NON-NLS-1$
		result.append(totalTrip);
		result.append(')');
		return result.toString();
	}

} //WorldImpl
