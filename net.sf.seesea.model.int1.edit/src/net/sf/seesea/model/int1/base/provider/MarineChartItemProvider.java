/**
 * <copyright>
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.base.provider;


import java.util.Collection;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPackage;

import net.sf.seesea.model.core.geo.osm.OsmFactory;
import net.sf.seesea.model.core.geo.osm.OsmPackage;

import net.sf.seesea.model.core.geo.provider.ChartItemProvider;

import net.sf.seesea.model.int1.base.BaseFactory;
import net.sf.seesea.model.int1.base.BasePackage;
import net.sf.seesea.model.int1.base.MarineChart;

import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsFactory;

import net.sf.seesea.model.int1.buoysandbeacons.provider.Int1EditPlugin;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link net.sf.seesea.model.int1.base.MarineChart} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MarineChartItemProvider
	extends ChartItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MarineChartItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addZoomLevelPropertyDescriptor(object);
			addLongitudeScalePropertyDescriptor(object);
			addLatitudeScalePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Zoom Level feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addZoomLevelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Area_zoomLevel_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Area_zoomLevel_feature", "_UI_Area_type"),
				 OsmPackage.Literals.AREA__ZOOM_LEVEL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Longitude Scale feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLongitudeScalePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_World_longitudeScale_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_World_longitudeScale_feature", "_UI_World_type"),
				 OsmPackage.Literals.WORLD__LONGITUDE_SCALE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Latitude Scale feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLatitudeScalePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_World_latitudeScale_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_World_latitudeScale_feature", "_UI_World_type"),
				 OsmPackage.Literals.WORLD__LATITUDE_SCALE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(GeoPackage.Literals.NAVIGATION_COMPOUND__POI_CONTAINER);
			childrenFeatures.add(GeoPackage.Literals.NAVIGATION_COMPOUND__ROUTING_CONTAINER);
			childrenFeatures.add(GeoPackage.Literals.NAVIGATION_COMPOUND__TRACKS_CONTAINER);
			childrenFeatures.add(OsmPackage.Literals.AREA__MAP_CENTER_POSITION);
			childrenFeatures.add(OsmPackage.Literals.AREA__SUB_AREA);
			childrenFeatures.add(BasePackage.Literals.MARINE_CHART__SEAMARKS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns MarineChart.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MarineChart"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((MarineChart)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_MarineChart_type") :
			getString("_UI_MarineChart_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(MarineChart.class)) {
			case BasePackage.MARINE_CHART__ZOOM_LEVEL:
			case BasePackage.MARINE_CHART__LONGITUDE_SCALE:
			case BasePackage.MARINE_CHART__LATITUDE_SCALE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case BasePackage.MARINE_CHART__POI_CONTAINER:
			case BasePackage.MARINE_CHART__ROUTING_CONTAINER:
			case BasePackage.MARINE_CHART__TRACKS_CONTAINER:
			case BasePackage.MARINE_CHART__MAP_CENTER_POSITION:
			case BasePackage.MARINE_CHART__SUB_AREA:
			case BasePackage.MARINE_CHART__SEAMARKS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(GeoPackage.Literals.NAVIGATION_COMPOUND__POI_CONTAINER,
				 GeoFactory.eINSTANCE.createPOIContainer()));

		newChildDescriptors.add
			(createChildParameter
				(GeoPackage.Literals.NAVIGATION_COMPOUND__ROUTING_CONTAINER,
				 GeoFactory.eINSTANCE.createRoutingContainer()));

		newChildDescriptors.add
			(createChildParameter
				(GeoPackage.Literals.NAVIGATION_COMPOUND__TRACKS_CONTAINER,
				 GeoFactory.eINSTANCE.createTracksContainer()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__MAP_CENTER_POSITION,
				 BuoysandbeaconsFactory.eINSTANCE.createBuoy()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__MAP_CENTER_POSITION,
				 BuoysandbeaconsFactory.eINSTANCE.createBeacon()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__MAP_CENTER_POSITION,
				 BuoysandbeaconsFactory.eINSTANCE.createSpecialBuoy()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__MAP_CENTER_POSITION,
				 GeoFactory.eINSTANCE.createGeoPosition()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__MAP_CENTER_POSITION,
				 GeoFactory.eINSTANCE.createNamedPosition()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__MAP_CENTER_POSITION,
				 GeoFactory.eINSTANCE.createGeoPosition3D()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__MAP_CENTER_POSITION,
				 GeoFactory.eINSTANCE.createMeasuredPosition3D()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__MAP_CENTER_POSITION,
				 GeoFactory.eINSTANCE.createGNSSMeasuredPosition()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__SUB_AREA,
				 BaseFactory.eINSTANCE.createMarineChart()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__SUB_AREA,
				 GeoFactory.eINSTANCE.createNavarea()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__SUB_AREA,
				 OsmFactory.eINSTANCE.createArea()));

		newChildDescriptors.add
			(createChildParameter
				(OsmPackage.Literals.AREA__SUB_AREA,
				 OsmFactory.eINSTANCE.createWorld()));

		newChildDescriptors.add
			(createChildParameter
				(BasePackage.Literals.MARINE_CHART__SEAMARKS,
				 BuoysandbeaconsFactory.eINSTANCE.createBuoy()));

		newChildDescriptors.add
			(createChildParameter
				(BasePackage.Literals.MARINE_CHART__SEAMARKS,
				 BuoysandbeaconsFactory.eINSTANCE.createBeacon()));

		newChildDescriptors.add
			(createChildParameter
				(BasePackage.Literals.MARINE_CHART__SEAMARKS,
				 BuoysandbeaconsFactory.eINSTANCE.createSpecialBuoy()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == OsmPackage.Literals.AREA__MAP_CENTER_POSITION ||
			childFeature == BasePackage.Literals.MARINE_CHART__SEAMARKS;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return Int1EditPlugin.INSTANCE;
	}

}
