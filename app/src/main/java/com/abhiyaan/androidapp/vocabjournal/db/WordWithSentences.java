package com.abhiyaan.androidapp.vocabjournal.db;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binaya Bhattarai on 3/26/2018.
 */

public class WordWithSentences {

    @Embedded
    Word word;

    @Relation(parentColumn = "title", entityColumn = "word_id", entity = Sentence.class)
    List<Sentence> sentences = new ArrayList<>();

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }
}