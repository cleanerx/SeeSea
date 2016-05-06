package net.sf.seesea.data.io;

/**
 * 
 */
public interface IWriterFactory {

	IDataWriter createWriter() throws WriterException;

}
