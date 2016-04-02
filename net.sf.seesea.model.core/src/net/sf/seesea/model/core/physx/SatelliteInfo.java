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
package net.sf.seesea.model.core.physx;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Satellite Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.SatelliteInfo#getId <em>Id</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.SatelliteInfo#getElevation <em>Elevation</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.SatelliteInfo#getAzimuth <em>Azimuth</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.SatelliteInfo#getSignalStrength <em>Signal Strength</em>}</li>
 * </ul>
 *
 * @see net.sf.seesea.model.core.physx.PhysxPackage#getSatelliteInfo()
 * @model
 * @generated
 */
public interface SatelliteInfo extends Measurement {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getSatelliteInfo_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.SatelliteInfo#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Elevation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elevation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elevation</em>' attribute.
	 * @see #setElevation(int)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getSatelliteInfo_Elevation()
	 * @model
	 * @generated
	 */
	int getElevation();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.SatelliteInfo#getElevation <em>Elevation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elevation</em>' attribute.
	 * @see #getElevation()
	 * @generated
	 */
	void setElevation(int value);

	/**
	 * Returns the value of the '<em><b>Azimuth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Azimuth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Azimuth</em>' attribute.
	 * @see #setAzimuth(int)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getSatelliteInfo_Azimuth()
	 * @model
	 * @generated
	 */
	int getAzimuth();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.SatelliteInfo#getAzimuth <em>Azimuth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Azimuth</em>' attribute.
	 * @see #getAzimuth()
	 * @generated
	 */
	void setAzimuth(int value);

	/**
	 * Returns the value of the '<em><b>Signal Strength</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal Strength</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal Strength</em>' attribute.
	 * @see #setSignalStrength(int)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getSatelliteInfo_SignalStrength()
	 * @model
	 * @generated
	 */
	int getSignalStrength();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.SatelliteInfo#getSignalStrength <em>Signal Strength</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal Strength</em>' attribute.
	 * @see #getSignalStrength()
	 * @generated
	 */
	void setSignalStrength(int value);

} // SatelliteInfo
