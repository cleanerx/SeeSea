package org.osm.depth.upload.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.osm.depth.upload.messages.Gauge;
import org.osm.depth.upload.messages.GaugeType;
import org.postgis.PGgeometry;
import org.postgis.Point;

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
	public List<Gauge> getAllGauges() {
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				Statement statement = conn.createStatement();
				try {
					ResultSet executeQuery;
					executeQuery = statement.executeQuery("SELECT id, name, gaugetype, ST_AsText(geom) as geom FROM gauge g"); //$NON-NLS-1$
					List<Gauge> list = new ArrayList<Gauge>();
					while(executeQuery.next()) {
						Gauge gauge = new Gauge();
						gauge.id = executeQuery.getLong("id"); //$NON-NLS-1$
						gauge.name = executeQuery.getString("name"); //$NON-NLS-1$
						PGgeometry geom = (PGgeometry)executeQuery.getObject("geom");
						gauge.latitude = ((Point)geom.getGeometry()).y; //$NON-NLS-1$
						gauge.longitude = ((Point)geom.getGeometry()).x; //$NON-NLS-1$
						try {
							gauge.gaugeType = GaugeType.valueOf(executeQuery.getString("gaugetype")); //$NON-NLS-1$
						} catch (IllegalArgumentException e) {
							gauge.gaugeType = GaugeType.UNDEFINED; // no such gauge type
						}
						list.add(gauge);
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
	@RolesAllowed(value = {"USER", "ADMIN"})
	public Gauge newGauge(@javax.ws.rs.core.Context SecurityContext context, Gauge gauge) {
		Context initContext;
		try {
			
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				Statement createIDStatement = conn.createStatement();
				try {
					PreparedStatement statement = conn.prepareStatement("INSERT INTO gauge (id, name, gaugetype, geom) VALUES (?,?,?, ST_SetSRID(ST_MakePoint(?, ?), 4326))"); //$NON-NLS-1$
					try {
						ResultSet executeQuery = createIDStatement.executeQuery("SELECT nextval('gauge_id_seq')"); //$NON-NLS-1$
						if(executeQuery.next()) {
							Long id = executeQuery.getLong(1);
							statement.setLong(1, id);
							statement.setString(2, gauge.name);
							statement.setString(3, gauge.gaugeType.toString());
							statement.setDouble(5, gauge.latitude);
							statement.setDouble(4, gauge.longitude);
							statement.execute();
							return gauge;
						} else {
							// failed to create id
						}
						throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
					} finally {
						statement.close();
					}
				} finally {
					createIDStatement.close();
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
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@RolesAllowed(value = {"ADMIN"})
	public Response delete(@PathParam(value = "id") String id) {
		Context initContext;
		try {
			long gaugeId = Long.parseLong(id);
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				conn.setAutoCommit(false);
//			Statement gaugeUsedinTracks = conn.createStatement();
				PreparedStatement deletestatement = conn.prepareStatement("DELETE FROM gauge WHERE id = ?"); //$NON-NLS-1$
				try {
					deletestatement.setLong(1, gaugeId);
					deletestatement.execute();
					conn.commit();
				} finally {
					deletestatement.close();
				}
//			ResultSet usedInTracksResultSet = gaugeUsedinTracks.executeQuery("SELECT COUNT(id) FROM gaugetracks WHERE gaugeid ='" + gaugeId + "' ");
//			if(usedInTracksResultSet.next() && usedInTracksResultSet.getLong(1) > 0) {
//				throw new ResourceInUseException("License is still being used for recorded tracks");
//			}
				
				return Response.ok().build();
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

}
