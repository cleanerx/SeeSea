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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Heading</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.Heading#getDegrees <em>Degrees</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.Heading#getHeadingType <em>Heading Type</em>}</li>
 * </ul>
 *
 * @see net.sf.seesea.model.core.physx.PhysxPackage#getHeading()
 * @model
 * @generated
 */
public interface Heading extends Measurement {
	/**
	 * Returns the value of the '<em><b>Degrees</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Degrees</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Degrees</em>' attribute.
	 * @see #setDegrees(double)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getHeading_Degrees()
	 * @model
	 * @generated
	 */
	double getDegrees();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.Heading#getDegrees <em>Degrees</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Degrees</em>' attribute.
	 * @see #getDegrees()
	 * @generated
	 */
	void setDegrees(double value);

	/**
	 * Returns the value of the '<em><b>Heading Type</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.core.physx.HeadingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Heading Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Heading Type</em>' attribute.
	 * @see net.sf.seesea.model.core.physx.HeadingType
	 * @see #setHeadingType(HeadingType)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getHeading_HeadingType()
	 * @model
	 * @generated
	 */
	HeadingType getHeadingType();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.Heading#getHeadingType <em>Heading Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Heading Type</em>' attribute.
	 * @see net.sf.seesea.model.core.physx.HeadingType
	 * @see #getHeadingType()
	 * @generated
	 */
	void setHeadingType(HeadingType value);

} // Heading
