package net.sf.seesea.upload.osm.api.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.seesea.lib.ResultStatus;
import net.sf.seesea.upload.osm.OSMUploadActivator;
import net.sf.seesea.upload.osm.api.v2.messages.License;
import net.sf.seesea.upload.osm.api.v2.messages.Track;
import net.sf.seesea.upload.osm.api.v2.messages.VesselConfiguration;

import org.apache.commons.httpclient.HttpStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

public class OSMUploadAPI2 implements IOSMUpload {

	private Client client;
	private WebTarget basePath;
	private boolean loggedIn;
	private Client fileclient;
	private String location;
	private String[] paths;
	
	public OSMUploadAPI2() {
		loggedIn = false;
	}
	
	public void activate(Map<String, Object> config) throws MalformedURLException {
		String url = (String) config.get(IOSMUpload.URL);
		java.net.URL url2 = new URL(url);
		String protocol = url2.getProtocol();
		String host = url2.getHost();
		int port2 = url2.getPort();
		
		paths = url2.getPath().split("/");
		if (port2 == - 1){
			location = protocol + "://" + host;
		} else {
			location = protocol + "://" + host + ":" + Integer.toString(port2);
		}	}

	public void modified(Map<String, Object> config) throws MalformedURLException {
		loggedIn = false;
		client = null;
		fileclient = null;
		String url = (String) config.get(IOSMUpload.URL);
		java.net.URL url2 = new URL(url);
		String protocol = url2.getProtocol();
		String host = url2.getHost();
		int port2 = url2.getPort();
		
//		if(url2.getPath().lastIndexOf("/") == url2.getPath().length()) {
//			
//		}
		String[] split = url2.getPath().split("/");
		paths = Arrays.copyOfRange(split, 1,split.length);
		if (port2 == - 1){
			location = protocol + "://" + host;
		} else {
			location = protocol + "://" + host + ":" + Integer.toString(port2);
		}

	}

	public void deactivate(Map<String, Object> config) {
		loggedIn = false;
		client = null;
		fileclient = null;
	}
	
	@Override
	public IStatus login(String username, String password) {
		HttpUrlConnectorProvider.ConnectionFactory factory = new HttpUrlConnectorProvider.ConnectionFactory() {

			@Override
			public HttpURLConnection getConnection(URL arg0) throws IOException {
				HttpURLConnection conn = (HttpURLConnection) arg0.openConnection(); 
				conn.setChunkedStreamingMode(2048);
				conn.setDoOutput(true);
				return conn;
			}
			
		};
		
		// avoid OutOfMemoryError https://java.net/jira/browse/JERSEY-2024
		ClientConfig clientConfig = new ClientConfig();

		HttpUrlConnectorProvider connectorProvider = new HttpUrlConnectorProvider();
		connectorProvider.connectionFactory(factory);
 
		
		clientConfig.connectorProvider(connectorProvider);  
		client = ClientBuilder.newClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(username, password);
		client.register(feature);
		client.register(MOXyJsonProvider.class);
		client.register(MultiPartFeature.class);
		LoggingFilter loggingFilter = new LoggingFilter();
		client.register(loggingFilter);
		
		fileclient = ClientBuilder.newClient(clientConfig);
		
		fileclient.register(feature);
		fileclient.register(MOXyJsonProvider.class);
		fileclient.register(MultiPartFeature.class);
		LoggingFilter loggingFilterfile = new LoggingFilter();
		fileclient.register(loggingFilterfile);

		WebTarget target = client.target(location);
		basePath = target;
		for (String path : paths) {
			basePath = basePath.path(path);
		}
//		basePath = target.path("org.osm.depth.upload").path("api2");
		WebTarget path = basePath.path("vesselconfig"); //$NON-NLS-1$
        try {
    		Response response = path.request(MediaType.APPLICATION_JSON).get();
            if(response.getStatus() == HttpStatus.SC_OK) {
            	loggedIn = true;
            	return Status.OK_STATUS;
            }
        } catch (Exception e) {
        	return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Login failed: " + e.getLocalizedMessage());
		}
		return new Status(IStatus.ERROR, OSMUploadActivator.PLUGIN_ID, "Login failed");
	}

	@Override
	public IStatus logout(String username) {
		client = null;
		return Status.OK_STATUS;
	}


	@Override
	public IStatus upload(Long licenseID, Long vesselConfigurationID,
			List<File> track, IProgressMonitor monitor) {
		MultiStatus multiStatus = new MultiStatus(OSMUploadActivator.PLUGIN_ID,
				IStatus.OK, "File Upload", null); 

		WebTarget trackPath = basePath.path("track");
		WebTarget filePath = fileclient.target(location);
		for (String path : paths) {
			filePath = filePath.path(path);
		}

		filePath = filePath.path("track");

		for (File file : track) {
			Track track3 = new Track();
			track3.vesselconfigid = vesselConfigurationID;
			track3.license = licenseID;
			track3.fileName = file.getName();
			monitor.subTask(file.getName());
			Response response = trackPath.request(MediaType.APPLICATION_JSON).post(Entity.entity(track3, MediaType.APPLICATION_JSON));
	        if(response.getStatus() == HttpStatus.SC_OK) {
				Track partiallyPersistedTrack = response.readEntity(Track.class);
	        	FormDataMultiPart multipart = new FormDataMultiPart();
	        	multipart.field("id", new Long(partiallyPersistedTrack.id).toString());
				FormDataBodyPart formDataBodyPart = new FormDataBodyPart("track", file,MediaType.MULTIPART_FORM_DATA_TYPE);
				StreamDataBodyPart streamDataBodyPart;
				try {
					streamDataBodyPart = new StreamDataBodyPart("track", new FileInputStream(file));
					multipart.bodyPart(formDataBodyPart).bodyPart(streamDataBodyPart).type(new MediaType("multipart", "form-data",
							Collections.singletonMap(Boundary.BOUNDARY_PARAMETER, Boundary.createBoundary())));
					response = filePath.request(MediaType.APPLICATION_JSON).put(Entity.entity(multipart, multipart.getMediaType()));
					if(response.getStatus() == HttpStatus.SC_OK) {
						multiStatus.add(new ResultStatus<File>(file, IStatus.OK,
								OSMUploadActivator.PLUGIN_ID, "Uploaded file " + file.getName()));
					} else {
						multiStatus.add(new ResultStatus<Object>(file, IStatus.ERROR,
								OSMUploadActivator.PLUGIN_ID, "Failed to contact server."));
					}
					monitor.worked(1);
				} catch (FileNotFoundException e) {
					multiStatus.add(new ResultStatus<Object>(file, IStatus.ERROR,
							OSMUploadActivator.PLUGIN_ID, "Failed to open file."));
				}
	        } else {
				multiStatus.add(new ResultStatus<Object>(file, IStatus.ERROR,
						OSMUploadActivator.PLUGIN_ID, "Failed to contact server."));
	        }
		}

		return multiStatus;
	}
	
	public List<VesselConfiguration> getVesselConfigurations() {
		WebTarget vesselPath = basePath.path("vesselconfig"); //$NON-NLS-1$
        Response response = vesselPath.request(MediaType.APPLICATION_JSON).get();
        if(response.getStatus() == HttpStatus.SC_OK) {
        	return response.readEntity(new GenericType<List<VesselConfiguration>>(){});
        }
        return Collections.emptyList();
	}

	public List<License> getLicenses() {
		WebTarget licensePath = basePath.path("license"); //$NON-NLS-1$
        Response response = licensePath.request(MediaType.APPLICATION_JSON).get();
        if(response.getStatus() == HttpStatus.SC_OK) {
        	return response.readEntity(new GenericType<List<License>>(){});
        }
        return Collections.emptyList();
	}
	
	public boolean isLoggedIn() {
		return client != null && loggedIn;
	}


}
