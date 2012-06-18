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

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;

import net.sf.seesea.model.core.physx.impl.MeasurementImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Depth</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.DepthImpl#getMeasurementPosition <em>Measurement Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.DepthImpl#getDepth <em>Depth</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DepthImpl extends MeasurementImpl implements Depth {
	/**
	 * The default value of the '{@link #getMeasurementPosition() <em>Measurement Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasurementPosition()
	 * @generated
	 * @ordered
	 */
	protected static final RelativeDepthMeasurementPosition MEASUREMENT_POSITION_EDEFAULT = RelativeDepthMeasurementPosition.UNKNOWN;

	/**
	 * The cached value of the '{@link #getMeasurementPosition() <em>Measurement Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasurementPosition()
	 * @generated
	 * @ordered
	 */
	protected RelativeDepthMeasurementPosition measurementPosition = MEASUREMENT_POSITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDepth() <em>Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDepth()
	 * @generated
	 * @ordered
	 */
	protected static final double DEPTH_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDepth() <em>Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDepth()
	 * @generated
	 * @ordered
	 */
	protected double depth = DEPTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DepthImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeoPackage.Literals.DEPTH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelativeDepthMeasurementPosition getMeasurementPosition() {
		return measurementPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeasurementPosition(RelativeDepthMeasurementPosition newMeasurementPosition) {
		RelativeDepthMeasurementPosition oldMeasurementPosition = measurementPosition;
		measurementPosition = newMeasurementPosition == null ? MEASUREMENT_POSITION_EDEFAULT : newMeasurementPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.DEPTH__MEASUREMENT_POSITION, oldMeasurementPosition, measurementPosition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDepth() {
		return depth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDepth(double newDepth) {
		double oldDepth = depth;
		depth = newDepth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.DEPTH__DEPTH, oldDepth, depth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeoPackage.DEPTH__MEASUREMENT_POSITION:
				return getMeasurementPosition();
			case GeoPackage.DEPTH__DEPTH:
				return getDepth();
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
			case GeoPackage.DEPTH__MEASUREMENT_POSITION:
				setMeasurementPosition((RelativeDepthMeasurementPosition)newValue);
				return;
			case GeoPackage.DEPTH__DEPTH:
				setDepth((Double)newValue);
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
			case GeoPackage.DEPTH__MEASUREMENT_POSITION:
				setMeasurementPosition(MEASUREMENT_POSITION_EDEFAULT);
				return;
			case GeoPackage.DEPTH__DEPTH:
				setDepth(DEPTH_EDEFAULT);
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
			case GeoPackage.DEPTH__MEASUREMENT_POSITION:
				return measurementPosition != MEASUREMENT_POSITION_EDEFAULT;
			case GeoPackage.DEPTH__DEPTH:
				return depth != DEPTH_EDEFAULT;
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
		result.append(" (measurementPosition: ");
		result.append(measurementPosition);
		result.append(", depth: ");
		result.append(depth);
		result.append(')');
		return result.toString();
	}

} //DepthImpl
