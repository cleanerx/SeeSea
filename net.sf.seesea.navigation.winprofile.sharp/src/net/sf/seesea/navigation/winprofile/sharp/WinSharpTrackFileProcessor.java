package net.sf.seesea.navigation.winprofile.sharp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import net.sf.seesea.services.navigation.IMeasurmentProcessor;
import net.sf.seesea.services.navigation.ITrackFile;
import net.sf.seesea.services.navigation.ITrackFileProcessor;
import net.sf.seesea.services.navigation.InputStreamNotFoundException;
import net.sf.seesea.services.navigation.ProcessingException;
import net.sf.seesea.services.navigation.SensorDescriptionUpdateRate;
import net.sf.seesea.model.core.physx.Measurement;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class WinSharpTrackFileProcessor implements ITrackFileProcessor {

	private IMeasurmentProcessor measurmentProcessor;

	@Override
	public void processFile(ITrackFile trackFile) throws FileNotFoundException,
			IOException, ProcessingException {
		
		try {
			InputSource inputSource = new InputSource(trackFile.getInputStream());
			FullWinProfileHandler winprofileHandler = new FullWinProfileHandler(measurmentProcessor, trackFile);
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			xmlReader.setContentHandler(winprofileHandler);
			xmlReader.setErrorHandler(new ErrorHandler() {
				
				@Override
				public void warning(SAXParseException exception) throws SAXException {
//					exception.printStackTrace();
				}
				
				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
//					exception.printStackTrace();
				}
				
				@Override
				public void error(SAXParseException exception) throws SAXException {
//					exception.printStackTrace();
				}
			});
			xmlReader.parse(inputSource);
		} catch (InputStreamNotFoundException e) {
			throw new ProcessingException(e);
		}
	catch (SAXException e) {
	 throw new ProcessingException(e);		}
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
		return false;
	}

	@Override
	public boolean hasAbsoluteTime() {
		return true;
	}

}
