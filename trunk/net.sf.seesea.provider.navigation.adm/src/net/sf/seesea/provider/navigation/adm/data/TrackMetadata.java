package net.sf.seesea.provider.navigation.adm.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackMetadata {

	private String trackName;
	private short headerMetadata;
	private short headerMetadata1;
	private int headerMetadata2;
	private int headerMetadata3;
	private int headerMetadata4;
	private int dataBegin;
	private int trackppointCount;

	
	public TrackMetadata(int[] contents, List<MetadataDescription> headerDataDescriptions) {
		int startByte = 0;
		for (MetadataDescription metadataDescription : headerDataDescriptions) {
			if(metadataDescription.getId() == 300) {
				trackName = getString(contents, startByte, startByte + metadataDescription.getSize());
			} else if(metadataDescription.getId() == 301) {
				trackppointCount = getUnsignedShort(contents, startByte);
			} else if(metadataDescription.getId() == 302) {
				headerMetadata2 = contents[startByte];
			} else if(metadataDescription.getId() == 303) {
				headerMetadata3 = contents[startByte];
			} else if(metadataDescription.getId() == 304) {
				dataBegin = getInt(contents, startByte);
			}

			startByte+=metadataDescription.getSize();
		}
	}
	
	
		
		public String getTrackName() {
		return trackName;
	}



	public int getTrackppointCount() {
		return trackppointCount;
	}

	private int getUnsignedShort(int[] data, int start) {
 		ByteBuffer allocate = ByteBuffer.allocate(4);
 		allocate.put((byte) data[start]);
 		allocate.put((byte) data[start + 1]);
 		allocate.put((byte)0);
 		allocate.put((byte)0);
 		allocate.order(ByteOrder.LITTLE_ENDIAN);
 		allocate.flip();
		return allocate.getInt();
	}


		private short getShort(int[] data, int start) {
	 		ByteBuffer allocate = ByteBuffer.allocate(2);
	 		allocate.put((byte) data[start]);
	 		allocate.put((byte) data[start + 1]);
	 		allocate.order(ByteOrder.LITTLE_ENDIAN);
	 		allocate.flip();
			return allocate.getShort();
		}

		private int getInt(int[] data, int start) {
	 		ByteBuffer allocate = ByteBuffer.allocate(4);
	 		allocate.put((byte) data[start]);
	 		allocate.put((byte) data[start + 1]);
	 		allocate.put((byte) data[start + 2]);
	 		allocate.put((byte) data[start + 3]);
	 		allocate.order(ByteOrder.LITTLE_ENDIAN);
	 		allocate.flip();
			return allocate.getInt();
		}
		
		private String getString(int[] contents, int start, int end) {
			StringBuffer x = new StringBuffer();
			int[] r = Arrays.copyOfRange(contents, start, end);
			for (int i = 0 ; i < r.length ; i++) {
				x.append((char) r[i]);
			}
			String string = x.toString();
			return string.trim();
		}



		public long getBegin() {
			return dataBegin;
		}
	
}
