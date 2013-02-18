/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons.impl;

import java.util.Collection;

import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.NamedArtifact;
import net.sf.seesea.model.core.geo.NamedPosition;
import net.sf.seesea.model.int1.base.AbstractSeamark;
import net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon;
import net.sf.seesea.model.int1.buoysandbeacons.BuoyType;
import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;
import net.sf.seesea.model.int1.buoysandbeacons.Topmark;
import net.sf.seesea.model.int1.buoysandbeacons.TopmarkType;
import net.sf.seesea.model.int1.lights.Color;
import net.sf.seesea.model.int1.lights.Orientation;
import net.sf.seesea.model.int1.lights.impl.LightCharacterImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Common Buoy Beacon</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getLongitude <em>Longitude</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getLatitude <em>Latitude</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getPrecision <em>Precision</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getName <em>Name</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getColor <em>Color</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getColorType <em>Color Type</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#isReflecting <em>Reflecting</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#isRadarreflector <em>Radarreflector</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getType <em>Type</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl#getTopmark <em>Topmark</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractCommonBuoyBeaconImpl extends LightCharacterImpl implements AbstractCommonBuoyBeacon {
	/**
	 * The cached value of the '{@link #getLongitude() <em>Longitude</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongitude()
	 * @generated
	 * @ordered
	 */
	protected Longitude longitude;

	/**
	 * The cached value of the '{@link #getLatitude() <em>Latitude</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLatitude()
	 * @generated
	 * @ordered
	 */
	protected Latitude latitude;

	/**
	 * The default value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected int precision = PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected static final String POSITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected String position = POSITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColor() <em>Color</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected EList<Color> color;

	/**
	 * The default value of the '{@link #getColorType() <em>Color Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorType()
	 * @generated
	 * @ordered
	 */
	protected static final Orientation COLOR_TYPE_EDEFAULT = Orientation.VERTICAL;

	/**
	 * The cached value of the '{@link #getColorType() <em>Color Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorType()
	 * @generated
	 * @ordered
	 */
	protected Orientation colorType = COLOR_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isReflecting() <em>Reflecting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReflecting()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REFLECTING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReflecting() <em>Reflecting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReflecting()
	 * @generated
	 * @ordered
	 */
	protected boolean reflecting = REFLECTING_EDEFAULT;

	/**
	 * The default value of the '{@link #isRadarreflector() <em>Radarreflector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRadarreflector()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RADARREFLECTOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRadarreflector() <em>Radarreflector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRadarreflector()
	 * @generated
	 * @ordered
	 */
	protected boolean radarreflector = RADARREFLECTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final BuoyType TYPE_EDEFAULT = BuoyType.UNKNOWN;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected BuoyType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTopmark() <em>Topmark</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopmark()
	 * @generated
	 * @ordered
	 */
	protected Topmark topmark;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractCommonBuoyBeaconImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BuoysandbeaconsPackage.Literals.ABSTRACT_COMMON_BUOY_BEACON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Longitude getLongitude() {
		return longitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLongitude(Longitude newLongitude, NotificationChain msgs) {
		Longitude oldLongitude = longitude;
		longitude = newLongitude;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE, oldLongitude, newLongitude);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongitude(Longitude newLongitude) {
		if (newLongitude != longitude) {
			NotificationChain msgs = null;
			if (longitude != null)
				msgs = ((InternalEObject)longitude).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE, null, msgs);
			if (newLongitude != null)
				msgs = ((InternalEObject)newLongitude).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE, null, msgs);
			msgs = basicSetLongitude(newLongitude, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE, newLongitude, newLongitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Latitude getLatitude() {
		return latitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLatitude(Latitude newLatitude, NotificationChain msgs) {
		Latitude oldLatitude = latitude;
		latitude = newLatitude;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE, oldLatitude, newLatitude);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLatitude(Latitude newLatitude) {
		if (newLatitude != latitude) {
			NotificationChain msgs = null;
			if (latitude != null)
				msgs = ((InternalEObject)latitude).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE, null, msgs);
			if (newLatitude != null)
				msgs = ((InternalEObject)newLatitude).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE, null, msgs);
			msgs = basicSetLatitude(newLatitude, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE, newLatitude, newLatitude));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecision(int newPrecision) {
		int oldPrecision = precision;
		precision = newPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__PRECISION, oldPrecision, precision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosition(String newPosition) {
		String oldPosition = position;
		position = newPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Color> getColor() {
		if (color == null) {
			color = new EDataTypeEList<Color>(Color.class, this, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR);
		}
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Orientation getColorType() {
		return colorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColorType(Orientation newColorType) {
		Orientation oldColorType = colorType;
		colorType = newColorType == null ? COLOR_TYPE_EDEFAULT : newColorType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE, oldColorType, colorType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReflecting() {
		return reflecting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReflecting(boolean newReflecting) {
		boolean oldReflecting = reflecting;
		reflecting = newReflecting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__REFLECTING, oldReflecting, reflecting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Topmark getTopmark() {
		return topmark;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTopmark(Topmark newTopmark, NotificationChain msgs) {
		Topmark oldTopmark = topmark;
		topmark = newTopmark;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK, oldTopmark, newTopmark);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTopmark(Topmark newTopmark) {
		if (newTopmark != topmark) {
			NotificationChain msgs = null;
			if (topmark != null)
				msgs = ((InternalEObject)topmark).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK, null, msgs);
			if (newTopmark != null)
				msgs = ((InternalEObject)newTopmark).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK, null, msgs);
			msgs = basicSetTopmark(newTopmark, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK, newTopmark, newTopmark));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRadarreflector() {
		return radarreflector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRadarreflector(boolean newRadarreflector) {
		boolean oldRadarreflector = radarreflector;
		radarreflector = newRadarreflector;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR, oldRadarreflector, radarreflector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuoyType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(BuoyType newType) {
		BuoyType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE:
				return basicSetLongitude(null, msgs);
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE:
				return basicSetLatitude(null, msgs);
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK:
				return basicSetTopmark(null, msgs);
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
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE:
				return getLongitude();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE:
				return getLatitude();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__PRECISION:
				return getPrecision();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__NAME:
				return getName();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__POSITION:
				return getPosition();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR:
				return getColor();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE:
				return getColorType();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__REFLECTING:
				return isReflecting();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR:
				return isRadarreflector();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TYPE:
				return getType();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK:
				return getTopmark();
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
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE:
				setLongitude((Longitude)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE:
				setLatitude((Latitude)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__PRECISION:
				setPrecision((Integer)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__NAME:
				setName((String)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__POSITION:
				setPosition((String)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR:
				getColor().clear();
				getColor().addAll((Collection<? extends Color>)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE:
				setColorType((Orientation)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__REFLECTING:
				setReflecting((Boolean)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR:
				setRadarreflector((Boolean)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TYPE:
				setType((BuoyType)newValue);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK:
				setTopmark((Topmark)newValue);
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
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE:
				setLongitude((Longitude)null);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE:
				setLatitude((Latitude)null);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__PRECISION:
				setPrecision(PRECISION_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__POSITION:
				setPosition(POSITION_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR:
				getColor().clear();
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE:
				setColorType(COLOR_TYPE_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__REFLECTING:
				setReflecting(REFLECTING_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR:
				setRadarreflector(RADARREFLECTOR_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK:
				setTopmark((Topmark)null);
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
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE:
				return longitude != null;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE:
				return latitude != null;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__PRECISION:
				return precision != PRECISION_EDEFAULT;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__POSITION:
				return POSITION_EDEFAULT == null ? position != null : !POSITION_EDEFAULT.equals(position);
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR:
				return color != null && !color.isEmpty();
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE:
				return colorType != COLOR_TYPE_EDEFAULT;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__REFLECTING:
				return reflecting != REFLECTING_EDEFAULT;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR:
				return radarreflector != RADARREFLECTOR_EDEFAULT;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TYPE:
				return type != TYPE_EDEFAULT;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK:
				return topmark != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ModelObject.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == GeoPosition.class) {
			switch (derivedFeatureID) {
				case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE: return GeoPackage.GEO_POSITION__LONGITUDE;
				case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE: return GeoPackage.GEO_POSITION__LATITUDE;
				case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__PRECISION: return GeoPackage.GEO_POSITION__PRECISION;
				default: return -1;
			}
		}
		if (baseClass == AbstractSeamark.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == NamedArtifact.class) {
			switch (derivedFeatureID) {
				case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__NAME: return GeoPackage.NAMED_ARTIFACT__NAME;
				default: return -1;
			}
		}
		if (baseClass == NamedPosition.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ModelObject.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == GeoPosition.class) {
			switch (baseFeatureID) {
				case GeoPackage.GEO_POSITION__LONGITUDE: return BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE;
				case GeoPackage.GEO_POSITION__LATITUDE: return BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE;
				case GeoPackage.GEO_POSITION__PRECISION: return BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__PRECISION;
				default: return -1;
			}
		}
		if (baseClass == AbstractSeamark.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == NamedArtifact.class) {
			switch (baseFeatureID) {
				case GeoPackage.NAMED_ARTIFACT__NAME: return BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__NAME;
				default: return -1;
			}
		}
		if (baseClass == NamedPosition.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (precision: ");
		result.append(precision);
		result.append(", name: ");
		result.append(name);
		result.append(", position: ");
		result.append(position);
		result.append(", color: ");
		result.append(color);
		result.append(", colorType: ");
		result.append(colorType);
		result.append(", reflecting: ");
		result.append(reflecting);
		result.append(", radarreflector: ");
		result.append(radarreflector);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //AbstractCommonBuoyBeaconImpl
