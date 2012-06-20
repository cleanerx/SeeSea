/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.lights.impl;

import java.util.Collection;

import net.sf.seesea.model.int1.lights.Color;
import net.sf.seesea.model.int1.lights.LightCharacter;
import net.sf.seesea.model.int1.lights.LightsPackage;
import net.sf.seesea.model.int1.lights.PhaseCharacteristic;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Light Character</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.lights.impl.LightCharacterImpl#getLightcolor <em>Lightcolor</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.lights.impl.LightCharacterImpl#getPeriod <em>Period</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.lights.impl.LightCharacterImpl#getPhaseCharacteristic <em>Phase Characteristic</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.lights.impl.LightCharacterImpl#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LightCharacterImpl extends EObjectImpl implements LightCharacter {
	/**
	 * The cached value of the '{@link #getLightcolor() <em>Lightcolor</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLightcolor()
	 * @generated
	 * @ordered
	 */
	protected EList<Color> lightcolor;

	/**
	 * The default value of the '{@link #getPeriod() <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriod()
	 * @generated
	 * @ordered
	 */
	protected static final int PERIOD_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPeriod() <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriod()
	 * @generated
	 * @ordered
	 */
	protected int period = PERIOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhaseCharacteristic() <em>Phase Characteristic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseCharacteristic()
	 * @generated
	 * @ordered
	 */
	protected static final PhaseCharacteristic PHASE_CHARACTERISTIC_EDEFAULT = PhaseCharacteristic.UNKNOWN;

	/**
	 * The cached value of the '{@link #getPhaseCharacteristic() <em>Phase Characteristic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhaseCharacteristic()
	 * @generated
	 * @ordered
	 */
	protected PhaseCharacteristic phaseCharacteristic = PHASE_CHARACTERISTIC_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> group;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LightCharacterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LightsPackage.Literals.LIGHT_CHARACTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Color> getLightcolor() {
		if (lightcolor == null) {
			lightcolor = new EDataTypeUniqueEList<Color>(Color.class, this, LightsPackage.LIGHT_CHARACTER__LIGHTCOLOR);
		}
		return lightcolor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPeriod(int newPeriod) {
		int oldPeriod = period;
		period = newPeriod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LightsPackage.LIGHT_CHARACTER__PERIOD, oldPeriod, period));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhaseCharacteristic getPhaseCharacteristic() {
		return phaseCharacteristic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhaseCharacteristic(PhaseCharacteristic newPhaseCharacteristic) {
		PhaseCharacteristic oldPhaseCharacteristic = phaseCharacteristic;
		phaseCharacteristic = newPhaseCharacteristic == null ? PHASE_CHARACTERISTIC_EDEFAULT : newPhaseCharacteristic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LightsPackage.LIGHT_CHARACTER__PHASE_CHARACTERISTIC, oldPhaseCharacteristic, phaseCharacteristic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getGroup() {
		if (group == null) {
			group = new EDataTypeUniqueEList<Integer>(Integer.class, this, LightsPackage.LIGHT_CHARACTER__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LightsPackage.LIGHT_CHARACTER__LIGHTCOLOR:
				return getLightcolor();
			case LightsPackage.LIGHT_CHARACTER__PERIOD:
				return getPeriod();
			case LightsPackage.LIGHT_CHARACTER__PHASE_CHARACTERISTIC:
				return getPhaseCharacteristic();
			case LightsPackage.LIGHT_CHARACTER__GROUP:
				return getGroup();
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
			case LightsPackage.LIGHT_CHARACTER__LIGHTCOLOR:
				getLightcolor().clear();
				getLightcolor().addAll((Collection<? extends Color>)newValue);
				return;
			case LightsPackage.LIGHT_CHARACTER__PERIOD:
				setPeriod((Integer)newValue);
				return;
			case LightsPackage.LIGHT_CHARACTER__PHASE_CHARACTERISTIC:
				setPhaseCharacteristic((PhaseCharacteristic)newValue);
				return;
			case LightsPackage.LIGHT_CHARACTER__GROUP:
				getGroup().clear();
				getGroup().addAll((Collection<? extends Integer>)newValue);
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
			case LightsPackage.LIGHT_CHARACTER__LIGHTCOLOR:
				getLightcolor().clear();
				return;
			case LightsPackage.LIGHT_CHARACTER__PERIOD:
				setPeriod(PERIOD_EDEFAULT);
				return;
			case LightsPackage.LIGHT_CHARACTER__PHASE_CHARACTERISTIC:
				setPhaseCharacteristic(PHASE_CHARACTERISTIC_EDEFAULT);
				return;
			case LightsPackage.LIGHT_CHARACTER__GROUP:
				getGroup().clear();
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
			case LightsPackage.LIGHT_CHARACTER__LIGHTCOLOR:
				return lightcolor != null && !lightcolor.isEmpty();
			case LightsPackage.LIGHT_CHARACTER__PERIOD:
				return period != PERIOD_EDEFAULT;
			case LightsPackage.LIGHT_CHARACTER__PHASE_CHARACTERISTIC:
				return phaseCharacteristic != PHASE_CHARACTERISTIC_EDEFAULT;
			case LightsPackage.LIGHT_CHARACTER__GROUP:
				return group != null && !group.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (lightcolor: ");
		result.append(lightcolor);
		result.append(", period: ");
		result.append(period);
		result.append(", phaseCharacteristic: ");
		result.append(phaseCharacteristic);
		result.append(", group: ");
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //LightCharacterImpl
