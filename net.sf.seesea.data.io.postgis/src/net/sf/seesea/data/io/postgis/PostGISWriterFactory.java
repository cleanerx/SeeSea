package net.sf.seesea.data.io.postgis;

import java.util.Arrays;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.data.io.IWriterFactory;
import net.sf.seesea.data.io.WriterException;

@Component(property = "type=postgis")
public class PostGISWriterFactory implements IWriterFactory {

	public PostGISWriterFactory() throws ClassNotFoundException {
		Class.forName ("org.postgresql.Driver"); //$NON-NLS-1$
	}

	@Override
	public IDataWriter createWriter(Map<String,Object> parameters) throws WriterException {
		String user = (String) parameters.get("user"); //$NON-NLS-1$
		String password = (String) parameters.get("password"); //$NON-NLS-1$
		String host = (String) parameters.get("host"); //$NON-NLS-1$
		if(host == null || host.isEmpty()) {
			host = "localhost"; //$NON-NLS-1$
		}
		String port = (String) parameters.get("port"); //$NON-NLS-1$
		if(port == null || port.isEmpty()) {
			port = "5432"; //$NON-NLS-1$
		}
		String database = (String) parameters.get("outputDatabase"); //$NON-NLS-1$
		String table = (String) parameters.get("outputTable"); //$NON-NLS-1$
		String[] outputTables = table.split(","); //$NON-NLS-1$
		
		String url = "jdbc:postgresql:" + (host != null ? ("//" + host) + (port != null ? ":" + port : "") + "/" : "") + database; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		return new PostInsertGISWriter(url, user, password,Arrays.asList(outputTables));
	}
}
