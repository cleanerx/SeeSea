/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.natureofseabed;

import net.sf.seesea.model.core.ModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nature Of Seabed</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed#getSeabedType <em>Seabed Type</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed#getSeabedNature <em>Seabed Nature</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage#getNatureOfSeabed()
 * @model
 * @generated
 */
public interface NatureOfSeabed extends ModelObject {
	/**
	 * Returns the value of the '<em><b>Seabed Type</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.natureofseabed.SeabedType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seabed Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seabed Type</em>' attribute.
	 * @see net.sf.seesea.model.int1.natureofseabed.SeabedType
	 * @see #setSeabedType(SeabedType)
	 * @see net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage#getNatureOfSeabed_SeabedType()
	 * @model required="true"
	 * @generated
	 */
	SeabedType getSeabedType();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed#getSeabedType <em>Seabed Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seabed Type</em>' attribute.
	 * @see net.sf.seesea.model.int1.natureofseabed.SeabedType
	 * @see #getSeabedType()
	 * @generated
	 */
	void setSeabedType(SeabedType value);

	/**
	 * Returns the value of the '<em><b>Seabed Nature</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seabed Nature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seabed Nature</em>' attribute.
	 * @see net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature
	 * @see #setSeabedNature(QualifyingSeabedNature)
	 * @see net.sf.seesea.model.int1.natureofseabed.NatureofseabedPackage#getNatureOfSeabed_SeabedNature()
	 * @model
	 * @generated
	 */
	QualifyingSeabedNature getSeabedNature();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.natureofseabed.NatureOfSeabed#getSeabedNature <em>Seabed Nature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seabed Nature</em>' attribute.
	 * @see net.sf.seesea.model.int1.natureofseabed.QualifyingSeabedNature
	 * @see #getSeabedNature()
	 * @generated
	 */
	void setSeabedNature(QualifyingSeabedNature value);

} // NatureOfSeabed
