/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.int1.tracksandroutes;

import net.sf.seesea.model.core.geo.ChartArea;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deep Water Route</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute#getTrafficDirection <em>Traffic Direction</em>}</li>
 *   <li>{@link net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute#getRouteArea <em>Route Area</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getDeepWaterRoute()
 * @model
 * @generated
 */
public interface DeepWaterRoute extends AbstractSeaWay {
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
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getDeepWaterRoute_TrafficDirection()
	 * @model
	 * @generated
	 */
	TrafficDirection getTrafficDirection();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute#getTrafficDirection <em>Traffic Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Traffic Direction</em>' attribute.
	 * @see net.sf.seesea.model.int1.tracksandroutes.TrafficDirection
	 * @see #getTrafficDirection()
	 * @generated
	 */
	void setTrafficDirection(TrafficDirection value);

	/**
	 * Returns the value of the '<em><b>Route Area</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route Area</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route Area</em>' reference.
	 * @see #setRouteArea(ChartArea)
	 * @see net.sf.seesea.model.int1.tracksandroutes.TracksandroutesPackage#getDeepWaterRoute_RouteArea()
	 * @model required="true"
	 * @generated
	 */
	ChartArea getRouteArea();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.int1.tracksandroutes.DeepWaterRoute#getRouteArea <em>Route Area</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Route Area</em>' reference.
	 * @see #getRouteArea()
	 * @generated
	 */
	void setRouteArea(ChartArea value);

} // DeepWaterRoute
