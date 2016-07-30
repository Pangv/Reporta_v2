package de.fi.reporta.files;

import java.util.ArrayList;
import java.util.Map;

public class Document {

    private enum Doctypes {TXT, PDF, CSV}
    /////////////////////////////////////////////////////

    private ConfigDocument configuration;
    private RawDocument rawDocument;

    public ArrayList<String[]> allProcessedLines = new ArrayList<String[]>();
    public Map<Integer, String> keyValueStore = configuration.getAttributes();

    public void buildConfiguration(String filename) {
        this.configuration = new ConfigDocument(filename);
    }
    public void buildRaw(String filename) {
        this.rawDocument = new RawDocument(filename);
    }


    //public void

    public void processDocument() {
        String doctype = configuration.getDoctype();
        switch (Doctypes.valueOf(doctype)) {
            case TXT:
                for (int i = 0; i < rawDocument.getAllLines().size(); i++) {
                    String delimiter = configuration.getDelimiter();
                    allProcessedLines.add(rawDocument.getAllLines().get(i).split(delimiter));

                }




                for(String[] lines : allProcessedLines){
                    for (String line : lines){
                        System.out.print(line);
                    }
                    System.out.println();
                }
                break;
            case PDF:
                System.out.println("PDF document (to be added)");
                break;
            case CSV:
                System.out.println("CSV document (to be added)Ø");
                break;
            default:
                System.out.println("Not a valid document-format (type)");
                break;

        }


    }


}
