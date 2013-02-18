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
package net.sf.seesea.model.core.geo.impl;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.GeoPosition3D;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;

import net.sf.seesea.model.core.physx.impl.MeasurementImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measured Position3 D</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.MeasuredPosition3DImpl#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.MeasuredPosition3DImpl#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.MeasuredPosition3DImpl#getPrecision <em>Precision</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.MeasuredPosition3DImpl#getAltitude <em>Altitude</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasuredPosition3DImpl extends MeasurementImpl implements MeasuredPosition3D {
	/**
	 * The cached value of the '{@link #getLongitude() <em>Longitude</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongitude()
	 * @generated
	 * @ordered
	 */
	protected Longitude longitude;

	/**
	 * The cached value of the '{@link #getLatitude() <em>Latitude</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatitude()
	 * @generated
	 * @ordered
	 */
	protected Latitude latitude;

	/**
	 * The default value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected int precision = PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAltitude() <em>Altitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAltitude()
	 * @generated
	 * @ordered
	 */
	protected static final double ALTITUDE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAltitude() <em>Altitude</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAltitude()
	 * @generated
	 * @ordered
	 */
	protected double altitude = ALTITUDE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasuredPosition3DImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeoPackage.Literals.MEASURED_POSITION3_D;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Longitude getLongitude() {
		return longitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLongitude(Longitude newLongitude, NotificationChain msgs) {
		Longitude oldLongitude = longitude;
		longitude = newLongitude;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GeoPackage.MEASURED_POSITION3_D__LONGITUDE, oldLongitude, newLongitude);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongitude(Longitude newLongitude) {
		if (newLongitude != longitude) {
			NotificationChain msgs = null;
			if (longitude != null)
				msgs = ((InternalEObject)longitude).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GeoPackage.MEASURED_POSITION3_D__LONGITUDE, null, msgs);
			if (newLongitude != null)
				msgs = ((InternalEObject)newLongitude).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GeoPackage.MEASURED_POSITION3_D__LONGITUDE, null, msgs);
			msgs = basicSetLongitude(newLongitude, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.MEASURED_POSITION3_D__LONGITUDE, newLongitude, newLongitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Latitude getLatitude() {
		return latitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLatitude(Latitude newLatitude, NotificationChain msgs) {
		Latitude oldLatitude = latitude;
		latitude = newLatitude;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GeoPackage.MEASURED_POSITION3_D__LATITUDE, oldLatitude, newLatitude);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLatitude(Latitude newLatitude) {
		if (newLatitude != latitude) {
			NotificationChain msgs = null;
			if (latitude != null)
				msgs = ((InternalEObject)latitude).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GeoPackage.MEASURED_POSITION3_D__LATITUDE, null, msgs);
			if (newLatitude != null)
				msgs = ((InternalEObject)newLatitude).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GeoPackage.MEASURED_POSITION3_D__LATITUDE, null, msgs);
			msgs = basicSetLatitude(newLatitude, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.MEASURED_POSITION3_D__LATITUDE, newLatitude, newLatitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecision(int newPrecision) {
		int oldPrecision = precision;
		precision = newPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.MEASURED_POSITION3_D__PRECISION, oldPrecision, precision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getAltitude() {
		return altitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAltitude(double newAltitude) {
		double oldAltitude = altitude;
		altitude = newAltitude;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.MEASURED_POSITION3_D__ALTITUDE, oldAltitude, altitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GeoPackage.MEASURED_POSITION3_D__LONGITUDE:
				return basicSetLongitude(null, msgs);
			case GeoPackage.MEASURED_POSITION3_D__LATITUDE:
				return basicSetLatitude(null, msgs);
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
			case GeoPackage.MEASURED_POSITION3_D__LONGITUDE:
				return getLongitude();
			case GeoPackage.MEASURED_POSITION3_D__LATITUDE:
				return getLatitude();
			case GeoPackage.MEASURED_POSITION3_D__PRECISION:
				return getPrecision();
			case GeoPackage.MEASURED_POSITION3_D__ALTITUDE:
				return getAltitude();
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
			case GeoPackage.MEASURED_POSITION3_D__LONGITUDE:
				setLongitude((Longitude)newValue);
				return;
			case GeoPackage.MEASURED_POSITION3_D__LATITUDE:
				setLatitude((Latitude)newValue);
				return;
			case GeoPackage.MEASURED_POSITION3_D__PRECISION:
				setPrecision((Integer)newValue);
				return;
			case GeoPackage.MEASURED_POSITION3_D__ALTITUDE:
				setAltitude((Double)newValue);
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
			case GeoPackage.MEASURED_POSITION3_D__LONGITUDE:
				setLongitude((Longitude)null);
				return;
			case GeoPackage.MEASURED_POSITION3_D__LATITUDE:
				setLatitude((Latitude)null);
				return;
			case GeoPackage.MEASURED_POSITION3_D__PRECISION:
				setPrecision(PRECISION_EDEFAULT);
				return;
			case GeoPackage.MEASURED_POSITION3_D__ALTITUDE:
				setAltitude(ALTITUDE_EDEFAULT);
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
			case GeoPackage.MEASURED_POSITION3_D__LONGITUDE:
				return longitude != null;
			case GeoPackage.MEASURED_POSITION3_D__LATITUDE:
				return latitude != null;
			case GeoPackage.MEASURED_POSITION3_D__PRECISION:
				return precision != PRECISION_EDEFAULT;
			case GeoPackage.MEASURED_POSITION3_D__ALTITUDE:
				return altitude != ALTITUDE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == GeoPosition.class) {
			switch (derivedFeatureID) {
				case GeoPackage.MEASURED_POSITION3_D__LONGITUDE: return GeoPackage.GEO_POSITION__LONGITUDE;
				case GeoPackage.MEASURED_POSITION3_D__LATITUDE: return GeoPackage.GEO_POSITION__LATITUDE;
				case GeoPackage.MEASURED_POSITION3_D__PRECISION: return GeoPackage.GEO_POSITION__PRECISION;
				default: return -1;
			}
		}
		if (baseClass == GeoPosition3D.class) {
			switch (derivedFeatureID) {
				case GeoPackage.MEASURED_POSITION3_D__ALTITUDE: return GeoPackage.GEO_POSITION3_D__ALTITUDE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == GeoPosition.class) {
			switch (baseFeatureID) {
				case GeoPackage.GEO_POSITION__LONGITUDE: return GeoPackage.MEASURED_POSITION3_D__LONGITUDE;
				case GeoPackage.GEO_POSITION__LATITUDE: return GeoPackage.MEASURED_POSITION3_D__LATITUDE;
				case GeoPackage.GEO_POSITION__PRECISION: return GeoPackage.MEASURED_POSITION3_D__PRECISION;
				default: return -1;
			}
		}
		if (baseClass == GeoPosition3D.class) {
			switch (baseFeatureID) {
				case GeoPackage.GEO_POSITION3_D__ALTITUDE: return GeoPackage.MEASURED_POSITION3_D__ALTITUDE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (precision: ");
		result.append(precision);
		result.append(", altitude: ");
		result.append(altitude);
		result.append(')');
		return result.toString();
	}

} //MeasuredPosition3DImpl
