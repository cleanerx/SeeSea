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
package net.sf.seesea.model.core.impl;

import net.sf.seesea.model.core.CorePackage;
import net.sf.seesea.model.core.ModelRoot;

import net.sf.seesea.model.core.data.Instruments;
import net.sf.seesea.model.core.geo.ChartContainer;

import net.sf.seesea.model.core.geo.impl.NavigationCompoundImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.impl.ModelRootImpl#getChartContainer <em>Chart Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.impl.ModelRootImpl#getInstruments <em>Instruments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelRootImpl extends NavigationCompoundImpl implements ModelRoot {
	/**
	 * The cached value of the '{@link #getChartContainer() <em>Chart Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChartContainer()
	 * @generated
	 * @ordered
	 */
	protected ChartContainer chartContainer;

	/**
	 * The cached value of the '{@link #getInstruments() <em>Instruments</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstruments()
	 * @generated
	 * @ordered
	 */
	protected Instruments instruments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.MODEL_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChartContainer getChartContainer() {
		return chartContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChartContainer(ChartContainer newChartContainer, NotificationChain msgs) {
		ChartContainer oldChartContainer = chartContainer;
		chartContainer = newChartContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ROOT__CHART_CONTAINER, oldChartContainer, newChartContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChartContainer(ChartContainer newChartContainer) {
		if (newChartContainer != chartContainer) {
			NotificationChain msgs = null;
			if (chartContainer != null)
				msgs = ((InternalEObject)chartContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.MODEL_ROOT__CHART_CONTAINER, null, msgs);
			if (newChartContainer != null)
				msgs = ((InternalEObject)newChartContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.MODEL_ROOT__CHART_CONTAINER, null, msgs);
			msgs = basicSetChartContainer(newChartContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ROOT__CHART_CONTAINER, newChartContainer, newChartContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Instruments getInstruments() {
		return instruments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInstruments(Instruments newInstruments, NotificationChain msgs) {
		Instruments oldInstruments = instruments;
		instruments = newInstruments;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ROOT__INSTRUMENTS, oldInstruments, newInstruments);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstruments(Instruments newInstruments) {
		if (newInstruments != instruments) {
			NotificationChain msgs = null;
			if (instruments != null)
				msgs = ((InternalEObject)instruments).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.MODEL_ROOT__INSTRUMENTS, null, msgs);
			if (newInstruments != null)
				msgs = ((InternalEObject)newInstruments).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.MODEL_ROOT__INSTRUMENTS, null, msgs);
			msgs = basicSetInstruments(newInstruments, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.MODEL_ROOT__INSTRUMENTS, newInstruments, newInstruments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.MODEL_ROOT__CHART_CONTAINER:
				return basicSetChartContainer(null, msgs);
			case CorePackage.MODEL_ROOT__INSTRUMENTS:
				return basicSetInstruments(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.MODEL_ROOT__CHART_CONTAINER:
				return getChartContainer();
			case CorePackage.MODEL_ROOT__INSTRUMENTS:
				return getInstruments();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CorePackage.MODEL_ROOT__CHART_CONTAINER:
				setChartContainer((ChartContainer)newValue);
				return;
			case CorePackage.MODEL_ROOT__INSTRUMENTS:
				setInstruments((Instruments)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CorePackage.MODEL_ROOT__CHART_CONTAINER:
				setChartContainer((ChartContainer)null);
				return;
			case CorePackage.MODEL_ROOT__INSTRUMENTS:
				setInstruments((Instruments)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CorePackage.MODEL_ROOT__CHART_CONTAINER:
				return chartContainer != null;
			case CorePackage.MODEL_ROOT__INSTRUMENTS:
				return instruments != null;
		}
		return super.eIsSet(featureID);
	}

} //ModelRootImpl
