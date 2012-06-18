/**
 * <copyright>
Copyright (c) 2010-2012, Jens Kübler
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
package net.sf.seesea.model.core;

import net.sf.seesea.model.core.geo.GeoPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "core";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "a";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "a";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CorePackage eINSTANCE = net.sf.seesea.model.core.impl.CorePackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.impl.ModelObjectImpl <em>Model Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.impl.ModelObjectImpl
	 * @see net.sf.seesea.model.core.impl.CorePackageImpl#getModelObject()
	 * @generated
	 */
	int MODEL_OBJECT = 1;

	/**
	 * The number of structural features of the '<em>Model Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OBJECT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.impl.ModelRootImpl <em>Model Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.impl.ModelRootImpl
	 * @see net.sf.seesea.model.core.impl.CorePackageImpl#getModelRoot()
	 * @generated
	 */
	int MODEL_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Poi Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT__POI_CONTAINER = GeoPackage.NAVIGATION_COMPOUND__POI_CONTAINER;

	/**
	 * The feature id for the '<em><b>Routing Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT__ROUTING_CONTAINER = GeoPackage.NAVIGATION_COMPOUND__ROUTING_CONTAINER;

	/**
	 * The feature id for the '<em><b>Tracks Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT__TRACKS_CONTAINER = GeoPackage.NAVIGATION_COMPOUND__TRACKS_CONTAINER;

	/**
	 * The feature id for the '<em><b>Chart Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT__CHART_CONTAINER = GeoPackage.NAVIGATION_COMPOUND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instruments</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT__INSTRUMENTS = GeoPackage.NAVIGATION_COMPOUND_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Model Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT_FEATURE_COUNT = GeoPackage.NAVIGATION_COMPOUND_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.ModelRoot <em>Model Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Root</em>'.
	 * @see net.sf.seesea.model.core.ModelRoot
	 * @generated
	 */
	EClass getModelRoot();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.ModelRoot#getChartContainer <em>Chart Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Chart Container</em>'.
	 * @see net.sf.seesea.model.core.ModelRoot#getChartContainer()
	 * @see #getModelRoot()
	 * @generated
	 */
	EReference getModelRoot_ChartContainer();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.ModelRoot#getInstruments <em>Instruments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Instruments</em>'.
	 * @see net.sf.seesea.model.core.ModelRoot#getInstruments()
	 * @see #getModelRoot()
	 * @generated
	 */
	EReference getModelRoot_Instruments();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.ModelObject <em>Model Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Object</em>'.
	 * @see net.sf.seesea.model.core.ModelObject
	 * @generated
	 */
	EClass getModelObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoreFactory getCoreFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.impl.ModelRootImpl <em>Model Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.impl.ModelRootImpl
		 * @see net.sf.seesea.model.core.impl.CorePackageImpl#getModelRoot()
		 * @generated
		 */
		EClass MODEL_ROOT = eINSTANCE.getModelRoot();

		/**
		 * The meta object literal for the '<em><b>Chart Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ROOT__CHART_CONTAINER = eINSTANCE.getModelRoot_ChartContainer();

		/**
		 * The meta object literal for the '<em><b>Instruments</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ROOT__INSTRUMENTS = eINSTANCE.getModelRoot_Instruments();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.impl.ModelObjectImpl <em>Model Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.impl.ModelObjectImpl
		 * @see net.sf.seesea.model.core.impl.CorePackageImpl#getModelObject()
		 * @generated
		 */
		EClass MODEL_OBJECT = eINSTANCE.getModelObject();

	}

} //CorePackage
