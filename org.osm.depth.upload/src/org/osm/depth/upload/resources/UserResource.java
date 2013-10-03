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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import nl.captcha.Captcha.Builder;
import nl.captcha.gimpy.BlockGimpyRenderer;
import nl.captcha.text.producer.NumbersAnswerProducer;

import org.apache.catalina.util.Base64;
import org.osm.depth.upload.CaptchaManagement;
import org.osm.depth.upload.messages.Captcha;
import org.osm.depth.upload.messages.User;

@Path("/users")
public class UserResource {

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("captcha")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCatpcha() {
		Builder builder = new nl.captcha.Captcha.Builder(120, 40);
		nl.captcha.Captcha captcha = builder.addText(new NumbersAnswerProducer(6)).gimp(new BlockGimpyRenderer()).build();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
			ImageIO.write(captcha.getImage(), "png", byteArrayOutputStream); //$NON-NLS-1$
			byte[] imageData = byteArrayOutputStream.toByteArray();
			
			String captchaid = UUID.randomUUID().toString();
			CaptchaManagement.getInstance().registerCaptcha(captchaid, captcha.getAnswer());
			Captcha captcha2 = new Captcha();
			captcha2.id = captchaid;
			captcha2.imageBase64 = Base64.encode(imageData);
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
	
	@PUT
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void changePassword(@javax.ws.rs.core.Context SecurityContext context, @QueryParam("oldPassword") String oldPassword, @QueryParam("newPassword") String newPassword) {
		String username = context.getUserPrincipal().getName();
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres"); //$NON-NLS-1$
			Connection conn = ds.getConnection();

			Statement selectstatement = conn.createStatement();
			selectstatement.execute(MessageFormat
					.format("UPDATE user_profiles SET password = '{2}' WHERE password = {0} AND user_name = ''{1}''", //$NON-NLS-1$
							oldPassword, username, newPassword));

			selectstatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createUser(
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("captcha") String captcha
			)
		{
		Context initContext;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/postgres");
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			ResultSet executeQuery;
			executeQuery = statement.executeQuery("SELECT * FROM user_profiles"); //$NON-NLS-1$
			
			List<User> list = new ArrayList<>(2);
			while(executeQuery.next()) {
				User user = new User();
				user.user_name = executeQuery.getString("user_name");
				user.attemps = executeQuery.getInt("attempts");
				user.lastAttempt = executeQuery.getDate("last_attempt");
				if(user.user_name.equals(username)) {
					return Response.serverError().header("X-Error", "103:Username already exists").build();
				}
			}
			statement.execute("INSERT INTO user_profiles (user_name, password) VALUES (" + username + ",'" +  password + "')"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.serverError().header("X-Error", "104:Database Error").build();
		} catch (NamingException e) {
			e.printStackTrace();
			return Response.serverError().header("X-Error", "104:Database Error").build();
		}

		return null;
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
			Statement statement = conn.createStatement();
			ResultSet executeQuery;
			executeQuery = statement.executeQuery("SELECT * FROM user_profiles"); //$NON-NLS-1$
			
			List<User> list = new ArrayList<>(2);
			while(executeQuery.next()) {
				User user = new User();
				user.user_name = executeQuery.getString("user_name");
				user.attemps = executeQuery.getInt("attempts");
				user.lastAttempt = executeQuery.getDate("last_attempt");
				list.add(user);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
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
