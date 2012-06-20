/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.natureofseabed.impl;

import net.sf.seesea.model.core.impl.ModelObjectImpl;

import net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed;
import net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage;
import net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature;
import net.sf.seesea.model.int1.natureofseabed.SeabedType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nature Of Seabed</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.natureofseabed.impl.NatureOfSeabedImpl#getSeabedType <em>Seabed Type</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.natureofseabed.impl.NatureOfSeabedImpl#getSeabedNature <em>Seabed Nature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NatureOfSeabedImpl extends ModelObjectImpl implements NatureOfSeabed {
	/**
	 * The default value of the '{@link #getSeabedType() <em>Seabed Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeabedType()
	 * @generated
	 * @ordered
	 */
	protected static final SeabedType SEABED_TYPE_EDEFAULT = SeabedType.UNDEFINED;

	/**
	 * The cached value of the '{@link #getSeabedType() <em>Seabed Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeabedType()
	 * @generated
	 * @ordered
	 */
	protected SeabedType seabedType = SEABED_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeabedNature() <em>Seabed Nature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeabedNature()
	 * @generated
	 * @ordered
	 */
	protected static final QualifyingSeabedNature SEABED_NATURE_EDEFAULT = QualifyingSeabedNature.UNDEFINED;

	/**
	 * The cached value of the '{@link #getSeabedNature() <em>Seabed Nature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeabedNature()
	 * @generated
	 * @ordered
	 */
	protected QualifyingSeabedNature seabedNature = SEABED_NATURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NatureOfSeabedImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NatureofseabedPackage.Literals.NATURE_OF_SEABED;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeabedType getSeabedType() {
		return seabedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeabedType(SeabedType newSeabedType) {
		SeabedType oldSeabedType = seabedType;
		seabedType = newSeabedType == null ? SEABED_TYPE_EDEFAULT : newSeabedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NatureofseabedPackage.NATURE_OF_SEABED__SEABED_TYPE, oldSeabedType, seabedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualifyingSeabedNature getSeabedNature() {
		return seabedNature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeabedNature(QualifyingSeabedNature newSeabedNature) {
		QualifyingSeabedNature oldSeabedNature = seabedNature;
		seabedNature = newSeabedNature == null ? SEABED_NATURE_EDEFAULT : newSeabedNature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NatureofseabedPackage.NATURE_OF_SEABED__SEABED_NATURE, oldSeabedNature, seabedNature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NatureofseabedPackage.NATURE_OF_SEABED__SEABED_TYPE:
				return getSeabedType();
			case NatureofseabedPackage.NATURE_OF_SEABED__SEABED_NATURE:
				return getSeabedNature();
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
			case NatureofseabedPackage.NATURE_OF_SEABED__SEABED_TYPE:
				setSeabedType((SeabedType)newValue);
				return;
			case NatureofseabedPackage.NATURE_OF_SEABED__SEABED_NATURE:
				setSeabedNature((QualifyingSeabedNature)newValue);
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
			case NatureofseabedPackage.NATURE_OF_SEABED__SEABED_TYPE:
				setSeabedType(SEABED_TYPE_EDEFAULT);
				return;
			case NatureofseabedPackage.NATURE_OF_SEABED__SEABED_NATURE:
				setSeabedNature(SEABED_NATURE_EDEFAULT);
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
			case NatureofseabedPackage.NATURE_OF_SEABED__SEABED_TYPE:
				return seabedType != SEABED_TYPE_EDEFAULT;
			case NatureofseabedPackage.NATURE_OF_SEABED__SEABED_NATURE:
				return seabedNature != SEABED_NATURE_EDEFAULT;
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
		result.append(" (seabedType: ");
		result.append(seabedType);
		result.append(", seabedNature: ");
		result.append(seabedNature);
		result.append(')');
		return result.toString();
	}

} //NatureOfSeabedImpl
