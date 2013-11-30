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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import jj.play.ns.nl.captcha.Captcha.Builder;
import jj.play.ns.nl.captcha.gimpy.BlockGimpyRenderer;
import jj.play.ns.nl.captcha.text.producer.DefaultTextProducer;
import jj.play.ns.nl.captcha.text.producer.TextProducer;


//import org.apache.catalina.util.Base64;
import org.glassfish.jersey.internal.util.Base64;
import org.osm.depth.upload.CaptchaManagement;
import org.osm.depth.upload.exceptions.ConflictException;
import org.osm.depth.upload.exceptions.DatabaseException;
import org.osm.depth.upload.exceptions.ValidationException;
import org.osm.depth.upload.messages.Captcha;
import org.osm.depth.upload.messages.User;

@Path("/users")
public class UserResource {

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("nls")
	@POST
	@Path("captcha")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCatpcha() {
		Builder builder = new Builder(160, 40);
		jj.play.ns.nl.captcha.Captcha captcha = builder.addText(new DefaultTextProducer(6)).gimp(new BlockGimpyRenderer()).build();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
			ImageIO.write(captcha.getImage(), "png", byteArrayOutputStream); //$NON-NLS-1$
			byte[] imageData = byteArrayOutputStream.toByteArray();
			
			CaptchaManagement.getInstance().registerCaptcha(captcha.getAnswer());
			Captcha captcha2 = new Captcha();
//			captcha2.id = captchaid;
			captcha2.imageBase64 = org.postgresql.util.Base64.encodeBytes(imageData);
			return Response.
					ok(captcha2).
					header("Cache-Control", "no-cache, no-store, must-revalidate").
					header("Pragma", "no-cache").
					header("Expires", "0").
					build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
//	@PUT
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void changePassword(@javax.ws.rs.core.Context SecurityContext context, @QueryParam("oldPassword") String oldPassword, @QueryParam("newPassword") String newPassword) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				Statement selectstatement = conn.createStatement();
				try {
					selectstatement.execute(MessageFormat
							.format("UPDATE user_profiles SET password = '{2}' WHERE password = {0} AND user_name = ''{1}''", //$NON-NLS-1$
									oldPassword, username, newPassword));
				} finally {
					selectstatement.close();
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
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createUser(
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("captcha") String captcha
			)
		{
		if(CaptchaManagement.getInstance().unregisterCaptcha(captcha)) {
			Context initContext;
			try {
				initContext = new InitialContext();
				DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
				Connection conn = ds.getConnection();
				try {
					conn.setAutoCommit(false);
					Statement statement = conn.createStatement();
					try {
						PreparedStatement insertUserStatement = conn.prepareStatement("INSERT INTO user_profiles (user_name, password) VALUES (?,?)");
						try {
							PreparedStatement insertUserRoleStatement = conn.prepareStatement("INSERT INTO userroles (user_name, role) VALUES (?, 'USER')");
							try {
								PreparedStatement usernameExists = conn.prepareStatement("SELECT user_name FROM user_profiles WHERE user_name = ?"); //$NON-NLS-1$
								try {
									usernameExists.setString(1, username);
									if(usernameExists.executeQuery().next()) {
										throw new ConflictException("Username exists", Status.CONFLICT);
									}
									insertUserStatement.setString(1, username);
									insertUserStatement.setString(2, password.toLowerCase());
									insertUserRoleStatement.setString(1, username);
									insertUserStatement.execute();
									insertUserRoleStatement.execute();
									conn.commit();
									return Response.status(204).build();
								} finally {
									usernameExists.close();
								}
							} finally {
								insertUserRoleStatement.close();
							}
						} finally {
							insertUserStatement.close();
						}
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
		throw new ValidationException(Status.BAD_REQUEST);
	}
	
	@GET
    @Produces({ MediaType.APPLICATION_XML })
	@RolesAllowed("ADMIN")
	public List<User> getAllUsers() {
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres");
			Connection conn = ds.getConnection();
			try {
				Statement statement = conn.createStatement();
				try {
					ResultSet executeQuery = statement.executeQuery("SELECT * FROM user_profiles"); //$NON-NLS-1$
					try {
						List<User> list = new ArrayList<User>(2);
						while(executeQuery.next()) {
							User user = new User();
							user.user_name = executeQuery.getString("user_name");
							user.attemps = executeQuery.getInt("attempts");
							user.lastAttempt = executeQuery.getDate("last_attempt");
							list.add(user);
						}
						return list;
					} finally {
						executeQuery.close();
					}
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
	
//	@PUT
//	@Path("{id}/reset")
//	@RolesAllowed("ADMIN")
//	public void resetAccount(@PathParam("id") Long id) {
//		Context initContext;
//		try {
//			initContext = new InitialContext();
//			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
//			Connection conn = ds.getConnection();
//
//			Statement selectstatement = conn.createStatement();
//			selectstatement.execute("UPDATE user_profiles SET attempts = 0");
//			selectstatement.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


}
