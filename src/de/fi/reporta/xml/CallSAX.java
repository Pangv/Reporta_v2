package de.fi.reporta.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class CallSAX {

    private final String FILE_SEPERATOR = File.separator;

    private static SAXParserFactory factory;
    private static XMLReader xmlReader;
    private static SAXParser saxParser;

    public void createSAXParser() {
        factory = SAXParserFactory.newInstance();
        try {
            saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        xmlReader.setContentHandler(new ConfigHandler());
        xmlReader.setErrorHandler(new ConfigErrorHandler());
    }

    public void parseXMLDocument(String filename) {
        try {
            String file = new File("./assets" + FILE_SEPERATOR + filename).toURI().toURL().toString();
            System.out.println(file);
            xmlReader.parse(file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
