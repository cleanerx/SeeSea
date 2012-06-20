/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.impl;

import net.sf.seesea.model.int1.buoysandbeacons.Beacon;
import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Beacon</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.BeaconImpl#isOnSubmergedRock <em>On Submerged Rock</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BeaconImpl extends AbstractCommonBuoyBeaconImpl implements Beacon {
	/**
	 * The default value of the '{@link #isOnSubmergedRock() <em>On Submerged Rock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnSubmergedRock()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ON_SUBMERGED_ROCK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOnSubmergedRock() <em>On Submerged Rock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnSubmergedRock()
	 * @generated
	 * @ordered
	 */
	protected boolean onSubmergedRock = ON_SUBMERGED_ROCK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BeaconImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BuoysandbeaconsPackage.Literals.BEACON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOnSubmergedRock() {
		return onSubmergedRock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnSubmergedRock(boolean newOnSubmergedRock) {
		boolean oldOnSubmergedRock = onSubmergedRock;
		onSubmergedRock = newOnSubmergedRock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.BEACON__ON_SUBMERGED_ROCK, oldOnSubmergedRock, onSubmergedRock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BuoysandbeaconsPackage.BEACON__ON_SUBMERGED_ROCK:
				return isOnSubmergedRock();
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
			case BuoysandbeaconsPackage.BEACON__ON_SUBMERGED_ROCK:
				setOnSubmergedRock((Boolean)newValue);
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
			case BuoysandbeaconsPackage.BEACON__ON_SUBMERGED_ROCK:
				setOnSubmergedRock(ON_SUBMERGED_ROCK_EDEFAULT);
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
			case BuoysandbeaconsPackage.BEACON__ON_SUBMERGED_ROCK:
				return onSubmergedRock != ON_SUBMERGED_ROCK_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (onSubmergedRock: ");
		result.append(onSubmergedRock);
		result.append(')');
		return result.toString();
	}

} //BeaconImpl
