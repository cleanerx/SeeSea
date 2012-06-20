/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.impl;

import net.sf.seesea.model.int1.tracksandroutes.*;

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
public class TracksandroutesFactoryImpl extends EFactoryImpl implements TracksandroutesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TracksandroutesFactory init() {
		try {
			TracksandroutesFactory theTracksandroutesFactory = (TracksandroutesFactory)EPackage.Registry.INSTANCE.getEFactory("tracksandroutes"); 
			if (theTracksandroutesFactory != null) {
				return theTracksandroutesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TracksandroutesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracksandroutesFactoryImpl() {
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
			case TracksandroutesPackage.TRAFFIC_SEPARATION_SCHEME: return createTrafficSeparationScheme();
			case TracksandroutesPackage.TRAFFIC_SEPARATION: return createTrafficSeparation();
			case TracksandroutesPackage.DEEP_WATER_ROUTE: return createDeepWaterRoute();
			case TracksandroutesPackage.ROUND_ABOUT: return createRoundAbout();
			case TracksandroutesPackage.AREA_TO_BE_AVOIDED: return createAreaToBeAvoided();
			case TracksandroutesPackage.SEAWAYS_CONTAINER: return createSeawaysContainer();
			case TracksandroutesPackage.INSHORE_TRAFFIC_ZONE: return createInshoreTrafficZone();
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
			case TracksandroutesPackage.BORDER_TYPE:
				return createBorderTypeFromString(eDataType, initialValue);
			case TracksandroutesPackage.TRAFFIC_DIRECTION:
				return createTrafficDirectionFromString(eDataType, initialValue);
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
			case TracksandroutesPackage.BORDER_TYPE:
				return convertBorderTypeToString(eDataType, instanceValue);
			case TracksandroutesPackage.TRAFFIC_DIRECTION:
				return convertTrafficDirectionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrafficSeparationScheme createTrafficSeparationScheme() {
		TrafficSeparationSchemeImpl trafficSeparationScheme = new TrafficSeparationSchemeImpl();
		return trafficSeparationScheme;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrafficSeparation createTrafficSeparation() {
		TrafficSeparationImpl trafficSeparation = new TrafficSeparationImpl();
		return trafficSeparation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeepWaterRoute createDeepWaterRoute() {
		DeepWaterRouteImpl deepWaterRoute = new DeepWaterRouteImpl();
		return deepWaterRoute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoundAbout createRoundAbout() {
		RoundAboutImpl roundAbout = new RoundAboutImpl();
		return roundAbout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AreaToBeAvoided createAreaToBeAvoided() {
		AreaToBeAvoidedImpl areaToBeAvoided = new AreaToBeAvoidedImpl();
		return areaToBeAvoided;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeawaysContainer createSeawaysContainer() {
		SeawaysContainerImpl seawaysContainer = new SeawaysContainerImpl();
		return seawaysContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InshoreTrafficZone createInshoreTrafficZone() {
		InshoreTrafficZoneImpl inshoreTrafficZone = new InshoreTrafficZoneImpl();
		return inshoreTrafficZone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BorderType createBorderTypeFromString(EDataType eDataType, String initialValue) {
		BorderType result = BorderType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBorderTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrafficDirection createTrafficDirectionFromString(EDataType eDataType, String initialValue) {
		TrafficDirection result = TrafficDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTrafficDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracksandroutesPackage getTracksandroutesPackage() {
		return (TracksandroutesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TracksandroutesPackage getPackage() {
		return TracksandroutesPackage.eINSTANCE;
	}

} //TracksandroutesFactoryImpl
