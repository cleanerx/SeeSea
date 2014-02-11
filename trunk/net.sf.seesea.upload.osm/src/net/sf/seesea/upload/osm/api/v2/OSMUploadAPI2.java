package net.sf.seesea.upload.osm.api.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.seesea.lib.ResultStatus;
import net.sf.seesea.upload.IDataUpload;
import net.sf.seesea.upload.osm.OSMUploadActivator;
import net.sf.seesea.upload.osm.api.v1.Messages;
import net.sf.seesea.upload.osm.api.v2.messages.License;
import net.sf.seesea.upload.osm.api.v2.messages.Track;
import net.sf.seesea.upload.osm.api.v2.messages.VesselConfiguration;

import org.apache.commons.httpclient.HttpStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
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
	
	public OSMUploadAPI2() {
		loggedIn = false;
	}
	
	@Override
	public IStatus login(String username, String password) {
		client = ClientBuilder.newClient();
		client.register(new HttpBasicAuthFilter(username, password));
		client.register(MOXyJsonProvider.class);
		client.register(MultiPartFeature.class);
		LoggingFilter loggingFilter = new LoggingFilter();
		client.register(loggingFilter);

		basePath = client.target("http://localhost:8100").path("org.osm.depth.upload").path("api2");
		WebTarget path = basePath.path("vesselconfig"); //$NON-NLS-1$
        Response response = path.request(MediaType.APPLICATION_JSON).get();
        if(response.getStatus() == HttpStatus.SC_OK) {
        	loggedIn = true;
        	return Status.OK_STATUS;
        }
		return new Status(IStatus.ERROR, "", "Login failed");
	}

	@Override
	public IStatus logout(String username) {
		client = null;
		return Status.OK_STATUS;
	}

	@Override
	public IStatus upload(List<File> track, IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStatus upload(Long licenseID, Long vesselConfigurationID,
			List<File> track, IProgressMonitor monitor) {
		MultiStatus multiStatus = new MultiStatus(OSMUploadActivator.PLUGIN_ID,
				IStatus.OK, Messages.getString("OSMDataUpload.fileUpload"), null); //$NON-NLS-1$

		WebTarget trackPath = basePath.path("track");

		for (File file : track) {
			Track track3 = new Track();
			track3.vesselconfigid = vesselConfigurationID;
			track3.license = licenseID;
			track3.fileName = file.getName();
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
					response = trackPath.request(MediaType.APPLICATION_JSON).put(Entity.entity(multipart, multipart.getMediaType()));
					if(response.getStatus() == HttpStatus.SC_OK) {
						multiStatus.add(new ResultStatus<File>(file, IStatus.OK,
								OSMUploadActivator.PLUGIN_ID, "Uploaded file " + file.getName()));
					} else {
						multiStatus.add(new ResultStatus<Object>(file, IStatus.ERROR,
								OSMUploadActivator.PLUGIN_ID, "Failed to contact server."));
					}
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
