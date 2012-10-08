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
package net.sf.seesea.model.core.data;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.GeoPosition3D;

import net.sf.seesea.model.core.physx.SatellitesVisible;
import net.sf.seesea.model.core.physx.Temperature;

import net.sf.seesea.model.core.weather.WindMeasurement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instruments</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.data.Instruments#getPosition <em>Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.data.Instruments#getWaterTemperature <em>Water Temperature</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.data.Instruments#getSatellitesVisible <em>Satellites Visible</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.data.Instruments#getWindVector <em>Wind Vector</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.core.data.DataPackage#getInstruments()
 * @model
 * @generated
 */
public interface Instruments extends ModelObject {
	/**
	 * Returns the value of the '<em><b>Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' reference.
	 * @see #setPosition(MeasuredPosition3D)
	 * @see net.sf.seesea.model.core.data.DataPackage#getInstruments_Position()
	 * @model
	 * @generated
	 */
	MeasuredPosition3D getPosition();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.data.Instruments#getPosition <em>Position</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' reference.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(MeasuredPosition3D value);

	/**
	 * Returns the value of the '<em><b>Water Temperature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Water Temperature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Water Temperature</em>' containment reference.
	 * @see #setWaterTemperature(Temperature)
	 * @see net.sf.seesea.model.core.data.DataPackage#getInstruments_WaterTemperature()
	 * @model containment="true"
	 * @generated
	 */
	Temperature getWaterTemperature();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.data.Instruments#getWaterTemperature <em>Water Temperature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Water Temperature</em>' containment reference.
	 * @see #getWaterTemperature()
	 * @generated
	 */
	void setWaterTemperature(Temperature value);

	/**
	 * Returns the value of the '<em><b>Satellites Visible</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Satellites Visible</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Satellites Visible</em>' containment reference.
	 * @see #setSatellitesVisible(SatellitesVisible)
	 * @see net.sf.seesea.model.core.data.DataPackage#getInstruments_SatellitesVisible()
	 * @model containment="true"
	 * @generated
	 */
	SatellitesVisible getSatellitesVisible();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.data.Instruments#getSatellitesVisible <em>Satellites Visible</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Satellites Visible</em>' containment reference.
	 * @see #getSatellitesVisible()
	 * @generated
	 */
	void setSatellitesVisible(SatellitesVisible value);

	/**
	 * Returns the value of the '<em><b>Wind Vector</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wind Vector</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wind Vector</em>' containment reference.
	 * @see #setWindVector(WindMeasurement)
	 * @see net.sf.seesea.model.core.data.DataPackage#getInstruments_WindVector()
	 * @model containment="true"
	 * @generated
	 */
	WindMeasurement getWindVector();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.data.Instruments#getWindVector <em>Wind Vector</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wind Vector</em>' containment reference.
	 * @see #getWindVector()
	 * @generated
	 */
	void setWindVector(WindMeasurement value);

} // Instruments
