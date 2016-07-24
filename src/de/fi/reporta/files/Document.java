package de.fi.reporta.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Document {

    private final String FILE_SEPARATOR = File.separator;
    // ///////////////////////////////////////////////////

    private ConfigDocument configuration;
    private RawDocument rawDocument;

    public void buildConfiguration(String filename) {

        configuration = new ConfigDocument(filename);

    }

    public void buildRaw() {
        rawDocument = new RawDocument();
    }

}
