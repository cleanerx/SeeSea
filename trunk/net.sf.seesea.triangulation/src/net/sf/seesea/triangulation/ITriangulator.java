/**
Copyright (c) 2013-2015, Jens Kübler
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
 *
 */
package net.sf.seesea.triangulation;

import java.util.List;

import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.ITriangle;


/**
 * This inteface describes a triangulation
 */
public interface ITriangulator {

	/**
	 * 
	 * @param point incrementally add new points
	 */
	void addPoint(IPoint point);

	/**
	 * adds holes to a triangulation set to have a boundary
	 * 
	 * @param holes
	 */
	void addHoles(List<IPolygon> holes);

	/**
	 * adds a boundary to the triangulation
	 * 
	 * @param boundary
	 */
	void addBoundary(IPolygon boundary);

	/**
	 * This method is designed not to be a return type of {@link ITriangulator#triangulate(ITriangulationPersistence)} for debugging purposes.<br>
	 * This way the triangulation may be persisted for reference while the triangulation is still running.
	 * 
	 * @return the triangles produces the the triangulator.
	 */
	List<ITriangle> getTriangulation();

	/**
	 * @param postgisTriangulationPersistence may be used for debugging purposes to persist the triangulation during the process 
	 * 
	 */
	void triangulate(ITriangulationPersistence triangulationPersistence);
	
	/**
	 * loads an existing triangulation into memory to add additional points
	 * 
	 * @param points
	 * @param constraintEdges
	 */
	void loadTriangulation(List<ITriangle> triangles, IPolygon boundary, List<IPolygon> holes);

	/**
	 * updates an existing triangulation that was previously loaded with {@link ITriangulator#loadTriangulation(List, IPolygon, List)}
	 * @param points
	 */
	void updateTriangulation(List<IPoint> points);
}
