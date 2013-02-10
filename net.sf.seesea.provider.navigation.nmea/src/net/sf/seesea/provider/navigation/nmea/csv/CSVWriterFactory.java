package net.sf.seesea.provider.navigation.nmea.csv;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import net.sf.seesea.data.io.IDataReader;
import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;
import net.sf.seesea.data.io.WriterException;

public class CSVWriterFactory implements IWriterFactory {

	private long fileCounter = 1;

	@Override
	public IDataReader createReader() {
		return null;
	}

	@Override
	public IDataWriter createWriter(Map<String, String> parameters)
			throws WriterException {
		try {
			FileOutputStream outputStream;
			String fileprefix = parameters.get("fileprefix"); //$NON-NLS-1$
			String filesuffix = parameters.get("filesuffix"); //$NON-NLS-1$
			String filename = fileprefix + fileCounter++ + filesuffix;
			outputStream = new FileOutputStream(filename);
			return new CSVWriter(outputStream);
		} catch (FileNotFoundException e) {
			throw new WriterException("Could not find file", e); //$NON-NLS-1$
		}
	}

}
