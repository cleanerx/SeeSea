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
package net.sf.seesea.model.core.weather;

import net.sf.seesea.model.core.physx.Measurement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cloud Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.weather.CloudMeasurement#getCloudCoverage <em>Cloud Coverage</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.weather.CloudMeasurement#getCloudTypes <em>Cloud Types</em>}</li>
 * </ul>
 *
 * @see net.sf.seesea.model.core.weather.WeatherPackage#getCloudMeasurement()
 * @model
 * @generated
 */
public interface CloudMeasurement extends Measurement {
	/**
	 * Returns the value of the '<em><b>Cloud Coverage</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.core.weather.CloudCoverage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cloud Coverage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cloud Coverage</em>' attribute.
	 * @see net.sf.seesea.model.core.weather.CloudCoverage
	 * @see #setCloudCoverage(CloudCoverage)
	 * @see net.sf.seesea.model.core.weather.WeatherPackage#getCloudMeasurement_CloudCoverage()
	 * @model
	 * @generated
	 */
	CloudCoverage getCloudCoverage();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.weather.CloudMeasurement#getCloudCoverage <em>Cloud Coverage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cloud Coverage</em>' attribute.
	 * @see net.sf.seesea.model.core.weather.CloudCoverage
	 * @see #getCloudCoverage()
	 * @generated
	 */
	void setCloudCoverage(CloudCoverage value);

	/**
	 * Returns the value of the '<em><b>Cloud Types</b></em>' attribute list.
	 * The list contents are of type {@link net.sf.seesea.model.core.weather.CloudType}.
	 * The literals are from the enumeration {@link net.sf.seesea.model.core.weather.CloudType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cloud Types</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cloud Types</em>' attribute list.
	 * @see net.sf.seesea.model.core.weather.CloudType
	 * @see net.sf.seesea.model.core.weather.WeatherPackage#getCloudMeasurement_CloudTypes()
	 * @model
	 * @generated
	 */
	EList<CloudType> getCloudTypes();

} // CloudMeasurement
