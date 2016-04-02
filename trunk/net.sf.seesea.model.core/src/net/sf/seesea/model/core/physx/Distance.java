/**
 * Copyright (c) 2010-2012, Jens Kï¿½bler
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
package net.sf.seesea.model.core.physx;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Distance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.Distance#getValue <em>Value</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.Distance#getUnit <em>Unit</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.Distance#getDistanceType <em>Distance Type</em>}</li>
 * </ul>
 *
 * @see net.sf.seesea.model.core.physx.PhysxPackage#getDistance()
 * @model
 * @generated
 */
public interface Distance extends Measurement {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getDistance_Value()
	 * @model
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.Distance#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.core.physx.LengthUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see net.sf.seesea.model.core.physx.LengthUnit
	 * @see #setUnit(LengthUnit)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getDistance_Unit()
	 * @model
	 * @generated
	 */
	LengthUnit getUnit();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.Distance#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see net.sf.seesea.model.core.physx.LengthUnit
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(LengthUnit value);

	/**
	 * Returns the value of the '<em><b>Distance Type</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.core.physx.DistanceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Distance Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Distance Type</em>' attribute.
	 * @see net.sf.seesea.model.core.physx.DistanceType
	 * @see #setDistanceType(DistanceType)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getDistance_DistanceType()
	 * @model
	 * @generated
	 */
	DistanceType getDistanceType();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.Distance#getDistanceType <em>Distance Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distance Type</em>' attribute.
	 * @see net.sf.seesea.model.core.physx.DistanceType
	 * @see #getDistanceType()
	 * @generated
	 */
	void setDistanceType(DistanceType value);

} // Distance
