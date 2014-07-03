package net.sf.seesea.provider.navigation.adm.data;

public class BlockDescription {
	
	private int startByte;
	
	private int size;

	public BlockDescription(int startByte, int size) {
		super();
		this.startByte = startByte;
		this.size = size;
	}

	public int getStartByte() {
		return startByte;
	}

	public int getSize() {
		return size;
	}
	
	

}
