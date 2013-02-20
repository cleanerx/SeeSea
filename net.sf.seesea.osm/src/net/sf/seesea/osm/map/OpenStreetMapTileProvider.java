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
package net.sf.seesea.osm.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.seesea.osm.OpenSeaMapActivator;
import net.sf.seesea.osm.preferences.IOSMPreferences;
import net.sf.seesea.osm.util.LRUCache;
import net.sf.seesea.tileservice.IAsynchronouslyUpdateable;
import net.sf.seesea.tileservice.ITileCacheProcessor;
import net.sf.seesea.tileservice.ITileProvider;
import net.sf.seesea.tileservice.ITileSource;
import net.sf.seesea.tileservice.projections.IMapProjection;
import net.sf.seesea.tileservice.projections.SphericalMercatorProjection;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 * This is the central tile provider. It caches tiles in a least recently used fashion for fast loading.  
 *
 */
public class OpenStreetMapTileProvider implements ITileProvider {

	private final LRUCache<TileLocation, ImageData> _tileCache;

	/** multiple tile sources may be used */
	private List<ITileSource> tileSources;

	private SphericalMercatorProjection projection;

	private ExecutorService fixedThreadPool;

	private ImageData noImage;

	private OSMCacheProcessor osmCacheProcessor;

	private Boolean overlay;

	private Set<GetTileRunnable> queuedTileRequests;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public OpenStreetMapTileProvider() {
		super();
		_tileCache = new LRUCache<TileLocation, ImageData>(100);
		projection = new SphericalMercatorProjection();
		Runtime runtime = Runtime.getRuntime();
		// OSM policy states no more than 2 threads retrieving tiles
        int nrOfThreads = runtime.availableProcessors() > 1 ? 2 : 1;
        // new BlockingLifoQueue<Runnable>()
		fixedThreadPool = new ThreadPoolExecutor(nrOfThreads, 2, 2, TimeUnit.MINUTES, new BlockingLifoQueue<Runnable>());
		
		URL entry = OpenSeaMapActivator.getDefault().getBundle().getEntry("/res/chart.png"); //$NON-NLS-1$
		ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(entry);
		
		noImage = imageDescriptor.getImageData();
		overlay = false;
		queuedTileRequests = Collections.synchronizedSet(new HashSet<OpenStreetMapTileProvider.GetTileRunnable>());
	}
	
	public void activate(Map properties) throws IOException {
		setupTileCache(properties);
	}

	private void setupTileCache(Map properties) {
		try {
		String cacheDir = (String) properties.get(IOSMPreferences.CACHE_DIRECTORY);
		if(cacheDir != null) {
			File cacheDirectory = new File(cacheDir);
			osmCacheProcessor = new OSMCacheProcessor(cacheDirectory); 
			this.tileSources = new ArrayList<ITileSource>(3);
			overlay = (Boolean) properties.get(IOSMPreferences.OVERLAY);
			
			String tileSource = (String) properties.get(IOSMPreferences.TILE_SOURCE); 
			
			URL server = new URL(tileSource);
			// FIXME: what to do if this was specified wrong?
			OpenStreetmapServerTileSource openStreetmapServerTileSource = new OpenStreetmapServerTileSource(server,0,18);
			OpenStreetMapHardDiskTileSource openStreetMapHardDiskTileSource = new OpenStreetMapHardDiskTileSource(cacheDirectory, openStreetmapServerTileSource);
			this.tileSources.add(openStreetMapHardDiskTileSource);
		}
		} catch (MalformedURLException e) {
			Logger.getLogger(getClass()).error("Failed to setup tile cache", e); //$NON-NLS-1$
		}
	}
	
	public void updateConfiguration(Map properties) {
		setupTileCache(properties);
	}
	
	@Override
	public int getMaxZoomLevel() {
		int max = 0; 
		for (ITileSource tileSource : tileSources) {
			if(tileSource.getMaxZoom() > max) {
				max = tileSource.getMaxZoom();
			}
		}
		return max;
	}

	@Override
	public int getMinZoomLevel() {
		int min = Integer.MAX_VALUE; 
		for (ITileSource tileSource : tileSources) {
			if(tileSource.getMinZoom() < min) {
				min = tileSource.getMinZoom();
			}
		}
		return min;
	}

	@Override
	public ImageData[][] getTiles(Point mercatorPosition, int requiredXTiles, int requiredYTiles, int zoomLevel, IAsynchronouslyUpdateable updateable, IProgressMonitor progressMonitor) {
		ImageData[][] imageData = new ImageData[requiredXTiles][requiredYTiles];
		for (int i = 0; i < imageData.length; i++) {
			for (int j = 0; j < imageData[i].length; j++) {
				int xTile = (mercatorPosition.x / getTileSize().x) + i;
				int yTile = (mercatorPosition.y / getTileSize().y) + j;
				TileLocation tileLocation = new TileLocation(xTile, yTile, zoomLevel);
				ImageData cachedTile = _tileCache.get(tileLocation);
				if(cachedTile == null) {
					// do this in a thread pool and return a bad image
					if(updateable == null) {
						GetTileRunnable tileRunnable = new GetTileRunnable(xTile, yTile, zoomLevel, imageData, i, j, updateable, progressMonitor);
						tileRunnable.run();
					} else {
						// overlay image do not show noImage
						if(!overlay) {
							imageData[i][j] = noImage;
						}
						GetTileRunnable getTileRunnable = new GetTileRunnable(xTile, yTile, zoomLevel, imageData, i, j, updateable, progressMonitor);
						if(queuedTileRequests.add(getTileRunnable)) {
							fixedThreadPool.submit(getTileRunnable);
						}
					}
				}  else {
					imageData[i][j] = cachedTile;
				}
				
			}
		}
		return imageData;
	}
	
	@Override
	public Point getTileSize() {
		return new Point(256, 256);
	}
	
	public synchronized void addTileSource(ITileSource tileSource) {
		tileSources.add(tileSource);
	}

	public synchronized void removeTileSource(ITileSource tileSource) {
		tileSources.add(tileSource);
	}
	
	private final class GetTileRunnable implements Runnable {
		private final int xTile;
		private final int yTile;
		private final int zoomLevel;
		private final ImageData[][] imageData;
		private final int i;
		private final int j;
		private final IAsynchronouslyUpdateable asynchronouslyUpdateable;
		private final IProgressMonitor progressMonitor;

		public GetTileRunnable(int xTile, int yTile, int zoomLevel,
				ImageData[][] imageData, int i, int j, IAsynchronouslyUpdateable updateable, IProgressMonitor progressMonitor) {
					this.xTile = xTile;
					this.yTile = yTile;
					this.zoomLevel = zoomLevel;
					this.imageData = imageData;
					this.i = i;
					this.j = j;
					this.asynchronouslyUpdateable = updateable;
					this.progressMonitor = progressMonitor;
		}

		@Override
		public void run() {
			try {
				URL tileURL = tileSources.get(0).getTileURL(xTile, yTile, zoomLevel);
				InputStream in;
				in = tileURL.openStream();
				imageData[i][j] = new ImageData(in);
				_tileCache.put(new TileLocation(xTile, yTile, zoomLevel), imageData[i][j]);
				progressMonitor.worked(1);
				if(asynchronouslyUpdateable != null) {
					asynchronouslyUpdateable.updateTilePresent();
				}
				queuedTileRequests.remove(this);
//				_tileCache.put(tileLocation, imageData[i][j]);
			} catch (FileNotFoundException e) {
				// nothing to do
			} catch (IOException e) {
				Status status = new Status(IStatus.ERROR, OpenSeaMapActivator.PLUGIN_ID, IStatus.ERROR, "Failed to load image from server", e); //$NON-NLS-1$
				OpenSeaMapActivator.getDefault().getLog().log(status);
			}

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + i;
			result = prime * result + j;
			result = prime * result + xTile;
			result = prime * result + yTile;
			result = prime * result + zoomLevel;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GetTileRunnable other = (GetTileRunnable) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			if (xTile != other.xTile)
				return false;
			if (yTile != other.yTile)
				return false;
			if (zoomLevel != other.zoomLevel)
				return false;
			return true;
		}

		private OpenStreetMapTileProvider getOuterType() {
			return OpenStreetMapTileProvider.this;
		}
		
		
	}

	private class TileLocation {
		
		private final int x;
		private final int y;
		private final int z;

		public TileLocation(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			result = prime * result + z;
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TileLocation other = (TileLocation) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			if (z != other.z)
				return false;
			return true;
		}



		private OpenStreetMapTileProvider getOuterType() {
			return OpenStreetMapTileProvider.this;
		}
		
		
		
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.map.ITileProvider#getProjection()
	 */
	@Override
	public IMapProjection getProjection() {
		return projection;
	}


	@Override
	public ITileCacheProcessor getTileCacheProcessor() {
		return osmCacheProcessor;
	}
	
	public void reconfigure(Map properties) {
		return;
	}

}
