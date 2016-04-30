/**
Copyright (c) 2013-2015, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package net.sf.seesea.data.io.postgis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 */
public class PostgresConnectionFactory {

	/**
	 * 
	 * @param properties
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(Properties properties, String databaseProperty) throws SQLException {
		String user = (String) properties.get("user"); //$NON-NLS-1$
		String password = (String) properties.get("password"); //$NON-NLS-1$
		String host = (String) properties.get("host"); //$NON-NLS-1$
		if(host == null || host.isEmpty()) {
			host = "localhost"; //$NON-NLS-1$
		}
		String port = (String) properties.get("port"); //$NON-NLS-1$
		if(port == null || port.isEmpty()) {
			port = "5432"; //$NON-NLS-1$
		}
		String database = (String) properties.get(databaseProperty); //$NON-NLS-1$
		
		String url = "jdbc:postgresql:" + (host != null ? ("//" + host) + (port != null ? ":" + port : "") + "/" : "") + database ; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return DriverManager.getConnection (url, user, password);
	}

	/**
	 * 
	 * @param properties
	 * @return
	 * @throws SQLException
	 */
	public static Connection getDBConnection(Properties properties, String database) throws SQLException {
		String user = (String) properties.get("user"); //$NON-NLS-1$
		String password = (String) properties.get("password"); //$NON-NLS-1$
		String host = (String) properties.get("host"); //$NON-NLS-1$
		if(host == null || host.isEmpty()) {
			host = "localhost"; //$NON-NLS-1$
		}
		String port = (String) properties.get("port"); //$NON-NLS-1$
		if(port == null || port.isEmpty()) {
			port = "5432"; //$NON-NLS-1$
		}
		
		String url = "jdbc:postgresql:" + (host != null ? ("//" + host) + (port != null ? ":" + port : "") + "/" : "") + database ; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

		return DriverManager.getConnection (url, user, password);
	}

	
}
