/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.util;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.NamedArtifact;
import net.sf.seesea.model.core.geo.NamedPosition;

import net.sf.seesea.model.int1.base.AbstractSeamark;

import net.sf.seesea.model.int1.buoysandbeacons.*;

import net.sf.seesea.model.int1.lights.LightCharacter;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage
 * @generated
 */
public class BuoysandbeaconsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BuoysandbeaconsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuoysandbeaconsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BuoysandbeaconsPackage.eINSTANCE;
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
	protected BuoysandbeaconsSwitch<Adapter> modelSwitch =
		new BuoysandbeaconsSwitch<Adapter>() {
			@Override
			public Adapter caseAbstractCommonBuoyBeacon(AbstractCommonBuoyBeacon object) {
				return createAbstractCommonBuoyBeaconAdapter();
			}
			@Override
			public Adapter caseBuoy(Buoy object) {
				return createBuoyAdapter();
			}
			@Override
			public Adapter caseBeacon(Beacon object) {
				return createBeaconAdapter();
			}
			@Override
			public Adapter caseSpecialBuoy(SpecialBuoy object) {
				return createSpecialBuoyAdapter();
			}
			@Override
			public Adapter caseTopmark(Topmark object) {
				return createTopmarkAdapter();
			}
			@Override
			public Adapter caseLightCharacter(LightCharacter object) {
				return createLightCharacterAdapter();
			}
			@Override
			public Adapter caseModelObject(ModelObject object) {
				return createModelObjectAdapter();
			}
			@Override
			public Adapter caseGeoPosition(GeoPosition object) {
				return createGeoPositionAdapter();
			}
			@Override
			public Adapter caseAbstractSeamark(AbstractSeamark object) {
				return createAbstractSeamarkAdapter();
			}
			@Override
			public Adapter caseNamedArtifact(NamedArtifact object) {
				return createNamedArtifactAdapter();
			}
			@Override
			public Adapter caseNamedPosition(NamedPosition object) {
				return createNamedPositionAdapter();
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
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon <em>Abstract Common Buoy Beacon</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon
	 * @generated
	 */
	public Adapter createAbstractCommonBuoyBeaconAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.buoysandbeacons.Buoy <em>Buoy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Buoy
	 * @generated
	 */
	public Adapter createBuoyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.buoysandbeacons.Beacon <em>Beacon</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Beacon
	 * @generated
	 */
	public Adapter createBeaconAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy <em>Special Buoy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy
	 * @generated
	 */
	public Adapter createSpecialBuoyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.buoysandbeacons.Topmark <em>Topmark</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Topmark
	 * @generated
	 */
	public Adapter createTopmarkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.lights.LightCharacter <em>Light Character</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.lights.LightCharacter
	 * @generated
	 */
	public Adapter createLightCharacterAdapter() {
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
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.int1.base.AbstractSeamark <em>Abstract Seamark</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.int1.base.AbstractSeamark
	 * @generated
	 */
	public Adapter createAbstractSeamarkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.GeoPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.GeoPosition
	 * @generated
	 */
	public Adapter createGeoPositionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.NamedArtifact <em>Named Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.NamedArtifact
	 * @generated
	 */
	public Adapter createNamedArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.seesea.model.core.geo.NamedPosition <em>Named Position</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.seesea.model.core.geo.NamedPosition
	 * @generated
	 */
	public Adapter createNamedPositionAdapter() {
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

} //BuoysandbeaconsAdapterFactory
