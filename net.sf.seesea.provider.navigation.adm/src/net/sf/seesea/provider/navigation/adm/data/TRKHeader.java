package net.sf.seesea.provider.navigation.adm.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TRKHeader {

	private int length;
	private int trackCount;
	private int blockCount;
	private int unknown3;
	private short unknown4;
	private int length2;
	private int unknown5;
	private int unknown6;
	private int unknown7;
	private int unknown8;
	private int unknown9;
	private int unknown10;
	private int unknown11;
	private int unknown12;
	private int unknown15;
	private int unknown13;
	private int unknown14;
	private int unknown16;
	private int unknown17;
	private int unknown18;
	private int unknown19;
	private int unknown20;
	private int unknown21;
	private String trackName;
	private short headerMetadata;
	private short headerMetadata1;
	private int headerMetadata2;
	private int headerMetadata3;
	private int headerMetadata4;
	private int dataBegin;
	private short trackppointCount;
	private List<MetadataDescription> headerDataDescriptions;
	private List<MetadataDescription> dataDescriptions;
	private List<BlockDescription> blockDescriptions;

	public TRKHeader(int[] contents) {
		length = getInt(contents, 2);
		blockCount = contents[6];
		trackCount = getInt(contents, 7);
		unknown3 = getInt(contents, 11);
		unknown4 = getShort(contents, 15);
		length2 = getInt(contents, 17);
		blockDescriptions = new ArrayList<BlockDescription>();
		for(int i = 0 ; i < blockCount ; i++) {
			int startByte = getInt(contents, 21 + i * 8);
			int size = getShort(contents, 21 + i * 8 + 4);
			blockDescriptions.add(new BlockDescription(startByte, size));
		}
		headerDataDescriptions = new ArrayList<MetadataDescription>();
		dataDescriptions = new ArrayList<MetadataDescription>();
		BlockDescription blockDescription = blockDescriptions.get(0);
		for (int i = 0 ; i < blockDescription.getSize(); i++) {
			short id = getShort(contents, blockDescription.getStartByte() + i * 4);
			short size = getShort(contents, blockDescription.getStartByte() + i * 4 + 2);
			headerDataDescriptions.add(new MetadataDescription(id, size));
		}
		blockDescription = blockDescriptions.get(1);
		for (int i = 0 ; i < blockDescription.getSize(); i++) {
			short id = getShort(contents, blockDescription.getStartByte() + i * 4);
			short size = getShort(contents, blockDescription.getStartByte() + i * 4 + 2);
			dataDescriptions.add(new MetadataDescription(id, size));
		}
		
//		int startByte = blockDescriptions.get(2).getStartByte();
//		for (MetadataDescription metadataDescription : headerDataDescriptions) {
//			if(metadataDescription.getId() == 300) {
//				trackName = getString(contents, startByte, startByte + metadataDescription.getSize());
//			} else if(metadataDescription.getId() == 301) {
//				trackppointCount = getShort(contents, startByte);
//			} else if(metadataDescription.getId() == 302) {
//				headerMetadata2 = contents[startByte];
//			} else if(metadataDescription.getId() == 303) {
//				headerMetadata3 = contents[startByte];
//			} else if(metadataDescription.getId() == 304) {
//				dataBegin = getInt(contents, startByte);
//			}
//
//			startByte+=metadataDescription.getSize();
//		}
	}
	
	public short getTrackppointCount() {
		return trackppointCount;
	}

	public List<MetadataDescription> getHeaderDataDescriptions() {
		return headerDataDescriptions;
	}

	public List<MetadataDescription> getDataDescriptions() {
		return dataDescriptions;
	}

	public List<BlockDescription> getBlockDescriptions() {
		return blockDescriptions;
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

	public int getTrackCount() {
		return trackCount;
	}
	
	

}
