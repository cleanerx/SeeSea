/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.buoysandbeacons;

import net.sf.seesea.model.core.CorePackage;
import net.sf.seesea.model.int1.lights.LightsPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoysandbeaconsFactory
 * @model kind="package"
 * @generated
 */
public interface BuoysandbeaconsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "buoysandbeacons";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "buoysandbeacons";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "buoysandbeacons";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BuoysandbeaconsPackage eINSTANCE = net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl <em>Abstract Common Buoy Beacon</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getAbstractCommonBuoyBeacon()
	 * @generated
	 */
	int ABSTRACT_COMMON_BUOY_BEACON = 0;

	/**
	 * The feature id for the '<em><b>Lightcolor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__LIGHTCOLOR = LightsPackage.LIGHT_CHARACTER__LIGHTCOLOR;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__PERIOD = LightsPackage.LIGHT_CHARACTER__PERIOD;

	/**
	 * The feature id for the '<em><b>Phase Characteristic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__PHASE_CHARACTERISTIC = LightsPackage.LIGHT_CHARACTER__PHASE_CHARACTERISTIC;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__GROUP = LightsPackage.LIGHT_CHARACTER__GROUP;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__LATITUDE = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__PRECISION = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__NAME = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__POSITION = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__COLOR = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Color Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Reflecting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__REFLECTING = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Radarreflector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__TYPE = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Topmark</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON__TOPMARK = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Abstract Common Buoy Beacon</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_COMMON_BUOY_BEACON_FEATURE_COUNT = LightsPackage.LIGHT_CHARACTER_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.BuoyImpl <em>Buoy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoyImpl
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getBuoy()
	 * @generated
	 */
	int BUOY = 1;

	/**
	 * The feature id for the '<em><b>Lightcolor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__LIGHTCOLOR = ABSTRACT_COMMON_BUOY_BEACON__LIGHTCOLOR;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__PERIOD = ABSTRACT_COMMON_BUOY_BEACON__PERIOD;

	/**
	 * The feature id for the '<em><b>Phase Characteristic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__PHASE_CHARACTERISTIC = ABSTRACT_COMMON_BUOY_BEACON__PHASE_CHARACTERISTIC;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__GROUP = ABSTRACT_COMMON_BUOY_BEACON__GROUP;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__LONGITUDE = ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__LATITUDE = ABSTRACT_COMMON_BUOY_BEACON__LATITUDE;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__PRECISION = ABSTRACT_COMMON_BUOY_BEACON__PRECISION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__NAME = ABSTRACT_COMMON_BUOY_BEACON__NAME;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__POSITION = ABSTRACT_COMMON_BUOY_BEACON__POSITION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__COLOR = ABSTRACT_COMMON_BUOY_BEACON__COLOR;

	/**
	 * The feature id for the '<em><b>Color Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__COLOR_TYPE = ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE;

	/**
	 * The feature id for the '<em><b>Reflecting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__REFLECTING = ABSTRACT_COMMON_BUOY_BEACON__REFLECTING;

	/**
	 * The feature id for the '<em><b>Radarreflector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__RADARREFLECTOR = ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__TYPE = ABSTRACT_COMMON_BUOY_BEACON__TYPE;

	/**
	 * The feature id for the '<em><b>Topmark</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__TOPMARK = ABSTRACT_COMMON_BUOY_BEACON__TOPMARK;

	/**
	 * The feature id for the '<em><b>Designation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__DESIGNATION = ABSTRACT_COMMON_BUOY_BEACON_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY__SHAPE = ABSTRACT_COMMON_BUOY_BEACON_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Buoy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUOY_FEATURE_COUNT = ABSTRACT_COMMON_BUOY_BEACON_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.BeaconImpl <em>Beacon</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BeaconImpl
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getBeacon()
	 * @generated
	 */
	int BEACON = 2;

	/**
	 * The feature id for the '<em><b>Lightcolor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__LIGHTCOLOR = ABSTRACT_COMMON_BUOY_BEACON__LIGHTCOLOR;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__PERIOD = ABSTRACT_COMMON_BUOY_BEACON__PERIOD;

	/**
	 * The feature id for the '<em><b>Phase Characteristic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__PHASE_CHARACTERISTIC = ABSTRACT_COMMON_BUOY_BEACON__PHASE_CHARACTERISTIC;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__GROUP = ABSTRACT_COMMON_BUOY_BEACON__GROUP;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__LONGITUDE = ABSTRACT_COMMON_BUOY_BEACON__LONGITUDE;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__LATITUDE = ABSTRACT_COMMON_BUOY_BEACON__LATITUDE;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__PRECISION = ABSTRACT_COMMON_BUOY_BEACON__PRECISION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__NAME = ABSTRACT_COMMON_BUOY_BEACON__NAME;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__POSITION = ABSTRACT_COMMON_BUOY_BEACON__POSITION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__COLOR = ABSTRACT_COMMON_BUOY_BEACON__COLOR;

	/**
	 * The feature id for the '<em><b>Color Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__COLOR_TYPE = ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE;

	/**
	 * The feature id for the '<em><b>Reflecting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__REFLECTING = ABSTRACT_COMMON_BUOY_BEACON__REFLECTING;

	/**
	 * The feature id for the '<em><b>Radarreflector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__RADARREFLECTOR = ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__TYPE = ABSTRACT_COMMON_BUOY_BEACON__TYPE;

	/**
	 * The feature id for the '<em><b>Topmark</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__TOPMARK = ABSTRACT_COMMON_BUOY_BEACON__TOPMARK;

	/**
	 * The feature id for the '<em><b>On Submerged Rock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON__ON_SUBMERGED_ROCK = ABSTRACT_COMMON_BUOY_BEACON_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Beacon</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEACON_FEATURE_COUNT = ABSTRACT_COMMON_BUOY_BEACON_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.SpecialBuoyImpl <em>Special Buoy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.SpecialBuoyImpl
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getSpecialBuoy()
	 * @generated
	 */
	int SPECIAL_BUOY = 3;

	/**
	 * The feature id for the '<em><b>Lightcolor</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__LIGHTCOLOR = BUOY__LIGHTCOLOR;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__PERIOD = BUOY__PERIOD;

	/**
	 * The feature id for the '<em><b>Phase Characteristic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__PHASE_CHARACTERISTIC = BUOY__PHASE_CHARACTERISTIC;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__GROUP = BUOY__GROUP;

	/**
	 * The feature id for the '<em><b>Longitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__LONGITUDE = BUOY__LONGITUDE;

	/**
	 * The feature id for the '<em><b>Latitude</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__LATITUDE = BUOY__LATITUDE;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__PRECISION = BUOY__PRECISION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__NAME = BUOY__NAME;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__POSITION = BUOY__POSITION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__COLOR = BUOY__COLOR;

	/**
	 * The feature id for the '<em><b>Color Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__COLOR_TYPE = BUOY__COLOR_TYPE;

	/**
	 * The feature id for the '<em><b>Reflecting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__REFLECTING = BUOY__REFLECTING;

	/**
	 * The feature id for the '<em><b>Radarreflector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__RADARREFLECTOR = BUOY__RADARREFLECTOR;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__TYPE = BUOY__TYPE;

	/**
	 * The feature id for the '<em><b>Topmark</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__TOPMARK = BUOY__TOPMARK;

	/**
	 * The feature id for the '<em><b>Designation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__DESIGNATION = BUOY__DESIGNATION;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__SHAPE = BUOY__SHAPE;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__PURPOSE = BUOY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__PRIVATE = BUOY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Seasonal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY__SEASONAL = BUOY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Special Buoy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIAL_BUOY_FEATURE_COUNT = BUOY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.TopmarkImpl <em>Topmark</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.TopmarkImpl
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getTopmark()
	 * @generated
	 */
	int TOPMARK = 4;

	/**
	 * The feature id for the '<em><b>Topmark Parts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPMARK__TOPMARK_PARTS = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Topmark Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPMARK__TOPMARK_COLOR = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Topmark</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPMARK_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.buoysandbeacons.BuoyType <em>Buoy Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoyType
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getBuoyType()
	 * @generated
	 */
	int BUOY_TYPE = 5;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.buoysandbeacons.Shape <em>Shape</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Shape
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getShape()
	 * @generated
	 */
	int SHAPE = 6;


	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.buoysandbeacons.TopmarkType <em>Topmark Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.buoysandbeacons.TopmarkType
	 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getTopmarkType()
	 * @generated
	 */
	int TOPMARK_TYPE = 7;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon <em>Abstract Common Buoy Beacon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Common Buoy Beacon</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon
	 * @generated
	 */
	EClass getAbstractCommonBuoyBeacon();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getPosition()
	 * @see #getAbstractCommonBuoyBeacon()
	 * @generated
	 */
	EAttribute getAbstractCommonBuoyBeacon_Position();

	/**
	 * Returns the meta object for the attribute list '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Color</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getColor()
	 * @see #getAbstractCommonBuoyBeacon()
	 * @generated
	 */
	EAttribute getAbstractCommonBuoyBeacon_Color();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getColorType <em>Color Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color Type</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getColorType()
	 * @see #getAbstractCommonBuoyBeacon()
	 * @generated
	 */
	EAttribute getAbstractCommonBuoyBeacon_ColorType();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#isReflecting <em>Reflecting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reflecting</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#isReflecting()
	 * @see #getAbstractCommonBuoyBeacon()
	 * @generated
	 */
	EAttribute getAbstractCommonBuoyBeacon_Reflecting();

	/**
	 * Returns the meta object for the containment reference '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getTopmark <em>Topmark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Topmark</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getTopmark()
	 * @see #getAbstractCommonBuoyBeacon()
	 * @generated
	 */
	EReference getAbstractCommonBuoyBeacon_Topmark();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#isRadarreflector <em>Radarreflector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Radarreflector</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#isRadarreflector()
	 * @see #getAbstractCommonBuoyBeacon()
	 * @generated
	 */
	EAttribute getAbstractCommonBuoyBeacon_Radarreflector();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.AbstractCommonBuoyBeacon#getType()
	 * @see #getAbstractCommonBuoyBeacon()
	 * @generated
	 */
	EAttribute getAbstractCommonBuoyBeacon_Type();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.buoysandbeacons.Buoy <em>Buoy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Buoy</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Buoy
	 * @generated
	 */
	EClass getBuoy();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.Buoy#getDesignation <em>Designation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Designation</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Buoy#getDesignation()
	 * @see #getBuoy()
	 * @generated
	 */
	EAttribute getBuoy_Designation();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.Buoy#getShape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shape</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Buoy#getShape()
	 * @see #getBuoy()
	 * @generated
	 */
	EAttribute getBuoy_Shape();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.buoysandbeacons.Beacon <em>Beacon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Beacon</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Beacon
	 * @generated
	 */
	EClass getBeacon();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.Beacon#isOnSubmergedRock <em>On Submerged Rock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Submerged Rock</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Beacon#isOnSubmergedRock()
	 * @see #getBeacon()
	 * @generated
	 */
	EAttribute getBeacon_OnSubmergedRock();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy <em>Special Buoy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Special Buoy</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy
	 * @generated
	 */
	EClass getSpecialBuoy();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#getPurpose <em>Purpose</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Purpose</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#getPurpose()
	 * @see #getSpecialBuoy()
	 * @generated
	 */
	EAttribute getSpecialBuoy_Purpose();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#isPrivate <em>Private</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Private</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#isPrivate()
	 * @see #getSpecialBuoy()
	 * @generated
	 */
	EAttribute getSpecialBuoy_Private();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#getSeasonal <em>Seasonal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seasonal</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.SpecialBuoy#getSeasonal()
	 * @see #getSpecialBuoy()
	 * @generated
	 */
	EAttribute getSpecialBuoy_Seasonal();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.buoysandbeacons.Topmark <em>Topmark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Topmark</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Topmark
	 * @generated
	 */
	EClass getTopmark();

	/**
	 * Returns the meta object for the attribute list '{@link net.sf.seesea.model.int1.buoysandbeacons.Topmark#getTopmarkParts <em>Topmark Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Topmark Parts</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Topmark#getTopmarkParts()
	 * @see #getTopmark()
	 * @generated
	 */
	EAttribute getTopmark_TopmarkParts();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.buoysandbeacons.Topmark#getTopmarkColor <em>Topmark Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Topmark Color</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Topmark#getTopmarkColor()
	 * @see #getTopmark()
	 * @generated
	 */
	EAttribute getTopmark_TopmarkColor();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.buoysandbeacons.BuoyType <em>Buoy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Buoy Type</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoyType
	 * @generated
	 */
	EEnum getBuoyType();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.buoysandbeacons.Shape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Shape</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.Shape
	 * @generated
	 */
	EEnum getShape();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.buoysandbeacons.TopmarkType <em>Topmark Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Topmark Type</em>'.
	 * @see net.sf.seesea.model.int1.buoysandbeacons.TopmarkType
	 * @generated
	 */
	EEnum getTopmarkType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BuoysandbeaconsFactory getBuoysandbeaconsFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl <em>Abstract Common Buoy Beacon</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.AbstractCommonBuoyBeaconImpl
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getAbstractCommonBuoyBeacon()
		 * @generated
		 */
		EClass ABSTRACT_COMMON_BUOY_BEACON = eINSTANCE.getAbstractCommonBuoyBeacon();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMON_BUOY_BEACON__POSITION = eINSTANCE.getAbstractCommonBuoyBeacon_Position();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMON_BUOY_BEACON__COLOR = eINSTANCE.getAbstractCommonBuoyBeacon_Color();

		/**
		 * The meta object literal for the '<em><b>Color Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMON_BUOY_BEACON__COLOR_TYPE = eINSTANCE.getAbstractCommonBuoyBeacon_ColorType();

		/**
		 * The meta object literal for the '<em><b>Reflecting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMON_BUOY_BEACON__REFLECTING = eINSTANCE.getAbstractCommonBuoyBeacon_Reflecting();

		/**
		 * The meta object literal for the '<em><b>Topmark</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_COMMON_BUOY_BEACON__TOPMARK = eINSTANCE.getAbstractCommonBuoyBeacon_Topmark();

		/**
		 * The meta object literal for the '<em><b>Radarreflector</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMON_BUOY_BEACON__RADARREFLECTOR = eINSTANCE.getAbstractCommonBuoyBeacon_Radarreflector();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMON_BUOY_BEACON__TYPE = eINSTANCE.getAbstractCommonBuoyBeacon_Type();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.BuoyImpl <em>Buoy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoyImpl
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getBuoy()
		 * @generated
		 */
		EClass BUOY = eINSTANCE.getBuoy();

		/**
		 * The meta object literal for the '<em><b>Designation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUOY__DESIGNATION = eINSTANCE.getBuoy_Designation();

		/**
		 * The meta object literal for the '<em><b>Shape</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUOY__SHAPE = eINSTANCE.getBuoy_Shape();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.BeaconImpl <em>Beacon</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BeaconImpl
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getBeacon()
		 * @generated
		 */
		EClass BEACON = eINSTANCE.getBeacon();

		/**
		 * The meta object literal for the '<em><b>On Submerged Rock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEACON__ON_SUBMERGED_ROCK = eINSTANCE.getBeacon_OnSubmergedRock();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.SpecialBuoyImpl <em>Special Buoy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.SpecialBuoyImpl
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getSpecialBuoy()
		 * @generated
		 */
		EClass SPECIAL_BUOY = eINSTANCE.getSpecialBuoy();

		/**
		 * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIAL_BUOY__PURPOSE = eINSTANCE.getSpecialBuoy_Purpose();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIAL_BUOY__PRIVATE = eINSTANCE.getSpecialBuoy_Private();

		/**
		 * The meta object literal for the '<em><b>Seasonal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIAL_BUOY__SEASONAL = eINSTANCE.getSpecialBuoy_Seasonal();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.buoysandbeacons.impl.TopmarkImpl <em>Topmark</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.TopmarkImpl
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getTopmark()
		 * @generated
		 */
		EClass TOPMARK = eINSTANCE.getTopmark();

		/**
		 * The meta object literal for the '<em><b>Topmark Parts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPMARK__TOPMARK_PARTS = eINSTANCE.getTopmark_TopmarkParts();

		/**
		 * The meta object literal for the '<em><b>Topmark Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPMARK__TOPMARK_COLOR = eINSTANCE.getTopmark_TopmarkColor();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.buoysandbeacons.BuoyType <em>Buoy Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.buoysandbeacons.BuoyType
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getBuoyType()
		 * @generated
		 */
		EEnum BUOY_TYPE = eINSTANCE.getBuoyType();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.buoysandbeacons.Shape <em>Shape</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.buoysandbeacons.Shape
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getShape()
		 * @generated
		 */
		EEnum SHAPE = eINSTANCE.getShape();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.buoysandbeacons.TopmarkType <em>Topmark Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.buoysandbeacons.TopmarkType
		 * @see net.sf.seesea.model.int1.buoysandbeacons.impl.BuoysandbeaconsPackageImpl#getTopmarkType()
		 * @generated
		 */
		EEnum TOPMARK_TYPE = eINSTANCE.getTopmarkType();

	}

} //BuoysandbeaconsPackage
