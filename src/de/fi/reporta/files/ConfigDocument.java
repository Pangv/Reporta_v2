package de.fi.reporta.files;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import de.fi.reporta.xml.CallSAX;

public class ConfigDocument {

    private String filename;

    private Map<Integer, String> attributes = new HashMap<Integer, String>(); // any number of attributes in an input xml
    private CallSAX saxParser;

    ConfigDocument(String filename) {
        this.filename = filename;
        introduceAttributes(this.attributes);
    }

    public void introduceAttributes(Map<Integer, String> attributes) {
        // TODO: remove duplicate parser-creation; only one is needed
        saxParser = new CallSAX();
        saxParser.createSAXParser();
        saxParser.parseXMLDocument(this.filename);
        saxParser.setAttributes();
        this.attributes = saxParser.getAttributes();

    }

    String getDoctype(){
        return saxParser.getConfigHandler().getMainAttributes(0);
    }

    String getDelimiter(){
        return saxParser.getConfigHandler().getMainAttributes(1);
    }

    Map<Integer, String> getAttributes(){
        return this.attributes;
    }

}

