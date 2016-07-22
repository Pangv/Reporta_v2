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
    
    
    
    public void setAttributes(Map<Integer, String> attributes){
        saxParser.createSAXParser();
        saxParser.setFilename(this.filename);
        saxParser.parseXMLDocument();
        
        
      
        
    }
    
    public ConfigDocument(String filename) {
        this.filename = filename;
        setAttributes(this.attributes);
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
