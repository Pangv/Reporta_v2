package de.fi.reporta.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RawDocument {

    private final String FILE_SEPARATOR = File.separator;
    private String filename;
    private File rawFile;

    private String line;


    public RawDocument(){
        // empty
    }
    
    public RawDocument(String filename){
        this.filename = filename;
    }



    public void readRawDocument() {
        rawFile = new File("assets" + FILE_SEPARATOR + filename);

        if (!rawFile.exists()) {
            System.err.println("The file with associated with the name " + filename + " does not exist.");
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(rawFile));
                while ((line = br.readLine()) != null) {
                   
                    
//                    line = 
                    
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void setFilename(String filename) {
        this.filename = "assets" + FILE_SEPARATOR + filename;
    }

}
