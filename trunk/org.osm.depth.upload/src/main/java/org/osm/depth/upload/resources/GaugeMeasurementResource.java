package org.osm.depth.upload.resources;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.osm.depth.upload.exceptions.DatabaseException;
import org.osm.depth.upload.exceptions.ResourceInUseException;
import org.osm.depth.upload.messages.Gauge;
import org.osm.depth.upload.messages.GaugeMeasurement;
import org.osm.depth.upload.messages.GaugeType;
import org.osm.depth.upload.messages.LengthUnit;

//@Path("/gauge//measurement")
public class GaugeMeasurementResource {

	private String id;

	public GaugeMeasurementResource() {
		
	}
		
	public GaugeMeasurementResource(String id) {
		this.id = id;		
	}
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	// @DefaultValue(value = "METERS") @QueryParam(value = "unit") LengthUnit lengthUnit
	public List<GaugeMeasurement> getGaugesMeasurements() {
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				PreparedStatement statement = conn.prepareStatement("SELECT gaugeid, value, time FROM gaugemeasurement g WHERE gaugeid = ? ORDER BY time DESC"); //$NON-NLS-1$
				try {
					statement.setLong(1, Long.parseLong(id));
					ResultSet executeQuery = statement.executeQuery();
					
					List<GaugeMeasurement> list = new ArrayList<GaugeMeasurement>();
					while(executeQuery.next()) {
						GaugeMeasurement gaugeMeasurement = new GaugeMeasurement();
						gaugeMeasurement.gaugeId = executeQuery.getLong("gaugeid"); //$NON-NLS-1$
						gaugeMeasurement.value = executeQuery.getFloat("value"); //$NON-NLS-1$
						gaugeMeasurement.timestamp = executeQuery.getTimestamp("time").getTime(); //$NON-NLS-1$
						try {
							gaugeMeasurement.lengthUnit = LengthUnit.METERS;
						} catch (IllegalArgumentException e) {
//					gaugeMeasurement.gaugeType = GaugeType.UNDEFINED; // no such gauge type
						}	
						list.add(gaugeMeasurement);
					}
					return list;
				} finally {
					statement.close();
				}
			} finally {
				conn.close();
			}
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
	public Gauge create(@javax.ws.rs.core.Context SecurityContext context, GaugeMeasurement gaugeMeasurement) {
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				PreparedStatement statement = conn.prepareStatement("INSERT INTO gaugemeasurement (gaugeid, value, time) VALUES (?,?,?)");
				try {
					statement.setLong(1, gaugeMeasurement.gaugeId);
					statement.setFloat(2, gaugeMeasurement.value);
					statement.setTimestamp(3, new Timestamp(gaugeMeasurement.timestamp));
					statement.execute();
					
					throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
				} finally {
					statement.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		}
	}
	
	@DELETE
	@Path("{date}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RolesAllowed(value = {"ADMIN"})
	public Response delete(@javax.ws.rs.core.Context SecurityContext context, @PathParam(value = "date") String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Context initContext;
		try {
			java.util.Date date = dateFormat.parse(dateString);
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				conn.setAutoCommit(false);
				PreparedStatement deletestatement = conn.prepareStatement("DELETE FROM gaugemeasurement WHERE time >= ? AND time < ? ");
				try {
					deletestatement.setTimestamp(1, new Timestamp(date.getTime()));
					deletestatement.setTimestamp(2, new Timestamp(date.getTime() + 1000));
					deletestatement.execute();
//			deletestatement.execute("DELETE FROM gaugemeasurement WHERE time >= TIMESTAMP '" + date.getTime() + "' AND time < TIMESTAMP '" + date.getTime() + 1000 + "'"); //$NON-NLS-1$));
					conn.commit();
					return Response.ok().build();
				} finally {
					deletestatement.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DatabaseException("Failed to parse date"); //$NON-NLS-1$
		}
	}

}
