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
package net.sf.seesea.model.core.physx;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ship Movement Vector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.ShipMovementVector#getHeadings <em>Headings</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.ShipMovementVector#getSpeeds <em>Speeds</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.core.physx.PhysxPackage#getShipMovementVector()
 * @model
 * @generated
 */
public interface ShipMovementVector extends EObject {
	/**
	 * Returns the value of the '<em><b>Headings</b></em>' map.
	 * The key is of type {@link net.sf.seesea.model.core.physx.HeadingType},
	 * and the value is of type {@link java.lang.Double},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Headings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Headings</em>' map.
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getShipMovementVector_Headings()
	 * @model mapType="net.sf.seesea.model.core.physx.Heading2DegreesEntry<net.sf.seesea.model.core.physx.HeadingType, org.eclipse.emf.ecore.EDoubleObject>"
	 * @generated
	 */
	EMap<HeadingType, Double> getHeadings();

	/**
	 * Returns the value of the '<em><b>Speeds</b></em>' map.
	 * The key is of type {@link net.sf.seesea.model.core.physx.SpeedType},
	 * and the value is of type {@link net.sf.seesea.model.core.physx.Speed},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Speeds</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Speeds</em>' map.
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getShipMovementVector_Speeds()
	 * @model mapType="net.sf.seesea.model.core.physx.SpeedType2SpeedEntry<net.sf.seesea.model.core.physx.SpeedType, net.sf.seesea.model.core.physx.Speed>"
	 * @generated
	 */
	EMap<SpeedType, Speed> getSpeeds();

} // ShipMovementVector
