/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.impl;

import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;
import net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Special Buoy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.SpecialBuoyImpl#getPurpose <em>Purpose</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.SpecialBuoyImpl#isPrivate <em>Private</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.SpecialBuoyImpl#getSeasonal <em>Seasonal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpecialBuoyImpl extends BuoyImpl implements SpecialBuoy {
	/**
	 * The default value of the '{@link #getPurpose() <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPurpose()
	 * @generated
	 * @ordered
	 */
	protected static final String PURPOSE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPurpose() <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPurpose()
	 * @generated
	 * @ordered
	 */
	protected String purpose = PURPOSE_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrivate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRIVATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrivate()
	 * @generated
	 * @ordered
	 */
	protected boolean private_ = PRIVATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeasonal() <em>Seasonal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeasonal()
	 * @generated
	 * @ordered
	 */
	protected static final String SEASONAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSeasonal() <em>Seasonal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeasonal()
	 * @generated
	 * @ordered
	 */
	protected String seasonal = SEASONAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecialBuoyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BuoysandbeaconsPackage.Literals.SPECIAL_BUOY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPurpose(String newPurpose) {
		String oldPurpose = purpose;
		purpose = newPurpose;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.SPECIAL_BUOY__PURPOSE, oldPurpose, purpose));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrivate() {
		return private_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrivate(boolean newPrivate) {
		boolean oldPrivate = private_;
		private_ = newPrivate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.SPECIAL_BUOY__PRIVATE, oldPrivate, private_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSeasonal() {
		return seasonal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeasonal(String newSeasonal) {
		String oldSeasonal = seasonal;
		seasonal = newSeasonal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.SPECIAL_BUOY__SEASONAL, oldSeasonal, seasonal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BuoysandbeaconsPackage.SPECIAL_BUOY__PURPOSE:
				return getPurpose();
			case BuoysandbeaconsPackage.SPECIAL_BUOY__PRIVATE:
				return isPrivate();
			case BuoysandbeaconsPackage.SPECIAL_BUOY__SEASONAL:
				return getSeasonal();
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
			case BuoysandbeaconsPackage.SPECIAL_BUOY__PURPOSE:
				setPurpose((String)newValue);
				return;
			case BuoysandbeaconsPackage.SPECIAL_BUOY__PRIVATE:
				setPrivate((Boolean)newValue);
				return;
			case BuoysandbeaconsPackage.SPECIAL_BUOY__SEASONAL:
				setSeasonal((String)newValue);
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
			case BuoysandbeaconsPackage.SPECIAL_BUOY__PURPOSE:
				setPurpose(PURPOSE_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.SPECIAL_BUOY__PRIVATE:
				setPrivate(PRIVATE_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.SPECIAL_BUOY__SEASONAL:
				setSeasonal(SEASONAL_EDEFAULT);
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
			case BuoysandbeaconsPackage.SPECIAL_BUOY__PURPOSE:
				return PURPOSE_EDEFAULT == null ? purpose != null : !PURPOSE_EDEFAULT.equals(purpose);
			case BuoysandbeaconsPackage.SPECIAL_BUOY__PRIVATE:
				return private_ != PRIVATE_EDEFAULT;
			case BuoysandbeaconsPackage.SPECIAL_BUOY__SEASONAL:
				return SEASONAL_EDEFAULT == null ? seasonal != null : !SEASONAL_EDEFAULT.equals(seasonal);
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
		result.append(" (purpose: ");
		result.append(purpose);
		result.append(", private: ");
		result.append(private_);
		result.append(", seasonal: ");
		result.append(seasonal);
		result.append(')');
		return result.toString();
	}

} //SpecialBuoyImpl
