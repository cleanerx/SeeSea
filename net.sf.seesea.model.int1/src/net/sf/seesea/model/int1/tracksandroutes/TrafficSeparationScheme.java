/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Traffic Separation Scheme</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme#getTrafficSeparations <em>Traffic Separations</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme#getTrafficDirection <em>Traffic Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getTrafficSeparationScheme()
 * @model
 * @generated
 */
public interface TrafficSeparationScheme extends AbstractSeaWay {
	/**
	 * Returns the value of the '<em><b>Traffic Separations</b></em>' containment reference list.
	 * The list contents are of type {@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traffic Separations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traffic Separations</em>' containment reference list.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getTrafficSeparationScheme_TrafficSeparations()
	 * @model containment="true"
	 * @generated
	 */
	EList<TrafficSeparation> getTrafficSeparations();

	/**
	 * Returns the value of the '<em><b>Traffic Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link net.sf.seesea.model.int1.tracksandroutes.TrafficDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traffic Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traffic Direction</em>' attribute.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficDirection
	 * @see #setTrafficDirection(TrafficDirection)
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getTrafficSeparationScheme_TrafficDirection()
	 * @model
	 * @generated
	 */
	TrafficDirection getTrafficDirection();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.tracksandroutes.TrafficSeparationScheme#getTrafficDirection <em>Traffic Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Traffic Direction</em>' attribute.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficDirection
	 * @see #getTrafficDirection()
	 * @generated
	 */
	void setTrafficDirection(TrafficDirection value);

} // TrafficSeparationScheme
