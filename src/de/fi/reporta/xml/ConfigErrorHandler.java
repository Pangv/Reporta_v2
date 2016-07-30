package de.fi.reporta.xml;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigErrorHandler extends DefaultHandler{

    public void warning(SAXParseException e){
        System.out.println("Warnung:");
        showSpecifics(e);
        System.out.println();
    }
    public void error(SAXParseException e){
        System.out.println("Fehler:");
        showSpecifics(e);
        System.out.println();
    }
    public void fatalError(SAXParseException e){
        System.out.println("Schwerer Fehler:");
        showSpecifics(e);
        System.out.println();
    }
    public void showSpecifics(SAXParseException e){
        System.out.println(e.getMessage());
        System.out.println(" Zeile " + e.getLineNumber());
        System.out.println(" Spalte " + e.getColumnNumber());
        System.out.println(" Dokument " + e.getSystemId());
    }

}
