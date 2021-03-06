/**
 * 
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
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

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.tileservice.IAsynchronouslyUpdateable;
import net.sf.seesea.tileservice.ITileProvider;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * The main tile layer. It paints tiles. 
 *
 */
public class MapLayer extends FreeformLayer {

	private Point mapPosition;

	private Rectangle paintBounds;

	private int zoomLevel;

	private List<Rectangle> prevpaintBounds = new ArrayList<Rectangle>(6);

	private String cursorPosition;
	
	/**
	 * paints the figure by requesting the tiles from the tile provider. This is done in asynchronous fashion and the figure is repainted once tiles become available  
	 */
	@Override
	protected void paintFigure(Graphics g) {
		
		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference<?>[] serviceReferences;
		try {
			serviceReferences = bundleContext.getServiceReferences(ITileProvider.class.getName(), null);
			SortedSet<ServiceReference<?>> xxx = new TreeSet<ServiceReference<?>>(new ServiceRankingComparator());
			for (ServiceReference<?> serviceReference : serviceReferences) {
				xxx.add(serviceReference);
			}
			if(serviceReferences == null) {
				g.drawText(Messages.getString("MapLayer.tileUnavailable"), 10, 10); //$NON-NLS-1$
			} else {
				List<ImagePosition> imagePositions = new ArrayList<MapLayer.ImagePosition>(20);
				for (ServiceReference<?> serviceReference : xxx) {
					ITileProvider tileProvider =  (ITileProvider) bundleContext.getService(serviceReference);
					Point tileSize = new Point(tileProvider.getTileSize().x, tileProvider.getTileSize().y);

					Point upperLeftCorner = new Point(mapPosition.x - (mapPosition.x % tileSize.y),
							mapPosition.y - (mapPosition.y % tileSize.y));
					int x0 = upperLeftCorner.x / tileSize.x;
					int y0 = upperLeftCorner.y / tileSize.y;
					int x1 = (int) Math.ceil((((double) upperLeftCorner.x) + paintBounds.width) / tileSize.x);
					int y1 = (int) Math.ceil((((double) upperLeftCorner.y) + paintBounds.height) / tileSize.y);
					
					org.eclipse.swt.graphics.Point point = new org.eclipse.swt.graphics.Point(mapPosition.x,
							mapPosition.y);
					org.eclipse.swt.graphics.Rectangle swtrectangle = new org.eclipse.swt.graphics.Rectangle(paintBounds.x, paintBounds.y, paintBounds.width, paintBounds.height);
					
					Dimension imageCount = getImageCount(tileProvider, swtrectangle, point);
					ImageData tiles[][] = tileProvider.getTiles(point, imageCount.width, imageCount.height, zoomLevel, new IAsynchronouslyUpdateable() {
						
						// TODO: optimize the exact area
						public void updateTilePresent() {
							Display.getDefault().asyncExec(new Runnable() {
								
								public void run() {
									// TODO Auto-generated method stub
									repaint();
								}
							});
						}
					}, new NullProgressMonitor());
					int dy = y0 * tileSize.y;
					for (int y = y0; y <= y1; y++) {
						int dx = x0 * tileSize.x;
						for (int x = x0; x <= x1; x++) {
							if (x - x0 < tiles.length && y - y0 < tiles[x - x0].length) {
								if(tiles[x - x0][y - y0] != null) {
									if(tiles[x - x0][y - y0] != null) {
										ImageData data = tiles[x - x0][y - y0];
										ImagePosition imagePosition = new ImagePosition();
										imagePosition.image = new Image(Display.getDefault(), data);;
										imagePosition.x = x * tileSize.x;
										imagePosition.y = y * tileSize.y;
										imagePositions.add(imagePosition);
									}
								} else {
//									image = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR);
//									g.drawImage(image, x * tileSize.x, y * tileSize.y);
								}
							}
							dx += tileSize.x;
						}
						dy += tileSize.y;
					}
					bundleContext.ungetService(serviceReference);
				}
				for (ImagePosition imagePosition : imagePositions) {
					g.drawImage(imagePosition.image, imagePosition.x, imagePosition.y);
					imagePosition.image.dispose();
				}
			}
		} catch (InvalidSyntaxException e) {
			Logger.getLogger(getClass()).error("Failed to set filter", e); //$NON-NLS-1$
		}
		Font font2 = new Font(Display.getDefault(),"Arial", 8, SWT.BOLD);  //$NON-NLS-1$
		g.setFont(font2);
		String text = "\u00A9 OpenStreetMap contributors"; //$NON-NLS-1$
		Dimension textExtents = FigureUtilities.getTextExtents(text, font2);
		textExtents.negate();
//		Rectangle clip = g.getClip(new Rectangle());
//		System.out.println(clip+ g.);
		Rectangle copy = paintBounds.getCopy();
		copy.expand(textExtents.width, textExtents.height);
		g.drawText(text , copy.getBottomRight());
		font2.dispose();
		
		copy = paintBounds.getCopy();
		if(cursorPosition != null) {
			Font font3 = new Font(Display.getDefault(),"Arial", 20, SWT.BOLD);  //$NON-NLS-1$
			g.setFont(font3);
			textExtents = FigureUtilities.getTextExtents(cursorPosition, font3);
			textExtents.negate();
			copy.expand(0, textExtents.height);
			Point bottomLeft = copy.getBottomLeft();
			g.drawText(cursorPosition, bottomLeft);
			font3.dispose();
		}
//		System.out.println("paint");
	}
	
	

	public Point getMapPosition() {
		return mapPosition;
	}

	public void setMapPosition(Point mapPosition) {
		this.mapPosition = mapPosition;
	}

	public Rectangle getPaintBounds() {
		return this.paintBounds;
	}

	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
	}
	
	/**
	 * calculates how many tiles to request from the tile provider
	 * 
	 * @param tileProvider
	 * @param displayableExtents
	 * @param mercatorPosition
	 * @return
	 */
	private Dimension getImageCount(ITileProvider tileProvider, org.eclipse.swt.graphics.Rectangle displayableExtents, org.eclipse.swt.graphics.Point mercatorPosition) {
		int numberOfTiles = 1 << zoomLevel;
		int totalWidth = numberOfTiles * tileProvider.getTileSize().x;
		int requiredXTiles = 0;
		if(totalWidth <= displayableExtents.width) {
			requiredXTiles = numberOfTiles;
			mercatorPosition.x = 0;
		} else {
			int additionalTile = mercatorPosition.x % tileProvider.getTileSize().x == 0 ? 0 : 1;
			requiredXTiles = displayableExtents.width / tileProvider.getTileSize().x + additionalTile + 1;
			int overdue = requiredXTiles * tileProvider.getTileSize().x + displayableExtents.x - totalWidth;
			if(overdue > 0) {
				requiredXTiles--;
			}
		}

		int totalHeight = numberOfTiles * tileProvider.getTileSize().y;
		int requiredYTiles = 0;
		if(totalHeight <= displayableExtents.height) {
			requiredYTiles = numberOfTiles;
			mercatorPosition.y = 0;
		} else {
			int additionalTile = mercatorPosition.y % tileProvider.getTileSize().y == 0 ? 0 : 1;
			requiredYTiles = displayableExtents.height / tileProvider.getTileSize().y + additionalTile + 1;

			int overdue = requiredYTiles * tileProvider.getTileSize().y + displayableExtents.y - totalWidth;
			if(overdue > 0) {
				requiredYTiles--;
			}

		}
		return new Dimension(requiredXTiles, requiredYTiles);
	}
	
	private class ImagePosition {
		
		Image image;
		
		int x;
		
		int y;
		
	}

	public void setPaintBounds(Rectangle bounds) {
		paintBounds = bounds;
		
	}



	public void setCursorPosition(String cursorPosition) {
		this.cursorPosition = cursorPosition;
	}
}
