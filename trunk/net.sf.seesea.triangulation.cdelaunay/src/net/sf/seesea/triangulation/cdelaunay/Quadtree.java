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
package net.sf.seesea.triangulation.cdelaunay;

import java.util.ArrayList;
import java.util.List;

import net.sf.seesea.geometry.IBoxExtends;
import net.sf.seesea.geometry.IRectangle;
import net.sf.seesea.geometry.impl.Rectangle;


/**
 * A 2D Quadtree implementation that is a spatial index structure.
 * 
 * @param <T>
 */
public class Quadtree<T extends IBoxExtends> {

	 private int MAX_OBJECTS = 1000;
	  private int MAX_LEVELS = 20;
	 
	  private int level;
	  private List<T> objects;
	  private IRectangle bounds;
	  private Quadtree[] nodes;
	 
	 /*
	  * Constructor
	  */
	  public Quadtree(int pLevel, IRectangle pBounds) {
	   level = pLevel;
	   objects = new ArrayList<>();
	   bounds = pBounds;
	   nodes = new Quadtree[4];
	  }
	  
	  /*
	   * Clears the quadtree
	   */
	   public void clear() {
	     objects.clear();
	   
	     for (int i = 0; i < nodes.length; i++) {
	       if (nodes[i] != null) {
	         nodes[i].clear();
	         nodes[i] = null;
	       }
	     }
	   }
	   
	   /*
	    * Splits the node into 4 subnodes
	    */
	    private void split() {
//	    	   System.out.println("split:" + bounds);

	      double subWidth = (bounds.getWidth() / 2);
	      double subHeight = (bounds.getHeight() / 2);
	      double x = bounds.getX();
	      double y = bounds.getY();
	    
	      nodes[0] = new Quadtree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
	      nodes[1] = new Quadtree(level+1, new Rectangle(x, y, subWidth, subHeight));
	      nodes[2] = new Quadtree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
	      nodes[3] = new Quadtree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
	    }
	    
	    /*
	     * Determine which node the object belongs to. -1 means
	     * object cannot completely fit within a child node and is part
	     * of the parent node
	     */
	     private int getIndex(IBoxExtends boundingBox) {
	    	 IRectangle pRect = boundingBox.getBoundingBox();
	       int index = -1;
	       double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
	       double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
	     
	       // Object can completely fit within the top quadrants
	       boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect.getY() + pRect.getHeight() < horizontalMidpoint);
	       // Object can completely fit within the bottom quadrants
	       boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);
	     
	       // Object can completely fit within the left quadrants
	       if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
	          if (topQuadrant) {
	            index = 1;
	          }
	          else if (bottomQuadrant) {
	            index = 2;
	          }
	        }
	        // Object can completely fit within the right quadrants
	        else if (pRect.getX() > verticalMidpoint) {
	         if (topQuadrant) {
	           index = 0;
	         }
	         else if (bottomQuadrant) {
	           index = 3;
	         }
	       }
	     
	       return index;
	     }

	      public void remove(T pRect) {
//	    	   System.out.println("remove:" + pRect);
		        if (nodes[0] != null) {
			          int index = getIndex(pRect);
			      
			          if (index != -1) {
			            nodes[index].remove(pRect);
			      
			            return;
			          }
			        }
		        boolean remove = objects.remove(pRect);
		        if(!remove)
		        System.out.println(pRect);
	      }
	     
	     /*
	      * Insert the object into the quadtree. If the node
	      * exceeds the capacity, it will split and add all
	      * objects to their corresponding nodes.
	      */
	      public void insert(T pRect) {
//	    	   System.out.println("insert:" + pRect);
	        if (nodes[0] != null) {
	          int index = getIndex(pRect);
	      
	          if (index != -1) {
	            nodes[index].insert(pRect);
	      
	            return;
	          }
	        }
	      
	        objects.add(pRect);
	      
	        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
	           if (nodes[0] == null) { 
	              split(); 
	           }
	      
	          int i = 0;
	          while (i < objects.size()) {
	            int index = getIndex(objects.get(i));
	            if (index != -1) {
	              nodes[index].insert(objects.remove(i));
	            }
	            else {
	              i++;
	            }
	          }
	        }
	      }
	      
	      /*
	       * Return all objects that could collide with the given object
	       */
	       public void retrieve(List<T> returnObjects, IBoxExtends pRect) {
//	    	   System.out.println("ret:" + pRect);
	         int index = getIndex(pRect);
	         if (index != -1 && nodes[0] != null) {
//	        	 System.out.println("->" + index + ":" + bounds);
	           nodes[index].retrieve(returnObjects, pRect);
	         }
	       
	         returnObjects.addAll(objects);
	       }
	       
	       public int size() {
	    	   if(nodes[0] == null) {
	    		   return objects.size();
	    	   }
	    	   return objects.size() + nodes[0].size() + nodes[1].size() + nodes[2].size() + nodes[3].size();   
	       }
	      
	       public void getObjectLocation(T object) {
	    	   
	       }

	       public int getObjectLocation2(T object) {
	    	   if(objects.contains(object)) {
//				   System.out.println("->-1:" + bounds); 
	    		   return -1;
	    	   } else {
	    		   if(nodes[0] != null) {
	    			   for (int i = 0; i < nodes.length; i++) {
	    				   int objectLocation2 = nodes[i].getObjectLocation2(object);
	    				   if(objectLocation2 != -2) {
//	    					   System.out.println("->" + i + ":" + bounds); 
	    					   return i;
	    				   }
	    			   }
	    		   }
	    	   }
	    	   return -2;
	       }

	       public void stats(List<Integer> sizes) {
    		   if(nodes[0] != null) {
    			   for (int i = 0; i < nodes.length; i++) {
    				   sizes.add(nodes[i].objects.size());
    				   nodes[i].stats(sizes);
    			   }
    		   }
	       }
	       
}
