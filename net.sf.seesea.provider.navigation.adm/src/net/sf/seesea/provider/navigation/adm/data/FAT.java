package net.sf.seesea.provider.navigation.adm.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FAT {

	private boolean subfile;

	private String subfileName;
	
	private String subFileType;

	private int subFileSize;

	private short subFilePart;
	
	private List<Short> blockNumbers;
	
	public FAT(int[] contents) {
		if (contents[0] == 0)
			subfile = false;
		else
			subfile = true;
		subfileName = getString(contents, 0x1, 0x9);
		subFileType = getString(contents, 0x9, 0xC);
		subFileSize =  getInt(contents, 0xC);
		subFilePart = getShort(contents, 0x10);
		
		blockNumbers = new ArrayList<Short>();
		for(int i = 0x20 ; i < 0x200; i+=2) {
			short short1 = getShort(contents, i);
			if(short1 != -1) {
				blockNumbers.add(short1);
			}
		}
		
		// block sequence numbers ???
		
		int i = 1;
	}
	
	
	
	public boolean isSubfile() {
		return subfile;
	}



	public String getSubfileName() {
		return subfileName;
	}



	public String getSubFileType() {
		return subFileType;
	}



	public int getSubFileSize() {
		return subFileSize;
	}



	public short getSubFilePart() {
		return subFilePart;
	}



	public List<Short> getBlockNumbers() {
		return blockNumbers;
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
