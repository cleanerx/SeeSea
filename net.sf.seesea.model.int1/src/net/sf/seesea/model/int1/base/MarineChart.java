/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.base;

import net.sf.seesea.model.core.geo.Chart;

import net.sf.seesea.model.core.geo.osm.World;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Marine Chart</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.base.MarineChart#getSeamarks <em>Seamarks</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.base.BasePackage#getMarineChart()
 * @model
 * @generated
 */
public interface MarineChart extends Chart, World {
	/**
	 * Returns the value of the '<em><b>Seamarks</b></em>' containment reference list.
	 * The list contents are of type {@link net.sf.seesea.model.int1.base.AbstractSeamark}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seamarks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seamarks</em>' containment reference list.
	 * @see net.sf.seesea.model.int1.base.BasePackage#getMarineChart_Seamarks()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractSeamark> getSeamarks();

} // MarineChart
