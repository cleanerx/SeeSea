package net.sf.seesea.provider.navigation.nmea.v183;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import net.sf.seesea.data.io.IDataReader;
import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;

public class NMEA0183WriterFactory implements IWriterFactory {

	@Override
	public IDataWriter createWriter(Map<String,String> parameters) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(parameters.get("filename")); //$NON-NLS-1$
		return new NMEA0183Writer(outputStream);
	}

	@Override
	public IDataReader createReader() {
		// TODO Auto-generated method stub
		return null;
	}

}
