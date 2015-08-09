/**
 * 
Copyright (c) 2010-2013, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.provider.navigation.nmea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import net.sf.seesea.lib.ResultStatus;
import net.sf.seesea.services.navigation.IDataLogger;
import net.sf.seesea.services.navigation.INMEAReader;
import net.sf.seesea.services.navigation.NMEAProcessingException;
import net.sf.seesea.services.navigation.RawDataEvent;
import net.sf.seesea.services.navigation.RawDataEventListener;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

/**
 * A logger for NMEA 0183 Data that logs into a gzipped file
 */

public class NMEA0183DataLogger implements RawDataEventListener, IDataLogger {
	private static final int INTERVAL_VALUE = 30;

	private static final int INTERVAL_UNIT = Calendar.MINUTE;

	/** registered services */
	private final Set<INMEAReader> nmeaReaders;

	private static final String PREFIX = "NMEA_"; //$NON-NLS-1$
	private String loggingDirectory;
	private GZIPOutputStream gzipOutputStream;
	private boolean rotateFileName;
	private Calendar nextLogRotationcalendar;

	private boolean persistentLogging;

	private StringBuffer transientLog;

	/**
	 * @param rotateFileName
	 * @throws IOException
	 * 
	 */
	public NMEA0183DataLogger() {
		nmeaReaders = Collections.synchronizedSet(new HashSet<INMEAReader>());
		transientLog = new StringBuffer();
	}

	/**
	 * component instanciation
	 */
	public void activate(Map<String, Object> properties) {
		try {
			persistentLogging = true;
			loggingDirectory = (String) properties.get("loggingDirectory"); //$NON-NLS-1$
			rotateFileName = (Boolean) properties.get("rotateFileName"); //$NON-NLS-1$
			URL url = new URL(loggingDirectory);
			File directory = new File(URLDecoder.decode(url.getFile())); //$NON-NLS-1$
			if (!directory.exists()) {
				directory.mkdirs();
			}
			File file = new File(URLDecoder.decode(url.getFile()),
					getRotateFileName());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			gzipOutputStream = new GZIPOutputStream(fileOutputStream);
			nextLogRotationcalendar = Calendar.getInstance();
			nextLogRotationcalendar.add(INTERVAL_UNIT, 30);
			Logger.getRootLogger().info(
					MessageFormat.format(
							"Storing file to {0}", file.getAbsolutePath())); //$NON-NLS-1$
		} catch (IOException e) {
			Logger.getRootLogger()
					.error("Failed to read admin service for configuring data logger", e); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 */
	public void deactivate(Map<String, Object> properties) {
		if (gzipOutputStream != null) {
			try {
				gzipOutputStream.close();
			} catch (IOException e) {
				Logger.getRootLogger().error(
						MessageFormat.format("Storing file to {0}", e)); //$NON-NLS-1$
			}
		}
	}

	public synchronized void bindReader(INMEAReader nmeaReader) {
		nmeaReaders.add(nmeaReader);
		nmeaReader.addNMEAEventListener(this);
		nmeaReader.addAISEventListener(this);
	}

	public synchronized void unbindReader(INMEAReader nmeaReader) {
		nmeaReaders.remove(nmeaReader);
		nmeaReader.removeNMEAEventListener(this);
		nmeaReader.removeAISEventListener(this);
		if (nmeaReaders.isEmpty()) {
			try {
				gzipOutputStream.close();
				Logger.getRootLogger().info("Closed nmea log file"); //$NON-NLS-1$
			} catch (IOException e) {
				Logger.getRootLogger().error("Unable to unbind reader", e); //$NON-NLS-1$
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.seesea.provider.navigation.nmea.NMEAEventListener#receiveNMEAEvent
	 * (net.sf.seesea.provider.navigation.nmea.RawDataEvent)
	 */
	@Override
	public synchronized void receiveRawDataEvent(RawDataEvent e)
			throws NMEAProcessingException {
		if (persistentLogging) {
			if (rotateFileName
					&& nextLogRotationcalendar.before(Calendar.getInstance())) {
				nextLogRotationcalendar = Calendar.getInstance();
				nextLogRotationcalendar.add(INTERVAL_UNIT, INTERVAL_VALUE);
				try {
					if (gzipOutputStream != null) {
						gzipOutputStream.close();
					}

					URL url = new URL(loggingDirectory);
					File file = new File(url.getFile(), getRotateFileName());
					FileOutputStream fileOutputStream = new FileOutputStream(
							file);
					gzipOutputStream = new GZIPOutputStream(fileOutputStream);
					Logger.getRootLogger().info("Storing log to file " + file); //$NON-NLS-1$
					if (transientLog.length() > 0) {
						Charset utf8 = Charset.forName("UTF-8"); //$NON-NLS-1$#
						gzipOutputStream.write(transientLog.toString()
								.getBytes(utf8));
						transientLog = new StringBuffer();
					}
				} catch (IOException e1) {
					throw new NMEAProcessingException(e1);
				}
			}
			try {
				Charset utf8 = Charset.forName("UTF-8"); //$NON-NLS-1$
				String nmeaMessageContent = e.getNmeaMessageContent();
				gzipOutputStream.write(nmeaMessageContent.getBytes(utf8));
			} catch (IOException e1) {
				throw new NMEAProcessingException(e1);
			}
		} else {
			transientLog.append(e.getNmeaMessageContent());
		}

	}

	private String getRotateFileName() {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMdd_HHmmss", Locale.ENGLISH); //$NON-NLS-1$
		String format = dateFormat.format(time);
		return PREFIX + format + ".183.gz"; //$NON-NLS-1$
	}

	public void modified(Map<String, Object> config) {
		rotateFileName = Boolean.valueOf((String) config.get("rotateFileName")); //$NON-NLS-1$
		loggingDirectory = (String) config.get("loggingDirectory"); //$NON-NLS-1$
	}

	@Override
	public void disable() {
		//
	}

	@Override
	public synchronized void suspendPersistentLogging()
			throws NMEAProcessingException {
		persistentLogging = false;
		try {
			gzipOutputStream.close();
		} catch (IOException e1) {
			throw new NMEAProcessingException(e1);

		}

	}

	@Override
	public synchronized void resumePersistentLogging() {
		persistentLogging = true;
	}

	@Override
	public List<File> getRecordedFiles() {
		URL url;
		try {
			url = new URL(loggingDirectory);
			File directory = new File(URLDecoder.decode(url.getFile()));
			List<File> file2Upload = Arrays.asList(directory.listFiles());
			return file2Upload;
		} catch (MalformedURLException e) {
			return new ArrayList<File>();
		}
	}

	@Override
	public IStatus archiveRecordedFiles(final List<File> recordedFiles,
			final MultiStatus status, IProgressMonitor subProgressMonitor) {
		MultiStatus multiStatus = new MultiStatus(NMEA0183Activator.PLUGIN_ID, IStatus.OK,
					"Archive files", null);
		URL url;
		try {
			url = new URL(loggingDirectory);
			File directory = new File(URLDecoder.decode(url.getFile()));
			File parentDir = directory.getParentFile();
			File archiveDir = new File(parentDir, "archive");
			archiveDir.mkdir();

			Set<File> correctlyUploadedFiles = new HashSet<File>();
			if(status.getChildren().length > 0) {
				IStatus[] children = ((MultiStatus)status.getChildren()[0]).getChildren();
				for (IStatus childstatus : children) {
					if(childstatus instanceof ResultStatus) {
						ResultStatus<?> resultStatus = (ResultStatus<?>) childstatus;
						if(resultStatus.getResult() instanceof File && resultStatus.isOK()) {
							correctlyUploadedFiles.add((File) resultStatus.getResult());
						}
					}
				}
				for (File file : recordedFiles) {
					subProgressMonitor.subTask("Archieving " + file.getName());
					if(correctlyUploadedFiles.contains(file)) {
						File archiveFile = new File(archiveDir.getAbsoluteFile(),file.getName());
						boolean renameTo = file.renameTo(archiveFile);
						if(!renameTo) {
							multiStatus.add(new Status(IStatus.OK, NMEA0183Activator.PLUGIN_ID, MessageFormat.format("Failed to move file {0}", file.getAbsoluteFile())));
						}
						subProgressMonitor.worked(1);
					}
				}
			}
			return multiStatus;
		} catch (MalformedURLException e) {
			return new Status(IStatus.ERROR, NMEA0183Activator.PLUGIN_ID,
					"Logging directory not an URL", e);
		}

	}
}
