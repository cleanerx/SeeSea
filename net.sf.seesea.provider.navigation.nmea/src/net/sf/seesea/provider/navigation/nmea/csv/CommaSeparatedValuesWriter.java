package net.sf.seesea.provider.navigation.nmea.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import net.sf.seesea.data.io.IDataReader;
import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;
import net.sf.seesea.data.io.WriterException;

public class CommaSeparatedValuesWriter implements IWriterFactory {

	@Override
	public IDataReader createReader() {
		return null;
	}

	@Override
	public IDataWriter createWriter(Map<String, String> parameters)
			throws WriterException {
		File file = new File(parameters.get("filename")); //$NON-NLS-1$
		System.out.println("Writing to file " + file.getAbsolutePath());
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(file);
			return new CSVWriter(outputStream);
		} catch (FileNotFoundException e) {
			throw new WriterException("Could not find file", e);
		}
	}

}
