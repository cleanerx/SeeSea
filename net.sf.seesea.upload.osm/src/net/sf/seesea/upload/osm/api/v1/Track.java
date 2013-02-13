package net.sf.seesea.upload.osm.api.v1;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import net.sf.seesea.upload.osm.OSMUploadActivator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.eclipse.core.runtime.IStatus;

public class Track {

	private final String baseURL;

	public Track(String baseURL) {
		this.baseURL = baseURL;
	}

	public ResultStatus<Integer> newId(String sessionId) {
		HttpClient client = new HttpClient();
		GetMethod method = null;
		try {
			method = new GetMethod(MessageFormat.format(
					"{0}/api1/auth/newid", baseURL)); //$NON-NLS-1$
			method.addRequestHeader("Cookie", "sessionId=" + sessionId);
			int statusCode = client.executeMethod(method);
			if (statusCode == 200) {
				int trackId = Integer
						.parseInt(method.getResponseBodyAsString());
				return new ResultStatus<Integer>(trackId, IStatus.OK,
						OSMUploadActivator.PLUGIN_ID, "OK");
			} else if (statusCode == 201) {
				return new ResultStatus<Integer>(IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID,
						"Unable to fetch new track id");
			} else if (statusCode == 999) {
				return new ResultStatus<Integer>(IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID, "Server side error");
			} else {
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

	public ResultStatus<Integer> upload(String sessionId, Integer trackId,
			File track) {
		HttpClient client = new HttpClient();
		PostMethod method = null;
		try {
			method = new PostMethod(MessageFormat.format(
					"{0}/api1/auth/logout", baseURL)); //$NON-NLS-1$
			method.addRequestHeader("Cookie", "sessionId=" + sessionId);
			method.addRequestHeader("X-Track-Id", trackId.toString());
			Part[] parts = { new FilePart("track", track) };
			method.setRequestEntity(new MultipartRequestEntity(parts,
					method.getParams()));
			int statusCode = client.executeMethod(method);
			if (statusCode == 200) {
				int length = Integer.parseInt(method.getResponseBodyAsString());
				return new ResultStatus<Integer>(length, IStatus.OK,
						OSMUploadActivator.PLUGIN_ID, "OK");
			} else if (statusCode == 202) {
				return new ResultStatus<Integer>(IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID, "Invalid track id");
			} else if (statusCode == 203) {
				return new ResultStatus<Integer>(IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID, "Upload already done");
			} else if (statusCode == 803) {
				return new ResultStatus<Integer>(IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID,
						"Header X-Track Id not set");
			} else if (statusCode == 804) {
				return new ResultStatus<Integer>(IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID,
						"Missing filed name track");
			} else if (statusCode == 999) {
				return new ResultStatus<Integer>(IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID, "Server side error");
			} else {
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
