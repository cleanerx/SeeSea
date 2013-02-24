package net.sf.seesea.rendering.chart.editor;

import java.util.List;

import net.sf.seesea.model.core.geo.GeoPosition;

public class AreaMarker {

	private long id;
	
	private List<GeoPosition> areaBounds;
	
	private String name;
	
	private String url;

	public AreaMarker(long id, List<GeoPosition> areaBounds, String name, String url) {
		super();
		this.areaBounds = areaBounds;
		this.name = name;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public List<GeoPosition> getAreaBounds() {
		return areaBounds;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}
	
	
	
}
