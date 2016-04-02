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

import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.PhysxPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Heading</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.HeadingImpl#getDegrees <em>Degrees</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.HeadingImpl#getHeadingType <em>Heading Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HeadingImpl extends MeasurementImpl implements Heading {
	/**
	 * The default value of the '{@link #getDegrees() <em>Degrees</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDegrees()
	 * @generated
	 * @ordered
	 */
	protected static final double DEGREES_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDegrees() <em>Degrees</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDegrees()
	 * @generated
	 * @ordered
	 */
	protected double degrees = DEGREES_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeadingType() <em>Heading Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadingType()
	 * @generated
	 * @ordered
	 */
	protected static final HeadingType HEADING_TYPE_EDEFAULT = HeadingType.UNKNOWN;

	/**
	 * The cached value of the '{@link #getHeadingType() <em>Heading Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadingType()
	 * @generated
	 * @ordered
	 */
	protected HeadingType headingType = HEADING_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HeadingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhysxPackage.Literals.HEADING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDegrees() {
		return degrees;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDegrees(double newDegrees) {
		double oldDegrees = degrees;
		degrees = newDegrees;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.HEADING__DEGREES, oldDegrees, degrees));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeadingType getHeadingType() {
		return headingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeadingType(HeadingType newHeadingType) {
		HeadingType oldHeadingType = headingType;
		headingType = newHeadingType == null ? HEADING_TYPE_EDEFAULT : newHeadingType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhysxPackage.HEADING__HEADING_TYPE, oldHeadingType, headingType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PhysxPackage.HEADING__DEGREES:
				return getDegrees();
			case PhysxPackage.HEADING__HEADING_TYPE:
				return getHeadingType();
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
			case PhysxPackage.HEADING__DEGREES:
				setDegrees((Double)newValue);
				return;
			case PhysxPackage.HEADING__HEADING_TYPE:
				setHeadingType((HeadingType)newValue);
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
			case PhysxPackage.HEADING__DEGREES:
				setDegrees(DEGREES_EDEFAULT);
				return;
			case PhysxPackage.HEADING__HEADING_TYPE:
				setHeadingType(HEADING_TYPE_EDEFAULT);
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
			case PhysxPackage.HEADING__DEGREES:
				return degrees != DEGREES_EDEFAULT;
			case PhysxPackage.HEADING__HEADING_TYPE:
				return headingType != HEADING_TYPE_EDEFAULT;
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
		result.append(" (degrees: ");
		result.append(degrees);
		result.append(", headingType: ");
		result.append(headingType);
		result.append(')');
		return result.toString();
	}

} //HeadingImpl
