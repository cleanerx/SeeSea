/**
 * 
 Copyright (c) 2010-2013, Jens Kübler All rights reserved.
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
package net.sf.seesea.upload.osm.api.v1;

import java.io.File;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

import net.sf.seesea.lib.IResultStatus;
import net.sf.seesea.lib.ResultStatus;
import net.sf.seesea.upload.IDataUpload;
import net.sf.seesea.upload.osm.OSMUploadActivator;

public class OSMDataUpload implements IDataUpload {

	private String baseURL;
	private Authentication authentication;
	private Track track;
	private String sessionId;
	private HttpClient httpClient;

	public OSMDataUpload(String server) {
		baseURL = server;
		httpClient = new HttpClient();
		authentication = new Authentication(httpClient, server);
		track = new Track(httpClient, baseURL);
	}

	@Override
	public IStatus login(String username, String password) {
		IResultStatus<String> login = authentication.login(username, password);
		sessionId = login.getResult();
		return new Status(login.getSeverity(), login.getPlugin(),
				login.getMessage());
	}

	@Override
	public IResultStatus<String> logout(String username) {
		return authentication.logout(sessionId);
	}

	@Override
	public IStatus upload(List<File> files, IProgressMonitor monitor) {
		MultiStatus multiStatus = new MultiStatus(OSMUploadActivator.PLUGIN_ID,
				IStatus.OK, "File Upload", null);

		if (sessionId != null) {
			for (File file : files) {
				ResultStatus<Integer> newId = track.newId(sessionId);
				if (newId.isOK()) {
					if (monitor.isCanceled()) {
						break;
					}
					ResultStatus<Long> uploadStatus = track.upload(sessionId,
							newId.getResult(), file, monitor);
					if (uploadStatus.isOK()) {
						Long uploadSize = uploadStatus.getResult();
						long fileLength = file.length();
						if (uploadSize != fileLength) {
							multiStatus.add(new Status(IStatus.ERROR,
									OSMUploadActivator.PLUGIN_ID,
									"File length upload does not match"));
						} else {
							multiStatus.add(uploadStatus);
						}
					} else {
						multiStatus.add(uploadStatus);
					}
				}
			}
		}
		return multiStatus;
	}

}
