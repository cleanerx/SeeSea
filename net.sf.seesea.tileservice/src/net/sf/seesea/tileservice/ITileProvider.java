package net.sf.seesea.tileservice;

import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 * A tile provider capable to retrieve image data from one or more
 * {@link ITileSource}s.  
 */
public interface ITileProvider {
	
	/**
	 * This method retrieves the tiles necessary to show the current position at the center of the screen.
	 * If is uncapable of retrieving the images in a manner that does not block the UI it may use the {@link IAsynchronouslyUpdateable} interface to update the ui when one or more image becomes available.
	 * 
	 * @param position
	 * @param zoomLevel
	 * @param displayableWidth
	 * @param displayableHeight
	 * @param updateable
	 * @return an array of {@link ImageData} that contains either loaded tiles or a waiting image that will be exchanged by the loaded tile, when it becomes available  
	 */
	ImageData[][] getTiles(Point mercatorPosition, int x, int y, int zoomLevel, 
			IAsynchronouslyUpdateable updateable, IProgressMonitor progressMonitor);

	/**
	 * 
	 * @return
	 */
	int getMinZoomLevel();
	
	/**
	 * 
	 * @return
	 */
	int getMaxZoomLevel();
	
	/**
	 * 
	 * @return
	 */
	Point getTileSize();

	/**
	 * 
	 * @return the projection interface capable to project a position to a location on the tiles and back
	 */
	IMapProjection getProjection();
	
	/**
	 * 
	 * @return the processor for building tile caches
	 */
	ITileCacheProcessor getTileCacheProcessor();

}
