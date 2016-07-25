package de.fi.reporta.files;

import java.util.HashMap;
import java.util.Map;

import de.fi.reporta.xml.CallSAX;


public class ConfigDocument {

    private String filename;
    private String delimeter;
    private String doctype;
    
    private Map<Integer, String> attributes = new HashMap<Integer, String>(); // any number of attributes in an input xml
    private CallSAX saxParser;
    
    public void introduceAttributes(Map<Integer, String> attributes){
        // TODO: remove duplicate parser-creation only one is needed
        saxParser = new CallSAX();
        saxParser.createSAXParser();
        saxParser.parseXMLDocument(this.filename);
    }
    
    public ConfigDocument(String filename) {
        this.filename = filename;
        introduceAttributes(this.attributes);
    }
    

    public String getDelimeter() {
        return this.delimeter;
    }
    
    public String getDoctype(){
        return this.doctype;
    }

    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }
    
    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }
}
