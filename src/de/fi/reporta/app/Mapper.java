package de.fi.reporta.app;

import de.fi.reporta.files.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Mapper {

    private final String LINE_SEPARATOR = System.getProperty("line.separator");

    private int newLinesCount = 0;
    private int deletedLinesCount = 0;
    private int changedLinesCount = 0;
    private int identicalLinesCount = 0;

    private int equalPositionsInLine = 0;
    private int unequalPositionsInLine = 0;

    private Document documentBase; // Base Document
    private Document documentScnd; // Second Document

    private Map<Integer, Integer> keyPositions = new HashMap<Integer, Integer>();
    private Map<Integer, String> changedLines = new HashMap<Integer, String>();
    private List<String> deletedLines = new ArrayList<String>();
    private List<String> newLines = new ArrayList<String>();

    /**
     * @param documentBase base-document
     * @param documentScnd compared-document
     */
    Mapper(Document documentBase, Document documentScnd) {
        this.documentBase = documentBase;
        this.documentScnd = documentScnd;
        compareKeyToValuePairs();


        int lineCountBase = this.documentBase.allProcessedLines.size();
        int lineCountScnd = this.documentScnd.allProcessedLines.size();
        int size = 0;

        if (lineCountBase != lineCountScnd) {
            if (lineCountBase > lineCountScnd) {
                deletedLinesCount = lineCountBase - lineCountScnd;

                Collections.reverse(documentBase.allProcessedLines); // reverse
                String temp = "# ";
                for (int i = 0; i < deletedLinesCount; i++) {
                    for (int j = 0; i < documentBase.allProcessedLines.get(i).length; j++) {
                        temp += trimString(documentBase.allProcessedLines.get(i)[j]);
                    }
                    deletedLines.add(temp + LINE_SEPARATOR);
                    temp = "# "; // reset
                }


                size = lineCountScnd;
                Collections.reverse(documentBase.allProcessedLines); // reverse back

            }
            if (lineCountBase < lineCountScnd) {
                newLinesCount = lineCountScnd - lineCountBase;

                Collections.reverse(documentScnd.allProcessedLines);
                String temp = "# ";
                for (int i = 0; i < newLinesCount; i++) {
                    for (int j = 0; j < documentScnd.allProcessedLines.get(i).length; j++) {
                        temp += trimString(documentScnd.allProcessedLines.get(i)[j]);
                    }
                    newLines.add(temp + LINE_SEPARATOR);
                    temp = "# "; // reset
                }
                size = lineCountBase;
                Collections.reverse(documentScnd.allProcessedLines);
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
        for (Integer key1 : documentBase.keyValueStore.keySet()) {
            for (Integer key2 : documentScnd.keyValueStore.keySet()) {

                String value_base = documentBase.keyValueStore.get(key1);
                String value_scnd = documentScnd.keyValueStore.get(key2);

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

        String itemFromAllProcessedLinesBase = documentBase.getItemFromAllProcessedLines(line, positionLineOne).trim();
        String itemFromAllProcessedLinesScnd = documentScnd.getItemFromAllProcessedLines(line, positionLineTwo).trim();

        //Handle BOM (Byte Order Marks)
        itemFromAllProcessedLinesBase = itemFromAllProcessedLinesBase.replaceAll("[\\uFEFF-\\uFFFF]", "");
        itemFromAllProcessedLinesScnd = itemFromAllProcessedLinesScnd.replaceAll("[\\uFEFF-\\uFFFF]", "");

        if (itemFromAllProcessedLinesBase.equals(itemFromAllProcessedLinesScnd)) {
            System.out.println("SAME: " + itemFromAllProcessedLinesBase + " = " + itemFromAllProcessedLinesScnd);
            ++equalPositionsInLine;
        } else {
            // TODO add the right
            System.out.println("DIFFER: " + itemFromAllProcessedLinesBase + " = " + itemFromAllProcessedLinesScnd);
            ++unequalPositionsInLine;

            String lineBase = "";
            for (String strings : documentBase.allProcessedLines.get(line)) {
                lineBase += trimString(strings);
            }
            String lineScnd = "";
            for (String strings : documentScnd.allProcessedLines.get(line)) {
                lineScnd += trimString(strings);
            }
            changedLines.put(line, "# " + lineBase + "!=" + lineScnd + LINE_SEPARATOR);
        }

        if (equalPositionsInLine == documentBase.keyValueStore.size()) {
            System.out.println("Equal Line");
            identicalLinesCount++;
            equalPositionsInLine = 0; //reset
            unequalPositionsInLine = 0; // reset
        } else if ((equalPositionsInLine + unequalPositionsInLine) == documentBase.keyValueStore.size()) {
            System.out.println("Unequal Line");
            changedLinesCount++;
            equalPositionsInLine = 0;
            unequalPositionsInLine = 0;
        }
    }

    private String trimString(String strings) {
        return strings.trim().replaceAll("[\\uFEFF-\\uFFFF]", "") + " ";
    }

    private String stripStrings(Map<Integer, String> values) {
        String toReturn = "";
        for (String string : values.values()) {
            toReturn += string;
        }
        return toReturn;
    }

    private String stripStrings(List<String> values) {
        String toReturn = "";
        for (String string : values) {
            toReturn += string;
        }
        return toReturn;
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
                    "#Anzahl Zeilen [Base:\t\t" + documentBase.rawDocument.getFilename() + "]: " + documentBase.allProcessedLines.size() + LINE_SEPARATOR
                            + "#Anzahl Zeilen [Secondary:\t" + documentScnd.rawDocument.getFilename() + "]: " + documentScnd.allProcessedLines.size() + LINE_SEPARATOR
                            + "####################################" + LINE_SEPARATOR
                            + "# Identische Zeilen: " + this.identicalLinesCount + LINE_SEPARATOR
                            + "# Geänderte Zeilen: " + this.changedLinesCount + LINE_SEPARATOR
                            + "# ++++++++++++++++++++++++++++++++++++" + LINE_SEPARATOR
                            + stripStrings(changedLines) + LINE_SEPARATOR
                            + "# Gelöschte Zeilen: " + this.deletedLinesCount + " in [Base:\t\t" + documentBase.rawDocument.getFilename() + "]" + LINE_SEPARATOR
                            + "# ++++++++++++++++++++++++++++++++++++" + LINE_SEPARATOR
                            + stripStrings(deletedLines) + LINE_SEPARATOR
                            + "# ++++++++++++++++++++++++++++++++++++" + LINE_SEPARATOR
                            + "# Neue Zeilen: " + this.newLinesCount + " in [Secondary:\t" + documentScnd.rawDocument.getFilename() + "]" + LINE_SEPARATOR
                            + "# ++++++++++++++++++++++++++++++++++++" + LINE_SEPARATOR
                            + stripStrings(newLines) + LINE_SEPARATOR
                            + "# ++++++++++++++++++++++++++++++++++++" + LINE_SEPARATOR;
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
