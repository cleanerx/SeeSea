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
package net.sf.seesea.model.core.geo.osm;

import net.sf.seesea.model.core.geo.Chart;
import net.sf.seesea.model.core.geo.GeoPosition;

import net.sf.seesea.model.core.geo.NavigationCompound;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Area</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.Area#getZoomLevel <em>Zoom Level</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.Area#getMapCenterPosition <em>Map Center Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.Area#getSubArea <em>Sub Area</em>}</li>
 * </ul>
 *
 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getArea()
 * @model
 * @generated
 */
public interface Area extends Chart, NavigationCompound {
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
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getArea_ZoomLevel()
	 * @model default="0"
	 * @generated
	 */
	int getZoomLevel();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.osm.Area#getZoomLevel <em>Zoom Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zoom Level</em>' attribute.
	 * @see #getZoomLevel()
	 * @generated
	 */
	void setZoomLevel(int value);

	/**
	 * Returns the value of the '<em><b>Map Center Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map Center Position</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map Center Position</em>' containment reference.
	 * @see #setMapCenterPosition(GeoPosition)
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getArea_MapCenterPosition()
	 * @model containment="true"
	 * @generated
	 */
	GeoPosition getMapCenterPosition();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.osm.Area#getMapCenterPosition <em>Map Center Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Center Position</em>' containment reference.
	 * @see #getMapCenterPosition()
	 * @generated
	 */
	void setMapCenterPosition(GeoPosition value);

	/**
	 * Returns the value of the '<em><b>Sub Area</b></em>' containment reference list.
	 * The list contents are of type {@link net.sf.seesea.model.core.geo.osm.Area}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Area</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Area</em>' containment reference list.
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getArea_SubArea()
	 * @model containment="true"
	 * @generated
	 */
	EList<Area> getSubArea();

} // Area
