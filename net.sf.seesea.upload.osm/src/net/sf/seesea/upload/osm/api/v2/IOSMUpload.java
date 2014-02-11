package net.sf.seesea.upload.osm.api.v2;

import java.util.List;

import net.sf.seesea.upload.IDataUpload;
import net.sf.seesea.upload.osm.api.v2.messages.License;
import net.sf.seesea.upload.osm.api.v2.messages.VesselConfiguration;

public interface IOSMUpload extends IDataUpload {

	List<VesselConfiguration> getVesselConfigurations();

	List<License> getLicenses();

	boolean isLoggedIn();

}
