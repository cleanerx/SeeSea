/**
 * <copyright>
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
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
package net.sf.seesea.model.int1.buoysandbeacons.provider;


import java.util.Collection;
import java.util.List;

import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPackage;

import net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon;
import net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsPackage;

import net.sf.seesea.model.int1.lights.provider.LightCharacterItemProvider;

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
 * This is the item provider adapter for a {@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractCommonBuoyBeaconItemProvider
	extends LightCharacterItemProvider
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
	public AbstractCommonBuoyBeaconItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
			addPositionPropertyDescriptor(object);
			addColorPropertyDescriptor(object);
			addColorTypePropertyDescriptor(object);
			addReflectingPropertyDescriptor(object);
			addRadarreflectorPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
			addTopmarkPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_NamedArtifact_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_NamedArtifact_name_feature", "_UI_NamedArtifact_type"),
				 GeoPackage.Literals.NAMED_ARTIFACT__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Position feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPositionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractCommonBuoyBeacon_position_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractCommonBuoyBeacon_position_feature", "_UI_AbstractCommonBuoyBeacon_type"),
				 BuoysandbeaconsPackage.Literals.ABSTRACT_COMMON_BUOY_BEACON__POSITION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Color feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addColorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractCommonBuoyBeacon_color_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractCommonBuoyBeacon_color_feature", "_UI_AbstractCommonBuoyBeacon_type"),
				 BuoysandbeaconsPackage.Literals.ABSTRACT_COMMON_BUOY_BEACON__COLOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Color Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addColorTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractCommonBuoyBeacon_colorType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractCommonBuoyBeacon_colorType_feature", "_UI_AbstractCommonBuoyBeacon_type"),
				 BuoysandbeaconsPackage.Literals.ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Reflecting feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReflectingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractCommonBuoyBeacon_reflecting_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractCommonBuoyBeacon_reflecting_feature", "_UI_AbstractCommonBuoyBeacon_type"),
				 BuoysandbeaconsPackage.Literals.ABSTRACT_COMMON_BUOY_BEACON__REFLECTING,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Topmark feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTopmarkPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractCommonBuoyBeacon_topmark_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractCommonBuoyBeacon_topmark_feature", "_UI_AbstractCommonBuoyBeacon_type"),
				 BuoysandbeaconsPackage.Literals.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Radarreflector feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRadarreflectorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractCommonBuoyBeacon_radarreflector_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractCommonBuoyBeacon_radarreflector_feature", "_UI_AbstractCommonBuoyBeacon_type"),
				 BuoysandbeaconsPackage.Literals.ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractCommonBuoyBeacon_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractCommonBuoyBeacon_type_feature", "_UI_AbstractCommonBuoyBeacon_type"),
				 BuoysandbeaconsPackage.Literals.ABSTRACT_COMMON_BUOY_BEACON__TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(GeoPackage.Literals.GEO_POSITION__LONGITUDE);
			childrenFeatures.add(GeoPackage.Literals.GEO_POSITION__LATITUDE);
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AbstractCommonBuoyBeacon)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractCommonBuoyBeacon_type") :
			getString("_UI_AbstractCommonBuoyBeacon_type") + " " + label;
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

		switch (notification.getFeatureID(AbstractCommonBuoyBeacon.class)) {
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__NAME:
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__POSITION:
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR:
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE:
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__REFLECTING:
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR:
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TYPE:
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__TOPMARK:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE:
			case BuoysandbeaconsPackage.ABSTRACT_COMMON_BUOY_BEACON__LATITUDE:
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
				(GeoPackage.Literals.GEO_POSITION__LONGITUDE,
				 GeoFactory.eINSTANCE.createLongitude()));

		newChildDescriptors.add
			(createChildParameter
				(GeoPackage.Literals.GEO_POSITION__LATITUDE,
				 GeoFactory.eINSTANCE.createLatitude()));
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
