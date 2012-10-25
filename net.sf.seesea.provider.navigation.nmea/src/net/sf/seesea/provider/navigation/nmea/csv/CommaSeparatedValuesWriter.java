package net.sf.seesea.provider.navigation.nmea.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import net.sf.seesea.data.io.IDataReader;
import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;

public class CommaSeparatedValuesWriter implements IWriterFactory {

	@Override
	public IDataReader createReader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataWriter createWriter(Map<String, String> parameters)
			throws IOException {
		File file = new File(parameters.get("filename")); //$NON-NLS-1$
		System.out.println("Writing to file " + file.getAbsolutePath());
		FileOutputStream outputStream = new FileOutputStream(file);
		return new CSVWriter(outputStream);
	}

}
