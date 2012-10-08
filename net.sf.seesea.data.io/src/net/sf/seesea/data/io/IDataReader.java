package net.sf.seesea.data.io;

import java.io.IOException;
import java.util.Collection;

import net.sf.seesea.model.core.physx.Measurement;

public interface IDataReader {
		
		Collection<Measurement> read() throws IOException;

}
