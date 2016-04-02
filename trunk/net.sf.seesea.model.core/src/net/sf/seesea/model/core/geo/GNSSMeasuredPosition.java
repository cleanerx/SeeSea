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
package net.sf.seesea.model.core.geo;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GNSS Measured Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.GNSSMeasuredPosition#getHdop <em>Hdop</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.GNSSMeasuredPosition#getVdop <em>Vdop</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.GNSSMeasuredPosition#getPdop <em>Pdop</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.GNSSMeasuredPosition#getAugmentation <em>Augmentation</em>}</li>
 * </ul>
 *
 * @see net.sf.seesea.model.core.geo.GeoPackage#getGNSSMeasuredPosition()
 * @model
 * @generated
 */
public interface GNSSMeasuredPosition extends MeasuredPosition3D {
	/**
	 * Returns the value of the '<em><b>Hdop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hdop</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hdop</em>' attribute.
	 * @see #setHdop(double)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getGNSSMeasuredPosition_Hdop()
	 * @model
	 * @generated
	 */
	double getHdop();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.GNSSMeasuredPosition#getHdop <em>Hdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hdop</em>' attribute.
	 * @see #getHdop()
	 * @generated
	 */
	void setHdop(double value);

	/**
	 * Returns the value of the '<em><b>Vdop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vdop</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vdop</em>' attribute.
	 * @see #setVdop(double)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getGNSSMeasuredPosition_Vdop()
	 * @model
	 * @generated
	 */
	double getVdop();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.GNSSMeasuredPosition#getVdop <em>Vdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vdop</em>' attribute.
	 * @see #getVdop()
	 * @generated
	 */
	void setVdop(double value);

	/**
	 * Returns the value of the '<em><b>Pdop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pdop</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pdop</em>' attribute.
	 * @see #setPdop(double)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getGNSSMeasuredPosition_Pdop()
	 * @model
	 * @generated
	 */
	double getPdop();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.GNSSMeasuredPosition#getPdop <em>Pdop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pdop</em>' attribute.
	 * @see #getPdop()
	 * @generated
	 */
	void setPdop(double value);

	/**
	 * Returns the value of the '<em><b>Augmentation</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Augmentation</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Augmentation</em>' attribute list.
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getGNSSMeasuredPosition_Augmentation()
	 * @model
	 * @generated
	 */
	EList<String> getAugmentation();

} // GNSSMeasuredPosition
