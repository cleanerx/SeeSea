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
package net.sf.seesea.model.core.data;

import net.sf.seesea.model.core.CorePackage;
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
 * @see net.sf.seesea.model.core.data.DataFactory
 * @model kind="package"
 * @generated
 */
public interface DataPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "data";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "data";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "data";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DataPackage eINSTANCE = net.sf.seesea.model.core.data.impl.DataPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.data.impl.SeriesImpl <em>Series</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.data.impl.SeriesImpl
	 * @see net.sf.seesea.model.core.data.impl.DataPackageImpl#getSeries()
	 * @generated
	 */
	int SERIES = 0;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERIES__MEASUREMENT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Series</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERIES_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.data.impl.InstrumentsImpl <em>Instruments</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.data.impl.InstrumentsImpl
	 * @see net.sf.seesea.model.core.data.impl.DataPackageImpl#getInstruments()
	 * @generated
	 */
	int INSTRUMENTS = 1;

	/**
	 * The feature id for the '<em><b>Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTS__POSITION = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Water Temperature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTS__WATER_TEMPERATURE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Heading And Velocity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTS__HEADING_AND_VELOCITY = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Satellites Visible</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTS__SATELLITES_VISIBLE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Wind Vector</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTS__WIND_VECTOR = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Instruments</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTS_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 5;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.data.Series <em>Series</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Series</em>'.
	 * @see net.sf.seesea.model.core.data.Series
	 * @generated
	 */
	EClass getSeries();

	/**
	 * Returns the meta object for the containment reference list '{@link net.sf.seesea.model.core.data.Series#getMeasurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measurement</em>'.
	 * @see net.sf.seesea.model.core.data.Series#getMeasurement()
	 * @see #getSeries()
	 * @generated
	 */
	EReference getSeries_Measurement();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.data.Instruments <em>Instruments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instruments</em>'.
	 * @see net.sf.seesea.model.core.data.Instruments
	 * @generated
	 */
	EClass getInstruments();

	/**
	 * Returns the meta object for the reference '{@link net.sf.seesea.model.core.data.Instruments#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Position</em>'.
	 * @see net.sf.seesea.model.core.data.Instruments#getPosition()
	 * @see #getInstruments()
	 * @generated
	 */
	EReference getInstruments_Position();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.data.Instruments#getWaterTemperature <em>Water Temperature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Water Temperature</em>'.
	 * @see net.sf.seesea.model.core.data.Instruments#getWaterTemperature()
	 * @see #getInstruments()
	 * @generated
	 */
	EReference getInstruments_WaterTemperature();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.data.Instruments#getHeadingAndVelocity <em>Heading And Velocity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Heading And Velocity</em>'.
	 * @see net.sf.seesea.model.core.data.Instruments#getHeadingAndVelocity()
	 * @see #getInstruments()
	 * @generated
	 */
	EReference getInstruments_HeadingAndVelocity();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.data.Instruments#getSatellitesVisible <em>Satellites Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Satellites Visible</em>'.
	 * @see net.sf.seesea.model.core.data.Instruments#getSatellitesVisible()
	 * @see #getInstruments()
	 * @generated
	 */
	EReference getInstruments_SatellitesVisible();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.core.data.Instruments#getWindVector <em>Wind Vector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Wind Vector</em>'.
	 * @see net.sf.seesea.model.core.data.Instruments#getWindVector()
	 * @see #getInstruments()
	 * @generated
	 */
	EReference getInstruments_WindVector();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DataFactory getDataFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.core.data.impl.SeriesImpl <em>Series</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.data.impl.SeriesImpl
		 * @see net.sf.seesea.model.core.data.impl.DataPackageImpl#getSeries()
		 * @generated
		 */
		EClass SERIES = eINSTANCE.getSeries();

		/**
		 * The meta object literal for the '<em><b>Measurement</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERIES__MEASUREMENT = eINSTANCE.getSeries_Measurement();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.data.impl.InstrumentsImpl <em>Instruments</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.data.impl.InstrumentsImpl
		 * @see net.sf.seesea.model.core.data.impl.DataPackageImpl#getInstruments()
		 * @generated
		 */
		EClass INSTRUMENTS = eINSTANCE.getInstruments();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTS__POSITION = eINSTANCE.getInstruments_Position();

		/**
		 * The meta object literal for the '<em><b>Water Temperature</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTS__WATER_TEMPERATURE = eINSTANCE.getInstruments_WaterTemperature();

		/**
		 * The meta object literal for the '<em><b>Heading And Velocity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTS__HEADING_AND_VELOCITY = eINSTANCE.getInstruments_HeadingAndVelocity();

		/**
		 * The meta object literal for the '<em><b>Satellites Visible</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTS__SATELLITES_VISIBLE = eINSTANCE.getInstruments_SatellitesVisible();

		/**
		 * The meta object literal for the '<em><b>Wind Vector</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTS__WIND_VECTOR = eINSTANCE.getInstruments_WindVector();

	}

} //DataPackage
