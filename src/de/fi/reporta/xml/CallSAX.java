package de.fi.reporta.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.InterfaceAddress;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class CallSAX {

    private final String FILE_SEPERATOR = File.separator;

    private SAXParserFactory factory;
    private XMLReader xmlReader;
    private SAXParser saxParser;

    private ConfigHandler configHandler;
    private ConfigErrorHandler configErrorHandler;

    private Map<Integer, String> attributes = new HashMap<Integer, String>();

    public ConfigHandler getConfigHandler() {
        return this.configHandler;
    }

    public void setAttributes() {
        this.attributes = configHandler.getSecondaryAttributes();
    }

    public Map<Integer, String> getAttributes() {
        return this.attributes;
    }


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

        xmlReader.setContentHandler(this.configHandler = new ConfigHandler(this.attributes));
        xmlReader.setErrorHandler(this.configErrorHandler = new ConfigErrorHandler());
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
