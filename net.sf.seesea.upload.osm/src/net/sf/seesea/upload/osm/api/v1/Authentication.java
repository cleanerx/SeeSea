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

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import net.sf.json.JSONObject;
import net.sf.seesea.lib.IResultStatus;
import net.sf.seesea.lib.ResultStatus;
import net.sf.seesea.upload.osm.OSMUploadActivator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

/**
 *  @see http://testdepth.openseamap.org/api1/index.html
 */
public class Authentication {

	private static final String SHA_1 = "SHA-1"; //$NON-NLS-1$
	private final String baseURL;
	private final HttpClient client;

	public Authentication(HttpClient client , String baseURL) {
		this.client = client;
		this.baseURL = baseURL;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public ImageData captcha() throws IOException {
		PostMethod method = new PostMethod(baseURL + "/api1/auth/captcha"); //$NON-NLS-1$
		int statusCode = client.executeMethod(method);
		
		if(statusCode == 200) {
			ImageLoader imageLoader = new ImageLoader();
			InputStream responseBodyAsStream = method.getResponseBodyAsStream();
			if(responseBodyAsStream == null) {
				throw new IOException("Failed to load captcha"); //$NON-NLS-1$
			} 
			
			ImageData[] imageData = imageLoader.load(responseBodyAsStream);
			method.releaseConnection();
			
			return imageData[0];
		}
		throw new IOException("Failed to contact server"); //$NON-NLS-1$
	}
	
	public IStatus createAccount(String user, String password, String captcha) {
		PostMethod method = null;
        try {
        	String shapassword = encryptPassword(password);
			method = new PostMethod(MessageFormat.format("{0}/api1/auth/create", baseURL)); //$NON-NLS-1$
			
			NameValuePair[] nameValuePairs = new NameValuePair[3];
			nameValuePairs[0] = new NameValuePair("username",user); //$NON-NLS-1$
			nameValuePairs[1] = new NameValuePair("password",shapassword); //$NON-NLS-1$
			nameValuePairs[2] = new NameValuePair("captcha",captcha); //$NON-NLS-1$
			method.addParameters(nameValuePairs);
//			method.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			int statusCode = client.executeMethod(method);
			String responseBodyAsString = method.getResponseBodyAsString();
			JSONObject jsonObject = JSONObject.fromObject(responseBodyAsString);
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
		PostMethod method = null;
		try {
        	String shapassword = encryptPassword(password);
			method = new PostMethod(MessageFormat.format("{0}/api1/auth/login", baseURL)); //$NON-NLS-1$
			
			NameValuePair[] nameValuePairs = new NameValuePair[2];
			nameValuePairs[0] = new NameValuePair("username",username); //$NON-NLS-1$
			nameValuePairs[1] = new NameValuePair("password",shapassword); //$NON-NLS-1$
			method.addParameters(nameValuePairs);

			int statusCode = client.executeMethod(method);
			String responseBodyAsString = method.getResponseBodyAsString();
			JSONObject jsonObject = JSONObject.fromObject(responseBodyAsString);
			if(statusCode == 200) {
				String sessionId = (String) jsonObject.get("session_id"); //$NON-NLS-1$ 
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
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}finally {
			if(method != null) {
				method.releaseConnection();
			}
		}
	}

	private String encryptPassword(String password) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest cript = MessageDigest.getInstance(SHA_1);
		cript.reset();
		cript.update(password.getBytes("utf8")); //$NON-NLS-1$
		String shapassword = new BigInteger(1, cript.digest()).toString(16);
		return shapassword;
	}


	public IResultStatus<String> logout(String sessionId) {
		PostMethod method = null;
		try {
			method = new PostMethod(MessageFormat.format("{0}/api1/auth/logout",baseURL));  //$NON-NLS-1$
			method.addRequestHeader("Cookie", "sessionId=" + sessionId); //$NON-NLS-1$ //$NON-NLS-2$
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
		PostMethod method = null;
		try {
			String oldshapassword = encryptPassword(oldpassword);
			String newshapassword = encryptPassword(newpassword);
			method = new PostMethod(MessageFormat.format("{0}/api1/auth/changepassword=old={1}&new={2}",baseURL, oldshapassword, newshapassword));  //$NON-NLS-1$
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
