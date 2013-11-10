package org.osm.depth.upload;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.osm.depth.upload.messages.VesselConfiguration;

import junit.framework.TestCase;

public class VesselConfigurationResourceTest extends TestCase {

	private Client client;
	private WebTarget basePath;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		client = ClientBuilder.newClient();
		client.register(new HttpBasicAuthFilter("x", "x"));
		client.register(MOXyJsonProvider.class);
		client.register(MultiPartFeature.class);
		LoggingFilter loggingFilter = new LoggingFilter();
		client.register(loggingFilter);

		basePath = client.target("http://localhost:8100").path("org.osm.depth.upload").path("api2");
	}
	
	public void testCreatDeleteRoundtrip() {
		WebTarget path = basePath.path("vesselconfig"); //$NON-NLS-1$
		
		VesselConfiguration vesselConfiguration = new VesselConfiguration();
		vesselConfiguration.name = "test";
		Response response = path.request(MediaType.APPLICATION_JSON).post(Entity.entity(vesselConfiguration, MediaType.APPLICATION_JSON));
		assertEquals(200, response.getStatus());
		VesselConfiguration vesselConfiguration2 = response.readEntity(VesselConfiguration.class);
		
		path.path(((Long)vesselConfiguration2.id).toString()).request(MediaType.APPLICATION_JSON).delete();
	}
	
}
