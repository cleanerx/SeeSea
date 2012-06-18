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
package net.sf.seesea.model.core.physx.impl;

import net.sf.seesea.model.core.physx.PhysxPackage;
import net.sf.seesea.model.core.physx.SatelliteInfo;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Satellite Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl#getId <em>Id</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl#getElevation <em>Elevation</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl#getAzimuth <em>Azimuth</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.SatelliteInfoImpl#getSignalStrength <em>Signal Strength</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SatelliteInfoImpl extends EObjectImpl implements SatelliteInfo {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getElevation() <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElevation()
	 * @generated
	 * @ordered
	 */
	protected static final int ELEVATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getElevation() <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElevation()
	 * @generated
	 * @ordered
	 */
	protected int elevation = ELEVATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAzimuth() <em>Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzimuth()
	 * @generated
	 * @ordered
	 */
	protected static final int AZIMUTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAzimuth() <em>Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzimuth()
	 * @generated
	 * @ordered
	 */
	protected int azimuth = AZIMUTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getSignalStrength() <em>Signal Strength</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignalStrength()
	 * @generated
	 * @ordered
	 */
	protected static final int SIGNAL_STRENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSignalStrength() <em>Signal Strength</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignalStrength()
	 * @generated
	 * @ordered
	 */
	protected int signalStrength = SIGNAL_STRENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SatelliteInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhysxPackage.Literals.SATELLITE_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.SATELLITE_INFO__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getElevation() {
		return elevation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElevation(int newElevation) {
		int oldElevation = elevation;
		elevation = newElevation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.SATELLITE_INFO__ELEVATION, oldElevation, elevation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAzimuth() {
		return azimuth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAzimuth(int newAzimuth) {
		int oldAzimuth = azimuth;
		azimuth = newAzimuth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.SATELLITE_INFO__AZIMUTH, oldAzimuth, azimuth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSignalStrength() {
		return signalStrength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignalStrength(int newSignalStrength) {
		int oldSignalStrength = signalStrength;
		signalStrength = newSignalStrength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.SATELLITE_INFO__SIGNAL_STRENGTH, oldSignalStrength, signalStrength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PhysxPackage.SATELLITE_INFO__ID:
				return getId();
			case PhysxPackage.SATELLITE_INFO__ELEVATION:
				return getElevation();
			case PhysxPackage.SATELLITE_INFO__AZIMUTH:
				return getAzimuth();
			case PhysxPackage.SATELLITE_INFO__SIGNAL_STRENGTH:
				return getSignalStrength();
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
			case PhysxPackage.SATELLITE_INFO__ID:
				setId((Integer)newValue);
				return;
			case PhysxPackage.SATELLITE_INFO__ELEVATION:
				setElevation((Integer)newValue);
				return;
			case PhysxPackage.SATELLITE_INFO__AZIMUTH:
				setAzimuth((Integer)newValue);
				return;
			case PhysxPackage.SATELLITE_INFO__SIGNAL_STRENGTH:
				setSignalStrength((Integer)newValue);
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
			case PhysxPackage.SATELLITE_INFO__ID:
				setId(ID_EDEFAULT);
				return;
			case PhysxPackage.SATELLITE_INFO__ELEVATION:
				setElevation(ELEVATION_EDEFAULT);
				return;
			case PhysxPackage.SATELLITE_INFO__AZIMUTH:
				setAzimuth(AZIMUTH_EDEFAULT);
				return;
			case PhysxPackage.SATELLITE_INFO__SIGNAL_STRENGTH:
				setSignalStrength(SIGNAL_STRENGTH_EDEFAULT);
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
			case PhysxPackage.SATELLITE_INFO__ID:
				return id != ID_EDEFAULT;
			case PhysxPackage.SATELLITE_INFO__ELEVATION:
				return elevation != ELEVATION_EDEFAULT;
			case PhysxPackage.SATELLITE_INFO__AZIMUTH:
				return azimuth != AZIMUTH_EDEFAULT;
			case PhysxPackage.SATELLITE_INFO__SIGNAL_STRENGTH:
				return signalStrength != SIGNAL_STRENGTH_EDEFAULT;
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
		result.append(" (id: ");
		result.append(id);
		result.append(", elevation: ");
		result.append(elevation);
		result.append(", azimuth: ");
		result.append(azimuth);
		result.append(", signalStrength: ");
		result.append(signalStrength);
		result.append(')');
		return result.toString();
	}

} //SatelliteInfoImpl
