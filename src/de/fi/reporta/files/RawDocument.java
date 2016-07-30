package de.fi.reporta.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RawDocument {

    private final String FILE_SEPARATOR = File.separator;
    private String filename;

    private ArrayList<String> allLines = new ArrayList<String>();

    public RawDocument(){}

    RawDocument(String filename){
        this.filename = filename;
        readRawDocument();
    }


    /**
     *
     */
    private void readRawDocument() {
        File rawFile = new File("assets" + FILE_SEPARATOR + filename);
        if (!rawFile.exists()) {
            System.err.println("The file with associated with the name " + filename + " does not exist.");
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(rawFile));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    allLines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    ArrayList<String> getAllLines(){
        return allLines;
    }

}
