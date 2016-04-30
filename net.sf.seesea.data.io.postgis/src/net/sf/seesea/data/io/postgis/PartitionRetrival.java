package net.sf.seesea.data.io.postgis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import net.sf.seesea.data.io.IParitionRetrival;
import net.sf.seesea.geometry.IIdentifiablePolygon;
import net.sf.seesea.geometry.impl.IdentifiablePolygon;
import net.sf.seesea.model.core.geo.GeoBoundingBox;

@Component
public class PartitionRetrival implements IParitionRetrival {

	public void getHitPartitionizedPolygons(Set<Long> trackIds, String trackpointTable, Set<String> inshoreOSMids,
			Set<String> offshoreIds) throws SQLException {
		for (Long trackId : trackIds) {
			// the bounding box of the points for the track
			try (PreparedStatement boundaryStatement = triangulationConnection.get().prepareStatement(
					"SELECT st_xmin(ST_Extent(the_geom)), st_xmax(ST_Extent(the_geom)), st_ymin(ST_Extent(the_geom)), st_ymax(ST_Extent(the_geom)) FROM "
							+ trackpointTable + " WHERE datasetid = ?")) {
				boundaryStatement.setLong(1, trackId);
				try (ResultSet query = boundaryStatement.executeQuery()) {
					if (query.next()) {
						double xmin = query.getDouble(1);
						double xmax = query.getDouble(2);
						double ymin = query.getDouble(3);
						double ymax = query.getDouble(4);
						if (xmin == 0.0 && xmax == 0.0 || ymin == 0.0 && ymax == 0.0) {
							Logger.getLogger(getClass())
									.info("Skipping track id because no bounding box exists : " + trackId);
							continue;
						}

						getHitPartitionizedPolygons(inshoreOSMids, offshoreIds, trackId, xmin, xmax, ymin, ymax);

						// ResultSet offshorePartionizedPolygonResultSet =
						// offshoreStatement.executeQuery("SELECT path,
						// ST_X(geom) AS lon, ST_Y(geom) as lat, gid FROM
						// (SELECT (ST_DUMPPOINTS(geom)).*, gid FROM
						// gebco_poly_100 WHERE ST_Overlaps(geom," + envelope +
						// ") OR ST_Contains(" + envelope +", geom) OR
						// ST_Contains(geom," + envelope +") ) AS g");
					}
				}
			}
		}
	}

	public void getHitPartitionizedPolygons(GeoBoundingBox boundingBox, Set<String> inshoreOSMids,
			Set<String> offshoreIds) throws SQLException {
		getHitPartitionizedPolygons(inshoreOSMids, offshoreIds, 0L, boundingBox.getLeft(), boundingBox.getRight(),
				boundingBox.getBottom(), boundingBox.getTop());
	}

	public void getHitPartitionizedPolygons(Set<String> inshoreOSMids, Set<String> offshoreIds, Long trackId,
			double xmin, double xmax, double ymin, double ymax) throws SQLException {
		// select all polygons of that bounding box
		try (Statement inshoreStatement = osmConnectionReference.get().createStatement();
				Statement offshoreStatement = triangulationConnection.get().createStatement()) {
			// formatter:off
			NumberFormat format = DecimalFormat.getNumberInstance(Locale.ENGLISH);
			format.setMaximumFractionDigits(Integer.MAX_VALUE);
			String envelope = MessageFormat.format("ST_MakeEnvelope({0}, {1}, {2}, {3}, 4326)", format.format(xmin),
					format.format(ymin), format.format(xmax), format.format(ymax));
			try (ResultSet inshorePartionizedPolygonResultSet = inshoreStatement.executeQuery(
					"SELECT osm_id FROM " + "(SELECT * FROM (" + "SELECT osm_id FROM planet_osm_polygon AS poly WHERE "
							+ "(ST_Overlaps(way, ST_Transform(" + envelope + ", 900913)) OR ST_Contains(ST_Transform("
							+ envelope + ", 900913), way) OR ST_Contains(way, ST_Transform(" + envelope
							+ ", 900913)))  AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  "
							+ ") as g ")) {
				while (inshorePartionizedPolygonResultSet.next()) {
					inshoreOSMids.add(inshorePartionizedPolygonResultSet.getString(1));
				}
			}
			try (ResultSet offshorePartionizedPolygonResultSet = offshoreStatement
					.executeQuery("SELECT gid FROM (SELECT gid FROM gebco_poly_100 WHERE ST_Overlaps(geom," + envelope
							+ ") OR ST_Contains(" + envelope + ", geom) OR ST_Contains(geom," + envelope
							+ ") ) AS g")) {
				while (offshorePartionizedPolygonResultSet.next()) {
					offshoreIds.add(offshorePartionizedPolygonResultSet.getString(1));
				}
				Logger.getLogger(getClass()).info((inshoreOSMids.size() + offshoreIds.size())
						+ " areas are in the given bounding box for track id " + trackId);
			}
			// formatter:on
		}
	}

	public IIdentifiablePolygon getHitPartitionizedPolygon(Set<String> inshoreOSMids, Set<String> offshoreIds,
			double lat, double lon) throws SQLException {
		// select all polygons of that bounding box
		try (Statement inshoreStatement = osmConnectionReference.get().createStatement();
				Statement offshoreStatement = triangulationConnection.get().createStatement()) {
			// formatter:off
			NumberFormat format = DecimalFormat.getNumberInstance(Locale.ENGLISH);
			format.setMaximumFractionDigits(Integer.MAX_VALUE);
			String envelope = MessageFormat.format("ST_GEOMFROM_TEXT('POINT ({0} {1})', 4326)", format.format(lon),
					format.format(lat));
			try (ResultSet inshorePartionizedPolygonResultSet = inshoreStatement.executeQuery(
					"SELECT osm_id FROM " + "(SELECT * FROM (" + "SELECT osm_id FROM planet_osm_polygon AS poly WHERE "
							+ "(ST_Contains(way, ST_Transform(" + envelope
							+ ", 900913)))  AND (\"natural\" = 'water' OR waterway IS NOT NULL OR water IS NOT NULL) ) AS xx  "
							+ ") as g ")) {
				while (inshorePartionizedPolygonResultSet.next()) {
					inshoreOSMids.add(inshorePartionizedPolygonResultSet.getString(1));
				}
			}
			try (ResultSet offshorePartionizedPolygonResultSet = offshoreStatement
					.executeQuery("SELECT gid FROM (SELECT gid FROM gebco_poly_100 WHERE ST_Overlaps(geom," + envelope
							+ ") OR ST_Contains(" + envelope + ", geom) OR ST_Contains(geom," + envelope
							+ ") ) AS g")) {
				while (offshorePartionizedPolygonResultSet.next()) {
					offshoreIds.add(offshorePartionizedPolygonResultSet.getString(1));
				}
			}
			// formatter:on
		}
		// FIXME: add a real polygon
		return new IdentifiablePolygon();
	}

	@Reference(policy = ReferencePolicy.DYNAMIC, target = "(db=userData)", cardinality = ReferenceCardinality.MANDATORY)
	public synchronized void bindUserDataConnection(Connection connection) {
		userDataConnectionReference.set(connection);
	}

	public synchronized void unbindUserDataConnection(Connection connection) {
		userDataConnectionReference.compareAndSet(connection, null);
	}

	private AtomicReference<Connection> userDataConnectionReference = new AtomicReference<Connection>();

	private AtomicReference<Connection> triangulationConnection = new AtomicReference<Connection>();

	private AtomicReference<Connection> osmConnectionReference = new AtomicReference<Connection>();

	@Reference(policy = ReferencePolicy.DYNAMIC, target = "(db=coastline)", cardinality = ReferenceCardinality.MANDATORY)
	public synchronized void bindGaugeConnection(Connection connection) {
		triangulationConnection.set(connection);
	}

	public synchronized void unbindGaugeConnection(Connection connection) {
		triangulationConnection.compareAndSet(connection, null);
	}

	@Reference(policy = ReferencePolicy.DYNAMIC, target = "(db=osm)", cardinality = ReferenceCardinality.MANDATORY)
	public synchronized void bindOSMConnection(Connection connection) {
		osmConnectionReference.set(connection);
	}

	public synchronized void unbindOSMConnection(Connection connection) {
		osmConnectionReference.compareAndSet(connection, null);
	}

}
