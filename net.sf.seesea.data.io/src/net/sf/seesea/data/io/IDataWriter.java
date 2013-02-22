package net.sf.seesea.data.io;

import java.util.Collection;

import net.sf.seesea.model.core.physx.Measurement;

public interface IDataWriter {

	void write(Measurement data) throws WriterException;

	void write(Collection<Measurement> data) throws WriterException;
	
	void closeOutput() throws WriterException;
	
}