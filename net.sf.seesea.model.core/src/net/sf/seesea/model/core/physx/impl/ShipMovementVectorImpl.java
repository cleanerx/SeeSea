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
package net.sf.seesea.model.core.physx.impl;

import net.sf.seesea.model.core.physx.HeadingType;
import java.util.Collection;

import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.PhysxPackage;
import net.sf.seesea.model.core.physx.ShipMovementVector;
import net.sf.seesea.model.core.physx.Speed;

import net.sf.seesea.model.core.physx.SpeedType;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ship Movement Vector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.ShipMovementVectorImpl#getHeadings <em>Headings</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.physx.impl.ShipMovementVectorImpl#getSpeeds <em>Speeds</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ShipMovementVectorImpl extends EObjectImpl implements ShipMovementVector {
	/**
	 * The cached value of the '{@link #getHeadings() <em>Headings</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadings()
	 * @generated
	 * @ordered
	 */
	protected EMap<HeadingType, Double> headings;

	/**
	 * The cached value of the '{@link #getSpeeds() <em>Speeds</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeeds()
	 * @generated
	 * @ordered
	 */
	protected EMap<SpeedType, Speed> speeds;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShipMovementVectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhysxPackage.Literals.SHIP_MOVEMENT_VECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<HeadingType, Double> getHeadings() {
		if (headings == null) {
			headings = new EcoreEMap<HeadingType,Double>(PhysxPackage.Literals.HEADING2_DEGREES_ENTRY, Heading2DegreesEntryImpl.class, this, PhysxPackage.SHIP_MOVEMENT_VECTOR__HEADINGS);
		}
		return headings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<SpeedType, Speed> getSpeeds() {
		if (speeds == null) {
			speeds = new EcoreEMap<SpeedType,Speed>(PhysxPackage.Literals.SPEED_TYPE2_SPEED_ENTRY, SpeedType2SpeedEntryImpl.class, this, PhysxPackage.SHIP_MOVEMENT_VECTOR__SPEEDS);
		}
		return speeds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__HEADINGS:
				return ((InternalEList<?>)getHeadings()).basicRemove(otherEnd, msgs);
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__SPEEDS:
				return ((InternalEList<?>)getSpeeds()).basicRemove(otherEnd, msgs);
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
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__HEADINGS:
				if (coreType) return getHeadings();
				else return getHeadings().map();
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__SPEEDS:
				if (coreType) return getSpeeds();
				else return getSpeeds().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__HEADINGS:
				((EStructuralFeature.Setting)getHeadings()).set(newValue);
				return;
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__SPEEDS:
				((EStructuralFeature.Setting)getSpeeds()).set(newValue);
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
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__HEADINGS:
				getHeadings().clear();
				return;
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__SPEEDS:
				getSpeeds().clear();
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
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__HEADINGS:
				return headings != null && !headings.isEmpty();
			case PhysxPackage.SHIP_MOVEMENT_VECTOR__SPEEDS:
				return speeds != null && !speeds.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ShipMovementVectorImpl
