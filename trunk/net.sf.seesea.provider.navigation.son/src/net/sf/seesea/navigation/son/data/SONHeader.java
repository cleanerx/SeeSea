/**
 * Copyright (c) 2010-2013, Jens Kübler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.navigation.son.data;

public class SONHeader {

	private int recordNum;
	
	private byte unitType;
	private int globalRecordNum;
	private int timeSinceStart;
	private int mmLong;
	private int mmLat;
	private double longitude;
	private double latitude;
	private short gpsOn;
	private double gpsHeading;
	private double cogSpeed;
	private int depth = -1;
	
	private byte beam;
	private int frequency;
	private int dataLen;
	
	private byte b0;
	private byte b1;
	private byte b2;
	private byte b3;
	private byte b4;
	private int i1;
	private int i2;
	public int getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}
	public byte getUnitType() {
		return unitType;
	}
	public void setUnitType(byte unitType) {
		this.unitType = unitType;
	}
	public int getGlobalRecordNum() {
		return globalRecordNum;
	}
	public void setGlobalRecordNum(int globalRecordNum) {
		this.globalRecordNum = globalRecordNum;
	}
	public int getTimeSinceStart() {
		return timeSinceStart;
	}
	public void setTimeSinceStart(int timeSinceStart) {
		this.timeSinceStart = timeSinceStart;
	}
	public int getMmLong() {
		return mmLong;
	}
	public void setLongitude(int mmLong) {
		this.mmLong = mmLong;
	}
	public int getMmLat() {
		return mmLat;
	}
	public void setMmLat(int mmLat) {
		this.mmLat = mmLat;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public short getGpsOn() {
		return gpsOn;
	}
	public void setGpsOn(short gpsOn) {
		this.gpsOn = gpsOn;
	}
	public double getGpsHeading() {
		return gpsHeading;
	}
	public void setGpsHeading(double gpsHeading) {
		this.gpsHeading = gpsHeading;
	}
	public double getGpsSpeed() {
		return cogSpeed;
	}
	public void setGpsSpeed(double gpsSpeed) {
		this.cogSpeed = gpsSpeed;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public byte getBeam() {
		return beam;
	}
	public void setBeam(byte beam) {
		this.beam = beam;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public int getDataLen() {
		return dataLen;
	}
	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}	
	
	
	
	
}
