package de.fi.reporta.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigHandler extends DefaultHandler {

    private String doctype;
    private String delimeter;


    public void startDocument() throws SAXException {
        System.out.println("Dokumentenstart!");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        System.out.println("Start-Tag: " + qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.println("Attribut: " + attributes.getQName(i) + " = " + attributes.getValue(i));

            if (attributes.getQName(i).equalsIgnoreCase("type")) {
                doctype = attributes.getValue(i);
            }
            if (attributes.getQName(i).equalsIgnoreCase("delimeter")) {
                delimeter = attributes.getValue(i);
            }

        }

    }

    public void characters(char[] chars, int start, int length) throws SAXException {
        String charString = new String(chars, start, length);
        charString = charString.replaceAll("\n", "[cr]");
        charString = charString.replaceAll(" ", "[blank]");
        System.out.println(length + " Zeichen: " + charString);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End-Tag: " + qName);
    }

    public void endDocument() throws SAXException {
        System.out.println("Dokumentenende!");
    }
}
