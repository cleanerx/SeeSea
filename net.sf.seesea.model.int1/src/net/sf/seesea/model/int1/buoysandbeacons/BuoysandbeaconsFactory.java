/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage
 * @generated
 */
public interface BuoysandbeaconsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BuoysandbeaconsFactory eINSTANCE = net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Buoy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Buoy</em>'.
	 * @generated
	 */
	Buoy createBuoy();

	/**
	 * Returns a new object of class '<em>Beacon</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Beacon</em>'.
	 * @generated
	 */
	Beacon createBeacon();

	/**
	 * Returns a new object of class '<em>Special Buoy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Special Buoy</em>'.
	 * @generated
	 */
	SpecialBuoy createSpecialBuoy();

	/**
	 * Returns a new object of class '<em>Topmark</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Topmark</em>'.
	 * @generated
	 */
	Topmark createTopmark();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BuoysandbeaconsPackage getBuoysandbeaconsPackage();

} //BuoysandbeaconsFactory
