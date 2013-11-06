package org.osm.depth.upload.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.osm.depth.upload.exceptions.DatabaseException;
import org.osm.depth.upload.exceptions.ResourceInUseException;
import org.osm.depth.upload.messages.Gauge;
import org.osm.depth.upload.messages.GaugeType;

@Path("/gauge")
public class GaugeResource {

//	@GET
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{gaugeid}/measurement")
	public GaugeMeasurementResource getAllGauges(@PathParam(value = "gaugeid") String gaugeId) {
		return new GaugeMeasurementResource(gaugeId);
////		List<GaugeMeasurement> gaugeMeasurement = new ArrayList<>();
////		GaugeMeasurement gaugeMeasurement2 = new GaugeMeasurement();
////		gaugeMeasurement2.value = 1.0f;
////		gaugeMeasurement.add(gaugeMeasurement2);
////		return gaugeMeasurement;
	}
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Gauge> getAllGauges(@javax.ws.rs.core.Context SecurityContext context) {
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			ResultSet executeQuery;
//			if(context.isUserInRole("ADMIN")) { //$NON-NLS-1$
				executeQuery = statement.executeQuery("SELECT * FROM gauge g"); //$NON-NLS-1$
//			} else {
//				executeQuery = statement.executeQuery("SELECT * FROM license l LEFT OUTER JOIN vesselconfiguration u ON u.user_name = l.user_name WHERE l.user_name='" + username + "' OR l.public = true ORDER BY shortname,name"); //$NON-NLS-1$ //$NON-NLS-2$
//			}
			
			List<Gauge> list = new ArrayList<Gauge>();
			while(executeQuery.next()) {
				Gauge gauge = new Gauge();
				gauge.id = executeQuery.getLong("id");
				gauge.name = executeQuery.getString("name");
				gauge.latitude = executeQuery.getDouble("lat");
				gauge.longitude = executeQuery.getDouble("lon");
				try {
					gauge.gaugeType = GaugeType.valueOf(executeQuery.getString("gaugetype"));
				} catch (IllegalArgumentException e) {
					gauge.gaugeType = GaugeType.UNDEFINED; // no such gauge type
				}
				list.add(gauge);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable");
		}
	}	
	
	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Gauge newGauge(@javax.ws.rs.core.Context SecurityContext context, Gauge gauge) {
		Context initContext;
		try {
			
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();

			Statement createIDStatement = conn.createStatement();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO gauge (id, name, lat, lon, gaugetype) VALUES (?,?,?,?,?)");
			
			ResultSet executeQuery = createIDStatement.executeQuery("SELECT nextval('gauge_id_seq')"); //$NON-NLS-1$
			if(executeQuery.next()) {
				Long id = executeQuery.getLong(1);
				statement.setLong(1, id);
				statement.setString(2, gauge.name);
				statement.setDouble(3, gauge.latitude);
				statement.setDouble(4, gauge.longitude);
				statement.setString(5, gauge.gaugeType.toString());
				statement.execute();
				return gauge;
			} else {
				// failed to create id
			}
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RolesAllowed(value = {"ADMIN"})
	public Response delete(@javax.ws.rs.core.Context SecurityContext context, @PathParam(value = "id") String id) {
		Context initContext;
		try {
			long gaugeId = Long.parseLong(id);
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();

			conn.setAutoCommit(false);
			Statement gaugeUsedinTracks = conn.createStatement();
			Statement deletestatement = conn.createStatement();
			
			ResultSet usedInTracksResultSet = gaugeUsedinTracks.executeQuery("SELECT COUNT(id) FROM gaugetracks WHERE gaugeid ='" + gaugeId + "' ");
			if(usedInTracksResultSet.next() && usedInTracksResultSet.getLong(1) > 0) {
				throw new ResourceInUseException("License is still being used for recorded tracks");
			}
			
			deletestatement.execute(MessageFormat.format("DELETE FROM gauge WHERE id = {0}", //$NON-NLS-1$
							gaugeId));
			conn.commit();
			deletestatement.close();
			return Response.ok().build();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		}
	}

}
