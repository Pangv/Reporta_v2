package de.fi.reporta.app;

import de.fi.reporta.files.Document;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 *
 */
public class Reporta {


    private static final boolean DEBUG = true;
    private static final boolean GUI = false;

    private static final String DEFAULT_LOCATION = "./assets";


    private static JFileChooser jFileChooser = new JFileChooser("Test");
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static Document doc1 = new Document();
    private static Document doc2 = new Document();

    public static void main(String[] args) {

        if (DEBUG) {
            System.out.println("Start in debug mode");
            start_wo_gui(true);
        } else if (GUI) {
            System.out.println("Start with user interface");
            // TODO: add GUI implementation
        } else {
            System.out.println("Start without user interface");
            start_wo_gui(false);
        }


    }

    /**
     * Launch Application without any user interface
     */
    private static void start_wo_gui(boolean debug) {


        if (debug) {
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
        } else {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


            try {

                // First Document
                System.out.print("Call the first configuration file: ");

                String filename = br.readLine();
                while (!new File("./assets" + File.separator + filename).exists()) {
                    System.err.print("Don't screw around, try again: ");
                    filename = br.readLine();
                }
                doc1.buildConfiguration(filename);
                System.out.println("Call the first data file: ");
                filename = br.readLine();
                while (!new File("./assets" + File.separator + filename).exists()) {
                    System.err.print("Don't screw around, try again: ");
                    filename = br.readLine();
                }
                doc1.buildRaw(filename);
                doc1.processDocument();
                doc1.fillKeyValueStore();


                // Second Document
                // First Document
                System.out.print("Call the second configuration file: ");

                filename = br.readLine();
                while (!new File("./assets" + File.separator + filename).exists()) {
                    System.err.print("Don't screw around, try again: ");
                    filename = br.readLine();
                }
                doc2.buildConfiguration(filename);
                System.out.println("Call the second data file: ");
                filename = br.readLine();
                while (!new File("./assets" + File.separator + filename).exists()) {
                    System.err.print("Don't screw around, try again: ");
                    filename = br.readLine();
                }
                doc2.buildRaw(filename);
                doc2.processDocument();
                doc2.fillKeyValueStore();

                Mapper mapper = new Mapper(doc1, doc2);
                mapper.generateOutputDocument();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
