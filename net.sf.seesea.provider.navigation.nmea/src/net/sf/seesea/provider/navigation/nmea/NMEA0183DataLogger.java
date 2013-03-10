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
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import net.sf.seesea.services.navigation.INMEAReader;
import net.sf.seesea.services.navigation.RawDataEventListener;
import net.sf.seesea.services.navigation.NMEAProcessingException;
import net.sf.seesea.services.navigation.RawDataEvent;

import org.apache.log4j.Logger;
import org.osgi.framework.Constants;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

/**
 * A logger for NMEA 0183 Data that logs into a gzipped file
 */
public class NMEA0183DataLogger implements RawDataEventListener, ManagedService {

	private static final int INTERVAL_VALUE = 5;

	private static final int INTERVAL_UNIT = Calendar.MINUTE;

	/** registered services */
	private final Set<INMEAReader> nmeaReaders;

	private static final String PREFIX = "NMEA_"; //$NON-NLS-1$
	private String loggingDirectory;
	private GZIPOutputStream gzipOutputStream;
	private boolean rotateFileName;
	private Calendar nextLogRotationcalendar;

	private ConfigurationAdmin _configAdmin;

	/**
	 * @param rotateFileName
	 * @throws IOException
	 * 
	 */
	public NMEA0183DataLogger() {
		nmeaReaders = Collections.synchronizedSet(new HashSet<INMEAReader>());
	}

	/**
	 * component instanciation
	 */
	public void activate() {
		try {
			Configuration configuration = _configAdmin.getConfiguration(
					"net.sf.seesea.provider.navigation.nmea.datalogger", null); //$NON-NLS-1$
			Dictionary<?,?> properties = configuration.getProperties();
			loggingDirectory = (String) properties.get("loggingDirectory"); //$NON-NLS-1$
			rotateFileName = (Boolean) properties.get("rotateFileName"); //$NON-NLS-1$
			URL url = new URL(loggingDirectory);
			File directory = new File(URLDecoder.decode(url.getFile())); //$NON-NLS-1$
			if(!directory.exists()) {
				directory.mkdirs();
			}
			File file = new File(URLDecoder.decode(url.getFile()), getRotateFileName());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			gzipOutputStream = new GZIPOutputStream(fileOutputStream);
			nextLogRotationcalendar = Calendar.getInstance();
			nextLogRotationcalendar.add(INTERVAL_UNIT, 30);
			Logger.getRootLogger().info(MessageFormat.format("Storing file to {0}", file.getAbsolutePath())); //$NON-NLS-1$
		} catch (IOException e) {
			Logger.getRootLogger().error("Failed to read admin service for configuring data logger", e); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 */
	public void deactivate() {
		if(gzipOutputStream != null) {
			try {
				gzipOutputStream.close();
			} catch (IOException e) {
				Logger.getRootLogger().error(MessageFormat.format("Storing file to {0}", e)); //$NON-NLS-1$
			}
		}
	}

	public synchronized void bindConfigAdminService(ConfigurationAdmin configAdmin) {
		this._configAdmin = configAdmin;
	}

	public synchronized void unbindConfigAdminService(ConfigurationAdmin configAdmin) {
		this._configAdmin = null;
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
	public void receiveRawDataEvent(RawDataEvent e) throws NMEAProcessingException {
		if (rotateFileName && nextLogRotationcalendar.before(Calendar.getInstance())) {
			nextLogRotationcalendar = Calendar.getInstance();
			nextLogRotationcalendar.add(INTERVAL_UNIT, INTERVAL_VALUE);
			try {
				gzipOutputStream.close();
				URL url = new URL(loggingDirectory);
				File file = new File(url.getFile(), getRotateFileName());
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				gzipOutputStream = new GZIPOutputStream(fileOutputStream);
				Logger.getRootLogger().info("Storing log to file " + file); //$NON-NLS-1$
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
	}

	private String getRotateFileName() {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH); //$NON-NLS-1$
		String format = dateFormat.format(time);
		return PREFIX + format + ".183.gz"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.cm.ManagedService#updated(java.util.Dictionary)
	 */
	@Override
	public void updated(Dictionary properties) throws ConfigurationException {
		Dictionary<String, Object> config = properties == null ? getDefaultConfig() : properties;
		rotateFileName = Boolean.valueOf((String) config.get("rotateFileName")); //$NON-NLS-1$
		loggingDirectory = (String) config.get("loggingDirectory"); //$NON-NLS-1$
	}

	private Dictionary<String, Object> getDefaultConfig() {
		Dictionary<String, Object> defaultConfig = new Hashtable<String, Object>();
		defaultConfig.put("rotateFileName", true); //$NON-NLS-1$
		defaultConfig.put("loggingDirectory", System.getProperty("user.home") + File.separator + "NMEALogging");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		defaultConfig.put(Constants.SERVICE_PID, RawDataEventListener.class.getName());
		return defaultConfig;
	}

	@Override
	public void disable() {
		//
	}
}
