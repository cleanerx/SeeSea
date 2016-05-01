/**
 * 
Copyright (c) 2010-2013, Jens Kübler
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
package net.sf.seesea.navigation.son;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;

import net.sf.seesea.navigation.son.data.SONHeader;
import net.sf.seesea.navigation.son.data.SONRoot;
import net.sf.seesea.navigation.son.data.ZippedSonTrack;
import net.sf.seesea.track.api.IStreamProcessor;
import net.sf.seesea.track.api.data.CompressionType;
import net.sf.seesea.track.api.data.ITrack;
import net.sf.seesea.track.api.exception.NMEAProcessingException;

/**
 * This stream procesor takes binary streams from the humminbird son files and analyze the block types
 */
public class SONStreamProcessor implements IStreamProcessor {

	SONProcessingState state;
	
	private int counter;
	
	int[] message = new int[65536 * 4];

	private int dataEnds;

	private SONHeader lastBlock;

	public SONStreamProcessor() { 
		state = SONProcessingState.HEADERSTART;
		counter = 0;
	}
	
	public SONHeader readBlock(InputStream is) throws IOException {
		List<Integer> list = new ArrayList<Integer>();
//		int[] blockInt = new int[1024];
		is.skip(4);
		int read = 0;
		while(((byte)(read = is.read())) != 33) {
			if(read == -1) {
				return null;
			}
			list.add(read);
		}
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size() ; i++) {
			array[i] = list.get(i);
		}
			
		return readBlock(array);
	}
	
	public SONHeader readBlock(int[] block) {
//		System.out.println(block.length);
		if(block.length < 62) {
			return null;
		}
		SONHeader sonHeader = new SONHeader();
		try {
			int unitType = block[3];
//			int headerSize = Integer.MAX_VALUE;
			for(int i = 3 ; i < block.length; i++) {
				      switch ((byte)block[i]) {
				      case -128:
				    	  sonHeader.setGlobalRecordNum(getInt(block, i + 1));
				    	  i+=4;
				    	  break;
				      case -127:
				    	  sonHeader.setTimeSinceStart(getInt(block, i + 1));
				    	  i+=4;
				    	  break;
				      case -126:
				    	  int lon = getInt(block, i + 1);
				    	  sonHeader.setLongitude(positionToEllipsiodDegreeLongitude(lon));
				    	  i+=4;
				    	  break;
				      case -125:
				    	  int lat = getInt(block, i + 1);
				    	  sonHeader.setLatitude(positionToEllipsiodDegreeLatitude(lat));
				    	  i+=4;
				    	  break;
				      case -124:
				    	  sonHeader.setGpsOn(getShort(block, i + 1));
				    	  sonHeader.setGpsHeading(getShort(block, i + 3) / 10.0);
				    	  i+=4;
				        break;
				      case -123:
				    	  sonHeader.setGpsSpeed(getShort(block, i + 3) / 10.0);
				    	  i+=4;
				    	  break;
				      case -122:
				    	  i+=4;
			    	  //			        this.i2 = di.readInt();
				        break;
				      case -121:
				    	  sonHeader.setDepth(getInt(block, i + 1));
				    	  i+=4;
				    	  break;
				      case 80:
				    	  sonHeader.setBeam((byte) block[i + 1]);
				    	  i++;
				    	  break;
				      case 81:
				    	  i++;
//				        this.b0 = di.readByte();
				        break;
				      case -110:
				    	  sonHeader.setFrequency(getInt(block, i + 1));
				    	  i+=4;
				        break;
				      case 83:
//				        this.b1 = di.readByte();
				    	  i++;
				        break;
				      case 84:
//				        this.b2 = di.readByte();
				    	  i++;
				        break;
				      case -107:
//				        this.i1 = di.readInt();
//				    	  i++;
				    	  i+=4;
				        break;
				      case 86:
//				        this.b3 = di.readByte();
				    	  i++;
				        break;
				      case 87:
//				        this.b4 = di.readByte();
				    	  i++;
				        break;
				      case -96:
				    	  sonHeader.setDataLen(getInt(block, i + 1));
				    	  i+=4;
				    	  break;
				      case 33:
//				        headerIncomplete = false;
				    	  i++;
				    	  return sonHeader;
				      default:
//				        throw new RuntimeException("Got unexpected spacer '" + spacer + "'");
				      }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sonHeader;
	}

	
	private int getInt(int[] block, int startIndex) {
		int guid = (block[startIndex + 0] & 0xFF) << 24;
		guid |= (block[startIndex + 1] & 0xFF) << 16;
		guid |= (block[startIndex + 2] & 0xFF) << 8;
		guid |= (block[startIndex + 3] & 0xFF) ;
		return guid;
	}
	
	private short getShort(int[] block, int startIndex) {
		int guid = (block[startIndex + 0] & 0xFF) << 8;
		guid |= (block[startIndex + 1] & 0xFF);
		return (short)guid;
	}
	
	
	public boolean readByte(int c, String providerName) {
		if(SONProcessingState.HEADERSTART.equals(state)) {
			message[counter] = c;
			counter++;
			if(counter == 67) {
				lastBlock = readBlock(message);
				counter = 0;
				return true;
			}
		}
		
		return true;
	}
		
	
	private double positionToEllipsiodDegreeLatitude(double latitude)
	  {
	    if (Math.abs(latitude) < 15433199.0D)
	    {
	      if (latitude != 0.0D)
	      {
	        return Math.atan(Math.tan(Math.atan(Math.exp(latitude / 6378388.0D)) * 2.0D - (Math.PI / 2.0D)) * 1.0067642927D) * 57.295779513082302D;
	      }
	      return 0.0D;
	    }
	    return 0.0D;
	  }

	  public static double positionToEllipsiodDegreeLongitude(double longitude)
	  {
	    if (Math.abs(longitude) <= 20038300.0D)
	    {
	      double d;
	      if ((d = longitude * 57.295779513082302D / 6378388.0D) > 180.0D)
	        d = 180.0D;
	      else if (d < -180.0D)
	        d = -180.0D;
	      return d;
	    }
	    return 0.0D;
	  }

		
	public boolean isValidStreamProcessor(int[] buffer) {
		for (int i : buffer) {
			readByte(i, "none"); //$NON-NLS-1$
		}
		return false;
	}

	@Override
	public void close() throws IOException {
		// nothing to do
	}



	@Override
	public String getMimeType() {
		return "application/x-humminbird"; //$NON-NLS-1$
	}

	public List<ITrack> getTracks(CompressionType compression, ZipFile zipFile) {
		List<ITrack> tracks = new ArrayList<ITrack>(); 
		List<ZipEntry> zipEntries = new ArrayList<ZipEntry>(1);
		zipEntries = getZipEntries(zipFile);
		return getSonFilesFromFile(zipFile, tracks, zipEntries, "ISO-8859-1");
	}

//	@Override
	public List<ITrack> getTracks(CompressionType compressionType, File file) throws ZipException, IOException {
		List<ITrack> tracks = new ArrayList<ITrack>();
		ZipFile zipFile = null;
		switch (compressionType) {
		case ZIP:
			List<ZipEntry> zipEntries = new ArrayList<ZipEntry>(1);
			String encoding = null;
			try {
				zipFile = new ZipFile(file, Charset.forName("UTF-8"));
				zipEntries = getZipEntries(zipFile); //$NON-NLS-1$
				encoding = "UTF-8";
			} catch (IllegalArgumentException e) {
				Logger.getLogger(this.getClass()).error("Failed to open zip entry. May it is not UTF-8 encoded:" + file.getAbsolutePath());
				try {
					zipFile = new ZipFile(file, Charset.forName("ISO-8859-1"));
					zipEntries = getZipEntries(zipFile); //$NON-NLS-1$
					encoding = "ISO-8859-1"; //$NON-NLS-1$
				} catch (IllegalArgumentException e2) {
					Logger.getLogger(this.getClass()).error("Failed to open zip entry. May it is not ISO-8859-1 encoded:" + file.getAbsolutePath());
					return Collections.emptyList();
				}
			}

			return getSonFilesFromFile(zipFile, tracks, zipEntries, encoding);

		default:
			break;
		}
		return tracks;
	}

	private List<ITrack> getSonFilesFromFile(ZipFile file, List<ITrack> tracks,
			List<ZipEntry> zipEntries, String encoding) {
		Map<ZipEntry, Map<ZipEntry,ZipEntry>> sonFiles = new HashMap<ZipEntry, Map<ZipEntry,ZipEntry>>();
		for (ZipEntry zipEntry : zipEntries) {
			// root file
			if(zipEntry.getName().toLowerCase().endsWith(".dat")) {
				Map<String,ZipEntry> nameMap = new HashMap<String, ZipEntry>();
				String directory = zipEntry.getName().substring(0, zipEntry.getName().length() - 4) + "/"; //$NON-NLS-1$
				Map<ZipEntry,ZipEntry> index2Dat = new HashMap<ZipEntry,ZipEntry>();
				for (ZipEntry zipEntry2 : zipEntries) {
					if(zipEntry2.getName().startsWith(directory) && zipEntry2.getName().toLowerCase().endsWith(".idx")) {
						String indexFile = zipEntry2.getName().substring(zipEntry2.getName().lastIndexOf("/") + 1, zipEntry2.getName().length() - 4); //$NON-NLS-1$
						if(nameMap.get(indexFile) == null) {
							nameMap.put(indexFile, zipEntry2);
						} else {
							index2Dat.put(zipEntry2, nameMap.get(indexFile));
						}
					} else if(zipEntry2.getName().startsWith(directory) && (zipEntry2.getName().toLowerCase().endsWith(".son") || zipEntry2.getName().toLowerCase().endsWith(".dri"))) {
						String indexFile = zipEntry2.getName().substring(zipEntry2.getName().lastIndexOf("/") + 1, zipEntry2.getName().length() - 4); //$NON-NLS-1$
						if(nameMap.get(indexFile) == null) {
							nameMap.put(indexFile, zipEntry2);
						} else {
							// TODO check son integrity
							index2Dat.put(nameMap.get(indexFile), zipEntry2);
						}
					}
				}	
				sonFiles.put(zipEntry, index2Dat);
			}
				
		}
		if(!sonFiles.values().isEmpty()) {
			for (Entry<ZipEntry, Map<ZipEntry, ZipEntry>> zipEntry : sonFiles.entrySet()) {
				if(!zipEntry.getValue().isEmpty()) {
//					tracks.add(new ZippedSonTrack(file, zipEntry.getKey(), zipEntry.getValue(), encoding));
				}
			}
		}
		return tracks;
	}

private List<ZipEntry> getZipEntries(ZipFile zipFile) {
	List<ZipEntry> zipEntries = new ArrayList<ZipEntry>();
	Enumeration<? extends ZipEntry> entries = zipFile.entries();
	while(entries.hasMoreElements()) {
		ZipEntry nextElement = entries.nextElement();
		if(!nextElement.isDirectory()) {
			zipEntries.add(nextElement);
		}
	}
	return zipEntries;
}

	@Override
	public boolean isBinary() {
		return true;
	}
	
	public List<SONHeader> readSON(InputStream inputStream, Map<Integer,Integer> index2Pointer) throws IOException {
		List<SONHeader> sonHeaders = new ArrayList<SONHeader>();
		inputStream.mark(2048);
		for (Entry<Integer, Integer> entry : index2Pointer.entrySet()) {
			inputStream.skip(entry.getValue());
			int[] header = new int[67];
			for(int i = 0; i < 67 ; i ++) {
				header[i] = inputStream.read();
			}
			SONHeader sonHeader = readBlock(header);
			sonHeaders.add(sonHeader);
		}
		return sonHeaders;
	}

	public SONRoot readDat(InputStream inputStream) throws IOException {
	      DataInputStream dis = new DataInputStream(inputStream);
	      if(dis.available() == 64) {
	    	  byte[] headerStart = new byte[20];
	    	  dis.read(headerStart);
	    	  int recordingStart = dis.readInt();
	    	  double mmLongitude = dis.readInt();
	    	  double degLongitude = mm2EllipsiodDegLongitude(mmLongitude);
	    	  
	    	  double mmLatitude = dis.readInt();
	    	  double degLatitude = mm2EllipsiodDegLatitude(mmLatitude);
	    	  
	    	  byte[] name = new byte[6];
	    	  dis.read(name);
	    	  String trackName = new String(name);
	    	  byte[] ext = new byte[4];
	    	  dis.read(ext);
	    	  String trackExtension = new String(ext);
	    	  return new SONRoot(new Date(1000L * recordingStart), degLatitude, degLongitude, trackName, trackExtension);
	      }
	      return null;
	}
	
	public Map<Integer, Integer> readIndex(InputStream inputStream) throws IOException {
		BufferedInputStream input = new BufferedInputStream(inputStream);
		byte[] buf = new byte[8];
		int index;
		int pointer;
		Map<Integer,Integer> index2Pointer = new TreeMap<Integer, Integer>();
		int i;
		while((i = input.read(buf)) != -1) {
			index = (buf[0] & 0xFF) << 24;
			index |= (buf[1] & 0xFF) << 16;
			index |= (buf[2] & 0xFF) << 8;
			index |= (buf[3] & 0xFF) ;

			pointer = (buf[4] & 0xFF) << 24;
			pointer |= (buf[5] & 0xFF) << 16;
			pointer |= (buf[6] & 0xFF) << 8;
			pointer |= (buf[7] & 0xFF) ;
			index2Pointer.put(index, pointer);
		}
		return index2Pointer;
	}
	
	
	private double mm2EllipsiodDegLongitude(double mmlongitude)
	  {
	    if (Math.abs(mmlongitude) <= 20038300.0)
	    {
	      double d;
	      if ((d = mmlongitude * 57.295779513082302 / 6378388.0) > 180.0)
	        d = 180.0;
	      else if (d < -180.0)
	        d = -180.0;
	      return d;
	    }
	    return 0.0;
	  }
	
	private double mm2EllipsiodDegLatitude(double mmLatitude)
	  {
	    if (Math.abs(mmLatitude) < 15433199.0)
	    {
	      if (mmLatitude != 0.0D)
	      {
	        return Math.atan(Math.tan(Math.atan(Math.exp(mmLatitude / 6378388.0)) * 2.0 - (Math.PI / 2)) * 1.0067642927D) * 57.295779513082302;
	      }
	      return 0.0D;
	    }
	    return 0.0D;
	  }


	
}
