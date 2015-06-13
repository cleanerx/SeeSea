package net.sf.seesea.provider.navigation.adm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipException;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.adm.data.FAT;
import net.sf.seesea.provider.navigation.adm.data.IMGHeader;
import net.sf.seesea.provider.navigation.adm.data.MetadataDescription;
import net.sf.seesea.provider.navigation.adm.data.TRKHeader;
import net.sf.seesea.provider.navigation.adm.data.TrackMetadata;
import net.sf.seesea.provider.navigation.adm.data.TrackPointADM;
import net.sf.seesea.services.navigation.CompressionType;
import net.sf.seesea.services.navigation.IStreamProcessor;
import net.sf.seesea.services.navigation.ITrack;
import net.sf.seesea.services.navigation.NMEAProcessingException;

public class ADMStreamProcessor implements IStreamProcessor, IADMReader {

	private List<IADMListener> listeners;

	private MessageProcessingState state;

	private int[] message = new int[4096];

	private int counter;

	private int fatEnd;

	private int blockSize;

	private int fatCounter;

	private int blockCounter;

	private List<FAT> fats;

	private FAT currentFat;

	private List<Short> fatBlocks;

	private int totalCount;

	private MessageProcessingState subfileState;

	private int trackPointCount;

	private int currentTrackPoint;

	private TRKHeader trkHeader;

	private int bytesToSkip;

	private int stopHeaderByteCount;

	public ADMStreamProcessor() {
		reset();
	}

	private void reset() {
		blockSize = Integer.MAX_VALUE;
		listeners = new ArrayList<IADMListener>();
		fatCounter = 0;
		fats = new ArrayList<FAT>();
		totalCount = 0;
		state = MessageProcessingState.HEADER_START;
		subfileState = MessageProcessingState.SUBFILE_HEADER;
		currentFat = null;
		counter = 0;
		message = new int[4096];
	}

	@Override
	public String getMimeType() {
		return "application/x-adm"; //$NON-NLS-1$
	}

	@Override
	public boolean isValidStreamProcessor(int[] buffer)
			throws NMEAProcessingException {
		reset();
		TrackPointReader trackPointReader = new TrackPointReader();
		listeners.add(trackPointReader);
		for (int i = 0; i < buffer.length; i++) {
			int j = buffer[i];
			readByte(j, "ADM");
		}
		listeners.remove(trackPointReader);
		return trackPointReader.isValidReader();
	}

	@Override
	public boolean readByte(int c, String streamProvider)
			throws NMEAProcessingException {
		// if(counter == message.length) {
		// throw new NMEAProcessingException("No usable data found within " +
		// counter + "bytes");
		// }
		totalCount++;
		if (state.equals(MessageProcessingState.HEADER_START)) {
			message[counter++] = c;
			if (counter == 512) {
				state = MessageProcessingState.HEADER_END;
				IMGHeader imgHeader = new IMGHeader(message);
				for (IADMListener listener : listeners) {
					listener.notifyIMGHeader(imgHeader);
				}
				blockSize = imgHeader.getBlockSize();
			}
		} else if (state.equals(MessageProcessingState.HEADER_END)) {
			if(counter == 512) {
				if(c == 255 ) {
					stopHeaderByteCount = 1024;
				} else {
					stopHeaderByteCount = 4096;
				}
			}
			counter++;
			if (counter == stopHeaderByteCount) {
				counter = 0;
				message = new int[512];
//				message[counter++] = c;
				state = MessageProcessingState.XSTART;
			}
		} else if (state.equals(MessageProcessingState.XSTART)) {
			message[counter++] = c;
			if (counter == 512) {
				PreFATHeader preFATHeader = new PreFATHeader(
						Arrays.copyOfRange(message, 0, 512));
				for (IADMListener listener : listeners) {
					listener.notifyPreFATHeader(preFATHeader);
				}
				fatEnd = preFATHeader.getFirstSubFileOffset();
				fatBlocks = preFATHeader.getBlockNumbers();
				state = MessageProcessingState.FAT;
				counter = 0;
				message = new int[512];
				// message[counter++] = c;
			}
		} else if (state.equals(MessageProcessingState.FAT)) {
			message[counter++] = c;
			if (counter == 512) {
				FAT fat = new FAT(Arrays.copyOfRange(message, 0, 512));
				if (fat.isSubfile() && fat.getSubFileSize() != -1) {
					fats.add(fat);
					for (IADMListener listener : listeners) {
						listener.notifyFATBlock(fat);
					}
				}
				counter = 0;
				message = new int[512];
				fatCounter++;
			}
			if (totalCount == fatEnd) {
				state = MessageProcessingState.SUBFILE;
				message = new int[blockSize];
				blockCounter = fatBlocks.get(fatBlocks.size() - 1) + 1;
				for (FAT fat : fats) {
					for (Short blockNumber : fat.getBlockNumbers()) {
						if (blockNumber == blockCounter) {
							currentFat = fat;
						}
					}
				}
				counter = 0;
			}
		} else if (state.equals(MessageProcessingState.SUBFILE)) {
			message[counter] = c;
			counter++;
			if(counter == blockSize) {
				counter = 0;
			}
			FAT fatByBlock = getFatByBlock(fats, totalCount / blockSize);
			if(fatByBlock != null) {
				currentFat = fatByBlock;
			}
//			if (currentFat.getBlockNumbers().size() * 4096 == counter) {
//				counter = 0;
//				blockCounter += currentFat.getBlockNumbers().size();
//				for (FAT fat : fats) {
//					for (Short blockNumber : fat.getBlockNumbers()) {
//						if (blockNumber == blockCounter) {
//							currentFat = fat;
//							subfileState = MessageProcessingState.SUBFILE_HEADER;
//							break;
//						}
//					}
//				}
//			}
			if (currentFat != null && currentFat.getSubFileType().equals("TRK")) {
//				if (subfileState.equals(MessageProcessingState.SUBFILE_HEADER)) {
					if (counter == 89) {
						trkHeader = new TRKHeader(Arrays.copyOfRange(message,
								0, 89));
						for (IADMListener listener : listeners) {
							listener.notifyTRKHeader(trkHeader);
						}
						subfileState = MessageProcessingState.TRACKPOINTS_DATA;
						counter = 0;
						currentTrackPoint = 0;
						trackPointCount = trkHeader.getTrackppointCount();
						subfileState = MessageProcessingState.TRACKPOINTS_METADATA;
						bytesToSkip = 0;
						List<MetadataDescription> headerDataDescriptions = trkHeader
								.getHeaderDataDescriptions();
						for (MetadataDescription metadataDescription : headerDataDescriptions) {
							bytesToSkip += metadataDescription.getSize();
						}
						List<MetadataDescription> dataDescriptions = trkHeader
								.getDataDescriptions();
						for (MetadataDescription metadataDescription : dataDescriptions) {
							bytesToSkip += metadataDescription.getSize();
						}
						message = new int[bytesToSkip];
//					}
				} else if (subfileState
						.equals(MessageProcessingState.TRACKPOINTS_METADATA)) {
					if (counter == bytesToSkip) {
						subfileState = MessageProcessingState.TRACKPOINTS_DATA;
						TrackMetadata trackMetadata = new TrackMetadata(
								message, trkHeader.getHeaderDataDescriptions());
						trackPointCount = (int) trackMetadata
								.getTrackppointCount();
						message = new int[21];
						counter = 0;
					}
				} else if (subfileState
						.equals(MessageProcessingState.TRACKPOINTS_DATA)) {
					if (counter == 21 && currentTrackPoint < trackPointCount) {
						TrackPointADM trackPointADM = new TrackPointADM(message);
						for (IADMListener listener : listeners) {
							listener.notifyTrackPoint(trackPointADM);
						}
						counter = 0;
						message = new int[21];
						currentTrackPoint++;
					} else if (counter == 21) {
						counter = 0;
						message = new int[21];
					}
				}
			}
		}

		return true;
	}

	@Override
	public void close() throws IOException {
		counter = 0;
		fatEnd = -1;
		blockSize = Integer.MAX_VALUE;
		fatCounter = 0;
		fats.clear();
		fatBlocks.clear();
		trackPointCount = 0;
		blockCounter = 0;
		state = MessageProcessingState.HEADER_START;
		message = new int[4096];
		totalCount = 0;
		bytesToSkip = 0;
	}

	public IMGHeader readHeader(InputStream inputStream) throws IOException {
		byte[] header = new byte[512];
		int[] intHeader = new int[512];
		int read = inputStream.read(header);
		if(read == 512) {
			for (int i = 0 ; i < 512 ; i++) {
				intHeader[i] = header[i] & 0xFF;
			}
			return new IMGHeader(intHeader);
		}
		return null;
	}

	@Override
	public List<ITrack> getTracks(CompressionType compressionType, File file)
			throws ZipException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBinary() {
		return true;
	}

	@Override
	public void addADMListener(IADMListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeADMListener(IADMListener listener) {
		listeners.remove(listener);
	}

	public int readHeaderPadding(InputStream inputStream, int blocksize) throws IOException {
		byte[] header = new byte[512];

		
		int read = inputStream.read(header);
		if(read == 512) {
			if(blocksize > 8192) {
				// there seems to be a case where the header ends at if 4096 if there are null in this 512 byte
				inputStream.read(header);
				inputStream.read(header);
				inputStream.read(header);
				inputStream.read(header);
				inputStream.read(header);
				inputStream.read(header);
				return 3584;
//				return 1024;
			}
			// else the header contains all -1 (seems to be the default garmin header)
		}
		return 512;
	}

	public List<FAT> readFats(InputStream inputStream, int bytesRead, int firstSubFileOffset) throws IOException {
		List<FAT> fats = new ArrayList<FAT>();
		int totalBytesRead = bytesRead;
		while(true) {
			byte[] header = new byte[512];
			int[] intHeader = new int[512];
			int read = inputStream.read(header);
			for (int i = 0 ; i < 512 ; i++) {
				intHeader[i] = header[i] & 0xFF;
			}
			FAT fat = new FAT(intHeader);
			if(fat.isSubfile()) {
				fats.add(fat);
			}
			totalBytesRead+=512;
			if((totalBytesRead + 512 )== firstSubFileOffset) {
				break;
			}
		}
		return fats;
	}

	public PreFATHeader readPreFatHeader(InputStream inputStream) throws IOException {
		byte[] header = new byte[512];
		int[] intHeader = new int[512];
		int read = inputStream.read(header);
		if(read == 512) {
			for (int i = 0 ; i < 512 ; i++) {
				intHeader[i] = header[i] & 0xFF;
			}
			return new PreFATHeader(intHeader);
		}
		return null;
	}

	public TRKHeader readTRKHeader(InputStream inputStream) throws IOException {
		int initialBytes = 6;
		byte[] header = new byte[initialBytes];
		int[] intHeader = new int[initialBytes];
		int read = inputStream.read(header);
		if(read == initialBytes) {
			for (int i = 0 ; i < initialBytes ; i++) {
				intHeader[i] = header[i] & 0xFF;
			}
			int[] copyOfRange = Arrays.copyOfRange(intHeader, 0, initialBytes);
			int bytesToBeRead = 89 - initialBytes; //= getInt(intHeader, 2) - initialBytes;
			header = new byte[bytesToBeRead];
			intHeader = new int[bytesToBeRead + initialBytes];
			read = inputStream.read(header);
			for (int i = 0 ; i < bytesToBeRead ; i++) {
				intHeader[i + initialBytes] = header[i] & 0xFF;
			}
			for (int i = 0; i < copyOfRange.length; i++) {
				intHeader[i] = copyOfRange[i];
			}
			
			if(read == bytesToBeRead) {
				return new TRKHeader(intHeader);
			}
			
		}
		return null;
	}
	
	private int getInt(int[] data, int start) {
 		ByteBuffer allocate = ByteBuffer.allocate(4);
 		allocate.put((byte) data[start]);
 		allocate.put((byte) data[start + 1]);
 		allocate.put((byte) data[start + 2]);
 		allocate.put((byte) data[start + 3]);
 		allocate.order(ByteOrder.LITTLE_ENDIAN);
 		allocate.flip();
		return allocate.getInt();
	}


	public TrackMetadata getTrackMetadata(InputStream inputStream, TRKHeader trkHeader1) throws IOException {
		List<MetadataDescription> headerDataDescriptions = trkHeader1
				.getHeaderDataDescriptions();
		int bytesToSkip1 = 0;
		for (MetadataDescription metadataDescription : headerDataDescriptions) {
			bytesToSkip1  += metadataDescription.getSize();
		}
		List<MetadataDescription> dataDescriptions = trkHeader1
				.getDataDescriptions();
		for (MetadataDescription metadataDescription : dataDescriptions) {
			bytesToSkip1 += metadataDescription.getSize();
		}
		byte[] header = new byte[bytesToSkip1];
		int[] intHeader = new int[bytesToSkip1];
		int read = inputStream.read(header);
		if(read == bytesToSkip1) {
			for (int i = 0 ; i < bytesToSkip1 ; i++) {
				intHeader[i] = header[i] & 0xFF;
			}
			return new TrackMetadata(intHeader, trkHeader1.getHeaderDataDescriptions());
		}
		
		return null;
	}

	public List<Measurement> extractMeasurementsFromADM(InputStream inputStream) throws IOException {
		int bytesToBeRead = 21;
		byte[] header = new byte[bytesToBeRead];
		int[] intHeader = new int[bytesToBeRead];
		int read = inputStream.read(header);
		for (int i = 0 ; i < bytesToBeRead ; i++) {
			intHeader[i] = header[i] & 0xFF;
		}
		TrackPointADM trackPointADM = new TrackPointADM(intHeader);
		List<Measurement> measurements = extractMeasurements(trackPointADM);
		 
		return measurements;
	}

	private List<Measurement> extractMeasurements(TrackPointADM trackPointADM) {
		List<Measurement> measurements = new ArrayList<Measurement>();
		MeasuredPosition3D position3d = GeoFactory.eINSTANCE.createMeasuredPosition3D();
		Latitude latitude2 = GeoFactory.eINSTANCE.createLatitude();
		latitude2.setDecimalDegree(trackPointADM.getLat());
		position3d.setLatitude(latitude2);
		Longitude longitude2 = GeoFactory.eINSTANCE.createLongitude();
		longitude2.setDecimalDegree(trackPointADM.getLon());
		position3d.setLongitude(longitude2);
		position3d.setValid(true);
		if(trackPointADM.getTimestamp() != 0) {
			position3d.setTime(new Date(trackPointADM.getTimestamp() * 1000L + 688344865000L));
		}
		
		double depth = (double)trackPointADM.getDepth();
		if(depth < 1000 && depth >= 0) {
			Depth depth2 = GeoFactory.eINSTANCE.createDepth();
			depth2.setDepth(depth);
			depth2.setValid(true);
			measurements.add(depth2);
		}
		
		measurements.add(position3d);
		return measurements;
	}

	public void skipBlock(InputStream inputStream, int blockSize2) throws IOException {
		inputStream.read(new byte[blockSize2]);
	}
	
	private FAT getFatByBlock(List<FAT> fats, int startBlock) {
		for (FAT fat : fats) {
			for (Short blockNumber : fat.getBlockNumbers()) {
				if(blockNumber == startBlock) {
					return fat;
				}
			}
		}
		return null;
	}


}
