package de.fi.reporta.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Document {

    private final String FILE_SEPERATOR = File.separator;
    // ///////////////////////////////////////////////////

    private ConfigDocument configuration;
    private RawDocument rawDocument;

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void buildConfiguration() {

        try {
            System.out.println("xml_Name: ");
            String filename = br.readLine();
            configuration = new ConfigDocument(filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void buildRaw() {
        rawDocument = new RawDocument();
    }

}
