/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.util;

import java.util.List;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.NamedArtifact;
import net.sf.seesea.model.core.geo.NamedPosition;

import net.sf.seesea.model.int1.base.AbstractSeamark;

import net.sf.seesea.model.int1.buoysandbeacons.*;

import net.sf.seesea.model.int1.lights.LightCharacter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

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
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage
 * @generated
 */
public class BuoysandbeaconsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BuoysandbeaconsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuoysandbeaconsSwitch() {
		if (modelPackage == null) {
			modelPackage = BuoysandbeaconsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON: {
				AbstractCommonBuoyBeacon abstractCommonBuoyBeacon = (AbstractCommonBuoyBeacon)theEObject;
				T result = caseAbstractCommonBuoyBeacon(abstractCommonBuoyBeacon);
				if (result == null) result = caseLightCharacter(abstractCommonBuoyBeacon);
				if (result == null) result = caseAbstractSeamark(abstractCommonBuoyBeacon);
				if (result == null) result = caseNamedPosition(abstractCommonBuoyBeacon);
				if (result == null) result = caseGeoPosition(abstractCommonBuoyBeacon);
				if (result == null) result = caseNamedArtifact(abstractCommonBuoyBeacon);
				if (result == null) result = caseModelObject(abstractCommonBuoyBeacon);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BuoysandbeaconsPackage.BUOY: {
				Buoy buoy = (Buoy)theEObject;
				T result = caseBuoy(buoy);
				if (result == null) result = caseAbstractCommonBuoyBeacon(buoy);
				if (result == null) result = caseLightCharacter(buoy);
				if (result == null) result = caseAbstractSeamark(buoy);
				if (result == null) result = caseNamedPosition(buoy);
				if (result == null) result = caseGeoPosition(buoy);
				if (result == null) result = caseNamedArtifact(buoy);
				if (result == null) result = caseModelObject(buoy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BuoysandbeaconsPackage.BEACON: {
				Beacon beacon = (Beacon)theEObject;
				T result = caseBeacon(beacon);
				if (result == null) result = caseAbstractCommonBuoyBeacon(beacon);
				if (result == null) result = caseLightCharacter(beacon);
				if (result == null) result = caseAbstractSeamark(beacon);
				if (result == null) result = caseNamedPosition(beacon);
				if (result == null) result = caseGeoPosition(beacon);
				if (result == null) result = caseNamedArtifact(beacon);
				if (result == null) result = caseModelObject(beacon);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BuoysandbeaconsPackage.SPECIAL_BUOY: {
				SpecialBuoy specialBuoy = (SpecialBuoy)theEObject;
				T result = caseSpecialBuoy(specialBuoy);
				if (result == null) result = caseBuoy(specialBuoy);
				if (result == null) result = caseAbstractCommonBuoyBeacon(specialBuoy);
				if (result == null) result = caseLightCharacter(specialBuoy);
				if (result == null) result = caseAbstractSeamark(specialBuoy);
				if (result == null) result = caseNamedPosition(specialBuoy);
				if (result == null) result = caseGeoPosition(specialBuoy);
				if (result == null) result = caseNamedArtifact(specialBuoy);
				if (result == null) result = caseModelObject(specialBuoy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BuoysandbeaconsPackage.TOPMARK: {
				Topmark topmark = (Topmark)theEObject;
				T result = caseTopmark(topmark);
				if (result == null) result = caseModelObject(topmark);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Common Buoy Beacon</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Common Buoy Beacon</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractCommonBuoyBeacon(AbstractCommonBuoyBeacon object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Buoy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Buoy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuoy(Buoy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Beacon</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Beacon</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBeacon(Beacon object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Special Buoy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Special Buoy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecialBuoy(SpecialBuoy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Topmark</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Topmark</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTopmark(Topmark object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Light Character</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Light Character</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLightCharacter(LightCharacter object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Seamark</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Seamark</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractSeamark(AbstractSeamark object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeoPosition(GeoPosition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Artifact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedArtifact(NamedArtifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedPosition(NamedPosition object) {
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
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //BuoysandbeaconsSwitch
