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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.osm.depth.upload.exceptions.DatabaseException;
import org.osm.depth.upload.messages.Track;

@Path("/track")
public class TrackResource {

	@javax.ws.rs.core.Context
	UriInfo uriInfo;

	@javax.ws.rs.core.Context
	ServletConfig config;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Track> getAllTracks(
			@javax.ws.rs.core.Context SecurityContext context) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection connection = ds.getConnection();

			try {
				ResultSet executeQuery = null;
				if (context.isUserInRole("ADMIN")) { //$NON-NLS-1$
					Statement statement = connection.createStatement();
					try {
						executeQuery = statement
								.executeQuery("SELECT * FROM user_tracks u LEFT OUTER JOIN vesselconfiguration v ON u.vesselconfigid = v.id ORDER BY track_id"); //$NON-NLS-1$
						return getTracksFromDatabase(executeQuery);
					} finally {
						statement.close();
					}
				} else {
					PreparedStatement pStatement = connection
							.prepareStatement("SELECT * FROM user_tracks u LEFT OUTER JOIN vesselconfiguration v ON u.vesselconfigid = v.id WHERE u.user_name= ? ORDER BY track_id"); //$NON-NLS-1$
					try {
						pStatement.setString(1, username);
						executeQuery = pStatement.executeQuery();
						return getTracksFromDatabase(executeQuery);
					} finally {
						pStatement.close();
					}
				}

			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable");
		}
	}

	private List<Track> getTracksFromDatabase(ResultSet executeQuery)
			throws SQLException {
		try {
			// Do stuff with the result set.
			List<Track> list = new ArrayList<Track>(2);
			while (executeQuery.next()) {
				Track track = new Track();
				track.id = executeQuery.getLong("track_id"); //$NON-NLS-1$
				track.fileName = executeQuery.getString("file_ref"); //$NON-NLS-1$
				track.upload_state = executeQuery.getInt("upload_state"); //$NON-NLS-1$
				track.fileType = executeQuery.getString("fileType"); //$NON-NLS-1$
				track.compression = executeQuery.getString("compression"); //$NON-NLS-1$
				track.containertrack = executeQuery.getLong("containertrack"); //$NON-NLS-1$
				Timestamp timestamp = executeQuery.getTimestamp("uploadDate"); //$NON-NLS-1$
				if (timestamp != null) {
					track.uploadDate = timestamp.getTime();
				}
				track.license = executeQuery.getLong("license"); //$NON-NLS-1$
				track.vesselconfigid = executeQuery.getLong("vesselconfigid"); //$NON-NLS-1$
				UriBuilder ub = uriInfo.getBaseUriBuilder();
				//						track.delete = ub.path("/track/" + track.id).build().toString(); //$NON-NLS-1$
				list.add(track);
			}
			// GenericEntity<List<Track>> entity = new
			// GenericEntity<List<Track>>(list) {/* */};
			//					Link self = Link.fromMethod(TrackResource.class,"delete").build(); //$NON-NLS-1$
			// Response response =
			// Response.ok().entity(entity).links(self).build();
			return list;
		} finally {
			if (executeQuery != null) {
				executeQuery.close();
			}
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Track newTrackWithNullId(
			@javax.ws.rs.core.Context SecurityContext context, Track track) {
		return newTrack(context, track);
	}

	/**
	 * creating a step is twofold: Create its id and the update the file
	 * contents later on through a put request. This way we can show progress of
	 * the uploaded file.
	 * 
	 * @param context
	 * @return
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Track reprocessTrack(@javax.ws.rs.core.Context SecurityContext context,
			Track track) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			DataSource depthDS = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/depth"); //$NON-NLS-1$
			Connection depthConn = depthDS.getConnection();
			PreparedStatement deleteStatement;
			try {
				deleteStatement = conn
						.prepareStatement("UPDATE user_tracks SET upload_state = 1 WHERE track_id = ? AND user_name = ?"); //$NON-NLS-1$
				deleteStatement.setLong(1, track.id);
				deleteStatement.setString(2, username);
				deleteStatement.execute();
				try {
					deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_8 WHERE datasetid = ?"); //$NON-NLS-1$
					deleteStatement.setLong(1, track.id);
					deleteStatement.execute();
					deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_10 WHERE datasetid = ?"); //$NON-NLS-1$
					deleteStatement.setLong(1, track.id);
					deleteStatement.execute();
					deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_12 WHERE datasetid = ?"); //$NON-NLS-1$
					deleteStatement.setLong(1, track.id);
					deleteStatement.execute();
					deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_16 WHERE datasetid = ?"); //$NON-NLS-1$
					deleteStatement.setLong(1, track.id);
					deleteStatement.execute();
					deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_filter_8 WHERE datasetid = ?"); //$NON-NLS-1$
					deleteStatement.setLong(1, track.id);
					deleteStatement.execute();
					deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_filter_10 WHERE datasetid = ?"); //$NON-NLS-1$
					deleteStatement.setLong(1, track.id);
					deleteStatement.execute();
					deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_filter_12 WHERE datasetid = ?"); //$NON-NLS-1$
					deleteStatement.setLong(1, track.id);
					deleteStatement.execute();
					deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_filter_16 WHERE datasetid = ?"); //$NON-NLS-1$
					deleteStatement.setLong(1, track.id);
					deleteStatement.execute();
				} finally {
					deleteStatement.close();
				}
			} finally {
				conn.close();
				depthConn.close();
			}

		} catch (SQLException e) {
			throw new DatabaseException("Internal SQL Error"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		}
		throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
	}

	/**
	 * creating a step is twofold: Create its id and the update the file
	 * contents later on through a put request. This way we can show progress of
	 * the uploaded file.
	 * 
	 * @param context
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Track newTrack(@javax.ws.rs.core.Context SecurityContext context,
			Track track) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				PreparedStatement insertTrackStatement = conn
						.prepareStatement("INSERT INTO user_tracks (track_id, user_name, uploaddate, vesselconfigid , license, file_ref ) VALUES (?,?,?,?,?,?)"); //$NON-NLS-1$
				PreparedStatement userOwnsVesselconfiguration = conn
						.prepareStatement("SELECT id FROM vesselconfiguration WHERE user_name = ? AND id = ?"); //$NON-NLS-1$
				PreparedStatement userMayUseLicense = conn
						.prepareStatement("SELECT id FROM license WHERE (user_name = ? OR public = 'true') AND id = ?"); //$NON-NLS-1$
				Statement createIDStatement = conn.createStatement();
				try {
					userOwnsVesselconfiguration.setString(1, username);
					userOwnsVesselconfiguration
							.setLong(2, track.vesselconfigid);
					userMayUseLicense.setString(1, username);
					userMayUseLicense.setLong(2, track.license);
					if (userOwnsVesselconfiguration.executeQuery().next()
							&& userMayUseLicense.executeQuery().next()) {
						try {
							ResultSet executeQuery = createIDStatement
									.executeQuery("SELECT nextval('user_tracks_track_id_seq')"); //$NON-NLS-1$
							try {
								if (executeQuery.next()) {
									Long id = executeQuery.getLong(1);
									insertTrackStatement.setLong(1, id);
									insertTrackStatement.setString(2, username);
									insertTrackStatement.setTimestamp(3,
											new java.sql.Timestamp(Calendar
													.getInstance().getTime()
													.getTime()));
									insertTrackStatement.setLong(4,
											track.vesselconfigid);
									insertTrackStatement.setLong(5,
											track.license);
									insertTrackStatement.setString(6,
											track.fileName);
									insertTrackStatement.execute();
									track.id = id;
									return track;
								} else {
									// failed to create id
								}
								throw new DatabaseException(
										"Database unavailable"); //$NON-NLS-1$
							} finally {
								executeQuery.close();
							}
						} finally {
							insertTrackStatement.close();
							createIDStatement.close();
						}
					}
				} finally {
					userOwnsVesselconfiguration.close();
					userMayUseLicense.close();
				}
			} finally {
				conn.close();
			}

		} catch (SQLException e) {
			throw new DatabaseException("Internal SQL Error"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		}
		throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
	}

	/**
	 * this method must be called upon successful completed upload
	 * 
	 * @param context
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @param trackID
	 * @return
	 */
	@PUT
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadDone(@javax.ws.rs.core.Context SecurityContext context,
			@FormDataParam("track") InputStream uploadedInputStream,
			@FormDataParam("track") FormDataContentDisposition fileDetail,
			@FormDataParam("id") String trackID) {

		Long bytesRead = 0L;
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			File file = getFile(Long.parseLong(trackID));
			FileOutputStream outputStream = new FileOutputStream(file);
			try {
				byte[] buffer = new byte[16384];
				int len = uploadedInputStream.read(buffer);
				while (len != -1) {
					outputStream.write(buffer, 0, len);
					len = uploadedInputStream.read(buffer);
					bytesRead += len;
				}

				initContext = new InitialContext();
				DataSource ds = (DataSource) initContext
						.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
				Connection conn = ds.getConnection();
				try {
					conn.setAutoCommit(false);
					PreparedStatement selectUploadStateStatement = conn
							.prepareStatement("SELECT track_id FROM user_tracks WHERE track_id = ? AND user_name = ? AND upload_state = 0"); //$NON-NLS-1$
					PreparedStatement updateTrackStatement = conn
							.prepareStatement("UPDATE user_tracks SET upload_state = 1 WHERE track_id = ?"); //$NON-NLS-1$
					try {
						selectUploadStateStatement.setLong(1,
								Long.parseLong(trackID));
						selectUploadStateStatement.setString(2, username);
						updateTrackStatement
								.setLong(1, Long.parseLong(trackID));
						ResultSet resultSet = selectUploadStateStatement
								.executeQuery();
						try {
							while (resultSet.next()
									&& resultSet.getLong("track_id") != 0) { //$NON-NLS-1$
								updateTrackStatement.execute();
							}
							conn.commit();
						} finally {
							resultSet.close();
						}
					} finally {
						selectUploadStateStatement.close();
						updateTrackStatement.close();
					}
				} finally {
					conn.close();
				}
			} finally {
				outputStream.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DatabaseException("Uploaded file not processable"); //$NON-NLS-1$
		} catch (IOException e) {
			e.printStackTrace();
			throw new DatabaseException("Uploaded file not processable"); //$NON-NLS-1$
		}
		return bytesRead.toString();
	}

	/**
	 * deletes a track by id
	 * 
	 * @param context
	 * @param id
	 */
	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Track delete(@javax.ws.rs.core.Context SecurityContext context,
			@PathParam(value = "id") String id) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			long trackId = Long.parseLong(id);
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			DataSource depthDS = (DataSource) initContext
					.lookup("java:/comp/env/jdbc/depth"); //$NON-NLS-1$
			Connection depthConn = depthDS.getConnection();
			try {
				PreparedStatement deleteStatement = null;
				try {
					if (context.isUserInRole("ADMIN")) { //$NON-NLS-1$
						deleteStatement = conn
								.prepareStatement("DELETE FROM user_tracks WHERE track_id = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();
					} else {
						deleteStatement = conn
								.prepareStatement("DELETE FROM user_tracks WHERE track_id = ? AND user_name = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.setString(2, username);
						deleteStatement.execute();
					}
						deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_8 WHERE datasetid = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();
						deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_10 WHERE datasetid = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();
						deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_12 WHERE datasetid = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();
						deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_16 WHERE datasetid = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();
						deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_filter_8 WHERE datasetid = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();
						deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_filter_10 WHERE datasetid = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();
						deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_filter_12 WHERE datasetid = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();
						deleteStatement = depthConn.prepareStatement("DELETE FROM trackpoints_raw_filter_16 WHERE datasetid = ?"); //$NON-NLS-1$
						deleteStatement.setLong(1, trackId);
						deleteStatement.execute();

				} finally {
					if (deleteStatement != null) {
						deleteStatement.close();
					}
				}
			} finally {
				conn.close();
				depthConn.close();
			}
			File file = getFile(trackId);
			if (!file.delete()) {
				System.out.println("Failed to deltee file " + file);
				throw new DatabaseException(
						"Failed to delete file on file system");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error"); //$NON-NLS-1$
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable"); //$NON-NLS-1$
		} catch (IOException e) {
			e.printStackTrace();
			throw new DatabaseException("Delete file not possible"); //$NON-NLS-1$
		}
		return new Track();
	}

	/**
	 * 
	 * @param trackId
	 * @return the file to create or null if the directory could not be created
	 * @throws IOException
	 */
	private File getFile(Long trackId) throws IOException {
		Long dirNumber = trackId / 100L * 100L;
		String fileDirectoryConfig = ".";
		// String fileDirectoryConfig = config
		//				.getInitParameter("org.osm.upload.dataDirectory"); //$NON-NLS-1$
		File fileDirectory = new File(fileDirectoryConfig + File.separator
				+ dirNumber.toString());
		String trackIDString = trackId.toString();
		if (!fileDirectory.exists()) {
			boolean mkdirs = fileDirectory.mkdirs();
			if (!mkdirs) {
				throw new IOException(
						"Failed to create directory" + fileDirectory.getAbsolutePath()); //$NON-NLS-1$
			}
		}
		if (fileDirectory.exists()) {
			return new File(fileDirectory, trackIDString + ".dat");
		}
		throw new IOException(
				"Failed to create directory" + fileDirectory.getAbsolutePath()); //$NON-NLS-1$
	}

}
