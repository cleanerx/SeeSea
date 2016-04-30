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

import net.sf.seesea.model.core.geo.AnchorPosition;
import net.sf.seesea.model.core.geo.GeoPosition;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>World</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.World#isLongitudeScale <em>Longitude Scale</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.World#isLatitudeScale <em>Latitude Scale</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.World#getAnchorPosition <em>Anchor Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.World#getCursorPosition <em>Cursor Position</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.World#getTrip <em>Trip</em>}</li>
 *   <li>{@link net.sf.seesea.model.core.geo.osm.World#getTotalTrip <em>Total Trip</em>}</li>
 * </ul>
 *
 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getWorld()
 * @model
 * @generated
 */
public interface World extends Area {
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
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getWorld_LongitudeScale()
	 * @model default="true"
	 * @generated
	 */
	boolean isLongitudeScale();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.osm.World#isLongitudeScale <em>Longitude Scale</em>}' attribute.
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
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getWorld_LatitudeScale()
	 * @model default="true"
	 * @generated
	 */
	boolean isLatitudeScale();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.osm.World#isLatitudeScale <em>Latitude Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Latitude Scale</em>' attribute.
	 * @see #isLatitudeScale()
	 * @generated
	 */
	void setLatitudeScale(boolean value);

	/**
	 * Returns the value of the '<em><b>Anchor Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anchor Position</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anchor Position</em>' containment reference.
	 * @see #setAnchorPosition(AnchorPosition)
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getWorld_AnchorPosition()
	 * @model containment="true"
	 * @generated
	 */
	AnchorPosition getAnchorPosition();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.osm.World#getAnchorPosition <em>Anchor Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anchor Position</em>' containment reference.
	 * @see #getAnchorPosition()
	 * @generated
	 */
	void setAnchorPosition(AnchorPosition value);

	/**
	 * Returns the value of the '<em><b>Cursor Position</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cursor Position</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cursor Position</em>' containment reference.
	 * @see #setCursorPosition(GeoPosition)
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getWorld_CursorPosition()
	 * @model containment="true"
	 * @generated
	 */
	GeoPosition getCursorPosition();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.osm.World#getCursorPosition <em>Cursor Position</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cursor Position</em>' containment reference.
	 * @see #getCursorPosition()
	 * @generated
	 */
	void setCursorPosition(GeoPosition value);

	/**
	 * Returns the value of the '<em><b>Trip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trip</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trip</em>' attribute.
	 * @see #setTrip(double)
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getWorld_Trip()
	 * @model
	 * @generated
	 */
	double getTrip();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.osm.World#getTrip <em>Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trip</em>' attribute.
	 * @see #getTrip()
	 * @generated
	 */
	void setTrip(double value);

	/**
	 * Returns the value of the '<em><b>Total Trip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Trip</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Trip</em>' attribute.
	 * @see #setTotalTrip(double)
	 * @see net.sf.seesea.model.core.geo.osm.OsmPackage#getWorld_TotalTrip()
	 * @model
	 * @generated
	 */
	double getTotalTrip();

	/**
	 * Sets the value of the '{@link net.sf.seesea.model.core.geo.osm.World#getTotalTrip <em>Total Trip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Trip</em>' attribute.
	 * @see #getTotalTrip()
	 * @generated
	 */
	void setTotalTrip(double value);

} // World
