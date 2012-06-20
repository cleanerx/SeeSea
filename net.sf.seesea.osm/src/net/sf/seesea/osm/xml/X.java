/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.osm.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.sf.seesea.model.int1.base.AbstractSeamark;

import org.eclipse.emf.common.util.EList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class X {

  /**
   * Find all way names in the given OSM file.
   * @param file The file.
   * @return The sorted set of way names.
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  public SortedSet<String> findWayNames(InputStream inputStream) throws ParserConfigurationException,
      SAXException, IOException {
    SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    //parserFactory.setNamespaceAware(true);
    SAXParser parser = parserFactory.newSAXParser();

    OsmNamesHandler handler = new OsmNamesHandler();
    parser.parse(inputStream, handler);
    
    return handler.getNames();
  }

  private static class OsmNamesHandler extends DefaultHandler {
    private final SortedSet<String> nameSet = new TreeSet<String>();
    private final Stack<String> eleStack = new Stack<String>();
    
    public SortedSet<String> getNames() {
      return nameSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
        Attributes attrs) throws SAXException {
      // System.out.printf("element: uri=%s localName=%s qName=%s\n", uri, localName, qName);
      if ("tag".equals(qName) && "way".equals(eleStack.peek())) {
        String key = attrs.getValue("k");
        if ("name".equals(key)) {
          nameSet.add(attrs.getValue("v"));
        }
      }
      eleStack.push(qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException {
      eleStack.pop();
    }
  }

  private void printWayNames(SortedSet<String> nameSet, PrintStream out) {
    for (String name : nameSet) {
      out.println(name);
    }
  }
  
  /**
   * Downlaod OSM sample file.
   * @param file The file to save the data into.
   * @throws IOException
   */
  public void downloadSample(File file) throws IOException {
    URL downloadUrl = new URL("http://api.openstreetmap.org/api/0.6/map?bbox=11.62973,52.12236,11.63736,52.12853");
    InputStream in = downloadUrl.openStream();
    OutputStream out = new FileOutputStream(file);
    byte[] buffer = new byte[10000];
    try {
      int len = in.read(buffer);
      while (len > 0) {
        out.write(buffer, 0, len);
        len = in.read(buffer);
      }
    } finally {
      out.close();
      in.close();
    }
  }

  /**
   * MAIN.
   * @param args ignored.
   * @throws Exception If an error occurs.
   */
  public static void main(String[] args) throws Exception {
	  OpenStreetMapReader openStreetMapReader = new OpenStreetMapReader();
//    X osmSaxNames = new X();
//    URL entry = OpenSeaMapActivator.getDefault().getBundle().getEntry("res/map.osm");
    long currentTimeMillis = System.currentTimeMillis();
//    File file = new File("C:\\Dokumente und Einstellungen\\jens\\Eigene Dateien\\Downloads\\map.osm");
    File file = new File("/mnt/upload/planet-latest.osm");
//    SortedSet<String> nameSet = osmSaxNames.findWayNames(new FileInputStream(file));
//    osmSaxNames.printWayNames(nameSet, System.out);
    EList<AbstractSeamark> list = openStreetMapReader.read(new FileInputStream(file));
    for (AbstractSeamark abstractSeamark : list) {
		System.out.println(abstractSeamark.toString());
	}
    long currentTimeMillis2 = System.currentTimeMillis();
    System.out.println( currentTimeMillis2 -currentTimeMillis);
  }

}
