package de.fi.reporta.xml;


import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigHandler extends DefaultHandler {

    private int c = 0;


    private String[] mainAttributes = new String[2];
    private Map<Integer, String> secondaryAttributes = new HashMap<Integer, String>();

    @SuppressWarnings("unused")
    public ConfigHandler() {}

    ConfigHandler(Map<Integer, String> attributes) {
        this.secondaryAttributes = attributes;
    }

    public String getMainAttributes(int index) {
        return this.mainAttributes[index];
    }

    Map<Integer, String> getSecondaryAttributes() {
        return this.secondaryAttributes;
    }


    private void addAttributes(int index, String value) {
        this.secondaryAttributes.put(index, value);
    }

    /*
    Basic SAX handling here:
     */
    public void startDocument() throws SAXException {
        System.out.println("Dokumentenstart!");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


        System.out.println("Start-Tag: " + qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.println("Attribut: " + attributes.getQName(i) + " = " + attributes.getValue(i));

            if (qName.equalsIgnoreCase("info")) {

                if (attributes.getQName(i).equalsIgnoreCase("type")) {
                    this.mainAttributes[0] = attributes.getValue(i);
                } else if (attributes.getQName(i).equalsIgnoreCase("delimeter")) {
                    this.mainAttributes[1] = attributes.getValue(i);
                }

            } else if (qName.equalsIgnoreCase("field")) {

                if (!attributes.getValue(i).equalsIgnoreCase("id")) {
                    addAttributes(c++, attributes.getValue(i));
                }
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
