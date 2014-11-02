package net.sf.seesea.provider.navigation.fsh.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;
import net.sf.seesea.provider.navigation.fsh.FSHStreamProcessor;
import net.sf.seesea.provider.navigation.fsh.FlobHeader;
import net.sf.seesea.provider.navigation.fsh.data.FSHBlock;
import net.sf.seesea.provider.navigation.fsh.data.FSHHeader;
import net.sf.seesea.services.navigation.NMEAProcessingException;

public class FSHStreamProcessorTest extends TestCase {

	public void input() throws IOException, NMEAProcessingException {
		String file = "res/ARCHIVE2.FSH"; //$NON-NLS-1$
		System.out.println(new File(file).getAbsoluteFile());
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

		int[] buf = new int[100];
		for(int i = 0; i < 100 ; i ++) {
			buf[i] = input.read();
		}
		
		FSHStreamProcessor fshStreamProcessor = new FSHStreamProcessor();
		assertTrue(fshStreamProcessor.isValidStreamProcessor(buf));
	}
	
	public void testX() throws IOException {
		String file = "res/219.dat"; //$NON-NLS-1$
		System.out.println(new File(file).getAbsoluteFile());
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
		FSHStreamProcessor fshStreamProcessor = new FSHStreamProcessor();
		FSHHeader fshHeader = fshStreamProcessor.readHeader(input);
		int hallo = 0;
		
		for(int i = 0 ; i < 8 ; i++) {
			FlobHeader flobHeader = fshStreamProcessor.readFlobHeader(input);
			FSHBlock fshBlock = fshStreamProcessor.readBlock(input);
			while(fshBlock != null) {
				byte[] message = new byte[fshBlock.getLength()];
				input.read(message);
				System.out.println("Type" + fshBlock.getType() + " : Length" + fshBlock.getLength());
				fshBlock = fshStreamProcessor.readBlock(input);
				hallo++;
			}
		}
		
	}
	
	
	
}
