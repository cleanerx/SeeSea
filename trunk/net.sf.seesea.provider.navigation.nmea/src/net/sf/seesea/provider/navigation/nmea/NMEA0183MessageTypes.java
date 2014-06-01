/**
 * 
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
package net.sf.seesea.provider.navigation.nmea;

/**
 * a class that contains all know NMEA strings.
 * 
 * Vendor specifcs not yet supported
 * 
 */
public enum NMEA0183MessageTypes {

	AAM,
	ACC,
	ALM, 
	APA, 
	APB, 
	ASD, 
	BEC, 
	BOD, 
	BWC, 
	BWR, 
	BWW, 
	DBK, 
	DBS,
	/** Depth below transducer */
	DBT, 
	DCN, 
	DPT, 
	DTM, 
	FSI, 
	GBS, 
	GGA, 
	GLC, 
	GLL, 
	GNS, 
	GRS,
	GSA, 
	GST, 
	GSV, 
	GTD, 
	GXA,
	HDA,
	HDG, 
	HDM, 
	HDO, 
	HDT, 
	HSC, 
	HVM, 
	LCD,
	LOR,
	MSK, 
	MTW, // ("Water Temperature")
	MWV, 
	OLN, 
	OSD, 
	ROO, 
	RMA, 
	RMB, 
	RMC, 
	ROT, 
	RPM, 
	RSA, 
	RSD, 
	RTE, 
	SFI,
	SMACC,
	SMGYR,
	SMVCC,
	SMST,
	SMSO,
	STN, 
	TLL, 
	TRF, 
	TTM, 
	VBW, 
	VDR, 
	VHW, 
	VLW, 
	VPW, 
	VTG, 
	VWR, 
	VWT, 
	WCV, 
	WNC, 
	WPL, 
	XDR, 
	XTE, 
	XTR, 
	ZDA, 
	ZFO, 
	ZTG, 
	MTA, 
	MWD, 
	RME, 
	RMZ,
	RMM

}
