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
package net.sf.seesea.track.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.sf.seesea.track.api.data.IContainedTrackFile;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.InputStreamNotFoundException;

public class ZipEntryTrackFile extends AbstractTrackFile implements IContainedTrackFile {

	private final ZipFile zipFile;
	private final ZipEntry entry;

	public ZipEntryTrackFile(ZipFile zipFile, ZipEntry entry) {
		this.zipFile = zipFile;
		this.entry = entry;
		
	}

	@Override
	public InputStream getInputStream() throws InputStreamNotFoundException {
		try {
			return zipFile.getInputStream(entry);
		} catch (IOException e) {
			throw new InputStreamNotFoundException(e);
		}
	}

	public ZipFile getZipFile() {
		return zipFile;
	}

	@Override
	public URL getTrackURL() {
		return null;
	}

	@Override
	public String getTrackQualifier() {
		return entry.getName();
	}

	@Override
	public Collection<ITrackFile> getTrackFiles() {
		// TODO Auto-generated method stub
		return null;
	}

}
