/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.impl;

import java.util.Collection;

import net.sf.seesea.model.core.impl.ModelObjectImpl;

import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;
import net.sf.seesea.model.int1.buoysandbeacons.Topmark;
import net.sf.seesea.model.int1.buoysandbeacons.TopmarkType;

import net.sf.seesea.model.int1.lights.Color;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Topmark</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.TopmarkImpl#getTopmarkParts <em>Topmark Parts</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.TopmarkImpl#getTopmarkColor <em>Topmark Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TopmarkImpl extends ModelObjectImpl implements Topmark {
	/**
	 * The cached value of the '{@link #getTopmarkParts() <em>Topmark Parts</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopmarkParts()
	 * @generated
	 * @ordered
	 */
	protected EList<TopmarkType> topmarkParts;

	/**
	 * The default value of the '{@link #getTopmarkColor() <em>Topmark Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopmarkColor()
	 * @generated
	 * @ordered
	 */
	protected static final Color TOPMARK_COLOR_EDEFAULT = Color.UNKNOWN;

	/**
	 * The cached value of the '{@link #getTopmarkColor() <em>Topmark Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopmarkColor()
	 * @generated
	 * @ordered
	 */
	protected Color topmarkColor = TOPMARK_COLOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TopmarkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BuoysandbeaconsPackage.Literals.TOPMARK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopmarkType> getTopmarkParts() {
		if (topmarkParts == null) {
			topmarkParts = new EDataTypeUniqueEList<TopmarkType>(TopmarkType.class, this, BuoysandbeaconsPackage.TOPMARK__TOPMARK_PARTS);
		}
		return topmarkParts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getTopmarkColor() {
		return topmarkColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTopmarkColor(Color newTopmarkColor) {
		Color oldTopmarkColor = topmarkColor;
		topmarkColor = newTopmarkColor == null ? TOPMARK_COLOR_EDEFAULT : newTopmarkColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.TOPMARK__TOPMARK_COLOR, oldTopmarkColor, topmarkColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BuoysandbeaconsPackage.TOPMARK__TOPMARK_PARTS:
				return getTopmarkParts();
			case BuoysandbeaconsPackage.TOPMARK__TOPMARK_COLOR:
				return getTopmarkColor();
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
			case BuoysandbeaconsPackage.TOPMARK__TOPMARK_PARTS:
				getTopmarkParts().clear();
				getTopmarkParts().addAll((Collection<? extends TopmarkType>)newValue);
				return;
			case BuoysandbeaconsPackage.TOPMARK__TOPMARK_COLOR:
				setTopmarkColor((Color)newValue);
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
			case BuoysandbeaconsPackage.TOPMARK__TOPMARK_PARTS:
				getTopmarkParts().clear();
				return;
			case BuoysandbeaconsPackage.TOPMARK__TOPMARK_COLOR:
				setTopmarkColor(TOPMARK_COLOR_EDEFAULT);
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
			case BuoysandbeaconsPackage.TOPMARK__TOPMARK_PARTS:
				return topmarkParts != null && !topmarkParts.isEmpty();
			case BuoysandbeaconsPackage.TOPMARK__TOPMARK_COLOR:
				return topmarkColor != TOPMARK_COLOR_EDEFAULT;
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
		result.append(" (topmarkParts: ");
		result.append(topmarkParts);
		result.append(", topmarkColor: ");
		result.append(topmarkColor);
		result.append(')');
		return result.toString();
	}

} //TopmarkImpl
