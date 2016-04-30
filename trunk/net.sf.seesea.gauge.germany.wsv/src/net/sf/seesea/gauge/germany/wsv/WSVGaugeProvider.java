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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import net.sf.seesea.data.io.postgis.PostgresConnectionFactory;
import net.sf.seesea.gauge.GaugeUpdateException;
import net.sf.seesea.gauge.IGaugeProvider;

@Component(property = {"provider=Wasser und Schifffahrsdirektion Germany"})
public class WSVGaugeProvider implements IGaugeProvider {

	private Connection gaugeConnection;

	@Override
	public void updateAllGaugeMeasurements(Date startDate, Date endDate) throws GaugeUpdateException {
		try (Statement statement = gaugeConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id, remoteid FROM gauge WHERE provider = 'Wasser und Schifffahrsdirektion Germany'")){
			while(resultSet.next()) {
				String localId = resultSet.getString(1);
				String remoteId = resultSet.getString(2);
				updateSingleGaugeMeasurements(localId, remoteId, startDate, endDate);
			}
		} catch (SQLException e) {
			throw new GaugeUpdateException(e);
		}
	}

	
	@Override
	public void updateSingleGaugeMeasurements(String localId, String remoteID, Date startDate, Date endDate) throws GaugeUpdateException {
		// this provide has only 60 days maxium so it does not make sense to do more
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -60);
		if(calendar.getTime().after(endDate)) {
			Logger.getLogger(getClass()).info("No gauge values available for end date " + endDate);
			return;
		}
		
		try (Statement statementLastEntry = gaugeConnection.createStatement();
			PreparedStatement statement = gaugeConnection.prepareStatement("INSERT INTO gaugemeasurement (gaugeid, value, time) VALUES (?,?,?)")){
			Timestamp lastGaugeValueTimestamp = null;
			try (ResultSet resultSet = statementLastEntry.executeQuery("SELECT gaugeid, MAX(time) AS maxtime FROM gaugemeasurement WHERE gaugeid = " + localId + " GROUP BY gaugeid")) {
				if(resultSet.next()) {
					lastGaugeValueTimestamp = resultSet.getTimestamp(2);
				}
				
				List<WSVGaugeMeasurement> measurements = getRemoteMeasurements(remoteID);
				for (WSVGaugeMeasurement gaugeMeasurement : measurements) {
					SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
					Date parse = mdyFormat.parse(gaugeMeasurement.timestamp);
					Timestamp timestamp = new Timestamp(parse.getTime());
					
					boolean doInsert = false;
					if(lastGaugeValueTimestamp != null) {
						if(timestamp.after(lastGaugeValueTimestamp)) {
							doInsert = true;
						}
					} else {
						doInsert = true;
					}
					if(doInsert) {
						statement.setInt(1, Integer.parseInt(localId));
						statement.setDouble(2, gaugeMeasurement.value / 100);
						statement.setTimestamp(3, timestamp);
						statement.addBatch();
					}
				}
				statement.executeBatch();
			}
		} catch (SQLException e) {
			throw new GaugeUpdateException(e);
		} catch (ParseException e) {
			throw new GaugeUpdateException(e);
		}

	}
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC, target = "(db=gauge)")
	public void bindConnection(Connection gaugeConnection) {
		this.gaugeConnection = gaugeConnection;
	}

	public void unbindConnection(Connection gaugeConnection) {
		this.gaugeConnection = gaugeConnection;
	}

	
	private List<WSVGaugeMeasurement> getRemoteMeasurements(String remoteID) {
		Client client = ClientBuilder.newClient();
		client.register(MoxyJsonFeature.class);
//		LoggingFilter loggingFilter = new LoggingFilter();
//		client.register(loggingFilter);

		WebTarget path = client.target("http://www.pegelonline.wsv.de").path("webservices").path("rest-api").path("v2").path("stations/" + remoteID + "/W/measurements.json").queryParam("start", "P60D");
        Response response = path.request(MediaType.APPLICATION_JSON).get();
        if(response.getStatus() == 200) {
        	List<WSVGaugeMeasurement> readEntity = response.readEntity(new GenericType<List<WSVGaugeMeasurement>>(){});
        	return readEntity;
        }
        return Collections.<WSVGaugeMeasurement>emptyList();
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException, SQLException, GaugeUpdateException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("config.cfg"));
		Connection connection = PostgresConnectionFactory.getConnection(properties, "database");
		WSVGaugeProvider wsvGaugeProvider = new WSVGaugeProvider();
		wsvGaugeProvider.bindConnection(connection);
		wsvGaugeProvider.updateAllGaugeMeasurements(null, null);
	}
}
