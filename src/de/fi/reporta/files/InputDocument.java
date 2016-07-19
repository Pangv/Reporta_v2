package de.fi.reporta.files;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.fi.reporta.xml.Config;

public class InputDocument {

    
    private final String FILE_SEPERATOR = File.separator;
    private String filename;
    private File document;
    
    private Config configuration;
    private String[] inlineContent;
    private List<String[]> content = new ArrayList<String[]>();;
    
    private BufferedReader br;
    
    
   
    public void setConfiguration(Config configuration){
        this.configuration = configuration;
    }
    
    
    public void processDocument(){    
        document = new File(filename);
        String line;
        
        if(!document.exists()){
            System.err.println("There is no document called: '" + filename + "' !");
        }else {
            try {
                br = new BufferedReader(new FileReader(document));
                while ((line = br.readLine()) != null){
                    inlineContent = line.split(this.configuration.getDelimeter());
                    content.add(inlineContent);
                }
            } catch (IOException e) {
                // TODO: handle exception
            }
        }
        
        
    }
    
    
    public void setFilename(String filename) {
        this.filename = "assets" + FILE_SEPERATOR + filename;
    }
    



}
