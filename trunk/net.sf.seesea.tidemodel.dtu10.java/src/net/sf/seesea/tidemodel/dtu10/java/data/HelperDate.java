package net.sf.seesea.tidemodel.dtu10.java.data;

public class HelperDate {
	
	private int year;
	private int month;
	private int day;
	private double hoursPastMidnight;
	
	public HelperDate(int year, int month, int day, double hoursPastMidnight) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hoursPastMidnight = hoursPastMidnight;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public double getHoursPastMidnight() {
		return hoursPastMidnight;
	}

	
	
	
}
