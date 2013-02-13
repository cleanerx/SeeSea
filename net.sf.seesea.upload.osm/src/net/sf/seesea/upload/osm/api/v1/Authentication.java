package net.sf.seesea.upload.osm.api.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import net.sf.seesea.upload.osm.OSMUploadActivator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

/**
 *  @see http://testdepth.openseamap.org/api1/index.html
 */
public class Authentication {

	private final String baseURL;

	public Authentication(String baseURL) {
		this.baseURL = baseURL;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public ImageData[] captcha() throws IOException {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(baseURL + "/api1/auth/captcha"); //$NON-NLS-1$
		int statusCode = client.executeMethod(method);
		
		if(statusCode == 999) {
			throw new IOException("Server Side Error: Reason unknown");
		}
		
		ImageLoader imageLoader = new ImageLoader();
		InputStream responseBodyAsStream = method.getResponseBodyAsStream();
		if(responseBodyAsStream == null) {
			throw new IOException("Failed to load captcha");
		} 
		ImageData[] imageData = imageLoader.load(responseBodyAsStream);
		method.releaseConnection();

		return imageData;
	}
	
	public IStatus createAccount(String user, String password, String captcha) {
		HttpClient client = new HttpClient();
		GetMethod method = null;
//		URLEncoder.encode(s, enc);
        try {
        	MessageDigest cript = MessageDigest.getInstance("SHA-1");
        	cript.reset();
			cript.update(password.getBytes("utf8")); //$NON-NLS-1$
			String shapassword = new String(cript.digest());
			method = new GetMethod(MessageFormat.format("{0}/api1/auth/create=username={1}&password={2}&captcha={3}",baseURL, user, shapassword, captcha));  //$NON-NLS-1$
			int statusCode = client.executeMethod(method);
			if(statusCode == 200) {
				return new Status(IStatus.OK, OSMUploadActivator.PLUGIN_ID, "OK");
			} else if(statusCode == 103) {
				return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Username exists");
			} else if(statusCode == 801) {
				return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Invalid Captcha");
			} else if(statusCode == 802) {
				return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Invalid password");
			} else if(statusCode == 999) {
				return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Server side error");
			} else {
				return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Unknown Error");
			}
		} catch (UnsupportedEncodingException e1) {
			return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "SHA 1 Encryption required in order to provide uploads");
		} catch (HttpException e) {
			return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		} catch (IOException e) {
			return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		} catch (NoSuchAlgorithmException e) {
			return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "SHA 1 Encryption required in order to provide uploads");		
		} finally {
			if(method != null) {
				method.releaseConnection();
			}
		}
	}
	
	public IResultStatus<String> login(String username, String password) {
		HttpClient client = new HttpClient();
		GetMethod method = null;
		try {
        	MessageDigest cript = MessageDigest.getInstance("SHA-1");
        	cript.reset();
			cript.update(password.getBytes("utf8")); //$NON-NLS-1$
			String shapassword = new String(cript.digest());
			method = new GetMethod(MessageFormat.format("{0}/api1/auth/login=username={1}&password={2}&captcha={3}",baseURL, username, shapassword));  //$NON-NLS-1$
			int statusCode = client.executeMethod(method);
			if(statusCode == 200) {
				String sessionId = method.getResponseBodyAsString(); 
				return new ResultStatus<String>(sessionId,IStatus.OK, OSMUploadActivator.PLUGIN_ID, "OK");
			} else if(statusCode == 103) {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Username exists");
			} else if(statusCode == 801) {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Invalid Captcha");
			} else if(statusCode == 802) {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Invalid password");
			} else if(statusCode == 999) {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Server side error");
			} else {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Unknown Error");
			}
		} catch (HttpException e) {
			return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		} catch (IOException e) {
			return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		} catch (NoSuchAlgorithmException e) {
			return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "SHA 1 Encryption required in order to provide uploads");
		} finally {
			if(method != null) {
				method.releaseConnection();
			}
		}
	}


	public IResultStatus<String> logout(String sessionId) {
		HttpClient client = new HttpClient();
		GetMethod method = null;
		try {
			method = new GetMethod(MessageFormat.format("{0}/api1/auth/logout",baseURL));  //$NON-NLS-1$
			method.addRequestHeader("Cookie", "sessionId=" + sessionId);
			int statusCode = client.executeMethod(method);
			if(statusCode == 200) {
				return new ResultStatus<String>(sessionId,IStatus.OK, OSMUploadActivator.PLUGIN_ID, "OK");
			} else if(statusCode == 999) {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Server side error");
			} else {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Unknown Error");
			}
		} catch (HttpException e) {
			return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		} catch (IOException e) {
			return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		} finally {
			if(method != null) {
				method.releaseConnection();
			}
		}
	}

	public IStatus changePassword(String sessionId, String oldpassword, String newpassword) {
		HttpClient client = new HttpClient();
		GetMethod method = null;
		try {
        	MessageDigest cript = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
			cript.reset();
			cript.update(oldpassword.getBytes("utf8")); //$NON-NLS-1$
			String oldshapassword = new String(cript.digest());
			cript.reset();
			cript.update(oldpassword.getBytes("utf8")); //$NON-NLS-1$
			String newshapassword = new String(cript.digest());
			method = new GetMethod(MessageFormat.format("{0}/api1/auth/changepassword=old={1}&new={2}",baseURL, oldshapassword, newshapassword));  //$NON-NLS-1$
			method.addRequestHeader("Cookie", "sessionId=" + sessionId);
			
			int statusCode = client.executeMethod(method);
			if(statusCode == 200) {
				return new ResultStatus<String>(sessionId,IStatus.OK, OSMUploadActivator.PLUGIN_ID, "OK");
			} else if(statusCode == 101) {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Username or password wrong");
			} else if(statusCode == 802) {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Invalid password");
			} else if(statusCode == 999) {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Server side error");
			} else {
				return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Unknown Error");
			}		} catch (HttpException e) {
			return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		} catch (IOException e) {
			return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Failed to contact server.", e);
		} catch (NoSuchAlgorithmException e) {
			return new ResultStatus<String>(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "SHA 1 Encryption required in order to provide uploads");
		} finally {
			if(method != null) {
				method.releaseConnection();
			}
		}
	}

}
