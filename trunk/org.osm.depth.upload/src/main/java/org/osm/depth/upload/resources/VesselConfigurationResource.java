/**
 * 
 Copyright (c) 2010-2013, Jens K�bler All rights reserved.
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
package org.osm.depth.upload.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.osm.depth.upload.exceptions.DatabaseException;
import org.osm.depth.upload.messages.VesselConfiguration;

@Path("/vesselconfig")
public class VesselConfigurationResource {

	@javax.ws.rs.core.Context
	UriInfo uriInfo;

	@POST
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public VesselConfiguration createVesselConfig(
			@javax.ws.rs.core.Context SecurityContext context,
			VesselConfiguration vesselConfiguration) {
		String username = context.getUserPrincipal().getName();
		if (!context.isUserInRole("ADMIN")
				|| vesselConfiguration.username == null) {
			vesselConfiguration.username = username;
		}
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				Statement createIDStatement = conn.createStatement();
				PreparedStatement selectstatement = conn
						.prepareStatement("INSERT INTO vesselconfiguration "
								+ "(name, " + "description, " + "depthm, "
								+ "depthd, " + "esinfront, " + "esrightof, "
								+ "esdisy, " + "esdisx, " + "slidingsp, "
								+ "yachtmodel, "
								+
								// "maximumspeed, " +
								"user_name," + "id)"
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				try {
					ResultSet executeQuery = createIDStatement
							.executeQuery("SELECT nextval('vesselconfiguration_id_seq')"); //$NON-NLS-1$
					try {
						if (executeQuery.next()) {
							vesselConfiguration.id = executeQuery.getLong(1);
							// @formatter:off
							selectstatement.setString(1,
									vesselConfiguration.name);
							selectstatement.setString(2,
									vesselConfiguration.description);
							selectstatement.setDouble(3,
									vesselConfiguration.depthm);
							selectstatement.setDouble(4,
									vesselConfiguration.depthd);
							selectstatement.setString(5,
									vesselConfiguration.esinfront);
							selectstatement.setString(6,
									vesselConfiguration.esrightof);
							selectstatement.setDouble(7,
									vesselConfiguration.esdisy);
							selectstatement.setDouble(8,
									vesselConfiguration.esdisx);
							selectstatement.setDouble(9,
									vesselConfiguration.slidingsp);
							selectstatement.setString(10,
									vesselConfiguration.yachtmodel);
							// selectstatement.setDouble(11,
							// vesselConfiguration.maximumspeed );
							selectstatement.setString(11,
									vesselConfiguration.username);
							selectstatement.setLong(12, vesselConfiguration.id);
							selectstatement.execute();
							selectstatement.close();
							return vesselConfiguration;
						}
					} finally {
						executeQuery.close();
					}
				} finally {
					selectstatement.close();
					createIDStatement.close();
				}
			} finally {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		}
		return null;

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAll(@javax.ws.rs.core.Context SecurityContext context) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				ResultSet executeQuery;
				if (context.isUserInRole("ADMIN")) { //$NON-NLS-1$
					Statement statement = conn.createStatement();
					try {
						executeQuery = statement
								.executeQuery("SELECT * FROM vesselconfiguration"); //$NON-NLS-1$
						return analyzeResult(executeQuery);
					} finally {
						statement.close();
					}
				} else {
					PreparedStatement pStatement = conn
							.prepareStatement("SELECT * FROM vesselconfiguration WHERE user_name= ? "); //$NON-NLS-1$
					try {
						pStatement.setString(1, username);
						executeQuery = pStatement.executeQuery();
						return analyzeResult(executeQuery);
					} finally {
						pStatement.close();
					}
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		}
	}

	private Response analyzeResult(ResultSet executeQuery) throws SQLException {
		try {
			List<VesselConfiguration> list = new ArrayList<VesselConfiguration>(
					2);
			while (executeQuery.next()) {
				VesselConfiguration vc = new VesselConfiguration();
				vc.id = executeQuery.getInt("id");
				vc.name = executeQuery.getString("name");
				list.add(vc);
			}
			GenericEntity<List<VesselConfiguration>> entity = new GenericEntity<List<VesselConfiguration>>(
					list) {
			};
			Response response = Response.ok().entity(entity).build();
			return response;
		} finally {
			executeQuery.close();
		}
	}

	/**
	 * Vessel configurations may only be deleted if they are no longer used by
	 * any track (processing)
	 */
	@DELETE
	@Path("{id}")
	public Response deleteVesselConfig(
			@javax.ws.rs.core.Context SecurityContext context,
			@PathParam(value = "id") String id) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			long vesselId = Long.parseLong(id);
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				PreparedStatement vesselconfigInUse = conn
						.prepareStatement("SELECT track_id FROM user_tracks WHERE vesselconfig = ?"); //$NON-NLS-1$
				PreparedStatement deleteStatement = conn
						.prepareStatement("DELETE FROM vesselconfiguration WHERE id = ? AND user_name = ?"); //$NON-NLS-1$
				try {
					vesselconfigInUse.setLong(1, vesselId);
					ResultSet executeQuery = vesselconfigInUse.executeQuery();
					try {
						if (executeQuery.next()) {
							// do not delete used vesselconfigs
							return Response.serverError().build();
						}
						deleteStatement.setLong(1, vesselId);
						deleteStatement.setString(2, username);
						deleteStatement.execute();
						return Response.ok().build();
					} finally {
						executeQuery.close();
					}
				} finally {
					vesselconfigInUse.close();
					deleteStatement.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		} catch (NumberFormatException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
