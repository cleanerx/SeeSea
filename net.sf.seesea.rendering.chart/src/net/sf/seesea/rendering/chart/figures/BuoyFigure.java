/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.rendering.chart.figures;

import java.util.List;

import net.sf.seesea.model.int1.buoysandbeacons.BuoyType;
import net.sf.seesea.model.int1.buoysandbeacons.Shape;
import net.sf.seesea.model.int1.buoysandbeacons.Topmark;
import net.sf.seesea.model.int1.buoysandbeacons.TopmarkType;
import net.sf.seesea.model.int1.lights.Orientation;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public class BuoyFigure extends GeoFigure {

	private String colorText;
	
	private List<Color> buoyColor;
	
	private Orientation colorOrientation;
	
	private String lightingID;

	private Color lightingColor;

	private Topmark topmark;

	private BuoyType buoyType;

	private Shape shape;

	private boolean radarreflecting;
	
	private final boolean drawColored = true;
	
	@Override
	protected void paintFigure(Graphics graphics) {
		
		Rectangle r = getBounds().getCopy();
		graphics.setAntialias(SWT.ON);
//		graphics.scale(2);
		graphics.setForegroundColor(ColorConstants.black);
		if(buoyColor != null && buoyColor.size() == 1) {
			graphics.setBackgroundColor(buoyColor.get(0));
		} else {
			graphics.setBackgroundColor(ColorConstants.black);
		}
//		graphics.fillRectangle(r);
		int xoffset = 0;
		int yoffset = 0;
		BuoyBoundsSpec buoySpec = null;
		Dimension topmarkShift;
		topmarkShift = getTopmarkShift().getNegated();
		topmarkShift = getShapeShift().getNegated().expand(topmarkShift);
		switch (shape) {
		case CONICAL:
			buoySpec = drawConicalBuoyShape(graphics, r.x + xoffset , r.y + yoffset + topmarkShift.height);
			break;
		case CYLINDRICAL:
			buoySpec = drawCanBuoyShape(graphics,r.x +  xoffset ,  r.y + yoffset + topmarkShift.height);
			break;
		case SPHERICAL:
			buoySpec = drawSpehereBuoyShape(graphics, r.x +  xoffset,  r.y + yoffset + topmarkShift.height);
			break;
		case BARREL:
			buoySpec = drawBarrelBuoyShape(graphics, r.x + xoffset, r.y + yoffset + topmarkShift.height);
			break;
		case PILLAR:
			buoySpec = drawPillarBuoyShape(graphics, r.x + xoffset, r.y + yoffset + topmarkShift.height);
			break;
		case SPAR:
			buoySpec = drawSparBuoyShape(graphics, r.x + xoffset, r.y + yoffset + topmarkShift.height); 
			break;
		case SUPER:
			buoySpec = drawSuperBuoyShape(graphics, r.x + xoffset, r.y + yoffset + topmarkShift.height);
			break;

		}
		if(buoySpec == null) {
			buoySpec = new BuoyBoundsSpec(new Point(0,0), new Point(0,0));

		}
		if(topmark != null) {
			List<TopmarkType> topmarkParts = topmark.getTopmarkParts();
			if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.CONE_UP) && topmarkParts.get(1).equals(TopmarkType.CONE_DOWN)) {
				drawEastTop(graphics, r.x + xoffset + buoySpec.getTopMarkDrawingposition().x, r.y + yoffset );
			} else if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.CONE_UP) && topmarkParts.get(1).equals(TopmarkType.CONE_UP)) {
				drawNorthTop(graphics, r.x + xoffset + buoySpec.getTopMarkDrawingposition().x, r.y + yoffset );
			} else if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.CONE_DOWN) && topmarkParts.get(1).equals(TopmarkType.CONE_UP)) {
				drawWestTop(graphics, r.x + xoffset + buoySpec.getTopMarkDrawingposition().x, r.y + yoffset);
			} else if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.CONE_DOWN) && topmarkParts.get(1).equals(TopmarkType.CONE_DOWN)) {
				drawSouthTop(graphics, r.x + xoffset + buoySpec.getTopMarkDrawingposition().x, r.y + yoffset );
			} else if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.BALL) && topmarkParts.get(1).equals(TopmarkType.BALL)) {
				drawIsolatedDanger(graphics, r.x + xoffset + buoySpec.getTopMarkDrawingposition().x, r.y + yoffset);
			} else if(topmarkParts.size() == 1 && topmarkParts.get(0).equals(TopmarkType.CYLINDER)) {
				drawFilledSquare(graphics, r.x + xoffset + buoySpec.getTopMarkDrawingposition().x , r.y + yoffset );
			} else if(topmarkParts.size() == 1 && topmarkParts.get(0).equals(TopmarkType.CONE_UP)) {
				drawFilledLateralTriangle(graphics, r.x + xoffset + buoySpec.getTopMarkDrawingposition().x, r.y + yoffset );
			} else if(topmarkParts.size() == 1 && topmarkParts.get(0).equals(TopmarkType.BALL)) {
				drawSafeWater(graphics, r.x + xoffset + buoySpec.getTopMarkDrawingposition().x, r.y + yoffset);
			}
		}
		
		if(colorText != null) {
//			drawColor(graphics, r.x + xoffset , r.y + yoffset, colorText);
		}
		if(lightingColor != null) {
			drawLightningColor(graphics, r.x + xoffset + buoySpec.getLighningIdPosition().x, r.y + yoffset + buoySpec.getLighningIdPosition().y);
		}
		if(lightingID != null) {
			drawLightID(graphics, r.x + xoffset + 15 , r.y + yoffset - 3, lightingID);
		}
		// TODO: designation
		
	}


	private BuoyBoundsSpec drawConicalBuoyShape(Graphics graphics, int xoffset, int yoffset) {
		if(drawColored) {
//			graphics.setBackgroundColor(ColorConstants.red);
//			graphics.setBackgroundColor(ColorConstants.white);
			graphics.fillArc(xoffset - 6, yoffset - 10, 15, 20, 0, 55);
			graphics.fillArc(xoffset + 3, yoffset - 10, 15, 20, 125, 55);
		}
		graphics.drawArc(xoffset - 6, yoffset - 10, 15, 20, 0, 55);
		graphics.drawArc(xoffset + 3, yoffset - 10, 15, 20, 125, 55);
	
		drawPositionLine(graphics, xoffset, yoffset , 12);
		return new BuoyBoundsSpec(new Point(6,0), new Point(-3,10));
	}


	private BuoyBoundsSpec drawCanBuoyShape(Graphics graphics, int xoffset, int yoffset) {

		PointList pointList = new PointList();
		pointList.addPoint(xoffset + 2, yoffset);
		pointList.addPoint(xoffset + 3, yoffset - 5);
		pointList.addPoint(xoffset + 11, yoffset - 4);
		pointList.addPoint(xoffset + 10, yoffset);
		if(buoyColor.size() == 1) {
			graphics.setBackgroundColor(buoyColor.get(0));
			graphics.fillPolygon(pointList);
		}
		graphics.drawPolygon(pointList);

//		graphics.drawLine(xoffset + 2 , yoffset , xoffset + 3, yoffset - 5); 
//		graphics.drawLine(xoffset + 3, yoffset - 5, xoffset + 11, yoffset - 4);
//		graphics.drawLine(xoffset + 11, yoffset - 4, xoffset + 10, yoffset);
	
		drawPositionLine(graphics, xoffset, yoffset , 12);
		return new BuoyBoundsSpec(new Point(7,0), new Point(-3,10));
	}


	private BuoyBoundsSpec drawSpehereBuoyShape(Graphics graphics, int xoffset, int yoffset) {
		graphics.drawArc(xoffset + 1, yoffset - 8 , 10, 10, 320, 260);
		drawPositionLine(graphics, xoffset, yoffset , 12);
		return new BuoyBoundsSpec(new Point(7,-8), new Point(-3,2));
	}


	/**
	 * @param graphics
	 * @param xoffset
	 * @param yoffset
	 * @return
	 */
	private BuoyBoundsSpec drawPillarBuoyShape(Graphics graphics, int xoffset, int yoffset) {
		PointList pointList = new PointList();
		Point start1 = new Point(xoffset + 2, yoffset);
		Point start2 = new Point(xoffset + 4, yoffset - 3);
		pointList.addPoint(start1);
		pointList.addPoint(start2);
		Point top1 = new Point(xoffset + 5, yoffset - 12);
		Point top2 = new Point(xoffset + 9, yoffset - 12);
		pointList.addPoint(top1);
		pointList.addPoint(top2);
		pointList.addPoint(xoffset + 9, yoffset - 3);
		pointList.addPoint(xoffset + 10, yoffset);
		if(buoyColor.size() == 1) {
			graphics.setBackgroundColor(buoyColor.get(0));
			graphics.fillPolygon(pointList);
		} else if(buoyColor.size() == 3) {
			PointList fillList1 = new PointList();
			fillList1.addPoint(start1);
			fillList1.addPoint(start2);
			Point midpoint1 = new Point(xoffset + 5, yoffset - 4);
			Point midpoint2 = new Point(xoffset + 9, yoffset - 4);
			fillList1.addPoint(midpoint1);
			fillList1.addPoint(midpoint2);
			fillList1.addPoint(xoffset + 9, yoffset - 3);
			fillList1.addPoint(xoffset + 10, yoffset);
			graphics.setBackgroundColor(buoyColor.get(0));
			graphics.fillPolygon(fillList1);

			PointList fillList2 = new PointList();
			fillList2.addPoint(midpoint1);
			Point midpoint3 = new Point(xoffset + 5, yoffset - 8);
			Point midpoint4 = new Point(xoffset + 9, yoffset - 7);
			fillList2.addPoint(midpoint3);
			fillList2.addPoint(midpoint4);
			fillList2.addPoint(midpoint2);
			graphics.setBackgroundColor(buoyColor.get(1));
			graphics.fillPolygon(fillList2);

			PointList fillList3 = new PointList();
			fillList3.addPoint(midpoint3);
			fillList3.addPoint(top1);
			fillList3.addPoint(top2);
			fillList3.addPoint(midpoint4);
			graphics.setBackgroundColor(buoyColor.get(2));
			graphics.fillPolygon(fillList3);

		}
		graphics.drawPolygon(pointList);
		drawPositionLine(graphics, xoffset, yoffset , 12);
		return new BuoyBoundsSpec(new Point(7,-12), new Point(-3,2));
	}


	/**
	 * @param graphics
	 * @param xoffset
	 * @param yoffset
	 * @return
	 */
	private BuoyBoundsSpec drawSparBuoyShape(Graphics graphics, int xoffset, int yoffset) {
		
		PointList pointList = new PointList();
		Point start1 = new Point(xoffset + 4, yoffset);
		Point top1 = new Point(xoffset + 4, yoffset -12);
		Point top2 = new Point(xoffset + 8, yoffset -12);
		Point end1 = new Point(xoffset + 8, yoffset);
		Point endCenter = new Point(xoffset + 6, yoffset - 1);

		pointList.addPoint(start1);
		pointList.addPoint(top1);
		pointList.addPoint(top2);
		pointList.addPoint(end1);
		pointList.addPoint(endCenter);


		
//		graphics.drawRectangle(xoffset, yoffset, 10, 10);
		if(buoyColor.size() == 1) {
			graphics.setBackgroundColor(buoyColor.get(0));
			graphics.fillPolygon(pointList);
//			graphics.fillPolygon(new int[] {xoffset + 4, yoffset , xoffset + 4, yoffset -12, xoffset + 8, yoffset -12, xoffset + 8, yoffset, xoffset + 6, yoffset - 1});
		} else if(buoyColor.size() == 3) {
			PointList fillList1 = new PointList();
			Point midPoint1 = new Point(xoffset + 4, yoffset - 4);
			Point midPoint2 = new Point(xoffset + 8, yoffset - 4);
			fillList1.addPoint(start1);
			fillList1.addPoint(midPoint1);
			fillList1.addPoint(midPoint2);
			fillList1.addPoint(end1);
			fillList1.addPoint(endCenter);
			graphics.setBackgroundColor(buoyColor.get(0));
			graphics.fillPolygon(fillList1);
			
			PointList fillList2 = new PointList();
			Point midPoint3 = new Point(xoffset + 4, yoffset - 8);
			Point midPoint4 = new Point(xoffset + 8, yoffset - 8);
			fillList2.addPoint(midPoint1);
			fillList2.addPoint(midPoint3);
			fillList2.addPoint(midPoint4);
			fillList2.addPoint(midPoint2);
			graphics.setBackgroundColor(buoyColor.get(1));
			graphics.fillPolygon(fillList2);

			PointList fillList3 = new PointList();
			fillList3.addPoint(midPoint3);
			fillList3.addPoint(top1);
			fillList3.addPoint(top2);
			fillList3.addPoint(midPoint4);
			graphics.setBackgroundColor(buoyColor.get(2));
			graphics.fillPolygon(fillList3);

		}
		// new int[] {xoffset + 4, yoffset , xoffset + 4, yoffset -12, xoffset + 8, yoffset -12, xoffset + 8, yoffset, xoffset + 6, yoffset - 1}
		graphics.drawPolygon(pointList);
		drawPositionLine(graphics, xoffset, yoffset , 12);
		return new BuoyBoundsSpec(new Point(6,-12), new Point(-3,2));
	}

	private BuoyBoundsSpec drawBarrelBuoyShape(Graphics graphics, int xoffset, int yoffset) {
		graphics.drawArc(xoffset + 1, yoffset - 5 , 4, 10, 0, 180);
		graphics.drawArc(xoffset + 7, yoffset - 5 , 4, 10, 0, 90);
		graphics.drawArc(xoffset + 2, yoffset - 6 , 8, 4, 0, 180);
		drawPositionLine(graphics, xoffset, yoffset , 12);
		return new BuoyBoundsSpec(new Point(7,-6), new Point(-3,2));
	}


	/**
	 * @param graphics
	 * @param xoffset
	 * @param yoffset
	 * @return
	 */
	private BuoyBoundsSpec drawSuperBuoyShape(Graphics graphics, int xoffset, int yoffset) {
		int[] points = new int[] {xoffset + 1, yoffset , xoffset + 2, yoffset - 5, xoffset + 10, yoffset -5, xoffset + 11, yoffset, xoffset + 8, yoffset,  xoffset + 6, yoffset - 1, xoffset + 4, yoffset };
		graphics.fillPolygon(points);
		graphics.drawPolygon(points);
		drawPositionLine(graphics, xoffset, yoffset , 12);
		return new BuoyBoundsSpec(new Point(6,-5), new Point(-3,2));
	}

	private BuoyBoundsSpec drawFillSpehereBuoyShape(Graphics graphics, int xoffset, int yoffset) {
		graphics.fillArc(xoffset + 1, yoffset - 8, 10, 10, 320, 260);
//		graphics.fillPolygon(new int[] {xoffset + 5, yoffset, 0, 10, 10, 20});
		drawPositionLine(graphics, xoffset, yoffset, 12);
		return new BuoyBoundsSpec(new Point(7,-8), new Point(-3,2));
	}
	
	private void drawTopCross(Graphics graphics, int xoffset, int yoffset) {
		drawShortTopLine(graphics, xoffset + 7, yoffset - 5);
		graphics.drawLine(xoffset + 8 ,yoffset - 9 ,xoffset + 11, yoffset - 4);
		graphics.drawLine(xoffset + 12 ,yoffset - 8,xoffset + 7, yoffset - 5);
	}
	
	private void drawEmptyLateralTriangle(Graphics graphics, int xoffset, int yoffset) {
		graphics.drawPolygon(getTopTriangle(xoffset - 2, yoffset - 11));
		drawShortTopLine(graphics, xoffset, yoffset );
	}

	private void drawFilledLateralTriangle(Graphics graphics, int xoffset, int yoffset) {
		graphics.fillPolygon(getTopTriangle(xoffset  , yoffset ));
		drawShortTopLine(graphics, xoffset + 2, yoffset + 9);
	}

	
	private void drawFilledSquare(Graphics graphics, int xoffset, int yoffset) {
//		graphics.drawLine(xoffset , yoffset + 5, xoffset , yoffset + 10);
		drawShortTopLine(graphics, xoffset , yoffset + 10);
//		graphics.fillRectangle(xoffset , yoffset ,  3 ,  3);
//		graphics.drawRectangle(xoffset , yoffset ,  4 ,  4);
		graphics.fillPolygon(new int[]{xoffset - 1, yoffset + 5, xoffset + 1, yoffset , xoffset + 6, yoffset + 1, xoffset + 4, yoffset + 6});
		graphics.drawPolygon(new int[]{xoffset - 1, yoffset + 5, xoffset + 1, yoffset , xoffset + 6, yoffset + 1, xoffset + 4, yoffset + 6});
	}

	private void drawEmptySquare(Graphics graphics, int xoffset, int yoffset) {
		drawShortTopLine(graphics, xoffset + 7, yoffset - 5);
		graphics.drawPolygon(new int[]{xoffset + 6, yoffset - 5, xoffset + 8, yoffset - 10, xoffset + 13, yoffset - 9, xoffset + 11, yoffset - 4});
	}

	private void drawSafeWater(Graphics graphics, int xoffset, int yoffset) {
		drawShortTopLine(graphics, xoffset  , yoffset + 11);
//		drawEmptyBall(graphics, xoffset , yoffset);
		drawFillBall(graphics, xoffset, yoffset);
	}

	private void drawColor(Graphics graphics, int xoffset, int yoffset, String colorText) {
		Font colorfont = new Font(Display.getDefault(),"Arial",6,SWT.NORMAL);  //$NON-NLS-1$
		graphics.setFont(colorfont);
		int textWidth = FigureUtilities.getTextWidth(colorText, colorfont);
		graphics.drawText(colorText, xoffset - textWidth / 2 + 7, yoffset + 9);
		colorfont.dispose();
	}

	private void drawLightID(Graphics graphics, int xoffset, int yoffset, String id) {
		Font font2 = new Font(Display.getDefault(),"Arial",8,SWT.BOLD | SWT.ITALIC);  //$NON-NLS-1$
		graphics.setFont(font2);
		graphics.drawText(id, xoffset, yoffset);
		font2.dispose();
	}
	
	private void drawLightningColor(Graphics graphics, int xoffset, int yoffset) {
		Color previousColor = graphics.getBackgroundColor();
		graphics.setBackgroundColor(lightingColor);
		graphics.fillPolygon(new int[] {6 + xoffset,0 + yoffset, xoffset - 5,14 + yoffset, xoffset - 9,8 + yoffset});
		graphics.fillArc(xoffset - 10, yoffset + 7, 7, 7, 120, 180);
		graphics.setBackgroundColor(previousColor);
	}
	
	
	private void drawPositionLine(Graphics graphics, int xoffset, int yoffset, int lengthOfBaseLine) {
		int halfBaseLineLength = lengthOfBaseLine / 2  - 1;
		graphics.drawLine(xoffset ,yoffset , xoffset + 5,yoffset);
		graphics.drawOval(xoffset + halfBaseLineLength , yoffset -1, 2, 2);
		graphics.drawLine(xoffset + halfBaseLineLength + 2  ,yoffset , xoffset + lengthOfBaseLine ,yoffset );
	}

	

	private void drawIsolatedDanger(Graphics graphics, int xoffset, int yoffset) {
		graphics.setBackgroundColor(ColorConstants.black);
		drawFillBall(graphics, xoffset + 5, yoffset);
		drawFillBall(graphics, xoffset + 2, yoffset + 8 );
		drawLongTopLine(graphics, xoffset + 3, yoffset);
	}

	
	private void drawFillBall(Graphics graphics, int xoffset, int yoffset) {
		if (buoyColor.size() == 2) {
			if(Orientation.HORIZONTAL.equals(colorOrientation)) {
					Rectangle rectangle = new Rectangle(xoffset , yoffset, 6, 6);
					graphics.setBackgroundColor(buoyColor.get(0));
					graphics.fillArc(rectangle, 0, 180);
					rectangle = new Rectangle(xoffset , yoffset , 6, 6);
					graphics.setBackgroundColor(buoyColor.get(1));
					graphics.fillArc(rectangle, 180, 180);
					graphics.drawOval(rectangle);
			} else if(Orientation.VERTICAL.equals(colorOrientation)) {
				Rectangle rectangle = new Rectangle(xoffset , yoffset, 6, 6);
				graphics.setBackgroundColor(buoyColor.get(0));
				graphics.fillArc(rectangle, 90, 180);
				rectangle = new Rectangle(xoffset , yoffset , 6, 6);
				graphics.setBackgroundColor(buoyColor.get(1));
				graphics.fillArc(rectangle, 270, 180);
				graphics.drawOval(rectangle);
			}
		} else {
			graphics.fillOval(xoffset, yoffset , 6, 6);
		}
	}

	private void drawEmptyBall(Graphics graphics, int xoffset, int yoffset) {
		graphics.drawOval(xoffset , yoffset , 6, 6);
	}

	private void drawEastTop(Graphics graphics, int xoffset, int yoffset) {
		graphics.setBackgroundColor(ColorConstants.black);
		graphics.fillPolygon(getTopTriangle(xoffset + 1, yoffset + 0));
		graphics.fillPolygon(getBottomTriangle(xoffset  , yoffset + 8));
		drawLongTopLine(graphics, xoffset  , yoffset + 1);
	}

	private void drawSouthTop(Graphics graphics, int xoffset, int yoffset) {
		graphics.setBackgroundColor(ColorConstants.black);
		graphics.fillPolygon(getBottomTriangle(xoffset + 3, yoffset + 0));
		graphics.fillPolygon(getBottomTriangle(xoffset  , yoffset + 8));
		drawLongTopLine(graphics, xoffset , yoffset + 1);
	}
	
	private void drawNorthTop(Graphics graphics, int xoffset, int yoffset) {
		graphics.setBackgroundColor(ColorConstants.black);
		graphics.fillPolygon(getTopTriangle(xoffset + 1, yoffset + 0));
		graphics.fillPolygon(getTopTriangle(xoffset - 2, yoffset + 8));
		drawLongTopLine(graphics, xoffset , yoffset + 1);
	}

	
	private void drawWestTop(Graphics graphics, int xoffset, int yoffset) {
		graphics.setBackgroundColor(ColorConstants.black);
		graphics.fillPolygon(getBottomTriangle(xoffset + 3, yoffset + 0));
		graphics.fillPolygon(getTopTriangle(xoffset - 2, yoffset + 8));
		drawLongTopLine(graphics, xoffset , yoffset + 1);
	}

	/**
	 * offsets describe the lower left corner of the figure
	 * 
	 * @param graphics
	 * @param xoffset 
	 * @param yoffset 
	 */
	private void drawShortTopLine(Graphics graphics, int xoffset, int yoffset) {
		graphics.drawLine(xoffset ,yoffset ,xoffset + 1, yoffset - 5);
	}
	
	private void drawLongTopLine(Graphics graphics, int xoffset, int yoffset) {
		graphics.drawLine(xoffset ,yoffset + 16,xoffset + 6, yoffset );
	}

	private int[] getTopTriangle(int xoffset, int yoffset) {
		return new int[] {5 + xoffset, yoffset,5 + xoffset,8 + yoffset,xoffset,6 + yoffset};
	}

	private int[] getBottomTriangle(int xoffset, int yoffset) {
		return new int[] {xoffset,8 + yoffset,5 + xoffset,2 + yoffset,xoffset,yoffset};
	}
	
	private class BuoyBoundsSpec {
		private final Point topMarkDrawingposition;
		private final Point lighningIdPosition;
		
		public BuoyBoundsSpec(Point topMarkDrawingposition, Point lighningIdPosition) {
			super();
			this.topMarkDrawingposition = topMarkDrawingposition;
			this.lighningIdPosition = lighningIdPosition;
		}

		public Point getTopMarkDrawingposition() {
			return topMarkDrawingposition;
		}

		public Point getLighningIdPosition() {
			return lighningIdPosition;
		}
		
	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		return getMinimumSize();
	}

	public void setTopmark(Topmark topmark) {
		this.topmark = topmark;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void setColorText(String colorText) {
		this.colorText = colorText;
	}

	public void setLightingID(String lightingID) {
		this.lightingID = lightingID;
		
	}

	public void setRadarReflecting(boolean radarreflecting) {
		this.radarreflecting = radarreflecting;
	}

	public void setLightingColor(Color lightingColor) {
		this.lightingColor = lightingColor;
	}


	public void setBuoyColor(List<Color> buoyColor) {
		this.buoyColor = buoyColor;
	}

	public void setColorOrientation(Orientation colorOrientation) {
		this.colorOrientation = colorOrientation;
	}


	@Override
	public Dimension getMinimumSize(int wHint, int hHint) {
		return new Dimension(14, getShapeShift().expand(getTopmarkShift()).getNegated().height + 3);
	}


	@Override
	public Dimension getGeoPosition() {
//		return new Dimension(0,0);
		return getTopmarkShift().expand(getShapeShift());
	}
	

	private Dimension getShapeShift() {
		switch (shape) {
		case CONICAL:
			return new Dimension(-6, -9 );
		case CYLINDRICAL:
			return new Dimension(-6, -6 );
		case SPHERICAL:
			return new Dimension(-6, -9 );
		case BARREL:
			return new Dimension(-6, -7 );
		case PILLAR:
			return new Dimension(-6, -13);
		case SPAR:
			return new Dimension(-6, -13);
		case SUPER:
			return new Dimension(-6, -6);
		default:
			return new Dimension(0, 0);
		}
	}

	private Dimension getTopmarkShift() {
		if(topmark != null) {
			List<TopmarkType> topmarkParts = topmark.getTopmarkParts();
			if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.CONE_UP) && topmarkParts.get(1).equals(TopmarkType.CONE_DOWN)) {
				return new Dimension(0, -17 );
			} else if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.CONE_UP) && topmarkParts.get(1).equals(TopmarkType.CONE_UP)) {
				return new Dimension(0, -17 );
			} else if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.CONE_DOWN) && topmarkParts.get(1).equals(TopmarkType.CONE_UP)) {
				return new Dimension(0, -17 );
			} else if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.CONE_DOWN) && topmarkParts.get(1).equals(TopmarkType.CONE_DOWN)) {
				return new Dimension(0, -17 );
			} else if(topmarkParts.size() == 2 && topmarkParts.get(0).equals(TopmarkType.BALL) && topmarkParts.get(1).equals(TopmarkType.BALL)) {
				return new Dimension(0, -18 );
			} else if(topmarkParts.size() == 1 && topmarkParts.get(0).equals(TopmarkType.CYLINDER)) {
				return new Dimension(0, -9 );
			} else if(topmarkParts.size() == 1 && topmarkParts.get(0).equals(TopmarkType.CONE_UP)) {
				return new Dimension(0, -10 );
			} else if(topmarkParts.size() == 1 && topmarkParts.get(0).equals(TopmarkType.BALL)) {
				return new Dimension(0, -11 );
			}
		}
		return new Dimension(0, 0);
	}

	

}
