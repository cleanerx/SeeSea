/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.base;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.base.BasePackage
 * @generated
 */
public interface BaseFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BaseFactory eINSTANCE = net.sf.seesea.model.int1.base.impl.BaseFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Marine Chart</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Marine Chart</em>'.
	 * @generated
	 */
	MarineChart createMarineChart();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BasePackage getBasePackage();

} //BaseFactory
