/**
Copyright (c) 2010-2012, Jens K�bler
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

package net.sf.seesea.model.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;

import net.sf.seesea.model.core.geo.Coordinate;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;

public class GeoPositionFormatter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210772739329209226L;
	private static GeoPositionFormatter _geoPositionFormatter;

	/**
	 * @param pattern 
	 */
	public GeoPositionFormatter() {
	}
	
	public static GeoPositionFormatter getInstance() {
		if(_geoPositionFormatter == null) {
			_geoPositionFormatter = new GeoPositionFormatter();
		}
		return _geoPositionFormatter;
	}

	/**
	 * 
	 * @param position
	 * @param buffer
	 * @param field
	 * @param format
	 * @return
	 */
	public StringBuffer format(GeoPosition position, StringBuffer buffer, FieldPosition field, GeoPositionFormat format) {
		Latitude latitude = position.getLatitude();
		Longitude longitude = position.getLongitude();
		switch (format) {
		case DECIMALDEGREES:
			formatCoordinateFullDecimal(buffer, latitude);
			if(latitude.getDegree() >= 0) {
				buffer.append("N "); // TODO This is locale depenedent //$NON-NLS-1$
			} else {
				buffer.append("S "); // TODO This is locale depenedent //$NON-NLS-1$
			}
			
			formatCoordinateFullDecimal(buffer, longitude);
			if(longitude.getDegree() < 0) {
				buffer.append("W"); // TODO This is locale depenedent //$NON-NLS-1$
			} else {
				buffer.append("E"); // TODO This is locale depenedent //$NON-NLS-1$
			}
			break;
		case DECIMALMINUTES:
			formatCoordinateMinutes(buffer, latitude);
			if(latitude.getDegree() >= 0) {
				buffer.append("N "); // TODO This is locale depenedent //$NON-NLS-1$
			} else {
				buffer.append("S "); // TODO This is locale depenedent //$NON-NLS-1$
			}
			
			formatCoordinateMinutes(buffer, longitude);
			if(longitude.getDegree() < 0) {
				buffer.append("W"); // TODO This is locale depenedent //$NON-NLS-1$
			} else {
				buffer.append("E"); // TODO This is locale depenedent //$NON-NLS-1$
			}
			break;
		case DECIMALSECONDS:
			formatCoordinateSeconds(buffer, latitude);
			if(latitude.getDegree() >= 0) {
				buffer.append("N "); // TODO This is locale depenedent //$NON-NLS-1$
			} else {
				buffer.append("S "); // TODO This is locale depenedent //$NON-NLS-1$
			}
			
			formatCoordinateSeconds(buffer, longitude);
			if(longitude.getDegree() < 0) {
				buffer.append("W"); // TODO This is locale depenedent //$NON-NLS-1$
			} else {
				buffer.append("E"); // TODO This is locale depenedent //$NON-NLS-1$
			}
			break;
		}
		return buffer;
	}

	public static void formatCoordinateFullDecimal(StringBuffer buffer, Coordinate coordinate) {
		double decimalminutes = coordinate.getMinute() + ((coordinate.getSecond()) / 60);
		double decimalDegrees = coordinate.getDegree() + (decimalminutes / 60);
		DecimalFormat format;
		if(coordinate instanceof Latitude) {
			format = new DecimalFormat("00.00000"); //$NON-NLS-1$
		} else {
			format = new DecimalFormat("000.00000"); //$NON-NLS-1$
		}
		buffer.append(format.format(Math.abs(decimalDegrees)));
	}

	public static void formatCoordinateMinutesWithSphere(StringBuffer buffer, Coordinate coordinate) {
		formatCoordinateMinutes(buffer, coordinate);
		if(coordinate instanceof Latitude) {
			if(coordinate.getDegree() < 0) {
				buffer.append(Messages.getString("GeoPositionFormatter.shortSouth")); //$NON-NLS-1$
			} else {
				buffer.append(Messages.getString("GeoPositionFormatter.shortNorth")); //$NON-NLS-1$
			}
		} else {
			if(coordinate.getDegree() < 0) {
				buffer.append(Messages.getString("GeoPositionFormatter.shortWest")); //$NON-NLS-1$
			} else {
				buffer.append(Messages.getString("GeoPositionFormatter.shortEast")); //$NON-NLS-1$
			}
		}
	}

	
	public static void formatCoordinateMinutes(StringBuffer buffer, Coordinate coordinate) {
		double decimalminutes = coordinate.getMinute() + ((coordinate.getSecond()) / 60);
		DecimalFormat format;
		if(coordinate instanceof Latitude) {
			format = new DecimalFormat("00"); //$NON-NLS-1$
		} else {
			format = new DecimalFormat("000"); //$NON-NLS-1$
		}
		buffer.append(format.format(Math.abs(coordinate.getDegree())));
		buffer.append("\u00B0"); //$NON-NLS-1$

		DecimalFormat minuteFormat = new DecimalFormat("00.0"); //$NON-NLS-1$
		buffer.append(minuteFormat.format(decimalminutes));
		buffer.append("'"); //$NON-NLS-1$
	}

	public static void formatCoordinateSeconds(StringBuffer buffer, Coordinate coordinate) {
		DecimalFormat format;
		if(coordinate instanceof Latitude) {
			format = new DecimalFormat("00"); //$NON-NLS-1$
		} else {
			format = new DecimalFormat("000"); //$NON-NLS-1$
		}
		buffer.append(format.format(Math.abs(coordinate.getDegree())));
		buffer.append("°"); //$NON-NLS-1$
		buffer.append(coordinate.getMinute());
		buffer.append("'"); //$NON-NLS-1$
		DecimalFormat secondFormat = new DecimalFormat("00.0"); //$NON-NLS-1$
		buffer.append(secondFormat.format(coordinate.getSecond()));
		buffer.append('"');
	}

	/**
	 * 
	 * @param position
	 * @return
	 */
	public String format(GeoPosition position) {
		return format(position, new StringBuffer(), new FieldPosition(0), GeoPositionFormat.DECIMALMINUTES).toString();
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public String format(GeoPosition position, GeoPositionFormat format) {
		return format(position, new StringBuffer(), new FieldPosition(0), format).toString();
	}


	public enum GeoPositionFormat {
		DECIMALDEGREES,
		DECIMALMINUTES,
		DECIMALSECONDS;
	}


	/**
	 * @param stringPosition
	 * @return
	 */
	public static GeoPosition parse(String stringPosition) {
		try {
			String numericalPattern = "^([+-]?([0-8][0-9](\\.[0-9]+)?|[9][0](\\.[0]+)?)) ([+-]?(([01][0-7]|[00][0-9])[0-9](\\.[0-9]+)?|[1][8][0](\\.[0]+)?))$"; //$NON-NLS-1$
			String degreeNumericalMatch = "^(([0-8][0-9](\\.([0-9]+))?|[9][0](\\.([0-9]+))?)° [NS] (([01][0-7]|[00][0-9])[0-9](\\.([0-9]+))?|[1][8][0](\\.([0-9]+))?))° [EW]$"; //$NON-NLS-1$
			String minuteNumericalMatch = "^([0-8][0-9]|[9][0])° ([0-5][0-9](\\.([0-9]+))?[´']) [NS] (([01][0-7]|[00][0-9])[0-9]|[1][8][0])° ([0-5][0-9](\\.([0-9]+))?[´']) [EW]$"; //$NON-NLS-1$
			
			if(stringPosition.matches(numericalPattern)) {
				String[] split = stringPosition.split(" "); //$NON-NLS-1$
				GeoFactory geoFactory = GeoFactory.eINSTANCE;
				GeoPosition geoPosition = geoFactory.createGeoPosition();
				Latitude latitude = geoFactory.createLatitude();
				geoPosition.setLatitude(latitude);
				parseCoordinateNumerical(latitude, split[0]);
				Longitude longitude = geoFactory.createLongitude();
				geoPosition.setLongitude(longitude);
				parseCoordinateNumerical(longitude, split[1]);
				return geoPosition;
			} else if(stringPosition.matches(degreeNumericalMatch)) {
				int latmultiplier = 1;
				int splitIndex = stringPosition.indexOf('N');
				if( splitIndex == -1) {
					splitIndex = stringPosition.indexOf('S');
					latmultiplier = -1;
				}
				int lonmultiplier = 1;
				if(stringPosition.indexOf('W') != -1) {
					lonmultiplier = -1;
				}
				GeoFactory geoFactory = GeoFactory.eINSTANCE;
				GeoPosition geoPosition = geoFactory.createGeoPosition();
				Latitude latitude = geoFactory.createLatitude();
				geoPosition.setLatitude(latitude);
				parseCoordinateNumerical(latitude, stringPosition.substring(0, stringPosition.indexOf('\u00B0')));
				latitude.setDegree(latitude.getDegree() * latmultiplier);
				
				Longitude longitude = geoFactory.createLongitude();
				geoPosition.setLongitude(longitude);
				parseCoordinateNumerical(longitude, stringPosition.substring(splitIndex + 1, stringPosition.lastIndexOf('\u00B0')));
				longitude.setDegree(longitude.getDegree() * lonmultiplier);
				return geoPosition;
			} else if(stringPosition.matches(minuteNumericalMatch)) {
				int latmultiplier = 1;
				int splitIndex = stringPosition.indexOf('N');
				if( splitIndex == -1) {
					splitIndex = stringPosition.indexOf('S');
					latmultiplier = -1;
				}
				int lonmultiplier = 1;
				if(stringPosition.indexOf('W') != -1) {
					lonmultiplier = -1;
				}
				GeoFactory geoFactory = GeoFactory.eINSTANCE;
				GeoPosition geoPosition = geoFactory.createGeoPosition();
				Latitude latitude = geoFactory.createLatitude();
				geoPosition.setLatitude(latitude);
				int latitudeDegreePosition = stringPosition.indexOf('\u00B0');
				int latitudeMinutePosition = stringPosition.indexOf("'"); //$NON-NLS-1$
				if(latitudeMinutePosition == -1) {
					latitudeMinutePosition = stringPosition.indexOf('\u00B0');
				}
				int degree = Integer.parseInt(stringPosition.substring(0,latitudeDegreePosition));
				latitude.setDegree(degree * latmultiplier);
				double doubleMinutes = Double.parseDouble(stringPosition.substring(latitudeDegreePosition + 1, latitudeMinutePosition));
				latitude.setMinute((int) (Math.floor(doubleMinutes)));
				doubleMinutes = (doubleMinutes - latitude.getMinute()) * 60;
				latitude.setSecond(doubleMinutes);
				
				Longitude longitude = geoFactory.createLongitude();
				geoPosition.setLongitude(longitude);
				int longitudeDegreePosition = stringPosition.lastIndexOf('\u00B0');
				int longitudeMinutePosition = stringPosition.lastIndexOf("'"); //$NON-NLS-1$
				if(longitudeMinutePosition == -1) {
					longitudeMinutePosition = stringPosition.lastIndexOf("\u00B0"); //$NON-NLS-1$
				}
				degree = Integer.parseInt(stringPosition.substring(splitIndex + 2,longitudeDegreePosition));
				longitude.setDegree(degree * lonmultiplier);
				doubleMinutes = Double.parseDouble(stringPosition.substring(longitudeDegreePosition + 1, longitudeMinutePosition));
				longitude.setMinute((int) (Math.floor(doubleMinutes)));
				doubleMinutes = (doubleMinutes - longitude.getMinute()) * 60;
				longitude.setSecond(doubleMinutes);
				
				return geoPosition;
			}
			
//			Pattern pattern = Pattern.compile("(([0-8][0-9]|[9][0])° ([0-5][0-9]\\.([0-9]+)[´'] |([0-5][0-9][´']( [0-5][0-9](\\.([0-9])+)\")? ))?[NS] (([01][0-7]|[00][0-9])[0-9]|[1][8][0])°( [0-5][0-9]\\.[0-9]´)? [EW]"); //$NON-NLS-1$
//			Matcher matcher = pattern.matcher(stringPosition);
//			if(!matcher.matches()) {
//				return null;
//			}
//			
//			stringPosition.matches("(([0-8][0-9]|[9][0])° ([0-5][0-9]\\.([0-9]+)[´'] |([0-5][0-9][´']( [0-5][0-9](\\.([0-9])+)\")? ))?[NS] (([01][0-7]|[00][0-9])[0-9]|[1][8][0])°( [0-5][0-9]\\.[0-9]´)? [EW]");
//			
//			double parseDouble = Double.parseDouble(stringPosition);
			
		} catch (NumberFormatException e) {
			
		}
		
		return null;
	}
	
	/**
	 * @param string
	 * @param string2
	 * @return
	 */
	private static Latitude parseLatitudeNumerical(String input, String pattern) {
		return null;
	}

	public static void parseCoordinateNumerical(Coordinate coordinate, String longitudeString) {
//		Longitude longitude = GeoFactory.eINSTANCE.createLongitude();
		
		double decDegree = Double.parseDouble(longitudeString);
		coordinate.setDecimalDegree(decDegree);
//		coordinate.setDegree((int) (Math.floor(Math.abs(decDegree)) * Math.signum(decDegree)));
//		decDegree = (decDegree - coordinate.getDegree()) * 60;
//		decDegree = Math.abs(decDegree);
//		coordinate.setMinute((int) (Math.floor(decDegree)));
//		decDegree = (decDegree - coordinate.getMinute()) * 60;
//		coordinate.setSecond(decDegree);
//		return parseLongitude(Double.parseDouble(longitude));
	}

	
}
