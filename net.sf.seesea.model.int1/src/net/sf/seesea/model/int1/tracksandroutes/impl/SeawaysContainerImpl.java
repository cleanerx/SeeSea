/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes.impl;

import net.sf.seesea.model.core.impl.ModelObjectImpl;

import net.sf.seesea.model.int1.tracksandroutes.AbstractSeaWayArtefact;
import net.sf.seesea.model.int1.tracksandroutes.SeawaysContainer;
import net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Seaways Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.impl.SeawaysContainerImpl#getSeaways <em>Seaways</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SeawaysContainerImpl extends ModelObjectImpl implements SeawaysContainer {
	/**
	 * The cached value of the '{@link #getSeaways() <em>Seaways</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeaways()
	 * @generated
	 * @ordered
	 */
	protected AbstractSeaWayArtefact seaways;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SeawaysContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracksandroutesPackage.Literals.SEAWAYS_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractSeaWayArtefact getSeaways() {
		return seaways;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSeaways(AbstractSeaWayArtefact newSeaways, NotificationChain msgs) {
		AbstractSeaWayArtefact oldSeaways = seaways;
		seaways = newSeaways;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS, oldSeaways, newSeaways);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeaways(AbstractSeaWayArtefact newSeaways) {
		if (newSeaways != seaways) {
			NotificationChain msgs = null;
			if (seaways != null)
				msgs = ((InternalEObject)seaways).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS, null, msgs);
			if (newSeaways != null)
				msgs = ((InternalEObject)newSeaways).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS, null, msgs);
			msgs = basicSetSeaways(newSeaways, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS, newSeaways, newSeaways));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS:
				return basicSetSeaways(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS:
				return getSeaways();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS:
				setSeaways((AbstractSeaWayArtefact)newValue);
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
			case TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS:
				setSeaways((AbstractSeaWayArtefact)null);
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
			case TracksandroutesPackage.SEAWAYS_CONTAINER__SEAWAYS:
				return seaways != null;
		}
		return super.eIsSet(featureID);
	}

} //SeawaysContainerImpl
