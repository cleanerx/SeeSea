package net.sf.seesea.provider.navigation.adm.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PartitionTable {
	
	public PartitionTable(int[] contents) {
		if (contents[0] == 0)
			boot = false;
		else
			boot = true;
		startHead = contents[0x01];
		startSector = contents[0x02];
		startCylinder = contents[0x03];
		systemType = contents[0x04];
		endHead = contents[0x05];
		endSector = contents[0x06];
		endCylinder = contents[0x07];
		relativeSectors = getInt(contents, 0x08);
		numberOfSectors = getInt(contents, 0x12);
		
	}

	boolean boot;
	
	int startHead;
	
	int startSector;
	
	int startCylinder;
	
	int systemType;
	
	int endHead;
	
	int endSector;
	
	int endCylinder;
	
	int relativeSectors;
	
	int numberOfSectors;
	
	byte[] unused1;
	
	int tableEnd;

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
