/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.util;

import net.sf.seesea.model.core.ModelObject;

import net.sf.seesea.model.core.geo.ChartArea;

import net.sf.seesea.model.int1.tracksandroutes.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage
 * @generated
 */
public class TracksandroutesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TracksandroutesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracksandroutesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TracksandroutesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TracksandroutesSwitch<Adapter> modelSwitch =
		new TracksandroutesSwitch<Adapter>() {
			@Override
			public Adapter caseAbstractSeaWayArtefact(AbstractSeaWayArtefact object) {
				return createAbstractSeaWayArtefactAdapter();
			}
			@Override
			public Adapter caseTrafficSeparationScheme(TrafficSeparationScheme object) {
				return createTrafficSeparationSchemeAdapter();
			}
			@Override
			public Adapter caseTrafficSeparation(TrafficSeparation object) {
				return createTrafficSeparationAdapter();
			}
			@Override
			public Adapter caseDeepWaterRoute(DeepWaterRoute object) {
				return createDeepWaterRouteAdapter();
			}
			@Override
			public Adapter caseRoundAbout(RoundAbout object) {
				return createRoundAboutAdapter();
			}
			@Override
			public Adapter caseAreaToBeAvoided(AreaToBeAvoided object) {
				return createAreaToBeAvoidedAdapter();
			}
			@Override
			public Adapter caseSeawaysContainer(SeawaysContainer object) {
				return createSeawaysContainerAdapter();
			}
			@Override
			public Adapter caseInshoreTrafficZone(InshoreTrafficZone object) {
				return createInshoreTrafficZoneAdapter();
			}
			@Override
			public Adapter caseAbstractSeaWay(AbstractSeaWay object) {
				return createAbstractSeaWayAdapter();
			}
			@Override
			public Adapter caseModelObject(ModelObject object) {
				return createModelObjectAdapter();
			}
			@Override
			public Adapter caseChartArea(ChartArea object) {
				return createChartAreaAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWayArtefact <em>Abstract Sea Way Artefact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWayArtefact
	 * @generated
	 */
	public Adapter createAbstractSeaWayArtefactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme <em>Traffic Separation Scheme</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme
	 * @generated
	 */
	public Adapter createTrafficSeparationSchemeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation <em>Traffic Separation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation
	 * @generated
	 */
	public Adapter createTrafficSeparationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute <em>Deep Water Route</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute
	 * @generated
	 */
	public Adapter createDeepWaterRouteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.RoundAbout <em>Round About</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.RoundAbout
	 * @generated
	 */
	public Adapter createRoundAboutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.AreaToBeAvoided <em>Area To Be Avoided</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.AreaToBeAvoided
	 * @generated
	 */
	public Adapter createAreaToBeAvoidedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer <em>Seaways Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer
	 * @generated
	 */
	public Adapter createSeawaysContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.InshoreTrafficZone <em>Inshore Traffic Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.InshoreTrafficZone
	 * @generated
	 */
	public Adapter createInshoreTrafficZoneAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWay <em>Abstract Sea Way</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWay
	 * @generated
	 */
	public Adapter createAbstractSeaWayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.ModelObject <em>Model Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.ModelObject
	 * @generated
	 */
	public Adapter createModelObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.ChartArea <em>Chart Area</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.ChartArea
	 * @generated
	 */
	public Adapter createChartAreaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TracksandroutesAdapterFactory
