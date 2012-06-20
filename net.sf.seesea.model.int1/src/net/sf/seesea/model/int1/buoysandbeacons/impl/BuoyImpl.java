/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.impl;

import net.sf.seesea.model.int1.buoysandbeacons.Buoy;
import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;
import net.sf.seesea.model.int1.buoysandbeacons.Shape;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Buoy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.BuoyImpl#getDesignation <em>Designation</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.BuoyImpl#getShape <em>Shape</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BuoyImpl extends AbstractCommonBuoyBeaconImpl implements Buoy {
	/**
	 * The default value of the '{@link #getDesignation() <em>Designation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDesignation()
	 * @generated
	 * @ordered
	 */
	protected static final String DESIGNATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDesignation() <em>Designation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDesignation()
	 * @generated
	 * @ordered
	 */
	protected String designation = DESIGNATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getShape() <em>Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShape()
	 * @generated
	 * @ordered
	 */
	protected static final Shape SHAPE_EDEFAULT = Shape.UNKNOWN;

	/**
	 * The cached value of the '{@link #getShape() <em>Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShape()
	 * @generated
	 * @ordered
	 */
	protected Shape shape = SHAPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuoyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BuoysandbeaconsPackage.Literals.BUOY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDesignation(String newDesignation) {
		String oldDesignation = designation;
		designation = newDesignation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.BUOY__DESIGNATION, oldDesignation, designation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShape(Shape newShape) {
		Shape oldShape = shape;
		shape = newShape == null ? SHAPE_EDEFAULT : newShape;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.BUOY__SHAPE, oldShape, shape));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BuoysandbeaconsPackage.BUOY__DESIGNATION:
				return getDesignation();
			case BuoysandbeaconsPackage.BUOY__SHAPE:
				return getShape();
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
			case BuoysandbeaconsPackage.BUOY__DESIGNATION:
				setDesignation((String)newValue);
				return;
			case BuoysandbeaconsPackage.BUOY__SHAPE:
				setShape((Shape)newValue);
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
			case BuoysandbeaconsPackage.BUOY__DESIGNATION:
				setDesignation(DESIGNATION_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.BUOY__SHAPE:
				setShape(SHAPE_EDEFAULT);
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
			case BuoysandbeaconsPackage.BUOY__DESIGNATION:
				return DESIGNATION_EDEFAULT == null ? designation != null : !DESIGNATION_EDEFAULT.equals(designation);
			case BuoysandbeaconsPackage.BUOY__SHAPE:
				return shape != SHAPE_EDEFAULT;
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
		result.append(" (designation: ");
		result.append(designation);
		result.append(", shape: ");
		result.append(shape);
		result.append(')');
		return result.toString();
	}

} //BuoyImpl
