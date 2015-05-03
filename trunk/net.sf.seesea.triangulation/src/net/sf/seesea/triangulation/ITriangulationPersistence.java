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

import java.util.Iterator;
import java.util.List;

import net.sf.seesea.data.io.PersistenceException;
import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.ITriangle;


public interface ITriangulationPersistence {

	/**
	 * This methods calculates the partitions that the recorded track crosses. 
	 * 
	 * @param trackId the track id to determine the partitions
	 * @param trackpointTable the sql table to use for reading measured points
	 * @return A {@link PolygonIterator} that iterates over the existing partitions
	 * @throws PersistenceException
	 */
	Iterator<List<IPolygon>> getHitPartitionizedPolygons(Long trackId, String trackpointTable)
			throws PersistenceException;

	/**
	 * 
	 * 
	 * @param hitPartitionizedPolygons
	 * @param trackpointTable
	 * @return
	 * @throws PersistenceException
	 */
	List<ITriangulationDescription> updateContainedTriangulations(Iterator<List<IPolygon>> hitPartitionizedPolygons, String trackpointTable) throws PersistenceException;

	/**
	 * persists the given triangulation
	 * 
	 * @param triangulationResult the triangulation to persist
	 * @param triangulationDescription the description with boundaries and holes
	 * @throws PersistenceException
	 */
	void persistTriangulation(List<ITriangle> triangulationResult, ITriangulationDescription triangulationDescription) throws PersistenceException;
	
	/**
	 * This method is used to split contour line that were merged over delaunay triangulation partitions. 
	 * The result of the operation is that no contour line crosses the given boundary. 
	 * 
	 * @param trackId
	 * @param boundary the boundary of the contour line
	 * @throws PersistenceException if a persistence error occurs
	 */
	void splitMergedContourLines(Long trackId, IPolygon boundary, List<NeighboringTrianglesOnBoundary> boundaryTrianglePairs) throws PersistenceException;

	/**
	 * This method creates a new contour line with the given points or updates
	 * 
	 * @param points the points of the contour line
	 * @param depth the depth of the contour line
	 * @param trackId the track id causing the update
	 * @param boundary the partition boundary 
	 * @param boundaryTrianglePairs 
	 * @throws PersistenceException
	 */
	void addOrUpdateContourLine(List<IPoint> points, Integer depth, Long trackId, IPolygon boundary, List<NeighboringTrianglesOnBoundary> boundaryTrianglePairs) throws PersistenceException;

	/**
	 * Removes contours lines in the given polygon with holes
	 * 
	 * @param polygon the boundary polygon
	 * @param holes the holes in the boundary polygon
	 * @throws PersistenceException
	 */
	void removeContourLines(IPolygon polygon, List<IPolygon> holes) throws PersistenceException;


	/**
	 * 
	 * @return the first polygon is the bounding polygon where the rest are contained polygons
	 */
	Iterator<List<IPolygon>> getRiverBoundaryPolygonsIterator();
	
	List<IPolygon> get100mBoundaryPolygons();
	
	List<IPolygon> getCostalBorderBoundaryHoles(IPolygon boundary100m);
	
	List<ITriangulationDescription> getPartitionizedTriangulations(Long trackId, String trackpointTable) throws PersistenceException;

	List<NeighboringTrianglesOnBoundary> getBoundaryTrianglePairs(IPolygon boundaryPolygon) throws PersistenceException;

	void finishAddOrUpdateContourLines() throws PersistenceException;



}
