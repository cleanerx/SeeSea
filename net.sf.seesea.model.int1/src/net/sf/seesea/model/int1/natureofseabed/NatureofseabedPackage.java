/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.natureofseabed;

import net.sf.seesea.model.core.CorePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

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
 * @see net.sf.seesea.model.int1.natureofseabed.NatureofseabedFactory
 * @model kind="package"
 * @generated
 */
public interface NatureofseabedPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "natureofseabed";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "natureofseabed";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "natureofseabed";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NatureofseabedPackage eINSTANCE = net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.natureofseabed.impl.SpringInSeabedImpl <em>Spring In Seabed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.natureofseabed.impl.SpringInSeabedImpl
	 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl#getSpringInSeabed()
	 * @generated
	 */
	int SPRING_IN_SEABED = 0;

	/**
	 * The number of structural features of the '<em>Spring In Seabed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPRING_IN_SEABED_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.natureofseabed.impl.NatureOfSeabedImpl <em>Nature Of Seabed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureOfSeabedImpl
	 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl#getNatureOfSeabed()
	 * @generated
	 */
	int NATURE_OF_SEABED = 1;

	/**
	 * The feature id for the '<em><b>Seabed Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURE_OF_SEABED__SEABED_TYPE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Seabed Nature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURE_OF_SEABED__SEABED_NATURE = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Nature Of Seabed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURE_OF_SEABED_FEATURE_COUNT = CorePackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.natureofseabed.SeabedType <em>Seabed Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.natureofseabed.SeabedType
	 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl#getSeabedType()
	 * @generated
	 */
	int SEABED_TYPE = 2;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature <em>Qualifying Seabed Nature</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature
	 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl#getQualifyingSeabedNature()
	 * @generated
	 */
	int QUALIFYING_SEABED_NATURE = 3;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.natureofseabed.SpringInSeabed <em>Spring In Seabed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Spring In Seabed</em>'.
	 * @see net.sf.seesea.model.int1.natureofseabed.SpringInSeabed
	 * @generated
	 */
	EClass getSpringInSeabed();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed <em>Nature Of Seabed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nature Of Seabed</em>'.
	 * @see net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed
	 * @generated
	 */
	EClass getNatureOfSeabed();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed#getSeabedType <em>Seabed Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seabed Type</em>'.
	 * @see net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed#getSeabedType()
	 * @see #getNatureOfSeabed()
	 * @generated
	 */
	EAttribute getNatureOfSeabed_SeabedType();

	/**
	 * Returns the meta object for the attribute '{@link net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed#getSeabedNature <em>Seabed Nature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seabed Nature</em>'.
	 * @see net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed#getSeabedNature()
	 * @see #getNatureOfSeabed()
	 * @generated
	 */
	EAttribute getNatureOfSeabed_SeabedNature();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.natureofseabed.SeabedType <em>Seabed Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Seabed Type</em>'.
	 * @see net.sf.seesea.model.int1.natureofseabed.SeabedType
	 * @generated
	 */
	EEnum getSeabedType();

	/**
	 * Returns the meta object for enum '{@link net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature <em>Qualifying Seabed Nature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Qualifying Seabed Nature</em>'.
	 * @see net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature
	 * @generated
	 */
	EEnum getQualifyingSeabedNature();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NatureofseabedFactory getNatureofseabedFactory();

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
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.natureofseabed.impl.SpringInSeabedImpl <em>Spring In Seabed</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.natureofseabed.impl.SpringInSeabedImpl
		 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl#getSpringInSeabed()
		 * @generated
		 */
		EClass SPRING_IN_SEABED = eINSTANCE.getSpringInSeabed();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.natureofseabed.impl.NatureOfSeabedImpl <em>Nature Of Seabed</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureOfSeabedImpl
		 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl#getNatureOfSeabed()
		 * @generated
		 */
		EClass NATURE_OF_SEABED = eINSTANCE.getNatureOfSeabed();

		/**
		 * The meta object literal for the '<em><b>Seabed Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NATURE_OF_SEABED__SEABED_TYPE = eINSTANCE.getNatureOfSeabed_SeabedType();

		/**
		 * The meta object literal for the '<em><b>Seabed Nature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NATURE_OF_SEABED__SEABED_NATURE = eINSTANCE.getNatureOfSeabed_SeabedNature();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.natureofseabed.SeabedType <em>Seabed Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.natureofseabed.SeabedType
		 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl#getSeabedType()
		 * @generated
		 */
		EEnum SEABED_TYPE = eINSTANCE.getSeabedType();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature <em>Qualifying Seabed Nature</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature
		 * @see net.sf.seesea.model.int1.natureofseabed.impl.NatureofseabedPackageImpl#getQualifyingSeabedNature()
		 * @generated
		 */
		EEnum QUALIFYING_SEABED_NATURE = eINSTANCE.getQualifyingSeabedNature();

	}

} //NatureofseabedPackage
