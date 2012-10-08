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
package net.sf.seesea.model.core.physx;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relative Speed</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.RelativeSpeed#getKey <em>Key</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.RelativeSpeed#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.core.physx.PhysxPackage#getRelativeSpeed()
 * @model
 * @generated
 */
public interface RelativeSpeed extends Measurement {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.core.physx.SpeedType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see net.sf.seesea.model.core.physx.SpeedType
	 * @see #setKey(SpeedType)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getRelativeSpeed_Key()
	 * @model
	 * @generated
	 */
	SpeedType getKey();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.RelativeSpeed#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see net.sf.seesea.model.core.physx.SpeedType
	 * @see #getKey()
	 * @generated
	 */
	void setKey(SpeedType value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' reference.
	 * @see #setValue(Speed)
	 * @see net.sf.seesea.model.core.physx.PhysxPackage#getRelativeSpeed_Value()
	 * @model
	 * @generated
	 */
	Speed getValue();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.physx.RelativeSpeed#getValue <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Speed value);

} // RelativeSpeed
