package net.sf.seesea.data.io;

import java.util.Map;

/**
 * 
 */
public interface IWriterFactory {

	IDataWriter createWriter(Map<String, Object> parameters) throws WriterException;

}
