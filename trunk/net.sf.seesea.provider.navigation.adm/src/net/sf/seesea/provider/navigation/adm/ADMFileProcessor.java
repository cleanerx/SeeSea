package net.sf.seesea.provider.navigation.adm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.provider.navigation.adm.data.FAT;
import net.sf.seesea.provider.navigation.adm.data.IMGHeader;
import net.sf.seesea.provider.navigation.adm.data.TRKHeader;
import net.sf.seesea.provider.navigation.adm.data.TrackMetadata;
import net.sf.seesea.services.navigation.IMeasurmentProcessor;
import net.sf.seesea.services.navigation.ITrackFile;
import net.sf.seesea.services.navigation.ITrackFileProcessor;
import net.sf.seesea.services.navigation.InputStreamNotFoundException;
import net.sf.seesea.services.navigation.ProcessingException;
import net.sf.seesea.services.navigation.SensorDescriptionUpdateRate;

public class ADMFileProcessor implements ITrackFileProcessor {

	private IMeasurmentProcessor measurmentProcessor;

	
	@Override
	public void processFile(ITrackFile trackFile)
			throws FileNotFoundException, IOException, ProcessingException {
		try {
			InputStream inputStream = trackFile.getInputStream();
			ADMStreamProcessor streamProcessor = new ADMStreamProcessor();
			IMGHeader imgHeader = streamProcessor.readHeader(inputStream);
			int bytesRead = streamProcessor.readHeaderPadding(inputStream, imgHeader.getBlockSize());
			PreFATHeader preFatHeader = streamProcessor.readPreFatHeader(inputStream);
			bytesRead+=512;
			int firstSubFileOffset = preFatHeader.getFirstSubFileOffset();
			List<FAT> readFats = streamProcessor.readFats(inputStream, bytesRead, firstSubFileOffset);
			bytesRead = firstSubFileOffset;
			int currentBlock = bytesRead / imgHeader.getBlockSize();
			for (int i = 0 ; i< readFats.size() ; i++) {
				FAT currentFATBlock = getFatByBlock(readFats, currentBlock);
				currentBlock += currentFATBlock.getBlockNumbers().size();
				if(currentFATBlock.getSubFileType().equals("TRK")) {
					TRKHeader trkHeader = streamProcessor.readTRKHeader(inputStream);
					long limit = trkHeader.getLength();
					
					x: while(true) {
						TrackMetadata trackMetadata = streamProcessor.getTrackMetadata(inputStream, trkHeader);
						long size = (21L * trackMetadata.getTrackppointCount()) + trackMetadata.getBegin();
						if(size <= limit) {
							for (int k = 0 ; k < trackMetadata.getTrackppointCount() - 1 ; k++) {
								List<Measurement> measurements = streamProcessor.extractMeasurementsFromADM(inputStream);
								measurmentProcessor.processMeasurements(measurements, "none", trackFile.getTrackId(), trackFile.getBoundingBox());
							}
							if(size >= limit) {
								break x;
							}
						}
					}
				} else {
					for(int j = 0 ; j < currentFATBlock.getBlockNumbers().size() ; j++) {
						streamProcessor.skipBlock(inputStream, imgHeader.getBlockSize());
					}
				}
			}
			return;
		} catch (InputStreamNotFoundException e) {
			throw new ProcessingException(e);
		}
		

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

	@Override
	public void setMeasurementProcessor(IMeasurmentProcessor measurmentProcessor) {
		this.measurmentProcessor = measurmentProcessor;
	}

	@Override
	public boolean hasTimedMeasurments() {
		return false;
	}

	@Override
	public void setFilter(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasRelativeTime() {
		return false;
	}

	@Override
	public boolean hasAbsoluteTime() {
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		ADMFileProcessor admFileProcessor = new ADMFileProcessor();
//		InputStream inputStream = new FileInputStream("C:\\pv5\\15249.dat");
//		InputStream inputStream = new FileInputStream("S:\\Segeln\\Data\\8921.dat");
		InputStream inputStream = new FileInputStream("S:\\Segeln\\Data\\15249.dat");
//		InputStream inputStream = new FileInputStream("S:\\Segeln\\Data\\9152.dat");
		ADMStreamProcessor streamProcessor = new ADMStreamProcessor();
		IMGHeader imgHeader = streamProcessor.readHeader(inputStream);
		int bytesRead = streamProcessor.readHeaderPadding(inputStream, imgHeader.getBlockSize());
		PreFATHeader preFatHeader = streamProcessor.readPreFatHeader(inputStream);
		bytesRead+=512;
		int firstSubFileOffset = preFatHeader.getFirstSubFileOffset();
		List<FAT> readFats = streamProcessor.readFats(inputStream, bytesRead, firstSubFileOffset);
		bytesRead = firstSubFileOffset;
		int currentBlock = bytesRead / imgHeader.getBlockSize();
		for (int i = 0 ; i< readFats.size() ; i++) {
			FAT currentFATBlock = admFileProcessor.getFatByBlock(readFats, currentBlock);
			currentBlock += currentFATBlock.getBlockNumbers().size();
			if(currentFATBlock.getSubFileType().equals("TRK")) {
				TRKHeader trkHeader = streamProcessor.readTRKHeader(inputStream);
				long limit = trkHeader.getLength();
				
				x: while(true) {
					TrackMetadata trackMetadata = streamProcessor.getTrackMetadata(inputStream, trkHeader);
					long size = (21L * trackMetadata.getTrackppointCount()) + trackMetadata.getBegin();
//					trackMetadata.getTrackppointCount()
					if(size <= limit) {
						for (int k = 0 ; k < trackMetadata.getTrackppointCount() - 1 ; k++) {
							List<Measurement> measurements = streamProcessor.extractMeasurementsFromADM(inputStream);
							for (Iterator iterator = measurements.iterator(); iterator
									.hasNext();) {
								Measurement measurement = (Measurement) iterator.next();
								System.out.println("k" + k + ":" + measurement.toString());
							}
						}
//							continue;
						if(size >= limit) {
							break x;
						}
					}
				}
//				for(int l = 0 ; l < trkHeader.getTrackCount() ; l++) {
//					measurmentProcessor.processMeasurements(measurements, "none", trackFile.getTrackId(), trackFile.getBoundingBox());
//				}
			} else {
				for(int j = 0 ; j < currentFATBlock.getBlockNumbers().size() ; j++) {
					streamProcessor.skipBlock(inputStream, imgHeader.getBlockSize());
				}
			}
		}
		return;
	}

}
