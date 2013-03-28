/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.base.impl;

import java.util.Collection;

import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.model.core.geo.GeoPackage;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.NavigationCompound;
import net.sf.seesea.model.core.geo.POIContainer;
import net.sf.seesea.model.core.geo.RoutingContainer;
import net.sf.seesea.model.core.geo.TracksContainer;
import net.sf.seesea.model.core.geo.impl.ChartImpl;

import net.sf.seesea.model.core.geo.osm.Area;
import net.sf.seesea.model.core.geo.osm.OsmPackage;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.int1.base.AbstractSeamark;
import net.sf.seesea.model.int1.base.BasePackage;
import net.sf.seesea.model.int1.base.MarineChart;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Marine Chart</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#getPoiContainer <em>Poi Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#getRoutingContainer <em>Routing Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#getTracksContainer <em>Tracks Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#getZoomLevel <em>Zoom Level</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#getMapCenterPosition <em>Map Center Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#getSubArea <em>Sub Area</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#isLongitudeScale <em>Longitude Scale</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#isLatitudeScale <em>Latitude Scale</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#getAnchorPosition <em>Anchor Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.base.impl.MarineChartImpl#getSeamarks <em>Seamarks</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MarineChartImpl extends ChartImpl implements MarineChart {
	/**
	 * The cached value of the '{@link #getPoiContainer() <em>Poi Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoiContainer()
	 * @generated
	 * @ordered
	 */
	protected POIContainer poiContainer;
	/**
	 * The cached value of the '{@link #getRoutingContainer() <em>Routing Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoutingContainer()
	 * @generated
	 * @ordered
	 */
	protected RoutingContainer routingContainer;
	/**
	 * The cached value of the '{@link #getTracksContainer() <em>Tracks Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracksContainer()
	 * @generated
	 * @ordered
	 */
	protected TracksContainer tracksContainer;
	/**
	 * The default value of the '{@link #getZoomLevel() <em>Zoom Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoomLevel()
	 * @generated
	 * @ordered
	 */
	protected static final int ZOOM_LEVEL_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getZoomLevel() <em>Zoom Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoomLevel()
	 * @generated
	 * @ordered
	 */
	protected int zoomLevel = ZOOM_LEVEL_EDEFAULT;
	/**
	 * The cached value of the '{@link #getMapCenterPosition() <em>Map Center Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapCenterPosition()
	 * @generated
	 * @ordered
	 */
	protected GeoPosition mapCenterPosition;
	/**
	 * The cached value of the '{@link #getSubArea() <em>Sub Area</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubArea()
	 * @generated
	 * @ordered
	 */
	protected EList<Area> subArea;
	/**
	 * The default value of the '{@link #isLongitudeScale() <em>Longitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLongitudeScale()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LONGITUDE_SCALE_EDEFAULT = true;
	/**
	 * The cached value of the '{@link #isLongitudeScale() <em>Longitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLongitudeScale()
	 * @generated
	 * @ordered
	 */
	protected boolean longitudeScale = LONGITUDE_SCALE_EDEFAULT;
	/**
	 * The default value of the '{@link #isLatitudeScale() <em>Latitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLatitudeScale()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LATITUDE_SCALE_EDEFAULT = true;
	/**
	 * The cached value of the '{@link #isLatitudeScale() <em>Latitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLatitudeScale()
	 * @generated
	 * @ordered
	 */
	protected boolean latitudeScale = LATITUDE_SCALE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getAnchorPosition() <em>Anchor Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnchorPosition()
	 * @generated
	 * @ordered
	 */
	protected AnchorPosition anchorPosition;
	/**
	 * The cached value of the '{@link #getSeamarks() <em>Seamarks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeamarks()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractSeamark> seamarks;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MarineChartImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BasePackage.Literals.MARINE_CHART;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public POIContainer getPoiContainer() {
		return poiContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPoiContainer(POIContainer newPoiContainer, NotificationChain msgs) {
		POIContainer oldPoiContainer = poiContainer;
		poiContainer = newPoiContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__POI_CONTAINER, oldPoiContainer, newPoiContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPoiContainer(POIContainer newPoiContainer) {
		if (newPoiContainer != poiContainer) {
			NotificationChain msgs = null;
			if (poiContainer != null)
				msgs = ((InternalEObject)poiContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__POI_CONTAINER, null, msgs);
			if (newPoiContainer != null)
				msgs = ((InternalEObject)newPoiContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__POI_CONTAINER, null, msgs);
			msgs = basicSetPoiContainer(newPoiContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__POI_CONTAINER, newPoiContainer, newPoiContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoutingContainer getRoutingContainer() {
		return routingContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoutingContainer(RoutingContainer newRoutingContainer, NotificationChain msgs) {
		RoutingContainer oldRoutingContainer = routingContainer;
		routingContainer = newRoutingContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__ROUTING_CONTAINER, oldRoutingContainer, newRoutingContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoutingContainer(RoutingContainer newRoutingContainer) {
		if (newRoutingContainer != routingContainer) {
			NotificationChain msgs = null;
			if (routingContainer != null)
				msgs = ((InternalEObject)routingContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__ROUTING_CONTAINER, null, msgs);
			if (newRoutingContainer != null)
				msgs = ((InternalEObject)newRoutingContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__ROUTING_CONTAINER, null, msgs);
			msgs = basicSetRoutingContainer(newRoutingContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__ROUTING_CONTAINER, newRoutingContainer, newRoutingContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracksContainer getTracksContainer() {
		return tracksContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTracksContainer(TracksContainer newTracksContainer, NotificationChain msgs) {
		TracksContainer oldTracksContainer = tracksContainer;
		tracksContainer = newTracksContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__TRACKS_CONTAINER, oldTracksContainer, newTracksContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTracksContainer(TracksContainer newTracksContainer) {
		if (newTracksContainer != tracksContainer) {
			NotificationChain msgs = null;
			if (tracksContainer != null)
				msgs = ((InternalEObject)tracksContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__TRACKS_CONTAINER, null, msgs);
			if (newTracksContainer != null)
				msgs = ((InternalEObject)newTracksContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__TRACKS_CONTAINER, null, msgs);
			msgs = basicSetTracksContainer(newTracksContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__TRACKS_CONTAINER, newTracksContainer, newTracksContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getZoomLevel() {
		return zoomLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setZoomLevel(int newZoomLevel) {
		int oldZoomLevel = zoomLevel;
		zoomLevel = newZoomLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__ZOOM_LEVEL, oldZoomLevel, zoomLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoPosition getMapCenterPosition() {
		return mapCenterPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMapCenterPosition(GeoPosition newMapCenterPosition, NotificationChain msgs) {
		GeoPosition oldMapCenterPosition = mapCenterPosition;
		mapCenterPosition = newMapCenterPosition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__MAP_CENTER_POSITION, oldMapCenterPosition, newMapCenterPosition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapCenterPosition(GeoPosition newMapCenterPosition) {
		if (newMapCenterPosition != mapCenterPosition) {
			NotificationChain msgs = null;
			if (mapCenterPosition != null)
				msgs = ((InternalEObject)mapCenterPosition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__MAP_CENTER_POSITION, null, msgs);
			if (newMapCenterPosition != null)
				msgs = ((InternalEObject)newMapCenterPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__MAP_CENTER_POSITION, null, msgs);
			msgs = basicSetMapCenterPosition(newMapCenterPosition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__MAP_CENTER_POSITION, newMapCenterPosition, newMapCenterPosition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Area> getSubArea() {
		if (subArea == null) {
			subArea = new EObjectContainmentEList<Area>(Area.class, this, BasePackage.MARINE_CHART__SUB_AREA);
		}
		return subArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLongitudeScale() {
		return longitudeScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongitudeScale(boolean newLongitudeScale) {
		boolean oldLongitudeScale = longitudeScale;
		longitudeScale = newLongitudeScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__LONGITUDE_SCALE, oldLongitudeScale, longitudeScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLatitudeScale() {
		return latitudeScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLatitudeScale(boolean newLatitudeScale) {
		boolean oldLatitudeScale = latitudeScale;
		latitudeScale = newLatitudeScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__LATITUDE_SCALE, oldLatitudeScale, latitudeScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnchorPosition getAnchorPosition() {
		return anchorPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnchorPosition(AnchorPosition newAnchorPosition, NotificationChain msgs) {
		AnchorPosition oldAnchorPosition = anchorPosition;
		anchorPosition = newAnchorPosition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__ANCHOR_POSITION, oldAnchorPosition, newAnchorPosition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnchorPosition(AnchorPosition newAnchorPosition) {
		if (newAnchorPosition != anchorPosition) {
			NotificationChain msgs = null;
			if (anchorPosition != null)
				msgs = ((InternalEObject)anchorPosition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__ANCHOR_POSITION, null, msgs);
			if (newAnchorPosition != null)
				msgs = ((InternalEObject)newAnchorPosition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BasePackage.MARINE_CHART__ANCHOR_POSITION, null, msgs);
			msgs = basicSetAnchorPosition(newAnchorPosition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BasePackage.MARINE_CHART__ANCHOR_POSITION, newAnchorPosition, newAnchorPosition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractSeamark> getSeamarks() {
		if (seamarks == null) {
			seamarks = new EObjectContainmentEList<AbstractSeamark>(AbstractSeamark.class, this, BasePackage.MARINE_CHART__SEAMARKS);
		}
		return seamarks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BasePackage.MARINE_CHART__POI_CONTAINER:
				return basicSetPoiContainer(null, msgs);
			case BasePackage.MARINE_CHART__ROUTING_CONTAINER:
				return basicSetRoutingContainer(null, msgs);
			case BasePackage.MARINE_CHART__TRACKS_CONTAINER:
				return basicSetTracksContainer(null, msgs);
			case BasePackage.MARINE_CHART__MAP_CENTER_POSITION:
				return basicSetMapCenterPosition(null, msgs);
			case BasePackage.MARINE_CHART__SUB_AREA:
				return ((InternalEList<?>)getSubArea()).basicRemove(otherEnd, msgs);
			case BasePackage.MARINE_CHART__ANCHOR_POSITION:
				return basicSetAnchorPosition(null, msgs);
			case BasePackage.MARINE_CHART__SEAMARKS:
				return ((InternalEList<?>)getSeamarks()).basicRemove(otherEnd, msgs);
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
			case BasePackage.MARINE_CHART__POI_CONTAINER:
				return getPoiContainer();
			case BasePackage.MARINE_CHART__ROUTING_CONTAINER:
				return getRoutingContainer();
			case BasePackage.MARINE_CHART__TRACKS_CONTAINER:
				return getTracksContainer();
			case BasePackage.MARINE_CHART__ZOOM_LEVEL:
				return getZoomLevel();
			case BasePackage.MARINE_CHART__MAP_CENTER_POSITION:
				return getMapCenterPosition();
			case BasePackage.MARINE_CHART__SUB_AREA:
				return getSubArea();
			case BasePackage.MARINE_CHART__LONGITUDE_SCALE:
				return isLongitudeScale();
			case BasePackage.MARINE_CHART__LATITUDE_SCALE:
				return isLatitudeScale();
			case BasePackage.MARINE_CHART__ANCHOR_POSITION:
				return getAnchorPosition();
			case BasePackage.MARINE_CHART__SEAMARKS:
				return getSeamarks();
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
			case BasePackage.MARINE_CHART__POI_CONTAINER:
				setPoiContainer((POIContainer)newValue);
				return;
			case BasePackage.MARINE_CHART__ROUTING_CONTAINER:
				setRoutingContainer((RoutingContainer)newValue);
				return;
			case BasePackage.MARINE_CHART__TRACKS_CONTAINER:
				setTracksContainer((TracksContainer)newValue);
				return;
			case BasePackage.MARINE_CHART__ZOOM_LEVEL:
				setZoomLevel((Integer)newValue);
				return;
			case BasePackage.MARINE_CHART__MAP_CENTER_POSITION:
				setMapCenterPosition((GeoPosition)newValue);
				return;
			case BasePackage.MARINE_CHART__SUB_AREA:
				getSubArea().clear();
				getSubArea().addAll((Collection<? extends Area>)newValue);
				return;
			case BasePackage.MARINE_CHART__LONGITUDE_SCALE:
				setLongitudeScale((Boolean)newValue);
				return;
			case BasePackage.MARINE_CHART__LATITUDE_SCALE:
				setLatitudeScale((Boolean)newValue);
				return;
			case BasePackage.MARINE_CHART__ANCHOR_POSITION:
				setAnchorPosition((AnchorPosition)newValue);
				return;
			case BasePackage.MARINE_CHART__SEAMARKS:
				getSeamarks().clear();
				getSeamarks().addAll((Collection<? extends AbstractSeamark>)newValue);
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
			case BasePackage.MARINE_CHART__POI_CONTAINER:
				setPoiContainer((POIContainer)null);
				return;
			case BasePackage.MARINE_CHART__ROUTING_CONTAINER:
				setRoutingContainer((RoutingContainer)null);
				return;
			case BasePackage.MARINE_CHART__TRACKS_CONTAINER:
				setTracksContainer((TracksContainer)null);
				return;
			case BasePackage.MARINE_CHART__ZOOM_LEVEL:
				setZoomLevel(ZOOM_LEVEL_EDEFAULT);
				return;
			case BasePackage.MARINE_CHART__MAP_CENTER_POSITION:
				setMapCenterPosition((GeoPosition)null);
				return;
			case BasePackage.MARINE_CHART__SUB_AREA:
				getSubArea().clear();
				return;
			case BasePackage.MARINE_CHART__LONGITUDE_SCALE:
				setLongitudeScale(LONGITUDE_SCALE_EDEFAULT);
				return;
			case BasePackage.MARINE_CHART__LATITUDE_SCALE:
				setLatitudeScale(LATITUDE_SCALE_EDEFAULT);
				return;
			case BasePackage.MARINE_CHART__ANCHOR_POSITION:
				setAnchorPosition((AnchorPosition)null);
				return;
			case BasePackage.MARINE_CHART__SEAMARKS:
				getSeamarks().clear();
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
			case BasePackage.MARINE_CHART__POI_CONTAINER:
				return poiContainer != null;
			case BasePackage.MARINE_CHART__ROUTING_CONTAINER:
				return routingContainer != null;
			case BasePackage.MARINE_CHART__TRACKS_CONTAINER:
				return tracksContainer != null;
			case BasePackage.MARINE_CHART__ZOOM_LEVEL:
				return zoomLevel != ZOOM_LEVEL_EDEFAULT;
			case BasePackage.MARINE_CHART__MAP_CENTER_POSITION:
				return mapCenterPosition != null;
			case BasePackage.MARINE_CHART__SUB_AREA:
				return subArea != null && !subArea.isEmpty();
			case BasePackage.MARINE_CHART__LONGITUDE_SCALE:
				return longitudeScale != LONGITUDE_SCALE_EDEFAULT;
			case BasePackage.MARINE_CHART__LATITUDE_SCALE:
				return latitudeScale != LATITUDE_SCALE_EDEFAULT;
			case BasePackage.MARINE_CHART__ANCHOR_POSITION:
				return anchorPosition != null;
			case BasePackage.MARINE_CHART__SEAMARKS:
				return seamarks != null && !seamarks.isEmpty();
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
		if (baseClass == NavigationCompound.class) {
			switch (derivedFeatureID) {
				case BasePackage.MARINE_CHART__POI_CONTAINER: return GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER;
				case BasePackage.MARINE_CHART__ROUTING_CONTAINER: return GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER;
				case BasePackage.MARINE_CHART__TRACKS_CONTAINER: return GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER;
				default: return -1;
			}
		}
		if (baseClass == Area.class) {
			switch (derivedFeatureID) {
				case BasePackage.MARINE_CHART__ZOOM_LEVEL: return OsmPackage.AREA__ZOOM_LEVEL;
				case BasePackage.MARINE_CHART__MAP_CENTER_POSITION: return OsmPackage.AREA__MAP_CENTER_POSITION;
				case BasePackage.MARINE_CHART__SUB_AREA: return OsmPackage.AREA__SUB_AREA;
				default: return -1;
			}
		}
		if (baseClass == World.class) {
			switch (derivedFeatureID) {
				case BasePackage.MARINE_CHART__LONGITUDE_SCALE: return OsmPackage.WORLD__LONGITUDE_SCALE;
				case BasePackage.MARINE_CHART__LATITUDE_SCALE: return OsmPackage.WORLD__LATITUDE_SCALE;
				case BasePackage.MARINE_CHART__ANCHOR_POSITION: return OsmPackage.WORLD__ANCHOR_POSITION;
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
		if (baseClass == NavigationCompound.class) {
			switch (baseFeatureID) {
				case GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER: return BasePackage.MARINE_CHART__POI_CONTAINER;
				case GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER: return BasePackage.MARINE_CHART__ROUTING_CONTAINER;
				case GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER: return BasePackage.MARINE_CHART__TRACKS_CONTAINER;
				default: return -1;
			}
		}
		if (baseClass == Area.class) {
			switch (baseFeatureID) {
				case OsmPackage.AREA__ZOOM_LEVEL: return BasePackage.MARINE_CHART__ZOOM_LEVEL;
				case OsmPackage.AREA__MAP_CENTER_POSITION: return BasePackage.MARINE_CHART__MAP_CENTER_POSITION;
				case OsmPackage.AREA__SUB_AREA: return BasePackage.MARINE_CHART__SUB_AREA;
				default: return -1;
			}
		}
		if (baseClass == World.class) {
			switch (baseFeatureID) {
				case OsmPackage.WORLD__LONGITUDE_SCALE: return BasePackage.MARINE_CHART__LONGITUDE_SCALE;
				case OsmPackage.WORLD__LATITUDE_SCALE: return BasePackage.MARINE_CHART__LATITUDE_SCALE;
				case OsmPackage.WORLD__ANCHOR_POSITION: return BasePackage.MARINE_CHART__ANCHOR_POSITION;
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
		result.append(" (zoomLevel: ");
		result.append(zoomLevel);
		result.append(", longitudeScale: ");
		result.append(longitudeScale);
		result.append(", latitudeScale: ");
		result.append(latitudeScale);
		result.append(')');
		return result.toString();
	}

} //MarineChartImpl
