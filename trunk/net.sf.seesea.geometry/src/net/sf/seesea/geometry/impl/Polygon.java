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
package net.sf.seesea.geometry.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.seesea.geometry.IPoint;
import net.sf.seesea.geometry.IPolygon;
import net.sf.seesea.geometry.IRectangle;


public class Polygon implements IPolygon {

	private List<IPoint> _points;

	public Polygon(IPoint... points) {
		_points = Arrays.asList(points);;
	}

	public Polygon(List<IPoint> points) {
		_points = points;
	}
	
	public Polygon() {
		_points = new ArrayList<IPoint>();
	}

	public void addPoint(IPoint point) {
		_points.add(point);
	}
	
	@Override
	public IRectangle getBoundingBox() {
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;
		for (IPoint point : _points) {
			if(point.getX() < minX) {
				minX = point.getX();
			}
			if(point.getX() > maxX ) {
				maxX = point.getX();
			}
			if(point.getY() < minY) {
				minY = point.getY();
			}
			if(point.getY() > maxY ) {
				maxY = point.getY();
			}
		}
		return new Rectangle(minX, minY, maxX - minX, maxY - minY);
	}

	@Override
	public List<IPoint> getPoints() {
		return _points;
	}

	@Override
	public String toString() {
		return "[_points=" + _points + "]";
	}

	@Override
	public boolean contains(IPoint point) {
		if (getPoints().size() < 3)
	         return false;

		int i;
	    int inside = 0;

	    if(new DirectedEdge(getPoints().get(0), getPoints().get(1)).isOnEdge(point)) {
	    	return true;
	    }
	    if(new DirectedEdge(getPoints().get(1), getPoints().get(2)).isOnEdge(point)) {
	    	return true;
	    }
	    if(new DirectedEdge(getPoints().get(2), getPoints().get(0)).isOnEdge(point)) {
	    	return true;
	    }
	    
	    for(i = 0; i < getPoints().size() - 1; i++)
	    {
	      double px1 = getPoints().get(i).getX();
	      double px2 = getPoints().get(i + 1).getX();
	      double py1 = getPoints().get(i).getY();
	      double py2 = getPoints().get(i + 1).getY();

	      if(py1 <= point.getY())
	      {
	        if((py2 > point.getY()) && (((px2 - px1) * (point.getY() - py1) - (point.getX() - px1) * (py2 - py1)) > 0)) {
	        	inside++;
	        }
	      }
	      else
	      {
	        if((py2 <= point.getY()) && (((px2 - px1) * (point.getY() - py1) - (point.getX() - px1) * (py2 - py1)) < 0)) {
	        	inside++;
	        }
	      }
	    }

	    if((inside & 1) > 0)
	      return true;
	    else
	      return false;
	    }
	
	
	@Override
	public boolean containsAll(List<IPoint> points) {
		for (IPoint point : points) {
			if(!contains(point)) {
				return false;
			}
		}
		return true;
	}
	

}
