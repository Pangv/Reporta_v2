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


    private String filename;
    private static SAXParserFactory factory;
    private static XMLReader xmlReader;
    private static SAXParser saxParser;

    private final String FILE_SEPERATOR = File.separator;

    public String getFilename(){
        return filename;
    }
    public void setFilename(String filename){
        this.filename = "assets" + FILE_SEPERATOR + filename;
    }

    public static void createSAXParser(){
        factory = SAXParserFactory.newInstance();
        try {
            saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        }


        xmlReader.setContentHandler(new ConfigHandler());
        xmlReader.setErrorHandler(new ConfigErrorHandler());
    }

    public void parseXMLDocument(){
        try {
            xmlReader.parse(new File(this.filename).toURI().toURL().toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }
    }


}
