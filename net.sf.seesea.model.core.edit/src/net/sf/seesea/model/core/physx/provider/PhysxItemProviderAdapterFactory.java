/**
 * <copyright>
Copyright (c) 2010-2012, Jens K�bler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.core.physx.provider;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.seesea.model.core.physx.util.PhysxAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysxItemProviderAdapterFactory extends PhysxAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysxItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.Temperature} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemperatureItemProvider temperatureItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.Temperature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTemperatureAdapter() {
		if (temperatureItemProvider == null) {
			temperatureItemProvider = new TemperatureItemProvider(this);
		}

		return temperatureItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.Speed} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpeedItemProvider speedItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.Speed}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSpeedAdapter() {
		if (speedItemProvider == null) {
			speedItemProvider = new SpeedItemProvider(this);
		}

		return speedItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.Heading} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HeadingItemProvider headingItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.Heading}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHeadingAdapter() {
		if (headingItemProvider == null) {
			headingItemProvider = new HeadingItemProvider(this);
		}

		return headingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.RelativeWind} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelativeWindItemProvider relativeWindItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.RelativeWind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRelativeWindAdapter() {
		if (relativeWindItemProvider == null) {
			relativeWindItemProvider = new RelativeWindItemProvider(this);
		}

		return relativeWindItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.SatelliteInfo} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SatelliteInfoItemProvider satelliteInfoItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.SatelliteInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSatelliteInfoAdapter() {
		if (satelliteInfoItemProvider == null) {
			satelliteInfoItemProvider = new SatelliteInfoItemProvider(this);
		}

		return satelliteInfoItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.SatellitesVisible} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SatellitesVisibleItemProvider satellitesVisibleItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.SatellitesVisible}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSatellitesVisibleAdapter() {
		if (satellitesVisibleItemProvider == null) {
			satellitesVisibleItemProvider = new SatellitesVisibleItemProvider(this);
		}

		return satellitesVisibleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.Time} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeItemProvider timeItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.Time}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTimeAdapter() {
		if (timeItemProvider == null) {
			timeItemProvider = new TimeItemProvider(this);
		}

		return timeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.Distance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DistanceItemProvider distanceItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.Distance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDistanceAdapter() {
		if (distanceItemProvider == null) {
			distanceItemProvider = new DistanceItemProvider(this);
		}

		return distanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.CompositeMeasurement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeMeasurementItemProvider compositeMeasurementItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.CompositeMeasurement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCompositeMeasurementAdapter() {
		if (compositeMeasurementItemProvider == null) {
			compositeMeasurementItemProvider = new CompositeMeasurementItemProvider(this);
		}

		return compositeMeasurementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link net.sf.seesea.model.core.physx.RelativeSpeed} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelativeSpeedItemProvider relativeSpeedItemProvider;

	/**
	 * This creates an adapter for a {@link net.sf.seesea.model.core.physx.RelativeSpeed}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRelativeSpeedAdapter() {
		if (relativeSpeedItemProvider == null) {
			relativeSpeedItemProvider = new RelativeSpeedItemProvider(this);
		}

		return relativeSpeedItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (temperatureItemProvider != null) temperatureItemProvider.dispose();
		if (speedItemProvider != null) speedItemProvider.dispose();
		if (headingItemProvider != null) headingItemProvider.dispose();
		if (relativeWindItemProvider != null) relativeWindItemProvider.dispose();
		if (satelliteInfoItemProvider != null) satelliteInfoItemProvider.dispose();
		if (satellitesVisibleItemProvider != null) satellitesVisibleItemProvider.dispose();
		if (timeItemProvider != null) timeItemProvider.dispose();
		if (distanceItemProvider != null) distanceItemProvider.dispose();
		if (compositeMeasurementItemProvider != null) compositeMeasurementItemProvider.dispose();
		if (relativeSpeedItemProvider != null) relativeSpeedItemProvider.dispose();
	}

}
