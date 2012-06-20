/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.natureofseabed;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage
 * @generated
 */
public interface NatureofseabedFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NatureofseabedFactory eINSTANCE = net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Spring In Seabed</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Spring In Seabed</em>'.
	 * @generated
	 */
	SpringInSeabed createSpringInSeabed();

	/**
	 * Returns a new object of class '<em>Nature Of Seabed</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nature Of Seabed</em>'.
	 * @generated
	 */
	NatureOfSeabed createNatureOfSeabed();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	NatureofseabedPackage getNatureofseabedPackage();

} //NatureofseabedFactory
