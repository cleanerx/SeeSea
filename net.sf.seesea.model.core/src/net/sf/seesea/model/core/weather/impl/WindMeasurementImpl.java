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
package net.sf.seesea.model.core.weather.impl;

import net.sf.seesea.model.core.physx.PhysxPackage;
import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.model.core.physx.SpeedUnit;

import net.sf.seesea.model.core.physx.impl.MeasurementImpl;
import net.sf.seesea.model.core.weather.Reference;
import net.sf.seesea.model.core.weather.WeatherPackage;
import net.sf.seesea.model.core.weather.WindMeasurement;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wind Measurement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.weather.impl.WindMeasurementImpl#getSpeed <em>Speed</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.weather.impl.WindMeasurementImpl#getSpeedUnit <em>Speed Unit</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.weather.impl.WindMeasurementImpl#getAngle <em>Angle</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.weather.impl.WindMeasurementImpl#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WindMeasurementImpl extends MeasurementImpl implements WindMeasurement {
	/**
	 * The default value of the '{@link #getSpeed() <em>Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeed()
	 * @generated
	 * @ordered
	 */
	protected static final double SPEED_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSpeed() <em>Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeed()
	 * @generated
	 * @ordered
	 */
	protected double speed = SPEED_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpeedUnit() <em>Speed Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeedUnit()
	 * @generated
	 * @ordered
	 */
	protected static final SpeedUnit SPEED_UNIT_EDEFAULT = SpeedUnit.K;

	/**
	 * The cached value of the '{@link #getSpeedUnit() <em>Speed Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeedUnit()
	 * @generated
	 * @ordered
	 */
	protected SpeedUnit speedUnit = SPEED_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getAngle() <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAngle()
	 * @generated
	 * @ordered
	 */
	protected static final double ANGLE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAngle() <em>Angle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAngle()
	 * @generated
	 * @ordered
	 */
	protected double angle = ANGLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getReference() <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected static final Reference REFERENCE_EDEFAULT = Reference.RELATIVE;

	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected Reference reference = REFERENCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WindMeasurementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.WIND_MEASUREMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAngle(double newAngle) {
		double oldAngle = angle;
		angle = newAngle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WIND_MEASUREMENT__ANGLE, oldAngle, angle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference getReference() {
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReference(Reference newReference) {
		Reference oldReference = reference;
		reference = newReference == null ? REFERENCE_EDEFAULT : newReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WIND_MEASUREMENT__REFERENCE, oldReference, reference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpeed(double newSpeed) {
		double oldSpeed = speed;
		speed = newSpeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WIND_MEASUREMENT__SPEED, oldSpeed, speed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpeedUnit getSpeedUnit() {
		return speedUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpeedUnit(SpeedUnit newSpeedUnit) {
		SpeedUnit oldSpeedUnit = speedUnit;
		speedUnit = newSpeedUnit == null ? SPEED_UNIT_EDEFAULT : newSpeedUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.WIND_MEASUREMENT__SPEED_UNIT, oldSpeedUnit, speedUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.WIND_MEASUREMENT__SPEED:
				return getSpeed();
			case WeatherPackage.WIND_MEASUREMENT__SPEED_UNIT:
				return getSpeedUnit();
			case WeatherPackage.WIND_MEASUREMENT__ANGLE:
				return getAngle();
			case WeatherPackage.WIND_MEASUREMENT__REFERENCE:
				return getReference();
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
			case WeatherPackage.WIND_MEASUREMENT__SPEED:
				setSpeed((Double)newValue);
				return;
			case WeatherPackage.WIND_MEASUREMENT__SPEED_UNIT:
				setSpeedUnit((SpeedUnit)newValue);
				return;
			case WeatherPackage.WIND_MEASUREMENT__ANGLE:
				setAngle((Double)newValue);
				return;
			case WeatherPackage.WIND_MEASUREMENT__REFERENCE:
				setReference((Reference)newValue);
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
			case WeatherPackage.WIND_MEASUREMENT__SPEED:
				setSpeed(SPEED_EDEFAULT);
				return;
			case WeatherPackage.WIND_MEASUREMENT__SPEED_UNIT:
				setSpeedUnit(SPEED_UNIT_EDEFAULT);
				return;
			case WeatherPackage.WIND_MEASUREMENT__ANGLE:
				setAngle(ANGLE_EDEFAULT);
				return;
			case WeatherPackage.WIND_MEASUREMENT__REFERENCE:
				setReference(REFERENCE_EDEFAULT);
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
			case WeatherPackage.WIND_MEASUREMENT__SPEED:
				return speed != SPEED_EDEFAULT;
			case WeatherPackage.WIND_MEASUREMENT__SPEED_UNIT:
				return speedUnit != SPEED_UNIT_EDEFAULT;
			case WeatherPackage.WIND_MEASUREMENT__ANGLE:
				return angle != ANGLE_EDEFAULT;
			case WeatherPackage.WIND_MEASUREMENT__REFERENCE:
				return reference != REFERENCE_EDEFAULT;
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
		if (baseClass == Speed.class) {
			switch (derivedFeatureID) {
				case WeatherPackage.WIND_MEASUREMENT__SPEED: return PhysxPackage.SPEED__SPEED;
				case WeatherPackage.WIND_MEASUREMENT__SPEED_UNIT: return PhysxPackage.SPEED__SPEED_UNIT;
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
		if (baseClass == Speed.class) {
			switch (baseFeatureID) {
				case PhysxPackage.SPEED__SPEED: return WeatherPackage.WIND_MEASUREMENT__SPEED;
				case PhysxPackage.SPEED__SPEED_UNIT: return WeatherPackage.WIND_MEASUREMENT__SPEED_UNIT;
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
		result.append(" (speed: ");
		result.append(speed);
		result.append(", speedUnit: ");
		result.append(speedUnit);
		result.append(", angle: ");
		result.append(angle);
		result.append(", reference: ");
		result.append(reference);
		result.append(')');
		return result.toString();
	}

} //WindMeasurementImpl
