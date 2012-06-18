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

import java.util.Collection;

import net.sf.seesea.model.core.physx.impl.MeasurementImpl;

import net.sf.seesea.model.core.weather.CloudCoverage;
import net.sf.seesea.model.core.weather.CloudMeasurement;
import net.sf.seesea.model.core.weather.CloudType;
import net.sf.seesea.model.core.weather.WeatherPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cloud Measurement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.weather.impl.CloudMeasurementImpl#getCloudCoverage <em>Cloud Coverage</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.weather.impl.CloudMeasurementImpl#getCloudTypes <em>Cloud Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CloudMeasurementImpl extends MeasurementImpl implements CloudMeasurement {
	/**
	 * The default value of the '{@link #getCloudCoverage() <em>Cloud Coverage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverage()
	 * @generated
	 * @ordered
	 */
	protected static final CloudCoverage CLOUD_COVERAGE_EDEFAULT = CloudCoverage.UNDEFINED;

	/**
	 * The cached value of the '{@link #getCloudCoverage() <em>Cloud Coverage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudCoverage()
	 * @generated
	 * @ordered
	 */
	protected CloudCoverage cloudCoverage = CLOUD_COVERAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCloudTypes() <em>Cloud Types</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloudTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<CloudType> cloudTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CloudMeasurementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WeatherPackage.Literals.CLOUD_MEASUREMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CloudCoverage getCloudCoverage() {
		return cloudCoverage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCloudCoverage(CloudCoverage newCloudCoverage) {
		CloudCoverage oldCloudCoverage = cloudCoverage;
		cloudCoverage = newCloudCoverage == null ? CLOUD_COVERAGE_EDEFAULT : newCloudCoverage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WeatherPackage.CLOUD_MEASUREMENT__CLOUD_COVERAGE, oldCloudCoverage, cloudCoverage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CloudType> getCloudTypes() {
		if (cloudTypes == null) {
			cloudTypes = new EDataTypeUniqueEList<CloudType>(CloudType.class, this, WeatherPackage.CLOUD_MEASUREMENT__CLOUD_TYPES);
		}
		return cloudTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WeatherPackage.CLOUD_MEASUREMENT__CLOUD_COVERAGE:
				return getCloudCoverage();
			case WeatherPackage.CLOUD_MEASUREMENT__CLOUD_TYPES:
				return getCloudTypes();
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
			case WeatherPackage.CLOUD_MEASUREMENT__CLOUD_COVERAGE:
				setCloudCoverage((CloudCoverage)newValue);
				return;
			case WeatherPackage.CLOUD_MEASUREMENT__CLOUD_TYPES:
				getCloudTypes().clear();
				getCloudTypes().addAll((Collection<? extends CloudType>)newValue);
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
			case WeatherPackage.CLOUD_MEASUREMENT__CLOUD_COVERAGE:
				setCloudCoverage(CLOUD_COVERAGE_EDEFAULT);
				return;
			case WeatherPackage.CLOUD_MEASUREMENT__CLOUD_TYPES:
				getCloudTypes().clear();
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
			case WeatherPackage.CLOUD_MEASUREMENT__CLOUD_COVERAGE:
				return cloudCoverage != CLOUD_COVERAGE_EDEFAULT;
			case WeatherPackage.CLOUD_MEASUREMENT__CLOUD_TYPES:
				return cloudTypes != null && !cloudTypes.isEmpty();
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
		result.append(" (cloudCoverage: ");
		result.append(cloudCoverage);
		result.append(", cloudTypes: ");
		result.append(cloudTypes);
		result.append(')');
		return result.toString();
	}

} //CloudMeasurementImpl
