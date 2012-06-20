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

import java.net.MalformedURLException;
import java.net.URL;

import net.sf.seesea.tileservice.ITileSource;

import org.apache.log4j.Logger;

/**
 * A Tile source provider that loads data from a online connection
 *
 */
public class OpenStreetmapServerTileSource implements ITileSource {

	/**
	 * 
	 * @param server the hostname of the OSM server
	 * @param minZoom minimum zoom level this server supports
	 * @param maxZoom maximum zoom level this server supports
	 */
	public OpenStreetmapServerTileSource(URL server, int minZoom, int maxZoom) {
		super();
		this.server = server;
		this.minZoom = minZoom;
		this.maxZoom = maxZoom;
	}

	private final URL server;
	
	private final int minZoom;

	private final int maxZoom;

	@Override
	public int getMaxZoom() {
		return maxZoom;
	}

	@Override
	public int getMinZoom() {
		return minZoom;
	}
	
    @Override
	public URL getTileURL(int xTile, int yTile, int zoom) {
        String url = server.toExternalForm() + zoom + "/" + xTile + "/" + yTile + ".png"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        try {
			return new URL(url);
		} catch (MalformedURLException e) {
			Logger.getLogger(OpenStreetmapServerTileSource.class).error("Failed to create URL", e); //$NON-NLS-1$
			return null;
		}
    }
    

}
