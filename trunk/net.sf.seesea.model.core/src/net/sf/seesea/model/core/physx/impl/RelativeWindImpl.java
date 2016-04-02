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

import net.sf.seesea.model.core.physx.HandOrientation;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.PhysxPackage;
import net.sf.seesea.model.core.physx.RelativeWind;

import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.model.core.physx.SpeedUnit;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relative Wind</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.RelativeWindImpl#getSpeed <em>Speed</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.RelativeWindImpl#getSpeedUnit <em>Speed Unit</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.RelativeWindImpl#getBowOrientation <em>Bow Orientation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelativeWindImpl extends HeadingImpl implements RelativeWind {
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
	 * The default value of the '{@link #getBowOrientation() <em>Bow Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBowOrientation()
	 * @generated
	 * @ordered
	 */
	protected static final HandOrientation BOW_ORIENTATION_EDEFAULT = HandOrientation.UNKNOWN;

	/**
	 * The cached value of the '{@link #getBowOrientation() <em>Bow Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBowOrientation()
	 * @generated
	 * @ordered
	 */
	protected HandOrientation bowOrientation = BOW_ORIENTATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelativeWindImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhysxPackage.Literals.RELATIVE_WIND;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.RELATIVE_WIND__SPEED, oldSpeed, speed));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.RELATIVE_WIND__SPEED_UNIT, oldSpeedUnit, speedUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HandOrientation getBowOrientation() {
		return bowOrientation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBowOrientation(HandOrientation newBowOrientation) {
		HandOrientation oldBowOrientation = bowOrientation;
		bowOrientation = newBowOrientation == null ? BOW_ORIENTATION_EDEFAULT : newBowOrientation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.RELATIVE_WIND__BOW_ORIENTATION, oldBowOrientation, bowOrientation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PhysxPackage.RELATIVE_WIND__SPEED:
				return getSpeed();
			case PhysxPackage.RELATIVE_WIND__SPEED_UNIT:
				return getSpeedUnit();
			case PhysxPackage.RELATIVE_WIND__BOW_ORIENTATION:
				return getBowOrientation();
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
			case PhysxPackage.RELATIVE_WIND__SPEED:
				setSpeed((Double)newValue);
				return;
			case PhysxPackage.RELATIVE_WIND__SPEED_UNIT:
				setSpeedUnit((SpeedUnit)newValue);
				return;
			case PhysxPackage.RELATIVE_WIND__BOW_ORIENTATION:
				setBowOrientation((HandOrientation)newValue);
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
			case PhysxPackage.RELATIVE_WIND__SPEED:
				setSpeed(SPEED_EDEFAULT);
				return;
			case PhysxPackage.RELATIVE_WIND__SPEED_UNIT:
				setSpeedUnit(SPEED_UNIT_EDEFAULT);
				return;
			case PhysxPackage.RELATIVE_WIND__BOW_ORIENTATION:
				setBowOrientation(BOW_ORIENTATION_EDEFAULT);
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
			case PhysxPackage.RELATIVE_WIND__SPEED:
				return speed != SPEED_EDEFAULT;
			case PhysxPackage.RELATIVE_WIND__SPEED_UNIT:
				return speedUnit != SPEED_UNIT_EDEFAULT;
			case PhysxPackage.RELATIVE_WIND__BOW_ORIENTATION:
				return bowOrientation != BOW_ORIENTATION_EDEFAULT;
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
				case PhysxPackage.RELATIVE_WIND__SPEED: return PhysxPackage.SPEED__SPEED;
				case PhysxPackage.RELATIVE_WIND__SPEED_UNIT: return PhysxPackage.SPEED__SPEED_UNIT;
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
				case PhysxPackage.SPEED__SPEED: return PhysxPackage.RELATIVE_WIND__SPEED;
				case PhysxPackage.SPEED__SPEED_UNIT: return PhysxPackage.RELATIVE_WIND__SPEED_UNIT;
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
		result.append(", bowOrientation: ");
		result.append(bowOrientation);
		result.append(')');
		return result.toString();
	}

} //RelativeWindImpl
