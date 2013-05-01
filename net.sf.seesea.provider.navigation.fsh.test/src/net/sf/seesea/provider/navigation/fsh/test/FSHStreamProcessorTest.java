package net.sf.seesea.provider.navigation.fsh.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import junit.framework.TestCase;
import net.sf.seesea.provider.navigation.fsh.FSHStreamProcessor;
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
	
}
