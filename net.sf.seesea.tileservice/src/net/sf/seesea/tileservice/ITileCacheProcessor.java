package net.sf.seesea.tileservice;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.graphics.Point;

public interface ITileCacheProcessor {

	public void cacheTiles(Point mercatorPosition, int requiredXTiles, int requiredYTiles, int zoomLevel, IAsynchronouslyUpdateable updateable, IProgressMonitor progressMonitor);
	
	public IStatus clearCachedTiles();
	
}
