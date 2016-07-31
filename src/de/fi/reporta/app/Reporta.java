package de.fi.reporta.app;

import de.fi.reporta.files.Document;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 */
public class Reporta {


    private static final boolean DEBUG = true;
    private static final boolean GUI = true;

    private static final String DEFAULT_LOCATION = "./assets";


    private static JFileChooser jFileChooser = new JFileChooser("Test");
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static Document doc1 = new Document();
    private static Document doc2 = new Document();

    public static void main(String[] args) {

        if (DEBUG) {
            System.out.println("Start in debug mode");
            start_wo_gui();
        } else if (GUI) {
            System.out.println("Start with user interface");
            // TODO: add GUI implementation
        } else {
            System.out.println("Start without user interface");
            start_wo_gui();
        }


    }

    /**
     * Launch Application without any user interface
     */
    private static void start_wo_gui() {


        System.out.print("Call the first configuration file: ");
        doc1.buildConfiguration("strukturA.xml");
        doc1.buildRaw("inputA.txt");
        doc1.processDocument();
        doc1.fillKeyValueStore();

        System.out.println("Call the second configuration file: ");
        doc2.buildConfiguration("strukturB.xml");
        doc2.buildRaw("inputB.txt");
        doc2.processDocument();
        doc2.fillKeyValueStore();

        Mapper mapper = new Mapper(doc1, doc2);
        mapper.generateOutputDocument();

    }

}
