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
package net.sf.seesea.osm.map;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import net.sf.seesea.osm.OpenSeaMapActivator;
import net.sf.seesea.osm.util.IOUtils;
import net.sf.seesea.tileservice.ITileSource;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * An tile source provider that stores tiles on local disk
 */
public class OpenStreetMapHardDiskTileSource implements ITileSource {

	private final File tileDirectory;

	private final ITileSource tileSource;

	public OpenStreetMapHardDiskTileSource(File tileDirectory) {
		this(tileDirectory, null);
	}
	
	/**
	 * Optionally this tile source may be backed up by another tile source. This way this tile source may retrieve missing tiles
	 * by another provider to store it to its own repository 
	 * 
	 * @param tileSource
	 * @param tileDirectory
	 */
	public OpenStreetMapHardDiskTileSource(File tileDirectory, ITileSource tileSource) {
		this.tileSource = tileSource;
		this.tileDirectory = tileDirectory;
		if(!tileDirectory.exists()) {
			tileDirectory.mkdirs();
		}
	}
	
	
	@Override
	public int getMaxZoom() {
		return 18;
	}

	@Override
	public int getMinZoom() {
		return 0;
	}

	@Override
	public URL getTileURL(int xTile, int yTile, int zoom) throws IOException {
		String path = zoom + File.separator + xTile + File.separator + yTile + ".png"; //$NON-NLS-1$
		File tile = new File(tileDirectory, path);
		if(!tile.exists() && tileSource != null) {
			retrieveFromBackingSource(xTile, yTile, zoom);
		}
		return tile.toURI().toURL();
	}

	/**
	 * asks the backing tile source for images and stores them locally
	 * 
	 * @param xTile
	 * @param yTile
	 * @param zoom
	 * @throws IOException if the tile could not be retrieved or stored locally
	 */
	private void retrieveFromBackingSource(int xTile, int yTile, int zoom) throws IOException {
		URL tileURL = tileSource.getTileURL(xTile, yTile, zoom);
		File zoomDir = new File(tileDirectory, ((Integer)zoom).toString());
		if(!zoomDir.exists()) {
			zoomDir.mkdir();
		}
		File xDir = new File(zoomDir, ((Integer)xTile).toString());
		if(!xDir.exists()) {
			xDir.mkdir();
		}
		URLConnection connection = tileURL.openConnection();
		connection.setConnectTimeout(5000);
		connection.setUseCaches(false);
		connection.setReadTimeout(5000);
		try {
			
			connection.connect();
			ReadableByteChannel readableByteChannel = Channels.newChannel(connection.getInputStream());
			FileOutputStream fileOutputStream = new FileOutputStream(new File(xDir, yTile + ".png")); //$NON-NLS-1$
			WritableByteChannel writableByteChannel = Channels.newChannel(fileOutputStream);
			IOUtils.channelCopy(readableByteChannel, writableByteChannel);
			fileOutputStream.close();
		} catch (SocketTimeoutException e) {
			// socket timeout
		} catch (UnknownHostException e) {
			Logger.getLogger(getClass()).debug("Host not found");
		}
		
	}

	public IStatus clearCache() {
		if(deleteDir(tileDirectory)) {
			return new Status(IStatus.OK, OpenSeaMapActivator.PLUGIN_ID, "Successfully deleted cache directory"); //$NON-NLS-1$
		} else {
			return new Status(IStatus.ERROR, OpenSeaMapActivator.PLUGIN_ID, "Failed to delete cache directory"); //$NON-NLS-1$
		}
	}

	public static boolean deleteDir(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
}
