package de.fi.reporta.app;

import de.fi.reporta.files.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Mapper {

    private final String LINE_SEPARATOR = System.getProperty("line.separator");

    private int newLinesCount = 0;
    private int deletedLinesCount = 0;
    private int changedLinesCount = 0;
    private int identicalLinesCount = 0;

    private int equalPositionsInLine = 0;
    private int unequalPositionsInLine = 0;

    private Document document1; // Base Document
    private Document document2; // Second Document

    private Map<Integer, Integer> keyPositions = new HashMap<Integer, Integer>();
    private Map<Integer, String> changedLines = new HashMap<Integer, String>();
    private Map<Integer, String> deletedLines = new HashMap<Integer, String>();
    private Map<Integer, String> newLines = new HashMap<Integer, String>();

    /**
     * @param documentBase base-document
     * @param documentScnd compared-document
     */
    Mapper(Document documentBase, Document documentScnd) {
        this.document1 = documentBase;
        this.document2 = documentScnd;
        compareKeyToValuePairs();


        int lineCount_base = document1.allProcessedLines.size();
        int lineCount_scnd = document2.allProcessedLines.size();
        int size = 0;

        if (lineCount_base != lineCount_scnd) {
            if (lineCount_base > lineCount_scnd) {
                deletedLinesCount = lineCount_base - lineCount_scnd;
                size = lineCount_scnd;
            }
            if (lineCount_base < lineCount_scnd) {
                newLinesCount = lineCount_scnd - lineCount_base;
                size = lineCount_base;
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.println("____ " + (i + 1) + ". Line ______");
            for (Map.Entry<Integer, Integer> entry : this.keyPositions.entrySet()) {
                compareProcessedLines(i, entry.getKey(), entry.getValue());
            }
        }
    }

    private void compareKeyToValuePairs() {
        for (Integer key1 : document1.keyValueStore.keySet()) {
            for (Integer key2 : document2.keyValueStore.keySet()) {

                String value_base = document1.keyValueStore.get(key1);
                String value_scnd = document2.keyValueStore.get(key2);

                if (value_base.equals(value_scnd)) {
                    System.out.println("SAME: " + value_base + " = " + value_scnd);
                    keyPositions.put(key1, key2);


                } else {
                    // System.out.println("DIFFER" + value_base + " != " + value_scnd);

                }

            }

        }
        System.out.println("==============");

    }

    private void compareProcessedLines(int line, int positionLineOne, int positionLineTwo) {

        String lineFromAllProcessedLineBase = document1.getLineFromAllProcessedLine(line, positionLineOne).trim();
        String lineFromAllProcessedLineScnd = document2.getLineFromAllProcessedLine(line, positionLineTwo).trim();

        //Handle BOM (Byte Order Marks)
        lineFromAllProcessedLineBase = lineFromAllProcessedLineBase.replaceAll("[\\uFEFF-\\uFFFF]", "");
        lineFromAllProcessedLineScnd = lineFromAllProcessedLineScnd.replaceAll("[\\uFEFF-\\uFFFF]", "");

        if (lineFromAllProcessedLineBase.equals(lineFromAllProcessedLineScnd)) {
            System.out.println("SAME: " + lineFromAllProcessedLineBase + " = " + lineFromAllProcessedLineScnd);
            ++equalPositionsInLine;

        } else {
            System.out.println("DIFFER: " + lineFromAllProcessedLineBase + " = " + lineFromAllProcessedLineScnd);
            ++unequalPositionsInLine;

        }

        if (equalPositionsInLine == document1.keyValueStore.size()) {
            identicalLinesCount++;
            equalPositionsInLine = 0; //reset
        }


    }

    /**
     * Generates the corresponding output
     */
    void generateOutputDocument() {
        File output = new File("./assets/test_output.txt");
        BufferedWriter bufferedWriter;
        try {
            if (!output.exists()) {
                output.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(output.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);
            String content =
                    "Anzahl Zeilen [Base:\t" + document1.rawDocument.getFilename() + "]: " + document1.allProcessedLines.size() + LINE_SEPARATOR
                            + "Anzahl Zeilen [Secondary:\t" + document2.rawDocument.getFilename() + "]: " + document2.allProcessedLines.size() + LINE_SEPARATOR
                            + "####################################" + LINE_SEPARATOR
                            + "Identische Zeilen: " + this.identicalLinesCount + LINE_SEPARATOR
                            + "Gelöschte Zeilen: " + this.deletedLinesCount + LINE_SEPARATOR
                            + "Geänderte Zeilen: " + this.changedLinesCount + LINE_SEPARATOR
                            + "Neue Zeilen: " + this.newLinesCount + LINE_SEPARATOR;
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
