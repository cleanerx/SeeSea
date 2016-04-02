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
package net.sf.seesea.model.core.geo;

import net.sf.seesea.model.core.ModelObject;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Compound</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.NavigationCompound#getPoiContainer <em>Poi Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.NavigationCompound#getRoutingContainer <em>Routing Container</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.NavigationCompound#getTracksContainer <em>Tracks Container</em>}</li>
 * </ul>
 *
 * @see net.sf.seesea.model.core.geo.GeoPackage#getNavigationCompound()
 * @model
 * @generated
 */
public interface NavigationCompound extends ModelObject {
	/**
	 * Returns the value of the '<em><b>Poi Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Poi Container</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Poi Container</em>' containment reference.
	 * @see #setPoiContainer(POIContainer)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getNavigationCompound_PoiContainer()
	 * @model containment="true"
	 * @generated
	 */
	POIContainer getPoiContainer();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.NavigationCompound#getPoiContainer <em>Poi Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Poi Container</em>' containment reference.
	 * @see #getPoiContainer()
	 * @generated
	 */
	void setPoiContainer(POIContainer value);

	/**
	 * Returns the value of the '<em><b>Routing Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Routing Container</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Routing Container</em>' containment reference.
	 * @see #setRoutingContainer(RoutingContainer)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getNavigationCompound_RoutingContainer()
	 * @model containment="true"
	 * @generated
	 */
	RoutingContainer getRoutingContainer();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.NavigationCompound#getRoutingContainer <em>Routing Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Routing Container</em>' containment reference.
	 * @see #getRoutingContainer()
	 * @generated
	 */
	void setRoutingContainer(RoutingContainer value);

	/**
	 * Returns the value of the '<em><b>Tracks Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tracks Container</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tracks Container</em>' containment reference.
	 * @see #setTracksContainer(TracksContainer)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getNavigationCompound_TracksContainer()
	 * @model containment="true"
	 * @generated
	 */
	TracksContainer getTracksContainer();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.NavigationCompound#getTracksContainer <em>Tracks Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tracks Container</em>' containment reference.
	 * @see #getTracksContainer()
	 * @generated
	 */
	void setTracksContainer(TracksContainer value);

} // NavigationCompound
