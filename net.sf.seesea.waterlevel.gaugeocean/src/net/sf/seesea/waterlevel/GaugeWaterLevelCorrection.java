package net.sf.seesea.waterlevel;
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


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

import net.sf.seesea.gauge.IGaugeProvider;
import net.sf.seesea.services.navigation.IGeoBoundingBox;


public class GaugeWaterLevelCorrection implements IWaterLevelCorrection {
	
	private Connection connection;
	
	private ComponentContext componentContext;
	
	//
	@SuppressWarnings("null")
	private void updateGaugeValues4Track(Long trackID, Date startTime, Date endTime) {
		boolean areGaugesUserDefined = false;
		// are gauges predefined by the user, then use them
		try (PreparedStatement userDefinedGaugeTrackStatement = connection.prepareStatement("SELECT gaugeid FROM gaugemeasurement WHERE trackid = ? AND source = ?");) {
			userDefinedGaugeTrackStatement.setLong(1, trackID);
			userDefinedGaugeTrackStatement.setInt(2, GaugeTrackSource.USERDEFINED.ordinal());
			ResultSet query = userDefinedGaugeTrackStatement.executeQuery();
			// multiple gauges. which one to pick for which location / area
			// easiest solution is distance (per point ?) Store it for each track point to determine errors ?
			while(query.next()) {
				int gaugeId = query.getInt(1);
				areGaugesUserDefined = true;
				// ideally the gauges have a location and an area for which they are responsible
				// then a fast decision for the appropriate gauge is possible
//				retrieveLatestGaugeValues(startTime, endTime, gaugeId);
				
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error("Failed to query for user gauges", e);
		}

		if(!areGaugesUserDefined) {
			// determine which water polygons may have been hit by the track
//			ITriangulationPersistence p;
//			p.getHitPartitionizedPolygons(Long trackId, "trackpoints_16_raw");
			// ideally the gauges know their responsible areas - can be formulated coarse - no area means distance based determination
			// query which gauges intersect the track bounding box
			
			// determine which gauges are present for the given polygons and their providers
//			retrieveLatestGaugeValues(startTime, endTime, gaugeId);
		}
		
		// determine the associated time frame for a single or multiple tracks - proper clustering required, global time required, if not provided anyway
		// download the data for that track and store it locally
		// save the water polygon id for later reference ?
		
	}

	
	
	@Override
	public double getCorrection(double lat, double lon, Date time2) {
		
		long time = time2.getTime(); // + 7200000;
//		Calendar instance = Calendar.getInstance();
//		instance.setTimeZone(TimeZone.getTimeZone("UTC"));
//		instance.setTime(time);
//		time = instance.getTime();time.getTime()
//		long timeX = time.getTime();
		Gauge gauge = getGauge(lat, lon);
		
		PreparedStatement minStatement = null;
		PreparedStatement maxStatement = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(time);
			calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			minStatement = connection.prepareStatement("SELECT time, value from GAUGEMEASUREMENT WHERE time = (SELECT MIN(time) FROM gaugemeasurement WHERE time >= ?) AND gaugeid = ?");
			minStatement.setTimestamp(1, new Timestamp(time), calendar);
			minStatement.setLong(2, gauge.getId());
			ResultSet resultSet = minStatement.executeQuery();
			Date endTime = null;
			double endValue = Double.NaN;
			if(resultSet.next()) {
				endTime = new Date(resultSet.getTimestamp(1).getTime());
				endValue = resultSet.getDouble(2);
			}
			
			maxStatement = connection.prepareStatement("SELECT time, value from GAUGEMEASUREMENT WHERE time = (SELECT MAX(time) FROM gaugemeasurement WHERE time <= ?) AND gaugeid = ?");
			maxStatement.setTimestamp(1, new Timestamp(time), calendar);
			maxStatement.setLong(2, gauge.getId());
			ResultSet resultSet2 = maxStatement.executeQuery();
			Date startTime = null;
			double startValue = Double.NaN;
			if(resultSet2.next()) {
				startTime = new Date(resultSet2.getTimestamp(1).getTime());
				startValue = resultSet2.getDouble(2);
			} else {
				// old UTC date, no such value
				return Double.NaN;
			}
			if(startTime == null || endTime == null) {
				Logger.getLogger(getClass()).warn("No gauge data for location " + lat + ":" + lon);
				return 0.0;
			}
			if(startTime.equals(endTime)) {
				return startValue - gauge.getCommonWaterLevel();
			}
			
			double timeRatio = (time - startTime.getTime()) / (double)(endTime.getTime() - startTime.getTime());
			// this currently assumes that the depth exactly follows the depth at the gauge which in general is not the case
			// case there is a time offset that is caused by the offset to the measured position
			double interpolatedDepth = (endValue - startValue) * timeRatio + startValue; ;
			return interpolatedDepth - gauge.getCommonWaterLevel();
			

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if(minStatement != null) {
				try {
					minStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(maxStatement != null) {
				try {
					maxStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}

	/**
	 * 
	 * @param lat
	 * @param lon
	 * @return the closest gauge id to the given location
	 */
	public Gauge getGauge(double lat, double lon) {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("SELECT id, waterlevel FROM gauge ORDER BY geom <-> ST_SetSRID(ST_MakePoint(?, ?), 4326) LIMIT 1;"); //$NON-NLS-1$
			statement.setDouble(1, lon);
			statement.setDouble(2, lat);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return new Gauge(resultSet.getLong(1), resultSet.getDouble(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
		
	}
	
	public synchronized void bindConnection(Connection connection) {
		this.connection = connection;
	}

	public synchronized void unbindConnection(Connection connection) {
		this.connection = null;
	}

	@Override
	public void setBoundingBox(IGeoBoundingBox boundingBox) {
		// TODO Auto-generated method stub
		
	}

	public void actviate(ComponentContext componentContext) {
		this.componentContext = componentContext;
	}
	
	public void deactivate(ComponentContext componentContext) {
		this.componentContext = null;
	}


}
