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
import java.util.Date;


import net.sf.seesea.services.navigation.GeoBoundingBox;
import net.sf.seesea.services.navigation.IGeoBoundingBox;
import net.sf.seesea.waterlevel.ocean.IOceanTideProvider;
import net.sf.seesea.waterlevel.ocean.LengthUnit;
import net.sf.seesea.waterlevel.ocean.TideLevel;

import org.apache.log4j.Logger;

public class CombinedWaterLevelCorrection implements IWaterLevelCorrection {

	private Connection coastlineConnection;

	private IWaterLevelCorrection gaugeCorrection;

	private IOceanTideProvider oceantideProvider;

	private CheckCoordinate checkCoordinate;

	private TrackLocation trackLocation;

	private GeoBoundingBox _cacheBoundingBox;

	private TrackLocation lastLocation;

	public CombinedWaterLevelCorrection() {
		// defaulting to point checking. only if a bbox is set we check the bbox
		checkCoordinate = CheckCoordinate.POINT;
	}

	/**
	 * checks the bounding box location
	 */
	@Override
	public void setBoundingBox(IGeoBoundingBox boundingBox) {
		if (boundingBox == null) {
			checkCoordinate = CheckCoordinate.POINT;
		} else {
			trackLocation = isBBoxOnOpenSea(boundingBox);
			checkCoordinate = CheckCoordinate.BBOX;
		}
	}

	@Override
	public double getCorrection(double lat, double lon, Date time) {

		// we found the bbox to be completely on the ocean
		if (CheckCoordinate.BBOX.equals(checkCoordinate) && TrackLocation.OCEAN.equals(trackLocation)) {
			return oceantideProvider.getTideHeight(TideLevel.LOWESTATRONOMICALTIDE, LengthUnit.METERS, lat, lon, time);
			// } else if(checkCoordinate.equals(CheckCoordinate.BBOX) &&
			// trackLocation.equals(TrackLocation.UNDEFINED)) {
			// // a bbox is provided but some portions of the bbox are on land,
			// so we need to do a point check
			// boolean pointOnOpenSea = isPointOnOpenSea(lat, lon);
		} else {
			boolean pointOnOpenSea = isPointOnOpenSea(lat, lon);
			if (pointOnOpenSea) {
				// FIXME if tide station with real data is nearby - choose that
				// one
				return oceantideProvider.getTideHeight(TideLevel.LOWESTATRONOMICALTIDE, LengthUnit.METERS, lat, lon, time);
			} else { // river or lake
				return gaugeCorrection.getCorrection(lat, lon, time);
			}
		}

	}

	/**
	 * if the bbox returns land, some of the points may be
	 * 
	 * @param boundingBox
	 * @return
	 */
	private TrackLocation isBBoxOnOpenSea(IGeoBoundingBox boundingBox) {
		PreparedStatement statement = null;
		try {
			String polygon = "SELECT gid FROM water WHERE ST_CONTAINS(the_geom, ST_SetSRID(ST_MakePolygon(ST_GeomFromText('LINESTRING(" + boundingBox.getLeft() + " " + boundingBox.getTop() + ", " + boundingBox.getRight() + " " + boundingBox.getTop() + ", " + boundingBox.getRight() + " " + boundingBox.getBottom() + ", " + boundingBox.getLeft() + " " + boundingBox.getBottom() + ", " + boundingBox.getLeft() + " " + boundingBox.getTop() + ")')), 4326)) = true"; //$NON-NLS-1$
			// String polygonString = MessageFormat.format(polygon,
			// boundingBox.getTop(), boundingBox.getLeft(),
			// boundingBox.getBottom(), boundingBox.getRight());

			statement = coastlineConnection.prepareStatement(polygon);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return TrackLocation.OCEAN;
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error("Failed to test if bbox is on open sear", e); //$NON-NLS-1$
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					Logger.getLogger(getClass()).error("Failed to test if bbox is on open sear", e); //$NON-NLS-1$
				}
			}
		}
		// can be both on ocean and land - need to do point checks
		return TrackLocation.UNDEFINED;
	}

	private boolean isPointOnOpenSea(double lat, double lon) {
		// create a virtual bounding box spanning around 1 nautical mile around
		// for cacheing purposes
		if (_cacheBoundingBox == null || !_cacheBoundingBox.contains(lat, lon)) {
			_cacheBoundingBox = new GeoBoundingBox();
			_cacheBoundingBox.setLeft(lon - 0.016666666666);
			_cacheBoundingBox.setRight(lon + 0.016666666666);
			_cacheBoundingBox.setTop(lat + 0.016666666666);
			_cacheBoundingBox.setBottom(lat - 0.016666666666);
			lastLocation = isBBoxOnOpenSea(_cacheBoundingBox);
		}
		// cache miss
		if (lastLocation.equals(TrackLocation.OCEAN)) {
			// Logger.getLogger(getClass()).info("Cache hit  at " + lat + ":" +
			// lon);
			return true;
		} else {
			_cacheBoundingBox = new GeoBoundingBox();
			_cacheBoundingBox.setLeft(lon - 0.0025);
			_cacheBoundingBox.setRight(lon + 0.0025);
			_cacheBoundingBox.setTop(lat + 0.0025);
			_cacheBoundingBox.setBottom(lat - 0.0025);
			lastLocation = isBBoxOnOpenSea(_cacheBoundingBox);
			if (lastLocation.equals(TrackLocation.OCEAN)) {
				// Logger.getLogger(getClass()).info("Cache hit  at " + lat +
				// ":" + lon);
				return true;
			} else {
				_cacheBoundingBox = new GeoBoundingBox();
				_cacheBoundingBox.setLeft(lon - 0.0002);
				_cacheBoundingBox.setRight(lon + 0.0002);
				_cacheBoundingBox.setTop(lat + 0.0002);
				_cacheBoundingBox.setBottom(lat - 0.0002);
				lastLocation = isBBoxOnOpenSea(_cacheBoundingBox);
				if (lastLocation.equals(TrackLocation.OCEAN)) {
					// Logger.getLogger(getClass()).info("Cache hit  at " + lat
					// + ":" + lon);
					return true;
				} else {
					Logger.getLogger(getClass()).info("Cache miss at " + lat + ":" + lon);
					PreparedStatement statement = null;
					try {
						statement = coastlineConnection.prepareStatement("SELECT gid FROM water WHERE ST_CONTAINS(the_geom, ST_SetSRID(ST_MakePoint(?, ?), 4326)) = true"); //$NON-NLS-1$
						statement.setDouble(1, lon);
						statement.setDouble(2, lat);
						ResultSet resultSet = null;
						try {
							resultSet = statement.executeQuery();
							if (resultSet.next()) {
								return true;
							}
						} finally {
							if (resultSet != null) {
								resultSet.close();
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (statement != null) {
							try {
								statement.close();
							} catch (SQLException e) {
								Logger.getLogger(getClass()).error("Failed query", e);
							}
						}
					}
					return false;
				}
			}
		}
	}

	public synchronized void bindWaterLevelCorrection(IWaterLevelCorrection gaugeCorrection) {
		this.gaugeCorrection = gaugeCorrection;
	}

	public synchronized void unbindWaterLevelCorrection(IWaterLevelCorrection gaugeCorrection) {
		this.gaugeCorrection = null;
	}

	public synchronized void bindCoastlineConnection(Connection connection) {
		this.coastlineConnection = connection;
	}

	public synchronized void unbindCoastlineConnection(Connection connection) {
		this.coastlineConnection = null;
	}

	public synchronized void bindOceanTideProvider(IOceanTideProvider oceantideProvider) {
		this.oceantideProvider = oceantideProvider;
	}

	public synchronized void unbindOceanTideProvider(IOceanTideProvider oceantideProvider) {
		this.oceantideProvider = null;
	}

}
