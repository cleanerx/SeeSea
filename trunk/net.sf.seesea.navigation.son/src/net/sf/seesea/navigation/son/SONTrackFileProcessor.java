package net.sf.seesea.navigation.son;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.Latitude;
import net.sf.seesea.model.core.geo.Longitude;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;
import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.Measurement;
import net.sf.seesea.model.core.physx.PhysxFactory;
import net.sf.seesea.model.core.physx.RelativeSpeed;
import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.navigation.son.data.SONHeader;
import net.sf.seesea.navigation.son.data.SONRoot;
import net.sf.seesea.navigation.son.data.ZippedSonTrack;
import net.sf.seesea.track.api.IMeasurmentProcessor;
import net.sf.seesea.track.api.ITrackFileProcessor;
import net.sf.seesea.track.api.data.CompressionType;
import net.sf.seesea.track.api.data.ITrack;
import net.sf.seesea.track.api.data.ITrackFile;
import net.sf.seesea.track.api.data.SensorDescriptionUpdateRate;
import net.sf.seesea.track.api.exception.ProcessingException;

public class SONTrackFileProcessor implements ITrackFileProcessor {

	private IMeasurmentProcessor measurmentProcessor;

	@Override
	public void processFile(ITrackFile trackFile) throws FileNotFoundException,
			IOException, ProcessingException {
		if(CompressionType.ZIP.equals(trackFile.getCompression())) {

			SONStreamProcessor sonStreamProcessor = new SONStreamProcessor();
			if(trackFile instanceof ZippedSonTrack) {
				List<ITrack> tracks = sonStreamProcessor.getTracks(trackFile.getCompression(), ((ZippedSonTrack) trackFile).getZipFile());
				for (ITrack track : tracks) {
					ZippedSonTrack sonTrack = (ZippedSonTrack) track;
					ZipFile file = sonTrack.getZipFile();
					InputStream rootInputStream = file.getInputStream(sonTrack.getRootFile());
					SONRoot sonRoot = sonStreamProcessor.readDat(rootInputStream);
					
					Map<ZipEntry, ZipEntry> index2Beams = sonTrack.getIndex2Beams();
					for (Entry<ZipEntry, ZipEntry> index2Beam : index2Beams.entrySet()) {
						InputStream indexInputStream = file.getInputStream(index2Beam.getKey());
						InputStream dataInputStream = file.getInputStream(index2Beam.getValue());
						Map<Integer, Integer> index = sonStreamProcessor.readIndex(indexInputStream);
						File tempFile = File.createTempFile("humminbird", "son"); //$NON-NLS-1$ //$NON-NLS-2$
						FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
						byte[] buffer  = new byte[32*1024];
						 int bytesRead;
					      try {
					    	  while ((bytesRead = dataInputStream.read(buffer)) != -1)
					    	    {
					    		  fileOutputStream.write(buffer, 0, bytesRead);
					    	    }
					      } finally {
					    	  fileOutputStream.close();
					      }
						      
						tempFile.deleteOnExit();
						// only the first index file should have depth data, so iterating must be safe
						for (Entry<Integer, Integer> indexEntry : index.entrySet()) {
					        dataInputStream = new BufferedInputStream(new FileInputStream(tempFile));
							long skip = dataInputStream.skip(indexEntry.getValue());
//							System.out.println(skip);
							SONHeader sonBlock = sonStreamProcessor.readBlock(dataInputStream);
//							dataInputStream.close();
//							dataInputStream.skip(sonBlock.getDataLen());
							if(sonBlock != null && sonBlock.getDataLen() > 0) {
								GeoFactory geoFactory = GeoFactory.eINSTANCE;
								PhysxFactory physxFactory = PhysxFactory.eINSTANCE;
								Date recordingTime = new Date(sonRoot.getRecordingStart().getTime() + (long)sonBlock.getTimeSinceStart());
								
								MeasuredPosition3D geoPosition = geoFactory.createMeasuredPosition3D();
								Latitude latitude = GeoFactory.eINSTANCE.createLatitude();
								latitude.setDecimalDegree(sonBlock.getLatitude());
								Longitude longitude = GeoFactory.eINSTANCE.createLongitude();
								longitude.setDecimalDegree(sonBlock.getLongitude());
								geoPosition.setLatitude(latitude);
								geoPosition.setLongitude(longitude);
								geoPosition.setTime(recordingTime);
								geoPosition.setValid(true);
								
								Heading heading = physxFactory.createHeading();
								heading.setHeadingType(HeadingType.COG);
								heading.setDegrees(sonBlock.getGpsHeading());
								heading.setValid(true);
								
								RelativeSpeed relativeSpeed = physxFactory.createRelativeSpeed();
								Speed speed = physxFactory.createSpeed();
								speed.setSpeed(sonBlock.getGpsSpeed());
								speed.setSpeedUnit(SpeedUnit.N);
								relativeSpeed.setKey(SpeedType.COG);
								relativeSpeed.setValue(speed);
								relativeSpeed.setValid(true);
								
								Depth depth = GeoFactory.eINSTANCE.createDepth();
								depth.setMeasurementPosition(RelativeDepthMeasurementPosition.TRANSDUCER);
								depth.setDepth(sonBlock.getDepth() / 10.0);
								depth.setValid(true);
								
								List<Measurement> results = new ArrayList<Measurement>(4);
								results.add(geoPosition);
								results.add(heading);
								results.add(depth);
								measurmentProcessor.processMeasurements(results, "xxx", trackFile.getTrackId(), trackFile.getBoundingBox());
							}
							dataInputStream.close();
						}
						tempFile.delete();
					}
				}
			}
		}
	}

	@Override
	public void setMeasurementProcessor(IMeasurmentProcessor measurmentProcessor) {
		this.measurmentProcessor = measurmentProcessor;
	}

	@Override
	public boolean hasTimedMeasurments() {
		return true;
	}

	@Override
	public void setFilter(Set<SensorDescriptionUpdateRate<Measurement>> bestSensors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasRelativeTime() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAbsoluteTime() {
		// TODO Auto-generated method stub
		return false;
	}

}
