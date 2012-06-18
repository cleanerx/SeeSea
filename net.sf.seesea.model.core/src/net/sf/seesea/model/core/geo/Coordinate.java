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
package net.sf.seesea.model.core.geo;

import net.sf.seesea.model.core.ModelObject;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Coordinate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.Coordinate#getDegree <em>Degree</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.Coordinate#getMinute <em>Minute</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.Coordinate#getSecond <em>Second</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.core.geo.GeoPackage#getCoordinate()
 * @model
 * @generated
 */
public interface Coordinate extends ModelObject {
	/**
	 * Returns the value of the '<em><b>Degree</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Degree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Degree</em>' attribute.
	 * @see #setDegree(int)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getCoordinate_Degree()
	 * @model default="0"
	 * @generated
	 */
	int getDegree();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.Coordinate#getDegree <em>Degree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Degree</em>' attribute.
	 * @see #getDegree()
	 * @generated
	 */
	void setDegree(int value);

	/**
	 * Returns the value of the '<em><b>Minute</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minute</em>' attribute.
	 * @see #setMinute(int)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getCoordinate_Minute()
	 * @model default="0"
	 * @generated
	 */
	int getMinute();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.Coordinate#getMinute <em>Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minute</em>' attribute.
	 * @see #getMinute()
	 * @generated
	 */
	void setMinute(int value);

	/**
	 * Returns the value of the '<em><b>Second</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Second</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Second</em>' attribute.
	 * @see #setSecond(double)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getCoordinate_Second()
	 * @model default="0"
	 * @generated
	 */
	double getSecond();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.Coordinate#getSecond <em>Second</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second</em>' attribute.
	 * @see #getSecond()
	 * @generated
	 */
	void setSecond(double value);

	/**
	 * 
	 * @return the decimal degree
	 */
	double getDecimalDegree();

} 
