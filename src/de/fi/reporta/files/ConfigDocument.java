package de.fi.reporta.files;


public class ConfigDocument {


    private String delimeter;
    private String doctype;

    
    public ConfigDocument(String delimeter, String doctype) {
        this.doctype = doctype;
        this.delimeter = delimeter;
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
}
