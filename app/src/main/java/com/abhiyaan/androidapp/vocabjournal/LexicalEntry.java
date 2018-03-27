package com.abhiyaan.androidapp.vocabjournal;

import java.util.ArrayList;

/**
 * Created by Binaya Bhattarai on 3/18/2018.
 */

public class LexicalEntry {

    private String phoneticSpelling;
    private String pronunciationURL;
    private String lexicalCategory;
    private ArrayList<Definition> definitionArrayList;

    public LexicalEntry(String phoneticSpelling, String pronunciationURL,
                        String lexicalCategory, ArrayList<Definition>defs){
        this.phoneticSpelling = phoneticSpelling;
        this.pronunciationURL = pronunciationURL;
        this.lexicalCategory = lexicalCategory;
        this.definitionArrayList = defs;
    }

    public String toString(){
        String returnStr = this.lexicalCategory;
        returnStr += "\t\t" + this.getPhoneticSpelling();

        for (int i=1; i<=this.definitionArrayList.size(); i++){
            returnStr += ("\n" + i + ". " + definitionArrayList.get(i-1)+"\n");
        }

        return returnStr;
    }

    public String getPhoneticSpelling() {
        return phoneticSpelling;
    }

    public void setPhoneticSpelling(String phoneticSpelling) {
        this.phoneticSpelling = phoneticSpelling;
    }

    public String getPronunciationURL() {
        return pronunciationURL;
    }

    public void setPronunciationURL(String pronunciationURL) {
        this.pronunciationURL = pronunciationURL;
    }

    public String getLexicalCategory() {
        return lexicalCategory;
    }

    public void setLexicalCategory(String lexicalCategory) {
        this.lexicalCategory = lexicalCategory;
    }
}