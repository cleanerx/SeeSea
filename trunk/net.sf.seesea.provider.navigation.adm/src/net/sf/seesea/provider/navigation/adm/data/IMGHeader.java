package net.sf.seesea.provider.navigation.adm.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class IMGHeader {
	
	private int firstSubFileOffset;

	private ArrayList<Object> fat;


	public IMGHeader(int[] contents) {
		if (contents[0] == 0)
			imageFileXored = false;
		else
			imageFileXored = true;
		updateMonth = contents[0x9];
		updateYear = contents[0xA] >= 0x63 ? contents[0xA] + 1900 : contents[0xA] + 2000;
		checksum = contents[0xE];
        signature = getString(contents, 0xF, 0x15);
		
		creationYear = getShort(contents, 0x38);
		creationMonth = contents[0x3A];
		creationDay = contents[0x3B];
		creationHour = contents[0x3C];
		creationMinute = contents[0x3D];
		creationSecond = contents[0x3E];
		
        mapFileIdentifier = getString(contents, 0x40, 0x46);
        mapDescription = getString(contents, 0x48, 0x5C);
        mapName = getString(contents, 0x64, 0x83);
        
        blockSize = 1 << contents[0x60] + contents[0x61];

        partitionTable = new PartitionTable(Arrays.copyOfRange(contents, 0x1bd, 0x1fd));
        
//        firstSubFileOffset = getInt(contents, 0x100B); // why at 4096 bytes?
        
//        fat = new ArrayList<>();
//        fat.add(new FAT(Arrays.copyOfRange(contents, 0x1200, 0x13ff)));
        int i = 1;
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
	
	private boolean imageFileXored;
	
	private int updateMonth;
	
	private int updateYear;
	
	private int unknown1;
	
	private long checksum;
	
	private String signature;
	
	private int unknown2;
	
	private int sectors;
	
	private int heads;
	
	private int cylinders;
	
	private int unknown3;
	
	private String unknown4;
	
	private int creationYear;
	
	private int creationMonth;
	
	private int creationDay;
	
	private int creationHour;
	
	private int creationMinute;
	
	private int creationSecond;
	
	private int unknown5;
	
	private String mapFileIdentifier;
	
	private int unknown6;
	
	private String mapDescription;
	
	private int heads2;
	
	private int sectors2;
	
	private int blockSize;
	
	private int blockSizeExponent2;
	
	private int unknown7;
	
	private String mapName;
	
	private String unused;
	
	private PartitionTable partitionTable;
	
	
	

	public int getBlockSize() {
		return blockSize;
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

}
