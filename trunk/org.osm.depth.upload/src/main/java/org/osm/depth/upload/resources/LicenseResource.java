package org.osm.depth.upload.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
import org.osm.depth.upload.messages.License;

@Path("/license")
public class LicenseResource {

	@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<License> getAllLicenses(@javax.ws.rs.core.Context SecurityContext context) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			ResultSet executeQuery;
			if(context.isUserInRole("ADMIN")) { //$NON-NLS-1$
				executeQuery = statement.executeQuery("SELECT * FROM license l LEFT OUTER JOIN user_profiles u ON u.user_name = l.user_name"); //$NON-NLS-1$
			} else {
				executeQuery = statement.executeQuery("SELECT * FROM license l LEFT OUTER JOIN vesselconfiguration u ON u.user_name = l.user_name WHERE l.user_name='" + username + "' OR l.public = true ORDER BY shortname,name"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			List<License> list = new ArrayList<License>();
			while(executeQuery.next()) {
				License license = new License();
				license.id = executeQuery.getLong("id");
				license.name = executeQuery.getString("name");
				license.shortName = executeQuery.getString("shortname");
				license.text = executeQuery.getString("text");
				license.publicLicense = executeQuery.getBoolean("public");
				if(context.isUserInRole("ADMIN")) { //$NON-NLS-1$
					license.User = executeQuery.getString("user_name");
				} else { 
					if(license.User.equals(context.getUserPrincipal().getName())) {
						license.User = executeQuery.getString("user_name");
					} else {
						license.User = "Other";
					}
				}
				
//				UriBuilder ub = uriInfo.getBaseUriBuilder();
//				track.delete = ub.path("/track/" + track.id).build().toString(); //$NON-NLS-1$
				list.add(license);
			}
//			GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(list) {/* */};
//			Link self = Link.fromMethod(TrackResource.class,"delete").build(); //$NON-NLS-1$
//			Response response = Response.ok().entity(entity).links(self).build();
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
	public License newLicense(@javax.ws.rs.core.Context SecurityContext context, License license) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();

			Statement createIDStatement = conn.createStatement();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO license (id, user_name, name, shortname, text, public) VALUES (?,?,?,?,?,?)"); //$NON-NLS-1$
			Statement userIDQueryStatement = conn.createStatement();
			
			ResultSet executeQuery = createIDStatement.executeQuery("SELECT nextval('license_id_seq')"); //$NON-NLS-1$
			if(executeQuery.next()) {
				Long id = executeQuery.getLong(1);
				statement.setLong(1, id);
				statement.setString(2, username);
				statement.setString(3, license.name);
				statement.setString(4, license.shortName);
				statement.setString(5, license.text);
				statement.setBoolean(6, license.publicLicense);
				statement.execute();
				return license;
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
	public Response delete(@javax.ws.rs.core.Context SecurityContext context, @PathParam(value = "id") String id) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			long licenseId = Long.parseLong(id);
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();

			conn.setAutoCommit(false);
			Statement licenseUsedinTracks = conn.createStatement();
			Statement deletestatement = conn.createStatement();
			
			ResultSet usedInTracksResultSet = licenseUsedinTracks.executeQuery("SELECT COUNT(track_id) FROM user_tracks WHERE license ='" + licenseId + "' ");
			if(usedInTracksResultSet.next() && usedInTracksResultSet.getLong(1) > 0) {
				throw new ResourceInUseException("License is still being used for recorded tracks");
			}
			
			if(context.isUserInRole("ADMIN")) { //$NON-NLS-1$
				deletestatement.execute(MessageFormat
					.format("DELETE FROM license WHERE id = {0}", //$NON-NLS-1$
							licenseId));
			} else {
				deletestatement.execute(MessageFormat
						.format("DELETE FROM license WHERE id = {0} AND user_name = ''{1}''", //$NON-NLS-1$
								licenseId, username));
			}
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
