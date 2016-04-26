package net.sf.seesea.provider.navigation.fsh.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import junit.framework.TestCase;
import net.sf.seesea.provider.navigation.fsh.FSHStreamProcessor;
import net.sf.seesea.provider.navigation.fsh.FlobHeader;
import net.sf.seesea.provider.navigation.fsh.data.FSHBlock;
import net.sf.seesea.provider.navigation.fsh.data.FSHHeader;
import net.sf.seesea.track.api.exception.NMEAProcessingException;
import net.sf.seesea.track.api.exception.RawDataEventException;

public class FSHStreamProcessorTest extends TestCase {

	public void testIsValidStreamProcessor() throws IOException, RawDataEventException {
		String file = "res/archive2.fsh"; //$NON-NLS-1$
		URL fileEntry = FshTestActivator.getContext().getBundle().getEntry(file);
		InputStream fileStream = fileEntry.openStream();
		BufferedInputStream input = new BufferedInputStream(fileStream);

		int[] buf = new int[100];
		for(int i = 0; i < 100 ; i ++) {
			buf[i] = input.read();
		}
		
		FSHStreamProcessor fshStreamProcessor = new FSHStreamProcessor();
		assertTrue(fshStreamProcessor.isValidStreamProcessor(buf));
	}
	
	public void testBasicFileRead() throws IOException {
		String file = "res/archive2.fsh"; //$NON-NLS-1$
		URL fileEntry = FshTestActivator.getContext().getBundle().getEntry(file);
		InputStream fileStream = fileEntry.openStream();
		BufferedInputStream input = new BufferedInputStream(fileStream);
		FSHStreamProcessor fshStreamProcessor = new FSHStreamProcessor();
		FSHHeader fshHeader = fshStreamProcessor.readHeader(input);
		
		for(int i = 0 ; i < 8 ; i++) {
			FlobHeader flobHeader = fshStreamProcessor.readFlobHeader(input);
			FSHBlock fshBlock = fshStreamProcessor.readBlock(input);
			while(fshBlock != null) {
				byte[] message = new byte[fshBlock.getLength()];
				input.read(message);
				System.out.println("Type" + fshBlock.getType() + " : Length" + fshBlock.getLength());
				fshBlock = fshStreamProcessor.readBlock(input);
				if(fshBlock != null) {
					assertTrue("Block types should exist", fshBlock.getType() == 13 || fshBlock.getType() == 14 || fshBlock.getType() == 65535);
				}
			}
		}
		
	}
	
	
	
}
