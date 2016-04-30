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
package net.sf.seesea.data.postprocessing.filter.boat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import net.sf.seesea.track.api.data.IBoatParameters;

/**
 * extract boat parameters from the user database
 */
public class UserDatabaseBoatParameters implements IBoatParameters {

	private Connection connection;

	@Override
	public double getSensorOffsetToWaterline(long trackId, String sensorId) {
		PreparedStatement prepareStatement = null;
		try {
			// FIXME: multi sensor setups not considered right now
			prepareStatement = connection.prepareStatement("SELECT depthsensor.x,depthsensor.y,depthsensor.z FROM depthsensor, vesselconfiguration, user_tracks WHERE vesselconfiguration.id = user_tracks.vesselconfigid AND depthsensor.vesselconfigid = vesselconfiguration.id AND user_tracks.track_id = ?"); //$NON-NLS-1$
			prepareStatement.setLong(1, trackId);
			ResultSet executeQuery = null;
			try {
				executeQuery = prepareStatement.executeQuery();
				while(executeQuery.next()) {
					return executeQuery.getDouble(3);
				}
			} finally {
				if( executeQuery != null) {
					executeQuery.close();
				}
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error("Failed to obtain sensor offset", e); //$NON-NLS-1$
		} finally {
			if(prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					Logger.getLogger(getClass()).error("Failed to close statement", e); //$NON-NLS-1$
				}
			}
		}
		return 0;
	}

	@Override
	public double getLatOffset2DepthSensor(double magneticBearing) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLonOffset2DepthSensor(double magneticBearing) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public synchronized void bindConnection(Connection connection) {
		this.connection = connection;
	}

	public synchronized void unbindConnection(Connection connection) {
		this.connection = null;
	}

}
