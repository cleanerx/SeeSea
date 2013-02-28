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
import java.io.IOException;
import java.text.MessageFormat;

import net.sf.json.JSONObject;
import net.sf.seesea.lib.ResultStatus;
import net.sf.seesea.upload.osm.OSMUploadActivator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class Track {

	private final String baseURL;
	private final HttpClient client;

	public Track(HttpClient httpClient, String baseURL) {
		this.client = httpClient;
		this.baseURL = baseURL;
	}

	public ResultStatus<Integer> newId(String sessionId) {
		PostMethod method = null;
		try {
			method = new PostMethod(MessageFormat.format(
					"{0}/api1/track/newid", baseURL)); //$NON-NLS-1$

			int statusCode = client.executeMethod(method);
			String responseBodyAsString = method.getResponseBodyAsString();
			JSONObject jsonObject = JSONObject.fromObject(responseBodyAsString);
			if (statusCode == 200) {
				int trackId = jsonObject.getInt("trackId"); //$NON-NLS-1$
				return new ResultStatus<Integer>(trackId, IStatus.OK,
						OSMUploadActivator.PLUGIN_ID, "OK"); //$NON-NLS-1$
			} else {
				int jsonCode = jsonObject.getInt("code"); //$NON-NLS-1$
				if (jsonCode == 201) {
					return new ResultStatus<Integer>(IStatus.ERROR,
							OSMUploadActivator.PLUGIN_ID,
							"Unable to fetch new track id");
				} else if (jsonCode == 999) {
					return new ResultStatus<Integer>(IStatus.ERROR,
							OSMUploadActivator.PLUGIN_ID, "Server side error");
				}
				return new ResultStatus<Integer>(IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID, "Unknown Error");
			}
		} catch (HttpException e) {
			return new ResultStatus<Integer>(IStatus.ERROR,
					OSMUploadActivator.PLUGIN_ID, "Failed to contact server.",
					e);
		} catch (IOException e) {
			return new ResultStatus<Integer>(IStatus.ERROR,
					OSMUploadActivator.PLUGIN_ID, "Failed to contact server.",
					e);
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}
	}

	public ResultStatus<Object> upload(String sessionId, Integer trackId,
			File track, IProgressMonitor monitor) {
		PostMethod method = null;
		try {
			method = new PostMethod(MessageFormat.format(
					"{0}/api1/track/upload", baseURL)); //$NON-NLS-1$
			method.addRequestHeader("Cookie", "sessionId=" + sessionId);  //$NON-NLS-1$//$NON-NLS-2$
			method.addRequestHeader("X-Track-Id", trackId.toString()); //$NON-NLS-1$
			Part[] parts = { new FilePart("track", track) }; //$NON-NLS-1$
			method.setRequestEntity(new MultipartRequestEntity(parts, method
					.getParams()));
			int statusCode = client.executeMethod(method);
			JSONObject jsonObject = JSONObject.fromObject(method.getResponseBodyAsString());
			if (statusCode == 200) {
				long length = jsonObject.getLong("length"); //$NON-NLS-1$
				return new ResultStatus<Object>(length, IStatus.OK,
						OSMUploadActivator.PLUGIN_ID, "OK"); //$NON-NLS-1$
			} else if(statusCode == 404) {
				return new ResultStatus<Object>(track, IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID, "This web page is temporarily not available.") ;
			} else {
				int jsonCode = jsonObject.getInt("code"); //$NON-NLS-1$
				if (jsonCode == 202) {
					return new ResultStatus<Object>(track, IStatus.ERROR,
							OSMUploadActivator.PLUGIN_ID, "Trying to upload with an invalid track id for file " + track.getName());
				} else if (jsonCode == 203) {
					return new ResultStatus<Object>(track, IStatus.ERROR,
							OSMUploadActivator.PLUGIN_ID, "Upload has already been done for file " + track.getName());
				} else if (jsonCode == 803) {
					return new ResultStatus<Object>(track, IStatus.ERROR,
							OSMUploadActivator.PLUGIN_ID,
							"Header X-Track Id not set");
				} else if (jsonCode == 804) {
					return new ResultStatus<Object>(track, IStatus.ERROR,
							OSMUploadActivator.PLUGIN_ID,
							"Missing filed name track");
				} else if (jsonCode == 999) {
					return new ResultStatus<Object>(track, IStatus.ERROR,
							OSMUploadActivator.PLUGIN_ID, "Server side error for file " + track.getName());
				}
				return new ResultStatus<Object>(track, IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID, "HTTP Error " + statusCode + " for file " + track.getName()) ;
				}
		} catch (HttpException e) {
			return new ResultStatus<Object>(track, IStatus.ERROR,
					OSMUploadActivator.PLUGIN_ID, "Failed to contact server.",
					e);
		} catch (IOException e) {
			return new ResultStatus<Object>(track, IStatus.ERROR,
					OSMUploadActivator.PLUGIN_ID, "Failed to contact server.",
					e);
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}
	}

	public UploadState status(int trackId) {
		// HttpClient client = new HttpClient();
		// PostMethod method = null;
		// try {
		//			method = new PostMethod(MessageFormat.format("{0}/api1/auth/logout",baseURL));  //$NON-NLS-1$
		// method.addRequestHeader("Cookie", "sessionId=" + sessionId);
		// method.addRequestHeader("X-Track-Id", trackId.toString());
		// int statusCode = client.executeMethod(method);
		// if(statusCode == 200) {
		// int length = Integer.parseInt(method.getResponseBodyAsString());
		// return new ResultStatus<UploadState>(length,IStatus.OK,
		// OSMUploadActivator.PLUGIN_ID, "OK");
		// } else if(statusCode == 999) {
		// return new ResultStatus<UploadState>(IStatus.ERROR,
		// OSMUploadActivator.PLUGIN_ID, "Server side error");
		// } else {
		// return new ResultStatus<UploadState>(IStatus.ERROR,
		// OSMUploadActivator.PLUGIN_ID, "Unknown Error");
		// }
		// } catch (HttpException e) {
		// return new ResultStatus<UploadState>(IStatus.ERROR,
		// OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		// } catch (IOException e) {
		// return new ResultStatus<UploadState>(IStatus.ERROR,
		// OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		// } finally {
		// if(method != null) {
		// method.releaseConnection();
		// }
		// }
		return null;
	}

}
