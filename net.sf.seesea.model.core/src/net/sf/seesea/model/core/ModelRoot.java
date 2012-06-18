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
package net.sf.seesea.model.core;

import net.sf.seesea.model.core.data.Instruments;
import net.sf.seesea.model.core.geo.ChartContainer;
import net.sf.seesea.model.core.geo.NavigationCompound;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.ModelRoot#getChartContainer <em>Chart Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.ModelRoot#getInstruments <em>Instruments</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.core.CorePackage#getModelRoot()
 * @model
 * @generated
 */
public interface ModelRoot extends NavigationCompound {
	/**
	 * Returns the value of the '<em><b>Chart Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chart Container</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chart Container</em>' containment reference.
	 * @see #setChartContainer(ChartContainer)
	 * @see net.sf.seesea.model.core.CorePackage#getModelRoot_ChartContainer()
	 * @model containment="true"
	 * @generated
	 */
	ChartContainer getChartContainer();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.ModelRoot#getChartContainer <em>Chart Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chart Container</em>' containment reference.
	 * @see #getChartContainer()
	 * @generated
	 */
	void setChartContainer(ChartContainer value);

	/**
	 * Returns the value of the '<em><b>Instruments</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instruments</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instruments</em>' containment reference.
	 * @see #setInstruments(Instruments)
	 * @see net.sf.seesea.model.core.CorePackage#getModelRoot_Instruments()
	 * @model containment="true"
	 * @generated
	 */
	Instruments getInstruments();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.ModelRoot#getInstruments <em>Instruments</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instruments</em>' containment reference.
	 * @see #getInstruments()
	 * @generated
	 */
	void setInstruments(Instruments value);

} // ModelRoot
