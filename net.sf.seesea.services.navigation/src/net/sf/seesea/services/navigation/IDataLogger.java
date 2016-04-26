package net.sf.seesea.services.navigation;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

import net.sf.seesea.track.api.exception.NMEAProcessingException;

public interface IDataLogger {

	void suspendPersistentLogging() throws NMEAProcessingException;
	
	void resumePersistentLogging();
	
	List<File> getRecordedFiles();
	
	IStatus archiveRecordedFiles(final List<File> recordedFiles,
			final MultiStatus status, IProgressMonitor subProgressMonitor);
}
