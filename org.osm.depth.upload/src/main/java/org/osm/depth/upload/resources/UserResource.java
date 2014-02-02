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
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import jj.play.ns.nl.captcha.Captcha.Builder;
import jj.play.ns.nl.captcha.gimpy.BlockGimpyRenderer;
import jj.play.ns.nl.captcha.text.producer.DefaultTextProducer;

import org.osm.depth.upload.CaptchaManagement;
import org.osm.depth.upload.exceptions.ConflictException;
import org.osm.depth.upload.exceptions.DatabaseException;
import org.osm.depth.upload.exceptions.ErrorCode;
import org.osm.depth.upload.exceptions.ValidationException;
import org.osm.depth.upload.messages.Captcha;
import org.osm.depth.upload.messages.User;

import com.wordnik.swagger.annotations.Api;

@Api(value = "/users", description="This resource is for creating, updating and deleting users")
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
	
//	@ApiOperation( value="changepass", description = "Changes the users password. The user must be signed in in order to do that.")
//	@ApiError(code=500, reason="No old password supplied, no new password supplied or user does no exist")
	@Path("changepass")
	@POST
	public Response changePassword(@javax.ws.rs.core.Context SecurityContext context, @QueryParam("oldPassword") String oldPassword, @QueryParam("newPassword") String newPassword) {
		String username = context.getUserPrincipal().getName();
		if(oldPassword == null) {
			return Response.serverError().header("Error", ErrorCode.NO_OLD_PASSWORD).build();
		}
		if(newPassword == null) {
			return Response.serverError().header("Error", ErrorCode.NO_NEW_PASSWORD).build();
		}
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				PreparedStatement selectstatement = conn.prepareStatement("UPDATE user_profiles SET password = ? WHERE password = ? AND user_name = ?");
				try {
					
					selectstatement.setString(1, newPassword);
					selectstatement.setString(2, oldPassword);
					selectstatement.setString(3, username);
					int executeUpdate = selectstatement.executeUpdate();
					if(executeUpdate == 1) {
						return Response.ok().build();
					} else {
						return Response.serverError().header("Error", ErrorCode.OLD_PASSWORD_MISMATCH).build();
					}
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
	
	@Path("reset")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response resetPassword(@QueryParam("user_name") String email) {
		InitialContext initCtx;
		try {
			initCtx = new InitialContext();
			DataSource ds = (DataSource)initCtx.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();
			try {
				PreparedStatement selectstatement = conn.prepareStatement("SELECT user_name FROM user_profiles WHERE user_name = ?");
				try {
					selectstatement.setString(1, email);
					selectstatement.execute();
					ResultSet resultSet = selectstatement.getResultSet();
					if(resultSet.next()) {
						SecureRandom random = new SecureRandom();
						String clearTextPassword = new BigInteger(130, random).toString(32);
						String newPassword = encryptPassword(clearTextPassword);
						PreparedStatement setNewPasswordStatement = conn.prepareStatement("UPDATE user_profiles SET password = ? WHERE user_name = ?");
						try {
							setNewPasswordStatement.setString(1, newPassword);
							setNewPasswordStatement.setString(2, email);
							int executeUpdate = setNewPasswordStatement.executeUpdate();
							if(executeUpdate == 1) {
								Context envCtx = (Context) initCtx.lookup("java:comp/env");
								Session session = (Session) envCtx.lookup("mail/Session");
								
								Message message = new MimeMessage(session);
								message.setFrom(new InternetAddress("support@openseamap.org"));
								InternetAddress to[] = new InternetAddress[1];
								to[0] = new InternetAddress(email);
								message.setRecipients(Message.RecipientType.TO, to);
								message.setSubject("OpenSeaMap Password Reset");
								message.setContent("You have requested a password reset.\nYour password has been reset to : " + clearTextPassword + "\n This is an generated email. Do NOT reply to this email.", "text/plain");
								Transport.send(message);
							} else {
								return Response.serverError().header("Error", ErrorCode.NO_SUCH_USER).build();
							}
							
						} finally {
							setNewPasswordStatement.close();
						}

					}
				} finally {
					selectstatement.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Internal SQL Error");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseException("Database unavailable");
		}
		return Response.ok().build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createUser(
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("forename") String forename,
			@FormParam("surname") String surname,
			@FormParam("organisation") String organisation,
			@FormParam("acceptedEmailContact") String acceptedEmailContact,
			@FormParam("country") String country,
			@FormParam("language") String language,
			@FormParam("phone") String phone,
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
						PreparedStatement insertUserStatement = conn.prepareStatement("INSERT INTO user_profiles (user_name, password, forename, surname, organisation, acceptedEmailContact, country, language, phone) VALUES (?,?,?,?,?,?,?,?,?)");
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
									insertUserStatement.setString(3, forename);
									insertUserStatement.setString(4, surname);
									insertUserStatement.setString(5, organisation);
									insertUserStatement.setBoolean(6, "on".equals(acceptedEmailContact) || "true".equals(acceptedEmailContact));
									insertUserStatement.setString(7, country);
									insertUserStatement.setString(8, language);
									insertUserStatement.setString(9, phone);
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
							user.forname = executeQuery.getString("forename");
							user.surname = executeQuery.getString("surname");
							user.country = executeQuery.getString("country");
							user.language = executeQuery.getString("language");
							user.organisation = executeQuery.getString("organisation");
							user.phone = executeQuery.getString("phone");
							user.acceptedEmailContact = executeQuery.getBoolean("acceptedEmailContact");
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
	
	@GET
	@Path("current")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@RolesAllowed({"ADMIN","USER"})
	public User getCurrentUser(@javax.ws.rs.core.Context SecurityContext context) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres");
			Connection conn = ds.getConnection();
			try {
				PreparedStatement statement = conn.prepareStatement("SELECT * FROM user_profiles WHERE user_name = ?");
				try {
					statement.setString(1, username);
					ResultSet executeQuery = statement.executeQuery();
					try {
						while(executeQuery.next()) {
							User user = new User();
							user.user_name = executeQuery.getString("user_name");
							user.forname = executeQuery.getString("forename");
							user.surname = executeQuery.getString("surname");
							user.country = executeQuery.getString("country");
							user.language = executeQuery.getString("language");
							user.organisation = executeQuery.getString("organisation");
							user.phone = executeQuery.getString("phone");
							user.acceptedEmailContact = executeQuery.getBoolean("acceptedEmailContact");
							return user;
						}
						return null;
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
	
	private String encryptPassword(String password) {
		String sha1 = ""; //$NON-NLS-1$
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
			crypt.reset();
			crypt.update(password.getBytes("UTF-8")); //$NON-NLS-1$
			sha1 = byteToHex(crypt.digest());
			sha1 = sha1.toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b); //$NON-NLS-1$
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}


}
