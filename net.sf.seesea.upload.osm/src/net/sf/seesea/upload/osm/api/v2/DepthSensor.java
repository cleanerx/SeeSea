package net.sf.seesea.upload.osm.api.v2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DepthSensor extends SBASSensor {
	
	public double frequency;

	public double angleofbeam;

	public double offsetKeel;
}
