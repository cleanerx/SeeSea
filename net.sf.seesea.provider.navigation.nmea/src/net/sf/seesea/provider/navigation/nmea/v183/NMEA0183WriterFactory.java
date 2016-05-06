package net.sf.seesea.provider.navigation.nmea.v183;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;
import net.sf.seesea.data.io.WriterException;

public class NMEA0183WriterFactory implements IWriterFactory {

	private long fileCounter = 1;

	@Override
	public IDataWriter createWriter() throws WriterException {
		Map<String,Object> parameters = new HashMap<String, Object>();
		FileOutputStream outputStream;
		try {
			String fileprefix = (String) parameters.get("fileprefix"); //$NON-NLS-1$
			String filesuffix = (String) parameters.get("filesuffix"); //$NON-NLS-1$
			String filename = fileprefix + fileCounter++ + filesuffix;
			outputStream = new FileOutputStream(filename);
			return new NMEA0183Writer(outputStream);
		} catch (FileNotFoundException e) {
			throw new WriterException("Could not find file", e); //$NON-NLS-1$
		}
	}

}
