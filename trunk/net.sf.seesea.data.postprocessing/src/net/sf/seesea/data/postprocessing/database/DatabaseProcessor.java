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

package net.sf.seesea.data.postprocessing.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import net.sf.seesea.data.postprocessing.process.IFilterController;
import net.sf.seesea.gauge.IGaugeValueUpdater;
import net.sf.seesea.model.core.geo.GeoBoundingBox;
import net.sf.seesea.track.api.exception.NMEAProcessingException;

@Component
public class DatabaseProcessor {

//	private Properties _properties;
	private Connection connection;
	private IFilterController filterController;
	private String basedir;
	private boolean fullprocess;
	private boolean detectContent;
	private boolean preprocessRun;
	private IGaugeValueUpdater gaugeValueUpdater;
	private boolean processContours;

	public DatabaseProcessor() {
	}
	
	public void activate(Map<String, Object> _properties) {
		basedir = (String) _properties.get("uploaddir");
//		List<Map<String, Object>> filterProperties = getFilterConfigs(_properties);
		fullprocess = "true".equals(_properties.get("fullprocess"));
		detectContent = "true".equals(_properties.get("detectContent"));
		
		preprocessRun = true;
		if ("true".equals(_properties.get("postprocess"))) { //$NON-NLS-1$ //$NON-NLS-2$
			preprocessRun = false;
		}
		processContours = "true".equals(_properties.get("process_contours"));

	}

	/**
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws NMEAProcessingException
	 */
	public void createAndFilterTracks() throws SQLException, FileNotFoundException, IOException, NMEAProcessingException {

		try {

//			Set<String> whitelistUsers = getValues("whitelistUsers"); //$NON-NLS-1$
//			Set<String> blacklistUsers = getValues("blacklistUsers"); //$NON-NLS-1$
//			Set<String> processTrackIds = getValues("processTrackIds"); //$NON-NLS-1$
//
//			boolean detectContent = "true".equals(_properties.get("detectContent"));

//			fullprocess = "true".equals(_properties.get("fullprocess"));
			
//			preprocessRun = true;
//			if ("true".equals(_properties.get("postprocess"))) { //$NON-NLS-1$ //$NON-NLS-2$
//				preprocessRun = false;
//			}

			
//			if (detectContent) { //$NON-NLS-1$ //$NON-NLS-2$
//				setContentTypes(connection, basedir, processTrackIds);
//			}
//
//
//			PreparedStatement markTrackFileDuplicate = connection.prepareStatement("UPDATE user_tracks SET upload_state='" + ProcessingState.FILE_DUPLICATE.ordinal() + "' WHERE track_id = ?"); //$NON-NLS-1$ //$NON-NLS-2$
//			PreparedStatement markTrackFileCorrupt = connection.prepareStatement("UPDATE user_tracks SET upload_state='" + ProcessingState.FILE_CORRUPT.ordinal() + "' WHERE track_id = ?"); //$NON-NLS-1$ //$NON-NLS-2$
//			PreparedStatement markTrackFileNoData = connection.prepareStatement("UPDATE user_tracks SET upload_state='" + ProcessingState.FILE_NODATA.ordinal() + "' WHERE track_id = ?"); //$NON-NLS-1$ //$NON-NLS-2$
//			PreparedStatement markTrackFileProcessed = connection.prepareStatement("UPDATE user_tracks SET upload_state='" + ProcessingState.FILE_PROCESSED.ordinal() + "' WHERE track_id = ?"); //$NON-NLS-1$ //$NON-NLS-2$
//			PreparedStatement markTrackFileTriangulated = connection.prepareStatement("UPDATE user_tracks SET upload_state='" + ProcessingState.TRIANGULATED.ordinal() + "' WHERE track_id = ?"); //$NON-NLS-1$ //$NON-NLS-2$
//			PreparedStatement markTrackFileContoursGenerated = connection.prepareStatement("UPDATE user_tracks SET upload_state='" + ProcessingState.CONTOURS_GENERATED.ordinal() + "' WHERE track_id = ?"); //$NON-NLS-1$ //$NON-NLS-2$
//			PreparedStatement setBoundingBox = connection.prepareStatement("UPDATE user_tracks SET bbox=GeomFromText(?,4326) WHERE track_id = ?"); //$NON-NLS-1$
//
////			ServiceReference<IContourLineGeneration> serviceReference = DataPostprocessingActivator.getContext().getServiceReference(IContourLineGeneration.class);
////			IContourLineGeneration contourLineGeneration = DataPostprocessingActivator.getContext().getService(serviceReference);
////
////			ServiceReference<IGaugeValueUpdater> gaugeUpdateServiceReference = DataPostprocessingActivator.getContext().getServiceReference(IGaugeValueUpdater.class);
////			IGaugeValueUpdater gaugeValueUpdater = DataPostprocessingActivator.getContext().getService(gaugeUpdateServiceReference);
//
//
//
//			for (String user : getUsersFromDatabase(connection)) {
//				System.out.println("Processing user " + user);
//				try {
//					if ((whitelistUsers.isEmpty() || whitelistUsers.contains(user)) && !blacklistUsers.contains(user)) {
//						List<AbstractTrackFile> orderedFilesFromDatabase = getFilesFromDatabase(connection, basedir, user, preprocessRun);
//						if (!processTrackIds.isEmpty()) {
//							List<AbstractTrackFile> filteredTrackFileList = new ArrayList<AbstractTrackFile>();
//							for (String trackId : processTrackIds) {
//								for (Iterator iterator = orderedFilesFromDatabase.iterator(); iterator.hasNext();) {
//									AbstractTrackFile abstractTrackFile = (AbstractTrackFile) iterator.next();
//									if (Long.parseLong(trackId) == abstractTrackFile.getTrackId()) {
//										filteredTrackFileList.add(abstractTrackFile);
//									}
//								}
//							}
//							orderedFilesFromDatabase = filteredTrackFileList;
//						}
//
//						// order files according to recored date (determined
//						// from within the files)
//						TimeBasedTrackClustering timeBasedTrackClustering = new TimeBasedTrackClustering(new FileTypeProcessingFactory());
//						TrackClusterResult timeOrderedTracks = timeBasedTrackClustering.classifyTracks(orderedFilesFromDatabase);
//
//						if (preprocessRun) {
//							// set bounding box
//							for (AbstractTrackFile abstractTrackFile : orderedFilesFromDatabase) {
//								IGeoBoundingBox boundingBox = abstractTrackFile.getBoundingBox();
//								if (boundingBox != null) {
//									StringBuffer b = getBoundingBox(boundingBox);
//									setBoundingBox.setString(1, b.toString());
//									setBoundingBox.setLong(2, abstractTrackFile.getTrackId());
//									setBoundingBox.addBatch();
//								}
//							}
//							setBoundingBox.executeBatch();
//
//							// mark as duplicate in database
//							for (ITrackFile abstractTrackFile : timeOrderedTracks.getDuplicateTrackFiles()) {
//								markTrackFileDuplicate.setLong(1, abstractTrackFile.getTrackId());
//								markTrackFileDuplicate.addBatch();
//							}
//							markTrackFileDuplicate.executeBatch();
//
//							// mark as corrupt in database
//							for (ITrackFile abstractTrackFile : timeOrderedTracks.getCorruptTrackFiles()) {
//								markTrackFileCorrupt.setLong(1, abstractTrackFile.getTrackId());
//								markTrackFileCorrupt.addBatch();
//							}
//							markTrackFileCorrupt.executeBatch();
//
//							// mark as corrupt in database
//							for (ITrackFile abstractTrackFile : timeOrderedTracks.getNodataTrackFiles()) {
//								markTrackFileNoData.setLong(1, abstractTrackFile.getTrackId());
//								markTrackFileNoData.addBatch();
//							}
//							markTrackFileNoData.executeBatch();
//						}
//
//						// start preprocessing
//						// detect best sensors,
//
//						/*
//						 * for stats we need multiple sensors and a counter OR
//						 * time ? for filtering we need to have at least
//						 * relative time for gauge correction we need UTC time
//						 * for depth vessel correction nothing is required for
//						 * GPS to depth correction magnetic orientation
//						 * corrected by abbreviation is required filtering 1) no
//						 * time -> no stats distribution for sensor selection,
//						 * unfiltered, not gauge corrected ? 2) relative ->
//						 * stats for sensor selection, raw and filtered, not
//						 * gauge corrected 3) absolute -> stats for sensor
//						 * selection, raw and - filtered, gauge and tide
//						 * corrected
//						 */
//						Set<Long> contourTrackIds = new LinkedHashSet<Long>();
//						// process files that have no time alignment
//						for (ITrackFile abstractTrackFile : timeOrderedTracks.getNoTimeMeasurementFiles()) {
//							filterController.setTimeout(60000);
//							filterController.setFilterProperties(filterProperties);
//							List<ITrackFile> singleTrackList = new ArrayList<ITrackFile>();
//							singleTrackList.add(abstractTrackFile);
//							filterController.process(singleTrackList, false);
//							if (preprocessRun) {
//								// mark as processed in database
//								markTrackFileProcessed.setLong(1, abstractTrackFile.getTrackId());
//								markTrackFileProcessed.addBatch();
//							}
//						}
//						if (preprocessRun) {
//							markTrackFileProcessed.executeBatch();
//						}
//
//						for (List<ITrackFile> clusterOfTrackFiles : timeOrderedTracks.getOrderedTrackFiles()) {
//							if(!clusterOfTrackFiles.isEmpty()) {
//								List<ITrackFile> trackFiles = new ArrayList<ITrackFile>(clusterOfTrackFiles);
//								try {
//									gaugeValueUpdater.updateGaugeValues4Track(trackFiles);
//								} catch (GaugeUpdateException e) {
//									Logger.getLogger(getClass()).error("Failed to update gauge", e);
//								}
//								FilterController filterController = new FilterController();
//								filterController.setTimeout(60000);
//								filterController.setFilterProperties(filterProperties);
//								filterController.process(clusterOfTrackFiles, true);
//							}
//
//							if (preprocessRun) {
//								// mark as processed in database
//								for (ITrackFile abstractTrackFile : clusterOfTrackFiles) {
//									markTrackFileProcessed.setLong(1, abstractTrackFile.getTrackId());
//									markTrackFileProcessed.addBatch();
//								}
//								markTrackFileProcessed.executeBatch();
//							}
//						}
//					}
//				} catch (FilterException e1) {
//					Logger.getLogger(getClass()).error("Not found", e1); //$NON-NLS-1$
//				}
//			}
//			Set<Long> contourTrackIds = new LinkedHashSet<Long>();
//			if(contourTracks != null) {
//				for (String track : contourTracks) {
//					try {
//						contourTrackIds.add(Long.parseLong(track.trim()));
//					} catch (NumberFormatException exception) {
//						Logger.getLogger(getClass()).error("Not found", exception); //$NON-NLS-1$
//					}
//				}
//			}
//			
//			if() {
//				Set<Long> processedTracksFromDatabase = null;
//				if(contourTrackIds.isEmpty()) {
//					processedTracksFromDatabase = getProcessedTracksFromDatabase(connection);
//				} else {
//					processedTracksFromDatabase = contourTrackIds;
//				}
//				contourLineGeneration.updateContourLines(processedTracksFromDatabase, contourLineDepths, (String) _properties.get("postprocessed_datapoints_table"), markTrackFileTriangulated);
//				for (Long trackId : processedTracksFromDatabase) {
//					markTrackFileContoursGenerated.setLong(1, trackId);
//					markTrackFileContoursGenerated.addBatch();
//				}
//				markTrackFileContoursGenerated.executeBatch();
//			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		Logger.getLogger(getClass()).info("Processing completed"); //$NON-NLS-1$
	}

	private List<Map<String, Object>> getFilterConfigs(Properties properties) {
		List<Map<String, Object>> filterProperties = new ArrayList<Map<String, Object>>(2);
		Map<String, Object> allProperties = new HashMap<String, Object>();
		for (Entry<Object, Object> entry : properties.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			if (key.startsWith("filter.")) {
				int indexOfFirstDot = key.indexOf("filter.") + 7;
				int indexOfSecondDot = key.indexOf(".", indexOfFirstDot);
				String filterNumber = key.substring(indexOfFirstDot, indexOfSecondDot);
				int parseInt = Integer.parseInt(filterNumber);
				while (filterProperties.size() < parseInt) {
					filterProperties.add(new HashMap<String, Object>());
				}
				Map<String, Object> filterProps = filterProperties.get(parseInt - 1);
				String filterKey = key.substring(indexOfSecondDot + 1).trim();
				filterProps.put(filterKey, value);
			} else {
				allProperties.put(key, value);
			}
		}
		for (Map<String, Object> map : filterProperties) {
			map.putAll(allProperties);
		}
		return filterProperties;
	}

//	private Set<String> getTrackFilesToReprocess(Connection connection, List<Map<String, Object>> filterProperties) {
//		Set<String> trackIDs = new HashSet<String>();
//		Statement getTracksToReprocessStatement = null;
//		ResultSet resultSet = null;
//		try {
//			getTracksToReprocessStatement = connection.createStatement();
//			resultSet = getTracksToReprocessStatement.executeQuery("SELECT track_id FROM user_tracks WHERE upload_state = '" + ProcessingState.REPROCESS.ordinal() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
//			while (resultSet.next()) {
//				Long trackId = resultSet.getLong(1);
//				trackIDs.add(trackId.toString());
//			}
//		} catch (SQLException e) {
//			Logger.getLogger(getClass()).error("Failed to query tracks to reprocess", e); //$NON-NLS-1$
//		} finally {
//			if (resultSet != null) {
//				try {
//					resultSet.close();
//				} catch (SQLException e) {
//					Logger.getLogger(getClass()).error("Failed to query tracks to reprocess", e); //$NON-NLS-1$
//				}
//			}
//			if (getTracksToReprocessStatement != null) {
//				try {
//					getTracksToReprocessStatement.close();
//				} catch (SQLException e) {
//					Logger.getLogger(getClass()).error("Failed to query tracks to reprocess", e); //$NON-NLS-1$
//				}
//			}
//		}
//		return trackIDs;
//	}

	private StringBuffer getBoundingBox(GeoBoundingBox boundingBox) {
		StringBuffer b = new StringBuffer();
		b.append("POLYGON("); //$NON-NLS-1$
		b.append("("); //$NON-NLS-1$
		b.append(boundingBox.getLeft());
		b.append(' ');
		b.append(boundingBox.getTop());
		b.append(',');
		b.append(boundingBox.getRight());
		b.append(' ');
		b.append(boundingBox.getTop());
		b.append(',');
		b.append(boundingBox.getRight());
		b.append(' ');
		b.append(boundingBox.getBottom());
		b.append(',');
		b.append(boundingBox.getLeft());
		b.append(' ');
		b.append(boundingBox.getBottom());
		b.append(',');
		b.append(boundingBox.getLeft());
		b.append(' ');
		b.append(boundingBox.getTop());
		b.append(")"); //$NON-NLS-1$
		b.append(")"); //$NON-NLS-1$
		return b;
	}



//	private List<String> getUsersFromDatabase(Connection connection) throws SQLException {
//		List<String> users = new ArrayList<String>();
//		Statement orderStatement = connection.createStatement();
//		ResultSet usersResult = orderStatement.executeQuery("SELECT user_name FROM user_profiles"); //$NON-NLS-1$
//		while (usersResult.next()) {
//			users.add(usersResult.getString(1));
//		}
//		return users;
//	}
//
//	private Set<Long> getProcessedTracksFromDatabase(Connection connection) throws SQLException {
//		Set<Long> users = new HashSet<Long>();
//		Statement orderStatement = connection.createStatement();
//		ResultSet usersResult = orderStatement.executeQuery("SELECT track_id FROM user_tracks WHERE upload_state = " + ProcessingState.FILE_PROCESSED.ordinal()); //$NON-NLS-1$
//		while (usersResult.next()) {
//			users.add(usersResult.getLong(1));
//		}
//		return users;
//	}
//
//	/**
//	 * 
//	 * @param connection
//	 *            the sql connection to retrieve tracks from
//	 * @param baseFileDir
//	 *            the base directory where file are stored
//	 * @param user
//	 *            the user for which to order tracks
//	 * @return all tracks the user owns
//	 * @throws SQLException
//	 * @throws IOException
//	 * @throws ZipException
//	 * @throws NMEAProcessingException
//	 */
//	public List<AbstractTrackFile> getFilesFromDatabase(Connection connection, String baseFileDir, String user, boolean preprocessed) throws SQLException, ZipException, IOException,
//			NMEAProcessingException {
//		List<AbstractTrackFile> orderedFiles = new ArrayList<AbstractTrackFile>();
//		DecimalFormat format = new DecimalFormat("000"); //$NON-NLS-1$
//
//		String sha1Username = encryptUser(user);
//
//		ResultSet vesselConfigurations = connection.createStatement().executeQuery("SELECT id, name, description FROM vesselconfiguration WHERE user_name = '" + user + "'"); //$NON-NLS-1$ //$NON-NLS-2$
//
//		while (vesselConfigurations.next()) {
//			VesselConfiguration vesselConfiguration = new VesselConfiguration();
//			int id = vesselConfigurations.getInt(1);
//
//			ResultSet gpsOffsets = connection.createStatement().executeQuery("SELECT vesselconfigid, sensorid, x,y,z FROM sbassensor WHERE vesselconfigid = '" + id + "'"); //$NON-NLS-1$ //$NON-NLS-2$
//			while (gpsOffsets.next()) {
//				Point3D offset = new Point3D(gpsOffsets.getDouble(3), gpsOffsets.getDouble(4), gpsOffsets.getDouble(5));
//				vesselConfiguration.getGpsSensorOffsets().put(gpsOffsets.getString(2), offset);
//			}
//
//			ResultSet depthOffsets = connection.createStatement().executeQuery("SELECT vesselconfigid, sensorid, x,y,z FROM depthsensor WHERE vesselconfigid = '" + id + "'"); //$NON-NLS-1$ //$NON-NLS-2$
//			while (depthOffsets.next()) {
//				Point3D offset = new Point3D(depthOffsets.getDouble(3), depthOffsets.getDouble(4), depthOffsets.getDouble(5));
//				vesselConfiguration.getDepthSensorOffsets().put(depthOffsets.getString(2), offset);
//			}
//		}
//
//		Statement orderStatement = connection.createStatement();
//
//		ResultSet singleUserTrackFiles = null;
//		if (preprocessed) {
//			singleUserTrackFiles = orderStatement.executeQuery("SELECT track_id, filetype, compression, file_ref FROM user_tracks " + //$NON-NLS-1$
//					"WHERE (user_name = '" + user + "' OR user_name = '" + sha1Username + "')" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//					"AND upload_state = '" + ProcessingState.PREPROCESSED.ordinal() + "' " + //$NON-NLS-1$ //$NON-NLS-2$
//					"AND containertrack IS NULL " + //$NON-NLS-1$
//					"AND track_id NOT IN (SELECT containertrack FROM user_tracks WHERE containertrack IS NOT NULL)"); //$NON-NLS-1$
//		} else {
//			singleUserTrackFiles = orderStatement.executeQuery("SELECT track_id, filetype, compression, file_ref FROM user_tracks " + //$NON-NLS-1$
//					"WHERE (user_name = '" + user + "' OR user_name = '" + sha1Username + "')" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//					"AND upload_state = '" + ProcessingState.FILE_PROCESSED.ordinal() + "' " + //$NON-NLS-1$ //$NON-NLS-2$
//					"AND containertrack IS NULL " + //$NON-NLS-1$
//					"AND track_id NOT IN (SELECT containertrack FROM user_tracks WHERE containertrack IS NOT NULL)"); //$NON-NLS-1$
//		}
//
//		while (singleUserTrackFiles.next()) {
//			long id = singleUserTrackFiles.getLong("track_id"); //$NON-NLS-1$
//			String trackFile = baseFileDir + "/" //$NON-NLS-1$
//					+ format.format((id / 100) * 100) + "/" + id + ".dat"; //$NON-NLS-1$ //$NON-NLS-2$
//			String compression = singleUserTrackFiles.getString("compression"); //$NON-NLS-1$
//			String fileType = singleUserTrackFiles.getString("filetype"); //$NON-NLS-1$
//			AbstractTrackFile file;
//			if (compression == null) {
//				SimpleTrackFile trackFileX = new SimpleTrackFile();
//				trackFileX.setTrackId(id);
//				trackFileX.setFileReference(trackFile);
//				trackFileX.setCompression(CompressionType.NONE);
//				trackFileX.setFileType(fileType);
//				trackFileX.setName(singleUserTrackFiles.getString("file_ref"));
//				file = trackFileX;
//				orderedFiles.add(file);
//			} else {
//				CompressionType compressionType = CompressionType.getCompressionType(compression);
//				switch (compressionType) {
//				case GZ:
//					GzipTrackFile trackFileX = new GzipTrackFile();
//					trackFileX.setTrackId(id);
//					trackFileX.setFileReference(trackFile);
//					trackFileX.setCompression(compressionType);
//					trackFileX.setFileType(fileType);
//					trackFileX.setName(singleUserTrackFiles.getString("file_ref"));
//					file = trackFileX;
//					orderedFiles.add(file);
//					break;
//				case ZIP:
//					try {
//						ZipFile zipFile = new ZipFile(trackFile);
//						ZipTrackFile trackFileY = new ZipTrackFile(zipFile, zipFile.entries().nextElement());
//						trackFileY.setTrackId(id);
//						trackFileY.setCompression(compressionType);
//						trackFileY.setFileType(fileType);
//						trackFileY.setName(singleUserTrackFiles.getString("file_ref"));
//						orderedFiles.add(trackFileY);
//					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
//						ZipFile zipFile = new ZipFile(trackFile, Charset.forName("ISO_8859_1")); //$NON-NLS-1$
//						ZipTrackFile trackFileY = new ZipTrackFile(zipFile, zipFile.entries().nextElement());
//						trackFileY.setTrackId(id);
//						trackFileY.setCompression(compressionType);
//						trackFileY.setFileType(fileType);
//						trackFileY.setName(singleUserTrackFiles.getString("file_ref"));
//						orderedFiles.add(trackFileY);
//					}
//				default:
//					break;
//				}
//			}
//		}
//
//		orderStatement = connection.createStatement();
//		ResultSet mutliTrackFiles = orderStatement.executeQuery("SELECT track_id, filetype, compression FROM user_tracks " + //$NON-NLS-1$
//				"WHERE (user_name = '" + user + "' OR user_name = '" + sha1Username + "')" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//				"AND upload_state = '" + ProcessingState.PREPROCESSED.ordinal() + "' " + //$NON-NLS-1$ //$NON-NLS-2$
//				"AND containertrack IS NULL " + //$NON-NLS-1$
//				"AND track_id IN (SELECT containertrack FROM user_tracks WHERE containertrack IS NOT NULL)"); //$NON-NLS-1$
//		while (mutliTrackFiles.next()) {
//			long id = mutliTrackFiles.getLong("track_id"); //$NON-NLS-1$
//			String trackFile = baseFileDir + "/" //$NON-NLS-1$
//					+ format.format((id / 100) * 100) + "/" + id + ".dat"; //$NON-NLS-1$ //$NON-NLS-2$
//			String compression = mutliTrackFiles.getString("compression"); //$NON-NLS-1$
//			Statement compressedFilesStatement = connection.createStatement();
//			ResultSet compressedTracks = compressedFilesStatement.executeQuery("SELECT track_id, filetype, compression, file_ref FROM user_tracks " + //$NON-NLS-1$
//					"WHERE (user_name = '" + user + "' OR user_name = '" + sha1Username + "')" + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//					"AND upload_state = '" + ProcessingState.PREPROCESSED.ordinal() + "' " + //$NON-NLS-1$ //$NON-NLS-2$
//					"AND containertrack = '" + id + "' " + //$NON-NLS-1$ //$NON-NLS-2$
//					"ORDER BY track_id"); //$NON-NLS-1$
//			while (compressedTracks.next()) {
//				String uncompressedTrackFile = compressedTracks.getString("file_ref"); //$NON-NLS-1$
//				String mimeType = compressedTracks.getString("filetype"); //$NON-NLS-1$
//				CompressionType compressionType = CompressionType.getCompressionType(compression);
//				switch (compressionType) {
//				case ZIP:
//					try {
//						ZipFile zipFile = new ZipFile(trackFile);
//						ZipTrackFile zippedTrack = new ZipTrackFile(zipFile, zipFile.getEntry(uncompressedTrackFile));
//						zippedTrack.setFileType(mimeType);
//						zippedTrack.setTrackId(compressedTracks.getLong("track_id"));
//						zippedTrack.setName(compressedTracks.getString("file_ref"));
//						orderedFiles.add(zippedTrack);
//					} catch (IllegalArgumentException e) {
//						ZipFile zipFile = new ZipFile(trackFile, Charset.forName("ISO_8859_1")); //$NON-NLS-1$
//						ZipTrackFile zippedTrack = new ZipTrackFile(zipFile, zipFile.getEntry(uncompressedTrackFile));
//						zippedTrack.setFileType(mimeType);
//						zippedTrack.setTrackId(compressedTracks.getLong("track_id"));
//						zippedTrack.setName(compressedTracks.getString("file_ref"));
//						orderedFiles.add(zippedTrack);
//					}
//					break;
//				case GZ:
//					GzipTrackFile gzipTrackFile = new GzipTrackFile();
//					gzipTrackFile.setCompression(compressionType);
//					gzipTrackFile.setFileType(mimeType);
//					gzipTrackFile.setTrackId(compressedTracks.getLong("track_id"));
//					gzipTrackFile.setName(compressedTracks.getString("file_ref"));
//					orderedFiles.add(gzipTrackFile);
//					break;
//				case SEVEN_ZIP:
//					break;
//				case TAR:
//					break;
//				case TARGZ:
//					break;
//				case RAR:
//					break;
//
//				default:
//					break;
//				}
//			}
//
//		}
//
//		// TODO : process multiple files contained in another compressed file
//
//		// orderStatement = connection.createStatement();
//		// ResultSet userCompositeTrackFiles = orderStatement
//		//				.executeQuery("SELECT track_id, filetype, compression FROM user_tracks WHERE user_name = '" + user + "' AND upload_state = '" + ProcessingState.PREPROCESSED.ordinal() + "' AND containertrack IS NULL"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//		return orderedFiles;
//	}
//
//	/**
//	 * updates the database with the discovered content types of the files
//	 * 
//	 * @param connection
//	 * @param basedir
//	 * @param processTrackIds
//	 * @throws SQLException
//	 */
//	public void setContentTypes(Connection connection, String basedir, Set<String> processTrackIds) throws SQLException {
//
//	}



	private static String encryptUser(String password) {
		String sha1 = ""; //$NON-NLS-1$
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1"); //$NON-NLS-1$
			crypt.reset();
			crypt.update(password.getBytes("UTF-8")); //$NON-NLS-1$
			sha1 = byteToHex(crypt.digest());
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

	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy =ReferencePolicy.DYNAMIC, target = "(db=database)")
	public synchronized void bindDepthConnection(Connection connection) {
		this.connection = connection;
	}
	
	public synchronized void unbindDepthConnection(Connection connection) {
		this.connection = null;
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy =ReferencePolicy.DYNAMIC, target = "(db=database)")
	public synchronized void bindFilterController(IFilterController filterController) {
		this.filterController = filterController;
	}
	
	public synchronized void unbindFilterController(IFilterController filterController) {
		this.filterController = null;
	}
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy =ReferencePolicy.DYNAMIC, target = "(db=database)")
	public synchronized void bindGaugeValueUpdater(IGaugeValueUpdater gaugeValueUpdater) {
		this.gaugeValueUpdater = gaugeValueUpdater;
	}
	
	public synchronized void unbindGaugeValueUpdater(IGaugeValueUpdater gaugeValueUpdater) {
		this.gaugeValueUpdater = null;
	}


}
