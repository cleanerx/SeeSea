package net.sf.seesea.data.io;

import java.io.IOException;
import java.util.Map;

public interface IWriterFactory {

	IDataReader createReader();

	IDataWriter createWriter(Map<String, String> parameters) throws IOException;

}
