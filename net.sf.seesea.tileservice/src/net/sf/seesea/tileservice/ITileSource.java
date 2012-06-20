package net.sf.seesea.tileservice;

import java.io.IOException;
import java.net.URL;

/**
 * Classes implementing this interface provide URLs to tiles from their x,y and zoom level
 */
public interface ITileSource {

	/**
	 * 
	 * @return the minimum zoom level this source provides
	 */
	public int getMinZoom();

	/**
	 * 
	 * @return the maximum zoom level this source provides
	 */
	public int getMaxZoom();
	
	/**
	 * 
	 * @param xTile
	 * @param yTile
	 * @param zoom
	 * @return a syntactically valid URL to the tile or <code>null</code> if this source is unavailable to provider the tile
	 * @throws IOException 
	 */
	public URL getTileURL(int xTile, int yTile, int zoom) throws IOException;

}
