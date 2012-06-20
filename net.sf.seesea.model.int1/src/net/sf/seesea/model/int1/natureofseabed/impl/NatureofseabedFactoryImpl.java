/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.natureofseabed.impl;

import net.sf.seesea.model.int1.natureofseabed.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NatureofseabedFactoryImpl extends EFactoryImpl implements NatureofseabedFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NatureofseabedFactory init() {
		try {
			NatureofseabedFactory theNatureofseabedFactory = (NatureofseabedFactory)EPackage.Registry.INSTANCE.getEFactory("natureofseabed"); 
			if (theNatureofseabedFactory != null) {
				return theNatureofseabedFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new NatureofseabedFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NatureofseabedFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case NatureofseabedPackage.SPRING_IN_SEABED: return createSpringInSeabed();
			case NatureofseabedPackage.NATURE_OF_SEABED: return createNatureOfSeabed();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case NatureofseabedPackage.SEABED_TYPE:
				return createSeabedTypeFromString(eDataType, initialValue);
			case NatureofseabedPackage.QUALIFYING_SEABED_NATURE:
				return createQualifyingSeabedNatureFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case NatureofseabedPackage.SEABED_TYPE:
				return convertSeabedTypeToString(eDataType, instanceValue);
			case NatureofseabedPackage.QUALIFYING_SEABED_NATURE:
				return convertQualifyingSeabedNatureToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpringInSeabed createSpringInSeabed() {
		SpringInSeabedImpl springInSeabed = new SpringInSeabedImpl();
		return springInSeabed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NatureOfSeabed createNatureOfSeabed() {
		NatureOfSeabedImpl natureOfSeabed = new NatureOfSeabedImpl();
		return natureOfSeabed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeabedType createSeabedTypeFromString(EDataType eDataType, String initialValue) {
		SeabedType result = SeabedType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSeabedTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualifyingSeabedNature createQualifyingSeabedNatureFromString(EDataType eDataType, String initialValue) {
		QualifyingSeabedNature result = QualifyingSeabedNature.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertQualifyingSeabedNatureToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NatureofseabedPackage getNatureofseabedPackage() {
		return (NatureofseabedPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static NatureofseabedPackage getPackage() {
		return NatureofseabedPackage.eINSTANCE;
	}

} //NatureofseabedFactoryImpl
