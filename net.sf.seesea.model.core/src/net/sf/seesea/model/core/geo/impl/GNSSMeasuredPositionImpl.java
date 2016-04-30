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

import java.util.Collection;
import net.sf.seesea.model.core.geo.GNSSMeasuredPosition;
import net.sf.seesea.model.core.geo.GeoPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GNSS Measured Position</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.GNSSMeasuredPositionImpl#getHdop <em>Hdop</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.GNSSMeasuredPositionImpl#getVdop <em>Vdop</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.GNSSMeasuredPositionImpl#getPdop <em>Pdop</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.impl.GNSSMeasuredPositionImpl#getAugmentation <em>Augmentation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GNSSMeasuredPositionImpl extends MeasuredPosition3DImpl implements GNSSMeasuredPosition {
	/**
	 * The default value of the '{@link #getHdop() <em>Hdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHdop()
	 * @generated
	 * @ordered
	 */
	protected static final double HDOP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getHdop() <em>Hdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHdop()
	 * @generated
	 * @ordered
	 */
	protected double hdop = HDOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getVdop() <em>Vdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVdop()
	 * @generated
	 * @ordered
	 */
	protected static final double VDOP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getVdop() <em>Vdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVdop()
	 * @generated
	 * @ordered
	 */
	protected double vdop = VDOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getPdop() <em>Pdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPdop()
	 * @generated
	 * @ordered
	 */
	protected static final double PDOP_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPdop() <em>Pdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPdop()
	 * @generated
	 * @ordered
	 */
	protected double pdop = PDOP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAugmentation() <em>Augmentation</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAugmentation()
	 * @generated
	 * @ordered
	 */
	protected EList<String> augmentation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GNSSMeasuredPositionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeoPackage.Literals.GNSS_MEASURED_POSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getHdop() {
		return hdop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHdop(double newHdop) {
		double oldHdop = hdop;
		hdop = newHdop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.GNSS_MEASURED_POSITION__HDOP, oldHdop, hdop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getVdop() {
		return vdop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVdop(double newVdop) {
		double oldVdop = vdop;
		vdop = newVdop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.GNSS_MEASURED_POSITION__VDOP, oldVdop, vdop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPdop() {
		return pdop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPdop(double newPdop) {
		double oldPdop = pdop;
		pdop = newPdop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeoPackage.GNSS_MEASURED_POSITION__PDOP, oldPdop, pdop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAugmentation() {
		if (augmentation == null) {
			augmentation = new EDataTypeUniqueEList<String>(String.class, this, GeoPackage.GNSS_MEASURED_POSITION__AUGMENTATION);
		}
		return augmentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeoPackage.GNSS_MEASURED_POSITION__HDOP:
				return getHdop();
			case GeoPackage.GNSS_MEASURED_POSITION__VDOP:
				return getVdop();
			case GeoPackage.GNSS_MEASURED_POSITION__PDOP:
				return getPdop();
			case GeoPackage.GNSS_MEASURED_POSITION__AUGMENTATION:
				return getAugmentation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GeoPackage.GNSS_MEASURED_POSITION__HDOP:
				setHdop((Double)newValue);
				return;
			case GeoPackage.GNSS_MEASURED_POSITION__VDOP:
				setVdop((Double)newValue);
				return;
			case GeoPackage.GNSS_MEASURED_POSITION__PDOP:
				setPdop((Double)newValue);
				return;
			case GeoPackage.GNSS_MEASURED_POSITION__AUGMENTATION:
				getAugmentation().clear();
				getAugmentation().addAll((Collection<? extends String>)newValue);
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
			case GeoPackage.GNSS_MEASURED_POSITION__HDOP:
				setHdop(HDOP_EDEFAULT);
				return;
			case GeoPackage.GNSS_MEASURED_POSITION__VDOP:
				setVdop(VDOP_EDEFAULT);
				return;
			case GeoPackage.GNSS_MEASURED_POSITION__PDOP:
				setPdop(PDOP_EDEFAULT);
				return;
			case GeoPackage.GNSS_MEASURED_POSITION__AUGMENTATION:
				getAugmentation().clear();
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
			case GeoPackage.GNSS_MEASURED_POSITION__HDOP:
				return hdop != HDOP_EDEFAULT;
			case GeoPackage.GNSS_MEASURED_POSITION__VDOP:
				return vdop != VDOP_EDEFAULT;
			case GeoPackage.GNSS_MEASURED_POSITION__PDOP:
				return pdop != PDOP_EDEFAULT;
			case GeoPackage.GNSS_MEASURED_POSITION__AUGMENTATION:
				return augmentation != null && !augmentation.isEmpty();
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
		result.append(" (hdop: "); //$NON-NLS-1$
		result.append(hdop);
		result.append(", vdop: "); //$NON-NLS-1$
		result.append(vdop);
		result.append(", pdop: "); //$NON-NLS-1$
		result.append(pdop);
		result.append(", augmentation: "); //$NON-NLS-1$
		result.append(augmentation);
		result.append(')');
		return result.toString();
	}

} //GNSSMeasuredPositionImpl
