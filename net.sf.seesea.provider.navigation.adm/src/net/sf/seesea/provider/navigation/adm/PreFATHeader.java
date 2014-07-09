package net.sf.seesea.provider.navigation.adm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class PreFATHeader {
	
	private List<Short> blockNumbers;

	public PreFATHeader(int[] contents) {
		firstSubFileOffset = getInt(contents, 0xC);
		blockNumbers = new ArrayList<Short>();
		for(int i = 0x20 ; i < 0x200; i+=2) {
			short short1 = getShort(contents, i);
			if(short1 != -1) {
				blockNumbers.add(short1);
			}
		}

	}

	private int firstSubFileOffset;
	
	public List<Short> getBlockNumbers() {
		return blockNumbers;
	}



	public int getFirstSubFileOffset() {
		return firstSubFileOffset;
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
	
	private short getShort(int[] data, int start) {
 		ByteBuffer allocate = ByteBuffer.allocate(2);
 		allocate.put((byte) data[start]);
 		allocate.put((byte) data[start + 1]);
 		allocate.order(ByteOrder.LITTLE_ENDIAN);
 		allocate.flip();
		return allocate.getShort();
	}


}
