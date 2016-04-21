package net.sf.seesea.data.io;

import java.sql.SQLException;
import java.util.Set;

import net.sf.seesea.geometry.IIdentifiablePolygon;
import net.sf.seesea.model.core.geo.GeoBoundingBox;

public interface IParitionRetrival {

	void getHitPartitionizedPolygons(Set<Long> trackIds, String trackpointTable, Set<String> inshoreOSMids,
			Set<String> offshoreIds) throws SQLException;

	void getHitPartitionizedPolygons(GeoBoundingBox boundingBox, Set<String> inshoreOSMids, Set<String> offshoreIds)
			throws SQLException;

	IIdentifiablePolygon getHitPartitionizedPolygon(Set<String> inshoreOSMids, Set<String> offshoreIds, double lat, double lon)
			throws SQLException;

}
