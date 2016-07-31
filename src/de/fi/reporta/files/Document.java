package de.fi.reporta.files;

import java.util.ArrayList;
import java.util.Map;

public class Document {

    public RawDocument rawDocument;
    /////////////////////////////////////////////////////
    public ArrayList<String[]> allProcessedLines = new ArrayList<String[]>();
    public Map<Integer, String> keyValueStore;
    private ConfigDocument configuration;

    public void buildConfiguration(String filename) {
        this.configuration = new ConfigDocument(filename);
    }

    public void buildRaw(String filename) {
        this.rawDocument = new RawDocument(filename);
    }

    public String getLineFromAllProcessedLine(int line, int index) {
        return this.allProcessedLines.get(line)[index];
    }

    public void fillKeyValueStore() {
        keyValueStore = configuration.getAttributes();
    }

    public void processDocument() {
        String doctype = configuration.getDoctype();
        switch (Doctypes.valueOf(doctype)) {
            case TXT:
                for (int i = 0; i < rawDocument.getAllLines().size(); i++) {
                    String delimiter = configuration.getDelimiter();
                    allProcessedLines.add(rawDocument.getAllLines().get(i).split(delimiter));

                }

                for (String[] lines : allProcessedLines) {
                    for (String line : lines) {
                        System.out.print(line);
                    }
                    System.out.println();
                }
                break;
            case PDF:
                System.out.println("PDF document (to be added)");
                break;
            case CSV:
                System.out.println("CSV document (to be added)");
                break;
            default:
                System.out.println("Not a valid document-format (type)");
                break;

        }


    }

    private enum Doctypes {TXT, PDF, CSV}


}
