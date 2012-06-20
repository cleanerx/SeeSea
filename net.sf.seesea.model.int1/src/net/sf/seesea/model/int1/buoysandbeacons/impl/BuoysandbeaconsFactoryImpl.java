/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.impl;

import net.sf.seesea.model.int1.buoysandbeacons.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BuoysandbeaconsFactoryImpl extends EFactoryImpl implements BuoysandbeaconsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BuoysandbeaconsFactory init() {
		try {
			BuoysandbeaconsFactory theBuoysandbeaconsFactory = (BuoysandbeaconsFactory)EPackage.Registry.INSTANCE.getEFactory("buoysandbeacons"); 
			if (theBuoysandbeaconsFactory != null) {
				return theBuoysandbeaconsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BuoysandbeaconsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuoysandbeaconsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BuoysandbeaconsPackage.BUOY: return createBuoy();
			case BuoysandbeaconsPackage.BEACON: return createBeacon();
			case BuoysandbeaconsPackage.SPECIAL_BUOY: return createSpecialBuoy();
			case BuoysandbeaconsPackage.TOPMARK: return createTopmark();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case BuoysandbeaconsPackage.BUOY_TYPE:
				return createBuoyTypeFromString(eDataType, initialValue);
			case BuoysandbeaconsPackage.SHAPE:
				return createShapeFromString(eDataType, initialValue);
			case BuoysandbeaconsPackage.TOPMARK_TYPE:
				return createTopmarkTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case BuoysandbeaconsPackage.BUOY_TYPE:
				return convertBuoyTypeToString(eDataType, instanceValue);
			case BuoysandbeaconsPackage.SHAPE:
				return convertShapeToString(eDataType, instanceValue);
			case BuoysandbeaconsPackage.TOPMARK_TYPE:
				return convertTopmarkTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Buoy createBuoy() {
		BuoyImpl buoy = new BuoyImpl();
		return buoy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Beacon createBeacon() {
		BeaconImpl beacon = new BeaconImpl();
		return beacon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecialBuoy createSpecialBuoy() {
		SpecialBuoyImpl specialBuoy = new SpecialBuoyImpl();
		return specialBuoy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Topmark createTopmark() {
		TopmarkImpl topmark = new TopmarkImpl();
		return topmark;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuoyType createBuoyTypeFromString(EDataType eDataType, String initialValue) {
		BuoyType result = BuoyType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBuoyTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shape createShapeFromString(EDataType eDataType, String initialValue) {
		Shape result = Shape.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShapeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopmarkType createTopmarkTypeFromString(EDataType eDataType, String initialValue) {
		TopmarkType result = TopmarkType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTopmarkTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuoysandbeaconsPackage getBuoysandbeaconsPackage() {
		return (BuoysandbeaconsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BuoysandbeaconsPackage getPackage() {
		return BuoysandbeaconsPackage.eINSTANCE;
	}

} //BuoysandbeaconsFactoryImpl
