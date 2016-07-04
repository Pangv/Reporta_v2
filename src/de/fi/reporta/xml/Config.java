package de.fi.reporta.xml;


public class Config {

    private String delimeter;
    private String doctype;

    public void addConfig(String doctype, String delimeter){
        this.doctype = doctype;
        this.delimeter = delimeter;
    }

    public String getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }
}
