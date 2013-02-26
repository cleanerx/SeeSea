package net.sf.seesea.provider.navigation.ais;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.seesea.services.navigation.INMEAReader;
import net.sf.seesea.services.navigation.NMEAProcessingException;
import net.sf.seesea.services.navigation.RawDataEvent;
import net.sf.seesea.services.navigation.RawDataEventListener;
import nl.esi.metis.aisparser.AISMessage;
import nl.esi.metis.aisparser.AISParser;
import nl.esi.metis.aisparser.AisMessageMultiplexer;
import nl.esi.metis.aisparser.DefaultErrorHandler;
import nl.esi.metis.aisparser.HandleAISMessage;
import nl.esi.metis.aisparser.provenance.FileSource;

public class AISParserProvider implements HandleAISMessage, RawDataEventListener  {

	private INMEAReader _nmeaReader;

	private AISParser aisParser;
	
	private List<HandleAISMessage> handlers;

	private int lineNumber;
	
	public AISParserProvider() {
		handlers = new ArrayList<HandleAISMessage>();
	}
	
	public void activate() {
		aisParser = new AISParser(this, new DefaultErrorHandler());
	}
	
	public void deactivate() {
		aisParser.finished();
	}
	
	public synchronized void addNMEAReader(INMEAReader nmeaReader) {
		this._nmeaReader = nmeaReader;
		_nmeaReader.addAISEventListener(this);
	}
	
	public synchronized void removeNMEAReader(INMEAReader nmeaReader) {
		_nmeaReader.removeAISEventListener(this);
		this._nmeaReader = null;
	}
	
	public void addAISHandler(HandleAISMessage handleAISMessage) {
		handlers.add(handleAISMessage);
	}
	
	public void removeAISHandler(HandleAISMessage handleAISMessage) {
		handlers.remove(handleAISMessage);
	}

	@Override
	public void handleAISMessage(AISMessage message) {
		for (HandleAISMessage handleAISMessage : handlers) {
			handleAISMessage.handleAISMessage(message);
		}
	}

	@Override
	public void receiveRawDataEvent(RawDataEvent e)
			throws NMEAProcessingException {
		File file = new File("."); //$NON-NLS-1$
		FileSource fileSource = new FileSource(file, lineNumber++, e.getNmeaMessageContent(), Calendar.getInstance().getTime().getTime());
		aisParser.handleSensorData(fileSource, e.getNmeaMessageContent().trim());
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}
	
}
