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

package net.sf.seesea.contour.api;

import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.contour.api.IContourLine;
import net.sf.seesea.geometry.IPoint;

public class ContourLine implements IContourLine {

	private List<IPoint> points;
	private long id;
	private int depth;
	
	public ContourLine() {
		points = new ArrayList<IPoint>();
	}
	
	/* (non-Javadoc)
	 * @see net.sf.seesea.data.postprocessing.triangulation.persistence.IContourLine#getPoints()
	 */
	@Override
	public List<IPoint> getPoints() {
		return points;
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.data.postprocessing.triangulation.persistence.IContourLine#setId(long)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.data.postprocessing.triangulation.persistence.IContourLine#setDepth(int)
	 */
	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.data.postprocessing.triangulation.persistence.IContourLine#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.data.postprocessing.triangulation.persistence.IContourLine#getDepth()
	 */
	@Override
	public int getDepth() {
		return depth;
	}
	
	
	

}
