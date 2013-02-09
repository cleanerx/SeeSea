package net.sf.seesea.provider.navigation.nmea.v183;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import net.sf.seesea.data.io.IDataReader;
import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;
import net.sf.seesea.data.io.WriterException;

public class NMEA0183WriterFactory implements IWriterFactory {

	@Override
	public IDataWriter createWriter(Map<String,String> parameters) throws WriterException {
		FileOutputStream outputStream;
		try {
			String filename = parameters.get("filename");
			outputStream = new FileOutputStream(filename);
			return new NMEA0183Writer(outputStream);
		} catch (FileNotFoundException e) {
			throw new WriterException("Could not find file", e);
		}
	}

	@Override
	public IDataReader createReader() {
		return null;
	}

}
