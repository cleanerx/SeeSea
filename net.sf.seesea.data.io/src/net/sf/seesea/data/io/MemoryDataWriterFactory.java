package net.sf.seesea.data.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MemoryDataWriterFactory implements IWriterFactory {

	private List<IDataWriter> createDatawriters;

	public MemoryDataWriterFactory() {
		createDatawriters = new ArrayList<IDataWriter>();
	}
	
	@Override
	public IDataWriter createWriter() {
		MemoryDataWriter memoryDataWriter = new MemoryDataWriter();
		createDatawriters.add(memoryDataWriter);
		return memoryDataWriter;
	}

	protected List<IDataWriter> getCreatedDatawriters() {
		return createDatawriters;
	}
	
	
	

}
