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
package net.sf.seesea.model.core.physx.impl;

import java.util.Map;
import net.sf.seesea.model.core.physx.*;

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
public class PhysxFactoryImpl extends EFactoryImpl implements PhysxFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PhysxFactory init() {
		try {
			PhysxFactory thePhysxFactory = (PhysxFactory)EPackage.Registry.INSTANCE.getEFactory("physx"); 
			if (thePhysxFactory != null) {
				return thePhysxFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PhysxFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysxFactoryImpl() {
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
			case PhysxPackage.TEMPERATURE: return createTemperature();
			case PhysxPackage.SPEED: return createSpeed();
			case PhysxPackage.HEADING: return createHeading();
			case PhysxPackage.SHIP_MOVEMENT_VECTOR: return createShipMovementVector();
			case PhysxPackage.RELATIVE_WIND: return createRelativeWind();
			case PhysxPackage.SATELLITE_INFO: return createSatelliteInfo();
			case PhysxPackage.SATELLITES_VISIBLE: return createSatellitesVisible();
			case PhysxPackage.HEADING2_DEGREES_ENTRY: return (EObject)createHeading2DegreesEntry();
			case PhysxPackage.SPEED_TYPE2_SPEED_ENTRY: return (EObject)createSpeedType2SpeedEntry();
			case PhysxPackage.SPEED_MAP: return createSpeedMap();
			case PhysxPackage.TIME: return createTime();
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
			case PhysxPackage.TEMPERATURE_UNIT:
				return createTemperatureUnitFromString(eDataType, initialValue);
			case PhysxPackage.HEADING_TYPE:
				return createHeadingTypeFromString(eDataType, initialValue);
			case PhysxPackage.SPEED_UNIT:
				return createSpeedUnitFromString(eDataType, initialValue);
			case PhysxPackage.HAND_ORIENTATION:
				return createHandOrientationFromString(eDataType, initialValue);
			case PhysxPackage.LENGTH_UNIT:
				return createLengthUnitFromString(eDataType, initialValue);
			case PhysxPackage.SPEED_TYPE:
				return createSpeedTypeFromString(eDataType, initialValue);
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
			case PhysxPackage.TEMPERATURE_UNIT:
				return convertTemperatureUnitToString(eDataType, instanceValue);
			case PhysxPackage.HEADING_TYPE:
				return convertHeadingTypeToString(eDataType, instanceValue);
			case PhysxPackage.SPEED_UNIT:
				return convertSpeedUnitToString(eDataType, instanceValue);
			case PhysxPackage.HAND_ORIENTATION:
				return convertHandOrientationToString(eDataType, instanceValue);
			case PhysxPackage.LENGTH_UNIT:
				return convertLengthUnitToString(eDataType, instanceValue);
			case PhysxPackage.SPEED_TYPE:
				return convertSpeedTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Temperature createTemperature() {
		TemperatureImpl temperature = new TemperatureImpl();
		return temperature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Speed createSpeed() {
		SpeedImpl speed = new SpeedImpl();
		return speed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Heading createHeading() {
		HeadingImpl heading = new HeadingImpl();
		return heading;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShipMovementVector createShipMovementVector() {
		ShipMovementVectorImpl shipMovementVector = new ShipMovementVectorImpl();
		return shipMovementVector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelativeWind createRelativeWind() {
		RelativeWindImpl relativeWind = new RelativeWindImpl();
		return relativeWind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SatelliteInfo createSatelliteInfo() {
		SatelliteInfoImpl satelliteInfo = new SatelliteInfoImpl();
		return satelliteInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SatellitesVisible createSatellitesVisible() {
		SatellitesVisibleImpl satellitesVisible = new SatellitesVisibleImpl();
		return satellitesVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<HeadingType, Double> createHeading2DegreesEntry() {
		Heading2DegreesEntryImpl heading2DegreesEntry = new Heading2DegreesEntryImpl();
		return heading2DegreesEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<SpeedType, Speed> createSpeedType2SpeedEntry() {
		SpeedType2SpeedEntryImpl speedType2SpeedEntry = new SpeedType2SpeedEntryImpl();
		return speedType2SpeedEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpeedMap createSpeedMap() {
		SpeedMapImpl speedMap = new SpeedMapImpl();
		return speedMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Time createTime() {
		TimeImpl time = new TimeImpl();
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemperatureUnit createTemperatureUnitFromString(EDataType eDataType, String initialValue) {
		TemperatureUnit result = TemperatureUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTemperatureUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeadingType createHeadingTypeFromString(EDataType eDataType, String initialValue) {
		HeadingType result = HeadingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHeadingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpeedUnit createSpeedUnitFromString(EDataType eDataType, String initialValue) {
		SpeedUnit result = SpeedUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSpeedUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HandOrientation createHandOrientationFromString(EDataType eDataType, String initialValue) {
		HandOrientation result = HandOrientation.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHandOrientationToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LengthUnit createLengthUnitFromString(EDataType eDataType, String initialValue) {
		LengthUnit result = LengthUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLengthUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpeedType createSpeedTypeFromString(EDataType eDataType, String initialValue) {
		SpeedType result = SpeedType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSpeedTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysxPackage getPhysxPackage() {
		return (PhysxPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PhysxPackage getPackage() {
		return PhysxPackage.eINSTANCE;
	}

} //PhysxFactoryImpl
