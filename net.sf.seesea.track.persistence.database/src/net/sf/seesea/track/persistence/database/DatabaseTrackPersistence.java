package net.sf.seesea.track.persistence.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import net.sf.seesea.track.api.IStreamProcessor;
import net.sf.seesea.track.api.ITrackPersistence;
import net.sf.seesea.track.api.data.IContainedTrackFile;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.ProcessingState;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;
import net.sf.seesea.track.model.SimpleTrackFile;

@Component
public class DatabaseTrackPersistence implements ITrackPersistence {

	private Map<IStreamProcessor, Map<String, Object>> streamProcessor2Properties = new ConcurrentHashMap<IStreamProcessor, Map<String, Object>>();
	
	private Connection sourceConnection;

	private Connection outputConnection;

	private String basedir;

	private boolean fullprocess;

	private DecimalFormat format = new DecimalFormat("#####000"); //$NON-NLS-1$
	private DecimalFormat fileFormat = new DecimalFormat("#######0"); //$NON-NLS-1$

	private Set<String> filterTrackIds;

	private Set<String> whitelistUsers;

	private Set<String> blacklistUsers;

	// FIMXE: configured with the connection
	
	public void activate(Map<String, Object> properties) {
		basedir = (String) properties.get("uploaddir");
		fullprocess = "true".equals(properties.get("fullprocess"));
		String[] processTrackIds = (String[]) properties.get("processTrackIds");
		
		whitelistUsers = getValues("whitelistUsers", properties);
		blacklistUsers = getValues("blacklistUsers", properties);
		
		filterTrackIds = new HashSet<String>();
		if(processTrackIds != null) {
			for (String ids : processTrackIds) {
				filterTrackIds.add(ids);
			}
		}

		// blacklist and whitelist users
//		List<Map<String, Object>> filterProperties = getFilterConfigs(_properties);

	}
	
	@Override
	public void resetAnalyzedData() throws TrackPerssitenceException {
		// should we reprocess data marked for reprocessing
		Set<String> reprocessTrackFiles = getTrackFilesToReprocess(sourceConnection);
		if (!reprocessTrackFiles.isEmpty()) {
//			resetAnalyzedData(sourceConnection, reprocessTrackFiles, filterProperties);
		}
		
		// should we reset data for either the processTrackIds or everything ?
		// TODO: erase only whitelist / blacklist users
		if (fullprocess) { //$NON-NLS-1$ //$NON-NLS-2$
//			resetAnalyzedData(sourceConnection, trackIds, filterProperties);
		}
		
	}

	@Override
	public void storePreprocessingStates(Collection<ITrackFile> trackFiles) throws TrackPerssitenceException {
		try (Statement statement = sourceConnection.createStatement();
			PreparedStatement updateTrackFileStatement = sourceConnection.prepareStatement("UPDATE user_tracks SET filetype=?, compression=?, upload_state=? WHERE track_id = ?");
			PreparedStatement setUploadStateStatement = sourceConnection.prepareStatement("UPDATE user_tracks SET upload_state= ? WHERE track_id = ?");
				) {
//			long i = 0;
			for (ITrackFile iTrackFile : trackFiles) {
//				i += 1L;
				net.sf.seesea.track.api.data.ProcessingState processingState = iTrackFile.getUploadState();
				switch(processingState) {
				case PREPROCESSED:
					updateTrackFileStatement.setString(1, iTrackFile.getFileType());
					updateTrackFileStatement.setString(2, iTrackFile.getCompression().getMimeType());
					updateTrackFileStatement.setInt(3, iTrackFile.getUploadState().ordinal());
					updateTrackFileStatement.setLong(4, iTrackFile.getTrackId());
					updateTrackFileStatement.addBatch();
//					if (i % 20 == 0) {
//						updateTrackFileStatement.executeBatch();
//						updateTrackFileStatement = sourceConnection.prepareStatement("UPDATE user_tracks SET filetype=?, compression=?, upload_state=? WHERE track_id = ?"); //$NON-NLS-1$
//					}
					break;
				case FILE_CORRUPT:
				case FILE_CONTENT_UNKNOWN:
				case FILE_NODATA:
					setUploadStateStatement.setInt(1, processingState.ordinal());
					setUploadStateStatement.setLong(2, iTrackFile.getTrackId());
					// execute it right away since we expect this does not happen very often
					setUploadStateStatement.execute();
					break;
				default:
					break;
				}
				if(iTrackFile.getTrackFiles().size() == 1) {
					// use the current track id for information
					updateTrackFileStatement.setString(1, iTrackFile.getFileType());
					updateTrackFileStatement.setString(2, iTrackFile.getCompression().getMimeType());
					updateTrackFileStatement.setInt(3, iTrackFile.getUploadState().ordinal());
					updateTrackFileStatement.setLong(4, iTrackFile.getTrackId());
					updateTrackFileStatement.addBatch();
				} else if(iTrackFile.getTrackFiles().size() > 1) {
					try (PreparedStatement createTrackFileStatement = sourceConnection
							.prepareStatement("INSERT INTO user_tracks (track_id, user_name, file_ref, upload_state,  filetype, compression, containertrack) VALUES(nextval('user_tracks_track_id_seq'), ?, ?, ?, ?, ?, ?)")) {
						for (ITrackFile track : iTrackFile.getTrackFiles()) {
							createTrackFileStatement.setString(1, track.getUsername());
							createTrackFileStatement.setString(2, ((IContainedTrackFile)track).getTrackQualifier());
							createTrackFileStatement.setInt(3, ProcessingState.PREPROCESSED.ordinal());
							createTrackFileStatement.setString(4, track.getFileType());
							createTrackFileStatement.setString(5, track.getCompression().getMimeType());
							createTrackFileStatement.setLong(6, iTrackFile.getTrackId());
							createTrackFileStatement.addBatch();
						}
						if (!iTrackFile.getTrackFiles().isEmpty()) {
							createTrackFileStatement.executeBatch();
						}
					}
					// don't store format type for container track
					updateTrackFileStatement.setString(1, null);
					updateTrackFileStatement.setString(2, iTrackFile.getCompression().getMimeType());
					updateTrackFileStatement.setInt(3, ProcessingState.PREPROCESSED.ordinal());
					updateTrackFileStatement.setLong(4, iTrackFile.getTrackId());
					updateTrackFileStatement.addBatch();
				}
			}
			updateTrackFileStatement.executeBatch();
		} catch (SQLException e) {
			throw new TrackPerssitenceException("Failed to persist track preprocessing states", e);
		}

	}
	
	@Override
	public Map<String, List<ITrackFile>> getUser2PostprocessTrackCluster() {
		// TODO Auto-generated method stub
		return null;
	}

	// FIXME readd whitelist and blacklist users
	@Override
	public List<ITrackFile> getTrackFiles2Process() throws TrackPerssitenceException {
		List<ITrackFile> tracks = new ArrayList<ITrackFile>();
		try(Statement statement = sourceConnection.createStatement(); 
			ResultSet userTrackFiles = statement.executeQuery("SELECT track_id, user_name FROM user_tracks WHERE upload_state = '1' AND filetype IS NULL AND compression is NULL");) {
			while (userTrackFiles.next()) {
				SimpleTrackFile simpleTrackFile = new SimpleTrackFile();
				long id = userTrackFiles.getLong("track_id"); //$NON-NLS-1$
				simpleTrackFile.setTrackId(id);
				
				// FIXME: filter in SQL query
				if (!filterTrackIds.isEmpty() && !filterTrackIds.contains(Long.toString(id))) {
					continue;
				}
				String username = userTrackFiles.getString("user_name"); //$NON-NLS-1$
				simpleTrackFile.setUsername(username);
				String trackFile = MessageFormat.format("{0}/{1}/{2}.dat", basedir, format.format((id / 100) * 100), fileFormat.format(id)); //$NON-NLS-1$
				simpleTrackFile.setFileReference(trackFile);
				tracks.add(simpleTrackFile);
			}
			return tracks;
		} catch (SQLException e) {
			throw new TrackPerssitenceException("Failed to retrieve user tracks", e);
		}
	}

	private Set<String> getTrackFilesToReprocess(Connection connection) throws TrackPerssitenceException {
		Set<String> trackIDs = new HashSet<String>();
		try (Statement getTracksToReprocessStatement = connection.createStatement(); 
				ResultSet resultSet = getTracksToReprocessStatement.executeQuery("SELECT track_id FROM user_tracks WHERE upload_state = '" + ProcessingState.REPROCESS.ordinal() + "'")) {
			while (resultSet.next()) {
				Long trackId = resultSet.getLong(1);
				trackIDs.add(trackId.toString());
			}
		} catch (SQLException e) {
			throw new TrackPerssitenceException("Failed to query tracks to reprocess", e);
		}
		return trackIDs;
	}
	
	/**
	 * cleans the existing database. This may either happen selective given a
	 * certain track id or by cleaning every analyzed piece of data.
	 * 
	 * @param connection
	 * @param processTrackIds
	 * @param filterProperties
	 * @throws SQLException
	 */
	private void resetAnalyzedData(Connection connection, Set<String> processTrackIds, List<Map<String, Object>> filterProperties) throws SQLException {
		
		// FIXME: delete filter data
		
		// a particular set of track ids is to be processed
		if (!processTrackIds.isEmpty()) {
			// Statement statement = connection.createStatement();

			// ResultSet resultSet = statement
			//					.executeQuery("SELECT track_id FROM user_tracks WHERE upload_state != '" + ProcessingState.UPLOAD_INCOMPLETE.ordinal() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			for (String trackIDString : processTrackIds) {
				// while (resultSet.next()) {
				Long trackId = Long.parseLong(trackIDString);
				PreparedStatement deletestatement = null;
				try {
					PreparedStatement containedTracks = connection.prepareStatement("SELECT track_id FROM user_tracks WHERE containertrack = ?");
					containedTracks.setLong(1, trackId);
					ResultSet containedTracksIds = containedTracks.executeQuery();
					while(containedTracksIds.next()) {
						long containedId = containedTracksIds.getLong(1);
						deleteOutputContents4Tracks(filterProperties, containedId);
					}
					// delete derived tracks from zip files
					deletestatement = connection.prepareStatement("DELETE FROM user_tracks WHERE containertrack = ?");
					deletestatement.setLong(1, trackId);
					deletestatement.execute();
					deleteOutputContents4Tracks(filterProperties, trackId);
				} finally {
					deletestatement.close();
				}
				// }

				PreparedStatement updateTrackFileStatement = connection.prepareStatement("UPDATE user_tracks SET filetype=?, compression=?, upload_state=? WHERE track_id=?");
				updateTrackFileStatement.setString(1, null);
				updateTrackFileStatement.setString(2, null);
				updateTrackFileStatement.setInt(3, ProcessingState.UPLOAD_COMPLETE.ordinal());
				updateTrackFileStatement.setLong(4, trackId);
				updateTrackFileStatement.execute();
			}

		} else {
			// reset all track files that have been completed
			PreparedStatement updateTrackFileStatement = connection
					.prepareStatement("UPDATE user_tracks SET filetype=?, compression=?, upload_state=? WHERE upload_state != '" + ProcessingState.UPLOAD_INCOMPLETE.ordinal() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			updateTrackFileStatement.setString(1, null);
			updateTrackFileStatement.setString(2, null);
			updateTrackFileStatement.setInt(3, ProcessingState.UPLOAD_COMPLETE.ordinal());
			updateTrackFileStatement.execute();

			// delete derived tracks from zip files
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM user_tracks WHERE containertrack IS NOT NULL"); //$NON-NLS-1$
			deleteOutputContents4Tracks(filterProperties);
		}
	}

	/**
	 * delete output tables for each configured filter. all content will be
	 * erased
	 * 
	 * @param filterProperties
	 *            the configured filters referencing output databases and tables
	 * @param trackId
	 *            the track id to be reset
	 * @throws SQLException
	 */
	private void deleteOutputContents4Tracks(List<Map<String, Object>> filterProperties) throws SQLException {
		deleteOutputContents4Tracks(filterProperties, null);
	}

	/**
	 * delete output tables for each configured filter
	 * 
	 * @param filterProperties
	 *            the configured filters referencing output databases and tables
	 * @param trackId
	 *            the track id to be reset
	 * @throws SQLException
	 */
	private void deleteOutputContents4Tracks(List<Map<String, Object>> filterProperties, Long trackId) throws SQLException {
		for (Map<String, Object> filterProperty : filterProperties) {
			String outputDatabase = (String) filterProperty.get("outputDatabase"); //$NON-NLS-1$
			if (outputDatabase != null) {
				PreparedStatement deletePostprocessStatement = null;
				try {
					// FIXME: a connection for each filter
//					outputConnection = PostgresConnectionFactory.getDBConnection(_properties, outputDatabase);
					String tablesString = (String) filterProperty.get("outputTable"); //$NON-NLS-1$
					String[] tables = tablesString.split(","); //$NON-NLS-1$
					for (String table : tables) {
						if (trackId == null) {
							deletePostprocessStatement = outputConnection.prepareStatement("DELETE FROM " + table); //$NON-NLS-1$
							deletePostprocessStatement.executeUpdate();
						} else {
							deletePostprocessStatement = outputConnection.prepareStatement("DELETE FROM " + table + " WHERE datasetid = ?"); //$NON-NLS-1$
							deletePostprocessStatement.setLong(1, trackId);
							deletePostprocessStatement.executeUpdate();
						}
					}
				} finally {
					if (deletePostprocessStatement != null) {
						deletePostprocessStatement.close();
					}
					if (outputConnection != null) {
						outputConnection.close();
					}
				}
			}
		}
	}

	private void cleanDeadData(Connection connection) {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.execute("DELETE FROM user_tracks WHERE file_ref IS NULL AND upload_state != 0"); //$NON-NLS-1$
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error("Failed to delete entries with no file reference", e); //$NON-NLS-1$
		}
	}
	
	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindStreamProcessor(IStreamProcessor streamProcessor, Map<String, Object> properties) {
		streamProcessor2Properties.put(streamProcessor, properties);
	}
	
	public void unbindStreamProcessor(IStreamProcessor streamProcessor, Map<String, Object> properties) {
		streamProcessor2Properties.remove(streamProcessor);
	}
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy =ReferencePolicy.DYNAMIC, target = "(db=userData)")
	public synchronized void bindDepthConnection(Connection connection) {
		this.sourceConnection = connection;
	}
	
	public synchronized void unbindDepthConnection(Connection connection) {
		this.sourceConnection = null;
	}
	
	// bind database outputs based on filters

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy =ReferencePolicy.DYNAMIC, target = "(db=filters)")
	public synchronized void bindOutputConnection(Connection connection) {
		this.outputConnection = connection;
	}
	
	public synchronized void unbindOutputConnection(Connection connection) {
		this.outputConnection = null;
	}
	
	private Set<String> getValues(String usertype, Map<String, Object> _properties) {
		String processUsers = (String) _properties.get(usertype);
		Set<String> whitelistUsers = new HashSet<String>();
		if (processUsers != null && !processUsers.isEmpty()) {
			String[] users = processUsers.split(","); //$NON-NLS-1$
			for (String user : users) {
				whitelistUsers.add(user.trim());
			}
		}
		return whitelistUsers;
	}


}
