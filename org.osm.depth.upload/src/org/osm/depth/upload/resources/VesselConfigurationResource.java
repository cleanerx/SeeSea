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
import java.text.MessageFormat;
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
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public VesselConfiguration createVesselConfig(@javax.ws.rs.core.Context SecurityContext context, VesselConfiguration vesselConfiguration) {
		String username = context.getUserPrincipal().getName();
		if(!context.isUserInRole("ADMIN") || vesselConfiguration.username == null) {
			vesselConfiguration.username = username;
		}
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();

			Statement createIDStatement = conn.createStatement();
			ResultSet executeQuery = createIDStatement.executeQuery("SELECT nextval('vesselconfiguration_id_seq')"); //$NON-NLS-1$
			if(executeQuery.next()) {
				vesselConfiguration.id = executeQuery.getLong(1);
				//@formatter:off
				PreparedStatement selectstatement = conn.prepareStatement(
						"INSERT INTO vesselconfiguration " +
								"(name, " +
								"description, " +
								"mmsi, " +
								"manufacturer, " +
								"model, " +
								"loa, " +
								"berth, " +
								"draft, " +
								"height, " +
								"displacement, " +
								"maximumspeed, " +
								"user_name," +
								"id)" +
						"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				selectstatement.setString(1, vesselConfiguration.name); 
				selectstatement.setString(2,  vesselConfiguration.description); 
				selectstatement.setString(3,  vesselConfiguration.mmsi );
				selectstatement.setString(4,  vesselConfiguration.manufacturer); 
				selectstatement.setString(5,  vesselConfiguration.model );
				selectstatement.setDouble(6,  vesselConfiguration.loa );
				selectstatement.setDouble(7,  vesselConfiguration.berth );
				selectstatement.setDouble(8,  vesselConfiguration.draft );
				selectstatement.setDouble(9,  vesselConfiguration.height );
				selectstatement.setDouble(10,  vesselConfiguration.displacement );
				selectstatement.setDouble(11,  vesselConfiguration.maximumspeed );
				selectstatement.setString(12,  vesselConfiguration.username );
				selectstatement.setLong(13,  vesselConfiguration.id);
				selectstatement.execute();
				selectstatement.close();
				return vesselConfiguration;
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
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres");
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			ResultSet executeQuery;
			if(context.isUserInRole("ADMIN")) {
				executeQuery = statement.executeQuery("SELECT * FROM vesselconfiguration"); //$NON-NLS-1$
			} else {
				executeQuery = statement.executeQuery("SELECT * FROM vesselconfiguration WHERE user_name='" + username + "'"); //$NON-NLS-1$
			}
			
			List<VesselConfiguration> list = new ArrayList<>(2);
			while(executeQuery.next()) {
				VesselConfiguration vc = new VesselConfiguration();
				vc.id = executeQuery.getInt("id");
				vc.name = executeQuery.getString("name");
//						, executeQuery.getInt("upload_state"), executeQuery.getString("compression")); //$NON-NLS-1$
//				UriBuilder ub = uriInfo.getBaseUriBuilder();
//				track.delete = ub.path("/vesselconfiguration/" + track.track_id).build().toString();
				list.add(vc);
			}
			GenericEntity<List<VesselConfiguration>> entity = new GenericEntity<List<VesselConfiguration>>(list) {};
//			Link self = Link.fromMethod(VesselConfigurationResource.class,"deleteVesselConfig").build();
			
			Response response = Response.ok().entity(entity).build();
			return response;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		}
	}

	public void changeVesselConfig() {

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
			int vesselId = Integer.parseInt(id);
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			Statement vesselconfigInUse = conn.createStatement();
			ResultSet executeQuery = vesselconfigInUse.executeQuery("SELECT track_id FROM user_tracks WHERE vesselconfig = '{0}'"); //$NON-NLS-1$
			if(executeQuery.next()) {
				// do not delete used vesselconfigs
				return Response.serverError().build();
			}
			Statement selectstatement = conn.createStatement();
			selectstatement
					.execute(MessageFormat
							.format("DELETE FROM vesselconfiguration WHERE id = {0} AND user_name = ''{1}''", //$NON-NLS-1$
									vesselId, username));

			selectstatement.close();
			return Response.ok().build();
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
