package net.sf.seesea.tidemodel.dtu10.java;

import java.util.Date;

public class LATTideCorrectionCache {
	
	private double lat;
	
	private double lon;
	
	private Date date;
	
	private double latCorrection;

	public LATTideCorrectionCache(double lat, double lon, Date date) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.date = date;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getLatCorrection() {
		return latCorrection;
	}

	public void setLatCorrection(double latCorrection) {
		this.latCorrection = latCorrection;
	}
	
	boolean isQueryClose(LATTideCorrectionCache latTidePredictionCache, double latDistance, double lonDistance, long timeDistance) {
		if(Math.abs(latTidePredictionCache.getLat() - getLat()) < latDistance && Math.abs(latTidePredictionCache.getLon() - getLon()) < lonDistance && Math.abs(latTidePredictionCache.getDate().getTime() - getDate().getTime()) < timeDistance) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "LATTideCorrectionCache [lat=" + lat + ", lon=" + lon
				+ ", date=" + date + ", latCorrection=" + latCorrection + "]";
	}
	
	

}
