package com.abhiyaan.androidapp.vocabjournal;

/**
 * Created by Binaya Bhattarai on 3/18/2018.
 */

public class Definition {

    public Definition(String definition, String example){
        this.definition = definition;
        this.example = example;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String toString(){
        String tmp = this.example != null? this.example: "!no example found!";
        return this.definition + "\n\t* " + tmp;
    }

    private String definition;
    private String example;
}
