/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.util;

import java.util.List;

import net.sf.seesea.model.core.ModelObject;

import net.sf.seesea.model.core.geo.ChartArea;

import net.sf.seesea.model.int1.tracksandroutes.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage
 * @generated
 */
public class TracksandroutesSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TracksandroutesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracksandroutesSwitch() {
		if (modelPackage == null) {
			modelPackage = TracksandroutesPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TracksandroutesPackage.ABSTRACT_SEA_WAY_ARTEFACT: {
				AbstractSeaWayArtefact abstractSeaWayArtefact = (AbstractSeaWayArtefact)theEObject;
				T result = caseAbstractSeaWayArtefact(abstractSeaWayArtefact);
				if (result == null) result = caseModelObject(abstractSeaWayArtefact);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME: {
				TrafficSeparationScheme trafficSeparationScheme = (TrafficSeparationScheme)theEObject;
				T result = caseTrafficSeparationScheme(trafficSeparationScheme);
				if (result == null) result = caseAbstractSeaWay(trafficSeparationScheme);
				if (result == null) result = caseAbstractSeaWayArtefact(trafficSeparationScheme);
				if (result == null) result = caseModelObject(trafficSeparationScheme);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracksandroutesPackage.TRAFFIC_SEPARATION: {
				TrafficSeparation trafficSeparation = (TrafficSeparation)theEObject;
				T result = caseTrafficSeparation(trafficSeparation);
				if (result == null) result = caseChartArea(trafficSeparation);
				if (result == null) result = caseAbstractSeaWayArtefact(trafficSeparation);
				if (result == null) result = caseModelObject(trafficSeparation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracksandroutesPackage.DEEP_WATER_ROUTE: {
				DeepWaterRoute deepWaterRoute = (DeepWaterRoute)theEObject;
				T result = caseDeepWaterRoute(deepWaterRoute);
				if (result == null) result = caseAbstractSeaWay(deepWaterRoute);
				if (result == null) result = caseAbstractSeaWayArtefact(deepWaterRoute);
				if (result == null) result = caseModelObject(deepWaterRoute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracksandroutesPackage.ROUND_ABOUT: {
				RoundAbout roundAbout = (RoundAbout)theEObject;
				T result = caseRoundAbout(roundAbout);
				if (result == null) result = caseAbstractSeaWayArtefact(roundAbout);
				if (result == null) result = caseModelObject(roundAbout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracksandroutesPackage.AREA_TO_BE_AVOIDED: {
				AreaToBeAvoided areaToBeAvoided = (AreaToBeAvoided)theEObject;
				T result = caseAreaToBeAvoided(areaToBeAvoided);
				if (result == null) result = caseChartArea(areaToBeAvoided);
				if (result == null) result = caseAbstractSeaWayArtefact(areaToBeAvoided);
				if (result == null) result = caseModelObject(areaToBeAvoided);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracksandroutesPackage.SEAWAYS_CONTAINER: {
				SeawaysContainer seawaysContainer = (SeawaysContainer)theEObject;
				T result = caseSeawaysContainer(seawaysContainer);
				if (result == null) result = caseModelObject(seawaysContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracksandroutesPackage.INSHORE_TRAFFIC_ZONE: {
				InshoreTrafficZone inshoreTrafficZone = (InshoreTrafficZone)theEObject;
				T result = caseInshoreTrafficZone(inshoreTrafficZone);
				if (result == null) result = caseChartArea(inshoreTrafficZone);
				if (result == null) result = caseAbstractSeaWayArtefact(inshoreTrafficZone);
				if (result == null) result = caseModelObject(inshoreTrafficZone);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracksandroutesPackage.ABSTRACT_SEA_WAY: {
				AbstractSeaWay abstractSeaWay = (AbstractSeaWay)theEObject;
				T result = caseAbstractSeaWay(abstractSeaWay);
				if (result == null) result = caseAbstractSeaWayArtefact(abstractSeaWay);
				if (result == null) result = caseModelObject(abstractSeaWay);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Sea Way Artefact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Sea Way Artefact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractSeaWayArtefact(AbstractSeaWayArtefact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Traffic Separation Scheme</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Traffic Separation Scheme</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrafficSeparationScheme(TrafficSeparationScheme object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Traffic Separation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Traffic Separation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrafficSeparation(TrafficSeparation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deep Water Route</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deep Water Route</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeepWaterRoute(DeepWaterRoute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Round About</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Round About</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoundAbout(RoundAbout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Area To Be Avoided</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Area To Be Avoided</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAreaToBeAvoided(AreaToBeAvoided object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Seaways Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Seaways Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSeawaysContainer(SeawaysContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Inshore Traffic Zone</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Inshore Traffic Zone</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInshoreTrafficZone(InshoreTrafficZone object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Sea Way</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Sea Way</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractSeaWay(AbstractSeaWay object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelObject(ModelObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chart Area</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chart Area</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChartArea(ChartArea object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //TracksandroutesSwitch
