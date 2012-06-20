/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.impl;

import java.util.Collection;

import net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWayArtefact;
import net.sf.seesea.model.int1.tracksandroutes.RoundAbout;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Round About</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.impl.RoundAboutImpl#getAttachedSeaWays <em>Attached Sea Ways</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoundAboutImpl extends AbstractSeaWayArtefactImpl implements RoundAbout {
	/**
	 * The cached value of the '{@link #getAttachedSeaWays() <em>Attached Sea Ways</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttachedSeaWays()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractSeaWayArtefact> attachedSeaWays;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoundAboutImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracksandroutesPackage.Literals.ROUND_ABOUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractSeaWayArtefact> getAttachedSeaWays() {
		if (attachedSeaWays == null) {
			attachedSeaWays = new EObjectResolvingEList<AbstractSeaWayArtefact>(AbstractSeaWayArtefact.class, this, TracksandroutesPackage.ROUND_ABOUT__ATTACHED_SEA_WAYS);
		}
		return attachedSeaWays;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracksandroutesPackage.ROUND_ABOUT__ATTACHED_SEA_WAYS:
				return getAttachedSeaWays();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracksandroutesPackage.ROUND_ABOUT__ATTACHED_SEA_WAYS:
				getAttachedSeaWays().clear();
				getAttachedSeaWays().addAll((Collection<? extends AbstractSeaWayArtefact>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TracksandroutesPackage.ROUND_ABOUT__ATTACHED_SEA_WAYS:
				getAttachedSeaWays().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TracksandroutesPackage.ROUND_ABOUT__ATTACHED_SEA_WAYS:
				return attachedSeaWays != null && !attachedSeaWays.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RoundAboutImpl
