/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.impl;

import net.sf.seesea.model.core.geo.impl.ChartAreaImpl;

import net.sf.seesea.model.int1.tracksandroutes.BorderType;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;
import net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Traffic Separation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationImpl#getBorderType <em>Border Type</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.impl.TrafficSeparationImpl#isIsNaturallyDivided <em>Is Naturally Divided</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TrafficSeparationImpl extends ChartAreaImpl implements TrafficSeparation {
	/**
	 * The default value of the '{@link #getBorderType() <em>Border Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBorderType()
	 * @generated
	 * @ordered
	 */
	protected static final BorderType BORDER_TYPE_EDEFAULT = BorderType.UNDEFINED;

	/**
	 * The cached value of the '{@link #getBorderType() <em>Border Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBorderType()
	 * @generated
	 * @ordered
	 */
	protected BorderType borderType = BORDER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsNaturallyDivided() <em>Is Naturally Divided</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNaturallyDivided()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NATURALLY_DIVIDED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNaturallyDivided() <em>Is Naturally Divided</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNaturallyDivided()
	 * @generated
	 * @ordered
	 */
	protected boolean isNaturallyDivided = IS_NATURALLY_DIVIDED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrafficSeparationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracksandroutesPackage.Literals.TRAFFIC_SEPARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BorderType getBorderType() {
		return borderType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBorderType(BorderType newBorderType) {
		BorderType oldBorderType = borderType;
		borderType = newBorderType == null ? BORDER_TYPE_EDEFAULT : newBorderType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracksandroutesPackage.TRAFFIC_SEPARATION__BORDER_TYPE, oldBorderType, borderType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNaturallyDivided() {
		return isNaturallyDivided;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNaturallyDivided(boolean newIsNaturallyDivided) {
		boolean oldIsNaturallyDivided = isNaturallyDivided;
		isNaturallyDivided = newIsNaturallyDivided;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracksandroutesPackage.TRAFFIC_SEPARATION__IS_NATURALLY_DIVIDED, oldIsNaturallyDivided, isNaturallyDivided));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracksandroutesPackage.TRAFFIC_SEPARATION__BORDER_TYPE:
				return getBorderType();
			case TracksandroutesPackage.TRAFFIC_SEPARATION__IS_NATURALLY_DIVIDED:
				return isIsNaturallyDivided();
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
			case TracksandroutesPackage.TRAFFIC_SEPARATION__BORDER_TYPE:
				setBorderType((BorderType)newValue);
				return;
			case TracksandroutesPackage.TRAFFIC_SEPARATION__IS_NATURALLY_DIVIDED:
				setIsNaturallyDivided((Boolean)newValue);
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
			case TracksandroutesPackage.TRAFFIC_SEPARATION__BORDER_TYPE:
				setBorderType(BORDER_TYPE_EDEFAULT);
				return;
			case TracksandroutesPackage.TRAFFIC_SEPARATION__IS_NATURALLY_DIVIDED:
				setIsNaturallyDivided(IS_NATURALLY_DIVIDED_EDEFAULT);
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
			case TracksandroutesPackage.TRAFFIC_SEPARATION__BORDER_TYPE:
				return borderType != BORDER_TYPE_EDEFAULT;
			case TracksandroutesPackage.TRAFFIC_SEPARATION__IS_NATURALLY_DIVIDED:
				return isNaturallyDivided != IS_NATURALLY_DIVIDED_EDEFAULT;
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
		result.append(" (borderType: ");
		result.append(borderType);
		result.append(", isNaturallyDivided: ");
		result.append(isNaturallyDivided);
		result.append(')');
		return result.toString();
	}

} //TrafficSeparationImpl
