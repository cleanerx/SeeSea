package net.sf.seesea.geometry.impl;

import java.util.List;

import net.sf.seesea.geometry.IIdentifiablePolygon;
import net.sf.seesea.geometry.IPoint;

public class IdentifiablePolygon extends Polygon implements IIdentifiablePolygon {

	
	public IdentifiablePolygon() {
		super();
	}

	public IdentifiablePolygon(IPoint... points) {
		super(points);
	}

	public IdentifiablePolygon(long id, String label, List<IPoint> points) {
		super(points);
		this.id = id;
		this.label = label;
	}


	private long id;
	private String label;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getLabel() {
		return label;
	}

}
