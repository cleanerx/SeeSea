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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.Map#isLongitudeScale <em>Longitude Scale</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.Map#isLatitudeScale <em>Latitude Scale</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.Map#getZoomLevel <em>Zoom Level</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.Map#getMapCenterPosition <em>Map Center Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.seesea.model.core.geo.GeoPackage#getMap()
 * @model
 * @generated
 */
public interface Map extends EObject {
	/**
	 * Returns the value of the '<em><b>Longitude Scale</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Longitude Scale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Longitude Scale</em>' attribute.
	 * @see #setLongitudeScale(boolean)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getMap_LongitudeScale()
	 * @model default="true"
	 * @generated
	 */
	boolean isLongitudeScale();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.Map#isLongitudeScale <em>Longitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Longitude Scale</em>' attribute.
	 * @see #isLongitudeScale()
	 * @generated
	 */
	void setLongitudeScale(boolean value);

	/**
	 * Returns the value of the '<em><b>Latitude Scale</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Latitude Scale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Latitude Scale</em>' attribute.
	 * @see #setLatitudeScale(boolean)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getMap_LatitudeScale()
	 * @model default="true"
	 * @generated
	 */
	boolean isLatitudeScale();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.Map#isLatitudeScale <em>Latitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Latitude Scale</em>' attribute.
	 * @see #isLatitudeScale()
	 * @generated
	 */
	void setLatitudeScale(boolean value);

	/**
	 * Returns the value of the '<em><b>Zoom Level</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Zoom Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Zoom Level</em>' attribute.
	 * @see #setZoomLevel(int)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getMap_ZoomLevel()
	 * @model default="0"
	 * @generated
	 */
	int getZoomLevel();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.Map#getZoomLevel <em>Zoom Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zoom Level</em>' attribute.
	 * @see #getZoomLevel()
	 * @generated
	 */
	void setZoomLevel(int value);

	/**
	 * Returns the value of the '<em><b>Map Center Position</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map Center Position</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map Center Position</em>' reference.
	 * @see #setMapCenterPosition(GeoPosition)
	 * @see net.sf.seesea.model.core.geo.GeoPackage#getMap_MapCenterPosition()
	 * @model
	 * @generated
	 */
	GeoPosition getMapCenterPosition();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.Map#getMapCenterPosition <em>Map Center Position</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Center Position</em>' reference.
	 * @see #getMapCenterPosition()
	 * @generated
	 */
	void setMapCenterPosition(GeoPosition value);

} // Map
