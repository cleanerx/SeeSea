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

import org.apache.commons.lang.StringEscapeUtils;
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
			try {
				ResultSet executeQuery;
				if(context.isUserInRole("ADMIN")) { //$NON-NLS-1$
					Statement statement = conn.createStatement();
					try {
						executeQuery = statement.executeQuery("SELECT * FROM license l LEFT OUTER JOIN user_profiles u ON u.user_name = l.user_name ORDER BY l.shortname,l.name"); //$NON-NLS-1$
						return getLicenses(context, executeQuery);
					} finally {
						statement.close();
					}
				} else {
					PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM license l LEFT OUTER JOIN vesselconfiguration u ON u.user_name = l.user_name WHERE l.user_name= ? OR l.public = true ORDER BY l.shortname,l.name"); //$NON-NLS-1$
					pStatement.setString(1, username);
					try {
						executeQuery = pStatement.executeQuery();
						return getLicenses(context, executeQuery);
					} finally {
						pStatement.close();
					}
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
	
	private List<License> getLicenses(SecurityContext context,
			ResultSet executeQuery) throws SQLException {
		List<License> list = new ArrayList<License>();
		while(executeQuery.next()) {
			License license = new License();
			license.id = executeQuery.getLong("id");
			license.name = StringEscapeUtils.escapeJavaScript(executeQuery.getString("name"));
			license.shortName = StringEscapeUtils.escapeJavaScript(executeQuery.getString("shortname"));
			license.text = StringEscapeUtils.escapeJavaScript(executeQuery.getString("text"));
			license.publicLicense = executeQuery.getBoolean("public");
			if(context.isUserInRole("ADMIN")) { //$NON-NLS-1$
				license.user = executeQuery.getString("user_name");
			} else { 
				if(context.getUserPrincipal().getName().equals(executeQuery.getString("user_name"))) {
					license.user = executeQuery.getString("user_name");
				} else {
					license.user = "Other";
				}
			}
			
//					UriBuilder ub = uriInfo.getBaseUriBuilder();
//					track.delete = ub.path("/track/" + track.id).build().toString(); //$NON-NLS-1$
			list.add(license);
		}
//				GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(list) {/* */};
//				Link self = Link.fromMethod(TrackResource.class,"delete").build(); //$NON-NLS-1$
//				Response response = Response.ok().entity(entity).links(self).build();
		return list;
	}
	
	
	/**
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return  the license names used for a given bounding box area
	 */
	@GET
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({ MediaType.TEXT_PLAIN})
	public String getLicenses(@QueryParam("lat1") String lat1, @QueryParam("lon1") String lon1, @QueryParam("lat2") String lat2, @QueryParam("lon2") String lon2) {
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource dsDepth = (DataSource)initContext.lookup("java:/comp/env/jdbc/depth"); //$NON-NLS-1$
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection depthConn = dsDepth.getConnection();
			try {
				Connection conn = ds.getConnection();
				try {
					ResultSet executeQuery;
					PreparedStatement statement = depthConn.prepareStatement("SELECT DISTINCT datasetid FROM trackpoints_raw_8 WHERE trackpoints_raw_8.the_geom && ST_MakeEnvelope(?, ?, ?, ?, 4326)"); //$NON-NLS-1$
					try {
						statement.setDouble(1, Double.parseDouble(lon1));
						statement.setDouble(2, Double.parseDouble(lat1));
						statement.setDouble(3, Double.parseDouble(lon2));
						statement.setDouble(4, Double.parseDouble(lat2));
						executeQuery = statement.executeQuery();
						StringBuffer buffer = new StringBuffer();
						while(executeQuery.next()) {
							buffer.append(executeQuery.getString(1));
							buffer.append(',');
						}
						if(buffer.length() > 0) {
							buffer.deleteCharAt(buffer.length() - 1);
						}
						// we have trackpoints there
						if(buffer.length() > 0) {
							PreparedStatement licenseStatement = conn.prepareStatement("SELECT shortname FROM license INNER JOIN (SELECT DISTINCT license FROM user_tracks WHERE track_id IN (" + buffer.toString() + ") ) l2 ON license.id = l2.license"); //$NON-NLS-1$
							try {
								ResultSet licensesResult = licenseStatement.executeQuery();
								buffer = new StringBuffer();
								while(licensesResult.next()) {
									buffer.append(licensesResult.getString(1));
									buffer.append(',');
								}
								if(buffer.length() > 0) {
									buffer.deleteCharAt(buffer.length() - 1);
								}
								return buffer.toString();
							} finally {
								licenseStatement.close();
							}
						}
						// return empty string for no licenses
						return ""; //$NON-NLS-1$
					} finally {
						statement.close();
					}
					
				} finally {
					conn.close();
				}
			} finally {
				depthConn.close();
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
	public String newLicense(@javax.ws.rs.core.Context SecurityContext context, License license) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				Statement createIDStatement = conn.createStatement();
				try {
					PreparedStatement statement = conn.prepareStatement("INSERT INTO license (id, user_name, name, shortname, text, public) VALUES (?,?,?,?,?,?)"); //$NON-NLS-1$
//			Statement userIDQueryStatement = conn.createStatement();
					try {
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
							return id.toString();
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
	public Response delete(@javax.ws.rs.core.Context SecurityContext context, @PathParam(value = "id") String id) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			long licenseId = Long.parseLong(id);
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				conn.setAutoCommit(false);
				PreparedStatement licenseUsedinTracks = conn.prepareStatement("SELECT COUNT(track_id) FROM user_tracks WHERE license = ? "); //$NON-NLS-1$
				try {
					licenseUsedinTracks.setLong(1, licenseId);
					Statement deletestatement = conn.createStatement();
					try {
						ResultSet usedInTracksResultSet = licenseUsedinTracks.executeQuery();
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
						return Response.ok().build();
					} finally {
						deletestatement.close();
					}
				} finally {
					licenseUsedinTracks.close();
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

}
