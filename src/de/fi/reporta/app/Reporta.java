package de.fi.reporta.app;

import de.fi.reporta.files.Document;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;

public class Reporta {


    private static final boolean DEBUG = true;
    private static final boolean GUI = true;

    private static final String DEFAULT_LOCATION = "./assets";


    private static JFileChooser jFileChooser = new JFileChooser("Test");
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static Document doc1 = new Document();
    private static Document doc2 = new Document();
    
    public static void main(String[] args){

        if (DEBUG){
            System.out.println("Start in debug mode");
            start_wo_gui();
        }else if (GUI){
            System.out.println("Start with user interface");
            start_w_gui();
        }else {
            System.out.println("Start without user interface");
            start_wo_gui();
        }


    }


    private static void start_w_gui(){
        jFileChooser.showOpenDialog(null);
        jFileChooser.setCurrentDirectory(new File(DEFAULT_LOCATION));
        try {
            String filename = jFileChooser.getSelectedFile().toURI().toURL().toString();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    private static void start_wo_gui(){


        System.out.print("Call the first configuration file: ");
       try {

           doc1.buildConfiguration(br.readLine());
         //  doc1.buildRaw(br.readLine());
       } catch (IOException e){
           e.printStackTrace();
       }

    }

}
