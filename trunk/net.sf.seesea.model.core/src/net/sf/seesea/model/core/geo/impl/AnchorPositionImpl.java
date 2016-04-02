/**
 * Copyright (c) 2010-2012, Jens Kübler
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.model.core.geo.impl;

import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.model.core.geo.GeoPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Anchor Position</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.AnchorPositionImpl#getXExtent <em>XExtent</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.AnchorPositionImpl#getYExtent <em>YExtent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnchorPositionImpl extends GeoPositionImpl implements AnchorPosition {
	/**
	 * The default value of the '{@link #getXExtent() <em>XExtent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXExtent()
	 * @generated
	 * @ordered
	 */
	protected static final double XEXTENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXExtent() <em>XExtent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXExtent()
	 * @generated
	 * @ordered
	 */
	protected double xExtent = XEXTENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getYExtent() <em>YExtent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYExtent()
	 * @generated
	 * @ordered
	 */
	protected static final double YEXTENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getYExtent() <em>YExtent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYExtent()
	 * @generated
	 * @ordered
	 */
	protected double yExtent = YEXTENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnchorPositionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeoPackage.Literals.ANCHOR_POSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXExtent() {
		return xExtent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXExtent(double newXExtent) {
		double oldXExtent = xExtent;
		xExtent = newXExtent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.ANCHOR_POSITION__XEXTENT, oldXExtent, xExtent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getYExtent() {
		return yExtent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYExtent(double newYExtent) {
		double oldYExtent = yExtent;
		yExtent = newYExtent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.ANCHOR_POSITION__YEXTENT, oldYExtent, yExtent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeoPackage.ANCHOR_POSITION__XEXTENT:
				return getXExtent();
			case GeoPackage.ANCHOR_POSITION__YEXTENT:
				return getYExtent();
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
			case GeoPackage.ANCHOR_POSITION__XEXTENT:
				setXExtent((Double)newValue);
				return;
			case GeoPackage.ANCHOR_POSITION__YEXTENT:
				setYExtent((Double)newValue);
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
			case GeoPackage.ANCHOR_POSITION__XEXTENT:
				setXExtent(XEXTENT_EDEFAULT);
				return;
			case GeoPackage.ANCHOR_POSITION__YEXTENT:
				setYExtent(YEXTENT_EDEFAULT);
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
			case GeoPackage.ANCHOR_POSITION__XEXTENT:
				return xExtent != XEXTENT_EDEFAULT;
			case GeoPackage.ANCHOR_POSITION__YEXTENT:
				return yExtent != YEXTENT_EDEFAULT;
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
		result.append(" (xExtent: ");
		result.append(xExtent);
		result.append(", yExtent: ");
		result.append(yExtent);
		result.append(')');
		return result.toString();
	}

} //AnchorPositionImpl
