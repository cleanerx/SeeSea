package net.sf.seesea.content.impl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.tika.Tika;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import net.sf.seesea.content.api.ContentDetectionException;
import net.sf.seesea.content.api.IContentDetector;
import net.sf.seesea.track.api.IStreamProcessor;
import net.sf.seesea.track.api.IStreamProcessorDetection;
import net.sf.seesea.track.api.ITrackFileDecompressor;
import net.sf.seesea.track.api.ITrackPersistence;
import net.sf.seesea.track.api.data.CompressionType;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.exception.RawDataEventException;
import net.sf.seesea.track.api.exception.TrackPerssitenceException;
import net.sf.seesea.track.model.ZipEntryTrackFile;

@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
public class ContentDetector implements IContentDetector {

	private AtomicReference<ITrackPersistence> trackPersistenceAR = new AtomicReference<ITrackPersistence>();

	private List<ITrackFileDecompressor> trackFileDecompressors = new CopyOnWriteArrayList<ITrackFileDecompressor>();;

	private String basedir;

	private AtomicReference<IStreamProcessorDetection> streamProcessorDetectionAR = new AtomicReference<IStreamProcessorDetection>();

	private boolean fullprocess;

	public ContentDetector() {
		// TODO Auto-generated constructor stub
	}
	
	@Activate
	public void activate(Map<String, Object> properties) {
		basedir = (String) properties.get("basedir");
		fullprocess = "true".equals(properties.get("fullprocess"));
	}

	@Override
	public void setContentTypes() throws ContentDetectionException {
		ITrackPersistence trackPersistence = trackPersistenceAR.get();
		IStreamProcessorDetection streamProcessorDetection = streamProcessorDetectionAR.get();
		DecimalFormat format = new DecimalFormat("#####000"); //$NON-NLS-1$
		DecimalFormat fileFormat = new DecimalFormat("#######0"); //$NON-NLS-1$
		format.setGroupingUsed(false);
		
		// reset states of previously processed files if they have been marked for reprocessing
		try {
			trackPersistence.resetAnalyzedData();
		} catch (TrackPerssitenceException e1) {
			throw new ContentDetectionException("Failed to reset analyzed data", e1);
		}
		
		try {
			List<ITrackFile> trackFiles2Process = trackPersistence.getTrackFiles2Process();
			for (ITrackFile trackFileX : trackFiles2Process) {
				long id = trackFileX.getTrackId();
				String trackFile = MessageFormat.format("{0}/{1}/{2}.dat", basedir, format.format((id / 100) * 100), //$NON-NLS-1$
						fileFormat.format(id));
				FileInputStream fis;
				try {
					File file = new File(trackFile);
					fis = new FileInputStream(file);
					String mimeType = getMimeType(fis);
					// Tika does not detect zips reliably
					if (mimeType.equals("application/octet-stream")) {
						try (ZipFile zipFile = new ZipFile(file, Charset.forName("ISO_8859_1"))){
							zipFile.entries();
							mimeType = "application/zip";
						} catch (IOException e) {
							// nothing to do;
						}
					}
					// only one file may be compressed in gz (tar not yet
					// considered
					// here)
					if (CompressionType.GZ.getMimeType().equals(mimeType)) {
						try {
							GZIPInputStream gzipInputStream = new GZIPInputStream(
									new FileInputStream(new File(trackFile)));
							String containerType = getMimeType(gzipInputStream);
							if ("text/plain".equals(containerType)) { //$NON-NLS-1$
								gzipInputStream = new GZIPInputStream(new FileInputStream(new File(trackFile)));
								CompressionType compressionType = CompressionType.getCompressionType(mimeType);
								IStreamProcessor streamProcessor = streamProcessorDetection
										.detectStreamProcessorEnblock(gzipInputStream, false);
								if (streamProcessor != null) {
									trackFileX.setFileType(streamProcessor.getMimeType());
									trackFileX.setCompression(compressionType);
									trackFileX
											.setUploadState(net.sf.seesea.track.api.data.ProcessingState.PREPROCESSED);
								} else {
									trackFileX.setUploadState(
											net.sf.seesea.track.api.data.ProcessingState.FILE_CONTENT_UNKNOWN);
								}
							}
						} catch (EOFException e) {
							trackFileX.setUploadState(net.sf.seesea.track.api.data.ProcessingState.FILE_CORRUPT);
						} catch (IOException e) {
//							Logger.getLogger(getClass()).error("Failed to read track id:" + id, e); //$NON-NLS-1$
							trackFileX.setUploadState(net.sf.seesea.track.api.data.ProcessingState.FILE_CORRUPT);
						}
					} else if ("text/plain".equals(mimeType) || "application/xml".equals(mimeType)) { //$NON-NLS-1$ //$NON-NLS-2$
						FileInputStream fileInputStream = new FileInputStream(new File(trackFile));
						CompressionType compressionType = CompressionType.getCompressionType(mimeType);
						IStreamProcessor streamProcessor = streamProcessorDetection
								.detectStreamProcessorEnblock(fileInputStream, false);
						if (streamProcessor != null) {
							trackFileX.setFileType(streamProcessor.getMimeType());
							trackFileX.setCompression(compressionType);
							trackFileX.setUploadState(net.sf.seesea.track.api.data.ProcessingState.PREPROCESSED);
						} else {
							trackFileX
									.setUploadState(net.sf.seesea.track.api.data.ProcessingState.FILE_CONTENT_UNKNOWN);
						}
					} else if (CompressionType.ZIP.getMimeType().equals(mimeType)) {
						// determine zip entries
						List<ZipEntry> zipEntries = new ArrayList<ZipEntry>(1);
						String encoding = null;
						ZipFile zipFile = null;
						try {
							zipFile = new ZipFile(file, Charset.forName("UTF-8"));
							zipEntries = getZipEntries(zipFile); // $NON-NLS-1$
							encoding = "UTF-8";
						} catch (IllegalArgumentException e) {
							// Logger.getLogger(this.getClass()).error("Failed
							// to
							// open zip entry. May it is not UTF-8 encoded:" +
							// file.getAbsolutePath());
							try {
								zipFile = new ZipFile(file, Charset.forName("ISO-8859-1"));
								zipEntries = getZipEntries(zipFile); // $NON-NLS-1$
								encoding = "ISO-8859-1"; //$NON-NLS-1$
							} catch (IllegalArgumentException e2) {
								// Logger.getLogger(this.getClass()).error("Failed
								// to open zip entry. May it is not ISO-8859-1
								// encoded:" + file.getAbsolutePath());
								// return Collections.emptyList();
							}
						}
						// if it is compressed, ask the providers for
						// decompression
						List<ITrackFile> unzippedFiles = new ArrayList<ITrackFile>();
						for (ITrackFileDecompressor trackFileDecompressor : trackFileDecompressors) {
							unzippedFiles.addAll(trackFileDecompressor.getUnzippedFiles(zipFile, zipEntries, encoding));
						}
						if (!unzippedFiles.isEmpty()) {
							trackFileX.getTrackFiles().addAll(unzippedFiles);
							continue; // format prececeds single decompression
						}

						// if it is not decompressed by the format, treat every
						// entry individually
						for (ZipEntry zipEntry : zipEntries) {
							InputStream inputStream = zipFile.getInputStream(zipEntry);
							IStreamProcessor streamProcessor = streamProcessorDetection
									.detectStreamProcessorEnblock(inputStream, false);
							if (streamProcessor != null) {
								ZipEntryTrackFile zipEntryTrackFile = new ZipEntryTrackFile(zipFile, zipEntry);
								zipEntryTrackFile.setFileType(streamProcessor.getMimeType());
								zipEntryTrackFile.setCompression(CompressionType.ZIP);
								zipEntryTrackFile
										.setUploadState(net.sf.seesea.track.api.data.ProcessingState.PREPROCESSED);
								zipEntryTrackFile.setUsername(trackFileX.getUsername());
								// zipEntryTrackFile.setTrackId(trackId);
								trackFileX.getTrackFiles().add(zipEntryTrackFile);
							}
						}

					} else if ("application/octet-stream".equals(mimeType)) { //$NON-NLS-1$
						// let the stream processors decide what type of data
						// this
						// is
						FileInputStream fileInputStream = new FileInputStream(new File(trackFile));
						IStreamProcessor streamProcessor = streamProcessorDetection
								.detectBinaryStreamProcessorEnblock(fileInputStream, false);
						if (streamProcessor != null) {
							trackFileX.setFileType(streamProcessor.getMimeType());
							trackFileX.setCompression(CompressionType.NONE);
							trackFileX.setUploadState(net.sf.seesea.track.api.data.ProcessingState.PREPROCESSED);
						} else {
							trackFileX
									.setUploadState(net.sf.seesea.track.api.data.ProcessingState.FILE_CONTENT_UNKNOWN);
						}
					} else {
						trackFileX.setUploadState(net.sf.seesea.track.api.data.ProcessingState.FILE_CONTENT_UNKNOWN);
					}
					trackPersistence.storePreprocessingStates(trackFiles2Process);
				} catch (FileNotFoundException e) {
//					Logger.getLogger(getClass()).error("Failed to find file", e); //$NON-NLS-1$
					trackFileX.setUploadState(net.sf.seesea.track.api.data.ProcessingState.FILE_CORRUPT);
				} catch (IOException e) {
					e.printStackTrace();
					trackFileX.setUploadState(net.sf.seesea.track.api.data.ProcessingState.FILE_CORRUPT);
				} catch (RawDataEventException e) {
					e.printStackTrace();
					trackFileX.setUploadState(net.sf.seesea.track.api.data.ProcessingState.FILE_CORRUPT);
				}
			}
		} catch (TrackPerssitenceException e) {
			throw new ContentDetectionException(e);
		}
	}

	private List<ZipEntry> getZipEntries(ZipFile zipFile) {
		List<ZipEntry> zipEntries = new ArrayList<ZipEntry>();
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry nextElement = entries.nextElement();
			if (!nextElement.isDirectory()) {
				zipEntries.add(nextElement);
			}
		}
		return zipEntries;
	}

	private String getMimeType(InputStream fis) throws IOException {
		Tika tika = new Tika();
		String detect = tika.detect(fis);
		return detect;
	}
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC)
	public void bindStreamProcessor(IStreamProcessorDetection  streamProcessorDetection) {
		streamProcessorDetectionAR.set(streamProcessorDetection);
	}

	public void unbindStreamProcessor(IStreamProcessorDetection streamProcessorDetection) {
		streamProcessorDetectionAR.compareAndSet(null, streamProcessorDetection);
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC)
	public void bindTrackPersistence(ITrackPersistence trackPersistence) {
		trackPersistenceAR.set(trackPersistence);
	}

	public void unbindTrackPersistence(ITrackPersistence trackPersistence) {
		trackPersistenceAR.compareAndSet(null, trackPersistence);
	}

}
