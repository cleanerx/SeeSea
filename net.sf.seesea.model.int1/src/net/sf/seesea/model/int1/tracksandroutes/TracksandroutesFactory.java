/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage
 * @generated
 */
public interface TracksandroutesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracksandroutesFactory eINSTANCE = net.sf.seesea.model.int1.tracksandroutes.impl.TracksandroutesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Traffic Separation Scheme</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Traffic Separation Scheme</em>'.
	 * @generated
	 */
	TrafficSeparationScheme createTrafficSeparationScheme();

	/**
	 * Returns a new object of class '<em>Traffic Separation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Traffic Separation</em>'.
	 * @generated
	 */
	TrafficSeparation createTrafficSeparation();

	/**
	 * Returns a new object of class '<em>Deep Water Route</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deep Water Route</em>'.
	 * @generated
	 */
	DeepWaterRoute createDeepWaterRoute();

	/**
	 * Returns a new object of class '<em>Round About</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Round About</em>'.
	 * @generated
	 */
	RoundAbout createRoundAbout();

	/**
	 * Returns a new object of class '<em>Area To Be Avoided</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Area To Be Avoided</em>'.
	 * @generated
	 */
	AreaToBeAvoided createAreaToBeAvoided();

	/**
	 * Returns a new object of class '<em>Seaways Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Seaways Container</em>'.
	 * @generated
	 */
	SeawaysContainer createSeawaysContainer();

	/**
	 * Returns a new object of class '<em>Inshore Traffic Zone</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inshore Traffic Zone</em>'.
	 * @generated
	 */
	InshoreTrafficZone createInshoreTrafficZone();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TracksandroutesPackage getTracksandroutesPackage();

} //TracksandroutesFactory
