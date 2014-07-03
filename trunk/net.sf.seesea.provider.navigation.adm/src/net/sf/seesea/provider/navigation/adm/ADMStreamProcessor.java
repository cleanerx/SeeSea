package net.sf.seesea.provider.navigation.adm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipException;

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


	public ADMStreamProcessor() {
		blockSize = Integer.MAX_VALUE;
		listeners = new ArrayList<>();
		fatCounter = 0;
		fats = new ArrayList<>();
		totalCount = 0;
		listeners.add(new TrackPointReader());
		state = MessageProcessingState.HEADER_START;
		subfileState = MessageProcessingState.SUBFILE_HEADER;
	}
	
	@Override
	public String getMimeType() {
		return "application/x-adm"; //$NON-NLS-1$
	}

	@Override
	public boolean isValidStreamProcessor(int[] buffer)
			throws NMEAProcessingException {
		return true; // FIXME
	}

	@Override
	public boolean readByte(int c, String streamProvider)
			throws NMEAProcessingException {
//		if(counter == message.length) {
//			throw new NMEAProcessingException("No usable data found within " + counter + "bytes");
//		}
		totalCount++;
		if(state.equals(MessageProcessingState.HEADER_START)) {
			message[counter++] = c;
			if(counter == 512) {
				state = MessageProcessingState.HEADER_END;
				IMGHeader imgHeader = new IMGHeader(message);
				for (IADMListener listener : listeners) {
					listener.notifyIMGHeader(imgHeader);
				}
				blockSize = imgHeader.getBlockSize();
			}
		} else if(state.equals(MessageProcessingState.HEADER_END)) {
			counter++;
//			message[counter++] = c;
			if(counter == 4096) {
				counter = 0;
				message = new int[512];
				message[counter++] = c;
				state = MessageProcessingState.XSTART;
			}
		} else if(state.equals(MessageProcessingState.XSTART)) {
			message[counter++] = c;
			if(counter == 512) {
				PreFATHeader preFATHeader = new PreFATHeader(Arrays.copyOfRange(message, 0, 512));
				for (IADMListener listener : listeners) {
					listener.notifyPreFATHeader(preFATHeader);
				}
				fatEnd = preFATHeader.getFirstSubFileOffset();
				fatBlocks = preFATHeader.getBlockNumbers();
				state = MessageProcessingState.FAT;
				counter = 0;
				message = new int[512];
//				message[counter++] = c;
			}
		} else if(state.equals(MessageProcessingState.FAT)) {
			message[counter++] = c;
			if(counter == 512) {
				FAT fat = new FAT(Arrays.copyOfRange(message, 0, 512));
				fats.add(fat);
				if(fat.isSubfile()) {
					for (IADMListener listener : listeners) {
						listener.notifyFATBlock(fat);
					}
				}
				counter = 0;
				message = new int[512];
				fatCounter++;
			}
			if(totalCount == fatEnd) {
				state = MessageProcessingState.SUBFILE;
				blockCounter = fatBlocks.get(fatBlocks.size() - 1) + 1;
				for (FAT fat : fats) {
					for (Short blockNumber : fat.getBlockNumbers()) {
						if(blockNumber == blockCounter) {
							currentFat = fat;
						}
					}
				}
				counter = 0;
			}
		} else if(state.equals(MessageProcessingState.SUBFILE)) {
			counter++;
			if(currentFat.getBlockNumbers().size() * blockSize == counter) {
				counter = 0;
				blockCounter += currentFat.getBlockNumbers().size();
				for (FAT fat : fats) {
					for (Short blockNumber : fat.getBlockNumbers()) {
						if(blockNumber == blockCounter) {
							currentFat = fat;
							subfileState = MessageProcessingState.SUBFILE_HEADER;
							break;
						}
					}
				}
			}
			if(currentFat.getSubFileType().equals("TRK")) {
				if(subfileState.equals(MessageProcessingState.SUBFILE_HEADER)) {
					if(counter == 89) {
						trkHeader = new TRKHeader(Arrays.copyOfRange(message, 0, 89));
						for (IADMListener listener : listeners) {
							listener.notifyTRKHeader(trkHeader);
						}
						subfileState = MessageProcessingState.TRACKPOINTS_DATA;
						counter = 0;
						currentTrackPoint = 0;
						trackPointCount = trkHeader.getTrackppointCount();
						subfileState = MessageProcessingState.TRACKPOINTS_METADATA;
						bytesToSkip = 0;
						List<MetadataDescription> headerDataDescriptions = trkHeader.getHeaderDataDescriptions();
						for (MetadataDescription metadataDescription : headerDataDescriptions) {
							bytesToSkip += metadataDescription.getSize();
						}
						List<MetadataDescription> dataDescriptions = trkHeader.getDataDescriptions();
						for (MetadataDescription metadataDescription : dataDescriptions) {
							bytesToSkip += metadataDescription.getSize();
						}
						message = new int[bytesToSkip];
					}
				} else if(subfileState.equals(MessageProcessingState.TRACKPOINTS_METADATA)) {
					if(counter == bytesToSkip) {
						subfileState = MessageProcessingState.TRACKPOINTS_DATA;
						TrackMetadata trackMetadata = new TrackMetadata(message, trkHeader.getHeaderDataDescriptions());
						trackPointCount = (int)trackMetadata.getTrackppointCount();
						message = new int[21];
						counter = 0;
					}
				} else if(subfileState.equals(MessageProcessingState.TRACKPOINTS_DATA)) {
					if(counter == 21 && currentTrackPoint < trackPointCount) {
						TrackPointADM trackPointADM = new TrackPointADM(message);
						for (IADMListener listener : listeners) {
							listener.notifyTrackPoint(trackPointADM);
						}
						counter = 0;
						message = new int[21];
						currentTrackPoint++;
					} else if(counter == 21) {
						counter = 0;
						message = new int[21];
					}
				}
				message[counter] = c;
			}
		}
		
		return true;	}

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
		int[] header = new int[512];
		for(int i = 0 ; i < 512; i++) {
			header[i] = inputStream.read();
		}
		return new IMGHeader(header);
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


}
