package com.abhiyaan.androidapp.vocabjournal.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Binaya Bhattarai on 3/26/2018.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = Word.class,
                parentColumns = "title",
                childColumns = "word_id",
                onDelete = ForeignKey.CASCADE)})
public class Sentence {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String content;

    @NonNull
    @ColumnInfo(name = "word_id")
    private String wordId;

    public Sentence() {
    }

    @Ignore
    public Sentence(String content, String wordId) {
        this.content = content;
        this.wordId = wordId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @NonNull
    public String getWordId() {
        return wordId;
    }

    public void setWordId(@NonNull String wordId) {
        this.wordId = wordId;
    }
}
