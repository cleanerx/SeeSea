/**
 * <copyright>
Copyright (c) 2010-2012, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.core.physx.util;

import java.util.List;

import java.util.Map;
import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.physx.*;

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
 * @see net.sf.seesea.model.core.physx.PhysxPackage
 * @generated
 */
public class PhysxSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PhysxPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysxSwitch() {
		if (modelPackage == null) {
			modelPackage = PhysxPackage.eINSTANCE;
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
			case PhysxPackage.TEMPERATURE: {
				Temperature temperature = (Temperature)theEObject;
				T result = caseTemperature(temperature);
				if (result == null) result = caseMeasurement(temperature);
				if (result == null) result = caseModelObject(temperature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.SPEED: {
				Speed speed = (Speed)theEObject;
				T result = caseSpeed(speed);
				if (result == null) result = caseMeasurement(speed);
				if (result == null) result = caseModelObject(speed);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.HEADING: {
				Heading heading = (Heading)theEObject;
				T result = caseHeading(heading);
				if (result == null) result = caseMeasurement(heading);
				if (result == null) result = caseModelObject(heading);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.MEASUREMENT: {
				Measurement measurement = (Measurement)theEObject;
				T result = caseMeasurement(measurement);
				if (result == null) result = caseModelObject(measurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.SHIP_MOVEMENT_VECTOR: {
				ShipMovementVector shipMovementVector = (ShipMovementVector)theEObject;
				T result = caseShipMovementVector(shipMovementVector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.RELATIVE_WIND: {
				RelativeWind relativeWind = (RelativeWind)theEObject;
				T result = caseRelativeWind(relativeWind);
				if (result == null) result = caseHeading(relativeWind);
				if (result == null) result = caseSpeed(relativeWind);
				if (result == null) result = caseMeasurement(relativeWind);
				if (result == null) result = caseModelObject(relativeWind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.SATELLITE_INFO: {
				SatelliteInfo satelliteInfo = (SatelliteInfo)theEObject;
				T result = caseSatelliteInfo(satelliteInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.SATELLITES_VISIBLE: {
				SatellitesVisible satellitesVisible = (SatellitesVisible)theEObject;
				T result = caseSatellitesVisible(satellitesVisible);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.HEADING2_DEGREES_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<HeadingType, Double> heading2DegreesEntry = (Map.Entry<HeadingType, Double>)theEObject;
				T result = caseHeading2DegreesEntry(heading2DegreesEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.SPEED_TYPE2_SPEED_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<SpeedType, Speed> speedType2SpeedEntry = (Map.Entry<SpeedType, Speed>)theEObject;
				T result = caseSpeedType2SpeedEntry(speedType2SpeedEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.SPEED_MAP: {
				SpeedMap speedMap = (SpeedMap)theEObject;
				T result = caseSpeedMap(speedMap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PhysxPackage.TIME: {
				Time time = (Time)theEObject;
				T result = caseTime(time);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Temperature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Temperature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemperature(Temperature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Speed</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Speed</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpeed(Speed object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Heading</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Heading</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHeading(Heading object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurement(Measurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ship Movement Vector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ship Movement Vector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShipMovementVector(ShipMovementVector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relative Wind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relative Wind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelativeWind(RelativeWind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Satellite Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Satellite Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSatelliteInfo(SatelliteInfo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Satellites Visible</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Satellites Visible</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSatellitesVisible(SatellitesVisible object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Heading2 Degrees Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Heading2 Degrees Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHeading2DegreesEntry(Map.Entry<HeadingType, Double> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Speed Type2 Speed Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Speed Type2 Speed Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpeedType2SpeedEntry(Map.Entry<SpeedType, Speed> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Speed Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Speed Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpeedMap(SpeedMap object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTime(Time object) {
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

} //PhysxSwitch
