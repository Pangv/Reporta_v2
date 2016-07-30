package de.fi.reporta.app;

import de.fi.reporta.files.Document;
import java.util.HashMap;
import java.util.Map;

public class Mapper {

    private Map<Integer, Integer> keyPositions = new HashMap<Integer, Integer>();
    private Document document1;
    private Document document2;


    private void addKeyPositions(){

        // TODO: add keys
        for (String item : document1.allProcessedLines){

        }

    }

    public void compareKeysToValuePairs(){
        // TODO: iterate for first key... for second key... for third key...

    }


    private int newLines;
    private int deletedLines;
    private int changedLines;
    private int identicalLines;






    Mapper(Document document_1, Document document_2){
        this.document1 = document_1;
        this.document2 = document_2;
    }

    public void mapDocuments(){

    }

    public void generateOutputDocument(){

    }


}
