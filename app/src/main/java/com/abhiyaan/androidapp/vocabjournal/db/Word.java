package com.abhiyaan.androidapp.vocabjournal.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Binaya Bhattarai on 3/26/2018.
 */
@Entity
public class Word {

    @PrimaryKey
    @NonNull
    private String title;

    @NonNull
    private String definition;

    @NonNull
    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "created_on")
    private Date createdOn;

    public Word(){
        this.createdOn = new Date();
    }

    @Ignore
    public Word(String title, String definition){
        this.title = title;
        this.definition = definition;
        this.createdOn = new Date();
    }

    @Ignore
    public Word(String title, String definition, Date createdOn){
        this(title, definition);
        this.createdOn = createdOn;
    }

    public String getCreatedOnAsString(){
        return createdOn.toString();
    }

    @Override
    public String toString(){
        return this.title + "\n\n" + this.definition + "\n";
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(@NonNull String definition) {
        this.definition = definition;
    }

    @NonNull
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(@NonNull Date createdOn) {
        this.createdOn = createdOn;
    }
}
