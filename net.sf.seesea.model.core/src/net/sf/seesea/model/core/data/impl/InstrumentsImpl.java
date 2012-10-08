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
package net.sf.seesea.model.core.data.impl;

import net.sf.seesea.model.core.data.DataPackage;
import net.sf.seesea.model.core.data.Instruments;

import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.GeoPosition3D;

import net.sf.seesea.model.core.impl.ModelObjectImpl;
import net.sf.seesea.model.core.physx.SatellitesVisible;
import net.sf.seesea.model.core.physx.Temperature;

import net.sf.seesea.model.core.weather.WindMeasurement;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instruments</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.data.impl.InstrumentsImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.data.impl.InstrumentsImpl#getWaterTemperature <em>Water Temperature</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.data.impl.InstrumentsImpl#getSatellitesVisible <em>Satellites Visible</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.data.impl.InstrumentsImpl#getWindVector <em>Wind Vector</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstrumentsImpl extends ModelObjectImpl implements Instruments {
	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected MeasuredPosition3D position;

	/**
	 * The cached value of the '{@link #getWaterTemperature() <em>Water Temperature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaterTemperature()
	 * @generated
	 * @ordered
	 */
	protected Temperature waterTemperature;

	/**
	 * The cached value of the '{@link #getSatellitesVisible() <em>Satellites Visible</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSatellitesVisible()
	 * @generated
	 * @ordered
	 */
	protected SatellitesVisible satellitesVisible;

	/**
	 * The cached value of the '{@link #getWindVector() <em>Wind Vector</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWindVector()
	 * @generated
	 * @ordered
	 */
	protected WindMeasurement windVector;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstrumentsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DataPackage.Literals.INSTRUMENTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasuredPosition3D getPosition() {
		if (position != null && position.eIsProxy()) {
			InternalEObject oldPosition = (InternalEObject)position;
			position = (MeasuredPosition3D)eResolveProxy(oldPosition);
			if (position != oldPosition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DataPackage.INSTRUMENTS__POSITION, oldPosition, position));
			}
		}
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasuredPosition3D basicGetPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosition(MeasuredPosition3D newPosition) {
		MeasuredPosition3D oldPosition = position;
		position = newPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.INSTRUMENTS__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Temperature getWaterTemperature() {
		return waterTemperature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWaterTemperature(Temperature newWaterTemperature, NotificationChain msgs) {
		Temperature oldWaterTemperature = waterTemperature;
		waterTemperature = newWaterTemperature;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DataPackage.INSTRUMENTS__WATER_TEMPERATURE, oldWaterTemperature, newWaterTemperature);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaterTemperature(Temperature newWaterTemperature) {
		if (newWaterTemperature != waterTemperature) {
			NotificationChain msgs = null;
			if (waterTemperature != null)
				msgs = ((InternalEObject)waterTemperature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DataPackage.INSTRUMENTS__WATER_TEMPERATURE, null, msgs);
			if (newWaterTemperature != null)
				msgs = ((InternalEObject)newWaterTemperature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DataPackage.INSTRUMENTS__WATER_TEMPERATURE, null, msgs);
			msgs = basicSetWaterTemperature(newWaterTemperature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.INSTRUMENTS__WATER_TEMPERATURE, newWaterTemperature, newWaterTemperature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SatellitesVisible getSatellitesVisible() {
		return satellitesVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSatellitesVisible(SatellitesVisible newSatellitesVisible, NotificationChain msgs) {
		SatellitesVisible oldSatellitesVisible = satellitesVisible;
		satellitesVisible = newSatellitesVisible;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DataPackage.INSTRUMENTS__SATELLITES_VISIBLE, oldSatellitesVisible, newSatellitesVisible);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSatellitesVisible(SatellitesVisible newSatellitesVisible) {
		if (newSatellitesVisible != satellitesVisible) {
			NotificationChain msgs = null;
			if (satellitesVisible != null)
				msgs = ((InternalEObject)satellitesVisible).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DataPackage.INSTRUMENTS__SATELLITES_VISIBLE, null, msgs);
			if (newSatellitesVisible != null)
				msgs = ((InternalEObject)newSatellitesVisible).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DataPackage.INSTRUMENTS__SATELLITES_VISIBLE, null, msgs);
			msgs = basicSetSatellitesVisible(newSatellitesVisible, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.INSTRUMENTS__SATELLITES_VISIBLE, newSatellitesVisible, newSatellitesVisible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WindMeasurement getWindVector() {
		return windVector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWindVector(WindMeasurement newWindVector, NotificationChain msgs) {
		WindMeasurement oldWindVector = windVector;
		windVector = newWindVector;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DataPackage.INSTRUMENTS__WIND_VECTOR, oldWindVector, newWindVector);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWindVector(WindMeasurement newWindVector) {
		if (newWindVector != windVector) {
			NotificationChain msgs = null;
			if (windVector != null)
				msgs = ((InternalEObject)windVector).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DataPackage.INSTRUMENTS__WIND_VECTOR, null, msgs);
			if (newWindVector != null)
				msgs = ((InternalEObject)newWindVector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DataPackage.INSTRUMENTS__WIND_VECTOR, null, msgs);
			msgs = basicSetWindVector(newWindVector, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DataPackage.INSTRUMENTS__WIND_VECTOR, newWindVector, newWindVector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DataPackage.INSTRUMENTS__WATER_TEMPERATURE:
				return basicSetWaterTemperature(null, msgs);
			case DataPackage.INSTRUMENTS__SATELLITES_VISIBLE:
				return basicSetSatellitesVisible(null, msgs);
			case DataPackage.INSTRUMENTS__WIND_VECTOR:
				return basicSetWindVector(null, msgs);
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
			case DataPackage.INSTRUMENTS__POSITION:
				if (resolve) return getPosition();
				return basicGetPosition();
			case DataPackage.INSTRUMENTS__WATER_TEMPERATURE:
				return getWaterTemperature();
			case DataPackage.INSTRUMENTS__SATELLITES_VISIBLE:
				return getSatellitesVisible();
			case DataPackage.INSTRUMENTS__WIND_VECTOR:
				return getWindVector();
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
			case DataPackage.INSTRUMENTS__POSITION:
				setPosition((MeasuredPosition3D)newValue);
				return;
			case DataPackage.INSTRUMENTS__WATER_TEMPERATURE:
				setWaterTemperature((Temperature)newValue);
				return;
			case DataPackage.INSTRUMENTS__SATELLITES_VISIBLE:
				setSatellitesVisible((SatellitesVisible)newValue);
				return;
			case DataPackage.INSTRUMENTS__WIND_VECTOR:
				setWindVector((WindMeasurement)newValue);
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
			case DataPackage.INSTRUMENTS__POSITION:
				setPosition((MeasuredPosition3D)null);
				return;
			case DataPackage.INSTRUMENTS__WATER_TEMPERATURE:
				setWaterTemperature((Temperature)null);
				return;
			case DataPackage.INSTRUMENTS__SATELLITES_VISIBLE:
				setSatellitesVisible((SatellitesVisible)null);
				return;
			case DataPackage.INSTRUMENTS__WIND_VECTOR:
				setWindVector((WindMeasurement)null);
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
			case DataPackage.INSTRUMENTS__POSITION:
				return position != null;
			case DataPackage.INSTRUMENTS__WATER_TEMPERATURE:
				return waterTemperature != null;
			case DataPackage.INSTRUMENTS__SATELLITES_VISIBLE:
				return satellitesVisible != null;
			case DataPackage.INSTRUMENTS__WIND_VECTOR:
				return windVector != null;
		}
		return super.eIsSet(featureID);
	}

} //InstrumentsImpl
