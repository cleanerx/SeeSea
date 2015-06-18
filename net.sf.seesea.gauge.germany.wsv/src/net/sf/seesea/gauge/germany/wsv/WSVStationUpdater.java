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

package net.sf.seesea.gauge.germany.wsv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.seesea.gauge.IStationProvider;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

public class WSVStationUpdater implements IStationProvider {

	private Connection gaugeConnection;


	@Override
	public void updateStations() {
		Client client = ClientBuilder.newClient();
		client.register(MoxyJsonFeature.class);
		LoggingFilter loggingFilter = new LoggingFilter();
		client.register(loggingFilter);

		WebTarget path = client.target("http://www.pegelonline.wsv.de").path("webservices").path("rest-api").path("v2").path("stations.json");
        Response response = path.request(MediaType.APPLICATION_JSON).get();
        if(response.getStatus() == 200) {
        	List<Station> readEntity = response.readEntity(new GenericType<List<Station>>(){});
        	
			PreparedStatement statement = null;
			try {
				statement = gaugeConnection.prepareStatement("INSERT INTO gauge (name, gaugetype, provider, water, remoteid, geom) VALUES (?,?,?,?,?, ST_SetSRID(ST_MakePoint(?, ?), 4326))");
				for (Station station : readEntity) {
					statement.setString(1, station.longname);
					statement.setString(2, "RIVER");
					statement.setString(3, "Wasser und Schifffahrsdirektion Germany");
					statement.setString(4, station.water);
					statement.setString(5, station.number);
					statement.setDouble(7, station.latitude);
					statement.setDouble(6, station.longitude);
					statement.addBatch();
				}
				statement.executeBatch();
			} catch (SQLException e) {
				if(statement != null) {
					try {
						statement.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			} 
        }
	}
	
	public void bindConnection(Connection gaugeConnection) {
		this.gaugeConnection = gaugeConnection;
	}

	public void unbindConnection(Connection gaugeConnection) {
		this.gaugeConnection = gaugeConnection;
	}

	
//	public static void main(String args[]) throws SQLException, FileNotFoundException, IOException {
//		Properties properties = new Properties();
//		properties.load(new FileInputStream("config.cfg"));
//		Connection connection = PostgresConnectionFactory.getConnection(properties, "database");
//		WSVStationUpdater wsvStationUpdater = new WSVStationUpdater();
//		wsvStationUpdater.bindConnection(connection);
//		wsvStationUpdater.updateStations();
//	}

}
